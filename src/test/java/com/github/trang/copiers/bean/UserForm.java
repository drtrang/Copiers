package com.github.trang.copiers.bean;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * 用户信息Form，限制前端发送的数据
 * 只有部分信息
 */
public class UserForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer age;
    private Byte sex;
    private Double height;
    private Long weight;
    private String name;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .add("name", name)
                .toString();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}