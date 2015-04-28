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

/**
 * <p>Title: 北京地税核心征管系统--上门申报--国地共管户</p>
 * <p>Description: 银行扣款文件生成上传Action</p>
 * @author 开发六组－－诸光林
 * @version 1.0
 */
public class YhdkwjscscAction
    extends SmsbAction {
  public YhdkwjscscAction() {
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
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscProcessor");
    vo.setData(yForm);
    vo.setUserData(userData);
    try {
      YhdkwjscscForm tForm =
          (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
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

    BankFTPMag ftpManager = new BankFTPMag(); //ftp操作类
    HttpSession session = request.getSession(false);
    YhdkwjscscForm yForm = (YhdkwjscscForm) aForm;

    VOPackage vo = new VOPackage();
    userData = this.getUserData(request);
    vo.setProcessor("com.ttsoft.bjtax.smsb.gghsb.processor.YhdkwjscscProcessor");
    vo.setUserData(userData);
    String qxdm = yForm.getQxdm(); //区县代码
    String yhdm = ""; //银行代码
    String ny = yForm.getNy(); //年月
    String nd = ny.substring(0, 4); //年度
    String yd = ny.substring(4, 6); //月度
    String ssny = ""; // 所属年月
    String ssnd = ""; // 所属年度
    String ssyd = ""; // 所属月度
    if (yd.equals("01")) {
      ssnd = String.valueOf(Integer.parseInt(nd) + 9999).substring(1, 5);
      ssyd = "12";
    }
    else {
      ssnd = nd;
      ssyd = String.valueOf(Integer.parseInt(yd) + 99).substring(1, 3);
    }
    ssny = ssnd + ssyd;

    StringBuffer lsLogName = new StringBuffer(GghConstant.FILE_PREFIX_LS).
        append(ssny);
    // 1. 判断是哪家银行
    if (yForm.getQxdmOfBccb().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_BJ;
    }
    else if (yForm.getQxdmOfNxs().indexOf(qxdm) >= 0) {
      yhdm = GghConstant.YHDM_NX;
    }
    lsLogName.append(yhdm).append(GghConstant.FILE_POSTFIX_LOG); //表示银行开始读取扣款文件的日志文件
    yForm.setYhdm(yhdm);
    // 2. 判断该区县该月文件是否已被读取
    try {
      if (ftpManager.checkLsLogFile(lsLogName.toString())) {
        // 2.1 更新银行取标识
        try {
          vo.setData(yForm);
          vo.setAction(CodeConstant.SMSB_UPDATEACTION);
          ZhsbProxy.getInstance().process(vo);
        }
        catch (Exception ex) {
          request.setAttribute(mapping.getAttribute(), yForm);
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("银行已取该月代征信息，不可再次生成", false));
          /* start added by huxiaofeng 2005.8.1*/
          ex.printStackTrace();
          /* end added by huxiaofeng 2005.8.1*/
          throw ExceptionUtil.getBaseException(ex);
        }

        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("银行已取该月代征信息，不可再次生成", false));
        return mapping.findForward("Save");
      }
    }
    catch (Exception ex5) {
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage("获取日志文件失败:" + ex5.getMessage(), false));
      /* start added by huxiaofeng 2005.8.1*/
      System.out.println("获取日志文件失败");
      ex5.printStackTrace();
      /* end added by huxiaofeng 2005.8.1*/
      return mapping.findForward("Save");
    }

    // 3. 删除已有扣款文件
    try {
      String fileName = new StringBuffer(ssny).append(qxdm.substring(0, 2)).
          append(yhdm).append(GghConstant.FILE_POSTFIX_DATA).toString();
      ftpManager.delete(new StringBuffer(GghConstant.FILE_PREFIX_MX).append(
          fileName).toString(), 0);
      ftpManager.delete(new StringBuffer(GghConstant.FILE_PREFIX_HZ).append(
          fileName).toString(), 0);
    }
    catch (Exception ex5) {
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage("删除已有代征信息失败:" + ex5.getMessage(), false));
      return mapping.findForward("Save");
    }
    long time2 = System.currentTimeMillis();
    // 4. 保存并封装扣款数据
    try {
      // 4.1 保存扣款数据
      try {
        vo.setData(yForm);
        vo.setAction(CodeConstant.SMSB_SAVEACTION); //保存方法
        yForm = (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
        ex.printStackTrace();
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("生成代征信息失败:" + ex.getMessage(), false));
        throw ExceptionUtil.getBaseException(ex);
      }

      long time3 = System.currentTimeMillis();
      System.out.println(qxdm.substring(0, 2) + " [银行代扣文件上传] 生成扣款总耗时:  " +
                         (time3 - time2) + " 毫秒");

      // 4.2 上传扣款文件
      List list = (List) ( (HashMap) (yForm.getFtpMap()).get("MXWJ")).get(
          "CONTENT");
      if (list.size() > 0) { //如果有扣款信息则上传
        try {
          ftpManager.putKkData(yForm.getFtpMap());
        }
        catch (Exception ex5) {
          request.setAttribute(mapping.getAttribute(), yForm);
          request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                               , getPrintMessage("上传代征信息失败:" + ex5.getMessage(), false));
          throw ExceptionUtil.getBaseException(ex5);
        }
      }

      long time4 = System.currentTimeMillis();
      System.out.println(qxdm.substring(0, 2) + " [银行代扣文件上传] 上传文件耗时:  " +
                         (time4 - time3) + " 毫秒");
      yForm.getFtpMap().remove("MXWJ"); //清楚无用的大对象
      System.gc();

      // 4.3 更新扣款信息状态
      vo.setAction(CodeConstant.SMSB_UPLOADACTION); //更新方法
      vo.setData(yForm);
      try {
        yForm = (YhdkwjscscForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex1) {
        request.setAttribute(mapping.getAttribute(), yForm);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("更新扣款信息状态失败", false));
        throw ExceptionUtil.getBaseException(ex1);
      }

      //成功！
      StringBuffer sb = new StringBuffer();
      if (list.size() > 0) {
        HashMap sum = (HashMap) (yForm.getFtpMap()).get("HZWJ");
        sb.append("生成并上传代征信息成功：共生成")
            .append(yForm.getHs()).append("户的")
            .append( (String) sum.get("COUNT")).append("笔扣款信息，总计")
            .append( (String) sum.get("TOTALAMOUNT")).append("元");
      }
      else {
        sb.append("本区县本月没有代征信息");
      }
      System.out.println(qxdm.substring(0, 2) + " [银行代扣文件上传] " + sb.toString());
      request.setAttribute(mapping.getAttribute(), yForm);
      request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                           , getPrintMessage(sb.toString(), true));
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

}