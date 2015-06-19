/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsHzjksForm;
import com.ttsoft.bjtax.smsb.lszs.web.LszsZfjksForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypdsAction;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: һƱ��˰�Ĳ鿴ҳ��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshJksYpdsAction
    extends ZhsbjksypdsAction
{

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                             "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                             "<font color=\"#7C9AAB\">���幤�̻�</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "�ɿ���");

    }

    /**
     * ���ظ��幤�̻�������ҳ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doGtgshZf (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        Debug.out("--- Debug --- Here is GtgshJksYpdsAction"
                  + ".doYpds");

        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            GtgshZfjksForm pf = new GtgshZfjksForm();
            pf.setActionType("Query");
            pf.setHzfs(pf1.getHzlx()); //�������� ����ܷ�ʽ
            pf.setJsjdm(pf1.getGtgshJsjdm()); //���������
            request.setAttribute("gtgshZfjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("GtgshZf");
    }

    /**
     * ���ظ��幤�̻��Ļ���ҳ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doGtgshHz (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            GtgshHzjksForm pf = new GtgshHzjksForm();
            pf.setSbhzdh(pf1.getSbhzdh()); //�걨���ܵ���
            pf.setHzlx(pf1.getHzlx()); //��������
            pf.setHzjsrq(pf1.getHzjsrq()); //���ܽ�������
            pf.setHzksrq(pf1.getHzksrq()); //���ܿ�ʼ����
            pf.setYpys(pf1.getYpys()); //һƱһ˰��ʶ

            pf.setActionType("Query");

            request.setAttribute("gtgshHzjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("GtgshHz");
    }

    /**
     * ������ɢ֤�յ�����ҳ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doLszsZf (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            LszsZfjksForm pf = new LszsZfjksForm();
            pf.setActionType("Query");
            pf.setHzfs(pf1.getHzlx()); //�������� ����ܷ�ʽ
            pf.setJsjdm(pf1.getGtgshJsjdm()); //���������
            request.setAttribute("lszsZfjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("LszsZf");
    }

    /**
     * ������ɢ֤�յĻ���ҳ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doLszsHz (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        try
        {
            GtgshJksYpdsForm pf1 = (GtgshJksYpdsForm) form;
            LszsHzjksForm pf = new LszsHzjksForm();
            pf.setSbhzdh(pf1.getSbhzdh()); //�걨���ܵ���
            pf.setHzlx(pf1.getHzlx()); //��������
            pf.setHzjsrq(pf1.getHzjsrq()); //���ܽ�������
            pf.setHzksrq(pf1.getHzksrq()); //���ܿ�ʼ����
            pf.setYpys(pf1.getYpys()); //һƱһ˰��ʶ

            pf.setActionType("Query");
            request.setAttribute("lszsHzjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("LszsHz");
    }

}