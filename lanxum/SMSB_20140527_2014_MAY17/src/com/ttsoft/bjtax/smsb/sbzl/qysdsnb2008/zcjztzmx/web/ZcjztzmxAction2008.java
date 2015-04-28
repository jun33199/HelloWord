package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zcjztzmx.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class ZcjztzmxAction2008 extends SmsbAction {
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) form;
		this.getBaseForm(request,ZcjztzmxForm);
		VOPackage vo = new VOPackage();
		vo.setData(ZcjztzmxForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_ZCJZTZMX_PROCESSOR);
		
		try {
			ZcjztzmxForm = (ZcjztzmxForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), ZcjztzmxForm);
		} catch (Exception ex) {
			ZcjztzmxForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	
	/**
	 * ��ѯ���걨����
	 * @param mapping struts.action.ActionMapping
	 * @param form ZcjztzmxForm
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
		ZcjztzmxForm nbForm = (ZcjztzmxForm) form;
		VOPackage vo = new VOPackage();
		vo.setData(nbForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB_PROCESSOR);
		
		try
		{
			nbForm = (ZcjztzmxForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), nbForm);
		}
		catch (Exception ex)
		{
			nbForm.reset(mapping, request);
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return mapping.findForward("Query");
	}
	
	/**
	 * �����걨����
	 * @param mapping struts.action.ActionMapping
	 * @param form ZcjztzmxForm
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm)form;
		this.getBaseForm(request,ZcjztzmxForm);
		ZcjztzmxForm.setDataList(SfRequestUtil.getValuesToList(request, ZcjztzmxForm.getSb_columns1()));
		VOPackage vo = new VOPackage();
		vo.setData(ZcjztzmxForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_ZCJZTZMX_PROCESSOR);
		request.setAttribute(mapping.getAttribute(), ZcjztzmxForm);
		try
		{
			ZcjztzmxForm = (ZcjztzmxForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), ZcjztzmxForm);
		}
		catch (Exception ex)
		{
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
		return mapping.findForward("Show");
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm)form;
		this.getBaseForm(request,ZcjztzmxForm);
		ZcjztzmxForm.setDataList(SfRequestUtil.getValuesToList(request, ZcjztzmxForm.getSb_columns1()));
		request.setAttribute("ActionForm",ZcjztzmxForm);
		VOPackage vo = new VOPackage();
		vo.setData(ZcjztzmxForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_ZCJZTZMX_PROCESSOR);
		try
		{
			//����processor
			ZcjztzmxForm=(ZcjztzmxForm)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), ZcjztzmxForm);
			if(ZcjztzmxForm.getCheckList()==null)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(ZcjztzmxForm.getCheckList().size()==0)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(ZcjztzmxForm.getCheckList().size()>0)
			{
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsUtil2008.getCheckResults(ZcjztzmxForm.getCheckList()));
			}
			System.out.println("------------Action_doCheck----------------");
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
	 * @param form ZcjztzmxForm
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) form;
		this.getBaseForm(request,ZcjztzmxForm);
		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(ZcjztzmxForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2008_ZCJZTZMX_PROCESSOR);
		try
		{
			//����processor
			ZcjztzmxForm = (ZcjztzmxForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), ZcjztzmxForm);
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
	
	/**
	 * ��session�л�ȡ������Ϣ
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request,ZcjztzmxForm form)
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
		
		if(baseForm!=null){
			form.setJsjdm(baseForm.getJsjdm());
			form.setSbrq(baseForm.getSbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			form.setSkssksrq(baseForm.getSkssksrq());
			form.setSkssjsrq(baseForm.getSkssjsrq());
			form.setGzglxs(baseForm.getGzglxs());
			form.setJmlx(baseForm.getJmlx());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setNextTableURL(QysdsUtil2008.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_2008_10));
			form.setPreviousTableURL(QysdsUtil2008.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_2008_10));
		}
	}
	
}
