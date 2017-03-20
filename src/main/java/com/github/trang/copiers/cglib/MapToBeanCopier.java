package com.github.trang.copiers.cglib;

import com.baidu.unbiz.easymapper.util.ReflectionUtil;
import com.github.trang.copiers.adapter.CopierAdapter;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;

/**
 * Map 转换为 Bean
 *
 * @author trang
 */
public class MapToBeanCopier<T> extends CopierAdapter<BeanMap, Map<String, Object>, T> {

    public MapToBeanCopier(Class<T> beanClass) {
        this.targetClass = beanClass;
    }

    @Override
    public T copy(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            throw new NullPointerException("map cannot be null!");
        } else if (targetClass == null) {
            throw new NullPointerException("target class cannot be null!");
        }
        try {
            T bean = ReflectionUtil.newInstance(targetClass);
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(Map<String, Object> map, T bean) {
        if (map == null || map.isEmpty()) {
            throw new NullPointerException("map cannot be null!");
        } else if (bean == null) {
            throw new NullPointerException("target bean cannot be null!");
        }
        try {
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + bean.getClass().getName(), e);
        }
    }
}
