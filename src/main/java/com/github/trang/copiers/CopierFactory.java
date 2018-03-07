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
        if (!orikaCache.containsKey(mapperKey)) {
            orikaCache.put(mapperKey, new OrikaCopier.Builder<>(mapperKey.getSourceClass(), mapperKey.getTargetClass()).register());
        }
        return orikaCache.get(mapperKey);
    }

    public static <F, T> CglibCopier<F, T> getCglibCopier(Class<F> sourceClass, Class<T> targetClass) {
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass);
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        if (!cglibCache.containsKey(mapperKey)) {
            cglibCache.put(mapperKey, new CglibCopier<>(mapperKey.getSourceClass(), mapperKey.getTargetClass()));
        }
        return cglibCache.get(mapperKey);
    }

    public static <F, T> CglibCopier<F, T> getCglibCopier(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB_CONVERTER, sourceClass, targetClass);
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierFactory.getCglibCache();
        if (!cglibCache.containsKey(mapperKey)) {
            cglibCache.put(mapperKey, new CglibCopier<>(mapperKey.getSourceClass(), mapperKey.getTargetClass(), converter));
        }
        return cglibCache.get(mapperKey);
    }

    private static class SingleHolder {
        private static final ConcurrentMap<MapperKey, OrikaCopier> ORIKA_CACHE = new ConcurrentHashMap<>(1024, 0.75f, 4);
        private static final ConcurrentMap<MapperKey, CglibCopier> CGLIB_CACHE = new ConcurrentHashMap<>(1024, 0.75f, 4);
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