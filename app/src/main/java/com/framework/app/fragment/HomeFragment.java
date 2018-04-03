package com.framework.app.fragment;

import android.view.WindowManager;
import android.widget.LinearLayout;
import com.framework.app.R;
import com.framework.app.base.BaseFragment;
import com.framework.app.contract.HomeFragMentContract;
import com.framework.app.presenter.HomeFragmentPresenter;
import com.youth.banner.Banner;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017/12/18.
 */

public class HomeFragment extends BaseFragment implements HomeFragMentContract.View{

    @BindView(R.id.banner)
    Banner banner;

    private HomeFragMentContract.Presenter mPresenter;

    public static HomeFragment getInStance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return null;
    }

    @Override
    protected void initPresenter() {
        mPresenter=new HomeFragmentPresenter(this);
        mPresenter.startBanner();
    }

    @Override
    protected void initData() {
        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public Banner getBanner() {
        return banner;
    }

}
