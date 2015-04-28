package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hznszjgfpb.web;
/**
 * <p>Title: 企业所得税汇总纳税总机构分配表action</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Company: 北京立思辰电子系统有限公司</p>
 *
 * @author wangxq
 * @version 1.0
 */

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gqtzsdmxb.web.GqtzsdmxbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;

public class HznszjgfpbAction extends SmsbAction
{
	 /**
     * 操作员数据
     */

    private UserData userData = null;
   public HznszjgfpbAction()
   {
   }

   /**
    * 公共的前置处理方法，每次进入页面都会被调用<BR>
    * 设置页面显示时使用的导航信息和标题
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param actionForm
    *            QysdsnbForm
    * @param httpServletRequest
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    */

   protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
           HttpServletRequest httpServletRequest, HttpServletResponse response)

   {
       super.initialRequest(mapping, actionForm, httpServletRequest, response);
       httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                       "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;<font color=\"#7C9AAB\">企业所得税汇总纳税总机构分配表</font></td>");
       httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
               "企业所得税汇总纳税总机构分配表");
       httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
               "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

   }

   /**
    * 初始化页面数据
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward的跳转目标
    * @throws BaseException
    *             系统捕捉异常，根据异常类型抛出
    */

/*    public ActionForward doShow(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {

       try {
           // 获取ZfjgqysdsjbForm对象
           ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
           // 获取本缴费系统用户信息
           UserData userData = this.getUserData(request);
           // 初始化数据传递容器
           VOPackage vo = new VOPackage();
           // 设置后台调用Action值---SHOWACTION
           vo.setAction(CodeConstant.SMSB_SHOWACTION);
           // 设置容器里的Data数据
           vo.setData(zfjgForm);
           // 设置本缴费系统用户信息
           vo.setUserData(userData);
           // 设置Proxy要调用的processor的类---ZfjgqysdsjbProcessor
           vo.setProcessor(CodeConstant.SBZL_QYSDSJB2012_ZFJG_PROCESSOR);

           // 调用Proxy，初始化CzqysdsjbForm中值
           zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
           // 将HdzssdsjbForm 放入request中
           System.out.println("mapping.getAttribute() = " + mapping.getAttribute());
           request.setAttribute(mapping.getAttribute(), zfjgForm);

           return mapping.findForward("Show");

       } catch (Exception ex) {
           // 系统捕捉异常，根据异常类型抛出
           throw ExceptionUtil.getBaseException(ex);
       }
   }*/

   /**
    * 查询已申报数据
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward的跳转目标
    * @throws BaseException
    *             系统捕捉异常，根据异常类型抛出
    */
   public ActionForward doQuery(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {
   	
   	    System.out.println("HznszjgfpbAction####ddddddd##########doQuery");
//   	CzzssdsjbForm czzssdsjbForm=(CzzssdsjbForm)request.getAttribute("czzssdsjbForm");
   	   QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
       // 获取ZfjgqysdsjbForm对象
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       System.out.println("22####hznszjgfpbForm.getYnsdse()="+hznszjgfpbForm.getYnsdse());
       if(request.getParameter("skksrq")!=null){
    	   hznszjgfpbForm.setSkssksrq(request.getParameter("skksrq"));
       }else{
    	   hznszjgfpbForm.setYnsdse(null);
    	   hznszjgfpbForm.setZjgftse(null);
    	   hznszjgfpbForm.setZjgfpse(null);
    	   hznszjgfpbForm.setFzjgftse(null);
       }
       if(request.getParameter("skjsrq")!=null){
    	   hznszjgfpbForm.setSkssjsrq(request.getParameter("skjsrq"));
       }
       
       hznszjgfpbForm.setJsjdm(baseForm.getJsjdm());
       hznszjgfpbForm.setSbrq(baseForm.getSbrq());
       System.out.println("###baseForm.getNsrmc()="+baseForm.getNsrmc());
      // hznszjgfpbForm.setNsrmc(baseForm.getNsrmc());
       hznszjgfpbForm.setZjgmc(baseForm.getNsrmc());
       
       // 获取本缴费系统用户信息
       UserData userData = this.getUserData(request);
       // 设置报表期类型-季报
       hznszjgfpbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
       // 设置税务计算机代码
       // czqysdsjbForm.setSwjsjdm(userData.getXtsbm1());
       // 设置录入人名称
       hznszjgfpbForm.setLrr(userData.getYhid());
       this.getBaseForm(request, hznszjgfpbForm);
       // 初始化数据传递容器
       VOPackage vo = new VOPackage();
       // 设置后台调用Action值---QUERYACTION
       vo.setAction(CodeConstant.SMSB_QUERYACTION);
       // 设置容器里的Data数据,这里存放HznszjgfpbForm
       vo.setData(hznszjgfpbForm);
       // 设置Proxy要调用的processor的类---ZfjgqysdsjbProcessor
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);
       // 设置系统用户信息
       vo.setUserData(userData);
       try {
           // 调用Proxy，执行processor,获取hznszjgfpbForm返回值
           hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           hznszjgfpbForm.setJglx("1");
           // 将czqysdsjbForm置入request,作为回显数据用
           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
           // 操作成功流转
           return mapping.findForward("Show");

       } catch (Exception ex) {

           hznszjgfpbForm.reset(mapping,request);
           // 系统捕捉异常，根据异常类型抛出
           throw ExceptionUtil.getBaseException(ex);
       }
   }

   /**
    * 保存申报数据
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward的跳转目标
    * @throws BaseException
    *             系统捕捉异常，根据异常类型抛出
    */
   public ActionForward doSave(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {

       // -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
       ActionForward forward = doHandleToken(mapping, request);
       if (forward != null) {
           return forward;
       }
       
       //System.out.println("$$$$$$$$$17.1_1="+request.getParameter("fzjgfpbl"));
       //System.out.println("$$$$$$$$$17.2_1="+request.getParameter("17.2_1"));
       //request.getp
       
       // 获取HznszjgfpbForm对象
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       
       // 获取本缴费系统用户信息
       UserData userData = this.getUserData(request);
       // 设置录入人名称
       hznszjgfpbForm.setLrr(userData.getYhid());
       // 设置报表期类型
       hznszjgfpbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
       // 按照columns中的字段提取所需要的前台列表数据，赋给czqysdsjbForm的DataList中
       System.out.println("length = " + hznszjgfpbForm.getFzjgColumns().length);
       System.out.println("Column[0] = " + hznszjgfpbForm.getFzjgColumns()[0]);
       System.out.println("Column[1] = " + hznszjgfpbForm.getFzjgColumns()[1]);
       hznszjgfpbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
               hznszjgfpbForm.getFzjgColumns()));
       System.out.println("list size = " + hznszjgfpbForm.getQysdsjbList().size());
       
       System.out.println("nsrmc = " + hznszjgfpbForm.getNsrmc());
       this.getBaseForm(request, hznszjgfpbForm);
       // 将czqysdsjbForm放入request,当保存失败时，保证页面数据仍然显示
       request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
       // 初始化数据传递容器
       VOPackage vo = new VOPackage();
       // 设置容器内容
       vo.setAction(CodeConstant.SMSB_SAVEACTION);
       vo.setData(hznszjgfpbForm);
       vo.setUserData(userData);
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);

       try {
           // 调用Proxy，执行processor,获取czqysdsjbForm返回值
           hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           hznszjgfpbForm.setJglx("1");
           // 将czqysdsjbForm中的基本数据置空
//           hznszjgfpbForm.reset(mapping, request);
           // 将czqysdsjbForm置入request,作为回显数据用
           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
           // 操作成功流转
           request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "保存成功！");
           return mapping.findForward("Show");
       } catch (Exception ex) {
           // 系统捕捉异常，根据异常类型抛出
           throw ExceptionUtil.getBaseException(ex);
       }
   }

   /**
    * 删除申报数据
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward的跳转目标
    * @throws BaseException
    *             系统捕捉异常，根据异常类型抛出
    */

   public ActionForward doDelete(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {
   	 System.out.println("doDelete ============Action ");
   	
       // -------------对数据库操作的Method进行修改，防止刷新或重复提交----------------------------------------
       ActionForward forward = doHandleToken(mapping, request);
       if (forward != null) {
           return forward;
       }
       // 获取本缴费系统用户信息
       UserData userData = this.getUserData(request);
       // 获取HznszjgfpbForm对象
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       // 按照columns中的字段提取所需要的前台列表数据，赋给hznszjgfpbForm的DataList中，
       hznszjgfpbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
               hznszjgfpbForm.getFzjgColumns()));
       // 将zfjgForm放入request,当删除失败时，保证页面数据仍然显示
       request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
       // 设置录入人名称
       hznszjgfpbForm.setLrr(userData.getYhid());
       // 设置报表期类型
       hznszjgfpbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
       // 初始化数据传递容器
       VOPackage vo = new VOPackage();
       // 设置容器内容
       vo.setAction(CodeConstant.SMSB_DELETEACTION);
       vo.setData(hznszjgfpbForm);
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);
       vo.setUserData(userData);
       this.getBaseForm(request, hznszjgfpbForm);
       try {
           // 调用Proxy，执行processor,获取hznszjgfpbForm返回值
           hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           // 将hznszjgfpbForm中的基本数据置空
//           hznszjgfpbForm.reset(mapping, request);
//           // 将hznszjgfpbForm置入request,作为回显数据用
//           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
//           // 操作成功流转，该提示信息在头文件header.jsp中获取
//           request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "删除成功！");
//           //this.doJump(mapping, hznszjgfpbForm, request, response);
//           return mapping.findForward("Show");
           
			System.out.println("I am jumping to  Hznszjgfpb....");
			// 获取CzzssdsjbForm对象
			HznszjgfpbForm tempHznszjgfpbForm = new HznszjgfpbForm();

			// 将CzzssdsjbForm 放入request中
			//request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			//
    System.out.println("####hznszjgfpbForm.getSkssksrq()="+hznszjgfpbForm.getSkssksrq());
    System.out.println("####hznszjgfpbForm.getSkssjsrq()="+hznszjgfpbForm.getSkssjsrq());
    
    System.out.println("11####hznszjgfpbForm.getYnsdse()="+hznszjgfpbForm.getYnsdse());
    
    System.out.println("33####tempHznszjgfpbFormgetYnsdse()="+tempHznszjgfpbForm.getYnsdse());
    hznszjgfpbForm.setYnsdse(null);
    
			tempHznszjgfpbForm.setJsjdm(hznszjgfpbForm.getJsjdm());
			tempHznszjgfpbForm.setSkssksrq(hznszjgfpbForm.getSkssksrq());
			tempHznszjgfpbForm.setSkssjsrq(hznszjgfpbForm.getSkssjsrq());
			//request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
			request.setAttribute("hznszjgfpbForm", tempHznszjgfpbForm);
			return mapping.findForward("Query");
           
       } catch (Exception ex) {
           // 系统捕捉异常，根据异常类型抛出
           throw ExceptionUtil.getBaseException(ex);
       }
   }

   /**
    * 跳转页面数据
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            HdzssdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward的跳转目标
    * @throws BaseException
    *             系统捕捉异常，根据异常类型抛出
    */
   
   public ActionForward doJump(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException
   {
       try {
           System.out.println("I am jumping....");
           // 获取HznszjgfpbForm对象
           HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
           String jsjdm = hznszjgfpbForm.getJsjdm();
           String jumpStr = null;
           // 将CzzssdsjbForm 放入request中
           HznszjgfpbForm tempHznszjgfpbForm=new HznszjgfpbForm();
           if(jsjdm != null && !jsjdm.equals(""))
           {
        	   tempHznszjgfpbForm.setJsjdm(jsjdm);
               jumpStr = "Query";
           }
           else
           {
               jumpStr = "Jump";
           }
           request.setAttribute("hznszjgfpbForm", tempHznszjgfpbForm);
           System.out.println("jumpStr = " + jumpStr);
           return mapping.findForward(jumpStr);
       } catch (Exception ex) {
           // 系统捕捉异常，根据异常类型抛出
           throw ExceptionUtil.getBaseException(ex);
       }
   }
   
   /**
    * 退出页面
    * @param mapping struts.action.ActionMapping
    * @param form QysdsnbForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return actionMapping.findForward的跳转目标
    * @throws BaseException 系统捕捉异常，根据异常类型抛出
    */
   public ActionForward doExit(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
           BaseException {

       return mapping.findForward("Return");
   }
   
   
   /**
    * 审核页面数据
    * @param mapping struts.action.ActionMapping
    * @param form QysdsnbForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return actionMapping.findForward的跳转目标
    * @throws BaseException 系统捕捉异常，根据异常类型抛出
    */
   public ActionForward doCheck(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
           BaseException {

       //-------对数据库操作的Method进行修改，防止刷新或重复提交----------
       ActionForward forward = doHandleToken(mapping, request);
       if (forward != null) {
           return forward;
       }
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       hznszjgfpbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
               hznszjgfpbForm.getFzjgColumns()));
       /*
       hznszjgfpbForm.setSbbczlList(SfRequestUtil.getValuesToList(request,
    		   hznszjgfpbForm.getSbbczl_columns()));
       hznszjgfpbForm.setHjList(SfRequestUtil.getValuesToList(request,
    		   hznszjgfpbForm.getHj_columns()));
      */		   
       this.getBaseForm(request, hznszjgfpbForm); //加入纳税人基本信息
       userData = this.getUserData(request);
       request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);

       VOPackage vo = new VOPackage();
       vo.setAction(CodeConstant.SMSB_CHECKACTION); //设置操作类型
       vo.setData(hznszjgfpbForm); //设置操作数据
       vo.setUserData(userData);
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);
       try {
           //调用processor
    	   hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
       
           
           if (hznszjgfpbForm.getCheckList() == null) {
               request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
           } else if (hznszjgfpbForm.getCheckList().size() == 0) {
               request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "审核通过！");
           } else if (hznszjgfpbForm.getCheckList().size() > 0) {
               request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
                                    QysdsUtil2009.getCheckResults(hznszjgfpbForm.
                       getCheckList()));
           }
           return mapping.findForward("Query");
       } catch (Exception ex) {
           //系统捕捉异常，根据异常类型抛出
           throw ExceptionUtil.getBaseException(ex);
       }

   }
   
   
   /**
    * 从session中获取基本信息
    *
    * @param request
    * @return
    */
   private void getBaseForm(HttpServletRequest request, HznszjgfpbForm form) {

       userData = this.getUserData(request);

       QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false)
                               .getAttribute(CodeConstant.
                                             SESSIONKEY_QYSDSNEWFORM);

       String ksrq = request.getParameter("skksrq");
       String jsrq = request.getParameter("skjsrq");

       if ((ksrq != null && !"".equals(ksrq)) &&
           (jsrq != null && !"".equals(jsrq))) {
           baseForm.setSkssksrq(request.getParameter("skksrq"));
           baseForm.setSkssjsrq(request.getParameter("skjsrq"));
           request.getSession(false).setAttribute(CodeConstant.
                   SESSIONKEY_QYSDSNEWFORM, baseForm);
       }

       if (baseForm != null) {
           form.setJsjdm(baseForm.getJsjdm());
           form.setSbrq(baseForm.getSbrq());
           form.setNsrmc(baseForm.getNsrmc());
           form.setQh("1");
           form.setSknd(baseForm.getSknd());
           form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
           form.setSkssksrq(baseForm.getSkssksrq());
           form.setSkssjsrq(baseForm.getSkssjsrq());
           form.setSwjsjdm(baseForm.getSwjsjdm());
           form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
           form.setQxdm(baseForm.getQxdm());
           form.setLrr(userData.getYhid());
           form.setCkzd(baseForm.getCkzd());
           form.setZsfs(baseForm.getZsfs());
           form.setSsjjlx(baseForm.getSsjjlx());
           form.setSshy(baseForm.getSshy());
           form.setGzglxs(baseForm.getGzglxs());
           form.setJmlx(baseForm.getJmlx());
           form.setNextTableURL(QysdsUtil2009.getTableURL(baseForm,
                   "N_" + CodeConstant.TABLE_ID_2012_12));
           form.setPreviousTableURL(QysdsUtil2009.getTableURL(baseForm,
                   "P_" + CodeConstant.TABLE_ID_2012_12));
       }
   }
   
}

