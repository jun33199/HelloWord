package com.lscdz.util;


/**
 * ��������servlet
 * 		����ҵ����Ҫ���صĴ�����ֵ���Ͻ��д�������
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
	 * ���к�
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ������
	 */
	public LoadCodeTableServlet() {
		// TODO Auto-generated constructor stub
		System.out.println("\n==============begin LoadCodeTableServlet==============");
	}
	
	/**
	 * servletִ�����
	 */
	public void init() throws ServletException
	{
		// ��ȡServletConfig
		ServletConfig servletConfig = getServletConfig();
		// ��ȡͨ��ServletConfig��ȡ����ֵ
		String className = servletConfig.getInitParameter("config.class");
		System.out.println("nclassName = " + className);
		try
		{
//			System.out.println("ac.getBean(classNames) = " + ac.getBean(className));
			CodeTableManager.initCodeTable((CodeTableConfig)Class.forName(className).newInstance());
		}
		catch (Exception e)
		{
			System.out.println("��ʼ�� " + className + " ����Ĵ�������ԭ��\n" + e.getMessage());
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
		req.setAttribute("msg", "�����еĴ�����������ɹ���");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/frame/dmwh/cache.jsp"); 
		dispatcher .forward(req, resp); 
	}
	
	

}
