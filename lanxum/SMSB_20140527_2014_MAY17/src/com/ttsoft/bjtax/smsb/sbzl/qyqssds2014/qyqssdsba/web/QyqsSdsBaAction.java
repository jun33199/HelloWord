package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2013.jbxxb.web.JbxxbForm2013;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class QyqsSdsBaAction extends SmsbAction {

	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QyqssdsBaForm
	 * @param httpServletRequest
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">˰�ѹ���</font>&gt;��ҵ��������˰������</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,"��ҵ��������˰������");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,"help/smsb/sbzl/qyqssds/qyqssdsba-000.htm");
	}
	
	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		qyqssdsBaForm.reset(mapping, request);
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		try {
			//����process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			qyqssdsBaForm.setTbrq(sdf.format(curTime));
			qyqssdsBaForm.setQsbaksrq(sdf.format(curTime));
			//������Ĭ��ѡ�з�
			qyqssdsBaForm.setJyqxjm("N");
			qyqssdsBaForm.setGdjyjs("N");
			qyqssdsBaForm.setYfdxgb("N");
			qyqssdsBaForm.setYfxgpc("N");
			qyqssdsBaForm.setYfgdqs("N");
			qyqssdsBaForm.setQtyy("N");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Show");
	}
	
	/**
	 * ������Ϣ
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		try {
			//����process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	
	/**
	 * ¼�����������ѯ�����Ϣ
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		String saveSuccess=qyqssdsBaForm.getSaveSuccess();
		try {
			//����process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			qyqssdsBaForm.setSaveSuccess(saveSuccess);
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(null==qyqssdsBaForm.getTbrq()||"".equals(qyqssdsBaForm.getTbrq())){
				qyqssdsBaForm.setTbrq(sdf.format(curTime));
			}
			if(null==qyqssdsBaForm.getQsbaksrq()||"".equals(qyqssdsBaForm.getQsbaksrq())){
				qyqssdsBaForm.setQsbaksrq(sdf.format(curTime));
			}			
			qyqssdsBaForm.setIsQuery("1");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Show");
	}
	/**
	 * ��������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doAccept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(5);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		Map map=QyqssdsActionHelper.getShztbs(qyqssdsBaForm.getJsjdm());
		if(!map.isEmpty() && map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString().equals("2")){
			throw new ApplicationException("����ҵ����ҵ��������˰���������ͨ���������ظ���ˣ�");
		}
		try {
			//����process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
			
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	/**
	 * �ܾ�����
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doRefuse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(6);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		Map map=QyqssdsActionHelper.getShztbs(qyqssdsBaForm.getJsjdm());
		if(map.isEmpty() || !map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString().equals("1")){
			throw new ApplicationException("����ҵ����ҵ��������˰���������ύδ���״̬������ִ����˲��أ�");
		}
		try {
			//����process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	
	/**
	 * ɾ����¼
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);	
		VOPackage vo = new VOPackage();
		vo.setAction(7);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		
		try {
			//����process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	/**
	 * ������¼
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsBaForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doCancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		UserData userData = null;
		//��ȡ������Ϣ
		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) form;
		userData = this.getUserData(request);
		
		VOPackage vo = new VOPackage();
		vo.setAction(8);
		vo.setData(qyqssdsBaForm);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor.QyqssdsBaProcessor");
		vo.setUserData(userData);
		
		try {
			//����process
			qyqssdsBaForm = (QyqssdsBaForm) SbzlProxy.getInstance().process(vo);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		request.setAttribute(mapping.getAttribute(), qyqssdsBaForm);
		return mapping.findForward("Query");
	}
	/**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doBack (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return new ActionForward(QyqssdsActionHelper.PAGE_QYQSSDS_QUERY);
	}
	/**
     * ����ҳ��
     * @param mapping struts.action.ActionMapping
     * @param form QysdsnbForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doPrintHz (ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response)throws BaseException
	{
    	return mapping.findForward("PrintHz");
	}
}
