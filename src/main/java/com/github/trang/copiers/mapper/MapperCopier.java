package com.github.trang.copiers.mapper;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.github.trang.copiers.adapter.CopierAdapter;
import com.github.trang.copiers.inter.Copier;

/**
 * 基于 EasyMapper #{@link Mapper} 的 #{@link Copier} 实现
 *
 * @author trang
 */
public class MapperCopier<F, T> extends CopierAdapter<Mapper, F, T> {

    /**
     * 创建默认的 #{@link Mapper}
     *
     * @param sourceClass 源类型
     * @param targetClass 目标类型
     */
    public MapperCopier(Class<F> sourceClass, Class<T> targetClass) {
        super(sourceClass, targetClass,
                MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass).register());
    }

    /**
     * 自定义 #{@link Mapper}，由 #{@link MapperCopierSupport} 创建
     *
     * @param builder 构造者
     */
    protected MapperCopier(ClassMapBuilder<F, T> builder) {
        super(builder.getAType().getRawType(), builder.getBType().getRawType(), builder.register());
    }

    @Override
    public T copy(F source) {
        checkNull(source, "source bean cannot be null!");
        try {
            return copier.map(source, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(F source, T target) {
        checkNull(source, "source bean cannot be null!");
        checkNull(target, "target bean cannot be null!");
        try {
            copier.map(source, target);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class: " + targetClass.getName(), e);
        }
    }

}