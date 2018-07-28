package com.yun.software.yunlearn.Thread.cn.itcast_04;

import java.util.Date;

public class ThreadSleep extends Thread {
	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			System.out.println(getName() + ":" + x + ",���ڣ�" + new Date());
			// ˯��
			// ���ˣ�����΢��Ϣ1����
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
