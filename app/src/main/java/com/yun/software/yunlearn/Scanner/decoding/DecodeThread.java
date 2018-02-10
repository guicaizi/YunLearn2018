package com.yun.software.yunlearn.Scanner.decoding;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.yun.software.yunlearn.TestDemo.ActivityScanerCode;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 描述: 解码线程
 */
final class DecodeThread extends Thread {

    private final CountDownLatch handlerInitLatch;
    ActivityScanerCode activity;
    private Handler handler;

	DecodeThread(ActivityScanerCode activity) {
		this.activity = activity;
		handlerInitLatch = new CountDownLatch(1);
	}

	Handler getHandler() {
		try {
			Log.i("http","怎么回事");
			handlerInitLatch.await();
			Log.i("http","解决了");
		} catch (InterruptedException ie) {
			// continue?
		}
		return handler;
	}

	@Override
	public void run() {
		Looper.prepare();
		handler = new DecodeHandler(activity);
		Log.i("http","我在处理");
		handlerInitLatch.countDown();
		Looper.loop();
	}

}
