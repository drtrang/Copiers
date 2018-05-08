package com.github.trang.copiers.base;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * List 拷贝底层接口
 *
 * @author trang
 */
public interface ListCopier<F, T> extends BeanCopier<F, T> {

    /**
     * 将 sourceList 拷贝到新集合，使用 ArrayList
     *
     * @param sourceList 源对象集合
     * @return 目标对象集合
     */
    default List<T> copyList(List<F> sourceList) {
        return copyList(sourceList, ArrayList::new);
    }

    /**
     * 将 sourceList 拷贝到新集合，使用自定义 List
     *
     * @param sourceList  源对象集合
     * @param listFactory 目标对象集合工厂，用于自定义收集器类型
     * @return 目标对象集合
     */
    default List<T> copyList(List<F> sourceList, Supplier<List<T>> listFactory) {
        checkNotNull(listFactory, "list factory cannot be null!");
        return Optional.ofNullable(sourceList)
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(this::copy).collect(toCollection(listFactory)))
                .orElse(emptyList());
    }

}