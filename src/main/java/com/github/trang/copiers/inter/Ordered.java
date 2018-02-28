package com.github.trang.copiers.inter;

/**
 * 有序接口
 *
 * @author trang
 */
public interface Ordered<F, T> {

    /**
     * 开启顺序拷贝
     *
     * @return copier
     */
    Copier<F, T> ordered();

}