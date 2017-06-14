package com.github.trang.copiers.mapper;

import com.baidu.unbiz.easymapper.ClassMapBuilder;
import com.baidu.unbiz.easymapper.MapperFactory;
import com.baidu.unbiz.easymapper.codegen.AtoBMapping;
import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.github.trang.copiers.inter.Copier;

/**
 * EasyMapper 特性支持
 *
 * @author trang
 */
public class MapperCopierSupport<F, T> {

    //自定义 Mapper
    private ClassMapBuilder<F, T> builder;

    public MapperCopierSupport(Class<F> sourceClass, Class<T> targetClass) {
        this.builder = MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass);
    }

    /**
     * 自定义字段映射
     *
     * @param sourceField 源对象字段名称
     * @param targetField 目标对象字段名称
     * @return this
     */
    public MapperCopierSupport<F, T> field(String sourceField, String targetField) {
        builder.field(sourceField, targetField);
        return this;
    }

    /**
     * 自定义字段映射
     *
     * @param sourceField 源对象字段名称
     * @param targetField 目标对象字段名称
     * @param transformer 自定义转换规则
     * @return this
     */
    public <S, D> MapperCopierSupport<F, T> field(String sourceField, String targetField,
                                                  Transformer<S, D> transformer) {
        builder.field(sourceField, targetField, transformer);
        return this;
    }

    /**
     * 自定义字段映射
     *
     * @param sourceField 源对象字段名称
     * @param targetField 目标对象字段名称
     * @param sourceType  源对象字段类型
     * @param targetType  目标对象字段类型
     * @param transformer 自定义转换规则
     * @return this
     */
    public <S, D> MapperCopierSupport<F, T> field(String sourceField, String targetField, Class<S> sourceType,
                                                  Class<D> targetType, Transformer<S, D> transformer) {
        builder.field(sourceField, targetField, sourceType, targetType, transformer);
        return this;
    }

    /**
     * 是否拷贝值为 null 的字段
     *
     * @return this
     */
    public MapperCopierSupport<F, T> nulls() {
        builder.mapOnNull(true);
        return this;
    }

    /**
     * 排除字段
     *
     * @param fields 要排除的字段名称
     * @return this
     */
    public MapperCopierSupport<F, T> skip(String... fields) {
        builder.exclude(fields);
        return this;
    }

    /**
     * 自定义映射规则
     *
     * @param mapping 自定义映射规则
     * @return this
     */
    public MapperCopierSupport<F, T> mapping(AtoBMapping<F, T> mapping) {
        builder.customMapping(mapping);
        return this;
    }

    /**
     * 构建执行拷贝的 Copier
     *
     * @return copier
     */
    public Copier<F, T> register() {
        return new MapperCopier<>(builder);
    }

}
