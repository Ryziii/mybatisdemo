package com.rysiw.demo.common.utils;


import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.common.dto.ResultDTO;
import com.rysiw.demo.common.vo.ResultVO;
import org.springframework.beans.BeanUtils;

public class ResultUtil {

    public static ResultVO<Object> getSuccessVO(){
        return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg(RespCode.SUCCESS.getMsg()).build();
    }

    public static ResultVO<Object> resultDTO2resultVO(ResultDTO resultDTO){
        ResultVO<Object> resultVO = ResultVO.builder().build();
        BeanUtils.copyProperties(resultDTO,resultVO);
        return resultVO;
    }

    public static ResultDTO getSuccessDTO(){
        return ResultDTO.builder().code(RespCode.SUCCESS.getCode()).msg(RespCode.SUCCESS.getMsg()).build();
    }

}
