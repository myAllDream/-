package com.framework.app.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.adapter.AddBillFragmentAdapter;
import com.framework.app.base.BaseActivity;
import com.framework.app.base.BasePresenter;
import com.framework.app.base.BaseView;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class AddBillActivity extends BaseActivity<BaseView, BasePresenter<BaseView>> {
    @BindView(R.id.top_ll)
    LinearLayout view;
    @BindView(R.id.add_bill_tab)
    TabLayout addBillTab;
    @BindView(R.id.add_bill_vp)
    ViewPager addBillVp;

    public CompositeDisposable mDisposable;

    private AddBillFragmentAdapter mAdapter;

    @Override
    public BasePresenter creatPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_bill;
    }

    @Override
    protected void initData() {

        mAdapter = new AddBillFragmentAdapter(getSupportFragmentManager());
        addBillVp.setAdapter(mAdapter);
        addBillVp.setOffscreenPageLimit(4);

        addBillTab.setupWithViewPager(addBillVp);


    }

    @Override
    protected LinearLayout getTopView() {
        return view;
    }

}
