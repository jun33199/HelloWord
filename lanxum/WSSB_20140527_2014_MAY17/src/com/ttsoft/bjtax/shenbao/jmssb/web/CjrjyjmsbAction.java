package com.ttsoft.bjtax.shenbao.jmssb.web;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.ActionErrors;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.jmssb.bo.CjrjyjmsbBo;
import com.ttsoft.bjtax.shenbao.jmssb.xmlvo.CjrjyjmsbVo;
import com.ttsoft.bjtax.shenbao.jmssb.xmlvo.CjrjyjmssbsjVO;
import com.ttsoft.bjtax.shenbao.jmssb.xmlvo.NsrxxVO_Cjrjy;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class CjrjyjmsbAction extends DcBaseAction {

	public CjrjyjmsbAction() {

	}

	protected int getActionID() {
		return com.ttsoft.bjtax.shenbao.util.SbzlAccess.CJRJYJMSB;
	}

	private UserData userData = null;
	private static final String DJ_JBSJ = "JBSJ";

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {		
		System.out.println("===============into CjrjyjmsAction=============<br>");
		Map djMap = FriendHelper.getDjInfo(request);
		CjrjyjmsbBo cjrjyjmsbbo = new CjrjyjmsbBo();
		CjrjyjmsbVo cjrjyjmsbvo = new CjrjyjmsbVo();
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ
		vo.setAction(CodeConstant.SMSB_SHOWACTION);
		vo.setUserData(userData);
		vo.setProcessor(ProcessorNames.JMSSB_CJRJYJMSB_PROCESSOR);
		try {
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);

			String jsjdm = jbsj.getJsjdm();

			userData = (UserData) this.getUserData(request);
			String lrrq = new SimpleDateFormat("yyyyMMdd").format(new Date());
			String skssrq = new SimpleDateFormat("yyyyMM").format(new Date());
			cjrjyjmsbbo.setJsjdm(jsjdm);			
	
			cjrjyjmsbbo.setLrrq(lrrq);
			cjrjyjmsbbo.setSkssrq(skssrq);
			
			// ���ú�̨������ȡ�÷���ֵ
			List dwxzList = (List) ShenbaoProxy.getInstance().process(
					vo);
			cjrjyjmsbvo = convertToXmlVO(cjrjyjmsbbo, jbsj);
			cjrjyjmsbvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
			String tmpxml = cjrjyjmsbvo.toXML();
			System.out.println("show xml:" + tmpxml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					cjrjyjmsbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					cjrjyjmsbvo.getSchemaVersion());
			
			//System.out.println("=========list"+dwxzList);
			request.setAttribute("dwxzList", dwxzList);
		
			return CAConstants.SHOW;
		} catch (Exception ex) {
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, cjrjyjmsbvo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					cjrjyjmsbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					cjrjyjmsbvo.getSchemaVersion());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public String doQuery(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		Map djMap = FriendHelper.getDjInfo(request);
		CjrjyjmsbBo cjrjyjmsbbo = null;
		CjrjyjmsbVo cjrjyjmsbvo = new CjrjyjmsbVo();
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
		
		
		userData = (UserData) this.getUserData(request);
		// ����Ĭ��չ����Ҫ����������
		// ��ʼ�����ݴ�������
		VOPackage vo = new VOPackage();
		// ���ú�̨����Actionֵ
		vo.setAction(CodeConstant.SMSB_QUERYACTION);
		// �����������Data����,�������������ActionForm
		vo.setUserData(userData);
		Map map = new HashMap();
		map.put("JSJDM", cjrjyjmsbvo.getNsrxx().getJsjdm());
		vo.setData(map);
		// ����ProxyҪ���õ�processor������
		vo.setProcessor(ProcessorNames.JMSSB_CJRJYJMSB_PROCESSOR);
		try {
			// ����Proxy����ʼ��ActionForm��ֵ
			cjrjyjmsbbo = (CjrjyjmsbBo) ShenbaoProxy.getInstance().process(vo);
			List dwxzList = cjrjyjmsbbo.getDwxzList();			
			
			cjrjyjmsbvo = convertToXmlVO1(cjrjyjmsbbo, jbsj);
			cjrjyjmsbvo.setYwczlx(CAcodeConstants.YWCZLX_NEW);
			
			// ��ת
			String tmpxml = cjrjyjmsbvo.toXML();
			System.out.println("show xml:" + tmpxml);

			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					cjrjyjmsbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					cjrjyjmsbvo.getSchemaVersion());
			request.setAttribute("dwxzList", dwxzList);
			
			return CAConstants.QUERY;
		} catch (Exception ex) {
			// ϵͳ��׽�쳣�������쳣�����׳�
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, cjrjyjmsbvo
					.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
					cjrjyjmsbvo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
					cjrjyjmsbvo.getSchemaVersion());
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}
	}

	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("do save method...");
		// -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ---------------------------------------
		/*if (!isTokenValid(request)) {
			System.out.println("========savetest122===========");
			return CAConstants.INVALIDTOKEN;
		}*/
		
