package com.github.trang.copiers;

import com.github.trang.copiers.cglib.BeanToMapCopier;
import com.github.trang.copiers.cglib.MapToBeanCopier;

import java.util.List;
import java.util.Map;

/**
 * MapCopiers 工具类，提供 Map 和 JavaBean 之间的相互拷贝
 *
 * @author trang
 */
public final class MapCopiers {

    private MapCopiers() {
        throw new UnsupportedOperationException();
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