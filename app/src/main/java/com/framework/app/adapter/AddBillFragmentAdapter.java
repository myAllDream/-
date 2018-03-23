package com.framework.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.framework.app.fragment.AddBillFragment;


/**
 * Created by admin on 2018/2/26.
 */

public class AddBillFragmentAdapter extends FragmentPagerAdapter {
    String[] title = {"浙江", "上海", "北京", "深圳"};

    public AddBillFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return AddBillFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
