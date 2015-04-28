/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.SzsmSyjdUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现综合申报功能：包括缴款书录入，维护。</p>
 * @author Shi Yanfeng
 * @version 1.1
 */
public class ZhsbAction
    extends SmsbAction
{
    //用户基本信息
    private UserData userData = null;

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
                                        "综合申报");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");

    }

    /**
     * 初始时显示页面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        form.setBankJsArray("var bankInfo=new Array();");
        form.setSbrq(SfDateUtil.getDate());
        return (new ActionForward(actionMapping.getInput()));
    }

    /**
     * 根据计算机代码查询基本信息
     * @param actionMapping The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("===  com.ttsoft.bjtax.smsb.zhsb.web.ZhsbAction doQuery()");

        ZhsbActionForm form = (ZhsbActionForm) actionForm;

        try
        {
            UserData ud = this.getUserData(httpServletRequest);
            //告知事项
            //初始化页面信息
            this.setInfo(actionForm, httpServletRequest);
            //返回根据税总税目代码生成的前台显示用明细数据列表
            ZhsbActionForm form1 = (ZhsbActionForm)this.getInitList(actionForm,
                ud);
            //如果有告知事项必须阅读
            if ( (form1.getGzsx() == null || form1.getGzsx().equals("0")) &&
                form1.getGzsxList().size() > 0)
            {

                //告知事项为空或0时并且有告知事项
                ZhsbGzsxActionForm gzsx = new ZhsbGzsxActionForm();
                gzsx.setJsjdm(form.getJsjdm());
                gzsx.setSbrq(form.getSbrq());

                httpServletRequest.setAttribute("zhsbGzsxActionForm", gzsx);
                return actionMapping.findForward("Gzsx");
            }

            form.setScriptStr(form1.getScriptStr());
            //生成税款所属日期数组
            form.setSkssrqArr(this.getSkssrq(SfDateUtil.getDate(form.getSbrq())));

            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {

            form.reset(actionMapping, httpServletRequest);

            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
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

        StringBuffer returnString = new StringBuffer();
        UserData ud = this.getUserData(httpServletRequest);
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        //得到列表数据
        String columns[] = form.getColumns();
        form.setDataList(this.getValuesToList(httpServletRequest, columns));

        //start add by qianchao 2005.11.1
        String yhdm = form.getYhdm();
        String zh = form.getZh();
        
        SWDJJBSJ jbsj = null;
        HashMap hm = null;
        try
        {
          hm = InterfaceDj.getDjInfo(form.getJsjdm(),ud);
        }
        catch (Exception ex1)
        {
          ex1.printStackTrace();
          form.reset(actionMapping, httpServletRequest);
          throw ExceptionUtil.getBaseException(ex1, "保存综合申报失败!无法取得纳税人基本数据");
        }
        jbsj = (SWDJJBSJ)hm.get("JBSJ");
        String ssdwdm = jbsj.getSwjgzzjgdm();
        
        if (! (yhdm.equals("") || zh.equals("")))
        {

          if (LWUtil.isZsjgLW(httpServletRequest.getSession().getServletContext(),
                              ssdwdm)) {
              form.setJksType(CodeConstant.PRINT_YPDS_KM);
          }
          else
          {
            form.setJksType(CodeConstant.PRINT_YPYS);
          }
          
        }else{
        	form.setJksType(CodeConstant.PRINT_YPYS);
        }
        //end add by qianchao 2005.11.1
        boolean isLW = LWUtil.isLW(httpServletRequest.getSession().getServletContext(), ssdwdm, yhdm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
        vo.setData(form);
        
        //设置通过总控得到的用户信息
        vo.setUserData(ud);
        try
        {

        	 String checkstr="";
             try
             {
               checkstr=SzsmSyjdUtil.getSzsmcljeBySyjdlx(form.getJsjdm(),form.getDataList());
             }catch (Exception ex1)
             {
             	 ex1.printStackTrace();
             	 throw ExceptionUtil.getBaseException(ex1, "判断税种税目代码和鉴定类型出错！");
             }
             
             
             if(checkstr.length()>0){
             	throw new ApplicationException(checkstr);
             }
        	
             //分离不同减免信息
             getJmList(form ,ud ,returnString);
               
            //start modifying by qianchao 2005.11.2
            //Map jksMap = ()
            //设置生成缴款书的申报编号
            //form.setSbbh(this.getSbbh(jksMap));
            Object retobj = ZhsbProxy.getInstance().process(vo);
            ZhsbActionForm rtForm = (ZhsbActionForm)retobj;
            //end modifying by qianchao 2005.11.2
            
            //减免信息处理
            operate(form ,ud );
            
            List dataList = rtForm.getDataList();
            List delList = new ArrayList();
            List okList = new ArrayList();
            
            if(dataList.size()>0){
            	
            	//一票多税
            	if(CodeConstant.PRINT_YPDS_KM == form.getJksType()){
                	if(isLW){
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
                   		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
                			List infoList = (List) iterator.next();
                			
                			List _infoList  = new ArrayList();
           	
    	                    for (int i = 0; i < infoList.size(); i++) {
    	
    	                    	DeclareInfor declareInfor = (DeclareInfor)infoList.get(i);
    	                    	
    	                    	BigDecimal total = new BigDecimal(0.00);
    	                    	
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
    	                        	_infoList.add(declareInfor);
    	                        }  
    	                    }
    	                    if(_infoList.size()>0){
    	                    	okList.add(_infoList);
    	                    } 
                		}
                	}
            	}
            	//一票一税
            	if(CodeConstant.PRINT_YPYS == form.getJksType()){
            		if(isLW){
            			BigDecimal total = new BigDecimal(0.00);
            			
	                    for (int i = 0; i < dataList.size(); i++) {	                    	
	                    	DeclareInfor declareInfor = (DeclareInfor)dataList.get(i);
	                    	
	                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
	                    	List jkmxList = declareInfor.getSbjkmxInfo();
	                    	
	                    	for (int j = 0; j < jkmxList.size(); j++) {
	                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
	                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
	                    		total = total.add(sjse);
	                    	}
	                    	
	                    	delList.add(sbjkzb.getJkpzh());
	                    	okList.add(declareInfor);

	                    }
                        if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                        	okList =  new ArrayList();
                        }else{
                        	delList = new ArrayList();
                        }
            		}else{
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
            }

            if(delList.size()>0){
	            vo.setAction(CodeConstant.SMSB_DELETEACTION);
	            Map map = new HashMap();
	            map.put("jsjdm", rtForm.getJsjdm());
	            map.put("jkpzhList", delList);
	            vo.setData(map);
	            ZhsbProxy.getInstance().process(vo);
	            //重新设置剩余数据
	            rtForm.setDataList(okList);
            }
            
            if(okList.size()==0 && dataList.size()>0){
            	returnString.append("缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!<br/>");
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,returnString.toString());
            	return this.doQuery(actionMapping,
                        actionForm,
                        httpServletRequest,
                        httpServletResponse);
            }
            
            if(delList.size()>0 && dataList.size()>0){
            	returnString.append("部分缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!<br/>");	
            }
            
            if(!"".equals(returnString.toString()) && !(null==returnString.toString()))
            {
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,returnString.toString());
            }

            //转移到缴款书维护界面，只显示本次生成的缴款书
            return this.doJkswhBySave(actionMapping, rtForm,
                                      httpServletRequest,
                                      httpServletResponse);
        }
        catch (Exception ex)
        {
            try
            {

                this.doQuery(actionMapping,
                             actionForm,
                             httpServletRequest,
                             httpServletResponse
                             );
            }
            catch (Exception ex1)
            {
              ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "生成缴款书信息失败!");
        }
    }

    
   
    
    
    /**
     * 设置纳税人基本信息，初始化页面信息
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @throws Exception
     */
    private void setInfo (ActionForm actionForm, HttpServletRequest request)
        throws
        Exception
    {
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            /* start added by huxiaofeng 2005.8.16*/
            //HashMap djMap = InterfaceDj.getDjInfo(form.getJsjdm(), ud);
            HashMap djMap = InterfaceDj.getDjInfo_New(form.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.16*/

            //通过登记接口取得纳税人相关信息

            SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
            //单位名称
            form.setNsrmc(jbsj.getNsrmc());
            //设置纳税人状态
            form.setNsrzt(jbsj.getNsrzt());
            //通过登记接口取得银行帐户信息
            List bankList = (List) djMap.get("YHZH"); ;
            //System.out.println("bankList.size()"+bankList.size());
            /* start added by huxiaofeng 2005.8.16*/
            if(bankList==null){
              bankList=new ArrayList(0);
            }
            /* end added by huxiaofeng 2005.8.16*/
            form.setBankList(bankList);
            //设置银行数组
            form.setBankJsArray(this.getBankJsArray(bankList));
            //申报日期
            if (form.getSbrq() == null || form.getSbrq().trim().equals(""))
            {
                //申报日期为空，则重新设置申报日期
                form.setSbrq(SfDateUtil.getDate());
            }
            //设置是否现实附加税标示，如果登记注册类型对应的内外资分类代码nwzfldm为１或２的
            //时候不显示附加税
            //得到登记注册类型代码表数据

            SfHashList list = (SfHashList) CodeManager.getCodeList(
                "ZHSB_DJZCLX",
                CodeConstants.CODE_MAP_MAPLIST);
            //得到计算机代码相关的登记注册类型
            String djzclxdm = jbsj.getDjzclxdm();
            //得到登记注册类型对应的内外资分类代码
            for (int i = 0; i < list.size(); i++)
            {

                if (djzclxdm.equals(list.get(i, "djzclxdm")) &&
                    (list.get(i, "nwzfldm").equals("1") ||
                     list.get(i, "nwzfldm").equals("2")))
                {
                    //内外资分类代码为1或2
                    form.setIsadditons(false);
                    //设置内外资分类标示
                    form.setWz(true);
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);

        }

    }

    /**
     * 根据选定的缴款凭证号来判断是一票一税或一票多税，转到相应的缴款书明细界面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return    ActionForward
     *
     */
    public ActionForward doJkswh (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
    {
        //缴款书维护FORMBEAN
        ZhsbjkswhActionForm jks = new ZhsbjkswhActionForm();
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        //设置计算机代码
        jks.setJsjdm(form.getJsjdm());
        //设置单位名称
        jks.setNsrmc(form.getNsrmc());
        //将FORMBEAN加入REQUEST
        httpServletRequest.setAttribute("zhsbjkswhActionForm", jks);
        return actionMapping.findForward("Jkswh");
    }

    /**
     * 取得包含税种税目list和营业税附加税list的list
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param ud  操作员基本信息
     * @throws Exception
     * @return    ActionForward
     */
    private ActionForm getInitList (ActionForm actionForm, UserData ud)
        throws
        Exception
    {
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
        vo.setData(actionForm);
        //设置userdata
        vo.setUserData(ud);
        try
        {
            return (ActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
        }
    }

    /**
     * 得到银行帐号的js数组
     * @param yhzh 银行账号列表
     * @return    ActionForward
     */
    private String getBankJsArray (List yhzh)
    {
        StringBuffer ret = new StringBuffer();
        try
        {
            for (int i = 0; i < yhzh.size(); i++)
            {
                YHZH temp = (YHZH) yhzh.get(i);
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
            return "var bankInfo = [" + ret.toString();
        }
        catch (Exception ex)
        {
            return "var bankInfo=new Array();";
        }
    }

    /**
     * 到缴款书维护页面
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return    ActionForward
     */
    public ActionForward doJkswhBySave (ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse)
    {
        //缴款书维护FORMBEAN
        ZhsbjkswhActionForm jks = new ZhsbjkswhActionForm();
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        //设置计算机代码
        jks.setJsjdm(form.getJsjdm());
        //设置单位名称
        jks.setNsrmc(form.getNsrmc());
        //申报编号
        jks.setSbbh(form.getSbbh());
        //预设制申报编号
        jks.setPresbbh("1");
        //将FORMBEAN加入REQUEST
        httpServletRequest.setAttribute("zhsbjkswhActionForm", jks);
        return actionMapping.findForward("JkswhBySave");
    }

    /**
     * 得到生成缴款书的申报编号
     * @param  jks 缴款书信息
     * @return  申报编号
     */
    /* deleted by qianchao 2005.11.2
    private String getSbbh (Map jks)
    {
        String sbbh = "";
        //生成缴款书的类型
        String jksType = (String) jks.get(CodeConstant.ZHSB_JKS_TYPE);
        //生成缴款书的列表
        List jksList = (List) jks.get(CodeConstant.ZHSB_JKS_LIST);
        //
        if (jksType != null && jksType.equals("T"))
        {
            //一票多税
            //得到第一张票的列表
            List temp = (List) jksList.get(0);
            //得到到第一张票的第一个缴款书
            DeclareInfor jksInfo = (DeclareInfor) temp.get(0);
            //申报缴款主表
            Sbjkzb sbjkzb = jksInfo.getSbjkzb();
            sbbh = sbjkzb.getSbbh();
        }
        else
        {
            //一票一税
            DeclareInfor jksInfo = (DeclareInfor) jksList.get(0);
            //申报缴款主表
            Sbjkzb sbjkzb = jksInfo.getSbjkzb();
            sbbh = sbjkzb.getSbbh();

        }
        //
        return sbbh;
    }
*/

    /**
     * 得到前台跳转到申报资料使用的税款所属日期
     * @param  rq 申报日期
     * @return  js数组['月','','季度','年','']
     */
    private String getSkssrq (Date rq)
    {
        StringBuffer ret = new StringBuffer();
        Map m = Skssrq.monthSkssrq(rq);
        //月份的税款所属日期
        ret.append("var skssrqArr = ['" +
                   SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSKSRQ))
                   + "','" +
                   SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSJSRQ))
                   + "',");
        //季度的税款所属日期
        Map q = Skssrq.quarterSkssrq(TinyTools.addMonth( -1, rq));
        ret.append("'" + SfDateUtil.getDate( (Timestamp) q.get(Skssrq.SKSSKSRQ)) +
                   "','"
                   + SfDateUtil.getDate( (Timestamp) q.get(Skssrq.SKSSJSRQ)) +
                   "',");
        //年
        Map y = Skssrq.yearSkssrq(rq);
        ret.append("'" + SfDateUtil.getDate( (Timestamp) y.get(Skssrq.SKSSKSRQ)) +
                   "','"
                   + SfDateUtil.getDate( (Timestamp) y.get(Skssrq.SKSSJSRQ)) +
                   "',");
        //季度其他
        Map qq = Skssrq.otherSkssrq(rq);
        ret.append("'" + SfDateUtil.getDate( (Timestamp) qq.get(Skssrq.SKSSKSRQ)) +
                   "','"
                   + SfDateUtil.getDate( (Timestamp) qq.get(Skssrq.SKSSJSRQ)) +
                   "']");
        return ret.toString();

    }

    /**
     * @Description: TODO 分离综合申报中可以减免的条目
     * @param form
     * @return
     * add by lijn 20141013
     * @throws Exception 
     */
    private Map getJmList(ZhsbActionForm form ,UserData ud ,StringBuffer sb) throws Exception
    {
    	List dateList = form.getDataList();
    	Map jmMap = new HashMap();
    	List jmXwqyList = new ArrayList();
    	
    	//只修改正常缴款和四代解缴
    	if("400".equals(form.getSklxdm()))
    	{
    		return jmMap;
    	}
    	
    	/*------小微企业营业税减免:移除源数据，生成专门的list数据---------*/
    	boolean hasYysXwqy = false;					//用于移除附加税
    		
    	/*-------------------------------------月报-----------*/
    	List jmXwqyYysYdList = new ArrayList();		//月度营业税小微企业
    	double sumYdJsJeXwqyYys = 0.0;
    	Iterator iteratorYd = dateList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorYd.hasNext())
    	{
    		Map mxMap = (Map)iteratorYd.next();
    		if(mxMap.get("szsmdm")==null)
    		{continue;}
    		String szsmdm_temp = mxMap.get("szsmdm").toString();
    		//税种税目是 非罚款或滞纳金的营业税
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{	
    			//是否是当期税款
    			form.setJmMap(mxMap);
    			form.setCheck_jmLx(0);
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(CodeConstant.ZHSB_JM01_CHECKCURRENT_ACTION);
    		     vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(form);
        		Boolean isYysXwjm_dq = (Boolean)ZhsbProxy.getInstance().process(vo);
        			
        		if(isYysXwjm_dq.booleanValue())
        		{
        			jmXwqyYysYdList.add(mxMap);
        			iteratorYd.remove();
        			sumYdJsJeXwqyYys+=Double.parseDouble((String)mxMap.get("jsje"));
        		}
    		}
    	}
    	
    	//是否够3万计税金额
    	if(sumYdJsJeXwqyYys>30000 || sumYdJsJeXwqyYys == 0){
    		dateList.addAll(jmXwqyYysYdList);
    	}else{			
    		jmXwqyList.addAll(jmXwqyYysYdList);
    		hasYysXwqy = true;
    	}
    	
    	/*----------------------------季报-------------------*/
    	List jmXwqyYysJdList = new ArrayList();		//季度营业税小微企业
    	double sumJdJsJeXwqyYys = 0.0;
    	Iterator iteratorJd = dateList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorJd.hasNext())
    	{
    		Map mxMap = (Map)iteratorJd.next();
    		if(mxMap.get("szsmdm")==null)
    		{continue;}
    		//税种税目是 非罚款或滞纳金的营业税
    		if("02".equals(mxMap.get("szsmdm").toString().substring(0, 2)) && !"020091".equals(mxMap.get("szsmdm").toString()) && !"020092".equals(mxMap.get("szsmdm").toString()))
    		{	
    			//是否是当期税款
    			form.setJmMap(mxMap);
    			form.setCheck_jmLx(1);
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(CodeConstant.ZHSB_JM01_CHECKCURRENT_ACTION);
    		     vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(form);
        		Boolean isYysXwjm_dq = (Boolean)ZhsbProxy.getInstance().process(vo);
        			
        		if(isYysXwjm_dq.booleanValue())
        		{
        			jmXwqyYysJdList.add(mxMap);
        			iteratorJd.remove();
        			sumJdJsJeXwqyYys+=Double.parseDouble((String)mxMap.get("jsje"));
        		}
    		}
    	}
    	
    	//是否够9万计税金额
    	if(sumJdJsJeXwqyYys>90000 ||  sumJdJsJeXwqyYys == 0){
    		dateList.addAll(jmXwqyYysJdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysJdList);
    		hasYysXwqy = true;
    	}
    	
    	/*---------------------------------------------存在营业税减免则同时减免附加费----------*/
    	if(hasYysXwqy == true)
    	{
    		sb.append("对营业税纳税人中月营业额不超过3万元（季度为9万元,含本数）的纳税人，暂免征收营业税。请纳税人到减免申报模块进行减免税申报。<br/>");
    		
    		Iterator iteratorfjf = dateList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
        	while(iteratorfjf.hasNext())
        	{
        		Map mxMap = (Map)iteratorfjf.next();
        		if("540010".equals(mxMap.get("szsmdm")) || "510010".equals(mxMap.get("szsmdm")) || "100010".equals(mxMap.get("szsmdm")))
        		{
        			iteratorfjf.remove();
        			jmXwqyList.add(mxMap);
        		}	
        	}
    	}
    	
    	
    	/*---------------------------------------------------------存在文化事业建设费--------*/
    	List jmXwqyWhsyjsfYdList = new ArrayList();		//文化事业建设费
    	double sumYdJsJeXwqyWhsyjsf = 0.0;				//文化事业建设费金额
    	Iterator iteratorWhsyjsf = dateList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorWhsyjsf.hasNext())
    	{
    		Map mxMap = (Map)iteratorWhsyjsf.next();
    		if(mxMap.get("szsmdm")==null)
    		{continue;}
    		
    		//20150101-20171231	
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		Date date_skssrqq = sdf.parse((String) mxMap.get("skssksrq"));		//税款所属日期起
    		Date date_skssrqz = sdf.parse((String) mxMap.get("skssjsrq"));		//税款所属日期止
    		Date start = sdf.parse("20150101");
    		Date end = sdf.parse("20180101");
    		if(date_skssrqq.before(start) || date_skssrqz.before(start) || date_skssrqz.after(end)||date_skssrqq.after(end)){
    			continue;
    		}
    		
    		String szsmdm_temp = mxMap.get("szsmdm").toString();
    		//税种税目是 非罚款或滞纳金的营业税
    		if("535610".equals(szsmdm_temp))
    		{	
    			//是否是当期税款
    			form.setJmMap(mxMap);
    			form.setCheck_jmLx(2);
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(CodeConstant.ZHSB_JM01_CHECKCURRENT_ACTION);
    		     vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(form);
        		Boolean isWhsyjsfXwjm_dq = (Boolean)ZhsbProxy.getInstance().process(vo);
        			
        		if(isWhsyjsfXwjm_dq.booleanValue())
        		{
        			jmXwqyWhsyjsfYdList.add(mxMap);
        			iteratorWhsyjsf.remove();
        			sumYdJsJeXwqyWhsyjsf+=Double.parseDouble((String)mxMap.get("jsje"));
        		}
    		}
    	}
    	
    	//是否够3万计税金额
    	if(sumYdJsJeXwqyWhsyjsf<=30000 &&  sumYdJsJeXwqyWhsyjsf > 0 && sumYdJsJeXwqyYys<=30000){
    		
    		jmXwqyList.addAll(jmXwqyWhsyjsfYdList);
    		sb.append("对按月纳税的月营业额不超过3万元（含3万元）的缴纳义务人，免征文化事业建设费。请缴纳义务人到减免申报模块进行减免税申报。<br/>");
    	}else{
    		dateList.addAll(jmXwqyWhsyjsfYdList);
    	}
    	jmMap.put(CodeConstant.ZHSB_JM01, jmXwqyList);
    	
   
    	
    	
    	
    	//一元减免在后面已实现
    	/*--*/
    	
    	form.setJmDateMap(jmMap);
    	return jmMap;
    }
    
    /**
     * @Description: TODO 不同的减免要单独处理
     * @param jMap
     * @param vo
     * @param form
     * @throws BaseException 
     */
    private void operate(ZhsbActionForm form ,UserData ud ) throws BaseException
    {
    	Map jmMap  = form.getJmDateMap();
    	 
    	//小微企业营业税减免：保存缴款数据但是保存到his中
    	if(jmMap.get(CodeConstant.ZHSB_JM01) != null && ((List)jmMap.get(CodeConstant.ZHSB_JM01)).size()>0)
    	{
    		try {
				//List ls1 = (List) jmMap.get(CodeConstant.ZHSB_JM01);
    			VOPackage vo = new VOPackage();
    			vo.setAction(CodeConstant.ZHSB_JM01_ACTION);
    	        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    	        vo.setUserData(ud);
    	        vo.setData(form);
				
    	        ZhsbProxy.getInstance().process(vo);
				
			} catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}
			
			
    	}
    	
    	
    }
    
}
