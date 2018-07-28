package com.yun.software.yunlearn.Thread.cn.itcast_04;

import org.junit.Test;

/*
 * public final void stop():让线程停止，过时了，但是还可以使用。
 * public void interrupt():中断线程。 把线程的状态终止，并抛出一个InterruptedException。
 */
public class ThreadStopDemo {
	 @Test
	 	public void test01() {
		ThreadStop ts = new ThreadStop();
		ts.start();

		// 你超过三秒不醒过来，我就干死你
		try {
			Thread.sleep(3000);
			// ts.stop();
			ts.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
