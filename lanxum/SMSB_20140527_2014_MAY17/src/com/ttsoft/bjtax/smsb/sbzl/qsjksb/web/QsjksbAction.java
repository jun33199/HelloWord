/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.model.ZRRYHZH;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.web.GtgshyysrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import java.util.ArrayList;
import com.ttsoft.bjtax.smsb.util.LWHelper;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbGzsxActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhActionForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现欠税缴款申报功能：包括缴款书录入，维护。</p>
 * @author Zhang Yijun
 * @version 1.1
 */
public class QsjksbAction
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
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;欠税缴款申报</td>");
        //httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
        //                                "欠税缴款申报");
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
    	QsjksbForm form = (QsjksbForm) actionForm;
        form.setBankJsArray("var bankInfo=new Array();");
        form.setSbrq(SfDateUtil.getDate());
        
        UserData ud = this.getUserData(httpServletRequest);
        form.setYhdm(ud.getYhid());
 
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SHOWACTION);
        vo.setProcessor(CodeConstant.SBZL_QSJKSB_PROCESSOR);
        vo.setData(form);
        vo.setUserData(ud);
        try        
        {
            form = (QsjksbForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return (new ActionForward(actionMapping.getInput()));
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }
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

        UserData ud = this.getUserData(httpServletRequest);
        QsjksbForm form = (QsjksbForm) actionForm;
        form.setYhdm(ud.getYhid());
 
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(CodeConstant.SBZL_QSJKSB_PROCESSOR);
        vo.setData(form);
        vo.setUserData(ud);
        try        
        {
            form = (QsjksbForm) SbzlProxy.getInstance().process(vo);
            ArrayList list=(ArrayList) form.getDataList();
            String xgqx=form.getXgqx();
        	form = (QsjksbForm) setInfo(form, httpServletRequest);

        	form.setXgqx(xgqx);
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
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
        UserData ud = this.getUserData(httpServletRequest);
        QsjksbForm form = (QsjksbForm) actionForm;
        
        String yhdm = form.getYhdm();
        String zh = form.getZh();
 
		String ssdwdm = null;

        if (! (yhdm.equals("") || zh.equals("")))
        {
          SWDJJBSJ jbsj = null;
          ZRRJBSJ zrrJbsj = null;
          HashMap hm = null;
          try {
				if (TinyTools.isCompany(form.getJsjdm())) {

					hm = InterfaceDj.getDjInfo(form.getJsjdm(), ud);
				} else {

					hm = (HashMap) InterfaceDj.getZRRInfo(form.getJsjdm(), ud);
				}
				
				/*hm = InterfaceDj.getDjInfo(form.getJsjdm(), ud);*/
			}
          catch (Exception ex1)
          {
            ex1.printStackTrace();
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex1, "保存欠税缴款申报失败!无法取得纳税人基本数据");
          }
          
          if (TinyTools.isCompany(form.getJsjdm())) {

				jbsj = (SWDJJBSJ) hm.get("JBSJ");
				ssdwdm = jbsj.getSwjgzzjgdm();
			} else {
				
				zrrJbsj = (ZRRJBSJ) hm.get(DjOuterConstant.ZRRJBSJ);
				ssdwdm = zrrJbsj.getSwjgzzjgdm();
				System.out.println("ssdwdm=="+ssdwdm);
			}
          /*
			 * jbsj = (SWDJJBSJ)hm.get("JBSJ"); String ssdwdm =
			 * jbsj.getSwjgzzjgdm();
			 */
          
          if (LWUtil.isZsjgLW(httpServletRequest.getSession().getServletContext(),
                              ssdwdm)) {
              form.setJksType(CodeConstant.PRINT_YPDS_KM);
          }
          else
          {
            form.setJksType(CodeConstant.PRINT_YPYS);
          }
        }
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor(CodeConstant.SBZL_QSJKSB_PROCESSOR);
        vo.setData(form);
        //设置通过总控得到的用户信息
        vo.setUserData(ud);
        try
        {
            //设置生成缴款书的申报编号
        	System.out.println("======设置生成缴款书的申报编号===123===");

        	
            Object retobj = SbzlProxy.getInstance().process(vo);
            actionForm = (ActionForm)retobj;

            //转移到缴款书维护界面
            return this.doJkswh(actionMapping, actionForm,
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
    private Object setInfo (ActionForm actionForm, HttpServletRequest request)
        throws
        Exception
    {
    	QsjksbForm form = (QsjksbForm) actionForm;
        try
        {
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            
            System.out.println("jsjdm=" + form.getJsjdm());			

			if (TinyTools.isCompany(form.getJsjdm())) {

				HashMap djMap = InterfaceDj.getDjInfo_New(form.getJsjdm(), ud);
				// 通过登记接口取得纳税人相关信息
				SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

				System.out.println("nsrmc=" + jbsj.getNsrmc());

				// 单位名称
				form.setNsrmc(jbsj.getNsrmc());
				// 设置纳税人状态
				form.setNsrzt(jbsj.getNsrzt());
				// 通过登记接口取得银行帐户信息
				List bankList = (List) djMap.get("YHZH");
				if (bankList == null) {
					bankList = new ArrayList(0);
				}
				form.setBankList(bankList);
				// 设置银行数组
				form.setBankJsArray(this.getBankJsArray(bankList, TinyTools.isCompany(form.getJsjdm())));

			} else {

				HashMap zrrdjMap = (HashMap) InterfaceDj.getZRRInfo(form
						.getJsjdm(), ud);
				// 通过自然人登记接口取得纳税人相关信息
				ZRRJBSJ zrrJbsj = (ZRRJBSJ) zrrdjMap
						.get(DjOuterConstant.ZRRJBSJ);

				System.out.println("zrrNsrmc=" + zrrJbsj.getNsrmc());

				// 单位名称
				form.setNsrmc(zrrJbsj.getNsrmc());
				// 设置纳税人状态
				form.setNsrzt(zrrJbsj.getNsrzt());
				// 通过自然人登记接口取得银行帐户信息
				List bankList = (List) zrrdjMap.get(DjOuterConstant.ZRRYHZH);
				if (bankList == null) {
					bankList = new ArrayList(0);
				}
				form.setBankList(bankList);
				// 设置银行数组
				form.setBankJsArray(this.getBankJsArray(bankList, TinyTools.isCompany(form.getJsjdm())));
			}
            
            /*
			 * //单位名称 form.setNsrmc(jbsj.getNsrmc()); //设置纳税人状态
			 * form.setNsrzt(jbsj.getNsrzt()); //通过登记接口取得银行帐户信息 List bankList =
			 * (List) djMap.get("YHZH");
			 * 
			 * if(bankList==null){ bankList=new ArrayList(0); }
			 * form.setBankList(bankList); //设置银行数组
			 * form.setBankJsArray(this.getBankJsArray(bankList));
			 */
            
            // 申报日期
            if (form.getSbrq() == null || form.getSbrq().trim().equals(""))
            {
                form.setSbrq(SfDateUtil.getDate());
            }
            return form;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);

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
     * @author gaoyh
     * @date 2010-09-27
     * @modify-type add
     * @description 为处理纳税人属于企业还是自然人
     * @param yhzh
     * @param isQy
     * @return String
     */
    private String getBankJsArray (List yhzh, boolean isQy)
    {
        StringBuffer ret = new StringBuffer();
        
        try
        {
        	if (isQy == true) {
				for (int i = 0; i < yhzh.size(); i++) {
					YHZH temp = (YHZH) yhzh.get(i);
					ret.append("[");
					ret.append("\"" + temp.getZh() + "\",");
					ret.append("\"" + temp.getKhyhmc() + "\",");
					ret.append("\"" + temp.getYhdm() + "\"");
					ret.append("],");

					System.out.println("zh=" + temp.getZh());
					System.out.println("khyhmc=" + temp.getKhyhmc());
				}
				if (ret.length() > 0) {
					// 如果有数据，则删除最后添加的逗号
					ret.delete(ret.length() - 1, ret.length());
				}
				ret.append("];");

			} else {
				for (int i = 0; i < yhzh.size(); i++) {
					ZRRYHZH zrrTemp = (ZRRYHZH) yhzh.get(i);
					ret.append("[");
					ret.append("\"" + zrrTemp.getZh() + "\",");
					ret.append("\"" + zrrTemp.getKhyhmc() + "\",");
					ret.append("\"" + zrrTemp.getYhdm() + "\"");
					ret.append("],");

					System.out.println("zrrZh=" + zrrTemp.getZh());
					System.out.println("zrrKhyhmc=" + zrrTemp.getKhyhmc());
				}
				if (ret.length() > 0) {
					// 如果有数据，则删除最后添加的逗号
					ret.delete(ret.length() - 1, ret.length());
				}
				ret.append("];");
			}
			return "var bankInfo = [" + ret.toString();
        }
        catch (Exception ex)
        {
            return "var bankInfo=new Array();";
        }
    }   
    
    
    /**
	 * 到缴款书维护页面
	 * 
	 * @param actionMapping
	 *            The ActionMapping used to select this instance
	 * @param actionForm
	 *            The optional ActionForm bean for this request (if any)
	 * @param httpServletRequest
	 *            The HTTP request we are processing
	 * @param httpServletResponse
	 *            The HTTP response we are creating
	 * @return ActionForward
	 */
    public ActionForward doJkswhBySave (ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse)
    {
        //缴款书维护FORMBEAN
        QsjksbjkswhActionForm jks = new QsjksbjkswhActionForm();
        QsjksbForm form = (QsjksbForm) actionForm;
        //设置计算机代码
        jks.setJsjdm(form.getJsjdm());
        //设置单位名称
        jks.setNsrmc(form.getNsrmc());
        //申报编号
        jks.setSbbhList(form.getSbbhList());
        //预设制申报编号
        jks.setPresbbh("1");
        //将FORMBEAN加入REQUEST
        httpServletRequest.setAttribute("qsjksbjkswhActionForm", jks);
        return actionMapping.findForward("JkswhBySave");
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
    	QsjksbjkswhActionForm jks = new QsjksbjkswhActionForm();
    	QsjksbForm form = (QsjksbForm) actionForm;
        //设置计算机代码
        jks.setJsjdm(form.getJsjdm());
        //设置单位名称
        jks.setNsrmc(form.getNsrmc());

        //将FORMBEAN加入REQUEST
        httpServletRequest.setAttribute("qsjksbjkswhActionForm", jks);
        return actionMapping.findForward("Jkswh");
    }    
}

