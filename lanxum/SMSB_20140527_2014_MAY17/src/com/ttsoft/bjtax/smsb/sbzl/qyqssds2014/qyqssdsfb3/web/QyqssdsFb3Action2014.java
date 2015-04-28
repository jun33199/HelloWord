/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2009-2009 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base.QyqssdsBaseForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsFb3Action2014   
 * ��������   ��������ʣ��Ʋ�����ͷ�����ϸ��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����11:42:14   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����11:42:14   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsFb3Action2014 extends SmsbAction {

	/**
	 * ����Ա����
	 */
	private UserData userData = null;

	/**
	 * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
	 * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param actionForm
	 *            QyqssdsFb3Form2014
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
				"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">˰�ѹ���</font>&gt;��ҵ��������˰�걨</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ��������˰�걨");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qyqssds-000.htm");
	}

	/**
	 * ��ʼ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb3Form2014
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
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ȡFb3Form����
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) form;
		// ������Ϣ����QyqssdsFb3Form2014��
		this.getBaseForm(request, fb3Form);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ñ��ɷ�ϵͳ�û���Ϣ
		vo.setUserData(userData);
		// ���ú�̨����Actionֵ---SHOWACTION
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// �����������Data����,������QyqssdsFb3Form2014
		vo.setData(fb3Form);
		// ����ProxyҪ���õ�processor����---QyqssdsFb3Processor2014
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.processor.QyqssdsFb3Processor2014");
		try {
			// ����Proxy��ִ��processor,��ȡQyqssdsFb3Form2014����ֵ
			fb3Form = (QyqssdsFb3Form2014) SbzlProxy.getInstance().process(vo);
			// ��QyqssdsFb3Form2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), fb3Form);
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		return mapping.findForward("Show");
	}

	/**
	 * ����ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb3Form2014
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

		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ȡQyqssdsFb3Form2014����
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) form;
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����QyqssdsFb3Form2014��DataList��
		fb3Form.setDataList(SfRequestUtil.getValuesToList(request, fb3Form.getColumns()));
		fb3Form.setSyccfpList(SfRequestUtil.getValuesToList(request, fb3Form.getSyccfp_columns()));
		// ������Ϣ����QyqssdsFb3Form2014��
		this.getBaseForm(request, fb3Form);
		// ��QyqssdsFb3Form2014����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), fb3Form);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setUserData(userData);
		vo.setData(fb3Form);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.processor.QyqssdsFb3Processor2014");
		try {
			// ����Proxy��ִ��processor,��ȡfb3Form����ֵ
			fb3Form = (QyqssdsFb3Form2014) SbzlProxy.getInstance().process(vo);
			// ��QyqssdsFb3Form2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), fb3Form);
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ������ݣ����ͨ������б���
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb3Form2014
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		
		// ��ȡQyqssdsFb3Form2014����
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		
		
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����fb3Form��DataList��
		fb3Form.setDataList(SfRequestUtil.getValuesToList(request, fb3Form.getColumns()));
		fb3Form.setSyccfpList(SfRequestUtil.getValuesToList(request, fb3Form.getSyccfp_columns()));
		// ������Ϣ����QyqssdsFb3Form2014��
		this.getBaseForm(request, fb3Form);
		for (int i = 0; i < fb3Form.getColumns().length; i++) {
			System.out.println("==========column:"+fb3Form.getColumns()[i]);
			
		}
		for (int i = 0; i < fb3Form.getDataList().size(); i++) {
			Map map = (HashMap)fb3Form.getDataList().get(i);
			System.out.println("============test check:"+map.get("ljje")+"==========");
		}

		// ��QyqssdsFb3Form2014����request,��У��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), fb3Form);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_CHECKACTION);
		vo.setData(fb3Form);
		vo.setUserData(userData);
		
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.processor.QyqssdsFb3Processor2014");
		try {
			// ����Proxy��ִ��processor,��ȡQyqssdsFb3Form2014����ֵ
			fb3Form = (QyqssdsFb3Form2014) SbzlProxy.getInstance().process(vo);
			// ��QyqssdsFb3Form2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), fb3Form);
			System.out.println(fb3Form.getSyccfpList().size()+"----���Ի���------");
			
			if(fb3Form.getCheckList()==null){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "У��ͨ����");
			}else if(fb3Form.getCheckList().size()==0){
				request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "У��ͨ����");
			}else if(fb3Form.getCheckList().size()>0){
				request.setAttribute(CodeConstant.CHECK_RESULT_HTML, QyqssdsUtil2014.getCheckResults(fb3Form.getCheckList()));
			}
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ɾ��ҳ������
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb3Form2014
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

		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);

		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) form;
		// ������Ϣ����QyqssdsFb3Form2014��
		this.getBaseForm(request, fb3Form);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setUserData(userData);
		vo.setData(fb3Form);
		vo.setProcessor("com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.processor.QyqssdsFb3Processor2014");
		try {
			// ����Proxy��ִ��processor,��ȡQyqssdsFb3Form2014����ֵ
			fb3Form = (QyqssdsFb3Form2014) SbzlProxy.getInstance().process(vo);
			// ��QyqssdsFb3Form2014����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), fb3Form);
			// �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * �˳�ҳ��
	 * 
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            QyqssdsFb3Form2014
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doExit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		return mapping.findForward("Return");
	}

	/**
	 * ��session�л�ȡ������Ϣ��������QyqssdsFb3Form2014������
	 * 
	 * @param request
	 * @return
	 */
	private void getBaseForm(HttpServletRequest request, QyqssdsFb3Form2014 form) {
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��session�л�ȡ��ҵ����˰�Ļ�����Ϣ
		QyqssdsBaseForm baseForm = (QyqssdsBaseForm) request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM);
		
		String ksrq=request.getParameter("skksrq");
		String jsrq=request.getParameter("skjsrq");
		
		if((ksrq!=null && !"".equals(ksrq))   && (jsrq!=null && !"".equals(jsrq)) ){
			
			request.getSession(false).setAttribute(CodeConstant.SESSIONKEY_QYQSSDSBASEFORM,baseForm);
		}
		
		// �ѻ�����Ϣ���뵽QyqssdsFb3Form2014������
		if (baseForm != null) {
			form.setJsjdm(baseForm.getJsjdm());
			form.setTbrq(baseForm.getTbrq());
			form.setNsrmc(baseForm.getNsrmc());
			form.setQh("1");
			form.setSknd(baseForm.getSknd());
			form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			form.setQssbksrq(baseForm.getQssbksrq());
			form.setQssbjsrq(baseForm.getQssbjsrq());
			form.setSwjsjdm(baseForm.getSwjsjdm());
			form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
			form.setQxdm(baseForm.getQxdm());
			form.setLrr(userData.getYhid());
			form.setSsjjlx(baseForm.getSsjjlx());
			form.setSshy(baseForm.getSshy());
			form.setPreviousTableURL(QyqssdsUtil2014.getTableURL(baseForm, "P_" + CodeConstant.QYQSSDS_TABLE_ID_2014_3));
		}
	}

}