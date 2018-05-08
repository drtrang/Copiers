package com.github.trang.copiers.test.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author trang
 */
@Getter
@Setter
@ToString
public class SimpleTarget {

    private String id;
    private Date time;
    private String statuses;
    private String types;
    private String name;
    private String same;

//    public SimpleTarget() {
//    }

    public SimpleTarget(String id) {
        this.id = id;
    }

    public SimpleTarget(Date time) {
        this.time = time;
    }

    public SimpleTarget(String id, Date time) {
        this.id = id;
        this.time = time;
    }

}