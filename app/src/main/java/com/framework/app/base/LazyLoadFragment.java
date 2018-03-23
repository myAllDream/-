package com.framework.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.framework.app.utils.LogUtil;

/**
 * Created by admin on 2018/3/23.
 */

public abstract class LazyLoadFragment extends Fragment{
    private boolean isFirstEnter = true;//是否是第一次进入,默认是
    private boolean isReuseView = true ;//是否进行复用，默认复用
    private boolean isFragmentVisible;
    private View rootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(rootView==null){
            return;
        }
        if(isFirstEnter && isVisibleToUser){
            //如果是第一次进入并且可见
            onFragmentFirstVisible();
            isFirstEnter=false;
        }
        if(isVisibleToUser){
            //如果不是第一次进入但是可见
            isFragmentVisible=true;
            onFragmentVisibleChange(isFragmentVisible);
            return;
        }
        if(isFragmentVisible){
            //如果当前fragment不可见且标识为true
            isFragmentVisible = false;//更改标识
            onFragmentVisibleChange(isFragmentVisible);//回调当前fragment不可见
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=view;
            if(getUserVisibleHint()){
                if(isFirstEnter){
                    onFragmentFirstVisible();
                    isFirstEnter=false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible=true;
            }
        }
        super.onViewCreated(isReuseView?rootView:view, savedInstanceState);
    }

    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }
    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {

    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    /**重置变量*/
    private void resetVariavle(){
        isFirstEnter = true;
        isReuseView = true;
        isFragmentVisible = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resetVariavle();
    }
}
