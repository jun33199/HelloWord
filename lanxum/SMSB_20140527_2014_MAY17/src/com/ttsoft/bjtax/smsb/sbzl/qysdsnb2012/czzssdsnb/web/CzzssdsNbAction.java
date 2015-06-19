package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.creports.Constants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.QysdsNb2012Util;
import com.ttsoft.bjtax.smsb.util.FriendHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlx;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:2012 ��ҵ����˰��֧���������˰�걨��
 * </p>
 *
 * @author wangcy
 * @version 1.0
 */

public class CzzssdsNbAction  extends SmsbAction {
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

	protected void initialRequest(ActionMapping mapping, ActionForm actionForm,HttpServletRequest httpServletRequest, HttpServletResponse response)
	{
		super.initialRequest(mapping, actionForm, httpServletRequest, response);
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰��֧���������˰�걨��</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"��ҵ����˰��֧���������˰�걨��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

	}

	/**
	 * ��ʼ��ҳ������
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            czzssdsNbForm
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
			// ��ȡczzssdsNbForm����
			CzzssdsNbForm czzssdsNbForm = new CzzssdsNbForm();
			// ��ȡ��ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼��������
			czzssdsNbForm.setLrr(userData.getYhid());
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������czzssdsNbForm
			vo.setData(czzssdsNbForm);
			// ���ñ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---CzzssdsNbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
			// ����Proxy����ʼ��czzssdsNbForm��ֵ
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);
			// ��czzssdsNbForm ����request��
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
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
	 *            czzssdsNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws BaseException
	{
		// ��ǰ��ActionForm---czzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(czzssdsNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzssdsNbForm.setSybs(sybs);
		czzssdsNbForm.setIsQuery("1");

		
		
		Timestamp skssksrq = QysdsNb2012Util.getTimestamp(czzssdsNbForm.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			throw new ApplicationException("����ҵ˰��Ǽǿ�ҵ�Ǽ�������˰���������ڣ�������д��ҵ����˰��֧���������˰�걨��!");
        }
		// ���ñ���������
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		System.out.println("getBbqlx====="+czzssdsNbForm.getBbqlx());
		// ����¼��������
		czzssdsNbForm.setLrr(userData.getYhid());

		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������CzzssdsnbForm
		vo.setData(czzssdsNbForm);
		// ����ProxyҪ���õ�processor����
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(czzssdsNbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			if(!checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR)){
				// ��CzzssdsnbForm����request,��Ϊ����������
				request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
				throw new ApplicationException("����ҵ���ǿ�ʡ�з�֧������������д��ҵ����˰��֧���������˰�걨��!");
			}
				
			// ����Proxy��ִ��processor,��ȡCzzssdsnbForm����ֵ
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);	
			czzssdsNbForm.setJdlx(checkBean.getJdlx());
			if("04".equals(checkBean.getJdlx()))
			{checkBean.setJdlx("01");}
			czzssdsNbForm.setSybs(checkBean.getJdlx());
			
			// ��CzzssdsnbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
		} catch (Exception ex) {
			czzssdsNbForm.reset(mapping,request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
		// �����ɹ���ת
		return mapping.findForward("Show");
	}
	
	
	/**
	 * �����걨����
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            czzssdsNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 * @throws ApplicationException 
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws BaseException, ApplicationException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// ��ǰ��ActionForm---CzzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		QysdsNb2012Util qysdsUtil = new QysdsNb2012Util();
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		
		SWDJJBSJ djsj = null;
		
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(czzssdsNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzssdsNbForm.setSybs(sybs);
		String sybs="";
		sybs = czzssdsNbForm.getSybs();
		czzssdsNbForm.setIsQuery("1");

		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// ��CzzssdsnbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			throw new ApplicationException("����ҵ��˰Դ��ʶ��Ϊ�֣���д����ҵ����˰��֧���������˰�걨�����ݲ��豣��!");
		}
		
		// ����¼��������
		czzssdsNbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����CzzssdsNbForm��DataList�У�
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzssdsNbForm.setQysdsnbList(getRequestValuesToList(request,czzssdsNbForm));
		// ��CzzssdsNbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czzssdsNbForm);

		Map pData = new HashMap();
		
		String nsfsNow = czzssdsNbForm.getNsfs();//request.getParameter("lje_nsfs");
		String zfjgNow =czzssdsNbForm.getZfjg(); //request.getParameter("lje_zfjg");
		
		pData.put("czzssdsNbForm", czzssdsNbForm);
		pData.put("userData", userData);
		pData.put("nsfsNow", nsfsNow);
		pData.put("zfjgNow", zfjgNow);

		//��ѯA�������˰�������ֻܷ���
		HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
		if(("".equals(czzssdsNbForm.getNsfs_fsjg().get("nsfs"))) || czzssdsNbForm.getNsfs_fsjg().get("nsfs")==null){
			czzssdsNbForm.getNsfs_fsjg().put("nsfs", "0");
		}
		if(("".equals(czzssdsNbForm.getNsfs_fsjg().get("zfjg"))) || czzssdsNbForm.getNsfs_fsjg().get("zfjg")==null){
			czzssdsNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		if(czzssdsNbForm.getNsfs_fsjg().get("nsfs").equals("2")){
			czzssdsNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		nsfs_zfjg.put("nsfs_old", czzssdsNbForm.getNsfs_fsjg().get("nsfs"));
		nsfs_zfjg.put("zfjg_old", czzssdsNbForm.getNsfs_fsjg().get("zfjg"));

		System.out.println("save: nsfs_old============" + nsfs_zfjg.get("nsfs_old"));
		System.out.println("save: zfjg_old============" + nsfs_zfjg.get("zfjg_old"));
		System.out.println("save: nsfs_now============" + nsfsNow);
		System.out.println("save: zfjg_now============" + zfjgNow);
		
		pData.put("nsfs_zfjg", nsfs_zfjg);
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzssdsNbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡCzzssdsNbForm����ֵ
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);
			// ��CzzssdsjbForm�еĻ��������ÿ�
			//if(!("1".equals(request.getParameter("lje_nsfs"))&&"1".equals(request.getParameter("lje_zfjg")))){
			if(!("1".equals(czzssdsNbForm.getNsfs()))){
				czzssdsNbForm.reset(mapping, request);
				czzssdsNbForm.setQysdsnbList(new ArrayList());
			}
			czzssdsNbForm.setSAVE_FLAG("1");//�޸ı�־λ
			System.out.println("�洢���="+czzssdsNbForm.getSAVE_FLAG());
			
			System.out.println("===================save process end =========================");
			String nsfsOld = (String)nsfs_zfjg.get("nsfs_old");
			String zfjgOld = (String)nsfs_zfjg.get("zfjg_old");
			
			if(nsfsNow.equals("1")){
				if(!zfjgOld.equals(zfjgNow)){
					qysdsUtil.deleteCascadeZfjgData(pData);
				}
				qysdsUtil.saveCascadeZfjgData(pData);
			}else{
				qysdsUtil.deleteCascadeZfjgData(pData);
			}			
			// ��CzzssdsNbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "��ҵ����˰��֧���������˰�걨����ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "��ҵ����˰��֧���������˰�걨����ʧ�ܣ�"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	private List getRequestValuesToList(HttpServletRequest request,CzzssdsNbForm form){
		List arrayList=new ArrayList();
		Map map=null;
		map=new HashMap();
		map.put("hc","1");
		
		map.put("lje",form.getNsfs());
		arrayList.add(map);
		map=new HashMap();
		map.put("hc","2");
		
		map.put("lje",form.getZfjg());
		arrayList.add(map);
		List tmpList=SfRequestUtil.getValuesToList(request,form.getColumns());
		for(int i=0;i<tmpList.size();i++){
			arrayList.add(tmpList.get(i));
		}
		return arrayList;
	}
	
	
	/**
	 * ��תҳ������
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            CzzssdsNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */
	public ActionForward doJump(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		System.out.println("I am jumping....");
		// ��ǰ��ActionForm---CzzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(czzssdsNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzssdsNbForm.setSybs(sybs);
		String sybs = czzssdsNbForm.getSybs();
		czzssdsNbForm.setIsQuery("1");
		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// ��CzzssdsnbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			throw new ApplicationException("����ҵ��˰Դ��ʶ��Ϊ�֣���д����ҵ����˰��֧���������˰�걨�����ݲ��豣��!");
		}
		// ����¼��������
		czzssdsNbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����CzzssdsNbForm��DataList�У�
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzssdsNbForm.setQysdsnbList(getRequestValuesToList(request,czzssdsNbForm));
		try {
			// ��zfjgqysdsNbForm ����request��
			ZfjgqysdsNbForm zfjgqysdsNbForm=new ZfjgqysdsNbForm();
			zfjgqysdsNbForm.setJsjdm(czzssdsNbForm.getJsjdm());
			System.out.println("czzssdsNbForm.getJsjdm()"+czzssdsNbForm.getJsjdm());
			
			//��czzssdsNbForm����session��
			request.getSession(false).setAttribute("REQ_KEY_CZZSSDSNBFORM", czzssdsNbForm);
			
			request.setAttribute("zfjgnb2012Form", zfjgqysdsNbForm);
			request.setAttribute("czzssdsnb2012Form", czzssdsNbForm);
			
			if(czzssdsNbForm.getQueryFlag().equals("0")){
				return mapping.findForward("JumpNew");
			}
			return mapping.findForward("Jump");

		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	/**
	 * ɾ���걨����
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            CzzssdsNbForm
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
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ��ǰ��ActionForm---czzssdsNbForm
		CzzssdsNbForm czzssdsNbForm = (CzzssdsNbForm) form;
		// ����¼��������
		czzssdsNbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czzssdsNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����CzzssdsjbForm��DataList�У�
		czzssdsNbForm.setQysdsnbList(getRequestValuesToList(request,
				czzssdsNbForm));
		// ��CzzssdsjbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
		
		QysdsNb2012Util qysdsUtil = new QysdsNb2012Util();
		Map pData = new HashMap();
		pData.put("czzssdsNbForm", czzssdsNbForm);
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(czzssdsNbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSNB2012_CZZSSDS_PROCESSOR);
		vo.setUserData(userData);
		try {
			//ɾ���ӱ�������
			qysdsUtil.deleteCascadeZfjgData(pData);
			
			// ����Proxy��ִ��processor,��ȡczzssdsNbForm����ֵ
			czzssdsNbForm = (CzzssdsNbForm) SbzlProxy.getInstance().process(vo);
			// ��czzssdsNbForm�еĻ��������ÿ�
			czzssdsNbForm.reset(mapping, request);
			// ��czzssdsNbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzssdsNbForm);
			
			// �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����������ҵ��ҵ����˰�����걨����ʧ�ܣ�"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	 /**
	 * @Description: TODO ����У�����
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(CzzssdsNbForm czzssdsNbForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setCurrentTime(czzssdsNbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2013");
		checkBean.setVersionEndTime("2013");
		
		checkBean.setJsjdm(czzssdsNbForm.getJsjdm());
		
		checkBean.setSkssrqq(czzssdsNbForm.getSkssksrq());
		checkBean.setSkssrqz(czzssdsNbForm.getSkssjsrq());
		return checkBean;
	}
}
