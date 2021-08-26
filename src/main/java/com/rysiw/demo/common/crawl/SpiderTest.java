package com.rysiw.demo.common.crawl;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Iterator;

/**
 * @author Rysiw
 * @date 2021/8/6 04:06
 */
public class SpiderTest {

    private final static OkHttpClient okHttpClient = new OkHttpClient();
    private String s = "123";

    public static void main(String[] args) throws Exception{
        String URL = "http://120.48.9.152:8848/nacos/";
        Request request = new Request
                .Builder()
                .url(URL)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println("########Response Code:########");
        System.out.println(response.code());
        Iterator it = response.headers().iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }

    public void test(){
    }
}
