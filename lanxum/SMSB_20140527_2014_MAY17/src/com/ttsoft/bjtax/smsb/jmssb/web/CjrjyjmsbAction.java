package com.ttsoft.bjtax.smsb.jmssb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


public class CjrjyjmsbAction extends SmsbAction {

	private UserData userData = null;

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;���òм��˾�ҵ��ҵӪҵ˰����˰�걨��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"���òм��˾�ҵ��ҵӪҵ˰����˰�걨��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/cjrjyjmsb/Cjrjyjmsb-000.htm");
	}

	public ActionForward doShow(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse response) throws BaseException {
		// ��ǰ��ActionForm
		CjrjyjmsForm form = (CjrjyjmsForm) actionForm;
		userData = this.getUserData(httpServletRequest);
		form.setLrr(userData.getYhid());
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		// �����������Data����
		vo.setData(form);
		vo.setUserData(userData);
		// ����ProxyҪ���õ�processor������
		vo
				.setProcessor(CodeConstant.JMSSB_CJRJYJMSB_PROCESSOR);
		try {
			// ����Proxy����ʼ��ActionForm��ֵ
			form = (CjrjyjmsForm) SbzlProxy.getInstance().process(vo);
			//���ڲ��Դ�����������
			//System.out.println(form.getLrrq() + "------------------");
			httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
			return actionMapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			Debug.out("-------------------------");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public ActionForward doQuery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		CjrjyjmsForm form = (CjrjyjmsForm) actionForm;
		userData = this.getUserData(httpServletRequest);
		form.setLrr(userData.getYhid());
		// ����Ĭ��չ����Ҫ����������
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,�������������ActionForm
		vo.setUserData(userData);
		vo.setData(form);
		// ����ProxyҪ���õ�processor������
		vo
				.setProcessor(CodeConstant.JMSSB_CJRJYJMSB_PROCESSOR);
		try {
			// ����Proxy����ʼ��ActionForm��ֵ
			//System.out.println("!!!!!!!!!!!!!!!!!!"+form.getIsError());
			form = (CjrjyjmsForm) SbzlProxy.getInstance().process(vo);
			// ��ת
			//System.out.println("===============sing"+form.getSign_re());
			if("01".equals(form.getSign_re())){
				//System.out.println("=============ceshi===============");
				form.resetA(actionMapping, httpServletRequest);
			}
				httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
				httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
						form.getIsError());
			return actionMapping.findForward("Query");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			Debug.out("-------------------------");
			form.reset(actionMapping, httpServletRequest);
			form.setJsjdm("");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public ActionForward doSave(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
		if (forward != null) {
			return forward;
		}

		// ��ǰ��ActionForm
		CjrjyjmsForm form = (CjrjyjmsForm) actionForm;
		userData = this.getUserData(httpServletRequest);
		form.setLrr(userData.getYhid());
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setUserData(userData);
		// �����������Data����,�������������ActionForm
		vo.setData(form);
		// ����ProxyҪ���õ�processor������
		vo.setProcessor("com.ttsoft.bjtax.smsb.jmssb.processor.CjrjyjmsbProcessor");
		try {
			// ����Proxy����ʼ��ActionForm��ֵ
			form = (CjrjyjmsForm) SbzlProxy.getInstance()
					.process(vo);
				//form.reset(actionMapping, httpServletRequest);
				httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
				httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
						form.getIsError());
			//}
			return actionMapping.findForward("Save");
			// ��ת
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			form.reset(actionMapping, httpServletRequest);
			form.setJsjdm("");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public ActionForward doDelete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {
		return null;
	}
}
