package com.github.trang.copiers;

import com.github.trang.copiers.cglib.BeanToMapCopier;
import com.github.trang.copiers.cglib.MapToBeanCopier;

import java.util.List;
import java.util.Map;

/**
 * Bean 与 Map 之间相互拷贝
 *
 * @author trang
 */
public final class BeanMapCopier {

    private BeanMapCopier() {
        throw new UnsupportedOperationException();
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        return new BeanToMapCopier<>().copy(bean);
    }

    public static <T> void beanToMap(T bean, Map<String, Object> map) {
        new BeanToMapCopier<>().copy(bean, map);
    }

    public static <T> List<Map<String, Object>> beansToMap(List<T> list) {
        return new BeanToMapCopier<T>().map(list);
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
        return new MapToBeanCopier<>(beanClass).copy(map);
    }

    public static <T> void mapToBean(Map<String, Object> map, T bean) {
        Class beanClass = bean.getClass();
        new MapToBeanCopier<T>(beanClass).copy(map, bean);
    }
    public static <T> List<T> mapToBeans(List<Map<String, Object>> list, Class<T> beanClass) {
        return new MapToBeanCopier<>(beanClass).map(list);
    }
}
