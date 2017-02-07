package com.github.trang.copiers.mapper;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.github.trang.copiers.adapter.CopierAdapter;
import com.github.trang.copiers.inter.Copier;
import com.google.common.base.Preconditions;

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
        super(sourceClass, targetClass, MapperFactory.getCopyByRefMapper()
                .mapClass(sourceClass, targetClass)
                .register());
    }

    /**
     * 通过#{@link ClassMapBuilder}自定义#{@link Mapper}
     * 通过#{@link MapperCopierSupport}创建
     *
     * @param builder
     */
    protected MapperCopier(ClassMapBuilder<F, T> builder) {
        this.sourceClass = builder.getAType().getRawType();
        this.targetClass = builder.getBType().getRawType();
        this.copier = builder.register();
    }

    @Override
    public T copy(F source) {
        Preconditions.checkNotNull(source);
        try {
            return copier.map(source, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(F source, T target) {
        Preconditions.checkNotNull(source);
        Preconditions.checkNotNull(target);
        copier.map(source, target);
    }
}