/*
 * <p>Title:北京地税市长决策支持</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光科技股份有限公司，版权所有. </p>
 * <p>Company: 四一安信科技股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 缴款申请单打印</p>
 * 
 * modified by qianchao 2006.2.12
 * 修改缴款申请单为电子缴款专用缴款书。
 * 
 * @author 开发部－－吴有智
 * @version 1.1
 */
public class JksqdPrintAction
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
                         "<font color=\"#7C9AAB\">申报征收</font>");
    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                         "打印电子缴款专用缴款书");

  }

  /**
   * 页面初始化
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

    JksqdPrintForm pf = (JksqdPrintForm) form;

    try
    {
      //获得当前的userData对象
      UserData ud = this.getUserData(request);
      pf.setLrr(ud.getYhid()); //获得当前登陆的用户id，作为录入人id

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.PRINT_JKSQDPRINT_PROCESSOR);

      //调用processor
      pf = (JksqdPrintForm) ZhsbProxy.getInstance().process(vo);
      //保存返回值--------Shi Yanfeng 20031029-------
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Show");
  }
}
