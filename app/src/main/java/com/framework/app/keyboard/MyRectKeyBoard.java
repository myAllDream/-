package com.framework.app.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.utils.DisplayUtil;
import com.framework.app.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 方格的密码输入框
 * Created by admin on 2018/4/3.
 */

public class MyRectKeyBoard extends GridLayout implements View.OnClickListener, View.OnTouchListener {

    public static final String DEL = "删除";

    public static final String DONE = "OK";
    //因为UED是给的是iPhone设计稿,所以是按照等比的思想设置键盘Key的高度和宽度
    private static final int IPHONE = 779;
    //每个键盘Key的宽度,为屏幕宽度的三分之一
    private int keyWidth = (((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth()-DisplayUtil.dip2px(getContext(),20)-60) / 3;
    //每个键盘Key的高度
    private int keyHeight = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight() * 59 / IPHONE;

    private int screenWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

    private Paint mPaint;
    private Paint linPaint;
    //List集合存储Key,方便每次输错都能再次随机数字键盘
    private final List<View> keyButtons = new ArrayList<>();

    private WorkHandler mWorkHandler;

    private static final int DELETE = 1;
    //WorkHandler 用于处理长按"删除"Key时,执行重复删除操作。
    private static class WorkHandler extends Handler {

        private int index = 0;

        int diffTime = 100;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELETE:
                    MyRectKeyBoard numberKeyBoard = (MyRectKeyBoard) msg.obj;
                    numberKeyBoard.handlerClick(DEL);
                    removeMessages(DELETE);
                    Message message = obtainMessage(DELETE);
                    message.obj = numberKeyBoard;
                    if (diffTime > 40) {
                        diffTime = diffTime - index;
                    }
                    sendMessageDelayed(message, diffTime);
                    index++;
                    break;
            }
        }

        public void reset() {
            index = 0;
            diffTime = 100;
        }
    }

    public MyRectKeyBoard(Context context) {
        super(context);
        initView();
    }

