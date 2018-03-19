package com.framework.app.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.framework.app.MyApp;
import com.framework.app.net.NetChangeObserver;
import com.framework.app.net.NetStateReceiver;
import com.framework.app.net.NetUtils;
import com.framework.app.utils.DialogUtils;
import com.framework.app.utils.LoadingUtils;
import com.framework.app.utils.StatusBar;
import com.framework.app.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by admin on 2017/12/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    private LoadingUtils mLoading;
    /**
     * 网络观察者
     */
    protected NetChangeObserver mNetChangeObserver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getWindow().setBackgroundDrawable(null);
        unbinder = ButterKnife.bind(this);
        if (getTopView() != null) {
            StatusBar.init(this, getTopView());
        }
        // 网络改变的一个回掉类
        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                onNetworkDisConnected();
            }
        };
        MyApp.getInstance().activityList.add(this);
        initPresenter();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开启广播去监听 网络 改变事件
        NetStateReceiver.registerObserver(mNetChangeObserver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
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

    /**
     * 网络连接状态
     *
     * @param type 网络状态
     */
    protected  void onNetworkConnected(NetUtils.NetType type){

    }

    /**
     * 网络断开的时候调用
     */
    protected  void onNetworkDisConnected(){

        DialogUtils.getInstance(new DialogUtils.Builder().setTitle("网络提醒")
                .setMessage("网络连接失败,请检查网络")
                .setonClickButtonListener(new DialogUtils.onClickButtonListener() {
                    @Override
                    public void clickNegtive() {

                    }

                    @Override
                    public void clickPositive() {
                        //设置网络
                        Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                        startActivity(intent);
                    }
                })).showDialog(getFragmentManager());
    }


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
