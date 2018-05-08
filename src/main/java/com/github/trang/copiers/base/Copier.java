package com.github.trang.copiers.base;

/**
 * 拷贝聚合接口
 *
 * @author trang
 */
public interface Copier<F, T> extends BeanCopier<F, T>, ArrayCopier<F, T>, ListCopier<F, T>, SetCopier<F, T> {

}