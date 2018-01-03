package com.framework.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.framework.app.R;

public class WelcomeActivity extends AppCompatActivity {
    private TextView timeTv;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        timeTv=findViewById(R.id.time);

        timer=new Timer(2*1000,1000);
        timer.start();
    }

    class Timer extends CountDownTimer{
        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timeTv.setText("还剩"+(millisUntilFinished/1000-1)+"s");
        }

        @Override
        public void onFinish() {
            goToMain();
        }
    }

    private void goToMain() {
        Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.animation_enter,R.anim.animation_exit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
        }
    }
}
