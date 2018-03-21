package com.github.trang.copiers.base;

/**
 * 拷贝数组底层接口
 *
 * @author trang
 */
public interface ArrayCopier<F, T> {

    /**
     * 将 sourceArray 拷贝到 targetArray
     *
     * @param sourceArray 源对象数组
     * @param targetArray 目标对象数组
     */
    void map(F[] sourceArray, T[] targetArray);

}