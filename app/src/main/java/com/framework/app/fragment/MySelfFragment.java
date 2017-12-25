package com.framework.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.activity.LoginActivity;
import com.framework.app.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/12/18.
 */

public class MySelfFragment extends BaseFragment {

    @BindView(R.id.view)
    LinearLayout view;

    public static MySelfFragment getInStance() {
        MySelfFragment fragment = new MySelfFragment();
        return fragment;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return view;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.myself_fragment;
    }



    @OnClick(R.id.login)
    public void onViewClicked() {

        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
}
