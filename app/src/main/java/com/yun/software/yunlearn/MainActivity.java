package com.yun.software.yunlearn;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.TestDemo.ActivityScanerCode;
import com.yun.software.yunlearn.TestDemo.BigNumberDemo;
import com.yun.software.yunlearn.TestDemo.BlueDemo;
import com.yun.software.yunlearn.TestDemo.BlueDemo2;
import com.yun.software.yunlearn.TestDemo.JavaReflect.TestGetSuperClass;
import com.yun.software.yunlearn.TestDemo.PDFRead;
import com.yun.software.yunlearn.TestDemo.RxJavaDemo.RxJavaTextDemo;
import com.yun.software.yunlearn.TestDemo.ScheduledExecutorServiceTest;
import com.yun.software.yunlearn.TestDemo.SystemUIChangeTest;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.toolbar_title)
    TextView mTitleTextView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.listView)
     ListView mListView;
    public static final String[] datas = new String[]{
            "下载",
            "权限使用","大数运算","查找蓝牙","查找蓝牙2","ScheduledExeCuttorServiceDemo",
            "SystemUIChangeTest","二维码扫描","反射测试","RxJava测试","pdf阅读器"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setData() {

        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        mTitleTextView = (TextView) this.findViewById(R.id.toolbar_title);
        mTitleTextView.setText("学习指南");

        mListView.setAdapter(new MainAdapter());
    }

    @Override
    public void addLisener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doClick(position);
            }
        });
    }
    private void doClick(int position) {
        switch (position) {
            case 0:
                enterPage(DownLoadActivity.class);
                break;
            case 1:
                enterPage(ManagerPermission.class);
                break;
            case 2:
                enterPage(BigNumberDemo.class);
                break;
            case 3:
                enterPage(BlueDemo.class);
                break;
            case 4:
                enterPage(BlueDemo2.class);
                break;
            case 5:
                enterPage(ScheduledExecutorServiceTest.class);
                break;
            case 6:
                enterPage(SystemUIChangeTest.class);
                break;
            case 7:
                enterPage(ActivityScanerCode.class);
                break;
            case 8:
                enterPage(TestGetSuperClass.class);
                break;
            case 9:
                enterPage(RxJavaTextDemo.class);
                break;
            case 10:
                enterPage(PDFRead.class);
                break;
        }
    }

    public class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public Object getItem(int position) {
            return datas[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder;
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                View mView = MainActivity.this.getLayoutInflater().inflate(R.layout.listview_main, parent, false);
                mViewHolder.mTextView = (TextView) mView.findViewById(R.id.content);
                mView.setTag(mViewHolder);
                convertView = mView;
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }

            mViewHolder.mTextView.setText(datas[position]);
            return convertView;
        }


    }

    class ViewHolder {
        TextView mTextView;
    }


    @Override
    public Activity getActivity() {
        return this;
    }

}
