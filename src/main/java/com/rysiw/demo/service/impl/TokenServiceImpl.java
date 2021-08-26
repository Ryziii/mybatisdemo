package com.rysiw.demo.service.impl;

import com.rysiw.demo.common.constant.SecurityConstants;
import com.rysiw.demo.common.utils.JwtTokenUtils;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.exception.MyException;
import com.rysiw.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Rysiw
 * @date 2021/8/1 18:41
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void addToken(String username, String jwt) throws Exception {
        try {
            redisTemplate.opsForValue().set(username, jwt);
            redisTemplate.expireAt(username, new Date(new Date().getTime() + SecurityConstants.EXPIRATION_REMEMBER * 1000));
        }catch (Exception e){
            throw new Exception("存入redis错误");
        }
    }

    @Override
    public boolean isTokenValid(UserEntity user, HttpHeaders httpHeaders) {
        //TODO jwt就有username信息
        String username = user.getUsername();
        String token = httpHeaders.getFirst(SecurityConstants.TOKEN_HEADER);
        return doIsTokenValid(username,token);
    }

    @Override
    public boolean doIsTokenValid(String username, String token){

        if(!JwtTokenUtils.isTokenValid(token))
            throw new MyException(500,"token: jwt 解析异常");
        String tokenInRedis = (String) redisTemplate.opsForValue().get(username);
        if(!Objects.equals(token,tokenInRedis)){
            throw new MyException(500,"token与用户名不匹配");
        }
        return true;
    }

    @Override
    public boolean userTokenExists(UserEntity reqUser) {
        return redisTemplate.opsForValue().get(reqUser.getUsername()) != null;
    }
}
