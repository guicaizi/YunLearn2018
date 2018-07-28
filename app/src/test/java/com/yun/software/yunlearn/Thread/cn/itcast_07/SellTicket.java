package com.yun.software.yunlearn.Thread.cn.itcast_07;

public class SellTicket implements Runnable {
	// ����100��Ʊ
	private int tickets = 100;

	@Override
	public void run() {
		while (true) {
			if (tickets > 0) {
				System.out.println(Thread.currentThread().getName() + "���ڳ��۵�"
						+ (tickets--) + "��Ʊ");
			}
		}
	}
}
