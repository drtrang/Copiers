package com.github.trang.copiers;

import static com.github.trang.copiers.util.Preconditions.checkNotNull;

import com.github.trang.copiers.base.Copier;

import lombok.Getter;

/**
 * #{@link Copier} 适配器，可继承该类实现具体的拷贝过程，也可直接实现 #{@link Copier} 接口
 *
 * @author trang
 */
@Getter
public abstract class AbstractCopier<C, F, T> implements Copier<F, T> {

    /** 实际执行拷贝的对象 */
    protected final C copier;
    /** 源对象的类型 */
    protected final Class<F> sourceClass;
    /** 目标对象的类型 */
    protected final Class<T> targetClass;

    protected AbstractCopier(Class<F> sourceClass, Class<T> targetClass, C copier) {
        checkNotNull(sourceClass, "source class cannot be null!");
        checkNotNull(targetClass, "target class cannot be null!");
        checkNotNull(copier, "copier cannot be null!");
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.copier = copier;
    }

}