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
	 * ����ʱ��ת������
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doShow(HttpServletRequest request,
			HttpServletResponse response) throws BaseException 
			{
		
			 //��ȡsession�е�keyΪ�˲���Աװ����Ʒ ��ֵ��
			//List scryzmypList = CodeTableUtil.getCodeTableList(request,CodeTable.JMBA_SCRYZMYP_LIST);
		
			//��ʼ�� 
			JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME);
			UserData userdata = (UserData) this.getUserData(request);
			Map djMap = (Map) FriendHelper.getDjInfo(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			
			// ����VOPackage���ݴ��͵���̨�߼�����������
			VOPackage voPackage = new VOPackage();
			
			// �趨vopackage����
			voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);		//��ҳ�Ĵ�����
			voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
			voPackage.setUserData(userdata);	
			voPackage.setData(bo);

			// ���ú�̨������ȡ�÷���ֵ
			JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
			zbvo = DmBeanUtils.completeDm(zbvo, CodeTableUtil.getCodeTableMap(request,CodeTable.JMBA_BASX_MAP));	//??

			//��װ������Ϣ
			JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		
			// ����xml��������
			String strXml = vo.toXML();
			Debug.out(strXml);
				
			//���servlet��Ϣ
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
				
			return CAConstants.EDITSHOW;
		}
	
	/**
	 * ������������
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

	    // ȡ��userdata			
	   UserData userdata = (UserData) this.getUserData(request);
	   
//	// ��֤����Ԫ��ǩ��
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
	   
	   
	   //��װ��ǰ̨ҳ���ȡ��������Ϣ
       String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
       try
       {
           XMLParseHelper.parseXMLString(vo, xmldata, XMLParseHelper.VTDXML_PARSER, false,true);
       }
       catch (ApplicationException e)
       {
           throw ExceptionUtil.getBaseException(e);
       }
       
       //���ڳ���ҵ���߼����ݵ�MAP
       HashMap map = new HashMap();
//     map.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
       map.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);

	    
	   // ����VOPackage
	   VOPackage voPackage = new VOPackage();
	   voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);
	   voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	   voPackage.setUserData(userdata);
	   voPackage.setData(map);

	   // ���ú�̨������ȡ�÷���ֵ
	   ShenbaoProxy.getInstance().process(voPackage);
       request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ������ɹ���");
       
       //������־
       LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ������",
                   (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");

	   return  doShow(request,response);
	}
	
	/**
	 * ���ɱ�����
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
	     
	    //��ʼ����Ϣ
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
	      
	    //���ڳ���ҵ���߼����ݵ�MAP
	    HashMap map = new HashMap();
	    map.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, vo);
	       
	    // ����VOPackage
	    VOPackage voPackage = new VOPackage();
	    voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);
	    voPackage.setAction(JmbaActionConstant.INTI_ACTION_SAVE);
	    voPackage.setUserData(userdata);
	    voPackage.setData(map);
		    
	    // ���ú�̨������ȡ�÷���ֵ
	    ShenbaoProxy.getInstance().process(voPackage);
	    request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰���ⱸ������ɹ���");
	    
	    //������־
        LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰���ⱸ������",
                   (new SimpleDateFormat("yyyyMMdd")).format(new java.util.Date()), "�ɹ�!");
        
        return  CAConstants.EDITZB;
	}
	
	/**
	 * ��ڣ������걨����˰����¼����ҳ���鿴
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	 public String doView(HttpServletRequest request, HttpServletResponse response) throws BaseException 
	{
		 //��ʼ����Ϣ
		 UserData userdata = (UserData) this.getUserData(request);											//userdate
		 Map djMap = (Map) FriendHelper.getDjInfo(request);
		 SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");														//��������
		 JmbamxBo bo =  (JmbamxBo)request.getSession().getAttribute(JmbazForm.JMBAMXBO_ATTRIBUTE_NAME); 	//�������������
		 
		 //����VOPackage
		 VOPackage voPackage = new VOPackage();
		 voPackage.setProcessor(ProcessorNames.BASX021_PROCESSOR);
		 voPackage.setAction(JmbaActionConstant.INTI_ACTION_QUERY);
		 voPackage.setUserData(userdata);
		 voPackage.setData(bo);
		 
		 // ���ú�̨������ȡ�÷���ֵ
		 JmbaZbVO zbvo = (JmbaZbVO) ShenbaoProxy.getInstance().process(voPackage);
		 
		 //����xml��������
		 JmbaVO vo  = this.convertToXmlVO(zbvo,userdata,jbsj);
		 String strXml = vo.toXML();
		 
		 //���servlet��Ϣ
		 request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
		 request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());
		 request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, vo.getSchemaVersion());
		 
		 return CAConstants.VIEWDETAIL;	   
	}
	
/*---------------------------��������----------------------------------------------------------------*/
	
	/**
	 * �÷������ֲ���Ϣ��װ��jmbaVo��
	 * @param zb
	 * @param ud
	 * @param jbsj
	 * @return
	 */
	 private JmbaVO convertToXmlVO(JmbaZbVO zb,UserData ud,SWDJJBSJ jbsj) {
			
		 	  //������Ϣ�洢vo
		      JmbaVO vo = new JmbaVO();
		      
		      //��vo�������˰����Ϣ
		      NsrxxVO nsrxx = new NsrxxVO();
		      nsrxx.setJsjdm(jbsj.getJsjdm());
		      nsrxx.setNsrmc(jbsj.getNsrmc());
		      nsrxx.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
		      vo.setNsrxx(nsrxx);
		      
		      //��vo���������ļ�¼��Ϣ
		      List zbList =new ArrayList();
		      zbList.add(zb);
		      vo.setJmsbajl(zbList);
		      
		      //��vo����ӿ�����Ϣ
		      vo.setYwczlx(CAcodeConstants.YWCZLX_MODIFY);            	//ҵ���������
		      vo.setYwlx(VOConstants.YWDM_QYSDSJMBA_BA21);				//ҵ������
		      
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
//"[01002070]]></jsjdm><nsrmc><![CDATA[����XXXX��˾]]></nsrmc><swjgzzjgdm><![CDATA[0504]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><![CD"+
//"ATA[����˰�����ⱸ����[2013]000173]]></basqbh><basqwsh><![CDATA[012013000173]]></basqwsh><band><![CDATA[2013]]></band><jmbasxdm><!"+
//"[CDATA[0210]]></jmbasxdm><jmbasxmc><![CDATA[������װ���˲���Աר����Ʒ��ҵ��������]]></jmbasxmc><ztdm><![CDATA[]]></ztdm><ztmc><!["+
//"CDATA[]]></ztmc><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><lrrq><![CDATA[2014-02-21]]></lrrq><qysdsjmba><xh><![CDATA[]]></"+
//"xh><mzqsnd><![CDATA[2012]]></mzqsnd></qysdsjmba></jmsbajl></taxdoc>";
//		String xmldata2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20100101]]></xsltVersion>"+
//"<schemaVersion><![CDATA[20100101]]></schemaVersion><ywlx><![CDATA[030001]]></ywlx><ywczlx><![CDATA[2]]></ywczlx><nsrxx><jsjdm><![C"+
//"DATA[01002070]]></jsjdm><nsrmc><![CDATA[����XXXX��˾]]></nsrmc><swjgzzjgdm><![CDATA[0504]]></swjgzzjgdm></nsrxx><jmsbajl><basqbh><"+
//"![CDATA[����˰�����ⱸ����[2013]000174]]></basqbh><basqwsh><![CDATA[012013000174]]></basqwsh><band><![CDATA[2013]]></band><jmbasxd"+
//"m><![CDATA[0010]]></jmbasxdm><jmbasxmc><![CDATA[��Դ�ۺ�������ҵ����Ŀ�����������ҵ����˰��������]]></jmbasxmc><ztdm><![CDATA[]]>"+
//"</ztdm><ztmc><![CDATA[]]></ztmc><szdm><![CDATA[]]></szdm><szmc><![CDATA[]]></szmc><lrrq><![CDATA[2014-02-21]]></lrrq><qysdsjmba><x"+
//"h><![CDATA[]]></xh><wjmc><![CDATA[1]]></wjmc><wh><![CDATA[1]]></wh><zyzhlyzldm><![CDATA[02]]></zyzhlyzldm><zsyxksrq><![CDATA[2014-"+
//"02-03]]></zsyxksrq><zsyxjzrq><![CDATA[2014-02-10]]></zsyxjzrq><qdsr><![CDATA[1.00]]></qdsr><jjsr><![CDATA[0.10]]></jjsr><zsbh><![C"+
//"DATA[1]]></zsbh><zyzhlyzlmc><![CDATA[��ˮ��Һ��������������]]></zyzhlyzlmc></qysdsjmba></jmsbajl></taxdoc>";
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
