//Source file: F:\\Generated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsnd\\web\\CzzsndAction.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.web;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.CzzsndMapConstant;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzsj;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import java.util.Date;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import java.util.*;

/**
 * @author Haifeng Su
 * @version 1.0
 *
 * �������ո��˶��ʺͺϻ���ҵ����걨
 */
public class CzzsndAction extends ShenbaoAction
{
    // ת����
    public static final String SELF = "Show";
    public static final String FAILING = "Failing";
    public static final String SUCCESS = "Success";

    /**
     * Ȩ�޿���
     * @return int
     */
    protected int getActionID()
    {
        return SbzlAccess.CZND;
    }

    /**
     * �����걨��Ϣ
     * 1��ȡ��CzzsndForm������һ��VOPackage��
     * 2������CzzsndForm��collect������ҳ���ύ����������ת����ֵ����ŵ�qysbsj
     * 	��tzzsbsjList�Լ�fpblsjList�С�
     * 3������CzzsndForm��beforeSave����ȡ�ñ����걨��Ϣ�Ĳ�����
     * 	������ΪvoPackage��data��
     * 4������voPackage��type��Ϊ
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsndProcessor��
     * 	voPackage��action��ΪCzzsndMapConstant.INT_ACTION_SAVE����ShenBaoProxy��
     * 	process������
     * 5��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	CzzsjdForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }

        CzzsndForm czzsndForm = (CzzsndForm)form;
        // ��Form ȡ���豣����������
        Map data = czzsndForm.beforeSave(this.getUserData(request).yhid);
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setAction(CzzsndActionConstant.INT_ACTION_SAVE);
        voPackage.setData(data);
        voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
        voPackage.setUserData(this.getUserData(request));
        try
        {
            // ���ú�̨��������
            ShenbaoProxy.getInstance().process(voPackage);

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "������������걨���ϱ���ɹ���");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��CzzsndForm������һ��VOPackage��
     * 2������CzzsndForm��beforeQuery����ȡ��ȡҵ�����ݵĲ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊ
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsndProcessor��
     * 	voPackage��action��ΪCzzsndMapConstant.INT_ACTION_QUERY����ShenBaoProxy
     * 	��process������
     * 4������CzzsndForm��afterQuery��������ȡҵ�����ݵĽ����
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
        // ȡ�ü��������
        String jsjdm = this.getUserData(request).yhid;
        // ȡ��CzzsndForm
        CzzsndForm czzsndForm = (CzzsndForm)form;

        try
        {
            // ͨ��˰�ѵĽӿ�ȡͶ�ʷ�����
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            // ȡ��һ�ꡣ
            Calendar now = Calendar.getInstance();
            now.add(Calendar.YEAR, -1);
            now.set(Calendar.MONTH, Calendar.DECEMBER);
            now.set(Calendar.DAY_OF_MONTH, 31);
            List tzfList = sfProxy.getTzfInfo(jsjdm, now.getTime());
            if(tzfList == null || tzfList.size() == 0)
            {
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "����Ͷ�ʷ�����Ϊ�գ�");
                return mapping.findForward(SUCCESS);
            }
            else
            {
                setCodeTable(request, czzsndForm);

                // ����VOPackage
                VOPackage voPackage = new VOPackage();
                voPackage.setAction(CzzsndActionConstant.INT_ACTION_QUERY);
                voPackage.setUserData(this.getUserData(request));
                voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
                voPackage.setData(czzsndForm.beforeQuery(jsjdm, tzfList));
                voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);

                // ��ΪCzzsnbtzzsj�����ݿ��еĶ�Ӧ�����ṩ����Ͷ���������ĵط�
                // ���������Ͷ�������������Ա�ҳ����ʾ
                Map data = (Map)voPackage.getData();
                // �ӷ���ֵȡ��Ͷ�����걨����
                List tzzsbsjList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);

                for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
                {
                    // ȡ��ÿ��Ͷ�����걨����
                    Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj)tzzsbsjList.get(i);
                    // ѭ����˰��ȡ����Ͷ��������
                    for(int j = 0, jSize = tzfList.size(); j < jSize; j++)
                    {
                        // ȡ��Ͷ��������
                        Tzf tzf = (Tzf)tzfList.get(j);
                        // �ж��Ƿ�֤�����ʹ����֤�����붼���
                        if(czzsnbtzzsj.getZjhm().equalsIgnoreCase(tzf.getZjhm())
                           && czzsnbtzzsj.getZjlxdm().equalsIgnoreCase(tzf.getZjlxdm()))
                        {
                            // ���ʱ����Ͷ�����걨���ݵ�Ͷ���������ֶ�
                            czzsnbtzzsj.setTzzxm(tzf.getTzfmc());
                            // �������ҵ�ǰͶ�����걨�����е�Ͷ��������ѭ��
                            break;
                        }
                    }
                }

                czzsndForm.afterQuery(jsjdm, data);
                czzsndForm.setDone(true);
                return(mapping.findForward(SELF));
            }
        }
        catch(Exception e)
        {
            czzsndForm.setDone(false);
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ɾ���걨��Ϣ
     * 1��ȡ��CzzsndForm������һ��VOPackage��
     * 2������CzzsndForm��beforeDelete����ȡ��ɾ���걨��Ϣ�Ĳ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊ
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsndProcessor��
     * 	voPackage��action��ΪCzzsndMapConstant.INT_ACTION_DELETE����
     * 	ShenBaoProxy��process������
     * 4��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	CzzsjdForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if(forward != null)
        {
            return forward;
        }

        // ȡ��Form
        CzzsndForm czzsndForm = (CzzsndForm)form;
        // ��Form ȡ����ɾ����������
        Map data = czzsndForm.beforeDelete(this.getUserData(request).yhid);
        // ����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setAction(CzzsndActionConstant.INT_ACTION_DELETE);
        voPackage.setData(data);
        voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
        voPackage.setUserData(this.getUserData(request));
        try
        {
            // ���ú�̨ɾ������
            ShenbaoProxy.getInstance().process(voPackage);

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "������������걨��ɾ���ɹ���");
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
     * @param czzsndForm CzzsndForm
     */
    private void setCodeTable(HttpServletRequest request,
                              CzzsndForm czzsndForm)
    {
        // ȡ�ô����
        List szsmList = CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST_AVAILABLE);

        // ��Ϊ����������ֻ�õ�����һ���֣�Ϊ�˼���ҳ������������˳����貿��
        List czzsndSzsmList = new ArrayList();
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
                czzsndSzsmList.add(szsm);
            }
        }
        // ���õ�formȥ
        czzsndForm.setSlbsjList(czzsndSzsmList);
    }
}