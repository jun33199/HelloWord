/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.cbmxbjrqy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author ����ϼ
 * @version 1.1
 */

public class CbmxbjrqyAction extends 
SmsbAction 
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

		CbmxbjrqyForm cbmxbjrqyForm = (CbmxbjrqyForm)form;
		userData = this.getUserData(request);
		this.getBaseForm(request, cbmxbjrqyForm);//������˰�˻�������
		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION); //�������ݰ���������
		vo.setUserData(userData);
		vo.setData(cbmxbjrqyForm); //���ò�������
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_CBMXBJRQY_PROCESSOR); //����PROCESSOR
		try
		{
			cbmxbjrqyForm =(CbmxbjrqyForm)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cbmxbjrqyForm); //��ѯ�������request
		}catch(Exception ex)
		{
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");

	}  

	/**
	 * ����ҳ������
	 * @param mapping struts.action.ActionMapping
	 * @param form QysdsnbForm
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
		
		CbmxbjrqyForm cbmxbjrqyform = (CbmxbjrqyForm)form;
		userData = this.getUserData(request);
		String[] columns = cbmxbjrqyform.getSb_columns();
		cbmxbjrqyform.setDataList(SfRequestUtil.getValuesToList(request, columns));
		this.getBaseForm(request, cbmxbjrqyform);//������˰�˻�������
		request.setAttribute(mapping.getAttribute(), cbmxbjrqyform);
		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION); //���ò�������
		vo.setData(cbmxbjrqyform); //���ò�������
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_CBMXBJRQY_PROCESSOR); //����Processor
		vo.setUserData(userData);
		try
		{
			//����processor
			cbmxbjrqyform=(CbmxbjrqyForm)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cbmxbjrqyform); //�Ѳ�ѯ�������request
		}catch(Exception ex)
		{
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���"); //���ر�������Ϣ
		return mapping.findForward("Show");
	}   

	/**
	 * ɾ��ҳ������
	 * @param mapping struts.action.ActionMapping
	 * @param form QysdsnbForm
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
		
		CbmxbjrqyForm cbmxbjrqyform = (CbmxbjrqyForm)form;
		userData = this.getUserData(request);
		this.getBaseForm(request,cbmxbjrqyform); //������˰�˻�����Ϣ
		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_DELETEACTION); //���ò�������
		vo.setData(cbmxbjrqyform);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_CBMXBJRQY_PROCESSOR); //����Processor
		vo.setUserData(userData);
		try
		{
			//����processor
			cbmxbjrqyform=(CbmxbjrqyForm)SbzlProxy.getInstance().process(vo);
			//cbmxbjrqyform.reset(mapping, request);
			request.setAttribute(mapping.getAttribute(), cbmxbjrqyform); //�Ѳ�ѯ�������request
		}catch(Exception ex)
		{
			//		ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}

		request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���"); //����ɾ�����
		return mapping.findForward("Show");
	}   

	/**
	 * ���ҳ������
	 * @param mapping struts.action.ActionMapping
	 * @param form QysdsnbForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doCheck(ActionMapping mapping,
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
		
		CbmxbjrqyForm cbmxbjrqyform = (CbmxbjrqyForm)form;
		String[] columns = cbmxbjrqyform.getSb_columns();
		cbmxbjrqyform.setDataList(SfRequestUtil.getValuesToList(request, columns));
		this.getBaseForm(request, cbmxbjrqyform); //������˰�˻�����Ϣ
	    userData = this.getUserData(request);
		request.setAttribute(mapping.getAttribute(),cbmxbjrqyform);
		
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_CHECKACTION); //���ò�������
		vo.setData(cbmxbjrqyform); //���ò�������
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNBNEW_CBMXBJRQY_PROCESSOR); //����processor
		try
		{
			//����processor
			cbmxbjrqyform =(CbmxbjrqyForm)SbzlProxy.getInstance().process(vo);
			request.setAttribute(mapping.getAttribute(), cbmxbjrqyform);
			
			if(cbmxbjrqyform.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(cbmxbjrqyform.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
			}else if(cbmxbjrqyform.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QysdsNewUtil.getCheckResults(cbmxbjrqyform.getCheckList()));
			}
			return mapping.findForward("Show");
		}
		catch (Exception ex)
		{
			//ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	
	}  
	
	/**
	 * ��ȡ��˰�˻�������
	 * @param request��form
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request,CbmxbjrqyForm form)
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
			form.setNextTableURL(QysdsNewUtil.getTableURL(baseForm,"N_"+CodeConstant.TABLE_ID_CBMXBJRQY));
			form.setPreviousTableURL(QysdsNewUtil.getTableURL(baseForm,"P_"+CodeConstant.TABLE_ID_CBMXBJRQY));
		}
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
}