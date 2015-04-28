 /*
 * Created on 2009-12-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.jmbaz.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba07Vo;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZlqdVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.dm.Zlqddm;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author kf2b
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JmbazAction  extends DcBaseAction{

	private boolean checkZq(){
    	Date now = new Date();
    	String zqstr = TinyTools.getProperty("WSSB_CZZSQYJBXXB_ZQRL_MONTH_"
                + (new SimpleDateFormat("MM")).format(now));
        return 	SbzlAccess.withinZq(zqstr,now);
        //return false;
	}

	  /**
	   * 对请求的权限进行检查
	   */
	  protected String beforePerform(HttpServletRequest request,
	          HttpServletResponse response) {

        // 检查权限 暂设置没有检查条件
	       System.out.println("beforePerform");
		      if (!SbzlAccess.checkCzzsQy(request))
//	      if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
	      {
	          return CAConstants.NOPERMISSION;
	      }
	      String zq="1";
	      if (checkZq()){
	      	zq="0";
	      }
	      request.setAttribute(JmbazForm.ZQ_ATTRIBUTE_NAME,zq);
	      return null;
	  }
	  public String doShow(HttpServletRequest request,
	          HttpServletResponse response) throws BaseException {


		  //取得代码表
		  List jmlx=CodeTableUtil.getCodeTableList(request, CodeTable.JMBA_BASX_LIST);
		  // 取得userdata
	      UserData userdata = (UserData) this.getUserData(request);
	    JmbazForm baform = (JmbazForm)request.getSession().getAttribute(JmbazForm.FORM_ATTRIBUTE_NAME);
	      JmbaVO vo  = new JmbaVO();
	    // 生成VOPackage
	      VOPackage voPackage = new VOPackage();
	      // 设定vopackage参数
	      voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	      voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
	      voPackage.setUserData(userdata);
	      voPackage.setData(baform);
	      Map djMap = (Map) FriendHelper.getDjInfo(request);
	      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

	      // 调用后台操作，取得返回值
		    if (baform ==null){
			      baform =new JmbazForm();
			      baform.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");
			      vo  = this.convertToXmlVO(null,userdata,jbsj);
			  }else{
			  	baform.setCheckZq("1");
			      if (checkZq()){
			      	baform.setCheckZq("0");
			      }
			  	List res = (List) ShenbaoProxy.getInstance().process(
	              voPackage);
			  	vo  = this.convertToXmlVO(res,userdata,jbsj);
			  }
		    
		      /*--modified by huohb,2014-06-16--*/
		      try {
			      	//调用校验
			      	CheckBean checkBean = new CheckBean();	
			      	checkBean.setJsjdm(userdata.getYhid());
			      	checkBean.createZgrqByCurrenttimeY();
			      	QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			      } catch (Exception e) {
			      	  vo.setSfxz("N");
			      	//throw ExceptionUtil.getBaseException(e);
			  	}

		      /*----*/

	      //构造数据
	      String strXml = vo.toXML();
	      Debug.out(strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
	      request.getSession().setAttribute("LS", jmlx);

	      // 转向到显示页面
		  Debug.out("转向到显示页面");
	      return CAConstants.SHOW;
	  }
	  public String doInit(HttpServletRequest request,
	          HttpServletResponse response) throws BaseException {


		  //取得代码表
		  List jmlx=CodeTableUtil.getCodeTableList(request, CodeTable.JMBA_BASX_LIST);
		  
		  // 取得userdata
	      UserData userdata = (UserData) this.getUserData(request);
	      request.getSession().removeAttribute(JmbazForm.FORM_ATTRIBUTE_NAME);
	      JmbazForm baform = null;
	    //JmbazForm baform = (JmbazForm)request.getSession().getAttribute(JmbazForm.FORM_ATTRIBUTE_NAME);
	      JmbaVO vo  = new JmbaVO();
	      
	      
	    // 生成VOPackage
	      VOPackage voPackage = new VOPackage();
	      // 设定vopackage参数
	      voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	      voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
	      voPackage.setUserData(userdata);
	      voPackage.setData(baform);
	      Map djMap = (Map) FriendHelper.getDjInfo(request);
	      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

	      // 调用后台操作，取得返回值
			      baform =new JmbazForm();
			      baform.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");
			      baform.setCheckZq("1");
			      if (checkZq()){
			      	baform.setCheckZq("0");
			      }
			      vo  = this.convertToXmlVO(null,userdata,jbsj);
			      /*--modified by huohb,2014-06-16--*/
			      try {
				      	//调用校验
				      	CheckBean checkBean = new CheckBean();	
				      	checkBean.setJsjdm(userdata.getYhid());
				      	checkBean.createZgrqByCurrenttimeY();
				      	QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
				      } catch (Exception e) {
				      	  vo.setSfxz("N");
				      	//throw ExceptionUtil.getBaseException(e);
				  	}

			      /*----*/
	      //构造数据
	      String strXml = vo.toXML();
	      Debug.out(strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
	      request.getSession().setAttribute("LS", jmlx);

	      // 转向到显示页面
		  Debug.out("转向到显示页面");
	      return CAConstants.SHOW;
	  }
	  public String doQuery(HttpServletRequest request,
	          HttpServletResponse response) throws BaseException {
		  // 取得userdata
	      UserData userdata = (UserData) this.getUserData(request);

	      JmbazForm zb =new JmbazForm();
	      zb.setBand(request.getParameter("band"));
	      zb.setZtdm(request.getParameter("ztdm"));
	      zb.setJmbasxdm(request.getParameter("jmbasxdm"));
       	  zb.setCheckZq("1");
	      if (checkZq()){
	      	zb.setCheckZq("0");
	      }

	      request.getSession().setAttribute(JmbazForm.FORM_ATTRIBUTE_NAME,zb);
	      // 生成VOPackage
	      VOPackage voPackage = new VOPackage();
	      // 设定vopackage参数
	      voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	      voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
	      voPackage.setUserData(userdata);
	      voPackage.setData(zb);

	      // 调用后台操作，取得返回值
	      List res = (List) ShenbaoProxy.getInstance().process(
	              voPackage);

	      Map djMap = (Map) FriendHelper.getDjInfo(request);
	      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
	      JmbaVO vo  = this.convertToXmlVO(res,userdata,jbsj);
	      
	      try {
		      	//调用校验
		      	CheckBean checkBean = new CheckBean();	
		      	checkBean.setJsjdm(userdata.getYhid());
		      	checkBean.createZgrqByCurrenttimeY();
		      	QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
		      } catch (Exception e) {
		      	  vo.setSfxz("N");
		      	//throw ExceptionUtil.getBaseException(e);
		  	}
	      //构造数据
	      String strXml = vo.toXML();
	      Debug.out(strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

	      // 转向到显示页面
		  Debug.out("转向到显示页面");
	      return CAConstants.SHOW;
		  //return null;
	  }

private JmbaVO convertToXmlVO(List zbl,UserData ud,SWDJJBSJ jbsj) {
	//1 最上层VO
      JmbaVO vo = new JmbaVO();
      //2 纳税人VO 1.set 2
      NsrxxVO nsrxx = new NsrxxVO();
      nsrxx.setJsjdm(jbsj.getJsjdm());
      nsrxx.setNsrmc(jbsj.getNsrmc());
      nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
      
      //1.set 2
      vo.setNsrxx(nsrxx);
      vo.setJmsbajl(zbl);
      
      vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
      vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
      vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
      	vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA00);
      return vo;
  }
private JmbaZbVO getZbvo(){
	JmbaZbVO vo= new JmbaZbVO();
	vo.setSzdm("30");
	vo.setSzmc("企业所得税");
	vo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");

	return vo;
}

public String doReturnEdit(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
 	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbaVO vo  = new JmbaVO();
    String jmbasxdm=request.getParameter("jmbasxdm");
    return  CAConstants.SAVE+"Lr"+jmbasxdm;

}
public String doReturnView(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
 	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbaVO vo  = new JmbaVO();
    String jmbasxdm=request.getParameter("jmbasxdm");
    return  CAConstants.SHOW+"Lr"+jmbasxdm;

}

public String doAdd(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	JmbaVO vo  = new JmbaVO();
 	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);
    try {
    	//调用校验
    	CheckBean checkBean = new CheckBean();	
    	checkBean.setJsjdm(userdata.getYhid());
    	checkBean.createZgrqByCurrenttimeY();
    	QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
    } catch (Exception e) {
    	throw ExceptionUtil.getBaseException(e);
	}
    
//    Map djMap = (Map) FriendHelper.getDjInfo(request);
//    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    String jmbasxdm=request.getParameter("jmbasxdmadd");
    JmbazForm baform = (JmbazForm)request.getSession().getAttribute(JmbazForm.FORM_ATTRIBUTE_NAME);
    baform.setJmbasxdmadd(jmbasxdm);
    request.getSession().setAttribute(JmbazForm.FORM_ATTRIBUTE_NAME,baform);
    // 调用后台操作，取得返回值
//    List l= new ArrayList();
    JmbaZbVO zbvo=getZbvo();
    zbvo.setJmbasxdm(jmbasxdm);
    //l.add(zbvo);

    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
    voPackage.setUserData(userdata);
    voPackage.setData(zbvo);

    // 调用后台操作，取得返回值
    JmbamxBo bamxbo= (JmbamxBo) ShenbaoProxy.getInstance().process(
            voPackage);
    
    //JmbamxBo bamxbo=null;//????
    
//    vo  = this.convertToXmlVO(l,userdata,jbsj);
//	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
//	
//    //构造数据
//    String strXml = vo.toXML();
//    Debug.out(strXml);
//测试用？？？？？
    //request.getSession().setAttribute("LS", res);
    
    //request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    //request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    //request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

    // 转向到显示页面
	  Debug.out("转向到显示页面");
	  if ("1".equals(bamxbo.getZtdm())){//重复
	  	request.setAttribute(JmbazForm.MESSAGE_ATTRIBUTE_NAME,"此备案事项已经保存过，不能重复保存。");
	  	  return doShow(request,response);
	  }
	  if ("2".equals(bamxbo.getZtdm())){//
	  	request.setAttribute(JmbazForm.MESSAGE_ATTRIBUTE_NAME,"系统错误，请与管理员联系。");
	  	return  doShow(request,response);
	  	
	  }
	  request.getSession().setAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME,bamxbo);
	   
	      return  CAConstants.SAVE+"Lr"+jmbasxdm;

   
}
//修改明细
public String doEdit(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);

    String basqwsh=request.getParameter("basqwsh");
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // 调用后台操作，取得返回值
    List res = (List) ShenbaoProxy.getInstance().process(
            voPackage);
    JmbaZbVO zbvo=(JmbaZbVO)res.get(0);

    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    JmbamxBo bamxbo=new JmbamxBo();
    bamxbo.setBasqwsh(basqwsh);
    bamxbo.setBand(zbvo.getBand());
    bamxbo.setJmbasxdm(zbvo.getJmbasxdm());
    bamxbo.setBasqbh(zbvo.getBasqbh());
    
    //JmbaVO vo  = this.convertToXmlVO(res,userdata,jbsj);
	//vo.setYwczlx(CAcodeConstants.YWCZLX_NEW);
  //构造数据

    // 转向到显示页面
	  Debug.out("转向到显示页面");
	  request.getSession().setAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME,bamxbo);
	   
	      return  CAConstants.SAVE+"Lr"+zbvo.getJmbasxdm();
}

