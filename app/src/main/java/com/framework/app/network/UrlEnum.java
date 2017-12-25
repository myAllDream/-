package com.framework.app.network;


/**
 * 请求URL地址枚举
 */
public enum UrlEnum {
    //登录
    login("/mobile/user/doLogin"),
    //用户发送短信验证码接口
    sendMsg("/mobile/sms/sendRandCode"),
    //用户注册接口
    register("/mobile/user/doRegister"),
    //借款列表
    loanRecordList("/mobile/item/itemList"),
    //我的
    mine("/mobile/user/stats"),
    //提额
    promote("/mobile/user/authStatus"),
    //保存个人信息和职业信息
    saveInformation("/mobile/user/setInfo"),
    //用户个人信息展示接口
    personInfoBack("/mobile/user/getInfo"),
    //保存联系人
    saveContacts("/mobile/user/setContacts"),
    //联系人回显
    contactDataBack("/mobile/user/getContacts"),
    //查看个人额度
    checkAmount("/mobile/apply/checkAmount"),
    //我要借款
    wantLoan("/mobile/apply/newApply"),
    //借款申请确认
    confirmLoan("/mobile/apply/confirmApply"),
    //查看借款详情
    checkLoanDetail("/mobile/item/detail"),
    //根据金额和天数计算到期后需要偿还的总金额
    calcReturnMoney("/mobile/item/calcReturnMoney"),
    //重置用户密码
    resetPassword("/mobile/user/doResetPassword"),
    //查看借款还款信息
    queryLoanDetail("/mobile/item/getItemAndRepay");



    public String url;
    public RequestMethod requestMethod = RequestMethod.POST;


    UrlEnum(String url) {
        this.url = url;
    }

    UrlEnum(String url, RequestMethod requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public enum RequestMethod {
        POST("POST"), GET("GET");
        private String requestMethodName;

        RequestMethod(String requestMethodName) {
            this.requestMethodName = requestMethodName;
        }

        public String getRequestMethodName() {
            return requestMethodName;
        }
    }
}
