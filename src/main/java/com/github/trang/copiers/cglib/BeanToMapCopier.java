package com.github.trang.copiers.cglib;

import com.github.trang.copiers.adapter.CopierAdapter;
import com.github.trang.copiers.exception.CopierException;
import net.sf.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * JavaBean 转换为 Map
 *
 * @author trang
 */
public class BeanToMapCopier<F> extends CopierAdapter<BeanMap, F, Map<String, Object>> {

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> copy(F bean) {
        checkNull(bean, "source bean cannot be null!");
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
        checkNull(bean, "source bean cannot be null!");
        checkNull(map, "map cannot be null!");
        try {
            BeanMap beanMap = BeanMap.create(bean);
            map.putAll(beanMap);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + bean.getClass().getName(), e);
        }
    }

}