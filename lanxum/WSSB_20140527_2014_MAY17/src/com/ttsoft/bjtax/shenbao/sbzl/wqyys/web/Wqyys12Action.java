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
 * 外企营业税申报action，支持按实申报、核定征收、经费换算三种方式
 */
public class Wqyys12Action  extends DcBaseAction
{

    // 转向的常量的定义
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
            errorMessage = "业务类型错误，不能执行业务操作。";
            return false;
        }
        if (!(vo.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || vo.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY) || vo.getYwczlx().equals(CAcodeConstants.YWCZLX_DELETE)))
        {
            System.out.println("业务操作类型错误" + vo.getYwczlx());
            errorMessage = "业务操作类型错误，不能执行业务操作。";
            return false;
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return true;
    }

    /**
     * 取业务信息
     * 1、取得ShenbaoRapidForm，产生一个VOPackage。
     * 2、调用ShenbaoRapidForm的beforeQuery方法取得业务数据的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为processor的值，
     * 	voPackage的action域为ShenbaoRapidActionConstant.INT_ACTION_QUERY调用
     * 	ShenBaoProxy的process方法。
     * 4、调用ShenbaoRapidForm的afterQuery方法设置取业务数据的结果。
     * 5、转向业务页面。
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
        //获取Form对象
        WqyysForm vicForm = null;
        Wqyys12VO xvo = new Wqyys12VO();

        try
        {
            HttpSession httpSession = request.getSession();

            // 设置代码表到form
            setCodeTable(request, vicForm);

            // 取得UserData
            UserData userData = (UserData) request.getSession(false).getAttribute("UserData");

            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);

            VOPackage voPackage = new VOPackage();
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_QUERY);

            Map map = new HashMap(3);
//查询参数
            Map sksjrqMap = Skssrq.monthSkssrq(new Date());
            Timestamp ksrq = (Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ);
            String skssksrq = TinyTools.Date2String(ksrq);

            map.put(WqyysMapConstant.JSJDM, jbsj.getJsjdm());
            map.put(WqyysMapConstant.SKSSKSRQ, skssksrq);
            map.put(WqyysMapConstant.DJJBSJ, jbsj);
            
            voPackage.setData(map);

            voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);
            Map data=(Map)voPackage.getData();
            // 进行处理结果的前台处理
            // 产生数据模板
            Wqyys wqyysTemplate = makeTemplate(jbsj.getJsjdm(), jbsj.getNsrmc(),
                                              jbsj.getSwjgzzjgdm());
            // 产生业务数据
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);
            for(int i = 0, size = wqyysList.size(); i < size; i++)
            {
                Wqyys wqyys = (Wqyys)wqyysList.get(i);
                // 设置纳税人名称
                wqyys.setNsrmc(jbsj.getNsrmc());
            }

            // 保存数据到session中
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

            // 转向到本页面
            if(wqzsfsdm.equals(CodeConstant.WQYYS_HDZS))
            {
                //核定征收
            	return this.WQYYS + CodeConstant.WQYYS_HDZS;
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_JFHS))
            {
                //经费换算
                return this.WQYYS + CodeConstant.WQYYS_JFHS;
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_ASSB))
            {
                //按实申报
                return this.WQYYS + CodeConstant.WQYYS_ASSB;
            }
            else
            {
                //没有找到合适的征收方式
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "未知的外企营业税征收方式！");
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
            // 设置纳税人名称
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
        // XML文档信息
        vo.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(ywdm).substring(8));
        vo.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(ywdm).substring(0,8));
        vo.setYwlx(ywdm);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

		return vo;
	}

	/**
     * 保存申报信息
     * 1、取得ShenbaoRapidForm，产生一个VOPackage。
     * 2、调用ShenbaoRapidForm的beforeSave方法取得保存申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为processor的值，
     * 	voPackage的action域为ShenbaoRapidActionConstant.INT_ACTION_SAVE
     * 	调用ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
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
		// 0.检查token,防止重复提交
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
         //获取Form对象
        WqyysForm vicForm = null;
        //生成VOPackage
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
	        //保存签名元件
            //if (ud.getCaflag())
	       // {
	            dzyj.setYwczlx(xvo.getYwczlx());
	            dzyj.setYwdm(xvo.getYwlx());
	        //}

	        voPackage.setUserData(ud);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_SAVE);
            // 从session中取得数据模板设置到form中去

            // 取保存的参数
            List l = beforeSave(ud.yhid ,xvo ,wqyysTemplate ,request);
            httpSession.setAttribute(WqyysForm.SHENBAO, l);
            Map data = new HashMap(1);
            data.put(WqyysMapConstant.LIST_WQYYS, l);
			data.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);// 签名原件
			data.put("dzyj_skssksrq", sdf.format( wqyysTemplate.getSkssksrq()));// 签名原件
            voPackage.setData(data);

            // 调用后台程序保存
            Map reMap=(Map)ShenbaoProxy.getInstance().process(voPackage);

            //设置回执下载页面的后续操作。
            if (ud.getCaflag())
            {
                dzyj = (DzyjsjVO) reMap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
            }
            else
            {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,"");
            }
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "外企营业税申报表"+CAUtils.getTsxx(xvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"外企营业税申报表"+CAUtils.getTsxx(xvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_WQYYS_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "外企营业税申报", (xvo.getSbxx()).getSbrq(),
                    "成功!");

            // 清除session
            //clearSession(request.getSession());
            return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
        }
        catch(Exception e)
        {
			// /3.9.设置空xml，转向失败页面
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
     * 删除申报信息
     * 1、取得ShenbaoRapidForm，产生一个VOPackage。
     * 2、调用ShenbaoRapidForm的beforeDelete方法取得删除申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为processor的值，
     * 	voPackage的action域为ShenbaoRapidActionConstant.INT_ACTION_DELETE调用
     * 	ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
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
        //生成VOPackage
        //System.out.println("  INTO ACTION  ");
        VOPackage voPackage = new VOPackage();
        DzyjsjVO dzyj =  new DzyjsjVO();
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        
        DzyjHelper dh = new DzyjHelper();
         System.out.println(xmldata);
        Map retmap = null;

//获得页面提交数据
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

            // 从session中取得数据模板设置到form中去
            Wqyys wqyysTemplate = (Wqyys)request.getSession().getAttribute(WqyysForm.JBXX);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            //保存签名数据
	        //if (ud.getCaflag())
	        //{
	            dzyj.setYwczlx(xvo.getYwczlx());
	            dzyj.setYwdm(xvo.getYwlx());
	        //}

            UserData userData = (UserData) request.getSession(false).getAttribute("UserData");
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_DELETE);


            // 取删除的参数
            Map map = new HashMap(1);
            List list = new ArrayList(1);
            list.add(wqyysTemplate);
            map.put(WqyysMapConstant.LIST_WQYYS, list);
            map.put(ZhsbMapConstant.CA_QMSJ_VO, dzyj);// 签名原件
            map.put("dzyj_skssksrq", sdf.format( wqyysTemplate.getSkssksrq()));// 签名原件

            httpSession.setAttribute(WqyysForm.SHENBAO, list);
            voPackage.setData(map);

            // 调用后台程序
            Map reMap=(Map)ShenbaoProxy.getInstance().process(voPackage);

            //设置回执下载页面的后续操作。
           request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,"");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"外企营业税申报表"+CAUtils.getTsxx(xvo.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_WQYYS_DELETE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "外企营业税申报", xvo.getSbxx().getSbrq(),
                    "成功!");
            // 清除session
            clearSession(request.getSession());
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "外企营业税申报表"+CAUtils.getTsxx(xvo.getYwczlx()));
            return CAUtils.jumpTo(ud.getCaflag(),CAConstants.DELETE,CAConstants.SUCCESSSB);
        }
        catch(Exception e)
        {
			// /3.9.设置空xml，转向失败页面
            xvo.setSbxx(new Sbxx12VO());
            xvo.getSbxx().setDone(CAUtils.bool2Str(false));
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, xvo.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, xvo.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, xvo.getSchemaVersion());
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 设置代码表到form。
     * @param request HttpServletRequest
     * @param vicForm WqyysForm
     */
    private void setCodeTable(HttpServletRequest request, WqyysForm vicForm)
    {
        List szsmList =
            CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST_AVAILABLE);
        request.getSession().setAttribute("szsmlist",setSzsmList(szsmList));
    }

    //根据征收方法代码，取业务代码
    private String getYwdm(String wqzsfsdm){
        if(wqzsfsdm.equals(CodeConstant.WQYYS_HDZS))
        {
            //核定征收
            
        	return CAcodeConstants.YWDM_SB_WS_WQYYS_HDZS;
        }
        else if(wqzsfsdm.equals(CodeConstant.WQYYS_JFHS))
        {
            //经费换算
            return CAcodeConstants.YWDM_SB_WS_WQYYS_JFHS;
        }
        else if(wqzsfsdm.equals(CodeConstant.WQYYS_ASSB))
        {
            //按实申报
            return CAcodeConstants.YWDM_SB_WS_WQYYS_ASSB;
        }
        else
        {
            //没有找到合适的征收方式
            
            return "";
        }
      	
    }
    /**
     * 设置页面下拉框的税种税目
     * @param szsmList 税种税目
     */
    public List setSzsmList(List szsmOriginalList)
    {
        // 使用复制机制保护原始数据
        List szsmList = new ArrayList();
        // 缩进的空格定义，全角
        String oneSpace = "　";
        String twoSpace = "　　";
        String threeSpace = "　　　";
        String fourSpace = "　　　　";
        for(int i = 0, size = szsmOriginalList.size(); i < size; i++)
        {
            Szsm szsmSource = (Szsm)szsmOriginalList.get(i);
            Szsm szsm = new Szsm();
            szsm.setSl(szsmSource.getSl());
            szsm.setSzsmdm(szsmSource.getSzsmdm());
            szsm.setSzsmmc(szsmSource.getSzsmmc());
            String szsmdm = szsm.getSzsmdm();
            // 代码以本税种代码开头
            if(szsmdm.startsWith(this.SZDM))
            {
                // 过滤掉"营业税滞纳金"和"营业税罚款"
                if(!szsmdm.endsWith(this.SZDMEND1) &&
                   !szsmdm.endsWith(this.SZDMEND2))
                {
                    switch (szsmdm.length() - 2) {
                        case 0:
                            // 2位长度的税种不做改变
                            break;
                        case 1:
                            // 3位长度的税种税目前面加1个空格
                            szsm.setSzsmmc(oneSpace + szsm.getSzsmmc());
                            break;
                        case 2:
                            // 4位长度的税种税目前面加2个空格
                            szsm.setSzsmmc(twoSpace + szsm.getSzsmmc());
                            break;
                        case 3:
                            // 5位长度的税种税目前面加3个空格
                            szsm.setSzsmmc(threeSpace + szsm.getSzsmmc());
                            break;
                        case 4:
                            // 6位长度的税种税目前面加4个空格
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
     * 处理异常
     * @param mapping Struts变量
     * @param e 异常变量
     * @param request 请求
     * @param response 回应
     * @return ActionForward 返回值
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
            // 从反射机制中取其包含的异常信息
            e.printStackTrace();
            String msg = ((InvocationTargetException)e).getTargetException().
                getMessage();
            errors.addError(msg);
        }
        else
        {
            // 普通异常            
            errors.addError("系统错误，请与管理员联系！");
        }
        saveErrors(request, errors);
        Wqzsf wqzsf = null;
        try
        {
            // 取外企征收方式
            wqzsf = FriendHelper.getWqzsfsInfo(request);
        }
        catch(BaseException be)
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "系统出错！");
            return CAConstants.FAILURE;
        }
        // 按照外企征收方式转向
        return CAConstants.FAILURE + wqzsf.getWqzsfsdm();
    }

    /**
     * 取消当前操作
     * 转向取消后应去的页面。
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

        //转向返回后的页面。
        return "Return";
    }

    /**
     * 清除session中保留的数据
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
        // 检查权限
    	//System.out.println("beforePerform");
        if (SbzlAccess.getAuthority(SbzlAccess.WQ, request)){
            return null;
        }
        return CAConstants.NOPERMISSION;
	}

    /**
     * 登记常量
     */
    private static final String DJ_JBSJ = "JBSJ";

    /**
     * 税种代码
     */
    private static final String SZDM = "02";

    /**
     * 税种代码处罚部分
     */
    private static final String SZDMEND1 = "91";

    /**
     * 税种代码处罚部分
     */
    private static final String SZDMEND2 = "92";

    /**
     * 申报数据常量
     */
    public static final String SHENBAO = "SHENBAO";

    /**
     * 基本信息常量
     */
    public static final String JBXX = "JBXX";
    /**
     * 产生一个外企营业税的申报数据模板
     * @param jsjdm 计算机代码
     * @param nsrmc 纳税人名称
     * @param swjgzzjgdm 税务机关组织机构代码
     * @return Wqyys 外企营业税的申报数据模板
     */
    private Wqyys makeTemplate(String jsjdm, String nsrmc, String swjgzzjgdm)
    {
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());
        // 税款所属日期
        Map sksjrqMap = Skssrq.monthSkssrq(today);

        Wqyys wqyys = new Wqyys();
        //计算机代码	JSJDM
        wqyys.setJsjdm(jsjdm);
        //税款所属开始日期	SKSSKSRQ
        wqyys.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //税种税目代码	SZSMDM
        //创建时间	CJSJ
        wqyys.setCjrq(now);
        //纳税人名称	NSRMC
        wqyys.setNsrmc(nsrmc);
        //申报日期	SBRQ
        wqyys.setSbrq(TinyTools.second2Day(now));
        //税款所属结束日期	SKSSJSRQ
        wqyys.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //税务机关组织机构代码	SWJGZZJGDM
        wqyys.setSwjgzzjgdm(swjgzzjgdm);
        //录入人代码	LRR
        wqyys.setLrr(jsjdm);
        //录入日期	LRRQ
        wqyys.setLrrq(now);
        //登记申报方式代码	FSDM
        wqyys.setFsdm(CodeConstant.FSDM_WSSB);
        //年度 ND
        wqyys.setNd((String)sksjrqMap.get(Skssrq.SKSSRQ_ND));

        wqyys.setQxdm(swjgzzjgdm.substring(0, 2));

        return wqyys;
    }

    /**
     * 根据税种税目代码取得税种税目对象
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
     * 从页面的资料填充值对象返回
     * @param jsjdm 计算机代码
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

        // 0作为基准值
        BigDecimal zero = new BigDecimal("0");

        for(int i = 0; i < size; i++)
        {
            // 取得一条数据模板
            Wqyys wqyys = makeTemplate(jsjdm, wqyysTemplate.getNsrmc() ,wqyysTemplate.getSwjgzzjgdm());
            Sbsj12VO sbsj=(Sbsj12VO)sbsjlist.get(i);
            // 查找对应的税种税目
            Szsm szsm = getSzsm(sbsj.getSzsmdm(),request);
            //税种税目代码	SZSMDM
            wqyys.setSzsmdm(sbsj.getSzsmdm());
            //征税方法代码	ZSFFDM
            wqyys.setZsffdm(zsffdm);
            if(zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_JFHS))
            {
                // 进入“经费换算”的判断逻辑

                //经费支出额	JFZCE
                wqyys.setJfzce(new BigDecimal(sbsj.getJfzce()));
                //换算收入额	HSSRE
                wqyys.setHssre(new BigDecimal(sbsj.getHssre()));
                //其他置0
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
                wqyys.setSre(zero);
            }
            else if(zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_HDZS))
            {
                // 进入“核定征收”的判断逻辑

                //合同成交额	HTCJE
                wqyys.setHtcje(new BigDecimal(sbsj.getHtcje()));
                //佣金率	YJL
                wqyys.setYjl(new BigDecimal(sbsj.getYjl()));
                //核定收入额	HDSRE
                wqyys.setHdsre(new BigDecimal(sbsj.getHdsre()));
                //其他置0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setSre(zero);
            }
            else if(zsffdm.equalsIgnoreCase(CodeConstant.WQYYS_ASSB))
            {
                // 进入“按实申报”的判断逻辑

                //收入额	SRE
                wqyys.setSre(new BigDecimal(sbsj.getSre()));
                //其他置0
                wqyys.setJfzce(zero);
                wqyys.setHssre(zero);
                wqyys.setHtcje(zero);
                wqyys.setYjl(zero);
                wqyys.setHdsre(zero);
            }
            else
            {
                // 没有这个征收方式
                throw new ApplicationException("操作失败！");
            }

            BigDecimal jsjeWrapper = new BigDecimal(sbsj.getJsje());

            BigDecimal slWrapper = szsm.getSl();
            // 应纳税额 = 计税金额 * 税率
            BigDecimal ynseWrapper = jsjeWrapper.multiply(slWrapper).setScale(2,
                BigDecimal.ROUND_HALF_EVEN);
            // 已缴纳税额
            BigDecimal yjnseWrapper = new BigDecimal(sbsj.getYjnse());
            // 本期应补税额 = 应纳税额 - 已缴纳税额
            BigDecimal bqybseWrapper =
                ynseWrapper.subtract(yjnseWrapper).setScale(2, BigDecimal.ROUND_HALF_EVEN);

            wqyys.setJsje(jsjeWrapper);  //计税金额
            wqyys.setSl(slWrapper);  //税率
            wqyys.setYnse(ynseWrapper);  //应纳税额
            wqyys.setYinse(yjnseWrapper);  //已纳税额
            wqyys.setBqybse(bqybseWrapper);  //本期应补税额

            wqyysList.add(wqyys);
        }
        
        return wqyysList;
    }
	
}