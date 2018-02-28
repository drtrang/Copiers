package com.github.trang.copiers;

import com.github.trang.copiers.cglib.BeanToMapCopier;
import com.github.trang.copiers.cglib.CglibCopier;
import com.github.trang.copiers.cglib.MapToBeanCopier;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.mapper.MapperCopier;
import com.github.trang.copiers.mapper.MapperCopier.Builder;
import net.sf.cglib.core.Converter;

import java.util.List;
import java.util.Map;

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

    /**
     * 单个 Bean 转换为 Map
     *
     * @param bean 源对象
     * @return map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        return new BeanToMapCopier<>().copy(bean);
    }

    /**
     * 单个 Bean 转换为已经存在的 Map
     *
     * @param bean 源对象
     * @param map  map
     */
    public static <T> void beanToMap(T bean, Map<String, Object> map) {
        new BeanToMapCopier<>().copy(bean, map);
    }

    /**
     * Bean List 转换为 Map List
     *
     * @param list 源对象集合
     * @return list
     */
    public static <T> List<Map<String, Object>> beansToMap(List<T> list) {
        return new BeanToMapCopier<T>().map(list);
    }

    /**
     * Map 转换为 Bean
     *
     * @param map       map
     * @param beanClass 目标类型
     * @return bean
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
        return new MapToBeanCopier<>(beanClass).copy(map);
    }

    /**
     * Map 转换为已经存在的 Bean
     *
     * @param map  map
     * @param bean 目标对象
     */
    @SuppressWarnings("unchecked")
    public static <T> void mapToBean(Map<String, Object> map, T bean) {
        Class<T> beanClass = ((Class<T>) bean.getClass());
        new MapToBeanCopier<>(beanClass).copy(map, bean);
    }

    /**
     * Map List 转换为 Bean List
     *
     * @param list map 集合
     * @return list
     */
    public static <T> List<T> mapToBeans(List<Map<String, Object>> list, Class<T> beanClass) {
        return new MapToBeanCopier<>(beanClass).map(list);
    }

}