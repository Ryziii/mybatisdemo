package com.rysiw.demo.exception;

public class DefineException extends RuntimeException{

    protected Integer errorCode;
    protected String errorMsg;

    public DefineException(){

    }

    public DefineException(Integer errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode(){
        return this.errorCode;
    }
    public String getErrorMsg(){
        return this.errorMsg;
    }
    public void setErrorCode(Integer errorCode){
        this.errorCode = errorCode;
    }
    public void setErrorMsg(String errorMsg){
        this.errorMsg = errorMsg;
    }
}
