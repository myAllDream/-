package com.framework.app.presenter;

import android.support.v4.app.FragmentManager;

import com.framework.app.contract.MainContract;
import com.framework.app.utils.FragmentUtils;

/**
 * Created by admin on 2017/12/20.
 */

public class MainPresenter implements MainContract.Presenter {

    FragmentUtils fragmentUtils;

    public MainPresenter() {
        fragmentUtils = new FragmentUtils();
    }

    @Override
    public void changeFragment(String tag, FragmentManager fragmentManager) {
        fragmentUtils.showFragment(tag, fragmentManager);
    }
}
