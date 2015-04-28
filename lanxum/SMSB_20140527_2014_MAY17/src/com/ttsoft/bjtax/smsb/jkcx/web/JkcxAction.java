package com.ttsoft.bjtax.smsb.jkcx.web;

import java.math.*;
import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.action.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.proxy.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.proxy.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.util.*;
import org.apache.struts.action.*;

public class JkcxAction extends SmsbAction {
    public JkcxAction() {
    }

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;" +
                             "<font color=\"#7C9AAB\">申报征收</font>&gt;" +
                             "<font color=\"#7C9AAB\">缴款查询</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "缴款查询");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/gtgsh/Gwszhz-000.htm");

    }

    public ActionForward doQuery(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {

        //获得当前的userData对象
        UserData ud = this.getUserData(httpServletRequest);
        JkcxForm form = (JkcxForm) actionForm;

        //生成数据包
        VOPackage vo = new VOPackage();
        //保存
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //调用processor
        vo.setProcessor(CodeConstant.JKCX_PROCESSOR);
        //设置actionForm
        vo.setData(form.getData());
        //设置userDate
        vo.setUserData(ud);
        try {
            List retList = (List) ZhsbProxy.getInstance().process(vo);
            for (int i = 0; i < retList.size(); i++) {
                Sbjkzb zb = (Sbjkzb) retList.get(i);
                String sklxdm = zb.getSklxdm();
                if(sklxdm.startsWith("1"))
                {
                    zb.setSklxmc("正常");
                }else if(sklxdm.startsWith("2"))
                {
                    zb.setSklxmc("四代解缴");
                }else if(sklxdm.startsWith("3"))
                {
                    zb.setSklxmc("跨区税款");
                }else if(sklxdm.startsWith("4"))
                {
                    zb.setSklxmc("自查补税");
                }else if(sklxdm.startsWith("5"))
                {
                    zb.setSklxmc("检查补税");
                }else if(sklxdm.startsWith("6"))
                {
                    zb.setSklxmc("评估补税");
                }else if(sklxdm.startsWith("7"))
                {
                    zb.setSklxmc("补缴欠税");
                }else if(sklxdm.startsWith("8"))
                {
                    zb.setSklxmc("行政处罚税款");
                }
            }
            form.setDataList(retList);
            httpServletRequest.setAttribute("jkcxForm", form);
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.setAttribute("jkcxForm", form);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Query");
    }

    public ActionForward doDetail(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {
        UserData ud = this.getUserData(httpServletRequest);
        JkcxForm form = (JkcxForm) httpServletRequest.getAttribute("jkcxForm");

        //生成数据包
        VOPackage vo = new VOPackage();
        //保存
        vo.setAction(CodeConstant.SMSB_PRINTACTION);
        //调用processor
        vo.setProcessor(CodeConstant.JKCX_PROCESSOR);
        //设置actionForm
        HashMap dataMap = new HashMap();
        dataMap.put("sbbh",form.getSbbh());
        vo.setData(dataMap);
        //设置userDate
        vo.setUserData(ud);
        try {
            HashMap retMap = (HashMap) ZhsbProxy.getInstance().process(vo);
            List mxList = (List)retMap.get("mxList");
            Sbjkzb zb = (Sbjkzb)retMap.get("sbjkzb");
            form.setMxList(mxList);
            form.setZb(zb);
            BigDecimal hj = new BigDecimal(0);
            for(int i=0;i<mxList.size();i++)
            {
                Sbjkmx mx = (Sbjkmx)mxList.get(i);
                mx.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM",mx.getSzsmdm()));
                hj = hj.add(mx.getSjse());
            }
            form.setHj(hj);
            if(zb.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
            {
                ZRRJBSJ zrrJbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
                form.setNarmc(zrrJbsj.getNsrmc());
            }else{
                SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ(form.getJsjdm());
                form.setNarmc(dj.getNsrmc());
            }
            httpServletRequest.setAttribute("jkcxForm", form);
        }catch (Exception ex) {
            ex.printStackTrace();
            httpServletRequest.setAttribute("jkcxForm", form);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Detail");
    }

    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
            BaseException {
        try {
            JkcxForm yForm = (JkcxForm) form;
            //获得当前的userData对象
            UserData ud = this.getUserData(request);
            // 取登记接口
            ServiceProxy proxy = new ServiceProxy();
            // 取得银行信息
            YHZH yhzh = new YHZH();
            String dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                    "swjgzzjgdm", ud.getSsdwdm(), "jsjdm");
            Map djMap = proxy.getDjInfo(dsJsjdm);
            if (djMap != null) {
                List yhList = (List) djMap.get("YHZH");
                if (yhList != null && yhList.size() > 0) {
                    yhzh = (YHZH) yhList.get(0);
                }
            }

            if(LWUtil.isZsjgLW(getServlet().getServletContext(),
                        ud.getSsdwdm()))
             {
                 //把预装载的信息传递给下一个页面
                 JksqdPrintForm pf = new JksqdPrintForm();

                 pf.setH_jsjdm(yForm.getJsjdm()); // 代售单位计算机代码
                 pf.setH_sbbh(yForm.getSbbh()); // 申报编号
                 pf.setSbbh(yForm.getSbbh());
                 pf.setJsjdm(yForm.getJsjdm());

                 pf.setHeadSjly(yForm.getSjly()); //数据来源
                 pf.setLrr(ud.getYhid()); //录入人
                 pf.setYhdm(yhzh.getYhdm()); //银行代码
                 pf.setYhmc(yhzh.getKhyhmc()); //银行名称
                 pf.setZh(yhzh.getZh()); //银行账号
                 pf.setSwjgzzjgdm(ud.getSsdwdm()); //税务机关组织机构代码
                 pf.setActionType("Show");
                 request.setAttribute("jksqdPrintForm", pf);
                 return mapping.findForward("PrintJksqd");
             }
             else {
                 //把预装载的信息传递给下一个页面
                 JksPrintForm pf = new JksPrintForm();
                 pf.setHeadJkpzh(yForm.getJkpzh());
                 pf.setHeadJsjdm(yForm.getJsjdm());
                 pf.setHeadSjly(yForm.getSjly()); //数据来源
                 pf.setActionType("Show");
                 request.setAttribute("jksPrintForm", pf);
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         }
         return mapping.findForward("Print");
     }
}
