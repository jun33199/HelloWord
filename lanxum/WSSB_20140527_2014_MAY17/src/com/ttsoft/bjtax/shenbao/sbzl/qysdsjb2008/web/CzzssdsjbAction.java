package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.QysdsUtil2008;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

/**
 * @version 1.0 ����������ҵ����˰����Action
 */
public class CzzssdsjbAction extends DcBaseAction {

	private String errorMessage = "";

	public CzzssdsjbAction() {

	}

	protected boolean validate(CzzssdsjbVO qynb) {
		if (!qynb.getYwlx().equals(Constant.CA_YWLXDM_CZZSSDSJB_2008)) {
			errorMessage = "ҵ�����ʹ��󣬲���ִ��ҵ�������";
			return false;
		}
		if (!(qynb.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || qynb
				.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
			System.out.println("ҵ��������ʹ���" + qynb.getYwczlx());
			errorMessage = "ҵ��������ʹ��󣬲���ִ��ҵ�������";
			return false;
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!qynb.getSbxx().getSbrq().startsWith(df.format(now))) {
			errorMessage = "�걨���ڴ���";
			return false;
		}
		return true;
	}

	protected int getActionID() {
		return com.ttsoft.bjtax.shenbao.util.SbzlAccess.CZZSQYJB;
	}

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		Map retmap = null;

		CzzssdsjbVO qynbvo = new CzzssdsjbVO();
		QysdsUtil2008 qysdsUtil = new QysdsUtil2008();

		//System.out.println("request.getParameter(\"lje_nsfs\")"+request.getParameter("lje_nsfs"));
		//System.out.println("request.getParameter(\"lje_zfjg\")"+request.getParameter("lje_zfjg"));

		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			System.out.println("save xml:" + xmldata);
			try {
				// XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
				// qyjb = new QysdsjbVO();
				XMLParseHelper.parseXMLString(qynbvo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}

			CzzssdsBO qysdsndbo = qysdsUtil.Czzsconvert2VO(qynbvo);
			dzyj.setYwdm(qynbvo.getYwlx());
			dzyj.setYwczlx(qynbvo.getYwczlx());
			
			String nsfs=qysdsndbo.getNsfs();
			String zfjg=qysdsndbo.getZfjg();
			// ȡ��Form
			// ȡ�õǼǻ�������
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsndbo);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_CZZSSDS_2008);
			pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qynbvo);

			// ����VoPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(ProcessorNames.CZZSQYSDSNB_PROCESSOR_2008);
			vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
			vo.setUserData(ud);
			// ���ú�̨Procxy

			// ���û�ִ����ҳ��ĺ���������
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);
			if (ud.getCaflag()) {
				dzyj = (DzyjsjVO) retmap
						.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				ArrayList hzlist = new ArrayList();
				hzlist.add(Long.toString(dzyj.getLsh()));
				// request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
				request
						.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,
								hzlist);
			} else {
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			}
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"��ҵ����˰������˰�걨��" + CAUtils.getTsxx(qynbvo.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"��ҵ����˰������˰�걨��" + CAUtils.getTsxx(qynbvo.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_CZZSSDS_QYJB2008);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ����˰������˰�걨���걨", qynbvo.getSbxx().getSbrq(), "�ɹ�!");
			System.out.println("nsfs="+nsfs);
			System.out.println("zfjg="+zfjg);
			if("1".equals(nsfs)&&"1".equals(zfjg)){//����걨������Ϊ���ܽ���-�ܻ���
				request.setAttribute(Constant.JUMP_FLAG_NAME,"1");//��������ɹ�,������ת
				return this.doShow(request,response);
			}else{
				return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
			}
//			return CAConstants.SUCCESSSB;
			 //return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qynbvo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					qynbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qynbvo.getSchemaVersion());
			ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ����˰������˰�걨���걨", qynbvo.getSbxx().getSbrq(), "ʧ��!");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	/**
	 * ɾ�������걨���� 1.ȡ��QysdsksForm 2.����form��toVo����ת������ 3.����VoPackage
	 * 4.����ShenBaoProxy��processor����������VoPackage 5.����ActionMappingת��
	 *
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            QysdsksForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ActionForward
	 * @throws BaseException
	 */
	public String doDelete(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// ���token
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		// �õ�xml
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		CzzssdsjbVO qyjb = new CzzssdsjbVO();
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		QysdsUtil2008 qysdsUtil = new QysdsUtil2008();
		try {
			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			System.out.println("delete xml:" + xmldata);

			try {
				// XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
				// qyjb = new QysdsjbVO();
				XMLParseHelper.parseXMLString(qyjb, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			dzyj.setYwczlx(qyjb.getYwczlx());
			dzyj.setYwdm(qyjb.getYwlx());

			// ȡ�õǼǻ�������
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
			// ����form�е�����
			CzzssdsBO qysdsjd = qysdsUtil.Czzsconvert2VO(qyjb);
			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);
			pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyjb);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_CZZSSDS_2008);
			pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
			// ����VOPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(ProcessorNames.CZZSQYSDSNB_PROCESSOR_2008);
			vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
			vo.setUserData(ud);
			// ���ú�̨Proxy
			ShenbaoProxy.getInstance().process(vo);

			request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"��ҵ����˰������˰�걨��" + CAUtils.getTsxx(qyjb.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_CZZSSDS_QYJB);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ����˰������˰�걨��ɾ��", qyjb.getSbxx().getSbrq(), "�ɹ�!");
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"��ҵ����˰������˰�걨��" + CAUtils.getTsxx(qyjb.getYwczlx()));
//			return CAConstants.SUCCESSSB;
			 return
			 CAUtils.jumpTo(ud.getCaflag(),CAConstants.DELETE,CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qyjb.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qyjb
					.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qyjb.getSchemaVersion());
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ɾ����ҵ����˰������˰�걨��",
					(new SimpleDateFormat("yyyyMMdd")).format(sbrq), "ʧ��!");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("This is doShow Action...");
		// �Ǽǻ�������
		SWDJJBSJ djJbsj = null;
		// ����VOPackage
		VOPackage vo = new VOPackage();
		Map pDataMap = new HashMap();
		UserData ud = (UserData) request.getSession(false).getAttribute(
				"UserData");
		if (ud == null) {
			System.out.println("session is null");
		}

		String jsjdm = ud.yhid;

		Timestamp curDate = new Timestamp(System.currentTimeMillis());

		CzzssdsBO qysdsjbbo = null;
		CzzssdsjbVO qysdsjbvo = new CzzssdsjbVO();
		QysdsUtil2008 qysdsUtil = new QysdsUtil2008();
		try {
			// ȡ�õǼǻ�������
			djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
			pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_CZZSSDS_2008);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil
					.getJbDM());
			vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
			vo.setProcessor(ProcessorNames.CZZSQYSDSNB_PROCESSOR_2008);
			vo.setData(pDataMap);
			vo.setUserData(ud);
			// ���ú�̨��ѯ����
			qysdsjbbo = (CzzssdsBO) ShenbaoProxy.getInstance().process(vo);
			qysdsjbvo = qysdsUtil.Czzsconvert2XMLVO(qysdsjbbo, djJbsj, Constant.TABLE_ID_CZZSSDS_2008);
			String tmpxml = qysdsjbvo.toXML();
			System.out.println("show xml:" + tmpxml);

			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					qysdsjbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qysdsjbvo.getSchemaVersion());

			// if (true) throw new Exception("test");

			// ת����ҵ����˰�����걨ҳ��
			return CAConstants.SHOW;
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					qysdsjbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qysdsjbvo.getSchemaVersion());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// ���Ȩ��
		if (!SbzlAccess.getAuthority(SbzlAccess.CZZSQYJB2008, request)) {
			return CAConstants.NOPERMISSION;
		}

		//
		request.removeAttribute(Constant.JUMP_FLAG_NAME);

		return null;
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		return "Return";
	}

	public String doJump(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("i am jumping...");
		return "Jump";
	}

}
