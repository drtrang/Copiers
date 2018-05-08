package com.github.trang.copiers.orika;

import ma.glasnost.orika.MapperFactory;

/**
 * 创建 MapperFactory 的单例，提供默认配置和自定义配置两种方式
 *
 * @author trang
 */
public final class OrikaMapperFactory {

    private static volatile MapperFactory INSTANCE = null;

    public static MapperFactory getMapperFactory(OrikaMapper orikaMapper) {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = orikaMapper.getFactory();
                }
            }
        }
        return INSTANCE;
    }

    public static MapperFactory getMapperFactory() {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrikaMapper().getFactory();
                }
            }
        }
        return INSTANCE;
    }

}