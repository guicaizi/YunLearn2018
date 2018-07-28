package com.yun.software.yunlearn.Thread.cn.itcast_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class ThreadDemo {
	public static void main(String[] args) {
		// �̰߳�ȫ����
		StringBuffer sb = new StringBuffer();
		Vector<String> v = new Vector<String>();
		Hashtable<String, String> h = new Hashtable<String, String>();

		// Vector���̰߳�ȫ��ʱ���ȥ����ʹ�õģ������һ�˵����ʹҪ��ȫ����Ҳ������
		// ��ô������˭��?
		// public static <T> List<T> synchronizedList(List<T> list)
		List<String> list1 = new ArrayList<String>();// �̲߳���ȫ
		List<String> list2 = Collections
				.synchronizedList(new ArrayList<String>()); // �̰߳�ȫ
	}
}
