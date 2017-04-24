package com.github.trang.copiers;

import com.baidu.unbiz.easymapper.util.ReflectionUtil;
import net.sf.cglib.beans.BeanMap;

import java.util.*;

/**
 * JavaBean 与 Map 互相转换
 *
 * @author trang
 */
public abstract class BeanUtil {
    /**
     * 将bean转换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        if (bean == null) {
            return Collections.emptyMap();
        }
        BeanMap beanMap = BeanMap.create(bean);
        return new HashMap<>(beanMap);
    }

    /**
     * 将map转换为bean对象
     * 
     * @param map
     * @param beanClass
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
        if (map == null || map.isEmpty()) {
            throw new NullPointerException("map cannot be null!");
        } else if (beanClass == null) {
            throw new NullPointerException("source class cannot be null!");
        }
        T bean = ReflectionUtil.newInstance(beanClass);
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
    
    /**
     * 将map转换为bean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        if (map == null || map.isEmpty()) {
            throw new NullPointerException("map cannot be null!");
        } else if (bean == null) {
            throw new NullPointerException("target bean cannot be null!");
        }
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param beans
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMap(List<T> beans) {
        if (beans == null || beans.isEmpty()) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (T bean : beans) {
            Map<String, Object> map = beanToMap(bean);
            list.add(map);
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param beanClass
     * @return
     */
    public static <T> List<T> mapToBeans(List<Map<String, Object>> maps, Class<T> beanClass) {
        if (maps == null || maps.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            T bean = mapToBean(map, beanClass);
            list.add(bean);
        }
        return list;
    }
}
