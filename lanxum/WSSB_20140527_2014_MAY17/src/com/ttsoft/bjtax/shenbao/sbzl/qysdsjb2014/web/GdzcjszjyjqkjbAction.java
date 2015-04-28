package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.web;

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

import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.QysdsUtil2012;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.GdzcjszjyjqkjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzssdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.Fzjgxx2014VO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.QysdsUtil2014;
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
/**
 * �̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�action
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-5 ����05:08:41
 */
public class GdzcjszjyjqkjbAction extends DcBaseAction
{

	public GdzcjszjyjqkjbAction()
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
     * ��ʼ���̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�����
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
      
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

        GdzcjszjyjqkjbBO qysdsjbbo = null;
        GdzcjszjyjqkjbVO qysdsjbvo = new GdzcjszjyjqkjbVO();
        QysdsUtil2014 qysdsUtil = new QysdsUtil2014();
        
        try {
            // ȡ�õǼǻ�������
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_GDZCJSZJYJQK_2014);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());

            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.GDZCJSZJYJQKJB_PROCESSOR_2014);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
            qysdsjbbo = (GdzcjszjyjqkjbBO)ShenbaoProxy.getInstance().process(vo);
            qysdsjbvo = qysdsUtil.gdzcxxGetDataFromAconvert2XMLVO(qysdsjbbo, djJbsj);
            
            //���ǰһ��ҳ������ƣ����ڷ��ز���ʱָ����Ӧ��λ��
            String jumpFlag=(String)request.getAttribute("jumpFlag");
            if(jumpFlag!=null && !jumpFlag.equals("")&&
            		qysdsjbvo.getJumpFlag().equals("")){
            	qysdsjbvo.setJumpFlag(jumpFlag);
            }
            String tmpxml = qysdsjbvo.toXML();
            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, Constant.CA_XSLTDM_GDZCJSZJYJQKJB_2014);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, Constant.CA_SCHEMADM_GDZCJSZJYJQKJB_2014);

            // ת����A���ȡ������
