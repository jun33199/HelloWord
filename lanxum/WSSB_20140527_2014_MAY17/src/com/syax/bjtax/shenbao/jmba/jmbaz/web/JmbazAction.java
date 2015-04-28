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
	   * �������Ȩ�޽��м��
	   */
	  protected String beforePerform(HttpServletRequest request,
	          HttpServletResponse response) {

        // ���Ȩ�� ������û�м������
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


		  //ȡ�ô����
		  List jmlx=CodeTableUtil.getCodeTableList(request, CodeTable.JMBA_BASX_LIST);
		  // ȡ��userdata
	      UserData userdata = (UserData) this.getUserData(request);
	    JmbazForm baform = (JmbazForm)request.getSession().getAttribute(JmbazForm.FORM_ATTRIBUTE_NAME);
	      JmbaVO vo  = new JmbaVO();
	    // ����VOPackage
	      VOPackage voPackage = new VOPackage();
	      // �趨vopackage����
	      voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	      voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
	      voPackage.setUserData(userdata);
	      voPackage.setData(baform);
	      Map djMap = (Map) FriendHelper.getDjInfo(request);
	      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

	      // ���ú�̨������ȡ�÷���ֵ
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
			      	//����У��
			      	CheckBean checkBean = new CheckBean();	
			      	checkBean.setJsjdm(userdata.getYhid());
			      	checkBean.createZgrqByCurrenttimeY();
			      	QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
			      } catch (Exception e) {
			      	  vo.setSfxz("N");
			      	//throw ExceptionUtil.getBaseException(e);
			  	}

		      /*----*/

	      //��������
	      String strXml = vo.toXML();
	      Debug.out(strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
	      request.getSession().setAttribute("LS", jmlx);

	      // ת����ʾҳ��
		  Debug.out("ת����ʾҳ��");
	      return CAConstants.SHOW;
	  }
	  public String doInit(HttpServletRequest request,
	          HttpServletResponse response) throws BaseException {


		  //ȡ�ô����
		  List jmlx=CodeTableUtil.getCodeTableList(request, CodeTable.JMBA_BASX_LIST);
		  
		  // ȡ��userdata
	      UserData userdata = (UserData) this.getUserData(request);
	      request.getSession().removeAttribute(JmbazForm.FORM_ATTRIBUTE_NAME);
	      JmbazForm baform = null;
	    //JmbazForm baform = (JmbazForm)request.getSession().getAttribute(JmbazForm.FORM_ATTRIBUTE_NAME);
	      JmbaVO vo  = new JmbaVO();
	      
	      
	    // ����VOPackage
	      VOPackage voPackage = new VOPackage();
	      // �趨vopackage����
	      voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	      voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
	      voPackage.setUserData(userdata);
	      voPackage.setData(baform);
	      Map djMap = (Map) FriendHelper.getDjInfo(request);
	      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

	      // ���ú�̨������ȡ�÷���ֵ
			      baform =new JmbazForm();
			      baform.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");
			      baform.setCheckZq("1");
			      if (checkZq()){
			      	baform.setCheckZq("0");
			      }
			      vo  = this.convertToXmlVO(null,userdata,jbsj);
			      /*--modified by huohb,2014-06-16--*/
			      try {
				      	//����У��
				      	CheckBean checkBean = new CheckBean();	
				      	checkBean.setJsjdm(userdata.getYhid());
				      	checkBean.createZgrqByCurrenttimeY();
				      	QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
				      } catch (Exception e) {
				      	  vo.setSfxz("N");
				      	//throw ExceptionUtil.getBaseException(e);
				  	}

			      /*----*/
	      //��������
	      String strXml = vo.toXML();
	      Debug.out(strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
	      request.getSession().setAttribute("LS", jmlx);

	      // ת����ʾҳ��
		  Debug.out("ת����ʾҳ��");
	      return CAConstants.SHOW;
	  }
	  public String doQuery(HttpServletRequest request,
	          HttpServletResponse response) throws BaseException {
		  // ȡ��userdata
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
	      // ����VOPackage
	      VOPackage voPackage = new VOPackage();
	      // �趨vopackage����
	      voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	      voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
	      voPackage.setUserData(userdata);
	      voPackage.setData(zb);

	      // ���ú�̨������ȡ�÷���ֵ
	      List res = (List) ShenbaoProxy.getInstance().process(
	              voPackage);

	      Map djMap = (Map) FriendHelper.getDjInfo(request);
	      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
	      JmbaVO vo  = this.convertToXmlVO(res,userdata,jbsj);
	      
	      try {
		      	//����У��
		      	CheckBean checkBean = new CheckBean();	
		      	checkBean.setJsjdm(userdata.getYhid());
		      	checkBean.createZgrqByCurrenttimeY();
		      	QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
		      } catch (Exception e) {
		      	  vo.setSfxz("N");
		      	//throw ExceptionUtil.getBaseException(e);
		  	}
	      //��������
	      String strXml = vo.toXML();
	      Debug.out(strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
	      request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

	      // ת����ʾҳ��
		  Debug.out("ת����ʾҳ��");
	      return CAConstants.SHOW;
		  //return null;
	  }

private JmbaVO convertToXmlVO(List zbl,UserData ud,SWDJJBSJ jbsj) {
	//1 ���ϲ�VO
      JmbaVO vo = new JmbaVO();
      //2 ��˰��VO 1.set 2
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
	vo.setSzmc("��ҵ����˰");
	vo.setBand((Integer.parseInt(DateUtilPro.getCurYearStr4())-1)+"");

	return vo;
}

public String doReturnEdit(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
 	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbaVO vo  = new JmbaVO();
    String jmbasxdm=request.getParameter("jmbasxdm");
    return  CAConstants.SAVE+"Lr"+jmbasxdm;

}
public String doReturnView(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
 	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbaVO vo  = new JmbaVO();
    String jmbasxdm=request.getParameter("jmbasxdm");
    return  CAConstants.SHOW+"Lr"+jmbasxdm;

}

public String doAdd(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	JmbaVO vo  = new JmbaVO();
 	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);
    try {
    	//����У��
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
    // ���ú�̨������ȡ�÷���ֵ
//    List l= new ArrayList();
    JmbaZbVO zbvo=getZbvo();
    zbvo.setJmbasxdm(jmbasxdm);
    //l.add(zbvo);

    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
    voPackage.setUserData(userdata);
    voPackage.setData(zbvo);

    // ���ú�̨������ȡ�÷���ֵ
    JmbamxBo bamxbo= (JmbamxBo) ShenbaoProxy.getInstance().process(
            voPackage);
    
    //JmbamxBo bamxbo=null;//????
    
//    vo  = this.convertToXmlVO(l,userdata,jbsj);
//	vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
//	
//    //��������
//    String strXml = vo.toXML();
//    Debug.out(strXml);
//�����ã���������
    //request.getSession().setAttribute("LS", res);
    
    //request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    //request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    //request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
	  if ("1".equals(bamxbo.getZtdm())){//�ظ�
	  	request.setAttribute(JmbazForm.MESSAGE_ATTRIBUTE_NAME,"�˱��������Ѿ�������������ظ����档");
	  	  return doShow(request,response);
	  }
	  if ("2".equals(bamxbo.getZtdm())){//
	  	request.setAttribute(JmbazForm.MESSAGE_ATTRIBUTE_NAME,"ϵͳ�����������Ա��ϵ��");
	  	return  doShow(request,response);
	  	
	  }
	  request.getSession().setAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME,bamxbo);
	   
	      return  CAConstants.SAVE+"Lr"+jmbasxdm;

   
}
//�޸���ϸ
public String doEdit(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);

    String basqwsh=request.getParameter("basqwsh");
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // ���ú�̨������ȡ�÷���ֵ
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
  //��������

    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
	  request.getSession().setAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME,bamxbo);
	   
	      return  CAConstants.SAVE+"Lr"+zbvo.getJmbasxdm();
}

