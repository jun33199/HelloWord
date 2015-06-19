package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.web;


/**
 * <p>Title: �۽���ҵ����˰-�����ǼǱ�Action</p>
 *
 * <p>Description: �������ǼǱ�ҳ��Ӧ���¼���Ӧ</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
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
//        request.setAttribute("name", "������ҵ����˰��ͬ�����ǼǱ�");
        return null;
    }


    /**
     * ��ʼ��ҳ������
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction do show");
        /**
         * ��ʼ��ҳ�������˵�
         */
        try {
            ActionHelper.initialPageSelectItem(request);
        }
        catch (Exception ex) {
            System.out.println("��ʼ��ҳ�������˵�����!");
        }

        // ����VOPackage
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
            // ���ú�̨��ѯ����
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

            // ת����ҵ����˰�����걨ҳ��
            return CAConstants.SHOW;
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * ��ʼ�������ǼǱ����ҳ������
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public String doShowModify(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction doShowModify");

        // ����VOPackage
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
            // ���ú�̨��ѯ����
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

            // ת����ҵ����˰�����걨ҳ��
            return "ShowModify";
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * ��ҳ����
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
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
	    	System.out.println("xmlת��ʧ��");
	        throw ExceptionUtil.getBaseException(e);
	    }
	
	    // ��vo����ת����bo���󹩺�̨ʹ��
	    ActionHelper.XMLVO2BO(badjb, bo);
	    bo.setJsjdm(ud.getYhid());
                  
        try
        {
        	// ����VOPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_QUERY_MXXX);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(bo);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
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

            // ת����ҵ����˰�����걨ҳ��
            return "ShowModify";
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * ɾ������
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
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
	    	System.out.println("xmlת��ʧ��");
	        throw ExceptionUtil.getBaseException(e);
	    }
	
	    // ��vo����ת����bo���󹩺�̨ʹ��
	    ActionHelper.XMLVO2BO(badjb, bo);
	    bo.setJsjdm(ud.getYhid());
                  
        try
        {
        	// ����VOPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_DELETE);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(bo);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
            ShenbaoProxy.getInstance().process(vo);

            // ת����ҵ����˰�����걨ҳ��
            return doShowModify(request, response);
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    /**
     * �޸ı����ǼǱ���Ϣ
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
     */
    public String doModify(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        System.out.println("coming into badjAction doModify");
        /**
         * ��ʼ��ҳ�������˵�
         */
        try {
            ActionHelper.initialPageSelectItem(request);
        }
        catch (Exception ex) {
            System.out.println("��ʼ��ҳ�������˵�����!");
        }
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        System.out.println("modify xml:::\n" + xmldata);
        
        BadjbVO badjb = new BadjbVO();
        
        try
        {
            XMLParseHelper.parseXMLString(badjb, xmldata, XMLParseHelper.VTDXML_PARSER, false, false);
        }
        catch (ApplicationException e) {
            System.out.println("xmlת��ʧ��");
            throw ExceptionUtil.getBaseException(e);
        }
        
        // ����VOPackage
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
            // ���ú�̨��ѯ����
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

            // ת����ҵ����˰�����걨ҳ��
            return "Show";
        }
        catch(Exception ex) {
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ��ʼ��ҳ������
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException
     *             ϵͳ��׽�쳣�������쳣�����׳�
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
                System.out.println("xmlת��ʧ��");
                throw ExceptionUtil.getBaseException(e);
            }
            
            // ���������������Ƹ�ʽ
        	String qtzlmc = badjb.getHtxx().getQtzlmc();
        	if (qtzlmc != null && !"".equals(qtzlmc)) 
        	{
    			//�滻�������������еĸ�ʽ��Ϊ��Ӧ��ʽ�ַ���
        		qtzlmc = qtzlmc.replaceAll(" ", "&nbsp;");
        		qtzlmc = qtzlmc.replaceAll("\r\n", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\r", "<br/>");
        		qtzlmc = qtzlmc.replaceAll("\n", "<br/>");
        		
        		badjb.getHtxx().setQtzlmc(qtzlmc);
    		}
        	// ��vo����ת����bo���󹩺�̨ʹ��
            ActionHelper.XMLVO2BO(badjb, bo);
            
            dzyj.setYwdm(badjb.getYwlx());
            dzyj.setYwczlx(badjb.getYwczlx());

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, bo);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, badjb);


            // ����VoPackage
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_SAVE);
            vo.setProcessor(ProcessorNames.KJQYSDS_BADJB_PROCESSOR);
            vo.setData(pData);
            vo.setUserData(ud);
            // ���ú�̨��ѯ����
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
	                    
	    				// ��badjForm ����request��
	            		request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
	                            bo.getMessage());
	            		
	            		return CAConstants.SHOW;
	    			}
            	}
            	request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY, "");
            }

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                    "���Ϻ�ͬ�ǼǱ�������ɹ����밴�涨������˰����ر���ֽ�ơ��۽���ҵ����˰��ͬ�����ǼǱ�����ͬ��ӡ�����ı�Ϊ���ĵģ�ͬʱ���������뱾�����Ǿ�����ҵ��Ȩί���鼰����������ϡ�" + CAUtils.getTsxx(badjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,
                    "�۽���ҵ����˰��ͬ�����ǼǱ�" + CAUtils.getTsxx(badjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,
                    "/shenbao/badjb.dc?actionType=Show");
            LogUtil.getInstance().log(ud, "�۽���ҵ����˰��ͬ�����ǼǱ�", badjb.getTbrq(), "�ɹ�!");

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
            // /3.9.���ÿ�xml��ת��ʧ��ҳ��
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, badjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, badjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, badjb.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(ud, "�۽���ҵ����˰��ͬ�����ǼǱ�", badjb.getTbrq(), "ʧ��!");

            return CAConstants.SHOW;
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
            HttpServletResponse response) throws BaseException {
        return doShowModify(request, response);
    }

}
