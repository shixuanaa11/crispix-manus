package com.example.crispixmanusbackend.common;

/**
 * 返回工具类 (这样我们外面如果要成功的接口就直接调这个就行了)
 * @author axuan
 */
public class ResultUtils {
    /**
     * 成功的返回
     * @param data
     * @return
     * @param <T>
     */
    public static <T>  BaseResponse<T> success(T data) {
        return new BaseResponse<>(0,data,"success");
    }

    /**
     * 失败的返回
     * @param errorCode
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }
    /**
     * 失败的返回(自定义描述和消息)
     * @param errorCode
     * @return
     */

    public static <T> BaseResponse<T> error(ErrorCode errorCode,String description,String message){
        return new BaseResponse<>(errorCode.getCode(),message,null,description);

    }
    /**
     * 失败的返回(自定义描述)
     * @param errorCode
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode,String description){
        return new BaseResponse<>(errorCode.getCode(),errorCode.getMessage(),null,description);
    }
    /**
     * 失败的返回(自定义code)
     * @param errorCode
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode,int code){
        return new BaseResponse<>(code, errorCode.getMessage(), null, errorCode.getDescription());
    }
    /**
     * 失败的返回(自定义全部)
     * @param code
     * @param description
     * @param message
     * @return
     */
    public static <T> BaseResponse<T> error(int code,String description,String message){
        return new BaseResponse<>(code, message,null, description);
    }

}
