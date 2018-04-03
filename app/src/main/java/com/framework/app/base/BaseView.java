package com.framework.app.base;

import android.app.Dialog;

import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/12/25.
 */

public interface BaseView {
    Dialog showLoading(String message);
    void dismissDialog();
    void addDisposed(Disposable disposable);

}
