/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税生成税收缴款书</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信息科技有限公司，版权所有. </p>
 * <p>Company: 四一安信息科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import java.util.ArrayList;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhActionForm;

/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税生成税收缴款书</p>
 * <p>Description: 扣缴企业所得税生成税收缴款书</p>
 * @author wangxm
 * @version 1.1
 */
public class KjssjksAction
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
                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">扣缴企业所得税</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "扣缴企业所得税生成税收缴款书");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");

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
    	System.out.println("--------Come in KjssjksAction------------------");
        KjssjksForm form = (KjssjksForm) actionForm;
        try
        {
            UserData ud = this.getUserData(httpServletRequest);
            //包装前台信息BO
            KjssjksBO qbo = new KjssjksBO();
            qbo.setJsjdm(form.getJsjdm());
            qbo.setBadjxh(form.getBadjxh());
            if(form.getBgbxh()==null || "".equals(form.getBgbxh()) || "null".equals(form.getBgbxh())){
            	form.setBgbxh((String)httpServletRequest.getAttribute("bgbxh"));
            	System.out.println("httpServletRequest.getAttribute----------"+form.getBgbxh());
            }
            qbo.setBgbxh(form.getBgbxh());
            //初始化页面信息
            this.setInfo(form, httpServletRequest);
            //包装后台信息BO
            KjssjksBO hbo = new KjssjksBO();
            
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.SBZL_KJSSJKS_PROCESSOR);
            vo.setData(qbo);
            try {
    			// 调用Proxy，执行processor,获取hbo返回值
    			hbo = (KjssjksBO) SbzlProxy.getInstance().process(vo);
    			if(null==hbo.getSbbh() || "".equals(hbo.getSbbh())){
	    			//将hbo放入form
	    			System.out.println("------------------hbo-------------"+hbo);
	    			form.setSzdm(hbo.getSzdm());
	    			form.setSzmc(hbo.getSzmc());
	    			form.setSzsmdm(hbo.getSzsmdm());
	    			form.setSzsmmc(hbo.getSzsmmc());
	    			
					form.setSkssksrq(hbo.getSkssksrq());
					form.setSkssjsrq(hbo.getSkssjsrq());
					form.setSjse(hbo.getSjse());
					form.setHtmc(hbo.getHtmc());
					form.setHtbh(hbo.getHtbh());
					form.setJsje(hbo.getJsje());
					form.setSl(hbo.getSl());
	    			// 将kjqysdsbgbForm置入request,作为回显数据用
	    			httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
	    			return actionMapping.findForward("Query");
    			}
    			else{
    				form.setSbbh(hbo.getSbbh());
    	            return this.doJkswhBySave(actionMapping, form,
                            httpServletRequest,
                            httpServletResponse);
    			}
    		} catch (Exception ex) {
    			// 系统捕捉异常，根据异常类型抛出
    			throw ExceptionUtil.getBaseException(ex);
    		}
            
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

        UserData ud = this.getUserData(httpServletRequest);
        KjssjksForm form = (KjssjksForm) actionForm;
        KjssjksBO qbo=new KjssjksBO();
        //得到列表数据
        String columns[] = form.getColumns();
        ArrayList list = new ArrayList();
        HashMap map = new HashMap();
        map.put("szsmdm",form.getSzsmdm() );
        map.put("szsmmc", form.getSzsmmc());
        map.put("jsje",form.getJsje());
        map.put("sjse",form.getSjse());
        map.put("skssksrq",form.getSkssksrq());
        map.put("skssjsrq",form.getSkssjsrq());
        map.put("szdm",form.getSzdm());
        map.put("szmc",form.getSzmc());
        map.put("sl",form.getSl());
        
        list.add(map);
        form.setDataList(list);


        String yhdm = form.getYhdm();
        String zh = form.getZh();

        if (! (yhdm.equals("") || zh.equals("")))
        {
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
            throw ExceptionUtil.getBaseException(ex1, "生成缴款书失败!无法取得纳税人基本数据");
          }
          jbsj = (SWDJJBSJ)hm.get("JBSJ");
          String ssdwdm = jbsj.getSwjgzzjgdm();

          form.setJksType(CodeConstant.PRINT_YPYS);
        }
        
        //将form放入bo 
        qbo.setJsjdm(form.getJsjdm());
        qbo.setDataList(form.getDataList());
        qbo.setYhdm(form.getYhdm());
        qbo.setZh(form.getZh());
        qbo.setJksType(form.getJksType());
        qbo.setNsrmc(form.getNsrmc());
        qbo.setYhmc(form.getYhmc());
        qbo.setSklxdm(form.getSklxdm());
        qbo.setSklxmc(form.getSklxmc());
        qbo.setSbrq(form.getSbrq());
        
        qbo.setBadjxh(form.getBadjxh());
        qbo.setBgbxh(form.getBgbxh());
        qbo.setSzsmmc(form.getSzsmmc());
        qbo.setSzsmdm(form.getSzsmdm());
        qbo.setSjse(form.getSjse());

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor(CodeConstant.SBZL_KJSSJKS_PROCESSOR);
        vo.setData(qbo);


        //设置通过总控得到的用户信息
        vo.setUserData(ud);
        try
        {
            //设置生成缴款书的申报编号
            //form.setSbbh(this.getSbbh(jksMap));
        	KjssjksBO hbo = (KjssjksBO)ZhsbProxy.getInstance().process(vo);

        	form.setSbbh(hbo.getSbbh());
        	System.out.println("sbbh----------"+form.getSbbh());
        	// 将kjqysdsbgbForm置入request,作为回显数据用
			//httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
			// 操作成功流转
			//httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "开拒缴款书成功！");
			actionForm=(ActionForm)form;
            //转移到缴款书维护界面，只显示本次生成的缴款书
            return this.doJkswhBySave(actionMapping, actionForm,
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
    private void setInfo (KjssjksForm form, HttpServletRequest request)
        throws
        Exception
    {
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
            /**
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
            }**/
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
    throws
    Exception
    {
        //缴款书维护FORMBEAN
        ZhsbjkswhActionForm jks = new ZhsbjkswhActionForm();
        KjssjksForm form = (KjssjksForm) actionForm;
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


        ActionForward gotourl = new ActionForward("/webapp/smsb/zhsbjkswhAction.do?actionType=Query");//url可以根据不同得条件指定不同得地址和不同得参数
        gotourl.setPath("/webapp/smsb/zhsbjkswhAction.do?actionType=Query&jsjdm="+form.getJsjdm()+"&nsrmc="+form.getNsrmc()+"&sbbh="+form.getSbbh()+"&presbbh=1&kjflag=1&bgbxh="+form.getBgbxh()+"&sjly="+CodeConstant.SMSB_SJLY_YQKJ);
        gotourl.setRedirect(true);
        return gotourl;

        //return actionMapping.findForward("JkswhBySave");
    }
    

}
