package com.github.trang.copiers.orika;

import com.github.trang.copiers.adapter.AbstractCopier;
import com.github.trang.copiers.exception.CopierException;
import com.github.trang.copiers.inter.Copier;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.ClassMap;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

/**
 * 基于 Orika 的 #{@link Copier} 实现
 *
 * @author trang
 */
public class OrikaCopier<F, T> extends AbstractCopier<MapperFacade, F, T> {

    /**
     * 创建默认的 OrikaCopier
     *
     * @param sourceClass 源类型
     * @param targetClass 目标类型
     */
    private OrikaCopier(Class<F> sourceClass, Class<T> targetClass) {
        super(sourceClass, targetClass, OrikaMapperFactory.getInstance().getMapperFacade());
    }

    @Override
    public T copy(F source) {
        checkNotNull(source, "source bean cannot be null!");
        checkNotNull(targetClass, "target class cannot be null!");
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
         * 自定义 Copier
         */
        private ClassMapBuilder<F, T> builder;

        public Builder(Class<F> sourceClass, Class<T> targetClass) {
            this.builder = OrikaMapperFactory.getInstance().classMap(sourceClass, targetClass);
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
         * @param converterId 自定义转换规则
         * @return this
         */
        public Builder<F, T> field(String sourceField, String targetField, String converterId) {
            builder.fieldMap(sourceField, targetField)
                    .converter(converterId)
                    .add();
            return this;
        }

        /**
         * 自定义属性映射
         *
         * @param sourceField 源对象属性名称
         * @param targetField 目标对象属性名称
         * @param sourceType  源对象属性类型
         * @param targetType  目标对象属性类型
         * @return this
         */
        public Builder<F, T> field(String sourceField, String targetField, Class<?> sourceType, Class<?> targetType) {
            builder.fieldMap(sourceField, targetField)
                    .aElementType(sourceType)
                    .bElementType(targetType)
                    .add();
            return this;
        }

        /**
         * 自定义属性映射
         *
         * @param sourceField 源对象属性名称
         * @param targetField 目标对象属性名称
         * @param sourceType  源对象属性类型
         * @param targetType  目标对象属性类型
         * @param converterId 自定义转换规则
         * @return this
         */
        public Builder<F, T> field(String sourceField, String targetField, Class<?> sourceType, Class<?> targetType,
                                   String converterId) {
            builder.fieldMap(sourceField, targetField)
                    .aElementType(sourceType)
                    .bElementType(targetType)
                    .converter(converterId)
                    .add();
            return this;
        }

        /**
         * 是否拷贝值为 null 的属性
         *
         * @return this
         */
        public Builder<F, T> nulls() {
            builder.mapNulls(true);
            return this;
        }

        /**
         * 排除属性
         *
         * @param fields 要排除的属性名称
         * @return this
         */
        public Builder<F, T> skip(String... fields) {
            if (fields != null && fields.length != 0) {
                for (String field : fields) {
                    builder.exclude(field);
                }
            }
            return this;
        }

        /**
         * 自定义映射规则
         *
         * @param customizedMapper 自定义映射规则
         * @return this
         */
        public Builder<F, T> customize(Mapper<F, T> customizedMapper) {
            builder.customize(customizedMapper);
            return this;
        }

        /**
         * 自定义构造器
         *
         * @param args 构造参数
         * @return this
         */
        public Builder<F, T> constructor(String... args) {
            builder.constructorB(args);
            return this;
        }

        /**
         * 自定义构造器
         *
         * @param parentSourceClass 源对象父类类型
         * @param parentTargetClass 目标对象父类类型
         * @return this
         */
        public Builder<F, T> parent(Class<?> parentSourceClass, Class<?> parentTargetClass) {
            builder.use(parentSourceClass, parentTargetClass);
            return this;
        }

        /**
         * 构建执行拷贝的 Copier
         *
         * @return copier
         */
        public OrikaCopier<F, T> register() {
            // 获取构建的 ClassMap，便于查看自定义参数
            ClassMap<F, T> classMap = builder.toClassMap();
            // 当用户没有自定义构造方法时，则使用空构造，覆盖默认的全参构造
            // https://github.com/orika-mapper/orika/issues/135
            String[] constructorB = classMap.getConstructorB();
            if (constructorB == null || constructorB.length == 0) {
                builder.constructorB();
            }
            // 当用户没有自定义构造方法时，则使用空构造，覆盖默认的全参构造
            String[] constructorA = classMap.getConstructorA();
            if (constructorA == null || constructorA.length == 0) {
                builder.constructorA();
            }
            // 使用默认的 DefaultFieldMappers
            builder.byDefault().register();
            return new OrikaCopier<>(builder.getAType().getRawType(), builder.getBType().getRawType());
        }

    }

}