package com.framework.app.adapter;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framework.app.R;
import com.framework.app.bean.Person;
import com.framework.app.pulltorefresh.BaseViewHolder;

public class PersonViewHolder extends BaseViewHolder<Person> {

    private TextView tv_title;
    private ImageView iv_news_image;
    private TextView tv_content;

    public PersonViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_news);
        iv_news_image = $(R.id.iv_news_image);
        tv_title = $(R.id.tv_title);
        tv_content = $(R.id.tv_content);
    }

    @Override
    public void setData(final Person person){
        Log.i("ViewHolder","position"+getDataPosition());
        tv_title.setText(person.getName());
        tv_content.setText(person.getSign());
        Glide.with(getContext())
                .load(person.getFace())
                .into(iv_news_image);
    }
}
