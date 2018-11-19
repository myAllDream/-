package com.framework.app.net;

import android.util.Log;

import com.framework.app.utils.LogUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by admin on 2018/1/29.
 */

public class LoggingInterceptor implements Interceptor {
    private final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response= chain.proceed(request);
        try {
            FormBody requestBody = (FormBody) request.body();
            String body = null;
            if(requestBody != null) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                body = buffer.readString(charset);
            }

            LogUtil.i("请求："+"\n url" + request.url()+ "\n body   " + body);


            ResponseBody responseBody = response.body();
            String rBody = null;
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            rBody = buffer.clone().readString(charset);
            LogUtil.i("收到响应\n"+response.request().url()+"\n"+rBody);
        }catch (Exception e){

        }

        return response;
    }
}
