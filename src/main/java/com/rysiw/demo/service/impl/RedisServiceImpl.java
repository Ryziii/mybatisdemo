package com.rysiw.demo.service.impl;

import com.rysiw.demo.common.constant.SecurityConstants;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.RedisService;
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
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void addToken(String username, String jwt) throws Exception {
        try {
            redisTemplate.opsForValue().set(username, jwt);
            redisTemplate.expireAt(username, new Date(new Date().getTime() + SecurityConstants.EXPIRATION_FORTEST * 1000));
        }catch (Exception e){
            throw new Exception("存入redis错误");
        }
    }

    @Override
    public boolean isTokenValid(UserEntity user, HttpHeaders httpHeaders) {
        String username = user.getUsername();
        String token = httpHeaders.getFirst(SecurityConstants.TOKEN_HEADER);
        String tokenInRedis = (String) redisTemplate.opsForValue().get(username);
        return Objects.equals(token, tokenInRedis);
    }

    @Override
    public boolean userTokenExists(UserEntity reqUser) {
        return redisTemplate.opsForValue().get(reqUser.getUsername()) != null;
    }
}
