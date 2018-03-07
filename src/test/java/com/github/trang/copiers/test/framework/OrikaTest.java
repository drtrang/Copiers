package com.github.trang.copiers.test.framework;

import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;
import com.google.common.base.Joiner;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author trang
 */
public class OrikaTest {

    @Test
    public void aaa() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter("list2String", new CustomConverter<List, String>() {
            @Override
            public String convert(List source, Type<? extends String> destinationType, MappingContext mappingContext) {
                return Joiner.on(",").join(source);
            }
        });
        mapperFactory.classMap(SimpleSource.class, SimpleTarget.class)
//                .exclude("time")
//                .exclude("id")
                .fieldMap("statusList", "statuses").converter("list2String").add()
                .constructorB()
                .byDefault()
                .register();
        SimpleSource source = new SimpleSource(1, System.currentTimeMillis());
        source.setStatusList(newArrayList(1,2,3));
        MapperFacade mapper = mapperFactory.getMapperFacade();
        SimpleTarget target = mapper.map(source, SimpleTarget.class);
        System.out.println(target);
    }

}