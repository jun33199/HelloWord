package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.web;

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
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.web.action.DcBaseAction;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;

import javax.servlet.http.HttpServletResponse;

import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.exception.BaseException;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.framework.util.VOPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import com.ttsoft.common.model.UserData;
import java.util.HashMap;
import java.sql.Timestamp;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.QysdsNbConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.QysdsNbUtil2012;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.bo.CzzssdsNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.bo.ZfjgqysdsNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.xmlvo.CzzssdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2012.xmlvo.ZfjgqysdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.QysdsUtil2012;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2012.xmlvo.ZfjgqysdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;

public class ZfjgqysdsNbAction extends DcBaseAction
{

	public ZfjgqysdsNbAction()
    {
    }

    protected String beforePerform(HttpServletRequest request, HttpServletResponse response)
    {
        // ���Ȩ��
        if(!SbzlAccess.getAuthority(SbzlAccess.CZZSQYNB2012, request)) {
        	
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
    public String doQuery(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into ZfjgqysdsjbAction.doQuery!!!");
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

        ZfjgqysdsNbBO qysdsnbbo = null;
        ZfjgqysdsNbVO qysdsnbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2012 qysdsUtil = new QysdsNbUtil2012();

        try {
            // ȡ�õǼǻ�������
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant.TABLE_ID_ZFJGSDSNB_2012);
            pDataMap.put(QysdsNbConstant.STRING_KEY_NDLX, QysdsNbConstant.STRING_KEY_NDLX_VALUE);

            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSNB_2012_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
            qysdsnbbo = (ZfjgqysdsNbBO)ShenbaoProxy.getInstance().process(vo);

            //qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            CzzssdsNbVO czzsjbvo = (CzzssdsNbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSNB_2012");
            //HashMap nsfs_zfjg = (HashMap)request.getSession(false).getAttribute("REQ_KEY_NSFS_ZFJG_OLD");
            qysdsnbvo = qysdsUtil.zfjgxxGetDataFromAconvert2XMLVO(qysdsnbbo, djJbsj, czzsjbvo, "1");
            
            //System.out.println("zfjg: nsfs_old========="+nsfs_zfjg.get("nsfs_old"));
            //System.out.println("zfjg: zfjg_old========="+nsfs_zfjg.get("zfjg_old"));
            
            String tmpxml = qysdsnbvo.toXML();
            System.out.println("Query xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            request.setAttribute("maxrows", ((HashMap) qysdsnbbo.getSbsj()).get(QysdsNbConstant.ZFJGSDSNB_2012_MAX_ROW));

            // ת����A���ȡ������
            request.setAttribute("DataFromA", formatDataFromA(request));

            // ת����ҵ����˰�����걨ҳ��
            return CAConstants.QUERY;
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsnbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    private String formatDataFromA(HttpServletRequest request){
    	CzzssdsNbVO cvo = (CzzssdsNbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSNB_2012");
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
     * ������ҵ����˰�걨���������
     *
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doSave(HttpServletRequest request,HttpServletResponse response) throws BaseException
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

        ZfjgqysdsNbBO qysdsnbbo = null;
        ZfjgqysdsNbVO qysdsNbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2012 qysdsUtil = new QysdsNbUtil2012();

        try
        {
        	this.doSaveSbsjZb(request,response,qysdsNbvo);
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
                XMLParseHelper.parseXMLString(qysdsNbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
            }
            catch(ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            
            qysdsnbbo = qysdsUtil.Zfjgconvert2BO(qysdsNbvo);
            dzyj.setYwdm(qysdsNbvo.getYwlx());
            dzyj.setYwczlx(qysdsNbvo.getYwczlx());
            System.out.println("qmyj_ywdm = " + dzyj.getYwdm());
            System.out.println("qmyj_Ywczlx = " + dzyj.getYwczlx());

//            // ȡ��Form
            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
//
            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsnbbo);
            pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsNbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant.TABLE_ID_ZFJGSDSNB_2012);
            pData.put(QysdsNbConstant.STRING_KEY_NDLX, QysdsNbConstant.STRING_KEY_NDLX_VALUE);

            // ����VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSNB_2012_PROCESSOR);
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
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "��ҵ����˰��֧���������˰�걨�������" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"��ҵ����˰��֧���������˰�걨�������" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_CZZSSDS_QYJB2012);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),"��ҵ����˰��֧���������˰�걨�������", qysdsNbvo.getSbxx().getSbrq(), "�ɹ�!");
//			return CAConstants.SUCCESSSB;
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsNbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsNbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsNbvo.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰��֧���������˰�걨�������", qysdsNbvo.getSbxx().getSbrq(), "ʧ��!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
	/**
	 * �����걨��������
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public void doSaveSbsjZb(HttpServletRequest request,HttpServletResponse response,ZfjgqysdsNbVO qysdsNbvo) throws BaseException 
	{
		UserData ud = (UserData) this.getUserData(request);
		DzyjHelper dh = new DzyjHelper();
		DzyjsjVO dzyj = new DzyjsjVO();
		Map retmap = null;

		CzzssdsNbVO qynbvo = (CzzssdsNbVO)request.getSession(false).getAttribute("REQ_KEY_QYSBSNB_2012");
		QysdsNbUtil2012 qysdsUtil = new QysdsNbUtil2012();

		try {

			if (ud.getCaflag()) {
				try {
					dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
				} catch (ApplicationException e1) {
					throw ExceptionUtil.getBaseException(e1);
				}
			}
			

			CzzssdsNbBO qysdsndbo = qysdsUtil.Czzsconvert2VO(qynbvo);
			dzyj.setYwdm(qynbvo.getYwlx());
			dzyj.setYwczlx(qynbvo.getYwczlx());
			
			// ȡ��Form
			// ȡ�õǼǻ�������
			SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

			Map pData = new HashMap();
			pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsndbo);
			pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant.TABLE_ID_CZZSSDSNB_2012);
			pData.put(QysdsNbConstant.STRING_KEY_NDLX, QysdsNbConstant.STRING_KEY_NDLX_VALUE);
			pData.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
			pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qynbvo);
			pData.put("fpb_fzjgftse", qysdsNbvo.getZjgxx().getFzjgftse());
			//��ѯA�������˰�������ֻܷ���
			HashMap nsfs_zfjg = (HashMap)qysdsUtil.getNsfsAndZfjg(pData);
			if(("".equals(qysdsndbo.getNsfs_fsjg().get("nsfs"))) || qysdsndbo.getNsfs_fsjg().get("nsfs")==null){
				qysdsndbo.getNsfs_fsjg().put("nsfs", "0");
			}
			if(("".equals(qysdsndbo.getNsfs_fsjg().get("zfjg"))) || qysdsndbo.getNsfs_fsjg().get("zfjg")==null){
				qysdsndbo.getNsfs_fsjg().put("zfjg", "0");
			}
			if(qysdsndbo.getNsfs_fsjg().get("nsfs").equals("2")){
				qysdsndbo.getNsfs_fsjg().put("zfjg", "0");
			}
			nsfs_zfjg.put("nsfs_old", qysdsndbo.getNsfs_fsjg().get("nsfs"));
			nsfs_zfjg.put("zfjg_old", qysdsndbo.getNsfs_fsjg().get("zfjg"));
			

			// ����VoPackage
			VOPackage vo = new VOPackage();
			vo.setData(pData);
			vo.setProcessor(ProcessorNames.CZZSQYSDSNB_PROCESSOR_2012);
			vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
			vo.setUserData(ud);
			// ���ú�̨Procxy
			// ���û�ִ����ҳ��ĺ���������
			retmap = (Map) ShenbaoProxy.getInstance().process(vo);
			String nsfsOld = (String)nsfs_zfjg.get("nsfs_old");
			String zfjgOld = (String)nsfs_zfjg.get("zfjg_old");
			
			if(qysdsndbo.getNsfs().equals("1")){
				if(!zfjgOld.equals(qysdsndbo.getZfjg())){
					qysdsUtil.deleteCascadeZfjgData(pData);
				}
				qysdsUtil.saveCascadeZfjgData(pData);
			}else{
				qysdsUtil.deleteCascadeZfjgData(pData);
			}

			if (ud.getCaflag()) {
				dzyj = (DzyjsjVO) retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
				ArrayList hzlist = new ArrayList();
				hzlist.add(Long.toString(dzyj.getLsh()));
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,hzlist);
			} else {
				request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
			}
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"��ҵ����˰��֧���������˰�걨�������", qynbvo.getSbxx().getSbrq(), "�ɹ�!");
		} catch (Exception ex) {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
			ex.printStackTrace();
			LogUtil.getInstance().log(FriendHelper.getUserData(request),"��ҵ����˰��֧���������˰�걨�������", qynbvo.getSbxx().getSbrq(), "ʧ��!");
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
    public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
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

        ZfjgqysdsNbBO qysdsNbbo = null;
        ZfjgqysdsNbVO qysdsNbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2012 qysdsUtil = new QysdsNbUtil2012();

        try {
            if(ud.getCaflag()) {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch(ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            System.out.println("delete xml:" + xmldata);

            try {
                XMLParseHelper.parseXMLString(qysdsNbvo, xmldata, XMLParseHelper.VTDXML_PARSER, false, true);
            }
            catch(ApplicationException e) {
                throw ExceptionUtil.getBaseException(e);
            }
            dzyj.setYwczlx(qysdsNbvo.getYwczlx());
            dzyj.setYwdm(qysdsNbvo.getYwlx());

            // ȡ�õǼǻ�������
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
            // ����form�е�����
            qysdsNbbo = qysdsUtil.Zfjgconvert2BO(qysdsNbvo);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsNbbo);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qysdsNbvo);
            pData.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant.TABLE_ID_ZFJGSDSNB_2012);
            pData.put(QysdsNbConstant.STRING_KEY_NDLX, QysdsNbConstant.STRING_KEY_NDLX_VALUE);
            // ����VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.ZFJGQYSDSNB_2012_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
            vo.setUserData(ud);
            // ���ú�̨Proxy
            ShenbaoProxy.getInstance().process(vo);

            request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG, "��ҵ����˰��֧���������˰�걨�������" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS, CAConstants.REQ_KEY_RETURN_HDZSSDS_QYJB);
            LogUtil.getInstance().log(FriendHelper.getUserData(request),"��ҵ����˰��֧���������˰�걨�������", qysdsNbvo.getSbxx().getSbrq(), "�ɹ�!");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"��ҵ����˰��֧���������˰�걨�������" + CAUtils.getTsxx(qysdsNbvo.getYwczlx()));
            return CAUtils.jumpTo(ud.getCaflag(), CAConstants.DELETE, CAConstants.SUCCESSSB);
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsNbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsNbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsNbvo.getSchemaVersion());
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "ɾ����ҵ����˰������˰��֧���������",(new SimpleDateFormat("yyyyMMdd")).format(sbrq), "ʧ��!");
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

        // ת����ҵ����˰���������걨ҳ��
        return "Jump";
    }
    
    /**
     * ��ʼ����ҵ����˰�걨����  
     * ���걨��¼
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

        ZfjgqysdsNbBO qysdsnbbo = null;
        ZfjgqysdsNbVO qysdsnbvo = new ZfjgqysdsNbVO();
        QysdsNbUtil2012 qysdsUtil = new QysdsNbUtil2012();

        try {
            // ȡ�õǼǻ�������
            djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);

//            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
//            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
//            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
//            pDataMap.put(QysdsksMapConstant.STRING_KEY_BBLX, QysdsNbConstant.TABLE_ID_ZFJGSDSNB_2012);
//            pDataMap.put(QysdsNbConstant.STRING_KEY_NDLX, QysdsNbConstant.STRING_KEY_NDLX_VALUE);
//
//            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
//            vo.setProcessor(ProcessorNames.ZFJGQYSDSNB_2012_PROCESSOR);
//            vo.setData(pDataMap);
//            vo.setUserData(ud);
//            // ���ú�̨��ѯ����
//            qysdsnbbo = (ZfjgqysdsNbBO)ShenbaoProxy.getInstance().process(vo);

           
            CzzssdsNbVO czzsjbvo = (CzzssdsNbVO)request.getSession(false).getAttribute(QysdsNbConstant.REQ_KEY_QYSBSNB_2012);
            //HashMap nsfs_zfjg = (HashMap)request.getSession(false).getAttribute("REQ_KEY_NSFS_ZFJG_OLD");
            qysdsnbvo = qysdsUtil.CzzssdsNbVOGetDataFromAconvert2XMLVO(djJbsj, czzsjbvo);
            
            //System.out.println("zfjg: nsfs_old========="+nsfs_zfjg.get("nsfs_old"));
            //System.out.println("zfjg: zfjg_old========="+nsfs_zfjg.get("zfjg_old"));
            
            String tmpxml = qysdsnbvo.toXML();
            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            request.setAttribute("maxrows", String.valueOf(QysdsNbConstant.ZFJGSDSNB_2012_DEFAULT_MX_ROW_NUMBER));

            // ת����A���ȡ������
            request.setAttribute("DataFromA", formatDataFromA(request));

            // ת����ҵ����˰�����걨ҳ��
            return CAConstants.QUERY;
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsnbvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsnbvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsnbvo.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
}
