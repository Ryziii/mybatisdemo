package com.rysiw.demo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RespCode {

    SUCCESS("200","success"),
    ERROR("0000","error");

    private final String code;
    private final String msg;
}
