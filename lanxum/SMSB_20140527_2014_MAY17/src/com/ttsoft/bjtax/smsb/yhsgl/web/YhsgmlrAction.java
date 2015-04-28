/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 录入印花税购买情况 Action</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmlrAction
    extends SmsbAction
{
//    UserData userData = null;

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>录入印花税购买情况");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/yhsgmlr-000.htm");
    }

    /**
     * 显示处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsgmlrForm yForm = (YhsgmlrForm) aForm;
            yForm.reset(mapping, request);
            UserData userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            yForm.setDsdwmc(String.valueOf(userData.getSsdwmc()));
            yForm.setSwjgzzjgdm(String.valueOf(userData.getSsdwdm()));
            yForm.setCjsj(SfDateUtil.getDate());
            try
            {
                //调票证接口
                ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                     getYhspList(this.getUserData(request));
                SfHashList sfList = new SfHashList(yhspList);
                sfList.orderByNum("pzzldm", true);
                yForm.setDataList(sfList.getRecords());
            }
            catch (Exception e1)
            {
                throw ExceptionUtil.getBaseException(e1);
            }
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 保存处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        VOPackage vo = new VOPackage();
        YhsgmlrForm yForm = (YhsgmlrForm) aForm;

        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //从表数据（"spmzdm", "spmzje", "gpsl", "je"）集合
        String columns[] = yForm.getColumns();
        yForm.setDataList(SfRequestUtil.getValuesToList(request, columns));
        vo.setProcessor(CodeConstant.YHSGL_GMLR_PROCESSOR);
        vo.setData(yForm);

        try
        {
            UserData userData = this.getUserData(request);
            vo.setUserData(userData);
            YhsgmlrForm retForm = new YhsgmlrForm();
            yForm = (YhsgmlrForm) ZhsbProxy.getInstance().process(vo);
            yForm.reset(mapping, request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            yForm.setDsdwmc(String.valueOf(userData.getSsdwmc()));
            yForm.setSwjgzzjgdm(String.valueOf(userData.getSsdwdm()));
            yForm.setCjsj(SfDateUtil.getDate());

            try
            {
                //调票证接口
                ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                     getYhspList(this.getUserData(request));
                SfHashList sfList = new SfHashList(yhspList);
                sfList.orderByNum("pzzldm", true);
                yForm.setDataList(sfList.getRecords());
                request.setAttribute(mapping.getAttribute(), yForm);
                request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
                throw ExceptionUtil.getBaseException(e1);
            }

            return mapping.findForward("Save");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                //调票证接口
                ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                     getYhspList(this.getUserData(request));
                SfHashList sfList = new SfHashList(yhspList);
                sfList.orderByNum("pzzldm", true);
                yForm.setDataList(sfList.getRecords());
                request.setAttribute(mapping.getAttribute(), yForm);
            }
            catch (Exception e1)
            {
                e.printStackTrace();
                throw ExceptionUtil.getBaseException(e1);
            }

            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 取名称处理
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doReader (ActionMapping mapping,
                                   ActionForm aForm, HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        YhsgmlrForm yForm = (YhsgmlrForm) aForm;
        //临时保存计算机代码和创建日期
        String jsjdm = yForm.getJsjdm();
        String cjsj = yForm.getCjsj();
        yForm.reset(mapping, request);
        //获得所需的域值
        UserData userData = this.getUserData(request);
        yForm.setLrr(String.valueOf(userData.getYhid()));
        yForm.setDsdwmc(String.valueOf(userData.getSsdwmc()));
        yForm.setSwjgzzjgdm(String.valueOf(userData.getSsdwdm()));
        yForm.setCjsj(cjsj);

        try
        { //调票证接口
            ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                 getYhspList(this.getUserData(request));
            SfHashList sfList = new SfHashList(yhspList);
            sfList.orderByNum("pzzldm", true);
            yForm.setDataList(sfList.getRecords());
        }
        catch (Exception e1)
        {
            throw ExceptionUtil.getBaseException(e1);
        }

        try
        { //调登记接口
            com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.
                bjtax.
                dj.proxy.ServiceProxy();
            HashMap ghdwMap = djProxy.getDjInfo(jsjdm, this.getUserData(request));
            SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
            yForm.setGhdwmc(swdjjbsj.getNsrmc());
            yForm.setJsjdm(jsjdm);
        }
        catch (Exception e2)
        {
            throw ExceptionUtil.getBaseException(e2);
        }
        return mapping.findForward("Reader");
    }

}