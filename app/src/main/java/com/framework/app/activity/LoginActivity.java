package com.framework.app.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.base.BaseActivity;
import com.framework.app.contract.LoginContract;
import com.framework.app.net.NetClient;
import com.framework.app.utils.DialogUtils;
import com.framework.app.utils.LogUtil;
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
        showDialogd();
    }

    private void showDialogd() {
        DialogUtils.getInstance(new DialogUtils.Builder().setTitle("成都方法大事发生的")
        .setMessage("士大夫撒付水电费水电费水电费都舒服的沙发上发生的")
        .setonClickButtonListener(new DialogUtils.onClickButtonListener() {
            @Override
            public void clickNegtive() {

            }

            @Override
            public void clickPositive() {

            }
        })).showDialog(getFragmentManager());

    }


    @Override
    protected LinearLayout getTopView() {
        return null;
    }

}
