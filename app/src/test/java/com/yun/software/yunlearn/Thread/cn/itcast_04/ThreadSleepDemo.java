package com.yun.software.yunlearn.Thread.cn.itcast_04;

/*
 * �߳�����
 *		public static void sleep(long millis)
 */
public class ThreadSleepDemo {
	public static void main(String[] args) {
		ThreadSleep ts1 = new ThreadSleep();
		ThreadSleep ts2 = new ThreadSleep();
		ThreadSleep ts3 = new ThreadSleep();

		ts1.setName("����ϼ");
		ts2.setName("��־��");
		ts3.setName("��־ӱ");

		ts1.start();
		ts2.start();
		ts3.start();
	}
}
