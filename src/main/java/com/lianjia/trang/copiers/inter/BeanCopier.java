package com.lianjia.trang.copiers.inter;

import com.google.common.base.Function;

/**
 * Bean拷贝的抽象封装，可以根据需要替换实现
 * 
 * @author trang
 */
public interface BeanCopier<F, T> extends Function<F, T> {
	T copy (F input);
	void copy (F input, T output);
}