package com.framework.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.base.BaseFragment;
import com.framework.app.utils.PublicStaticClass;
import com.framework.app.view.MyScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/3/21.
 */

public class TwoFragment extends BaseFragment {

    @BindView(R.id.twoScrollview)
    MyScrollView twoScrollview;

    @Override
    protected LinearLayout getLinearLayout() {
        return null;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        twoScrollview.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollToBottom() {

            }

            @Override
            public void onScrollToTop() {

            }

            @Override
            public void onScroll(int scrollY) {
                if (scrollY == 0) {
                    PublicStaticClass.IsTop = true;
                } else {
                    PublicStaticClass.IsTop = false;
                }
            }

            @Override
            public void notBottom() {

            }

        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_two;
    }


}
