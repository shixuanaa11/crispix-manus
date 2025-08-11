package com.example.crispixmanusbackend.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户ip信息
 * </p>
 *
 * @author <a href="https://github.com/zongzibinbin">abin</a>
 * @since 2023-03-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 接口请求状态（success/fail）
     * 示例值："success"
     */
    @JsonProperty("status")
    private String status;

    /**
     * 国家英文全称
     * 示例值："China"
     */
    @JsonProperty("country")
    private String country;

    /**
     * 国家二字码（ISO 3166-1 alpha-2）
     * 示例值："CN"
     */
    @JsonProperty("countryCode")
    private String countryCode;

    /**
     * 省级行政区划代码（如GD表示广东）
     */
    @JsonProperty("region")
    private String region;

    /**
     * 省级行政区全称
     * 示例值："广东"
     */
    @JsonProperty("regionName")
    private String regionName;

    /**
     * 城市名称（中文）
     * 示例值："广州"
     */
    @JsonProperty("city")
    private String city;

    /**
     * 邮政编码（当前返回为空）
     */
    @JsonProperty("zip")
    private String zip;

    /**
     * 纬度坐标（WGS84坐标系）
     * 示例值：23.1181
     */
    @JsonProperty("lat")
    private double latitude;

    /**
     * 经度坐标（WGS84坐标系）
     * 示例值：113.2539
     */
    @JsonProperty("lon")
    private double longitude;

    /**
     * 时区标识（IANA格式）
     * 示例值："Asia/Shanghai"
     */
    @JsonProperty("timezone")
    private String timezone;

    /**
     * 互联网服务提供商简称
     * 示例值："China Mobile communications corporation"
     */
    @JsonProperty("isp")
    private String isp;

    /**
     * 所属组织全称
     * 示例值："China Mobile"
     */
    @JsonProperty("org")
    private String org;

    /**
     * 自治系统编号及名称（ASN格式）
     * 示例值："AS56040 China Mobile communications corporation"
     */
    @JsonProperty("as")
    private String as;

    /**
     * 查询的IP地址
     * 示例值："223.104.87.59"
     */
    @JsonProperty("query")
    private String query;
}