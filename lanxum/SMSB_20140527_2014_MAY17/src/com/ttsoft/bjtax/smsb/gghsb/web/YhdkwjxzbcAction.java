/*
 * <p>Title:北京地税核心征管系统--上门申报</p>
 * <p>Copyright:  (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司版权所有.</p>
 * <p>Company: 清华紫光股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.gghsb.web.YhdkwjscscForm;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.gghsb.ftp.BankFTPMag;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.gghsb.GghConstant;

/**
 * <p>Title: 北京地税核心征管系统--上门申报--国地共管户</p>
 * <p>Description: 银行扣款文件下载保存Action</p>
 * @author 开发六组－－回佩杰
 * @version 1.0
 */
public class YhdkwjxzbcAction
    extends SmsbAction {
  public YhdkwjxzbcAction() {
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
    YhdkwjxzbcForm yForm = (YhdkwjxzbcForm) aForm;
    userData = this.getUserData(request);
    vo.setAction(CodeConstant.SMSB_SHOWACTION);
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjxzbcProcessor");
    vo.setData(yForm);
    vo.setUserData(userData);
    try {
      YhdkwjxzbcForm tForm =
          (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
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
    userData = this.getUserData(request);
    VOPackage vo = new VOPackage();
    YhdkwjxzbcForm yForm = (YhdkwjxzbcForm) aForm;
    String ny = yForm.getNy();
    String qxdm = yForm.getQxdm().substring(0, 2);
    yForm.setNd(ny.substring(0, 4));
    yForm.setYd(ny.substring(4, 6));
    String ssny = ""; // 所属年月
    String ssnd = ""; // 所属年度
    String ssyd = ""; // 所属月度
    if (yForm.getYd().equals("01")) {
      ssnd = String.valueOf(Integer.parseInt(yForm.getNd()) + 9999).substring(1, 5);
      ssyd = "12";
    }
    else {
      ssnd = yForm.getNd();
      ssyd = String.valueOf(Integer.parseInt(yForm.getYd()) + 99).substring(1, 3);
    }
    ssny = ssnd + ssyd;

    String yhdm = "";
    //判断是哪家银行
    if (yForm.getQxdmOfBccb().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_BJ;
    }
    else if (yForm.getQxdmOfNxs().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_NX;
    }
    String flag = ssny + qxdm + yhdm; //生成传送的标记
    String[] files = null; //已经取过的文件名...从上传状态表中取得

    try {
      // 1. 获取已下载文件名列表
      try {
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjxzbcProcessor");
        vo.setData(yForm);
        vo.setUserData(userData);
        yForm = (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
        files = yForm.getFileNames();
      }
      catch (Exception ex) {
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("获取已下载文件列表失败", false));
        return mapping.findForward("Save");
      }

      // 2. 从ftp Server获取文件....
      HashMap fileHash = null;
      try {
        BankFTPMag bankFtp = new BankFTPMag();
        fileHash = bankFtp.getFtpYhfk(flag, files);
      }
      catch (Exception ex1) {
        ex1.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             ,
                             getPrintMessage("从FTP服务器下载文件失败:" + ex1.getMessage(), false));
        return mapping.findForward("Save");
      }
      if (fileHash.isEmpty()) {
        request.setAttribute(mapping.getAttribute(), yForm);
        if (files == null) {
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("该征期没有可供下载的银行扣款文件", false));
        }
        else {
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("该征期的银行扣款文件已经下载幷保存过", false));
        }
        return mapping.findForward("Save");
      }
      else if (files == null || files.length < 4) {
        //如果上次已取文件数小于4,则需要变更 状态表里获取文件状态
        try {
          yForm.setFileCount(fileHash.keySet().size());
          vo.setAction(CodeConstant.SMSB_UPDATEACTION);
          vo.setData(yForm);
          yForm = (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex3) {
          request.setAttribute(mapping.getAttribute(), yForm);
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               ,
                               getPrintMessage("修改状态表里获取文件状态失败:" +
                                               ex3.getMessage(), false));
          return mapping.findForward("Save");
        }
      }

      // 3. 保存扣款回执信息
      try {
        yForm.setFtpMap(fileHash);
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setData(yForm);
        yForm = (YhdkwjxzbcForm) ZhsbProxy.getInstance().process(vo);
        yForm.setFtpMap(null);
        fileHash=null;
        System.gc();
      }
      catch (Exception ex2) {
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                             getPrintMessage("保存该征期银行扣款回执信息失败:" + ex2.getMessage(), false));
        return mapping.findForward("Save");
      }
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(yForm.getCgxx(), true));
      return mapping.findForward("Save");
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
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

}