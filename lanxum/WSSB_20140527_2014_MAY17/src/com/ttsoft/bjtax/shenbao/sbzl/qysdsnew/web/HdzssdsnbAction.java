package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.QysdsUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.HdzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzssdsnbVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

/**
 * @version 1.0 核定征收企业所得税年报Action
 */
public class HdzssdsnbAction extends DcBaseAction {

    private String errorMessage = "";

    public HdzssdsnbAction() {
    }

    protected boolean validate(HdzssdsnbVO qynb) {
        if (!qynb.getYwlx().equals(Constant.CA_YWLXDM_HDZSSDSNB)) {
            errorMessage = "业务类型错误，不能执行业务操作。";
            return false;
        }
        if (!(qynb.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || qynb
              .getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
            System.out.println("业务操作类型错误" + qynb.getYwczlx());
            errorMessage = "业务操作类型错误，不能执行业务操作。";
            return false;
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (!qynb.getSbxx().getSbrq().startsWith(df.format(now))) {
            errorMessage = "申报日期错误。";
            return false;
        }
        return true;
    }

    protected int getActionID() {
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.HDZSQYNB;
    }

    public String doSave(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException {

        if (!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("Save: xmldata === " + xmldata);
        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Map retmap = null;

        HdzssdsnbVO qynbvo = new HdzssdsnbVO();
        QysdsUtil qysdsUtil = new QysdsUtil();

        try {

            if (ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud
                                            .getSsdwdm());
                } catch (ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            System.out.println("save xml:" + xmldata);
            try {
                // XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
                // qyjb = new QysdsjbVO();
                XMLParseHelper.parseXMLString(qynbvo, xmldata,
                                              XMLParseHelper.VTDXML_PARSER, false, true);
            } catch (ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }

            HdzssdsBO qysdsndbo = qysdsUtil.Hdzsconvert2VO(qynbvo);
            dzyj.setYwdm(qynbvo.getYwlx());
            dzyj.setYwczlx(qynbvo.getYwczlx());
            // 取得Form
            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsndbo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.WENNB);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qynbvo);

            // 构造VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNB_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
            vo.setUserData(ud);
            // 调用后台Procxy

            // 设置回执下载页面的后续操作。
            retmap = (Map) ShenbaoProxy.getInstance().process(vo);
            if (ud.getCaflag()) {
                dzyj = (DzyjsjVO) retmap
                       .get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                ArrayList hzlist = new ArrayList();
                hzlist.add(Long.toString(dzyj.getLsh()));
                // request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
                request
                        .setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,
                                      hzlist);
            } else {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            }
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "企业所得税年度纳税申报表" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "企业所得税年度纳税申报表" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "企业所得税年度纳税申报表申报",
                                      qynbvo.getSbxx().getSbrq(), "成功!");
//			return CAConstants.SUCCESSSB;
            return
                    CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
                                   CAConstants.SUCCESSSB);
        } catch (Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qynbvo
                                 .toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 qynbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qynbvo.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "企业所得税年度纳税申报表申报",
                                      qynbvo.getSbxx().getSbrq(), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 删除亏损申报数据 1.取得QysdsksForm 2.调用form的toVo方法转换数据 3.构造VoPackage
     * 4.调用ShenBaoProxy的processor方法，传入VoPackage 5.利用ActionMapping转向
     *
     * @param mapping
     *            ActionMapping
     * @param form
     *            QysdsksForm
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public String doDelete(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException {
        // 检查token
        if (!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }

        // 得到xml
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        HdzssdsnbVO qyjb = new HdzssdsnbVO();
        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
        QysdsUtil qysdsUtil = new QysdsUtil();
        try {
            if (ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud
                                            .getSsdwdm());
                } catch (ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            System.out.println("delete xml:" + xmldata);

            try {
                // XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
                // qyjb = new QysdsjbVO();
                XMLParseHelper.parseXMLString(qyjb, xmldata,
                                              XMLParseHelper.VTDXML_PARSER, false, true);
            } catch (ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            dzyj.setYwczlx(qyjb.getYwczlx());
            dzyj.setYwdm(qyjb.getYwlx());

            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
            // 更新form中的数据
            Map pData = new HashMap();
            // pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyjb);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.WENNB);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            // 构造VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNB_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
            vo.setUserData(ud);
            // 调用后台Proxy
            ShenbaoProxy.getInstance().process(vo);

            request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "企业所得税年度纳税申报表" +
                                 CAUtils.getTsxx(qyjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "企业所得税年度纳税申报表删除", qyjb.getSbxx().getSbrq(),
                                      "成功!");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "企业所得税年度纳税申报表" +
                                 CAUtils.getTsxx(qyjb.getYwczlx()));
//			return CAConstants.SUCCESSSB;
            return
                    CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE,
                                   CAConstants.SUCCESSSB);
        } catch (Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qyjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qyjb
                                 .getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qyjb.getSchemaVersion());
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "删除企业所得税年度纳税申报表",
                                      (new SimpleDateFormat("yyyyMMdd")).format(
                    sbrq), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public String doShow(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException {
        // 登记基本数据
        SWDJJBSJ djJbsj = null;

        String rkbj = (String)request.getAttribute("RKBJ");

        // 构造VOPackage
        VOPackage vo = new VOPackage();
        Map pDataMap = new HashMap();
        UserData ud = (UserData) request.getSession(false).getAttribute(
                "UserData");
        if (ud == null) {
            System.out.println("session is null");
        }

        String jsjdm = ud.yhid;

        Timestamp curDate = new Timestamp(System.currentTimeMillis());

        HdzssdsBO qysdsnbbo = null;
        HdzssdsnbVO qysdsnbvo = new HdzssdsnbVO();
        if(rkbj!=null)
        {
            if(rkbj.equals("1"))
            {
                qysdsnbvo = (HdzssdsnbVO) request.getSession().getAttribute("NBVO");
                System.out.println("ActionShow === qynbvo："+ qysdsnbvo.getSbsj().getSrze_ljs());

                String tmpxml = qysdsnbvo.toXML();
                System.out.println("show xml:" + tmpxml);

                request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                     qysdsnbvo.getXsltVersion());
                request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                     qysdsnbvo.getSchemaVersion());
                request.setAttribute("RKBJ", "");
                return CAConstants.SHOW;
            }
        }

        QysdsUtil qysdsUtil = new QysdsUtil();
        try {
			//检查是否是试点区县，适用时间20090201-20090215
//			checkIsTestTaxpayer(request,response);
            // 取得登记基本数据
            djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.WENNB);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil
                         .getJbDM());
            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNB_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // 调用后台查询数据
            qysdsnbbo = (HdzssdsBO) ShenbaoProxy.getInstance().process(vo);

            qysdsnbvo = qysdsUtil.Hdzsconvert2XMLVO(qysdsnbbo, djJbsj,
                    Constant.WENNB);
            String tmpxml = qysdsnbvo.toXML();
            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qysdsnbvo.getSchemaVersion());

