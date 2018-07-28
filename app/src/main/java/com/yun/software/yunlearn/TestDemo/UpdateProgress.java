package com.yun.software.yunlearn.TestDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2018/5/18 13:50
 */

public class UpdateProgress extends BaseActivity {
    @Bind(R.id.left_listview)
    ListView mListView;
    public List<MyObject> mData;       //数据源（模拟）
    private MyAdapter mAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_update_progress;
    }

    @Override
    public void setData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //组装数据
            MyObject myObject = new MyObject();
            myObject.text = "按钮" + i;
            myObject.progress = -1;
            //添加到数据源
            mData.add(myObject);
        }
        /* 填充适配器 */
        mAdapter = new MyAdapter(this, mData);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return null;
    }
    public class MyObject {
        public Integer progress;       //下载进度
        public String text;            //按钮文字
    }
}
