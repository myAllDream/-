package com.framework.app.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.framework.app.utils.LoadingUtils;
import com.framework.app.utils.StatusBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/12/20.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;
    private LoadingUtils mLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutId(),container,false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getLinearLayout()!=null){
            StatusBar.init(getActivity(),getLinearLayout());
        }
        initData();
        initPresenter();
    }

    protected abstract LinearLayout getLinearLayout();

    protected abstract void initPresenter();

    protected abstract void initData();

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }

    public Dialog showLoading(String message){
        if(mLoading==null){
            mLoading=new LoadingUtils(getActivity());
        }
        mLoading.show(message);
        return mLoading;
    }

    public void dismissDialog(){
        if(mLoading!=null && mLoading.isShowing()){
            mLoading.dismiss();
        }
    }
}
