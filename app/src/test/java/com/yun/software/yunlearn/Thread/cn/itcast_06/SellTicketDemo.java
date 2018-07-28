package com.yun.software.yunlearn.Thread.cn.itcast_06;

/*
 * ĳ��ӰԺĿǰ������ӳ�����Ƭ(�����,�����´���ؾ���)������100��Ʊ��������3����Ʊ������Ʊ�������һ������ģ��õ�ӰԺ��Ʊ��
 * �̳�Thread����ʵ�֡�
 */
public class SellTicketDemo {
	public static void main(String[] args) {
		// ���������̶߳���
		SellTicket st1 = new SellTicket();
		SellTicket st2 = new SellTicket();
		SellTicket st3 = new SellTicket();

		// ���̶߳���������
		st1.setName("����1");
		st2.setName("����2");
		st3.setName("����3");

		// �����߳�
		st1.start();
		st2.start();
		st3.start();
	}
}