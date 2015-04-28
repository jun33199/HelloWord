/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.dzyj.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �⼮��������˰�·��걨��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DzyjcxAction
    extends SmsbAction
{
    /**
     *��ʼʱ��ʾҳ��
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        DzyjcxActionForm form = (DzyjcxActionForm) actionForm;
        
        form.setYwlxList(CodeManager.getCodeList("DM_CA_YWLX",CodeConstants.CODE_MAP_BEANLIST).getRecords());
        form.setJsjdm("");
        form.setCzlx("0");
        form.setYwlx("0");
        form.setQssj("");
        form.setJzsj("");
        
        /*
        form.setJsjdm("06003600");
        form.setQssj("20060201");
        form.setJzsj("20060228");
        */
        httpServletRequest.setAttribute("dzyjcxActionForm", form);
        
        return (new ActionForward(actionMapping.getInput()));
    }

    /**
     * ��ѯҳ��
     * 
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);
        DzyjcxActionForm form = (DzyjcxActionForm) actionForm;

        //�������ݰ�
        VOPackage vo = new VOPackage();
        //����
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //����processor
        vo.setProcessor(CodeConstant.DZYJ_PROCESSOR);
        //����actionForm
        vo.setData(form);
        //����userDate
        vo.setUserData(ud);
        try
        {
            DzyjcxActionForm retForm = (DzyjcxActionForm) ZhsbProxy.getInstance().process(vo);
            httpServletRequest.getSession(false).setAttribute("CA_DZYJSJ_QUERY_RESULT",retForm.getResultList());
            retForm.setResultList(null);
            
            httpServletRequest.setAttribute("dzyjcxActionForm", retForm);
        }
        catch (Exception ex)
        {

            form.setYwlxList(CodeManager.getCodeList("DM_CA_YWLX",CodeConstants.CODE_MAP_BEANLIST).getRecords());
            httpServletRequest.setAttribute("dzyjcxActionForm", form);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Query");
    }

    /**
     * ��ʾһ����ϸ����
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doOneItem (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        DzyjcxActionForm form = (DzyjcxActionForm) actionForm;
        
        List mxlist = (List)httpServletRequest.getSession(false).getAttribute("CA_DZYJSJ_QUERY_RESULT");
        if (mxlist == null)
        {
            throw new ApplicationException("ϵͳ���޴����ݣ�");
        }
        DzyjsjVO dzvo;
        Map sjmap = new HashMap();
        String tempstr = "";
        List ywlist = CodeManager.getCodeList("DM_CA_YWLX",CodeConstants.CODE_MAP_BEANLIST).getRecords();
        int i,j;
        
        for (i = 0; i < mxlist.size(); i++)
        {
            dzvo = (DzyjsjVO)mxlist.get(i);
            if (dzvo.getLsh() == form.getOneItemLsh())
            {
                sjmap.put("lsh",new Long(dzvo.getLsh()));
                sjmap.put("jsjdm",dzvo.getJsjdm());
                
                for (j = 0; j < ywlist.size(); j++)
                {
                    if (((LabelValueBean)ywlist.get(j)).getValue().equals(dzvo.getYwdm()))
                    {
                        tempstr = ((LabelValueBean)ywlist.get(j)).getLabel();
                        break;
                    }
                }

                sjmap.put("ywlx",tempstr);
                if (dzvo.getYwczlx().equals("1"))
                {
                    sjmap.put("czlx","����");    
                }
                else if (dzvo.getYwczlx().equals("2"))
                {
                    sjmap.put("czlx","�޸�");    
                }
                else if (dzvo.getYwczlx().equals("3"))
                {
                    sjmap.put("czlx","ɾ��");    
                }
                else 
                {
                    sjmap.put("czlx",dzvo.getYwczlx());    
                }
                
                sjmap.put("ywuid",dzvo.getYwuid());
                sjmap.put("jssj",(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(dzvo.getJssj()));
                sjmap.put("yjsj",dzvo.getDzyj());
                sjmap.put("qmsj",dzvo.getDzyjqm());
                sjmap.put("zsxlh",dzvo.getZsxlh());
                httpServletRequest.setAttribute("CA_DZQMYJSJ",sjmap);
                
                return actionMapping.findForward("ShowOne");
            }
        }
        throw new ApplicationException("ϵͳæ���޴����ݣ�");
    }
    
    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨�ɿ�</font>&gt;<font color=\"#7C9AAB\">����ԭ��</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "����ԭ����ѯ");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "");
    }
    
 
}
