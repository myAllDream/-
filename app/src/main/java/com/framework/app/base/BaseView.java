package com.framework.app.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/12/25.
 */

public interface BaseView {
    BaseView getBaseView();

    void addDisposed(Disposable disposable);

}
