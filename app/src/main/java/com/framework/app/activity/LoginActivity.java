package com.framework.app.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.base.BaseActivity;
import com.framework.app.contract.LoginContract;
import com.framework.app.utils.DialogUtils;
import com.framework.app.utils.LoadingUtils;
import com.framework.app.utils.LogUtil;
import com.framework.app.network.BaseRequest;
import com.framework.app.network.RequestMaker;
import com.framework.app.network.SimpleImpOnHttpCallBack;
import com.framework.app.utils.ModifyTitleBarColor;
import com.framework.app.utils.StatusBar;

import butterknife.BindView;


public class LoginActivity extends BaseActivity implements LoginContract.View{

    @BindView(R.id.login_top)
    LinearLayout loginTop;
    @BindView(R.id.login_tv)
    TextView login_tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {
        StatusBar.setBarColor(this,loginTop, Color.parseColor("#ffffff"));
        ModifyTitleBarColor.StatusBarLightMode(this);
    }

    @Override
    protected void initData() {

        requestGetData(RequestMaker.getInstance().login("18348086228", "123456"), new SimpleImpOnHttpCallBack<String>(showLoading("")) {
            @Override
            public void onSuccessData(String s, String code, String jsonStr, BaseRequest request) {
                LogUtil.printAll(s,code,jsonStr);
            }
        });



    }



    @Override
    protected LinearLayout getTopView() {
        return null;
    }

}
