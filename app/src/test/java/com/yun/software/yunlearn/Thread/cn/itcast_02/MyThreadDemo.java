package com.yun.software.yunlearn.Thread.cn.itcast_02;

/*
 * ��������Ҫʵ�ֶ��̵߳ĳ���
 * ���ʵ����?
 * 		�����߳����������̶����ڵģ���������Ӧ���ȴ���һ�����̳�����
 * 		����������ϵͳ�����ģ���������Ӧ��ȥ����ϵͳ���ܴ���һ�����̡�
 * 		Java�ǲ���ֱ�ӵ���ϵͳ���ܵģ����ԣ�����û�а취ֱ��ʵ�ֶ��̳߳���
 * 		������?Java����ȥ����C/C++д�õĳ�����ʵ�ֶ��̳߳���
 * 		��C/C++ȥ����ϵͳ���ܴ������̣�Ȼ����Javaȥ���������Ķ�����
 * 		Ȼ���ṩһЩ�๩����ʹ�á����ǾͿ���ʵ�ֶ��̳߳����ˡ�
 * ��ôJava�ṩ������ʲô��?
 * 		Thread
 * 		ͨ���鿴API������֪������2�з�ʽʵ�ֶ��̳߳���
 * 
 * ��ʽ1���̳�Thread�ࡣ
 * ����
 * 		A:�Զ�����MyThread�̳�Thread�ࡣ
 * 		B:MyThread��������дrun()?
 * 			Ϊʲô��run()������?
 * 		C:��������
 * 		D:�����߳�
 */
public class MyThreadDemo {
	public static void main(String[] args) {
		// �����̶߳���
		// MyThread my = new MyThread();
		// // �����߳�
		// my.run();
		// my.run();
		// ����run()����Ϊʲô�ǵ��̵߳���?
		// ��Ϊrun()����ֱ�ӵ�����ʵ���൱����ͨ�ķ�������,�����㿴�����ǵ��̵߳�Ч��
		// Ҫ�뿴�����̵߳�Ч�����ͱ���˵˵��һ��������start()
		// �����⣺run()��start()������?
		// run():�����Ƿ�װ���߳�ִ�еĴ��룬ֱ�ӵ�������ͨ����
		// start():�����������̣߳�Ȼ������jvmȥ���ø��̵߳�run()������
		// MyThread my = new MyThread();
		// my.start();
		// // IllegalThreadStateException:�Ƿ����߳�״̬�쳣
		// // Ϊʲô��?��Ϊ����൱����my�̱߳����������Ρ������������߳�������
		// my.start();

		// ���������̶߳���
		MyThread my1 = new MyThread();
		MyThread my2 = new MyThread();

		my1.start();
		my2.start();
	}
}
