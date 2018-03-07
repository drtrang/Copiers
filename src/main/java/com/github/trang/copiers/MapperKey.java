package com.github.trang.copiers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class MapperKey<F, T> {

    private final String source;
    private final Class<F> sourceClass;
    private final Class<T> targetClass;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapperKey<?, ?> mapperKey = (MapperKey<?, ?>) o;
        return Objects.equals(source, mapperKey.source) &&
                Objects.equals(sourceClass, mapperKey.sourceClass) &&
                Objects.equals(targetClass, mapperKey.targetClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, sourceClass, targetClass);
    }

    @Override
    public String toString() {
        return "(" + source + ", " + sourceClass + ", " + targetClass + ")";
    }

}