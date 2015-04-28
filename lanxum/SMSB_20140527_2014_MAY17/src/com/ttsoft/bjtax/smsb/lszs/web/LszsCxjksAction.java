/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.LWHelper;
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散税征收撤销缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsCxjksAction
    extends SmsbAction
{

  /**
   * 公共的前置处理方法，每次进入页面都会被调用<BR>
   * 设置页面显示时使用的导航信息和标题
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)

  {
    super.initialRequest(mapping, form, request, response);
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                         "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
                         "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
                         "<font color=\"#7C9AAB\">零散税征收</font>");
    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                         "撤销缴款书");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                         "help/smsb/lszs/jkscx-000.htm");
  }

  /**
   * 初始化
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doShow(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException
  {
    LszsCxjksForm pf = (LszsCxjksForm) form;
    Debug.out("in to doshow");
    try
    {
      //获得当前的userData对象
      UserData ud = this.getUserData(request);
      pf.setLrrdm(ud.getYhid()); //获得单前登陆的用户id，作为录入人id
      pf.setHeadJsjdm(InterfaceDj.getZrrJsjdm(ud)); //所属机关计算机代码，
      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_SHOWACTION);

      //start add by qianchao 2005.10.26
      boolean lwstate = LWHelper.getLWState(request, pf.getHeadJsjdm());
      if (lwstate)
      {
        pf.setJksType(CodeConstant.PRINT_YPDS_KM);
      }
      else
      {
        pf.setJksType(CodeConstant.PRINT_YPYS);
      }
      //vo.setData(pf);
      vo.setData(pf);
      //end add by qianchao 2005.10.26


      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);
      //调用processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);

      //start add by qianchao 2005.10.27
      List ar = TranslateHelper.splitMAP(pf.getDataList(),request);

      pf.setDataList((List)ar.get(0));
      pf.setNlwDataList((List)ar.get(1));
      //end add by qianchao 2005.10.27

      //保存返回值--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
      return mapping.findForward("Show");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 查询
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doQuery(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException
  {
    //获得当前的userData对象
    UserData ud = this.getUserData(request);

    LszsCxjksForm pf = (LszsCxjksForm) form;
    try
    {
      pf.setHeadJsjdm(InterfaceDj.getZrrJsjdm(ud)); //所属机关计算机代码，
      //把预装载的信息获得并填写
      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);

      //调用processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);


      //start add by qianchao 2005.10.27
      List ar = TranslateHelper.splitMAP(pf.getDataList(),request);

      pf.setDataList((List)ar.get(0));
      pf.setNlwDataList((List)ar.get(1));
      //end add by qianchao 2005.10.27


      //保存返回值--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);

      //保存返回值--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Query");
  }

  /**
   * 删除
   * modified by qianchao 2005.10.26
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doDelete(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException
  {
    //防止refresh
    ActionForward forward = doHandleToken(mapping, request);
    if (forward != null)
    {
      return forward;
    }
    //获得当前的userData对象
    UserData ud = this.getUserData(request);
    LszsCxjksForm pf = (LszsCxjksForm) form;
    //把预装载的信息获得并填写
    try
    {

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_DELETEACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);
      //调用processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);

      //重新查询
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setProcessor(CodeConstant.LSZS_CXJKS_PROCESSOR);
      //调用processor
      pf = (LszsCxjksForm) ZhsbProxy.getInstance().process(vo);
      //保存返回值--------Shi Yanfeng 20031029-------

      //start add by qianchao 2005.10.27
      List ar = TranslateHelper.splitMAP(pf.getDataList(),request);

      pf.setDataList((List)ar.get(0));
      pf.setNlwDataList((List)ar.get(1));
      //end add by qianchao 2005.10.27

      request.setAttribute(mapping.getAttribute(), pf);
      pf.reset(mapping, request);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "撤销成功！");
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Delete");
  }

  /**
   * 返回缴款书录入页面
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doBack(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //把预装载的信息传递给下一个页面
      LszsJkslrForm pf = new LszsJkslrForm();
      pf.setActionType("Show");
      request.setAttribute("LszsJkslrForm", pf);
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Back");
  }

  /**
   * 打印
   *
   * modified by qianchao 2005.10.27
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doPrint(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //把预装载的信息传递给下一个页面
      LszsCxjksForm pf1 = (LszsCxjksForm) form;

      //start modifying by qianchao 2005.10.27
      if (pf1.getJksType() == CodeConstant.PRINT_YPYS)
      {
        JksPrintForm pf = new JksPrintForm();
        pf.setHeadJkpzh(pf1.getHeadJkpzh());
        pf.setHeadJsjdm(pf1.getHeadJsjdm());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSZSLR); //数据来源
        pf.setActionType("Show");
        request.setAttribute("jksPrintForm", pf);
        return mapping.findForward("Print");
      }
      else
      {
        JksqdPrintForm pf = new JksqdPrintForm();
        pf.setH_jsjdm(pf1.getHeadJsjdm());
        pf.setH_sbbh(pf1.getHeadSbbh());
        pf.setJsjdm(pf1.getHeadJsjdm());
        pf.setSbbh(pf1.getHeadSbbh());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_LSZSLR); //数据来源
        pf.setActionType("Show");
        request.setAttribute("jksqdPrintForm", pf);
        return mapping.findForward("PrintSQD");
      }

      //end modifying by qianchao 2005.10.27

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

}