    public MyRectKeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyRectKeyBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        setMeasuredDimension(screenWidth, keyHeight * 4+80);
    }

    //重新设置键盘key位置
    public void resetKeyboard() {
        List<String> keyList = randomKeys(10);
        for (int i = 0; i < keyList.size(); i++) {
            if(keyButtons.get(i) instanceof TextView){
                ((TextView) keyButtons.get(i)).setText(keyList.get(i));
                ((TextView) keyButtons.get(i)).setTag(keyList.get(i));
            }

        }
    }

    private void initView() {
        //必须设置调用该方法,不然onDraw方法不执行。如果ViewGroup没有背景,则其onDraw方法不执行
        setWillNotDraw(false);
        if (getChildCount() > 0) {
            keyButtons.clear();
            removeAllViews();
        }

        //获取随机键盘数字的字符串
        List<String> keyList = randomKeys(10);
        L.i("-------"+keyList.size());
        //填充键盘Key,用Button来完成Key功能
        for (int i = 0; i < keyList.size(); i++) {

            if(DEL.equals(keyList.get(i))){
                ImageView imageView=new ImageView(getContext());
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(keyWidth, keyHeight);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setImageResource(R.mipmap.detele_icon);

                GridLayout.LayoutParams gl = new GridLayout.LayoutParams(params);
                gl.rightMargin = DisplayUtil.px2dip(getContext(), 20);
                gl.topMargin = DisplayUtil.px2dip(getContext(), 20);
                gl.leftMargin = DisplayUtil.px2dip(getContext(), 20);
                gl.bottomMargin = DisplayUtil.px2dip(getContext(), 20);
                imageView.setLayoutParams(gl);

                //监听"删除"的长按监听事件,完成重复删除操作
                if (DEL.equals(keyList.get(i))) {
                    imageView.setOnTouchListener(this);
                    //item.setBackgroundColor(Color.parseColor("#dcdcdc"));
                }
                imageView.setTag(keyList.get(i));
                addView(imageView);
                keyButtons.add(imageView);
            }else{
                TextView item = new TextView(getContext());
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(keyWidth, keyHeight);
                item.setLayoutParams(params);
                item.setOnClickListener(this);
                item.setGravity(Gravity.CENTER);
                item.setText(keyList.get(i));
                item.setTextSize(18);



                GridLayout.LayoutParams gl = new GridLayout.LayoutParams(params);
                gl.rightMargin = DisplayUtil.px2dip(getContext(), 20);
                gl.topMargin = DisplayUtil.px2dip(getContext(), 20);
                gl.leftMargin = DisplayUtil.px2dip(getContext(), 20);
                gl.bottomMargin = DisplayUtil.px2dip(getContext(), 20);
                item.setLayoutParams(gl);

                if(TextUtils.isEmpty(keyList.get(i))){
                    item.setBackgroundColor(Color.TRANSPARENT);
                }else {
                    item.setBackgroundResource(R.drawable.key_board_selector);
                }
                //监听"删除"的长按监听事件,完成重复删除操作
                /*if (DEL.equals(keyList.get(i))) {
                    item.setOnTouchListener(this);
                    //item.setBackgroundColor(Color.parseColor("#dcdcdc"));
                }*/
                item.setTag(keyList.get(i));
                addView(item);
                keyButtons.add(item);
            }

            //item.setBackgroundDrawable(getResources().getDrawable(R.drawable.key_selector));


        }
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(Color.parseColor("#3a3a3a"));
            mPaint.setStrokeWidth(3);
        }
        if(linPaint==null){
            linPaint=new Paint();
            linPaint.setColor(Color.parseColor("#cccccc"));
            linPaint.setStrokeWidth(1);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制分割线
        /*canvas.drawLine(0, 0, getMeasuredWidth(), 0, linPaint);
        canvas.drawLine(0, getMeasuredHeight() / 4, getMeasuredWidth(), getMeasuredHeight() / 4, linPaint);
        canvas.drawLine(0, 2 * getMeasuredHeight() / 4, getMeasuredWidth(), 2 * getMeasuredHeight() / 4, linPaint);
        canvas.drawLine(0, 3 * getMeasuredHeight() / 4, getMeasuredWidth(), 3 * getMeasuredHeight() / 4, linPaint);
        canvas.drawLine(getMeasuredWidth() / 3, 0, getMeasuredWidth() / 3, getMeasuredHeight(), linPaint);
        canvas.drawLine(2 * getMeasuredWidth() / 3, 0, 2 * getMeasuredWidth() / 3, getMeasuredHeight(), linPaint);
    */}

    @Override
    public void onClick(View v) {
        String character = v.getTag().toString();
        L.i("-------"+character);
        handlerClick(character);
    }

    private void handlerClick(String character) {
        //密码字符输出回调
        if (mListener != null) {
            if (DONE.equals(character)) {
                mListener.onInput(DONE);
            } else if (DEL.equals(character)) {
                mListener.onInput(DEL);
            } else {
                mListener.onInput(character);
            }
        }
    }

    //生产键盘Key随机数字
    private List<String> randomKeys(int no) {
        int[] keys = new int[no];
        for (int i = 0; i < no; i++) {
            keys[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < no; i++) {
            int p = random.nextInt(no);
            int tmp = keys[i];
            keys[i] = keys[p];
            keys[p] = tmp;
        }
        List<String> keyList = new ArrayList<>();
        for (int key : keys) {
            keyList.add(String.valueOf(key));
        }
        //将空字符串插入到第10个位置,是个无操作的Key
        keyList.add(9, "");
        //将删除字符串插入最后
        keyList.add(DEL);
        return keyList;
    }

    public void setOnPasswordInputListener(MyRectKeyBoard.OnPasswordInputListener listener) {
        this.mListener = listener;
    }

    private MyRectKeyBoard.OnPasswordInputListener mListener;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mWorkHandler == null) {
            mWorkHandler = new MyRectKeyBoard.WorkHandler();
        }
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            Message msg = mWorkHandler.obtainMessage(DELETE);
            msg.obj = this;
            mWorkHandler.sendMessage(msg);
            //mWorkHandler.sendMessageDelayed(msg, 500);
        } else if (MotionEvent.ACTION_UP == event.getAction()) {
            mWorkHandler.removeMessages(DELETE);
            mWorkHandler.reset();
        } else if (MotionEvent.ACTION_CANCEL == event.getAction()) {
            mWorkHandler.removeMessages(DELETE);
            mWorkHandler.reset();
        } else if (MotionEvent.ACTION_MOVE == event.getAction()) {

        } else {
            //do nothing
        }
        return true;
    }

    public interface OnPasswordInputListener {
        void onInput(String number);
    }
}
