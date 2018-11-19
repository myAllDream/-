package com.framework.app.presenter;


import android.content.Context;

import com.framework.app.base.BasePresenter;
import com.framework.app.bean.PlatformBean;
import com.framework.app.contract.AddBillFragmentContract;
import com.framework.app.net.NetClient;
import com.framework.app.net.SimpleSubscriber;
import com.framework.app.utils.LogUtil;
import com.framework.app.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/3/1.
 */

public class AddBillPresenter extends BasePresenter<AddBillFragmentContract> {

    private Context mActivity;

    public AddBillPresenter(Context mActivity) {
        this.mActivity = mActivity;
    }

    public void getPlatforms(String cityCode) {

        NetClient.getInstance().net().getPlatforms("1", "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleSubscriber<PlatformBean>(mViewRf.get(), mActivity) {
                    @Override
                    public void success(PlatformBean platformBean) {
                        mViewRf.get().getPlatformsSuccess(platformBean);
                    }

                    @Override
                    public void error(String errMessage) {
                        LogUtil.i(errMessage);
                        ToastUtils.show(errMessage);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mViewRf.get().addDisposed(d);
                    }
                });
    }


}
