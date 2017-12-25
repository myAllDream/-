package com.framework.app.fragment;

import android.view.WindowManager;
import android.widget.LinearLayout;
import com.framework.app.R;
import com.framework.app.base.BaseFragment;

/**
 * Created by admin on 2017/12/18.
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment getInStance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return null;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
