package com.framework.app.utils;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.framework.app.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 自定义弹出对话框
 */

public class DialogUtils extends DialogFragment {

    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.message)
    TextView messageTv;
    @BindView(R.id.negative)
    TextView negativeTv;
    @BindView(R.id.positive)
    TextView positiveTv;
    private Unbinder mUnbinder;
    private Dialog dialog;

    private Builder mBuilder;
    public static DialogUtils mDialog;

    public static DialogUtils getInstance(Builder builder){
        mDialog=new DialogUtils();
        mDialog.mBuilder=builder;
        return mDialog;
    }

    public interface onClickButtonListener{
        void clickNegtive();
        void clickPositive();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dialog= getDialog();
        if (dialog == null) {
            return null;
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        //设置dialog动画
        dialog.getWindow().getAttributes().windowAnimations=R.style.CustomDialog;
        View view = inflater.inflate(R.layout.dialog_custom, container);
        mUnbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (dialog!=null){
            //更改弹出框的大小
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(TextUtils.isEmpty(mBuilder.title)){
            titleTv.setVisibility(View.INVISIBLE);
        }else {
            titleTv.setText(mBuilder.title);
        }
        if (mBuilder.isCancel){
            mDialog.setCancelable(true);
        }else {
            mDialog.setCancelable(false);
        }
        if(TextUtils.isEmpty(mBuilder.message)){
            messageTv.setVisibility(View.INVISIBLE);
        }else {
            messageTv.setText(mBuilder.message);
        }
        if(TextUtils.isEmpty(mBuilder.negtive)){
            negativeTv.setText("取消");
        }else {
            negativeTv.setText(mBuilder.negtive);
        }
        if(TextUtils.isEmpty(mBuilder.positive)){
            positiveTv.setText("确定");
        }else {
            positiveTv.setText(mBuilder.positive);
        }
    }

    public void showDialog(FragmentManager fragmentManager){
        if (mDialog!=null && !mDialog.isAdded() && !mDialog.isRemoving())
        mDialog.show(fragmentManager,DialogUtils.class.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mDialog=null;
    }

    @OnClick({R.id.negative, R.id.positive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.negative:
                mBuilder.mListener.clickNegtive();
                dismissAllowingStateLoss();
                break;
            case R.id.positive:
                mBuilder.mListener.clickPositive();
                dismissAllowingStateLoss();
                break;
        }
    }

    public static class Builder{
        private String title;
        private String message;
        private String negtive;
        private String positive;
        private boolean isCancel;
        private onClickButtonListener mListener;

        public Builder setTitle(String titie){
            this.title=titie;
            return this;
        }
        public Builder setMessage(String message){
            this.message=message;
            return this;
        }
        public Builder setNegtive(String negtive){
            this.negtive=negtive;
            return this;
        }
        public Builder setPositive(String positive){
            this.positive=positive;
            return this;
        }
        public Builder setIsCancel(boolean isCancel){
            this.isCancel=isCancel;
            return this;
        }
        public Builder setonClickButtonListener(onClickButtonListener mListener){
            this.mListener=mListener;
            return this;
        }
    }
}
