package com.lianjia.trang.copiers.test;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.lianjia.trang.copiers.Copiers;
import com.lianjia.trang.copiers.bean.User;
import com.lianjia.trang.copiers.bean.UserEntity;
import com.lianjia.trang.copiers.bean.UserForm;
import com.lianjia.trang.copiers.bean.UserVo;
import com.lianjia.trang.copiers.container.CopierContainer;
import com.lianjia.trang.copiers.inter.Copier;

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
		Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class).field("name", "username").regist();
		UserEntity target = copier.copy(source);
		System.out.println(target);
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
	
	@Test
	public void list() {
		List<User> users = ImmutableList.of(source, source);
		UserEntity entity = Copiers.create(User.class, UserEntity.class).copy(source);
		System.out.println("source:" + source);
		System.out.println("entity:" + entity);
		
		List<UserEntity> entities = Copiers.create(User.class, UserEntity.class).map(users);
		System.out.println(users);
		System.out.println(entities);
	}
}
