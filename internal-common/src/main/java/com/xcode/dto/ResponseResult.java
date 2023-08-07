package com.xcode.dto;

import com.xcode.CommonStateEnum;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;


    public static <T> ResponseResult success(){

        return new ResponseResult()
                .setCode(CommonStateEnum.SUCCESS.getCode())
                .setMessage(CommonStateEnum.SUCCESS.getValue())
                ;

    }


    public static <T> ResponseResult success(T data){

        return new ResponseResult()
                .setCode(CommonStateEnum.SUCCESS.getCode())
                .setMessage(CommonStateEnum.SUCCESS.getValue())
                .setData(data);

    }

    public static ResponseResult fail(int code,String message){

        return new ResponseResult().setCode(CommonStateEnum.FAIL.getCode()).setMessage(CommonStateEnum.FAIL.getValue());

    }

    public static ResponseResult fail(int code,String message,String data){

        return new ResponseResult().setCode(code).setMessage(message).setData(data);

    }




}
