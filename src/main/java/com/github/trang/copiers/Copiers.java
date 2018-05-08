package com.github.trang.copiers;

import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.orika.OrikaCopier;

import net.sf.cglib.core.Converter;

/**
 * Copiers 工具类，通过工厂方法创建 #{@link Copier} 对象
 * 
 * 目前有两种实现：Cglib & Orika
 *
 * @author trang
 */
public final class Copiers {

    private Copiers() {
        throw new UnsupportedOperationException();
    }

    /**
     * 基于 Orika 实现的简单拷贝，满足基本需求
     *
     * @param sourceClass 源对象类型
     * @param targetClass 目标对象类型
     * @return copier
     */
    public static <F, T> Copier<F, T> create(Class<F> sourceClass, Class<T> targetClass) {
        return CopierFactory.getOrCreateOrikaCopier(sourceClass, targetClass);
    }

    /**
     * 基于 Orika 实现的高级拷贝，满足复杂需求
     *
     * @param sourceClass 源对象类型
     * @param targetClass 目标对象类型
     * @return copier
     */
    public static <F, T> OrikaCopier.Builder<F, T> createOrika(Class<F> sourceClass, Class<T> targetClass) {
        return new OrikaCopier.Builder<>(sourceClass, targetClass);
    }

    /**
     * 基于 Cglib 实现的拷贝，不支持 Converter
     *
     * @param sourceClass 源对象类型
     * @param targetClass 目标对象类型
     * @return copier
     */
    public static <F, T> Copier<F, T> createCglib(Class<F> sourceClass, Class<T> targetClass) {
        return CopierFactory.getOrCreateCglibCopier(sourceClass, targetClass);
    }

    /**
     * 基于 Cglib 实现的拷贝，支持 Converter
     *
     * @param sourceClass 源对象类型
     * @param targetClass 目标对象类型
     * @param converter   转换器
     * @return copier
     */
    public static <F, T> Copier<F, T> createCglib(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        return CopierFactory.getOrCreateCglibCopier(sourceClass, targetClass, converter);
    }

}