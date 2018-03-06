package com.github.trang.copiers.test.coveralls;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserEntity;
import com.github.trang.copiers.test.util.MockUtils;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author trang
 */
public class OrikaCopierTest {

    private User user = MockUtils.newUser();

    @Test
    public void orika() {
        MapperFactory mapperFactory = new Builder().mapNulls(false).build();
        mapperFactory.classMap(User.class, UserEntity.class)
                .exclude("age")
                .exclude("sex")
                .field("name", "username")
                .fieldMap("wife.sex", "wife.sex").add()
                .byDefault()
                .register();
        BoundMapperFacade<User, UserEntity> mapperFacade = mapperFactory.getMapperFacade(User.class, UserEntity.class);
        UserEntity target = mapperFacade.map(user);

        // age 属性跳过拷贝
        assertThat(target.getAge(), nullValue(Integer.class));
        // sex 属性跳过拷贝
        assertThat(target.getSex(), nullValue(Byte.class));
        // weight 属性在 user 对象中没有设置值
        assertThat(target.getWeight(), nullValue(Long.class));
        assertThat(target.getHeight(), equalTo(user.getHeight()));
        assertThat(target.getUsername(), equalTo(user.getName()));
        assertThat(target.getHobbits(), equalTo(user.getHobbits()));
        assertThat(target.getHandsome(), equalTo(user.getHandsome()));
        assertThat(target.getHouse(), equalTo(user.getHouse()));
        assertThat(target.getWife().getUsername(), equalTo(user.getWife().getName()));
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
    public void copier() {
        Copier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
                // 跳过拷贝的属性，支持配置多个
                .skip("age", "sex")
                // 自定义属性映射，只映射名称，适用于类型一致名称不同的场景
                .field("name", "username")
                .field("wife.sex", "wife.sex")
                // 自定义属性映射，声明映射关系，适用于类型不一致的场景
                .nulls()
                .register();
        UserEntity target = copier.copy(user);

        // age 属性跳过拷贝
        assertThat(target.getAge(), nullValue(Integer.class));
        // sex 属性跳过拷贝
        assertThat(target.getSex(), nullValue(Byte.class));
        // weight 属性在 user 对象中没有设置值
        assertThat(target.getWeight(), nullValue(Long.class));
        assertThat(target.getHeight(), equalTo(user.getHeight()));
        assertThat(target.getUsername(), equalTo(user.getName()));
        assertThat(target.getHobbits(), equalTo(user.getHobbits()));
        assertThat(target.getHandsome(), equalTo(user.getHandsome()));
        assertThat(target.getHouse(), equalTo(user.getHouse()));
        assertThat(target.getWife().getUsername(), equalTo(user.getWife().getName()));
        // 使用上级对象的设置，跳过拷贝
        assertThat(target.getWife().getAge(), nullValue());
        // 使用自定义的设置
        assertThat(target.getWife().getSex(), equalTo(user.getWife().getSex()));
        assertThat(target.getWife().getHeight(), equalTo(user.getWife().getHeight()));
        assertThat(target.getWife().getWeight(), equalTo(user.getWife().getWeight().longValue()));
        assertThat(target.getWife().getHouse(), equalTo(user.getWife().getHouse()));
        assertThat(target.getOther(), nullValue(String.class));

        UserEntity target2 = new UserEntity();
        target2.setAge(100);
        target2.setUsername("nobody");
        target2.setWeight(70L);
        copier.copy(user, target2);

        // age 属性跳过拷贝，所以使用 target2 的值
        assertThat(target2.getAge(), equalTo(100));
        // sex 属性跳过拷贝
        assertThat(target2.getSex(), nullValue());
        // 相同属性拷贝后 target2 的值被覆盖
        assertThat(target2.getUsername(), equalTo(user.getName()));
        // 拷贝 null 的配置已开启，所以拷贝后 target2 的值被覆盖，关闭后不会被覆盖
        assertThat(target2.getWeight(), nullValue());
        assertThat(target2.getHeight(), equalTo(user.getHeight()));
        assertThat(target2.getHobbits(), equalTo(user.getHobbits()));
        assertThat(target2.getHandsome(), equalTo(user.getHandsome()));
        assertThat(target2.getHouse(), equalTo(user.getHouse()));
    }

}