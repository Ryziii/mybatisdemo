package com.rysiw.demo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RespCode {

    SUCCESS("200","success"),
    ERROR("A0000","error"),
    TOKEN_ALIVE("A0001", "用户token存活，无需重新注册"),
    CANT_FIND_USER("A0002", "未找到用户"),
    PASSWORD_WRONG("A0003", "密码错误"),
    REDIS_ERROR("A0004", "存入redis错误"),
    JWT_PARSE_ERROR("A0004", "JWT解析异常"),
    TOKEN_DONT_MATCH("A0005", "token与用户名不匹配");

    private final String code;
    private final String msg;
}
