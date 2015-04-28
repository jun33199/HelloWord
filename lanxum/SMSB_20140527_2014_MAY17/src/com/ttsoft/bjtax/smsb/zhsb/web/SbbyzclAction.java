package com.ttsoft.bjtax.smsb.zhsb.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import java.util.List;
/**
 * <p>Title: 北京地税核心征管系统-上门申报--申报不一致处理</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Ha Zhengze</p>
 *
 * @author Ha Zhengze
 * @version 1.0  
 */
public class SbbyzclAction extends SmsbAction {
    /**
     <forward name="Show" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryDj" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QuerySb" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryA" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryB" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="MoveA" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="MoveB" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="SelectMoveA" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="SelectMoveB" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryMoveA" path="/webapp/smsb/sbbyzcl002.jsp" />
      <forward name="QueryMoveB" path="/webapp/smsb/sbbyzcl002.jsp" />
     */

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报入库不一致处理</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "申报入库不一致处理");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "");
    }

    /**
     * 初始化
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doShow(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doShow().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            ///3.0.设置当前年度作为查询年度
            pf.setQueryNd(String.valueOf(TinyTools.getYear(new Date())));
            ///3.1.设置查询日期
            pf.setQueryKsrq(String.valueOf(TinyTools.getYear(new Date())) +
                            "0101");
            pf.setQueryJsrq(String.valueOf(TinyTools.getYear(new Date())) +
                            "1231");
            ///3.2.设置其它参数
            pf.setCzr(ud.yhid);
            pf.setSwjgzzjgdm(ud.ssdwdm);
            pf.setSwjgzzjgmc(ud.ssdwmc);
            pf.setRq(TinyTools.Date2String(new Date()));
            pf.setOpeFlag("0");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("Show");
    }

    /**
     * 查询登记信息
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryDj(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doQueryDj().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(1);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("QueryDj");
    }

    /**
     * 查询申报信息
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQuerySb(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doQuerySb().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(2);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
            //pf=this.getTestData(pf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("QuerySb");
    }

    /**
     * QueryA
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryA(ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doQueryA().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(3);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("QueryA");
    }

    /**
     * QueryB
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryB(ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doQueryB().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(4);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("QueryB");
    }

    /**
     * MoveA,转欠税
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doMoveA(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doMoveA().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(5);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("MoveA");
    }

    /**
     * MoveB,转重复申报
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doMoveB(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doMoveB().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(6);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("MoveB");
    }

    /**
     * SelectMoveA
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doSelectMoveA(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doSelectMoveA().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(7);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("SelectMoveA");
    }

    /**
     * SelectMoveB
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doSelectMoveB(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doSelectMoveB().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(8);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("SelectMoveB");
    }

    /**
     * QueryMoveA
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryMoveA(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doQueryMoveA().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(9);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("QueryMoveA");
    }

    /**
     * QueryMoveB
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryMoveB(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        /**
         * @todo doQueryMoveB().输入参数检查
         */
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(10);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("QueryMoveB");
    }

    private SbbyzclForm getTestData(SbbyzclForm pf){
        List dataList=pf.getDataList();
        List mxList=null;
        SingleSbInfo ssi=null;
        SbInfo si=null;
        for(int i=0;i<2;i++){
            ssi=new SingleSbInfo();
            mxList=ssi.getMxList();
            ssi.setSbbh("申报编号"+i);
            ssi.setSklx("税款类型"+i);
            ssi.setSbrq("申报日期"+i);
            ssi.setRkje("入库金额"+i);
            ssi.setCe("差额"+i);
            ssi.setBz("备注"+i);
            for(int j=0;j<2;j++){
                si=new SbInfo();
                si.setSzdm("税种代码"+j);
                si.setSzmc("税种名称"+j);
                si.setSkssksrq("税款所属开始日期"+j);
                si.setSkssjsrq("税款所属结束日期"+j);
                si.setSjje("实缴金额"+j);
                si.setRkje("入库金额"+j);
                si.setCe("差额"+j);
                si.setBz("备注"+j);
                mxList.add(si);
            }
            dataList.add(ssi);
        }
        return pf;
    }

    //  打印申报表 added by zhangyj 20070720
    public ActionForward doDetail(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {

        //0.句柄申明
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.输入参数检查
        try {
            //2.初始化
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.业务流程
            vo.setAction(11);
            vo.setUserData(ud);
    		HashMap dataMap = new HashMap();
    		dataMap.put("sbbh", httpServletRequest.getParameter("sbbh"));
    		dataMap.put("jsjdm", httpServletRequest.getParameter("jsjdm"));
    		dataMap.put("ksrq", httpServletRequest.getParameter("ksrq"));
    		dataMap.put("jsrq", httpServletRequest.getParameter("jsrq"));
    		dataMap.put("nd", httpServletRequest.getParameter("nd"));
    		pf.setQueryJsjdm(httpServletRequest.getParameter("jsjdm"));
    		vo.setData(dataMap);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            HashMap retMap = (HashMap) ZhsbProxy.getInstance().process(vo);
            
			List mxList = (List) retMap.get("mxList");
			Sbjkzb zb = (Sbjkzb) retMap.get("sbjkzb");
			pf.setMxList(mxList);
			pf.setZb(zb);
			BigDecimal hj = new BigDecimal(0);
			for (int i = 0; i < mxList.size(); i++) {
				Sbjkmx mx = (Sbjkmx) mxList.get(i);
				mx.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM", mx
						.getSzsmdm()));
				hj = hj.add(mx.getSjse());
			}
			pf.setHj(hj);
			if (zb.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
				ZRRJBSJ zrrJbsj = InterfaceDj.getZRRJBSJ(httpServletRequest.getParameter("jsjdm"));
				pf.setNsrmc(zrrJbsj.getNsrmc());
			} else {
				SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ(httpServletRequest.getParameter("jsjdm"));
				pf.setNsrmc(dj.getNsrmc());
			}
			httpServletRequest.setAttribute("sbbyzclForm", pf);            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.设置内存对象
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.返回值
        return actionMapping.findForward("Detail");    	
	}
    //end
}
