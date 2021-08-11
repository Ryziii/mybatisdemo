package com.rysiw.demo.service.impl;

import com.rysiw.demo.common.utils.JwtTokenUtils;
import com.rysiw.demo.dao.UserMapper;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.AuthorizationService;
import com.rysiw.demo.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * @author Rysiw
 * @date 2021/8/1 18:33
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public String createToken(UserEntity reqUser, HttpHeaders httpHeaders) throws Exception {
        boolean isUserTokenExists = redisService.userTokenExists(reqUser);
        if(isUserTokenExists){
            throw new Exception("用户token存活，无需重新注册");
        }
        UserEntity user = userMapper.getUserByUsername(reqUser.getUsername());
        if(user == null){
            throw new Exception("未找到用户");
        }
        if(!StringUtils.equals(user.getPassword(), reqUser.getPassword())){
            throw new Exception("密码错误");
        }
        String jwt = JwtTokenUtils.generatorToken(user.getUsername(), false);
        redisService.addToken(reqUser.getUsername(),jwt);
        return jwt;
    }
}
