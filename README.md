# Copiers

[![Build Status](https://img.shields.io/travis/drtrang/Copiers/master.svg?style=flat-square)](https://www.travis-ci.org/drtrang/Copiers)
[![Coverage Status](https://img.shields.io/coveralls/drtrang/Copiers/master.svg?style=flat-square)](https://coveralls.io/github/drtrang/Copiers?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.drtrang/copiers.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/com.github.drtrang/copiers)
[![GitHub Release](https://img.shields.io/github/release/drtrang/Copiers.svg?style=flat-square)](https://github.com/drtrang/Copiers/releases)
[![License](http://img.shields.io/badge/license-apache%202-blue.svg?style=flat-square)](https://github.com/drtrang/Copiers/blob/master/LICENSE)

Copiers 是一个优雅的 Bean 拷贝工具，可通过友好的 Fluent API 帮助用户完成拷贝对象的操作。

## 依赖
```xml
<!-- java7 -->
<dependency>
    <groupId>com.github.drtrang</groupId>
    <artifactId>copiers</artifactId>
    <version>1.3.0</version>
</dependency>

<!-- java8 or higher -->
<dependency>
    <groupId>com.github.drtrang</groupId>
    <artifactId>copiers</artifactId>
    <version>2.3.0</version>
</dependency>
```

## 底层实现
Copiers 目前有两种实现：`Cglib` & `Orika`，用户可以通过工厂方法来切换底层的拷贝方式。

```java
// orika
Copiers.create(Class<F> sourceClass, Class<T> targetClass)
Copiers.createOrika(Class<F> sourceClass, Class<T> targetClass)
// cglib
Copiers.createCglib(Class<F> sourceClass, Class<T> targetClass)
Copiers.createCglib(Class<F> sourceClass, Class<T> targetClass, Converter converter)
```

### Cglib
Cglib 中的 BeanCopier 是目前性能最好的拷贝方式，基于 ASM 字节码增强技术，千万次拷贝仅在 **1.5s** 左右，但高性能带来的显著缺点是功能单一、拓展性差，BeanCopier 仅支持源对象到目标对象的**完全拷贝**，不支持自定义映射，Convert 拓展也只能对拷贝的 value 做处理，很多情况下不满足实际的业务需求。

**注意：**
1. BeanCopier 只拷贝名称和类型都相同的属性
2. 当目标类的 setter 方法少于 getter 方法时，会导致创建 BeanCopier 失败
3. 一旦使用 Converter，BeanCopier 将完全使用 Converter 中定义的规则去拷贝，所以在 `convert()` 方法中要考虑到所有的属性，否则会抛出 `ClassCastException`

### Orika
[Orika](https://github.com/orika-mapper/orika) 基于 Javassist 字节码技术，千万次拷贝在 **8s** 左右。虽不如 Cglib，但 Orika 的优点在于使用灵活、扩展性强，具体情况可以查看 Orika 的 Github，地址：https://github.com/neoremind/easy-mapper，中文文档地址：http://neoremind.com/2016/08/easy-mapper-一个灵活可扩展的高性能bean-mapping类库

**注意：**
1. 拷贝结果为浅拷贝
2. 支持级联拷贝，但是需要提前注册好级联对象之间的映射关系
3. 支持与 Java8 结合使用，支持 lambda 与 stream

## 使用方式
通过工厂方法建立 Source 与 Target 之间的关系后，调用 `copy()` 方法即可完成 Bean 拷贝，调用 `map()` 方法即可完成 List 拷贝，简洁高效。

### Cglib
```java
// 拷贝对象，创建新对象
User user = User.of("trang", 25);
UserEntity entity = Copiers.createCglib(User.class, UserEntity.class).copy(user);

// 拷贝对象，传入已有对象，完全拷贝
User user = User.of("trang", null);
UserEntity entity = UserEntity.of("meng", 24);
Copiers.createCglib(User.class, UserEntity.class).copy(user, entity);

// 拷贝 List，创建新 List
User trang = User.of("trang", 25);
User meng = User.of("meng", 24);
List<User> family = ImmutableList.of(trang, meng);
List<UserEntity> entries = Copiers.createCglib(User.class, UserEntity.class).map(family);
```

### Orika
```java
// 拷贝对象，创建新对象
User user = User.of("trang", 25);
UserEntity entity = Copiers.create(User.class, UserEntity.class).copy(user);

// 拷贝对象，传入已有对象，不会拷贝值为 null 的属性（可以配置）
User user = User.of("trang", null);
UserEntity entity = UserEntity.of("meng", 24);
Copiers.create(User.class, UserEntity.class).copy(user, entity);

// 拷贝 List，创建新 List
User trang = User.of("trang", 25);
User meng = User.of("meng", 24);
List<User> family = ImmutableList.of(trang, meng);
List<UserEntity> entries = Copiers.create(User.class, UserEntity.class).map(family);
```

## Orika 进阶
Orika 支持强大的自定义关系映射，并且使用缓存技术，一次注册后续直接使用。

```java
// 跳过拷贝的属性，支持配置多个
Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
        .skip("age", "sex")
        .register();

// 将源对象的 `name` 属性映射到目标对象的 `username` 属性，只映射名称，适用于类型一致名称不同的场景
Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
        .field("name", "username")
        .register();

// 将源对象的 `weight` 属性映射到目标对象的 `weight` 属性，声明映射关系，适用于类型不一致的场景
Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
        .field("weight", "weight", new Transformer<Integer, Long>() {
            @Override
            public Long transform(Integer source) {
                return source.longValue();
            }
        })
        .register();

// 开启拷贝 null 值，默认 Orika 不会将源对象中值为 null 的属性拷贝到目标对象中，如有需要可以手动开启
Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
        .nulls()
        .register();

// 全局自定义映射关系，若和其它方法结合使用则在最后执行
Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
        .mapping(new AtoBMapping<User, UserEntity>() {
            @Override
            public void map(User source, UserEntity target) {
                target.setUsername("extra");
            }
        })
        .register();
```

当然，以上映射关系可以任意搭配使用，同样只需一次注册。

```java
// 创建 copier
Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                // 跳过拷贝
                .skip("age", "sex")
                // 自定义属性映射
                .field("name", "username")
                // 自定义属性映射
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
                // 全局自定义映射关系
                .mapping(new AtoBMapping<User, UserEntity>() {
                    @Override
                    public void map(User source, UserEntity target) {
                        target.setUsername("user:" + target.getUsername());
                    }
                })
                .register();
```

如果你在使用 Java8 那就更好了，利用 Copiers 可以更容易的完成拷贝操作。

```java
// 创建 copier
Copier<User, UserEntity> copier = Copiers.createMapper(User.class, UserEntity.class)
                .skip("sub")
                .field("name", "username")
                .field("weight", "weight", Integer::longValue)
                .mapping((source, target) -> target.setUsername("user:" + target.getUsername()))
                .register();
// 使用 Stream 拷贝 List
sourceList.stream().map(copier::copy).collect(toList());
// 2.2.1 版本之后可以如下调用，等同于上边代码
copier.map(sourceList);
// 使用并行 Stream 拷贝 List
sourceList.parallelStream().map(copier::copy).collect(toList());
// 2.2.1 版本之后可以如下调用，等同于上边代码
copier.parallel().map(sourceList);
// 使用 Optional 拷贝 List
Optional.of(name)
        .map(service::selectByName)
        .map(copier::map) 
        .orElse(emptyList());
```

## Change Log
[Release Notes](https://github.com/drtrang/Copiers/releases)

## TODO
任何意见和建议可以提 [ISSUE](https://github.com/drtrang/Copiers/issues)，我会酌情加到 [TODO List](https://github.com/drtrang/Copiers/blob/master/TODO.md)，一般情况一周内迭代完毕。

## About Me
QQ：349096849<br>
Email：donghao.l@hotmail.com<br>
Blog：[Trang's Blog](http://blog.trang.space)