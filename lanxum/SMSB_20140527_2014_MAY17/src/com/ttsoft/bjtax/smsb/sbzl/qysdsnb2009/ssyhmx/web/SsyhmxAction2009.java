package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.ssyhmx.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class SsyhmxAction2009 extends SmsbAction {
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
	{   SsyhmxForm SsyhmxForm=new SsyhmxForm();
		System.out.println(mapping.getAttribute());
		this.getBaseForm(request,SsyhmxForm);
		VOPackage vo = new VOPackage();
		vo.setData(SsyhmxForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_SSYHMX_PROCESSOR);
		try {
			SsyhmxForm = (SsyhmxForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), SsyhmxForm);
		} catch (Exception ex) {
			SsyhmxForm.reset(mapping, request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}
	/**
	 * ��ѯ���걨����
	 * @param mapping struts.action.ActionMapping
	 * @param form SsyhmxForm
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
		
		SsyhmxForm nbForm = (SsyhmxForm) form;
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(nbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB_PROCESSOR);
		vo.setUserData(userData);
		try
		{
			nbForm = (SsyhmxForm) SbzlProxy.getInstance().process(vo);
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
	 * @param form SsyhmxForm
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
		SsyhmxForm SsyhmxForm = (SsyhmxForm)form;
		this.getBaseForm(request,SsyhmxForm);
		SsyhmxForm.setDataList(SfRequestUtil.getValuesToList(request, SsyhmxForm.getSb_columns()));
		VOPackage vo = new VOPackage();
		vo.setData(SsyhmxForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_SSYHMX_PROCESSOR);
		try
		{
			SsyhmxForm = (SsyhmxForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), SsyhmxForm);
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
		SsyhmxForm SsyhmxForm = (SsyhmxForm)form;
		this.getBaseForm(request,SsyhmxForm);
		SsyhmxForm.setDataList(SfRequestUtil.getValuesToList(request, SsyhmxForm.getSb_columns()));
		request.setAttribute("ActionForm",SsyhmxForm);
		VOPackage vo = new VOPackage();
		vo.setData(SsyhmxForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_SSYHMX_PROCESSOR);
		try
		{
//			����processor
			SsyhmxForm=(SsyhmxForm)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), SsyhmxForm);
			if(SsyhmxForm.getCheckList()==null)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
				System.out.println("checked1 passed");
			}else if(SsyhmxForm.getCheckList().size()==0)
			{
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
				System.out.println("checked2 passed");
			}else if(SsyhmxForm.getCheckList().size()>0)
			{
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsUtil2009.getCheckResults(SsyhmxForm.getCheckList()));
			}	
			System.out.println("----------------Action_doCheck---------------------");
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
	 * @param form SsyhmxForm
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
		SsyhmxForm SsyhmxForm = (SsyhmxForm) form;
		this.getBaseForm(request,SsyhmxForm);
//		SsyhmxForm.setDataList(SfRequestUtil.getValuesToList(request, SsyhmxForm.getSb_columns()));
		VOPackage vo = new VOPackage();
		vo.setData(SsyhmxForm);
		vo.setUserData(userData);
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2009_SSYHMX_PROCESSOR);
		try
		{
			//����processor
			SsyhmxForm = (SsyhmxForm) SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), SsyhmxForm);
//			SsyhmxForm.reset(mapping, request);
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
	private void getBaseForm(HttpServletRequest request,SsyhmxForm form)
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
			form.setNextTableURL(QysdsUtil2009.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_2009_5));
			form.setPreviousTableURL(QysdsUtil2009.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_2009_5));
		}
	}
}
