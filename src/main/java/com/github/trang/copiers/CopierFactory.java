package com.github.trang.copiers;

import com.github.trang.copiers.cglib.CglibCopier;
import com.github.trang.copiers.orika.OrikaCopier;
import net.sf.cglib.core.Converter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Copier 对象缓存
 *
 * @author trang
 */
public final class CopierFactory {

    private CopierFactory() {
        throw new UnsupportedOperationException();
    }

    private static final String ORIKA = "orika";
    private static final String CGLIB = "cglib";
    private static final String CGLIB_CONVERTER = "cglibConverter";

    public static <F, T> OrikaCopier<F, T> getOrikaCopier(Class<F> sourceClass, Class<T> targetClass) {
        MapperKey<F, T> mapperKey = new MapperKey<>(ORIKA, sourceClass, targetClass);
        ConcurrentMap<MapperKey<F, T>, OrikaCopier<F, T>> orikaCache = CopierFactory.getOrikaCache();
        return orikaCache.computeIfAbsent(mapperKey,
                key -> new OrikaCopier.Builder<>(key.getSourceClass(), key.getTargetClass()).register());
    }

    public static <F, T> CglibCopier<F, T> getCglibCopier(Class<F> sourceClass, Class<T> targetClass) {
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass);
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        return cglibCache.computeIfAbsent(mapperKey,
                key -> new CglibCopier<>(key.getSourceClass(), key.getTargetClass()));
    }

    public static <F, T> CglibCopier<F, T> getCglibCopier(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB_CONVERTER, sourceClass, targetClass);
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        return cglibCache.computeIfAbsent(mapperKey,
                key -> new CglibCopier<>(key.getSourceClass(), key.getTargetClass(), converter));
    }

    private static class SingleHolder {
        private static final ConcurrentMap<MapperKey, OrikaCopier> ORIKA_CACHE = new ConcurrentHashMap<>(1024, 0.75f);
        private static final ConcurrentMap<MapperKey, CglibCopier> CGLIB_CACHE = new ConcurrentHashMap<>(1024, 0.75f);
    }

    @SuppressWarnings("unchecked")
    private static <F, T> ConcurrentMap<MapperKey<F, T>, OrikaCopier<F, T>> getOrikaCache() {
        return (ConcurrentMap) SingleHolder.ORIKA_CACHE;
    }

    @SuppressWarnings("unchecked")
    private static <F, T> ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> getCglibCache() {
        return (ConcurrentMap) SingleHolder.CGLIB_CACHE;
    }

}