package com.rysiw.demo.service.impl;

import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.common.utils.JwtTokenUtils;
import com.rysiw.demo.dao.UserMapper;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.exception.DefineException;
import com.rysiw.demo.service.AuthorizationService;
import com.rysiw.demo.service.TokenService;
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
    private TokenService tokenService;

    @Override
    public String createToken(UserEntity reqUser, HttpHeaders httpHeaders) throws Exception {
        boolean isUserTokenExists = tokenService.userTokenExists(reqUser);
        if(isUserTokenExists){
            throw new DefineException(RespCode.TOKEN_ALIVE);
        }
        UserEntity user = userMapper.getUserByUsername(reqUser.getUsername());
        if(user == null){
            throw new DefineException(RespCode.CANT_FIND_USER);
        }
        if(!StringUtils.equals(user.getPassword(), reqUser.getPassword())){
            throw new DefineException(RespCode.PASSWORD_WRONG);
        }
        String jwt = JwtTokenUtils.generatorToken(user.getUsername(), false);
        tokenService.addToken(reqUser.getUsername(),jwt);
        return jwt;
    }
}
