package com.lianjia.trang.copiers;

import com.lianjia.trang.copiers.impl.MapperBeanCopier;
import com.lianjia.trang.copiers.inter.BeanCopier;

/**
 * BeanCopier工具类，可根据需要替换底层实现
 * 
 * @author trang
 */
public class BeanCopiers {
	// 静态工厂方法
	public static <F, T> BeanCopier<F, T> copier(Class<F> sourceClass, Class<T> targetClass) {
		return new MapperBeanCopier<F, T>(sourceClass, targetClass);
	}
}
