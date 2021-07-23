package com.rysiw.demo.controller;


import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Map<String, Object> getUsers(
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "2", required = false) int pageSize){

        return userService.getAll(pageNum, pageSize);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserEntity getUserById(Long id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Long insert(UserEntity user){
        userService.insertUser(user);
        return user.getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUser(@RequestBody UserEntity user){
        userService.update(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(Long id){
        userService.deleteById(id);
    }



}
