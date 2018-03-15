package com.github.trang.copiers.cglib;

import com.github.trang.copiers.AbstractCopier;
import com.github.trang.copiers.exception.CopierException;
import net.sf.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

/**
 * JavaBean 转换为 Map
 *
 * @author trang
 */
public class BeanToMapCopier<F> extends AbstractCopier<BeanMap, F, Map<String, Object>> {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> copy(F bean) {
        checkNotNull(bean, "source bean cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            return new HashMap<>(beanMap);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void copy(F bean, Map<String, Object> map) {
        checkNotNull(bean, "source bean cannot be null!");
        checkNotNull(map, "map cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            map.putAll(beanMap);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

}