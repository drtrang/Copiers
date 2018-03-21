package com.github.trang.copiers.cache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 用作对象缓存的 Key
 *
 * @param <F>
 * @param <T>
 * @author trang
 */
@Getter
@Setter
@RequiredArgsConstructor
public class MapperKey<F, T> {

    private final String source;
    private final Class<F> sourceClass;
    private final Class<T> targetClass;
    private Object[] extra;

    public MapperKey(String source, Class<F> sourceClass, Class<T> targetClass, Object... extra) {
        this.source = source;
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapperKey<?, ?> mapperKey = (MapperKey<?, ?>) o;
        return Objects.equals(source, mapperKey.source) &&
                Objects.equals(sourceClass, mapperKey.sourceClass) &&
                Objects.equals(targetClass, mapperKey.targetClass) &&
                Arrays.deepEquals(extra, mapperKey.extra);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(source, sourceClass, targetClass);
        result = 31 * result + Arrays.deepHashCode(extra);
        return result;
    }

    @Override
    public String toString() {
        return "(" + source + ", " + sourceClass + ", " + targetClass + ", " + Arrays.deepToString(extra) + ")";
    }

}