//��ϸ���������������
public String doEditZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);

    String basqwsh=bo.getBasqwsh();
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // ���ú�̨������ȡ�÷���ֵ
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
	
	
  //��������
    String strXml = vo.toXML();
    Debug.out(strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
    return CAConstants.EDITSHOW;
}

/**
 * ���ݲ�ͬ�ļ��ⱸ���������ͱ������ ��ȡ����ʹ�����
 * ���òм�����֧���Ĺ��ʱ�������    0030
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
		//�������ɲ��ã������걨������С��2012��
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
	 * @description: ������ҵ����˰��2011.08.15Ҫ�󣬽������嵥�С���˰��˰�������޸�Ϊ��
	 *  			 ������˰������������ʾΪ��������XXX���ط־ֵط�˰��ּ�˰��˰������
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
	 * ���ݲ�ͬ�ļ��ⱸ���������ͱ������ ��ȡ����ʹ�����
	 */
	String synd="2012";
	synd=getSynd(bo);
			
	//������˰���������ضԱ����嵥���д���
	Map clzlqddm01Map = baMap;
	//clzlqddm01Map.get()
	System.out.println(zbvo.getJmbasxdm()+"  zlqdmapSize="+baMap.size());
	if (l==null || l.size()==0){//����
		l=new ArrayList();
		//����07���⴦��
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
						qo.setZlqd("�����ж������ط�˰��ּ�˰��˰������");
					}
					else if("02".equals(qxdm)||qxdm.equals("02")){
						qo.setZlqd("�������������ط�˰��ּ�˰��˰������");
					}
					else if("03".equals(qxdm)||qxdm.equals("03")){
						qo.setZlqd("�����г������ط�˰��ּ�˰��˰������");
					}
					else if("04".equals(qxdm)||qxdm.equals("04")){
						qo.setZlqd("�������������ط�˰��ּ�˰��˰������");
					}
					else if("05".equals(qxdm)||qxdm.equals("05")){
						qo.setZlqd("�����г������ط�˰��ּ�˰��˰������");
					}
					else if("06".equals(qxdm)||qxdm.equals("06")){
						qo.setZlqd("�����к������ط�˰��ּ�˰��˰������");
					}
					else if("07".equals(qxdm)||qxdm.equals("07")){
						qo.setZlqd("�����з�̨���ط�˰��ּ�˰��˰������");
					}
					else if("08".equals(qxdm)||qxdm.equals("08")){
						qo.setZlqd("������ʯ��ɽ���ط�˰��ּ�˰��˰������");
					}
					else if("09".equals(qxdm)||qxdm.equals("09")){
						qo.setZlqd("��������ͷ�����ط�˰��ּ�˰��˰������");
					}
					else if("10".equals(qxdm)||qxdm.equals("10")){
						qo.setZlqd("��������ɽ���ط�˰��ּ�˰��˰������");
					}
					else if("11".equals(qxdm)||qxdm.equals("11")){
						qo.setZlqd("�����в�ƽ���ط�˰��ּ�˰��˰������");
					}
					else if("12".equals(qxdm)||qxdm.equals("12")){
						qo.setZlqd("������ͨ�����ط�˰��ּ�˰��˰������");
					}
					else if("13".equals(qxdm)||qxdm.equals("13")){
						qo.setZlqd("������˳�����ط�˰��ּ�˰��˰������");
					}
					else if("14".equals(qxdm)||qxdm.equals("14")){
						qo.setZlqd("�����д������ط�˰��ּ�˰��˰������");
					}
					else if("15".equals(qxdm)||qxdm.equals("15")){
						qo.setZlqd("�����з�ɽ���ط�˰��ּ�˰��˰������");
					}
					else if("16".equals(qxdm)||qxdm.equals("16")){
						qo.setZlqd("�����л������ط�˰��ּ�˰��˰������");
					}
					else if("17".equals(qxdm)||qxdm.equals("17")){
						qo.setZlqd("�����������صط�˰��ּ�˰��˰������");
					}
					else if("18".equals(qxdm)||qxdm.equals("18")){
						qo.setZlqd("������ƽ�����ط�˰��ּ�˰��˰������");
					}
					else if("19".equals(qxdm)||qxdm.equals("19")){
						qo.setZlqd("�����������صط�˰��ּ�˰��˰������");
					}
					else if("20".equals(qxdm)||qxdm.equals("20")){
						qo.setZlqd("�����п������־ֵط�˰��ּ�˰��˰������");
					}
					else if("21".equals(qxdm)||qxdm.equals("21")){
						qo.setZlqd("��������վ�־ֵط�˰��ּ�˰��˰������");
					}
					else if("22".equals(qxdm)||qxdm.equals("22")){
						qo.setZlqd("����������־ֵط�˰��ּ�˰��˰������");
					}
					else if("25".equals(qxdm)||qxdm.equals("25")){
						qo.setZlqd("�����еڶ�����ּ�˰��˰������");
					}
					else if("41".equals(qxdm)||qxdm.equals("41")){
						qo.setZlqd("�����е�һ����ּ�˰��˰������");
					}
					else if("90".equals(qxdm)||qxdm.equals("90")){
						qo.setZlqd("�����еط�˰��ּ�˰��˰������");
					}
					else {
						qo.setZlqd("�����������ط�˰��ּ�˰��˰������");
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


//�����嵥�����������������Ӵ������ȡ
//�����ô���
private List getZlqd(JmbaZbVO zbvo,Map baMap,JmbamxBo bo){
	System.out.println("============getZlqd(, , , )=================");
	List l=zbvo.getBajlzlqd();
	System.out.println(zbvo.getJmbasxdm()+"  zlqdmapSize="+baMap.size());
	if (l==null || l.size()==0){//����
		l=new ArrayList();
		//����07���⴦��
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

//��ϸ���������������
public String doViewZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);
    JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);

    String basqwsh=bo.getBasqwsh();
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // ���ú�̨������ȡ�÷���ֵ
    List res = (List) ShenbaoProxy.getInstance().process(
            voPackage);

    Map djMap = (Map) FriendHelper.getDjInfo(request);
    SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
    JmbaVO vo  = this.convertToXmlVO(res,userdata,jbsj);
	vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);
	
  //��������
    String strXml = vo.toXML();
    Debug.out(strXml);   
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());

    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
	  return CAConstants.VIEWSHOW;
}

