package com.framework.app.network;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    public String code;
    public String msg;

    public BaseResponse(String resultCode, String resultText) {
        this.code = resultCode;
        this.msg = resultText;
    }

    public BaseResponse() {

    }

}
