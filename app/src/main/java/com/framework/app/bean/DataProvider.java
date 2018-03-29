package com.framework.app.bean;
import android.graphics.Picture;
import com.framework.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class DataProvider {
    public static List<Person> getPersonList(int page){
        ArrayList<Person> arr = new ArrayList<>();
        if (page >= 2){
            return arr;
        }
        arr.add(new Person("这是条目：1"));
        arr.add(new Person("这是条目：2"));
        arr.add(new Person("这是条目：3"));
        arr.add(new Person("这是条目：4"));
        arr.add(new Person("这是条目：5"));
        arr.add(new Person("这是条目：6"));
        arr.add(new Person("这是条目：7"));
        arr.add(new Person("这是条目：8"));
        return arr;
    }

}
