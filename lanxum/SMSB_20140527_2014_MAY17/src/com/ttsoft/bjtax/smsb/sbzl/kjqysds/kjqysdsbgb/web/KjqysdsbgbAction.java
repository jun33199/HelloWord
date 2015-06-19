/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.dm.BZ;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.kjqysds.*;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web.KjssjksForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �۽���ҵ����˰�����-����ģ��
 * </p>
 * <p>
 * Description:�۽���ҵ����˰�����
 * </p>
 *
 * @author wang xiaomin
 * @version 1.1
 */

public class KjqysdsbgbAction extends SmsbAction {
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
	 *            QysdsnbForm
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
                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�۽���ҵ����˰</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "�۽���ҵ����˰�����");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");
	}
	/**
	 * ��ѯ���걨����
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsjbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doQuery1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		// ��ǰ��ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		kjqysdsbgbForm.setLrr(userData.getYhid());
		//��װǰ̨��ϢBO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//��װ��̨��ϢBO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		//��ǰ̨form����qbo
		qbo.setJsjdm(kjqysdsbgbForm.getJsjdm());
		qbo.setDqzt(kjqysdsbgbForm.getDqzt());
		qbo.setDqy(kjqysdsbgbForm.getDqy());
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION1);
		// �����������Data����,������KjqysdsbgbForm
		vo.setData(qbo);
		// ����ProxyҪ���õ�processor����---KjqysdsbgbForm
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsjbForm����ֵ
			System.out.println("----------kjqysdsbgbAction-------------------");
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			//��hbo���յ�����Ϣ����form��
			kjqysdsbgbForm.setQysdsbgbList(hbo.getQysdsbgbList());
			kjqysdsbgbForm.setZts(hbo.getZts());
			kjqysdsbgbForm.setZys(hbo.getZys());
			kjqysdsbgbForm.setDqy(hbo.getDqy());
			kjqysdsbgbForm.setOldzt(kjqysdsbgbForm.getDqzt());
			// ��kjqysdsbgbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
			// �����ɹ���ת
			return mapping.findForward("Show");
		} catch (Exception ex) {
			kjqysdsbgbForm.reset(mapping,request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}


	/**
	 * ��ʼ��ҳ������
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsjbForm
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

		try {
			// ��ȡHdzssdsjbForm����
			KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
			//��װǰ̨��ϢBO
			KjqysdsbgbBO qbo = new KjqysdsbgbBO();
			//��װ��̨��ϢBO
			KjqysdsbgbBO hbo = new KjqysdsbgbBO();
			// ��ȡ���ɷ�ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼��������
			kjqysdsbgbForm.setLrr(userData.getYhid());
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������KjqysdsbgbForm
			vo.setData(qbo);
			// ���ñ��ɷ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---KjqysdsbgbProcessor
			vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
			// ����Proxy����ʼ��HdzssdsjbForm��ֵ
			//hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			// ��KjqysdsbgbForm ����request��
			request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);

			return mapping.findForward("Show");

		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ��ѯ���걨����
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            HdzssdsjbForm
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
		// ��ǰ��ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		kjqysdsbgbForm.setLrr(userData.getYhid());
		//��װǰ̨��ϢBO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//��װ��̨��ϢBO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		//��ǰ̨form����qbo
		//kjqysdsbgbForm.setJsjdm("06025800");
		
		qbo.setJsjdm(kjqysdsbgbForm.getJsjdm());
		qbo.setBadjxh(kjqysdsbgbForm.getBadjxh());
		qbo.setBgbxh(kjqysdsbgbForm.getBgbxh());
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������KjqysdsbgbForm
		vo.setData(qbo);
		// ����ProxyҪ���õ�processor����---KjqysdsbgbForm
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡhdzssdsjbForm����ֵ
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			if(hbo.isFlag()){
				//��hbo���յ�����Ϣ����form��
				kjqysdsbgbForm=botoform(hbo);		
				//��ʼ��ҳ���б�
				// ��ȡsession
		        HttpSession session = request.getSession(false);
				if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_SBSDLX) == null) {
		            List sbsdlxList = hbo.getSbsdlxList();
	
		            // ת��֮����걨��������
		            String[][] sbsdlx = new String[sbsdlxList.size()][2];
		            for (int i = 0; i < sbsdlxList.size(); i++) {
		            	sbsdlx[i][0] = ((SBSDLX) sbsdlxList.get(i)).getSbsdlxdm();
		            	sbsdlx[i][1] = ((SBSDLX) sbsdlxList.get(i)).getSbsdlxmc();
		            }
		            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_SBSDLX, sbsdlx);
		        }
		        // ��ȡ�����б�
		        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) == null) {
		        	List bzList = CodeTableUtil.getCodeTableList(CodeTableKey.BZ);
	
		            // ת��֮��ı���
		            String[][] bz = new String[bzList.size()][2];
		            for (int i = 0; i < bzList.size(); i++) {
		                bz[i][0] = ((BZ) bzList.get(i)).getBzdm();
		                bz[i][1] = ((BZ) bzList.get(i)).getBzmc();
		            }
		            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ, bz);
		        }
				// ��kjqysdsbgbForm����request,��Ϊ����������
				request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
				// �����ɹ���ת
				return mapping.findForward("Query");
			}
			else{
				// ��kjqysdsbgbForm����request,��Ϊ����������
				request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
				// �����ɹ���ת
				request.setAttribute(CodeConstant.SMSB_OTHER_SUCCESS, "�˱���δȷ��,���Ƚ��б���ȷ��!");
				return doQuery1(mapping,kjqysdsbgbForm,request,response);
			}
		} catch (Exception ex) {
			kjqysdsbgbForm.reset(mapping,request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ���汨�������
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form 
	 *            KjqysdsbgbForm
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
		// ��ǰ��ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		//��װǰ̨��ϢBO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//��װ��̨��ϢBO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		// ��ȡ���ɷ�ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		qbo.setLrr(userData.getYhid());
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����KjqysdsbgbForm��DataList�У�
		kjqysdsbgbForm.setQysdsbgbList(SfRequestUtil.getValuesToList(request,
				kjqysdsbgbForm.getBgb_columns()));
		//¼����
		kjqysdsbgbForm.setLrr(userData.getYhid());
		System.out.println("-------------"+kjqysdsbgbForm.getBahtxx().getHtbh());
		// ��kjqysdsbgbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
		
		// ��form����hbo
		qbo=this.formtobo(kjqysdsbgbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(qbo);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡhbo����ֵ
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			kjqysdsbgbForm.setBgbxh(hbo.getBgbxh());

			/*KjssjksForm kjssjksForm=new KjssjksForm();
			kjssjksForm.setJsjdm(kjqysdsbgbForm.getJsjdm());
			kjssjksForm.setBadjxh(kjqysdsbgbForm.getBadjxh());
			kjssjksForm.setBgbxh(kjqysdsbgbForm.getBgbxh());
			System.out.println("kjqysdsbgbForm--------"+kjqysdsbgbForm.getBgbxh());*/
			request.setAttribute("bgbxh",kjqysdsbgbForm.getBgbxh());
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "�������ɹ�,��������߽ɿ��飡");
			return mapping.findForward("Save");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}


	/**
	 * ɾ�����������
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            KjqysdsbgbForm
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
		// ��ǰ��ActionForm---HdzssdsjbForm
		KjqysdsbgbForm kjqysdsbgbForm = (KjqysdsbgbForm) form;
		//��װǰ̨��ϢBO
		KjqysdsbgbBO qbo = new KjqysdsbgbBO();
		//��ǰ̨form���ݷ���qbo
		qbo.setJsjdm(kjqysdsbgbForm.getJsjdm());
		qbo.setBadjxh(kjqysdsbgbForm.getBadjxh());
		qbo.setBgbxh(kjqysdsbgbForm.getBgbxh());
		qbo.setDqzt(kjqysdsbgbForm.getDqzt());
		//��װ��̨��ϢBO
		KjqysdsbgbBO hbo = new KjqysdsbgbBO();
		// ����¼��������
		kjqysdsbgbForm.setLrr(userData.getYhid());
		// ��hkjqysdsbgbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(qbo);
		vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
		vo.setUserData(userData);
		try {
			// ����Proxy��ִ��processor,��ȡhbo����ֵ
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			//����ȡֵ
			vo.setAction(CodeConstant.SMSB_QUERYACTION1);
			vo.setData(qbo);
			vo.setProcessor(CodeConstant.SBZL_KJQYSDSBGB_PROCESSOR);
			// ����Proxy��ִ��processor,��ȡhbo����ֵ
			hbo = (KjqysdsbgbBO) SbzlProxy.getInstance().process(vo);
			//��hbo���յ�����Ϣ����form��
			kjqysdsbgbForm.setQysdsbgbList(hbo.getQysdsbgbList());
			kjqysdsbgbForm.setZts(hbo.getZts());
			kjqysdsbgbForm.setZys(hbo.getZys());
			kjqysdsbgbForm.setDqy(hbo.getDqy());
			kjqysdsbgbForm.setDqzt(hbo.getDqzt());
			// ��kjqysdsbgbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), kjqysdsbgbForm);
			// �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	/**
	 * ��form�е����ݷ���bo
	 * @param form
	 * @return bo
	 */
	private KjqysdsbgbBO formtobo(KjqysdsbgbForm form){
		KjqysdsbgbBO bo=new KjqysdsbgbBO();
		
		bo.setQysdsbgbList(form.getQysdsbgbList());
		bo.setFjmqyxx(form.getFjmqyxx());
		bo.setKjywrxx(form.getKjywrxx());
		bo.setBahtxx(form.getBahtxx());
		System.out.println("bo.htbh---"+form.getBahtxx().getHtbh());
		bo.setSbsdlxdm(form.getSbsdlxdm());
		bo.setSbsdqdrq(form.getSbsdqdrq());
		bo.setJsjdm(form.getJsjdm());
		bo.setBadjxh(form.getBadjxh());
		bo.setLrr(form.getLrr());
		bo.setBgbxh(form.getBgbxh());
		System.out.println("bo.bgbxh------"+bo.getBgbxh()+"form.bgxh---------"+form.getBgbxh());
		bo.setSkssksrq(form.getSkssksrq());
		bo.setSkssjsrq(form.getSkssjsrq());
		return bo;
	}
	/**
	 * ��bo�е����ݷ���form
	 * @param bo
	 * @return form
	 */
	private KjqysdsbgbForm botoform(KjqysdsbgbBO bo){
		KjqysdsbgbForm form=new KjqysdsbgbForm();
		
		//��bo���յ�����Ϣ����form��
		form.setSkssksrq(bo.getSkssksrq());
		form.setSkssjsrq(bo.getSkssjsrq());
		form.setKjywrxx(bo.getKjywrxx());
		form.setFjmqyxx(bo.getFjmqyxx());
		form.setBahtxx(bo.getBahtxx());
		form.setQysdsbgbList(bo.getQysdsbgbList());
		form.setSbsdlxdm(bo.getSbsdlxdm());
		form.setSbsdqdrq(bo.getSbsdqdrq());
		form.setJsjdm(bo.getJsjdm());
		form.setBadjxh(bo.getBadjxh());
		form.setBgbxh(bo.getBgbxh());
		return form;
	}
	
	
}
