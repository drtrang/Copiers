package com.github.trang.copiers.test.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户信息 Entity
 * 与 #{@link User} 区别：
 *   1. 增加 other 属性
 *   2. weight 属性由 Integer 类型改为 Long
 */
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private Integer age;
    private Byte sex;
    private Double height;
    private Long weight;
    private List<String> hobbits;
    private Boolean handsome;
    private Map<String, Object> house;
    private UserEntity wife;
    private String other;
    private List<UserEntity> sub;

    public static UserEntity of(String name, Integer age) {
        UserEntity entity = new UserEntity();
        entity.setUsername(name);
        entity.setAge(age);
        return entity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("username", username)
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .add("hobbits", hobbits)
                .add("handsome", handsome)
                .add("house", house)
                .add("wife", wife)
                .add("other", other)
                .add("sub", sub)
                .toString();
    }

}