package com.yun.software.yunlearn.Tools;

import android.os.AsyncTask;
import android.view.View;


import com.yun.software.yunlearn.R;
import com.yun.software.yunlearn.TestDemo.MyAdapter;
import com.yun.software.yunlearn.TestDemo.UpdateProgress;

import java.util.List;

/**
 * desc: 异步请求
 * author: dj
 * date: 2017/3/30 13:44
 */

public class MyAsyncTask extends AsyncTask<UpdateProgress.MyObject, Integer, Void> {
    private UpdateProgress.MyObject myObject;     //单个数据，用于完成后的处理
    private List<View> viewList;                //视图对象集合，用于设置样式
    private Integer viewId;                     //视图标识，用于匹配视图对象

    public
    MyAsyncTask(List<View> viewList, Integer viewId) {
        this.viewList = viewList;
        this.viewId = viewId;
    }

    @Override
    protected Void doInBackground(UpdateProgress.MyObject... params) {
        myObject = params[0];
        /* 模拟下载任务 */
        for (int i = 0; i < 100; i++) {
            //发布进度
            publishProgress(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        View view = null;
        /* 匹配视图对象 */
        for (int i = 0; i < viewList.size(); i++) {
            if (viewList.get(i).getTag(R.id.progress_bar) == viewId) {
                //检查所有视图ID，如果ID匹配则取出该对象
                view = viewList.get(i);
                break;
            }
        }
        if (view != null) {
            //将视图对象中缓存的ViewHolder对象取出，并使用该对象对控件进行更新
            MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder) view.getTag();
            viewHolder.progressBar.setProgress(values[0]);
        }
        myObject.progress = values[0];
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //更新数据源信息
        myObject.progress = 100;
        myObject.text = "完成";

    }
}

