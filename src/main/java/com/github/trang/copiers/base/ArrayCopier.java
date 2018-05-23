package com.github.trang.copiers.base;

/**
 * 数组拷贝底层接口
 *
 * @author trang
 */
public interface ArrayCopier<F, T> extends BeanCopier<F, T> {

    /**
     * 将 sourceArray 拷贝到新数组
     *
     * @param sourceArray 源对象数组
     * @return Object 数组
     */
    Object[] copyArray(F[] sourceArray);

    /**
     * 将 sourceArray 拷贝到新数组
     *
     * @param sourceArray 源对象数组
     * @param targetArray 目标对象数组生成器
     * @return 目标对象数组
     */
    T[] copyArray(F[] sourceArray, T[] targetArray);

}