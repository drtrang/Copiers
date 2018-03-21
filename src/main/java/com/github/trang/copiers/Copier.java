package com.github.trang.copiers;

import com.github.trang.copiers.base.ArrayCopier;
import com.github.trang.copiers.base.BeanCopier;
import com.github.trang.copiers.base.ListCopier;
import com.github.trang.copiers.base.SetCopier;

/**
 * 拷贝底层接口
 *
 * @author trang
 */
public interface Copier<F, T> extends BeanCopier<F, T>, ArrayCopier<F, T>, ListCopier<F, T>, SetCopier<F, T> {

}