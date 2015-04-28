package com.syax.bjtax.shenbao.jmba.basx021.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbazForm;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.util.DmBeanUtils;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class Basx021Action  extends DcBaseAction 
{

	protected String beforePerform(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * 新增时跳转到此项
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
		
			 //获取session中的key为伤残人员装配用品 的值。
			//List scryzmypList = CodeTableUtil.getCodeTableList(request,CodeTable.JMBA_SCRYZMYP_LIST);
		
			//初始化 
			JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
			UserData userdata = (UserData) this.getUserData(request);
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			
			// 生成VOPackage传递传送到后台逻辑操作的数据
			VOPackage voPackage = new VOPackage();
			
			// 设定vopackage参数
			voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);		//主页的处理类
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
			voPackage.setUserData(userdata);	
			voPackage.setData(bo);

			// 调用后台操作，取得返回值
			JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
			zbvo = DmBeanUtils.completeDm(zbvo, CodeTableUtil.getCodeTableMap(request,CodeTable.JMBA_BASX_MAP));	//??

			//封装传输信息
			JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		
			// 构造xml类型数据
			String strXml = vo.toXML();
			Debug.out(strXml);
				
			//添加servlet信息
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
				
			return CAConstants.EDITSHOW;
		}
	
	/**
	 * 保存新增数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	    if (!isTokenValid(request))
	    {
	        return CAConstants.INVALIDTOKEN;
	    }
	    
	    JmbaVO vo = new JmbaVO();

	    // 取得userdata			
	   UserData userdata = (UserData) this.getUserData(request);
	   
//	// 验证电子元件签名
//		 DzyjsjVO dzyj =  new DzyjsjVO();
//	       DzyjHelper dh = new DzyjHelper();
//	       if (userdata.getCaflag())
//	       {
//	           try
//	           {
//	               dzyj = dh.verifyAndSign(request, userdata.getCert(), userdata.getSsdwdm());
//	           }
//	           catch (ApplicationException e)
//	           {
//	               throw ExceptionUtil.getBaseException(e);
//	           }
//	       }
//		   dzyj.setYwczlx(vo.getYwczlx());
//	      dzyj.setYwdm(vo.getYwlx());
	   
	   
	   //封装从前台页面获取的数据信息
       String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
       try
       {
           XMLParseHelper.parseXMLString(vo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
       }
       catch (ApplicationException e)
       {
           throw ExceptionUtil.getBaseException(e);
       }
       
       //用于承载业务逻辑数据的MAP
       HashMap map = new HashMap();
//     map.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
       map.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

	    
	   // 生成VOPackage
	   VOPackage voPackage = new VOPackage();
	   voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);
	   voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	   voPackage.setUserData(userdata);
	   voPackage.setData(map);

	   // 调用后台操作，取得返回值
	   ShenbaoProxy.getInstance().process(voPackage);
       request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
       
       //生成日志
       LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案保存",
                   (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");

	   return  doShow(request,response);
	}
	
	/**
	 * 生成备案表
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doEditZb(HttpServletRequest request, HttpServletResponse response) throws BaseException
	{
	    if (!isTokenValid(request))
	    {
	        return CAConstants.INVALIDTOKEN;
	    }
	     
	    //初始化信息
	    JmbaVO vo = new JmbaVO();
	    UserData userdata = (UserData) this.getUserData(request);
	    String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
	    try
	    {
	    	
	    	
	    	XMLParseHelper.parseXMLString(vo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
	    }
	    catch (ApplicationException e)
	    {
	    	throw ExceptionUtil.getBaseException(e);
	    }
	      
	    //用于承载业务逻辑数据的MAP
	    HashMap map = new HashMap();
	    map.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);
	       
	    // 生成VOPackage
	    VOPackage voPackage = new VOPackage();
	    voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(map);
		    
	    // 调用后台操作，取得返回值
	    ShenbaoProxy.getInstance().process(voPackage);
	    request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "企业所得税减免备案保存成功！");
	    
	    //生成日志
        LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税减免备案保存",
                   (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "成功!");
        
        return  CAConstants.EDITZB;
	}
	
	/**
	 * 入口：网上申报减免税备案录入主页：查看
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	 public String doView(HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		 //初始化信息
		 UserData userdata = (UserData) this.getUserData(request);											//userdate
		 Map djMap = (Map) FriendHelper.getDjInfo(request);
		 SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");														//基本数据
		 JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME); 	//用与操作的数据
		 
		 //生成VOPackage
		 VOPackage voPackage = new VOPackage();
		 voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);
		 voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
		 voPackage.setUserData(userdata);
		 voPackage.setData(bo);
		 
		 // 调用后台操作，取得返回值
		 JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
		 
		 //生成xml类型数据
		 JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		 String strXml = vo.toXML();
		 
		 //添加servlet信息
		 request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		 request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
		 request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
		 
		 return CAConstants.VIEWDETAIL;	   
	}
	
/*---------------------------辅助方法----------------------------------------------------------------*/
	
	/**
	 * 该方法将分布信息封装到jmbaVo中
	 * @param zb
	 * @param ud
	 * @param jbsj
	 * @return
	 */
	 private JmbaVO convertToXmlVO(JmbaZbVO zb,UserData ud,SWDJJBSJ jbsj) {
			
		 	  //定义信息存储vo
		      JmbaVO vo = new JmbaVO();
		      
		      //向vo中添加纳税人信息
		      NsrxxVO nsrxx = new NsrxxVO();
		      nsrxx.setJsjdm(jbsj.getJsjdm());
		      nsrxx.setNsrmc(jbsj.getNsrmc());
		      nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		      vo.setNsrxx(nsrxx);
		      
		      //向vo中添加主表的记录信息
		      List zbList =new ArrayList();
		      zbList.add(zb);
		      vo.setJmsbajl(zbList);
		      
		      //向vo中添加控制信息
		      vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);            	//业务操作类型
		      vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA21);				//业务类型
		      
		      //??
		      vo.setXsltVersion(VOConstants.CA_SCHEMA_JMBA_VERSION);
		      vo.setSchemaVersion(VOConstants.CA_XSLT_JMBA_VERSION);
		      
		      return vo;
		  }

	 
	 
	 
	 
