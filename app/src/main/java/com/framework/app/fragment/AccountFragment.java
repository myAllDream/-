package com.framework.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.framework.app.R;
import com.framework.app.activity.ProductDetailActivity;
import com.framework.app.base.BaseFragment;
import com.framework.app.keyboard.Callback;
import com.framework.app.keyboard.PasswordKeypad;
import com.framework.app.utils.LogUtil;

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


    @OnClick({R.id.tv, R.id.pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv:
                startActivity(new Intent(getActivity(), ProductDetailActivity.class));
                break;
            case R.id.pwd:
                show();
                break;
        }

    }

    private PasswordKeypad mKeypad;
    private boolean state;

    private void show() {
        mKeypad = new PasswordKeypad();
        mKeypad.setPasswordCount(6);
        mKeypad.setCallback(new Callback() {
            @Override
            public void onForgetPassword() {
                Toast.makeText(getActivity(), "忘记密码", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInputCompleted(CharSequence password) {
                LogUtil.i("------"+password.toString());
                if ("123456".equals(password.toString())) {
                    mKeypad.setPasswordState(true);
                    state = false;
                } else {
                    mKeypad.setPasswordError("密码输入错误", getActivity());
                    state = true;
                }
            }

            @Override
            public void onPasswordCorrectly() {
                mKeypad.dismiss();
            }

            @Override
            public void onCancel() {
                //todo:做一些埋点类的需求
            }
        });
        mKeypad.show(getActivity().getSupportFragmentManager(), "PasswordKeypad");
    }
}