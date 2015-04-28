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
 * 查账征收季度
 *
 * @author Haifeng Su
 * @version 1.0
 */
public class CzzsjdAction extends ShenbaoAction
{

    // 转向的常量的定义
    public static final String SELF = "Show";
    public static final String FAILING = "Failing";
    public static final String SUCCESS = "Success";

    /**
     * 权限控制
     * @return int
     */
    protected int getActionID()
    {
        return SbzlAccess.CZJD;
    }

    /**
     * 保存申报信息
     * 1、取得CzzsjdForm，产生一个VOPackage。
     * 2、调用CzzsjdForm的beforeSave方法取得保存申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CZZSJD_PROCESSOR，
     * 	voPackage的action域为CzzsjdActionConstant.INT_ACTION_SAVE调用
     * 	ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
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

        //获取Form对象
        CzzsjdForm czzsjdForm = (CzzsjdForm)form;
        //生成VOPackage
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

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "查账征收季度申报成功！");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 取业务信息
     * 1、取得CzzsjdForm，产生一个VOPackage。
     * 2、调用CzzsjdForm的beforeQuery方法取得取业务数据的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsjdProcessor，
     * 	voPackage的action域为CzzsjdActionConstant.INT_ACTION_QUERY
     * 	调用ShenBaoProxy的process方法。
     * 4、调用CzzsjdActionForm的afterQuery方法设置取业务数据的结果。
     * 5、转向业务页面。
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
        //获取Form对象
        CzzsjdForm czzsjdForm = (CzzsjdForm)form;
        //生成VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setProcessor(ProcessorNames.CZZSJD_PROCESSOR);
        voPackage.setAction(CzzsjdActionConstant.INT_ACTION_QUERY);

        try
        {
            UserData userData = this.getUserData(request);
            String jsjdm = userData.yhid;
            // 通过税费的接口取投资方数据
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            Calendar time = Calendar.getInstance();
            time.add(Calendar.MONTH, -1);
            time.set(Calendar.DAY_OF_MONTH, time.getActualMaximum(Calendar.DAY_OF_MONTH));
            List tzfList = sfProxy.getTzfInfo(jsjdm, time.getTime());
            if(tzfList == null || tzfList.size() == 0)
            {
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "您的投资方数据为空！");
                return mapping.findForward(SUCCESS);
            }
            else
            {
                voPackage.setUserData(userData);
                setCodeTable(request, czzsjdForm);
                voPackage.setData(czzsjdForm.beforeQuery(jsjdm, tzfList));
                voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);

                // 因为Czzsjbtzzsj在数据库中的对应表不再提供保存投资者姓名的地方
                // 所以这里把投资者姓名填上以便页面显示
                Map data = (Map)voPackage.getData();
                // 从返回值取得投资者申报数据
                List tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);
                // 循环投资者申报数据
                for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
                {
                    // 取出每条投资者申报数据
                    Czzsjbtzzsj czzsjbtzzsj = (Czzsjbtzzsj)tzzsbsjList.get(i);
                    // 循环从税费取到的投资者数据
                    for(int j = 0, jSize = tzfList.size(); j < jSize; j++)
                    {
                        // 取出投资者数据
                        Tzf tzf = (Tzf)tzfList.get(j);
                        // 判断是否证件类型代码和证件号码都相等
                        if(czzsjbtzzsj.getZjhm().equalsIgnoreCase(tzf.getZjhm())
                           && czzsjbtzzsj.getZjlxdm().equalsIgnoreCase(tzf.getZjlxdm()))
                        {
                            // 相等时设置投资者申报数据的投资者姓名字段
                            czzsjbtzzsj.setTzzxm(tzf.getTzfmc());
                            // 结束查找当前投资者申报数据中的投资者姓名循环
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
     * 删除申报信息
     * 1、取得CzzsjdForm，产生一个VOPackage。
     * 2、调用CzzsjdForm的beforeDelete方法取得删除申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsjdProcessor，
     * 	voPackage的action域为CzzsjdActionConstant.INT_ACTION_DELETE
     * 	调用ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
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

        //获取Form对象
        CzzsjdForm czzsjdForm = (CzzsjdForm)form;
        //生成VOPackage
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

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "查账征收季度删除成功！");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 设置代码表到form。
     * @param request HttpServletRequest
     * @param czzsjdForm CzzsjdForm
     */
    private void setCodeTable(HttpServletRequest request, CzzsjdForm czzsjdForm)
    {
        // 取得代码表
        List szsmList = CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST_AVAILABLE);

        // 因为代码表的数据只用到其中一部分，为了减少页面代码量，过滤出所需部分
        List grsdsSzsmList = new ArrayList();
        for(int i = 0, size = szsmList.size(); i < size; i++)
        {
            // 取每一条税种税目
            Szsm szsm = (Szsm)szsmList.get(i);
            if(!szsm.getSzsmdm().startsWith(SzsmdmConstant.GTGSH))
            {
                // 非个人所得税的税种税目继续循环
                continue;
            }
            else if(szsm.getSzsmdm().length() == 4)
            {
                // 是个人所得税的税种继续循环
                continue;
            }
            else if(szsm.getSl() == null)
            {
                // 是个人所得税的没有税率的税种税目继续循环
                continue;
            }
            else
            {
                // 个人所得税的税种税目保存
                grsdsSzsmList.add(szsm);
            }
        }

        czzsjdForm.setSlbsjList(grsdsSzsmList);
    }
}