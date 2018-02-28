package com.github.trang.copiers.cglib;

import com.baidu.unbiz.easymapper.util.ReflectionUtil;
import com.github.trang.copiers.adapter.CopierAdapter;
import com.github.trang.copiers.exception.CopierException;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;

/**
 * Map 转换为 JavaBean
 *
 * @author trang
 */
public class MapToBeanCopier<T> extends CopierAdapter<BeanMap, Map<String, Object>, T> {

    public MapToBeanCopier(Class<T> beanClass) {
        this.targetClass = beanClass;
    }

    @Override
    public T copy(Map<String, Object> map) {
        checkNull(map, "map cannot be null!");
        checkNull(targetClass, "target class cannot be null!");
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
        checkNull(map, "map cannot be null!");
        checkNull(targetClass, "target bean cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

}