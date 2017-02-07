package com.github.trang.copiers.inter;

import java.util.List;

/**
 * 实际执行拷贝的抽象封装
 *
 * @author trang
 */
public interface Copier<F, T> {
    T copy(F source);

    void copy(F source, T target);

    List<T> map(List<F> sourceList);
}