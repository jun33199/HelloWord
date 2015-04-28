package com.ttsoft.bjtax.smsb.zhsb.web;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

public class ZnjwhAction extends SmsbAction{
    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">上门申报</font>&gt;<font color=\"#7C9AAB\">滞纳金维护</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "综合申报");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsbjkswh-000.htm");
    }

    /**
     * 查询对应的未入库缴款书
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("=====com.ttsoft.bjtax.smsb.zhsb.web.ZnjwhAction == doQuery()");

        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        ZnjwhActionForm form = (ZnjwhActionForm) actionForm;

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZnjwhProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        try
        {
            form = (ZnjwhActionForm) ZhsbProxy.getInstance().process(vo);
            //保存返回值--------Shi Yanfeng 20031029-------

            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书信息失败!");
        }
    }

    /**
     * 察看一票一税缴款书信息
     * modified by qianchao 2005.11.2
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpys (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        ZnjwhActionForm form = (ZnjwhActionForm) actionForm;
        ZnjwhMxActionForm form1 = new ZnjwhMxActionForm();
        form1.setJkpzh(form.getJkpzhStr());
        form1.setJsjdm(form.getJsjdm());
        form1.setQsrq(form.getQsrq());
        form1.setJzrq(form.getJzrq());
        form1.setSbbh(form.getSbbh());
        form1.setForward("Back");
        //设置申报编号标志
        httpServletRequest.setAttribute("znjwhMxActionForm", form1);
        return actionMapping.findForward("Ypys");
    }

    /**
     * 察看一票多税缴款书信息
     * modified by qianchao 2005.11.2
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpds (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
      /*
        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;

        ZhsbjksypdsActionForm form1 = new ZhsbjksypdsActionForm();
        form1.setSbbh(form.getSbbh());
        form1.setJsjdm(form.getJsjdm());
        form1.setForward("Back");

        httpServletRequest.setAttribute("zhsbjksypdsActionForm", form1);
        return actionMapping.findForward("Ypds");
       */
      ZhsbjkswhActionForm pf1 = (ZhsbjkswhActionForm) actionForm;
      JksqdPrintForm pf = new JksqdPrintForm();

      Debug.out("pf1.getJsjdm()=" + pf1.getJsjdm());
      Debug.out("pf1.getSbbh()=" + pf1.getSbbh());

      pf.setH_jsjdm(pf1.getJsjdm());
      pf.setH_sbbh(pf1.getSbbh());
      pf.setJsjdm(pf1.getJsjdm());
      pf.setSbbh(pf1.getSbbh());
      pf.setHeadSjly(CodeConstant.SMSB_SJLY_SBLR); //数据来源
      pf.setActionType("Show");
      httpServletRequest.setAttribute("jksqdPrintForm", pf);
      return actionMapping.findForward("PrintSQD");

    }

    /**
     * 撤销缴款书
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doCx (ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //防止refresh
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;

        form.setCxStr("");
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbjkswhProcessor");
        vo.setData(form);
        //总控信息
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            form = (ZhsbjkswhActionForm) ZhsbProxy.getInstance().process(vo);

            //start add by qianchao 2005.11.3
            //form.setCxStr(this.getWarStr(form.getCoList()));
            form.setSbbh("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "撤销成功！");
            //end add by qianchao 2005.11.3

            //保存返回值--------Shi Yanfeng 20031029-------
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Cx");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "撤销缴款书失败!");
        }
    }

    /**
     * 根据列表得到提示信息
     * @param list 相关明细列表
     * @return String 提示信息
     */
    private String getWarStr (List list)
    {
        String ret = "";
        String sf = "附加税";
        Map map = new HashMap();
        for (int i = 0; i < list.size(); i++)
        {
            Sbjkmx temp = (Sbjkmx) list.get(i);
            //不显示重复的缴款书
            if (map.get(temp.getJkpzh().substring(0, 15)) != null)
            {
                continue;
            }
            map.put(temp.getJkpzh().substring(0, 15), temp);

            if (temp.getJkpzh().substring(15, 16).equals("0"))
            {
                //一票一税的16位票号
                ret = ret + "<li>" + temp.getJkpzh();
            }
            else
            {
                //一票多税的15位票号
                ret = ret + "<li>" + temp.getJkpzh().substring(0, 15);

            }
            //判断是主税相关还是附税相关
            String szsm = CodeUtils.getCodeMapValue("SZSMYFJS",
                "szsmdm", temp.getSzsmdm(),
                "fjsszsmdm");
            if (szsm != null && !szsm.equals(""))
            {
                sf = "主税";
            }
        }
        if (!ret.equals(""))
        {
            ret = "<font color=red>撤销涉及其" + sf + "缴款书</font>" + ret;

        }
        return ret;
    }

    /**
     * 返回综合申报主页面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doBack (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;

        Debug.out("=====com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhAction==in doBack()");

        return actionMapping.findForward("Back");
    }

    /**
     * 页面初始展现
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
    	ZnjwhActionForm form = (ZnjwhActionForm) actionForm;
    	form.setQsrq(getQsrq());
    	form.setJzrq(getJzrq());
        return actionMapping.findForward("Show");
    }

    private String getQsrq()
    {

        Calendar car = Calendar.getInstance();
        car.add(Calendar.MONTH, -1);
        String year = new Integer(car.get(Calendar.YEAR)).toString();
        String month = "";
        int intMonth = car.get(Calendar.MONTH);
        intMonth++;
        if(intMonth<10)
        {
        	month = "0" + new Integer(intMonth).toString();
        }
        else
        {
        	month = new Integer(intMonth).toString();
        }

        String date = year+month+"01";
    	return date;
    }

    private String getJzrq() throws BaseException
    {
    	return DateTimeUtil.getCurrentDate();
    }

}
