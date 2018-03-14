package com.github.trang.copiers.test.benchmark;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Java8 测试
 *
 * @author trang
 */
@Slf4j
public class Java8Test {

    @Test
    public void array() {
        Copier<SimpleSource, SimpleTarget> copier = Copiers.create(SimpleSource.class, SimpleTarget.class);
        int size = 500000;
        SimpleSource[] sourceArray = IntStream.range(0, size).mapToObj(SimpleSource::new).toArray(SimpleSource[]::new);

        Stopwatch stopwatch = Stopwatch.createUnstarted();

        SimpleTarget[] targetArray3 = new SimpleTarget[size];
        stopwatch.start();
        copier.parallel().ordered().map(sourceArray, targetArray3);
        log.info("多线程 Ordered 耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetArray3.length);

        SimpleTarget[] targetArray2 = new SimpleTarget[size];
        stopwatch.reset().start();
        copier.parallel().map(sourceArray, targetArray2);
        log.info("多线程耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetArray2.length);

        SimpleTarget[] targetArray1 = new SimpleTarget[size];
        stopwatch.reset().start();
        copier.map(sourceArray, targetArray1);
        log.info("单线程耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetArray1.length);
    }

    @Test
    public void list() {
        Copier<SimpleSource, SimpleTarget> copier = Copiers.create(SimpleSource.class, SimpleTarget.class);
        int size = 500000;
        List<SimpleSource> sourceList = IntStream.range(0, size).mapToObj(SimpleSource::new).collect(toList());

        Stopwatch stopwatch = Stopwatch.createUnstarted();

        stopwatch.start();
        List<SimpleTarget> targetList1 = copier.map(sourceList);
        log.info("单线程耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetList1.size());

        stopwatch.reset().start();
        List<SimpleTarget> targetList2 = copier.parallel().map(sourceList);
        log.info("多线程耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetList2.size());

        stopwatch.reset().start();
        List<SimpleTarget> targetList3 = copier.parallel().ordered().map(sourceList);
        log.info("多线程 Ordered 耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetList3.size());
    }

    @Test
    public void set() {
        Copier<SimpleSource, SimpleTarget> copier = Copiers.create(SimpleSource.class, SimpleTarget.class);
        int size = 500000;
        Set<SimpleSource> sourceSet = IntStream.range(0, size).mapToObj(SimpleSource::new).collect(toSet());

        Stopwatch stopwatch = Stopwatch.createUnstarted();

        stopwatch.start();
        Set<SimpleTarget> targetSet1 = copier.map(sourceSet);
        log.info("单线程耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetSet1.size());

        stopwatch.reset().start();
        Set<SimpleTarget> targetSet2 = copier.parallel().map(sourceSet);
        log.info("多线程耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetSet2.size());

        stopwatch.reset().start();
        Set<SimpleTarget> targetSet3 = copier.parallel().ordered().map(sourceSet);
        log.info("多线程 Ordered 耗时: {}ms, 大小: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), targetSet3.size());
    }

    @Test
    public void concurrentMap() {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("A", key -> value());
        map.computeIfAbsent("A", key -> value());
        map.computeIfAbsent("B", key -> value());
        System.out.println(map.size());
    }

    private Object value() {
        System.out.println("---");
        return "VALUE";
    }

    @Test
    public void from() {
        Optional.of(Arrays.asList(null,1.1,2.22,3.333,4.4444,5.55555))
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .filter(Objects::nonNull)
                        .map(Objects::toString)
                        .collect(joining(",")))
                .ifPresent(System.out::println);
    }

    @Test
    public void to() {
        Optional.ofNullable("1.1,2.22,3.333,4.4444,5.55555")
                .map(s -> s.split(","))
                .map(arr -> Arrays.stream(arr).filter(Objects::nonNull).map(Double::parseDouble).collect(toList()))
                .ifPresent(System.out::println);
    }

}