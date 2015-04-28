package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014.web;

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
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014.QysdsHdzsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014.QysdsHdzsnbUtil2014;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb2014.xmlvo.HdzssdsnbVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

/**
 * @version 1.0 �˶�������ҵ����˰�걨Action
 */
public class HdzssdsnbAction extends DcBaseAction {

	private String errorMessage = "";

	public HdzssdsnbAction() {
		
	}

	protected boolean validate(HdzssdsnbVO qynb) {
        if (!qynb.getYwlx().equals(QysdsHdzsConstant.CA_YWLXDM_HDZSSDSNB)) {
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
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.HDZSQYNB;
    }

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		System.out.println("Save: xmldata === " + xmldata);
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		Map retmap = null;

		HdzssdsnbVO qynbvo = new HdzssdsnbVO();
		QysdsHdzsnbUtil2014 qysdsUtil = new QysdsHdzsnbUtil2014();

		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			try {
				// XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
				// qyjb = new QysdsjbVO();
				XMLParseHelper.parseXMLString(qynbvo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			System.out.println("xmldata"+xmldata);
			HdzssdsBO qysdsndbo = qysdsUtil.Hdzsconvert2VO(qynbvo);
			dzyj.setYwdm(qynbvo.getYwlx());
			dzyj.setYwczlx(qynbvo.getYwczlx());
			// ȡ��Form
			// ȡ�õǼǻ�������
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsndbo);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsHdzsConstant.WENNB);
			pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qynbvo);

			// ����VoPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(QysdsHdzsConstant.HDZSQYSDSNB_PROCESSOR_2014);
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
					"��ҵ����˰�����˰�걨��" + CAUtils.getTsxx(qynbvo.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"��ҵ����˰�����˰�걨��" + CAUtils.getTsxx(qynbvo.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB_2012);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ����˰�����˰�걨���걨", qynbvo.getSbxx().getSbrq(), "�ɹ�!");
			// return CAConstants.SUCCESSSB;
			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
					CAConstants.SUCCESSSB);
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
					"��ҵ����˰�����˰�걨���걨", qynbvo.getSbxx().getSbrq(), "ʧ��!");
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
		HdzssdsnbVO qyjb = new HdzssdsnbVO();
		Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		QysdsHdzsnbUtil2014 qysdsUtil = new QysdsHdzsnbUtil2014();
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
			Map pData = new HashMap();
			// pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);
			pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyjb);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsHdzsConstant.WENNB);
			pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
			// ����VOPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(QysdsHdzsConstant.HDZSQYSDSNB_PROCESSOR_2014);
			vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
			vo.setUserData(ud);
			// ���ú�̨Proxy
			ShenbaoProxy.getInstance().process(vo);

			request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"��ҵ����˰�����˰�걨��" + CAUtils.getTsxx(qyjb.getYwczlx()));
			request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
					CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB_2012);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ����˰�����˰�걨��ɾ��", qyjb.getSbxx().getSbrq(), "�ɹ�!");
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"��ҵ����˰�����˰�걨��" + CAUtils.getTsxx(qyjb.getYwczlx()));
			// return CAConstants.SUCCESSSB;
			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE,
					CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qyjb.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qyjb
					.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qyjb.getSchemaVersion());
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ɾ����ҵ����˰�����˰�걨��",
					(new SimpleDateFormat("yyyyMMdd")).format(sbrq), "ʧ��!");
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// �Ǽǻ�������
		SWDJJBSJ djJbsj = null;

		String rkbj = (String) request.getAttribute("RKBJ");

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

		HdzssdsBO qysdsnbbo = null;
		HdzssdsnbVO qysdsnbvo = new HdzssdsnbVO();
		if (rkbj != null) {
			if (rkbj.equals("1")) {
				qysdsnbvo = (HdzssdsnbVO) request.getSession().getAttribute(
						"NBVO");
				System.out.println("ActionShow === qynbvo��"
						+ qysdsnbvo.getSbsj().getSyze());

				String tmpxml = qysdsnbvo.toXML();
				System.out.println("show xml:" + tmpxml);

				request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
				request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
						qysdsnbvo.getXsltVersion());
				request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
						qysdsnbvo.getSchemaVersion());
				request.setAttribute("RKBJ", "");
				return CAConstants.SHOW;
			}
		}

		QysdsHdzsnbUtil2014 qysdsUtil = new QysdsHdzsnbUtil2014();
		try {
			
			//����У��
			CheckBean checkBean = new CheckBean();	
			checkBean.setJsjdm(jsjdm);
			checkBean.createZgrqByCurrenttimeY();
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			
			// ����Ƿ����Ե����أ�����ʱ��20090201-20090215
			// checkIsTestTaxpayer(request,response);
			// ȡ�õǼǻ�������
			djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
			pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsHdzsConstant.WENNB);
			pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
			vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
			vo.setProcessor(QysdsHdzsConstant.HDZSQYSDSNB_PROCESSOR_2014);
			vo.setData(pDataMap);
			vo.setUserData(ud);
			// ���ú�̨��ѯ����
			qysdsnbbo = (HdzssdsBO) ShenbaoProxy.getInstance().process(vo);

			qysdsnbvo = qysdsUtil.Hdzsconvert2XMLVO(qysdsnbbo, djJbsj,
					QysdsHdzsConstant.WENNB);
			String tmpxml = qysdsnbvo.toXML();
			System.out.println("show xml:" + tmpxml);

			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					qysdsnbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qysdsnbvo.getSchemaVersion());

			// if (true) throw new Exception("test");

			// ת����ҵ����˰�����걨ҳ��
			return CAConstants.SHOW;
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsnbvo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					qysdsnbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qysdsnbvo.getSchemaVersion());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// ���Ȩ��
		if (!SbzlAccess.getAuthority(SbzlAccess.HDZSQYNB, request)) {
			return CAConstants.NOPERMISSION;
		}

		return null;
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		return "Return";
	}

}