package com.rysiw.demo.controller;

import com.rysiw.demo.exception.DefineException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * controller for test
 *
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private final static OkHttpClient okHttpClient = new OkHttpClient();

    @RequestMapping("/client")
    public String tt() throws IOException {
        String URL = "http://gateway/client/test";
        Request request = new Request
                .Builder()
                .url(URL)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println("########Response Code:########");
        System.out.println(response.body());
        return response.body().string();
    }

    @RequestMapping("/exception")
    public String exception() {
        throw new DefineException(2,"22");
    }
}
