package com.github.trang.copiers.test.coveralls;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserEntity;
import com.github.trang.copiers.test.bean.UserForm;
import com.github.trang.copiers.test.plugin.SimpleConverter;
import com.github.trang.copiers.test.util.MockUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Copiers 测试
 *
 * @author trang
 */
@Slf4j
public class CglibCopierTest {

    private User user = MockUtils.newUser();

    /**
     * cglib 仅支持相同名称、相同类型的拷贝，且不支持级联，不支持自定义
     */
    @Test
    public void cglib() {
        Copier<User, UserEntity> cglibCopier = Copiers.createCglib(User.class, UserEntity.class);
        UserEntity target = cglibCopier.copy(user);
        List<UserEntity> targets = cglibCopier.copyList(newArrayList(user, user));

        User reverseUser = Copiers.createCglib(UserEntity.class, User.class).copy(target);
    }

    @Test
    public void cglibWithConverter() {
        SimpleConverter converter = new SimpleConverter();
        Copier<User, UserForm> cglibCopier = Copiers.createCglib(User.class, UserForm.class, converter);
        UserForm target = cglibCopier.copy(user);
        assertThat(target.getName(), equalTo("converter:trang"));
    }

}