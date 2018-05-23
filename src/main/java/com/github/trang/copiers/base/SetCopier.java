package com.github.trang.copiers.base;

import java.util.Set;

/**
 * Set 拷贝底层接口
 *
 * @author trang
 */
public interface SetCopier<F, T> extends BeanCopier<F, T> {

    /**
     * 将 sourceSet 拷贝到新集合，使用 HashSet
     *
     * @param sourceSet 源对象集合
     * @return 目标对象集合
     */
    Set<T> copySet(Set<F> sourceSet);

}