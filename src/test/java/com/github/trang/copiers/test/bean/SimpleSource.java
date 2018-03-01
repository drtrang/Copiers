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
public class SimpleSource {

    private Integer id;

    @Override
    public String toString() {
        return Integer.toString(id);
    }

}