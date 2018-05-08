package com.github.trang.copiers.orika.converter;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * List 转换器，用于 List 与常用类型之间的互相转换，默认会注册到 Orika
 *
 * @author trang
 */
public class ListConverters {

    @RequiredArgsConstructor
    public static class ByteListToStringConverter extends BidirectionalConverter<List<Byte>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Byte> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, Object::toString);
        }

        @Override
        public List<Byte> convertFrom(String source, Type<List<Byte>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, Byte::parseByte);
        }
    }

    @RequiredArgsConstructor
    public static class ShortListToStringConverter extends BidirectionalConverter<List<Short>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Short> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, Object::toString);
        }

        @Override
        public List<Short> convertFrom(String source, Type<List<Short>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, Short::parseShort);
        }
    }

    @RequiredArgsConstructor
    public static class IntegerListToStringConverter extends BidirectionalConverter<List<Integer>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Integer> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, Object::toString);
        }

        @Override
        public List<Integer> convertFrom(String source, Type<List<Integer>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, Integer::parseInt);
        }
    }

    @RequiredArgsConstructor
    public static class LongListToStringConverter extends BidirectionalConverter<List<Long>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Long> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, Object::toString);
        }

        @Override
        public List<Long> convertFrom(String source, Type<List<Long>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, Long::parseLong);
        }

    }

    @RequiredArgsConstructor
    public static class DoubleListToStringConverter extends BidirectionalConverter<List<Double>, String> {

        private final String delimiter;
        private NumberFormat formatter;

        public DoubleListToStringConverter(String delimiter, Integer scale, RoundingMode mode) {
            this.delimiter = delimiter;
            if (scale != null && scale >= 0) {
                this.formatter = createNumberFormatter(scale, mode);
            }
        }

        @Override
        public String convertTo(List<Double> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, e -> formatter != null ? formatter.format(e) : e.toString());
        }

        @Override
        public List<Double> convertFrom(String source, Type<List<Double>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, Double::parseDouble);
        }

    }

    @RequiredArgsConstructor
    public static class FloatListToStringConverter extends BidirectionalConverter<List<Float>, String> {

        private final String delimiter;
        private NumberFormat formatter;

        public FloatListToStringConverter(String delimiter, Integer scale, RoundingMode mode) {
            this.delimiter = delimiter;
            if (scale != null) {
                this.formatter = createNumberFormatter(scale, mode);
            }
        }

        @Override
        public String convertTo(List<Float> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, e -> formatter != null ? formatter.format(e) : e.toString());
        }

        @Override
        public List<Float> convertFrom(String source, Type<List<Float>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, Float::parseFloat);
        }

    }

    @RequiredArgsConstructor
    public static class BigDecimalListToStringConverter extends BidirectionalConverter<List<BigDecimal>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<BigDecimal> source, Type<String> destinationType, MappingContext mappingContext) {
            return convertList2String(source, delimiter, BigDecimal::toPlainString);
        }

        @Override
        public List<BigDecimal> convertFrom(String source, Type<List<BigDecimal>> destinationType, MappingContext mappingContext) {
            return convertString2List(source, delimiter, BigDecimal::new);
        }

    }

    private static NumberFormat createNumberFormatter(Integer scale, RoundingMode mode) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(scale);
        format.setMaximumFractionDigits(scale);
        format.setRoundingMode(mode);
        return format;
    }

    private static <E> String convertList2String(List<E> source, String delimiter, Function<E, String> transformer) {
        return source.stream()
                .filter(Objects::nonNull)
                .map(transformer)
                .collect(joining(delimiter));
    }

    private static <E> List<E> convertString2List(String source, String delimiter, Function<String, E> transformer) {
        return Arrays.stream(source.split(delimiter))
                .filter(s -> s != null && !s.isEmpty())
                .map(transformer)
                .collect(toList());
    }

}