package com.rysiw.demo.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Rysiw
 * @date 2021/7/23 22:04
 */
@Data
@Builder
public class ResultDTO {

    private String code;
    private String msg;
    private Object data;
}
