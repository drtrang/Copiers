package com.github.trang.copiers.test;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.bean.User;
import com.github.trang.copiers.bean.UserEntity;
import com.github.trang.copiers.bean.UserForm;
import com.github.trang.copiers.bean.UserVo;
import com.github.trang.copiers.container.CopierContainer;
import com.github.trang.copiers.inter.Copier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    public void feature() {
        Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                .skip("name", "username")
                .regist();
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
