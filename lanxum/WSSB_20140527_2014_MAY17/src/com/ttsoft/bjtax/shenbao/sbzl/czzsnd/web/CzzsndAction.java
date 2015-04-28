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
 * 查账征收个人独资和合伙企业年度申报
 */
public class CzzsndAction extends ShenbaoAction
{
    // 转向常量
    public static final String SELF = "Show";
    public static final String FAILING = "Failing";
    public static final String SUCCESS = "Success";

    /**
     * 权限控制
     * @return int
     */
    protected int getActionID()
    {
        return SbzlAccess.CZND;
    }

    /**
     * 保存申报信息
     * 1、取得CzzsndForm，产生一个VOPackage。
     * 2、调用CzzsndForm的collect方法把页面提交过来的数据转换成值对象放到qysbsj
     * 	和tzzsbsjList以及fpblsjList中。
     * 3、调用CzzsndForm的beforeSave方法取得保存申报信息的参数，
     * 	设置它为voPackage的data域。
     * 4、设置voPackage的type域为
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsndProcessor，
     * 	voPackage的action域为CzzsndMapConstant.INT_ACTION_SAVE调用ShenBaoProxy的
     * 	process方法。
     * 5、转向操作结果页面。
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
        // 从Form 取得需保存数据数据
        Map data = czzsndForm.beforeSave(this.getUserData(request).yhid);
        // 构造VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setAction(CzzsndActionConstant.INT_ACTION_SAVE);
        voPackage.setData(data);
        voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
        voPackage.setUserData(this.getUserData(request));
        try
        {
            // 调用后台保存数据
            ShenbaoProxy.getInstance().process(voPackage);

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "查账征收年度申报资料保存成功！");
            return mapping.findForward(SUCCESS);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 取业务信息
     * 1、取得CzzsndForm，产生一个VOPackage。
     * 2、调用CzzsndForm的beforeQuery方法取得取业务数据的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsndProcessor，
     * 	voPackage的action域为CzzsndMapConstant.INT_ACTION_QUERY调用ShenBaoProxy
     * 	的process方法。
     * 4、调用CzzsndForm的afterQuery方法设置取业务数据的结果。
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
        // 取得计算机代码
        String jsjdm = this.getUserData(request).yhid;
        // 取得CzzsndForm
        CzzsndForm czzsndForm = (CzzsndForm)form;

        try
        {
            // 通过税费的接口取投资方数据
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfProxy
                = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            // 取上一年。
            Calendar now = Calendar.getInstance();
            now.add(Calendar.YEAR, -1);
            now.set(Calendar.MONTH, Calendar.DECEMBER);
            now.set(Calendar.DAY_OF_MONTH, 31);
            List tzfList = sfProxy.getTzfInfo(jsjdm, now.getTime());
            if(tzfList == null || tzfList.size() == 0)
            {
                request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "您的投资方数据为空！");
                return mapping.findForward(SUCCESS);
            }
            else
            {
                setCodeTable(request, czzsndForm);

                // 构造VOPackage
                VOPackage voPackage = new VOPackage();
                voPackage.setAction(CzzsndActionConstant.INT_ACTION_QUERY);
                voPackage.setUserData(this.getUserData(request));
                voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
                voPackage.setData(czzsndForm.beforeQuery(jsjdm, tzfList));
                voPackage = (VOPackage)ShenbaoProxy.getInstance().process(voPackage);

                // 因为Czzsnbtzzsj在数据库中的对应表不再提供保存投资者姓名的地方
                // 所以这里把投资者姓名填上以便页面显示
                Map data = (Map)voPackage.getData();
                // 从返回值取得投资者申报数据
                List tzzsbsjList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);

                for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
                {
                    // 取出每条投资者申报数据
                    Czzsnbtzzsj czzsnbtzzsj = (Czzsnbtzzsj)tzzsbsjList.get(i);
                    // 循环从税费取到的投资者数据
                    for(int j = 0, jSize = tzfList.size(); j < jSize; j++)
                    {
                        // 取出投资者数据
                        Tzf tzf = (Tzf)tzfList.get(j);
                        // 判断是否证件类型代码和证件号码都相等
                        if(czzsnbtzzsj.getZjhm().equalsIgnoreCase(tzf.getZjhm())
                           && czzsnbtzzsj.getZjlxdm().equalsIgnoreCase(tzf.getZjlxdm()))
                        {
                            // 相等时设置投资者申报数据的投资者姓名字段
                            czzsnbtzzsj.setTzzxm(tzf.getTzfmc());
                            // 结束查找当前投资者申报数据中的投资者姓名循环
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
     * 删除申报信息
     * 1、取得CzzsndForm，产生一个VOPackage。
     * 2、调用CzzsndForm的beforeDelete方法取得删除申报信息的参数，
     * 	设置它为voPackage的data域。
     * 3、设置voPackage的type域为
     * 	com.ttsoft.bjtax.shenbao.constant.ProcessorNames.CzzsndProcessor，
     * 	voPackage的action域为CzzsndMapConstant.INT_ACTION_DELETE调用
     * 	ShenBaoProxy的process方法。
     * 4、转向操作结果页面。
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

        // 取得Form
        CzzsndForm czzsndForm = (CzzsndForm)form;
        // 从Form 取得需删除数据数据
        Map data = czzsndForm.beforeDelete(this.getUserData(request).yhid);
        // 构造VOPackage
        VOPackage voPackage = new VOPackage();
        voPackage.setAction(CzzsndActionConstant.INT_ACTION_DELETE);
        voPackage.setData(data);
        voPackage.setProcessor(ProcessorNames.CZZSND_PROCESSOR);
        voPackage.setUserData(this.getUserData(request));
        try
        {
            // 调用后台删除数据
            ShenbaoProxy.getInstance().process(voPackage);

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "查账征收年度申报表删除成功！");
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
     * @param czzsndForm CzzsndForm
     */
    private void setCodeTable(HttpServletRequest request,
                              CzzsndForm czzsndForm)
    {
        // 取得代码表
        List szsmList = CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST_AVAILABLE);

        // 因为代码表的数据只用到其中一部分，为了减少页面代码量，过滤出所需部分
        List czzsndSzsmList = new ArrayList();
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
                czzsndSzsmList.add(szsm);
            }
        }
        // 设置到form去
        czzsndForm.setSlbsjList(czzsndSzsmList);
    }
}