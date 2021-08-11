package com.rysiw.demo.service;

import com.rysiw.demo.entity.UserEntity;
import org.springframework.http.HttpHeaders;

/**
 * @author Rysiw
 * @date 2021/8/1 18:41
 */
public interface RedisService {

    void addToken(String username, String jwt) throws Exception;

    boolean isTokenValid(UserEntity user, HttpHeaders httpHeaders);

    boolean userTokenExists(UserEntity reqUser);
}
