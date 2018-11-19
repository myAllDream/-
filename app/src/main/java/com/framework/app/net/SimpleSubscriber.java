package com.framework.app.net;

import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.framework.app.base.BaseView;
import com.framework.app.utils.LoadingUtils;
import com.framework.app.utils.LogUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class SimpleSubscriber<T> implements Observer<T> {

    private BaseView mBaseView = null;
    private String loadMessage = null;
    private Disposable mDisposable;
    private LoadingUtils mUtils;

    private SimpleSubscriber() {

    }

    public SimpleSubscriber(BaseView baseView, Context context) {
        this.mBaseView = baseView;
        mUtils = new LoadingUtils(context);
    }

    public SimpleSubscriber(BaseView baseView, Context context, String message) {
        this.mBaseView = baseView;
        mUtils = new LoadingUtils(context);
        loadMessage = message;
    }


    @Override
    public void onSubscribe(Disposable d) {
        LogUtil.iMsg("---------------------");
        mDisposable = d;
        if (mUtils != null && !mUtils.isShowing()) {
            if (loadMessage != null) {
                mUtils.show(loadMessage);
            } else {
                mUtils.show("正在请求...");
            }
        }
        if (mUtils != null) {
            LogUtil.iMsg("----------11111-----------");
            mUtils.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {

                    LogUtil.iMsg("---------22222------------");
                    return false;
                }
            });
        }
    }

    @Override
    public void onNext(T t) {

        if (mBaseView.getBaseView() == null) {
            return;
        }
        if (t != null) {
            success(t);
        }
        /*if (mUtils != null && mUtils.isShowing()) {
            mUtils.dismiss();
        }*/
    }

    @Override
    public void onError(Throwable t) {
        if (mBaseView.getBaseView() == null) {
            return;
        }
        if (mUtils != null) {
            mUtils.dismiss();
        }
        if (t instanceof SocketTimeoutException || t instanceof InterruptedIOException) {
            error("链接超时");
        } else if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException) {
            error("连接服务器失败");
        } else if (t instanceof JSONException || t instanceof JsonParseException || t instanceof ParseException) {
            error("数据解析异常");
        } else {
            error(t.getMessage());
        }
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public abstract void success(T t);

    public abstract void error(String errMessage);
}
