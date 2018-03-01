package com.github.trang.copiers.test.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author trang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTarget {

    private String id;

    @Override
    public String toString() {
        return "\"" + id + "\"";
    }

}