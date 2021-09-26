package com.rysiw.demo.common.utils;


import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.common.dto.ResultDTO;
import com.rysiw.demo.common.vo.ResultVO;
import org.springframework.beans.BeanUtils;

//TODO 此类中的方法可以放在ResultVO中做
public class ResultUtil {

    public static ResultVO<Object> getSuccessVO(){
        return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg(RespCode.SUCCESS.getMsg()).build();
    }

    public static ResultVO<Object> getSuccessVO(String msg){
        return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg(msg).build();
    }

    public static ResultVO<Object> resultDTO2resultVO(ResultDTO resultDTO){
        ResultVO<Object> resultVO = ResultVO.builder().build();
        BeanUtils.copyProperties(resultDTO,resultVO);
        return resultVO;
    }

    public static ResultDTO getSuccessDTO(){
        return ResultDTO.builder().code(RespCode.SUCCESS.getCode()).msg(RespCode.SUCCESS.getMsg()).build();
    }

    public static ResultVO<Object> getErrorVO() {
        return ResultVO.builder().code(RespCode.ERROR.getCode()).msg(RespCode.ERROR.getMsg()).build();
    }

    public static ResultVO<Object> getErrorVO(String msg) {
        return ResultVO.builder().code(RespCode.ERROR.getCode()).msg(msg).build();
    }
}
