package com.rysiw.demo.service.impl;

import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.common.constant.SecurityConstants;
import com.rysiw.demo.common.utils.JwtTokenUtils;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.exception.DefineException;
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
    public void addToken(String username, String jwt) {
        try {
            redisTemplate.opsForValue().set(username, jwt);
            redisTemplate.expireAt(username, new Date(new Date().getTime() + SecurityConstants.EXPIRATION_REMEMBER * 1000));
        }catch (Exception e){
            throw new DefineException(RespCode.REDIS_ERROR);
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
            throw new DefineException(RespCode.JWT_PARSE_ERROR);
        String tokenInRedis = (String) redisTemplate.opsForValue().get(username);
        if(!Objects.equals(token,tokenInRedis)){
            throw new DefineException(RespCode.TOKEN_DONT_MATCH);
        }
        return true;
    }

    @Override
    public boolean userTokenExists(UserEntity reqUser) {
        return redisTemplate.opsForValue().get(reqUser.getUsername()) != null;
    }
}
