package com.github.trang.copiers.test.bean;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author trang
 */
@Getter
@Setter
@ToString
public class SimpleSource {

    private Integer id;
    private Long time;
    private List<Integer> statusList;
    private Map<String, Object> map;

//    public SimpleSource() {
//    }

    public SimpleSource(Integer id) {
        this.id = id;
    }

    public SimpleSource(Integer id, Long time) {
        this.id = id;
        this.time = time;
    }

}