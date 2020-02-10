package com.gemini.always.experimentmanagementsystem.util;

import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 20:10 2020/2/9
 */
public class ReflectUtil {
    /**
     * 通过类名和字段名，通过反射获取对象的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
        return null;
    }

    /**
     * 通过类名和字段名，通过反射获取对象的set方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
        return null;
    }

    /**
     * 调用getSetMethod，直接使用对象的set方法
     *
     * @param o
     * @param fieldName
     * @param value
     */
    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[]{value});
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
    }

    /**
     * 调用getGetMethod，直接使用对象的set方法
     *
     * @param o
     * @param fieldName
     * @return
     */
    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
        return null;
    }
}
