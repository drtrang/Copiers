package com.github.trang.copiers.orika.converter;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * BooleanConverters
 *
 * @author trang
 */
public class ListConverters {

    @RequiredArgsConstructor
    public static class ByteListToStringConverter extends BidirectionalConverter<List<Byte>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Byte> source, Type<String> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.stream()
                            .filter(Objects::nonNull)
                            .map(Objects::toString)
                            .collect(joining(delimiter)))
                    .orElse(null);
        }

        @Override
        public List<Byte> convertFrom(String source, Type<List<Byte>> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .map(s -> s.split(delimiter))
                    .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(Byte::parseByte).collect(toList()))
                    .orElse(null);
        }
    }

    @RequiredArgsConstructor
    public static class ShortListToStringConverter extends BidirectionalConverter<List<Short>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Short> source, Type<String> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.stream()
                            .filter(Objects::nonNull)
                            .map(Objects::toString)
                            .collect(joining(delimiter)))
                    .orElse(null);
        }

        @Override
        public List<Short> convertFrom(String source, Type<List<Short>> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .map(s -> s.split(delimiter))
                    .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(Short::parseShort).collect(toList()))
                    .orElse(null);
        }
    }

    @RequiredArgsConstructor
    public static class IntegerListToStringConverter extends BidirectionalConverter<List<Integer>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Integer> source, Type<String> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.stream()
                            .filter(Objects::nonNull)
                            .map(Objects::toString)
                            .collect(joining(delimiter)))
                    .orElse(null);
        }

        @Override
        public List<Integer> convertFrom(String source, Type<List<Integer>> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .map(s -> s.split(delimiter))
                    .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(Integer::parseInt).collect(toList()))
                    .orElse(null);
        }
    }

    @RequiredArgsConstructor
    public static class LongListToStringConverter extends BidirectionalConverter<List<Long>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<Long> source, Type<String> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.stream()
                            .filter(Objects::nonNull)
                            .map(Objects::toString)
                            .collect(joining(delimiter)))
                    .orElse(null);
        }

        @Override
        public List<Long> convertFrom(String source, Type<List<Long>> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .map(s -> s.split(delimiter))
                    .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(Long::parseLong).collect(toList()))
                    .orElse(null);
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
            return Optional.ofNullable(source)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.stream()
                            .filter(Objects::nonNull)
                            .map(d -> formatter != null ? formatter.format(d) : d.toString())
                            .collect(joining(delimiter)))
                    .orElse(null);
        }

        @Override
        public List<Double> convertFrom(String source, Type<List<Double>> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .map(s -> s.split(delimiter))
                    .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(Double::parseDouble).collect(toList()))
                    .orElse(null);
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
            return Optional.ofNullable(source)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.stream()
                            .filter(Objects::nonNull)
                            .map(f -> formatter != null ? formatter.format(f) : f.toString())
                            .collect(joining(delimiter)))
                    .orElse(null);
        }

        @Override
        public List<Float> convertFrom(String source, Type<List<Float>> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .map(s -> s.split(delimiter))
                    .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(Float::parseFloat).collect(toList()))
                    .orElse(null);
        }

    }

    @RequiredArgsConstructor
    public static class BigDecimalListToStringConverter extends BidirectionalConverter<List<BigDecimal>, String> {

        private final String delimiter;

        @Override
        public String convertTo(List<BigDecimal> source, Type<String> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .filter(list -> !list.isEmpty())
                    .map(list -> list.stream()
                            .filter(Objects::nonNull)
                            .map(BigDecimal::toPlainString)
                            .collect(joining(delimiter)))
                    .orElse(null);
        }

        @Override
        public List<BigDecimal> convertFrom(String source, Type<List<BigDecimal>> destinationType, MappingContext mappingContext) {
            return Optional.ofNullable(source)
                    .map(s -> s.split(delimiter))
                    .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(BigDecimal::new).collect(toList()))
                    .orElse(null);
        }

    }

    private static NumberFormat createNumberFormatter(Integer scale, RoundingMode mode) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(scale);
        format.setMaximumFractionDigits(scale);
        format.setRoundingMode(mode);
        return format;
    }

}