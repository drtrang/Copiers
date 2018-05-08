package com.github.trang.copiers.base;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntFunction;

/**
 * 数组拷贝底层接口
 *
 * @author trang
 */
public interface ArrayCopier<F, T> extends BeanCopier<F, T> {

    Object[] EMPTY = new Object[0];

    /**
     * 将 sourceArray 拷贝到新数组
     *
     * @param sourceArray 源对象数组
     * @return Object 数组
     */
    default Object[] copyArray(F[] sourceArray) {
        return Optional.ofNullable(sourceArray)
                .filter(arr -> arr.length > 0)
                .map(arr -> Arrays.stream(arr).map(this::copy).toArray())
                .orElse(EMPTY);
    }

    /**
     * 将 sourceArray 拷贝到新数组
     *
     * @param sourceArray 源对象数组
     * @param generator   目标对象数组生成器
     * @return 目标对象数组
     */
    @SuppressWarnings("unchecked")
    default T[] copyArray(F[] sourceArray, IntFunction<T[]> generator) {
        checkNotNull(generator, "array generator cannot be null!");
        return Optional.ofNullable(sourceArray)
                .filter(arr -> arr.length > 0)
                .map(arr -> Arrays.stream(arr).map(this::copy).toArray(generator))
                .orElse((T[]) EMPTY);
    }

}