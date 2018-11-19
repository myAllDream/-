package com.framework.app.net;

import com.framework.app.base.BaseResponseBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by admin on 2018/5/23.
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String response = value.string();
            BaseResponseBean bean = gson.fromJson(response, BaseResponseBean.class);
            /*if (!"0".equals(bean.getCode())) {
                throw new DataResultException(bean.getMsg(), bean.getCode());
            }*/
            return gson.fromJson(response, type);
        } finally {
            value.close();
        }
    }
}
