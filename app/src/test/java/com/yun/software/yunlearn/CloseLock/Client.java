package com.yun.software.yunlearn.CloseLock;

import org.junit.Test;

/**
 * Created by yanliang
 * on 2018/3/27 09:50
 */

public class Client {
    @Test
    public void main(){

        LockThread lockThread=new LockThread();
        lockThread.setFlag(0);
        Thread Athread=new Thread(lockThread);
        Athread.start();




//        lockThread.setFlag(1);
//        Thread Bthread=new Thread(lockThread);
//        Bthread.start();





    }
}
