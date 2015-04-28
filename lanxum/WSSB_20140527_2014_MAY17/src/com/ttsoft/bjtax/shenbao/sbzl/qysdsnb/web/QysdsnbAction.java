package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.QysdsnbActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.QysdsnbMapConstant;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * 企业所得税年报action
 */
public class QysdsnbAction extends ShenbaoAction
{
    protected int getActionID()
    {
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.QYND;
    }

    /**
     * 获取纳税人的申报数据并显示申报页面
     *
     * 1、从申报报表项目代码数据表中取出企业财务指标的定义数据，放入form中
     *
     * 2、从税费管理中得到该纳税人的征收方式放入form中，根据征收方式不同使相应的行次的?
     * 据域为可编辑。
     *
     * 3、从企业财务指标申报表中取出纳税人的申报数据，放入form对应的数组中
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        QysdsnbForm nbForm = (QysdsnbForm)form;
        String frompage = nbForm.getFrompage();
        if (frompage != null && !frompage.equals("")){
           request.getSession().setAttribute("FROMPAGE",frompage);
       }else{
           request.getSession().removeAttribute("FROMPAGE");
       }

        //获得计算机代码
        UserData userData = getUserData(request);
        String jsjdm = userData.yhid;

        //获得当前时间
        Timestamp curTime = new Timestamp(System.currentTimeMillis());

        Map map = new HashMap();
        map.put(QysdsnbMapConstant.STRING_KEY_JSJDM, jsjdm);
        map.put(QysdsnbMapConstant.STRING_KEY_DATE, curTime);
        map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, FriendHelper.getSWDJJBSJ(request));

        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
        vo.setAction(QysdsnbActionConstant.INT_ACTION_QUERY_All);
        vo.setData(map);

        nbForm.setActionType("Show");

        try
        {
            //根据计算机代码和当前时间查询企业所得税年报数据
            QysdsnbData data = (QysdsnbData)ShenbaoProxy.getInstance().process(vo);

            QysdsHelper helper = new QysdsHelper();

            //设置登记数据
            helper.setDjInfo(data.getDjJbsj(), nbForm);

            //设置年报数据
            helper.setQysdsnbData(data.getNbData(), nbForm);

            //设置财务指标数据
            helper.setCwzbData(data.getCwzbData(), nbForm);

            if(nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SYDW) ||
               nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SHTT))
            {
                //设置事业单位数据
                helper.setSydwData(data.getSydwData(), nbForm);
            }

            //设置联营股份数据
            helper.setLygfData(data.getLygfData(), nbForm);

            //设置税款所属日期
            helper.setSkssrq(curTime, nbForm);

            //设置税费管理相关的数据（税率和审批资格）
            helper.setSfglData(userData, nbForm, data.getDjJbsj());
        }
        catch(Exception ex)
        {
            String message = null;
            if(ex instanceof ApplicationException)
            {
                message = ( (ApplicationException)ex).getMessage();
            }
            else
            {
                ex.printStackTrace();
                message = "系统异常，请与管理员联系";
            }

            ActionErrors errors = new ActionErrors();

            errors.add("", new ActionError("error.server.custom", message));
            saveErrors(request, errors);

            nbForm.setActionType("InitError");

            return(new ActionForward(mapping.getInput()));
        }

        return mapping.findForward("Show");
    }

   /**
    * 执行存盘操作
    *
    * 1、检查数据
    * 2、调用QysdsHelper，获得财务指标申报数据list
    * 3、构造vopackage
    * 4、调用proxy的方法存盘
    * 5、显示qycwzb009.jsp
    * @param mapping ActionMapping
    * @param form ActionForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return ActionForward
    */
   public ActionForward doSave(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
   {
       //start added code by qianchao 2006-2-11
       //为签名压力测试
       /*
       ActionForward tokenError = doHandleToken(mapping,request);
       if(tokenError!= null)
       {
           removeForm(mapping,request);
           return tokenError;
       }*/
       //end   added code by qianchao 2006-2-11

       QysdsnbForm nbForm = (QysdsnbForm)form;

       UserData userData = getUserData(request);

       //started added by qianchao 2005-12-27
       //压力测试加入
       String tmpjsjdm = request.getParameter("qcjsjdm");
       //ended   added by qianchao 2005-12-27
       
       
       QysdsnbData nbdata = new QysdsnbData();

       QysdsHelper helper = new QysdsHelper();
       Timestamp now = new Timestamp(System.currentTimeMillis());
       
       
       
       // started added by qianchao 2006-2-11
       DzyjsjVO dzyj = null;
       
       String strOrginData = request.getParameter("SecX_OrginData");
       String ContainerName = request.getParameter("SecX_ContainerName");
       String signData = request.getParameter("SecX_SignData");

       System.out.println("signData      : " + signData);
       System.out.println("OrginData     : " + strOrginData);
       System.out.println("ContainerName : " + ContainerName);
       
       
       if ((signData != null) && userData.caflag) 
       {
           String SecX_Error = request.getParameter("SecX_Error");
           if (! "0".equals(SecX_Error))
           {
               String tempstr;
               tempstr = "解密验签名错误!Error:" + SecX_Error
               + " SecX_OD " + userData.getYhid() + ":" + strOrginData
               + "-----SecX_SD:" + signData  + "-----";
               System.out.println(tempstr);
               ActionErrors errors = new ActionErrors();
               errors.add("", new ActionError("error.server.custom", "解密验签名错误！errorcode:" + SecX_Error));
               saveErrors(request, errors);
               return (new ActionForward(mapping.getInput()));
           }
           
           System.out.println("jsjdm         : " + tmpjsjdm);
           ContainerName = userData.getCert().getContainerName();
           
           System.out.println("============保存签名数据开始==============");
           // /检测签名并保存

           GregorianCalendar calendar = new GregorianCalendar();
           calendar.setTime(now);
           int year = calendar.get(calendar.YEAR) - 1;
           
           String ywuid = nbForm.getJsjdm()+ "+" + year;            
           try
           {
//               dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(
//                       userData, strOrginData, signData,
//                       codeConstants.YWDM_SB_WS_QYSDS_NB, ywuid,
//                       codeConstants.YWCZLX_MODIFY);                
               System.out.println(" lsh=" + dzyj.getLsh());
           }
           catch (Exception ex)
           {
               ex.printStackTrace();

               ActionErrors errors = new ActionErrors();
               errors.add("", new ActionError("error.server.custom", "保存签名错误！"));
               saveErrors(request, errors);
               return (new ActionForward(mapping.getInput()));
           }
           System.out.println("============保存签名数据结束==============");
       }
       // ended added by qianchao 2006-2-11
       
       
       
       try
       {

           SWDJJBSJ djInfo = FriendHelper.getSWDJJBSJ(request);
           //企业所得税年报数据
           helper.getQysdsnbData(userData, nbForm, nbdata, djInfo);
           //财务指标数据
           helper.getCwzbData(userData, nbForm, nbdata, djInfo);
           //事业单位数据
           if(nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SYDW) ||
              nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SHTT))
           {
               helper.getSydwData(userData, nbForm, nbdata, djInfo);
           }
           //联营股份数据
           helper.getLygfData(userData, nbForm, nbdata, djInfo);

           //获得计算机代码
           String jsjdm = userData.yhid;

           //获得当前时间
           Timestamp curTime = new Timestamp(System.currentTimeMillis());

           Map map = new HashMap();
           map.put(QysdsnbMapConstant.STRING_KEY_JSJDM, jsjdm);
           map.put(QysdsnbMapConstant.STRING_KEY_DATE, curTime);
           map.put(QysdsnbMapConstant.STRING_KEY_QYSDSNB_DATA, nbdata);
           map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, djInfo);

