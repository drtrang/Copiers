package com.github.trang.copiers.base;

import java.util.Set;

/**
 * 拷贝集合底层接口
 *
 * @author trang
 */
public interface SetCopier<F, T> {

    /**
     * 将 sourceSet 拷贝到新集合
     *
     * @param sourceSet 源对象集合
     * @return 目标对象集合
     */
    Set<T> map(Set<F> sourceSet);

}