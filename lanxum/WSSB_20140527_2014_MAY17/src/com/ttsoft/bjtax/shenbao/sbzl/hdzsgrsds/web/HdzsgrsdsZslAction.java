//Source file: E:\\Generate Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\hdzsgrsds\\web\\HdzsgrsdsZslAction.java

package com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsMapConstant;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzstzzsb;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;

/**
 * �˶����ո��˶��ʸ��˺ϻ��������˰�걨��_������
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class HdzsgrsdsZslAction extends ShenbaoAction
{

    // ת��ĳ����Ķ���
    public static final String SELF = "Show";
    public static final String FAILING = "Failing";
    public static final String SUCCESS = "Success";
    // ˰��˰Ŀ���˵ĳ�������
    public static final String SZSMDM_FILTER = "0512";

    /**
     * Ȩ�޿���
     * @return int
     */
    protected int getActionID()
    {
        return SbzlAccess.HDZSL;
    }

    /**
     * �����걨��Ϣ
     * 1��ȡ��HdzsgrsdsZslForm������һ��VOPackage��
     * 2������HdzsgrsdsZslForm��beforeSave����ȡ�ñ����걨��Ϣ�Ĳ�����
     * 	������ΪvoPackage��data��
     * 3������voPackage��type��Ϊ
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.HdzsgrsdsProcessor��
     * 	voPackage��action��ΪHdzsgrsdsActionConstant.INT_ACTION_SAVE
     * 	����ShenBaoProxy��process������
     * 4��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	HdzsgrsdsZslForm
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
        HdzsgrsdsZslForm hdzsgrsdsForm = (HdzsgrsdsZslForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.HDZSGRSDS_PROCESSOR);
        voPackage.setAction(HdzsgrsdsActionConstant.INT_ACTION_SAVE);

        try
        {
            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);

            setCodeTable(request, hdzsgrsdsForm);
            voPackage.setData(hdzsgrsdsForm.beforeSave(userData.yhid));
            voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "�˶������걨�ɹ���");
            return mapping.findForward(SUCCESS);
        } catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��HdzsgrsdsZslForm������һ��VOPackage��
     * 2������HdzsgrsdsZslForm��beforeQuery����ȡ��ȡҵ�����ݵĲ�����������ΪvoPackage\uFFFD
     * data��
         * 3������voPackage��type��Ϊcom.ttsoft.bjtax.shenbao.constant.ProcessorNames.Hdzsg
         * rsdsProcessor��voPackage��action��ΪHdzsgrsdsActionConstant.INT_ACTION_QUERY����
     * ShenBaoProxy��process������
     * 4������HdzsgrsdsZslForm��afterQuery��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	HdzsgrsdsZslForm
     * @param request	HttpServletRequest
     * @param response	HttpServletResponse
     * @return ActionForward struts.action.ActionForward
     * @throws BaseException
     */
    public ActionForward doShow(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
        BaseException
    {
        //��ȡForm����
        HdzsgrsdsZslForm hdzsgrsdsForm = (HdzsgrsdsZslForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.HDZSGRSDS_PROCESSOR);
        voPackage.setAction(HdzsgrsdsActionConstant.INT_ACTION_QUERY);

        try
        {
            UserData userData = this.getUserData(request);
            String jsjdm = userData.yhid;
            // ͨ��˰�ѵĽӿ�ȡͶ�ʷ�����
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List tzfList = sfProxy.getTzfInfo(jsjdm, new Date());

            //���幤�̻���Ӫ����
            /** @todo
             * �ȴ�˰�Ѻ�����ȷ��ȡ˰�ѵļ������ݽӿڵĲ������ĸ�˰��˰Ŀ����*/
            String CODE_GTGSH = "0512";
            // ����˰�ѵĽӿ�ȡ��������
            String result = sfProxy.getJmsbs(jsjdm, CODE_GTGSH, new Date(), new Date());

            if(tzfList == null || tzfList.size() == 0)
            {
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                     "����Ͷ�ʷ�����Ϊ�գ�");
                return mapping.findForward(SUCCESS);
            } else
            {
                voPackage.setUserData(userData);
        setCodeTable(request, hdzsgrsdsForm);
                /** @todo ��˰�ѵĽӿڿ���ȡ��������ʱ��������滻 */
                voPackage.setData(hdzsgrsdsForm.beforeQuery(jsjdm, tzfList, "0.2"));
                voPackage = (VOPackage)ShenbaoProxy.getInstance().process(
                    voPackage);
                Map data = (Map)voPackage.getData();
                List jmsjList = new ArrayList(1);
                jmsjList.add(result == null ? Boolean.FALSE : Boolean.TRUE);
                data.put(HdzsgrsdsMapConstant.LIST_JMSJ, jmsjList);

                List tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.
                                                  LIST_TZZSBSJ);
                for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
                {
                    Hdzstzzsb hdzstzzsb = (Hdzstzzsb)tzzsbsjList.get(i);
                    for(int j = 0, jSize = tzfList.size(); j < jSize; j++)
                    {
                        Tzf tzf = (Tzf)tzfList.get(j);
                        if(hdzstzzsb.getZjhm().equalsIgnoreCase(tzf.getZjhm())
                           &&
                           hdzstzzsb.getZjlxdm().equalsIgnoreCase(tzf.getZjlxdm()))
                        {
                            hdzstzzsb.setTzzxm(tzf.getTzfmc());
                            break;
                        }
                    }
                }

                hdzsgrsdsForm.afterQuery(jsjdm, data);
                hdzsgrsdsForm.setDone(true);
                return(mapping.findForward(SELF));
            }
        } catch(Exception e)
        {
            hdzsgrsdsForm.setDone(false);
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ɾ���걨��Ϣ
     * 1��ȡ��HdzsgrsdsZslForm������һ��VOPackage��
     * 2������HdzsgrsdsZslForm��beforeDelete����ȡ��ɾ���걨��Ϣ�Ĳ�����������ΪvoPacka
     * ge��data��
         * 3������voPackage��type��Ϊcom.ttsoft.bjtax.shenbao.constant.ProcessorNames.Hdzsg
     * rsdsProcessor��voPackage��action��ΪHdzsgrsdsActionConstant.INT_ACTION_DELETE��\uFFFD
     * ShenBaoProxy��process������
     * 4��ת��������ҳ�档
     * @param mapping 	struts.action.ActionMapping
     * @param form	HdzsgrsdsZslForm
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
        HdzsgrsdsZslForm hdzsgrsdsForm = (HdzsgrsdsZslForm)form;
        //����VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.HDZSGRSDS_PROCESSOR);
        voPackage.setAction(HdzsgrsdsActionConstant.INT_ACTION_DELETE);

        try
        {
            UserData userData = this.getUserData(request);
            voPackage.setUserData(userData);
            setCodeTable(request, hdzsgrsdsForm);
            voPackage.setData(hdzsgrsdsForm.beforeDelete(userData.yhid));
            voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "�˶�����ɾ���ɹ���");
            return mapping.findForward(SUCCESS);
        } catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * ���ô����form��
     * @param request HttpServletRequest
     * @param hdzsgrsdsForm HdzsgrsdsZslForm
     */
    private void setCodeTable(HttpServletRequest request,
                              HdzsgrsdsZslForm hdzsgrsdsForm)
    {
        List szsmList = CodeTableUtil.getCodeTableList(request,
            CodeTable.SZSM_LIST);
        List grsdsSzsmList = new ArrayList();
        for(int i = 0, size = szsmList.size(); i < size; i++)
        {
            Szsm szsm = (Szsm)szsmList.get(i);
            if(!szsm.getSzsmdm().startsWith(SZSMDM_FILTER))
            {
                continue;
            } else if(szsm.getSzsmdm().length() == 4)
            {
                continue;
            } else if(szsm.getSl() == null)
            {
                continue;
            } else
            {
                grsdsSzsmList.add(szsm);
            }
        }
        hdzsgrsdsForm.setSlbsjList(grsdsSzsmList);
    }

}