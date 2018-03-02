package com.github.trang.copiers.test.coveralls;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserEntity;
import com.github.trang.copiers.test.bean.UserForm;
import com.github.trang.copiers.test.bean.UserVo;
import com.github.trang.copiers.test.plugin.CopierContainer;
import com.github.trang.copiers.test.util.MockUtils;
import com.github.trang.easymapper.MapperFactory;
import com.github.trang.easymapper.transformer.Transformer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author trang
 */
@Slf4j
public class MapperCopierTest {

    private User user = MockUtils.newUser();

    /**
     * EasyMapper 支持自定义属性映射、类型转换、级联映射等
     * http://neoremind.com/2016/08/easy-mapper-一个灵活可扩展的高性能bean-mapping类库
     */
    @Test
    public void easyMapper() {
        Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                // 跳过拷贝的属性，支持配置多个
                .skip("age", "sex")
                // 自定义属性映射，只映射名称，适用于类型一致名称不同的场景
                .field("name", "username")
                // 自定义属性映射，声明映射关系，适用于类型不一致的场景
                .field("weight", "weight", Integer::longValue)
                // easy-mapper 默认不支持 List 的级联拷贝，需要手动声明，否则会抛出异常
                .field("sub", "sub", (Transformer<List<User>, List<UserEntity>>) users -> {
                    List<UserEntity> result = new ArrayList<>();
                    for (User u : users) {
                        result.add(Copiers.create(User.class, UserEntity.class).copy(u));
                    }
                    return result;
                })
                // 开启拷贝 null 值，默认关闭
                .nulls()
                .register();
        UserEntity target = copier.copy(user);

        // age 属性跳过拷贝
        assertThat(target.getAge(), nullValue());
        // sex 属性跳过拷贝
        assertThat(target.getSex(), nullValue());
        // weight 属性在 user 对象中没有设置值
        assertThat(target.getWeight(), nullValue());
        assertThat(target.getHeight(), equalTo(user.getHeight()));
        assertThat(target.getUsername(), equalTo(user.getName()));
        assertThat(target.getHobbits(), equalTo(user.getHobbits()));
        assertThat(target.getHandsome(), equalTo(user.getHandsome()));
        assertThat(target.getHouse(), equalTo(user.getHouse()));

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

    @Test
    public void mapping() {
        Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                .field("age", "age", (Transformer<Integer, Integer>) age -> age - 1)
                .field("name", "username")
                .skip("sub", "weight")
                .register();
        UserEntity target = copier.copy(user);
        System.out.println(target);
        MapperFactory.getCopyByRefMapper().mapClass(User.class, UserEntity.class)
                .field("age", "age", (Transformer<Integer, Integer>) age -> age - 1)
                .register();
    }

    @Test
    public void reverse() {
        Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                .skip("sub")
                .field("name", "username")
                .field("weight", "weight", Integer::longValue)
                .register();
        UserEntity target = copier.copy(user);

        Copier<UserEntity, User> reverseCopier = Copiers.createMapper(UserEntity.class, User.class)
                .skip("sub")
                .field("username", "name")
                .field("weight", "weight", Long::intValue)
                .register();
        User reverseUser = reverseCopier.copy(target);
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

}