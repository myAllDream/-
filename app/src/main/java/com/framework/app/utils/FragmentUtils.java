package com.framework.app.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.framework.app.R;
import com.framework.app.config.Parms;
import com.framework.app.fragment.AccountFragment;
import com.framework.app.fragment.FinanceFragment;
import com.framework.app.fragment.HomeFragment;
import com.framework.app.fragment.MySelfFragment;

/**
 * fragment工具类
 */

public class FragmentUtils {

    private Fragment lastFragment = null;

    public void showFragment(String tag, FragmentManager fragmentManager) {
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
        if (fragmentByTag == null) {
            fragmentByTag = creatFragmentByTag(tag);
            addFragment(fragmentByTag, tag, fragmentManager);
        } else {
            showMyFragment(fragmentByTag, fragmentManager);
        }
        lastFragment = fragmentByTag;
    }

    private void showMyFragment(Fragment fragmentByTag, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (lastFragment != null) {
            fragmentTransaction.hide(lastFragment);
        }
        fragmentTransaction.show(fragmentByTag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void addFragment(Fragment fragment, String tag, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }
        transaction.add(R.id.frame, fragment, tag);
        transaction.commitAllowingStateLoss();
    }


    private Fragment creatFragmentByTag(String tag) {
        switch (tag) {
            case Parms.FragmentTag.HOME_FRAGMENT:
                return HomeFragment.getInStance();
            case Parms.FragmentTag.FINANCE_FRAGMENT:
                return FinanceFragment.getInStance();
            case Parms.FragmentTag.ACCOUNT_FRAGMENT:
                return AccountFragment.getInStance();
            case Parms.FragmentTag.MY_FRAGMENT:
                return MySelfFragment.getInStance();
        }
        return HomeFragment.getInStance();
    }
}
