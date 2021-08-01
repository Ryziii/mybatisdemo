package com.rysiw.demo.controller;

import com.rysiw.demo.common.utils.ResultUtil;
import com.rysiw.demo.common.vo.ResultVO;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.AuthorizationService;
import com.rysiw.demo.service.RedisService;
import com.rysiw.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/token")
public class LoginController {

    private static final String JWT_HEADER_NAME = "Authorization";

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public ResultVO<Object> regisToken(
            @Validated @RequestBody UserEntity reqUser,
            HttpServletResponse response,
            @RequestHeader HttpHeaders httpHeaders) throws Exception {

        if(redisService.userTokenExists(reqUser)){
            if(redisService.isTokenValid(reqUser, httpHeaders)) {
                return ResultUtil.getSuccessVO("验证token成功");
            }else{
                return ResultUtil.getErrorVO("请携带与用户名对应的正确token");
            }
        }
        String jwt = authorizationService.createToken(reqUser, httpHeaders);
        response.setHeader(JWT_HEADER_NAME, jwt);
        return ResultUtil.getSuccessVO();
    }
}
