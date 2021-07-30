package com.rysiw.demo.controller;

import com.rysiw.demo.common.utils.ResultUtil;
import com.rysiw.demo.common.vo.ResultVO;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVO<Object> login(@RequestBody UserEntity user){
        if(StringUtils.isEmpty(user.getUsername())){
            return ResultUtil.getErrorVO("请输入正确的用户名");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return ResultUtil.getErrorVO("请输入正确的密码");
        }
        UserEntity userEntity = userService.getUserByUsername(user.getUsername());
        if(Objects.isNull(userEntity) || !userEntity.getPassword().equals(user.getPassword())){
            return ResultUtil.getErrorVO("登录失败");
        }
        //TODO 登录成功使用jwt返回token

        return ResultUtil.getSuccessVO();
    }
}
