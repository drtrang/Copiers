package com.lianjia.trang.copiers.test;

import java.util.concurrent.ConcurrentHashMap;
import org.junit.Before;
import org.junit.Test;

import com.baidu.unbiz.easymapper.metadata.MapperKey;
import com.baidu.unbiz.easymapper.metadata.TypeFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lianjia.trang.copiers.Copiers;
import com.lianjia.trang.copiers.bean.User;
import com.lianjia.trang.copiers.bean.UserEntity;
import com.lianjia.trang.copiers.bean.UserForm;
import com.lianjia.trang.copiers.bean.UserVo;
import com.lianjia.trang.copiers.container.CopierContainer;

/**
 * Copier示例
 * 
 * @author trang
 */
public class CopierTest {
	//source object
	private User source = new User();
	
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
	
	@Test
	public void m() throws InterruptedException {
		MapperKey e1 = new MapperKey(TypeFactory.valueOf(User.class), TypeFactory.valueOf(UserForm.class));
		MapperKey e2 = new MapperKey(TypeFactory.valueOf(UserForm.class), TypeFactory.valueOf(User.class));
		System.out.println(e1.equals(e2));
		
		ConcurrentHashMap<MapperKey, String> cache = new ConcurrentHashMap<>();
		cache.put(e1, "e1");
		cache.put(e2, "e2");
		System.out.println(cache);
	}
	
	@Test
	public void form_user() {
		UserForm form = new UserForm();
		User target = CopierContainer.FORM_USER_COPIER.copy(form);
		System.out.println("form:" + form);
		System.out.println("target:" + target);
		
		CopierContainer.USER_FORM_COPIER.copy(source, form);
		System.out.println("form:" + form);
		System.out.println("target:" + target);
		
		CopierContainer.FORM_USER_COPIER.copy(form, target);
		System.out.println("form:" + form);
		System.out.println("target:" + target);
	}
	
	@Test
	public void user_vo() {
		UserVo target = CopierContainer.USER_VO_COPIER.copy(source);
		System.out.println(target);
		
		User secondTarget = Copiers.create(UserVo.class, User.class).copy(target);
		//User secondTarget = CopierContainer.USER_VO_COPIER.reverse().copy(target);
		System.out.println(secondTarget);
	}
	
	@Test
	public void user_entity() {
		UserEntity target = CopierContainer.USER_ENTITY_COPIER.copy(source);
		System.out.println(target);
	}
}
