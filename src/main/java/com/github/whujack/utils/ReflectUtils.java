package com.github.whujack.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * @author Created By LiJie at 2018/03/16
 * <p>
 * 反射调用方法
 */
public class ReflectUtils {

    /**
     * 反射调用setter实现批量setter
     * @param object 对象
     * @param name 方法名
     * @param clazz 参数类型
     * @param params 参数
     * @return setter后的对象
     * @throws NoSuchMethodException 找不到方法
     * @throws InvocationTargetException 调用异常
     * @throws IllegalAccessException 非法异常
     */
    public static Object invokeSetter(Object object, String name, Class clazz,Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getMethod(name, clazz);
        return method.invoke(object, params);
    }
}
