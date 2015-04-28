package com.ttsoft.bjtax.smsb.gghsb.web;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gghsb.excel.ExcelUtil;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统--个体工商户税收征收管理</p>
 * <p>Description:定期定额户入库情况查询Action </p>
 * @author zhou jinguang
 * @version 1.0
 */

public class DqdehrkcxAction
    extends SmsbAction {

  public DqdehrkcxAction() {
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
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;定期定额户入库情况查询&gt;</td>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "定期定额户入库情况查询");
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
  public ActionForward doShow(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    try {
      //清除数据
      removeForm(mapping, request);
      DqdehrkcxForm df = (DqdehrkcxForm) actionForm;
      df.setDataList(new ArrayList());
      vo.setData(df);
      vo.setUserData(this.getUserData(request));
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
      df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      request.setAttribute(mapping.getAttribute(), df);
      return mapping.findForward("Show");
    }
    catch (Exception e) {
      Debug.printException(e);
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
    DqdehrkcxForm df = (DqdehrkcxForm) form;
    try {
      //查询
      System.out.println("execu doquery!");
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
      vo.setData(df);
      vo.setUserData(this.getUserData(request));
      df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      System.out.println("df.getDataList().size():" + df.getDataList().size());
      if (df.getDataList().size() == 0) {
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS
                             , getPrintMessage("没有符合条件的信息", true));
      }

      //传递form
      request.setAttribute(mapping.getAttribute(), df);
      return mapping.findForward("Query");
    }
    catch (Exception e) {
      df.setPgNum(0);
      df.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
        vo.setData(df);
        vo.setUserData(this.getUserData(request));
        df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }
      //传递form
      request.setAttribute(mapping.getAttribute(), df);
      Debug.printException(e);
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
    DqdehrkcxForm df = (DqdehrkcxForm) aForm;
    try {
      vo.setAction(CodeConstant.SMSB_TOEXCELACTION);
      vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
      vo.setData(df);
      vo.setUserData(this.getUserData(request));
      df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      if (df.getDataList() == null || df.getDataList().isEmpty() ||
          df.getSumList() == null || df.getSumList().isEmpty()) {
        df.setPgNum(0);
        df.setPgSum(0);
        doShow(mapping, aForm, request, response);
        throw new ApplicationException("没有找到指定的记录！");
      }
      else {
        String currDate = TinyTools.Date2String(new Date(System.
            currentTimeMillis()));
        String fileName = "定期定额户入库情况".concat(currDate).concat("查询.xls");
        String encodeFileName = com.ttsoft.framework.util.StringUtil.
            GBK2ISO(fileName);
        response.resetBuffer();
        response.setHeader("Content-disposition",
                           "attachment; filename=" + encodeFileName);
        response.setContentType("application/vnd.ms-excel");
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.generateDQDEHRKCXExcel(response.getOutputStream(), df);
        return null;
      }
    }
    catch (Exception e) {
      df.setPgNum(0);
      df.setPgSum(0);
      try {
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.GTGSH_DQDEHRKCX_PROCESSOR);
        vo.setData(df);
        vo.setUserData(this.getUserData(request));
        df = (DqdehrkcxForm) ZhsbProxy.getInstance().process(vo);
      }
      catch (Exception ex) {
      }

      //传递form
      request.setAttribute(mapping.getAttribute(), df);
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
      DqdehrkcxForm df = (DqdehrkcxForm) aForm;
      df.reset(mapping, request);
      //释放session空间
      request.getSession().removeAttribute("DataList");
      return mapping.findForward("Return");

    }
    catch (Exception e) {
      Debug.printException(e);
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