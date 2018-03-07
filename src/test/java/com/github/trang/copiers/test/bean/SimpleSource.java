package com.github.trang.copiers.test.bean;

import lombok.*;

import java.util.List;

/**
 * @author trang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SimpleSource {

    private Integer id;
    private Long time;
    private List<Integer> statusList;

    public SimpleSource(Integer id) {
        this.id = id;
    }

    public SimpleSource(Integer id, Long time) {
        this.id = id;
        this.time = time;
    }

}