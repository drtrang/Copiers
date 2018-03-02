package com.github.trang.copiers.orika;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 *
 *
 * @author trang
 */
public class OrikaMapperFactory {

    private static volatile MapperFactory INSTANCE = null;

    public static MapperFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DefaultMapperFactory.Builder()
                            .mapNulls(false)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}