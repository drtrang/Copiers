package com.github.trang.copiers.inter;

/**
 * 并行接口，继承 #{@link Ordered}
 *
 * @author trang
 */
public interface Parallel<F, T> extends Ordered<F, T> {

    /**
     * 开启并行拷贝
     *
     * @return copier
     */
    Copier<F, T> parallel();

}