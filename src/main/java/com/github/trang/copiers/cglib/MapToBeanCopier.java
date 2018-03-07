package com.github.trang.copiers.cglib;

import com.github.trang.copiers.adapter.AbstractCopier;
import com.github.trang.copiers.exception.CopierException;
import com.github.trang.copiers.util.ReflectionUtil;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

/**
 * Map 转换为 JavaBean
 *
 * @author trang
 */
public class MapToBeanCopier<T> extends AbstractCopier<BeanMap, Map<String, Object>, T> {

    public MapToBeanCopier(Class<T> beanClass) {
        checkNotNull(beanClass, "bean class cannot be null!");
        this.targetClass = beanClass;
    }

    @Override
    public T copy(Map<String, Object> map) {
        checkNotNull(map, "map cannot be null!");
        try {
            T bean = ReflectionUtil.newInstance(targetClass);
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
            return bean;
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(Map<String, Object> map, T bean) {
        checkNotNull(map, "map cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

}