package com.ttsoft.bjtax.smsb.lwpk.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.syax.skh.sjjh.service.SKHSjjhProxy;

/**
 * <p>Title: ������˰��������ϵͳ-˰����</p>
 * <p>Description: �����ۿ������</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author ������
 * @version 1.0
 */


public class PKListener implements ServletContextListener{

	PKTaskThread thread;
	public void contextDestroyed(ServletContextEvent arg0) {
		thread.stopThread();
		System.out.println("��ֹ�߳�");
	}
	

	public void contextInitialized(ServletContextEvent arg0) {
		//�����߳�
		System.out.println("��PKListener�������߳�");
		thread = new PKTaskThread() ;
		thread.start();
		
		
	}

}
