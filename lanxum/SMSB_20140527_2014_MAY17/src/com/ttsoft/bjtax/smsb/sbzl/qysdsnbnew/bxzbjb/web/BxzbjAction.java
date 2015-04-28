/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.bxzbjb.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class BxzbjAction
extends SmsbAction
{
	/**
	 * ����Ա����
	 */
	
	private UserData userData = null;
	
	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * @param mapping struts.action.ActionMapping
	 * @param actionForm QysdsnbForm
	 * @param httpServletRequest HttpServletRequest
	 * @param response HttpServletResponse
	 */
	
	protected void initialRequest (ActionMapping mapping,
			ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse response)
	
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
		"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰�����˰�걨��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
		"��ҵ����˰�����˰�걨��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
		"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");
		
	}
	
	/**
	 * ��ʼ��ҳ������
	 * @param mapping struts.action.ActionMapping
	 * @param form QysdsnbForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doShow (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
		
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
		try {
			bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		} catch (Exception ex) {
			bxzbjForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * ��ѯ���걨����
	 * @param mapping struts.action.ActionMapping
	 * @param form BxzbjForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doQuery (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
		
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		userData = this.getUserData(request);
		bxzbjForm.setLrr(userData.getYhid());
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(bxzbjForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB_PROCESSOR);
		vo.setUserData(userData);
		try
		{
			bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		}
		catch (Exception ex)
		{
			bxzbjForm.reset(mapping, request);
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return mapping.findForward("Query");
	}
	
	/**
	 * �����걨����
	 * @param mapping struts.action.ActionMapping
	 * @param form BxzbjForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doSave (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		//��ȡ�̶���������
		bxzbjForm.setDataList(SfRequestUtil.getValuesToList(request,bxzbjForm.getGd_Colums()));
		System.out.println("bxzbjForm.getDataList().size()"+bxzbjForm.getDataList().size());
		
		//��ȡ����׼��������������
		bxzbjForm.setQtzbjList(SfRequestUtil.getValuesToList(request,bxzbjForm.getQtzbj_Columns()));
		System.out.println("bxzbjForm.getQtzbjList().size()"+bxzbjForm.getQtzbjList().size());
		
		//��ȡ��������������
		bxzbjForm.setQtList(SfRequestUtil.getValuesToList(request,bxzbjForm.getQt_Columns()));
		System.out.println("bxzbjForm.getQtList().size()"+bxzbjForm.getQtList().size());
		
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		vo.setUserData(userData);
	    vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
	    try
		{
	    	bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		}
		catch (Exception ex)
		{
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
		return mapping.findForward("Show");
	}
	
	//����ת������
	/*
	 * 
	 */
	public  ArrayList getValuesToList(HttpServletRequest request,
			String columns[]) {
		ArrayList list = new ArrayList();
		if (columns == null) {
			System.out.println("columns==null");
			return list;
		}
		System.out.println("request.getParameter(columns[0])--"+request.getParameter(columns[0]));
		if (request.getParameter(columns[0]) != null) {
			System.out.println("request.getParameter(columns[0]) != null");
			int rows = request.getParameterValues(columns[0]).length;
			System.out.println("rows=="+rows);
			for (int i = 0; i < rows; i++) {
				HashMap map = new HashMap();
				for (int j = 0; j < columns.length; j++) {
					if (request.getParameter(columns[j]) == null) {
						continue;
					}
					map.put(columns[j],
							request.getParameterValues(columns[j])[i]);
				}
				list.add(map);
			}
		}
		System.out.println("over");
		return list;
	}
	/**
	 * ������ݣ����ͨ������б���
	 * @param mapping struts.action.ActionMapping
	 * @param form ZcmxbForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doCheck (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		
		//��ȡ�̶���������
		bxzbjForm.setDataList(SfRequestUtil.getValuesToList(request,bxzbjForm.getGd_Colums()));
		//System.out.println("bxzbjForm.getDataList().size()"+bxzbjForm.getDataList().size());
		
		//��ȡ����׼��������������
		bxzbjForm.setQtzbjList(this.getValuesToList(request,bxzbjForm.getQtzbj_Columns()));
		//System.out.println("bxzbjForm.getQtzbjList().size()"+bxzbjForm.getQtzbjList().size());
		
		//��ȡ��������������
		bxzbjForm.setQtList(this.getValuesToList(request,bxzbjForm.getQt_Columns()));
		//System.out.println("bxzbjForm.getQtList().size()"+bxzbjForm.getQtList().size());
		
		//request.setAttribute("ActionForm",hzssmxbform);
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		 vo.setUserData(userData);
	    vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
	    try
		{
	    	bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
			if(bxzbjForm.getCheckList()==null)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(bxzbjForm.getCheckList().size()==0)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(bxzbjForm.getCheckList().size()>0)
			{
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsNewUtil.getCheckResults(bxzbjForm.getCheckList()));
			}
	    	System.out.println("---------------------Action___doCheck--------------------");
		}
		catch (Exception ex)
		{
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * ɾ���걨����
	 * @param mapping struts.action.ActionMapping
	 * @param form BxzbjForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
	 */
	
	public ActionForward doDelete (ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws
	BaseException
	{
//		 -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		BxzbjForm bxzbjForm = (BxzbjForm) form;
		this.getBaseForm(request,bxzbjForm);
		VOPackage vo = new VOPackage();
		vo.setData(bxzbjForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_BXZBJ_PROCESSOR);
		try
		{
			//����processor
			bxzbjForm = (BxzbjForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), bxzbjForm);
		}
		catch (Exception ex)
		{
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
		return mapping.findForward("Show");
	}
	 /**
     * �˳�ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doExit (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	throws
	BaseException
	{
	
	return mapping.findForward("Return");
	}   
    private void getBaseForm(HttpServletRequest request,BxzbjForm form)
	{
		userData = this.getUserData(request);
		QysdsNewForm baseForm =(QysdsNewForm)request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			baseForm.setSkssksrq(request.getParameter("skksrq"));
			baseForm.setSkssjsrq(request.getParameter("skjsrq"));
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM,baseForm);
		}
		
		if(baseForm!=null)
		{
			form.setJsjdm(baseForm.getJsjdm());
			form.setSbrq(baseForm.getSbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			form.setSkssksrq(baseForm.getSkssksrq());
			form.setSkssjsrq(baseForm.getSkssjsrq());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setCkzd(baseForm.getCkzd());
			form.setZsfs(baseForm.getZsfs());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setGzglxs(baseForm.getGzglxs());
			form.setJmlx(baseForm.getJmlx());
			if("no".equals(QysdsNewUtil.isLastTable(baseForm,CodeConstant.TABLE_ID_BXZBJ))){
				form.setIsLastTable("no");
				form.setNextTableURL(QysdsNewUtil.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_BXZBJ));
			}else{
				form.setIsLastTable("yes");
			}
			form.setPreviousTableURL(QysdsNewUtil.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_BXZBJ));
		}
	}
}