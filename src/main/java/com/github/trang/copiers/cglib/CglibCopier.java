package com.github.trang.copiers.cglib;

import com.baidu.unbiz.easymapper.util.ReflectionUtil;
import com.github.trang.copiers.adapter.AbstractCopier;
import com.github.trang.copiers.exception.CopierException;
import com.github.trang.copiers.inter.Copier;
import lombok.Getter;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

/**
 * 基于 Cglib #{@link BeanCopier} 的 #{@link Copier} 实现
 * 使用时需注意：
 *   #{@link BeanCopier#create(Class, Class, boolean)} 每次都会做 Class 之间的映射，为了避免资源浪费，
 *   我们可以新建一个静态容器保存常用的 BeanCopier，使用时直接从容器中取即可。
 *
 * @author trang
 */
@Getter
public class CglibCopier<F, T> extends AbstractCopier<BeanCopier, F, T> {

    /** 自定义转换器，只有在 useConverter 为 true 时生效 */
    private Converter converter;

    public CglibCopier(Class<F> sourceClass, Class<T> targetClass) {
        // 创建 BeanCopier 对象，不使用转换器
        super(sourceClass, targetClass, BeanCopier.create(sourceClass, targetClass, false));
    }

    public CglibCopier(Class<F> sourceClass, Class<T> targetClass, Converter converter) {
        // 创建 BeanCopier 对象，使用转换器
        super(sourceClass, targetClass, BeanCopier.create(sourceClass, targetClass, true));
        this.converter = converter;
    }

    @Override
    public T copy(F source) {
        checkNotNull(source, "source bean cannot be null!");
        try {
            T target = ReflectionUtil.newInstance(targetClass);
            copier.copy(source, target, converter);
            return target;
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + targetClass.getName(), e);
        }
    }

    @Override
    public void copy(F source, T target) {
        checkNotNull(source, "source bean cannot be null!");
        checkNotNull(target, "target bean cannot be null!");
        try {
            copier.copy(source, target, converter);
        } catch (Exception e) {
            throw new CopierException("create object fail, class: " + targetClass.getName(), e);
        }
    }

}