/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.hjbhjnjs06.web;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;

import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba04VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba04WnVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba06VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba06WnVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba07Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.dm.JNJSXMLX;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HjbhjnjsAction  extends DcBaseAction{

		  /**
		   * �������Ȩ�޽��м��
		   */
		  protected String beforePerform(HttpServletRequest request,
		          HttpServletResponse response) {
		      // ���Ȩ�� ������û�м������
		      // System.out.println("beforePerform");
//		      if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
//		      {
		//
//		          return CAConstants.NOPERMISSION;
//		      }
		      return null;
		  }
   public String doShowBak(HttpServletRequest request,
		          HttpServletResponse response) throws BaseException {

		      UserData userdata = (UserData) this.getUserData(request);
		      Map djMap = (Map) FriendHelper.getDjInfo(request);
		      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		      // ����VOPackage
		      VOPackage voPackage = new VOPackage();
		      // �趨vopackage����
		      voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
		      voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		      voPackage.setUserData(userdata);
		      //@todo �ӵڶ�����תҳ���request�л�ȡ
		      String basqwsh = (String)request.getSession().getAttribute("basqwsh");
		      JmbamxBo bo = new JmbamxBo();
		      bo.setBasqwsh(basqwsh);

		      voPackage.setData(bo);

		      // ���ú�̨������ȡ�÷���ֵ
		      bo = (JmbamxBo) ShenbaoProxy.getInstance().process(
		              voPackage);
		      JmbaZbVO zbvo = bo.getZbvo();
		      //bo��ztdm��0������1�ٴΣ�2�޸ģ�3����
		      completeDm(zbvo,CodeTableUtil.getCodeTableMap(request,CodeTable.JMBA_JNJSXMLX_MAP));;
		      
		      
		      JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);

		      //��������
		      String strXml = vo.toXML();
		      Debug.out(strXml);
		      request.setAttribute(JmbamxBo.BAZTDM_ATTRIBUTE_NAME, bo.getZtdm());
             request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
		      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
		      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
		      // ת����ʾҳ��
			  Debug.out("ת����ʾҳ��");
		      return CAConstants.SHOW;
			  //return null;
		  }
   private JmbaZbVO completeDm(JmbaZbVO vo,Map dmMap){
   	  
   	List ba04List=vo.getQysdsjmba();
   	if (ba04List!=null){
   	  for (int i=0;i<ba04List.size();i++){
   	  	Jmba06VO vo04=(Jmba06VO)ba04List.get(i);
   	  	vo04.setJnjsxmlxmc(((JNJSXMLX)dmMap.get(vo04.getJnjsxmlxdm())).getJNJSXMLXMC());
   	  }
   	}
   	ba04List=vo.getWnqysdsjmba();
   	if (ba04List!=null){
 	  for (int i=0;i<ba04List.size();i++){
   	  	Jmba06WnVO vo04=(Jmba06WnVO)ba04List.get(i);
   	  	vo04.setJnjsxmlxmc(((JNJSXMLX)dmMap.get(vo04.getJnjsxmlxdm())).getJNJSXMLXMC());
   	  }
   	}
   	  return vo;
   }
   public String doAdd(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {
	  // ȡ��userdataJMBA_JNJSXMLX_LIST
	  List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJSXMLX_LIST);
    System.out.println(jmflList.size());

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    //@todo �ӵڶ�����תҳ���request�л�ȡ
    String basqwsh = (String)request.getSession().getAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME);

    voPackage.setData(basqwsh);

    // ���ú�̨������ȡ�÷���ֵ
    List l = (List) ShenbaoProxy.getInstance().process(
            voPackage);
    JmbaZbVO zbvo = (JmbaZbVO)l.get(0);
    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //��������
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    request.setAttribute("CS", jmflList);
    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
    return CAConstants.EDITSHOW;
	  //return null;
}
   public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into delete action");
	    if (!isTokenValid(request))
	    {	        return CAConstants.INVALIDTOKEN;
	    }
		  // ȡ��userdata
	    UserData userdata = (UserData) this.getUserData(request);
        DzyjsjVO dzyj =  new DzyjsjVO();
        JmbaVO vo = new JmbaVO();
        JmbaVO oldVo = (JmbaVO) request.getSession().getAttribute(SessionKey.JMBA_DATA_KEY);

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
        
        List oldList = ((JmbaZbVO)oldVo.getJmsbajl().get(0)).getQysdsjmba();
        List newList = ((JmbaZbVO)vo.getJmsbajl().get(0)).getQysdsjmba();
        boolean find = false;
        String mxxh="";
        for (int i=0;i<oldList.size();i++){
        	Jmba06VO oldmxvo = (Jmba06VO)oldList.get(i);
        	mxxh = oldmxvo.getXh();
        	find = false;
        	for (int j=0;j<newList.size();j++){
        		Jmba06VO mxvo = (Jmba06VO)newList.get(j);
        		if (mxxh.equals(mxvo.getXh())){
        			find=true;
        		}
        	}
        	
        	if (!find){
        		break;
        	}        	
        }

        dzyj.setYwczlx(vo.getYwczlx());
        dzyj.setYwdm(vo.getYwlx());

        JmbamxBo bo=new JmbamxBo();
        bo.setXh(mxxh);
        HashMap hm = new HashMap();
        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);
        hm.put(VOConstants.KEY_JMBA_MX_BO, bo);
	    
	    // ����VOPackage
	    VOPackage voPackage = new VOPackage();
	    // �趨vopackage����
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_DELETE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // ���ú�̨������ȡ�÷���ֵ
	     ShenbaoProxy.getInstance().process(voPackage);
	     
        request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ��ɾ���ɹ���");
        //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
              LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ��ɾ��",
                      (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");

	            return  doShow(request,response);
	}

   public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
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
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // ���ú�̨������ȡ�÷���ֵ
	     ShenbaoProxy.getInstance().process(voPackage);
       request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ������ɹ���");
       //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
             LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ������",
                     (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");

	            return  doShow(request,response);
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
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
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

   public String doCommit(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into commit action");
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
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // ���ú�̨������ȡ�÷���ֵ
	     ShenbaoProxy.getInstance().process(voPackage);
       request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ���ύ�ɹ���");
       //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
             LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ���ύ",
                     (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");

	            return  CAConstants.RETURN;
	}

   public String doShow(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {
	  // ȡ��userdata
	  List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJSXMLX_LIST);
    

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo �ӵڶ�����תҳ���request�л�ȡ
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
    bo.setZtdm("1");
    //String basqwsh = (String)request.getSession().getAttribute("basqwsh");
    //JmbamxBo bo = new JmbamxBo();
    //bo.setBasqwsh(basqwsh);

    voPackage.setData(bo);
//    bo.setXh(mxxh);

    // ���ú�̨������ȡ�÷���ֵ
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //��������
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    request.setAttribute("CS", jmflList);
    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
    return CAConstants.EDITSHOW;
	  //return null;
}
   public String doZcba(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {
	  // ȡ��userdata
	  List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJSXMLX_LIST);
    

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo �ӵڶ�����תҳ���request�л�ȡ
    String basqwsh = (String)request.getSession().getAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME);
    String mxxh=request.getParameter(JmbamxBo.BAMXXH_ATTRIBUTE_NAME);
    JmbamxBo bo = new JmbamxBo();
    bo.setZtdm("2");
    bo.setBasqwsh(basqwsh);
    bo.setXh(mxxh);

    voPackage.setData(bo);

    // ���ú�̨������ȡ�÷���ֵ
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //��������
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    request.setAttribute("CS", jmflList);
    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
    return CAConstants.EDITSHOW;
	  //return null;
}
   public String doView(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {
	  // ȡ��userdata

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo �ӵڶ�����תҳ���request�л�ȡ
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
    //String basqwsh = (String)request.getSession().getAttribute("basqwsh");
    //JmbamxBo bo = new JmbamxBo();
    //bo.setBasqwsh(basqwsh);
    bo.setZtdm("9");

    voPackage.setData(bo);
  //  bo.setXh(mxxh);

    // ���ú�̨������ȡ�÷���ֵ
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //��������
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
    return CAConstants.VIEWDETAIL;
	  //return null;
}

   public String doViewBak(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo �ӵڶ�����תҳ���request�л�ȡ
    String basqwsh = (String)request.getSession().getAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME);
    //String mxxh=request.getParameter(JmbamxBo.BAMXXH_ATTRIBUTE_NAME);
    JmbamxBo bo = new JmbamxBo();
    bo.setBasqwsh(basqwsh);
    bo.setZtdm("9");

    voPackage.setData(bo);

    // ���ú�̨������ȡ�÷���ֵ
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //��������
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
    return CAConstants.VIEWSHOW;
	  //return null;
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
  	vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA06);
     vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
      vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
      return vo;
  }


}
