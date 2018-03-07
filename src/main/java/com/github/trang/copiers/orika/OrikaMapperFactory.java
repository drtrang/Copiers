package com.github.trang.copiers.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;

/**
 * OrikaMapperFactory
 *
 * @author trang
 */
public class OrikaMapperFactory {

    private static volatile MapperFactory INSTANCE = null;

    public static MapperFactory getMapperFactory() {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DefaultMapperFactory.Builder().mapNulls(false).build();
                }
            }
        }
        return INSTANCE;
    }

    public static MapperFactory getMapperFactory(MapperFactoryBuilder<?, ?> builder) {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = builder.build();
                }
            }
        }
        return INSTANCE;
    }

}