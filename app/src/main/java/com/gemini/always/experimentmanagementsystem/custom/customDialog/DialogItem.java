package com.gemini.always.experimentmanagementsystem.custom.customDialog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DialogItem {

    int TYPE_SPINNER = 0;
    int TYPE_EDITTEXT = 1;

    /**
     * 名称
     */
    String name();

    /**
     * id 越小越在前面
     */
    int id();

    int type() default TYPE_EDITTEXT;

    String hint() default "";
}
