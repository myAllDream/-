package com.framework.app.network;


/**
 * 请求URL地址枚举
 */
public enum UrlEnum {
    //登录
    login("/mobile/user/doLogin");



    public String url;
    public RequestMethod requestMethod = RequestMethod.POST;


    UrlEnum(String url) {
        this.url = url;
    }

    UrlEnum(String url, RequestMethod requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public enum RequestMethod {
        POST("POST"), GET("GET");
        private String requestMethodName;

        RequestMethod(String requestMethodName) {
            this.requestMethodName = requestMethodName;
        }

        public String getRequestMethodName() {
            return requestMethodName;
        }
    }
}
