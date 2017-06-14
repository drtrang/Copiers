package com.github.trang.copiers.cglib;

import com.baidu.unbiz.easymapper.util.ReflectionUtil;
import com.github.trang.copiers.adapter.CopierAdapter;
import com.github.trang.copiers.inter.Copier;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

/**
 * 基于 Cglib #{@link BeanCopier} 的 #{@link Copier} 实现
 * 使用时需注意：
 *   #{@link BeanCopier#create(Class, Class, boolean)} 每次都会做 Class 之间的映射，为了避免资源浪费，
 *   我们可以新建一个静态容器保存常用的 BeanCopier，使用时直接从容器中取即可。
 *
 * @author trang
 */
public class CglibCopier<F, T> extends CopierAdapter<BeanCopier, F, T> {

    // 是否使用 BeanCopier 的转换器，默认否
    private static final boolean USE_CONVERTER = false;

    // 自定义转换器，只有在 useConverter 为 true 时生效
    private Converter converter;

    public CglibCopier(Class<F> sourceClass, Class<T> targetClass) {
        // 创建 BeanCopier 对象，不使用转换器
        super(sourceClass, targetClass, BeanCopier.create(sourceClass, targetClass, USE_CONVERTER));
    }

    @Override
    public T copy(F source) {
        checkNull(source, "source bean cannot be null!");
        try {
            T target = ReflectionUtil.newInstance(targetClass);
            copier.copy(source, target, converter);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(F source, T target) {
        checkNull(source, "source bean cannot be null!");
        checkNull(target, "target bean cannot be null!");
        try {
            copier.copy(source, target, converter);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    // getter & setter
    public Converter getConverter() {
        return converter;
    }

    /**
     * 自定义 #{@link BeanCopier} 的转换器
     *
     * @author trang
     */
    static class CustomConvert implements Converter {
        /**
         * 重写 convert 方法，每一个 set 方法都会走一次 convert
         *
         * @param value   源字段属性值
         * @param target  目标字段属性类型 java.lang.Lang
         * @param context 目标字段 setter 方法名
         */
        @Override
        @SuppressWarnings("rawtypes")
        public Object convert(Object value, Class target, Object context) {
            return value;
        }
    }

}
