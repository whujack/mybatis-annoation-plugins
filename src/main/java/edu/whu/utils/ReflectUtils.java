package edu.whu.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Created By LiJie at 2018/03/16
 * <p>
 * 反射调用方法
 */
public class ReflectUtils {

    public static Object invokeSetter(Object object, String name, Class clazz,Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getMethod(name, clazz);
        return method.invoke(object, params);
    }
}
