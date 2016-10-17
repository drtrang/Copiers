package com.lianjia.trang.copiers.impl;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import com.lianjia.trang.copiers.adapter.CopierAdapter;
import com.lianjia.trang.copiers.inter.Copier;

/**
 * 基于 cglib #{@link BeanCopier}的#{@link Copier}实现
 * 使用时需注意：
 *   #{@link BeanCopier#create(Class, Class, boolean)}每次都会做Class之间的
 *   映射，为了避免，我们可以新建一个静态容器保存常用的BeanCopier，使用时直接从容器中取即可
 *   #{@code public static final BeanCopier<Long, String> COPIER = BeanCopiers.copier(Long.class, String.class);}
 * 
 * @author trang
 */
public class CglibCopier<F, T> extends CopierAdapter<BeanCopier, F, T> {
	// 是否使用BeanCopier的转换器
	private static final boolean useConverter = false;

	// 自定义转换器，只有在useConverter为true时生效
	private Converter converter;

	public CglibCopier(Class<F> sourceClass, Class<T> targetClass) {
		// 创建BeanCopier对象，不使用转换器
		super(sourceClass, targetClass, BeanCopier.create(sourceClass, targetClass, useConverter));
	}

	@Override
	public BeanCopier reverse() {
		BeanCopier reverse = this.getReverse();
		synchronized (this) {
			if (reverse == null) {
				return BeanCopier.create(this.getTargetClass(), this.getSourceClass(), useConverter);
			}
		}
		return reverse;
	}

	@Override
	public T copy(F source) {
		try {
			T target = this.getTargetClass().newInstance();
			this.getCopier().copy(source, target, converter);
			return target;
		} catch (Exception e) {
			throw new RuntimeException("create object fail, class:" + this.getTargetClass().getName(), e);
		}
	}

	@Override
	public void copy(F source, T target) {
		this.getCopier().copy(source, target, converter);
	}

	@Override
	public T apply(F source) {
		return copy(source);
	}

	// getter & setter
	protected Converter getConverter() {
		return converter;
	}
	
	/**
	 * 自定义#{@link BeanCopier}的转换器
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
