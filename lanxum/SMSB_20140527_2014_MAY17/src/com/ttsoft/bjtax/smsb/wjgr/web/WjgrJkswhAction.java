/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.web;

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
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.Debug;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 外籍个人所得税月份申报表</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrJkswhAction
    extends SmsbAction
{
  /**
   * 公共的前置处理方法，每次进入页面都会被调用<BR>
   * 设置页面显示时使用的导航信息和标题
   * @param mapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse response)

  {
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                    "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报缴款</font>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "自然人个税申报");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/zhsb/zhsbjkswh-000.htm");
  }

  /**
   * 初始时显示页面
   * 初始化缴款书列表
   *
   * modified by qianchao 2005.10.31
   *
   *
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doShow(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    try
    {
      WjgrJkswhActionForm form = (WjgrJkswhActionForm) actionForm; //初始化相关信息
      //System.out.println("-----------"+form.getSbbh());
      //获得当前的userData对象
      UserData ud = this.getUserData(httpServletRequest);
      //生成数据包
      VOPackage vo = new VOPackage();
      //保存
      vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
      //调用processor
      vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
      //设置actionForm
      //form.setJsjdm("06123456");
      vo.setData(form);

      //设置userDate
      vo.setUserData(ud);
      form = (WjgrJkswhActionForm) ZhsbProxy.getInstance().process(vo);

      //补全税种税目名称

      Debug.out("form.getDataList().size()=" + form.getDataList().size());

      TranslateHelper.completeSzsmmc(form.getDataList(), httpServletRequest);
      //根据银行联网状态，拆分成2个list
      List dlist = TranslateHelper.splitMAPInto2(form.getDataList(), httpServletRequest);

      form.setDataList( (List) dlist.get(0));
      form.setNlwDataList( (List) dlist.get(1));

      
      
      Debug.out("联网：" + form.getDataList().size());      
      Debug.out("非联网：" + form.getNlwDataList().size());      

      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      form);
      return (new ActionForward(actionMapping.getInput()));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "生成缴款书列表失败!");
    }
  }

  /** doPrint打印缴款书
   * @param mapping  The ActionMapping used to select this instance
   * @param form  The optional ActionForm bean for this request (if any)
   * @param request  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   * */
  public ActionForward doPrint(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException
  {
    try
    {
      WjgrJkswhActionForm pf1 = (WjgrJkswhActionForm) form;
      //把预装载的信息传递给下一个页面
      if (pf1.getJksType() == CodeConstant.PRINT_YPYS)
      {
        JksPrintForm pf = new JksPrintForm();
        pf.setHeadJkpzh(pf1.getHeadJkpzh());
        pf.setHeadJsjdm(pf1.getJsjdm());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_ZRRLR); //数据来源
        pf.setActionType("Show");
        request.setAttribute("jksPrintForm", pf);
        return mapping.findForward("Print");
      }
      else
      {
        JksqdPrintForm pf = new JksqdPrintForm();

        Debug.out("pf1.getJsjdm()=" + pf1.getJsjdm());
        Debug.out("pf1.getSbbh()=" + pf1.getSbbh());

        pf.setH_jsjdm(pf1.getJsjdm());
        pf.setH_sbbh(pf1.getSbbh());
        pf.setJsjdm(pf1.getJsjdm());
        pf.setSbbh(pf1.getSbbh());
        pf.setHeadSjly(CodeConstant.SMSB_SJLY_ZRRLR); //数据来源
        pf.setActionType("Show");
        request.setAttribute("jksqdPrintForm", pf);
        return mapping.findForward("PrintSQD");
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /** doBack返回到自然人申报界面
   * @param mapping  The ActionMapping used to select this instance
   * @param form  The optional ActionForm bean for this request (if any)
   * @param request  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   * */
  public ActionForward doBack(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //把预装载的信息传递给下一个页面

      WjgrJkswhActionForm pf1 = (WjgrJkswhActionForm) form;
      return mapping.findForward("Back");

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "生成缴款书列表失败!");
    }

  }

  /*
   * doCx,撤销缴款书
   * @param mapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param request  The HTTP request we are processing
   * @param response  The HTTP response we are creating
   */
  public ActionForward doCx(ActionMapping actionMapping,
                            ActionForm actionForm,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse response) throws
      BaseException
  {
    try
    {
      //防止refresh
      ActionForward forward = doHandleToken(actionMapping,
                                            httpServletRequest);
      if (forward != null)
      {
        return forward;
      }

      WjgrJkswhActionForm form = (WjgrJkswhActionForm) actionForm; //初始化相关信息
      //获得当前的userData对象
      UserData ud = this.getUserData(httpServletRequest);
      //生成数据包
      VOPackage vo = new VOPackage();
      //保存
      vo.setAction(CodeConstant.SMSB_CXJKSACTION);
      //调用processor
      vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
      //设置actionForm
      vo.setData(form);
      //设置userDate
      vo.setUserData(ud);
      form = (WjgrJkswhActionForm) ZhsbProxy.
          getInstance().
          process(vo);

      form.setSbbh("");
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      form);

      httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "撤销成功！");

      return this.doShow(actionMapping, form, httpServletRequest,
                         response);

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "生成缴款书列表失败!");
    }

  }

}