//明细保存完后跳到这里
public String doEditZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);

    String basqwsh=bo.getBasqwsh();
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // 调用后台操作，取得返回值
    List res = (List) ShenbaoProxy.getInstance().process(
            voPackage);

    Map baMap=CodeTableUtil.getCodeTableMap(request,CodeTable.JMBA_JMBAZLQD_MAP);
    
    JmbaZbVO zbvo=(JmbaZbVO)res.get(0);
    if(zbvo.getQsrq()==null ||zbvo.getQsrq().equals("")){
    	zbvo.setQsrq(zbvo.getBand()+"-01-01");
    	zbvo.setJzrq(zbvo.getBand()+"-12-31");
    	
    }
    //zbvo.setBajlzlqd(getZlqd(zbvo,baMap,bo));
    
    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    
    zbvo.setBajlzlqd(getZlqd(zbvo,baMap,bo,jbsj));
    
    JmbaVO vo  = this.convertToXmlVO(res,userdata,jbsj);
	vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);
	
	
  //构造数据
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

    // 转向到显示页面
	  Debug.out("转向到显示页面");
    return CAConstants.EDITSHOW;
}

/**
 * 根据不同的减免备案事项代码和备案年度 获取资料使用年度
 * 安置残疾人所支付的工资备案事项    0030
 * @param bo
 * @return
 * wangcy 2014-02-14
 */
