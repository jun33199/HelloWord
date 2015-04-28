package com.ttsoft.bjtax.shenbao.sbzl.czzsjd.web;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import com.ttsoft.common.model.UserData;

import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdMapConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;

import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * �������ռ���
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class CzzsjdAction extends ShenbaoAction
{

    // ת��ĳ����Ķ���
    public static final String SELF = "Show";
    public static final String FAILING = "Failing";
    public static final String SUCCESS = "Success";

    /**
     * Ȩ�޿���
     * @return int
     */
    protected int getActionID()
    {
        return SbzlAccess.CZJD;
    }

    /**
     * �����걨��Ϣ
     * 1��ȡ��CzzsjdForm������һ��VOPackage��
     * 2������CzzsjdForm��beforeSave����ȡ�ñ����걨��Ϣ�Ĳ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊ
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CZZSJD_PROCESSOR��
     * 	voPackage��action��ΪCzzsjdActionConstant.INT_ACTION_SAVE����
     * 	ShenBaoProxy��process������
     * 4��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	CzzsjdForm
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
        CzzsjdForm czzsjdForm = (CzzsjdForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.CZZSJD_PROCESSOR);
        voPackage.setAction(CzzsjdActionConstant.INT_ACTION_SAVE);

        try
        {
            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);
            setCodeTable(request, czzsjdForm);
            voPackage.setData(czzsjdForm.beforeSave(userData.yhid));
            ShenbaoProxy.getInstance().process(voPackage);

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "�������ռ����걨�ɹ���");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsjdForm������һ��VOPackage��
     * 2������CzzsjdForm��beforeQuery����ȡ��ȡҵ�����ݵĲ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊ
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsjdProcessor��
     * 	voPackage��action��ΪCzzsjdActionConstant.INT_ACTION_QUERY
     * 	����ShenBaoProxy��process������
     * 4������CzzsjdActionForm��afterQuery��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	CzzsjdForm
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
        CzzsjdForm czzsjdForm = (CzzsjdForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.CZZSJD_PROCESSOR);
        voPackage.setAction(CzzsjdActionConstant.INT_ACTION_QUERY);

        try
        {
            UserData userData = this.getUserData(request);
            String jsjdm = userData.yhid;
            // ͨ��˰�ѵĽӿ�ȡͶ�ʷ�����
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            Calendar time = Calendar.getInstance();
            time.add(Calendar.MONTH, -1);
            time.set(Calendar.DAY_OF_MONTH, time.getActualMaximum(Calendar.DAY_OF_MONTH));
            List tzfList = sfProxy.getTzfInfo(jsjdm, time.getTime());
            if(tzfList == null || tzfList.size() == 0)
            {
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "����Ͷ�ʷ�����Ϊ�գ�");
                return mapping.findForward(SUCCESS);
            }
            else
            {
                voPackage.setUserData(userData);
                setCodeTable(request, czzsjdForm);
                voPackage.setData(czzsjdForm.beforeQuery(jsjdm, tzfList));
                voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);

                // ��ΪCzzsjbtzzsj�����ݿ��еĶ�Ӧ�����ṩ����Ͷ���������ĵط�
                // ���������Ͷ�������������Ա�ҳ����ʾ
                Map data = (Map)voPackage.getData();
                // �ӷ���ֵȡ��Ͷ�����걨����
                List tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);
                // ѭ��Ͷ�����걨����
                for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
                {
                    // ȡ��ÿ��Ͷ�����걨����
                    Czzsjbtzzsj czzsjbtzzsj = (Czzsjbtzzsj)tzzsbsjList.get(i);
                    // ѭ����˰��ȡ����Ͷ��������
                    for(int j = 0, jSize = tzfList.size(); j < jSize; j++)
                    {
                        // ȡ��Ͷ��������
                        Tzf tzf = (Tzf)tzfList.get(j);
                        // �ж��Ƿ�֤�����ʹ����֤�����붼���
                        if(czzsjbtzzsj.getZjhm().equalsIgnoreCase(tzf.getZjhm())
                           && czzsjbtzzsj.getZjlxdm().equalsIgnoreCase(tzf.getZjlxdm()))
                        {
                            // ���ʱ����Ͷ�����걨���ݵ�Ͷ���������ֶ�
                            czzsjbtzzsj.setTzzxm(tzf.getTzfmc());
                            // �������ҵ�ǰͶ�����걨�����е�Ͷ��������ѭ��
                            break;
                        }
                    }
                }

                czzsjdForm.afterQuery(data);
                czzsjdForm.setDone(true);
                return(mapping.findForward(SELF));
            }
        }
        catch(Exception e)
        {
            czzsjdForm.setDone(false);
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ɾ���걨��Ϣ
     * 1��ȡ��CzzsjdForm������һ��VOPackage��
     * 2������CzzsjdForm��beforeDelete����ȡ��ɾ���걨��Ϣ�Ĳ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊ
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsjdProcessor��
     * 	voPackage��action��ΪCzzsjdActionConstant.INT_ACTION_DELETE
     * 	����ShenBaoProxy��process������
     * 4��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	CzzsjdForm
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
        CzzsjdForm czzsjdForm = (CzzsjdForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.CZZSJD_PROCESSOR);
        voPackage.setAction(CzzsjdActionConstant.INT_ACTION_DELETE);

        try
        {
            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);
            setCodeTable(request, czzsjdForm);
            voPackage.setData(czzsjdForm.beforeDelete(userData.yhid));
            ShenbaoProxy.getInstance().process(voPackage);

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "�������ռ���ɾ���ɹ���");
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
     * @param czzsjdForm CzzsjdForm
     */
    private void setCodeTable(HttpServletRequest request, CzzsjdForm czzsjdForm)
    {
        // ȡ�ô����
        List szsmList = CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST_AVAILABLE);

        // ��Ϊ����������ֻ�õ�����һ���֣�Ϊ�˼���ҳ������������˳����貿��
        List grsdsSzsmList = new ArrayList();
        for(int i = 0, size = szsmList.size(); i < size; i++)
        {
            // ȡÿһ��˰��˰Ŀ
            Szsm szsm = (Szsm)szsmList.get(i);
            if(!szsm.getSzsmdm().startsWith(SzsmdmConstant.GTGSH))
            {
                // �Ǹ�������˰��˰��˰Ŀ����ѭ��
                continue;
            }
            else if(szsm.getSzsmdm().length() == 4)
            {
                // �Ǹ�������˰��˰�ּ���ѭ��
                continue;
            }
            else if(szsm.getSl() == null)
            {
                // �Ǹ�������˰��û��˰�ʵ�˰��˰Ŀ����ѭ��
                continue;
            }
            else
            {
                // ��������˰��˰��˰Ŀ����
                grsdsSzsmList.add(szsm);
            }
        }

        czzsjdForm.setSlbsjList(grsdsSzsmList);
    }
}