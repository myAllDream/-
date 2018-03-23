package com.framework.app.bean;


import java.io.Serializable;

/**
 * Created by ouzy on 2018/2/28.
 * desc:BaseResponse
 */

public class BaseResponseBean implements Serializable {

    /**
     * code : 0
     * msg : 请求成功
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
