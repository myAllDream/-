package com.framework.app.utils;

import android.widget.Toast;

import com.framework.app.MyApp;

/**
 * Created by admin on 2017/12/20.
 */

public class ToastUtils {

    public static void show(String message){
        Toast.makeText(MyApp.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



}
