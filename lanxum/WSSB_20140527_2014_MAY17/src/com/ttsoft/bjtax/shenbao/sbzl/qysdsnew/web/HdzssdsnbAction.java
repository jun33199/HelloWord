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
 * @version 1.0 �˶�������ҵ����˰�걨Action
 */
public class HdzssdsnbAction extends DcBaseAction {

    private String errorMessage = "";

    public HdzssdsnbAction() {
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
            // ȡ��Form
            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsndbo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.WENNB);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qynbvo);

            // ����VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNB_PROCESSOR);
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
            return
                    CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE,
                                   CAConstants.SUCCESSSB);
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

            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
            // ����form�е�����
            Map pData = new HashMap();
            // pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyjb);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.WENNB);
            pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
            // ����VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.HDZSQYSDSNB_PROCESSOR);
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
            return
                    CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE,
                                   CAConstants.SUCCESSSB);
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
        // �Ǽǻ�������
        SWDJJBSJ djJbsj = null;

        String rkbj = (String)request.getAttribute("RKBJ");

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

        HdzssdsBO qysdsnbbo = null;
        HdzssdsnbVO qysdsnbvo = new HdzssdsnbVO();
        if(rkbj!=null)
        {
            if(rkbj.equals("1"))
            {
                qysdsnbvo = (HdzssdsnbVO) request.getSession().getAttribute("NBVO");
                System.out.println("ActionShow === qynbvo��"+ qysdsnbvo.getSbsj().getSrze_ljs());

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
			//����Ƿ����Ե����أ�����ʱ��20090201-20090215
//			checkIsTestTaxpayer(request,response);
            // ȡ�õǼǻ�������
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
            // ���ú�̨��ѯ����
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
            // ȡ��Form
            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
            System.out.println("ActionFb === qynbvo��"+ qynbvo.getSbsj().getSrze_ljs());

            request.getSession().setAttribute("NBVO", qynbvo);



            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                 "��ҵ����˰�����˰�걨��" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                                 "��ҵ����˰�����˰�걨��" +
                                 CAUtils.getTsxx(qynbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                                 CAConstants.REQ_KEY_RETURN_HDZSSDS_QYNB);
//            LogUtil.getInstance().log(FriendHelper.getUserData(request),
//                                      "��ҵ����˰�����˰�걨���걨",
//                                      qynbvo.getSbxx().getSbrq(), "�ɹ�!");
//			return CAConstants.SUCCESSSB;
            return "Fb";
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
    
	/**����Ƿ����Ե����أ�����ʱ��20090201-20090215
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected void checkIsTestTaxpayer(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// ����Ƿ����Ե����أ�����ʱ��20090201-20090215

		

    	Date now = new Date();

        int fromDate = Integer.parseInt("20090201");
        int toDate = Integer.parseInt("20090215");
        
        int nowDate = Integer.parseInt((new SimpleDateFormat("yyyyMMdd")).format(now));

        if (nowDate <= toDate && nowDate >= fromDate)
        {
    		request.setAttribute("_REQ_KEY_TEST_ORG_USE", "1");
    		

    		// �Ǽǻ�������
    		SWDJJBSJ djJbsj = null;
    		
    		//�Ե����� ���� ����		
    		HashMap testOrg = new HashMap();
    		testOrg.put("03", "03");
    		testOrg.put("06", "06");
    		
    		// ȡ�õǼǻ�������
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
    			
    			testOrgMessage = "�𾴵���˰�ˣ�2008�����ҵ����˰��������걨ϵͳ��2009-2-1��2009-2-15�����У��˽׶�������걨���ݿ�����ϵͳ��ʽ���к���Ҫ�������ɴ˸��������Ĳ��㣬�����½⣡";
    			
    		}else{

        		request.setAttribute("_REQ_KEY_TEST_ORG_TEST", "0");

    			testOrgMessage = "�𾴵���˰�ˣ�2008�����ҵ����˰��������걨ϵͳĿǰ���������н׶Ρ��������Ƿ��Ե��û���ϵͳ��ʱ�޷����������걨���������ڹ�ע�����еط�˰��ֹٷ���վ��http://www.tax861.gov.cn�������ǽ���ʱ�������µ������Ϣ���ɴ˸��������Ĳ��㣬�����½⣡";
    			
    		}
    		
    		request.setAttribute("_REQ_KEY_TEST_ORG_MSG", testOrgMessage);
    		
        }

		
		
	}
    

}
