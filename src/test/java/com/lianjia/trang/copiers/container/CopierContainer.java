package com.lianjia.trang.copiers.container;

import com.lianjia.trang.copiers.Copiers;
import com.lianjia.trang.copiers.bean.User;
import com.lianjia.trang.copiers.bean.UserEntity;
import com.lianjia.trang.copiers.bean.UserForm;
import com.lianjia.trang.copiers.bean.UserVo;
import com.lianjia.trang.copiers.impl.MapperCopier;

public class CopierContainer {
	public static final MapperCopier<User, UserEntity> USER_ENTITY_COPIER = Copiers.create(User.class, UserEntity.class);
	public static final MapperCopier<UserForm, User> FORM_USER_COPIER = Copiers.create(UserForm.class, User.class);
	public static final MapperCopier<User, UserVo> USER_VO_COPIER = Copiers.create(User.class, UserVo.class);
	
	public static final MapperCopier<User, UserForm> USER_FORM_COPIER = Copiers.create(User.class, UserForm.class);
}
