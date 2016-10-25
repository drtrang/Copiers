package com.lianjia.trang.copiers.impl;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
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
public class MapperCopier<F, T> extends CopierAdapter<ClassMapBuilder<F, T>, F, T> {

	public MapperCopier(Class<F> sourceClass, Class<T> targetClass) {
		// 创建Copier对象
		super(sourceClass, targetClass, MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass));
	}

	public MapperCopier<F, T> mapOnNull(boolean mapOnNull) {
		this.getCopier().mapOnNull(mapOnNull);
		return this;
	}
	
	public MapperCopier<F, T> field(String sourceField, String targetField) {
		this.getCopier().field(sourceField, targetField);
		return this;
	}
	
	public MapperCopier<F, T> exclude(String... properties) {
		this.getCopier().exclude(properties);
		return this;
	}

	@Override
	public T copy(F input) {
		try {
			return this.getCopier().register().map(input, getTargetClass());
		} catch (Exception e) {
			throw new RuntimeException("create object fail, class:" + getTargetClass().getName(), e);
		}
	}

	@Override
	public void copy(F input, T output) {
		this.getCopier().register().map(input, output);
	}

	@Override
	public T apply(F input) {
		return copy(input);
	}

	@Override
	public MapperCopier<T, F> reverse() {
		return new MapperCopier<>(getTargetClass(), getSourceClass());
	}
}