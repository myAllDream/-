package com.framework.app.utils;

import android.content.Context;

/**
 * Created by admin on 2018/3/19.
 */

public class PermissionManager {
    private static PermissionManager mManager;
    private Context mContext;

    private PermissionManager(){

    }
    public static PermissionManager getInstance(Context context){
        if(mManager==null){
            mManager=new PermissionManager();
        }
        mManager.initContext(context);
        return mManager;
    }

    private void initContext(Context context) {
        this.mContext=context;
    }



}
