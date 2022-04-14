package com.rysiw.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.rysiw.demo.common.utils.ResultUtil;
import com.rysiw.demo.common.vo.ResultVO;
import com.rysiw.demo.dao.UserTestMapper;
import com.rysiw.demo.exception.DefineException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        throw new DefineException("2","22");
    }

    @GetMapping("/queryRequest")
    public ResultVO<Object> queryRequest(@RequestParam(required = true) String userId){

        System.out.println(DateTime.now());
        return ResultVO.builder().build();
    }

}
