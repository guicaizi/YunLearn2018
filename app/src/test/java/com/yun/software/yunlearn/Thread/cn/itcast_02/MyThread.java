package com.yun.software.yunlearn.Thread.cn.itcast_02;

/*
 * ����Ҫ��дrun()����,Ϊʲô��?
 * �������е����д��붼��Ҫ���߳�ִ�еġ�
 * �����ʱ��Ϊ��������Щ�����ܹ����߳�ִ�У�java�ṩ��Thread���е�run()����������Щ���߳�ִ�еĴ��롣
 */
public class MyThread extends Thread {

	@Override
	public void run() {
		// �Լ�д����
		// System.out.println("�ú�ѧϰ����������");
		// һ����˵�����߳�ִ�еĴ���϶��ǱȽϺ�ʱ�ġ�����������ѭ���Ľ�
		for (int x = 0; x < 200; x++) {
			System.out.println(x);
		}
	}

}