private String getSynd(JmbamxBo bo){
	String synd="2012";
	String jmbasxdm=bo.getJmbasxdm();
	String band=bo.getBand();
	System.out.println("jmbasxdm:"+jmbasxdm +" band: "+ band);
	
	String jmbsxdm[]={"0020","0030","0040","0050","0060","0180","0210","0220"};
	if(Arrays.asList(jmbsxdm).contains(jmbasxdm)){
		if(Integer.valueOf(band).intValue()>=2012){
			synd="2013";
		}else{
			synd="2012";
		}
		//下面代码可不用，网上申报不可能小于2012年
		if(jmbasxdm.equals("0210") || jmbasxdm.equals("0220")){
			synd="2013";
		}
	}
	return synd;
}

	/**
	 * /**
	 * @author gaoyh 
	 * @date 2011-09-01 
	 * @modify-type add
	 * @description: 根据企业所得税处2011.08.15要求，将资料清单中“减税免税备案表”修改为：
	 *  			 根据纳税人所属区县显示为“北京市XXX区县分局地方税务局减税免税备案表”
	 * @param zbvo
	 * @param baMap
	 * @param bo
	 * @param jbsj
	 * @return
	 */
private List getZlqd(JmbaZbVO zbvo,Map baMap,JmbamxBo bo, SWDJJBSJ jbsj){
	System.out.println("============getZlqd(, , , , )=================");
	String qxdm = jbsj.getSwjgzzjgdm().substring(0,2);
	System.out.println("============qxdm=="+qxdm);
	List l=zbvo.getBajlzlqd();
	
	/**
	 * 根据不同的减免备案事项代码和备案年度 获取资料使用年度
	 */
	String synd="2012";
	synd=getSynd(bo);
			
	//根据纳税人所属区县对备案清单进行处理
	Map clzlqddm01Map = baMap;
	//clzlqddm01Map.get()
	System.out.println(zbvo.getJmbasxdm()+"  zlqdmapSize="+baMap.size());
	if (l==null || l.size()==0){//新增
		l=new ArrayList();
		//备案07特殊处理
		String basxdm=zbvo.getJmbasxdm();
		if (basxdm.equals("0070")){
			basxdm=basxdm.substring(0,3)+bo.getJnjwbz();
		}
	    if (baMap!=null && baMap.containsKey(basxdm)){

		List l1= (List)baMap.get(basxdm);
		for (int i=0;i<l1.size();i++){
			Zlqddm dm=(Zlqddm)l1.get(i);
			if(dm.getSynd().equals(synd)){
				JmbaZlqdVO qo=new JmbaZlqdVO();
				qo.setZlqddm(dm.getZlqddm());
				if("01".equals(dm.getZlqddm())||dm.getZlqddm().equals("01")){
					if("01".equals(qxdm)||qxdm.equals("01")){
						qo.setZlqd("北京市东城区地方税务局减税免税备案表");
					}
					else if("02".equals(qxdm)||qxdm.equals("02")){
						qo.setZlqd("北京市西城区地方税务局减税免税备案表");
					}
					else if("03".equals(qxdm)||qxdm.equals("03")){
						qo.setZlqd("北京市崇文区地方税务局减税免税备案表");
					}
					else if("04".equals(qxdm)||qxdm.equals("04")){
						qo.setZlqd("北京市宣武区地方税务局减税免税备案表");
					}
					else if("05".equals(qxdm)||qxdm.equals("05")){
						qo.setZlqd("北京市朝阳区地方税务局减税免税备案表");
					}
					else if("06".equals(qxdm)||qxdm.equals("06")){
						qo.setZlqd("北京市海淀区地方税务局减税免税备案表");
					}
					else if("07".equals(qxdm)||qxdm.equals("07")){
						qo.setZlqd("北京市丰台区地方税务局减税免税备案表");
					}
					else if("08".equals(qxdm)||qxdm.equals("08")){
						qo.setZlqd("北京市石景山区地方税务局减税免税备案表");
					}
					else if("09".equals(qxdm)||qxdm.equals("09")){
						qo.setZlqd("北京市门头沟区地方税务局减税免税备案表");
					}
					else if("10".equals(qxdm)||qxdm.equals("10")){
						qo.setZlqd("北京市燕山区地方税务局减税免税备案表");
					}
					else if("11".equals(qxdm)||qxdm.equals("11")){
						qo.setZlqd("北京市昌平区地方税务局减税免税备案表");
					}
					else if("12".equals(qxdm)||qxdm.equals("12")){
						qo.setZlqd("北京市通州区地方税务局减税免税备案表");
					}
					else if("13".equals(qxdm)||qxdm.equals("13")){
						qo.setZlqd("北京市顺义区地方税务局减税免税备案表");
					}
					else if("14".equals(qxdm)||qxdm.equals("14")){
						qo.setZlqd("北京市大兴区地方税务局减税免税备案表");
					}
					else if("15".equals(qxdm)||qxdm.equals("15")){
						qo.setZlqd("北京市房山区地方税务局减税免税备案表");
					}
					else if("16".equals(qxdm)||qxdm.equals("16")){
						qo.setZlqd("北京市怀柔区地方税务局减税免税备案表");
					}
					else if("17".equals(qxdm)||qxdm.equals("17")){
						qo.setZlqd("北京市密云县地方税务局减税免税备案表");
					}
					else if("18".equals(qxdm)||qxdm.equals("18")){
						qo.setZlqd("北京市平谷区地方税务局减税免税备案表");
					}
					else if("19".equals(qxdm)||qxdm.equals("19")){
						qo.setZlqd("北京市延庆县地方税务局减税免税备案表");
					}
					else if("20".equals(qxdm)||qxdm.equals("20")){
						qo.setZlqd("北京市开发区分局地方税务局减税免税备案表");
					}
					else if("21".equals(qxdm)||qxdm.equals("21")){
						qo.setZlqd("北京市西站分局地方税务局减税免税备案表");
					}
					else if("22".equals(qxdm)||qxdm.equals("22")){
						qo.setZlqd("北京市涉外分局地方税务局减税免税备案表");
					}
					else if("25".equals(qxdm)||qxdm.equals("25")){
						qo.setZlqd("北京市第二稽查局减税免税备案表");
					}
					else if("41".equals(qxdm)||qxdm.equals("41")){
						qo.setZlqd("北京市第一稽查局减税免税备案表");
					}
					else if("90".equals(qxdm)||qxdm.equals("90")){
						qo.setZlqd("北京市地方税务局减税免税备案表");
					}
					else {
						qo.setZlqd("北京市其它地方税务局减税免税备案表");
					}
				}else{
					qo.setZlqd(dm.getZlqdmc());	
				}
				qo.setSfkysc(dm.getSfkysc());
			    l.add(qo);
		    }
		}
	    }
	}
	return l;
}


