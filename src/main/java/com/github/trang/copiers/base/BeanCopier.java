package com.github.trang.copiers.base;

/**
 * Bean 拷贝底层接口
 *
 * @author trang
 */
public interface BeanCopier<F, T> {

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

}