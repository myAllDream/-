package com.framework.app.utils;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by admin on 2018/3/19.
 */

public class PermissionManager {
    private static PermissionManager mManager;
    private Context mContext;
    private ArrayList<String> permissionList;
    private PermissionCallBack mBack;
    private int tag;

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

    /**
     * 权限请求码
     */
    public PermissionManager setTag(int tag){
        this.tag=tag;
        return this;
    }

    /**
     * 需要请求的权限
     */
    public PermissionManager shouldPermission(String[] permissions){
        permissionList=new ArrayList<>();
        for(String permission : permissions){
            permissionList.add(permission);
        }
        return this;
    }

    /**
     * 回调
     */
    public PermissionManager callBack(PermissionCallBack back){
        this.mBack=back;
        return this;
    }
    public interface PermissionCallBack{
        void onGranted(ArrayList<String> perimisson);
        void onDenied(ArrayList<String> perimisson);
    }


}
