package com.framework.app.net;


import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HeaderInterceptor implements Interceptor {
    private final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("softVersion", "1.0.0")//客户端版本号
                .addHeader("systemVersion", "7.1.2")//设备系统版本号
                .addHeader("deviceId", "864698031881978")//设备ID
                .addHeader("sign", "617e10c71e8e2ef1586e3e6eda507cb1")//签名
                .addHeader("sid", "6babba71-0190-4e97-bf7e-a81b63aff15c")//登录时返回的session id
                .addHeader("source", "android")
                .build();
        return chain.proceed(request);
    }
}
