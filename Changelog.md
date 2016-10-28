# Changelog

---

##1.0.0
1. 将 Copier 抽象出来，实现基于 Cglib 和 EasyMapper 的拷贝工具
2. 添加 CopierAdapter，代替直接实现 Copier 接口

##1.0.1
1. 增加 EasyMapper 的几个常用特性

##1.0.2
1. 修复 EasyMapper 不能逆向拷贝的bug
2. 更改 Cglib 包为可选，需要使用 BeanCopier 时可自行添加依赖
3. 更新字节码依赖为最新版本