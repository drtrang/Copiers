package com.lianjia.trang.copiers.impl;

import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.lianjia.trang.copiers.inter.BeanCopier;

/**
 * 基于#{@link Mapper}的BeanCopier实现
 * 
 * @author trang
 */
public class MapperBeanCopier<F, T> implements BeanCopier<F, T> {

	// 实际执行拷贝的类，基于开源工具Mapper
	private Mapper copier;
	// 源类
	private Class<F> sourceClass;
	// 目标类
	private Class<T> targetClass;
	// 反转Copier，lazyinit，在调用时才初始化
	private Mapper reverse;

	public MapperBeanCopier(Class<F> sourceClass, Class<T> targetClass) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
		//创建BeanCopier对象，不使用转换器
		this.copier = MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass).register();
	}

	public Mapper reverse() {
		return getReverse();
	}

	@Override
	public T copy(F input) {
		try {
			return copier.map(input, targetClass);
		} catch (Exception e) {
			throw new RuntimeException("create object fail, class:" + targetClass.getName(), e);
		}
	}

	@Override
	public void copy(F input, T output) {
		copier.map(input, output);
	}

	@Override
	public T apply(F input) {
		return copy(input);
	}

	// getter & setter
	private Mapper getReverse() {
		Mapper reverse = this.reverse;
		synchronized (this) {
			if (reverse == null) {
				return MapperFactory.getCopyByRefMapper().mapClass(targetClass, sourceClass).register();
			}
		}
		return reverse;
	}
	public Class<F> getSourceClass() {
		return sourceClass;
	}
	public Class<T> getTargetClass() {
		return targetClass;
	}

}
