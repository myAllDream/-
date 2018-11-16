package com.framework.app.base;

import java.lang.ref.WeakReference;

public class BasePresenter<T> {
    //view的引用
    protected WeakReference<T> mViewRf;

    //进行绑定
    public void attachView(T view){
        mViewRf=new WeakReference<T>(view);
    }

    //解除绑定
    public void detachView(){
        mViewRf.clear();
    }

}
