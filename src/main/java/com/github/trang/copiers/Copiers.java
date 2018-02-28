package com.github.trang.copiers;

import com.github.trang.copiers.cglib.CglibCopier;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.mapper.MapperCopier;
import com.github.trang.copiers.mapper.MapperCopier.Builder;
import net.sf.cglib.core.Converter;

/**
 * Copiers 工具类，通过工厂方法创建 #{@link Copier} 对象
 * 目前有两种实现：Cglib & EasyMapper
 *
 * @author trang
 */
public final class Copiers {

    private Copiers() {
        throw new UnsupportedOperationException();
    }

    /**
     * 基于 EasyMapper 实现的简单拷贝，满足基本需求
     *
     * @param sourceClass 源类型
     * @param targetClass 目标类型
     * @return copier
     */
    public static <F, T> Copier<F, T> create(Class<F> sourceClass, Class<T> targetClass) {
        return new MapperCopier<>(sourceClass, targetClass);
    }

    /**
     * 基于 EasyMapper 实现的高级拷贝，满足复杂需求
     *
     * @param sourceClass 源类型
     * @param targetClass 目标类型
     * @return copier
     */
    public static <F, T> Builder<F, T> createMapper(Class<F> sourceClass, Class<T> targetClass) {
        return new MapperCopier.Builder<>(sourceClass, targetClass);
    }

    /**
     * 基于 Cglib 实现的拷贝，不能自定义，拷贝跟原对象一模一样的对象
     *
     * @param sourceClass 源类型
     * @param targetClass 目标类型
     * @return copier
     */
    public static <F, T> Copier<F, T> createCglib(Class<F> sourceClass, Class<T> targetClass) {
        return new CglibCopier<>(sourceClass, targetClass);
    }

    /**
     * 基于 Cglib 实现的拷贝，不能自定义，拷贝跟原对象一模一样的对象
     *
     * @param sourceClass 源类型
     * @param targetClass 目标类型
     * @param converter   转换器
     * @return copier
     */
    public static <F, T> Copier<F, T> createCglib(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        return new CglibCopier<>(sourceClass, targetClass, converter);
    }

}