package com.rysiw.demo.service;

import com.rysiw.demo.entity.UserEntity;

import java.util.Map;

public interface UserService {

    Map<String, Object> getAll(int pageNum, int pageSize);

    UserEntity getUserById(Long id);

    void insertUser(UserEntity userEntity);

    void update(UserEntity userEntity);

    void deleteById(Long id);
}