            // if (true) throw new Exception("test");

            // 转向企业所得税亏损申报页面
            return CAConstants.SHOW;
        } catch (Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsnbvo
                                 .toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qysdsnbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    protected String beforePerform(HttpServletRequest request,
                                   HttpServletResponse response) {
        // 检查权限
        if (!SbzlAccess.getAuthority(SbzlAccess.HDZSQYNB, request)) {
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    public String doReturn(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException {
        return "Return";
    }

    public String doFb(HttpServletRequest request,
                       HttpServletResponse response) throws BaseException {

//        if (!isTokenValid(request)) {
//            return CAConstants.INVALIDTOKEN;
//        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();

        HdzssdsnbVO qynbvo = new HdzssdsnbVO();

        try {

            if (ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud
                                            .getSsdwdm());
                } catch (ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            System.out.println("save xml:" + xmldata);
            try {
                // XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
                // qyjb = new QysdsjbVO();
                XMLParseHelper.parseXMLString(qynbvo, xmldata,
                                              XMLParseHelper.VTDXML_PARSER, false, true);
            } catch (ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }

            request.getSession().setAttribute("NBVO", qynbvo);
            String ynssde_bqs = qynbvo.getSbsj().getYnssde_bqs();
            if (ynssde_bqs == null || ynssde_bqs.trim().equals("")) {
                ynssde_bqs = "0.00";
            }
            Double ynssde = Double.valueOf(ynssde_bqs);
            double ynssde_dou = ynssde.doubleValue();

            String zxlx = "";
            if (ynssde_dou == 0 || ynssde_dou > 300000) {
                zxlx = "2";
            }
            request.setAttribute("zxlx",zxlx);
            dzyj.setYwdm(qynbvo.getYwlx());
            dzyj.setYwczlx(qynbvo.getYwczlx());
            // 取得Form
            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
            System.out.println("ActionFb === qynbvo："+ qynbvo.getSbsj().getSrze_ljs());

            request.getSession().setAttribute("NBVO", qynbvo);



            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "企业所得税年度纳税申报表" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "企业所得税年度纳税申报表" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB);
//            LogUtil.getInstance().log(FriendHelper.getUserData(request),
//                                      "企业所得税年度纳税申报表申报",
//                                      qynbvo.getSbxx().getSbrq(), "成功!");
//			return CAConstants.SUCCESSSB;
            return "Fb";
        } catch (Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qynbvo
                                 .toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 qynbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qynbvo.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "企业所得税年度纳税申报表申报",
                                      qynbvo.getSbxx().getSbrq(), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }

    }
    
	/**检查是否是试点区县，适用时间20090201-20090215
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void checkIsTestTaxpayer(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// 检查是否是试点区县，适用时间20090201-20090215

		

    	Date now = new Date();

        int fromDate = Integer.parseInt("20090201");
        int toDate = Integer.parseInt("20090215");
        
        int nowDate = Integer.parseInt((new SimpleDateFormat("yyyyMMdd")).format(now));

        if (nowDate <= toDate && nowDate >= fromDate)
        {
    		request.setAttribute("_REQ_KEY_TEST_ORG_USE", "1");
    		

    		// 登记基本数据
    		SWDJJBSJ djJbsj = null;
    		
    		//试点区县 崇文 海淀		
    		HashMap testOrg = new HashMap();
    		testOrg.put("03", "03");
    		testOrg.put("06", "06");
    		
    		// 取得登记基本数据
    		djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
    		
    		String swjgzzjgdm = djJbsj.getSwjgzzjgdm();
    		
    		String swjgzzjgdmSub = swjgzzjgdm.substring(0,2);
    		
    		String testOrgMessage = "";
    		
    		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%\n:getQxdm:"+djJbsj.getQxdm() 
    				+ ":getSwjgzzjgdm:" +djJbsj.getSwjgzzjgdm()
    				+ ":swjgzzjgdm:" + swjgzzjgdm
    				+":swjgzzjgdmSub:" + swjgzzjgdmSub
    				+"\n");
    		
    		if(testOrg.containsKey(swjgzzjgdmSub)){

        		request.setAttribute("_REQ_KEY_TEST_ORG_TEST", "1");
    			
    			testOrgMessage = "尊敬的纳税人：2008年度企业所得税汇算清缴申报系统于2009-2-1至2009-2-15试运行，此阶段您填报的申报数据可能在系统正式运行后需要调整。由此给您带来的不便，请您谅解！";
    			
    		}else{

        		request.setAttribute("_REQ_KEY_TEST_ORG_TEST", "0");

    			testOrgMessage = "尊敬的纳税人：2008年度企业所得税汇算清缴申报系统目前处于试运行阶段。由于您是非试点用户，系统暂时无法受理您的申报。请您近期关注北京市地方税务局官方网站（http://www.tax861.gov.cn），我们将及时发布最新的相关信息。由此给您带来的不便，请您谅解！";
    			
    		}
    		
    		request.setAttribute("_REQ_KEY_TEST_ORG_MSG", testOrgMessage);
    		
        }

		
		
	}
    

}
