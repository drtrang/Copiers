package com.github.trang.copiers.test.bean;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户信息 Form
 * 与 #{@link User} 区别：只有部分信息
 */
@Getter
@Setter
@NoArgsConstructor
public class UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer age;
    private Byte sex;
    private Double height;
    private Integer weight;
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

}