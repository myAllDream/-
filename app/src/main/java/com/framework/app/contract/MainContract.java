package com.framework.app.contract;

import android.support.v4.app.FragmentManager;

/**
 * Created by admin on 2017/12/20.
 */

public interface MainContract {
    interface View{

    }

    interface Presenter{
        void changeFragment(String tag, FragmentManager fragmentManager);
    }

}
