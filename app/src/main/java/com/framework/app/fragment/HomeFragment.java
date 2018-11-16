package com.framework.app.fragment;

import android.view.WindowManager;
import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.base.BaseFragment;
import com.framework.app.contract.HomeFragMentContract;
import com.framework.app.presenter.HomeFragmentPresenter;
import com.youth.banner.Banner;

import butterknife.BindView;

/**
 * Created by admin on 2017/12/18.
 */

public class HomeFragment extends BaseFragment<HomeFragMentContract, HomeFragmentPresenter> implements HomeFragMentContract {
    @BindView(R.id.banner)
    Banner banner;

    public static HomeFragment getInStance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected HomeFragmentPresenter creatPresenter() {
        return new HomeFragmentPresenter();
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return null;
    }


    @Override
    protected void initData() {
        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mPresenter.startBanner();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public Banner getBanner() {
        return banner;
    }


}
