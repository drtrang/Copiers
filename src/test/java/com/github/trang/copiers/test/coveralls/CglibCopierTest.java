package com.github.trang.copiers.test.coveralls;

import static com.github.trang.copiers.test.util.MockUtils.newTeacher;
import static com.github.trang.copiers.test.util.MockUtils.newTeacherEntity;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.junit.Test;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.exception.CopierException;
import com.github.trang.copiers.test.bean.Teacher;
import com.github.trang.copiers.test.bean.TeacherEntity;
import com.github.trang.copiers.test.plugin.TestConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * CglibCopierTest
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
@Slf4j
@SuppressWarnings("Duplicates")
public class CglibCopierTest {

    private static Copier<Teacher, TeacherEntity> copier = Copiers.createCglib(Teacher.class, TeacherEntity.class);
    private static Copier<Teacher, TeacherEntity> converterCopier = Copiers.createCglib(Teacher.class, TeacherEntity.class, new TestConverter());
    private static Copier<TeacherEntity, Teacher> reverseCopier = Copiers.createCglib(TeacherEntity.class, Teacher.class);

    /**
     * cglib 仅支持相同名称、相同类型的拷贝，不支持自定义
     */
    @Test
    public void copyTest() {
        // 名称不同，忽略转换
        Teacher teacher = newTeacher();
        TeacherEntity target = copier.copy(teacher);
        // 名称不同
        assertThat(target.getUsername()).isNull();
        assertThat(target.getAge()).isEqualTo(teacher.getAge());
        assertThat(target.getSex()).isEqualTo(teacher.getSex());
        assertThat(target.getHeight()).isEqualTo(teacher.getHeight());
        // 类型不同
        assertThat(target.getWeight()).isNull();
        assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
        assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
        // 类型不同
        assertThat(target.getLover()).isNull();
        // 这里解释一下为什么相等，因为两个字段名称相同且类型都为 list
        assertThat(target.getStudents()).isEqualTo(teacher.getStudents());
    }

    @Test
    public void reverseCopyTest() {
        TeacherEntity teacher = newTeacherEntity();
        Teacher target = reverseCopier.copy(teacher);
        // 名称不同
        assertThat(target.getName()).isNull();
        assertThat(target.getAge()).isEqualTo(teacher.getAge());
        assertThat(target.getSex()).isEqualTo(teacher.getSex());
        assertThat(target.getHeight()).isEqualTo(teacher.getHeight());
        // 类型不同
        assertThat(target.getWeight()).isNull();
        assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
        assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
        // 类型不同
        assertThat(target.getLover()).isNull();
        // 这里解释一下为什么相等，因为两个字段名称相同且类型都为 list
        assertThat(target.getStudents()).isEqualTo(teacher.getStudents());
    }

    @Test(expected = CopierException.class)
    public void converterCopyTest() {
        Teacher teacher = newTeacher();
        // converter 要考虑所有的场景，这里 lover 字段会抛出转换异常
        converterCopier.copy(teacher);
    }

    @Test
    public void copyArrayTest() {
        Teacher teacher = newTeacher();
        Teacher[] teachers = {teacher, teacher};
        TeacherEntity[] targets = copier.copyArray(teachers, TeacherEntity[]::new);
        for (TeacherEntity target : targets) {
            // 名称不同
            assertThat(target.getUsername()).isNull();
            assertThat(target.getAge()).isEqualTo(teacher.getAge());
            assertThat(target.getSex()).isEqualTo(teacher.getSex());
            assertThat(target.getHeight()).isEqualTo(teacher.getHeight());
            // 类型不同
            assertThat(target.getWeight()).isNull();
            assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
            assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
            // 类型不同
            assertThat(target.getLover()).isNull();
            // 这里解释一下为什么相等，因为两个字段名称相同且类型都为 list
            assertThat(target.getStudents()).isEqualTo(teacher.getStudents());
        }
        // 简单测试一下
        copier.copyArray(teachers);
    }

    @Test
    public void copyListTest() {
        Teacher teacher = newTeacher();
        ArrayList<Teacher> teachers = newArrayList(teacher, teacher);
        List<TeacherEntity> targets = copier.copyList(teachers);
        for (TeacherEntity target : targets) {
            // 名称不同
            assertThat(target.getUsername()).isNull();
            assertThat(target.getAge()).isEqualTo(teacher.getAge());
            assertThat(target.getSex()).isEqualTo(teacher.getSex());
            assertThat(target.getHeight()).isEqualTo(teacher.getHeight());
            // 类型不同
            assertThat(target.getWeight()).isNull();
            assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
            assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
            // 类型不同
            assertThat(target.getLover()).isNull();
            // 这里解释一下为什么相等，因为两个字段名称相同且类型都为 list
            assertThat(target.getStudents()).isEqualTo(teacher.getStudents());
        }
        // 简单测试一下
        copier.copyList(teachers, LinkedList::new);
        copier.copyList(teachers, CopyOnWriteArrayList::new);
    }

    @Test
    public void copySetTest() {
        Teacher teacher = newTeacher();
        Set<Teacher> teachers = newHashSet(teacher, teacher);
        Set<TeacherEntity> targets = copier.copySet(teachers);
        for (TeacherEntity target : targets) {
            // 名称不同
            assertThat(target.getUsername()).isNull();
            assertThat(target.getAge()).isEqualTo(teacher.getAge());
            assertThat(target.getSex()).isEqualTo(teacher.getSex());
            assertThat(target.getHeight()).isEqualTo(teacher.getHeight());
            // 类型不同
            assertThat(target.getWeight()).isNull();
            assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
            assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
            // 类型不同
            assertThat(target.getLover()).isNull();
            // 这里解释一下为什么相等，因为两个字段名称相同且类型都为 list
            assertThat(target.getStudents()).isEqualTo(teacher.getStudents());
        }
        // 简单测试一下
        copier.copySet(teachers, LinkedHashSet::new);
        copier.copySet(teachers, CopyOnWriteArraySet::new);
        copier.copySet(teachers, ConcurrentSkipListSet::new);
    }

}