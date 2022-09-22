package com.laboratory.config;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.laboratory.annotation.DataScope;
import com.laboratory.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Slf4j
@Component
public class DataScopePermissionHandler implements DataPermissionHandler {

    /**
     * 通过ThreadLocal记录权限相关的属性值
     */
    ThreadLocal<DataScopeParam> threadLocal = new ThreadLocal<>();

    /**
     * 清空当前线程上次保存的权限信息
     */
    @After("dataScopePointCut()")
    public void clearThreadLocal(){
        threadLocal.remove();
        log.debug("threadLocal.remove()");
    }

    /**
     * 注解对象
     * 该成员变量在并发情况下导致多个线程公用了一套 @DataScope 配置的参数，导致sql拼接失败，必须改为局部变量
     * 修改于：2022.04.28
     */
//    private DataScope controllerDataScope;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.laboratory.annotation.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) {
        // 获得注解
        DataScope controllerDataScope = getAnnotationLog(point);
        if (controllerDataScope != null) {
            // 获取当前的用户及相关属性，需提前获取和保存数据权限对应的部门ID集合
            Boolean isAdmin = SecurityUtils.currentUserIsAdmin();
            List<Long> dataScopes = SecurityUtils.getCurrentUserDataScope();
            DataScopeParam dataScopeParam = new DataScopeParam(controllerDataScope.deptAlias(),
                    controllerDataScope.deptField(),
                    isAdmin,
                    dataScopes.stream().collect(Collectors.toSet()));
            threadLocal.set(dataScopeParam);
            log.debug("currentUser.getDataScope() = {}", dataScopes);
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }

    /**
     * @param where             原SQL Where 条件表达式
     * @param mappedStatementId Mapper接口方法ID
     * @return
     */
    @SneakyThrows
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        log.debug("DataScopePermissionHandler .getSqlSegment");
        DataScopeParam dataScopeParam = threadLocal.get();
        if(dataScopeParam == null || dataScopeParam.isAdmin()){
            return where;
        }

        if (where == null) {
            where = new HexValue(" 1 = 1 ");
        }

        String deptSql = "".equals(dataScopeParam.deptAlias) ? dataScopeParam.deptField : dataScopeParam.deptAlias + "." + dataScopeParam.deptField;

        // 把集合转变为JSQLParser需要的元素列表
        ItemsList itemsList;
        if(CollectionUtil.isEmpty(dataScopeParam.secretary)){
            //如果权限为空，则只能看自己部门的
            Long deptId = SecurityUtils.getCurrentUserDeptId();
            itemsList = new ExpressionList(Collections.singletonList(new LongValue(deptId)));
        }else {
            //查看权限内的数据
            itemsList = new ExpressionList(dataScopeParam.secretary.stream().map(LongValue::new).collect(Collectors.toList()));
        }
        InExpression inExpression = new InExpression(new Column(deptSql), itemsList);
        log.debug("where = {}", where);
        log.debug("inExpression = {}", inExpression);
        return new AndExpression(where, inExpression);

    }

    /**
     * ThreadLocal存储对象
     */
    @Data
    @AllArgsConstructor
    static class DataScopeParam{
        /**
         * 部门表的别名
         */
        private String deptAlias;

        /**
         * 部门字段名
         */
        private String deptField;

        /**
         * 是否是管理员
         */
        private boolean isAdmin;

        /**
         * 数据权限范围
         */
        private Set<Long> secretary;
    }
}
