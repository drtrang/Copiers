package com.github.trang.copiers.adapter;

import com.github.trang.copiers.inter.Copier;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Copier的适配器，可继承该类实现具体的拷贝过程，也可直接实现#{@link Copier}接口
 *
 * @author trang
 */
public abstract class CopierAdapter<C, F, T> implements Copier<F, T>, Function<F, T> {
    // 实际执行拷贝的类
    protected C copier;
    // 源类
    protected Class<F> sourceClass;
    // 目标类
    protected Class<T> targetClass;

    protected CopierAdapter() {
    }

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

    /**
     * 将List转换为目标List
     *
     * @param sourceList
     * @return targetList
     */
    @Override
    public List<T> map(List<F> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> targetList = Lists.transform(sourceList, this);
        // 安全起见，返回List，而不是视图
        return Lists.newArrayList(targetList);
    }

    @Override
    public T apply(F source) {
        return copy(source);
    }
}
