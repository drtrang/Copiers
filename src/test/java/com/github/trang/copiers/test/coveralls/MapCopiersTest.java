package com.github.trang.copiers.test.coveralls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.junit.Before;
import org.junit.Test;

import com.github.trang.copiers.MapCopiers;
import com.github.trang.copiers.test.bean.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class MapCopiersTest {

    private User trang = new User();
    private User meng = new User();

    @Before
    public void before() {
        trang.setName("trang");
        trang.setSex((byte) 0);
        trang.setAge(23);
        trang.setHeight(1.73);
        trang.setWeight(68);
        trang.setHobbits(ImmutableList.of("coding"));
        trang.setHouse(ImmutableMap.of("home", "home"));

        meng.setName("meng");
        meng.setSex((byte) 1);
        meng.setAge(22);
        meng.setWeight(60);
        meng.setHandsome(true);
        meng.setHobbits(ImmutableList.of("beautiful"));
        meng.setHouse(ImmutableMap.of("home", "home"));

        trang.setWife(meng);
    }

    @Test
    public void beanToMap() {
        Map<String, Object> map = MapCopiers.<User>createBeanToMap().copy(trang);
        System.out.println(map);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("value", "value");
        map2.put("name", "name");
        MapCopiers.<User>createBeanToMap().copy(trang, map2);
        System.out.println(map2);
    }

    @Test
    public void beanToMapList() {
        List<User> users = Lists.newArrayList(trang, meng);
        List<Map<String, Object>> list = MapCopiers.<User>createBeanToMap().copyList(users);
        assertTrue(list instanceof ArrayList);
        assertEquals(2, list.size());
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }

        List<Map<String, Object>> linkedList = MapCopiers.<User>createBeanToMap().copyList(users, LinkedList::new);
        assertTrue(linkedList instanceof LinkedList);
        assertEquals(2, linkedList.size());
        for (Map<String, Object> map : linkedList) {
            System.out.println(map);
        }
    }

    @Test
    public void beanToMapSet() {
        Set<User> users = Sets.newHashSet(trang, meng);
        Set<Map<String, Object>> hashSet = MapCopiers.<User>createBeanToMap().copySet(users);
        assertTrue(hashSet instanceof HashSet);
        assertEquals(2, hashSet.size());
        for (Map<String, Object> map : hashSet) {
            System.out.println(map);
        }

        Set<Map<String, Object>> cowSet = MapCopiers.<User>createBeanToMap().copySet(users, CopyOnWriteArraySet::new);
        assertTrue(cowSet instanceof CopyOnWriteArraySet);
        assertEquals(2, cowSet.size());
        for (Map<String, Object> map : cowSet) {
            System.out.println(map);
        }
    }

    @Test
    public void beanToMapArray() {
        List<User> users = Lists.newArrayList(trang, meng);
        List<Map<String, Object>> list = MapCopiers.<User>createBeanToMap().copyList(users);
        assertTrue(list instanceof ArrayList);
        assertEquals(2, list.size());
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }

        List<Map<String, Object>> linkedList = MapCopiers.<User>createBeanToMap().copyList(users, LinkedList::new);
        assertTrue(linkedList instanceof LinkedList);
        assertEquals(2, linkedList.size());
        for (Map<String, Object> map : linkedList) {
            System.out.println(map);
        }
    }

    @Test
    public void mapToBean() {
        Map<String, Object> map = MapCopiers.<User>createBeanToMap().copy(trang);
        map.remove("handsome");
        System.out.println(map);

        User user = MapCopiers.createMapToBean(User.class).copy(map);
        System.out.println(user);

        User u2 = new User();
        u2.setName("name");
        u2.setHandsome(true);
        MapCopiers.createMapToBean(User.class).copy(map, u2);
        System.out.println(u2);

    }

    @Test
    public void mapToBeans() {
        List<User> users = Lists.newArrayList(trang, meng);
        System.out.println(users);
        List<Map<String, Object>> map = MapCopiers.<User>createBeanToMap().copyList(users);
        System.out.println(map);
        List<User> list = MapCopiers.createMapToBean(User.class).copyList(map);
        System.out.println(list);
    }

}