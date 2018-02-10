package com.yun.software.yunlearn.MVPLear.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanliang
 * on 2018/2/7 11:24
 */

public class MyLoding implements BaseLoding {
    @Override
    public void load(LodingLisener lisener) {
        List<String> str=new ArrayList<>();
        str.add("hello");
        str.add("hello");
        str.add("hello");
        str.add("hello");
        str.add("hello");
        str.add("hello");
        str.add("hello");
        str.add("hello");
        str.add("hello");
        str.add("hello");
        lisener.complate(str);
    }
}
