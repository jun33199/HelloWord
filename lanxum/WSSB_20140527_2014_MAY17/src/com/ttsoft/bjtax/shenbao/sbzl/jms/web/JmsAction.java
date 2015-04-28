package com.ttsoft.bjtax.shenbao.sbzl.jms.web;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.ActionErrors;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;

import com.ttsoft.common.model.UserData;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;

import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.shenbao.model.domain.Zjqyjmslx;
import com.ttsoft.bjtax.shenbao.model.client.JmsInfo;
import com.ttsoft.bjtax.shenbao.model.client.JmsSkny;
import com.ttsoft.bjtax.shenbao.model.client.JmsSkssrq;
import com.ttsoft.bjtax.shenbao.model.client.JmsSzsm;
import com.ttsoft.bjtax.shenbao.model.client.JmsxmInfor;

import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;

import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.SessionKey;
import com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo.JmsVO;
import com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo.NsrxxVO_JMS;
import com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo.SbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo.SbsjlistVO;
import com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo.SbxxVO_JMS;

import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.JspUtil;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.model.domain.Jmfl;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.WsksbVO;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Djzclx;
import com.syax.common.web.action.DcBaseAction;

/**
 * 
 * <p>
 * Title: 北京地税综合管理系统 申报征收模块
 * </p>
 * <p>
 * Description: 减免税申报Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: TTSOFT
 * </p>
 * 
 * @version 1.0
 */
public class JmsAction extends DcBaseAction {

	/**
	 * 登记常量
	 */
	private static final String DJ_JBSJ = "JBSJ";

