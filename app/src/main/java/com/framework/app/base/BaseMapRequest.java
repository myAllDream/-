package com.framework.app.base;

import android.support.v4.util.ArrayMap;
import java.util.Map;

/**
 * Created by admin on 2017/12/20.
 */

public abstract class BaseMapRequest {
    protected Map<String,String> map;


    public BaseMapRequest(){
        this.map=new ArrayMap<>();
    }

    public Map<String,String> getParams(){
        map.clear();
        putParams();
        return map;
    }

    protected abstract void putParams();
}
