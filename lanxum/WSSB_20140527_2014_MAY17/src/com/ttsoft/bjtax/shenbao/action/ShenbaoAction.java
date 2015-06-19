package com.ttsoft.bjtax.shenbao.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.framework.action.BaseAction;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �걨ģ��action����</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */

public abstract class ShenbaoAction extends BaseAction
{
    public ShenbaoAction()
    {
    }

    /**
     * �걨���Ϲ��ܹ���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    protected ActionForward filter(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        // ���Ȩ��
        if (!SbzlAccess.getAuthority(getActionID(), request))
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "��û��Ȩ��ʹ���������");
            return mapping.findForward("Success");
        }
        return null;
    }

    protected int getActionID()
    {
        // ����ͨ��
        return SbzlAccess.ALWAYSPASS;
    }

    /**
     * action ����ں���
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException, ServletException
    {
        try
        {
            //Ȩ�޼��
            ActionForward f = filter(mapping, form, request, response);
            if (f != null)
            {
                com.ttsoft.common.util.Debug.out(mapping.getPath() + " was filted...... ");
                return f;
            }
            //��ȡaction.
            String actionType = "";
            actionType = ((BaseForm)form).getActionType();

            //����Form��action���ò�ͬ�ķ�����

            String methodName = "do" + actionType;  //��ȡ������

            //���÷��������Ӧ����
            Class[] param = {
                Class.forName("org.apache.struts.action.ActionMapping"),
                Class.forName("org.apache.struts.action.ActionForm"),
                Class.forName("javax.servlet.http.HttpServletRequest"),
                Class.forName("javax.servlet.http.HttpServletResponse")};
            Object[] obj = {mapping, form, request, response};
            Method method = this.getClass().getMethod(methodName, param);
            ActionForward forward = (ActionForward)method.invoke(this, obj);
            saveToken(request);

            return forward;
        }
        catch(Exception e)
        {
            return processForwardByException(mapping, e, request, response);
        }
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
 /*   public ActionForward doReturn(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {

        String frompage = (String)request.getSession().getAttribute("FROMPAGE");
        if (frompage != null && !frompage.equals("")){
            removeForm(mapping, request);
            request.getSession().removeAttribute("FROMPAGE");
            return mapping.findForward(frompage);
        }else{
            removeForm(mapping, request);

            //ת�򷵻غ��ҳ�档
            return mapping.findForward("Return");
        }
    }*/
    /**
     * �ж�token�Ƿ���Ч
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @return ActionForward
     */
    protected ActionForward doHandleToken(ActionMapping mapping,
                                          HttpServletRequest request)
    {
        //�ж�token�Ƿ���Ч
        if (!isTokenValid(request))
        {
            return mapping.findForward("InvalidToken");
        }
        return null;
    }

    protected ActionForward processForwardByException(
        ActionMapping mapping,
        Exception e,
        HttpServletRequest request,
        HttpServletResponse response)
    {
        ActionErrors errors = new ActionErrors();

        if (e instanceof InvocationTargetException &&
            ((InvocationTargetException)e).getTargetException() instanceof ApplicationException)
        {
            e.printStackTrace();
            String msg = ((InvocationTargetException)e).getTargetException().getMessage();
            errors.add("err1", new ActionError("error.server.custom", msg));
        }
        else
        {
            e.printStackTrace();
            errors.add("err1", new ActionError("error.server.default"));
        }
        saveErrors(request, errors);
        return mapping.findForward("Failure");
    }
}
