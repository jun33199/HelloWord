package com.ttsoft.bjtax.shenbao.sbzl.wqyys.web;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
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
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import java.util.*;
import com.ttsoft.bjtax.shenbao.util.*;
import java.sql.*;
import com.ttsoft.bjtax.dj.model.*;

/**
 * ����Ӫҵ˰�걨action��֧�ְ�ʵ�걨���˶����ա����ѻ������ַ�ʽ
 */
public class WqyysAction extends ShenbaoAction
{

    // ת��ĳ����Ķ���
    public static final String SELF = "Show";
    public static final String SUCCESS = "Success";
    public static final String WQYYS = "Wqyys";

    protected int getActionID()
    {
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.WQ;
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
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        //��ȡForm����
        WqyysForm vicForm = (WqyysForm)form;

        try
        {
            HttpSession httpSession = request.getSession();

            // ���ô����form
            setCodeTable(request, vicForm);

            // ȡ��UserData
            UserData userData = this.getUserData(request);

            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);

            VOPackage voPackage = new VOPackage();
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_QUERY);

            voPackage.setData(vicForm.beforeQuery(jbsj));

            voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);

            // ���д�������ǰ̨����
            Map saveData = vicForm.afterQuery((Map)voPackage.getData(), jbsj);

            // �������ݵ�session��
            httpSession.setAttribute(WqyysForm.SHENBAO, saveData.get(WqyysForm.SHENBAO));
            httpSession.setAttribute(WqyysForm.JBXX, saveData.get(WqyysForm.JBXX));
            vicForm.setDone(true);

            // ת�򵽱�ҳ��
            String wqzsfsdm = FriendHelper.getWqzsfsInfo(request).getWqzsfsdm();
            if(wqzsfsdm.equals(CodeConstant.WQYYS_HDZS))
            {
                //�˶�����
                return(mapping.findForward(this.WQYYS + CodeConstant.WQYYS_HDZS));
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_JFHS))
            {
                //���ѻ���
                return(mapping.findForward(this.WQYYS + CodeConstant.WQYYS_JFHS));
            }
            else if(wqzsfsdm.equals(CodeConstant.WQYYS_ASSB))
            {
                //��ʵ�걨
                return(mapping.findForward(this.WQYYS + CodeConstant.WQYYS_ASSB));
            }
            else
            {
                //û���ҵ����ʵ����շ�ʽ
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "δ֪������Ӫҵ˰���շ�ʽ��");
                return mapping.findForward(SUCCESS);
            }
        }
        catch(Exception e)
        {
            vicForm.setDone(false);
            throw ExceptionUtil.getBaseException(e);
        }
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
    public ActionForward doSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }

        //��ȡForm����
        WqyysForm vicForm = (WqyysForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();

        try
        {
            HttpSession httpSession = request.getSession();
            setCodeTable(request, vicForm);
            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_SAVE);
            // ��session��ȡ������ģ�����õ�form��ȥ
            vicForm.setWqyysTemplate((Wqyys)request.getSession().getAttribute(WqyysForm.JBXX));

            // ȡ����Ĳ���
            Map data = vicForm.beforeSave(userData.yhid);
            httpSession.setAttribute(WqyysForm.SHENBAO, data.get(WqyysMapConstant.LIST_WQYYS));
            voPackage.setData(data);

            // ���ú�̨���򱣴�
            ShenbaoProxy.getInstance().process(voPackage);

            // ���session
            clearSession(request.getSession());
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "����Ӫҵ˰�걨���ϱ���ɹ���");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
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
    public ActionForward doDelete(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }

        //��ȡForm����
        WqyysForm vicForm = (WqyysForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();

        try
        {
            HttpSession httpSession = request.getSession();
            setCodeTable(request, vicForm);

            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);
            voPackage.setProcessor(ProcessorNames.WQYYS_PROCESSOR);
            voPackage.setAction(WqyysActionConstant.INT_ACTION_DELETE);

            // ��session��ȡ������ģ�����õ�form��ȥ
            vicForm.setWqyysTemplate((Wqyys)httpSession.getAttribute(WqyysForm.JBXX));

            // ȡɾ���Ĳ���
            Map data = vicForm.beforeDelete(userData.yhid);
            httpSession.setAttribute(WqyysForm.SHENBAO, data.get(WqyysMapConstant.LIST_WQYYS));
            voPackage.setData(data);

            // ���ú�̨����
            ShenbaoProxy.getInstance().process(voPackage);

            // ���session
            clearSession(request.getSession());
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "����Ӫҵ˰�걨����ɾ���ɹ���");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
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
        vicForm.setSzsmList(szsmList);
    }

    /**
     * �����쳣
     * @param mapping Struts����
     * @param e �쳣����
     * @param request ����
     * @param response ��Ӧ
     * @return ActionForward ����ֵ
     */
    public ActionForward processForwardByException(
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
            errors.add("err1", new ActionError("error.server.custom", msg));
        }
        else
        {
            // ��ͨ�쳣
            errors.add("err1", new ActionError("error.server.default"));
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
            return mapping.findForward(SUCCESS);
        }
        // �����������շ�ʽת��
        return mapping.findForward("Failure" + wqzsf.getWqzsfsdm());
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
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        clearSession(request.getSession());

        //ת�򷵻غ��ҳ�档
        return mapping.findForward("Return");
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
}