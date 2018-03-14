package com.github.trang.copiers.orika.converter;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.math.BigDecimal;

/**
 * BooleanConverters
 *
 * @author trang
 */
public class BooleanConverters {

    public static class BooleanToByteConverter extends BidirectionalConverter<Boolean, Byte> {
        @Override
        public Byte convertTo(Boolean source, Type<Byte> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source ? (byte)1 : (byte)0
                    : null;
        }

        @Override
        public Boolean convertFrom(Byte source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source != 0 ? Boolean.TRUE : Boolean.FALSE
                    : null;
        }
    }

    public static class BooleanToShortConverter extends BidirectionalConverter<Boolean, Short> {
        @Override
        public Short convertTo(Boolean source, Type<Short> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source ? (short) 1 : (short)0
                    : null;
        }

        @Override
        public Boolean convertFrom(Short source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source != 0 ? Boolean.TRUE : Boolean.FALSE
                    : null;
        }
    }

    public static class BooleanToIntegerConverter extends BidirectionalConverter<Boolean, Integer> {
        @Override
        public Integer convertTo(Boolean source, Type<Integer> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source ? 1 : 0
                    : null;
        }

        @Override
        public Boolean convertFrom(Integer source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source != 0 ? Boolean.TRUE : Boolean.FALSE
                    : null;
        }
    }

    public static class BooleanToLongConverter extends BidirectionalConverter<Boolean, Long> {
        @Override
        public Long convertTo(Boolean source, Type<Long> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source ? 1L : 0L
                    : null;
        }

        @Override
        public Boolean convertFrom(Long source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source != 0 ? Boolean.TRUE : Boolean.FALSE
                    : null;
        }
    }

    public static class BooleanToDoubleConverter extends BidirectionalConverter<Boolean, Double> {
        @Override
        public Double convertTo(Boolean source, Type<Double> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source ? 1.0 : 0.0
                    : null;
        }

        @Override
        public Boolean convertFrom(Double source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source != 0 ? Boolean.TRUE : Boolean.FALSE
                    : null;
        }
    }

    public static class BooleanToFloatConverter extends BidirectionalConverter<Boolean, Float> {
        @Override
        public Float convertTo(Boolean source, Type<Float> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source ? 1.0f : 0.0f
                    : null;
        }

        @Override
        public Boolean convertFrom(Float source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source != 0f ? Boolean.TRUE : Boolean.FALSE
                    : null;
        }
    }

    public static class BooleanToBigDecimalConverter extends BidirectionalConverter<Boolean, BigDecimal> {
        @Override
        public BigDecimal convertTo(Boolean source, Type<BigDecimal> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source ? BigDecimal.ONE : BigDecimal.ZERO
                    : null;
        }

        @Override
        public Boolean convertFrom(BigDecimal source, Type<Boolean> destinationType, MappingContext mappingContext) {
            return source != null
                    ? source.compareTo(BigDecimal.ONE) >= 0 ? Boolean.TRUE : Boolean.FALSE
                    : null;
        }
    }

}