package com.lscdz.util;


/**
 * 代码表加载servlet
 * 		根据业务需要加载的代码表键值集合进行代码表加载
 * @author wangcy
 *
 */

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lscdz.util.codetable.CodeTableConfig;
import com.lscdz.util.codetable.CodeTableManager;



public class LoadCodeTableServlet extends HttpServlet
{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造器
	 */
	public LoadCodeTableServlet() {
		// TODO Auto-generated constructor stub
		System.out.println("\n==============begin LoadCodeTableServlet==============");
	}
	
	/**
	 * servlet执行入口
	 */
	public void init() throws ServletException
	{
		// 获取ServletConfig
		ServletConfig servletConfig = getServletConfig();
		// 获取通过ServletConfig获取传入值
		String className = servletConfig.getInitParameter("config.class");
		System.out.println("nclassName = " + className);
		try
		{
//			System.out.println("ac.getBean(classNames) = " + ac.getBean(className));
			CodeTableManager.initCodeTable((CodeTableConfig)Class.forName(className).newInstance());
		}
		catch (Exception e)
		{
			System.out.println("初始化 " + className + " 定义的代码表错误。原因：\n" + e.getMessage());
		}
		
		System.out.println("\n==============Finished load Code Tables ==============");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.init();
		req.setAttribute("msg", "缓存中的代码重新载入成功！");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/frame/dmwh/cache.jsp"); 
		dispatcher .forward(req, resp); 
	}
	
	

}
