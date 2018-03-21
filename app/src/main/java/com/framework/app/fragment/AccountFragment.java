package com.framework.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.activity.ProductDetailActivity;
import com.framework.app.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/3/19.
 */

public class AccountFragment extends BaseFragment {

    @BindView(R.id.top_ll)
    LinearLayout view;
    @BindView(R.id.center_title)
    TextView title;

    public static AccountFragment getInStance() {
        AccountFragment fragment = new AccountFragment();
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
        title.setText("账单");
    }

    @Override
    public int getLayoutId() {
        return R.layout.account_fragment;
    }


    @OnClick(R.id.tv)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), ProductDetailActivity.class));
    }
}
