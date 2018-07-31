package com.github.trang.copiers.test.bean;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师信息 VO
 * 与 #{@link Teacher} 区别：
 *   1. weight: Float -> Integer
 *   2. 删除了 n 个字段
 * <p>
 * Write the code. Change the world.
 *
 * @author trang
 * @date 2018/6/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
    private Byte sex;
    private Double height;
    private Integer weight;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Teacher").omitNullValues()
                .add("name", name)
                .add("age", age)
                .add("sex", sex)
                .add("height", height)
                .add("weight", weight)
                .toString();
    }

}