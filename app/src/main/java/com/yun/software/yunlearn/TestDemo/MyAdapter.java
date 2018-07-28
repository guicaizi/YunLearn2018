package com.yun.software.yunlearn.TestDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;

import com.yun.software.yunlearn.MainActivity;
import com.yun.software.yunlearn.R;
import com.yun.software.yunlearn.Tools.MyAsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * desc: progressList的Adapter
 * author: dj
 * date: 2017/3/30 13:43
 */
public class MyAdapter extends BaseAdapter {
    private Context context;                        //上下文对象用于视图填充
    private List<UpdateProgress.MyObject> data;       //需要适配的数据源
    private List<View> viewList;                    //View对象集合

    public MyAdapter(Context context, List<UpdateProgress.MyObject> data) {
        this.viewList = new ArrayList<>();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        /* 初始化控件 */
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar);
            viewHolder.button = (Button) convertView.findViewById(R.id.btn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /* 添加控件样式 */
        final UpdateProgress.MyObject myObject = data.get(position);
        viewHolder.button.setText(myObject.text);
        if(position==3){
            viewHolder.progressBar.setProgress(80);
        }else{
            viewHolder.progressBar.setProgress(myObject.progress);
        }

        /* 设置按钮点击事件 */
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myObject.progress == -1) {
                    myObject.progress = 0;
                    //如果未开始下载，启动异步下载任务
                    MyAsyncTask asyncTask = new MyAsyncTask(viewList, position);
                    //添加THREAD_POOL_EXECUTOR可启动多个异步任务
                    asyncTask.executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR, myObject);
//                    asyncTask.cancel(true);
                }
            }
        });

        /* 标识View对象 */
        //将list_view的ID作为Tag的Key值
        convertView.setTag(R.id.progress_bar, position);//此处将位置信息作为标识传递
        viewList.add(convertView);

        return convertView;
    }

    /**
     * 用于缓存控件ID
     */
    public class ViewHolder {
        public ProgressBar progressBar;
        public Button button;
    }
}
