package com.framework.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.framework.app.utils.StatusBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 懒加载fragment
 */

public abstract class BaseLazyFragment<V, T extends BasePresenter<V>> extends LazyLoadFragment {
    private Unbinder mUnbinder;
    private View rootView;
    protected T mPresenter;
    public CompositeDisposable mDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }

        mPresenter = creatPresenter();
        mPresenter.attachView((V) this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            mUnbinder = ButterKnife.bind(this, rootView);
            initData();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    protected abstract T creatPresenter();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getLinearLayout() != null) {
            StatusBar.init(getActivity(), getLinearLayout());
        }

    }

    protected abstract LinearLayout getLinearLayout();

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
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        mPresenter=null;
        rootView = null;
    }


    public BaseView getBaseView() {
        if (mPresenter!=null && mPresenter.mViewRf!=null){
            return (BaseView) mPresenter.mViewRf.get();
        }else {
            return null;
        }
    }

    public void addDisposed(Disposable disposable) {
        mDisposable.add(disposable);
    }
}