//            request.setAttribute("DataFromA", formatDataFromA(request));

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

    /**
     * ����̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�����
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
	public String doSave(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		System.out.println("..........doSave....start");
		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		GdzcjszjyjqkjbVO gdzcjbVO =new GdzcjszjyjqkjbVO();
		GdzcjszjyjqkjbBO gdzcjbBO =new GdzcjszjyjqkjbBO();
		System.out.println("..........doSave....zhangj\n"+xmldata);
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		QysdsUtil2014 qysdsUtil = new QysdsUtil2014();
		Map retmap = null;
		try {
			XMLParseHelper.parseXMLString(gdzcjbVO, xmldata,XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}
		if (ud.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
			} catch (ApplicationException e1) {
				throw ExceptionUtil.getBaseException(e1);
			}
		}	
		dzyj.setYwdm(gdzcjbVO.getYwlx());
		dzyj.setYwczlx(gdzcjbVO.getYwczlx());
		System.out.println("..............ywlx"+gdzcjbVO.getYwlx());
		System.out.println("..............Ywczlx"+gdzcjbVO.getYwczlx());
		
//		gdzcjbBO = qysdsUtil.Zfjgconvert2BO(gdzcjbVO);
		try {
			// ȡ��Form
			// ȡ�õǼǻ�������
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
			
			Map pData = new HashMap();
//		pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, gdzcjbBO);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_GDZCJSZJYJQK_2014);
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, gdzcjbVO);
			pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
			
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(ProcessorNames.GDZCJSZJYJQKJB_PROCESSOR_2014);
			vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
			vo.setUserData(ud);
			// ���ú�̨Procxy

			// ���û�ִ����ҳ��ĺ���������
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
                    "�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�" + CAUtils.getTsxx(gdzcjbVO.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                    "�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�" + CAUtils.getTsxx(gdzcjbVO.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, Constant.REQ_KEY_RETURN_CZZSSDS_QYJB2014);
			return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB); 
		} catch (Exception e) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, gdzcjbVO.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,gdzcjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,gdzcjbVO.getSchemaVersion());
			e.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�", gdzcjbVO.getSbxx().getSbrq(), "ʧ��!");
			throw ExceptionUtil.getBaseException(e);
		}

	}
    /**
     * ɾ���̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�����
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doDelete(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException
    {

		if (!isTokenValid(request)) {
			return CAConstants.INVALIDTOKEN;
		}
		
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		GdzcjszjyjqkjbVO gdzcjbVO =new GdzcjszjyjqkjbVO();
		GdzcjszjyjqkjbBO gdzcjbBO =new GdzcjszjyjqkjbBO();
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		QysdsUtil2014 qysdsUtil = new QysdsUtil2014();
		Map retmap = null;
		try {
			XMLParseHelper.parseXMLString(gdzcjbVO, xmldata,XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}
		if (ud.getCaflag()) {
			try {
				dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
			} catch (ApplicationException e1) {
				throw ExceptionUtil.getBaseException(e1);
			}
		}	
		dzyj.setYwdm(gdzcjbVO.getYwlx());
		dzyj.setYwczlx(gdzcjbVO.getYwczlx());
		
		
//		gdzcjbBO = qysdsUtil.Zfjgconvert2BO(gdzcjbVO);
		try {
			// ȡ��Form
			// ȡ�õǼǻ�������
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
			
			Map pData = new HashMap();
//		pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, gdzcjbBO);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, Constant.TABLE_ID_GDZCJSZJYJQK_2014);
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, gdzcjbVO);
			pData.put(QysdsksMapConstant.STRING_KEY_JDLX, qysdsUtil.getJbDM());
			
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(ProcessorNames.GDZCJSZJYJQKJB_PROCESSOR_2014);
			vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
			vo.setUserData(ud);
			// ���ú�̨Procxy

			// ���û�ִ����ҳ��ĺ���������
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);
            if(ud.getCaflag()) {
                dzyj = (DzyjsjVO)retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                ArrayList hzlist = new ArrayList();
                hzlist.add(Long.toString(dzyj.getLsh()));
                // request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
                //request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, hzlist);
            }
            else {
               // request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            }
			//return this.doShow(request, response);
			// ���û�ִ����ҳ��ĺ���������
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);
			request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                    "�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�" + CAUtils.getTsxx(gdzcjbVO.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                    "�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�" + CAUtils.getTsxx(gdzcjbVO.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, Constant.REQ_KEY_RETURN_CZZSSDS_QYJB2014);
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE, CAConstants.SUCCESSSB);
		} catch (Exception e) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, gdzcjbVO.toXML());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION,gdzcjbVO.getXsltVersion());
			request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION,gdzcjbVO.getSchemaVersion());
			e.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�", gdzcjbVO.getSbxx().getSbrq(), "ʧ��!");
			throw ExceptionUtil.getBaseException(e);
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
		String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
		GdzcjszjyjqkjbVO gdzcjbVO =new GdzcjszjyjqkjbVO();
		Map retmap = null;
		try {
			XMLParseHelper.parseXMLString(gdzcjbVO, xmldata,XMLParseHelper.VTDXML_PARSER, false, true);
		} catch (ApplicationException e) {
			throw ExceptionUtil.getBaseException(e);
		}
    	
    	String jumpFlag=gdzcjbVO.getJumpFlag();    	
    	System.out.println(".....jumpFlag: "+jumpFlag);
    	if(jumpFlag!=null && jumpFlag.equals("czzssdsjb2014")){
    		return "JumpCzzs";
    	}else{
    		return "JumpZfjg";
    	}
    }


    /**
     * �˳�����
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return String
     * @throws BaseException
     */
    public String doExit(HttpServletRequest request,
                           HttpServletResponse response) throws BaseException
    {
        return "Return";
    }
    
 
}
