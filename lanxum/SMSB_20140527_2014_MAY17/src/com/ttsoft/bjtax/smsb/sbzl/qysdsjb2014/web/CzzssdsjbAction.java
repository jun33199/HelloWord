package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.QysdsUtil2014;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsCzzsNb2014Util;
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
 * Description:2014����������ҵ����˰����
 * </p>
 *
 * @author gaoyh
 * @version 1.2
 */

public class CzzssdsjbAction  extends SmsbAction {
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
		httpServletRequest
				.setAttribute(
						CodeConstant.SMSB_HEADER_POSITION,
						"<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;��ҵ����˰��(��)��Ԥ����˰�걨��(A��)</td>");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
				"2014��ҵ����˰�����˰�걨��");
		httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
				"help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

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
			// ��ȡCzzssdsjbForm����
			CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
			// ��ȡ��ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼��������
			CzzssdsjbForm.setLrr(userData.getYhid());
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������CzzssdsjbForm
			vo.setData(CzzssdsjbForm);
			// ���ñ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
			vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_CZZSSDS_PROCESSOR);
			
			// ����Proxy����ʼ��CzzssdsjbForm��ֵ
			CzzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			//��ʼ�������
			QysdsUtil2014.initCodeTable(CzzssdsjbForm);
			// ��CzzssdsjbForm ����request��
			request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);

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
	 *            CzzssdsjbForm
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
		// ��ǰ��ActionForm---CzzssdsjbForm
		CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ���ñ���������
		CzzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		System.out.println("getBbqlx====="+CzzssdsjbForm.getBbqlx());
		// ����˰����������
		// CzzssdsjbForm.setSwjsjdm(userData.getXtsbm1());
		// ����¼��������
		CzzssdsjbForm.setLrr(userData.getYhid());
		/*----*/
		
		CheckBean checkBean = new CheckBean();	
		try{
		checkBean.setJsjdm(CzzssdsjbForm.getJsjdm());

		checkBean.setSkssrqq(CzzssdsjbForm.getSkssksrq());
		checkBean.setSkssrqz(CzzssdsjbForm.getSkssjsrq());
//		checkBean.createZgrqByCurrenttimeM();

		QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
		}catch(Exception e){
			//���������׳��쳣����Ҫ���������͸�ֵ��ǰ̨�п���
			checkBean.setJdlx("-1");
		}
		/*----*/
		SWDJJBSJ djsj = null;
		// �����ҵ�Ǽǻ�����Ϣ
		try {
				djsj = InterfaceDj.getJBSJ_New(CzzssdsjbForm.getJsjdm(), userData);
		} catch (Exception e) {
					// TODO Auto-generated catch block
					throw ExceptionUtil.getBaseException(e);
		}
		String sybs=FriendHelper.getNsrSybs(djsj);
		CzzssdsjbForm.setSybs(sybs);
		CzzssdsjbForm.setIsQuery("1");
		
		Timestamp skssksrq = QysdsUtil2014.getTimestamp(CzzssdsjbForm.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			CzzssdsjbForm.setIsXky("Y");
        }else{
        	CzzssdsjbForm.setIsXky("N");
        }
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������CzzssdsjbForm
		vo.setData(CzzssdsjbForm);
		// ����ProxyҪ���õ�processor����---HdzssdsjbProcessor
		vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_CZZSSDS_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			//����У��
//			CheckBean checkBean = this.setCheckInf(CzzssdsjbForm);	
//			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			// ����Proxy��ִ��processor,��ȡCzzssdsjbForm����ֵ
			CzzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			//��������
			CzzssdsjbForm.setJdlx(checkBean.getJdlx());
			// ��CzzssdsjbForm����request,��Ϊ����������
			//��ʼ�������
			QysdsUtil2014.initCodeTable(CzzssdsjbForm);
