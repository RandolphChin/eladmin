package com.laboratory.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件
 * Created by jinjin on 2020-09-21.
 */
@Configuration
@MapperScan(basePackages ={"com.laboratory.**.mapper"})
public class MybatisPlusConfig {
    @Autowired
    private DataScopePermissionHandler dataScopePermissionHandler;

    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor paginationInterceptor = new MybatisPlusInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        //paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));

        // 添加数据权限插件
        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
        // 添加自定义的数据权限处理器
        dataPermissionInterceptor.setDataPermissionHandler(dataScopePermissionHandler);
        paginationInterceptor.addInnerInterceptor(dataPermissionInterceptor);

        PaginationInnerInterceptor page = new PaginationInnerInterceptor();
        paginationInterceptor.addInnerInterceptor(page);
        return paginationInterceptor;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MybatisPlusFillHandler());
        return globalConfig;
    }
}
