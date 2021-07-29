package com.rysiw.demo.service;

import com.rysiw.demo.common.dto.ResultDTO;
import com.rysiw.demo.entity.UserEntity;

import java.util.Map;

public interface UserService {

    Map<String, Object> getAll(int pageNum, int pageSize);

    UserEntity getUserById(Long id);

    ResultDTO insertUser(UserEntity userEntity);

    Boolean update(UserEntity userEntity);

    ResultDTO deleteById(Long id);
}
