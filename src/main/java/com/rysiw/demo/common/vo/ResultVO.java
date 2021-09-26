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
          resultVO.code = String.valueOf(e.getErrorCode());
          resultVO.msg = e.getErrorMsg();
          return resultVO;
     }

     public static ResultVO otherError(Exception e) {
          ResultVO resultVO = new ResultVO();
          resultVO.code = "500";
          resultVO.msg = e.getMessage();
          resultVO.data = e.getStackTrace();
          return resultVO;
     }
}
