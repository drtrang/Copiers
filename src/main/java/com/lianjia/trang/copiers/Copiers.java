package com.lianjia.trang.copiers;

import com.lianjia.trang.copiers.impl.CglibCopier;
import com.lianjia.trang.copiers.impl.MapperCopier;

/**
 * Copier工具类，可根据需要替换底层实现
 * 目前有两种实现：cglib & easy mapper
 * 
 * @author trang
 */
public class Copiers {
	// 静态工厂方法
	public static <F, T> MapperCopier<F, T> create(Class<F> sourceClass, Class<T> targetClass) {
		return new MapperCopier<F, T>(sourceClass, targetClass);
	}
	
	public static <F, T> CglibCopier<F, T> createCglib(Class<F> sourceClass, Class<T> targetClass) {
		return new CglibCopier<F, T>(sourceClass, targetClass);
	}
}