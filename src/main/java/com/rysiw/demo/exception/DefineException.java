package com.rysiw.demo.exception;

import com.rysiw.demo.common.constant.RespCode;
import lombok.Data;

@Data
public class DefineException extends RuntimeException{

    protected String errorCode;
    protected Object data;

    public DefineException(){

    }

    public DefineException(String errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public DefineException(RespCode respCode){
        super(respCode.getMsg());
        this.errorCode = respCode.getCode();
    }

    public DefineException(RespCode respCode, Object data){
        super(respCode.getMsg());
        this.errorCode = respCode.getCode();
        this.data = data;
    }

    public DefineException(Object data){
        super(RespCode.ERROR.getMsg());
        this.errorCode = RespCode.ERROR.getCode();
        this.data = data;
    }
}
