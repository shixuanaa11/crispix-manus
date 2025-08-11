package com.example.crispixmanusbackend.exception;


import com.example.crispixmanusbackend.common.BaseResponse;
import com.example.crispixmanusbackend.common.ErrorCode;
import com.example.crispixmanusbackend.common.ResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器（把接口其他的报错都返回是这样的错误，可以避免在前端泄露原始的报错信息,不暴露服务器内部状态）
 * @Author axuan
 * @Date 2022/12/6 14:43
 */
@RestControllerAdvice
@Hidden
@Slf4j
public class GlobalExceptionHandler {
//    捕获运行时业务异常
//    @ExceptionHandler(RuntimeException.class)：这是一个注解，用于指定这个方法处理RuntimeException类型的异常。当控制器中抛出RuntimeException时，Spring会自动调用这个方法来处理异常
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e){
        log.error("业务异常",e);
        return ResultUtils.error(e.getCode(),e.getDescription(), e.getMessage());
    }
//    捕获运行时系统内部异常
   @ExceptionHandler(RuntimeException.class)
   public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
    log.error("RuntimeException", e);
    return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

//    @ExceptionHandler(SaTokenException.class)
//    public BaseResponse<?> SaTokenExceptionHandler(Exception e) {
//        return ResultUtils.error(ErrorCode.NO_LOGIN,e.getMessage());
//    }
//
//    @ExceptionHandler(DisableServiceException.class)
//    public BaseResponse<?> DisableExceptionHandler(Exception e) {
//        return ResultUtils.error(ErrorCode.ACCOUNT_FORBIDDEN,e.getMessage());
//    }

}
