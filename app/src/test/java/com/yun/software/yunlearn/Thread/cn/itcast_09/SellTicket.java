package com.yun.software.yunlearn.Thread.cn.itcast_09;

public class SellTicket implements Runnable {
	// ����100��Ʊ
	private int tickets = 100;
	//����������
	private Object obj = new Object();

//	@Override
//	public void run() {
//		while (true) {
//			synchronized(new Object()){
//				if (tickets > 0) {
//					try {
//						Thread.sleep(100); 
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName() + "���ڳ��۵�"
//							+ (tickets--) + "��Ʊ");
//				}
//			}
//		}
//	}
	
	@Override
	public void run() {
		while (true) {
			synchronized (obj) {
				if (tickets > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()
							+ "���ڳ��۵�" + (tickets--) + "��Ʊ");
				}
			}
		}
	}
}
