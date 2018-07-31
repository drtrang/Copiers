package com.github.trang.copiers.test.util;

import static com.google.common.collect.Lists.newArrayList;

import com.github.trang.copiers.test.bean.Student;
import com.github.trang.copiers.test.bean.StudentEntity;
import com.github.trang.copiers.test.bean.Teacher;
import com.github.trang.copiers.test.bean.TeacherEntity;
import com.google.common.collect.ImmutableMap;

/**
 * Mock 工具类
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
public class MockUtils {

    public static Teacher newTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("teacher");
        teacher.setAge(36);
        teacher.setSex((byte) 1);
        teacher.setHeight(188.0);
        teacher.setWeight(120F);
        teacher.setHandsome(true);
        teacher.setHouse(ImmutableMap.of("home", "bj"));
        teacher.setLover(new Teacher("lover", 36));
        teacher.setStudents(newArrayList(newMaleStudent(), newFemaleStudent()));
        return teacher;
    }

    public static Student newMaleStudent() {
        Student student = new Student();
        student.setName("male");
        student.setAge(10);
        student.setSex((byte) 1);
        student.setHeight(100.0);
        student.setWeight(50F);
        student.setHandsome(true);
        student.setHobbits(newArrayList("吃", "喝", "玩"));
        return student;
    }

    public static Student newFemaleStudent() {
        Student student = new Student();
        student.setName("female");
        student.setAge(10);
        student.setSex((byte) 2);
        student.setHeight(100.0);
        student.setWeight(50F);
        student.setHandsome(false);
        student.setHobbits(newArrayList("吃", "喝", "玩"));
        return student;
    }

    public static TeacherEntity newTeacherEntity() {
        TeacherEntity teacher = new TeacherEntity();
        teacher.setUsername("teacher");
        teacher.setAge(36);
        teacher.setSex((byte) 1);
        teacher.setHeight(188.0);
        teacher.setWeight(120L);
        teacher.setHandsome(true);
        teacher.setHobbits(newArrayList("说", "学"));
        teacher.setHouse(ImmutableMap.of("home", "bj"));
        teacher.setLover(new TeacherEntity("lover", 36));
        teacher.setStudents(newArrayList(newMaleStudentEntity(), newFemaleStudentEntity()));
        return teacher;
    }

    public static StudentEntity newMaleStudentEntity() {
        StudentEntity student = new StudentEntity();
        student.setName("male");
        student.setAge(10);
        student.setSex((byte) 1);
        student.setHeight(100.0);
        student.setWeight(50F);
        student.setHandsome(true);
        student.setHobbits(newArrayList("吃", "喝", "玩"));
        return student;
    }

    public static StudentEntity newFemaleStudentEntity() {
        StudentEntity student = new StudentEntity();
        student.setName("female");
        student.setAge(10);
        student.setSex((byte) 2);
        student.setHeight(100.0);
        student.setWeight(50F);
        student.setHandsome(false);
        student.setHobbits(newArrayList("吃", "喝", "玩"));
        return student;
    }

}