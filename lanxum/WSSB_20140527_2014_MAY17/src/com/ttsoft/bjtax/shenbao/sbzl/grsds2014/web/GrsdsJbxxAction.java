package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.web.action.DcBaseAction;
import com.syax.common.web.util.DcActionConfig;
import com.syax.common.web.util.FileUploadUtil;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.GrsdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.JbxxbVO;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class GrsdsJbxxAction extends DcBaseAction {

	

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) 
	{
		
		return null;
	}
	
	
	/**
	 * @Description: TODO 显示基础信息 如果当年有信息则取当年信息，如果没有则取上一年信息，如果在没有则空；
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,HttpServletResponse response) throws BaseException 
	{
		
		JbxxbVO jbxxbVO = new JbxxbVO();
		jbxxbVO.setXsltVersion(Grsds2014Constant.XSLT_GRSDS_JBXXB_2014);
		
		String jsjdm = request.getParameter("query_jsjdm");
		String sfzjhm = request.getParameter("query_sfzjhm");
		String sfzjlx = request.getParameter("query_sfzjlx");
		jbxxbVO.getQyxxvo().setQyxx_jsjdm(jsjdm);
		jbxxbVO.getGrxx().setGrxx_sfzjlx(sfzjlx);
		jbxxbVO.getGrxx().setGrxx_sfzjhm(sfzjhm);
		
		//较验一下
		if(jsjdm==null||"".equals(jsjdm)||sfzjlx==null||"".equals(sfzjlx)||sfzjhm==null||"".equals(sfzjhm))
		{
			throw ExceptionUtil.getBaseException(new Exception("基本查询信息不正确"));
		}
		
		try {
			jbxxbVO.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));	//身份证件类型代码表
			jbxxbVO.setGjList(GrsdsUtil.getGjDqList(request));			//国籍地区代码表
			jbxxbVO.setGjbzhyList(GrsdsUtil.getGjBzhyList(request));	//国家标准行业代码表
			jbxxbVO.setSwjgzzjgList(GrsdsUtil.getSwjgzzjgList(request));//税务机关组织机构
			jbxxbVO.setDjzclxList(GrsdsUtil.getDjzclxList(request));	//登记注册类型
			//获取基本信息数据
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_JBXXB);
			vo.setData(jbxxbVO);
			jbxxbVO = (JbxxbVO)ShenbaoProxy.getInstance().process(vo);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		
		//传输的内容
		String tmpxml = jbxxbVO.toXML();
		//System.out.println("jbxx_servertoweb:"+tmpxml);
		//返回报文
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_DATE, tmpxml);
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_VERSION,
				jbxxbVO.getXsltVersion());
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_SCHEMA_VERSION,
				jbxxbVO.getSchemaVersion());
		
		return "SHOW";
	}
	
	
	/**
	 * @Description: TODO 显示基础信息 如果当年有信息则取当年信息，如果没有则取上一年信息，如果在没有则空；
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doSave(HttpServletRequest request,HttpServletResponse response) throws BaseException 
	{
		try {
			//System.out.println(request.getCharacterEncoding());
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GB2312' ?>");
		sb.append("<re>");

		try {
			
			UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
			
			String xmldata = request.getParameter(Grsds2014Constant.XML_RESPONSE_Data);
			//System.out.println("jbxx_webtoserver"+xmldata);
			JbxxbVO jbxxbVO = new JbxxbVO();
			
			//xml转vo
			//jbxxbVO.resoveXML(xmldata);
			XMLParseHelper.parseXMLString(jbxxbVO, xmldata,XMLParseHelper.VTDXML_PARSER, false, true);
		
			//获取基本信息数据
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_SAVE);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_JBXXB);
			vo.setData(jbxxbVO);
			vo.setUserData(ud);

			ShenbaoProxy.getInstance().process(vo);
			sb.append("SUCCESS");
		} catch (Exception e) {
			sb.append("FALSE");
			e.printStackTrace();
		}
		
		sb.append("</re>");
		
		
		response.setContentType("text/xml;charset=GB2312");
	  	response.setHeader("Cache-Control", "no-cache");		
		try {
			BufferedWriter bw = new BufferedWriter(response.getWriter());
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DcActionConfig.NO_FORWARD;
	}
}
