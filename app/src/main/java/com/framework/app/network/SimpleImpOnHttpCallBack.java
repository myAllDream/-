package com.framework.app.network;

import android.app.Dialog;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.framework.app.utils.ToastUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class SimpleImpOnHttpCallBack<T> implements OkHttpManager.OnHttpCallBack {

    private Dialog mDialog;

    public SimpleImpOnHttpCallBack() {
    }

    public SimpleImpOnHttpCallBack(Dialog dialog) {
        this.mDialog = dialog;
    }

    @Override
    public void onFail(String errMsg, String code) {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        ToastUtils.show(errMsg);
    }
    /**
     * @param jsonStr 返回json数据
     * @param code    返回状态码
     * @param request 请求的request
     */
    public void onSuccess(String jsonStr, String code, BaseRequest request) {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        try {
            Class<T> entityClass = null;
            Type t = getClass().getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
                entityClass = (Class<T>) p[0];
            }
            onSuccessData(JSONObject.parseObject(jsonStr, entityClass), code, jsonStr, request);
        } catch (Exception e) {
            Log.i("code","出现错误："+ e.toString());
            onFail("数据解析失败", code);
        }

    }


    public abstract void onSuccessData(T t, String code, String jsonStr, BaseRequest request);

}
