/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.xml.sax.InputSource;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.yhsgl.processor.xml4YHS;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ������۵�λӡ��˰������� Action</p>
 * @author �������飭�������
 * @version 1.1
 */

public class YhsxsdrAction
    extends SmsbAction
{
    UserData userData = null;

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>������۵�λӡ��˰�������");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/Yhsxsdr-000.htm");
    }

    /**
     * ��ʾ����
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsxsdrForm yForm = (YhsxsdrForm) aForm;
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���봦��
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doLoad (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        VOPackage vo = new VOPackage();
        YhsxsdrForm yForm = (YhsxsdrForm) aForm;

        vo.setAction(CodeConstant.SMSB_LOADACTION);
        vo.setProcessor(CodeConstant.YHSGL_XSDR_PROCESSOR);
        vo.setData(yForm);
        yForm.reset(mapping, request);

        //���µ�ʱ�������
        userData = this.getUserData(request);
        yForm.setLrr(String.valueOf(userData.getYhid()));
        //���������ļ��õ�����
        try
        {
            yForm = (YhsxsdrForm) doHandleXMLFile(yForm);
            request.getSession().setAttribute(CodeConstant.
                                              SESSIONKEY_YHSDATALIST,
                                              yForm.getDataList());
            request.getSession().setAttribute(CodeConstant.SESSIONKEY_XSLIST,
                                              yForm.getXsList());

            //��ҳ
            yForm.setPgNum(1);
            List pgList = yForm.getDataList();
            yForm.setLength(CodeConstant.YHS_PG_LENGTH);
            yForm.setPgSum(pgList.size() % yForm.getLength() == 0 ?
                           pgList.size() / yForm.getLength() :
                           pgList.size() / yForm.getLength() + 1);

            //�õ��ϼ���
            int gpslhj = 0;
            float jehj = 0;
            for (int i = 0; i < yForm.getDataList().size(); i++)
            {
                HashMap map = (HashMap) yForm.getDataList().get(i);
                jehj += Float.parseFloat( (String) map.get("je"));
                gpslhj += Integer.parseInt( (String) map.get("gpsl"));
            }
            int loc = String.valueOf(jehj).indexOf(".");
            yForm.setGpslhj(String.valueOf(gpslhj));
            yForm.setHjje(String.valueOf(jehj).substring(0, loc + 2));

        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "���鵼����������ݣ�");
        }

        //���Ǽǽӿ�,��ô��۵�λ��Ϣ
        try
        {
            com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.
                bjtax.
                dj.proxy.ServiceProxy();
            HashMap ghdwMap = djProxy.getDjInfo(yForm.getDsjsjdm(),
                                                this.getUserData(request));
            SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
            yForm.setDsdwmc(swdjjbsj.getNsrmc());
            yForm.setDsdwlxdh(swdjjbsj.getJydzlxdm());
            yForm.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), yForm);
            return mapping.findForward("Load");
        }
        catch (Exception e2)
        {
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            throw ExceptionUtil.getBaseException(e2, "���鵼���ļ��еĴ��۵�λ����������Ƿ���ȷ��");
        }
    }

    /**
     * ���ܴ���
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doHzjks (ActionMapping mapping,
                                  ActionForm aForm, HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        YhsxsdrForm yForm = (YhsxsdrForm) aForm;
        yForm.setDataList( (ArrayList) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_YHSDATALIST));
        yForm.setXsList( (ArrayList) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_XSLIST));
        VOPackage vo = new VOPackage();
        vo.setProcessor(CodeConstant.YHSGL_XSDR_PROCESSOR);
        vo.setData(yForm);
        vo.setAction(CodeConstant.SMSB_HZJKSACTION);
        vo.setUserData(this.getUserData(request));

        try
        {//�������ܽɿ���
            HashMap map = new HashMap();
            map = (HashMap) ZhsbProxy.getInstance().process(vo);
            YhsxshzForm hzForm = new YhsxshzForm();
            hzForm.setDsjsjdm(yForm.getDsjsjdm());
            hzForm.setDsdwmc(String.valueOf(map.get("dsdwmc")));
            hzForm.setJkpzh(String.valueOf(map.get("jkpzh")));
            hzForm.setSjse(String.valueOf(map.get("sjse")));
            request.setAttribute("yhsxshzForm", hzForm);
            return mapping.findForward("Hzjks");
        }
        catch (Exception e)
        {
            yForm.reset(mapping, request);
            userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��������
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doCxjks (ActionMapping mapping,
                                  ActionForm aForm, HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        try
        {//��ת�������ɿ���
            YhsxsdrForm form = (YhsxsdrForm) aForm;
            YhsxshzcxForm yForm = new YhsxshzcxForm();
            yForm.reset(mapping, request);
            yForm.setDsjsjdm(form.getDsjsjdm());
            request.setAttribute("yhsxshzcxForm", yForm);
            return mapping.findForward("Cxjks");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ����ѡ���ҳ
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doGotopage (ActionMapping mapping,
                                     ActionForm aForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsxsdrForm yForm = (YhsxsdrForm) aForm;
            yForm.setDataList( (ArrayList) request.getSession().getAttribute(
                CodeConstant.SESSIONKEY_YHSDATALIST));
            return mapping.findForward("Gotopage");//����ѡ���ҳ
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ����XML�ļ�
     * @param form The optional ActionForm bean for this request (if any)
     * @return The optional ActionForm bean for this request (if any)
     * @throws BaseException
     */
    private ActionForm doHandleXMLFile (YhsxsdrForm form)
        throws BaseException
    {
        FormFile formFile = form.getTheFile();
        try
        {
            //��õ�������
            InputSource theFile = new InputSource(formFile.getInputStream());
            //�������ݵõ������¼
            xml4YHS parser = new xml4YHS(theFile);
            form.setDataList(parser.yhsList); //��������
            form.setDsjsjdm(parser.dsjsjdm); //���۵�λ���������
            form.setXsList(parser.xsList); //����ƾ֤������Ӧ�ĺϼƽ��
            return form;
        }
        catch (ApplicationException aEx)
        {
            throw ExceptionUtil.getBaseException(aEx);
        }
        catch (Exception ex)
        {
            throw new ApplicationException("��ǰ�����xml�ļ���ʽ�����ݴ���!");
        }
    }
}