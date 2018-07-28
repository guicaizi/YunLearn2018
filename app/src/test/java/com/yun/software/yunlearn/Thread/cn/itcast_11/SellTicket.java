package com.yun.software.yunlearn.Thread.cn.itcast_11;

public class SellTicket implements Runnable {

	// ����100��Ʊ
	private static int tickets = 100;

	// ����ͬһ����
	private Object obj = new Object();
	private Demo d = new Demo();

	private int x = 0;
	
	//ͬ���������obj����
//	@Override
//	public void run() {
//		while (true) {
//			synchronized (obj) {
//				if (tickets > 0) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()
//							+ "���ڳ��۵�" + (tickets--) + "��Ʊ ");
//				}
//			}
//		}
//	}
	
	//ͬ��������������������
//	@Override
//	public void run() {
//		while (true) {
//			synchronized (d) {
//				if (tickets > 0) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()
//							+ "���ڳ��۵�" + (tickets--) + "��Ʊ ");
//				}
//			}
//		}
//	}
	
	@Override
	public void run() {
		while (true) {
			if(x%2==0){
				synchronized (SellTicket.class) {
					if (tickets > 0) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()
								+ "���ڳ��۵�" + (tickets--) + "��Ʊ ");
					}
				}
			}else {
//				synchronized (d) {
//					if (tickets > 0) {
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						System.out.println(Thread.currentThread().getName()
//								+ "���ڳ��۵�" + (tickets--) + "��Ʊ ");
//					}
//				}
				
				sellTicket();
				
			}
			x++;
		}
	}

//	private void sellTicket() {
//		synchronized (d) {
//			if (tickets > 0) {
//			try {
//					Thread.sleep(100);
//			} catch (InterruptedException e) {
//					e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName()
//						+ "���ڳ��۵�" + (tickets--) + "��Ʊ ");
//			}
//		}
//	}
	
	//���һ������һ��ȥ�Ϳ����˴��뱻ͬ���ˣ���ô�Ҿ������ܲ��ܰ����ͬ�����ڷ�������?
//	 private synchronized void sellTicket() {
//			if (tickets > 0) {
//			try {
//					Thread.sleep(100);
//			} catch (InterruptedException e) {
//					e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName()
//						+ "���ڳ��۵�" + (tickets--) + "��Ʊ ");
//			}
//	}
	
	private static synchronized void sellTicket() {
		if (tickets > 0) {
		try {
				Thread.sleep(100);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()
					+ "���ڳ��۵�" + (tickets--) + "��Ʊ ");
		}
}
}

class Demo {
}
