/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司，版权所有. </p>
 * <p>Company: 清华紫光股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.wynsk.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现无应纳税费款申报模块。</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WynsksbAction
    extends SmsbAction {
  protected void initialRequest(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                         "<font color=\"#7C9AAB\">综合服务管理信息系统&gt;上门申报&gt;</font>无应纳税（费）款申报 ");
    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                         "help/smsb/wynsk/wynsk-001.htm");
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
      BaseException {

    //获得当前的userData对象
    UserData ud = this.getUserData(request);

    try {
      //把预装载的信息获得并填写
      WynsksbActionForm pf = (WynsksbActionForm) form;
      pf.setNsrmc("");
      pf.setZcdzlxdh("");
      pf.setSwjgzzjgdm("");
      pf.setSwjgzzjgmc("");

      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_QUERYACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(CodeConstant.WYNSKSB_PROCESSOR);
      //获得当前的userData对象
      /* start added by huxiaofeng 2005.8.16*/
      //HashMap djMap = InterfaceDj.getDjInfo(pf.getJsjdm(), ud);
      HashMap djMap = InterfaceDj.getDjInfo_New(pf.getJsjdm(), ud);
      /* end added by huxiaofeng 2005.8.16*/

      //通过登记接口取得纳税人相关信息

      SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

      pf.setNsrmc(jbsj.getNsrmc());
      pf.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
      pf.setZcdzlxdh(jbsj.getZcdzlxdh());
      pf.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
      //调用processor
      pf = (WynsksbActionForm) ZhsbProxy.getInstance().process(vo);
      //设置税款所属日期
      pf.setSkssrqArr(this.getSkssrq(new Date()));
      /* start added by huxiaofeng 2005.8.16*/
      //设置纳税人状态
      if ("false".equals( (String) request.getAttribute("flag"))) {
         pf.setNsrzt("0");
      }
      else {
        pf.setNsrzt(jbsj.getNsrzt());
      }

      /* end added by huxiaofeng 2005.8.16*/

      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Query");
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
  public ActionForward doInit(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException {
    super.doInit(mapping, form, request, response);
    //获得当前的userData对象
    UserData ud = this.getUserData(request);

    try {
      //把预装载的信息获得并填写
      WynsksbActionForm pf = (WynsksbActionForm) form;
      pf.setSbrq(SfDateUtil.getDate());
      //税款所属日期，按月征收方式
      HashMap skssrq = (HashMap) Skssrq.monthSkssrq(new Date());
      pf.setSkssksrq(SfDateUtil.getDate( (Timestamp) skssrq.get(Skssrq.SKSSKSRQ)));
      pf.setSkssjsrq(SfDateUtil.getDate( (Timestamp) skssrq.get(Skssrq.SKSSJSRQ)));
      //设置税款所属日期
      pf.setSkssrqArr(this.getSkssrq(new Date()));
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Init");
  }

  /**
   * 保存申报数据
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doSave(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException {
    //防止refresh
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null) {
      return forward;
    }

    WynsksbActionForm form = (WynsksbActionForm) actionForm;

    VOPackage vo = new VOPackage();
    vo.setAction(CodeConstant.SMSB_SAVEACTION);
    vo.setProcessor(CodeConstant.WYNSKSB_PROCESSOR);
    vo.setData(form);
    //设置通过总控得到的用户信息
    vo.setUserData(this.getUserData(httpServletRequest));
    try {
      WynsksbActionForm returnForm = (WynsksbActionForm) ZhsbProxy.getInstance().
          process(vo);

//      return actionMapping.findForward("Init");
      /* start added by huxiaofeng 2005.8.16*/
      httpServletRequest.setAttribute("flag", "false");
      /* end added by huxiaofeng 2005.8.16*/
      return this.doQuery(actionMapping, actionForm, httpServletRequest,
                          httpServletResponse);
    }
    catch (Exception ex) {

      throw ExceptionUtil.getBaseException(ex, "保存申报数据失败!");
    }
  }

  /**
   * 保存申报数据并生成缴款书
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doDelete(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws
      BaseException {
    //防止refresh
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null) {
      return forward;
    }

    WynsksbActionForm form = (WynsksbActionForm) actionForm;
    //得到列表数据

    VOPackage vo = new VOPackage();
    vo.setAction(CodeConstant.SMSB_DELETEACTION);
    vo.setProcessor(CodeConstant.WYNSKSB_PROCESSOR);
    vo.setData(form);
    //设置通过总控得到的用户信息
    vo.setUserData(this.getUserData(httpServletRequest));
    try {
      WynsksbActionForm returnForm = (WynsksbActionForm) ZhsbProxy.getInstance().
          process(vo);

      //转移到缴款书维护界面，只显示本次生成的缴款书
//      return actionMapping.findForward("Delete");
      /* start added by huxiaofeng 2005.8.16*/
      httpServletRequest.setAttribute("flag", "false");
      /* end added by huxiaofeng 2005.8.16*/

      return this.doQuery(actionMapping, actionForm, httpServletRequest,
                          httpServletResponse);
    }
    catch (Exception ex) {

      throw ExceptionUtil.getBaseException(ex, "删除申报数据失败!");
    }
  }

  /**
   * 得到前台跳转到申报资料使用的税款所属日期
   * @param  rq 申报日期
   * @return  js数组['月','','季度','年','']
   */
  private String getSkssrq(Date rq) {
    StringBuffer ret = new StringBuffer();
    Map m = Skssrq.monthSkssrq(rq);
    //月份的税款所属日期
    ret.append("var skssrqArr = ['").append(
        SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSKSRQ))
        ).append("','").append(
        SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSJSRQ))
        ).append("',");
    //季度的税款所属日期
    Map q = Skssrq.quarterSkssrq(TinyTools.addMonth( -1, rq));
    ret.append("'").append(SfDateUtil.getDate( (Timestamp) q.get(Skssrq.
        SKSSKSRQ))).append("','").append(SfDateUtil.getDate( (Timestamp) q.get(
        Skssrq.SKSSJSRQ))).append("',");
    //年
    Map y = Skssrq.yearSkssrq(rq);
    ret.append("'").append(SfDateUtil.getDate( (Timestamp) y.get(Skssrq.
        SKSSKSRQ))).append("','").append(SfDateUtil.getDate( (Timestamp) y.get(
        Skssrq.SKSSJSRQ))).
        append("',");
    //季度其他
    Map qq = Skssrq.otherSkssrq(rq);
    ret.append("'").append(SfDateUtil.getDate( (Timestamp) qq.get(Skssrq.
        SKSSKSRQ))).append("','").append(SfDateUtil.getDate( (Timestamp) qq.get(
        Skssrq.SKSSJSRQ))).
        append("']");
    return ret.toString();

  }

}
