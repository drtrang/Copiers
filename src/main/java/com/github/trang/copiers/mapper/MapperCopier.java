package com.github.trang.copiers.mapper;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.github.trang.copiers.adapter.CopierAdapter;
import com.github.trang.copiers.inter.Copier;

/**
 * 基于 easy mapper #{@link Mapper}的#{@link Copier}实现
 *
 * @author trang
 */
public class MapperCopier<F, T> extends CopierAdapter<Mapper, F, T> {

    /**
     * 直接创建执行拷贝的#{@link Mapper}
     *
     * @param sourceClass
     * @param targetClass
     */
    public MapperCopier(Class<F> sourceClass, Class<T> targetClass) {
        super(sourceClass, targetClass,
                MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass).register());
    }

    /**
     * 通过#{@link ClassMapBuilder}自定义#{@link Mapper}
     * 通过#{@link MapperCopierSupport}创建
     *
     * @param builder
     */
    protected MapperCopier(ClassMapBuilder<F, T> builder) {
        super(builder.getAType().getRawType(), builder.getBType().getRawType(), builder.register());
    }

    @Override
    public T copy(F source) {
        if (source == null) {
            throw new NullPointerException("source bean cannot be null!");
        }
        try {
            return copier.map(source, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(F source, T target) {
        if (source == null) {
            throw new NullPointerException("source bean cannot be null!");
        } else if (target == null) {
            throw new NullPointerException("target bean cannot be null!");
        }
        try {
            copier.map(source, target);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + targetClass.getName(), e);
        }
    }
}