package com.github.trang.copiers.test.bean;

import lombok.*;

import java.util.Date;

/**
 * @author trang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTarget {

    private String id;
    private Date time;

    public SimpleTarget(String id) {
        this.id = id;
    }
}