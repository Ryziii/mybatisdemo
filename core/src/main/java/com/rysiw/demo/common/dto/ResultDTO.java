package com.rysiw.demo.common.dto;

import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.common.vo.ResultVO;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    public static ResultDTO getSuccessDTO(){
        return ResultDTO.builder().code(RespCode.SUCCESS.getCode()).msg(RespCode.SUCCESS.getMsg()).build();
    }

    public static ResultVO<Object> resultDTO2resultVO(ResultDTO resultDTO){
        ResultVO<Object> resultVO = ResultVO.builder().build();
        BeanUtils.copyProperties(resultDTO,resultVO);
        return resultVO;
    }
}