	protected int getActionID() {
		return SbzlAccess.JM;
	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 从codetable取得税种税目List Map
		Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP_AVAILABLE);
		List jmflList = CodeTableUtil.getCodeTableList(request, CodeTable.JMFL_LIST);
		Map jmflMap = CodeTableUtil.getCodeTableMap(request, CodeTable.JMFL_MAP);
		//从codetable取得减免项目及代码List Map 减免税项目  tujb 200404
		//List jmxmList = CodeTableUtil.getCodeTableList(request, CodeTable.JMXM_BASX_LIST);
		Map jmxmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.JMXM_BASX_MAP);
		
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.JMS_PROCESSOR);
		voPackage.setAction(JmsActionConstant.INT_ACTION_QUERY);
		voPackage.setUserData(userdata);

		try {

			// 取得登记Map
			Map djMap = FriendHelper.getDjInfo(request);
			// 登记基本数据
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
			// 申报日期-当前日期
			Timestamp sbrq = new Timestamp(new Date().getTime());
			
			Map queryMap = new HashMap();
			queryMap.put(JmsMapConstant.SBRQ, sbrq);
			queryMap.put(JmsMapConstant.QXDM, jbsj.getSwjgzzjgdm().substring(0,
					2));
			queryMap.put(JmsMapConstant.DJZCLXDM, jbsj.getDjzclxdm());
			voPackage.setData(queryMap);

			// 调用后台操作，取得返回值
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);
			Map voMap = (Map) voPackage.getData();
			List szsmList = (List) voMap.get(JmsMapConstant.LIST_SZSM);
			List jmxmList = (List) voMap.get(JmsMapConstant.LIST_JMXM);

			Map szsmListMap = makeSzsmList(szsmList);
			Map jmxmListMap = makeJmxmList(jmxmList); // 生成减免项目及代码Map 减免税项目  tujb 200404
			List szList = (List) szsmListMap.get(SessionKey.SESSION_JMSZ_LIST);
			List smList = (List) szsmListMap.get(SessionKey.SESSION_JMSM_LIST);
			List jmxmNewList = (List) jmxmListMap.get(SessionKey.SESSION_JMXM_LIST); // 生成减免项目及代码List 减免税项目  tujb 200404

			// 取得本期已申报数据
			List bqysb = (List) voMap.get(JmsMapConstant.LIST_JMYSB);
			List jmsList = makeJmsList(szsmMap, jmflMap, bqysb, jmxmMap);
			List skssrqList = makeSkssrqList(); // 生成特定税款所属日期Map 减免税项目  tujb 200404
			List sknyList = makeSknyList(); // 生成特定年月Map 减免税项目  tujb 200404

			// 取得附加税List
			List fjsList = null;
			String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
			Djzclx djzclx = (Djzclx) CodeTableUtil.getCodeTableMap(request,
					CodeTable.DJZCLX_MAP).get(djzclxdm);
			if (djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GAT)
					|| djzclx.getNwzfldm().equals(
							CodeConstant.DJZCLXDM_NWZFLDM_WZ)) {
				// 取外资企业附加税
				fjsList = CodeTableUtil.getCodeTableList(request,
						CodeTable.SZSMFJS_WQ_LIST);
			} else {
				// 取内资企业附加税
				fjsList = CodeTableUtil.getCodeTableList(request,
						CodeTable.SZSMFJS_LIST);
			}

			JmsVO jms = convertToXmlVO(jbsj, bqysb, jmsList, jmflList, smList, szList, jmxmNewList);
			if (jmsList != null && jmsList.size() != 0) {
				jms.setYwczlx(CAcodeConstants.YWCZLX_NEW);
			}else{
				jms.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			}
			String xmlStr = jms.toXML();
			Debug.out(xmlStr);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xmlStr);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jms
					.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jms
					.getSchemaVersion());
			// 把税种、税目、本期已申报List放入session
			HttpSession session = request.getSession();
			session.setAttribute(SessionKey.SESSION_JMSZ_LIST, szList);
			session.setAttribute(SessionKey.SESSION_JMSM_LIST, smList);
			session.setAttribute(SessionKey.SESSION_BQYSB_LIST, bqysb);
			session.setAttribute(SessionKey.SESSION_FJS_LIST, fjsList);
			session.setAttribute(SessionKey.SESSION_JMFL_LIST, jmflList);
			session.setAttribute(SessionKey.SESSION_JMSB_LIST, jmsList);
			session.setAttribute(SessionKey.SESSION_JMXM_LIST, jmxmNewList);
			session.setAttribute(SessionKey.SESSION_SKSSRQ_LIST, skssrqList);
			session.setAttribute(SessionKey.SESSION_SKSNY_LIST, sknyList);
			// 转向到显示页面
			return CAConstants.SHOW;
		} catch (Exception e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"操作失败，显示数据出错！");
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
	}

	private JmsVO convertToXmlVO(SWDJJBSJ jbsj, List bqysb, List jmsbList, List jmflList,
			List smJsList, List szList, List jmxmList) throws ApplicationException {
		JmsVO jms = new JmsVO();
		NsrxxVO_JMS nsrxx = new NsrxxVO_JMS();
		SbxxVO_JMS sbxx = new SbxxVO_JMS();
		SbsjlistVO sbsjlist = new SbsjlistVO();
		SbsjVO sbsj = new SbsjVO();
		// 设置纳税人信息
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(TinyTools.replaceBlank(jbsj.getNsrmc()));
		nsrxx.setQylxdh(TinyTools.replaceBlank(jbsj.getJydzlxdm()));
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		
		DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
		if (bqysb != null && bqysb.size() != 0) 
		{
			for (int i = 0; i < bqysb.size(); i++) 
			{
				Jm ysbData = (Jm) bqysb.get(0);

				// 给前台值对象赋值
				if(String.valueOf(ysbData.getDqxse()) != null && !String.valueOf(ysbData.getDqxse()).equals("")&& !String.valueOf(ysbData.getDqxse()).equals("null"))
				{
					nsrxx.setDqxse(String.valueOf(deFormat.format(ysbData.getDqxse())));
				}
				if(String.valueOf(ysbData.getDqlrze()) != null && !String.valueOf(ysbData.getDqlrze()).equals("")&& !String.valueOf(ysbData.getDqlrze()).equals("null"))
				{
					nsrxx.setDqlrze(String.valueOf(deFormat.format(ysbData.getDqlrze())));
				}
				if(String.valueOf(ysbData.getQyrs()) != null && !String.valueOf(ysbData.getQyrs()).equals("")&& !String.valueOf(ysbData.getQyrs()).equals("null"))
				{
					nsrxx.setQyrs(String.valueOf(ysbData.getQyrs()));
				}
				if(String.valueOf(ysbData.getAzrs()) != null && !String.valueOf(ysbData.getAzrs()).equals("")&& !String.valueOf(ysbData.getAzrs()).equals("null"))
				{
					nsrxx.setAzrs(String.valueOf(ysbData.getAzrs()));
				}
/*				if(String.valueOf(ysbData.getAzbl()) != null && !String.valueOf(ysbData.getAzbl()).equals("") && !String.valueOf(ysbData.getAzbl()).equals("null"))
				{
					nsrxx.setAzbl(String.valueOf(ysbData.getAzbl()));
				}*/
			}
		}
		
		// 申报信息
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(curTime);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String skssksrq = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
		//String skssjsrq = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
		sbxx.setSbrq(df.format(curTime));
		//sbxx.setSkssksrq(skssksrq);
		//sbxx.setSkssjsrq(skssjsrq);
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		String nd = (String) ((Map) Skssrq.yearSkssrq(new Date()))
				.get(Skssrq.SKSSRQ_ND);
		// String nd = new SimpleDateFormat("yyyy").format(curTime);
		sbxx.setNd(nd);
		// 申报数据
		int size = 1;
		if (jmsbList != null && jmsbList.size() != 0) {
			size = jmsbList.size();
		}
		String[] hc = new String[size];
		String[] jmfldm = new String[size];
		String[] jmflmc = new String[size];
		String[] szdm = new String[size];
		String[] szmc = new String[size];
		String[] szsmdm = new String[size];
		String[] szsmmc = new String[size];
		String[] aslj = new String[size];
		String[] kssl = new String[size];
		String[] jsje = new String[size];
		String[] jmse = new String[size];
		String[] cjsj = new String[size];
		String[] jmxmdm = new String[size];
		String[] jmxmjdm = new String[size];
		String[] jmxmszdm = new String[size];
		String[] jmxmksrq = new String[size];
		String[] jmxmjsrq = new String[size];
		String[] skssksrq = new String[size];
		String[] skssjsrq = new String[size];
		String hj = "";
		String ifjmzg = "true";
		String jmzghc = "";
		String alertStr = "";
		if (jmsbList != null && jmsbList.size() != 0) {
			for (int i = 0; i < jmsbList.size(); i++) {
				JmsInfo jmsInfo = (JmsInfo) jmsbList.get(i);
				if (!jmsInfo.canShenpi()
						&& jmsInfo.getJmlxdm().equals(CodeConstant.JMLX_SP)) {
					alertStr = alertStr + (i + 1) + "、";
				}
				hc[i] = i + 1 + "";
				for (int j = 0; j < jmflList.size(); j++) {
					Jmfl tmpJmfl = (Jmfl) jmflList.get(j);
					if (jmsInfo.getJmlxdm().equals(tmpJmfl.getJmfldm())) {
						jmfldm[i] = tmpJmfl.getJmfldm();
						jmflmc[i] = tmpJmfl.getJmflmc();
					}
				}
				for (int j = 0; j < jmxmList.size(); j++) {
					JmsxmInfor tmpJmxm = (JmsxmInfor) jmxmList.get(j);
					if (jmsInfo.getJmxmjdm()!= null && jmsInfo.getJmxmjdm().equals(tmpJmxm.getJmslxdm())) {
						jmxmszdm[i] = tmpJmxm.getSzdm();
						jmxmdm[i] = tmpJmxm.getJmslxdm();
						jmxmjdm[i] = tmpJmxm.getWh();
					}
				}
				// *******************************

				for (int j = 0; j < szList.size(); j++) {
					JmsSzsm szsm = (JmsSzsm) szList.get(j);
					if (szsm.getSzsmdm().equals(jmsInfo.getSzdm())) {
						szdm[i] = szsm.getSzsmdm();
						szmc[i] = szsm.getSzsmmc();
					}
				}
				for (int j = 0; j < smJsList.size(); j++) {
					JmsSzsm szsm = (JmsSzsm) smJsList.get(j);
					if (szsm.getSzsmdm().startsWith(jmsInfo.getSzdm())) {
						if (szsm.getSzsmdm().equals(jmsInfo.getSzsmdm())) {
							szsmdm[i] = szsm.getSzsmdm();
							szsmmc[i] = szsm.getSzsmmc();
						}
					}
				}
				aslj[i] = jmsInfo.isAslj() + "";
				if (jmsInfo.getKssl() != null) {
					kssl[i] = JspUtil.format(jmsInfo.getKssl());
				} else {
					kssl[i] = "";
				}
				jmxmksrq[i] = jmsInfo.getJmxmksrq();
				jmxmjsrq[i] = jmsInfo.getJmxmjsrq();
				
				
				skssksrq[i] = jmsInfo.getSkssksrq();
				skssjsrq[i] = jmsInfo.getSkssjsrq();
				
				/*String ny = TinyTools.getProperty(CodeConstant.WSSB_JMSB_ACCESSCONTROL_NY);
				String skssrq = TinyTools.getProperty(CodeConstant.WSSB_JMSB_ACCESSCONTROL_SKSSQ);
				String[] nyArr = TinyTools.divideString(ny, CodeConstant.SEPARATOR);
				String[] skssrqArr = TinyTools.divideString(skssrq, CodeConstant.SEPARATOR);
				Date now = new Date();
				SimpleDateFormat from = new SimpleDateFormat("yyyyMMdd HH24:mm:ss");
				String strDate = from.format(now).substring(0, 6);
				int intDate = Integer.parseInt(strDate);
				
				System.out.println("strDate >>>>>:"+strDate+" now:"+now+" intDate:"+intDate);
				
				for(int j=0; j<nyArr.length;j++)
				{
					System.out.println(i+" nyArr:"+nyArr[i]);
					if(strDate.equals(nyArr[i]))
					{
						skssksrq[i] = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
						skssjsrq[i] = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
					}
					else
					{
						skssksrq[i] = jmsInfo.getSkssksrq();
						skssjsrq[i] = jmsInfo.getSkssjsrq();
					}
				}*/
				
				jsje[i] = JspUtil.format(jmsInfo.getJsje());
				jmse[i] = JspUtil.format(jmsInfo.getJmse());
				cjsj[i] = jmsInfo.getCjsj().getTime() + "";
			}
		} else {
			for (int i = 0; i < size; i++) {
				hc[i] = i + 1 + "";
				jmfldm[i] = "1";
				jmflmc[i] = "审批减免";
				szdm[i] = "02";
				szmc[i] = "02 营业税";
				szsmdm[i] = "";
				szsmmc[i] = "";
				jmxmszdm[i] = "";
				jmxmdm[i] = "";
				jmxmjdm[i] = "";
				jmxmksrq[i] = "";
				jmxmjsrq[i] = "";
				skssksrq[i] = "";
				skssjsrq[i] = "";
				aslj[i] = "false";
				kssl[i] = "";
				jsje[i] = "";
				jmse[i] = "";
				cjsj[i] = "";
			}
		}
		if (alertStr != null && !alertStr.equals("")) {
			ifjmzg = "false";
			jmzghc = alertStr.substring(0, alertStr.length() - 1);
		}
		sbsj.setHc(hc);
		sbsj.setJmfldm(jmfldm);
		sbsj.setJmflmc(jmflmc);
		sbsj.setJmxmszdm(jmxmszdm);
		sbsj.setJmxmdm(jmxmdm);
		sbsj.setJmxmjdm(jmxmjdm);
		sbsj.setJmxmksrq(jmxmksrq);
		sbsj.setJmxmjsrq(jmxmjsrq);
		sbsj.setSkssksrq(skssksrq);
		sbsj.setSkssjsrq(skssjsrq);
		sbsj.setSzdm(szdm);
		sbsj.setSzmc(szmc);
		sbsj.setSzsmdm(szsmdm);
		sbsj.setSzsmmc(szsmmc);
		sbsj.setAslj(aslj);
		sbsj.setKssl(kssl);
		sbsj.setJsje(jsje);
		sbsj.setJmse(jmse);
		sbsj.setCjsj(cjsj);

		sbsjlist.setSbsj(sbsj);
		sbsjlist.setHj(hj);
		sbsjlist.setIfjmzg(ifjmzg);
		sbsjlist.setJmzghc(jmzghc);

		jms.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_JMS).substring(0, 8));
		jms.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_JMS).substring(8));
		jms.setYwlx(CAcodeConstants.YWDM_SB_WS_JMS);
		
		jms.setNsrxx(nsrxx);
		jms.setSbxx(sbxx);
		jms.setSbsjlist(sbsjlist);

		return jms;

	}
	
	
	private JmsVO convertToXmlVO(JmsVO jmsvo, SWDJJBSJ jbsj, List jmsbList, List jmflList,
			List smJsList, List szList, List jmxmList) throws ApplicationException {
		JmsVO jms = new JmsVO();
		NsrxxVO_JMS nsrxx = new NsrxxVO_JMS();
		SbxxVO_JMS sbxx = new SbxxVO_JMS();
		SbsjlistVO sbsjlist = new SbsjlistVO();
		SbsjVO sbsj = new SbsjVO();
		// 设置纳税人信息
		
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(TinyTools.replaceBlank(jbsj.getNsrmc()));
		nsrxx.setQylxdh(TinyTools.replaceBlank(jbsj.getJydzlxdm()));
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		nsrxx.setDqxse(jmsvo.getNsrxx().getDqxse());
		nsrxx.setDqlrze(jmsvo.getNsrxx().getDqlrze());
		nsrxx.setQyrs(jmsvo.getNsrxx().getQyrs());
		nsrxx.setAzrs(jmsvo.getNsrxx().getAzrs());
		
		// 申报信息
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(curTime);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String skssksrq = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
		//String skssjsrq = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
		sbxx.setSbrq(df.format(curTime));
		//sbxx.setSkssksrq(skssksrq);
		//sbxx.setSkssjsrq(skssjsrq);
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		String nd = (String) ((Map) Skssrq.yearSkssrq(new Date()))
				.get(Skssrq.SKSSRQ_ND);
		// String nd = new SimpleDateFormat("yyyy").format(curTime);
		sbxx.setNd(nd);
		// 申报数据
		int size = 1;
		if (jmsbList != null && jmsbList.size() != 0) {
			size = jmsbList.size();
		}
		String[] hc = new String[size];
		String[] jmfldm = new String[size];
		String[] jmflmc = new String[size];
		String[] szdm = new String[size];
		String[] szmc = new String[size];
		String[] szsmdm = new String[size];
		String[] szsmmc = new String[size];
		String[] aslj = new String[size];
		String[] kssl = new String[size];
		String[] jsje = new String[size];
		String[] jmse = new String[size];
		String[] cjsj = new String[size];
		String[] jmxmdm = new String[size];
		String[] jmxmjdm = new String[size];
		String[] jmxmszdm = new String[size];
		String[] jmxmksrq = new String[size];
		String[] jmxmjsrq = new String[size];
		String[] skssksrq = new String[size];
		String[] skssjsrq = new String[size];
		String hj = "";
		String ifjmzg = "true";
		String jmzghc = "";
		String alertStr = "";
		if (jmsbList != null && jmsbList.size() != 0) 
		{
			for (int i = 0; i < jmsbList.size(); i++) 
			{
				JmsInfo jmsInfo = (JmsInfo) jmsbList.get(i);
				if (!jmsInfo.canShenpi()
						&& jmsInfo.getJmlxdm().equals(CodeConstant.JMLX_SP)) {
					alertStr = alertStr + (i + 1) + "、";
				}
				hc[i] = i + 1 + "";
				for (int j = 0; j < jmflList.size(); j++) {
					Jmfl tmpJmfl = (Jmfl) jmflList.get(j);
					if (jmsInfo.getJmlxdm().equals(tmpJmfl.getJmfldm())) {
						jmfldm[i] = tmpJmfl.getJmfldm();
						jmflmc[i] = tmpJmfl.getJmflmc();
					}
				}
				for (int j = 0; j < jmxmList.size(); j++) {
					JmsxmInfor tmpJmxm = (JmsxmInfor) jmxmList.get(j);
					if (jmsInfo.getJmxmjdm().equals(tmpJmxm.getJmslxdm())) {
						jmxmszdm[i] = tmpJmxm.getSzdm();
						jmxmdm[i] = tmpJmxm.getJmslxdm();
						jmxmjdm[i] = tmpJmxm.getWh();
					}
				}
				// *******************************

				for (int j = 0; j < szList.size(); j++) {
					JmsSzsm szsm = (JmsSzsm) szList.get(j);
					if (szsm.getSzsmdm().equals(jmsInfo.getSzdm())) {
						szdm[i] = szsm.getSzsmdm();
						szmc[i] = szsm.getSzsmmc();
					}
				}
				for (int j = 0; j < smJsList.size(); j++) {
					JmsSzsm szsm = (JmsSzsm) smJsList.get(j);
					if (szsm.getSzsmdm().startsWith(jmsInfo.getSzdm())) {
						if (szsm.getSzsmdm().equals(jmsInfo.getSzsmdm())) {
							szsmdm[i] = szsm.getSzsmdm();
							szsmmc[i] = szsm.getSzsmmc();
						}
					}
				}
				aslj[i] = jmsInfo.isAslj() + "";
				if (jmsInfo.getKssl() != null) {
					kssl[i] = JspUtil.format(jmsInfo.getKssl());
				} else {
					kssl[i] = "";
				}
				jmxmksrq[i] = jmsInfo.getJmxmksrq();
				jmxmjsrq[i] = jmsInfo.getJmxmjsrq();
				skssksrq[i] = jmsInfo.getSkssksrq();
				skssjsrq[i] = jmsInfo.getSkssjsrq();
				
				/*String ny = TinyTools.getProperty(CodeConstant.WSSB_JMSB_ACCESSCONTROL_NY);
				String[] nyArr = TinyTools.divideString(ny, CodeConstant.SEPARATOR);
				Date now = new Date();
				SimpleDateFormat from = new SimpleDateFormat("yyyyMMdd HH24:mm:ss");
				String strDate = from.format(now).substring(0, 6);
				int intDate = Integer.parseInt(strDate);
				
				System.out.println("2 strDate >>>>>:"+strDate+" now:"+now+" intDate:"+intDate);
				
				for(int j=0; j<nyArr.length;j++)
				{
					System.out.println(i+" nyArr:"+nyArr[i]);
					if(strDate.equals(nyArr[i]))
					{
						skssksrq[i] = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
						skssjsrq[i] = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
					}
					else
					{
						skssksrq[i] = jmsInfo.getSkssksrq();
						skssjsrq[i] = jmsInfo.getSkssjsrq();
					}
				}*/
				
				jsje[i] = JspUtil.format(jmsInfo.getJsje());
				jmse[i] = JspUtil.format(jmsInfo.getJmse());
				cjsj[i] = jmsInfo.getCjsj().getTime() + "";
			}
		} else {
			for (int i = 0; i < size; i++) {
				hc[i] = i + 1 + "";
				jmfldm[i] = "1";
				jmflmc[i] = "审批减免";
				szdm[i] = "02";
				szmc[i] = "02 营业税";
				szsmdm[i] = "";
				szsmmc[i] = "";
				jmxmszdm[i] = "";
				jmxmdm[i] = "";
				jmxmjdm[i] = "";
				jmxmksrq[i] = "";
				jmxmjsrq[i] = "";
				aslj[i] = "false";
				kssl[i] = "";
				jsje[i] = "";
				jmse[i] = "";
				cjsj[i] = "";
			}
		}
		if (alertStr != null && !alertStr.equals("")) {
			ifjmzg = "false";
			jmzghc = alertStr.substring(0, alertStr.length() - 1);
		}
		sbsj.setHc(hc);
		sbsj.setJmfldm(jmfldm);
		sbsj.setJmflmc(jmflmc);
		sbsj.setJmxmszdm(jmxmszdm);
		sbsj.setJmxmdm(jmxmdm);
		sbsj.setJmxmjdm(jmxmjdm);
		sbsj.setJmxmksrq(jmxmksrq);
		sbsj.setJmxmjsrq(jmxmjsrq);
		sbsj.setSkssksrq(skssksrq);
		sbsj.setSkssjsrq(skssjsrq);
		sbsj.setSzdm(szdm);
		sbsj.setSzmc(szmc);
		sbsj.setSzsmdm(szsmdm);
		sbsj.setSzsmmc(szsmmc);
		sbsj.setAslj(aslj);
		sbsj.setKssl(kssl);
		sbsj.setJsje(jsje);
		sbsj.setJmse(jmse);
		sbsj.setCjsj(cjsj);

		sbsjlist.setSbsj(sbsj);
		sbsjlist.setHj(hj);
		sbsjlist.setIfjmzg(ifjmzg);
		sbsjlist.setJmzghc(jmzghc);

		jms.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_JMS).substring(0, 8));
		jms.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(
				CAcodeConstants.YWDM_SB_WS_JMS).substring(8));
		jms.setYwlx(CAcodeConstants.YWDM_SB_WS_JMS);
		
		jms.setNsrxx(nsrxx);
		jms.setSbxx(sbxx);
		jms.setSbsjlist(sbsjlist);

		return jms;

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
		// 取得登记Map
		Map djMap = FriendHelper.getDjInfo(request);
		// 登记基本数据
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
		DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();

		if (userData.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userData.getCert(), userData
						.getSsdwdm());
			} catch (ApplicationException e1) {
				//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
						//"操作失败，验证签名出错！");
				e1.printStackTrace();
				throw ExceptionUtil.getBaseException(e1);
			}
		}
		JmsVO jms = new JmsVO();
		try {
			//XMLParseHelper.parseXMLString(jms, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//jms = new JmsVO();
			XMLParseHelper.parseXMLString(jms, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"操作失败，解析xml数据出错！");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(jms.getYwczlx());
        dzyj.setYwdm(jms.getYwlx());
        
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			// 从session中取得税种、税目、本期已申报、附加税List
			HttpSession session = request.getSession();
			List smList = (List) session.getAttribute(SessionKey.SESSION_JMSM_LIST);
			List szList = (List) session.getAttribute(SessionKey.SESSION_JMSZ_LIST);
			List bqysbList = (List) session.getAttribute(SessionKey.SESSION_BQYSB_LIST);
			List fjsList = (List) session.getAttribute(SessionKey.SESSION_FJS_LIST);
			List jmxmList = CodeTableUtil.getCodeTableList(request, CodeTable.JMXM_BASX_LIST);
			Map jmxmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.JMXM_BASX_MAP);
			
			// 取得税种税目Map
			Map szsmMap = CodeTableUtil.getCodeTableMap(request,
					CodeTable.SZSM_MAP);
			// 取得减免类型List
			Map jmflMap = CodeTableUtil.getCodeTableMap(request,
					CodeTable.JMFL_MAP);
			List jmflList = CodeTableUtil.getCodeTableList(request,
					CodeTable.JMFL_LIST);
			
			Map jmxmListMap = makeJmxmList(jmxmList);
			List jmxmNewList = (List) jmxmListMap.get(SessionKey.SESSION_JMXM_LIST);

			// 取得征期类型代码
			String[] szsmdm = jms.getSbsjlist().getSbsj().getSzsmdm();
			String[] zqlxdm = new String[szsmdm.length];
			if (szsmdm != null) {
				for (int i = 0; i < szsmdm.length; i++) {
					Szsm szsm = (Szsm) szsmMap.get(szsmdm[i]);
					zqlxdm[i] = szsm.getZqlxdm();
				}
			}

			// 如果数据校验成功，提交数据给后台
			Map jmxm = new HashMap();
			boolean canSubmit = reflashJmsList(jms, zqlxdm, szsmMap, jmflMap,
					jbsj, jmxm);
			if (canSubmit) {
				// 生成vopackage并设置参数
				VOPackage vo = new VOPackage();
				vo.setAction(JmsActionConstant.INT_ACTION_SAVE);
				vo.setProcessor(ProcessorNames.JMS_PROCESSOR);
				vo.setUserData(userData);
				Map data = getSbData(jms, jbsj, zqlxdm, szsmMap, bqysbList,
						jmxm);
				data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
				data.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jms);
				vo.setData(data);
				// 调用后台保存数据
				Map retMap = (Map) ShenbaoProxy.getInstance().process(vo);

				// 保存签名数据
				if (userData.getCaflag()) {
					dzyj = (DzyjsjVO) retMap
							.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
					request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,
							Long.toString(dzyj.getLsh()));
				} else {
					request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,
							"");
				}
				request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,
						"减免税申报表"+CAUtils.getTsxx(jms.getYwczlx()));
				request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
						"/shenbao/jms.dc?actionType=Show");
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "减免税申报表"+CAUtils.getTsxx(jms.getYwczlx()));
				LogUtil.getInstance().log(FriendHelper.getUserData(request),
						"减免税申报表",
						(new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
				// 清除session
				clearSession(request);
				// 转向成功页面
				return CAUtils.jumpTo(userData.getCaflag(), CAConstants.SUCCESS,
						CAConstants.SUCCESSSB);
			} else {
				List jmsList = (List) jmxm.get(SessionKey.SESSION_JMSB_LIST);
				JmsVO jmsVO = convertToXmlVO(jms, jbsj, jmsList, jmflList, smList, szList, jmxmNewList);
                if (jmsList != null && jmsList.size() != 0) {
                    jms.setYwczlx(CAcodeConstants.YWCZLX_NEW);
                }else{
                    jms.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
                }
                
				String xmlStr = jmsVO.toXML();
//				System.out.println(xmlStr);
				request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xmlStr);
				request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
						jmsVO.getXsltVersion());
				request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
						jmsVO.getSchemaVersion());
				// 返回显示页面
				return CAConstants.SHOW;
			}
		} catch (Exception ex) {
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"减免税申报表", (new SimpleDateFormat("yyyyMMdd")).format(now),
					"失败!");
			String message = null;
			if (ex instanceof BaseException) {
				message = ((BaseException) ex).getMessage();
			} else {
				ex.printStackTrace();
				message = "系统异常，请与管理员联系";
			}
			ActionErrors errors = new ActionErrors();
			errors.addError(message);
			saveErrors(request, errors);
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, message);
			return CAConstants.FAILURE;
			// 异常处理
			// throw ExceptionUtil.getBaseException(ex);
		}
	}

	private Map getSbData(JmsVO jms, SWDJJBSJ jbsj, String[] zqlxdm,
			Map szsmMap, List jmysbList, Map jmxm) throws ApplicationException {
		try {
			Map data = new HashMap();
			List sbList = new ArrayList();
			SbsjVO sbsj = jms.getSbsjlist().getSbsj();
			String[] jmxmdm = (String[]) jmxm.get("jmxmdm");
			for (int i = 0; i < sbsj.getSzsmdm().length; i++) 
			{
				Jm vo = new Jm();
				// 计算机代码
				vo.setJsjdm(jbsj.getJsjdm());
				// 方式代码
				vo.setFsdm(CodeConstant.FSDM_WSSB);
				// 减免类型
				// vo.setJmlx(this.jmlx[i]);
				vo.setJmlx(sbsj.getJmfldm()[i]);
				// 减免税额
				vo.setJmse(new BigDecimal(sbsj.getJmse()[i]));
				// 计税金额
				vo.setJsje(new BigDecimal(sbsj.getJsje()[i]));
				// 课税数量
				Szsm szsmTmp = (Szsm) szsmMap.get(sbsj.getSzsmdm()[i]); // 取得Szsm值对象
				if (szsmTmp.getAsljbs() != null ? szsmTmp.getAsljbs().equals(
						"1") : false) // 是否按数量记
				{
					vo.setKssl(new BigDecimal(sbsj.getKssl()[i]));
				}
				// 减免类型代码
				vo.setJmxmdm(jmxmdm[i]);
				// 登记注册类型代码
				vo.setDjzclxdm(jbsj.getDjzclxdm());
				// 国家标准行业代码
				vo.setGjbzhydm(jbsj.getGjbzhydm());
				// 录入人
				vo.setLrr(jbsj.getJsjdm());
				// 录入日期
				Date sbrq2 = null;
				try {
					sbrq2 = DateTimeUtil.stringToTimestamp(jms.getSbxx().getSbrq(), "yyyy-MM-dd HH:mm:ss");
				} catch (Exception e) {
					sbrq2 = DateTimeUtil.stringToTimestamp(jms.getSbxx().getSbrq(), "yyyy-MM-dd");
					e.printStackTrace();
				}
				
				Timestamp sbrq = new Timestamp(sbrq2.getTime() + i*1000);
				vo.setLrrq(sbrq);
				// 纳税人名称
				// vo.setNsrmc(nsrzl.getNsrmc());
				// 申报日期
				vo.setSbrq(TinyTools.second2Day(sbrq));
				// 税务机关组织结构代码
				vo.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
				// 税种税目代码
				vo.setSzsmdm(sbsj.getSzsmdm()[i]);
				// 创建时间
				if (sbsj.getCjsj()[i] == null || sbsj.getCjsj()[i].equals("")) {
					vo.setCjrq(sbrq);
				} else {
					vo.setCjrq(new Timestamp(Long.parseLong(sbsj.getCjsj()[i])+ i*1000));
				}
				// 年度
				vo.setNd(TinyTools.Date2String(sbrq).substring(0, 4));
				// 区县代码
				vo.setQxdm(jbsj.getSwjgzzjgdm().substring(0, 2));
				vo.setJmxmjdm(sbsj.getJmxmdm()[i]);
				
				if (sbsj.getJmxmksrq()[i] != null && !sbsj.getJmxmksrq()[i].equals("")) 
				{
					vo.setJmxmksrq(new Timestamp(TinyTools.stringToDate(sbsj.getJmxmksrq()[i], "yyyyMMdd").getTime()));
				}
				if (sbsj.getJmxmjsrq()[i] != null && !sbsj.getJmxmjsrq()[i].equals("")) 
				{
					vo.setJmxmjsrq(new Timestamp(TinyTools.stringToDate(sbsj.getJmxmjsrq()[i], "yyyyMMdd").getTime()));
				}
				
				//System.out.println("vo:"+vo.getJmxmjdm()+">"+sbsj.getJmxmjdm()[i]+">"+sbsj.getJmxmdm()[i]+">"+vo.getJmxmksrq()+">"+sbsj.getJmxmksrq()[i]);
				
				String dqxse = StringUtils.trim(String.valueOf(jms.getNsrxx().getDqxse()));
				String dqlrze = StringUtils.trim(String.valueOf(jms.getNsrxx().getDqlrze()));
				String qyrs = StringUtils.trim(String.valueOf(jms.getNsrxx().getQyrs()));
				String azrs = StringUtils.trim(String.valueOf(jms.getNsrxx().getAzrs()));
				//String azbl = StringUtils.trim(String.valueOf(jms.getNsrxx().getAzbl()));
				if(dqxse != null && !dqxse.equals("")&& !dqxse.equals("null"))
				{
					vo.setDqxse(new BigDecimal(jms.getNsrxx().getDqxse()));
				}
				if(dqlrze != null && !dqlrze.equals("")&& !dqlrze.equals("null"))
				{
					vo.setDqlrze(new BigDecimal(jms.getNsrxx().getDqlrze()));
				}
				if(qyrs != null && !qyrs.equals("")&& !qyrs.equals("null"))
				{
					vo.setQyrs(new BigDecimal(jms.getNsrxx().getQyrs()));
				}
				if(azrs != null && !azrs.equals("")&& !azrs.equals("null"))
				{
					vo.setAzrs(new BigDecimal(jms.getNsrxx().getAzrs()));
				}
				/*if(azbl != null && !azbl.equals("") && !azbl.equals("null"))
				{
					vo.setAzbl(new BigDecimal(jms.getNsrxx().getAzbl()));
				}*/

				// 取得税款所属日期
				Map skssrqMap = Skssrq.getSksssq(jbsj.getJsjdm(), sbsj
						.getSzsmdm()[i], jbsj.getDjzclxdm(),
						CodeConstant.SKLXDM_ZCJK, zqlxdm[i], sbrq, null, null,
						null);
				if (sbsj.getSkssksrq() != null && sbsj.getSkssksrq()[i] != null && !sbsj.getSkssksrq()[i].equals("")) 
				{
					vo.setSkssksrq(new Timestamp(TinyTools.stringToDate(sbsj.getSkssksrq()[i], "yyyyMMdd").getTime()));
					//System.out.println("vo 1:"+vo.getSkssksrq()+">"+sbsj.getSkssksrq()[i]);
				}
				else
				{
					vo.setSkssjsrq((Timestamp) skssrqMap.get(Skssrq.SKSSJSRQ));
				}
				
				if (sbsj.getSkssjsrq() != null && sbsj.getSkssjsrq()[i] != null && !sbsj.getSkssjsrq()[i].equals("")) 
				{
					vo.setSkssjsrq(new Timestamp(TinyTools.stringToDate(sbsj.getSkssjsrq()[i], "yyyyMMdd").getTime()));
					//System.out.println("vo 2:"+vo.getSkssjsrq()+">"+sbsj.getSkssjsrq()[i]);
				}
				else
				{
					vo.setSkssksrq((Timestamp) skssrqMap.get(Skssrq.SKSSKSRQ));
				}

				sbList.add(vo);
			}
			// 把本次申报数据和本期已申报数据放入
			data.put(JmsMapConstant.LIST_JMSB, sbList);
			data.put(JmsMapConstant.LIST_JMYSB, jmysbList);
			data.put(JmsMapConstant.ZQLXDM, zqlxdm);

			return data;
		} catch (Exception e) {
			throw new ApplicationException("您提交了错误的数据！请检查后重新申报！");
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
		// 取得登记Map
		Map djMap = FriendHelper.getDjInfo(request);
		// 登记基本数据
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
		DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();

		if (userData.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, userData.getCert(), userData
						.getSsdwdm());
			} catch (ApplicationException e1) {
				//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					//	"操作失败，验证签名出错！");
				e1.printStackTrace();
				throw ExceptionUtil.getBaseException(e1);
			}
		}
		JmsVO jms = new JmsVO();
		try {
			//XMLParseHelper.parseXMLString(jms, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//jms = new JmsVO();
			XMLParseHelper.parseXMLString(jms, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"操作失败，解析xml数据出错！");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(jms.getYwczlx());
        dzyj.setYwdm(jms.getYwlx());
        
		// 从session中取得税种、税目、本期已申报List
		HttpSession session = request.getSession();
		List smList = (List) session.getAttribute(SessionKey.SESSION_JMSM_LIST);
		List szList = (List) session.getAttribute(SessionKey.SESSION_JMSZ_LIST);
		List bqysbList = (List) session
				.getAttribute(SessionKey.SESSION_BQYSB_LIST);
		List fjsList = (List) session.getAttribute(SessionKey.SESSION_FJS_LIST);

		Timestamp sbrq = new Timestamp(new Date().getTime());

		// 将本期已申报数据作为参数放入Map
		Map dataMap = new HashMap();
		dataMap.put(JmsMapConstant.LIST_JMYSB, bqysbList);
		dataMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		dataMap.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jms);
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		try {
			// 声明vopackage并设置参数
			VOPackage vo = new VOPackage();
			vo.setAction(JmsActionConstant.INT_ACTION_DELETE);
			vo.setProcessor(ProcessorNames.JMS_PROCESSOR);
			vo.setUserData(userData);
			vo.setData(dataMap);
			// 调用后台保存数据
			ShenbaoProxy.getInstance().process(vo);
			request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,
					"减免税申报表"+CAUtils.getTsxx(jms.getYwczlx()));
			request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
					"/shenbao/jms.dc?actionType=Show");

			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"减免税申报表",
					(new SimpleDateFormat("yyyyMMdd")).format(curTime), "成功!");
			// 清除session
			clearSession(request);

			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"减免税申报表"+CAUtils.getTsxx(jms.getYwczlx()));
			// 转向成功页面
			return CAUtils.jumpTo(userData.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"减免税申报表",
					(new SimpleDateFormat("yyyyMMdd")).format(curTime), "失败!");
			String message = null;
			if (ex instanceof BaseException) {
				message = ((BaseException) ex).getMessage();
			} else {
				ex.printStackTrace();
				message = "系统异常，请与管理员联系";
			}

			ActionErrors errors = new ActionErrors();

			errors.addError(message);
			saveErrors(request, errors);
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"删除减免税申报资料失败！");
			return CAConstants.FAILURE;
			// throw ExceptionUtil.getBaseException(ex);

		}
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) {
		// 清除session
		clearSession(request);
		return CAConstants.RETURN;
	}

	private List makeJmsList(Map szsmMap, Map jmflMap, List bqysb, Map jmxmMap)
			throws BaseException {
		List jmsTmpList = new ArrayList();
		try {
			// 加入本期已申报数据
			if (bqysb != null) {
				for (int i = 0; i < bqysb.size(); i++) {
					JmsInfo jmsInfo = new JmsInfo();
					
					Jm ysbData = (Jm) bqysb.get(i);

					// 给前台值对象赋值
					jmsInfo.setJmlxdm(ysbData.getJmlx());
					jmsInfo.setJmlxmc(((Jmfl) jmflMap.get(ysbData.getJmlx())).getJmflmc());
					String szsmdm = ysbData.getSzsmdm();
					jmsInfo.setSzsmdm(szsmdm);
					jmsInfo.setJmxmjdm(ysbData.getJmxmjdm());
					
					if(ysbData != null && ysbData.getJmxmksrq() != null)
					{
						jmsInfo.setJmxmksrq(TinyTools.Date2String(ysbData.getJmxmksrq(), "yyyyMMdd"));
					}
					if(ysbData != null && ysbData.getJmxmjsrq() != null)
					{
						jmsInfo.setJmxmjsrq(TinyTools.Date2String(ysbData.getJmxmjsrq(), "yyyyMMdd"));
					}
					if(ysbData != null && ysbData.getSkssksrq() != null)
					{
						jmsInfo.setSkssksrq(TinyTools.Date2String(ysbData.getSkssksrq(), "yyyyMMdd"));
					}
					if(ysbData != null && ysbData.getSkssjsrq() != null)
					{
						jmsInfo.setSkssjsrq(TinyTools.Date2String(ysbData.getSkssjsrq(), "yyyyMMdd"));
					}

					// 上一级税种代码
					String szdm;
					// 营业税 & 个人所得税
					if (szsmdm.startsWith(SzsmdmConstant.YYS)
							|| szsmdm.startsWith(SzsmdmConstant.GRSDS)) {
						szdm = szsmdm.substring(0, 4);
					}
					// 资源税
					else if (szsmdm.startsWith(SzsmdmConstant.ZYS)) {
						szdm = szsmdm.substring(0, 3);
					} else {
						szdm = szsmdm.substring(0, 2);
					}
					jmsInfo.setSzdm(szdm);
					jmsInfo.setCjsj(ysbData.getCjrq());
					jmsInfo.setJmse(ysbData.getJmse());
					jmsInfo.setKssl(ysbData.getKssl());
					jmsInfo.setJmlxdm(ysbData.getJmlx());
					jmsInfo.setJsje(ysbData.getJsje());
					// 是否按数量记
					Szsm szsmTmp = (Szsm) szsmMap.get(szsmdm);
					jmsInfo.setAslj(szsmTmp.getAsljbs() != null ? szsmTmp
							.getAsljbs().equals("1") : false);

					// 如果申报数据是审批减免
					if (ysbData.getJmlx().equals(CodeConstant.JMLX_SP)) {
						jmsInfo.setcanShenpi(true);
					} else {
						jmsInfo.setcanShenpi(false);
					}
					jmsTmpList.add(jmsInfo);
				}
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}

		return jmsTmpList;
	}
	
/*	
	
	/**
	 *  获取特定的税款所属日期
	 * @return
	 * @throws BaseException
	 *//*
	private List makeSkssrqList() throws BaseException 
	{
		List skssrqList = new ArrayList();
		try 
		{
			String skssrq = TinyTools.getProperty(CodeConstant.WSSB_JMSB_ACCESSCONTROL_SKSSQ);
			System.out.println("skssrq >>>>>:"+skssrq);
			if(skssrq != null && !skssrq.equals(""))
			{
				String[] skssrqArr = TinyTools.divideString(skssrq, CodeConstant.SEPARATOR);
				
				for(int i=0; i<skssrqArr.length;i++)
				{
					JmsSkssrq tmpSkssrq = new JmsSkssrq();
					tmpSkssrq.setSkssksrq(skssrqArr[i].substring(0, 8));
					tmpSkssrq.setSkssjsrq(skssrqArr[i].substring(skssrqArr[i].length()-8));
					skssrqList.add(tmpSkssrq);
				}
			}
		} 
		catch (Exception ex) 
		{
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return skssrqList;
	}*/
	
	
	/**
	 *  获取特定的税款所属日期
	 * @return
	 * @throws BaseException
	 */
	private List makeSkssrqList() throws BaseException 
	{
		List skssrqList = new ArrayList();
		try 
		{
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			//Map map = com.ttsoft.bjtax.shenbao.util.Skssrq.yearSkssrq(curTime);
			// 取得税款所属日期
			//Map map = Skssrq.getSksssq(jbsj.getJsjdm(), sbsj.getSzsmdm()[i], jbsj.getDjzclxdm(),CodeConstant.SKLXDM_ZCJK, zqlxdm[i], sbrq, null, null,null);
			//DateFormat df = new SimpleDateFormat("yyyyMMdd");
			//String skssksrq = df.format((Timestamp) map.get(Skssrq.SKSSKSRQ));
			//String skssjsrq = df.format((Timestamp) map.get(Skssrq.SKSSJSRQ));
			
			String skssrq = TinyTools.getProperty(CodeConstant.WSSB_JMSB_ACCESSCONTROL_SKSSQ);
			String ny = TinyTools.getProperty(CodeConstant.WSSB_JMSB_ACCESSCONTROL_NY);
			Timestamp now = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat from = new SimpleDateFormat("yyyyMMdd HH24:mm:ss");
			String strDate = from.format(now).substring(0, 6);
			//String strDate = "201406";
			if(skssrq != null && !skssrq.equals(""))
			{
				String[] skssrqArr = TinyTools.divideString(skssrq, CodeConstant.SEPARATOR);
				String[] nyArr = TinyTools.divideString(ny, CodeConstant.SEPARATOR);
				boolean isDate = false;
				for(int i=0; i<nyArr.length;i++)
				{
					if(strDate.equals(nyArr[i]))
					{
						isDate = true;
						break;
					}
				}
				if(isDate)
				{
					for(int j=0; j<skssrqArr.length;j++)
					{
						JmsSkssrq tmpSkssrq = new JmsSkssrq();
						tmpSkssrq.setSkssksrq(skssrqArr[j].substring(0, 8));
						tmpSkssrq.setSkssjsrq(skssrqArr[j].substring(skssrqArr[j].length()-8));
						skssrqList.add(tmpSkssrq);
					}
				}
			}
		} 
		catch (Exception ex) 
		{
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return skssrqList;
	}
	
	
	
	/**
	 * 获取特定的税款年月
	 * @return
	 * @throws BaseException
	 */
	private List makeSknyList() throws BaseException 
	{
		List sknyList = new ArrayList();
		try 
		{
			String ny = TinyTools.getProperty(CodeConstant.WSSB_JMSB_ACCESSCONTROL_NY);
			if(ny != null && !ny.equals(""))
			{
				String[] sknyArr = TinyTools.divideString(ny, CodeConstant.SEPARATOR);
				
				for(int i=0; i<sknyArr.length;i++)
				{
					JmsSkny tmpSkny = new JmsSkny();
					tmpSkny.setSkny(sknyArr[i]);
					sknyList.add(tmpSkny);
				}
			}
		} 
		catch (Exception ex) 
		{
			throw ExceptionUtil.getBaseException(ex);
		}
		
		return sknyList;
	}

	/**
	 * 更新form中的值对象
	 * 
	 * @param jmsForm
	 *            减免税form
	 * @return boolean 是否存在不能审批数据
	 * @throws BaseException
	 */
	private boolean reflashJmsList(JmsVO jms, String[] zqlxdm, Map szsmMap,
			Map jmflMap, SWDJJBSJ jbsj, Map jmxm) throws BaseException {
		// 是否存在不能审批的数据
		boolean canSubmit = true;

		// 声明税费接口
		com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfgl = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
		
		List skssrqList = makeSkssrqList();
		// 生成新list
		List jmsList = new ArrayList();
		SbsjVO sbsj = jms.getSbsjlist().getSbsj();
		String[] szsmdm = sbsj.getSzsmdm();
		String[] cjsj = sbsj.getCjsj();
		String[] kssl = sbsj.getKssl();
		String[] jsje = sbsj.getJsje();
		String[] jmse = sbsj.getJmse();
		String[] szdm = sbsj.getSzdm();
		String[] jmlx = sbsj.getJmfldm();
		String[] jmxmjdm = sbsj.getJmxmdm();
		String[] jmxmksrq = sbsj.getJmxmksrq();
		String[] jmxmjsrq = sbsj.getJmxmjsrq();
		String[] skssksrq = sbsj.getSkssksrq();
		String[] skssjsrq = sbsj.getSkssjsrq();
		String[] jmxmdm = new String[szsmdm.length];

		try {
			if (szsmdm != null) {
				int itemSize = szsmdm.length;
				for (int i = 0; i < itemSize; i++) {
					// 生成前台值对象
					JmsInfo jmsInfo = new JmsInfo();
					jmsInfo.setJmxmjdm(jmxmjdm[i]);
					jmsInfo.setJmxmksrq(jmxmksrq[i]);
					jmsInfo.setJmxmjsrq(jmxmjsrq[i]);
					if(skssrqList != null && skssrqList.size() > 0)
					{
						jmsInfo.setSkssksrq(skssksrq[i]);
						jmsInfo.setSkssjsrq(skssjsrq[i]);
					}
					
					jmsInfo.setSzsmdm(szsmdm[i]);
					jmsInfo.setSzdm(szdm[i]);

					// 判断重复申报
					if (itemSize > 1) {
						for (int j = i + 1; j < itemSize; j++) {
							if (szsmdm[i].equals(szsmdm[j])
									&& jmlx[i].equals(jmlx[j])
										&& jmxmjdm[i].equals(jmxmjdm[j])) {
								canSubmit = false;
							}
						}
					}
					// 如果没有创建时间则设为当前时间
					if (cjsj[i] == null || cjsj[i].equals("")) {
						Timestamp sbrq = new Timestamp(new Date().getTime());
						jmsInfo.setCjsj(sbrq);
					} else {
						jmsInfo.setCjsj(new Timestamp(Long.parseLong(cjsj[i])));
					}

					// 是否按数量记
					Szsm szsmTmp = (Szsm) szsmMap.get(szsmdm[i]);
					jmsInfo.setAslj(szsmTmp.getAsljbs() != null ? szsmTmp
							.getAsljbs().equals("1") : false);

					// 如果按数量记
					if (jmsInfo.isAslj()) {
						jmsInfo.setKssl(new BigDecimal(kssl[i]));
					}

					jmsInfo.setJsje(new BigDecimal(jsje[i]));
					jmsInfo.setJmse(new BigDecimal(jmse[i]));
					jmsInfo.setJmlxdm(jmlx[i]);
					jmsInfo
							.setJmlxmc(((Jmfl) jmflMap.get(jmlx[i]))
									.getJmflmc());
					// 如果是审批减免，调用税费接口判断是否有审批资格，
					// 如果没有则不允许保存
					if (jmlx[i].equals(CodeConstant.JMLX_SP)) 
					{
						// 营业税和附加税不判断减免资格
						if (szsmdm[i].startsWith(SzsmdmConstant.YYS)
								|| (szsmTmp.getSffjs() != null && szsmTmp
										.getSffjs().equals("2"))) 
						{
							jmsInfo.setcanShenpi(true);
							jmxmdm[i] = JmsActionConstant.STRING_ZCXJM;
						} 
						else 
						{
							Date sbrq = DateTimeUtil.stringToTimestamp(jms
									.getSbxx().getSbrq(), "yyyy-MM-dd");
							Map skssrqMap = Skssrq.getSksssq(jbsj.getJsjdm(), szsmdm[i], jbsj.getDjzclxdm(),
									CodeConstant.SKLXDM_ZCJK, zqlxdm[i], sbrq,
									null, null, null);
							Timestamp skssjsrqls = (Timestamp) skssrqMap.get(Skssrq.SKSSJSRQ);
							Timestamp skssksrqls = (Timestamp) skssrqMap.get(Skssrq.SKSSKSRQ);
							String jmxmdmTmp = sfgl.getJmsbs(jbsj.getJsjdm(),
									szsmdm[i].substring(0, 2), skssjsrqls, skssksrqls);
							jmxmdm[i] = jmxmdmTmp;
							if (jmxmdmTmp == null || jmxmdmTmp.equals("")) {
								canSubmit = false;
								jmsInfo.setcanShenpi(false);
							} else {
								jmsInfo.setcanShenpi(true);
							}
						}
					}
					// 政策性减免的减免类型代码为99
					else 
					{
						jmxmdm[i] = JmsActionConstant.STRING_ZCXJM;
					}
					jmsList.add(jmsInfo);
				}
				// 设置减免类型代码
				jmxm.put("jmxmdm", jmxmdm);
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		jmxm.put(SessionKey.SESSION_JMSB_LIST, jmsList);
		return canSubmit;
	}

	/**
	 * 清空session
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	private void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionKey.SESSION_JMSM_LIST);
		session.removeAttribute(SessionKey.SESSION_JMSZ_LIST);
		session.removeAttribute(SessionKey.SESSION_BQYSB_LIST);
		session.removeAttribute(SessionKey.SESSION_FJS_LIST);
		session.removeAttribute(SessionKey.SESSION_JMFL_LIST);
		session.removeAttribute(SessionKey.SESSION_JMSB_LIST);
	}

	/**
	 * 生成减免项目List
	 */
	private Map makeJmxmList(List jmxmList) 
	{
		// 通过过滤，生成税种、税目List
		List jmxmNewList = new ArrayList();
		Map szsmListMap = new HashMap();
		for (int i = 0; i < jmxmList.size(); i++) 
		{
			Zjqyjmslx jmxm = (Zjqyjmslx) jmxmList.get(i);
			String jmslxdm = jmxm.getJmslxdm();
			String wsh = jmxm.getJmslxmc();
			String szdm = jmxm.getSzdm();

			JmsxmInfor newJmxm = new JmsxmInfor();
			newJmxm.setSzdm(szdm);
			newJmxm.setJmslxdm(jmslxdm);
			newJmxm.setWh(wsh);
			jmxmNewList.add(newJmxm);	
		}
		szsmListMap.put(SessionKey.SESSION_JMXM_LIST, jmxmNewList);
		return szsmListMap;
	}
	
	
	
	/**
	 * 生成税种税目List
	 */
	private Map makeSzsmList(List szsmList) {
		// 通过过滤，生成税种、税目List
		List szList = new ArrayList();
		List smList = new ArrayList();
		Map szsmListMap = new HashMap();
		for (int i = 0; i < szsmList.size(); i++) {
			// 取得税种税目值对象
			Szsm szsm = (Szsm) szsmList.get(i);
			// 税种税目代码
			String szsmdm = szsm.getSzsmdm();
			// 税种税目名称
			String szsmmc = szsm.getSzsmmc();
			// 是否按数量记
			boolean aslj = (szsm.getAsljbs() != null ? szsm.getAsljbs().equals(
					"1") : false);

			// 企业所得税过滤掉 ---减免税项目 过滤掉契税、耕地占用税  tujb 201404
			if (szsmdm.startsWith(SzsmdmConstant.QS) 
					|| szsmdm.startsWith(SzsmdmConstant.GDZYS) || szsmdm.startsWith(SzsmdmConstant.GDTZFXTJS) 
					|| szsmdm.startsWith(SzsmdmConstant.CSJTFWSYF)) 
			{
				continue;
			}

			// 税种税目代码长度小于6就是税种，且过滤罚款、滞纳金
			if (szsmdm.length() < 6
					&& (szsm.getSffjs() == null || !szsm.getSffjs().equals("3"))) {
				JmsSzsm newSzsm = new JmsSzsm();
				int len = szsmdm.length();
				// 营业税
				if (szsmdm.startsWith(SzsmdmConstant.YYS)) {
					if (len == 2) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 3) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("　" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 4) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("　　" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(true);
					}
					szList.add(newSzsm);
				}
				// 个人所得税
				else if (szsmdm.startsWith(SzsmdmConstant.GRSDS)) {
					if (len == 2) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 4) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("　" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(true);
					}
					szList.add(newSzsm);
				}
				// 资源税
				else if (szsmdm.startsWith(SzsmdmConstant.ZYS)) {
					if (len == 2) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 3) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("　" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(true);
					}
					szList.add(newSzsm);
				}
				// 其他税种
				else {
					newSzsm.setSzsmdm(szsmdm);
					newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
					newSzsm.setBottom(true);
					szList.add(newSzsm);
				}
			}
			// 长度为6都是税目
			else {
				// 过滤附加税
				if (szsm.getSffjs() == null || szsm.getSffjs().equals("1")
						|| szsm.getSffjs().equals("2")) {
					JmsSzsm newSzsm = new JmsSzsm();
					newSzsm.setSzsmdm(szsmdm);
					newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
					newSzsm.setBottom(true);
					newSzsm.setAslj(aslj);
					smList.add(newSzsm);
				}
			}
		}
		szsmListMap.put(SessionKey.SESSION_JMSZ_LIST, szList);
		szsmListMap.put(SessionKey.SESSION_JMSM_LIST, smList);
		return szsmListMap;
	}
	
	
	
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限
		if (!SbzlAccess.getAuthority(SbzlAccess.JM, request)) {
			request
					.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
							"您没有权限使用这个功能");
			return CAConstants.FAILURE;
		}

		return null;
	}
}
