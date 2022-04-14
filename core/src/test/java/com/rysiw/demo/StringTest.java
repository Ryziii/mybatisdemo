package com.rysiw.demo;


import cn.hutool.core.date.DateTime;
import com.rysiw.demo.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StringTest {

    @Test
    public void test(){
        UserEntity u1 = new UserEntity();
        u1.setUsername("1");
        u1.setEmail("1");
        UserEntity u2 = new UserEntity();
        u2.setUsername("2");
        u2.setEmail("1");
        UserEntity u3 = new UserEntity();
        u3.setUsername("3");
        u3.setEmail("3");

        List<UserEntity> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        list.forEach(o -> o.setEmail("456"));
        System.out.println(list);
    }
}
