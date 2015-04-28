package com.ttsoft.bjtax.smsb.wrkcx.web;
import java.util.*;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import java.io.OutputStream;
import com.ttsoft.bjtax.smsb.wrkcx.WrkVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import com.ttsoft.framework.exception.ApplicationException;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import com.ttsoft.bjtax.dj.model.JBSJ;
import com.ttsoft.bjtax.dj.model.ZJG;
import com.ttsoft.bjtax.dj.model.SWDL;
import com.ttsoft.bjtax.smsb.wrkcx.processor.DjxxxxBO;
import com.ttsoft.framework.util.JspUtil;
import com.ttsoft.bjtax.smsb.wrkcx.processor.ConstantKey;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class NsrxxAction extends SmsbAction{
  public NsrxxAction() {
  }

  /**
      *   汇总查询
      *   1，从form中取查询条件,并把查询条件放到SybdcxBO中去
      *   2，将查询结果放到session中
      *   @param mapping The ActionMapping used to select this instance
      *   @param form The optional ActionForm bean for this request (if any)
      *   @param request The HTTP request we are processing
      *   @param response The HTTP response we are creating
      *   @return 返回跳转的页面
      *   @throws BaseException 抛出应用异常
      */
     public ActionForward doQuery_DjInfo(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws BaseException
     {
         System.out.println("---------nsrxxAction----doQuery_DjInfo");
         HttpSession session = request.getSession(false);
         session.removeAttribute(WebConstantKey.SESSION_KEY_DJXXXXBO);
         NsrxxForm theForm = (NsrxxForm) form;
         String dj_jsjdm = theForm.getDj_jsjdm();
         try
         {
             com.ttsoft.bjtax.dj.util.DjxxUtil djproxy = new com.ttsoft.bjtax.dj.util.DjxxUtil();
             HashMap map = (HashMap) djproxy.getDjDetailInfo(dj_jsjdm);
             DjxxxxBO djbo = new DjxxxxBO();
             //查询基本信息
             djbo.setJbsj( (JBSJ) map.get("JBSJ"));
             JBSJ jbsj=(JBSJ) map.get("JBSJ");

             //查询总机构
             djbo.setZjg( (ZJG) map.get("ZJG"));
             //税务代理信息
             djbo.setSwdl( (SWDL) map.get("SWDL"));
             //查询变更历史
             djbo.setBgxxList( (List) map.get("BGSJ_LS"));
             //查询吊销登记
             djbo.setDxdjList( (List) map.get("DXHZCHCL"));
             //查询吊销登记历史
             djbo.setDxdjList_ls( (List) map.get("DXHZCHCL_LS"));
             //查询分支机构
             djbo.setFzjgList( (List) map.get("FZJG"));
             //查询停复业
             djbo.setTydjList( (List) map.get("TFY"));
             //查询停复业历史
             djbo.setTydjList_ls( (List) map.get("TFY_LS"));
             //查询银行帐号
             djbo.setYhzhList( (List) map.get("YHZH"));
             //查询注销登记
             djbo.setZxdjList( (List) map.get("ZXDJ"));
             //税务登记——联系人
             djbo.setLxrList( (List) map.get("QYRY"));
             //税务登记——投资方登记信息
             djbo.setTzfList( (List) map.get("TZF"));

             session.setAttribute(WebConstantKey.SESSION_KEY_DJXXXXBO, djbo);
         }
         catch(Exception e)
         {
             throw new ApplicationException("查询纳税人登记详细信息时,远程调用错误！");
         }
         //跳转页面
         return mapping.findForward("Query_DjInfo");
     }

     /**
  * 跳转到其他状态信息
  * @param mapping mapping对象
  * @param form 页面对象
  * @param request 页面请求
  * @param response 页面响应
  * @return 返回跳转的页面
  * @throws BaseException 抛出应用异常
  */
 public ActionForward doQtztxx(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
     BaseException
 {

     NsrxxForm cxForm = (NsrxxForm) form;
     //把返回Action重新赋值
     cxForm.setBackAction(cxForm.getBackAction());

     return mapping.findForward("Qtztxx");
 }

 /**
  * 跳转到分支机构
  * @param mapping mapping对象
  * @param form 页面对象
  * @param request 页面请求
  * @param response 页面响应
  * @return 返回跳转的页面
  * @throws BaseException 抛出应用异常
  */
 public ActionForward doFzjgxx(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
     BaseException
 {

     NsrxxForm cxForm = (NsrxxForm) form;
     //把返回Action重新赋值
     cxForm.setBackAction(cxForm.getBackAction());

     return mapping.findForward("Fzjgxx");
 }

 /**
  * 跳转到基本数据
  * @param mapping mapping对象
  * @param form 页面对象
  * @param request 页面请求
  * @param response 页面响应
  * @return 返回跳转的页面
  * @throws BaseException 抛出应用异常
  */
 public ActionForward doJbsj(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws
     BaseException
 {
     HttpSession session = request.getSession();
     NsrxxForm cxForm = (NsrxxForm) form;

     //把返回Action重新赋值
     cxForm.setBackAction(cxForm.getBackAction());

     return mapping.findForward("Jbsj");
 }

 /**
   * 跳转到银行帐号信息
   * @param mapping mapping对象
   * @param form 页面对象
   * @param request 页面请求
   * @param response 页面响应
   * @return 返回跳转的页面
   * @throws BaseException 抛出应用异常
   */
  public ActionForward doYhzhxx(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException
  {

      NsrxxForm cxForm = (NsrxxForm) form;
      //把返回Action重新赋值
      cxForm.setBackAction(cxForm.getBackAction());

      return mapping.findForward("Yhzhxx");
  }
  /**
    * 跳转到总机构信息
    * @param mapping mapping对象
    * @param form 页面对象
    * @param request 页面请求
    * @param response 页面响应
    * @return 返回跳转的页面
    * @throws BaseException 抛出应用异常
    */
   public ActionForward doZjgxx(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
       BaseException
   {
       NsrxxForm cxForm = (NsrxxForm) form;
       //把返回Action重新赋值
       cxForm.setBackAction(cxForm.getBackAction());

       return mapping.findForward("Zjgxx");
   }
   /**
      * 跳转到变更信息
      * @param mapping mapping对象
      * @param form 页面对象
      * @param request 页面请求
      * @param response 页面响应
      * @return 返回跳转的页面
      * @throws BaseException 抛出应用异常
      */
     public ActionForward doBgxx(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
         BaseException
     {

         NsrxxForm cxForm = (NsrxxForm) form;
         //把返回Action重新赋值
         cxForm.setBackAction(cxForm.getBackAction());

         return mapping.findForward("Bgxx");
     }


     /**
          *   初始化Action参数，设置标题、帮助信息等公用信息
          *   初始化页面代码表和页面header信息；
          *   @param mapping The ActionMapping used to select this instance
          *   @param form The optional ActionForm bean for this request (if any)
          *   @param request The HTTP request we are processing
          *   @param response The HTTP response we are creating
          */
         public void initialRequest(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
         {
             String init = JspUtil.displayValue(request.getParameter("initType"));
             NsrxxForm theForm = (NsrxxForm) form;
             String query = theForm.getHzcxlx();
             int initType = 0;
             if (init != null && init.trim().length() > 0)
             {
                 initType = Integer.valueOf(init).intValue();
             }
             if (query != null && query.trim().length() > 0)
             {
                 initType = Integer.valueOf(query).intValue();
             }
             request.setAttribute(WebConstantKey.RAK_REQUEST_URI,
                                  request.getServletPath());
             request.setAttribute(WebConstantKey.RAK_ACTION,
                                  WebConstantKey.ACTION_HZCX);

                     request.setAttribute(ConstantKey.HEAD_HELPURL,
                                          "");
                     request.setAttribute(ConstantKey.HEAD_POSITION,
                                          "综合服务管理信息系统&gt;申报征收&gt;申报入库不一致查询&gt;纳税人详细信息");
                     request.setAttribute(ConstantKey.HEAD_TITLE, "纳税人详细信息");
                     theForm.setTitle("纳税人详细信息");


         }



}