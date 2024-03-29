package com.laboratory.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 部门字段名
     */
    String deptField() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
    /**
     * 用户字段名
     */
    String userField() default "";
}
