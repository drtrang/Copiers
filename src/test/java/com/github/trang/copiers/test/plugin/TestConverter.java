package com.github.trang.copiers.test.plugin;

import net.sf.cglib.core.Converter;

/**
 * #{@link net.sf.cglib.beans.BeanCopier} 的转换器示例
 *
 * @author trang
 */
public class TestConverter implements Converter {

    /**
     * 重写 convert 方法，每一个 set 方法都会走一次 convert
     *
     * @param value   源属性值
     * @param target  目标属性类 java.lang.Lang
     * @param context 目标属性 setter 方法名
     */
    @Override
    public Object convert(Object value, Class target, Object context) {
        if (target == Double.class || target == double.class) {
            value = Math.random();
        }
        return value;
    }

}