package com.github.trang.copiers.test.framework;

import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.junit.Test;

/**
 * @author trang
 */
public class CglibTest {

    @Test
    public void test1() {
        SimpleSource source = new SimpleSource(1, System.currentTimeMillis());
        source.setSame("same");
        SimpleTarget target = new SimpleTarget("A");
        BeanCopier copier = BeanCopier.create(SimpleSource.class, SimpleTarget.class, false);
        copier.copy(source, target, null);
        System.out.println(target);

        BeanCopier copier2 = BeanCopier.create(SimpleSource.class, SimpleTarget.class, true);
        copier2.copy(source, target, new Converter() {
            @Override
            public Object convert(Object value, Class target, Object context) {
                System.out.println("value:" + value + ", target:" + target + ", context:" + context);
                if (String.class.equals(target)) {
                    return value.toString();
                }
                return value;
            }
        });
        System.out.println(target);
    }

}