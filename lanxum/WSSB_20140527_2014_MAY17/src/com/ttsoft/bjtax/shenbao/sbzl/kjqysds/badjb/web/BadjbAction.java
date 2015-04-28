package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.web;


/**
 * <p>Title: 扣缴企业所得税-备案登记表Action</p>
 *
 * <p>Description: 处理备案登记表页面应用事件响应</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.common.model.UserData;
import com.syax.common.web.action.DcBaseAction;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.KjqysdsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo.BadjbVO;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.exception.BaseException;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import java.util.Map;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.frame.exception.ApplicationException;
import java.util.ArrayList;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import java.util.HashMap;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.bo.BadjbBO;



public class BadjbAction extends DcBaseAction
{
    public BadjbAction()
    {
    }

    protected String beforePerform(HttpServletRequest request,
                                   HttpServletResponse response)
    {
//        request.setAttribute("name", "报备企业所得税合同备案登记表");
        return null;
    }


    /**
     * 初始化页面数据
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction do show");
        /**
         * 初始化页面下拉菜单
         */
        try {
            ActionHelper.initialPageSelectItem(request);
        }
        catch (Exception ex) {
            System.out.println("初始化页面下拉菜单错误!");
        }

        // 构造VOPackage
        VOPackage vo = new VOPackage();
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        if(ud == null) {
            System.out.println("session is null");
        }

        BadjbVO badjb = new BadjbVO();
        BadjbBO bo = new BadjbBO();
        bo.setJsjdm(ud.getYhid());

        try
        {
            vo.setAction(ActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(bo);
            vo.setUserData(ud);
            // 调用后台查询数据
            bo = (BadjbBO) ShenbaoProxy.getInstance().process(vo);

//            qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            ActionHelper.BO2XMLVO(bo, badjb);

            System.out.println("redeay to show xml");
            String tmpxml = badjb.toXML();

            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());

            // if (true) throw new Exception("test");

            // 转向企业所得税亏损申报页面
            return CAConstants.SHOW;
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * 初始化备案登记表管理页面数据
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public String doShowModify(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction doShowModify");

        // 构造VOPackage
        VOPackage vo = new VOPackage();
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        if(ud == null) {
            System.out.println("session is null");
        }

        BadjbVO badjb = new BadjbVO();
        BadjbBO bo = new BadjbBO();
        //ActionHelper.XMLVO2BO(vo, bo)
        bo.setJsjdm(ud.getYhid());

        try
        {
            vo.setAction(ActionConstant.INT_ACTION_QUERY_MXXX);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(bo);
            vo.setUserData(ud);
            // 调用后台查询数据
            bo = (BadjbBO) ShenbaoProxy.getInstance().process(vo);

//            qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            ActionHelper.BO2XMLVO(bo, badjb);

            System.out.println("redeay to show xml");
            String tmpxml = badjb.toXML();

            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            
            request.setAttribute("totalCount", String.valueOf(bo.getTotalCount()));
            request.setAttribute("currentPage", String.valueOf(bo.getCurrentPage()));            
            request.setAttribute("totalPage", String.valueOf(bo.getTotalPage()));

            // if (true) throw new Exception("test");

            // 转向企业所得税亏损申报页面
            return "ShowModify";
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * 翻页操作
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public String doFy(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction doFy");
        
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("doFy xml:::\n" + xmldata);
        UserData ud = (UserData) this.getUserData(request);
        if(ud == null) {
            System.out.println("session is null");
        }

        BadjbVO badjb = new BadjbVO();
        BadjbBO bo = new BadjbBO();
           
	    try
	    {	
	    	XMLParseHelper.parseXMLString(badjb, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
	    }
	    catch (ApplicationException e) {
	    	System.out.println("xml转换失败");
	        throw ExceptionUtil.getBaseException(e);
	    }
	
	    // 将vo内容转换成bo对象供后台使用
	    ActionHelper.XMLVO2BO(badjb, bo);
	    bo.setJsjdm(ud.getYhid());
                  
        try
        {
        	// 构造VOPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_QUERY_MXXX);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(bo);
            vo.setUserData(ud);
            // 调用后台查询数据
            bo = (BadjbBO) ShenbaoProxy.getInstance().process(vo);

//            qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            ActionHelper.BO2XMLVO(bo, badjb);

            System.out.println("redeay to show xml");
            String tmpxml = badjb.toXML();

            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            
            request.setAttribute("totalCount", String.valueOf(bo.getTotalCount()));
            request.setAttribute("currentPage", String.valueOf(bo.getCurrentPage()));            
            request.setAttribute("totalPage", String.valueOf(bo.getTotalPage()));

            // if (true) throw new Exception("test");

            // 转向企业所得税亏损申报页面
            return "ShowModify";
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * 删除操作
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public String doDelete(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction doDelete");
        
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("doDelete xml:::\n" + xmldata);
        UserData ud = (UserData) this.getUserData(request);
        if(ud == null) {
            System.out.println("session is null");
        }

        BadjbVO badjb = new BadjbVO();
        BadjbBO bo = new BadjbBO();
           
	    try
	    {	
	    	XMLParseHelper.parseXMLString(badjb, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
	    }
	    catch (ApplicationException e) {
	    	System.out.println("xml转换失败");
	        throw ExceptionUtil.getBaseException(e);
	    }
	
	    // 将vo内容转换成bo对象供后台使用
	    ActionHelper.XMLVO2BO(badjb, bo);
	    bo.setJsjdm(ud.getYhid());
                  
        try
        {
        	// 构造VOPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_DELETE);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(bo);
            vo.setUserData(ud);
            // 调用后台查询数据
            ShenbaoProxy.getInstance().process(vo);

            // 转向企业所得税亏损申报页面
            return doShowModify(request, response);
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * 修改备案登记表信息
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public String doModify(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction doModify");
        /**
         * 初始化页面下拉菜单
         */
        try {
            ActionHelper.initialPageSelectItem(request);
        }
        catch (Exception ex) {
            System.out.println("初始化页面下拉菜单错误!");
        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("modify xml:::\n" + xmldata);
        
        BadjbVO badjb = new BadjbVO();
        
        try
        {
            XMLParseHelper.parseXMLString(badjb, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
        }
        catch (ApplicationException e) {
            System.out.println("xml转换失败");
            throw ExceptionUtil.getBaseException(e);
        }
        
        // 构造VOPackage
        VOPackage vo = new VOPackage();
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        if(ud == null) {
            System.out.println("session is null");
        }

        
        BadjbBO bo = new BadjbBO();
        ActionHelper.XMLVO2BO(badjb, bo);
        bo.setJsjdm(ud.getYhid());

        try
        {
            vo.setAction(ActionConstant.INT_ACTION_VIEWMX);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(bo);
            vo.setUserData(ud);
            // 调用后台查询数据
            bo = (BadjbBO) ShenbaoProxy.getInstance().process(vo);

//            qysdsjbvo = qysdsUtil.Zfjgconvert2XMLVO(qysdsjbbo, djJbsj);
            ActionHelper.BO2XMLVO(bo, badjb);

            System.out.println("redeay to show xml");
            String tmpxml = badjb.toXML();

            System.out.println("show xml:" + tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());

            // if (true) throw new Exception("test");

            // 转向企业所得税亏损申报页面
            return "Show";
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 初始化页面数据
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException
     *             系统捕捉异常，根据异常类型抛出
     */
    public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction do save");
        if(!isTokenValid(request)) {
            return CAConstants.INVALIDTOKEN;
        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("save xml:::\n" + xmldata);
        UserData ud = (UserData) this.getUserData(request);
        System.out.println("ud.getCaflag() = " + ud.getCaflag());
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();

        BadjbVO badjb = new BadjbVO();
        BadjbBO bo = new BadjbBO();
        Map retmap = null;

        try
        {
            if (ud.getCaflag())
            {
                try {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (ApplicationException e1) {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            try
            {
                XMLParseHelper.parseXMLString(badjb, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
            }
            catch (ApplicationException e) {
                System.out.println("xml转换失败");
                throw ExceptionUtil.getBaseException(e);
            }
            
            // 处理其它资料名称格式
        	String qtzlmc = badjb.getHtxx().getQtzlmc();
        	if (qtzlmc != null && !"".equals(qtzlmc)) 
        	{
    			//替换其它资料名称中的格式符为对应格式字符串
        		qtzlmc = qtzlmc.replaceAll(" ", "&nbsp;");
        		qtzlmc = qtzlmc.replaceAll("\r\n", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\r", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\n", "<br/>");
        		
        		badjb.getHtxx().setQtzlmc(qtzlmc);
    		}
        	// 将vo内容转换成bo对象供后台使用
            ActionHelper.XMLVO2BO(badjb, bo);
            
            dzyj.setYwdm(badjb.getYwlx());
            dzyj.setYwczlx(badjb.getYwczlx());

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, bo);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, badjb);


            // 构造VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_SAVE);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(pData);
            vo.setUserData(ud);
            // 调用后台查询数据
            retmap = (Map) ShenbaoProxy.getInstance().process(vo);

            if (ud.getCaflag()) {
                dzyj = (DzyjsjVO)retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                ArrayList hzlist = new ArrayList();
                hzlist.add(Long.toString(dzyj.getLsh()));
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, hzlist);
            }
            else
            {
            	bo = (BadjbBO) retmap.get(KjqysdsConstant.RETURN_OBJECT);
            	if(bo != null)
            	{
	            	if(bo.getZtbz().equals("3"))
	    			{
	            		ActionHelper.BO2XMLVO(bo, badjb);
	                	
	                	String tmpxml = badjb.toXML();
	
	                    System.out.println("show xml:" + tmpxml);
	                    request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
	                    request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
	                    request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
	                    request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
	                    
	    				// 将badjForm 放入request中
	            		request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
	                            bo.getMessage());
	            		
	            		return CAConstants.SHOW;
	    			}
            	}
            	request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            }

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                    "网上合同登记备案保存成功，请按规定到主管税务机关报送纸制《扣缴企业所得税合同备案登记表》、合同复印件（文本为外文的，同时附送中文译本）、非居民企业授权委托书及其他相关资料。" + CAUtils.getTsxx(badjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                    "扣缴企业所得税合同备案登记表" + CAUtils.getTsxx(badjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                    "/shenbao/badjb.dc?actionType=Show");
            LogUtil.getInstance().log(ud, "扣缴企业所得税合同备案登记表", badjb.getTbrq(), "成功!");

            if(ud.getCaflag())
            {
                return CAUtils.jumpTo(ud.getCaflag(), CAConstants.SAVE, CAConstants.SUCCESSSB);
            }
            else
            {
                return doShow(request, response);
            }
        }
        catch(Exception ex) {
            // /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(ud, "扣缴企业所得税合同备案登记表", badjb.getTbrq(), "失败!");

            return CAConstants.SHOW;
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
            HttpServletResponse response) throws BaseException {
        return doShowModify(request, response);
    }

}
