package com.yun.software.yunlearn.Thread.cn.itcast_03;

/*
 * ��λ�ȡ�̶߳����������?
 * public final String getName():��ȡ�̵߳����ơ�
 * ��������̶߳����������?
 * public final void setName(String name):�����̵߳�����
 * 
 * ��Բ���Thread�����������λ�ȡ�̶߳���������?
 * public static Thread currentThread():���ص�ǰ����ִ�е��̶߳���
 * Thread.currentThread().getName()
 */
public class MyThreadDemo {
	public static void main(String[] args) {
		// �����̶߳���
		//�޲ι���+setXxx()
		// MyThread my1 = new MyThread();
		// MyThread my2 = new MyThread();
		// //���÷�����������
		// my1.setName("����ϼ");
		// my2.setName("����");
		// my1.start();
		// my2.start();
		
		//���ι��췽�����߳�������
		// MyThread my1 = new MyThread("����ϼ");
		// MyThread my2 = new MyThread("����");
		// my1.start();
		// my2.start();
		
		//��Ҫ��ȡmain�������ڵ��̶߳�������ƣ�����ô����?
		//�����������,Thread���ṩ��һ���ܺ���ķ���:
		//public static Thread currentThread():���ص�ǰ����ִ�е��̶߳���
		System.out.println(Thread.currentThread().getName());
	}
}

/*
����Ϊʲô�ǣ�Thread-? ���

class Thread {
	private char name[];

	public Thread() {
        init(null, null, "Thread-" + nextThreadNum(), 0);
    }
    
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize) {
        init(g, target, name, stackSize, null);
    }
    
     private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc) {
        //�󲿷ִ��뱻ʡ����
        this.name = name.toCharArray();
    }
    
    public final void setName(String name) {
        this.name = name.toCharArray();
    }
    
    
    private static int threadInitNumber; //0,1,2
    private static synchronized int nextThreadNum() {
        return threadInitNumber++; //return 0,1
    }
    
    public final String getName() {
        return String.valueOf(name);
    }
}

class MyThread extends Thread {
	public MyThread() {
		super();
	}
}

*/