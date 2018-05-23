package com.github.trang.copiers.test.framework;

import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;
import com.google.common.base.Joiner;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

/**
 * @author trang
 */
public class OrikaTest {

    @Test
    public void aaa() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.getConverterFactory().registerConverter("list2String", new CustomConverter<List, String>() {
            @Override
            public String convert(List source, Type<? extends String> destinationType, MappingContext mappingContext) {
                return Joiner.on(",").join(source);
            }
        });
        mapperFactory.classMap(SimpleSource.class, SimpleTarget.class)
//                .exclude("time")
                .exclude("id")
                .fieldMap("statusList", "statuses").converter("list2String").add()
                .constructorB("time")
                .byDefault()
                .register();
        SimpleSource source = new SimpleSource(1, System.currentTimeMillis());
        source.setStatusList(newArrayList(1,2,3));
        source.setMap(new HashMap<String, Object>());
        BoundMapperFacade<SimpleSource, SimpleTarget> mapper = mapperFactory.getMapperFacade(SimpleSource.class, SimpleTarget.class);
        SimpleTarget target = mapper.map(source);
        System.out.println(target);
    }

}