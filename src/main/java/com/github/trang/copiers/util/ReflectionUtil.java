package com.github.trang.copiers.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;

import lombok.extern.slf4j.Slf4j;

/**
 * 反射工具类
 *
 * @author zhangxu
 */
@Slf4j
public final class ReflectionUtil {

    private ReflectionUtil() {

    }

    /**
     * 使用反射新建一个对象，尽全力去新建，如果没有默认构造方法也支持
     *
     * @param clazz 类型
     * @param <T>   T
     * @return 对象
     */
    public static <T> T newInstance(final Class<T> clazz) {
        Constructor<?>[] constructors = getAllConstructorsOfClass(clazz, true);
        if (constructors == null || constructors.length == 0) {
            return null;
        }
        Object[] initParameters = getInitParameters(constructors[0].getParameterTypes());
        try {
            @SuppressWarnings("unchecked")
            T instance = (T) constructors[0].newInstance(initParameters);
            return instance;
        } catch (Exception e) {
            log.error("newInstance", e);
            return null;
        }
    }

    /**
     * 获取某个类型的所有构造方法
     *
     * @param clazz      类型
     * @param accessible 是否可以访问
     * @return 构造方法数组
     */
    public static Constructor<?>[] getAllConstructorsOfClass(final Class<?> clazz, boolean accessible) {
        if (clazz == null) {
            return null;
        }
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors != null && constructors.length > 0) {
            AccessibleObject.setAccessible(constructors, accessible);
        }
        return constructors;
    }

    /**
     * 获取默认参数
     *
     * @param parameterTypes 参数类型数组
     * @return 参数值数组
     */
    private static Object[] getInitParameters(Class<?>[] parameterTypes) {
        int length = parameterTypes.length;
        Object[] result = new Object[length];
        for (int i = 0; i < length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                Object init = ClassUtil.getPrimitiveDefaultValue(parameterTypes[i]);
                result[i] = init;
                continue;
            }
            result[i] = null;
        }
        return result;
    }

}