package com.framework.app.fragment;

import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.base.BaseFragment;
import com.framework.app.base.BasePresenter;
import com.framework.app.base.BaseView;
import com.framework.app.pulluplookmore.PublicStaticClass;
import com.framework.app.pulluplookmore.MyScrollView;

import butterknife.BindView;

/**
 * Created by admin on 2018/3/21.
 */

public class TwoFragment extends BaseFragment<BaseView,BasePresenter<BaseView>> {

    @BindView(R.id.twoScrollview)
    MyScrollView twoScrollview;

    @Override
    protected BasePresenter<BaseView> creatPresenter() {
        return null;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return null;
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
