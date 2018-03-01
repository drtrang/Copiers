package com.github.trang.copiers.mapper;

import com.github.trang.copiers.adapter.AbstractCopier;
import com.github.trang.copiers.exception.CopierException;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.easymapper.ClassMapBuilder;
import com.github.trang.easymapper.Mapper;
import com.github.trang.easymapper.MapperFactory;
import com.github.trang.easymapper.codegen.AtoBMapping;
import com.github.trang.easymapper.transformer.Transformer;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

/**
 * 基于 EasyMapper #{@link Mapper} 的 #{@link Copier} 实现
 *
 * @author trang
 */
public class MapperCopier<F, T> extends AbstractCopier<Mapper, F, T> {

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
     * 自定义 #{@link Mapper}，由 #{@link Builder} 创建
     *
     * @param builder 构造者
     */
    private MapperCopier(ClassMapBuilder<F, T> builder) {
        super(builder.getAType().getRawType(), builder.getBType().getRawType(), builder.register());
    }

    @Override
    public T copy(F source) {
        checkNotNull(source, "source bean cannot be null!");
        try {
            return copier.map(source, targetClass);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(F source, T target) {
        checkNotNull(source, "source bean cannot be null!");
        checkNotNull(target, "target bean cannot be null!");
        try {
            copier.map(source, target);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    public static class Builder<F, T> {

        /**
         * 自定义 Mapper
         */
        private ClassMapBuilder<F, T> builder;

        public Builder(Class<F> sourceClass, Class<T> targetClass) {
            this.builder = MapperFactory.getCopyByRefMapper().mapClass(sourceClass, targetClass);
        }

        /**
         * 自定义属性映射
         *
         * @param sourceField 源对象属性名称
         * @param targetField 目标对象属性名称
         * @return this
         */
        public Builder<F, T> field(String sourceField, String targetField) {
            builder.field(sourceField, targetField);
            return this;
        }

        /**
         * 自定义属性映射
         *
         * @param sourceField 源对象属性名称
         * @param targetField 目标对象属性名称
         * @param transformer 自定义转换规则
         * @return this
         */
        public <S, D> Builder<F, T> field(String sourceField, String targetField, Transformer<S, D> transformer) {
            builder.field(sourceField, targetField, transformer);
            return this;
        }

        /**
         * 自定义属性映射
         *
         * @param sourceField 源对象属性名称
         * @param targetField 目标对象属性名称
         * @param sourceType  源对象属性类型
         * @param targetType  目标对象属性类型
         * @param transformer 自定义转换规则
         * @return this
         */
        public <S, D> Builder<F, T> field(String sourceField, String targetField, Class<S> sourceType,
                                          Class<D> targetType, Transformer<S, D> transformer) {
            builder.field(sourceField, targetField, sourceType, targetType, transformer);
            return this;
        }

        /**
         * 是否拷贝值为 null 的属性
         *
         * @return this
         */
        public Builder<F, T> nulls() {
            builder.mapOnNull(true);
            return this;
        }

        /**
         * 排除属性
         *
         * @param fields 要排除的属性名称
         * @return this
         */
        public Builder<F, T> skip(String... fields) {
            builder.exclude(fields);
            return this;
        }

        /**
         * 自定义映射规则
         *
         * @param mapping 自定义映射规则
         * @return this
         */
        public Builder<F, T> mapping(AtoBMapping<F, T> mapping) {
            builder.customMapping(mapping);
            return this;
        }

        /**
         * 构建执行拷贝的 Copier
         *
         * @return copier
         */
        public MapperCopier<F, T> register() {
            return new MapperCopier<>(builder);
        }

    }

}