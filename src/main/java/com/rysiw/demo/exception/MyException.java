package com.rysiw.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Rysiw
 * @date 2021/8/27 00:02
 */
public class MyException extends RuntimeException{

    //TODO exception就返回到json
    private Integer code;

    public MyException(Integer code,String message){
        super(message);
        this.code = code;
    }

    @ExceptionHandler(value = Exception.class)
    public String myException(Exception e){
        return e.getMessage();
    }
}
