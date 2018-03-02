package com.github.trang.copiers.test.framework;

import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

/**
 * @author trang
 */
public class OrikaTest {

    @Test
    public void aaa() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(SimpleSource.class, SimpleTarget.class)
                .exclude("time")
                .exclude("id")
                .constructorB()
                .byDefault()
                .register();
        SimpleSource source = new SimpleSource(1, System.currentTimeMillis());
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SimpleTarget target = mapper.map(source, SimpleTarget.class);
        System.out.println(target);
    }

}