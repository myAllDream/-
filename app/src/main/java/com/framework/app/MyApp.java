package com.framework.app;

import android.app.Activity;
import android.app.Application;

import com.framework.app.net.NetStateReceiver;
import com.framework.app.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by admin on 2017/12/18.
 */

public class MyApp extends Application {
    private static MyApp application;
    public ArrayList<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        activityList = new ArrayList<>();
        application = this;
        /*开启网络广播监听*/
        NetStateReceiver.registerNetworkStateReceiver(this);

    }

    public static MyApp getInstance() {
        return application;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void removeAll(){
        for(Activity activity:activityList){
            activity.finish();
        }
    }
}
