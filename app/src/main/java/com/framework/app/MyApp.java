package com.framework.app;

import android.app.Activity;
import android.app.Application;

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

    }

    public static MyApp getInstance() {
        return application;
    }

    public void removeAll(){
        for(Activity activity:activityList){
            activity.finish();
        }
    }
}
