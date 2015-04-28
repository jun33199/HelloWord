/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRYHZH;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 外籍个人所得税月份申报表</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrAction
    extends SmsbAction
{
    /**
     *初始时显示页面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        try
        {
            WjgrActionForm form = (WjgrActionForm) actionForm;

            //初始化相关信息
            this.setInitInfo(form);
            return (new ActionForward(actionMapping.getInput()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "保存外籍个人所得税月份申报表失败!");
        }
    }

    /**
     *初始时显示页面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        WjgrActionForm form = (WjgrActionForm) actionForm;
        //设置申报日期
        this.setInitInfo(actionForm);
        //生成数据包
        VOPackage vo = new VOPackage();
        //保存
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //调用processor
        vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
        //设置actionForm
        vo.setData(form);
        //设置userDate
        vo.setUserData(ud);
        try
        {
            WjgrActionForm retForm = (WjgrActionForm) ZhsbProxy.getInstance().
                                     process(
                vo);
            //设置明细列表使用js数组
            //设置银行账号使用的js数组
            retForm.setBankJsArray(this.getBankJsArray(retForm.getBankList()));
            httpServletRequest.setAttribute("wjgrActionForm", retForm);

        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Query");

    }

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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报缴款</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "自然人个税申报");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");
    }

    /*
     *保存
     * @param mapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    public ActionForward doSave (ActionMapping actionMapping,
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

        WjgrActionForm form = (WjgrActionForm) actionForm;
        //得到列表数据
        String columns[] = form.getColumns();
        //设置明细数据
        form.setDataList(this.getValuesToList(httpServletRequest, columns));


        //start add by qianchao 2005.11.1
        String yhdm = form.getYhdm();
        String zh = form.getZh();

        if (! (yhdm.equals("") || zh.equals("")))
        {
          ZRRJBSJ jbsj = null;
          try
          {
            jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
          }
          catch (Exception ex1)
          {
            ex1.printStackTrace();
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex1, "保存个人所得税失败!无法取得纳税人基本数据");
          }
          String ssdwdm = jbsj.getSwjgzzjgdm();
          if (LWUtil.isLW(httpServletRequest.getSession().getServletContext(), ssdwdm,
                          yhdm))
          {
            form.setJksType(CodeConstant.PRINT_YPDS_KM);
          }
          else
          {
            form.setJksType(CodeConstant.PRINT_YPYS);
          }
        }
        //end add by qianchao 2005.11.1

        //生成数据包
        VOPackage vo = new VOPackage();
        //保存
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //调用processor
        vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
        vo.setData(form);
        //设置通过总控得到的用户信息
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            WjgrActionForm tt = (WjgrActionForm) ZhsbProxy.getInstance().process(vo);
            
            List dataList = tt.getDataList();
            List delList = new ArrayList();
            ArrayList okList = new ArrayList();
            
            if(dataList.size()>0){
            	if(form.getJksType()== CodeConstant.PRINT_YPDS_KM){
            		//一票多税
                	BigDecimal total = new BigDecimal(0.00);
               		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
            			List infoList = (List) iterator.next();
       	
	                    for (int i = 0; i < infoList.size(); i++) {
	
	                    	DeclareInfor declareInfor = (DeclareInfor)infoList.get(i);
	                    	
	                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
	                    	List jkmxList = declareInfor.getSbjkmxInfo();
	                    	
	                    	delList.add(sbjkzb.getJkpzh());
	                    	
	                    	for (int j = 0; j < jkmxList.size(); j++) {
	                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
	                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
	                    		total = total.add(sjse);
	                    	}
	                    }
	                    okList.add(infoList);
            		}
                    
                    if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                    	okList =  new ArrayList();
                    }else{
                    	delList = new ArrayList();
                    }
            	}else{
            		//一票一税        		
                    for (int i = 0; i < dataList.size(); i++) {
                    	
                    	BigDecimal total = new BigDecimal(0.00);
                    	
                    	DeclareInfor declareInfor = (DeclareInfor)dataList.get(i);
                    	
                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
                    	List jkmxList = declareInfor.getSbjkmxInfo();
                    	
                    	for (int j = 0; j < jkmxList.size(); j++) {
                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
                    		total = total.add(sjse);
                    	}
                        if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                        	delList.add(sbjkzb.getJkpzh());
                        }else{
                        	okList.add(declareInfor);
                        }
                    }
        		}
            }
            
            if(delList.size()>0){
	            vo.setAction(CodeConstant.SMSB_DELETEACTION);
	            Map map = new HashMap();
	            map.put("jsjdm", tt.getJsjdm());
	            map.put("jkpzhList", delList);
	            vo.setData(map);
	            ZhsbProxy.getInstance().process(vo);
	            //重新设置剩余数据
	            tt.setDataList(okList);
            }
            
            if(okList.size()==0){
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!");
            	return this.doQuery(actionMapping,actionForm,httpServletRequest,httpServletResponse);
            }
            
            if(delList.size()>0){
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"部分缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!");
            }else{
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"保存成功！");
            }
            
            form.setSbbh(tt.getSbbh());
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex, "保存外籍个人所得税月份申报表失败!");
        }
        //保存成功，重设查询页面
        return this.doJkswh(actionMapping, actionForm, httpServletRequest,httpServletResponse);

    }

    /**
     * 初始化
         * @param actionForm  The optional ActionForm bean for this request (if any)
     */
    private void setInitInfo (ActionForm actionForm)
    {
        WjgrActionForm form = (WjgrActionForm) actionForm;
        //设置申报日期
        if (form.getSbrq() == null || form.getSbrq().equals(""))
        {
            form.setSbrq(SfDateUtil.getDate());
        }
        //取得税款所属日期
        Map skssrq = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
        form.setSkssjsrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.SKSSJSRQ)));
        form.setSkssksrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.SKSSKSRQ)));

    }

    /**
     *初始时显示页面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doJkswh (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("=========doJkswh()=");

        WjgrActionForm form = (WjgrActionForm) actionForm;
        WjgrJkswhActionForm form1 = new WjgrJkswhActionForm();
        form1.setJsjdm(form.getJsjdm());
        form1.setSbbh(form.getSbbh());
        httpServletRequest.setAttribute("wjgrJkswhActionForm", form1);
        httpServletRequest.setAttribute("sbbh", form.getSbbh());
        return actionMapping.findForward("Jkswh");
    }

    /**
     * 得到银行帐号的js数组
     * @param yhzh 银行信息
     * @return String
     */
    private String getBankJsArray (List yhzh)
    {
        StringBuffer ret = new StringBuffer();
        try
        {
            for (int i = 0; i < yhzh.size(); i++)
            {
                ZRRYHZH temp = (ZRRYHZH) yhzh.get(i);
                ret.append("[");
                ret.append("\"" + temp.getZh() + "\",");
                ret.append("\"" + temp.getKhyhmc() + "\",");
                ret.append("\"" + temp.getYhdm() + "\"");
                ret.append("],");
            }
            if (ret.length() > 0)
            {
                //如果有数据，则删除最后添加的逗号
                ret.delete(ret.length() - 1, ret.length());
            }
            ret.append("];");
            return "var bankInfo = [" +
                SfStringUtils.replaceAll(ret.toString(), "null", "");
        }
        catch (Exception ex)
        {
            return "var bankInfo=new Array();";
        }
    }

}
