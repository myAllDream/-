package com.framework.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framework.app.R;
import com.framework.app.bean.PlatformBean;
import com.framework.app.utils.ToastUtils;

import java.util.List;

/**
 * Created by admin on 2018/2/26.
 */

public class AddBillRecycleAdapter extends RecyclerView.Adapter<AddBillRecycleAdapter.MyHolder> {

    private Context context;
    private List<PlatformBean.DataBean> data;

    public AddBillRecycleAdapter(Context context, List<PlatformBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_bill_recycle_adapter, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        Glide.with(context).load(data.get(position).getProfile_image()).into(holder.billLogo);
        holder.billTv.setText(data.get(position).getText());

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView billLogo;
        TextView billTv;

        public MyHolder(View itemView) {
            super(itemView);
            billLogo = itemView.findViewById(R.id.bill_logo);
            billTv = itemView.findViewById(R.id.bill_name);
        }
    }
}
