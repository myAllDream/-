package com.framework.app.presenter;

import com.framework.app.R;
import com.framework.app.base.BasePresenter;
import com.framework.app.base.BaseView;
import com.framework.app.contract.HomeFragMentContract;
import com.framework.app.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/3.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragMentContract>{


    public HomeFragmentPresenter() {

    }

    public void startBanner() {
        Banner banner= mViewRf.get().getBanner();

        List<Integer> images=new ArrayList<>();
        images.add(R.mipmap.delete_home);
        images.add(R.mipmap.delete_home);
        images.add(R.mipmap.delete_home);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles("");
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }
}
