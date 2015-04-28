package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.ActionErrors;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Yh;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.SzsmSyjdUtil;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.zhsb.jm.JmUtil;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Sbsj02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Zhsb02VO;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * ������Э����ۺ��걨action
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class ZhsbWithSfxy02Action
    extends Zhsb02Action {
    public ZhsbWithSfxy02Action() {
    }

    //start modified code by qianchao 2006-2-13
//    protected int getActionID()
//    {
//        return SbzlAccess.ZHSBWITHSFXY;
//    }
    //end   modified code by qianchao 2006-2-13
    protected String beforePerform(HttpServletRequest request,
                                   HttpServletResponse response) {
        // ���Ȩ��
        if (!SbzlAccess.getAuthority(SbzlAccess.ZHSBWITHSFXY, request)) {
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    protected DeclareInfor setDeclareInforDetail(DeclareInfor declareInfor,
                                                 HttpServletRequest request) {
        Sbjkzb zb = declareInfor.getSbjkzb();
        zb.setClbjdm(CodeConstant.CLBJDM_DHK);
        declareInfor.setIsReturnPaymentInfo(true);
        /**
         * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� Start
         */
        //declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
        UserData ud = (UserData)this.getUserData(request);
        String yhdm = zb.getYhdm();
        /*
              if (yhdm == null) {
                // �û�û�еǼ�������Ϣ��һƱһ˰
                declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
              }
              else {
         Map yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
                Yh yh = (Yh) yhMap.get(yhdm);
         if (LWUtil.isLW(request.getSession().getServletContext(), ud.getSsdwdm(),
                                yh.getYhdm())) {
         */
        if (LWUtil.isLW(request.getSession().getServletContext(), ud.getSsdwdm(),
                        yhdm)) {
            // ����������������һƱ��˰
            super.log("����������������һƱ��˰");
            declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM);
        }
        else {
            super.log("����δ������һƱһ˰");
            // ����δ������һƱһ˰
            declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
        }
//      }

        /**
         * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� End
         */
        return declareInfor;
    }

    protected void setBankInfo(HttpServletRequest request,
                               ZhsbForm form) throws BaseException {

        // ��˰�ѻ������Э��
        Sfxy sfxy = FriendHelper.getYhkkSfxy(request);

        // ����������Ϣ
        form.setYhmc(sfxy.getYhmc());
        form.setJkyhzh(sfxy.getZh());
        form.setYhdm(sfxy.getYhdm());
        
        List yhsZqrlList = (List) FriendHelper.getZqrlInfor(request);
        request.getSession().setAttribute("YhsZqrlInfor",
        		yhsZqrlList);

    }

    protected BankInfo getPaymentBankInfo(HttpServletRequest request,
                                          ZhsbForm form) throws BaseException {
        Sfxy sfxy = FriendHelper.getYhkkSfxy(request);

        String selectedAccountNumber = form.getJkyhzh();
        String yhdm = form.getYhdm().trim();
        String yhmc = form.getYhmc().trim();
        Map djInfo = FriendHelper.getDjInfo(request);

        BankInfo bankInfo = new BankInfo();
        String tmpYhzh = null;
        String tmpYhdm = null;
        tmpYhdm = (sfxy.getYhdm()).trim();
        tmpYhzh = (sfxy.getZh()).trim();

        if (tmpYhdm.equals(yhdm) && tmpYhzh.equals(selectedAccountNumber)) {
            bankInfo.accountNumber = selectedAccountNumber;
            bankInfo.bankID = yhdm;
            bankInfo.bankName = yhmc;
            // System.out.println("ƥ��ɹ�");
            tmpYhzh = null;
            tmpYhdm = null;
            return bankInfo;
        }
        else {
            tmpYhzh = null;
            tmpYhdm = null;
            throw ExceptionUtil.getBaseException(new Exception(
                "�û��ύ��������Ϣ������Э���е�������Ϣ��ͬ��"));
        }
    }

    /**
     * �����걨����
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     * @throws BaseException
     */
    public String doSave(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException {
    	
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        // 0.���token,��ֹ�ظ��ύ
        if (!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }
        System.out.println("xmldata============" + xmldata);
        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        log(xmldata);
        DzyjsjVO dzyj = new DzyjsjVO();

        // 1.�������
        String showMsg = "";
        List zcjkList = null;
        List sdjjList = null;
        boolean lwFlag = false;
        Map declareMap = new HashMap(3);
        // 2.��ʼ��
        ZhsbForm myForm = new ZhsbWithoutSfxyForm();
        SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request); // ��ʱ�޸�
        // 3.ҵ�����
        Zhsb02VO xvo = new Zhsb02VO();
       
        try {
        
            try {
                // XMLParseHelper.parseXMLString(xvo,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
                // xvo = new Zhsb02VO();
                XMLParseHelper.parseXMLString(xvo, xmldata,
                                              XMLParseHelper.VTDXML_PARSER, false, true);
            }
            catch (ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            // /3.0.��sessionȡ�ýɿ���Ϣ(���������ɿ����ݡ�����Ƿ˰�����������ju)
            zcjkList = (List) request.getSession().getAttribute(SessionKey.
                ZCJK_LIST);
            sdjjList = (List) request.getSession().getAttribute(SessionKey.
            	SDJJ_LIST);
            // ��֤����Ԫ��ǩ��
            if (ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (ApplicationException e1) {
                    showMsg = "��ǩ������jsjdm:" + ud.getYhid() + " time : " +
                        new Date();
                    showMsg += "\r\n-----ԭ�� start----------------\r\n";
                    showMsg += xmldata;
                    showMsg += "\r\n-----ԭ��  end ----------------\r\n";
                    showMsg += "\r\n-----ǩ�� start----------------\r\n";
                    showMsg += request.getParameter("strSignData");
                    showMsg += "\r\n-----ǩ�� end----------------\r\n";
                    showMsg += "\r\n-----֤�� start----------------\r\n";
                    showMsg += ud.getCert().getCert();
                    showMsg += "\r\n-----֤�� end----------------\r\n";
                    System.out.println(showMsg);
                    // e1.printStackTrace();
                    throw ExceptionUtil.getBaseException(e1);

                }
            }

            // /3.2.��ȡ��ǰʱ���ǩ
            Timestamp now = new Timestamp(System.currentTimeMillis());

            // /3.4.���ɽɿ�����
            // //3.4.0.�����ɿ���������
            if(zcjkList != null && zcjkList.size() >0)
            {
            	DeclareInfor declareInforZcjk = createDeclareInfor(zcjkList,
                        CodeConstant.SKLXDM_ZCJK, request, myForm,now, xvo);
            	System.out.println("LW declareInforZcjk mx size============" +
                		declareInforZcjk.getSbjkmxInfo().size());
                declareMap.put("0", declareInforZcjk);
                if (declareInforZcjk.getPrintTag() == CodeConstant.PRINT_YPDS_KM) 
                {
                    lwFlag = true;
                }
            }

            // //3.4.2.�Ĵ������������
            if(sdjjList != null && sdjjList.size() >0)
            {
            	DeclareInfor declareInforSdjj = createDeclareInfor(sdjjList,
                        CodeConstant.SKLXDM_SDJJ, request, myForm, now, xvo);
            	System.out.println("LW declareInforSdjj mx size============" +
                        declareInforSdjj.getSbjkmxInfo().size());
            	declareMap.put("2", declareInforSdjj);
            	if (declareInforSdjj.getPrintTag() == CodeConstant.PRINT_YPDS_KM) 
                {
                    lwFlag = true;
                }
            }
            // /3.5.ͨ��˰�ѽӿڻ�ȡ����Э��
            Sfxy sfxy = FriendHelper.getYhkkSfxy(request);
            String yhdm = null;
            Map yhMap = null;
            Yh yh = null;

            if (sfxy != null) {
                yhdm = sfxy.getYhdm();
                yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
                yh = (Yh) yhMap.get(yhdm);
            }
            // /3.6.���ú�̨ҵ����д���
            // //3.6.0.����ֵ����
            dzyj.setYwczlx(xvo.getYwczlx());
            dzyj.setYwdm(CAcodeConstants.YWDM_SB_WS_ZHSB);

            HashMap dataMap = new HashMap();
            dataMap.put("sfxy", sfxy); // ����Э��
            dataMap.put("declareMap", declareMap); // �걨��Ϣ
            dataMap.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj); // ǩ��ԭ��
            dataMap.put("yh", yh); // ���ж���
            dataMap.put("jbsj", jbsj); // �Ǽ�����
            dataMap.put("lwFlag", new Boolean(lwFlag));
            
            
            
          //add by wangxq 20140608 start
            StringBuffer buf=new StringBuffer();
            Map  mapDeclareInfor = (Map) dataMap.get("declareMap");
            Set set = mapDeclareInfor.keySet();
            String key = null;
            DeclareInfor declareInfor = null;
           
            Sbjkzb sbjkzb;
            List sbjkmxInfo;
            for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
            	 key = (String) keyset.next();
                 // //2.2.0.��ȡֵ����
                 declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
                 if (declareInfor.getSbjkmxInfo() == null ||
                     declareInfor.getSbjkmxInfo().size() < 1
                     || declareInfor.getSbjkzb() == null) {
                     throw ExceptionUtil.getBaseException(new Exception(),
                         "�걨������Ϊ��!");
                 }
                 else {
                     sbjkzb = declareInfor.getSbjkzb();
                     sbjkmxInfo = declareInfor.getSbjkmxInfo();
                 }
                 

                
                try{
                 String tempStr=SzsmSyjdUtil.getSzsmcljeBySyjdlx(sbjkzb,sbjkmxInfo);
                 if(tempStr.length()>0){
                	 if(buf.length()>0){
                		   buf.append("<br>");
                	 }
                	 buf.append(tempStr); 
                 }
                }catch(Exception e){
                	 e.printStackTrace();
                	 throw ExceptionUtil.getBaseException(e, "�ж�˰��˰Ŀ����ͼ������ͳ���");
                }
            }
            if(buf.toString().length()>0){
            	
            	 request.getSession().setAttribute(SessionKey.ZCJK_LIST, zcjkList);
                 request.getSession().setAttribute(SessionKey.SDJJ_LIST, sdjjList);
                 myForm.setZcjkList(zcjkList);
                 myForm.setSdjjList(sdjjList);
                 setBankInfo(request, myForm);
                 Zhsb02VO xvo1 = new Zhsb02VO();
                 try {
                     xvo1 = convert2XMLVO(request, myForm, jbsj);
                 }
                 catch (Exception e1) {
                     e1.printStackTrace();
                     xvo1 = new Zhsb02VO();
                 }
                 String tmpxml = xvo1.toXML();
                 log("zhsbAction throw Exception xml:" + tmpxml);

                 request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
                 request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                      xvo1.getXsltVersion());
                 request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                      xvo1.getSchemaVersion());
                 
            	
            	//throw new ApplicationException(buf.toString());
            	 ActionErrors errors = new ActionErrors();
            	 errors.addError(buf.toString() );
            	 saveErrors(request, errors);
                 return  "Show";
            }
            //add by wangxq 20140608 end
            
            
            //�������˰��Ϣ
            StringBuffer msg = new StringBuffer();
            boolean esparatored = JmUtil.separator(declareInfor, ud,dataMap,msg);
           	 
            
           
            //����걨��Ϣȫ�����⣬�򱣴������Ϣ�������ؽɿ���չʾҳ
            if(declareInfor.getSbjkmxInfo().size()<=0 && esparatored==true)
            {
            	clearSession(request);
            	
            	//���������Ϣ
            	VOPackage vo = new VOPackage();
            	vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
                vo.setAction(ActionConstant.INT_ACTION_JM_DEAL);
                vo.setData(dataMap);
                vo.setUserData(ud);
            	
  	           Boolean result = (Boolean)ShenbaoProxy.getInstance().process(vo);
  	           if(result.booleanValue())
  	           {
  	        	 return AllJm(request,xvo,msg);
  	           }else{
  	        	   throw new Exception();
  	           }
            	
            }else{
            	 dataMap.put("JMXWQY", new Boolean(esparatored));
            }
            
            request.getSession().setAttribute("JM_SHOWMSG", msg.toString());
            request.getSession().setAttribute(SessionKey.DATA_MAP, dataMap);
            return "HK";
        }
        catch (Exception e) {
            e.printStackTrace();
            // /3.9.���·ŵ�session��,ʹ���汣���û��������Ϣ
            request.getSession().setAttribute(SessionKey.ZCJK_LIST, zcjkList);
            request.getSession().setAttribute(SessionKey.SDJJ_LIST, sdjjList);
            myForm.setZcjkList(zcjkList);
            myForm.setSdjjList(sdjjList);
            setBankInfo(request, myForm);
            Zhsb02VO xvo1 = new Zhsb02VO();
            try {
                xvo1 = convert2XMLVO(request, myForm, jbsj);
            }
            catch (Exception e1) {
                e1.printStackTrace();
                xvo1 = new Zhsb02VO();
            }
            String tmpxml = xvo1.toXML();
            log("zhsbAction throw Exception xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 xvo1.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 xvo1.getSchemaVersion());
            

              // /3.10.�����׳��쳣��ҳ��
             throw ExceptionUtil.getBaseException(e, "����ʧ�����뼼��֧����ϵ��");

            // try
            // {
            // request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xmldata);
            // request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
            // CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB).substring(0,
            // 8));
            // request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
            // CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB).substring(8));
            // }
            // catch (ApplicationException e1)
            // {
            // e1.printStackTrace();
            // }
            // // /3.10.�����׳��쳣��ҳ��
            // throw ExceptionUtil.getBaseException(e);
        }
    }

    private String AllJm(HttpServletRequest request ,Zhsb02VO xvo ,StringBuffer msg)
    {
    	UserData ud = (UserData) getUserData(request);
    	request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
        
        request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG, msg.toString());
        request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
                             CAConstants.REQ_KEY_RETURN_ZHSB_SAVE);
    	String tmpxml = new Zhsb02VO().toXML();
    	request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                             xvo.getXsltVersion());
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                             xvo.getSchemaVersion());

        request.getSession().setAttribute(SessionKey.CA_XML_DATA, tmpxml);
        request.getSession().setAttribute(SessionKey.CA_XML_XSLT_VERSION,
                                          xvo.getXsltVersion());
        request.getSession().setAttribute(SessionKey.CA_XML_SCHEMA_VERSION,
                                          xvo.getSchemaVersion());
    	request.getSession().removeAttribute(SessionKey.DATA_MAP);
    	return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
    }
    
    /**
     * �����걨
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            ActionForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActoinForward
     * @throws BaseException
     */
    public String doHK(HttpServletRequest request,
                       HttpServletResponse response) throws BaseException {
        // 0.���token,��ֹ�ظ��ύ
        if (!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }
     
        
        HttpSession session = request.getSession();
        UserData ud = (UserData)this.getUserData(request);
        String showMsg = "";
        String sklxdm = "";
        DzyjsjVO dzyj = new DzyjsjVO();
        Zhsb02VO xvo = new Zhsb02VO();
        Sbjkzb zb = new Sbjkzb();
        ZhsbForm myForm = new ZhsbWithoutSfxyForm();
        SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request); // ��ʱ�޸�
        try {
        	
        	 
            VOPackage vo = new VOPackage();
            HashMap dataMap = (HashMap) request.getSession().getAttribute(
                SessionKey.DATA_MAP);
            
            HashMap declareMap = (HashMap) dataMap.get("declareMap");
            DeclareInfor declareInforZcjk = (DeclareInfor) declareMap.get("0");
            DeclareInfor declareInforSdjj = (DeclareInfor) declareMap.get("2");
            
            if(declareMap.get("0") != null && !declareMap.get("0").equals(""))
            {
            	zb = declareInforZcjk.getSbjkzb();
            	sklxdm = CodeConstant.SKLXDM_ZCJK;
            }
            
            if(declareMap.get("2") != null && !declareMap.get("2").equals(""))
            {
            	zb = declareInforSdjj.getSbjkzb();
            	sklxdm = CodeConstant.SKLXDM_SDJJ;
            }
            
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_SAVE);
            vo.setData(dataMap);
            vo.setUserData(ud);
            HashMap reMap = null;
            // //3.6.1.����Processor

            
            // �쳣����ȫ��������catchʵ�֡�
            reMap = (HashMap) ShenbaoProxy.getInstance().process(vo);

            showMsg = (String) reMap.get("showMsg");
            if(dataMap.get("JMXWQY")!=null && dataMap.get("JMXWQY") instanceof Boolean && ((Boolean)dataMap.get("JMXWQY")).booleanValue()==true)
            {
               vo.setAction(ActionConstant.INT_ACTION_JM_DEAL);
               //vo.setData(dataMap);
 	           Boolean result = (Boolean)ShenbaoProxy.getInstance().process(vo);
 	           if(result.booleanValue())
 	           {
 	        	  showMsg+=("<br/>"+session.getAttribute("JM_SHOWMSG"));
 	           }
            }
            
            // //3.6.2.��ȡ������������
            Object reObject = reMap.get("reObject");
            
            Boolean sskk = (Boolean) reMap.get("sskk");

            // /3.7.�����ڴ����
            clearSession(request);
            session.setAttribute(SessionKey.JKS, reObject);

            // ���ɵĽɿ���ת��Ϊxml��ʽ����
            request.setAttribute("QYSBSuccess", showMsg);
            // /3.8.����
            // ���û�ִ����ҳ��ĺ���������
            if (ud.getCaflag()) {
                dzyj = (DzyjsjVO) reMap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,
                                     Long.toString(dzyj.getLsh()));
            }
            else {
                request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
            }
            request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG, showMsg);
            request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_ZHSB_SAVE);
            boolean lwFlag = false;

            if (declareInforZcjk.getPrintTag() == CodeConstant.PRINT_YPDS_KM) {
                lwFlag = true;
            }

            xvo = convert2XMLJksVO(request, (Map) reObject, jbsj, sskk, zb,
                                   lwFlag, sklxdm);
            if(xvo.getSbsj().size()>0){
	            LogUtil.getInstance().log(FriendHelper.getUserData(request), "�ۺ��걨",
	                                      ( (Sbsj02VO) xvo.getSbsj().get(0)).
	                                      getSbrq(), "�ɹ�!");
            }else{
            	//�����ڽɿ����ݣ�Ӫҵ˰3�򡢽ɿ���1Ԫ ���⣩
            	request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
            }
            String tmpxml = xvo.toXML();
            log(tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 xvo.getSchemaVersion());

            request.getSession().setAttribute(SessionKey.CA_XML_DATA, tmpxml);
            request.getSession().setAttribute(SessionKey.CA_XML_XSLT_VERSION,
                                              xvo.getXsltVersion());
            request.getSession().setAttribute(SessionKey.CA_XML_SCHEMA_VERSION,
                                              xvo.getSchemaVersion());

            session.removeAttribute(SessionKey.DATA_MAP);
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
                                  CAConstants.SUCCESSSB);

        }
        catch (Exception e) {
            e.printStackTrace();
            // /3.9.���·ŵ�session��,ʹ���汣���û��������Ϣ
            List zcjkList = (List) session.getAttribute(SessionKey.ZCJK_LIST);
            List sdjjList = (List) session.getAttribute(SessionKey.SDJJ_LIST);
            myForm.setZcjkList(zcjkList);
            myForm.setSdjjList(sdjjList);
            //System.out.println("ZhsbWithSfxy02Action 1:"+zcjkList.size()+"--"+sdjjList.size());
            setBankInfo(request, myForm);
            Zhsb02VO xvo1 = new Zhsb02VO();
            try {
                xvo1 = convert2XMLVO(request, myForm, jbsj);
            }
            catch (Exception e1) {
                e1.printStackTrace();
                xvo1 = new Zhsb02VO();
            }
            String tmpxml = xvo1.toXML();
            log("zhsbAction throw Exception xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 xvo1.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 xvo1.getSchemaVersion());
            // /3.10.�����׳��쳣��ҳ��
            throw ExceptionUtil.getBaseException(e,
                "��5���Ӻ󡾲鿴���ڽɿ��顿�˶��Ƿ��Ѿ��γɡ����ӽɿ�ר�ýɿ��顿������ʱ�˶������˻������������⣬���뼼��֧����ϵ��лл��");

            // try
            // {
            // request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xmldata);
            // request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
            // CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB).substring(0,
            // 8));
            // request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
            // CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_ZHSB).substring(8));
            // }
            // catch (ApplicationException e1)
            // {
            // e1.printStackTrace();
            // }
            // // /3.10.�����׳��쳣��ҳ��
            // throw ExceptionUtil.getBaseException(e);
        }
    }
    public String doTimeOut(HttpServletRequest request,
                            HttpServletResponse response) throws BaseException {
        ApplicationException e = new ApplicationException(
            "���з�����Ϣ���������ʵ�����˻���");
        throw ExceptionUtil.getBaseException(e);
    }
}
