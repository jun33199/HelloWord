package com.ttsoft.bjtax.shenbao.sbzl.wqyys.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

//import org.apache.struts.action.ActionError;
//import org.apache.struts.action.ActionErrors;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.util.NumberUtils;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.syax.common.web.action.ActionErrors;
import com.syax.common.web.action.DcBaseAction;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysActionConstant;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import javax.servlet.http.HttpSession;
import com.ttsoft.bjtax.sfgl.common.model.Wqzsf;
import java.util.Date;
import java.util.Calendar;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo.Hdxx12VO;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo.Nsrxx12VO;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo.Sbsj12VO;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo.Sbxx12VO;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.xmlvo.Wqyys12VO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import java.util.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Hdxx02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Nsrxx02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Sbsj02VO;
import com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Zhsb02VO;

import java.sql.*;

import com.ttsoft.bjtax.dj.model.*;

/**
 * ����Ӫҵ˰�걨action��֧�ְ�ʵ�걨���˶����ա����ѻ������ַ�ʽ
 */
public class Wqyys12Action  extends DcBaseAction
{

    // ת��ĳ����Ķ���
    public static final String SELF = "Show";
    public static final String SUCCESS = "Success";
    public static final String WQYYS = "Wqyys";
    private String errorMessage = "";

    protected int getActionID()
    {
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.WQ;
    }

