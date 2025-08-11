package com.example.crispixmanusbackend.exception;


import com.example.crispixmanusbackend.common.ErrorCode;
import lombok.Getter;

import java.io.Serial;

/**
 * 业务异常类(接口错误返回时抛一个异常)
 */

@Getter
public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1201767062283762959L;
    private final int code;
    private final String description;

//    构造函数
    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }
    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

}
