package com.framework.app.contract;


import com.framework.app.base.BaseView;
import com.framework.app.bean.PlatformBean;

/**
 * Created by admin on 2018/3/1.
 */

public interface AddBillFragmentContract {
    interface View extends BaseView {
        void getPlatformsSuccess(PlatformBean platformBean);
    }
    interface Presenter{
        void getPlatforms(String cityCode);
    }
}
