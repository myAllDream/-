package com.framework.app.activity;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.base.BaseActivity;
import com.framework.app.base.BasePresenter;
import com.framework.app.contract.LoginContract;
import com.framework.app.keyboard.MyRectKeyBoard;
import com.framework.app.keyboard.PasswordKeyboard;
import com.framework.app.utils.DialogUtils;
import com.framework.app.utils.LogUtil;
import com.framework.app.utils.StatusBar;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;


public class LoginActivity extends BaseActivity<LoginContract,BasePresenter<LoginContract>> implements MyRectKeyBoard.OnPasswordInputListener {

    @BindView(R.id.top_ll)
    LinearLayout loginTop;
    @BindView(R.id.input)
    MyRectKeyBoard input;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    public BasePresenter<LoginContract> creatPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initData() {
        //showDialogd();
        StatusBar.setBarColor(this, loginTop, Color.parseColor("#999999"));
        input.setOnPasswordInputListener(this);
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

    StringBuilder builder=new StringBuilder();
    @Override
    public void onInput(String number) {
        LogUtil.i("----密码---："+number);

        if (PasswordKeyboard.DEL.equals(number)) {
            if (builder.length() > 0) {
                builder.delete(builder.length() - 1, builder.length());
            }
        }else {
            builder.append(number);
        }
        tv.setText(builder.toString());

        if(builder.toString().equals("123456")){
            input.resetKeyboard();
        }

    }
}
