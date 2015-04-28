/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.jmssb.web;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
//import java.util.*;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.common.model.UserData;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 北京市减免税申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */


public class JmssbAction
    extends SmsbAction
{
    private UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm JmssbForm
     * @param httpServletRequest HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;减免税申报表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "减免税申报表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/jmssb/jmssb-000.htm");
    }

    /**
     * 取业务信息
     * 1、取得JmssbForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor，
     *         voPackage的Data域为JmssbForm
     * 	voPackage的action域为CodeConstant.SMSB_SHOWACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //当前的ActionForm
        JmssbForm form = (JmssbForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //设置Proxy要调用的processor的类名
//    //得到当前时间
//    //Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (JmssbForm) SbzlProxy.getInstance().process(vo);
//      form.setCjrq(now);
//      form.setSbrq(SfDateUtil.getDate());
            //流转
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            Debug.out("-------------------------");
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 取业务信息
     * 1、取得JmssbForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor，
     *         voPackage的Data域为JmssbForm
     * 	voPackage的action域为CodeConstant.SMSB_QUERYACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doQuery方法设置取业务数据的结果。
     * 5、根据得到的zsfs(征收方式)的数值来决定跳转页面以及业务异常类型抛出
     * 6、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *         ApplicationException 业务异常类型抛出
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //当前的ActionForm
        JmssbForm form = (JmssbForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setUserData(userData);
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            form = (JmssbForm) SbzlProxy.getInstance().process(vo);
            //流转
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            Debug.out("-------------------------");
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得JmssbForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor，
     *    voPackage的Data域为JmssbForm
     * 	voPackage的action域为CodeConstant.SMSB_SAVEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doSave方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *
     */
    public ActionForward doSave (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm
        JmssbForm form = (JmssbForm) actionForm;
        userData = this.getUserData(httpServletRequest);
        form.setLrr(userData.getYhid());
        //将前台列表的数据赋给ActionForm的DataList
        String columns[] = form.getColumns();
        //得到明细提交数据，并且过滤不符合条件的数据，如税种税目为空的数据
        SfHashList tmpList = new SfHashList(getValuesToList(httpServletRequest,
            columns));
        tmpList.filter("szsmdm", "");
        String pks[] = new String[tmpList.getRecords().size()];
        for(int i=0; i<tmpList.getRecords().size(); i++) {
        	String pk = ((Map)tmpList.getRecords().get(i)).get("jmlx") + "-" +
        	((Map)tmpList.getRecords().get(i)).get("szsmdm") + "-" +
        	((Map)tmpList.getRecords().get(i)).get("jmxmjdm");

        	int temp = i + 1;
        	for(int ii=0; ii<pks.length; ii++) {
        		if(pk.equals(pks[ii])) {
        			throw ExceptionUtil.getBaseException(new Exception("第" + temp + "行数据重复了，减免分类、减免税种（税目）代码、减免项目及代码不能完全相同！"));
        		}
        	}
        	pks[i] = pk;
        }
        
        form.setDataList(tmpList.getRecords());
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setUserData(userData);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            JmssbForm waringform = (JmssbForm) SbzlProxy.getInstance().process(
                vo);
            //流转
            Debug.out("waringform=" + waringform);

            if (waringform != null)
            {
                httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                                waringform);
                return actionMapping.findForward("Error");
            }

            form.reset(actionMapping, httpServletRequest);
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "保存成功！");
            return actionMapping.findForward("Save");
            //流转

        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取业务信息
     * 1、取得JmssbForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor，
     *        voPackage的Data域为JmssbForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doDelete方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *
     */

    public ActionForward doDelete (ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm
        JmssbForm form = (JmssbForm) actionForm;
        /*补充默认展现需要带出的内容*/
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        //设置容器里的Data数据,在我们这儿就是ActionForm
        vo.setData(form);
        vo.setUserData(userData);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.jmssb.processor.JmssbProcessor");
        try
        {
            //调用Proxy，初始化ActionForm中值
            SbzlProxy.getInstance().process(vo);

            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            //流转
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                            "删除成功！");
            return actionMapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            form.reset(actionMapping, httpServletRequest);
            form.setJsjdm("");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}