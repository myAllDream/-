package com.framework.app.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public class MyConverterFavtory extends Converter.Factory {

    public static MyConverterFavtory create() {
        return create(new Gson());
    }

    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static MyConverterFavtory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new MyConverterFavtory(gson);
    }

    private final Gson gson;

    private MyConverterFavtory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonRequestBodyConverter<>(gson, adapter);
    }
}
