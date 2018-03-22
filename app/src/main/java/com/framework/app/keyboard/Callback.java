package com.framework.app.keyboard;


public interface Callback {

    void onForgetPassword();

    void onInputCompleted(CharSequence password);

    void onPasswordCorrectly();

    void onCancel();
}
