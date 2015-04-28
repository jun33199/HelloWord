package com.syax.bjtax.shenbao.jmba.xrjscqyjcdlsjqy09.web;

import java.sql.Date;
import java.sql.Timestamp;
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
import com.syax.bjtax.shenbao.jmba.gjyqzdfcgxjsqy08.web.GjyqzdfcgxjsqyAction;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba08Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba09Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.dm.JMBASXDM;
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
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * <p>
 * Title: 北京地税核心征管系统－－上门申报
 * </p>
 * 
 * <p>
 * Description: (九)新办软件生产企业、集成电路设计企业
 * </p>
 * 
 * <p>
 * Copyright: 四一安信
 * </p>
 * 
 * <p>
 * Company: 四一安信
 * </p>
 * 
 * @author wangcl
 * @version 1.1
 */
public class XrjscqyjcdlsjqyAction extends DcBaseAction {
	// 构造器
	public XrjscqyjcdlsjqyAction() {

	}
	 private JmbaZbVO completeDm(JmbaZbVO vo,Map dmMap){
		   	vo.setJmbasxmc(((JMBASXDM)dmMap.get(vo.getJmbasxdm())).getJMBASXMC());
		   	return vo;
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
	  	vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA09);
	     vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
	      vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
	      return vo;
	  }
	// 初始化页面
	public String doView(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		// 获取纳税人登记基本信息
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.XRJRJSCQYJCDLSJQY09_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		
		JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
		  voPackage.setData(bo);
		// 调用后台操作，取得返回值
		  JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
			// 拼装jmbaVO
			  JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		// 构造xml数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		// 在请求中设置xml数据和xsl文件的版本号
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		 request.getSession().setAttribute("XSLLX09", "VIEW");
		 request.getSession().setAttribute("ZSLX09", ((Jmba09Vo) (zbvo.getQysdsjmba().get(0))).getZslxdm());
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.SHOW;
	}
	// 初始化页面
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if("VIEW".equals(request.getSession().getAttribute("XSLLX09"))){
			request.getSession().setAttribute("XSLLX09", null);
		}
		// 获取纳税人登记基本信息
		Map djMap = (Map) FriendHelper.getDjInfo(request);
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		// 取得userdata
		UserData userdata = (UserData) this.getUserData(request);
		// 生成VOPackage
		VOPackage voPackage = new VOPackage();
		// 设定vopackage参数
		voPackage.setProcessor(ProcessorNames.XRJRJSCQYJCDLSJQY09_PROCESSOR);
		voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
		voPackage.setUserData(userdata);
		
		// 调用后台操作，取得返回值
		JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
	      voPackage.setData(bo);
	      JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
	      zbvo=completeDm(zbvo,CodeTableUtil.getCodeTableMap(request, CodeTable.JMBA_BASX_MAP));
		// 拼装jmbaVO
	     JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
	     if (zbvo.getQysdsjmba()==null || zbvo.getQysdsjmba().size()==0){
		      	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		      }
		// 构造xml数据
		String strXml = vo.toXML();
		Debug.out(strXml);
		// 在请求中设置xml数据和xsl文件的版本号
		request.getSession().setAttribute(SessionKey.JMBA_DATA_KEY, vo);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo
				.getXsltVersion());
		if(vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)){
		 request.getSession().setAttribute("ZSLX09", ((Jmba09Vo) (zbvo.getQysdsjmba().get(0))).getZslxdm());
		 request.getSession().setAttribute("CZLX09", ((Jmba09Vo) (zbvo.getQysdsjmba().get(0))).getZs());
		}
		// 转向到显示页面
		Debug.out("转向到显示页面");
		return CAConstants.SHOW;
	}

	private JmbaVO convertToXmlVO(Map map, UserData ud, SWDJJBSJ jbsj,String BASQWSH) {
		// 主VO
		JmbaVO vo = new JmbaVO();
		// 纳税人VO构建，并将纳税人VO设置到主VO中
		NsrxxVO nsrxx = new NsrxxVO();
		nsrxx.setJsjdm(jbsj.getJsjdm());
		nsrxx.setNsrmc(jbsj.getNsrmc());
		nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		vo.setNsrxx(nsrxx);
		// 设置主表VO的减免备案事项代码和办案申请文书号
		JmbaZbVO zbvo = (JmbaZbVO) map.get("JmbaZbVO");
		zbvo.setJmbasxdm(VOConstants.QYSDSJMBA_SXDM09);
		zbvo.setBasqwsh(BASQWSH);
		// 将明细VO设置到主表Vo中
		Jmba09Vo vo09 = (Jmba09Vo) map.get("Jmba09VO");
	      vo09.setLrr(jbsj.getJsjdm());
	      vo09.setBand(Integer.parseInt(DateUtilPro.getCurYearStr4())-1+"");
	      DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	      vo09.setLrrq(format.format(new Date(System.currentTimeMillis())));
		zbvo.getQysdsjmba().add(vo09);
		// 将主表VO设置到主VO中，并设置主VO的xsl版本、schema版本、业务类型信息
		vo.getJmsbajl().add(zbvo);
		vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA09);
		return vo;
	}
	//新增功能
	public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
	  {
	          
	      if (!isTokenValid(request))
	      {
	          return CAConstants.INVALIDTOKEN;
	      }

	      String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
	      // 生成VOPackage
	      VOPackage voPackage = new VOPackage();
	      UserData ud = (UserData) this.getUserData(request);
	      DzyjHelper dh = new DzyjHelper();
	      System.out.println("jmba xmldata" + xmldata);
	      DzyjsjVO dzyj =  new DzyjsjVO();
	      JmbaVO jmbavo = new JmbaVO();

	      try
	      {
	      	
	          if (ud.getCaflag())
	          {
	              try
	              {
	                  dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
	              }
	              catch (ApplicationException e)
	              {
	                  throw ExceptionUtil.getBaseException(e);
	              }
	          }
	          System.out.println("执行操作之前要+++++++++++++++++++++++++++11111111111111111+++++++++++++++++++++++++++++++++++++++++++++++++" );
	          try
	          {
	             // XMLParseHelper.parseXMLString(wsksbvo, xmldata, XMLParseHelper.XERCES_PARSER, true,true);
	              //wsksbvo = new WsksbVO();
	              XMLParseHelper.parseXMLString(jmbavo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
	          }
	          catch (ApplicationException e)
	          {
	              throw ExceptionUtil.getBaseException(e);
	          }
	          System.out.println("执行操作之前要+++++++++++++++++++++++++++++++++++222222222222222+++++++++++++++++++++++++++++++++++++++++" );

	              dzyj.setYwczlx(jmbavo.getYwczlx());
	              dzyj.setYwdm(jmbavo.getYwlx());
	              // 取得登记数据
	              Map djMap = (Map) FriendHelper.getDjInfo(request);
	              SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

	              Timestamp now = new Timestamp(System.currentTimeMillis());
	              voPackage.setUserData(ud);
	              HashMap hm = new HashMap();
	              //hm.put(ZhsbMapConstant.SBSJ, jmbavo);
	           //   hm.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);

	              hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
	              hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
	              voPackage.setData(hm);
	              voPackage.setProcessor(ProcessorNames.XRJRJSCQYJCDLSJQY09_PROCESSOR);
	              voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	              System.out.println("执行操作之前要++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
	      ShenbaoProxy.getInstance().process(voPackage);
	      System.out.println("执行操作之后要+++++++++++++++++++++1111+++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
	      //签名原件暂不实现  20091227
	             // dzyj = (DzyjsjVO) ((HashMap) voPackage.getData()).get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
	              // 如果是CA户则进行签名回写

	              //jmbavo = convert2XMLVOResult(request, jbsj, wynsksb, now);
//	              String tmpxml = jmbavo.toXML();
//	              request.setAttribute("CA_XML_DATA", tmpxml);
//	              request.setAttribute("CA_XML_XSLT_VERSION", jmbavo.getXsltVersion());
//	              request.setAttribute("CA_XML_SCHEMA_VERSION", jmbavo.getSchemaVersion());

	              // 设置回执下载页面的后续操作。
	              //签名原件暂不实现  20091227
	            /*  if (ud.getCaflag())
	              {
	                  request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, Long.toString(dzyj.getLsh()));
	              }
	              else
	              {
	                  request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
	              }
	              */
	              System.out.println("执行操作之后要++++++++++++++++++2222++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
	              request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "新办软件生产企业、集成电路设计企业减免备案保存成功！");
	        //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
	              LogUtil.getInstance().log(FriendHelper.getUserData(request), "新办软件生产企业、集成电路设计企业减免备案保存",
	                      (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
	              System.out.println("执行操作之后要+++++++++++++++++++3333+++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );
	 
	             
	  				return doShow(request, response);
	  			
	          //return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
	      }
	      catch (Exception e)
	      {
	          // /3.9.设置空xml，转向失败页面
	          request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo.toXML());
	          request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jmbavo.getXsltVersion());
	          request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jmbavo.getSchemaVersion());
	          System.out.println("减免备案异常信息: ===== " + e.getMessage());

	          throw ExceptionUtil.getBaseException(e);
	      }

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
	    voPackage.setProcessor(ProcessorNames.XRJRJSCQYJCDLSJQY09_PROCESSOR);
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
   

	/**
	 * 对请求的权限进行检查
	 */
	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限 暂设置没有检查条件
		// System.out.println("beforePerform");
		// if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
		// {
		//
		// return CAConstants.NOPERMISSION;
		// }
		return null;
	}
}
