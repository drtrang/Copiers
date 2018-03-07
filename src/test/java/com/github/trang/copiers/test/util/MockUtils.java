package com.github.trang.copiers.test.util;

import com.github.trang.copiers.test.bean.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author trang
 */
public class MockUtils {

    public static User newUser() {
        User user = new User();
        user.setAge(25);
        user.setSex((byte) 0);
        user.setHeight(1.73);
        user.setWeight(null);
        user.setName("trang");
        user.setHobbits(ImmutableList.of("coding"));
        user.setHandsome(true);
        user.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        User wife = new User();
        wife.setName("meng");
        wife.setSex((byte) 1);
        wife.setAge(24);
        wife.setHeight(1.65);
        wife.setWeight(55);
        wife.setHouse(ImmutableMap.<String, Object>of("home", "home"));

        user.setWife(wife);
        user.setSub(newArrayList(wife));
        return user;
    }

}