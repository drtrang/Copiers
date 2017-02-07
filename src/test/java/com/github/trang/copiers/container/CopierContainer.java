package com.github.trang.copiers.container;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.bean.User;
import com.github.trang.copiers.bean.UserEntity;
import com.github.trang.copiers.bean.UserForm;
import com.github.trang.copiers.bean.UserVo;
import com.github.trang.copiers.mapper.MapperCopier;

public class CopierContainer {
    public static final MapperCopier<User, UserEntity> USER_ENTITY_COPIER = Copiers.create(User.class, UserEntity.class);
    public static final MapperCopier<UserForm, User> FORM_USER_COPIER = Copiers.create(UserForm.class, User.class);
    public static final MapperCopier<User, UserVo> USER_VO_COPIER = Copiers.create(User.class, UserVo.class);

    public static final MapperCopier<User, UserForm> USER_FORM_COPIER = Copiers.create(User.class, UserForm.class);
}
