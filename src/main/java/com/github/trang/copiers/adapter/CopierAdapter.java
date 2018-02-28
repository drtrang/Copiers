package com.github.trang.copiers.adapter;

import com.github.trang.copiers.inter.Copier;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * #{@link Copier} 适配器，可继承该类实现具体的拷贝过程，也可直接实现 #{@link Copier} 接口
 *
 * @author trang
 */
public abstract class CopierAdapter<C, F, T> implements Copier<F, T> {

    /** 实际执行拷贝的对象 */
    protected C copier;
    /** 源对象的类型 */
    protected Class<F> sourceClass;
    /** 目标对象的类型 */
    protected Class<T> targetClass;

    protected CopierAdapter() {
    }

    protected CopierAdapter(Class<F> sourceClass, Class<T> targetClass, C copier) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.copier = copier;
    }

    /**
     * 定义拷贝 List 的方式
     *
     * @param sourceList 源对象集合
     * @return 目标对象集合
     */
    @Override
    public List<T> map(List<F> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> targetList = createActualList(sourceList);
        for (F source : sourceList) {
            targetList.add(copy(source));
        }
        return targetList;
    }

    private List<T> createActualList(List<F> sourceList) {
        List<T> targetList;
        if (sourceList instanceof LinkedList) {
            targetList = new LinkedList<>();
        } else if (sourceList instanceof CopyOnWriteArrayList) {
            targetList = new CopyOnWriteArrayList<>();
        } else if (sourceList instanceof Vector) {
            targetList = new Vector<>(sourceList.size());
        } else {
            targetList = new ArrayList<>(sourceList.size());
        }
        return targetList;
    }

    protected void checkNull(Object o, String msg) {
        if (o == null) {
            throw new NullPointerException(msg);
        }
    }

    protected void checkNull(Map map, String msg) {
        if (map == null) {
            throw new NullPointerException(msg);
        } else if (map.isEmpty()) {
            throw new IllegalArgumentException(msg);
        }
    }

}