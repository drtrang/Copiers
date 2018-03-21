package com.github.trang.copiers.orika;

import com.github.trang.copiers.orika.OrikaMapper.SimpleOrikaMapper;
import ma.glasnost.orika.MapperFactory;

/**
 * 创建 MapperFactory 的单例
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
                    INSTANCE = new SimpleOrikaMapper().getFactory();
                }
            }
        }
        return INSTANCE;
    }

}