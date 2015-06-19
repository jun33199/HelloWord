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
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: ����˰�걨Action
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
	 * �Ǽǳ���
	 */
	private static final String DJ_JBSJ = "JBSJ";

	protected int getActionID() {
		return SbzlAccess.JM;
	}

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// ��codetableȡ��˰��˰ĿList Map
		Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP_AVAILABLE);
		List jmflList = CodeTableUtil.getCodeTableList(request, CodeTable.JMFL_LIST);
		Map jmflMap = CodeTableUtil.getCodeTableMap(request, CodeTable.JMFL_MAP);
		//��codetableȡ�ü�����Ŀ������List Map ����˰��Ŀ  tujb 200404
		//List jmxmList = CodeTableUtil.getCodeTableList(request, CodeTable.JMXM_BASX_LIST);
		Map jmxmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.JMXM_BASX_MAP);
		
		// ȡ��userdata
		UserData userdata = (UserData) this.getUserData(request);
		// ����VOPackage
		VOPackage voPackage = new VOPackage();
		// �趨vopackage����
		voPackage.setProcessor(ProcessorNames.JMS_PROCESSOR);
		voPackage.setAction(JmsActionConstant.INT_ACTION_QUERY);
		voPackage.setUserData(userdata);

		try {

			// ȡ�õǼ�Map
			Map djMap = FriendHelper.getDjInfo(request);
			// �Ǽǻ�������
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
			// �걨����-��ǰ����
			Timestamp sbrq = new Timestamp(new Date().getTime());
			
			Map queryMap = new HashMap();
			queryMap.put(JmsMapConstant.SBRQ, sbrq);
			queryMap.put(JmsMapConstant.QXDM, jbsj.getSwjgzzjgdm().substring(0,
					2));
			queryMap.put(JmsMapConstant.DJZCLXDM, jbsj.getDjzclxdm());
			voPackage.setData(queryMap);

			// ���ú�̨������ȡ�÷���ֵ
			voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
					voPackage);
			Map voMap = (Map) voPackage.getData();
			List szsmList = (List) voMap.get(JmsMapConstant.LIST_SZSM);
			List jmxmList = (List) voMap.get(JmsMapConstant.LIST_JMXM);

			Map szsmListMap = makeSzsmList(szsmList);
			Map jmxmListMap = makeJmxmList(jmxmList); // ���ɼ�����Ŀ������Map ����˰��Ŀ  tujb 200404
			List szList = (List) szsmListMap.get(SessionKey.SESSION_JMSZ_LIST);
			List smList = (List) szsmListMap.get(SessionKey.SESSION_JMSM_LIST);
			List jmxmNewList = (List) jmxmListMap.get(SessionKey.SESSION_JMXM_LIST); // ���ɼ�����Ŀ������List ����˰��Ŀ  tujb 200404

			// ȡ�ñ������걨����
			List bqysb = (List) voMap.get(JmsMapConstant.LIST_JMYSB);
			List jmsList = makeJmsList(szsmMap, jmflMap, bqysb, jmxmMap);
			List skssrqList = makeSkssrqList(); // �����ض�˰����������Map ����˰��Ŀ  tujb 200404
			List sknyList = makeSknyList(); // �����ض�����Map ����˰��Ŀ  tujb 200404

			// ȡ�ø���˰List
			List fjsList = null;
			String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
			Djzclx djzclx = (Djzclx) CodeTableUtil.getCodeTableMap(request,
					CodeTable.DJZCLX_MAP).get(djzclxdm);
			if (djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GAT)
					|| djzclx.getNwzfldm().equals(
							CodeConstant.DJZCLXDM_NWZFLDM_WZ)) {
				// ȡ������ҵ����˰
				fjsList = CodeTableUtil.getCodeTableList(request,
						CodeTable.SZSMFJS_WQ_LIST);
			} else {
				// ȡ������ҵ����˰
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
			// ��˰�֡�˰Ŀ���������걨List����session
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
			// ת����ʾҳ��
			return CAConstants.SHOW;
		} catch (Exception e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"����ʧ�ܣ���ʾ���ݳ���");
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
		// ������˰����Ϣ
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(TinyTools.replaceBlank(jbsj.getNsrmc()));
		nsrxx.setQylxdh(TinyTools.replaceBlank(jbsj.getJydzlxdm()));
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		
		DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ
		if (bqysb != null && bqysb.size() != 0) 
		{
			for (int i = 0; i < bqysb.size(); i++) 
			{
				Jm ysbData = (Jm) bqysb.get(0);

				// ��ǰֵ̨����ֵ
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
		
		// �걨��Ϣ
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
		// �걨����
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
					alertStr = alertStr + (i + 1) + "��";
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
				jmflmc[i] = "��������";
				szdm[i] = "02";
				szmc[i] = "02 Ӫҵ˰";
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
		// ������˰����Ϣ
		
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(TinyTools.replaceBlank(jbsj.getNsrmc()));
		nsrxx.setQylxdh(TinyTools.replaceBlank(jbsj.getJydzlxdm()));
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		nsrxx.setDqxse(jmsvo.getNsrxx().getDqxse());
		nsrxx.setDqlrze(jmsvo.getNsrxx().getDqlrze());
		nsrxx.setQyrs(jmsvo.getNsrxx().getQyrs());
		nsrxx.setAzrs(jmsvo.getNsrxx().getAzrs());
		
		// �걨��Ϣ
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
		// �걨����
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
					alertStr = alertStr + (i + 1) + "��";
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
				jmflmc[i] = "��������";
				szdm[i] = "02";
				szmc[i] = "02 Ӫҵ˰";
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
		// ȡ�õǼ�Map
		Map djMap = FriendHelper.getDjInfo(request);
		// �Ǽǻ�������
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
		DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();

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
		JmsVO jms = new JmsVO();
		try {
			//XMLParseHelper.parseXMLString(jms, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//jms = new JmsVO();
			XMLParseHelper.parseXMLString(jms, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"����ʧ�ܣ�����xml���ݳ���");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(jms.getYwczlx());
        dzyj.setYwdm(jms.getYwlx());
        
		Timestamp now = new Timestamp(System.currentTimeMillis());
		try {
			// ��session��ȡ��˰�֡�˰Ŀ���������걨������˰List
			HttpSession session = request.getSession();
			List smList = (List) session.getAttribute(SessionKey.SESSION_JMSM_LIST);
			List szList = (List) session.getAttribute(SessionKey.SESSION_JMSZ_LIST);
			List bqysbList = (List) session.getAttribute(SessionKey.SESSION_BQYSB_LIST);
			List fjsList = (List) session.getAttribute(SessionKey.SESSION_FJS_LIST);
			List jmxmList = CodeTableUtil.getCodeTableList(request, CodeTable.JMXM_BASX_LIST);
			Map jmxmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.JMXM_BASX_MAP);
			
			// ȡ��˰��˰ĿMap
			Map szsmMap = CodeTableUtil.getCodeTableMap(request,
					CodeTable.SZSM_MAP);
			// ȡ�ü�������List
			Map jmflMap = CodeTableUtil.getCodeTableMap(request,
					CodeTable.JMFL_MAP);
			List jmflList = CodeTableUtil.getCodeTableList(request,
					CodeTable.JMFL_LIST);
			
			Map jmxmListMap = makeJmxmList(jmxmList);
			List jmxmNewList = (List) jmxmListMap.get(SessionKey.SESSION_JMXM_LIST);

			// ȡ���������ʹ���
			String[] szsmdm = jms.getSbsjlist().getSbsj().getSzsmdm();
			String[] zqlxdm = new String[szsmdm.length];
			if (szsmdm != null) {
				for (int i = 0; i < szsmdm.length; i++) {
					Szsm szsm = (Szsm) szsmMap.get(szsmdm[i]);
					zqlxdm[i] = szsm.getZqlxdm();
				}
			}

			// �������У��ɹ����ύ���ݸ���̨
			Map jmxm = new HashMap();
			boolean canSubmit = reflashJmsList(jms, zqlxdm, szsmMap, jmflMap,
					jbsj, jmxm);
			if (canSubmit) {
				// ����vopackage�����ò���
				VOPackage vo = new VOPackage();
				vo.setAction(JmsActionConstant.INT_ACTION_SAVE);
				vo.setProcessor(ProcessorNames.JMS_PROCESSOR);
				vo.setUserData(userData);
				Map data = getSbData(jms, jbsj, zqlxdm, szsmMap, bqysbList,
						jmxm);
				data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
				data.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jms);
				vo.setData(data);
				// ���ú�̨��������
				Map retMap = (Map) ShenbaoProxy.getInstance().process(vo);

				// ����ǩ������
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
						"����˰�걨��"+CAUtils.getTsxx(jms.getYwczlx()));
				request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
						"/shenbao/jms.dc?actionType=Show");
				request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "����˰�걨��"+CAUtils.getTsxx(jms.getYwczlx()));
				LogUtil.getInstance().log(FriendHelper.getUserData(request),
						"����˰�걨��",
						(new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
				// ���session
				clearSession(request);
				// ת��ɹ�ҳ��
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
				// ������ʾҳ��
				return CAConstants.SHOW;
			}
		} catch (Exception ex) {
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"����˰�걨��", (new SimpleDateFormat("yyyyMMdd")).format(now),
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
			// �쳣����
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
				// ���������
				vo.setJsjdm(jbsj.getJsjdm());
				// ��ʽ����
				vo.setFsdm(CodeConstant.FSDM_WSSB);
				// ��������
				// vo.setJmlx(this.jmlx[i]);
				vo.setJmlx(sbsj.getJmfldm()[i]);
				// ����˰��
				vo.setJmse(new BigDecimal(sbsj.getJmse()[i]));
				// ��˰���
				vo.setJsje(new BigDecimal(sbsj.getJsje()[i]));
				// ��˰����
				Szsm szsmTmp = (Szsm) szsmMap.get(sbsj.getSzsmdm()[i]); // ȡ��Szsmֵ����
				if (szsmTmp.getAsljbs() != null ? szsmTmp.getAsljbs().equals(
						"1") : false) // �Ƿ�������
				{
					vo.setKssl(new BigDecimal(sbsj.getKssl()[i]));
				}
				// �������ʹ���
				vo.setJmxmdm(jmxmdm[i]);
				// �Ǽ�ע�����ʹ���
				vo.setDjzclxdm(jbsj.getDjzclxdm());
				// ���ұ�׼��ҵ����
				vo.setGjbzhydm(jbsj.getGjbzhydm());
				// ¼����
				vo.setLrr(jbsj.getJsjdm());
				// ¼������
				Date sbrq2 = null;
				try {
					sbrq2 = DateTimeUtil.stringToTimestamp(jms.getSbxx().getSbrq(), "yyyy-MM-dd HH:mm:ss");
				} catch (Exception e) {
					sbrq2 = DateTimeUtil.stringToTimestamp(jms.getSbxx().getSbrq(), "yyyy-MM-dd");
					e.printStackTrace();
				}
				
				Timestamp sbrq = new Timestamp(sbrq2.getTime() + i*1000);
				vo.setLrrq(sbrq);
				// ��˰������
				// vo.setNsrmc(nsrzl.getNsrmc());
				// �걨����
				vo.setSbrq(TinyTools.second2Day(sbrq));
				// ˰�������֯�ṹ����
				vo.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
				// ˰��˰Ŀ����
				vo.setSzsmdm(sbsj.getSzsmdm()[i]);
				// ����ʱ��
				if (sbsj.getCjsj()[i] == null || sbsj.getCjsj()[i].equals("")) {
					vo.setCjrq(sbrq);
				} else {
					vo.setCjrq(new Timestamp(Long.parseLong(sbsj.getCjsj()[i])+ i*1000));
				}
				// ���
				vo.setNd(TinyTools.Date2String(sbrq).substring(0, 4));
				// ���ش���
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

				// ȡ��˰����������
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
			// �ѱ����걨���ݺͱ������걨���ݷ���
			data.put(JmsMapConstant.LIST_JMSB, sbList);
			data.put(JmsMapConstant.LIST_JMYSB, jmysbList);
			data.put(JmsMapConstant.ZQLXDM, zqlxdm);

			return data;
		} catch (Exception e) {
			throw new ApplicationException("���ύ�˴�������ݣ�����������걨��");
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
		// ȡ�õǼ�Map
		Map djMap = FriendHelper.getDjInfo(request);
		// �Ǽǻ�������
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
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
		JmsVO jms = new JmsVO();
		try {
			//XMLParseHelper.parseXMLString(jms, receiveXml,XMLParseHelper.XERCES_PARSER, true,true);
			//jms = new JmsVO();
			XMLParseHelper.parseXMLString(jms, receiveXml,
					XMLParseHelper.VTDXML_PARSER, false,true);
		} catch (ApplicationException e) {
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"����ʧ�ܣ�����xml���ݳ���");
			throw ExceptionUtil.getBaseException(e);
		}
        dzyj.setYwczlx(jms.getYwczlx());
        dzyj.setYwdm(jms.getYwlx());
        
		// ��session��ȡ��˰�֡�˰Ŀ���������걨List
		HttpSession session = request.getSession();
		List smList = (List) session.getAttribute(SessionKey.SESSION_JMSM_LIST);
		List szList = (List) session.getAttribute(SessionKey.SESSION_JMSZ_LIST);
		List bqysbList = (List) session
				.getAttribute(SessionKey.SESSION_BQYSB_LIST);
		List fjsList = (List) session.getAttribute(SessionKey.SESSION_FJS_LIST);

		Timestamp sbrq = new Timestamp(new Date().getTime());

		// ���������걨������Ϊ��������Map
		Map dataMap = new HashMap();
		dataMap.put(JmsMapConstant.LIST_JMYSB, bqysbList);
		dataMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		dataMap.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jms);
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		try {
			// ����vopackage�����ò���
			VOPackage vo = new VOPackage();
			vo.setAction(JmsActionConstant.INT_ACTION_DELETE);
			vo.setProcessor(ProcessorNames.JMS_PROCESSOR);
			vo.setUserData(userData);
			vo.setData(dataMap);
			// ���ú�̨��������
			ShenbaoProxy.getInstance().process(vo);
			request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
			request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG,
					"����˰�걨��"+CAUtils.getTsxx(jms.getYwczlx()));
			request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
					"/shenbao/jms.dc?actionType=Show");

			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"����˰�걨��",
					(new SimpleDateFormat("yyyyMMdd")).format(curTime), "�ɹ�!");
			// ���session
			clearSession(request);

			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"����˰�걨��"+CAUtils.getTsxx(jms.getYwczlx()));
			// ת��ɹ�ҳ��
			return CAUtils.jumpTo(userData.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);
		} catch (Exception ex) {
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"����˰�걨��",
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
			//request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
				//	"ɾ������˰�걨����ʧ�ܣ�");
			return CAConstants.FAILURE;
			// throw ExceptionUtil.getBaseException(ex);

		}
	}

	public String doReturn(HttpServletRequest request,
			HttpServletResponse response) {
		// ���session
		clearSession(request);
		return CAConstants.RETURN;
	}

	private List makeJmsList(Map szsmMap, Map jmflMap, List bqysb, Map jmxmMap)
			throws BaseException {
		List jmsTmpList = new ArrayList();
		try {
			// ���뱾�����걨����
			if (bqysb != null) {
				for (int i = 0; i < bqysb.size(); i++) {
					JmsInfo jmsInfo = new JmsInfo();
					
					Jm ysbData = (Jm) bqysb.get(i);

					// ��ǰֵ̨����ֵ
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

					// ��һ��˰�ִ���
					String szdm;
					// Ӫҵ˰ & ��������˰
					if (szsmdm.startsWith(SzsmdmConstant.YYS)
							|| szsmdm.startsWith(SzsmdmConstant.GRSDS)) {
						szdm = szsmdm.substring(0, 4);
					}
					// ��Դ˰
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
					// �Ƿ�������
					Szsm szsmTmp = (Szsm) szsmMap.get(szsmdm);
					jmsInfo.setAslj(szsmTmp.getAsljbs() != null ? szsmTmp
							.getAsljbs().equals("1") : false);

					// ����걨��������������
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
	 *  ��ȡ�ض���˰����������
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
	 *  ��ȡ�ض���˰����������
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
			// ȡ��˰����������
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
	 * ��ȡ�ض���˰������
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
	 * ����form�е�ֵ����
	 * 
	 * @param jmsForm
	 *            ����˰form
	 * @return boolean �Ƿ���ڲ�����������
	 * @throws BaseException
	 */
	private boolean reflashJmsList(JmsVO jms, String[] zqlxdm, Map szsmMap,
			Map jmflMap, SWDJJBSJ jbsj, Map jmxm) throws BaseException {
		// �Ƿ���ڲ�������������
		boolean canSubmit = true;

		// ����˰�ѽӿ�
		com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfgl = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
		
		List skssrqList = makeSkssrqList();
		// ������list
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
					// ����ǰֵ̨����
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

					// �ж��ظ��걨
					if (itemSize > 1) {
						for (int j = i + 1; j < itemSize; j++) {
							if (szsmdm[i].equals(szsmdm[j])
									&& jmlx[i].equals(jmlx[j])
										&& jmxmjdm[i].equals(jmxmjdm[j])) {
								canSubmit = false;
							}
						}
					}
					// ���û�д���ʱ������Ϊ��ǰʱ��
					if (cjsj[i] == null || cjsj[i].equals("")) {
						Timestamp sbrq = new Timestamp(new Date().getTime());
						jmsInfo.setCjsj(sbrq);
					} else {
						jmsInfo.setCjsj(new Timestamp(Long.parseLong(cjsj[i])));
					}

					// �Ƿ�������
					Szsm szsmTmp = (Szsm) szsmMap.get(szsmdm[i]);
					jmsInfo.setAslj(szsmTmp.getAsljbs() != null ? szsmTmp
							.getAsljbs().equals("1") : false);

					// �����������
					if (jmsInfo.isAslj()) {
						jmsInfo.setKssl(new BigDecimal(kssl[i]));
					}

					jmsInfo.setJsje(new BigDecimal(jsje[i]));
					jmsInfo.setJmse(new BigDecimal(jmse[i]));
					jmsInfo.setJmlxdm(jmlx[i]);
					jmsInfo
							.setJmlxmc(((Jmfl) jmflMap.get(jmlx[i]))
									.getJmflmc());
					// ������������⣬����˰�ѽӿ��ж��Ƿ��������ʸ�
					// ���û����������
					if (jmlx[i].equals(CodeConstant.JMLX_SP)) 
					{
						// Ӫҵ˰�͸���˰���жϼ����ʸ�
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
					// �����Լ���ļ������ʹ���Ϊ99
					else 
					{
						jmxmdm[i] = JmsActionConstant.STRING_ZCXJM;
					}
					jmsList.add(jmsInfo);
				}
				// ���ü������ʹ���
				jmxm.put("jmxmdm", jmxmdm);
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		jmxm.put(SessionKey.SESSION_JMSB_LIST, jmsList);
		return canSubmit;
	}

	/**
	 * ���session
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
	 * ���ɼ�����ĿList
	 */
	private Map makeJmxmList(List jmxmList) 
	{
		// ͨ�����ˣ�����˰�֡�˰ĿList
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
	 * ����˰��˰ĿList
	 */
	private Map makeSzsmList(List szsmList) {
		// ͨ�����ˣ�����˰�֡�˰ĿList
		List szList = new ArrayList();
		List smList = new ArrayList();
		Map szsmListMap = new HashMap();
		for (int i = 0; i < szsmList.size(); i++) {
			// ȡ��˰��˰Ŀֵ����
			Szsm szsm = (Szsm) szsmList.get(i);
			// ˰��˰Ŀ����
			String szsmdm = szsm.getSzsmdm();
			// ˰��˰Ŀ����
			String szsmmc = szsm.getSzsmmc();
			// �Ƿ�������
			boolean aslj = (szsm.getAsljbs() != null ? szsm.getAsljbs().equals(
					"1") : false);

			// ��ҵ����˰���˵� ---����˰��Ŀ ���˵���˰������ռ��˰  tujb 201404
			if (szsmdm.startsWith(SzsmdmConstant.QS) 
					|| szsmdm.startsWith(SzsmdmConstant.GDZYS) || szsmdm.startsWith(SzsmdmConstant.GDTZFXTJS) 
					|| szsmdm.startsWith(SzsmdmConstant.CSJTFWSYF)) 
			{
				continue;
			}

			// ˰��˰Ŀ���볤��С��6����˰�֣��ҹ��˷�����ɽ�
			if (szsmdm.length() < 6
					&& (szsm.getSffjs() == null || !szsm.getSffjs().equals("3"))) {
				JmsSzsm newSzsm = new JmsSzsm();
				int len = szsmdm.length();
				// Ӫҵ˰
				if (szsmdm.startsWith(SzsmdmConstant.YYS)) {
					if (len == 2) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 3) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("��" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 4) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("����" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(true);
					}
					szList.add(newSzsm);
				}
				// ��������˰
				else if (szsmdm.startsWith(SzsmdmConstant.GRSDS)) {
					if (len == 2) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 4) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("��" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(true);
					}
					szList.add(newSzsm);
				}
				// ��Դ˰
				else if (szsmdm.startsWith(SzsmdmConstant.ZYS)) {
					if (len == 2) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
						newSzsm.setBottom(false);
					} else if (len == 3) {
						newSzsm.setSzsmdm(szsmdm);
						newSzsm.setSzsmmc("��" + szsmdm + " " + szsmmc);
						newSzsm.setBottom(true);
					}
					szList.add(newSzsm);
				}
				// ����˰��
				else {
					newSzsm.setSzsmdm(szsmdm);
					newSzsm.setSzsmmc(szsmdm + " " + szsmmc);
					newSzsm.setBottom(true);
					szList.add(newSzsm);
				}
			}
			// ����Ϊ6����˰Ŀ
			else {
				// ���˸���˰
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
		// ���Ȩ��
		if (!SbzlAccess.getAuthority(SbzlAccess.JM, request)) {
			request
					.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
							"��û��Ȩ��ʹ���������");
			return CAConstants.FAILURE;
		}

		return null;
	}
}
