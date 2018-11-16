package com.framework.app.presenter;

import android.support.v4.app.FragmentManager;

import com.framework.app.base.BasePresenter;
import com.framework.app.base.BaseView;
import com.framework.app.contract.MainContract;
import com.framework.app.utils.FragmentUtils;

/**
 * Created by admin on 2017/12/20.
 */

public class MainPresenter extends BasePresenter<BaseView>{

    FragmentUtils fragmentUtils;

    public MainPresenter() {
        fragmentUtils = new FragmentUtils();
    }


    public void changeFragment(String tag, FragmentManager fragmentManager) {
        fragmentUtils.showFragment(tag, fragmentManager);
    }
}