//		java.util.Enumeration a=request.getParameterNames();
//		while(a.hasMoreElements()){
//			System.out.println(a.nextElement().toString());
//		}
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		
		UserData ud = (UserData) this.getUserData(request);
		
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		CjrjyjmsbVo cjrjyjmsbvo = new CjrjyjmsbVo();
		Map djMap = FriendHelper.getDjInfo(request);
		
		// �Ǽǻ�������
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get(DJ_JBSJ);
		Timestamp now = new Timestamp(System.currentTimeMillis());

		// ��ǰ��ActionForm
		try{
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
				//System.out.println(cjrjyjmsbvo.getSbxx().getZgzrs());
				//System.out.println(xmldata);
				
				XMLParseHelper.parseXMLString(cjrjyjmsbvo, xmldata,
						XMLParseHelper.VTDXML_PARSER, false, true);
			} catch (ApplicationException e) {
				throw ExceptionUtil.getBaseException(e);
			}
			CjrjyjmsbBo cjrjyjmsbbo = Czzsconvert2VO(cjrjyjmsbvo);
			
			//dzyj.setYwdm(cjrjyjmsbvo.getYwlx());
			dzyj.setYwdm("020040");
			System.out.println("=====ywdm========"+cjrjyjmsbvo.getYwlx()+"|");
			dzyj.setYwczlx(cjrjyjmsbvo.getYwczlx());
			
			Map sjMap =  new HashMap();
			sjMap.put("cjrjyjmsbbo", cjrjyjmsbbo);
			sjMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, jbsj);
			sjMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);//���ݵ���ԭ��
			VOPackage vo = new VOPackage();
			// ���ú�̨����Actionֵ
			vo.setAction(CodeConstant.SMSB_SAVEACTION);
			vo.setUserData(ud);
			// �����������Data����,�������������ActionForm
			vo.setData(sjMap);
			// ����ProxyҪ���õ�processor������
			vo.setProcessor(ProcessorNames.JMSSB_CJRJYJMSB_PROCESSOR);
			Map retmap = (Map) ShenbaoProxy.getInstance().process(vo);
			List dwxzList = (List) retmap.get("dwxzList");
			if (ud.getCaflag()) {
				dzyj = (DzyjsjVO) retmap
						.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,
						Long.toString(dzyj.getLsh()));
			} else {
				request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,
						"");
			}
			request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
					"/shenbao/cjrjyjmsb.dc?actionType=Show");		  

			request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
					"���òм��˾�ҵ����˰�걨");
			request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
					"���òм��˾�ҵ����˰�걨");
					
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"���òм��˾�ҵ����˰�걨",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");

			request.setAttribute("dwxzList", dwxzList);
			// ���session
			//clearSession(request);
			// ת��ɹ�ҳ��
			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SUCCESS,
					CAConstants.SUCCESSSB);

		}catch(Exception ex){
			// ϵͳ��׽�쳣�������쳣�����׳�
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"���òм��˾�ҵ����˰�걨", (new SimpleDateFormat("yyyyMMdd")).format(now),
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

		}
		
	}

	protected String beforePerform(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	private CjrjyjmsbVo convertToXmlVO(CjrjyjmsbBo cjrjyjmsbbo, SWDJJBSJ jbsj)
			throws ApplicationException {
		CjrjyjmsbVo cjrjyjmsb = new CjrjyjmsbVo();
		NsrxxVO_Cjrjy nsrxx = new NsrxxVO_Cjrjy();
		CjrjyjmssbsjVO sbxx = new CjrjyjmssbsjVO();
		
		//cjrjyjmsbvo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		// ������˰����Ϣ
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(jbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		// �걨��Ϣ
		sbxx.setLrrq(cjrjyjmsbbo.getLrrq());
		sbxx.setSkssrq(cjrjyjmsbbo.getSkssrq());
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);

		nsrxx.setSwdjzh(cjrjyjmsbbo.getSwdjzh()+"");
		nsrxx.setZcdz(cjrjyjmsbbo.getZcdz()+"");
		nsrxx.setJyfw(cjrjyjmsbbo.getJyfw()+"");
		// �걨��Ϣ
		sbxx.setDwxz(cjrjyjmsbbo.getDwxz()+"");
		sbxx.setZgzrs(cjrjyjmsbbo.getZgzrs()+"");
		sbxx.setCjrzgrs(cjrjyjmsbbo.getCjrzgrs()+"");
		sbxx.setCjrybl(cjrjyjmsbbo.getCjrybl()+"");
		sbxx.setYnyyssr(cjrjyjmsbbo.getYnyyssr()+"");
		sbxx.setYjyysse(cjrjyjmsbbo.getYjyysse()+"");
		sbxx.setXsyhzzse(cjrjyjmsbbo.getXsyhzzse()+"");
		sbxx.setByyjzyysxe(cjrjyjmsbbo.getByyjzyysxe()+"");
		sbxx.setSyyjzyysxe(cjrjyjmsbbo.getByyjzyysxe()+"");
		sbxx.setBykjzyysxe(cjrjyjmsbbo.getBykjzyysxe()+"");
		sbxx.setBysjjzyysye(cjrjyjmsbbo.getBysjjzyysye()+"");
		sbxx.setBysjyesse(cjrjyjmsbbo.getBysjyesse()+"");
		sbxx.setBymjzyysxe(cjrjyjmsbbo.getBymjzyysxe()+"");
		sbxx.setQxdm(cjrjyjmsbbo.getQxdm()+"");
		sbxx.setSqspbh(cjrjyjmsbbo.getSqspbh()+"");
		sbxx.setSignFlagVal(cjrjyjmsbbo.getSignFlagVal()+"");//��Щ�ط�û�¸��ں���ӿո��ʲô����������hazz
		sbxx.setIsError(cjrjyjmsbbo.getIsError()+"");
		cjrjyjmsb.setNsrxx(nsrxx);
		cjrjyjmsb.setSbxx(sbxx);
		
		//����ҵ���������ֵ
		//����ʹ�ö���ywlx��ֵCJR4001
		String YWDM_SB_WS_CJRJMS = "20090120";
		String YWDM_SB_WS_CJRJMS_YWLX = "020040";
		
		//System.out.println("00000000"+CAProxy.getInstance().getXSLTSCHEMAVersion(YWDM_SB_WS_CJRJMS));
		//cjrjyjmsb.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(YWDM_SB_WS_CJRJMS));
		cjrjyjmsb.setXsltVersion(YWDM_SB_WS_CJRJMS);
		
		//cjrjyjmsb.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(YWDM_SB_WS_CJRJMS));
		cjrjyjmsb.setSchemaVersion(YWDM_SB_WS_CJRJMS);
		cjrjyjmsb.setYwlx(YWDM_SB_WS_CJRJMS_YWLX);
		return cjrjyjmsb;

	}

	private CjrjyjmsbVo convertToXmlVO1(CjrjyjmsbBo cjrjyjmsbbo, SWDJJBSJ jbsj)
			throws ApplicationException {
		//System.out.println("===========test========convertToXmlVO1==========");
		CjrjyjmsbVo cjrjyjmsb = new CjrjyjmsbVo();
		CjrjyjmssbsjVO sbxx= new CjrjyjmssbsjVO();
		NsrxxVO_Cjrjy nsrxx = new NsrxxVO_Cjrjy();
		//SbxxVO sbxx = new SbxxVO();

		// ������˰����Ϣ
		nsrxx.setJsjdm(jbsj.getJsjdm());
		//System.out.println("000000"+jbsj.getJsjdm());
		nsrxx.setNsrmc(jbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		nsrxx.setSwdjzh(cjrjyjmsbbo.getSwdjzh());
		//System.out.println("000000"+cjrjyjmsbbo.getSwdjzh());
		nsrxx.setZcdz(cjrjyjmsbbo.getZcdz());
		nsrxx.setJyfw(cjrjyjmsbbo.getJyfw());
		// �걨��Ϣ
		sbxx.setLrrq(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		sbxx.setSkssrq(cjrjyjmsbbo.getSkssrq());
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
		sbxx.setDwxz(cjrjyjmsbbo.getDwxz());
		sbxx.setZgzrs(cjrjyjmsbbo.getZgzrs());
		sbxx.setCjrzgrs(cjrjyjmsbbo.getCjrzgrs());
		sbxx.setCjrybl(cjrjyjmsbbo.getCjrybl());
		sbxx.setYnyyssr(cjrjyjmsbbo.getYnyyssr());
		sbxx.setYjyysse(cjrjyjmsbbo.getYjyysse());
		sbxx.setXsyhzzse(cjrjyjmsbbo.getXsyhzzse());
		sbxx.setByyjzyysxe(cjrjyjmsbbo.getByyjzyysxe());
		sbxx.setSyyjzyysxe(cjrjyjmsbbo.getSyyjzyysxe());
		sbxx.setBykjzyysxe(cjrjyjmsbbo.getBykjzyysxe());
		sbxx.setBysjjzyysye(cjrjyjmsbbo.getBysjjzyysye());
		sbxx.setBysjyesse(cjrjyjmsbbo.getBysjyesse());
		sbxx.setBymjzyysxe(cjrjyjmsbbo.getBymjzyysxe());
		sbxx.setQxdm(cjrjyjmsbbo.getQxdm());
		sbxx.setSqspbh(cjrjyjmsbbo.getSqspbh());
		sbxx.setSignFlagVal(cjrjyjmsbbo.getSignFlagVal());
		sbxx.setLrr(cjrjyjmsbbo.getLrr());
		sbxx.setIsError(cjrjyjmsbbo.getIsError());
		//System.out.println("===========test========convertToXmlVO1==========end");
		
		cjrjyjmsb.setNsrxx(nsrxx);
		cjrjyjmsb.setSbxx(sbxx);
		
		//����ҵ���������ֵ
		//����ʹ�ö���ywlx��ֵCJR4001
		String YWDM_SB_WS_CJRJMS = "20090120";
		//System.out.println("00000000"+CAProxy.getInstance().getXSLTSCHEMAVersion(YWDM_SB_WS_CJRJMS));
		//cjrjyjmsb.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(YWDM_SB_WS_CJRJMS));
		cjrjyjmsb.setYwlx("020040");
		cjrjyjmsb.setYwczlx("1");
		
		
		cjrjyjmsb.setXsltVersion(YWDM_SB_WS_CJRJMS);
		
		//cjrjyjmsb.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(YWDM_SB_WS_CJRJMS));
		cjrjyjmsb.setSchemaVersion(YWDM_SB_WS_CJRJMS);
		cjrjyjmsb.setYwlx(YWDM_SB_WS_CJRJMS);
		return cjrjyjmsb;

	}

	public CjrjyjmsbBo Czzsconvert2VO(CjrjyjmsbVo cjrjyjmsbvo)throws BaseException{
		CjrjyjmsbBo cjrjyjmsbbo = new CjrjyjmsbBo();
		//��˰����Ϣ
		cjrjyjmsbbo.setJsjdm(cjrjyjmsbvo.getNsrxx().getJsjdm());
		cjrjyjmsbbo.setNsrmc(cjrjyjmsbvo.getNsrxx().getNsrmc());
		cjrjyjmsbbo.setSwjgzzjgdm(cjrjyjmsbvo.getNsrxx().getSwjgzzjgdm());
		//�걨��Ϣ
		cjrjyjmsbbo.setSkssrq(cjrjyjmsbvo.getSbxx().getSkssrq());
		cjrjyjmsbbo.setLrrq(cjrjyjmsbvo.getSbxx().getLrrq());
		//�걨����
		cjrjyjmsbbo.setSwdjzh(cjrjyjmsbvo.getNsrxx().getSwdjzh());
		cjrjyjmsbbo.setDwxz(cjrjyjmsbvo.getSbxx().getDwxz());
		cjrjyjmsbbo.setZgzrs(cjrjyjmsbvo.getSbxx().getZgzrs());
		cjrjyjmsbbo.setCjrzgrs(cjrjyjmsbvo.getSbxx().getCjrzgrs());
		cjrjyjmsbbo.setCjrybl(cjrjyjmsbvo.getSbxx().getCjrybl());
		cjrjyjmsbbo.setYnyyssr(cjrjyjmsbvo.getSbxx().getYnyyssr());
		cjrjyjmsbbo.setYjyysse(cjrjyjmsbvo.getSbxx().getYjyysse());
		cjrjyjmsbbo.setXsyhzzse(cjrjyjmsbvo.getSbxx().getXsyhzzse());
		cjrjyjmsbbo.setByyjzyysxe(cjrjyjmsbvo.getSbxx().getByyjzyysxe());
		cjrjyjmsbbo.setSyyjzyysxe(cjrjyjmsbvo.getSbxx().getSyyjzyysxe());
		cjrjyjmsbbo.setBykjzyysxe(cjrjyjmsbvo.getSbxx().getBykjzyysxe());
		cjrjyjmsbbo.setBysjjzyysye(cjrjyjmsbvo.getSbxx().getBysjjzyysye());
		cjrjyjmsbbo.setBysjyesse(cjrjyjmsbvo.getSbxx().getBysjyesse());
		cjrjyjmsbbo.setBymjzyysxe(cjrjyjmsbvo.getSbxx().getBymjzyysxe());
		//cjrjyjmsbbo("yhid", userData.getYhid());
		cjrjyjmsbbo.setBz(cjrjyjmsbvo.getSbxx().getBz());
		cjrjyjmsbbo.setQxdm(cjrjyjmsbvo.getNsrxx().getSwjgzzjgdm().substring(0, 2));
		cjrjyjmsbbo.setSqspbh(cjrjyjmsbvo.getSbxx().getSqspbh());
		return cjrjyjmsbbo;
	}

	/*
	 * public ActionForward doReturn(ActionMapping actionMapping, ActionForm
	 * actionForm, HttpServletRequest httpServletRequest, HttpServletResponse
	 * httpServletResponse) throws BaseException { return
	 * actionMapping.findForward("Return"); }
	 */
	


    public String doReturn(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        // ת�򷵻غ��ҳ�档
        return "Return";
    }

}
