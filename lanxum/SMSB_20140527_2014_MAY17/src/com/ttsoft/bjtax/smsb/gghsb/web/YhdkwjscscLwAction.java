/*
 * <p>Title:北京地税核心征管系统--上门申报</p>
 * <p>Copyright:  (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司版权所有.</p>
 * <p>Company: 清华紫光股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.gghsb.web.YhdkwjscscForm;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.gghsb.ftp.BankFTPMag;
import com.ttsoft.bjtax.smsb.gghsb.GghConstant;
import com.ttsoft.bjtax.shenbao.service.adaptor.SKHAdaptor;
import java.util.Date;
import java.util.Map;

/**
 * <p>Title: 北京地税核心征管系统--上门申报--国地共管户</p>
 * <p>Description: </p>
 * @author 开发六组－－诸光林
 * @version 1.0
 */
public class YhdkwjscscLwAction
    extends SmsbAction {

  public YhdkwjscscLwAction() {
  }

  UserData userData = null;

  /**
   * 功能：公共的前置处理方法！
   * @param mapping Struct ActionMapping
   * @param form  instance of BaseForm
   * @param request request from HTML
   * @param response response from HTML
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                         "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">上门申报</font>&gt;<font color=\"#567587\">委托银行扣款征收</font>");
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
  public ActionForward doShow(ActionMapping mapping,
                              ActionForm aForm, HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    HttpSession session = request.getSession(false);
    VOPackage vo = new VOPackage();
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;
    userData = this.getUserData(request);
    vo.setAction(CodeConstant.SMSB_SHOWACTION);
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscLwProcessor");
    vo.setData(yForm);
    vo.setUserData(userData);
    try {
      YhdkwjscscForm tForm =
          (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
      log(tForm.getNyList().size()+"");
      log(tForm.getQxList().size()+"");
      session.setAttribute("GGHSB_YHDK_QXLIST", tForm.getQxList()); // 区县局列表
      session.setAttribute("GGHSB_YHDK_NYLIST", tForm.getNyList()); // 可生成年月列表
      tForm.setLrr(String.valueOf(userData.getYhid()));
      tForm.setLrrq(SfDateUtil.getDate());
      tForm.setCjr(String.valueOf(userData.getYhid()));
      tForm.setCjrq(SfDateUtil.getDate());
      request.setAttribute(mapping.getAttribute(), tForm);
      return mapping.findForward("Show");
    }
    catch (Exception e) {
      e.printStackTrace();
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage("初始化页面失败:" + e.getMessage(), false));
      return mapping.findForward("Show");
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
  public ActionForward doSave(ActionMapping mapping,
                              ActionForm aForm, HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    ActionForward forward = doHandleToken(mapping, request);
    if (forward != null) {
      return forward;
    }
    HttpSession session = request.getSession(false);
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;
    VOPackage vo = new VOPackage();
    userData = this.getUserData(request);
    String msg=null;
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscLwProcessor");
    vo.setUserData(userData);
    try {
      try {

        vo.setAction(CodeConstant.SMSB_SAVEACTION); //保存方法
        Map map=new HashMap();
        map.put("form",yForm);
        vo.setData(map);
        log(map);
        map = (HashMap) ZhsbProxy.getInstance().process(vo);
        //返回值应当包括form和msg
        yForm=(YhdkwjscscForm)map.get("form");
        msg=(String)map.get("msg");
      }
      catch (Exception ex) {
                ex.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("生成代征信息失败:" + ex.getMessage(), false));
        throw ExceptionUtil.getBaseException(ex);
      }
      //成功！
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(msg, true));
      return mapping.findForward("Save");
    }
    catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("Save");
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
  public ActionForward doSend(ActionMapping mapping,
                              ActionForm aForm, HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    ActionForward forward = doHandleToken(mapping, request);
    if (forward != null) {
      return forward;
    }
    HttpSession session = request.getSession(false);
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;
    VOPackage vo = new VOPackage();
    userData = this.getUserData(request);
    String msg=null;
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscLwProcessor");
    vo.setUserData(userData);
    try {
      try {
        vo.setAction(CodeConstant.SMSB_UPLOADACTION); //保存方法
        Map map=new HashMap();
        map.put("form",yForm);
        vo.setData(map);
        map = (HashMap) ZhsbProxy.getInstance().process(vo);
        //返回值应当包括form和msg
        yForm=(YhdkwjscscForm)map.get("yForm");
        msg=(String)map.get("msg");
      }
      catch (Exception ex) {
                ex.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("发送代征信息失败:" + ex.getMessage(), false));
        throw ExceptionUtil.getBaseException(ex);
      }
      //成功！
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(msg, true));
      return mapping.findForward("Save");
    }
    catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("Save");
    }
  }

  /**
   * 转换输出信息格式
   * @param message 输出内容
   * @param successFlag 成功|失败
   * @return
   */
  private String getPrintMessage(String message, boolean successFlag) {
    StringBuffer printMessage = new StringBuffer();
    if (successFlag) { //成功信息
      printMessage.append("<br><strong><font color=\"#0000FF\">&nbsp;&nbsp;")
          .append(message).append("！</font></strong>");
    }
    else { //失败信息
      printMessage.append("<br><strong><font color=\"#FF0000\">&nbsp;&nbsp;")
          .append(message).append("！</font></strong>");
    }
    return printMessage.toString();
  }

  private void log(Object o){
    System.out.println("[SMSB GTGSH YhdkwjscscLwAction (" + (new Date()) + ")]" + o);
  }


}
