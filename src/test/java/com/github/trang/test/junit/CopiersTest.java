package com.github.trang.test.junit;

import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.test.bean.*;
import com.github.trang.test.container.CopierContainer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Copiers 测试
 *
 * @author trang
 */
public class CopiersTest {

    private User user = new User();

    @Before
    public void before() {
        user.setName("trang");
        user.setSex((byte) 0);
        user.setAge(25);
        user.setHeight(1.73);
        user.setWeight(65);
        user.setHandsome(true);
        user.setHobbits(ImmutableList.of("coding"));
        user.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        User wife = new User();
        wife.setName("meng");
        wife.setSex((byte) 1);
        wife.setAge(24);
        wife.setHeight(1.65);
        wife.setWeight(55);
        wife.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        user.setWife(wife);
    }

    /**
     * cglib 仅支持相同名称、相同类型的拷贝，且不支持级联，不支持自定义
     */
    @Test
    public void cglib() {
        UserEntity target = Copiers.createCglib(User.class, UserEntity.class).copy(user);
        System.out.println(user);
        System.out.println(target);

        User reverseUser = Copiers.createCglib(UserEntity.class, User.class).copy(target);
        System.out.println(reverseUser);
    }

    /**
     * EasyMapper 支持自定义字段映射、类型转换、级联映射等
     * http://neoremind.com/2016/08/easy-mapper-一个灵活可扩展的高性能bean-mapping类库
     */
    @Test
    public void easyMapper() {
        Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                .skip("age")
                .field("name", "username")
                .field("weight", "weight", new Transformer<Integer, Long>() {
                    @Override
                    public Long transform(Integer source) {
                        return source.longValue();
                    }
                })
                .register();
        UserEntity target = copier.copy(user);
        System.out.println(user);
        System.out.println(target);

        Copier<UserEntity, User> reverseCopier = Copiers.createMapper(UserEntity.class, User.class)
                .skip("age")
                .field("username", "name")
                .field("weight", "weight", new Transformer<Long, Integer>() {
                    @Override
                    public Integer transform(Long source) {
                        return source.intValue();
                    }
                })
                .register();
        User reverseUser = reverseCopier.copy(target);
        System.out.println(reverseUser);
    }

    @Test
    public void form_to_user() {
        UserForm form = new UserForm();
        form.setName("name");
        User target = CopierContainer.FORM_TO_USER.copy(form);
        System.out.println("form:" + form);
        System.out.println("target:" + target);

        // 覆盖 form 中存在的属性
        CopierContainer.USER_TO_FORM.copy(user, form);
        System.out.println("form:" + form);

        // 覆盖 target 中存在的属性
        CopierContainer.FORM_TO_USER.copy(form, target);
        System.out.println("target:" + target);
    }

    @Test
    public void user_to_vo() {
        UserVo target = CopierContainer.USER_TO_VO.copy(user);
        System.out.println(target);

        User secondTarget = Copiers.create(UserVo.class, User.class).copy(target);
        //User secondTarget = CopierContainer.USER_TO_VO.reverse().copy(target);
        System.out.println(secondTarget);
    }

    @Test
    public void user_to_entity() {
        UserEntity target = CopierContainer.USER_TO_ENTITY.copy(user);
        System.out.println(target);
    }

    @Test
    public void list() {
        List<User> users = ImmutableList.of(user, user);
        List<UserEntity> entities = Copiers.create(User.class, UserEntity.class).map(users);
        System.out.println(users);
        System.out.println(entities);
    }

}