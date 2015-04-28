package com.ttsoft.bjtax.smsb.lwpk.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.syax.skh.sjjh.service.SKHSjjhProxy;

/**
 * <p>Title: 北京地税核心征管系统-税库行</p>
 * <p>Description: 批量扣款监听器</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 康洪涛
 * @version 1.0
 */


public class PKListener implements ServletContextListener{

	PKTaskThread thread;
	public void contextDestroyed(ServletContextEvent arg0) {
		thread.stopThread();
		System.out.println("终止线程");
	}
	

	public void contextInitialized(ServletContextEvent arg0) {
		//启动线程
		System.out.println("【PKListener】启动线程");
		thread = new PKTaskThread() ;
		thread.start();
		
		
	}

}
