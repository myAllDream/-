package com.framework.app.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.base.BaseFragment;
import com.framework.app.utils.LogUtil;

import butterknife.BindView;

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

}
