package com.github.trang.copiers.adapter;

import com.github.trang.copiers.inter.Copier;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * #{@link Copier} 适配器，可继承该类实现具体的拷贝过程，也可直接实现 #{@link Copier} 接口
 *
 * @author trang
 */
@Getter
public abstract class AbstractCopier<C, F, T> implements Copier<F, T> {

    /** 实际执行拷贝的对象 */
    protected C copier;
    /** 源对象的类型 */
    protected Class<F> sourceClass;
    /** 目标对象的类型 */
    protected Class<T> targetClass;

    protected AbstractCopier() {
    }

    protected AbstractCopier(Class<F> sourceClass, Class<T> targetClass, C copier) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.copier = copier;
    }

    private boolean parallel;
    private boolean ordered;

    @Override
    public AbstractCopier<C, F, T> parallel() {
        this.parallel = true;
        return this;
    }

    @Override
    public AbstractCopier<C, F, T> ordered() {
        this.ordered = true;
        return this;
    }

    /**
     * 拷贝数组
     *
     * @param sourceArray 源对象数组
     * @param targetArray 目标对象数组
     */
    @Override
    public void map(F[] sourceArray, T[] targetArray) {
        if (sourceArray == null || sourceArray.length == 0 || targetArray == null || targetArray.length == 0
                || sourceArray.length != targetArray.length) {
            return;
        }
        if (parallel) {
            Arrays.parallelSetAll(targetArray, index -> copy(sourceArray[index]));
        } else {
            Arrays.stream(sourceArray).map(this::copy).toArray(non -> targetArray);
        }
    }

    /**
     * 拷贝 List
     *
     * @param sourceList 源对象集合
     * @return 目标对象集合
     */
    @Override
    public List<T> map(List<F> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> targetList = newActualList(sourceList);
        forEachTransform(sourceList, targetList);
        return targetList;
    }

    /**
     * 拷贝 Set
     *
     * @param sourceSet 源对象集合
     * @return 目标对象集合
     */
    @Override
    public Set<T> map(Set<F> sourceSet) {
        if (sourceSet == null || sourceSet.isEmpty()) {
            return Collections.emptySet();
        }
        Set<T> targetSet = newActualSet(sourceSet);
        forEachTransform(sourceSet, targetSet);
        return targetSet;
    }

    protected void forEachTransform(Collection<F> sources, Collection<T> targets) {
        if (parallel && ordered) {
            sources.parallelStream().map(this::copy).forEachOrdered(targets::add);
        } else if (parallel) {
            sources.parallelStream().map(this::copy).forEach(targets::add);
        } else {
            sources.stream().map(this::copy).forEach(targets::add);
        }
    }

    protected List<T> newActualList(List<F> sourceList) {
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

    protected Set<T> newActualSet(Set<F> sourceSet) {
        Set<T> targetSet;
        if (sourceSet instanceof TreeSet) {
            targetSet = new TreeSet<>();
        } else if (sourceSet instanceof CopyOnWriteArraySet) {
            targetSet = new CopyOnWriteArraySet<>();
        } else if (sourceSet instanceof LinkedHashSet) {
            targetSet = new LinkedHashSet<>(sourceSet.size());
        } else {
            targetSet = new HashSet<>(sourceSet.size());
        }
        return targetSet;
    }

}