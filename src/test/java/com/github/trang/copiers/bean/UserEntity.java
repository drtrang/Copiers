package com.github.trang.copiers.bean;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户信息Entity，做其他操作
 * 添加other信息
 */
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer age;
    private Byte sex;
    private Double height;
    private Long weight;
    private String username;
    private List<String> hobbits;
    private Boolean handsome;
    private Map<String, Object> house;
    private UserEntity wife;
    private String other;

    public static UserEntity of() {
        return new UserEntity();
    }

    public static UserEntity of(String name, Integer age) {
        UserEntity entity = new UserEntity();
        entity.setUsername(name);
        entity.setAge(age);
        return entity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .add("username", username)
                .add("hobbits", hobbits)
                .add("handsome", handsome)
                .add("house", house)
                .add("wife", wife)
                .add("other", other)
                .toString();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Map<String, Object> getHouse() {
        return house;
    }

    public void setHouse(Map<String, Object> house) {
        this.house = house;
    }

    public UserEntity getWife() {
        return wife;
    }

    public void setWife(UserEntity wife) {
        this.wife = wife;
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

    public List<String> getHobbits() {
        return hobbits;
    }

    public void setHobbits(List<String> hobbits) {
        this.hobbits = hobbits;
    }

    public Boolean getHandsome() {
        return handsome;
    }

    public void setHandsome(Boolean handsome) {
        this.handsome = handsome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}