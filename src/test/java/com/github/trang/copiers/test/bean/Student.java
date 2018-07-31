package com.github.trang.copiers.test.bean;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.MoreObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 学生信息
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
@Data
@Builder
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private byte sex;
    private double height;
    private float weight;
    private boolean handsome;
    private List<String> hobbits;

    public Student() { }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Student").omitNullValues()
                .add("name", name)
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .add("handsome", handsome)
                .add("hobbits", hobbits)
                .toString();
    }

}