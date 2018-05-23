package com.github.trang.copiers;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.trang.copiers.base.Copier;

import lombok.Getter;

/**
 * #{@link Copier} 适配器，可继承该类实现具体的拷贝过程，也可直接实现 #{@link Copier} 接口
 *
 * @author trang
 */
@Getter
public abstract class AbstractCopier<C, F, T> implements Copier<F, T> {

    /** 实际执行拷贝的对象 */
    protected final C copier;
    /** 源对象的类型 */
    protected final Class<F> sourceClass;
    /** 目标对象的类型 */
    protected final Class<T> targetClass;

    public AbstractCopier() {
        this.sourceClass = null;
        this.targetClass = null;
        this.copier = null;
    }

    protected AbstractCopier(Class<F> sourceClass, Class<T> targetClass, C copier) {
        checkNotNull(sourceClass, "source class cannot be null!");
        checkNotNull(targetClass, "target class cannot be null!");
        checkNotNull(copier, "copier cannot be null!");
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.copier = copier;
    }

    private static final Object[] EMPTY = new Object[0];

    @Override
    public Object[] copyArray(F[] sourceArray) {
        if (sourceArray != null && sourceArray.length > 0) {
            List<T> targetList = new ArrayList<>();
            for (F f : sourceArray) {
                T t = copy(f);
                targetList.add(t);
            }
            return targetList.toArray();
        }
        return EMPTY;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] copyArray(F[] sourceArray, T[] targetArray) {
        if (sourceArray != null && sourceArray.length > 0) {
            List<T> targetList = new ArrayList<>();
            for (F f : sourceArray) {
                T t = copy(f);
                targetList.add(t);
            }
            return targetList.toArray(targetArray);
        }
        return (T[]) EMPTY;
    }

    @Override
    public List<T> copyList(List<F> sourceList) {
        if (sourceList != null && !sourceList.isEmpty()) {
            List<T> targetList = new ArrayList<>();
            for (F f : sourceList) {
                T t = copy(f);
                targetList.add(t);
            }
            return targetList;
        }
        return emptyList();
    }

    @Override
    public Set<T> copySet(Set<F> sourceSet) {
        if (sourceSet != null && !sourceSet.isEmpty()) {
            Set<T> targetSet = new HashSet<>();
            for (F f : sourceSet) {
                T t = copy(f);
                targetSet.add(t);
            }
            return targetSet;
        }
        return emptySet();
    }

}