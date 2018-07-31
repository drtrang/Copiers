package com.github.trang.copiers.test.coveralls;

import static com.github.trang.copiers.test.util.MockUtils.newTeacher;
import static com.github.trang.copiers.test.util.MockUtils.newTeacherEntity;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.base.Copier;
import com.github.trang.copiers.orika.OrikaCopier;
import com.github.trang.copiers.test.bean.SimpleSource;
import com.github.trang.copiers.test.bean.SimpleTarget;
import com.github.trang.copiers.test.bean.Student;
import com.github.trang.copiers.test.bean.StudentEntity;
import com.github.trang.copiers.test.bean.Teacher;
import com.github.trang.copiers.test.bean.TeacherEntity;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

/**
 * OrikaCopierTest
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
@SuppressWarnings("Duplicates")
public class OrikaCopierTest {

    private static Copier<Teacher, TeacherEntity> copier = Copiers.create(Teacher.class, TeacherEntity.class);
    private static Copier<TeacherEntity, Teacher> reverseCopier = Copiers.create(TeacherEntity.class, Teacher.class);

    // orika 支持级联拷贝，当 pojo 中有级联对象时需要提前注册映射关系，否则会抛出异常
    private static Copier<Student, StudentEntity> useless1 = Copiers.create(Student.class, StudentEntity.class);
    private static Copier<StudentEntity, Student> useless2 = Copiers.create(StudentEntity.class, Student.class);

    @Test
    public void copyTest() {
        Teacher teacher = newTeacher();
        TeacherEntity target = copier.copy(teacher);
        // 名称不同，忽略转换
        assertThat(target.getUsername()).isNull();
        assertThat(target.getAge()).isEqualTo(teacher.getAge());
        assertThat(target.getSex()).isEqualTo(teacher.getSex());
        assertThat(target.getHeight()).isEqualTo(teacher.getHeight());
        // 类型不同，由默认转换器转换
        assertThat(target.getWeight()).isEqualTo(teacher.getWeight().longValue());
        assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
        assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
        // 名称不同，忽略转换
        assertThat(target.getLover().getUsername()).isNull();
        // 相同类型的级联不需要提前注册映射关系
        assertThat(target.getLover().getAge()).isEqualTo(teacher.getLover().getAge());
        // 支持级联拷贝，可以转换
        assertThat(target.getStudents().toString()).isEqualTo(teacher.getStudents().toString());
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
        assertThat(target.getWeight()).isEqualTo(teacher.getWeight().floatValue());
        assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
        assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
        // 类型不同
        assertThat(target.getLover().getName()).isNull();
        assertThat(target.getLover().getAge()).isEqualTo(teacher.getLover().getAge());
        // 这里解释一下为什么相等，因为两个字段名称相同且类型都为 list
        assertThat(target.getStudents().toString()).isEqualTo(teacher.getStudents().toString());
    }

    @Test
    @Ignore
    public void orika() {
        Teacher teacher = newTeacher();
        OrikaCopier<Teacher, TeacherEntity> copier = Copiers.createOrika(Teacher.class, TeacherEntity.class)
                .skip("age", "sex")
                .field("name", "username")
                .field("wife.sex", "wife.sex")
                .customize(new CustomMapper<Teacher, TeacherEntity>() {
                    @Override
                    public void mapAtoB(Teacher source, TeacherEntity target, MappingContext context) {
                        target.setUsername("prefix:" + source.getName());
                    }
                })
                .register();

        TeacherEntity target = copier.copy(teacher);

        System.out.println(teacher);
        System.out.println(target);

        // age 属性跳过拷贝
        assertThat(target.getAge()).isNull();
        // sex 属性跳过拷贝
        assertThat(target.getSex()).isNull();
        // weight 属性在 teacher 对象中没有设置值
        assertThat(target.getWeight()).isNull();
        assertThat(target.getHeight()).isEqualTo(teacher.getHeight());
        assertThat(target.getUsername()).isEqualTo("prefix:" + teacher.getName());
        assertThat(target.getHandsome()).isEqualTo(teacher.getHandsome());
        assertThat(target.getHouse()).isEqualTo(teacher.getHouse());
    }

    @Test
    public void list2String() {
        Copier<SimpleSource, SimpleTarget> copier = Copiers.createOrika(SimpleSource.class, SimpleTarget.class)
                .field("statusList", "statuses")
                .field("typeList", "types")
                .skip("id")
                // 自定义 constructor() 需在 skip() 之后
                .constructor("time")
                .register();
        Copier<SimpleSource, SimpleTarget> copier2 = Copiers.createOrika(SimpleSource.class, SimpleTarget.class)
                .field("statusList", "statuses")
                .field("typeList", "types")
                .skip("id")
                // 自定义 constructor() 需在 skip() 之后
                .constructor("time")
                .register();
        SimpleSource source = new SimpleSource(1, System.currentTimeMillis());
//        source.setStatusList(newArrayList(1,2,3));
        source.setTypeList(newArrayList(1.1,2.22,3.333));
        SimpleTarget target = copier.copy(source);
        System.out.println(target);
    }

}