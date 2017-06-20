package com.github.trang.copiers.test.container;

import com.baidu.unbiz.easymapper.transformer.Transformer;
import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.github.trang.copiers.test.bean.UserEntity;
import com.github.trang.copiers.test.bean.User;
import com.github.trang.copiers.test.bean.UserForm;
import com.github.trang.copiers.test.bean.UserVo;

public class CopierContainer {

    public static final Copier<User, UserEntity> USER_TO_ENTITY =
            Copiers.createMapper(User.class, UserEntity.class)
                    .field("name", "username")
                    .field("weight", "weight", new Transformer<Integer, Long>() {
                        @Override
                        public Long transform(Integer source) {
                            return source.longValue();
                        }
                    })
                    .register();

    public static final Copier<UserForm, User> FORM_TO_USER =
            Copiers.create(UserForm.class, User.class);

    public static final Copier<User, UserVo> USER_TO_VO =
            Copiers.create(User.class, UserVo.class);

    public static final Copier<User, UserForm> USER_TO_FORM =
            Copiers.create(User.class, UserForm.class);

}