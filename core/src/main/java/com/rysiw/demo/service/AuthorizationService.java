package com.rysiw.demo.service;

import com.rysiw.demo.entity.UserEntity;
import org.springframework.http.HttpHeaders;

/**
 * @author Rysiw
 * @date 2021/8/1 18:33
 */
public interface AuthorizationService {

    String createToken(UserEntity reqUser, HttpHeaders httpHeaders) throws Exception;
}
