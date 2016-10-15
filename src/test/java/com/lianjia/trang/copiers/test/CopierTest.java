package com.lianjia.trang.copiers.test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.lianjia.trang.copiers.bean.NetData;
import com.lianjia.trang.copiers.bean.NetDataExportEntity;
import com.lianjia.trang.copiers.impl.CglibBeanCopier;
import com.lianjia.trang.copiers.impl.MapperBeanCopier;
import com.lianjia.trang.copiers.inter.BeanCopier;

public class CopierTest {
	
	private NetData source = new NetData();
	//a thousand ~ a hundred million
	private List<Integer> timesList = ImmutableList.of(1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000);
	
	@Before
	public void before() {
		source.setAreaCode("areaCode");
		source.setAreaCodes(Lists.newArrayList("areaCodes"));
		source.setAreaName("areaName");
		source.setAuditOpinion("auditOpinion");
		source.setAuditorMobile("auditorMobile");
		source.setAuditorName("auditorName");
		source.setAuditorUcid(1L);
		source.setAuditStatus(1);
		source.setBizNo("bizNo");
		source.setBuildArea(BigDecimal.TEN);
		source.setBuildAreaRegion("buildAreaRegion");
		source.setBuildingId(1L);
		source.setBuildingNo("buildingNo");
		source.setBuildYear("buildYear");
		source.setCommission(BigDecimal.ONE);
		source.setContractNo("contractNo");
	}
	
	/**
	 * 重新生成对象
	 */
	@Test
	public void copier() {
		BeanCopier<NetData, NetDataExportEntity> cglib = new CglibBeanCopier<>(NetData.class, NetDataExportEntity.class);
		Stopwatch cglibWatch = Stopwatch.createStarted();
		for (Integer times : timesList) {
			long start = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			for (int i = 0; i < times; i++) {
				cglib.copy(source);
			}
			long end = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.println("copier:cglib, " + "times:" + times + ", time:" + (end - start));
		}
		
		BeanCopier<NetData, NetDataExportEntity> mapper = new MapperBeanCopier<>(NetData.class, NetDataExportEntity.class);
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
		BeanCopier<NetData, NetDataExportEntity> cglib = new CglibBeanCopier<>(NetData.class, NetDataExportEntity.class);
		Stopwatch cglibWatch = Stopwatch.createStarted();
		for (Integer times : timesList) {
			long start = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			for (int i = 0; i < times; i++) {
				NetDataExportEntity target = new NetDataExportEntity();
				cglib.copy(source, target);
			}
			long end = cglibWatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.println("copier:cglib, " + "times:" + times + ", time:" + (end - start));
		}
		
		BeanCopier<NetData, NetDataExportEntity> mapper = new MapperBeanCopier<>(NetData.class, NetDataExportEntity.class);
		Stopwatch mapperWatch = Stopwatch.createStarted();
		for (Integer times : timesList) {
			long start = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
			for (int i = 0; i < times; i++) {
				NetDataExportEntity target = new NetDataExportEntity();
				mapper.copy(source, target);
			}
			long end = mapperWatch.elapsed(TimeUnit.MILLISECONDS);
			System.out.println("copier:mapper, " + "times:" + times + ", time:" + (end - start));
		}
	}
}
