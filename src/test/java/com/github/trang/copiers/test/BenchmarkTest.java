package com.github.trang.copiers.test;

import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserEntity;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 性能测试
 *
 * @author trang
 */
public class BenchmarkTest {

    // source object
    private final User source = new User();
    // a thousand ~ a hundred million
    private final List<Integer> timesList = ImmutableList.of(1_000, 10_000, 100_000, 1_000_000, 10_000_000/*, 100_000_000*/);
    private final Copier<User, UserEntity> copier =
            Copiers.createMapper(User.class, UserEntity.class)
                    .skip("sub")
                    .field("weight", "weight", new Transformer<Integer, Long>() {
                        @Override
                        public Long transform(Integer source) {
                            return source.longValue();
                        }
                    })
                    .register();
    @Before
    public void before() {
        source.setName("trang");
        source.setSex((byte) 0);
        source.setAge(23);
        source.setHeight(1.73);
        source.setWeight(65);
        source.setHandsome(true);
        source.setHobbits(ImmutableList.of("coding"));
        source.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        User wife = new User();
        wife.setName("trang");
        wife.setSex((byte) 0);
        wife.setAge(23);
        wife.setHeight(1.73);
        wife.setWeight(65);
        wife.setHandsome(true);
        wife.setHobbits(ImmutableList.of("coding"));
        wife.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        source.setWife(wife);
    }

    /**
     * 传入对象
     */
    @Test
    public void test2() {
        // cglib
        Copier<User, UserEntity> cglibCopier = Copiers.createCglib(User.class, UserEntity.class);
        Stopwatch cglibWatch = Stopwatch.createStarted();
        for (Integer times : timesList) {
            long start = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
            for (int i = 0; i < times; i++) {
                UserEntity target = new UserEntity();
                cglibCopier.copy(source, target);
            }
            long end = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("copier-2: cglib, " + "times:" + times + ", time:" + (end - start));
        }

        // easy-mapper
        Stopwatch mapperWatch = Stopwatch.createStarted();
        for (Integer times : timesList) {
            long start = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            for (int i = 0; i < times; i++) {
                UserEntity target = new UserEntity();
                copier.copy(source, target);
            }
            long end = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("copier-2: easy mapper, " + "times:" + times + ", time:" + (end - start));
        }
    }

    /**
     * 重新生成对象
     */
    @Test
    public void test1() {
        // cglib
        Copier<User, UserEntity> cglibCopier = Copiers.createCglib(User.class, UserEntity.class);
        Stopwatch cglibWatch = Stopwatch.createStarted();
        for (Integer times : timesList) {
            long start = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
            for (int i = 0; i < times; i++) {
                cglibCopier.copy(source);
            }
            long end = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("copier-1: cglib, " + "times:" + times + ", time:" + (end - start));
        }

        // easy-mapper
        Stopwatch mapperWatch = Stopwatch.createStarted();
        for (Integer times : timesList) {
            long start = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            for (int i = 0; i < times; i++) {
                copier.copy(source);
            }
            long end = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("copier-1: easy mapper, " + "times:" + times + ", time:" + (end - start));
        }
    }

}