//资料清单处理，如果是新增，则从代码表里取
//否则不用处理
private List getZlqd(JmbaZbVO zbvo,Map baMap,JmbamxBo bo){
	System.out.println("============getZlqd(, , , )=================");
	List l=zbvo.getBajlzlqd();
	System.out.println(zbvo.getJmbasxdm()+"  zlqdmapSize="+baMap.size());
	if (l==null || l.size()==0){//新增
		l=new ArrayList();
		//备案07特殊处理
		String basxdm=zbvo.getJmbasxdm();
		if (basxdm.equals("0070")){
			basxdm=basxdm.substring(0,3)+bo.getJnjwbz();
		}
	    if (baMap!=null && baMap.containsKey(basxdm)){

		List l1= (List)baMap.get(basxdm);
		for (int i=0;i<l1.size();i++){
			Zlqddm dm=(Zlqddm)l1.get(i);
			JmbaZlqdVO qo=new JmbaZlqdVO();
			qo.setZlqddm(dm.getZlqddm());
			qo.setZlqd(dm.getZlqdmc());
			qo.setSfkysc(dm.getSfkysc());
		    l.add(qo);
		}
	    }
	}
	return l;
}

//明细保存完后跳到这里
public String doViewZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);

    String basqwsh=bo.getBasqwsh();
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // 调用后台操作，取得返回值
    List res = (List) ShenbaoProxy.getInstance().process(
            voPackage);

    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    JmbaVO vo  = this.convertToXmlVO(res,userdata,jbsj);
	vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);
	
  //构造数据
    String strXml = vo.toXML();
    Debug.out(strXml);   
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

    // 转向到显示页面
	  Debug.out("转向到显示页面");
	  return CAConstants.VIEWSHOW;
}

