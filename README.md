# Copiers

[![Build Status](https://img.shields.io/travis/drtrang/Copiers/master.svg?style=flat-square)](https://www.travis-ci.org/drtrang/Copiers)
[![Coverage Status](https://img.shields.io/coveralls/drtrang/Copiers/master.svg?style=flat-square)](https://coveralls.io/github/drtrang/Copiers?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.drtrang/copiers.svg?style=flat-square)](https://maven-badges.herokuapp.com/maven-central/com.github.drtrang/copiers)
[![GitHub Release](https://img.shields.io/github/release/drtrang/Copiers.svg?style=flat-square)](https://github.com/drtrang/Copiers/releases)
[![License](http://img.shields.io/badge/license-apache%202-blue.svg?style=flat-square)](https://github.com/drtrang/Copiers/blob/master/LICENSE)

Copiers 是一个优雅的 Bean 拷贝工具，可通过友好的 Fluent API 帮助用户完成拷贝对象的操作。

## 依赖
```xml
<!-- java8 or higher -->
<dependency>
    <groupId>com.github.drtrang</groupId>
    <artifactId>copiers</artifactId>
    <version>2.5.1</version>
</dependency>

<!-- java7 -->
<dependency>
    <groupId>com.github.drtrang</groupId>
    <artifactId>copiers</artifactId>
    <version>1.4.2</version>
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
Cglib 中的 BeanCopier 是目前性能最好的拷贝方式，基于 ASM 字节码增强技术，千万次拷贝仅需毫秒即可完成，但高性能带来的显著缺点是功能单一、拓展性差，BeanCopier 仅支持源对象到目标对象的**完全拷贝**，不支持自定义映射，Convert 拓展也只能对拷贝的 value 做处理，很多情况下不满足实际的业务需求。

> **注意：**
> 1. BeanCopier 只拷贝名称和类型都相同的属性
> 2. 当目标类的 setter 方法少于 getter 方法时，会导致创建 BeanCopier 失败
> 3. 一旦使用 Converter，BeanCopier 将完全使用 Converter 中定义的规则去拷贝，所以在 `convert()` 方法中要考虑到所有的属性，否则会抛出 `ClassCastException`

### Orika
[Orika](https://github.com/orika-mapper/orika) 基于 Javassist 字节码技术，千万次拷贝在 **5s** 左右。性能虽不如 Cglib，但 Orika 的优点在于灵活性、扩展性强，详细介绍可以查看 Orika 的 Github：https://github.com/orika-mapper/orika，另外强烈推荐这篇使用教程：http://www.baeldung.com/orika-mapping

> **注意：**
> 1. 拷贝结果为浅拷贝
> 2. 支持级联拷贝，但是需要提前注册好级联对象之间的映射关系，且可以使用 `parent()` 方法来指定父类
> 3. 支持源对象中的集合类型直接拷贝到目标对象的集合
> 4. 不同类型有默认的 Converter 做转换

## 使用方式
通过工厂方法建立 sourceClass 与 targetClass 之间的关系后，调用 `copy()` 方法即可完成 Bean 拷贝，调用 `copyList()` 方法即可完成 List 拷贝，简洁高效。

### Cglib
```java
// 建立 User.class 与 UserEntity.class 之间的映射关系
Copier copier = Copiers.createCglib(User.class, UserEntity.class);

// 拷贝对象，创建新对象
User user = User.of("trang", 25);
UserEntity entity = copier.copy(user);

// 拷贝对象，传入已有对象，完全拷贝
User user = User.of("trang", null);
UserEntity entity = UserEntity.of("meng", 24);
copier.copy(user, entity);

// 拷贝 List，创建新 List
User trang = User.of("trang", 25);
User meng = User.of("meng", 24);
List<User> family = ImmutableList.of(trang, meng);
List<UserEntity> entries = copier.copyList(family);
```

### Orika
```java
// 建立 User.class 与 UserEntity.class 之间的映射关系
Copier copier = Copiers.create(User.class, UserEntity.class);

// 拷贝对象，创建新对象
User user = User.of("trang", 25);
UserEntity entity = copier.copy(user);

// 拷贝对象，传入已有对象，不会拷贝值为 null 的属性（可以配置）
User user = User.of("trang", null);
UserEntity entity = UserEntity.of("meng", 24);
copier.copy(user, entity);

// 拷贝 List，创建新 List
User trang = User.of("trang", 25);
User meng = User.of("meng", 24);
List<User> family = ImmutableList.of(trang, meng);
List<UserEntity> entries = copier.copyList(family);
```

## Orika 进阶
Orika 支持强大的自定义关系映射，并且使用缓存技术，一次注册后续直接使用。

```java
// 跳过拷贝的属性，支持配置多个
// Orika 默认使用全参构造，这时 skip() 不生效，需要使用不包含 skip 属性的构造方法，
// 所以 Copiers 将默认值改为了无参构造，用户也可以在调用 skip() 后使用 constructor() 方法自己指定
Copier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
        .skip("age", "sex")
        .register();

// 将源对象的 `name` 属性映射到目标对象的 `username` 属性
Copier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
        .field("name", "username")
        .register();

// 开启拷贝 null 值，默认 Orika 不会将源对象中值为 null 的属性拷贝到目标对象中，如有需要可以手动开启
Copier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
        .nulls()
        .register();

// 全局自定义映射关系，若和其它方法结合使用则在最后执行
Copier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
        .customize(new CustomMapper<User, UserEntity>() {
            @Override
            public void mapAtoB(User source, UserEntity target, MappingContext context) {
                target.setUsername("prefix:" + source.getName());
            }
        })
        .register();
```

当然，以上映射关系可以任意搭配使用，同样只需一次注册。

```java
// 创建 copier
Copier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
                // 跳过拷贝
                .skip("age", "sex")
                // 自定义属性映射
                .field("name", "username")
                // 全局自定义映射关系
                .customize(new CustomMapper<User, UserEntity>() {
                    @Override
                    public void mapAtoB(User source, UserEntity target, MappingContext context) {
                        target.setUsername("prefix:" + source.getName());
                    }
                })
                .register();
```

如果你在使用 Java8 那就更好了，利用 Copiers 可以更容易的完成拷贝操作。

```java
// 创建 copier
Copier<User, UserEntity> copier = Copiers.createOrika(User.class, UserEntity.class)
                .field("name", "username")
                .register();
// 使用 Stream 拷贝 List
sourceList.stream().map(copier::copy).collect(toList()); //copier.copyList(sourceList);
// 使用并行 Stream 拷贝 List
sourceList.parallelStream().map(copier::copy).collect(toList());
// 使用 Optional 拷贝 List
Optional.of(name)
        .map(service::selectByName)
        .map(copier::copyList) 
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