package com.github.trang.copiers.test.bean;

import lombok.*;

/**
 * @author trang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleSource {

    private Integer id;

    @Override
    public String toString() {
        return Integer.toString(id);
    }

}