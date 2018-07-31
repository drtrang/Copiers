package com.github.trang.copiers.test.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 教师信息
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
@Data
@Builder
@AllArgsConstructor
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
    private Byte sex;
    private Double height;
    private Float weight;
    private Boolean handsome;
    private Map<String, Object> house;
    private Teacher lover;
    private List<Student> students;

    public Teacher() { }

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Teacher").omitNullValues()
                .add("name", name)
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .add("handsome", handsome)
                .add("house", house)
                .add("lover", lover)
                .add("students", students)
                .toString();
    }

}