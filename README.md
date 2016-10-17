# Copiers
A Friendly Bean Copier Packaging.

```java
public class CopierContainer {
	// 推荐使用此方法调用，隐藏实现细节
	public static final BeanCopier<Long, String> COPIER = BeanCopiers.copier(Long.class, String.class);
	
	// 不推荐以下方式
	public static final BeanCopier<Long, String> CGLIG_COPIER1 = new CglibBeanCopier<>(Long.class, String.class);
	public static final BeanCopier<Long, String> MAPPER_COPIER = new MapperBeanCopier<>(Long.class, String.class);
}
```