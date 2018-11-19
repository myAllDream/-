package com.framework.app.base;

import java.io.Serializable;

public class BaseResponseBean implements Serializable {

    /**
     * code : 0
     * msg : 请求成功
     */

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
