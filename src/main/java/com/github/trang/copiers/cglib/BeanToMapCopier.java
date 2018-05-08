package com.github.trang.copiers.cglib;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.exception.CopierException;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;

/**
 * JavaBean 转换为 Map
 *
 * @author trang
 */
@Slf4j(topic = "copiers")
public class BeanToMapCopier<F> implements Copier<F, Map<String, Object>> {

    @Override
    public Map<String, Object> copy(F bean) {
        return copy(bean, HashMap::new);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> copy(F bean, Supplier<Map<String, Object>> mapFactory) {
        checkNotNull(bean, "source bean cannot be null!");
        checkNotNull(mapFactory, "map factory cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            Map<String, Object> map = mapFactory.get();
            map.putAll(beanMap);
            return map;
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void copy(F bean, Map<String, Object> target) {
        checkNotNull(bean, "source bean cannot be null!");
        checkNotNull(target, "target map cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            target.putAll(beanMap);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

}