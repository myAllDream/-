package com.framework.app.net;


import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ouzy on 2018/2/27.
 * desc:请求头信息
 */

public class HeaderInterceptor implements Interceptor {
    private final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        /*Request request = chain.request()
                .newBuilder()
                .addHeader("softVersion", MyApp.getInstance().getAppVersionName())//客户端版本号
                .addHeader("systemVersion", DeviceUtil.getSystemVersion())//设备系统版本号
                .addHeader("deviceId", DeviceUtil.getDeviceId(MyApp.getInstance()))//设备ID
                .addHeader("sign", "")//签名
                .addHeader("sid", Constant.Toke)//登录时返回的session id
                //.addHeader("sid", "f145c592-4b75-4868-8607-c3101afe9449")//这个是测试session
                .addHeader("source", "android")
                .build();
        return chain.proceed(request);*/
        return chain.proceed(chain.request());
    }
}
