package com.framework.app.config;

import android.support.annotation.StringDef;

/**
 * Created by admin on 2017/12/19.
 */

public interface Parms {

    /**
     * fragment的别名
     */
    @StringDef(value = {FragmentTag.HOME_FRAGMENT,FragmentTag.MY_FRAGMENT})
    @interface FragmentTag{
        String HOME_FRAGMENT="HOME";
        String FINANCE_FRAGMENT="FINANCE";
        String ACCOUNT_FRAGMENT="ACCOUNT";
        String MY_FRAGMENT="MY";
    }

}
