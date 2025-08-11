package com.example.crispixmanusbackend.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(0,"success","操作成功"),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NO_PERMISSION(40101, "无权限访问", ""),
    NO_LOGIN(40100, "未登录", ""),

    SYSTEM_ERROR(50000, "系统内部异常", ""),
    TOO_MANY_REQUEST(42900,"请求过于频繁",""),
    FORBIDDEN_ERROR(40300, "禁止访问",""),
    OPERATION_ERROR(50001, "操作失败",""),
    AGENT_ERROR(50001, "AI响应失败",""),
    API_ERROR(5001, "第三方服务异常",""),
    NETWORK_ERROR(5002, "网络通信异常",""),
    ACCOUNT_FORBIDDEN(40003,"账号被封禁","");

    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码描述
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
