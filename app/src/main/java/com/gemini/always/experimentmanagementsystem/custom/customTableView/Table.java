package com.gemini.always.experimentmanagementsystem.custom.customTableView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 15:33 2020/2/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {
    String name() default "";

    boolean count() default false;
}
