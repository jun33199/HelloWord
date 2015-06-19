package com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.web;

import javax.servlet.http.*;

import com.syax.common.web.action.*;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzssdsnbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.QysdsUtil;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.syax.frame.exception.BaseException;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.HdzssdsBO;
import java.text.SimpleDateFormat;
import java.util.Map;
import com.syax.common.util.CAcodeConstants;
import java.util.HashMap;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.syax.frame.exception.ApplicationException;
import java.util.ArrayList;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.Constant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.HdzssdsFbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.HdzsnbFbVO;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class HdzssdsnbFbAction extends DcBaseAction {
    private String errorMessage = "";
    public HdzssdsnbFbAction() {
    }


    protected boolean validate(HdzssdsnbVO qynb) {
        if (!qynb.getYwlx().equals(Constant.CA_YWLXDM_HDZSSDSNB)) {
            errorMessage = "ҵ�����ʹ��󣬲���ִ��ҵ�������";
            return false;
        }
        if (!(qynb.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || qynb
              .getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY))) {
            System.out.println("ҵ��������ʹ���" + qynb.getYwczlx());
            errorMessage = "ҵ��������ʹ��󣬲���ִ��ҵ�������";
            return false;
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (!qynb.getSbxx().getSbrq().startsWith(df.format(now))) {
            errorMessage = "�걨���ڴ���";
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
        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Map retmap = null;

        HdzsnbFbVO qynbvo = new HdzsnbFbVO();
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

            HdzssdsFbBO qysdsndbo = qysdsUtil.HdzsFbconvert2VO(qynbvo);
            dzyj.setYwdm(qynbvo.getYwlx());
            dzyj.setYwczlx(qynbvo.getYwczlx());
            // ȡ��Form
            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsndbo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.NBFB);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qynbvo);

            // ����VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNBFB_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
            vo.setUserData(ud);
            // ���ú�̨Procxy

            // ���û�ִ����ҳ��ĺ���������
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
                                 "��ҵ����˰�����˰�걨��" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "��ҵ����˰�����˰�걨��" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "��ҵ����˰�����˰�걨���걨",
                                      qynbvo.getSbxx().getSbrq(), "�ɹ�!");
//			return CAConstants.SUCCESSSB;

            request.setAttribute("RKBJ", "1");

            ((HdzssdsnbVO) request.getSession().getAttribute("NBVO")).getSbsj().
                    setSjyyjdsdse_ljs(qynbvo.getSbsj().getJmshj_je());

            ((HdzssdsnbVO) request.getSession().getAttribute("NBVO")).getSbsj().
            setSjyyjdsdse_ljs(qynbvo.getSbsj().getJmshj_je());
            
            HdzssdsnbVO qynbvo1 = (HdzssdsnbVO)request.getSession().getAttribute("NBVO");
            qynbvo1.getSbsj().setXxwl_je(qynbvo.getSbsj().getXxwl_je());

            return "Save";
        } catch (Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qynbvo
                                 .toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 qynbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qynbvo.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "��ҵ����˰�����˰�걨���걨",
                                      qynbvo.getSbxx().getSbrq(), "ʧ��!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ɾ�������걨���� 1.ȡ��QysdsksForm 2.����form��toVo����ת������ 3.����VoPackage
     * 4.����ShenBaoProxy��processor����������VoPackage 5.����ActionMappingת��
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
        // ���token
        if (!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }

        // �õ�xml
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        HdzsnbFbVO qyjb = new HdzsnbFbVO();
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

            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
            // ����form�е�����
            Map pData = new HashMap();
            // pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyjb);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.NBFB);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            // ����VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNBFB_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
            vo.setUserData(ud);
            // ���ú�̨Proxy
            ShenbaoProxy.getInstance().process(vo);

            request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "��ҵ����˰�����˰�걨��" +
                                 CAUtils.getTsxx(qyjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "��ҵ����˰�����˰�걨��ɾ��", qyjb.getSbxx().getSbrq(),
                                      "�ɹ�!");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "��ҵ����˰�����˰�걨��" +
                                 CAUtils.getTsxx(qyjb.getYwczlx()));
//			return CAConstants.SUCCESSSB;
            request.setAttribute("RKBJ", "1");
            ((HdzssdsnbVO) request.getSession().getAttribute("NBVO")).getSbsj().
                    setSjyyjdsdse_ljs("0.00");
            return "Delete";
        } catch (Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qyjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qyjb
                                 .getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qyjb.getSchemaVersion());
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "ɾ����ҵ����˰�����˰�걨��",
                                      (new SimpleDateFormat("yyyyMMdd")).format(
                                              sbrq), "ʧ��!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public String doShow(HttpServletRequest request,
                         HttpServletResponse response) throws BaseException {

        String zxlx = (String) request.getAttribute("zxlx");
        // �Ǽǻ�������
        SWDJJBSJ djJbsj = null;
        // ����VOPackage
        VOPackage vo = new VOPackage();
        Map pDataMap = new HashMap();
        UserData ud = (UserData) request.getSession(false).getAttribute(
                "UserData");
        if (ud == null) {
            System.out.println("session is null");
        }

        String jsjdm = ud.yhid;

        Timestamp curDate = new Timestamp(System.currentTimeMillis());

        HdzssdsFbBO qysdsnbbo = null;
        HdzsnbFbVO qysdsnbvo = new HdzsnbFbVO();
        QysdsUtil qysdsUtil = new QysdsUtil();
        try {
            // ȡ�õǼǻ�������
            djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.NBFB);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil
                         .getJbDM());
            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNBFB_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
            qysdsnbbo = (HdzssdsFbBO) ShenbaoProxy.getInstance().process(vo);

            qysdsnbvo = qysdsUtil.HdzsFbconvert2XMLVO(qysdsnbbo, djJbsj,
                    Constant.NBFB);
            HdzssdsnbVO qynbvo1 = (HdzssdsnbVO)request.getSession().getAttribute("NBVO");

            qysdsnbvo.getSbsj().setZbYnsdse(qynbvo1.getSbsj().getYnssde_bqs());
            String tmpxml = qysdsnbvo.toXML();
            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,
                                 qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,
                                 qysdsnbvo.getSchemaVersion());
            request.setAttribute("zxlx",
                                 zxlx);

            // if (true) throw new Exception("test");

            // ת����ҵ����˰�����걨ҳ��
            return CAConstants.SHOW;
        } catch (Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
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
        // ���Ȩ��
        if (!SbzlAccess.getAuthority(SbzlAccess.HDZSQYNB, request)) {
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    public String doReturn(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException {
        request.setAttribute("RKBJ", "1");
        return "Return";
    }
}
