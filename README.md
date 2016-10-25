# Copiers

---
A Friendly Bean Copier Packaging.

---

**Copiers** 提供了简单的 API 来实现 Bean 拷贝的功能，目前有两种底层实现：`Cglib` & `Easy Mapper`，两种方式各有千秋，可根据实际情况使用。

**Copiers** 实现了 Guava 的 Function 接口，可直接拷贝 List。

使用方法：
```java
public class CopierContainer {
	public static final Copier<FORM, PO> EASY_COPIER = Copiers.create(FORM.class, PO.class);
    public static final Copier<FORM, PO> ORG_COPIER = Copiers.create(FORM.class, PO.class).field("orgCode", "code");
    public static final Copier<FORM, PO> ORG_EXCLUDE_COPIER = Copiers.create(FORM.class, PO.class).exclude("orgCode");
    
    public static final Copier<FORM, PO> CGLIB_COPIER = Copiers.createCglib(FORM.class, PO.class);
}


public class Test {
    PO po = CopierContainer.COPIER.copy(form);
    
    PO po = PO.of();
    CopierContainer.COPIER.copy(form, po);
    
    List<PO> list = Lists.transform(forms, ORG_COPIER);
}
```