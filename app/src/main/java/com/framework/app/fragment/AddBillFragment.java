package com.framework.app.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.activity.AddBillActivity;
import com.framework.app.adapter.AddBillRecycleAdapter;
import com.framework.app.base.BaseFragment;
import com.framework.app.base.BaseLazyFragment;
import com.framework.app.base.BasePresenter;
import com.framework.app.bean.PlatformBean;
import com.framework.app.contract.AddBillFragmentContract;
import com.framework.app.presenter.AddBillPresenter;
import com.framework.app.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2018/2/26.
 */

public class AddBillFragment extends BaseLazyFragment<AddBillFragmentContract,AddBillPresenter> implements AddBillFragmentContract{

    @BindView(R.id.bill_empty)
    LinearLayout billEmpty;
    @BindView(R.id.add_bill_recy)
    RecyclerView addBillRecy;

    private AddBillRecycleAdapter mAdapter;
    private List<PlatformBean.DataBean> data;
    private int position=10;

    public static AddBillFragment getInstance(int position) {
        AddBillFragment fragment = new AddBillFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected AddBillPresenter creatPresenter() {
        return new AddBillPresenter(getActivity());
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return null;
    }
    @Override
    protected void initData() {
        position = getArguments().getInt("position");
        data = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        addBillRecy.setLayoutManager(manager);
        mAdapter = new AddBillRecycleAdapter(getActivity(), data);
        addBillRecy.setAdapter(mAdapter);

    }

    @Override
    public int getLayoutId() {
        return R.layout.add_bill_fragment;
    }

    @Override
    public void loadData() {
        if (position == 0) {
            //浙江
            mPresenter.getPlatforms("330000");
        } else {
            mPresenter.getPlatforms("");
        }
    }

    public void getPlatformsSuccess(PlatformBean platformBean) {
        if (platformBean.getData() != null && platformBean.getData() != null && platformBean.getData().size() > 0) {
            billEmpty.setVisibility(View.GONE);
            addBillRecy.setVisibility(View.VISIBLE);

            data.clear();
            data.addAll(platformBean.getData());
            mAdapter.notifyDataSetChanged();

        } else {
            billEmpty.setVisibility(View.VISIBLE);
            addBillRecy.setVisibility(View.GONE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
