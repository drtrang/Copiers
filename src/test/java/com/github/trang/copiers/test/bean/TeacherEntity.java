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
 * 与 #{@link Teacher} 区别：
 *   1. name -> username
 *   2. 增加 hobbits
 *   3. weight: Float -> Long
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
@Data
@Builder
@AllArgsConstructor
public class TeacherEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private Integer age;
    private Byte sex;
    private Double height;
    private Long weight;
    private Boolean handsome;
    private List<String> hobbits;
    private Map<String, Object> house;
    private TeacherEntity lover;
    private List<StudentEntity> students;

    public TeacherEntity() { }

    public TeacherEntity(String name, Integer age) {
        this.username = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Teacher").omitNullValues()
                .add("username", username)
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .add("handsome", handsome)
                .add("hobbits", hobbits)
                .add("house", house)
                .add("lover", lover)
                .add("students", students)
                .toString();
    }

}