    protected boolean validate(Zhsb02VO vo)
    {
        if (!vo.getYwlx().equals(CAcodeConstants.YWDM_SB_WS_WQYYS_ASSB))
        {
            errorMessage = "ҵ�����ʹ��󣬲���ִ��ҵ�������";
            return false;
        }
        if (!(vo.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY) || vo.getYwczlx().equals(CAcodeConstants.YWCZLX_DELETE)))
        {
            System.out.println("ҵ��������ʹ���" + vo.getYwczlx());
            errorMessage = "ҵ��������ʹ��󣬲���ִ��ҵ�������";
            return false;
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return true;
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��ShenbaoRapidForm������һ��VOPackage��
     * 2������ShenbaoRapidForm��beforeQuery����ȡ��ҵ�����ݵĲ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊprocessor��ֵ��
     * 	voPackage��action��ΪShenbaoRapidActionConstant.INT_ACTION_QUERY����
     * 	ShenBaoProxy��process������
     * 4������ShenbaoRapidForm��afterQuery��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	WqyysForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public String doShow(
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        //��ȡForm����
        WqyysForm vicForm = null;
        Wqyys12VO xvo = new Wqyys12VO();

        try
        {
            HttpSession httpSession = request.getSession();

            // ���ô����form
            setCodeTable(request, vicForm);

            // ȡ��UserData
            UserData userData = (UserData) request.getSession(false).getAttribute("UserData");

            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);

            VOPackage voPackage = new VOPackage();
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_QUERY);

            Map map = new HashMap(3);
//��ѯ����
            Map sksjrqMap = Skssrq.monthSkssrq(new Date());
            Timestamp ksrq = (Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ);
            String skssksrq = TinyTools.Date2String(ksrq);

            map.put(WqyysMapConstant.JSJDM, jbsj.getJsjdm());
            map.put(WqyysMapConstant.SKSSKSRQ, skssksrq);
            map.put(WqyysMapConstant.DJJBSJ, jbsj);
            
            voPackage.setData(map);

            voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);
            Map data=(Map)voPackage.getData();
            // ���д�������ǰ̨����
            // ��������ģ��
            Wqyys wqyysTemplate = makeTemplate(jbsj.getJsjdm(), jbsj.getNsrmc(),
                                              jbsj.getSwjgzzjgdm());
            // ����ҵ������
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);
            for(int i = 0, size = wqyysList.size(); i < size; i++)
            {
                Wqyys wqyys = (Wqyys)wqyysList.get(i);
                // ������˰������
                wqyys.setNsrmc(jbsj.getNsrmc());
            }

            // �������ݵ�session��
            String wqzsfsdm = FriendHelper.getWqzsfsInfo(request).getWqzsfsdm();
            wqyysTemplate.setZsffdm(wqzsfsdm);
            
            httpSession.setAttribute(WqyysForm.SHENBAO, wqyysList);
            httpSession.setAttribute(WqyysForm.JBXX, wqyysTemplate);
            xvo = convert2XMLVO(request, wqyysList, Boolean.valueOf(true),wqyysTemplate );
            String tmpxml = xvo.toXML();
            //System.out.println("Wqyys ====================");
            System.out.println(tmpxml);

           request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
           request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
           request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());

            // ת�򵽱�ҳ��
            if(wqzsfsdm.equals(CodeConstant.WQYYS_HDZS))
            {
                //�˶�����
            	return this.WQYYS + CodeConstant.WQYYS_HDZS;
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_JFHS))
            {
                //���ѻ���
                return this.WQYYS + CodeConstant.WQYYS_JFHS;
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_ASSB))
            {
                //��ʵ�걨
                return this.WQYYS + CodeConstant.WQYYS_ASSB;
            }
            else
            {
                //û���ҵ����ʵ����շ�ʽ
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "δ֪������Ӫҵ˰���շ�ʽ��");
                return CAConstants.SUCCESS;

            }
        }
        catch(Exception e)
        {
            xvo.setSbxx(new Sbxx12VO());
            xvo.getSbxx().setDone(CAUtils.bool2Str(false));
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());
           
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
	 * @param request
	 * @param wqyysList
	 * @param jbsj
	 * @param boolean1
	 * @return
     * @throws com.syax.frame.exception.ApplicationException
	 */
	private Wqyys12VO convert2XMLVO(HttpServletRequest request, List wqyysList, Boolean done ,Wqyys wqyysTemplate) throws com.syax.frame.exception.ApplicationException {
		// TODO Auto-generated method stub
		Wqyys12VO vo=new Wqyys12VO();
        Sbsj12VO sbsj = new Sbsj12VO();
        Sbxx12VO sbxx = new Sbxx12VO();
        Nsrxx12VO nsrxx = new Nsrxx12VO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List sbsjlist=new ArrayList();
        Hdxx12VO hdxx = new Hdxx12VO();
        
        nsrxx.setJsjdm(wqyysTemplate.getJsjdm());
        nsrxx.setSwjgzzjgdm(wqyysTemplate.getSwjgzzjgdm());
        nsrxx.setNsrmc(wqyysTemplate.getNsrmc());
        hdxx.setZsffdm(wqyysTemplate.getZsffdm());
        
        sbxx.setDone(CAUtils.bool2Str(done.booleanValue()));
        sbxx.setSbrq(sdf.format(wqyysTemplate.getSbrq()));
        sbxx.setSkssksrq(sdf.format(wqyysTemplate.getSkssksrq()));
        sbxx.setSkssjsrq(sdf.format(wqyysTemplate.getSkssjsrq()));
		sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        vo.setHdxx(hdxx);
        
		for(int i = 0, size = wqyysList.size(); i < size; i++)
        {
            Wqyys wqyys = (Wqyys)wqyysList.get(i);
            sbsj = new Sbsj12VO();
            // ������˰������
            sbsj.setSzsmdm(wqyys.getSzsmdm());

            sbsj.setBqybse(StringUtils.bigDeciaml2String(wqyys.getBqybse()));
            sbsj.setHdsre(StringUtils.bigDeciaml2String(wqyys.getHdsre()));
            sbsj.setHssre(StringUtils.bigDeciaml2String(wqyys.getHssre()));
            sbsj.setHtcje(StringUtils.bigDeciaml2String(wqyys.getHtcje()));
            sbsj.setJfzce(StringUtils.bigDeciaml2String(wqyys.getJfzce()));
            sbsj.setJsje(StringUtils.bigDeciaml2String(wqyys.getJsje()));
            //sbsj.setKssl(NumberUtils.bigDeciaml2String(wqyys.getKssl()));
            sbsj.setSl(StringUtils.bigDeciaml2String(wqyys.getSl()));
            sbsj.setSre(StringUtils.bigDeciaml2String(wqyys.getSre()));
            sbsj.setYjl(StringUtils.bigDeciaml2String(wqyys.getYjl()));
            sbsj.setYjnse(StringUtils.bigDeciaml2String(wqyys.getYinse()));
            sbsj.setYnse(StringUtils.bigDeciaml2String(wqyys.getYnse()));
            sbsjlist.add(sbsj);
        }

		vo.setSbsj(sbsjlist);
        String ywdm=getYwdm(wqyysTemplate.getZsffdm());
        // XML�ĵ���Ϣ
        vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(ywdm).substring(8));
        vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(ywdm).substring(0,8));
        vo.setYwlx(ywdm);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return vo;
	}

	/**
     * �����걨��Ϣ
     * 1��ȡ��ShenbaoRapidForm������һ��VOPackage��
     * 2������ShenbaoRapidForm��beforeSave����ȡ�ñ����걨��Ϣ�Ĳ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊprocessor��ֵ��
     * 	voPackage��action��ΪShenbaoRapidActionConstant.INT_ACTION_SAVE
     * 	����ShenBaoProxy��process������
     * 4��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	WqyysForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public String doSave(
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {
		// 0.���token,��ֹ�ظ��ύ
        if (!isTokenValid(request))
        {
        return CAConstants.INVALIDTOKEN;
        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        DzyjHelper dh = new DzyjHelper();
         System.out.println(xmldata);
        DzyjsjVO dzyj =  new DzyjsjVO();
        Map retmap = null;

        Wqyys12VO xvo = new Wqyys12VO();
         //��ȡForm����
        WqyysForm vicForm = null;
        //����VOPackage
        VOPackage voPackage = new VOPackage();

        try
        {
            try
	         {
	            // XMLParseHelper.parseXMLString(xvo,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
	             //xvo = new Wqyys12VO();
	             XMLParseHelper.parseXMLString(xvo,xmldata,XMLParseHelper.VTDXML_PARSER,false,true);
	         }
	         catch (com.syax.frame.exception.ApplicationException e)
	         {
	         throw ExceptionUtil.getBaseException(e);
	         }

	         if (ud.getCaflag())
	         {
	             try
	             {
	 					dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
	             }
	             catch (com.syax.frame.exception.ApplicationException e1)
	             {
	                 throw ExceptionUtil.getBaseException(e1);
	             }
	         }

            HttpSession httpSession = request.getSession();
            setCodeTable(request, vicForm);
            Wqyys wqyysTemplate = (Wqyys)request.getSession().getAttribute(WqyysForm.JBXX);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	        //����ǩ��Ԫ��
            //if (ud.getCaflag())
	       // {
	            dzyj.setYwczlx(xvo.getYwczlx());
	            dzyj.setYwdm(xvo.getYwlx());
	        //}

	        voPackage.setUserData(ud);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_SAVE);
            // ��session��ȡ������ģ�����õ�form��ȥ

            // ȡ����Ĳ���
            List l = beforeSave(ud.yhid ,xvo ,wqyysTemplate ,request);
            httpSession.setAttribute(WqyysForm.SHENBAO, l);
            Map data = new HashMap(1);
            data.put(WqyysMapConstant.LIST_WQYYS, l);
			data.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);// ǩ��ԭ��
			data.put("dzyj_skssksrq", sdf.format( wqyysTemplate.getSkssksrq()));// ǩ��ԭ��
            voPackage.setData(data);

            // ���ú�̨���򱣴�
            Map reMap=(Map)ShenbaoProxy.getInstance().process(voPackage);

            //���û�ִ����ҳ��ĺ���������
            if (ud.getCaflag())
            {
                dzyj = (DzyjsjVO) reMap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
            }
            else
            {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,"");
            }
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "����Ӫҵ˰�걨��"+CAUtils.getTsxx(xvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"����Ӫҵ˰�걨��"+CAUtils.getTsxx(xvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_WQYYS_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "����Ӫҵ˰�걨", (xvo.getSbxx()).getSbrq(),
                    "�ɹ�!");

            // ���session
            //clearSession(request.getSession());
            return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
        }
        catch(Exception e)
        {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
            xvo.setSbxx(new Sbxx12VO());
            xvo.getSbxx().setDone(CAUtils.bool2Str(false));
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());
            
            throw ExceptionUtil.getBaseException(e);
        }
    }
	protected void log(String str) {
		if (ApplicationConstant.DEBUG_FLAG) {
			System.out.println("[WSSB DEBUG]" + (new Date()) + str);
		}
	}

    /**
     * ɾ���걨��Ϣ
     * 1��ȡ��ShenbaoRapidForm������һ��VOPackage��
     * 2������ShenbaoRapidForm��beforeDelete����ȡ��ɾ���걨��Ϣ�Ĳ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊprocessor��ֵ��
     * 	voPackage��action��ΪShenbaoRapidActionConstant.INT_ACTION_DELETE����
     * 	ShenBaoProxy��process������
     * 4��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	WqyysForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public String doDelete(
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        BaseException
    {
        if (!isTokenValid(request))
        {
           return CAConstants.INVALIDTOKEN;
        }
        //����VOPackage
        //System.out.println("  INTO ACTION  ");
        VOPackage voPackage = new VOPackage();
        DzyjsjVO dzyj =  new DzyjsjVO();
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        
        DzyjHelper dh = new DzyjHelper();
         System.out.println(xmldata);
        Map retmap = null;

//���ҳ���ύ����
        Wqyys12VO xvo = new Wqyys12VO();
        try
        {
            if (ud.getCaflag())
            {
                try
                {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (com.syax.frame.exception.ApplicationException e1)
                {
                    //e1.printStackTrace();
                    throw ExceptionUtil.getBaseException(e1);
                }
            }

            try
	        {
	            //XMLParseHelper.parseXMLString(xvo,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
	            //xvo = new Wqyys12VO();
	            XMLParseHelper.parseXMLString(xvo,xmldata,XMLParseHelper.VTDXML_PARSER,false,true);
	        }
	        catch (com.syax.frame.exception.ApplicationException e)
	        {
	        throw ExceptionUtil.getBaseException(e);
	        }


            HttpSession httpSession = request.getSession();
            setCodeTable(request, null);

            // ��session��ȡ������ģ�����õ�form��ȥ
            Wqyys wqyysTemplate = (Wqyys)request.getSession().getAttribute(WqyysForm.JBXX);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            //����ǩ������
	        //if (ud.getCaflag())
	        //{
	            dzyj.setYwczlx(xvo.getYwczlx());
	            dzyj.setYwdm(xvo.getYwlx());
	        //}

            UserData userData = (UserData) request.getSession(false).getAttribute("UserData");
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_DELETE);


            // ȡɾ���Ĳ���
            Map map = new HashMap(1);
            List list = new ArrayList(1);
            list.add(wqyysTemplate);
            map.put(WqyysMapConstant.LIST_WQYYS, list);
            map.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);// ǩ��ԭ��
            map.put("dzyj_skssksrq", sdf.format( wqyysTemplate.getSkssksrq()));// ǩ��ԭ��

            httpSession.setAttribute(WqyysForm.SHENBAO, list);
            voPackage.setData(map);

            // ���ú�̨����
            Map reMap=(Map)ShenbaoProxy.getInstance().process(voPackage);

            //���û�ִ����ҳ��ĺ���������
           request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,"");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"����Ӫҵ˰�걨��"+CAUtils.getTsxx(xvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_WQYYS_DELETE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "����Ӫҵ˰�걨", xvo.getSbxx().getSbrq(),
                    "�ɹ�!");
            // ���session
            clearSession(request.getSession());
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "����Ӫҵ˰�걨��"+CAUtils.getTsxx(xvo.getYwczlx()));
            return CAUtils.jumpTo(ud.getCaflag(),CAConstants.DELETE,CAConstants.SUCCESSSB);
        }
        catch(Exception e)
        {
			// /3.9.���ÿ�xml��ת��ʧ��ҳ��
            xvo.setSbxx(new Sbxx12VO());
            xvo.getSbxx().setDone(CAUtils.bool2Str(false));
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���ô����form��
     * @param request HttpServletRequest
     * @param vicForm WqyysForm
     */
    private void setCodeTable(HttpServletRequest request, WqyysForm vicForm)
    {
        List szsmList =
            CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST_AVAILABLE);
        request.getSession().setAttribute("szsmlist",setSzsmList(szsmList));
    }

    //�������շ������룬ȡҵ�����
    private String getYwdm(String wqzsfsdm){
        if(wqzsfsdm.equals(CodeConstant.WQYYS_HDZS))
        {
            //�˶�����
            
        	return CAcodeConstants.YWDM_SB_WS_WQYYS_HDZS;
        }
        else if(wqzsfsdm.equals(CodeConstant.WQYYS_JFHS))
        {
            //���ѻ���
            return CAcodeConstants.YWDM_SB_WS_WQYYS_JFHS;
        }
        else if(wqzsfsdm.equals(CodeConstant.WQYYS_ASSB))
        {
            //��ʵ�걨
            return CAcodeConstants.YWDM_SB_WS_WQYYS_ASSB;
        }
        else
        {
            //û���ҵ����ʵ����շ�ʽ
            
            return "";
        }
      	
    }
    /**
     * ����ҳ���������˰��˰Ŀ
     * @param szsmList ˰��˰Ŀ
     */
    public List setSzsmList(List szsmOriginalList)
    {
        // ʹ�ø��ƻ��Ʊ���ԭʼ����
        List szsmList = new ArrayList();
        // �����Ŀո��壬ȫ��
        String oneSpace = "��";
        String twoSpace = "����";
        String threeSpace = "������";
        String fourSpace = "��������";
        for(int i = 0, size = szsmOriginalList.size(); i < size; i++)
        {
            Szsm szsmSource = (Szsm)szsmOriginalList.get(i);
            Szsm szsm = new Szsm();
            szsm.setSl(szsmSource.getSl());
            szsm.setSzsmdm(szsmSource.getSzsmdm());
            szsm.setSzsmmc(szsmSource.getSzsmmc());
            String szsmdm = szsm.getSzsmdm();
            // �����Ա�˰�ִ��뿪ͷ
            if(szsmdm.startsWith(this.SZDM))
            {
                // ���˵�"Ӫҵ˰���ɽ�"��"Ӫҵ˰����"
                if(!szsmdm.endsWith(this.SZDMEND1) &&
                   !szsmdm.endsWith(this.SZDMEND2))
                {
                    switch (szsmdm.length() - 2) {
                        case 0:
                            // 2λ���ȵ�˰�ֲ����ı�
                            break;
                        case 1:
                            // 3λ���ȵ�˰��˰Ŀǰ���1���ո�
                            szsm.setSzsmmc(oneSpace + szsm.getSzsmmc());
                            break;
                        case 2:
                            // 4λ���ȵ�˰��˰Ŀǰ���2���ո�
                            szsm.setSzsmmc(twoSpace + szsm.getSzsmmc());
                            break;
                        case 3:
                            // 5λ���ȵ�˰��˰Ŀǰ���3���ո�
                            szsm.setSzsmmc(threeSpace + szsm.getSzsmmc());
                            break;
                        case 4:
                            // 6λ���ȵ�˰��˰Ŀǰ���4���ո�
                            szsm.setSzsmmc(fourSpace + szsm.getSzsmmc());
                            break;
                    }
                    szsmList.add(szsm);
                }
            }
        }
        return szsmList;
    }

    /**
     * �����쳣
     * @param mapping Struts����
     * @param e �쳣����
     * @param request ����
     * @param response ��Ӧ
     * @return ActionForward ����ֵ
     */
    public String processForwardByException(
        ActionMapping mapping,
        Exception e,
        HttpServletRequest request,
        HttpServletResponse response)
    {
        ActionErrors errors = new ActionErrors();

        if(e instanceof InvocationTargetException &&
           ((InvocationTargetException)e).getTargetException() instanceof
           ApplicationException)
        {
            // �ӷ��������ȡ��������쳣��Ϣ
            e.printStackTrace();
            String msg = ((InvocationTargetException)e).getTargetException().
                getMessage();
            errors.addError(msg);
        }
        else
        {
            // ��ͨ�쳣            
            errors.addError("ϵͳ�����������Ա��ϵ��");
        }
        saveErrors(request, errors);
        Wqzsf wqzsf = null;
        try
        {
            // ȡ�������շ�ʽ
            wqzsf = FriendHelper.getWqzsfsInfo(request);
        }
        catch(BaseException be)
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "ϵͳ����");
            return CAConstants.FAILURE;
        }
        // �����������շ�ʽת��
        return CAConstants.FAILURE + wqzsf.getWqzsfsdm();
    }

    /**
     * ȡ����ǰ����
     * ת��ȡ����Ӧȥ��ҳ�档
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public String doReturn(
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        clearSession(request.getSession());

        //ת�򷵻غ��ҳ�档
        return "Return";
    }

    /**
     * ���session�б���������
     * @param httpSession HttpSession
     */
    private void clearSession(HttpSession httpSession)
    {
        httpSession.removeAttribute(WqyysForm.SHENBAO);
        httpSession.removeAttribute(WqyysForm.JBXX);
    }

	/* (non-Javadoc)
	 * @see com.syax.common.web.action.DcBaseAction#beforePerform(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected String beforePerform(HttpServletRequest request, HttpServletResponse response) {
        // ���Ȩ��
    	//System.out.println("beforePerform");
        if (SbzlAccess.getAuthority(SbzlAccess.WQ, request)){
            return null;
        }
        return CAConstants.NOPERMISSION;
	}

    /**
     * �Ǽǳ���
     */
    private static final String DJ_JBSJ = "JBSJ";

    /**
     * ˰�ִ���
     */
    private static final String SZDM = "02";

    /**
     * ˰�ִ��봦������
     */
    private static final String SZDMEND1 = "91";

    /**
     * ˰�ִ��봦������
     */
    private static final String SZDMEND2 = "92";

    /**
     * �걨���ݳ���
     */
    public static final String SHENBAO = "SHENBAO";

    /**
     * ������Ϣ����
     */
    public static final String JBXX = "JBXX";
    /**
     * ����һ������Ӫҵ˰���걨����ģ��
     * @param jsjdm ���������
     * @param nsrmc ��˰������
     * @param swjgzzjgdm ˰�������֯��������
     * @return Wqyys ����Ӫҵ˰���걨����ģ��
     */
    private Wqyys makeTemplate(String jsjdm, String nsrmc, String swjgzzjgdm)
    {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        // ˰����������
        Map sksjrqMap = Skssrq.monthSkssrq(today);

        Wqyys wqyys = new Wqyys();
        //���������	JSJDM
        wqyys.setJsjdm(jsjdm);
        //˰��������ʼ����	SKSSKSRQ
        wqyys.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //˰��˰Ŀ����	SZSMDM
        //����ʱ��	CJSJ
        wqyys.setCjrq(now);
        //��˰������	NSRMC
        wqyys.setNsrmc(nsrmc);
        //�걨����	SBRQ
        wqyys.setSbrq(TinyTools.second2Day(now));
        //˰��������������	SKSSJSRQ
        wqyys.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //˰�������֯��������	SWJGZZJGDM
        wqyys.setSwjgzzjgdm(swjgzzjgdm);
        //¼���˴���	LRR
        wqyys.setLrr(jsjdm);
        //¼������	LRRQ
        wqyys.setLrrq(now);
        //�Ǽ��걨��ʽ����	FSDM
        wqyys.setFsdm(CodeConstant.FSDM_WSSB);
        //��� ND
        wqyys.setNd((String)sksjrqMap.get(Skssrq.SKSSRQ_ND));

        wqyys.setQxdm(swjgzzjgdm.substring(0, 2));

        return wqyys;
    }

    /**
     * ����˰��˰Ŀ����ȡ��˰��˰Ŀ����
     * @param szsmdm String
     * @param request HttpServletRequest
     * @return Szsm
     */
    protected Szsm getSzsm(String szsmdm, HttpServletRequest request)
    {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);

        return (Szsm)szsmMap.get(szsmdm);
    }

    /**
     * ��ҳ����������ֵ���󷵻�
     * @param jsjdm ���������
     * @throws ApplicationException
     */
    private List beforeSave(String jsjdm ,Wqyys12VO xvo ,Wqyys wqyysTemplate, HttpServletRequest request) throws ApplicationException
    {
        List wqyysList=new ArrayList();
        List sbsjlist=xvo.getSbsj();
    	int size = 0;
        if(sbsjlist != null)
        {
            size = sbsjlist.size();
        }
        String zsffdm=wqyysTemplate.getZsffdm();

        // 0��Ϊ��׼ֵ
        BigDecimal zero = new BigDecimal("0");

        for(int i = 0; i < size; i++)
        {
            // ȡ��һ������ģ��
            Wqyys wqyys = makeTemplate(jsjdm, wqyysTemplate.getNsrmc() ,wqyysTemplate.getSwjgzzjgdm());
            Sbsj12VO sbsj=(Sbsj12VO)sbsjlist.get(i);
            // ���Ҷ�Ӧ��˰��˰Ŀ
            Szsm szsm = getSzsm(sbsj.getSzsmdm(),request);
            //˰��˰Ŀ����	SZSMDM
            wqyys.setSzsmdm(sbsj.getSzsmdm());
            //��˰��������	ZSFFDM
            wqyys.setZsffdm(zsffdm);
            if(zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_JFHS))
            {
                // ���롰���ѻ��㡱���ж��߼�

                //����֧����	JFZCE
                wqyys.setJfzce(new BigDecimal(sbsj.getJfzce()));
                //���������	HSSRE
                wqyys.setHssre(new BigDecimal(sbsj.getHssre()));
                //������0
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
                wqyys.setSre(zero);
            }
            else if(zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_HDZS))
            {
                // ���롰�˶����ա����ж��߼�

                //��ͬ�ɽ���	HTCJE
                wqyys.setHtcje(new BigDecimal(sbsj.getHtcje()));
                //Ӷ����	YJL
                wqyys.setYjl(new BigDecimal(sbsj.getYjl()));
                //�˶������	HDSRE
                wqyys.setHdsre(new BigDecimal(sbsj.getHdsre()));
                //������0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setSre(zero);
            }
            else if(zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_ASSB))
            {
                // ���롰��ʵ�걨�����ж��߼�

                //�����	SRE
                wqyys.setSre(new BigDecimal(sbsj.getSre()));
                //������0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
            }
            else
            {
                // û��������շ�ʽ
                throw new ApplicationException("����ʧ�ܣ�");
            }

            BigDecimal jsjeWrapper = new BigDecimal(sbsj.getJsje());

            BigDecimal slWrapper = szsm.getSl();
            // Ӧ��˰�� = ��˰��� * ˰��
            BigDecimal ynseWrapper = jsjeWrapper.multiply(slWrapper).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
            // �ѽ���˰��
            BigDecimal yjnseWrapper = new BigDecimal(sbsj.getYjnse());
            // ����Ӧ��˰�� = Ӧ��˰�� - �ѽ���˰��
            BigDecimal bqybseWrapper =
                ynseWrapper.subtract(yjnseWrapper).setScale(2, BigDecimal.ROUND_HALF_EVEN);

            wqyys.setJsje(jsjeWrapper);  //��˰���
            wqyys.setSl(slWrapper);  //˰��
            wqyys.setYnse(ynseWrapper);  //Ӧ��˰��
            wqyys.setYinse(yjnseWrapper);  //����˰��
            wqyys.setBqybse(bqybseWrapper);  //����Ӧ��˰��

            wqyysList.add(wqyys);
        }
        
        return wqyysList;
    }
	
}