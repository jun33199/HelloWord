package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.web;

/**
 * <p>Title: ������˰��������ϵͳ���������걨 -- ��ҵ����˰����08��</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
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
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.Fzjgxx2012VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.QysdsUtil2012;
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
        // ���Ȩ��
        if(!SbzlAccess.getAuthority(SbzlAccess.CZZSQYJB2008, request)) {
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    /**
     * ��ʼ����ҵ����˰�걨����
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doShow!!!");

        // �Ǽǻ�������
        SWDJJBSJ djJbsj = null;
        // ����VOPackage
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
        QysdsUtil2012 qysdsUtil = new QysdsUtil2012();

        try {
            // ȡ�õǼǻ�������
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_ZFJGSDS_2008);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());

            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSJB_2012_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
            qysdsjbbo = (ZfjgqysdsjbBO)ShenbaoProxy.getInstance().process(vo);

            //qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            CzzssdsjbVO czzsjbvo = (CzzssdsjbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSJ");
            //HashMap nsfs_zfjg = (HashMap)request.getSession(false).getAttribute("REQ_KEY_NSFS_ZFJG_OLD");
            qysdsjbvo = qysdsUtil.zfjgxxGetDataFromAconvert2XMLVO(qysdsjbbo, djJbsj, czzsjbvo, "1");
            
            //System.out.println("zfjg: nsfs_old========="+nsfs_zfjg.get("nsfs_old"));
            //System.out.println("zfjg: zfjg_old========="+nsfs_zfjg.get("zfjg_old"));
            
            String tmpxml = qysdsjbvo.toXML();
            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            request.setAttribute("maxrows", ((HashMap) qysdsjbbo.getSbsj()).get(Constant.ZFJGSDSJB_2008_MAX_ROW));

            // ת����A���ȡ������
            request.setAttribute("DataFromA", formatDataFromA(request));

            // ת����ҵ����˰�����걨ҳ��
            return CAConstants.SHOW;
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    private String formatDataFromA(HttpServletRequest request){
    	CzzssdsjbVO cvo = (CzzssdsjbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSJ");
    	StringBuffer sb = new StringBuffer();
    	sb.append("nsfs:"+cvo.getSbsj().getNsfs()+";")
    	.append("zfjg:"+cvo.getSbsj().getZfjg()+";")
    	.append("ynsdse:"+cvo.getSbsj().getYnsdse()+";")
    	.append("zjgyftsdse:"+cvo.getSbsj().getZjgyftsdse()+";")
    	.append("czjzfpsdse:"+cvo.getSbsj().getCzjzfpsdse()+";")
    	.append("fzjgyftsdse:"+cvo.getSbsj().getFzjgyftsdse()+";")
    	.append("fpbl:"+cvo.getSbsj().getFpbl()+";")
    	.append("fpsdse:"+cvo.getSbsj().getFpsdse()+";")
    	.append("nsrsbh:"+cvo.getNsrxx().getNsrsbh()+";")
    	.append("nsrmc:"+cvo.getNsrxx().getNsrmc());
    	return sb.toString();
    }

    /**
     * ������ҵ����˰�걨����
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
        QysdsUtil2012 qysdsUtil = new QysdsUtil2012();

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

//            // ȡ��Form
            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
//
            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjbbo);
            pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsjbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_ZFJGSDS_2008);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());

            // ����VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSJB_2012_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
            vo.setUserData(ud);
            // ���ú�̨Procxy,���û�ִ����ҳ��ĺ���������
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
                                 "��ҵ����˰������˰��֧���������" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "��ҵ����˰������˰��֧���������" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_CZZSSDS_QYJB2012);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "��ҵ����˰������˰��֧���������", qysdsjbvo.getSbxx().getSbrq(), "�ɹ�!");
//			return CAConstants.SUCCESSSB;
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰������˰��֧���������", qysdsjbvo.getSbxx().getSbrq(), "ʧ��!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ɾ����ҵ����˰�걨����
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doDelete(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException
    {
        // ���token
        if(!isTokenValid(request))
        {
            return CAConstants.INVALIDTOKEN;
        }

        // �õ�xml
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);

        UserData ud = (UserData)this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Timestamp sbrq = new Timestamp(System.currentTimeMillis());

        ZfjgqysdsjbBO qysdsjbbo = null;
        ZfjgqysdsjbVO qysdsjbvo = new ZfjgqysdsjbVO();
        QysdsUtil2012 qysdsUtil = new QysdsUtil2012();

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

            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
            // ����form�е�����
            qysdsjbbo = qysdsUtil.Zfjgconvert2BO(qysdsjbvo);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjbbo);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsjbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_ZFJGSDS_2008);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            // ����VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSJB_2012_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
            vo.setUserData(ud);
            // ���ú�̨Proxy
            ShenbaoProxy.getInstance().process(vo);

            request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "��ҵ����˰������˰��֧���������" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),
                                      "��ҵ����˰������˰��֧���������", qysdsjbvo.getSbxx().getSbrq(), "�ɹ�!");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "��ҵ����˰������˰��֧���������" + CAUtils.getTsxx(qysdsjbvo.getYwczlx()));
//			return CAConstants.SUCCESSSB;
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "ɾ����ҵ����˰������˰��֧���������",
                                      (new SimpleDateFormat("yyyyMMdd")).format(sbrq), "ʧ��!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ���ز���
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
     * ���ز�������ҳ��
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     * @throws BaseException
     */
    public String doJump(HttpServletRequest request,
            HttpServletResponse response) throws BaseException {
        System.out.println("i am jumping...");
//        // �õ�xml
//        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
//        request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());

        // ת����ҵ����˰���������걨ҳ��
        return "Jump";
    }
    
    /**
     * ��ʼ����ҵ����˰�걨����
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doReset(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doReset!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        // �Ǽǻ�������
        SWDJJBSJ djJbsj = null;
        // ����VOPackage
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
        QysdsUtil2012 qysdsUtil = new QysdsUtil2012();

        try {
            // ȡ�õǼǻ�������
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_ZFJGSDS_2008);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());

            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSJB_2012_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
            qysdsjbbo = (ZfjgqysdsjbBO)ShenbaoProxy.getInstance().process(vo);

            //qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            CzzssdsjbVO czzsjbvo = (CzzssdsjbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSJ");
            //HashMap nsfs_zfjg = (HashMap)request.getSession(false).getAttribute("REQ_KEY_NSFS_ZFJG_OLD");
            qysdsjbvo = qysdsUtil.zfjgxxGetDataFromAconvert2XMLVO(qysdsjbbo, djJbsj, czzsjbvo, "1");
            
            //System.out.println("zfjg: nsfs_old========="+nsfs_zfjg.get("nsfs_old"));
            //System.out.println("zfjg: zfjg_old========="+nsfs_zfjg.get("zfjg_old"));
            
            String tmpxml = qysdsjbvo.toXML();
            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            request.setAttribute("maxrows", ((HashMap) qysdsjbbo.getSbsj()).get(Constant.ZFJGSDSJB_2008_MAX_ROW));

            // ת����A���ȡ������
            request.setAttribute("DataFromA", formatDataFromA(request));

            // ת����ҵ����˰�����걨ҳ��
            return CAConstants.SHOW;
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}
