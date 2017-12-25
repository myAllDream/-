package com.framework.app.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.framework.app.R;

/**
 * Created by admin on 2017/12/25.
 */

public class LoadingUtils extends Dialog{
    private Context mContext;
    private TextView mesageTv;

    public LoadingUtils(@NonNull Context context) {
        this(context,R.style.progress_dialog);
    }

    public LoadingUtils(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext=context;
        initView();
    }

    private void initView() {
        View view= LayoutInflater.from(mContext).inflate(R.layout.loading_view,null);
        setContentView(view);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(true);
        mesageTv=view.findViewById(R.id.load_tv);
    }


    public void show(String message) {
        super.show();
        if(TextUtils.isEmpty(message)){
            mesageTv.setText("正在努力加载...");
        }else {
            mesageTv.setText(message);
        }
    }
}
