package com.github.trang.copiers.test.coveralls;

import static com.google.common.collect.Sets.newHashSet;
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

import org.junit.Test;

import com.github.trang.copiers.MapCopiers;
import com.github.trang.copiers.test.bean.Teacher;
import com.github.trang.copiers.test.util.MockUtils;
import com.google.common.collect.Lists;

/**
 * MapCopiersTest
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
public class MapCopiersTest {

    private Teacher teacher = MockUtils.newTeacher();

    @Test
    public void beanToMap() {
        Map<String, Object> map = MapCopiers.<Teacher>createBeanToMap().copy(teacher);
        System.out.println(map);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("value", "value");
        map2.put("name", "name");
        MapCopiers.<Teacher>createBeanToMap().copy(teacher, map2);
        System.out.println(map2);
    }

    @Test
    public void beanToMapList() {
        List<Teacher> teachers = Lists.newArrayList(teacher, teacher);
        List<Map<String, Object>> list = MapCopiers.<Teacher>createBeanToMap().copyList(teachers);
        assertTrue(list instanceof ArrayList);
        assertEquals(2, list.size());
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }

        List<Map<String, Object>> linkedList = MapCopiers.<Teacher>createBeanToMap().copyList(teachers, LinkedList::new);
        assertTrue(linkedList instanceof LinkedList);
        assertEquals(2, linkedList.size());
        for (Map<String, Object> map : linkedList) {
            System.out.println(map);
        }
    }

    @Test
    public void beanToMapSet() {
        Set<Teacher> teachers = newHashSet(teacher, teacher);
        Set<Map<String, Object>> hashSet = MapCopiers.<Teacher>createBeanToMap().copySet(teachers);
        assertTrue(hashSet instanceof HashSet);
        assertEquals(1, hashSet.size());
        for (Map<String, Object> map : hashSet) {
            System.out.println(map);
        }

        Set<Map<String, Object>> cowSet = MapCopiers.<Teacher>createBeanToMap().copySet(teachers, CopyOnWriteArraySet::new);
        assertTrue(cowSet instanceof CopyOnWriteArraySet);
        assertEquals(1, cowSet.size());
        for (Map<String, Object> map : cowSet) {
            System.out.println(map);
        }
    }

    @Test
    public void beanToMapArray() {
        List<Teacher> teachers = Lists.newArrayList(teacher, teacher);
        List<Map<String, Object>> list = MapCopiers.<Teacher>createBeanToMap().copyList(teachers);
        assertTrue(list instanceof ArrayList);
        assertEquals(2, list.size());
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }

        List<Map<String, Object>> linkedList = MapCopiers.<Teacher>createBeanToMap().copyList(teachers, LinkedList::new);
        assertTrue(linkedList instanceof LinkedList);
        assertEquals(2, linkedList.size());
        for (Map<String, Object> map : linkedList) {
            System.out.println(map);
        }
    }

    @Test
    public void mapToBean() {
        Map<String, Object> map = MapCopiers.<Teacher>createBeanToMap().copy(teacher);
        map.remove("handsome");
        System.out.println(map);

        Teacher teacher = MapCopiers.createMapToBean(Teacher.class).copy(map);
        System.out.println(teacher);

        Teacher u2 = new Teacher();
        u2.setName("name");
        u2.setHandsome(true);
        MapCopiers.createMapToBean(Teacher.class).copy(map, u2);
        System.out.println(u2);

    }

    @Test
    public void mapToBeans() {
        List<Teacher> teachers = Lists.newArrayList(teacher, teacher);
        System.out.println(teachers);
        List<Map<String, Object>> map = MapCopiers.<Teacher>createBeanToMap().copyList(teachers);
        System.out.println(map);
        List<Teacher> list = MapCopiers.createMapToBean(Teacher.class).copyList(map);
        System.out.println(list);
    }

}