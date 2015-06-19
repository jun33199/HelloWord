package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsConstant2014;
/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:2014 ��ҵ����˰��֧���������˰�걨��
 * </p>
 *
 * @author wangcy
 * @version 1.0
 */

public class CzzsfzjgNbAction  extends SmsbAction {
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
	 *            czzsfzjgNbForm
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
			// ��ȡczzsfzjgNbForm����
			CzzsfzjgNbForm czzsfzjgNbForm = new CzzsfzjgNbForm();
			// ��ȡ��ϵͳ�û���Ϣ
			UserData userData = this.getUserData(request);
			// ����¼��������
			czzsfzjgNbForm.setLrr(userData.getYhid());
			// ��ʼ�����ݴ�������
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ---SHOWACTION
			vo.setAction(CodeConstant.SMSB_SHOWACTION);
			// �����������Data����,������czzsfzjgNbForm
			vo.setData(czzsfzjgNbForm);
			// ���ñ�ϵͳ�û���Ϣ
			vo.setUserData(userData);
			// ����ProxyҪ���õ�processor����---CzzssdsNbProcessor
			vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
			// ����Proxy����ʼ��czzsfzjgNbForm��ֵ
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);
			// ��czzsfzjgNbForm ����request��
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
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
	 *            czzsfzjgNbForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return actionMapping.findForward����תĿ��
	 * @throws BaseException
	 *             ϵͳ��׽�쳣�������쳣�����׳�
	 */

	public ActionForward doQuery(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws BaseException
	{System.out.println("doquery��������������������������������������");
		// ��ǰ��ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(czzsfzjgNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzsfzjgNbForm.setSybs(sybs);
		czzsfzjgNbForm.setIsQuery("1");

		
		
		Timestamp skssksrq = QysdsCzzsNb2014Util.getTimestamp(czzsfzjgNbForm.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			throw new ApplicationException("����ҵ˰��Ǽǿ�ҵ�Ǽ�������˰���������ڣ�������д��ҵ����˰��֧���������˰�걨��!");
        }
		// ���ñ���������
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		System.out.println("getBbqlx====="+czzsfzjgNbForm.getBbqlx());
		// ����¼��������
		czzsfzjgNbForm.setLrr(userData.getYhid());

		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ---QUERYACTION
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,������czzsfzjgNbForm
		vo.setData(czzsfzjgNbForm);
		// ����ProxyҪ���õ�processor����
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
		// ����ϵͳ�û���Ϣ
		vo.setUserData(userData);
		try {
			
			//����У��
			CheckBean checkBean = this.setCheckInf(czzsfzjgNbForm);	
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			//if(!checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR)){
			if(!CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())){
				// ��czzsfzjgNbForm����request,��Ϊ����������
				request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
				throw new ApplicationException("����ҵ���ǿ�ʡ�з�֧������������д��ҵ����˰��֧���������˰�걨��!");
			}
				
			// ����Proxy��ִ��processor,��ȡczzsfzjgNbForm����ֵ
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);	
			czzsfzjgNbForm.setJdlx(checkBean.getJdlx());
			if("04".equals(checkBean.getJdlx()))
			{checkBean.setJdlx("01");}
			czzsfzjgNbForm.setSybs(checkBean.getJdlx());
			
			// ��czzsfzjgNbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
		} catch (Exception ex) {
			czzsfzjgNbForm.reset(mapping,request);
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
	 *            czzsfzjgNbForm
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
		// ��ǰ��ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		QysdsCzzsNb2014Util qysdsUtil = new QysdsCzzsNb2014Util();
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		
		SWDJJBSJ djsj = null;
		
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(czzsfzjgNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzsfzjgNbForm.setSybs(sybs);
		String sybs="";
		sybs = czzsfzjgNbForm.getSybs();
		czzsfzjgNbForm.setIsQuery("1");

		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// ��czzsfzjgNbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			throw new ApplicationException("����ҵ��˰Դ��ʶ��Ϊ�֣���д����ҵ����˰��֧���������˰�걨�����ݲ��豣��!");
		}
		
		// ����¼��������
		czzsfzjgNbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czzsfzjgNbForm��DataList�У�
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzsfzjgNbForm.setQysdsnbList(getRequestValuesToList(request,czzsfzjgNbForm));
		// ��CzczzsfzjgNbFormrequest,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);

		Map pData = new HashMap();
		
		String nsfsNow = czzsfzjgNbForm.getNsfs();//request.getParameter("lje_nsfs");
		String zfjgNow =czzsfzjgNbForm.getZfjg(); //request.getParameter("lje_zfjg");
		
		pData.put("czzsfzjgNbForm", czzsfzjgNbForm);
		pData.put("userData", userData);
		pData.put("nsfsNow", nsfsNow);
		pData.put("zfjgNow", zfjgNow);

		//��ѯA�������˰�������ֻܷ���
		HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
		if(("".equals(czzsfzjgNbForm.getNsfs_fsjg().get("nsfs"))) || czzsfzjgNbForm.getNsfs_fsjg().get("nsfs")==null){
			czzsfzjgNbForm.getNsfs_fsjg().put("nsfs", "0");
		}
		if(("".equals(czzsfzjgNbForm.getNsfs_fsjg().get("zfjg"))) || czzsfzjgNbForm.getNsfs_fsjg().get("zfjg")==null){
			czzsfzjgNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		if(czzsfzjgNbForm.getNsfs_fsjg().get("nsfs").equals("2")){
			czzsfzjgNbForm.getNsfs_fsjg().put("zfjg", "0");
		}
		nsfs_zfjg.put("nsfs_old", czzsfzjgNbForm.getNsfs_fsjg().get("nsfs"));
		nsfs_zfjg.put("zfjg_old", czzsfzjgNbForm.getNsfs_fsjg().get("zfjg"));

		System.out.println("save: nsfs_old============" + nsfs_zfjg.get("nsfs_old"));
		System.out.println("save: zfjg_old============" + nsfs_zfjg.get("zfjg_old"));
		System.out.println("save: nsfs_now============" + nsfsNow);
		System.out.println("save: zfjg_now============" + zfjgNow);
		
		pData.put("nsfs_zfjg", nsfs_zfjg);
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_SAVEACTION);
		vo.setData(czzsfzjgNbForm);
		vo.setUserData(userData);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
		try {
			// ����Proxy��ִ��processor,��ȡczzsfzjgNbForm����ֵ
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);
			// ��CzzssdsjbForm�еĻ��������ÿ�
			//if(!("1".equals(request.getParameter("lje_nsfs"))&&"1".equals(request.getParameter("lje_zfjg")))){
			if(!("1".equals(czzsfzjgNbForm.getNsfs()))){
				czzsfzjgNbForm.reset(mapping, request);
				czzsfzjgNbForm.setQysdsnbList(new ArrayList());
			}
			czzsfzjgNbForm.setSAVE_FLAG("1");//�޸ı�־λ
			System.out.println("�洢���="+czzsfzjgNbForm.getSAVE_FLAG());
			
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
			// ��czzsfzjgNbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			// �����ɹ���ת
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "��ҵ����˰��֧���������˰�걨����ɹ���");
			return mapping.findForward("Show");
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "��ҵ����˰��֧���������˰�걨����ʧ�ܣ�"+ex.getMessage());
			throw ExceptionUtil.getBaseException(ex);
		}
	}
	
	private List getRequestValuesToList(HttpServletRequest request,CzzsfzjgNbForm form){
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
	 *            czzsfzjgNbForm
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

		// ��ǰ��ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		// ��ȡ��ϵͳ�û���Ϣ
		userData = this.getUserData(request);
		SWDJJBSJ djsj = null;
		// �����ҵ�Ǽǻ�����Ϣ
		try {
			djsj = InterfaceDj.getJBSJ_New(czzsfzjgNbForm.getJsjdm(), userData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		//String sybs=FriendHelper.getNsrSybs(djsj);
		//czzsfzjgNbForm.setSybs(sybs);
		String sybs = czzsfzjgNbForm.getSybs();
		czzsfzjgNbForm.setIsQuery("1");
		if(!sybs.equals(CodeConstant.CODE_QYSDS_ZGFWJD_KSSFZJG)){
			// ��czzsfzjgNbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			throw new ApplicationException("����ҵ��˰Դ��ʶ��Ϊ�֣���д����ҵ����˰��֧���������˰�걨�����ݲ��豣��!");
		}
		// ����¼��������
		czzsfzjgNbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czzsfzjgNbForm��DataList�У�
		System.out.println("hc_field.size()"+request.getParameterValues("hc").length);
		System.out.println("lje_field.size()"+request.getParameterValues("lje").length);
		czzsfzjgNbForm.setQysdsnbList(getRequestValuesToList(request,czzsfzjgNbForm));
		try {
			// ��zfjgfzjgNbForm ����request��
			ZfjgfzjgNbForm zfjgfzjgNbForm=new ZfjgfzjgNbForm();
			zfjgfzjgNbForm.setJsjdm(czzsfzjgNbForm.getJsjdm());

			//��czzsfzjgNbForm����session��
			request.getSession(false).setAttribute("REQ_KEY_CZZSFZJGNBFORM", czzsfzjgNbForm);
			
			request.setAttribute("zfjgfzjgNbForm2014", zfjgfzjgNbForm);
			request.setAttribute("czzsfzjgNbForm2014", czzsfzjgNbForm);
			
			if(czzsfzjgNbForm.getQueryFlag().equals("0")){
				return mapping.findForward("JumpNew");
			}
			return mapping.findForward("Jump");
//			return mapping.findForward("JumpNew");
//			return new ActionForward("/webapp/smsb/qysds/2014/zfjgfzjgNbAction2014.do?actionType=Query");
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
	 *            czzsfzjgNbForm
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
		// ��ǰ��ActionForm---czzsfzjgNbForm
		CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) form;
		// ����¼��������
		czzsfzjgNbForm.setLrr(userData.getYhid());
		// ���ñ���������
		czzsfzjgNbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		// ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����CzzssdsjbForm��DataList�У�
		czzsfzjgNbForm.setQysdsnbList(getRequestValuesToList(request,
				czzsfzjgNbForm));
		// ��CzzssdsjbForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
		request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
		
		QysdsCzzsNb2014Util qysdsUtil = new QysdsCzzsNb2014Util();
		Map pData = new HashMap();
		pData.put("czzsfzjgNbForm", czzsfzjgNbForm);
		
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ������������
		vo.setAction(CodeConstant.SMSB_DELETEACTION);
		vo.setData(czzsfzjgNbForm);
		vo.setProcessor(QysdsConstant2014.SBZL_QYSDSNB2014_CZZSFZJG_PROCESSOR);
		vo.setUserData(userData);
		try {
			//ɾ���ӱ�������
			qysdsUtil.deleteCascadeZfjgData(pData);
			
			// ����Proxy��ִ��processor,��ȡczzsfzjgNbForm����ֵ
			czzsfzjgNbForm = (CzzsfzjgNbForm) SbzlProxy.getInstance().process(vo);
			// ��czzsfzjgNbForm�еĻ��������ÿ�
			czzsfzjgNbForm.reset(mapping, request);
			// ��czzsfzjgNbForm����request,��Ϊ����������
			request.setAttribute(mapping.getAttribute(), czzsfzjgNbForm);
			
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
	private CheckBean setCheckInf(CzzsfzjgNbForm czzsfzjgNbForm)
	{
		CheckBean checkBean = new CheckBean();
		checkBean.setCurrentTime(czzsfzjgNbForm.getSkssjsrq().substring(0,4));
		checkBean.setVersionStartTime("2014");
		checkBean.setVersionEndTime("#");
		
		checkBean.setJsjdm(czzsfzjgNbForm.getJsjdm());
		
		checkBean.setSkssrqq(czzsfzjgNbForm.getSkssksrq());
		checkBean.setSkssrqz(czzsfzjgNbForm.getSkssjsrq());
		return checkBean;
	}
}
