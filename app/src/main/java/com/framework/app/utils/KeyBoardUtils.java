package com.framework.app.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by admin on 2017/12/20.
 */

public class KeyBoardUtils {

    /**
     * 关闭软键盘
     * @param activity
     */
    public void closeKeyBoard(Activity activity){
        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View focus = activity.getCurrentFocus();
        if(manager!=null && focus!=null){
            manager.hideSoftInputFromWindow(focus.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
