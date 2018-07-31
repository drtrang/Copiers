package com.github.trang.copiers.test.coveralls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.github.trang.copiers.cache.MapperKey;
import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;

/**
 * MapperKeyTest
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
public class MapperKeyTest {

    @Test
    public void basicTest() {
        MapperKey key1 = new MapperKey<>("cglib", SimpleSource.class, SimpleTarget.class);
        MapperKey key2 = new MapperKey<>("cglib", SimpleSource.class, SimpleTarget.class);

        assertEquals(key1, key2);
    }

    @Test
    public void extraTest() {
        MapperKey key1 = new MapperKey<>("cglib", SimpleSource.class, SimpleTarget.class, "extra", 0);
        MapperKey key2 = new MapperKey<>("cglib", SimpleSource.class, SimpleTarget.class, "extra", 0);
        MapperKey key3 = new MapperKey<>("cglib", SimpleSource.class, SimpleTarget.class, "extra", 1);

        assertEquals(key1, key2);
        assertNotEquals(key1, key3);
        assertNotEquals(key2, key3);
    }

}