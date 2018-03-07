package com.yun.software.yunlearn.ThreadPool;

import com.yun.software.corelib.Tools.MyLogUtils;

/**
 * Created by yanliang
 * on 2018/2/10 17:05
 */

public class ThreadForpools implements Runnable{

    private Integer index;
    public  ThreadForpools(Integer index)
    {
        this.index=index;
    }
    @Override
    public void run() {
        /***
         * 业务......省略
         */
        try {
            MyLogUtils.i("threadtest","开始测试"+index);
            Thread.sleep(index*1000);
            MyLogUtils.i("threadtest","我的线程标识是："+this.toString()+"第几个"+index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}