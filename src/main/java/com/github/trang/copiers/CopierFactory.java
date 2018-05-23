package com.github.trang.copiers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.github.trang.copiers.cache.MapperKey;
import com.github.trang.copiers.cglib.CglibCopier;
import com.github.trang.copiers.orika.OrikaCopier;

import lombok.Getter;
import net.sf.cglib.core.Converter;

/**
 * Copier 对象工厂
 *
 * @author trang
 */
public final class CopierFactory {

    private CopierFactory() {
        throw new UnsupportedOperationException();
    }

    private static final String ORIKA = "orika";
    private static final String CGLIB = "cglib";

    public static <F, T> OrikaCopier<F, T> getOrCreateOrikaCopier(Class<F> sourceClass, Class<T> targetClass) {
        ConcurrentMap<MapperKey<F, T>, OrikaCopier<F, T>> orikaCache = CopierCache.<F, T>getInstance().getOrikaCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(ORIKA, sourceClass, targetClass);
        if (!orikaCache.containsKey(mapperKey)) {
            orikaCache.put(mapperKey, new OrikaCopier.Builder<>(mapperKey.getSourceClass(), mapperKey.getTargetClass()).register());
        }
        return orikaCache.get(mapperKey);
    }

    public static <F, T> CglibCopier<F, T> getOrCreateCglibCopier(Class<F> sourceClass, Class<T> targetClass) {
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierCache.<F, T>getInstance().getCglibCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass);
        if (!cglibCache.containsKey(mapperKey)) {
            cglibCache.put(mapperKey, new CglibCopier<>(mapperKey.getSourceClass(), mapperKey.getTargetClass()));
        }
        return cglibCache.get(mapperKey);
    }

    public static <F, T> CglibCopier<F, T> getOrCreateCglibCopier(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache = CopierCache.<F, T>getInstance().getCglibCache();
        MapperKey<F, T> mapperKey = new MapperKey<>(CGLIB, sourceClass, targetClass, converter);
        if (!cglibCache.containsKey(mapperKey)) {
            cglibCache.put(mapperKey, new CglibCopier<>(mapperKey.getSourceClass(), mapperKey.getTargetClass(), converter));
        }
        return cglibCache.get(mapperKey);
    }

    public static class CopierCache<F, T> {

        private static volatile CopierCache<?, ?> INSTANCE;

        @SuppressWarnings("unchecked")
        public static <F, T> CopierCache<F, T> getInstance() {
            if (INSTANCE == null) {
                synchronized (CopierCache.class) {
                    if (INSTANCE == null) {
                        init();
                    }
                }
            }
            return (CopierCache<F, T>) INSTANCE;
        }

        private static void init() {
            INSTANCE = new CopierCache();
            CopierCache<?, ?> instance = INSTANCE;
            instance.cglibCache = new ConcurrentHashMap<>(1024, 0.75f);
            instance.orikaCache = new ConcurrentHashMap<>(1024, 0.75f);
        }

        @Getter
        private ConcurrentMap<MapperKey<F, T>, CglibCopier<F, T>> cglibCache;
        @Getter
        private ConcurrentMap<MapperKey<F, T>, OrikaCopier<F, T>> orikaCache;

    }

}