public String doView(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
 	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);

    String basqwsh=request.getParameter("basqwsh");
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // 生成VOPackage
    VOPackage voPackage = new VOPackage();
    // 设定vopackage参数
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // 调用后台操作，取得返回值
    List res = (List) ShenbaoProxy.getInstance().process(
            voPackage);

    JmbaZbVO zbvo=(JmbaZbVO)res.get(0);

    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    JmbamxBo bamxbo=new JmbamxBo();
    bamxbo.setBasqwsh(basqwsh);
    bamxbo.setBand(zbvo.getBand());
    bamxbo.setJmbasxdm(zbvo.getJmbasxdm());
    bamxbo.setBasqbh(zbvo.getBasqbh());

    // 转向到显示页面
	  Debug.out("转向到显示页面");
	  request.getSession().setAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME,bamxbo);
	   
	      return  CAConstants.SHOW+"Lr"+zbvo.getJmbasxdm();
    //return CAConstants.VIEWSHOW;
}


public String doSave1(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	return doSave(request,response,"1");
}

public String doSave2(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	return doSave(request,response,"2");
}


public String doViewDetail1(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	return doViewDetail(request,response,"1");
}

public String doViewDetail2(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	return doViewDetail(request,response,"2");
}

//保存并打印saveType=1
//保存并录入备案申请明细saveType=2
public String doViewDetail(HttpServletRequest request, HttpServletResponse response,String saveType) throws BaseException
{

	  // 取得userdata
    UserData userdata = (UserData) this.getUserData(request);
    String jmbasxdm=request.getParameter("jmbasxdm");
    String basqwsh=request.getParameter("basqwsh");
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    String basxdm="Print";
    if (saveType=="1"){//打印
        // 生成VOPackage
        VOPackage voPackage = new VOPackage();
        // 设定vopackage参数
        voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
        voPackage.setUserData(userdata);
        voPackage.setData(basqwsh);

        // 调用后台操作，取得返回值
        List res = (List) ShenbaoProxy.getInstance().process(
                voPackage);
        Map djMap = (Map) FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
        JmbaVO resvo  = this.convertToXmlVO(res,userdata,jbsj);
    	request.setAttribute("jmbavo",resvo);
    }else{//录入备案申请
      basxdm="Lr"+jmbasxdm;
    }
      return  CAConstants.SHOW+basxdm;
}
//保存并打印saveType=1
//保存并录入备案申请明细saveType=2
public String doSave(HttpServletRequest request, HttpServletResponse response,String saveType) throws BaseException
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
    System.out.println("saveType="+saveType);

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
//            	jmbavo.setBz("N");
                throw ExceptionUtil.getBaseException(e);
            }
        }
        try
        {
            XMLParseHelper.parseXMLString(jmbavo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
        }
        catch (ApplicationException e)
        {
            throw ExceptionUtil.getBaseException(e);
        }

        jmbavo.setYwlx((String)VOConstants.badmmap.get(((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getJmbasxdm()));
        dzyj.setYwczlx(jmbavo.getYwczlx());
            dzyj.setYwdm(jmbavo.getYwlx());
            // 取得登记数据
            Map djMap = (Map) FriendHelper.getDjInfo(request);
            SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

            Timestamp now = new Timestamp(System.currentTimeMillis());
            voPackage.setUserData(ud);
            HashMap hm = new HashMap();

            hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, jmbavo);
            voPackage.setData(hm);
            voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
            voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
            if (saveType=="1"){//打印
            	voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
            }
    JmbaVO resvo = (JmbaVO) ShenbaoProxy.getInstance().process(voPackage);
    //签名原件暂不实现  20091227
           // dzyj = (DzyjsjVO) ((HashMap) voPackage.getData()).get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            // 如果是CA户则进行签名回写
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,((JmbaZbVO)resvo.getJmsbajl().get(0)).getBasqwsh());
    //String basxdm="Print";

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
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
      //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案保存",
                    (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
            if (saveType=="1"){//打印
            	request.setAttribute("jmbavo",resvo);
            	return  CAConstants.SHOW+"Print";
            }else{//保存
              //basxdm="Lr"+((JmbaZbVO)resvo.getJmsbajl().get(0)).getJmbasxdm();
              
              return doEditZb(request,response);
            }

           // return  CAConstants.SAVE+basxdm;
        //return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
    }
    catch (Exception e)
    {
        // /3.9.设置空xml，转向失败页面
    	e.printStackTrace();
    	System.out.println("减免备案异常信息: ===== " + e.getMessage());
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, jmbavo.toXML());
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, jmbavo.getXsltVersion());
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, jmbavo.getSchemaVersion());
        

        throw ExceptionUtil.getBaseException(e);
    }
    }
    public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into delete action");
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


	    //System.out.println("basqwsh="+basqwsh+"   basxdm="+basxdm);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
        HashMap hm = new HashMap();
        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);
        //hm.put(VOConstants., bo);
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
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
    public String doRollback(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into rollback action");
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


	    //System.out.println("basqwsh="+basqwsh+"   basxdm="+basxdm);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
        HashMap hm = new HashMap();
        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

	    //System.out.println("basqwsh="+basqwsh);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_ROLLBACK);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // 调用后台操作，取得返回值
	     ShenbaoProxy.getInstance().process(voPackage);
         request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案撤回成功！");
         //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
               LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案撤回",
                       (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

	            return  doShow(request,response);
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


	    //System.out.println("basqwsh="+basqwsh+"   basxdm="+basxdm);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
        HashMap hm = new HashMap();
        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

	    //System.out.println("basqwsh="+basqwsh);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    // 设定vopackage参数
	    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // 调用后台操作，取得返回值
	     ShenbaoProxy.getInstance().process(voPackage);
         request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案提交成功！");
         //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
               LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案提交",
                       (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

	            return  doShow(request,response);
	}

}
