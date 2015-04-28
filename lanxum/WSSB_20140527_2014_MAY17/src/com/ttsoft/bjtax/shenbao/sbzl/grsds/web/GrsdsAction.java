package com.ttsoft.bjtax.shenbao.sbzl.grsds.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsmx;
import com.ttsoft.bjtax.shenbao.model.domain.Grsdsz;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.GrsdsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.grsds.SessionKey;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * @author Ding Chenggang
 * @version 1.0
 */
public class GrsdsAction extends ShenbaoAction
{

    public GrsdsAction()
    {
    }

    protected int getActionID()
    {
        return SbzlAccess.GRSDS;
    }

    /**
     * 显示
     *
     * return mapping.findForward("Show");
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        try
        {
            // 1. 取得国籍列表，证件类型列表，职业列表，个税税目列表，并放到form中去
            List gjList = CodeTableUtil.getCodeTableList(request, CodeTable.GJ_LIST);
            List zjlxList = CodeTableUtil.getCodeTableList(request, CodeTable.ZJLX_LIST);
            List zyList = CodeTableUtil.getCodeTableList(request, CodeTable.ZY_LIST);
            List szsmTotalList = CodeTableUtil.getCodeTableList(request, CodeTable.SZSM_LIST);

            GrsdsForm myForm = (GrsdsForm)form;
            myForm.setGjList(gjList);
            myForm.setZjlxList(zjlxList);
            myForm.setZyList(zyList);
            myForm.setSzsmList(getGrsdsSzsmList(szsmTotalList));
            myForm.setNsrmc(FriendHelper.getSWDJJBSJ(request).getNsrmc());

            // 设置申报日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            myForm.setSbrq(sdf.format(new Date()));

            // 设置申报所属时期
            Map skssrq = Skssrq.monthSkssrq(new Date());

            String strSkssrq = sdf.format(skssrq.get(Skssrq.SKSSKSRQ)) + "至" +
                sdf.format(skssrq.get(Skssrq.SKSSJSRQ));

            myForm.setSbsssq(strSkssrq);

            // 取计算机代码
            String jsjdm = getUserData(request).yhid;

            myForm.setJsjdm(jsjdm);

            // 取得本征期上次数据
            GrsdsHelper helper = new GrsdsHelper();
            Map info = helper.queryHistoryData(jsjdm, new Timestamp(System.currentTimeMillis()));

            List mx = null;
            Grsdsz z = null;
            if(info != null)
            {
                request.getSession().setAttribute(SessionKey.CURRENT_DATA, "random");
                // 取得本征期申报资料
                z = (Grsdsz)info.get(GrsdsMapConstant.OLDZ);
                mx = (List)info.get(GrsdsMapConstant.OLDMX);
                myForm.setBqdwzrs(z.getBqdwzrs().toString());
                myForm.setBqfdwzrs(z.getBqfdwzrs().toString());
            }

            // 取定期定额数据
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List dqdeList = null;//proxy.getGrtszygsde(jsjdm, new Date());

            List deList = filterDqdeList(dqdeList, mx);

            myForm.setDeMxList(deList);

            request.getSession().setAttribute(SessionKey.GRSDSZ, z);
            request.getSession().setAttribute(SessionKey.GRSDSMX, mx);
            myForm.setMxList(mx);

            return mapping.findForward("Show");
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    private List filterDqdeList(List dqdeList, List mxList)
    {
        List deMxList = new ArrayList();
        if (mxList == null || mxList.size() == 0)
        {
            for (int i=0; i<dqdeList.size(); i++)
            {
                Grtszygsde de = (Grtszygsde)dqdeList.get(i);
                deMxList.add(convertDqde(de));
            }
            return deMxList;
        }

        for (int i=0; i<dqdeList.size(); i++)
        {
            Grtszygsde de = (Grtszygsde)dqdeList.get(i);
            boolean found = false;
            for (int j=0; j<mxList.size(); j++)
            {
                Grsdsmx mx = (Grsdsmx)mxList.get(j);
                if (matchMxDe(mx, de))
                {
                    found = true;
                }
            }
            if (!found)
            {
                deMxList.add(convertDqde(de));
            }
        }
        return deMxList;
    }

    private Grsdsmx convertDqde(Grtszygsde de)
    {
        Grsdsmx deMx = new Grsdsmx();
        deMx.setGjdm(de.getGjdm());
        deMx.setGjmc(de.getGjmc());
        deMx.setZjlxdm(de.getZjlxdm());
        deMx.setZjlxmc(de.getZjlxmc());
        deMx.setZjhm(de.getZjhm());
        deMx.setZydm(de.getZydm());
        deMx.setZymc(de.getZymc());
        deMx.setXm(de.getXm());
        deMx.setSzsmdm(SzsmdmConstant.GRSDS_GXDE);
        deMx.setSre(new BigDecimal("0"));
        deMx.setYnssde(new BigDecimal("0"));
        deMx.setJmje(new BigDecimal("0"));
        deMx.setBqskse(de.getHdske());
        deMx.setWszh("");

        return deMx;
    }

    private List getGrsdsSzsmList(List szsmList)
    {
        List grsdsSzsmList = new ArrayList();
        for (int i=0; i<szsmList.size(); i++)
        {
            Szsm szsm = (Szsm)szsmList.get(i);
            if (szsm.getSzsmdm().startsWith("05")
                && !szsm.getSzsmdm().equals("05")
                && !szsm.getSzsmdm().startsWith("0512")
                && (szsm.getSffjs() == null || szsm.getSffjs().equals("1")))
            {
                Szsm szsmclone = new Szsm();
                szsmclone.setSzsmdm(szsm.getSzsmdm());
                szsmclone.setSl(szsm.getSl());
                szsmclone.setYnsqss(szsm.getYnsqss());
                szsmclone.setYnszzs(szsm.getYnszzs());
                String szsmmc = szsm.getSzsmmc();
                if (szsm.getSzsmdm().length() != 4)
                {
                    // 是税目就缩进
                    szsmclone.setSzsmmc("　" + szsmmc);
                }
                else
                {
                    szsmclone.setSzsmmc(szsmmc);
                }

                grsdsSzsmList.add(szsmclone);
            }
        }
        return grsdsSzsmList;
    }

    /**
     * 清理session
     * @param request HttpServletRequest
     */
    private void clearSession(HttpServletRequest request)
    {
        request.getSession().removeAttribute(SessionKey.GRSDSZ);
        request.getSession().removeAttribute(SessionKey.GRSDSMX);
        request.getSession().removeAttribute(SessionKey.CURRENT_DATA);
    }

