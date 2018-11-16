package com.framework.app.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.framework.app.R;
import com.framework.app.adapter.SimpleFragmentPagerAdapter;
import com.framework.app.base.BaseActivity;
import com.framework.app.base.BasePresenter;
import com.framework.app.base.BaseView;
import com.framework.app.fragment.OneFragment;
import com.framework.app.fragment.TwoFragment;

import java.util.ArrayList;

public class ProductDetailActivity extends BaseActivity<BaseView,BasePresenter<BaseView>> {

    private ArrayList<Fragment> list_fragment = new ArrayList<>();                                //定义要装fragment的列表
    private ArrayList<String> list_title = new ArrayList<>();                                //定义要装fragment的列表
    private OneFragment mOneFragment;              //热门推荐fragment
    private TwoFragment mTwoFragment;            //热门收藏fragment


    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    public BasePresenter<BaseView> creatPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initData() {
//初始化各fragment
        mOneFragment = new OneFragment();
        mTwoFragment = new TwoFragment();

        //将fragment装进列表中

        list_fragment.add(mOneFragment);
        list_fragment.add(mTwoFragment);

        list_title.add("第一个页面");
        list_title.add("第二个页面");


        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, list_fragment, list_title);
//        viewPager = (ChildViewPager) findViewById(R.id.viewpager);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected LinearLayout getTopView() {
        return null;
    }
}
