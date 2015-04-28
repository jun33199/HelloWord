package com.syax.bjtax.shenbao.jmba.jyxwhsy19.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jyxwhsy19.processor.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;

import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba08Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba19VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;

import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.dm.JMBASXDM;
import com.syax.bjtax.shenbao.util.QueryDmUtil;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Jmfl;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: ��Ӫ���Ļ���ҵ��λת��Ϊ��ҵ���������������¼��Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: SYAX
 * </p>
 * @version 1.0
 */

public class LrjyxwhsyAction extends DcBaseAction{

	/**
	 * ¼�뱸������ ��ʼ������ 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	 private JmbaZbVO completeDm(JmbaZbVO vo,Map dmMap){
		   	vo.setJmbasxmc(((JMBASXDM)dmMap.get(vo.getJmbasxdm())).getJMBASXMC());
		   	return vo;
		   }
	  private JmbaVO convertToXmlVO(JmbaZbVO zb,UserData ud,SWDJJBSJ jbsj) {
			//1 ���ϲ�VO
		      JmbaVO vo = new JmbaVO();
		      //2 ��˰��VO 1.set 2
		      NsrxxVO nsrxx = new NsrxxVO();
		      nsrxx.setJsjdm(jbsj.getJsjdm());
		      nsrxx.setNsrmc(jbsj.getNsrmc());
		      nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		      //1.set 2
		      vo.setNsrxx(nsrxx);
		      List zbl=new ArrayList();
		      zbl.add(zb);
		      vo.setJmsbajl(zbl);
		      
		  	vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);
		  	vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA08);
		     vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		      vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		      return vo;
		  }

	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		QueryDmUtil obj = new QueryDmUtil();
		request.getSession().setAttribute("GXJSFUDM", obj.getWhdm());
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		/*
		List jmflList = CodeTableUtil.getCodeTableList(request,
					CodeTable.JMBA_BASX_LIST);
		System.out.println("JMBA_BASX_LIST:" + jmflList.size());
		*/	
		
		// ȡ��userdata
		UserData userdata = (UserData) this.getUserData(request);
        VOPackage voPackage = new VOPackage();
        // �趨vopackage����
        voPackage.setProcessor(ProcessorNames.JYXWHSY19_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
        voPackage.setUserData(userdata);
    	JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
	      voPackage.setData(bo);
	      JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
	      zbvo=completeDm(zbvo,CodeTableUtil.getCodeTableMap(request, CodeTable.JMBA_BASX_MAP));
		// ƴװjmbaVO
	     JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
	     if (zbvo.getQysdsjmba()==null || zbvo.getQysdsjmba().size()==0){
		      	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		      }
	  // ����xml����
			String strXml = vo.toXML();
			Debug.out(strXml);
			Debug.out("+++++++++++"+zbvo.toXML());
			// ������������xml���ݺ�xsl�ļ��İ汾��
			request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
					.getXsltVersion());
			Debug.out(vo.getJmsbajl().get(0).getClass());
		return CAConstants.SHOW;
	}
	public String doQuery(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// ��ȡ���¼�����������LIST

		QueryDmUtil obj = new QueryDmUtil();
		request.getSession().setAttribute("GXJSFUDM", obj.getWhdm());
		// ��ȡ��˰�˵Ǽǻ�����Ϣ
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// ȡ��userdata
		UserData userdata = (UserData) this.getUserData(request);
		// ����VOPackage
		VOPackage voPackage = new VOPackage();
		// �趨vopackage����
		voPackage.setProcessor(ProcessorNames.JYXWHSY19_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		// @todo �ӵڶ�����תҳ���session�л�ȡ
		// ���ú�̨������ȡ�÷���ֵ
		 JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
		  voPackage.setData(bo);
		  JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
		// ƴװjmbaVO
		  JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		// ����xml����
		String strXml = vo.toXML();
		Debug.out(strXml);
		// ������������xml���ݺ�xsl�ļ��İ汾��
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		
		//Debug.out(((Jmba08Vo) (zbvo.getQysdsjmba().get(0))).getGxjslydm()+"-------------���¼�������");
		// ת����ʾҳ��
		Debug.out("ת����ʾҳ��");
		return CAConstants.QUERY;
	}
	public String doEditZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into save action");
	    if (!isTokenValid(request))
	    {
	        return CAConstants.INVALIDTOKEN;
	    }
		  // ȡ��userdata
	    UserData userdata = (UserData) this.getUserData(request);
      DzyjsjVO dzyj =  new DzyjsjVO();
      JmbaVO vo = new JmbaVO();

      // ��֤����Ԫ��ǩ��
      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
      DzyjHelper dh = new DzyjHelper();
      if (userdata.getCaflag())
      {
          try
          {
              dzyj = dh.verifyAndSign(request, userdata.getCert(), userdata.getSsdwdm());
          }
          catch (ApplicationException e)
          {
              throw ExceptionUtil.getBaseException(e);
          }
      }
      try
      {
          XMLParseHelper.parseXMLString(vo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
      }
      catch (ApplicationException e)
      {
          throw ExceptionUtil.getBaseException(e);
      }

      dzyj.setYwczlx(vo.getYwczlx());
      dzyj.setYwdm(vo.getYwlx());

      HashMap hm = new HashMap();
      hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
      hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

	    
	    // ����VOPackage
	    VOPackage voPackage = new VOPackage();
	    // �趨vopackage����
	    voPackage.setProcessor(ProcessorNames.JYXWHSY19_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // ���ú�̨������ȡ�÷���ֵ
	     ShenbaoProxy.getInstance().process(voPackage);
      request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ������ɹ���");
      //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ������",
                    (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");

	            return  CAConstants.EDITZB;
	}

	/**
	 * ¼�뱸������ ��ʼ������ 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	
	
	


	// ��������
	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}

		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		// ����VOPackage
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
					.println("ִ�в���֮ǰҪ+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++");
			try {
				// XMLParseHelper.parseXMLString(wsksbvo, xmldata,
				// XMLParseHelper.XERCES_PARSER, true,true);
				// wsksbvo = new WsksbVO();
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
//			Map djMap = (Map) FriendHelper.getDjInfo(request);
//			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			voPackage.setUserData(ud);
			HashMap hm = new HashMap();
			// hm.put(ZhsbMapConstant.SBSJ, jmbavo);
			// hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
			voPackage.setData(hm);
			voPackage.setProcessor(ProcessorNames.JYXWHSY19_PROCESSOR);
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
			System.out
					.println("ִ�в���֮ǰҪ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			ShenbaoProxy.getInstance().process(voPackage);
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
					"��Ӫ���Ļ���ҵ��λת��Ϊ��ҵ���������ɹ���");
			// request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
			// CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
			LogUtil.getInstance().log(FriendHelper.getUserData(request),
					"��Ӫ���Ļ���ҵ��λת��Ϊ��ҵ���������",
					(new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
			System.out
					.println("ִ�в���֮��Ҫ+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//			if ("3".equals(jmbavo.getYwczlx())) {
//				return "Commit";
//			} else {
//				return doShow(request, response);
//			}
			return doShow(request, response);
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
	 
	/**
	 * �������Ȩ�޽��м��
	 */
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// ���Ȩ�� ������û�м������
        // System.out.println("beforePerform");
//        if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
//        {
//
//            return CAConstants.NOPERMISSION;
//        }
        return null;
	}
}
