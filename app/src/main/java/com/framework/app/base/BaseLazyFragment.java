package com.framework.app.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.framework.app.utils.LoadingUtils;
import com.framework.app.utils.LogUtil;
import com.framework.app.utils.StatusBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 懒加载fragment
 */

public abstract class BaseLazyFragment extends LazyLoadFragment{
    private Unbinder mUnbinder;
    private View rootView;
    private LoadingUtils mLoading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(getLayoutId(),container,false);
            mUnbinder = ButterKnife.bind(this, rootView);
            initData();
        }else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getLinearLayout()!=null){
            StatusBar.init(getActivity(),getLinearLayout());
        }

    }

    protected abstract LinearLayout getLinearLayout();

    protected abstract void initPresenter();

    protected abstract void initData();

    public abstract int getLayoutId();

    public abstract void loadData();

    @Override
    protected void onFragmentFirstVisible() {
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
        rootView=null;
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
