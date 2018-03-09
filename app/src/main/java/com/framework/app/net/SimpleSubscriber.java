package com.framework.app.net;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.framework.app.MyApp;
import com.framework.app.activity.LoginActivity;
import com.framework.app.base.BaseView;
import com.framework.app.utils.ToastUtils;
import com.google.gson.JsonParseException;


import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by ouzy on 2018/1/29.
 * desc:Observer
 */

public abstract class SimpleSubscriber<T> implements Observer<T> {

    private BaseView mBaseView = null;
    private String loadMessage = null;
    private Disposable mDisposable;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public SimpleSubscriber() {

    }

    public SimpleSubscriber(SwipeRefreshLayout swipeRefreshLayout) {
        mSwipeRefreshLayout = swipeRefreshLayout;
    }

    public SimpleSubscriber(BaseView baseView) {
        this.mBaseView = baseView;
    }

    public SimpleSubscriber(BaseView baseView, String message) {
        this.mBaseView = baseView;
        loadMessage = message;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {

        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (t != null) {
            /*if (t instanceof BaseResponseBean) {
                int code = ((BaseResponseBean) t).getCode();
                if (code == 0) {
                    success(t);
                } else if (code == 2016) {
                    //跳转到登录界面
                    ArrayList<Activity> activityList = MyApp.getInstance().activityList;
                    ToastUtils.show("请重新登录");
                    if (activityList != null && activityList.size() > 0) {
                        TurnToActivityUtils.turnToActivity(activityList.get(activityList.size() - 1), LoginActivity.class);
                    } else {
                        TurnToActivityUtils.turnToActivity(MyApp.getInstance(), LoginActivity.class);
                    }
                    error("登录超时");
                } else {
                    error(((BaseResponseBean) t).getMsg());
                }
            } else {
                success(t);
            }*/
        }
        if (mBaseView != null) {
            mBaseView.dismissDialog();
        }
    }

    @Override
    public void onError(Throwable t) {
        if (mBaseView != null) {
            mBaseView.dismissDialog();
        }
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }

        if (t instanceof SocketTimeoutException || t instanceof InterruptedIOException) {
            error("链接超时");
        } else if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException) {
            error("网络异常");
        } else if (t instanceof JSONException || t instanceof JsonParseException || t instanceof ParseException) {
            error("数据解析异常");
        } else {
            error(t.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void success(T t);

    public abstract void error(String errMessage);
}
