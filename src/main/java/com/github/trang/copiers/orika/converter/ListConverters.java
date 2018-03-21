package com.github.trang.copiers.orika.converter;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * BooleanConverters
 *
 * @author trang
 */
public class ListConverters {

    @RequiredArgsConstructor
    public static class ByteListToStringConverter extends BidirectionalConverter<ArrayList<Byte>, String> {

        private final String delimiter;

        @Override
        public String convertTo(ArrayList<Byte> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter);
        }

        @Override
        public ArrayList<Byte> convertFrom(String source, Type<ArrayList<Byte>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, new Transformer<String, Byte>() {
                @Override
                public Byte transfer(String s) {
                    return Byte.parseByte(s);
                }
            });
        }
    }

    @RequiredArgsConstructor
    public static class ShortListToStringConverter extends BidirectionalConverter<ArrayList<Short>, String> {

        private final String delimiter;

        @Override
        public String convertTo(ArrayList<Short> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter);
        }

        @Override
        public ArrayList<Short> convertFrom(String source, Type<ArrayList<Short>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, new Transformer<String, Short>() {
                @Override
                public Short transfer(String s) {
                    return Short.parseShort(s);
                }
            });
        }
    }

    @RequiredArgsConstructor
    public static class IntegerListToStringConverter extends BidirectionalConverter<ArrayList<Integer>, String> {

        private final String delimiter;

        @Override
        public String convertTo(ArrayList<Integer> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter);
        }

        @Override
        public ArrayList<Integer> convertFrom(String source, Type<ArrayList<Integer>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, new Transformer<String, Integer>() {
                @Override
                public Integer transfer(String s) {
                    return Integer.parseInt(s);
                }
            });
        }
    }

    @RequiredArgsConstructor
    public static class LongListToStringConverter extends BidirectionalConverter<ArrayList<Long>, String> {

        private final String delimiter;

        @Override
        public String convertTo(ArrayList<Long> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter);
        }

        @Override
        public ArrayList<Long> convertFrom(String source, Type<ArrayList<Long>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, new Transformer<String, Long>() {
                @Override
                public Long transfer(String s) {
                    return Long.parseLong(s);
                }
            });
        }

    }

    @RequiredArgsConstructor
    public static class DoubleListToStringConverter extends BidirectionalConverter<ArrayList<Double>, String> {

        private final String delimiter;
        private NumberFormat formatter;

        public DoubleListToStringConverter(String delimiter, Integer scale, RoundingMode mode) {
            this.delimiter = delimiter;
            if (scale != null && scale >= 0) {
                this.formatter = createNumberFormatter(scale, mode);
            }
        }

        @Override
        public String convertTo(ArrayList<Double> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, new Transformer<Double, String>() {
                @Override
                public String transfer(Double e) {
                    return formatter != null ? formatter.format(e) : e.toString();
                }
            });
        }

        @Override
        public ArrayList<Double> convertFrom(String source, Type<ArrayList<Double>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, new Transformer<String, Double>() {
                @Override
                public Double transfer(String s) {
                    return Double.parseDouble(s);
                }
            });
        }

    }

    @RequiredArgsConstructor
    public static class FloatListToStringConverter extends BidirectionalConverter<ArrayList<Float>, String> {

        private final String delimiter;
        private NumberFormat formatter;

        public FloatListToStringConverter(String delimiter, Integer scale, RoundingMode mode) {
            this.delimiter = delimiter;
            if (scale != null) {
                this.formatter = createNumberFormatter(scale, mode);
            }
        }

        @Override
        public String convertTo(ArrayList<Float> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, new Transformer<Float, String>() {
                @Override
                public String transfer(Float e) {
                    return formatter != null ? formatter.format(e) : e.toString();
                }
            });
        }

        @Override
        public ArrayList<Float> convertFrom(String source, Type<ArrayList<Float>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, new Transformer<String, Float>() {
                @Override
                public Float transfer(String s) {
                    return Float.parseFloat(s);
                }
            });
        }

    }

    @RequiredArgsConstructor
    public static class BigDecimalListToStringConverter extends BidirectionalConverter<ArrayList<BigDecimal>, String> {

        private final String delimiter;

        @Override
        public String convertTo(ArrayList<BigDecimal> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, new Transformer<BigDecimal, String>() {
                @Override
                public String transfer(BigDecimal e) {
                    return e.toPlainString();
                }
            });
        }

        @Override
        public ArrayList<BigDecimal> convertFrom(String source, Type<ArrayList<BigDecimal>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, new Transformer<String, BigDecimal>() {
                @Override
                public BigDecimal transfer(String s) {
                    return new BigDecimal(s);
                }
            });
        }

    }

    private static NumberFormat createNumberFormatter(Integer scale, RoundingMode mode) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(scale);
        format.setMaximumFractionDigits(scale);
        format.setRoundingMode(mode);
        return format;
    }

    private static <E> String convertList2String(ArrayList<E> source, String delimiter) {
        return convertList2String(source, delimiter, null);
    }

    private static <E> String convertList2String(ArrayList<E> source, String delimiter, Transformer<E, String> transformer) {
        if (source != null && !source.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (E e : source) {
                if (e != null) {
                    builder.append(transformer != null ? transformer.transfer(e) : e).append(delimiter);
                }
            }
            int length = builder.length();
            if (length > 0) {
                builder = builder.delete(length - 1, length);
            }
            return builder.toString();
        }
        return null;
    }

    private static <E> ArrayList<E> convertString2List(String source, String delimiter, Transformer<String, E> transformer) {
        if (source != null && !source.isEmpty()) {
            ArrayList<E> r = new ArrayList<>();
            String[] arr = source.split(delimiter);
            for (String s : arr) {
                if (s != null && !s.isEmpty()) {
                    r.add(transformer.transfer(s));
                }
            }
            return r;
        }
        return null;
    }

    private interface Transformer<F, T> {
        T transfer(F t);
    }

}