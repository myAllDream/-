package com.framework.app.net;

import java.io.IOException;

/**
 * Created by admin on 2018/5/23.
 */

public class DataResultException extends IOException {
    private String msg;
    private String code;

    public DataResultException(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public DataResultException(String message, String msg, String code) {
        super(message);
        this.msg = msg;
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
