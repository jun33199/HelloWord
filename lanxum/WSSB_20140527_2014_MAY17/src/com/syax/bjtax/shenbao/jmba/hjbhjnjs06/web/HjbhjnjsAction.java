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
		   * 对请求的权限进行检查
		   */
		  protected String beforePerform(HttpServletRequest request,
		          HttpServletResponse response) {
		      // 检查权限 暂设置没有检查条件
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
		      // 生成VOPackage
		      VOPackage voPackage = new VOPackage();
		      // 设定vopackage参数
		      voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
		      voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		      voPackage.setUserData(userdata);
		      //@todo 从第二个跳转页面的request中获取
		      String basqwsh = (String)request.getSession().getAttribute("basqwsh");
		      JmbamxBo bo = new JmbamxBo();
		      bo.setBasqwsh(basqwsh);

		      voPackage.setData(bo);

		      // 调用后台操作，取得返回值
		      bo = (JmbamxBo) ShenbaoProxy.getInstance().process(
		              voPackage);
		      JmbaZbVO zbvo = bo.getZbvo();
		      //bo的ztdm，0新增，1再次，2修改，3错误
		      completeDm(zbvo,CodeTableUtil.getCodeTableMap(request,CodeTable.JMBA_JNJSXMLX_MAP));;
		      
		      
		      JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);

		      //构造数据
		      String strXml = vo.toXML();
		      Debug.out(strXml);
		      request.setAttribute(JmbamxBo.BAZTDM_ATTRIBUTE_NAME, bo.getZtdm());
             request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
		      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
		      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
		      // 转向到显示页面
			  Debug.out("转向到显示页面");
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
	  // 取得userdataJMBA_JNJSXMLX_LIST
	  List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJSXMLX_LIST);
    System.out.println(jmflList.size());

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    //@todo 从第二个跳转页面的request中获取
    String basqwsh = (String)request.getSession().getAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME);

    voPackage.setData(basqwsh);

    // 调用后台操作，取得返回值
    List l = (List) ShenbaoProxy.getInstance().process(
            voPackage);
    JmbaZbVO zbvo = (JmbaZbVO)l.get(0);
    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //构造数据
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    request.setAttribute("CS", jmflList);
    // 转向到显示页面
	  Debug.out("转向到显示页面");
    return CAConstants.EDITSHOW;
	  //return null;
}
   public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into delete action");
	    if (!isTokenValid(request))
	    {	        return CAConstants.INVALIDTOKEN;
	    }
		  // 取得userdata
	    UserData userdata = (UserData) this.getUserData(request);
        DzyjsjVO dzyj =  new DzyjsjVO();
        JmbaVO vo = new JmbaVO();
        JmbaVO oldVo = (JmbaVO) request.getSession().getAttribute(SessionKey.JMBA_DATA_KEY);

        // 验证电子元件签名
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
	    
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_DELETE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // 调用后台操作，取得返回值
	     ShenbaoProxy.getInstance().process(voPackage);
	     
        request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案删除成功！");
        //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
              LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案删除",
                      (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

	            return  doShow(request,response);
	}

   public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into save action");
	    if (!isTokenValid(request))
	    {
	        return CAConstants.INVALIDTOKEN;
	    }
		  // 取得userdata
	    UserData userdata = (UserData) this.getUserData(request);
       DzyjsjVO dzyj =  new DzyjsjVO();
       JmbaVO vo = new JmbaVO();

       // 验证电子元件签名
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

	    
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // 调用后台操作，取得返回值
	     ShenbaoProxy.getInstance().process(voPackage);
       request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
       //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
             LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案保存",
                     (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

	            return  doShow(request,response);
	}
   

   public String doEditZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into save action");
	    if (!isTokenValid(request))
	    {
	        return CAConstants.INVALIDTOKEN;
	    }
		  // 取得userdata
	    UserData userdata = (UserData) this.getUserData(request);
      DzyjsjVO dzyj =  new DzyjsjVO();
      JmbaVO vo = new JmbaVO();

      // 验证电子元件签名
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

	    
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // 调用后台操作，取得返回值
	     ShenbaoProxy.getInstance().process(voPackage);
      request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
      //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案保存",
                    (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

	            return  CAConstants.EDITZB;
	}

   public String doCommit(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into commit action");
	    if (!isTokenValid(request))
	    {
	        return CAConstants.INVALIDTOKEN;
	    }
		  // 取得userdata
	    UserData userdata = (UserData) this.getUserData(request);
       DzyjsjVO dzyj =  new DzyjsjVO();
       JmbaVO vo = new JmbaVO();

       // 验证电子元件签名
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

	    
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // 调用后台操作，取得返回值
	     ShenbaoProxy.getInstance().process(voPackage);
       request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案提交成功！");
       //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
             LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案提交",
                     (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

	            return  CAConstants.RETURN;
	}

   public String doShow(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {
	  // 取得userdata
	  List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJSXMLX_LIST);
    

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo 从第二个跳转页面的request中获取
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
    bo.setZtdm("1");
    //String basqwsh = (String)request.getSession().getAttribute("basqwsh");
    //JmbamxBo bo = new JmbamxBo();
    //bo.setBasqwsh(basqwsh);

    voPackage.setData(bo);
//    bo.setXh(mxxh);

    // 调用后台操作，取得返回值
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //构造数据
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    request.setAttribute("CS", jmflList);
    // 转向到显示页面
	  Debug.out("转向到显示页面");
    return CAConstants.EDITSHOW;
	  //return null;
}
   public String doZcba(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {
	  // 取得userdata
	  List jmflList = CodeTableUtil.getCodeTableList(request,
				CodeTable.JMBA_JNJSXMLX_LIST);
    

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo 从第二个跳转页面的request中获取
    String basqwsh = (String)request.getSession().getAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME);
    String mxxh=request.getParameter(JmbamxBo.BAMXXH_ATTRIBUTE_NAME);
    JmbamxBo bo = new JmbamxBo();
    bo.setZtdm("2");
    bo.setBasqwsh(basqwsh);
    bo.setXh(mxxh);

    voPackage.setData(bo);

    // 调用后台操作，取得返回值
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //构造数据
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    request.setAttribute("CS", jmflList);
    // 转向到显示页面
	  Debug.out("转向到显示页面");
    return CAConstants.EDITSHOW;
	  //return null;
}
   public String doView(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {
	  // 取得userdata

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo 从第二个跳转页面的request中获取
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
    //String basqwsh = (String)request.getSession().getAttribute("basqwsh");
    //JmbamxBo bo = new JmbamxBo();
    //bo.setBasqwsh(basqwsh);
    bo.setZtdm("9");

    voPackage.setData(bo);
  //  bo.setXh(mxxh);

    // 调用后台操作，取得返回值
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //构造数据
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    // 转向到显示页面
	  Debug.out("转向到显示页面");
    return CAConstants.VIEWDETAIL;
	  //return null;
}

   public String doViewBak(HttpServletRequest request,
        HttpServletResponse response) throws BaseException {

    UserData userdata = (UserData) this.getUserData(request);
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.HJBHJNJSZ06_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
    voPackage.setUserData(userdata);
    //@todo 从第二个跳转页面的request中获取
    String basqwsh = (String)request.getSession().getAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME);
    //String mxxh=request.getParameter(JmbamxBo.BAMXXH_ATTRIBUTE_NAME);
    JmbamxBo bo = new JmbamxBo();
    bo.setBasqwsh(basqwsh);
    bo.setZtdm("9");

    voPackage.setData(bo);

    // 调用后台操作，取得返回值
    JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
    //构造数据
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
    // 转向到显示页面
	  Debug.out("转向到显示页面");
    return CAConstants.VIEWSHOW;
	  //return null;
}

   private JmbaVO convertToXmlVO(JmbaZbVO zb,UserData ud,SWDJJBSJ jbsj) {
	//1 最上层VO
      JmbaVO vo = new JmbaVO();
      //2 纳税人VO 1.set 2
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
