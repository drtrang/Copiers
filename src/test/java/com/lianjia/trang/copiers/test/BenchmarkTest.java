package com.lianjia.trang.copiers.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lianjia.trang.copiers.bean.User;
import com.lianjia.trang.copiers.bean.UserEntity;
import com.lianjia.trang.copiers.impl.CglibCopier;
import com.lianjia.trang.copiers.impl.MapperCopier;
import com.lianjia.trang.copiers.inter.Copier;

/**
 * 性能测试
 * 
 * @author trang
 */
public class BenchmarkTest {

	//source object
	private User source = new User();
	//a thousand ~ a hundred million
	private List<Integer> timesList = ImmutableList.of(1_000, 10_000, 100_000, 1_000_000, 10_000_000/*, 100_000_000*/);
	
	@Before
	public void before() {
		source.setName("trang");
		source.setSex((byte) 0);
		source.setAge(23);
		source.setHeight(1.73);
		source.setWeight(65L);
		source.setHandsome(true);
		source.setHobbits(ImmutableList.of("coding"));
		source.setHouse(ImmutableMap.<String, Object>of("home", "home"));
		
		User wife = new User();
		wife.setName("trang");
		wife.setSex((byte) 0);
		wife.setAge(23);
		wife.setHeight(1.73);
		wife.setWeight(65L);
		wife.setHandsome(true);
		wife.setHobbits(ImmutableList.of("coding"));
		wife.setHouse(ImmutableMap.<String, Object>of("home", "home"));
		
		source.setWife(wife);
	}
	
	/**
	 * 重新生成对象
	 */
	@Test
	public void cglib() {
		Copier<User, UserEntity> cglib = new CglibCopier<>(User.class, UserEntity.class);
		Stopwatch cglibWatch = Stopwatch.createStarted();
		for (Integer times : timesList) {
			long start = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			for (int i = 0; i < times; i++) {
				cglib.copy(source);
			}
			long end = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.printf("copier:cglib, " + "times:" + times + ", time:" + (end - start));
		}
		
		Copier<User, UserEntity> mapper = new MapperCopier<>(User.class, UserEntity.class);
		Stopwatch mapperWatch = Stopwatch.createStarted();
		for (Integer times : timesList) {
			long start = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
			for (int i = 0; i < times; i++) {
				mapper.copy(source);
			}
			long end = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.println("copier:mapper, " + "times:" + times + ", time:" + (end - start));
		}
	}
	
	/**
	 * 传入对象
	 */
	@Test
	public void mapper() {
		Copier<User, UserEntity> cglib = new CglibCopier<>(User.class, UserEntity.class);
		Stopwatch cglibWatch = Stopwatch.createStarted();
		for (Integer times : timesList) {
			long start = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			for (int i = 0; i < times; i++) {
				UserEntity target = new UserEntity();
				cglib.copy(source, target);
			}
			long end = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.println("copier:cglib, " + "times:" + times + ", time:" + (end - start));
		}
		
		Copier<User, UserEntity> mapper = new MapperCopier<>(User.class, UserEntity.class);
		Stopwatch mapperWatch = Stopwatch.createStarted();
		for (Integer times : timesList) {
			long start = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
			for (int i = 0; i < times; i++) {
				UserEntity target = new UserEntity();
				mapper.copy(source, target);
			}
			long end = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.println("copier:mapper, " + "times:" + times + ", time:" + (end - start));
		}
	}

}
