package com.github.trang.copiers.test.bean;

import lombok.*;

/**
 * @author trang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SimpleSource {

    private Integer id;
    private Long time;

    public SimpleSource(Integer id) {
        this.id = id;
    }

}