package com.rysiw.demo.controller;


import com.rysiw.demo.common.ResultDTO;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResultDTO getUsers(
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "2", required = false) int pageSize){

        Map<String, Object> map = userService.getAll(pageNum, pageSize);
        if(map == null){
            return ResultDTO.builder().code(200).msg("user为空").data(map).build();
        }
        else{
            return ResultDTO.builder().code(200).msg("成功").data(map).build();
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResultDTO getUserById(@PathVariable Long id){
        UserEntity user = userService.getUserById(id);
        ResultDTO result = user == null ?
                ResultDTO.builder().code(200).msg("无此id用户").data(null).build() :
                ResultDTO.builder().code(200).msg("成功").data(user).build();
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Long insert( UserEntity user){
        userService.insertUser(user);
        return user.getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUser(@RequestBody UserEntity user){
        userService.update(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }



}
