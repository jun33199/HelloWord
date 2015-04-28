package com.ttsoft.bjtax.shenbao.sbzl.yhs.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.ActionErrors;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgz;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.NsrxxVO_YHS;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.SbsjlistVO;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.SbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.SbxxVO_YHS;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.YhsbgmxVO;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.common.model.UserData;

/**
 * @author
 * @version 1.0 ӡ��˰Action
 */
public class YhsAction extends DcBaseAction {

	private String errorMessage = "";

	protected boolean validate(YhsbgmxVO yhs) {
		if (!yhs.getYwlx().equals(CAcodeConstants.YWDM_SB_WS_YHS)) {
			errorMessage = "ҵ�����ʹ��󣬲���ִ��ҵ�������";
			return false;
		}
		if (!(yhs.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW)
				|| yhs.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY) || yhs
				.getYwczlx().equals(CAcodeConstants.YWCZLX_DELETE))) {
			System.out.println("ҵ��������ʹ���" + yhs.getYwczlx());
			errorMessage = "ҵ��������ʹ��󣬲���ִ��ҵ�������";
			return false;
		}

		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (!yhs.getSbxx().getSbrq().startsWith(df.format(now))) {
			errorMessage = "�걨���ڴ���";
			return false;
		}
		return true;
	}

	public YhsAction() {
	}

	protected int getActionID() {
		return com.ttsoft.bjtax.shenbao.util.SbzlAccess.YHND;
	}
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		try {
			Map data = new HashMap();
			// ȡ�õǼ�����
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			UserData userData = (UserData) this.getUserData(request);
			data.put(YhsMapConstant.JSJDM, userData.yhid);
			data.put(YhsMapConstant.MAP_DJSJ, djMap);
			VOPackage voPackage = new VOPackage();
			voPackage.setData(data);
			voPackage.setAction(YhsActionConstant.INT_ACTION_QUERY);
			voPackage.setUserData(userData);
			voPackage.setProcessor(ProcessorNames.YHS_PROCESSOR);

			VOPackage backData = (VOPackage) ShenbaoProxy.getInstance()
					.process(voPackage);

			Map yhsData = (Map) backData.getData();
			SWDJJBSJ djInfo = (SWDJJBSJ) yhsData.get(YhsMapConstant.JBSJ);

			// Yhsbgz yhsbgz = (Yhsbgz) yhsData.get(YhsMapConstant.YHSBGZ);
			// List yhsbgmxList = (List)
			// yhsData.get(YhsMapConstant.LIST_YHSBGMX);
			// List yhsSzsmList = (List) yhsData.get(YhsMapConstant.LIST_SZSM);
			String ifSB = (String) yhsData.get(YhsMapConstant.IFSB);
			YhsbgmxVO yhsmx = convertToXmlVO(yhsData, djInfo);
			if (ifSB.equals("wsb")) {
				yhsmx.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			} else {
				yhsmx.setYwczlx(CAcodeConstants.YWCZLX_NEW);
			}
			String xmlStr = yhsmx.toXML();
            Debug.out(xmlStr);

			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xmlStr);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, yhsmx
					.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					yhsmx.getSchemaVersion());
			// ת��ҵ��ҳ��
			return CAConstants.SHOW;

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	private YhsbgmxVO convertToXmlVO(Map yhsData, SWDJJBSJ djjbsj)
			throws ApplicationException {
		NsrxxVO_YHS nsrxx = new NsrxxVO_YHS();
		SbxxVO_YHS sbxx = new SbxxVO_YHS();
		SbsjVO sbsj = new SbsjVO();
		SbsjlistVO sbsjlist = new SbsjlistVO();
		YhsbgmxVO yhs = new YhsbgmxVO();
		// ��˰����Ϣ
		nsrxx.setJsjdm(djjbsj.getJsjdm());
		nsrxx.setNsrmc(djjbsj.getNsrmc());
		nsrxx.setQylxdh(djjbsj.getJydzlxdm());
		nsrxx.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
		// �걨��Ϣ
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(curTime);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String skssksrq = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
		String skssjsrq = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
		sbxx.setSbrq(df.format(curTime));
		sbxx.setSkssksrq(skssksrq);
		sbxx.setSkssjsrq(skssjsrq);
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		String nd = (String) ((Map) Skssrq.yearSkssrq(new Date()))
				.get(Skssrq.SKSSRQ_ND);
		// String nd = new SimpleDateFormat("yyyy").format(curTime);
		sbxx.setNd(nd);
		// �걨����
		List yhsbgmxList = (List) yhsData.get(YhsMapConstant.LIST_YHSBGMX);
		List yhsSzsmInfoList = (List) yhsData.get(YhsMapConstant.LIST_SZSM);
		Yhsbgz yhsbgz = (Yhsbgz) yhsData.get(YhsMapConstant.YHSBGZ);

		int size = yhsbgmxList.size();
		String[] szsmdm = new String[size];
		String[] szsmmc = new String[size];
		String[] fs = new String[size];
		String[] jsje = new String[size];
		String[] sl = new String[size];
		String[] ynse = new String[size];
		for (int i = 0; i < yhsbgmxList.size(); i++) {
			Yhsbgmx yhsmx = (Yhsbgmx) yhsbgmxList.get(i);
			szsmdm[i] = yhsmx.getSzsmdm();
			for (int j = 0; j < yhsSzsmInfoList.size(); j++) {
				Szsm mc = (Szsm) yhsSzsmInfoList.get(j);
				if (mc.getSzsmdm().equals(szsmdm[i])) {
					szsmmc[i] = mc.getSzsmmc();
				}
			}
			fs[i] = yhsmx.getFs() + "";
			jsje[i] = yhsmx.getJsje() + "";
			sl[i] = yhsmx.getSl() + "";
			if (sl[i].equals("null")) {
				sl[i] = "0";
			}
			ynse[i] = yhsmx.getSjse() + "";

		}
		sbsj.setSzsmdm(szsmdm);
		sbsj.setSzsmmc(szsmmc);
		sbsj.setFs(fs);
		sbsj.setJsje(jsje);
		sbsj.setSl(sl);
		sbsj.setYnse(ynse);
		sbsjlist.setHjfs(yhsbgz.getHjfs() + "");
		sbsjlist.setHjjsje(yhsbgz.getHjjsje() + "");
		sbsjlist.setHjynse(yhsbgz.getHjynse() + "");
		sbsjlist.setSbsj(sbsj);

		yhs.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_YHS).substring(0, 8));
		yhs.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_YHS).substring(8));

		yhs.setYwlx(CAcodeConstants.YWDM_SB_WS_YHS);

		yhs.setNsrxx(nsrxx);
		yhs.setSbxx(sbxx);
		yhs.setSbsjlist(sbsjlist);

		return yhs;
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) {
		return CAConstants.RETURN;
	}

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if (!isTokenValid(request)) {
			return CAConstants.FAILURE;
		}
		String receiveXml = request
				.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        Debug.out(receiveXml);

		UserData userData = (UserData) this.getUserData(request);
		SWDJJBSJ djInfo = FriendHelper.getSWDJJBSJ(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();

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
		YhsbgmxVO yhs = new YhsbgmxVO();
		try {
			//XMLParseHelper.parseXMLString(yhs, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//yhs = new YhsbgmxVO();
			XMLParseHelper.parseXMLString(yhs, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"����ʧ�ܣ�����xml���ݳ���");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(yhs.getYwczlx());
        dzyj.setYwdm(yhs.getYwlx());

        Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			VOPackage voPackage = new VOPackage();
			Map map = convertToVO(yhs, userData, djInfo);
			map.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			map.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, yhs);

			voPackage.setData(map);
			voPackage.setUserData(userData);
			voPackage.setProcessor(ProcessorNames.YHS_PROCESSOR);
			// ���ñ��涯��
			voPackage.setAction(YhsActionConstant.INT_ACTION_SAVE);
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);
			// ����ǩ������
			Map retMap = (Map) voPackage.getData();
			if (userData.getCaflag()) {
				dzyj = (DzyjsjVO) retMap
						.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, Long
						.toString(dzyj.getLsh()));
			} else {
				request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
			}
			request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,
					"ӡ��˰��ȱ����"+CAUtils.getTsxx(yhs.getYwczlx()));
			request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
					"/shenbao/yhs.dc?actionType=Show");
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "ӡ��˰��ȱ����"+CAUtils.getTsxx(yhs.getYwczlx()));
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ӡ��˰��ȱ����", (new SimpleDateFormat("yyyyMMdd")).format(now),
					"�ɹ�!");
			return  CAUtils.jumpTo(userData.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			// ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ӡ��˰��ȱ����", (new SimpleDateFormat("yyyyMMdd")).format(now),
					"ʧ��!");
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

	private Map convertToVO(YhsbgmxVO yhs, UserData userData, SWDJJBSJ djInfo)
			throws com.syax.frame.exception.BaseException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		VOPackage voPackage = new VOPackage();
		Map data = new HashMap();

		// ӡ��˰�걨��ϸ������
		List yhsbgmxList = new ArrayList();
		// ȥ���ո�
		SbsjlistVO sbsjlist = yhs.getSbsjlist();
		SbsjVO sbsj = sbsjlist.getSbsj();
		for (int i = 0; i < sbsj.getSzsmdm().length; i++) {
			Yhsbgmx mx = new Yhsbgmx();
			// �������ӡ��˰˰Ŀ�ǰ���������λ˰���������˰��
			if (sbsj.getSzsmdm()[i].equals(SzsmdmConstant.YHS_QTZB)
					|| sbsj.getSzsmdm()[i].equals(SzsmdmConstant.YHS_QLXKZZ)) {
				mx.setFs(strToBigDecimal(sbsj.getFs()[i]));
				mx.setSl(strToBigDecimal(sbsj.getSl()[i]));
				mx.setSjse(strToBigDecimal(sbsj.getYnse()[i]));
				mx.setJsje(strToBigDecimal(sbsj.getJsje()[i]));
			}
			// �������ӡ��˰˰Ŀ�ǰ���˰����˰�ʼ�������˰��
			else {
				mx.setFs(strToBigDecimal(sbsj.getFs()[i]));
				mx.setSl(strToBigDecimal(sbsj.getSl()[i]));
				mx.setSjse(strToBigDecimal(sbsj.getYnse()[i]));
				mx.setJsje(strToBigDecimal(sbsj.getJsje()[i]));
			}
			mx.setJsjdm(yhs.getNsrxx().getJsjdm());
			mx.setNd(yhs.getSbxx().getNd());
			mx.setSzdm(SzsmdmConstant.YHS);
			mx.setSzsmdm(sbsj.getSzsmdm()[i]);
			mx.setSwjgzzjgdm(yhs.getNsrxx().getSwjgzzjgdm());
			mx.setLrrq(now);
			mx.setCjrq(now);
			mx.setQxdm(yhs.getNsrxx().getSwjgzzjgdm().substring(0, 2));

			yhsbgmxList.add(mx);
		}
		// ӡ��˰��ȱ�������
		Yhsbgz yhsbgz = new Yhsbgz();
		yhsbgz.setFsdm(CodeConstant.FSDM_WSSB);
		yhsbgz.setHjfs(strToBigDecimal(sbsjlist.getHjfs()));
		yhsbgz.setHjjsje(strToBigDecimal(sbsjlist.getHjjsje()));
		yhsbgz.setHjynse(strToBigDecimal(sbsjlist.getHjynse()));
		yhsbgz.setJsjdm(yhs.getNsrxx().getJsjdm());
		yhsbgz.setLrr(userData.yhid);
		yhsbgz.setNd(yhs.getSbxx().getNd());
		yhsbgz.setSkssjsrq(DateTimeUtil.stringToTimestamp(yhs.getSbxx()
				.getSkssjsrq(), "yyyy-MM-dd"));
		yhsbgz.setSkssksrq(DateTimeUtil.stringToTimestamp(yhs.getSbxx()
				.getSkssksrq(), "yyyy-MM-dd"));
		yhsbgz.setSwjgzzjgdm(yhs.getNsrxx().getSwjgzzjgdm());

		yhsbgz.setLrrq(now);
		yhsbgz.setCjrq(now);
		yhsbgz.setQxdm(yhs.getNsrxx().getSwjgzzjgdm().substring(0, 2));

		data.put(YhsMapConstant.YHSBGZ, yhsbgz);
		data.put(YhsMapConstant.LIST_YHSBGMX, yhsbgmxList);
		data.put(YhsMapConstant.JBSJ, djInfo);

		return data;

	}

	private BigDecimal strToBigDecimal(String value) {
		if (value != null && !value.trim().equals("")) {
			return new BigDecimal(value);
		} else {
			return null;
		}
	}

	public String doDelete(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if (!isTokenValid(request)) {
			return CAConstants.FAILURE;
		}
		String receiveXml = request
				.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        Debug.out(receiveXml);

		UserData userData = (UserData) this.getUserData(request);
		SWDJJBSJ djInfo = FriendHelper.getSWDJJBSJ(request);
		DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();

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
		YhsbgmxVO yhs = new YhsbgmxVO();
		try {
			//XMLParseHelper.parseXMLString(yhs, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//yhs = new YhsbgmxVO();
			XMLParseHelper.parseXMLString(yhs, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					//"����ʧ�ܣ�����xml���ݳ���");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(yhs.getYwczlx());
        dzyj.setYwdm(yhs.getYwlx());

		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		try {
			// ȡ�ñ�������
			Map data = new HashMap();
			data.put(YhsMapConstant.JBSJ, FriendHelper.getSWDJJBSJ(request));
			data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			data.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, yhs);
			VOPackage vo = new VOPackage();
			vo.setData(data);
			vo.setUserData(userData);
			vo.setProcessor(ProcessorNames.YHS_PROCESSOR);
			vo.setAction(YhsActionConstant.INT_ACTION_DELETE);
			ShenbaoProxy.getInstance().process(vo);
			
			request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,
					"ӡ��˰��ȱ����"+CAUtils.getTsxx(yhs.getYwczlx()));
			request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
					"/shenbao/yhs.dc?actionType=Show");

			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ӡ��˰��ȱ����",
					(new SimpleDateFormat("yyyyMMdd")).format(curTime), "�ɹ�!");
			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
			 "ӡ��˰��ȱ����"+CAUtils.getTsxx(yhs.getYwczlx()));
			return  CAUtils.jumpTo(userData.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"ӡ��˰��ȱ����",
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
			// throw ExceptionUtil.getBaseException(ex);
		}

	}
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// ���Ȩ��
		if (!SbzlAccess.getAuthority(SbzlAccess.YHND, request)) {
			request
					.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
							"��û��Ȩ��ʹ���������");
			return CAConstants.FAILURE;
		}

		return null;
	}
}