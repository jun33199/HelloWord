package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.web.action.DcBaseAction;
import com.syax.common.web.util.DcActionConfig;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.GrsdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.JbxxbVO;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.NdsbbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class GrsdsNdsbAction extends  DcBaseAction {

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) 
	{
		
		return null;
	}
	
	/**
	 * @Description: TODO 显示信息
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
	{
		
		String query_jsjdm=request.getParameter("query_jsjdm");
		String query_sfzjhm=request.getParameter("query_sfzjhm");
		String query_sfzjlx=request.getParameter("query_sfzjlx");
	
		NdsbbVO ndsbbVO = new NdsbbVO();
		ndsbbVO.setXsltVersion(Grsds2014Constant.XSLT_GRSDS_NDSBB_2014);
		ndsbbVO.getInf_gr().setTzzxx_sfzjlx(query_sfzjlx);
		ndsbbVO.getInf_gr().setTzzxx_sfzjhm(query_sfzjhm);
		ndsbbVO.getInf_qy().setBtzzxx_jsjdm(query_jsjdm);
		
		
		
		try {
			if(query_jsjdm==null || "".equals(query_jsjdm) || query_sfzjlx==null || "".equals(query_sfzjlx) || query_sfzjhm==null || "".equals(query_sfzjhm)){
				throw ExceptionUtil.getBaseException(new SystemException("查询信息不完整"));
			}
			
			//检验是否填写基本信息表 如未填写跳转至投资者列表页 
			if (!GrsdsUtil.hasItemJcxxTzz(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1), query_jsjdm, query_sfzjlx, query_sfzjhm)) {	
				return "BACK";
			}
			
			ndsbbVO.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));	//身份证件类型
			ndsbbVO.setGjList(GrsdsUtil.getGjDqList(request));			//国籍地区代码表
			//查询年报信息
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_NDSBB);
			vo.setData(ndsbbVO);
			ndsbbVO = (NdsbbVO)ShenbaoProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		
		//传输的内容
		String tmpxml = ndsbbVO.toXML();
		System.out.println("nb_servertoweb:" + tmpxml);
			
		
		//返回报文
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_DATE, tmpxml);
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_VERSION,
				ndsbbVO.getXsltVersion());
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_SCHEMA_VERSION,
				ndsbbVO.getSchemaVersion());
		
		return "SHOW";
	}

	/**
	 * @Description: TODO 保存
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doSave(HttpServletRequest request,HttpServletResponse response) throws BaseException 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GB2312' ?>");
		sb.append("<re>");
			
		try {
			//System.out.println(request.getCharacterEncoding());
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
			String xmldata = request.getParameter(Grsds2014Constant.XML_RESPONSE_Data);
			//System.out.println("nb_webtoserver:"+xmldata);
			NdsbbVO ndsbbVO = new NdsbbVO();
			
			//xml转vo
			//jbxxbVO.resoveXML(xmldata);
			XMLParseHelper.parseXMLString(ndsbbVO, xmldata,XMLParseHelper.VTDXML_PARSER, false, true);
		
			//获取基本信息数据
			VOPackage vo = new VOPackage();
			vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_SAVE);
			vo.setProcessor(Grsds2014Constant.PROCESSOR_NDSBB);
			vo.setData(ndsbbVO);
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
