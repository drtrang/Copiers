package com.github.trang.copiers.cache;

import java.util.Arrays;
import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 用作对象缓存的 Key
 *
 * @param <F> 源对象类型
 * @param <T> 目标对象类型
 * @author trang
 */
@Getter
@Setter
@RequiredArgsConstructor
public class MapperKey<F, T> {

    private final String source;
    private final Class<F> sourceClass;
    private final Class<T> targetClass;
    private Object[] extras;

    public MapperKey(String source, Class<F> sourceClass, Class<T> targetClass, Object... extras) {
        this.source = source;
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.extras = extras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapperKey<?, ?> mapperKey = (MapperKey<?, ?>) o;
        return Objects.equals(source, mapperKey.source) &&
                Objects.equals(sourceClass, mapperKey.sourceClass) &&
                Objects.equals(targetClass, mapperKey.targetClass) &&
                Arrays.deepEquals(extras, mapperKey.extras);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(source, sourceClass, targetClass);
        result = 31 * result + Arrays.deepHashCode(extras);
        return result;
    }

    @Override
    public String toString() {
        return "(" + source + ", " + sourceClass + ", " + targetClass + ", " + Arrays.deepToString(extras) + ")";
    }

}