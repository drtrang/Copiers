package com.github.trang.copiers.inter;

import java.util.List;

/**
 * 拷贝接口
 *
 * @author trang
 */
public interface Copier<F, T> {

    /**
     * 将 source 对象拷贝到新对象
     *
     * @param source 源对象
     * @return 目标对象
     */
    T copy(F source);

    /**
     * 将 source 对象拷贝到 target 对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    void copy(F source, T target);

    /**
     * 将 source 对象集合拷贝到新集合
     *
     * @param sourceList 源对象集合
     * @return 目标对象集合
     */
    List<T> map(List<F> sourceList);

}