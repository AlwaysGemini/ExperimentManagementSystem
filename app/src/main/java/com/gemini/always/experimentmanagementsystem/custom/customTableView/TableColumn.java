package com.gemini.always.experimentmanagementsystem.custom.customTableView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 15:34 2020/2/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableColumn {
    /**
     * 名称
     */
    String name() default "";

    /**
     * id 越小越在前面
     */
    int id() default 0;
}
