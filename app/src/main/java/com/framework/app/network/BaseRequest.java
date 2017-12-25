package com.framework.app.network;


import java.util.Map;

/**
 * 基础请求实体
 */
public class BaseRequest {

    public Map<String, Object> headerParams;
    public Map<String, Object> params;
    public UrlEnum urlEnum;

    public BaseRequest(Map<String, Object> params, UrlEnum urlEnum) {
        this.params = params;
        this.urlEnum = urlEnum;
    }

    /**
     * 带请求头的网络请求
     * @param headerParams
     * @param params
     * @param urlEnum
     */
    public BaseRequest(Map<String, Object> headerParams, Map<String, Object> params, UrlEnum urlEnum) {
        this.headerParams = headerParams;
        this.params = params;
        this.urlEnum = urlEnum;
    }

    /**
     * 无参数的网络请求
     * @param urlEnum
     */
    public BaseRequest(UrlEnum urlEnum) {
        this.urlEnum = urlEnum;
    }
}
