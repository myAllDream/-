package com.framework.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.framework.app.utils.LogUtil;

/**
 * Created by admin on 2018/3/21.
 */

public class MyScrollView extends ScrollView {

    private ScrollListener mListener;

    public void setScrollListener(ScrollListener listener) {
        this.mListener = listener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mListener != null) {
                    int contentHeight = getChildAt(0).getHeight();
                    int scrollHeight = getHeight();
                    int scrollY = getScrollY();
                    mListener.onScroll(scrollY);
                    if (scrollY + scrollHeight >= contentHeight || contentHeight <= scrollHeight) {
                        mListener.onScrollToBottom();
                    } else {
                        mListener.notBottom();
                    }
                    if (scrollY == 0) {
                        mListener.onScrollToTop();
                    }
                }
                break;
        }

        boolean result = super.onTouchEvent(ev);
        requestDisallowInterceptTouchEvent(false);
        return result;
    }

    public interface ScrollListener {
        void onScrollToBottom();

        void onScrollToTop();

        void onScroll(int scrollY);

        void notBottom();
    }
}
