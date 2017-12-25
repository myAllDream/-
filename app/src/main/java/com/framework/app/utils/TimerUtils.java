package com.framework.app.utils;

import android.os.CountDownTimer;

/**
 * Created by admin on 2017/12/21.
 */

public class TimerUtils extends CountDownTimer{
    private long time;

    public TimerUtils(long time,long delay){
        super(time,delay);
        this.time=time;
    }


    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }
}
