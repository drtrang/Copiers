package com.github.trang.copiers.adapter;

import com.github.trang.copiers.inter.Copier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * #{@link Copier} 适配器，可继承该类实现具体的拷贝过程，也可直接实现 #{@link Copier} 接口
 *
 * @author trang
 */
public abstract class CopierAdapter<C, F, T> implements Copier<F, T> {

    // 实际执行拷贝的对象
    protected C copier;
    // 源类型
    protected Class<F> sourceClass;
    // 目标类型
    protected Class<T> targetClass;

    protected CopierAdapter() {
    }

    protected CopierAdapter(Class<F> sourceClass, Class<T> targetClass, C copier) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.copier = copier;
    }

    /**
     * 定义拷贝 List 的方式
     *
     * @param sourceList 源对象集合
     * @return 目标对象集合
     */
    @Override
    public List<T> map(List<F> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> targetList = new ArrayList<>(sourceList.size());
        for (F source : sourceList) {
            targetList.add(copy(source));
        }
        return targetList;
    }

    protected void checkNull(Object o, String msg) {
        if (o == null) {
            throw new NullPointerException(msg);
        }
    }

    protected void checkNull(Map map, String msg) {
        if (map == null || map.isEmpty()) {
            throw new NullPointerException(msg);
        }
    }

}
