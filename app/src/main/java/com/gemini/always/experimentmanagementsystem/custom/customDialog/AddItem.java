package com.gemini.always.experimentmanagementsystem.custom.customDialog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AddItem {
    /**
     * 名称
     */
    String name();

    /**
     * id 越小越在前面
     */
    int id();
}
