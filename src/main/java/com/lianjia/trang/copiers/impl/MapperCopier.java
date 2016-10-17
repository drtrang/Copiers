package com.lianjia.trang.copiers.impl;

import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.lianjia.trang.copiers.adapter.CopierAdapter;
import com.lianjia.trang.copiers.inter.Copier;

/**
 * 基于 easy mapper #{@link Mapper}的#{@link Copier}实现
 * 只实现了最基础的拷贝，其它特性可自行添加
 * 
 * @author trang
 */
public class MapperCopier<F, T> extends CopierAdapter<Mapper, F, T> {

	public MapperCopier(Class<F> sourceClass, Class<T> targetClass) {
		// 创建Copier对象
		super(sourceClass, targetClass, MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass).register());
	}

	@Override
	public Mapper reverse() {
		Mapper reverse = this.getReverse();
		synchronized (this) {
			if (reverse == null) {
				return MapperFactory.getCopyByRefMapper().mapClass(getTargetClass(), getSourceClass()).register();
			}
		}
		return reverse;
	}

	@Override
	public T copy(F input) {
		try {
			return this.getCopier().map(input, getTargetClass());
		} catch (Exception e) {
			throw new RuntimeException("create object fail, class:" + getTargetClass().getName(), e);
		}
	}

	@Override
	public void copy(F input, T output) {
		this.getCopier().map(input, output);
	}

	@Override
	public T apply(F input) {
		return copy(input);
	}
}