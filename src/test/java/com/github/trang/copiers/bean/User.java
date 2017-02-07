package com.github.trang.copiers.bean;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户信息PO
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer age;
    private Byte sex;
    private Double height;
    private Long weight;
    private String name;
    private List<String> hobbits;
    private Boolean handsome;
    private Map<String, Object> house;
    private User wife;

    public static User of(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .add("name", name)
                .add("hobbits", hobbits)
                .add("handsome", handsome)
                .add("house", house)
                .add("wife", wife)
                .toString();
    }

    public Map<String, Object> getHouse() {
        return house;
    }

    public void setHouse(Map<String, Object> house) {
        this.house = house;
    }

    public User getWife() {
        return wife;
    }

    public void setWife(User wife) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}