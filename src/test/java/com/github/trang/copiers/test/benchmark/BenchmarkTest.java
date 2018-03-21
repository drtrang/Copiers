package com.github.trang.copiers.test.benchmark;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.cglib.CglibCopier;
import com.github.trang.copiers.Copier;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserEntity;
import com.github.trang.copiers.test.util.MockUtils;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
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
    private final User source = MockUtils.newUser();
    // a thousand ~ a hundred million
    private final List<Integer> timesList = ImmutableList.of(1_000, 10_000, 100_000, 1_000_000, 10_000_000/*, 100_000_000*/);

    /**
     * 传入对象
     */
    @Test
    public void test2() {
        // cglib
        CglibCopier<User, UserEntity> cglibCopier = Copiers.createCglib(User.class, UserEntity.class);
        Stopwatch cglibWatch = Stopwatch.createStarted();
        for (Integer times : timesList) {
            long start = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
            for (int i = 0; i < times; i++) {
                UserEntity target = new UserEntity();
//                Copiers.createCglib(User.class, UserEntity.class).copy(source, target);
                cglibCopier.copy(source, target);
            }
            long end = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("copier-2: cglib, " + "times:" + times + ", time:" + (end - start));
        }

        // orika
        Copier<User, UserEntity> orikaCopier =
                Copiers.createOrika(User.class, UserEntity.class)
                        .skip("sub")
                        .field("name", "username")
                        .register();
        Stopwatch mapperWatch = Stopwatch.createStarted();
        for (Integer times : timesList) {
            long start = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            for (int i = 0; i < times; i++) {
                UserEntity target = new UserEntity();
//                Copiers.createOrika(User.class, UserEntity.class)
//                        .skip("sub")
//                        .field("name", "username")
//                        .register().copy(source, target);
//                Copiers.create(User.class, UserEntity.class).copy(source, target);
                orikaCopier.copy(source, target);
            }
            long end = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("copier-2: orika, " + "times:" + times + ", time:" + (end - start));
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

        // orika
        Copier<User, UserEntity> orikaCopier =
                Copiers.createOrika(User.class, UserEntity.class)
                        .skip("sub")
                        .field("name", "username")
                        .register();
        Stopwatch mapperWatch = Stopwatch.createStarted();
        for (Integer times : timesList) {
            long start = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            for (int i = 0; i < times; i++) {
                orikaCopier.copy(source);
            }
            long end = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
            System.out.println("copier-1: orika, " + "times:" + times + ", time:" + (end - start));
        }
    }

}
