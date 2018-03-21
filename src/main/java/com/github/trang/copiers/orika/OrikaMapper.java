package com.github.trang.copiers.orika;

import com.github.trang.copiers.orika.converter.BooleanConverters;
import com.github.trang.copiers.orika.converter.ListConverters;
import lombok.Getter;
import lombok.ToString;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * OrikaMapper
 *
 * @author trang
 */
@Getter
public class OrikaMapper {

    private static final Boolean defaultUseBuiltinBooleanConverters = Boolean.TRUE;
    private static final Boolean defaultUseBuiltinListConverters = Boolean.TRUE;
    private static final String defaultDelimiter = ",";

    /** 是否启用内置的 Boolean Converter，默认开启 */
    private Boolean useBuiltinListConverters = defaultUseBuiltinListConverters;
    /** 是否启用内置的 List Converter，默认开启 */
    private Boolean useBuiltinBooleanConverters = defaultUseBuiltinBooleanConverters;
    /** 启用 Boolean Converter 时使用的分隔符，默认 "," */
    private String delimiter = defaultDelimiter;
    /** Orika MapperFactory */
    private volatile DefaultMapperFactory factory;
    /** 是否已经初始化 */
    private volatile boolean initialized = false;

    public OrikaMapper() {
        this(defaultUseBuiltinBooleanConverters, defaultUseBuiltinListConverters, defaultDelimiter);
    }

    public OrikaMapper(Boolean useBuiltinBooleanConverters, Boolean useBuiltinListConverters, String delimiter) {
        this.useBuiltinBooleanConverters = useBuiltinBooleanConverters;
        this.useBuiltinListConverters = useBuiltinListConverters;
        this.delimiter = delimiter;
        init();
    }

    public static OrikaMapper.Builder builder() {
        return new OrikaMapper.Builder();
    }

    private synchronized void init() {
        if (!initialized) {
            DefaultMapperFactory.Builder factoryBuilder = new DefaultMapperFactory.Builder();
            configureFactoryBuilder(factoryBuilder);
            this.factory = factoryBuilder.build();
            configure(this.factory);
            registerConverters(this.factory.getConverterFactory());
            initialized = true;
        }
    }

    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {}

    protected void configure(MapperFactory factory) {}

    protected void registerConverters(ConverterFactory converterFactory) {
        if (useBuiltinBooleanConverters) {
            converterFactory.registerConverter(new BooleanConverters.BooleanToByteConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToShortConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToIntegerConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToLongConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToDoubleConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToFloatConverter());
            converterFactory.registerConverter(new BooleanConverters.BooleanToBigDecimalConverter());
        }
        if (useBuiltinListConverters && delimiter != null) {
            converterFactory.registerConverter(new ListConverters.ByteListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.ShortListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.IntegerListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.LongListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.DoubleListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.FloatListToStringConverter(delimiter));
            converterFactory.registerConverter(new ListConverters.BigDecimalListToStringConverter(delimiter));
        }
    }

    @ToString
    public static class Builder {

        private Boolean useBuiltinBooleanConverters;
        private Boolean useBuiltinListConverters;
        private String delimiter;

        public Builder() {
            this.useBuiltinBooleanConverters = defaultUseBuiltinBooleanConverters;
            this.useBuiltinListConverters = defaultUseBuiltinListConverters;
            this.delimiter = defaultDelimiter;
        }

        public OrikaMapper.Builder useBuiltinBooleanConverters(Boolean useBuiltinBooleanConverters) {
            this.useBuiltinBooleanConverters = useBuiltinBooleanConverters;
            return this;
        }

        public OrikaMapper.Builder useBuiltinListConverters(Boolean useBuiltinListConverters) {
            this.useBuiltinListConverters = useBuiltinListConverters;
            return this;
        }

        public OrikaMapper.Builder delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public OrikaMapper build() {
            return new OrikaMapper(useBuiltinBooleanConverters, useBuiltinListConverters, delimiter);
        }

    }

    public static class SimpleOrikaMapper extends OrikaMapper {

        @Override
        protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
            factoryBuilder.mapNulls(false);
        }

    }

}