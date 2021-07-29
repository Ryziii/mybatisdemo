package com.rysiw.demo.controller;


import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.common.utils.ResultUtil;
import com.rysiw.demo.common.vo.ResultVO;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResultVO<Object> getUsers(
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "2", required = false) int pageSize){

        Map<String, Object> map = userService.getAll(pageNum, pageSize);
        if(map == null){
            return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg("user为空").data(map).build();
        }
        else{
            return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg("成功").data(map).build();
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResultVO<Object> getUserById(@PathVariable Long id){
        UserEntity user = userService.getUserById(id);
        ResultVO result = user == null ?
                ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg("无此id用户").data(null).build() :
                ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg("成功").data(user).build();
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultVO<Object> insert(UserEntity user){
        return ResultUtil.resultDTO2resultVO(userService.insertUser(user));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVO<Object> updateUser(@RequestBody UserEntity user){

        if(userService.update(user)){
            return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg("更新用户成功").build();
        }else{
            return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg("更新用户失败").build();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResultVO deleteById(@PathVariable Long id){
        return ResultUtil.resultDTO2resultVO(userService.deleteById(id));
    }
}
