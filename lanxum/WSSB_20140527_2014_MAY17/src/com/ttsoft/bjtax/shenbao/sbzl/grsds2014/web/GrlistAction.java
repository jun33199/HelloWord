/**
 * @Title:       Grlist.java
 * @Description: TODO
 * @date:        2014-11-24上午10:34:07
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.sbzl.grsds2014.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.common.web.action.DcBaseAction;
import com.syax.common.web.util.DcActionConfig;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.Grsds2014Constant;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.Common.GrsdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.GrLsVO;
import com.ttsoft.bjtax.shenbao.sbzl.grsds2014.xmlvo.GrVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.QysdsNbConstant;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-24
 */
public class GrlistAction extends DcBaseAction {

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) 
	{
		// 检查权限
		if (!SbzlAccess.getAuthority(SbzlAccess.GRSDSNDSBB2014, request)) {
			return CAConstants.NOPERMISSION;
		}
		
		return null;
	}
	
	/**
	 * @Description: TODO 显示列表
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,HttpServletResponse response) throws BaseException 
	{
		
		GrLsVO grListVO = new GrLsVO();
		try {
			grListVO.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));
			
		} catch (Exception e) {
			ExceptionUtil.getBaseException(e);
		}
		
		UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
		grListVO.setJsjdm(ud.yhid);
		
		VOPackage vo = new VOPackage();
		vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
		vo.setProcessor(Grsds2014Constant.PROCESSOR_LIST);
		vo.setData(grListVO);
		vo.setUserData(ud);
		
		List allList = (ArrayList)ShenbaoProxy.getInstance().process(vo);
		
		//一次读到内存中,并存在session
		request.getSession().setAttribute(Grsds2014Constant.SESSION_GRLIST, allList);
		
		//截取部分
		grListVO.setGrList(GrsdsUtil.getPageList(allList, Integer.parseInt(grListVO.getCurrentPage()), Grsds2014Constant.SESSION_PAGE_NUM));
		
		grListVO.setSumPage(String.valueOf(Math.ceil((double)allList.size()/(double)Grsds2014Constant.SESSION_PAGE_NUM)));
	
		grListVO.setXsltVersion(Grsds2014Constant.XSLT_GRSDS_GRLIST_2014);
				
		//传输的内容
		String tmpxml = grListVO.toXML();
		System.out.println("show xml:" + tmpxml);
			
		//返回报文
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_DATE, tmpxml);
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_VERSION,grListVO.getXsltVersion());
		request.setAttribute(Grsds2014Constant.XSLT_GRSDS_SCHEMA_VERSION,grListVO.getSchemaVersion());
		
		return "SHOW";
	}

	/**
	 * @Description: TODO 显示列表
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doPage(HttpServletRequest request,HttpServletResponse response) throws BaseException 
	{
		
		GrLsVO grListVO = new GrLsVO();
		
		UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
		grListVO.setJsjdm(ud.yhid);
		
		String currentPage = request.getParameter("currentPage");
		
		//一次读到内存中,并存在session
		List allList = (ArrayList)request.getSession().getAttribute(Grsds2014Constant.SESSION_GRLIST);
		
		if(allList==null){
			allList = new ArrayList();
		}
		//截取部分
		grListVO.setGrList(GrsdsUtil.getPageList(allList, Integer.parseInt(currentPage), Grsds2014Constant.SESSION_PAGE_NUM));
		
		grListVO.setSumPage(String.valueOf(Math.ceil((double)allList.size()/(double)Grsds2014Constant.SESSION_PAGE_NUM)));
		grListVO.setCurrentPage(currentPage);
		grListVO.setXsltVersion(Grsds2014Constant.XSLT_GRSDS_GRLIST_2014);
				
		//传输的内容
		String tmpxml = grListVO.toXMLWithoutSchema();
		System.out.println("show xml:" + tmpxml);
			
		response.setContentType("text/xml;charset=GB2312");
	  	response.setHeader("Cache-Control", "no-cache");		
		try {
			BufferedWriter bw = new BufferedWriter(response.getWriter());
			bw.write(tmpxml);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return  DcActionConfig.NO_FORWARD;
	}
	
	
	/**
	 * @Description: TODO 添加新条目
	 * @param request
	 * @param response
	 * @return
	 */
	public String doAddSave(HttpServletRequest request,HttpServletResponse response) 
	{
		UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
		//------------
		GrVO grVO = new GrVO();
		grVO.setJsjdm(request.getParameter("jsjdm"));
		grVO.setGr_tzzxm(request.getParameter("gr_tzzxm"));
		grVO.setGr_sfzjlx(request.getParameter("gr_sfzjlx"));
		grVO.setGr_sfzjhm(request.getParameter("gr_sfzjhm"));
		grVO.setGr_fpbl(request.getParameter("gr_fpbl"));
		
		//----------------
		VOPackage vo = new VOPackage();
		vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_ADD);
		vo.setProcessor(Grsds2014Constant.PROCESSOR_LIST);
		vo.setData(grVO);
		vo.setUserData(ud);
			
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GB2312' ?>");
		sb.append("<re>");
		try{
			List allList = (ArrayList)ShenbaoProxy.getInstance().process(vo);
			
			//一次读到内存中,并存在session
			request.getSession().setAttribute(Grsds2014Constant.SESSION_GRLIST, allList);
			
			int sumPage = (int)(Math.ceil((double)allList.size()/(double)Grsds2014Constant.SESSION_PAGE_NUM));
			sb.append("<sumPage>").append(sumPage).append("</sumPage>");
			sb.append("<result>SUCCESS</result>");
		}catch (Exception e) {
			e.printStackTrace();
			sb.append("<result>FALSE</result>");
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
		
		return  DcActionConfig.NO_FORWARD;
	}

	/**
	 * @Description: TODO 删除一条信息
	 * @param request
	 * @param response
	 * @return
	 */
	public String doDelete(HttpServletRequest request,HttpServletResponse response)
	{
		GrVO grVO = new GrVO();
		grVO.setJsjdm(request.getParameter("jsjdm"));
		grVO.setGr_sfzjlx(request.getParameter("gr_sfzjlx"));
		grVO.setGr_sfzjhm(request.getParameter("gr_sfzjhm"));
		
		//----------------
		VOPackage vo = new VOPackage();
		vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_DELETE);
		vo.setProcessor(Grsds2014Constant.PROCESSOR_LIST);
		vo.setData(grVO);
			
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GB2312' ?>");
		sb.append("<re>");
		try{
			List allList = (ArrayList)ShenbaoProxy.getInstance().process(vo);
			
			//一次读到内存中,并存在session
			request.getSession().setAttribute(Grsds2014Constant.SESSION_GRLIST, allList);
			
			int sumPage = (int)(Math.ceil((double)allList.size()/(double)Grsds2014Constant.SESSION_PAGE_NUM));
			sb.append("<sumPage>").append(sumPage).append("</sumPage>");
			sb.append("<result>SUCCESS</result>");
		}catch (Exception e) {
			e.printStackTrace();
			sb.append("<result>FALSE</result>");
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
		
		return  DcActionConfig.NO_FORWARD;
	}

	
}
