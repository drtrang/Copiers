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
    private String statuses;

    public SimpleTarget(String id) {
        this.id = id;
    }

    public SimpleTarget(String id, Date time) {
        this.id = id;
        this.time = time;
    }

}