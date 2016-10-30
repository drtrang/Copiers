# Copiers

---
A Friendly Bean Copier Packaging.

---

[toc]

---

## 1. 介绍
**Copiers** 是基于 Bean 拷贝工具的再次封装，隐藏了 Bean 拷贝的具体过程，仅对外暴露友好的 Fluent API。

**Copiers** 对不同的拷贝方式做了统一的封装，用户无需逐一学习不同工具的 API，调用 `copy()` 方法即可完成 Bean 拷贝，调用 `map()` 方法即可完成 List 拷贝，简单高效。

## 2. 底层实现
**Copiers** 目前有两种底层实现：`Cglib` & `EasyMapper`，两种方式差明显，可根据实际情况任选其一或二者结合使用。

### 2.1 Cglib
Cglib 的 BeanCopier 是目前性能最好的拷贝方式，基于 ASM 字节码增强技术，千万次拷贝在 **1s** 以内，与其它方式差距明显。但高性能带来的显著缺点是功能单一、拓展性差，BeanCopier 仅支持源对象到目标对象的完全拷贝，不支持自定义映射，仅有的 Convert 拓展也只能对拷贝的 value 做处理，很多情况下不满足实际的业务需求。

### 2.2 EasyMapper
[EasyMapper](https://github.com/neoremind/easy-mapper) 基于 Javassist 的字节码技术，千万次拷贝在 **4s** 左右，虽不如 BeanCopier，但大幅领先 Apache Commons BeanUtils、Dozer 等其它拷贝方式。且 EasyMapper 的优点在于使用灵活、扩展性强，具体情况可以查看 EasyMapper 的 Github， 地址：https://github.com/neoremind/easy-mapper

## 3. 使用方法
### 3.1 简单拷贝
简单拷贝，只拷贝源对象和目标对象字段相同的部分，有两种实现，基于 EasyMapper 的`Copiers.create()`和基于 Cglib 的`Copiers.createCglib()`，区别是 EasyMapper 不会拷贝值为 null 的字段，而Cglib会。
```
//拷贝对象，创建新对象，使用EasyMapper
User user = User.of("trang", 23);
UserEntity entity = Copiers.create(User.class, UserEntity.class).copy(user);

//拷贝对象，传入目标对象
User user = User.of("trang", 23);
UserEntity entity = UserEntity.of();
Copiers.create(User.class, UserEntity.class).copy(user, entity);

//拷贝List
User user1 = User.of("trang", 23);
User user2 = User.of("t", 32);
List<User> users = ImmutableList.of(user1, user2);
List<UserEntity> entities = Copiers.create(User.class, UserEntity.class).map(users);

//拷贝对象，使用cglib，会拷贝null值
User user = User.of("trang", null);
UserEntity entity = UserEntity.of("t", 23);
Copiers.createCglib(User.class, UserEntity.class).copy(user, entity);
```

### 3.2 复杂拷贝
若简单拷贝不满足业务需求，可以通过`Copiers.createMapper()`拓展拷贝方式，基于 EasyMapper 的特性实现。
```
//将源对象的name字段映射到目标对象的username字段
Copiers.createMapper(User.class, UserEntity.class).field("name", "username").regist();

//排除拷贝字段
Copiers.createMapper(User.class, UserEntity.class).skip("name", "sex").regist();

//强制拷贝值为null的字段，默认不拷贝
Copiers.createMapper(User.class, UserEntity.class).skip("name").isNull(true).regist();
```