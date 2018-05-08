package com.github.trang.copiers.base;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toCollection;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Set 拷贝底层接口
 *
 * @author trang
 */
public interface SetCopier<F, T> extends BeanCopier<F, T> {

    /**
     * 将 sourceSet 拷贝到新集合，使用 HashSet
     *
     * @param sourceSet 源对象集合
     * @return 目标对象集合
     */
    default Set<T> copySet(Set<F> sourceSet) {
        return copySet(sourceSet, HashSet::new);
    }

    /**
     * 将 sourceSet 拷贝到新集合，使用自定义 Set
     *
     * @param sourceSet  源对象集合
     * @param setFactory 目标对象集合工厂，用于自定义收集器类型
     * @return 目标对象集合
     */
    default Set<T> copySet(Set<F> sourceSet, Supplier<Set<T>> setFactory) {
        checkNotNull(setFactory, "set factory cannot be null!");
        return Optional.ofNullable(sourceSet)
                .filter(set -> !set.isEmpty())
                .map(set -> set.stream().map(this::copy).collect(toCollection(setFactory)))
                .orElse(emptySet());
    }

}