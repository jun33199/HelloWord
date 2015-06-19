package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.util.NumberUtils;
import com.syax.common.web.action.ActionErrors;
import com.syax.common.web.action.DcBaseAction;
import com.syax.common.xml.util.XMLVOUtil;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.*;
import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.QycwzbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.NsrxxVO_QYCW;
import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.SbsjlistVO;
import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.SbxxVO_QYCW;
import com.ttsoft.bjtax.shenbao.sbzl.qyjbcwzb.xmlvo.SbsjVO;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData.CwzbData;
import com.ttsoft.bjtax.shenbao.model.domain.Qyjbcwzb;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ttsoft.bjtax.shenbao.model.domain.Sbbbxm;
import com.ttsoft.framework.exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.*;

import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.util.*;

import java.text.*;

/**
 * ��ҵ����ָ��action
 */
public class QycwzbAction extends DcBaseAction {

	private String errorMessage = "";

	protected int getActionID() {
		return com.ttsoft.bjtax.shenbao.util.SbzlAccess.CWZB;
	}

	protected boolean validate(QycwzbVO qycw) {
		if (!qycw.getYwlx().equals(CAcodeConstants.YWDM_SB_WS_QYJBCWZB)) {
			errorMessage = "ҵ�����ʹ��󣬲���ִ��ҵ�������";
			return false;
		}
		if (!(qycw.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW)
				|| qycw.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY) || qycw
				.getYwczlx().equals(CAcodeConstants.YWCZLX_DELETE))) {
			System.out.println("ҵ��������ʹ���" + qycw.getYwczlx());
			errorMessage = "ҵ��������ʹ��󣬲���ִ��ҵ�������";
			return false;
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!qycw.getSbxx().getSbrq().startsWith(df.format(now))) {
			errorMessage = "�걨���ڴ���";
			return false;
		}
		return true;
	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		//System.out.println("doShow+++++++++++++");

		// ��ü��������
		UserData userData = (UserData) request.getSession(false).getAttribute(
				"UserData");
		String jsjdm = userData.yhid;
		// ��õ�ǰʱ��
		Timestamp curTime = new Timestamp(System.currentTimeMillis());

		try {
			Map map = new HashMap();
			map.put(QysdsnbMapConstant.STRING_KEY_JSJDM, jsjdm);
			map.put(QysdsnbMapConstant.STRING_KEY_DATE, curTime);
			map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, FriendHelper
					.getSWDJJBSJ(request));

			VOPackage vo = new VOPackage();
			vo.setUserData(userData);
			vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
			vo.setAction(QysdsnbActionConstant.INT_ACTION_QUERY_CWZB);
			vo.setData(map);

			// ���ݼ��������͵�ǰʱ���ѯ��ҵ����ָ������
			QysdsnbData data = (QysdsnbData) ShenbaoProxy.getInstance()
					.process(vo);

			QysdsHelper helper = new QysdsHelper();
			CwzbData cwzbData = data.getCwzbData();
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
			QycwzbVO qycwzb = convertToXmlVO(data.getCwzbData(), djJbsj);
			List dataList = cwzbData.getCwzbData();
			if (dataList == null || dataList.size() == 0) {
				qycwzb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			} else {
				qycwzb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
			}
			String xmlStr = qycwzb.toXML();
			System.out.println(xmlStr);

			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xmlStr);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					qycwzb.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					qycwzb.getSchemaVersion());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
		return CAConstants.SHOW;
	}

	private QycwzbVO convertToXmlVO(QysdsnbData.CwzbData cwzbData,
			SWDJJBSJ djJbsj) throws ApplicationException {
		QycwzbVO qycw = new QycwzbVO();
		NsrxxVO_QYCW nsrxx = new NsrxxVO_QYCW();
		SbxxVO_QYCW sbxx = new SbxxVO_QYCW();
		SbsjVO sbsj = new SbsjVO();
		SbsjlistVO sbsjList = new SbsjlistVO();
		// ������˰����Ϣ
		nsrxx.setJsjdm(djJbsj.getJsjdm());
		nsrxx.setNsrmc(djJbsj.getNsrmc());
		nsrxx.setQylxdh(djJbsj.getJydzlxdm());
		nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());

		// �����걨��Ϣ
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(curTime);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String skssksrq = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
		String skssjsrq = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
		sbxx.setSbrq(df.format(curTime));
		sbxx.setSkssksrq(skssksrq);
		sbxx.setSkssjsrq(skssjsrq);
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		//String nd = new SimpleDateFormat("yyyy").format(curTime);
		String nd = (String) ((Map) Skssrq.yearSkssrq(new Date()))
		.get(Skssrq.SKSSRQ_ND);
		sbxx.setNd(nd);

		// �����걨����
		List data = cwzbData.getCwzbData();
		List sbbData = cwzbData.getDefineList();
		String[] hc = null;
		String[] ncs = null;
		String[] nms = null;
		String[] xmmc = null;

		if (data == null || data.size() == 0) {
			int len = sbbData.size();
			hc = new String[len];
			ncs = new String[len];
			nms = new String[len];
			xmmc = new String[len];
			for (int i = 0; i < len; i++) {
				Sbbbxm mc = (Sbbbxm) sbbData.get(i);
				xmmc[i] = mc.getXmmc();
				hc[i] = mc.getHc();
				ncs[i] = " ";
				nms[i] = " ";
			}
		}
		if (data != null && data.size() > 0) {
			int size = data.size();
			// �д�
			hc = new String[size];
			// �����
			ncs = new String[size];
			// ����
			nms = new String[size];
			xmmc = new String[size];
			for (int i = 0; i < data.size(); i++) {
				Qyjbcwzb item = (Qyjbcwzb) data.get(i);
				hc[i] = item.getHc();
				ncs[i] = (item.getNcs() == null) ? " " : String.valueOf(item
						.getNcs());
				nms[i] = (item.getNms() == null) ? " " : String.valueOf(item
						.getNms());
				for (int j = 0; j < data.size(); j++) {
					Sbbbxm mc = (Sbbbxm) sbbData.get(j);
					if (mc.getHc().equals(hc[i])) {
						xmmc[i] = mc.getXmmc();
					}
				}
			}
		}
		sbsj.setHc(hc);
		sbsj.setNcs(ncs);
		sbsj.setNms(nms);
		sbsj.setXmmc(xmmc);

		sbsjList.setSbsj(sbsj);

		qycw.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_QYJBCWZB).substring(0, 8));
		qycw.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_QYJBCWZB).substring(8));

		qycw.setYwlx(CAcodeConstants.YWDM_SB_WS_QYJBCWZB);

		qycw.setNsrxx(nsrxx);
		qycw.setSbxx(sbxx);
		qycw.setSbsjlist(sbsjList);

		return qycw;
	}

	/**
	 * ִ�д��̲���
	 * 
	 * 1��������� 2������QysdsHelper����ò���ָ���걨����list 3������vopackage 4������proxy�ķ�������
	 * 5����ʾqycwzb009.jsp
	 */
	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		//System.out.println("doSave++++++++++");
		if (!isTokenValid(request)) {
			return CAConstants.FAILURE;
		}
		String receiveXml = request
				.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		System.out.println(receiveXml);

		UserData userData = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
		Map retmap = null;

		if (userData.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userData.getCert(), userData
						.getSsdwdm());
			} catch (ApplicationException e1) {
				//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
						//"����ʧ�ܣ���֤ǩ������");
				e1.printStackTrace();
				throw ExceptionUtil.getBaseException(e1);
			}
		}
		QycwzbVO qycw = new QycwzbVO();
		try {
			//XMLParseHelper.parseXMLString(qycw, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//qycw = new QycwzbVO();
			XMLParseHelper.parseXMLString(qycw, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					//"����ʧ�ܣ�����xml���ݳ���");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(qycw.getYwczlx());
        dzyj.setYwdm(qycw.getYwlx());

        convert2VO(qycw);
		QysdsnbData nbdata = new QysdsnbData();

		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			SWDJJBSJ djInfo = FriendHelper.getSWDJJBSJ(request);
			QysdsHelper helper = new QysdsHelper();
			helper.getCwzbData(userData, qycw, nbdata, djInfo);

			// ��ü��������
			String jsjdm = userData.yhid;

			// ��õ�ǰʱ��
			Timestamp curTime = new Timestamp(System.currentTimeMillis());

			Map map = new HashMap();
			map.put(QysdsnbMapConstant.STRING_KEY_JSJDM, jsjdm);
			map.put(QysdsnbMapConstant.STRING_KEY_DATE, curTime);
			map.put(QysdsnbMapConstant.STRING_KEY_QYSDSNB_DATA, nbdata);
			map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, djInfo);

			map.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			map.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qycw);

			VOPackage vo = new VOPackage();
			vo.setUserData(userData);
			vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
			vo.setAction(QysdsnbActionConstant.INT_ACTION_SAVE_CWZB);
			vo.setData(map);

			// ���ݼ��������͵�ǰʱ���ѯ��ҵ����˰�걨����
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);

			// ����ǩ������
			if (userData.getCaflag()) {
				dzyj = (DzyjsjVO) retmap
						.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, Long
						.toString(dzyj.getLsh()));
			} else {
				request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
			}
			request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,
					"��ҵ��������ָ�������"+CAUtils.getTsxx(qycw.getYwczlx()));
			request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
					"/shenbao/qycwzb.dc?actionType=Show");
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ��������ָ��������걨",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"��ҵ��������ָ�������"+CAUtils.getTsxx(qycw.getYwczlx()));
			return CAUtils.jumpTo(userData.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��ҵ��������ָ��������걨",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "ʧ��!");
			String message = null;
			if (ex instanceof BaseException) {
				message = ((BaseException) ex).getMessage();
			} else {
				ex.printStackTrace();
				message = "ϵͳ�쳣���������Ա��ϵ";
			}
			ActionErrors errors = new ActionErrors();

			errors.addError(message);
			saveErrors(request, errors);
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, message);
			return CAConstants.FAILURE;
			// throw ExceptionUtil.getBaseException(ex);

		}
	}

	private void convert2VO(QycwzbVO qycw) throws BaseException {

		SbsjlistVO sbsjlist = qycw.getSbsjlist();
		SbsjVO sbsjVO = sbsjlist.getSbsj();
		for (int i = 0; i < sbsjVO.getHc().length; i++) {
			Qyjbcwzb qyjbcwzb = new Qyjbcwzb();
			qyjbcwzb.setJsjdm(qycw.getNsrxx().getJsjdm());
			qyjbcwzb.setSwjgzzjgdm(qycw.getNsrxx().getSwjgzzjgdm());

			qyjbcwzb.setFsdm(qycw.getSbxx().getFsdm());
			qyjbcwzb.setSbrq(DateTimeUtil.stringToTimestamp(qycw.getSbxx()
					.getSbrq(), "yyyy-MM-dd"));
			qyjbcwzb.setSkssksrq(DateTimeUtil.stringToTimestamp(qycw.getSbxx()
					.getSkssksrq(), "yyyy-MM-dd"));
			qyjbcwzb.setSkssjsrq(DateTimeUtil.stringToTimestamp(qycw.getSbxx()
					.getSkssjsrq(), "yyyy-MM-dd"));
			qyjbcwzb.setNd(qycw.getSbxx().getNd());

			Timestamp now = new Timestamp(System.currentTimeMillis());
			qyjbcwzb.setLrrq(now);
			qyjbcwzb.setCjrq(now);
			qyjbcwzb.setLrr(qycw.getNsrxx().getJsjdm());
			qyjbcwzb.setQxdm(qycw.getNsrxx().getSwjgzzjgdm().substring(0, 2));

			qyjbcwzb.setHc(sbsjVO.getHc()[i]);

			qyjbcwzb.setNcs(NumberUtils.string2BigDecimal(sbsjVO.getNcs()[i],
					null));
			qyjbcwzb.setNms(NumberUtils.string2BigDecimal(sbsjVO.getNms()[i],
					null));
		}
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		//System.out.println("return++++++++++");
		return CAConstants.RETURN;

	}

	public String doRemove(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		//System.out.println("doRemove+++++++++++");
		if (!isTokenValid(request)) {
			return CAConstants.FAILURE;
		}
		String receiveXml = request.getParameter("strXMLData");
		// ��ü��������
		UserData userData = (UserData) this.getUserData(request);
		String jsjdm = userData.yhid;

		// ��õ�ǰʱ��
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj =  new DzyjsjVO();
		if (userData.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userData.getCert(), userData
						.getSsdwdm());
			} catch (ApplicationException e1) {
				//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					//	"����ʧ�ܣ���֤ǩ������");
				e1.printStackTrace();
				throw ExceptionUtil.getBaseException(e1);
			}
		}
		QycwzbVO qycw = new QycwzbVO();
		try {
			//XMLParseHelper.parseXMLString(qycw, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//qycw = new QycwzbVO();
			XMLParseHelper.parseXMLString(qycw, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					//"����ʧ�ܣ�����xml���ݳ���");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(qycw.getYwczlx());
        dzyj.setYwdm(qycw.getYwlx());
        
		try {
			Map map = new HashMap();
			map.put(QysdsnbMapConstant.STRING_KEY_JSJDM, jsjdm);
			map.put(QysdsnbMapConstant.STRING_KEY_DATE, curTime);
			map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, FriendHelper
					.getSWDJJBSJ(request));
			map.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			map.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qycw);

			VOPackage vo = new VOPackage();
			vo.setUserData(userData);
			vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
			vo.setAction(QysdsnbActionConstant.INT_ACTION_DELETE_CWZB);
			vo.setData(map);

			// ���ݼ��������͵�ǰʱ��ɾ����ҵ����˰�걨����
			ShenbaoProxy.getInstance().process(vo);
			request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,
					"��ҵ��������ָ�������"+CAUtils.getTsxx(qycw.getYwczlx()));
			request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
					"/shenbao/qycwzb.dc?actionType=Show");
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"��ҵ��������ָ�������"+CAUtils.getTsxx(qycw.getYwczlx()));
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ɾ����ҵ��������ָ�������",
					(new SimpleDateFormat("yyyyMMdd")).format(curTime), "�ɹ�!");
			return CAUtils.jumpTo(userData.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);

		} catch (Exception ex) {
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ɾ����ҵ��������ָ�������",
					(new SimpleDateFormat("yyyyMMdd")).format(curTime), "ʧ��!");
			String message = null;
			if (ex instanceof BaseException) {
				message = ((BaseException) ex).getMessage();
			} else {
				ex.printStackTrace();
				message = "ϵͳ�쳣���������Ա��ϵ";
			}

			ActionErrors errors = new ActionErrors();

			errors.addError(message);
			saveErrors(request, errors);
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, message);
			return CAConstants.FAILURE;
		}

	}

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// ���Ȩ��
		if (!SbzlAccess.getAuthority(SbzlAccess.CWZB, request)) {
			request
					.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
							"��û��Ȩ��ʹ���������");
			return CAConstants.FAILURE;
		}

		return null;
	}
}
