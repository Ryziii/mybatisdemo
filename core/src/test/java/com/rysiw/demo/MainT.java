package com.rysiw.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rysiw
 * @date 2022/8/18 14:04
 */
public class MainT {

    public static void main(String[] args) {
//        List<Man> list = new ArrayList<>();
//        for(int i = 0; i<5; i++){
//            Man m = new Man();
//            m.setName("123");
//            list.add(m);
//        }
//        modi(list);
//        System.out.println(list.get(0).name);
//        System.out.println(list.get(1).name);
//        System.out.println(list.get(2).name);
        ss();
    }

    private static void modi(List<Man> list) {
        for( Man m: list){
            mm(m);
        }
    }

    public static void ss(){
        String a = "123";
        String b = "123";
        String c = new String("123");
        System.out.println(a==b);
        System.out.println(b==c);
    }

    public static void mm(Man mm){
        mm.setName("234");
    }

    @Data
    static class Man{
        String name;
        String sex;
    }
}
