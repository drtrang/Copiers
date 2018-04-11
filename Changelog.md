# Changelog

## 1.4.2 & 2.4.2
1. OrikaMapper 增加初始化标识

## 1.4.0 & 2.4.0
1. **去掉并行和有序功能**
2. **更改包结构**
3. 支持自定义 Orika，并添加了额外的 Converters，使用起来更加便利

## 1.3.0 & 2.3.0
1. 将 EasyMapper 全面替换为 Orika，避免初始化异常
2. 通过享元模式为创建好的 Copier 对象增加缓存，避免大量对象被创建

## 2.2.1
1. 将 Copier 分离成职责更加单一的接口
2. 新增并行流拷贝，开启并行时可额外开启顺序拷贝
3. 新增拷贝 Set、Array
4. 拷贝集合的时候最大限度的返回传入的对象类，如传入 LinkedList 则返回 LinkedList

## 1.2.1
1. 将 Copier 分离成职责更加单一的接口
2. 新增拷贝 Set、Array
3. 拷贝集合的时候最大限度的返回传入的对象类，如传入 LinkedList 则返回 LinkedList

## 1.2.0
1. 更新 EasyMapper 版本到 1.0.4
2. CglibCopier 新增支持 Converter 的方法
3. MapperCopierSupport 移动到 MapperCopier.Builder
4. Bean 与 Map 转换的功能移动到 MapCopiers
5. 新增 CopierException，代替之前的 RuntimeException
6. 完善 ReadMe 与单元测试

## 1.1.1
1. 完善注释和单元测试

## 1.1.0 2017-03-16
1. 去除 Guava 依赖
2. 增加 Bean 与 Map 互相转换的工具
3. 发布到 Maven 中央仓库

## 1.0.3 2016-10-30
1. CopierAdapter 去掉不实用的 `reverse()` 方法
2. Copier 接口新增转换 List 的方法 `map()`
3. 新增 EasyMapper 新特性，抽象到 MapperCopierSupport 类

## 1.0.2 2016-10-28
1. 修复 EasyMapper 不能逆向拷贝的bug
2. 更改 Cglib 包为可选，需要使用 BeanCopier 时可自行添加依赖
3. 更新字节码依赖为最新版本

## 1.0.1 2016-10-19
1. 增加 EasyMapper 的几个常用特性

## 1.0.0 2016-10-17
1. 将 Copier 抽象出来，实现基于 Cglib 和 EasyMapper 的拷贝工具
2. 添加 CopierAdapter，代替直接实现 Copier 接口