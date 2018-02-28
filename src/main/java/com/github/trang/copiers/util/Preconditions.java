package com.github.trang.copiers.util;

import java.util.Map;

/**
 * @author trang
 */
public final class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static void checkNotNull(Object o, String msg) {
        if (o == null) {
            throw new NullPointerException(msg);
        }
    }

    public static void checkNotNull(Map map, String msg) {
        if (map == null) {
            throw new NullPointerException(msg);
        } else if (map.isEmpty()) {
            throw new IllegalArgumentException(msg);
        }
    }

}