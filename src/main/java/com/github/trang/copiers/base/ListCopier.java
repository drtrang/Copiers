package com.github.trang.copiers.base;

import java.util.List;

/**
 * 拷贝集合底层接口
 *
 * @author trang
 */
public interface ListCopier<F, T> extends CollectionCopier<F, T> {

    /**
     * 将 source 对象集合拷贝到新集合
     *
     * @param sourceList 源对象集合
     * @return 目标对象集合
     */
    List<T> map(List<F> sourceList);

}