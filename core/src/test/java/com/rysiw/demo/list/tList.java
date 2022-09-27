package com.rysiw.demo.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rysiw
 * @date 2022/7/25 15:48
 */
public class tList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        t(list);
        System.out.println(list);
    }

    public static void t(List<String> l){
        l.add("a");
        l.add("b");
    }
}
