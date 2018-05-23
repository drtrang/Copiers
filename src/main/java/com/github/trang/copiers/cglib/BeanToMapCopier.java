package com.github.trang.copiers.cglib;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import com.github.trang.copiers.AbstractCopier;
import com.github.trang.copiers.exception.CopierException;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;

/**
 * JavaBean 转换为 Map
 *
 * @author trang
 */
@Slf4j(topic = "copiers")
public class BeanToMapCopier<F> extends AbstractCopier<BeanMap, F, Map<String,Object>> {

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