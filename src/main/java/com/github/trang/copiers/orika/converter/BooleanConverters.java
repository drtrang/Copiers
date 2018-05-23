package com.github.trang.copiers.orika.converter;

import java.math.BigDecimal;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * Boolean 转换器，用于布尔型与常用类型之间的互相转换，默认会注册到 Orika
 *
 * @author trang
 */
public class BooleanConverters {

    public static class BooleanToByteConverter extends BidirectionalConverter<Boolean, Byte> {
        @Override
        public Byte convertTo(Boolean source, Type<Byte> destinationType, MappingContext mappingContext) {
            return source ? (byte) 1 : (byte) 0;
        }

        @Override
        public Boolean convertFrom(Byte source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static class BooleanToShortConverter extends BidirectionalConverter<Boolean, Short> {
        @Override
        public Short convertTo(Boolean source, Type<Short> destinationType, MappingContext mappingContext) {
            return source ? (short) 1 : (short) 0;
        }

        @Override
        public Boolean convertFrom(Short source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static class BooleanToIntegerConverter extends BidirectionalConverter<Boolean, Integer> {
        @Override
        public Integer convertTo(Boolean source, Type<Integer> destinationType, MappingContext mappingContext) {
            return source ? 1 : 0;
        }

        @Override
        public Boolean convertFrom(Integer source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static class BooleanToLongConverter extends BidirectionalConverter<Boolean, Long> {
        @Override
        public Long convertTo(Boolean source, Type<Long> destinationType, MappingContext mappingContext) {
            return source ? 1L : 0L;
        }

        @Override
        public Boolean convertFrom(Long source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static class BooleanToDoubleConverter extends BidirectionalConverter<Boolean, Double> {
        @Override
        public Double convertTo(Boolean source, Type<Double> destinationType, MappingContext mappingContext) {
            return source ? 1.0 : 0.0;
        }

        @Override
        public Boolean convertFrom(Double source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static class BooleanToFloatConverter extends BidirectionalConverter<Boolean, Float> {
        @Override
        public Float convertTo(Boolean source, Type<Float> destinationType, MappingContext mappingContext) {
            return source ? 1.0f : 0.0f;
        }

        @Override
        public Boolean convertFrom(Float source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != 0f ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    public static class BooleanToBigDecimalConverter extends BidirectionalConverter<Boolean, BigDecimal> {
        @Override
        public BigDecimal convertTo(Boolean source, Type<BigDecimal> destinationType, MappingContext mappingContext) {
            return source ? BigDecimal.ONE : BigDecimal.ZERO;
        }

        @Override
        public Boolean convertFrom(BigDecimal source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source.compareTo(BigDecimal.ONE) >= 0 ? Boolean.TRUE : Boolean.FALSE;
        }
    }

}