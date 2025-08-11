package com.example.crispixmanusbackend.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * web请求信息收集类
 */
@Data
public class RequestInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1673068883578103994L;
    private Long uid;
    private String ip;
}