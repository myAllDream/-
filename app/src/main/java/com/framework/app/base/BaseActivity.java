package com.framework.app.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.framework.app.MyApp;
import com.framework.app.network.BaseRequest;
import com.framework.app.network.OkHttpManager;
import com.framework.app.utils.LoadingUtils;
import com.framework.app.utils.StatusBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by admin on 2017/12/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    //表单请求
    private Call okHttpCall;
    private LoadingUtils mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getWindow().setBackgroundDrawable(null);
        unbinder = ButterKnife.bind(this);
        if (getTopView() != null) {
            StatusBar.init(this, getTopView());
        }
        MyApp.getInstance().activityList.add(this);
        initPresenter();
        initData();
    }

    /**
     * 网络请求
     *
     * @param baseRequest
     * @param onHttpCallBack
     */
    public void requestGetData(BaseRequest baseRequest, OkHttpManager.OnHttpCallBack onHttpCallBack) {
        okHttpCall = OkHttpManager.getManager().getHttpData(baseRequest, onHttpCallBack);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (okHttpCall != null) {
            okHttpCall.cancel();
        }
        MyApp.getInstance().activityList.remove(this);
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化P层
     */
    protected abstract void initPresenter();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取沉浸式状态栏顶部占位栏
     *
     * @return
     */
    protected abstract LinearLayout getTopView();

    public Dialog showLoading(String message){
        if(mLoading==null){
            mLoading=new LoadingUtils(this);
        }
        mLoading.show(message);
        return mLoading;
    }

    public void dismissDialog(){
        if(mLoading!=null && mLoading.isShowing()){
            mLoading.dismiss();
        }
    }

}
