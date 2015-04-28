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
 * 核定征收个人独资个人合伙个人所得税申报表_征收率
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class HdzsgrsdsZslAction extends ShenbaoAction
{

    // 转向的常量的定义
    public static final String SELF = "Show";
    public static final String FAILING = "Failing";
    public static final String SUCCESS = "Success";
    // 税种税目过滤的常量定义
    public static final String SZSMDM_FILTER = "0512";

    /**
     * 权限控制
     * @return int
     */
    protected int getActionID()
    {
        return SbzlAccess.HDZSL;
    }

    /**
     * 保存申报信息
     * 1、取得HdzsgrsdsZslForm，产生一个VOPackage。
     * 2、调用HdzsgrsdsZslForm的beforeSave方法取得保存申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.HdzsgrsdsProcessor，
     * 	voPackage的action域为HdzsgrsdsActionConstant.INT_ACTION_SAVE
     * 	调用ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
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

        //获取Form对象
        HdzsgrsdsZslForm hdzsgrsdsForm = (HdzsgrsdsZslForm)form;
        //生成VOPackage
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
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "核定征收申报成功！");
            return mapping.findForward(SUCCESS);
        } catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 取业务信息
     * 1、取得HdzsgrsdsZslForm，产生一个VOPackage。
     * 2、调用HdzsgrsdsZslForm的beforeQuery方法取得取业务数据的参数，设置它为voPackage\uFFFD
     * data域。
         * 3、设置voPackage的type域为com.ttsoft.bjtax.shenbao.constant.ProcessorNames.Hdzsg
         * rsdsProcessor，voPackage的action域为HdzsgrsdsActionConstant.INT_ACTION_QUERY调用
     * ShenBaoProxy的process方法。
     * 4、调用HdzsgrsdsZslForm的afterQuery方法设置取业务数据的结果。
     * 5、转向业务页面。
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
        //获取Form对象
        HdzsgrsdsZslForm hdzsgrsdsForm = (HdzsgrsdsZslForm)form;
        //生成VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.HDZSGRSDS_PROCESSOR);
        voPackage.setAction(HdzsgrsdsActionConstant.INT_ACTION_QUERY);

        try
        {
            UserData userData = this.getUserData(request);
            String jsjdm = userData.yhid;
            // 通过税费的接口取投资方数据
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List tzfList = sfProxy.getTzfInfo(jsjdm, new Date());

            //个体工商户经营所得
            /** @todo
             * 等待税费和需求确认取税费的减免数据接口的参数是哪个税种税目代码*/
            String CODE_GTGSH = "0512";
            // 调用税费的接口取减免数据
            String result = sfProxy.getJmsbs(jsjdm, CODE_GTGSH, new Date(), new Date());

            if(tzfList == null || tzfList.size() == 0)
            {
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                                     "您的投资方数据为空！");
                return mapping.findForward(SUCCESS);
            } else
            {
                voPackage.setUserData(userData);
        setCodeTable(request, hdzsgrsdsForm);
                /** @todo 当税费的接口可以取到征收率时把下面的替换 */
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
     * 删除申报信息
     * 1、取得HdzsgrsdsZslForm，产生一个VOPackage。
     * 2、调用HdzsgrsdsZslForm的beforeDelete方法取得删除申报信息的参数，设置它为voPacka
     * ge的data域。
         * 3、设置voPackage的type域为com.ttsoft.bjtax.shenbao.constant.ProcessorNames.Hdzsg
     * rsdsProcessor，voPackage的action域为HdzsgrsdsActionConstant.INT_ACTION_DELETE调\uFFFD
     * ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
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

        //获取Form对象
        HdzsgrsdsZslForm hdzsgrsdsForm = (HdzsgrsdsZslForm)form;
        //生成VOPackage
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
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "核定征收删除成功！");
            return mapping.findForward(SUCCESS);
        } catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }

    }

    /**
     * 设置代码表到form。
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