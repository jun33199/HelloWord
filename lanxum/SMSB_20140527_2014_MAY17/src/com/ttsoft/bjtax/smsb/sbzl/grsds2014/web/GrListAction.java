/**
 * @Title:       Grlist.java
 * @Description: TODO
 * @date:        2014-11-24����10:34:07
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.Grsds2014Constant;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.common.GrsdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.grsds2014.model.GrxxModel;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-24
 */
public class GrListAction extends SmsbAction {

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;������Ӫ������˰Ͷ�ʷ��б�</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"2014������Ӫ������˰Ͷ�ʷ��б�");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}
	
	/**
	 * @Description: TODO ��ʾ�б�
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response)throws BaseException  
	{
		GrListActionForm lsForm = (GrListActionForm)form;
		try {
			lsForm.setSfzjlxList(GrsdsUtil.getSfzjlxList(request));
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		
		//��ȡ�û���Ϣ
		UserData ud = getUserData(request);
		
		VOPackage vo = new VOPackage();
		vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
		vo.setProcessor(Grsds2014Constant.PROCESSOR_LIST);
		vo.setData(lsForm);
		vo.setUserData(ud);
		
		try {
			if(null!=lsForm.getJsjdm() && !"".equals(lsForm.getJsjdm())){
				List  ls= (ArrayList)SbzlProxy.getInstance().process(vo);
				
				//һ�ζ����ڴ���,������session
				request.getSession().setAttribute(Grsds2014Constant.SESSION_GRLIST, ls);
				
				//��ȡ����
				lsForm.setGrList(GrsdsUtil.getPageList(ls, Integer.parseInt(lsForm.getCurrentPage()), Grsds2014Constant.SESSION_PAGE_NUM));
			
				
				lsForm.setSumPage(String.valueOf(Math.ceil((double)ls.size()/(double)Grsds2014Constant.SESSION_PAGE_NUM)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		request.setAttribute(mapping.getAttribute(), lsForm);	
		return mapping.findForward("Show");
	}

	
	
	/**
	 * @Description: TODO �������Ŀ
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doAddSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
		UserData ud = getUserData(request);
		//------------
		GrxxModel grModel = new GrxxModel();
		grModel.setJsjdm(request.getParameter("jsjdm"));
		grModel.setGr_tzzxm(request.getParameter("gr_tzzxm"));
		grModel.setGr_sfzjlx(request.getParameter("gr_sfzjlx"));
		grModel.setGr_sfzjhm(request.getParameter("gr_sfzjhm"));
		grModel.setGr_fpbl(request.getParameter("gr_fpbl"));
		
		//----------------
		VOPackage vo = new VOPackage();
		vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_ADD);
		vo.setProcessor(Grsds2014Constant.PROCESSOR_LIST);
		vo.setData(grModel);
		vo.setUserData(ud);
			
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		try{
			if(!GrsdsUtil.checkAuthority(grModel.getJsjdm(), ud)){
				sb.append("result:false,msg:\"����Ȩ�����ü����������ȷ��Ȩ�ޣ�\"");
			}else{
				List  ls= (ArrayList)SbzlProxy.getInstance().process(vo);
				//һ�ζ����ڴ���,������session
				request.getSession().setAttribute(Grsds2014Constant.SESSION_GRLIST, ls);
				
				sb.append("result:true");
			}
		}catch (Throwable e) {
			e.printStackTrace();
			sb.append("result:false,msg:\"����ʧ�ܣ��������Ա��ϵ��\"");
		}
		sb.append("}");
		
		
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
		
		return null;
	}

	/**
	 * @Description: TODO ɾ��һ����Ϣ
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response)
	{
		UserData ud = getUserData(request);
		GrxxModel grModel = new GrxxModel();
		grModel.setJsjdm(request.getParameter("jsjdm"));
		grModel.setGr_sfzjlx(request.getParameter("gr_sfzjlx"));
		grModel.setGr_sfzjhm(request.getParameter("gr_sfzjhm"));
		
		//----------------
		VOPackage vo = new VOPackage();
		vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_DELETE);
		vo.setProcessor(Grsds2014Constant.PROCESSOR_LIST);
		vo.setData(grModel);
			
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		try{
			if(!GrsdsUtil.checkAuthority(grModel.getJsjdm(), ud)){
				sb.append("result:false,msg:\"����Ȩ�����ü����������ȷ��Ȩ�ޣ�\"");
			}else{
				List  ls= (ArrayList)SbzlProxy.getInstance().process(vo);
				//һ�ζ����ڴ���,������session
				request.getSession().setAttribute(Grsds2014Constant.SESSION_GRLIST, ls);
				
				sb.append("result:true");
			}
		}catch (Exception e) {
			e.printStackTrace();
			sb.append("result:false,msg:\"ɾ��ʧ���������Ա��ϵ��\"");
		}
		sb.append("}");
		
		
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
		
		return null;
	}

	/**
	 * @Description: TODO������������ ��ѯͶ�����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		GrListActionForm lsForm = (GrListActionForm)form;
		//��ȡ�û���Ϣ
		UserData ud = getUserData(request);
		
		VOPackage vo = new VOPackage();
		vo.setAction(Grsds2014Constant.PROCESSOR_ACTION_QUERY);
		vo.setProcessor(Grsds2014Constant.PROCESSOR_LIST);
		vo.setData(lsForm);
		vo.setUserData(ud);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");	
		try {
			if(!GrsdsUtil.checkAuthority(lsForm.getJsjdm(), ud)){
				sb.append("msg:\"��û�в����ü���������Ȩ�޻��������벻����\"");
			}else{
				List ls = (ArrayList)SbzlProxy.getInstance().process(vo);
				
				//һ�ζ����ڴ���,������session
				request.getSession().setAttribute(Grsds2014Constant.SESSION_GRLIST, ls);
				
				//��ȡ����
				List currentList = GrsdsUtil.getPageList(ls, Integer.parseInt(lsForm.getCurrentPage()), Grsds2014Constant.SESSION_PAGE_NUM);
			
				//��ҳ��
				int sumPage = (int)Math.ceil((double)ls.size()/(double)Grsds2014Constant.SESSION_PAGE_NUM);
				
				sb.append("sumPage:").append(sumPage).append(",");
				sb.append("datels:[");
			
				for(int i =0;i<currentList.size();i++){
					if(i!=0){
						sb.append(",");
					}
					sb.append("{");
					GrxxModel model = (GrxxModel)currentList.get(i);
					sb.append("\"gr_tzzxm\":\"").append(model.getGr_tzzxm()).append("\",");
					sb.append("\"gr_sfzjlx\":\"").append(model.getGr_sfzjlx()).append("\",");
					sb.append("\"gr_sfzjhm\":\"").append(model.getGr_sfzjhm()).append("\",");
					sb.append("\"gr_fpbl\":\"").append(model.getGr_fpbl()).append("\",");
					sb.append("\"gr_txzt\":\"").append(model.getGr_txzt()).append("\"");
					sb.append("}");
				}
				sb.append("]");
			}
		} catch (Exception e) {
			sb.append("msg:\"ϵͳ����,����ϵ����Ա\"");
		}
		sb.append("}");
		
		
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
		return null;
	}
	
	/**
	 * @Description: TODO ��ȡ��ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		GrListActionForm lsForm = (GrListActionForm)form;

		List allList = (ArrayList)request.getSession().getAttribute(Grsds2014Constant.SESSION_GRLIST);
		if(allList==null){
			return null;
		}
		List currentList = GrsdsUtil.getPageList(allList, Integer.parseInt(lsForm.getCurrentPage()), Grsds2014Constant.SESSION_PAGE_NUM);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		try {					
				sb.append("datels:[");
				for(int i =0;i<currentList.size();i++){
					if(i!=0){
						sb.append(",");
					}
					sb.append("{");
					GrxxModel model = (GrxxModel)currentList.get(i);
					sb.append("\"gr_tzzxm\":\"").append(model.getGr_tzzxm()).append("\",");
					sb.append("\"gr_sfzjlx\":\"").append(model.getGr_sfzjlx()).append("\",");
					sb.append("\"gr_sfzjhm\":\"").append(model.getGr_sfzjhm()).append("\",");
					sb.append("\"gr_fpbl\":\"").append(model.getGr_fpbl()).append("\",");
					sb.append("\"gr_txzt\":\"").append(model.getGr_txzt()).append("\"");
					sb.append("}");
				}
				sb.append("]");
		} catch (Exception e) {
			sb.append("msg:\"ϵͳ����,����ϵ����Ա\"");
		}
		sb.append("}");
		
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
		return null;
	}
	
	
}
