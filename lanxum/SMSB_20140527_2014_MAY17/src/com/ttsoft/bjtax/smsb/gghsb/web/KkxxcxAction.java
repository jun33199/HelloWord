package com.ttsoft.bjtax.smsb.gghsb.web;

/**
 * <p>Title: 北京地税核心征管系统--个体工商户税收征收管理</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003-2004北京市地方税务局，清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @version 1.0
 */

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.common.model.UserData;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;

import java.util.ArrayList;
import java.util.Date;

import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.gghsb.excel.ExcelUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;

/**
 * <p>Title: 北京地税核心征管系统--个体工商户税收征收管理</p>
 * <p>Description:扣款信息查询Action </p>
 * @author not attributable
 * @version 1.0
 */

public class KkxxcxAction
    extends SmsbAction {
  public KkxxcxAction() {
  }

  /**
   * 数据集对象（包括Form和UserData对象）
   */
  private VOPackage vo = new VOPackage();

  /**
   * 用户信息对象
   */
  UserData userData = null;

  /**
   * 公共的前置处理方法，每次进入页面都会被调用<BR>
   * 设置页面显示时使用的导航信息和标题
   * @param mapping The ActionMapping used to select this instance
   * @param actionForm The optional ActionForm bean for this request (if any)
   * @param httpServletRequest The HTTP request we are processing
   * @param response The HTTP response we are creating
   */

  protected void initialRequest(ActionMapping mapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse response)

  {
    System.out.println("--print--action doInit");
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                    "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">委托银行扣款征收</font>&gt;扣款信息查询&gt;</td>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "扣款信息查询");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/gzwh/gzwh-000.htm");
  }

  /**
   * 显示处理
   * @param mapping The ActionMapping used to select this instance
   * @param aForm The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @throws BaseException
   */
  public ActionForward doShow(ActionMapping mapping, ActionForm aForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    try {
      System.out.println("--print--action doShow");
      //清除数据
      removeForm(mapping, request);
      KkxxcxForm kf = (KkxxcxForm) aForm;
      kf.setDataList(new ArrayList());
      vo.setData(kf);
      vo.setUserData(this.getUserData(request));
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
      kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      request.setAttribute(mapping.getAttribute(), kf);
      return mapping.findForward("Show");
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * 查询处理
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @throws BaseException
   */
  public ActionForward doQuery(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException {
    KkxxcxForm kf = (KkxxcxForm) form;
    try {
      //查询
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
      vo.setData(kf);
      vo.setUserData(this.getUserData(request));
      kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      if (kf.getDataList().size() == 0) {
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("没有符合条件的扣款信息", true));
      }
      //传递form
      request.setAttribute(mapping.getAttribute(), kf);
      return mapping.findForward("Query");
    }
    catch (Exception e) {
      kf.setPgNum(0);
      kf.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
        vo.setData(kf);
        vo.setUserData(this.getUserData(request));
        kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }

      //传递form
      request.setAttribute(mapping.getAttribute(), kf);
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * 返回处理
   * @param mapping The ActionMapping used to select this instance
   * @param aFrom The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @throws BaseException
   */
  public ActionForward doReturn(ActionMapping mapping, ActionForm aForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException {
    try {
      removeForm(mapping, request);
      KkxxcxForm kf = (KkxxcxForm) aForm;
      kf.reset(mapping, request);
      //释放session空间
      request.getSession().removeAttribute("DataList");
      return mapping.findForward("Return");

    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * 生成Excel文件处理
   * @param mapping The ActionMapping used to select this instance
   * @param aFrom The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @throws BaseException
   */

  public ActionForward doSaveExcel(ActionMapping mapping, ActionForm aForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws
      BaseException {
    KkxxcxForm kf = (KkxxcxForm) aForm;
    try {
      vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
      vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
      vo.setData(kf);
      vo.setUserData(this.getUserData(request));
      kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      if (kf.getDataList() == null || kf.getDataList().isEmpty() ||
          kf.getSumList() == null || kf.getSumList().isEmpty()) {
        kf.setPgNum(0);
        kf.setPgSum(0);
        doShow(mapping, aForm, request, response);
        throw new ApplicationException("没有找到指定的记录！");
      }
      else {
        String currDate = TinyTools.Date2String(new Date(System.
            currentTimeMillis()));
        String fileName = "委托银行扣款征收".concat(currDate).concat("查询.xls");
        String encodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(
            fileName);
        response.resetBuffer();
        response.setHeader("Content-disposition",
                           "attachment; filename=" + encodeFileName);
        response.setContentType("application/vnd.ms-excel");
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.generateKKXXExcel(response.getOutputStream(), kf);
        return null;
      }
    }
    catch (Exception e) {
      kf.setPgNum(0);
      kf.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_KKXXCX_PROCESSOR);
        vo.setData(kf);
        vo.setUserData(this.getUserData(request));
        kf = (KkxxcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }

      //传递form
      request.setAttribute(mapping.getAttribute(), kf);
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