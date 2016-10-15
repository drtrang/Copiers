package com.lianjia.trang.copiers.impl;

import org.springframework.cglib.core.Converter;

import com.lianjia.trang.copiers.inter.BeanCopier;

/**
 * 基于cglib的#{@link org.springframework.cglib.beans.BeanCopier}实现
 * 使用时需注意：
 *   #{@link org.springframework.cglib.beans.BeanCopier#create(Class, Class, boolean)}每次都会做Class之间的
 *   映射，为了避免，我们可以新建一个静态容器保存常用的BeanCopier，使用时直接从容器中取即可
 *   #{@code com.lianjia.trang.copiers.container.DemoCopierContainer#COPIER}
 * 
 * @author trang
 */
public class CglibBeanCopier<F, T> implements BeanCopier<F, T> {

	// 实际执行拷贝的类，基于cglib的BeanCopier
	private org.springframework.cglib.beans.BeanCopier copier;
	// 源类
	private Class<F> sourceClass;
	// 目标类
	private Class<T> targetClass;
	// 使用转换器
	private boolean useConverter;
	// 自定义转换器，只有在useConverter为true时生效
	private Converter converter;
	// 反转Copier，lazyinit，在调用时才初始化
	private org.springframework.cglib.beans.BeanCopier reverse;

	public CglibBeanCopier(Class<F> sourceClass, Class<T> targetClass) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
		// 不使用转换器
		this.useConverter = false;
		// 创建BeanCopier对象
		this.copier = org.springframework.cglib.beans.BeanCopier.create(sourceClass, targetClass, useConverter);
	}

	public org.springframework.cglib.beans.BeanCopier reverse() {
		return getReverse();
	}

	@Override
	public T copy(F input) {
		try {
			T output = targetClass.newInstance();
			copier.copy(input, output, null);
			return output;
		} catch (Exception e) {
			throw new RuntimeException("create object fail, class:" + targetClass.getName(), e);
		}
	}

	@Override
	public void copy(F input, T output) {
		copier.copy(input, output, null);
	}

	@Override
	public T apply(F input) {
		return copy(input);
	}

	// getter & setter
	public org.springframework.cglib.beans.BeanCopier getReverse() {
		org.springframework.cglib.beans.BeanCopier reverse = this.reverse;
		synchronized (this) {
			if (reverse == null) {
				return org.springframework.cglib.beans.BeanCopier.create(targetClass, sourceClass, useConverter);
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
	public Converter getConverter() {
		return converter;
	}
	
	/**
	 * 自定义#{@link org.springframework.cglib.beans.BeanCopier}的转换器
	 * 
	 * @author trang
	 */
	static class CustomConvert implements Converter {
		/**
		 * 重写convert方法，每一个set方法都会走一次convert
		 * 
		 * @param value 源field属性值
		 * @param target 目标field属性类型 java.lang.Lang
		 * @param context 目标field setter方法名
		 */
		@Override
		@SuppressWarnings("rawtypes")
		public Object convert(Object value, Class target, Object context) {
			return value;
		}
	}
}
