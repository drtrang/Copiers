package com.github.trang.copiers.inter;

/**
 * 拷贝集合底层接口，支持并行流
 *
 * @author trang
 */
public interface CollectionCopier<F, T> extends Parallel<F, T> {

}