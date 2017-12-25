package com.framework.app.network;

import java.util.HashMap;
import java.util.Map;

/**
 * 构建请求request
 */
public class RequestMaker {

    private volatile static RequestMaker manager;

    public static RequestMaker getInstance() {
        if (manager == null) {
            synchronized (RequestMaker.class) {
                if (manager == null) {
                    manager = new RequestMaker();
                }
            }
        }
        return manager;
    }

    /**
     * 登录
     */
    public BaseRequest login(String phone,String pwd) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", phone);
        params.put("password", pwd);
        return new BaseRequest(params, UrlEnum.login);
    }



}
