package com.yun.software.yunlearn.Thread.cn.itcast_04;

public class ThreadPriority extends Thread {
	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			System.out.println(getName() + ":" + x);
		}
	}
}