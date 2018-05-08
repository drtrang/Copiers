package com.github.trang.copiers.test.coveralls;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Ignore;
import org.junit.Test;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.orika.OrikaCopier;
import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserEntity;
import com.github.trang.copiers.test.util.MockUtils;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

/**
 * @author trang
 */
public class OrikaCopierTest {

    private User user = MockUtils.newUser();

    @Test
    public void defaultOrika() {
        Copier<User, UserEntity> copier = Copiers.create(User.class, UserEntity.class);
        UserEntity target = copier.copy(user);

        System.out.println(user);
        System.out.println(target);

        assertThat(target.getAge(), equalTo(user.getAge()));
        assertThat(target.getSex(), equalTo(user.getSex()));
        // weight 属性在 user 对象中没有设置值
        assertThat(target.getWeight(), nullValue(Long.class));
        assertThat(target.getHeight(), equalTo(user.getHeight()));
        // 属性不一致
        assertThat(target.getUsername(), nullValue());
        assertThat(target.getHobbits(), equalTo(user.getHobbits()));
        assertThat(target.getHandsome(), equalTo(user.getHandsome()));
        assertThat(target.getHouse(), equalTo(user.getHouse()));
        // 属性不一致
        assertThat(target.getWife().getUsername(), nullValue());
        // 使用上级对象的设置，跳过拷贝
        assertThat(target.getWife().getAge(), equalTo(user.getWife().getAge()));
        assertThat(target.getWife().getSex(), equalTo(user.getWife().getSex()));
        assertThat(target.getWife().getHeight(), equalTo(user.getWife().getHeight()));
        assertThat(target.getWife().getWeight(), equalTo(user.getWife().getWeight().longValue()));
        assertThat(target.getWife().getHouse(), equalTo(user.getWife().getHouse()));
        assertThat(target.getOther(), nullValue());
    }

    @Test
    @Ignore
    public void orika() {
        OrikaCopier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
                .skip("age", "sex")
                .field("name", "username")
                .field("wife.sex", "wife.sex")
                .customize(new CustomMapper<User, UserEntity>() {
                    @Override
                    public void mapAtoB(User source, UserEntity target, MappingContext context) {
                        target.setUsername("prefix:" + source.getName());
                    }
                })
                .register();

        UserEntity target = copier.copy(user);

        System.out.println(user);
        System.out.println(target);

        // age 属性跳过拷贝
        assertThat(target.getAge(), nullValue(Integer.class));
        // sex 属性跳过拷贝
        assertThat(target.getSex(), nullValue(Byte.class));
        // weight 属性在 user 对象中没有设置值
        assertThat(target.getWeight(), nullValue(Long.class));
        assertThat(target.getHeight(), equalTo(user.getHeight()));
        assertThat(target.getUsername(), equalTo("prefix:" + user.getName()));
        assertThat(target.getHobbits(), equalTo(user.getHobbits()));
        assertThat(target.getHandsome(), equalTo(user.getHandsome()));
        assertThat(target.getHouse(), equalTo(user.getHouse()));
        assertThat(target.getWife().getUsername(), equalTo("prefix:" + user.getWife().getName()));
        // 使用上级对象的设置，跳过拷贝
        assertThat(target.getWife().getAge(), nullValue());
        // 使用自定义的设置
        // assertThat(target.getWife().getSex(), equalTo(user.getWife().getSex()));
        assertThat(target.getWife().getSex(), nullValue());
        assertThat(target.getWife().getHeight(), equalTo(user.getWife().getHeight()));
        assertThat(target.getWife().getWeight(), equalTo(user.getWife().getWeight().longValue()));
        assertThat(target.getWife().getHouse(), equalTo(user.getWife().getHouse()));
        assertThat(target.getOther(), nullValue(String.class));
    }

    @Test
    public void list2String() {
        Copier<SimpleSource, SimpleTarget> copier = Copiers.createOrika(SimpleSource.class, SimpleTarget.class)
                .field("statusList", "statuses")
                .field("typeList", "types")
                .skip("id")
                // 自定义 constructor() 需在 skip() 之后
                .constructor("time")
                .register();
        Copier<SimpleSource, SimpleTarget> copier2 = Copiers.createOrika(SimpleSource.class, SimpleTarget.class)
                .field("statusList", "statuses")
                .field("typeList", "types")
                .skip("id")
                // 自定义 constructor() 需在 skip() 之后
                .constructor("time")
                .register();
        SimpleSource source = new SimpleSource(1, System.currentTimeMillis());
//        source.setStatusList(newArrayList(1,2,3));
        source.setTypeList(newArrayList(1.1,2.22,3.333));
        SimpleTarget target = copier.copy(source);
        System.out.println(target);
    }

}