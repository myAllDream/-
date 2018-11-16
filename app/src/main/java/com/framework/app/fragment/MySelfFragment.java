package com.framework.app.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.activity.LoginActivity;
import com.framework.app.base.BaseFragment;
import com.framework.app.base.BasePresenter;
import com.framework.app.base.BaseView;
import com.framework.app.utils.StatusBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017/12/18.
 */

public class MySelfFragment extends BaseFragment<BaseView,BasePresenter<BaseView>> {

    @BindView(R.id.top_ll)
    LinearLayout view;
    @BindView(R.id.center_title)
    TextView title;

    public static MySelfFragment getInStance() {
        MySelfFragment fragment = new MySelfFragment();
        return fragment;
    }

    @Override
    protected BasePresenter<BaseView> creatPresenter() {
        return null;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return view;
    }


    @Override
    protected void initData() {
        title.setText("我的");
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
