package com.github.trang.copiers.test.bean;

import lombok.*;

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