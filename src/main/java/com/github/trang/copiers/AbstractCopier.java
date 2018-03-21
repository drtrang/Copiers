package com.github.trang.copiers;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

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

    protected AbstractCopier() {}

    protected AbstractCopier(Class<F> sourceClass, Class<T> targetClass, C copier) {
        checkNotNull(sourceClass, "source class cannot be null!");
        checkNotNull(targetClass, "target class cannot be null!");
        checkNotNull(copier, "copier cannot be null!");
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.copier = copier;
    }

    @Override
    public void map(F[] sourceArray, T[] targetArray) {
        if (sourceArray == null || sourceArray.length == 0 || targetArray == null || targetArray.length == 0
                || sourceArray.length != targetArray.length) {
            return;
        }
        for (int i = 0; i < sourceArray.length; i++) {
            T target = copy(sourceArray[i]);
            targetArray[i] = target;
        }
    }

    @Override
    public List<T> map(List<F> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> targetList = newActualList(sourceList);
        forEachTransform(sourceList, targetList);
        return targetList;
    }

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
        for (F source : sources) {
            targets.add(copy(source));
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