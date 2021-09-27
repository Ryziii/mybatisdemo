package com.rysiw.demo.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.exception.DefineException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
     private String code;
     private String msg;
     private T data;

     public ResultVO(){

     }

     public ResultVO<T> buildSuccess(){
          this.code = RespCode.SUCCESS.getCode();
          this.msg = RespCode.SUCCESS.getMsg();
          return this;
     }

     public static ResultVO buildError(){
          ResultVO resultVO = new ResultVO();
          resultVO.code = RespCode.ERROR.getCode();
          resultVO.msg = RespCode.ERROR.getMsg();
          return resultVO;
     }

     public static ResultVO buildDefineError(DefineException e){
          ResultVO resultVO = new ResultVO();
          resultVO.code = e.getErrorCode();
          resultVO.msg = e.getMessage();
          resultVO.data = e.getData();
          return resultVO;
     }

     public static ResultVO otherError(Exception e) {
          ResultVO resultVO = new ResultVO();
          resultVO.code = RespCode.ERROR.getCode();
          resultVO.msg = e.getMessage();
          resultVO.data = e.getStackTrace();
          return resultVO;
     }

     public static ResultVO<Object> getSuccessVO(){
          return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg(RespCode.SUCCESS.getMsg()).build();
     }

     public static ResultVO<Object> getSuccessVO(String msg){
          return ResultVO.builder().code(RespCode.SUCCESS.getCode()).msg(msg).build();
     }

     public static ResultVO<Object> getErrorVO() {
          return ResultVO.builder().code(RespCode.ERROR.getCode()).msg(RespCode.ERROR.getMsg()).build();
     }

     public static ResultVO<Object> getErrorVO(String msg) {
          return ResultVO.builder().code(RespCode.ERROR.getCode()).msg(msg).build();
     }
}