    void completeNewMx(List mxList, HttpServletRequest request) throws BaseException
    {
        List deList = null;//FriendHelper.getGrtszygsde(request, new Date());
        if (deList == null || deList.size() == 0)
        {
            return;
        }

        for (int i=0; i<mxList.size(); i++)
        {
            Grsdsmx mx = (Grsdsmx)mxList.get(i);
            for (int j=0; j<deList.size(); j++)
            {
                Grtszygsde de = (Grtszygsde)deList.get(j);
                if (matchMxDe(mx, de))
                {
                    mx.setSre(new BigDecimal("0"));
                    mx.setYnssde(new BigDecimal("0"));
                    mx.setJmje(new BigDecimal("0"));
                    mx.setBqskse(de.getHdske());
                }
            }
        }
    }

    boolean matchMxDe(Grsdsmx mx, Grtszygsde de)
    {
        if (de.getGjdm().equals(mx.getGjdm()) &&
            de.getZjlxdm().equals(mx.getZjlxdm()) &&
            de.getZjhm().equals(mx.getZjhm()))
        {
            return true;
        }
        return false;
    }

    /**
     * 保存个税明细数据
     *
     * GrsdsForm myForm = (GrsdsForm)form;
     *
     * GrsdsInfo info = new GrsdsInfo();
     *
     * info.setGrsdsz(myForm.getGsz());
     * info.setMx(myForm.getGsmx());
     * info.setoldMxList(myForm.getOldGsmx());
     *
     * GrsdsHelper helper = new GrsdsHelper();
     * helper.save(info);
     *
     * return mapping.findForward("Save");
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doSave(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        GrsdsForm myForm = (GrsdsForm)form;

        try
        {
            ActionForward forward = doHandleToken(mapping,request);
            if (forward != null)
            {
                return forward;
            }

            Timestamp cjsj = new Timestamp(System.currentTimeMillis());

            String jsjdm = getUserData(request).yhid;
            Map skssrq = Skssrq.monthSkssrq(cjsj);

            Grsdsz newZ = new Grsdsz();
            newZ.setSkssksrq((Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            newZ.setSkssjsrq((Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
            newZ.setBqdwzrs(new BigDecimal(myForm.getBqdwzrs()));
            newZ.setBqfdwzrs(new BigDecimal(myForm.getBqfdwzrs()));
            newZ.setJsjdm(jsjdm);
            newZ.setSbrq(cjsj);
            newZ.setCjsj(cjsj);
            newZ.setLrr(jsjdm);
            newZ.setLrrq(cjsj);
            newZ.setFsdm(CodeConstant.FSDM_WSSB);
            newZ.setNd((new SimpleDateFormat("yyyy")).format(cjsj));
            newZ.setSwjgzzjgdm(FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm());

            List newMx = myForm.getGsmx(request, cjsj);

            completeNewMx(newMx, request);
            request.getSession().setAttribute(SessionKey.NEWMX, newMx);

            Grsdsz oldZ = null;
            List oldMx = null;
            if (request.getSession().getAttribute(SessionKey.CURRENT_DATA) != null)
            {
                // 有本期申报资料
                oldZ = (Grsdsz)request.getSession().getAttribute(SessionKey.GRSDSZ);
                oldMx = (List)request.getSession().getAttribute(SessionKey.GRSDSMX);
            }

            GrsdsHelper helper = new GrsdsHelper();
            helper.save(oldZ, oldMx, newZ, newMx);

            // 调用登记接口插入自然人信息
            try
            {
                String swzgzzjgdm = FriendHelper.getSWDJJBSJ(jsjdm).getSwjgzzjgdm();

                List zrrList = new ArrayList(newMx.size());
                for (int i=0; i<newMx.size(); i++)
                {
                    HashMap zrr = new HashMap(7);
                    Grsdsmx mx = (Grsdsmx)newMx.get(i);
                    zrr.put(DjOuterConstant.ZJLXDM, mx.getZjlxdm());
                    zrr.put(DjOuterConstant.ZJHM, mx.getZjhm());
                    zrr.put(DjOuterConstant.GJDM, mx.getGjdm());
                    zrr.put(DjOuterConstant.NSRMC, mx.getXm());
                    zrr.put(DjOuterConstant.SWJGZZJGDM, swzgzzjgdm);
                    zrr.put(DjOuterConstant.CZRY, "网上申报");
                    zrr.put(DjOuterConstant.CZRQ, new Timestamp(System.currentTimeMillis()));

                    zrrList.add(zrr);
                }

                ServiceProxy proxy = new ServiceProxy();
                proxy.insertZrrDjInfo(zrrList);
            }
            catch (Exception ex)
            {
            }

            clearSession(request);

            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "个税明细资料保存成功");
            return mapping.findForward("Success");
        }
        catch (Exception ex)
        {
            myForm.setMxList((List)request.getSession().getAttribute(SessionKey.NEWMX));

            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 删除
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        GrsdsForm myForm = (GrsdsForm)form;
        try
        {
            ActionForward forward = doHandleToken(mapping,request);
            if (forward != null)
            {
                return forward;
            }

            if (request.getSession().getAttribute(SessionKey.CURRENT_DATA) != null)
            {
                Grsdsz z = (Grsdsz)request.getSession().getAttribute(SessionKey.GRSDSZ);
                List mx = (List)request.getSession().getAttribute(SessionKey.GRSDSMX);

                GrsdsHelper helper = new GrsdsHelper();
                helper.delete(z, mx);
            }
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "本期个税明细资料已经删除");
            clearSession(request);

            return mapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            myForm.setMxList((List)request.getSession().getAttribute(SessionKey.NEWMX));
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 返回
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        clearSession(request);
        return mapping.findForward("Return");
    }
}