public String doView(HttpServletRequest request, HttpServletResponse response) throws BaseException
{
 	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);

    String basqwsh=request.getParameter("basqwsh");
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    // ����VOPackage
    VOPackage voPackage = new VOPackage();
    // �趨vopackage����
    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
    voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
    voPackage.setUserData(userdata);
    voPackage.setData(basqwsh);

    // ���ú�̨������ȡ�÷���ֵ
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

    // ת����ʾҳ��
	  Debug.out("ת����ʾҳ��");
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

//���沢��ӡsaveType=1
//���沢¼�뱸��������ϸsaveType=2
public String doViewDetail(HttpServletRequest request, HttpServletResponse response,String saveType) throws BaseException
{

	  // ȡ��userdata
    UserData userdata = (UserData) this.getUserData(request);
    String jmbasxdm=request.getParameter("jmbasxdm");
    String basqwsh=request.getParameter("basqwsh");
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
    String basxdm="Print";
    if (saveType=="1"){//��ӡ
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        // �趨vopackage����
        voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERYZB);
        voPackage.setUserData(userdata);
        voPackage.setData(basqwsh);

        // ���ú�̨������ȡ�÷���ֵ
        List res = (List) ShenbaoProxy.getInstance().process(
                voPackage);
        Map djMap = (Map) FriendHelper.getDjInfo(request);
        SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
        JmbaVO resvo  = this.convertToXmlVO(res,userdata,jbsj);
    	request.setAttribute("jmbavo",resvo);
    }else{//¼�뱸������
      basxdm="Lr"+jmbasxdm;
    }
      return  CAConstants.SHOW+basxdm;
}
//���沢��ӡsaveType=1
//���沢¼�뱸��������ϸsaveType=2
public String doSave(HttpServletRequest request, HttpServletResponse response,String saveType) throws BaseException
{

    if (!isTokenValid(request))
    {
        return CAConstants.INVALIDTOKEN;
    }

    String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
    // ����VOPackage
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
            // ȡ�õǼ�����
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
            if (saveType=="1"){//��ӡ
            	voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
            }
    JmbaVO resvo = (JmbaVO) ShenbaoProxy.getInstance().process(voPackage);
    //ǩ��ԭ���ݲ�ʵ��  20091227
           // dzyj = (DzyjsjVO) ((HashMap) voPackage.getData()).get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            // �����CA�������ǩ����д
    request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,((JmbaZbVO)resvo.getJmsbajl().get(0)).getBasqwsh());
    //String basxdm="Print";

            // ���û�ִ����ҳ��ĺ���������
            //ǩ��ԭ���ݲ�ʵ��  20091227
          /*  if (ud.getCaflag())
            {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, Long.toString(dzyj.getLsh()));
            }
            else
            {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            }
            */
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ������ɹ���");
      //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ������",
                    (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
            if (saveType=="1"){//��ӡ
            	request.setAttribute("jmbavo",resvo);
            	return  CAConstants.SHOW+"Print";
            }else{//����
              //basxdm="Lr"+((JmbaZbVO)resvo.getJmsbajl().get(0)).getJmbasxdm();
              
              return doEditZb(request,response);
            }

           // return  CAConstants.SAVE+basxdm;
        //return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
    }
    catch (Exception e)
    {
        // /3.9.���ÿ�xml��ת��ʧ��ҳ��
    	e.printStackTrace();
    	System.out.println("���ⱸ���쳣��Ϣ: ===== " + e.getMessage());
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


	    //System.out.println("basqwsh="+basqwsh+"   basxdm="+basxdm);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
        HashMap hm = new HashMap();
        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);
        //hm.put(VOConstants., bo);
	    // ����VOPackage
	    VOPackage voPackage = new VOPackage();
	    // �趨vopackage����
	    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
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
    public String doRollback(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	        System.out.println("into rollback action");
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


	    //System.out.println("basqwsh="+basqwsh+"   basxdm="+basxdm);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
        HashMap hm = new HashMap();
        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

	    //System.out.println("basqwsh="+basqwsh);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
	    // ����VOPackage
	    VOPackage voPackage = new VOPackage();
	    // �趨vopackage����
	    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_ROLLBACK);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // ���ú�̨������ȡ�÷���ֵ
	     ShenbaoProxy.getInstance().process(voPackage);
         request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ�����سɹ���");
         //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
               LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ������",
                       (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");

	            return  doShow(request,response);
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


	    //System.out.println("basqwsh="+basqwsh+"   basxdm="+basxdm);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
        HashMap hm = new HashMap();
        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        hm.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

	    //System.out.println("basqwsh="+basqwsh);
	    //request.setAttribute(JmbazForm.BASQWSH_ATTRIBUTE_NAME,basqwsh);
	    // ����VOPackage
	    VOPackage voPackage = new VOPackage();
	    // �趨vopackage����
	    voPackage.setProcessor(ProcessorNames.JMBAZ_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_COMMIT);
	    voPackage.setUserData(userdata);
	    voPackage.setData(hm);

	    // ���ú�̨������ȡ�÷���ֵ
	     ShenbaoProxy.getInstance().process(voPackage);
         request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ���ύ�ɹ���");
         //      request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_WSKSB_SAVE);
               LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ���ύ",
                       (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");

	            return  doShow(request,response);
	}

}
