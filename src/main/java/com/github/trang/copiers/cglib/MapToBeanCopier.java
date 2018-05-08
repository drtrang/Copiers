package com.github.trang.copiers.cglib;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

import java.util.Map;

import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.exception.CopierException;
import com.github.trang.copiers.util.ReflectionUtil;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;

/**
 * Map 转换为 JavaBean
 *
 * @author trang
 */
@Slf4j(topic = "copiers")
public class MapToBeanCopier<T> implements Copier<Map<String, Object>, T> {

    private final Class<T> targetClass;

    public MapToBeanCopier(Class<T> targetClass) {
        checkNotNull(targetClass, "target class cannot be null!");
        this.targetClass = targetClass;
    }

    @Override
    public T copy(Map<String, Object> source) {
        checkNotNull(source, "source map cannot be null!");
        try {
            T bean = ReflectionUtil.newInstance(targetClass);
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(source);
            return bean;
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(Map<String, Object> source, T bean) {
        checkNotNull(source, "source map cannot be null!");
        checkNotNull(bean, "target bean cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(source);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

}