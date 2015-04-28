package com.syax.bjtax.shenbao.jmba.qjfzjzsd19.web;

import com.ekernel.foundation.xml.XMLHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.framework.util.VOPackage;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import java.util.Map;
import java.util.HashMap;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba05VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_18VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba_19VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZlqdVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;

/**
 * <p>
 * Title: ������˰��������ϵͳ���������걨
 * </p>
 * 
 * <p>
 * Description: ��ʮ�ţ���෢չ������Ŀ���� Action
 * </p>
 * <p>
 * Company: ��һ����
 * </p>
 * 
 * @author �׾�
 * @version 1.0
 */
public class QjfzjzsdAction extends DcBaseAction {
	public QjfzjzsdAction() {
	}

	// �鿴ҳ��
	public String doView(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// ��ȡ��˰�˵Ǽǻ�����Ϣ
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// ȡ��userdata
		UserData userdata = (UserData) this.getUserData(request);
		// ����VOPackage
		VOPackage voPackage = new VOPackage();
		// �趨vopackage����
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		// @todo �ӵڶ�����תҳ���session�л�ȡ
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		// test if
		if (BASQWSH == null)
			BASQWSH = "062008000002";
		String jsjdm = userdata.getYhid();
		// ��˰�˵�����˰�����
		String zgswjg = userdata.getSsdwdm();
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("jsjdm", jsjdm);
		voPackage.setData(map);
		// ���ú�̨������ȡ�÷���ֵ
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
		// ƴװjmbaVO
		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH);
		// ����xml����
		String strXml = vo.toXML();
		Debug.out(strXml);
		// ������������xml���ݺ�xsl�ļ��İ汾��
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession().setAttribute("XSLLX19", "VIEW");
		// ת����ʾҳ��
		Debug.out("ת����ʾҳ��");
		return CAConstants.SHOW;
	}

	/**
	 * ¼�뱸������ ��ʼ������
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		Debug.out("****************************************");
		if ("VIEW".equals(request.getSession().getAttribute("XSLLX19"))) {
			request.getSession().setAttribute("XSLLX19", null);
		}
		// ȡ��userdata
		UserData userdata = (UserData) this.getUserData(request);

		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// ����VOPackage
		VOPackage voPackage = new VOPackage();
		// �趨vopackage����
		voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		// @todo �ӵڶ�����תҳ���request�л�ȡ
		String BASQWSH = (String) request.getSession(false).getAttribute(
				"basqwsh");
		// test if
		if (BASQWSH == null)
			BASQWSH = "062008000002";
		String jsjdm = userdata.getYhid();
		// ��˰�˵�����˰�����
		String zgswjg = userdata.getSsdwdm();
		Map map = new HashMap();
		map.put("BASQWSH", BASQWSH);
		map.put("jsjdm", jsjdm);

		voPackage.setData(map);

		// ���ú�̨������ȡ�÷���ֵ
		voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);

		JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
				jbsj, BASQWSH);
		// ��������
		String strXml = vo.toXML();
		Debug.out(strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		request.getSession().setAttribute("CZLX19",
				((Map) voPackage.getData()).get("czlx19"));

		// ת����ʾҳ��
		return CAConstants.SHOW;
	}

	private JmbaVO convertToXmlVO(Map map, UserData ud, SWDJJBSJ jbsj,
			String BASQWSH) {
		// 1 ���ϲ�VO
		JmbaVO vo = new JmbaVO();
		// 2 ��˰��VO 1.set 2
		NsrxxVO nsrxx = new NsrxxVO();
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(jbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		// 1.set 2
		vo.setNsrxx(nsrxx);
		// 3 ����VO 1-n ��������� 1.list.add 3s
		JmbaZbVO zbvo = (JmbaZbVO) map.get("JmbaZbVO");
		zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM17);
		zbvo.setBasqwsh(BASQWSH);

		Jmba_19VO vo19 = (Jmba_19VO) map.get("Jmba_19VO");
		vo19.setLrr(jbsj.getJsjdm());
		vo19.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1 + "");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		vo19.setLrrq(format.format(new Date(System.currentTimeMillis())));
		zbvo.getQysdsjmba().add(vo19);
		System.out.println(zbvo.getBasqwsh() + "----------" + vo19.getQtzl()
				+ "=======�鿴==================");
		// ������VO���õ���VO�У���������VO��xsl�汾��schema�汾��ҵ��������Ϣ

		vo.getJmsbajl().add(zbvo);
		vo.setXsltVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA17);
		return vo;
	}

	// if (zbvo.getQysdsjmba().isEmpty()) {
	// Jmba_19VO mxvo = new Jmba_19VO();
	// mxvo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1)
	// + "");
	// // mxvo.setCjr(jbsj.getJsjdm());
	// // mxvo.setCjrq(DateUtilPro.getCurYearStr4());
	// mxvo.setLrr(jbsj.getJsjdm());
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// mxvo.setLrrq(format.format(new Date(System.currentTimeMillis())));
	// zbvo.getQysdsjmba().add(mxvo);
	// }

	// db query result,set zbvo data
	// zbvo.setJmbasxdm

	// 4 ��ϸ����VO 1-ns 3.list.add 4s
	// Jmba_19VO vo19 = (Jmba_19VO) map.get("Jmba_19VO");
	// // 3.list.add 4s
	// zbvo.getQysdsjmba().add(vo19);
	// �����嵥��ѯVO����,����ҳ����ʱ������
	// zbvo.getBajlzlqd().add(zlqdVOs);
	// 1.list.add 3s

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("save==================================");
		if (!isTokenValid(request)) {

			return CAConstants.INVALIDTOKEN;
		}

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		VOPackage voPackage = new VOPackage();
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		System.out.println("jmba xmldata" + xmldata);
		DzyjsjVO dzyj = new DzyjsjVO();
		JmbaVO jmbavo = new JmbaVO();
		try {
			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud
							.getSsdwdm());
				} catch (ApplicationException e) {
					throw ExceptionUtil.getBaseException(e);
				}
			}
			System.out
					.println("ִ�в���֮ǰҪ+++++++++++++++++++++++++++++++++++111111111+++++++++++++++++++++++++++++++++++++++++");
			try {

				XMLParseHelper.parseXMLString(jmbavo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);

			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			System.out
					.println("ִ�в���֮ǰҪ+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++");
			dzyj.setYwczlx(jmbavo.getYwczlx());
			dzyj.setYwdm(jmbavo.getYwlx());
			// ȡ�õǼ�����
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			voPackage.setUserData(ud);
			HashMap hm = new HashMap();
			// hm.put(ZhsbMapConstant.SBSJ, jmbavo);
			// hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.BASX017_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
			System.out
					.println("ִ�в���֮ǰҪ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);
			System.out
					.println("ִ�в���֮��Ҫ+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			// ǩ��ԭ���ݲ�ʵ�� 20091227
			// dzyj = (DzyjsjVO) ((HashMap)
			// voPackage.getData()).get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
			// �����CA�������ǩ����д

			// jmbavo = convert2XMLVOResult(request, jbsj, wynsksb, now);
			// String tmpxml = jmbavo.toXML();
			// request.setAttribute("CA_XML_DATA", tmpxml);
			// request.setAttribute("CA_XML_XSLT_VERSION",
			// jmbavo.getXsltVersion());
			// request.setAttribute("CA_XML_SCHEMA_VERSION",
			// jmbavo.getSchemaVersion());

			// ���û�ִ����ҳ��ĺ���������
			// ǩ��ԭ���ݲ�ʵ�� 20091227
			/*
			 * if (ud.getCaflag()) {
			 * request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,
			 * Long.toString(dzyj.getLsh())); } else {
			 * request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, ""); }
			 */
			System.out
					.println("ִ�в���֮��Ҫ++++++++++++++++++2222++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"��෢չ������Ŀ���ü��ⱸ������ɹ���");
			// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
			// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��෢չ������Ŀ���ü��ⱸ������",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
			System.out
					.println("ִ�в���֮��Ҫ+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			return CAConstants.SAVE;
			// return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
			// CAConstants.SUCCESSSB);
		} catch (Exception e) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					jmbavo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					jmbavo.getSchemaVersion());
			System.out.println("���ⱸ���쳣��Ϣ: ===== " + e.getMessage());

			throw ExceptionUtil.getBaseException(e);
		}

	}

	// /**
	// * ¼�뱸������ �༭
	// *
	// * @param request
	// * @param response
	// * @return
	// * @throws BaseException
	// */
	// public String doEditor(HttpServletRequest request,
	// HttpServletResponse response) throws BaseException {
	// String selIndex = request.getParameter("selIndex");
	// // System.out.println("selIndex = "+selIndex);
	//
	// // ȡ��userdata
	// UserData userdata = (UserData) this.getUserData(request);
	// // ����VOPackage
	// VOPackage voPackage = new VOPackage();
	// // �趨vopackage����
	// voPackage.setProcessor(ProcessorNames.WGRJSDNX18_PROCESSOR);
	// voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
	// voPackage.setUserData(userdata);
	// // @todo �ӵڶ�����תҳ���request�л�ȡ
	// String BASQWSH = (String) request.getSession(false).getAttribute(
	// "basqwsh");
	// // test if
	// if (BASQWSH == null)
	// BASQWSH = "062008000002";
	// Map djMap = (Map) FriendHelper.getDjInfo(request);
	// SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
	// Map map = new HashMap();
	// map.put("BASQWSH", BASQWSH);
	// map.put("selIndex", selIndex);
	// map.put("type", "EDITOR");
	//
	// voPackage.setData(map);
	//
	// // ���ú�̨������ȡ�÷���ֵ
	// voPackage = (VOPackage) ShenbaoProxy.getInstance().process(voPackage);
	// JmbaVO vo = this.convertToXmlVO((Map) voPackage.getData(), userdata,
	// jbsj, BASQWSH,"");
	// // ��������
	// String strXml = vo.toXML();
	// Debug.out(strXml);
	// request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	// request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
	// .getXsltVersion());
	// request.getSession(false).setAttribute("buss", "Update");
	// request.getSession(false).setAttribute("selIndex", selIndex);
	//
	// // ת����ʾҳ��
	// return CAConstants.ADDSHOW;
	// }

	/**
	 * �������Ȩ�޽��м��
	 */
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// ���Ȩ�� ������û�м������
		// System.out.println("beforePerform");
		// if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
		// {
		//
		// return CAConstants.NOPERMISSION;
		// }
		return null;
	}

}
