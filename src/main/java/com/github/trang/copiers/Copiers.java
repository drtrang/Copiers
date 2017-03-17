package com.github.trang.copiers;

import com.github.trang.copiers.cglib.BeanToMapCopier;
import com.github.trang.copiers.cglib.CglibCopier;
import com.github.trang.copiers.cglib.MapToBeanCopier;
import com.github.trang.copiers.mapper.MapperCopier;
import com.github.trang.copiers.mapper.MapperCopierSupport;

/**
 * Copier工具类，可根据需要替换底层实现
 * 目前有两种实现：cglib & easy mapper
 *
 * @author trang
 */
public final class Copiers {

    private Copiers() {
        throw new UnsupportedOperationException();
    }

    /**
     * 基于EasyMapper实现的基础拷贝，满足基本需求
     *
     * @param sourceClass
     * @param targetClass
     * @return copier
     */
    public static <F, T> MapperCopier<F, T> create(Class<F> sourceClass, Class<T> targetClass) {
        return new MapperCopier<>(sourceClass, targetClass);
    }

    /**
     * 基于EasyMapper实现的自定义拷贝，满足复杂需求
     *
     * @param sourceClass
     * @param targetClass
     * @return copier
     */
    public static <F, T> MapperCopierSupport<F, T> createMapper(Class<F> sourceClass, Class<T> targetClass) {
        return new MapperCopierSupport<>(sourceClass, targetClass);
    }

    /**
     * 基于Cglib实现的拷贝，不能自定义，拷贝跟原对象一模一样的对象
     *
     * @param sourceClass
     * @param targetClass
     * @return copier
     */
    public static <F, T> CglibCopier<F, T> createCglib(Class<F> sourceClass, Class<T> targetClass) {
        return new CglibCopier<>(sourceClass, targetClass);
    }

    /**
     * 创建Bean与Map之间的映射关系
     *
     * @param beanClass
     * @param <F>
     * @return copier
     */
    public static <F> BeanToMapCopier<F> createBeanToMap(Class<F> beanClass) {
        return new BeanToMapCopier<>();
    }

    /**
     * 创建Map与Bean之间的映射关系
     *
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> MapToBeanCopier<T> createMapToBean(Class<T> beanClass) {
        return new MapToBeanCopier<>(beanClass);
    }
}