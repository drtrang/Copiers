package com.lianjia.trang.copiers.container;

import com.lianjia.trang.copiers.BeanCopiers;
import com.lianjia.trang.copiers.impl.CglibBeanCopier;
import com.lianjia.trang.copiers.impl.MapperBeanCopier;
import com.lianjia.trang.copiers.inter.BeanCopier;

/**
 * BeanCopier容器，推荐使用#{@code BeanCopiers#copier(Class, Class)}方式调用
 * 
 * @author trang
 */
public class DemoCopierContainer {
	// 推荐使用此方法调用，隐藏实现细节
	public static final BeanCopier<Long, String> COPIER = BeanCopiers.copier(Long.class, String.class);
	
	// 不推荐以下方式
	public static final BeanCopier<Long, String> CGLIG_COPIER1 = new CglibBeanCopier<Long, String>(Long.class, String.class);
	public static final BeanCopier<Long, String> MAPPER_COPIER = new MapperBeanCopier<Long, String>(Long.class, String.class);
}
