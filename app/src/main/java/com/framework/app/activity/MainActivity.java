package com.framework.app.activity;

import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.framework.app.MyApp;
import com.framework.app.R;
import com.framework.app.base.BaseActivity;
import com.framework.app.config.Parms;
import com.framework.app.contract.MainContract;
import com.framework.app.net.NetUtils;
import com.framework.app.presenter.MainPresenter;
import com.framework.app.utils.LogUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.main_group)
    RadioGroup mainGroup;
    private long exitTime = 0;
    private MainContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter=new MainPresenter();
    }

    @Override
    protected void initData() {
        mainGroup.setOnCheckedChangeListener(this);
        radioHome.setChecked(true);
    }

    @Override
    protected LinearLayout getTopView() {
        return null;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_home:
                mPresenter.changeFragment(Parms.FragmentTag.HOME_FRAGMENT, getSupportFragmentManager());
                break;
            case R.id.radio_second:
                mPresenter.changeFragment(Parms.FragmentTag.MY_FRAGMENT, getSupportFragmentManager());
                break;
            case R.id.radio_three:
                mPresenter.changeFragment(Parms.FragmentTag.HOME_FRAGMENT, getSupportFragmentManager());
                break;
            case R.id.radio_my:
                mPresenter.changeFragment(Parms.FragmentTag.MY_FRAGMENT, getSupportFragmentManager());
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MyApp.getInstance().removeAll();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
