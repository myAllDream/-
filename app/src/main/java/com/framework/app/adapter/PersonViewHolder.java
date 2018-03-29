package com.framework.app.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.framework.app.R;
import com.framework.app.bean.Person;
import com.framework.app.pulltorefresh.BaseViewHolder;

public class PersonViewHolder extends BaseViewHolder<Person> {

    private TextView tv_title;

    public PersonViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_news);
        tv_title = $(R.id.tv_recy);
    }

    @Override
    public void setData(final Person person){
        tv_title.setText(person.getName());
    }
}
