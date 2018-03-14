package com.github.trang.copiers.orika;

import com.github.trang.copiers.orika.converter.BooleanConverters;
import com.github.trang.copiers.orika.converter.ListConverters;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;

/**
 * 创建 MapperFactory 的单例
 *
 * @author trang
 */
public class OrikaMapperFactory {

    private static volatile MapperFactory INSTANCE = null;

    public static MapperFactory getMapperFactory() {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = configure(new DefaultMapperFactory.Builder().mapNulls(false).build());
                }
            }
        }
        return INSTANCE;
    }

    public static MapperFactory getMapperFactory(MapperFactoryBuilder<?, ?> builder) {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = configure(builder.build());
                }
            }
        }
        return INSTANCE;
    }

    private static final String COMMA = ",";

    private synchronized static MapperFactory configure(MapperFactory factory) {
        ConverterFactory converterFactory = factory.getConverterFactory();
        converterFactory.registerConverter(new BooleanConverters.BooleanToByteConverter());
        converterFactory.registerConverter(new BooleanConverters.BooleanToShortConverter());
        converterFactory.registerConverter(new BooleanConverters.BooleanToIntegerConverter());
        converterFactory.registerConverter(new BooleanConverters.BooleanToLongConverter());
        converterFactory.registerConverter(new BooleanConverters.BooleanToDoubleConverter());
        converterFactory.registerConverter(new BooleanConverters.BooleanToFloatConverter());
        converterFactory.registerConverter(new BooleanConverters.BooleanToBigDecimalConverter());
        converterFactory.registerConverter(new ListConverters.ByteListToStringConverter(COMMA));
        converterFactory.registerConverter(new ListConverters.ShortListToStringConverter(COMMA));
        converterFactory.registerConverter(new ListConverters.IntegerListToStringConverter(COMMA));
        converterFactory.registerConverter(new ListConverters.LongListToStringConverter(COMMA));
        converterFactory.registerConverter(new ListConverters.DoubleListToStringConverter(COMMA));
        converterFactory.registerConverter(new ListConverters.FloatListToStringConverter(COMMA));
        converterFactory.registerConverter(new ListConverters.BigDecimalListToStringConverter(COMMA));
        return factory;
    }

}