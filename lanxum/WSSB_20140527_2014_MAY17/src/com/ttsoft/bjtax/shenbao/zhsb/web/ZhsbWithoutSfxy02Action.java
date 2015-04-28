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


import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
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
 * 无三方协议的综合申报form
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class ZhsbWithoutSfxy02Action
    extends Zhsb02Action {
    public ZhsbWithoutSfxy02Action() {
    }

    protected String beforePerform(HttpServletRequest request,
                                   HttpServletResponse response) {
        // 检查权限
        if (!SbzlAccess.getAuthority(SbzlAccess.ZHSBWITHOUTSFXY, request)) {
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    //start modified code by qianchao 2006-2-13
//  protected int getActionID() {
//    return SbzlAccess.ZHSBWITHOUTSFXY;
//  }
    //end   modified code by qianchao 2006-2-13

    protected DeclareInfor setDeclareInforDetail(DeclareInfor declareInfor,
                                                 HttpServletRequest request) {
        declareInfor.setIsReturnPaymentInfo(true);
        UserData ud = (UserData)this.getUserData(request);

        Sbjkzb zb = declareInfor.getSbjkzb();
        zb.setClbjdm(CodeConstant.CLBJDM_YSB);
        String yhdm = zb.getYhdm();
        /*
            if (yhdm == null) {
              // 用户没有登记银行信息，一票一税
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
            super.log("银行已联网，一票多税");
            // 银行已联网，一票多税
            declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM);
        }
        else {
            super.log("银行未联网，一票一税");
            // 银行未联网，一票一税
            declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
        }
        /**
         * Modified by Ha Zhengze 20051008 20:59 为部分区县试运行进行一票多税的调整 End
         */
//    }

        return declareInfor;
    }

    protected void setBankInfo(HttpServletRequest request,
                               ZhsbForm form) throws
        BaseException {
        Map djInfo = FriendHelper.getDjInfo(request);
        List bankInfo = (List) djInfo.get("YHZH");

        //ZhsbWithoutSfxyForm myForm = (ZhsbWithoutSfxyForm) form;
        //myForm.setBankInfo(bankInfo);
        request.getSession().setAttribute("bankinfo",
                                          bankInfo);
        
        List yhsZqrlList = (List) FriendHelper.getZqrlInfor(request);
        request.getSession().setAttribute("YhsZqrlInfor",
        		yhsZqrlList);
    }

    protected BankInfo getPaymentBankInfo(HttpServletRequest request,
                                          ZhsbForm form) throws BaseException {
        //ZhsbWithoutSfxyForm myForm = (ZhsbWithoutSfxyForm) form;

        String selectedAccountNumber = form.getJkyhzh();
        String yhdm = form.getYhdm().trim();
        String yhmc = form.getYhmc().trim();
        Map djInfo = FriendHelper.getDjInfo(request);
        List bank = (List) djInfo.get("YHZH");

        BankInfo bankInfo = new BankInfo();
        String tmpYhzh = null;
        String tmpYhdm = null;
        if ("".equals(selectedAccountNumber) && "".equals(yhdm)) {
            return null;
        }
        for (int i = 0; i < bank.size(); i++) {
            YHZH yhzh = (YHZH) bank.get(i);
            tmpYhdm = (yhzh.getYhdm()).trim();
            tmpYhzh = (yhzh.getZh()).trim();
            if (tmpYhdm.equals(yhdm) && tmpYhzh.equals(selectedAccountNumber)) { //比对前台提交的信息是否与数据库中用户的银行信息一致
                bankInfo.accountNumber = selectedAccountNumber;
                bankInfo.bankID = yhdm;
                bankInfo.bankName = yhmc;
                tmpYhzh = null;
                tmpYhdm = null;
                return bankInfo;
            }
        }
        tmpYhzh = null;
        tmpYhdm = null;
        throw ExceptionUtil.getBaseException(new Exception("用户提交的非空银行信息系统无法识别！"));

    }

    /**
     * 进行申报
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
    public String doSave(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException {
        // 0.检查token,防止重复提交
//        if (!isTokenValid(request)) {
//            return CAConstants.INVALIDTOKEN;
//        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        log(xmldata);
        DzyjsjVO dzyj = new DzyjsjVO();

        // 1.句柄申明
        String showMsg = "";
        String sklxdm = "";
        List zcjkList = null;
        List sdjjList = null;
        boolean lwFlag = false;
        Sbjkzb zb = new Sbjkzb();
        Map declareMap = new HashMap(3);
        // 2.初始化
        ZhsbForm myForm = new ZhsbWithoutSfxyForm();
        SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request); // 临时修改
        // 3.业务过程

        Zhsb02VO xvo = new Zhsb02VO();

        try {
//        	 if(true){
//      	    	throw new ApplicationException("MMaaaabbbbb");
//      	       }
        	
            try {
                // XMLParseHelper.parseXMLString(xvo,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
                // xvo = new Zhsb02VO();
                XMLParseHelper.parseXMLString(xvo, xmldata,
                                              XMLParseHelper.VTDXML_PARSER, false, true);
            }
            catch (ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            // /3.0.从session取得缴款信息(包括正常缴款数据、补交欠税和三代解缴数ju)
            zcjkList = (List) request.getSession().getAttribute(SessionKey.ZCJK_LIST);
            sdjjList = (List) request.getSession().getAttribute(SessionKey.SDJJ_LIST);
            // 验证电子元件签名
            if (ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (ApplicationException e1) {
                    showMsg = "验签名错误。jsjdm:" + ud.getYhid() + " time : " +
                        new Date();
                    showMsg += "\r\n-----原文 start----------------\r\n";
                    showMsg += xmldata;
                    showMsg += "\r\n-----原文  end ----------------\r\n";
                    showMsg += "\r\n-----签名 start----------------\r\n";
                    showMsg += request.getParameter("strSignData");
                    showMsg += "\r\n-----签名 end----------------\r\n";
                    showMsg += "\r\n-----证书 start----------------\r\n";
                    showMsg += ud.getCert().getCert();
                    showMsg += "\r\n-----证书 end----------------\r\n";
                    System.out.println(showMsg);
                    // e1.printStackTrace();
                    throw ExceptionUtil.getBaseException(e1);

                }
            }

            // /3.2.获取当前时间标签
            Timestamp now = new Timestamp(System.currentTimeMillis());

            // /3.4.生成缴款数据
            // //3.4.0.正常缴款数据生成
            if(zcjkList != null && zcjkList.size() >0)
            {
            	DeclareInfor declareInforZcjk = createDeclareInfor(zcjkList,
                        CodeConstant.SKLXDM_ZCJK, request, myForm,now, xvo);
            	System.out.println("declareInforZcjk mx size============" +
                		declareInforZcjk.getSbjkmxInfo().size());
            	
                declareMap.put("0", declareInforZcjk);
                zb = declareInforZcjk.getSbjkzb();
                sklxdm = CodeConstant.SKLXDM_ZCJK;
                if (declareInforZcjk.getPrintTag() == CodeConstant.PRINT_YPDS_KM) 
                {
                    lwFlag = true;
                }
            }
            
            // //3.4.2.四代解缴数据生成
            if(sdjjList != null && sdjjList.size() >0)
            {
            	DeclareInfor declareInforSdjj = createDeclareInfor(sdjjList,
                        CodeConstant.SKLXDM_SDJJ, request, myForm, now, xvo);
            	System.out.println("declareInforSdjj mx size============" +
                        declareInforSdjj.getSbjkmxInfo().size());
            	
            	declareMap.put("2", declareInforSdjj);
            	zb = declareInforSdjj.getSbjkzb();
            	sklxdm = CodeConstant.SKLXDM_SDJJ;
            	if (declareInforSdjj.getPrintTag() == CodeConstant.PRINT_YPDS_KM) 
                {
                    lwFlag = true;
                }
            }

//            if(declareMap.get("0") != null && !declareMap.get("0").equals(""))
//            {
//            	System.out.println("1::::::"+createDeclareInfor(zcjkList, CodeConstant.SKLXDM_ZCJK, request, myForm,now, xvo));
//            }
//            if(declareMap.get("2") != null && !declareMap.get("2").equals(""))
//            {
//            	System.out.println("2::::::"+createDeclareInfor(sdjjList, CodeConstant.SKLXDM_SDJJ, request, myForm,now, xvo));
//            }
            
            // /3.5.通过税费接口获取三方协议
            Sfxy sfxy = FriendHelper.getYhkkSfxy(request);
            String yhdm = null;
            Map yhMap = null;
            Yh yh = null;

            if (sfxy != null) {
                yhdm = sfxy.getYhdm();
                yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
                yh = (Yh) yhMap.get(yhdm);
            }
            // /3.6.调用后台业务进行处理
            // //3.6.0.生成值对象
            dzyj.setYwczlx(xvo.getYwczlx());
            dzyj.setYwdm(CAcodeConstants.YWDM_SB_WS_ZHSB);

            VOPackage vo = new VOPackage();
            HashMap dataMap = new HashMap();
            dataMap.put("sfxy", sfxy); // 三方协议
            dataMap.put("declareMap", declareMap); // 申报信息
            dataMap.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj); // 签名原件
            dataMap.put("yh", yh); // 银行对象
            dataMap.put("jbsj", jbsj); // 登记数据
            dataMap.put("lwFlag", new Boolean(lwFlag));
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_SAVE);
            vo.setData(dataMap);
            vo.setUserData(ud);
            HashMap reMap = null;
            
          //add by wangxq 20140608 start
            Map  mapDeclareInfor = (Map) dataMap.get("declareMap");
            Set set = mapDeclareInfor.keySet();
            String key = null;
            DeclareInfor declareInfor = null;
            StringBuffer buf=new StringBuffer();
            Sbjkzb sbjkzb;
            List sbjkmxInfo;
            for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
            	 key = (String) keyset.next();
            	// System.out.println("key1111111111111"+key);
                 // //2.2.0.获取值对象
                 declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
                 if (declareInfor.getSbjkmxInfo() == null ||
                     declareInfor.getSbjkmxInfo().size() < 1
                     || declareInfor.getSbjkzb() == null) {
                     throw ExceptionUtil.getBaseException(new Exception(),
                         "申报的数据为空!");
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
                	 throw ExceptionUtil.getBaseException(e, "判断税种税目代码和鉴定类型出错！");
                }

                 
            }
            
            if(buf.toString().length()>0){
            	throw new ApplicationException("<br>"+buf.toString());
            }
            //add by wangxq 20140608 end
            
            
            /*---分离小微企业减免 add by lijn 20141017--*/
            StringBuffer sb = new StringBuffer();
            boolean esparatored = JmUtil.separator(declareInfor, ud ,dataMap,sb);
            showMsg += sb.toString();
            // //3.6.1.调用Processor
            // 异常处理全部由最后的catch实现。
            if(declareInfor.getSbjkmxInfo().size()>0){
            	reMap = (HashMap) ShenbaoProxy.getInstance().process(vo);
            	((Map)vo.getData()).put("SBBH", reMap.get("SBBH"));
            }
            
            /*-----------处理减免信息------------*/
            Boolean result=new Boolean(false);
            if(esparatored){   
	            vo.setAction(ActionConstant.INT_ACTION_JM_DEAL);
	            result = (Boolean)ShenbaoProxy.getInstance().process(vo);
            }
            
            Object reObject=null;
            // //3.6.2.获取并整理返回数据
            Boolean sskk =new Boolean(false);
            if(declareInfor.getSbjkmxInfo().size()>0){
            	reObject = reMap.get("reObject");
            	showMsg += (String) reMap.get("showMsg");
            	sskk = (Boolean) reMap.get("sskk");
            }
           
           
            
            // /3.7.清理内存对象
            clearSession(request);
            request.getSession().setAttribute(SessionKey.JKS, reObject);

            // 生成的缴款书转化为xml形式返回
            request.setAttribute("QYSBSuccess", showMsg);
            // /3.8.返回
            // 设置回执下载页面的后续操作。
            if (ud.getCaflag()) {
            	if(reMap!=null){
            		dzyj = (DzyjsjVO) reMap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            	}else{
            		dzyj = new DzyjHelper().saveDzyjsj(dzyj, "0", "0", "0", "0");
            	}
                request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY,
                                     Long.toString(dzyj.getLsh()));
            }
            else {
                request.setAttribute(CodeConstant.REQ_SHENBAO_HUIZHI_KEY, "");
            }
            request.setAttribute(CodeConstant.REQ_KEY_SUCCESS_MSG, showMsg);
            request.setAttribute(CodeConstant.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_ZHSB_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "综合申报",
                                      ( (Sbsj02VO) xvo.getSbsj().get(0)).
                                      getSbrq(), "成功!");

            xvo = convert2XMLJksVO(request, (Map) reObject, jbsj, sskk, zb,
                                   lwFlag, sklxdm);
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

            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
                                  CAConstants.SUCCESSSB);

        }
        catch (Exception e) {
            e.printStackTrace();
            // /3.9.重新放到session中,使界面保留用户填过的信息
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
            // /3.10.继续抛出异常到页面
            throw ExceptionUtil.getBaseException(e);

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
            // // /3.10.继续抛出异常到页面
            // throw ExceptionUtil.getBaseException(e);
        }
    }
}
