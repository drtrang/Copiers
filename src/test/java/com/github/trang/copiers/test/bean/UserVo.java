package com.github.trang.copiers.test.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户信息 VO
 * 与 #{@link User} 区别：去除wife信息
 */
@Getter
@Setter
@NoArgsConstructor
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer age;
    private Byte sex;
    private Double height;
    private Integer weight;
    private String name;
    private List<String> hobbits;
    private Boolean handsome;
    private Map<String, Object> house;

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
                .toString();
    }

}