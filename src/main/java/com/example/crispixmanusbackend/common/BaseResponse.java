package com.example.crispixmanusbackend.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

//接口返回标准类

/**
 * 通用返回类，服务端向前端返回的响应数据格式
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -5545399893014539209L;
    private int code;

    private T data;
    private String message;
    private String description;
    public BaseResponse(int code, String message, T data,String description) {
        this.code = code;

        this.data = data;
        this.message = message;
        this.description=description;
    }
    public BaseResponse(int code, T data,String message) {
      this(code,message,data,"");
    }
    public BaseResponse(int code, T data) {
        this(code,"",data,"");
    }


    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage(), null,errorCode.getDescription());
    }

}
