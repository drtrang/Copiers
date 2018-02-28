package com.github.trang.copiers.test.coveralls;

import com.baidu.unbiz.easymapper.codegen.AtoBMapping;
import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserEntity;
import com.github.trang.copiers.test.bean.UserForm;
import com.github.trang.copiers.test.bean.UserVo;
import com.github.trang.copiers.test.plugin.CopierContainer;
import com.github.trang.copiers.test.plugin.SimpleConverter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

/**
 * Copiers 测试
 *
 * @author trang
 */
@Slf4j
public class CopiersTest {

    private User user = new User();

    @Before
    public void before() {
        user.setAge(25);
        user.setSex((byte) 0);
        user.setHeight(1.73);
        user.setWeight(null);
        user.setName("trang");
        user.setHobbits(ImmutableList.of("coding"));
        user.setHandsome(true);
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
        Copier<User, UserEntity> cglibCopier = Copiers.createCglib(User.class, UserEntity.class);
        UserEntity target = cglibCopier.copy(user);
        List<UserEntity> targets = cglibCopier.map(newArrayList(user, user));

        User reverseUser = Copiers.createCglib(UserEntity.class, User.class).copy(target);
    }

    @Test
    public void cglibWithConverter() {
        SimpleConverter converter = new SimpleConverter();
        Copier<User, UserForm> cglibCopier = Copiers.createCglib(User.class, UserForm.class, converter);
        UserForm target = cglibCopier.copy(user);
        assertThat(target.getName(), equalTo("converter:trang"));
    }

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
                .field("weight", "weight", new Transformer<Integer, Long>() {
                    @Override
                    public Long transform(Integer source) {
                        return source.longValue();
                    }
                })
                // easy-mapper 默认不支持 List 的级联拷贝，需要手动声明，否则会抛出异常
                .field("sub", "sub", new Transformer<List<User>, List<UserEntity>>() {
                    @Override
                    public List<UserEntity> transform(List<User> users) {
                        List<UserEntity> result = new ArrayList<>();
                        for (User u : users) {
                            result.add(Copiers.create(User.class, UserEntity.class).copy(u));
                        }
                        return result;
                    }
                })
                .mapping(new AtoBMapping<User, UserEntity>() {
                    @Override
                    public void map(User source, UserEntity target) {
                        target.setUsername("user:" + target.getUsername());
                    }
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
        assertThat(target.getUsername(), equalTo("user:" + user.getName()));
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
        assertThat(target2.getUsername(), equalTo("user:" + user.getName()));
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
                .field("age", "age")
                .mapping(new AtoBMapping<User, UserEntity>() {
                    @Override
                    public void map(User source, UserEntity target) {
                        target.setUsername("extra");
                        target.setAge(0);
                    }
                })
                .field("name", "username")
                .field("age", "age")
                .skip("sub", "weight")
                .register();
        UserEntity target = copier.copy(user);
        System.out.println(target);
    }

    @Test
    public void reverse() {
        Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                .skip("sub")
                .field("name", "username")
                .field("weight", "weight", new Transformer<Integer, Long>() {
                    @Override
                    public Long transform(Integer source) {
                        return source.longValue();
                    }
                })
                .register();
        UserEntity target = copier.copy(user);

        Copier<UserEntity, User> reverseCopier = Copiers.createMapper(UserEntity.class, User.class)
                .skip("sub")
                .field("username", "name")
                .field("weight", "weight", new Transformer<Long, Integer>() {
                    @Override
                    public Integer transform(Long source) {
                        return source.intValue();
                    }
                })
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