/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.ExcelParser;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 三代申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DsdzdkAction
    extends SmsbAction
{

    //数据来源 录入
    public final static String SMSB_SJLY_LR = "0";

    //数据来源 导入
    public final static String SMSB_SJLY_DR = "1";

    private UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;代征代扣代缴表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "代征代扣代缴表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/dsdzdk/dsdzdk-000.htm");

    }

    /**
     * 取业务信息
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *         voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_SHOWACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doShow方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
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

        //当前的ActionForm---DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());

        //初始化数据传递容器
        VOPackage vo = new VOPackage();

        //设置后台调用Action值---SHOWACTION
        vo.setAction(CodeConstant.SMSB_SHOWACTION);

        //设置容器里的Data数据,这里存放DsdzdklrForm
        vo.setData(sdForm);
        vo.setUserData(userData);
        //设置Proxy要调用的processor的类---DsdzdkProcessor
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        try
        {
            //调用Proxy，初始化DsdzdklrForm中值
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            //流转
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
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *         voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_QUERYACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doQuery方法设置取业务数据的结果。
     * 5、根据得到的zsfs(征收方式)的数值来决定跳转页面以及业务异常类型抛出
     * 6、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
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

        //当前的ActionForm---DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());
        sdForm.setExcelFile(null);

        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //设置后台调用Action值----QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);

        //设置容器里的Data数据,这里存放DsdzdklrForm
        vo.setData(sdForm);

        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        try
        {
            //调用Proxy，初始化DsdzdkForm中值
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            //流转
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 录入处理
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *        voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    // 录入处理
    public ActionForward doInput (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {

        //当前的ActionForm---DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());
        sdForm.setExcelFile(null);

        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //设置后台调用Action值----QUERYACTION
        vo.setAction(CodeConstant.SMSB_INPUTACTION);

        //设置容器里的Data数据,这里存放DsdzdklrForm
        vo.setData(sdForm);

        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        try
        {
            //调用Proxy，初始化DsdzdkForm中值
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            //流转
            return actionMapping.findForward("Input");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 文件上传处理
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *        voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    // 文件上传处理
    public ActionForward doUpload (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        //-------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            DsdzdkForm sdForm = (DsdzdkForm) form;
            sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));
            return forward;
        }

        DsdzdkForm sdForm = (DsdzdkForm) form;
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setSjly(SMSB_SJLY_DR);
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPLOADACTION);
        vo.setData(sdForm);
        vo.setUserData(userData);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");

        try
        {
            //调用processor
            //sdForm = (DsdzdkForm)SbzlProxy.getInstance().process(vo);

            // 存放上传文件中三代明细数据的列表
            List mxList = new ArrayList();

            // EXCEL文件解析器
            ExcelParser excelParser = new ExcelParser();
            java.io.InputStream inFile;
            try
            {
                inFile = sdForm.getExcelFile().getInputStream();
            }
            catch (Exception ex)
            {
                throw new ApplicationException("系统无法上传导入Excel文件!");
            }
            //解析文件
            mxList = excelParser.parseExcel(inFile);

            //检查上传数据的合法性同时计算合计数
            sum(mxList, sdForm);

            sdForm.setMxDataList(mxList);
            sdForm.setExcelFile(null);

            request.setAttribute(mapping.getAttribute(), sdForm);

            //保存导入的结果到会话
            request.getSession().setAttribute(CodeConstant.
                                              SESSIONKEY_SDDATALIST,
                                              sdForm.getMxDataList());
            request.getSession().setAttribute("jsjehj",
                                              sdForm.getJsjehj());
            request.getSession().setAttribute("sjsehj",
                                              sdForm.getSjsehj());
            //分页
            sdForm.setPgNum(1);
            List pgList = sdForm.getMxDataList();
            sdForm.setLength(CodeConstant.SD_PG_LENGTH);
            sdForm.setPgSum(pgList.size() % sdForm.getLength() == 0 ?
                            pgList.size() / sdForm.getLength() :
                            pgList.size() / sdForm.getLength() + 1);
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(mapping, request);
            sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Upload");
    }

    /**
     * 取业务信息
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *         voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_SAVEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doSave方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
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

        //-------------对数据库操作的Method进行修改，防止刷新或重复提交-------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //当前的ActionForm---------DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());
        sdForm.setExcelFile(null);
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        //按照columns中的字段提取所需要前台列表数据赋给DsdzdkForm的DataList中，
        sdForm.getMxDataList().clear();
        sdForm.getMxDataList().addAll(
            getValuesToList(httpServletRequest, sdForm.getColumns()));

        if (SMSB_SJLY_DR.equals(sdForm.getSjly()))
        {
            List dataList = new ArrayList();
            Iterator items = sdForm.getMxDataList().iterator();
            while (items.hasNext())
            {
                Map item = (Map) items.next();
                item.put("bdzrmc",
                         SfStringUtil.ISO2GBK( (String) item.get("bdzrmc")));
                item.put("szmc", SfStringUtil.ISO2GBK( (String) item.get("szmc")));
                item.put("szsmmc",
                         SfStringUtil.ISO2GBK( (String) item.get("szsmmc")));
                dataList.add(item);
                Debug.out(item);
            }
            sdForm.setMxDataList(dataList);
            Debug.out(sdForm.getMxDataList());
        }

        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值-----SAVEACTION

        vo.setAction(CodeConstant.SMSB_SAVEACTION);

        //设置容器里的Data数据,在我们这儿就是DsdzdkForm
        vo.setData(sdForm);
        vo.setUserData(userData);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");

        try
        {
            //调用Proxy，初始化DsdzdkForm中值
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 取业务信息
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *        voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * 4、调用doDelete方法设置取业务数据的结果。
     * 5、转向业务页面。
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *
     */

    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //调用processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 汇总申报交款单
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *        voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doHzsbjkd (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        //从session中取得导入的明细数据
        sdForm.setMxDataList( (List) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_SDDATALIST));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_HZSBJKDACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //调用processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Hzsbjkd");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 汇总申报交款单打印
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *        voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */

    public ActionForward doHzsbjkdp (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        //从session中取得导入的明细数据
        sdForm.setMxDataList( (List) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_SDDATALIST));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_HZSBJKDACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //调用processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Hzsbjkdp");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 撤销汇总申报交款单
     * 1、取得DsdzdkForm，产生一个VOPackage。
     * 2、取业务数据的参数，设置它为voPackage的data域。
     * 3、设置voPackage的Processor域为
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor，
     *        voPackage的Data域为DsdzdkForm
     * 	voPackage的action域为CodeConstant.SMSB_DELETEACTION
     * 	调用SbzlProxy的process方法。
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doCxhzsbjkd (ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
        throws
        BaseException
    {

        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_CXHZSBJKDACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //调用processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Cxhzsbjkd");
        }
        catch (Exception ex)
        {
            //系统捕捉异常，根据异常类型抛出
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     *  分页显示
     *
     *  @param mapping struts.action.ActionMapping
     *  @param aForm DsdzdkForm
     *  @param request HttpServletRequest
     *  @param response HttpServletResponse
     *  @return actionMapping.findForward的跳转目标
     *  @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    public ActionForward doGotopage (ActionMapping mapping,
                                     ActionForm aForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            DsdzdkForm yForm = (DsdzdkForm) aForm;
            yForm.setNsrmc(SfStringUtil.ISO2GBK(yForm.getNsrmc()));
            yForm.setMxDataList( (ArrayList) request.getSession().getAttribute(
                CodeConstant.SESSIONKEY_SDDATALIST));
            yForm.setJsjehj( (BigDecimal) request.getSession().getAttribute(
                "jsjehj"));
            yForm.setSjsehj( (BigDecimal) request.getSession().getAttribute(
                "sjsehj"));
            return mapping.findForward("Gotopage");
        }
        catch (Exception e)
        {
            Debug.out("--------Action-Gotopage----------" + e.getMessage());
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 计算合计金额
     * @param sdForm 当前的ActionForm
     * @param mxList 已HashMap为节点的明细记录List
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     */
    private void sum (List mxList, DsdzdkForm sdForm)
        throws BaseException
    {
        //记税金额合计
        BigDecimal jsjeTotal = new BigDecimal(0.00);
        //实缴金额合计
        BigDecimal sjseTotal = new BigDecimal(0.00);

        for (int i = 0; i < mxList.size(); i++)
        {
            HashMap record = (HashMap) mxList.get(i);

            String jsje = (String) record.get("jsje"); //记税金额
            String sjse = (String) record.get("sjse"); //实缴税额

            BigDecimal thisSjse = new BigDecimal(sjse);
            thisSjse = thisSjse.setScale(2, BigDecimal.ROUND_HALF_UP);
            sjseTotal = sjseTotal.add(thisSjse);

            BigDecimal thisJsje = new BigDecimal(jsje);
            thisJsje = thisJsje.setScale(2, BigDecimal.ROUND_HALF_UP);
            jsjeTotal = jsjeTotal.add(thisJsje);
        }

        sdForm.setJsjehj(jsjeTotal);
        sdForm.setSjsehj(sjseTotal);
    }

    /** doPrint
     *  @param mapping struts.action.ActionMapping
     *  @param form DsdzdkForm
     *  @param request HttpServletRequest
     *  @param response HttpServletResponse
     *  @return actionMapping.findForward的跳转目标
     *  @throws BaseException 系统捕捉异常，根据异常类型抛出
     * */
    public ActionForward doPrint (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        Debug.out("--- Debug --- Here is GtgshWszlrAction"
                  + ".doDismiss");
        try
        {
            //把预装载的信息传递给下一个页面
            JksPrintForm pf = new JksPrintForm();
            DsdzdkForm pf1 = (DsdzdkForm) form;
            pf.setHeadJkpzh(pf1.getHeadJkpzh());
            pf.setHeadJsjdm(pf1.getHeadJsjdm());
            pf.setHeadSjly(CodeConstant.SMSB_SJLY_SDHZ); //数据来源
            pf.setActionType("Show");
            request.setAttribute("jksPrintForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Print");
    }

}