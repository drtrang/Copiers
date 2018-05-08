package com.github.trang.copiers;

import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.cglib.BeanToMapCopier;
import com.github.trang.copiers.cglib.MapToBeanCopier;

/**
 * MapCopiers 工具类，通过工厂方法创建 #{@link Copier} 对象
 *
 * @author trang
 */
public final class MapCopiers {

    private MapCopiers() {
        throw new UnsupportedOperationException();
    }

    /**
     * JavaBean 转换为 Map
     *
     * @param <F> 源对象范型
     * @return copier
     */
    public static <F> BeanToMapCopier<F> createBeanToMap() {
        return new BeanToMapCopier<>();
    }

    /**
     * Map 转换为 JavaBean
     *
     * @param targetClass 目标对象类型
     * @param <T>         目标对象范型
     * @return copier
     */
    public static <T> MapToBeanCopier<T> createMapToBean(Class<T> targetClass) {
        return new MapToBeanCopier<>(targetClass);
    }

}