//	 public static void main(String[] args) 
//	 
//	 {
//		 JmbaVO vo = new JmbaVO();
//		 
//		String xmldata1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion><sch"+
//"emaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030021]]></ywlx><ywczlx><![CDATA[2]]></ywczlx><nsrxx><jsjdm><![CDATA"+
//"[01002070]]></jsjdm><nsrmc><![CDATA[北京XXXX公司]]></nsrmc><swjgzzjgdm><![CDATA[0504]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CD"+
//"ATA[京地税东减免备企字[2013]000173]]></basqbh><basqwsh><![CDATA[012013000173]]></basqwsh><band><![CDATA[2013]]></band><jmbasxdm><!"+
//"[CDATA[0210]]></jmbasxdm><jmbasxmc><![CDATA[生产和装配伤残人员专门用品企业备案事项]]></jmbasxmc><ztdm><![CDATA[]]></ztdm><ztmc><!["+
//"CDATA[]]></ztmc><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><lrrq><![CDATA[2014-02-21]]></lrrq><qysdsjmba><xh><![CDATA[]]></"+
//"xh><mzqsnd><![CDATA[2012]]></mzqsnd></qysdsjmba></jmsbajl></taxdoc>";
//		String xmldata2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion>"+
//"<schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030001]]></ywlx><ywczlx><![CDATA[2]]></ywczlx><nsrxx><jsjdm><![C"+
//"DATA[01002070]]></jsjdm><nsrmc><![CDATA[北京XXXX公司]]></nsrmc><swjgzzjgdm><![CDATA[0504]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><"+
//"![CDATA[京地税东减免备企字[2013]000174]]></basqbh><basqwsh><![CDATA[012013000174]]></basqwsh><band><![CDATA[2013]]></band><jmbasxd"+
//"m><![CDATA[0010]]></jmbasxdm><jmbasxmc><![CDATA[资源综合利用企业（项目）申请减免企业所得税备案事项]]></jmbasxmc><ztdm><![CDATA[]]>"+
//"</ztdm><ztmc><![CDATA[]]></ztmc><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><lrrq><![CDATA[2014-02-21]]></lrrq><qysdsjmba><x"+
//"h><![CDATA[]]></xh><wjmc><![CDATA[1]]></wjmc><wh><![CDATA[1]]></wh><zyzhlyzldm><![CDATA[02]]></zyzhlyzldm><zsyxksrq><![CDATA[2014-"+
//"02-03]]></zsyxksrq><zsyxjzrq><![CDATA[2014-02-10]]></zsyxjzrq><qdsr><![CDATA[1.00]]></qdsr><jjsr><![CDATA[0.10]]></jjsr><zsbh><![C"+
//"DATA[1]]></zsbh><zyzhlyzlmc><![CDATA[废水（液）、废气、废渣]]></zyzhlyzlmc></qysdsjmba></jmsbajl></taxdoc>";
//	 
//		 try {
//			XMLParseHelper.parseXMLString(vo, xmldata1, XMLParseHelper.VTDXML_PARSER, false,true);
//			
//		} catch (ApplicationException e) {
//			 
//			e.printStackTrace(); 
//		}
//		System.out.println(vo);
//	 }

}
