package com.lianjia.trang.copiers.adapter;

import com.lianjia.trang.copiers.inter.Copier;

/**
 * Copier的适配器，可继承该类实现具体的拷贝过程，也可直接实现#{@link Copier}接口
 * 
 * @author trang
 */
public abstract class CopierAdapter<C, F, T> implements Copier<F, T> {
	// 实际执行拷贝的类
	private C copier;
	// 源类
	private Class<F> sourceClass;
	// 目标类
	private Class<T> targetClass;
	// 反转Copier，lazyinit，在调用时才初始化
	private C reverse;
	
	/**
	 * 反转Copier，子类需定义反转的过程
	 * 
	 * @return
	 */
	public abstract C reverse();
	
	/**
	 * 子类可以访问的构造方法
	 * 
	 * @param sourceClass
	 * @param targetClass
	 * @param copier
	 */
	protected CopierAdapter(Class<F> sourceClass, Class<T> targetClass, C copier) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
		this.copier = copier;
	}
	
	protected C getCopier() {
		return copier;
	}
	protected Class<F> getSourceClass() {
		return sourceClass;
	}
	protected Class<T> getTargetClass() {
		return targetClass;
	}
	protected C getReverse() {
		return reverse;
	};
}
