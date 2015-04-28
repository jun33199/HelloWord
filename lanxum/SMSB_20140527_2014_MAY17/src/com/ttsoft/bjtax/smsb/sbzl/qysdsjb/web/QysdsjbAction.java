/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb.QysdsjbHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税季报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbAction
    extends SmsbAction
{
//    private UserData userData = null;

  /**
   * 公共的前置处理方法，每次进入页面都会被调用<BR>
   * 设置页面显示时使用的导航信息和标题
   * @param mapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param response HttpServletResponse
   */
  protected void initialRequest(ActionMapping mapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse response)

  {
    super.initialRequest(mapping, actionForm, httpServletRequest, response);
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                    "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;企业所得税年季度纳税申报表</td>");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "企业所得税年季度纳税申报表");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/sbzl/qysdsjb/qysdsjb-000.htm");

  }

  /**
   * 取业务信息
   * 1、取得QysdsjbForm，产生一个VOPackage。
   * 2、取业务数据的参数，设置它为voPackage的data域。
   * 3、设置voPackage的Processor域为
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor，
   *         voPackage的Data域为QysdsjbForm
   * 	voPackage的action域为CodeConstant.SMSB_SHOWACTION
   * 	调用SbzlProxy的process方法。
   * 4、调用doShow方法设置取业务数据的结果。
   * 5、转向业务页面。
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward的跳转目标
   * @throws BaseException 系统捕捉异常，根据异常类型抛出
   */

  public ActionForward doShow(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //当前的ActionForm---QysdsjbForm
    try
    {
      QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
      //初始化数据传递容器
      UserData userData = this.getUserData(httpServletRequest);
      // Debug.out(userData.getYhid());
      qysdsjbForm.setLrr(userData.getYhid());

      VOPackage vo = new VOPackage();
      //设置后台调用Action值---SHOWACTION
      vo.setAction(CodeConstant.SMSB_SHOWACTION);
      //设置容器里的Data数据,这里存放CzzsjdForm
      vo.setData(qysdsjbForm);
      vo.setUserData(userData);
      //设置Proxy要调用的processor的类---QysdsjbProcessor
      vo.setProcessor(
          "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");

      //调用Proxy，初始化CzzsjdForm中值
      qysdsjbForm = (QysdsjbForm) SbzlProxy.getInstance().process(vo);

      //Debug.out(qysdsjbForm.getDataList());
      //流转
      //获得预装载的信息
      //获得当前的userData对象
      //获得单前登陆的用户id，作为录入人id
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      //form.setLrr(userData.);
      return actionMapping.findForward("Show");
    }
    catch (Exception ex)
    {
      //系统捕捉异常，根据异常类型抛出
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 取业务信息
   * 1、取得QysdsjbForm，产生一个VOPackage。
   * 2、取业务数据的参数，设置它为voPackage的data域。
   * 3、设置voPackage的Processor域为
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor，
   *         voPackage的Data域为QysdsjbForm
   * 	voPackage的action域为CodeConstant.SMSB_SAVEACTION
   * 	调用SbzlProxy的process方法。
   * 4、调用doSave方法设置取业务数据的结果。
   * 5、转向业务页面。
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward的跳转目标
   * @throws BaseException 系统捕捉异常，根据异常类型抛出
   *
   */

  public ActionForward doQuery(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //当前的ActionForm---QysdsjbForm
    Debug.out("Query");
    QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
    UserData userData = this.getUserData(httpServletRequest);
    qysdsjbForm.setLrr(userData.getYhid());
    //初始化数据传递容器
    VOPackage vo = new VOPackage();
    //设置后台调用Action值----QUERYACTION
    vo.setAction(CodeConstant.SMSB_QUERYACTION);
    //设置容器里的Data数据,这里存放CzzsjdForm
    vo.setData(qysdsjbForm);
    vo.setUserData(userData);
    //设置Proxy要调用的processor的类名
    vo.setProcessor(
        "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");
    try
    {
      //调用Proxy，初始化CzzsjdForm中值
      qysdsjbForm = (QysdsjbForm) SbzlProxy.getInstance().process(vo);

      Debug.out("这里传递的是DataList中的数据" + qysdsjbForm.getDataList());
      //流转
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      return actionMapping.findForward("Query");
    }
    catch (Exception ex)
    {
      //系统捕捉异常，根据异常类型抛出
      qysdsjbForm.reset(actionMapping, httpServletRequest);

      qysdsjbForm.setDataList(QysdsjbHelper.getShowList());
      qysdsjbForm.setJsjdm("");
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 取业务信息
   * 1、取得QysdsjbForm，产生一个VOPackage。
   * 2、取业务数据的参数，设置它为voPackage的data域。
   * 3、设置voPackage的Processor域为
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor，
   *         voPackage的Data域为QysdsjbForm
   * 	voPackage的action域为CodeConstant.SMSB_SAVEACTION
   * 	调用SbzlProxy的process方法。
   * 4、调用doSave方法设置取业务数据的结果。
   * 5、转向业务页面。
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward的跳转目标
   * @throws BaseException 系统捕捉异常，根据异常类型抛出
   *
   */

  public ActionForward doSave(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null)
    {
      return forward;
    }

    //当前的ActionForm---------QysdsjbForm
    QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
    UserData userData = this.getUserData(httpServletRequest);
    qysdsjbForm.setLrr(userData.getYhid());
    //按照columns中的字段提取所需要前台列表数据赋给czzsjdForm的DataList中，
    String columns[] = qysdsjbForm.getColumns();
    qysdsjbForm.setDataList(getValuesToList(httpServletRequest, columns));
    Debug.out("这里传递的是DataList中的数据" + qysdsjbForm.getDataList());

    //初始化数据传递容器
    VOPackage vo = new VOPackage();
    //设置后台调用Action值-----SAVEACTION
    vo.setAction(CodeConstant.SMSB_SAVEACTION);
    //设置容器里的Data数据,在我们这儿就是CzzsjdForm
    vo.setData(qysdsjbForm);
    vo.setUserData(userData);
    //设置Proxy要调用的processor的类名
    vo.setProcessor(
        "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");
    try
    {
      //调用Proxy，初始化CzzsjdForm中值
      SbzlProxy.getInstance().process(vo);
      //保存成功,流转
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setJsjdm("");
      List dataList = QysdsjbHelper.getShowList();
      qysdsjbForm.setDataList(dataList);
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                      "保存成功！");
      return actionMapping.findForward("Save");
    }
    catch (Exception ex)
    {
      //系统捕捉异常，根据异常类型抛出
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setDataList(QysdsjbHelper.getShowList());
      qysdsjbForm.setJsjdm("");
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 取业务信息
   * 1、取得QysdsjbForm，产生一个VOPackage。
   * 2、取业务数据的参数，设置它为voPackage的data域。
   * 3、设置voPackage的Processor域为
   * 	com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor，
   *        voPackage的Data域为QysdsjbForm
   * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
   * 	调用SbzlProxy的process方法。
   * 4、调用doDelete方法设置取业务数据的结果。
   * 5、转向业务页面。
   * @param actionMapping struts.action.ActionMapping
   * @param actionForm QysdsjbForm
   * @param httpServletRequest HttpServletRequest
   * @param httpServletResponse HttpServletResponse
   * @return actionMapping.findForward的跳转目标
   * @throws BaseException 系统捕捉异常，根据异常类型抛出
   *
   */

  public ActionForward doDelete(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
    ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
    if (forward != null)
    {
      return forward;
    }

    //当前的ActionForm---------QysdsjbForm
    QysdsjbForm qysdsjbForm = (QysdsjbForm) actionForm;
    //初始化数据传递容器
    UserData userData = this.getUserData(httpServletRequest);
    //初始化数据传递容器
    VOPackage vo = new VOPackage();
    //设置后台调用Action值-----SAVEACTION
    vo.setAction(CodeConstant.SMSB_DELETEACTION);
    //设置容器里的Data数据,在我们这儿就是CzzsjdForm
    vo.setData(qysdsjbForm);
    vo.setUserData(userData);
    //设置Proxy要调用的processor的类名
    vo.setProcessor(
        "com.ttsoft.bjtax.smsb.sbzl.qysdsjb.processor.QysdsjbProcessor");
    try
    {
      //调用Proxy，初始化CzzsjdForm中值
      SbzlProxy.getInstance().process(vo);
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setJsjdm("");
      List dataList = QysdsjbHelper.getShowList();
      qysdsjbForm.setDataList(dataList);
      httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                      qysdsjbForm);
      httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                      "删除成功！");
      //保存成功,流转
      return actionMapping.findForward("Delete");
    }
    catch (Exception ex)
    {
      //系统捕捉异常，根据异常类型抛出
      qysdsjbForm.reset(actionMapping, httpServletRequest);
      qysdsjbForm.setDataList(QysdsjbHelper.getShowList());
      qysdsjbForm.setJsjdm("");
      throw ExceptionUtil.getBaseException(ex);
    }
  }

}