           VOPackage vo = new VOPackage();
           vo.setUserData(userData);
           vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
           vo.setAction(QysdsnbActionConstant.INT_ACTION_SAVE_ALL);
           vo.setData(map);

           //根据计算机代码和当前时间查询企业所得税年报数据
           ShenbaoProxy.getInstance().process(vo);
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税年度纳税申报表申报", (new SimpleDateFormat("yyyyMMdd")).format(now), "成功!");
       }
       catch(Exception ex)
       {
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税年度纳税申报表申报", (new SimpleDateFormat("yyyyMMdd")).format(now), "失败!");
           String message = null;
           if(ex instanceof BaseException)
           {
               message = ( (BaseException)ex).getMessage();
           }
           else
           {
               ex.printStackTrace();
               message = "系统异常，请与管理员联系";
           }

           ActionErrors errors = new ActionErrors();

           errors.add("", new ActionError("error.server.custom", message));
           saveErrors(request, errors);
           return(new ActionForward(mapping.getInput()));
       }

       removeForm(mapping,request);
       request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                            "保存企业所得税年度申报表成功！");
       return mapping.findForward("Save");
   }

   /**
    * 返回申报主界面
    * @param mapping ActionMapping
    * @param form ActionForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return ActionForward
    */
   public ActionForward doReturn(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
   {
       String frompage = (String)request.getSession().getAttribute("FROMPAGE");
       if (frompage != null && !frompage.equals("")){
           removeForm(mapping, request);
           request.getSession().removeAttribute("FROMPAGE");
           return mapping.findForward(frompage);
       }else{
           removeForm(mapping, request);

           //转向返回后的页面。
           return mapping.findForward("Return");
       }
/*       removeForm(mapping,request);
       return mapping.findForward("Return");*/
   }


   /**
    * 执行删除操作
    * @param mapping ActionMapping
    * @param form ActionForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return ActionForward
    */
   public ActionForward doRemove(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
       throws BaseException
   {
       ActionForward tokenError = doHandleToken(mapping,request);
       if(tokenError!= null)
       {
           removeForm(mapping,request);
           return tokenError;
       }

       QysdsnbForm nbForm = (QysdsnbForm)form;

       //获得计算机代码
       UserData userData = getUserData(request);
       String jsjdm = userData.yhid;

       //获得当前时间
       Timestamp curTime = new Timestamp(System.currentTimeMillis());

       try
       {
           Map map = new HashMap();
           map.put(QysdsnbMapConstant.STRING_KEY_JSJDM,jsjdm);
           map.put(QysdsnbMapConstant.STRING_KEY_DATE,curTime);
           map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, FriendHelper.getSWDJJBSJ(request));

           VOPackage vo = new VOPackage();
           vo.setUserData(userData);
           vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
           vo.setAction(QysdsnbActionConstant.INT_ACTION_DELETE_ALL);
           vo.setData(map);

           //根据计算机代码和当前时间删除企业所得税年报数据
           ShenbaoProxy.getInstance().process(vo);
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "删除企业所得税年度纳税申报表", (new SimpleDateFormat("yyyyMMdd")).format(curTime), "成功!");
       }
       catch(Exception ex)
       {
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "删除企业所得税年度纳税申报表", (new SimpleDateFormat("yyyyMMdd")).format(curTime), "失败!");
           String message = null;
           if(ex instanceof BaseException)
           {
               message = ( (BaseException)ex).getMessage();
           }
           else
           {
               ex.printStackTrace();
               message = "系统异常，请与管理员联系";
           }

           ActionErrors errors = new ActionErrors();

           errors.add("", new ActionError("error.server.custom",message));
           saveErrors(request, errors);
           return(new ActionForward(mapping.getInput()));
       }

       removeForm(mapping,request);
       request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                            "删除企业所得税年度申报表成功！");
       return mapping.findForward("Success");
   }
}
