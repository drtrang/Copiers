package com.github.trang.copiers.inter;

/**
 * 拷贝底层接口
 *
 * @author trang
 */
public interface Copier<F, T> extends BeanCopier<F, T>, ArrayCopier<F, T>, ListCopier<F, T>, SetCopier<F, T> {

}