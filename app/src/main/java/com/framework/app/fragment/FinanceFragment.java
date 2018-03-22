package com.framework.app.fragment;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by admin on 2018/3/19.
 */

public class FinanceFragment extends BaseFragment {

    @BindView(R.id.top_ll)
    LinearLayout view;
    @BindView(R.id.center_title)
    TextView title;

    public static FinanceFragment getInStance() {
        FinanceFragment fragment = new FinanceFragment();
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
        title.setText("理财");
    }

    @Override
    public int getLayoutId() {
        return R.layout.finance_fragment;
    }

}