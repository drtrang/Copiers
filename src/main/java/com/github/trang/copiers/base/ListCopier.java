package com.github.trang.copiers.base;

import java.util.List;

/**
 * List 拷贝底层接口
 *
 * @author trang
 */
public interface ListCopier<F, T> extends BeanCopier<F, T> {

    /**
     * 将 sourceList 拷贝到新集合，使用 ArrayList
     *
     * @param sourceList 源对象集合
     * @return 目标对象集合
     */
    List<T> copyList(List<F> sourceList);

}