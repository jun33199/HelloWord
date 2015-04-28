package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.web;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 企业所得税季报08版</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import com.syax.common.web.action.DcBaseAction;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import javax.servlet.http.HttpServletResponse;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.exception.BaseException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.framework.util.VOPackage;
import java.util.Map;
import com.ttsoft.common.model.UserData;
import java.util.HashMap;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2008.QysdsUtil2008;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.frame.exception.ApplicationException;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ZfjgqysdsjbAction extends DcBaseAction
{
    public ZfjgqysdsjbAction()
    {
    }

    protected String beforePerform(HttpServletRequest request,
                                   HttpServletResponse response)
    {
        // 检查权限
        if(!SbzlAccess.getAuthority(SbzlAccess.CZZSQYJB2008, request)) {
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    /**
     * 初始化企业所得税申报数据
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doShow!!!");

        // 登记基本数据
        SWDJJBSJ djJbsj = null;
        // 构造VOPackage
        VOPackage vo = new VOPackage();
        Map pDataMap = new HashMap();
        UserData ud = (UserData)request.getSession(false).getAttribute("UserData");
        if(ud == null) {
            System.out.println("session is null");
        }

        String jsjdm = ud.yhid;

        Timestamp curDate = new Timestamp(System.currentTimeMillis());

        ZfjgqysdsjbBO qysdsjbbo = null;
        ZfjgqysdsjbVO qysdsjbvo = new ZfjgqysdsjbVO();
        QysdsUtil2008 qysdsUtil = new QysdsUtil2008();

        try {
            // 取得登记基本数据
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_ZFJGSDS_2008);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());

            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSJB_2008_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // 调用后台查询数据
            qysdsjbbo = (ZfjgqysdsjbBO)ShenbaoProxy.getInstance().process(vo);

            qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            String tmpxml = qysdsjbvo.toXML();

            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            request.setAttribute("maxrows", ((HashMap) qysdsjbbo.getSbsj()).get(Constant.ZFJGSDSJB_2008_MAX_ROW));

            // if (true) throw new Exception("test");

            // 转向企业所得税亏损申报页面
            return CAConstants.SHOW;
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 保存企业所得税申报数据
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doSave(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doSave!!!");
        if(!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("save xml:::\n" + xmldata);
        UserData ud = (UserData) this.getUserData(request);
        System.out.println("ud.getCaflag() = " + ud.getCaflag());
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Map retmap = null;

        ZfjgqysdsjbBO qysdsjbbo = null;
        ZfjgqysdsjbVO qysdsjbvo = new ZfjgqysdsjbVO();
        QysdsUtil2008 qysdsUtil = new QysdsUtil2008();

        try
        {
            if(ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch(ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            try
            {
//                // XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
//                // qyjb = new QysdsjbVO();
                XMLParseHelper.parseXMLString(qysdsjbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
            }
            catch(ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }

            qysdsjbbo = qysdsUtil.Zfjgconvert2BO(qysdsjbvo);
            dzyj.setYwdm(qysdsjbvo.getYwlx());
            dzyj.setYwczlx(qysdsjbvo.getYwczlx());
            System.out.println("qmyj_ywdm = " + dzyj.getYwdm());
            System.out.println("qmyj_Ywczlx = " + dzyj.getYwczlx());

//            // 取得Form
            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
//
            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjbbo);
            pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsjbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_ZFJGSDS_2008);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());

            // 构造VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSJB_2008_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
            vo.setUserData(ud);
            // 调用后台Procxy,设置回执下载页面的后续操作。
            retmap = (Map) ShenbaoProxy.getInstance().process(vo);
            if(ud.getCaflag()) {
                dzyj = (DzyjsjVO)retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                ArrayList hzlist = new ArrayList();
                hzlist.add(Long.toString(dzyj.getLsh()));
                // request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, hzlist);
            }
            else {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            }
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "企业所得税汇总纳税分支机构分配表" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "企业所得税汇总纳税分支机构分配表" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_CZZSSDS_QYJB2008);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "企业所得税汇总纳税分支机构分配表", qysdsjbvo.getSbxx().getSbrq(), "成功!");
//			return CAConstants.SUCCESSSB;
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税汇总纳税分支机构分配表", qysdsjbvo.getSbxx().getSbrq(), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 删除企业所得税申报数据
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doDelete(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException
    {
        // 检查token
        if(!isTokenValid(request))
        {
            return CAConstants.INVALIDTOKEN;
        }

        // 得到xml
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Timestamp sbrq = new Timestamp(System.currentTimeMillis());

        ZfjgqysdsjbBO qysdsjbbo = null;
        ZfjgqysdsjbVO qysdsjbvo = new ZfjgqysdsjbVO();
        QysdsUtil2008 qysdsUtil = new QysdsUtil2008();

        try {
            if(ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud
                                            .getSsdwdm());
                }
                catch(ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            System.out.println("delete xml:" + xmldata);

            try {
                // XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
                // qyjb = new QysdsjbVO();
                XMLParseHelper.parseXMLString(qysdsjbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false, true);
            }
            catch(ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            dzyj.setYwczlx(qysdsjbvo.getYwczlx());
            dzyj.setYwdm(qysdsjbvo.getYwlx());

            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
            // 更新form中的数据
            qysdsjbbo = qysdsUtil.Zfjgconvert2BO(qysdsjbvo);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjbbo);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsjbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_ZFJGSDS_2008);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            // 构造VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSJB_2008_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
            vo.setUserData(ud);
            // 调用后台Proxy
            ShenbaoProxy.getInstance().process(vo);

            request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "企业所得税汇总纳税分支机构分配表" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "企业所得税汇总纳税分支机构分配表", qysdsjbvo.getSbxx().getSbrq(), "成功!");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "企业所得税汇总纳税分支机构分配表" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
//			return CAConstants.SUCCESSSB;
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "删除企业所得税汇总纳税分支机构分配表",
                                      (new SimpleDateFormat("yyyyMMdd")).format(sbrq), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 返回操作
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     * @throws BaseException
     */
    public String doReturn(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException
    {
        return "Return";
    }

    /**
     * 返回查帐征收页面
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     * @throws BaseException
     */
    public String doJump(HttpServletRequest request,
            HttpServletResponse response) throws BaseException {
        System.out.println("i am jumping...");
//        // 得到xml
//        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());

        // 转向企业所得税查帐征收申报页面
        return "Jump";
    }

}
