package com.github.trang.copiers;

import com.github.trang.copiers.cache.MapperKey;
import com.github.trang.copiers.cglib.CglibCopier;
import com.github.trang.copiers.orika.OrikaCopier;
import net.sf.cglib.core.Converter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Copier 对象缓存，享元模式
 *
 * @author trang
 */
public final class CopierFactory {

    private CopierFactory() {
        throw new UnsupportedOperationException();
    }

    private static final String ORIKA = "orika";
    private static final String CGLIB = "cglib";

    public static <F, T> boolean containsOrikaCopier(Class<F> sourceClass, Class<T> targetClass) {
        ConcurrentMap<MapperKey<F, T>, OrikaCopier<F, T>> orikaCache = CopierFactory.getOrikaCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(ORIKA, sourceClass, targetClass);
        return orikaCache.containsKey(mapperKey);
    }

    public static <F, T> OrikaCopier<F, T> getOrCreateOrikaCopier(Class<F> sourceClass, Class<T> targetClass) {
        ConcurrentMap<MapperKey<F, T>, OrikaCopier<F, T>> orikaCache = CopierFactory.getOrikaCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(ORIKA, sourceClass, targetClass);
        return orikaCache.computeIfAbsent(mapperKey,
                key -> new OrikaCopier.Builder<>(key.getSourceClass(), key.getTargetClass()).register());
    }

    public static <F, T> boolean containsCglibCopier(Class<F> sourceClass, Class<T> targetClass) {
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass);
        return cglibCache.containsKey(mapperKey);
    }

    public static <F, T> CglibCopier<F, T> getOrCreateCglibCopier(Class<F> sourceClass, Class<T> targetClass) {
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass);
        return cglibCache.computeIfAbsent(mapperKey,
                key -> new CglibCopier<>(key.getSourceClass(), key.getTargetClass()));
    }

    public static <F, T> boolean containsCglibCopier(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass, converter);
        return cglibCache.containsKey(mapperKey);
    }

    public static <F, T> CglibCopier<F, T> getOrCreateCglibCopier(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass, converter);
        return cglibCache.computeIfAbsent(mapperKey,
                key -> new CglibCopier<>(key.getSourceClass(), key.getTargetClass(), converter));
    }

    private static class SingleHolder {
        private static final ConcurrentMap<MapperKey<Object, Object>, OrikaCopier<Object, Object>> ORIKA_CACHE = new ConcurrentHashMap<>(1024, 0.75f);
        private static final ConcurrentMap<MapperKey<Object, Object>, CglibCopier<Object, Object>> CGLIB_CACHE = new ConcurrentHashMap<>(1024, 0.75f);
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