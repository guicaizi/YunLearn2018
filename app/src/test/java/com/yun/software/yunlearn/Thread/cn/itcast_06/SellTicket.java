package com.yun.software.yunlearn.Thread.cn.itcast_06;

public class SellTicket extends Thread {

	// ����100��Ʊ
	// private int tickets = 100;
	// Ϊ���ö���̶߳�������100��Ʊ��������ʵӦ���þ�̬����
	private static int tickets = 100;

	@Override
	public void run() {
		// ����100��Ʊ
		// ÿ���߳̽�����������������Ļ���ÿ���̶߳����൱��������Լ�����100��Ʊ���ⲻ��������Ӧ�ö��嵽����
		// int tickets = 100;

		// ��Ϊ��ģ��һֱ��Ʊ
		while (true) {
			if (tickets > 0) {
				System.out.println(getName() + "���ڳ��۵�" + (tickets--) + "��Ʊ");
			}
		}
	}
}