//			CzzssdsjbForm.setJdlx(checkBean.getJdlx());
			request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			// �����ɹ���ת
			return mapping.findForward("Show");
		} catch (Exception ex) {
			CzzssdsjbForm.reset(mapping,request);
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * �����걨����
	 *
	 * @param mapping
	 *            struts.action.ActionMapping
	 * @param form
	 *            CzzssdsjbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 * @throws ApplicationException 
	 * @throws com.syax.frame.exception.ApplicationException 
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException, com.syax.frame.exception.ApplicationException {
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
		ActionForward forward = doHandleToken(mapping, request);
		if (forward != null) {
			return forward;
		}
		// ��ǰ��ActionForm---CzzssdsjbForm
		CzzssdsjbForm czzssdsjbForm = (CzzssdsjbForm) form;
		QysdsUtil2014 qysdsUtil = new QysdsUtil2014();
		//�����¼�������룬�����Ѷ�ʧ
		String jsjdm=czzssdsjbForm.getJsjdm();
		
		SWDJJBSJ djsj = null;
		
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(czzssdsjbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		String sybs=FriendHelper.getNsrSybs(djsj);
		czzssdsjbForm.setSybs(sybs);
		
		czzssdsjbForm.setIsQuery("1");

		Timestamp skssksrq = QysdsUtil2014.getTimestamp(czzssdsjbForm.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			czzssdsjbForm.setIsXky("Y");
        }else{
        	czzssdsjbForm.setIsXky("N");
        }
		/*--modified by huohb 2014-07-30--*/
		CheckBean checkBean = new CheckBean();
		try{
		checkBean.setJsjdm(czzssdsjbForm.getJsjdm());

		checkBean.setSkssrqq(czzssdsjbForm.getSkssksrq());
		checkBean.setSkssrqz(czzssdsjbForm.getSkssjsrq());
//		checkBean.createZgrqByCurrenttimeM();
		checkBean.setSkssrqq(czzssdsjbForm.getSkssksrq());
		checkBean.setSkssrqz(czzssdsjbForm.getSkssjsrq());

		QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
		}catch(Exception e){
			//���������͸�ֵ����Ϊǰ̨�ж�Ϊ�յ�ʱ��Ҫ��
			checkBean.setJdlx("-1");
		}
		
//		if(sybs.equals(CodeConstant.CODE_QYSDS_SYBS_OTHER)){
//			// ��CzzssdsnbForm����request,��Ϊ����������
//			request.setAttribute(mapping.getAttribute(), czzssdsjbForm);
//			throw new ApplicationException("����ҵ��˰Դ��ʶ��Ϊ�ܡ��ء��֣�����Ҫ��д��ҵ����˰��(��)��Ԥ����˰�걨��(A��)��!");
//		}
		
		if(!checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_DLNSR)&&checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR)
				&&checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR)&&checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR)){
			request.setAttribute(mapping.getAttribute(), czzssdsjbForm);
			throw new ApplicationException("����ҵ����Ҫ��д��ҵ����˰�£�������Ԥ����˰�걨��A�ࣩ��");
		}
		/*--modified end--*/
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		// ����¼��������
		czzssdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����CzzssdsjbForm��DataList�У�
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzssdsjbForm.setQysdsjbList(getRequestValuesToList(request,
				czzssdsjbForm));
		czzssdsjbForm.setCbsb_qysdsjbList(SfRequestUtil.getValuesToList(request,czzssdsjbForm.getCbsb_columns()));
		czzssdsjbForm.setCbsbMssrxmList(SfRequestUtil.getValuesToList(request,czzssdsjbForm.getCbsbMssrxmCol()));
		czzssdsjbForm.setCbsbJzmzxmList(SfRequestUtil.getValuesToList(request,czzssdsjbForm.getCbsbJzmzxmCol()));
		// ��CzzssdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czzssdsjbForm);

		Map pData = new HashMap();
		String nsfsNow = czzssdsjbForm.getNsfs();//request.getParameter("nsfs");
		String zfjgNow = czzssdsjbForm.getZfjg();//request.getParameter("zfjg");
		
		pData.put("czzssdsjbForm", czzssdsjbForm);
		pData.put("userData", userData);
		pData.put("nsfsNow", nsfsNow);
		pData.put("zfjgNow", zfjgNow);

		//��ѯA�������˰�������ֻܷ���
		HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
		if(("".equals(czzssdsjbForm.getNsfs_fsjg().get("nsfs"))) || czzssdsjbForm.getNsfs_fsjg().get("nsfs")==null){
			czzssdsjbForm.getNsfs_fsjg().put("nsfs", "0");
		}
		if(("".equals(czzssdsjbForm.getNsfs_fsjg().get("zfjg"))) || czzssdsjbForm.getNsfs_fsjg().get("zfjg")==null){
			czzssdsjbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		if(czzssdsjbForm.getNsfs_fsjg().get("nsfs").equals("2")){
			czzssdsjbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		nsfs_zfjg.put("nsfs_old", czzssdsjbForm.getNsfs_fsjg().get("nsfs"));
		nsfs_zfjg.put("zfjg_old", czzssdsjbForm.getNsfs_fsjg().get("zfjg"));

		System.out.println("save: nsfs_old============" + nsfs_zfjg.get("nsfs_old"));
		System.out.println("save: zfjg_old============" + nsfs_zfjg.get("zfjg_old"));
		System.out.println("save: nsfs_now============" + nsfsNow);
		System.out.println("save: zfjg_now============" + zfjgNow);
		
		pData.put("nsfs_zfjg", nsfs_zfjg);

		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzssdsjbForm);
		vo.setUserData(userData);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_CZZSSDS_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡCzzssdsjbForm����ֵ
			czzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);

			// ��CzzssdsjbForm�еĻ��������ÿ�
			//if(!("1".equals(request.getParameter("lje_nsfs"))&&"1".equals(request.getParameter("lje_zfjg")))){
			if(!("1".equals(czzssdsjbForm.getNsfs()))){
				czzssdsjbForm.reset(mapping, request);
				czzssdsjbForm.setQysdsjbList(new ArrayList());
			}
			czzssdsjbForm.setSAVE_FLAG("1");//�޸ı�־λ
			System.out.println("�洢���="+czzssdsjbForm.getSAVE_FLAG());
			
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
			// ��CzzssdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzssdsjbForm);
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����������ҵ��ҵ����˰�����걨����ɹ���");
			
			czzssdsjbForm.setJsjdm(jsjdm);//����������ݱ��ÿ��ˣ������������븳ֵ

			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����������ҵ��ҵ����˰�����걨����ʧ�ܣ�"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	

	/**
	 * ��תҳ������
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
	public ActionForward doJump(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		try {
			System.out.println("I am jumping....");
			// ��ȡCzzssdsjbForm����
			CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;

			// ��CzzssdsjbForm ����request��
			//request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			//
			ZfjgqysdsjbForm zfjgForm=new ZfjgqysdsjbForm();
			zfjgForm.setJsjdm(CzzssdsjbForm.getJsjdm());
			request.setAttribute("zfjgForm", zfjgForm);
			request.setAttribute("czzssdsjbForm", CzzssdsjbForm);
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
	 *            CzzssdsjbForm
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
		// ��ǰ��ActionForm---CzzssdsjbForm
		CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
		// ����¼��������
		CzzssdsjbForm.setLrr(userData.getYhid());
		// ���ñ���������
		CzzssdsjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����CzzssdsjbForm��DataList�У�
		CzzssdsjbForm.setQysdsjbList(getRequestValuesToList(request,
				CzzssdsjbForm));
		// ��CzzssdsjbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
		
		QysdsUtil2014 qysdsUtil = new QysdsUtil2014();
		Map pData = new HashMap();
		pData.put("czzssdsjbForm", CzzssdsjbForm);
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(CzzssdsjbForm);
		vo.setProcessor(CodeConstant.SBZL_QYSDSJB2014_CZZSSDS_PROCESSOR);
		vo.setUserData(userData);
		try {
			//ɾ���ӱ�������
			qysdsUtil.deleteCascadeZfjgData(pData);
			
			// ����Proxy��ִ��processor,��ȡCzzssdsjbForm����ֵ
			CzzssdsjbForm = (CzzssdsjbForm) SbzlProxy.getInstance().process(vo);
			// ��CzzssdsjbForm�еĻ��������ÿ�
			CzzssdsjbForm.reset(mapping, request);
			// ��CzzssdsjbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			
			// �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����������ҵ��ҵ����˰�����걨����ʧ�ܣ�"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	private List getRequestValuesToList(HttpServletRequest request,CzzssdsjbForm czzssdsjbForm){
		List arrayList=new ArrayList();
		Map map=null;
		map=new HashMap();
		map.put("hc","1");
		System.out.println("czzssdsjbForm.getNsfs()="+czzssdsjbForm.getNsfs());
		//map.put("lje",request.getParameter("lje_nsfs"));
		map.put("lje",czzssdsjbForm.getNsfs());
		arrayList.add(map);
		map=new HashMap();
		map.put("hc","2");
		System.out.println("czzssdsjbForm.getZfjg()="+czzssdsjbForm.getZfjg());
		//map.put("lje",request.getParameter("lje_zfjg"));
		map.put("lje",czzssdsjbForm.getZfjg());
		arrayList.add(map);
		List tmpList=SfRequestUtil.getValuesToList(request,czzssdsjbForm.getColumns());
		for(int i=0;i<tmpList.size();i++){
			arrayList.add(tmpList.get(i));
		}		
		return arrayList;
	}

	/**
	 * @Description: TODO ����У�����
	 * @param CzzssdsjbForm
	 * @return
	 */
	private CheckBean setCheckInf(CzzssdsjbForm CzzssdsjbForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setCurrentTime(CzzssdsjbForm.getSkssjsrq().substring(0,8));
		checkBean.setVersionStartTime("20140331");
		checkBean.setVersionEndTime("#");
		
		checkBean.setJsjdm(CzzssdsjbForm.getJsjdm());
		
		checkBean.setSkssrqq(CzzssdsjbForm.getSkssksrq());
		checkBean.setSkssrqz(CzzssdsjbForm.getSkssjsrq());
		return checkBean;
	}
	
	/**
	 * ��תҳ������
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
	public ActionForward doJumpGdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {

		try {
			// ��ȡCzzssdsjbForm����
			CzzssdsjbForm CzzssdsjbForm = (CzzssdsjbForm) form;
//			System.out.println("jsjdm: jump "+CzzssdsjbForm.getJsjdm());
			request.setAttribute("jumpFlag", "czzssdsjb2014");
			
			return mapping.findForward("JumpGdzc");

		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
}
