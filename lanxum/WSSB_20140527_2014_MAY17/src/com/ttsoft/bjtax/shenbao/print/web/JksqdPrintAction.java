/*
 * <p>Title:北京地税市长决策支持</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光科技股份有限公司，版权所有. </p>
 * <p>Company: 四一安信科技股份有限公司</p>
 */
package com.ttsoft.bjtax.shenbao.print.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 缴款申请单打印</p>
 * 
 * 从上门申报的电子缴款专用缴款书 使用的java、jsp直接移植而来。
 * 
 * @author 钱超 
 * @version 1.1
 */
public class JksqdPrintAction
    extends ShenbaoAction
{

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
                                HttpServletResponse response)

  {
  }

  /**
   * 页面初始化
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return the element previously at the specified position.
   * @exception BaseException
   */
  public ActionForward doShow(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws
      BaseException
  {

    JksqdPrintForm pf = (JksqdPrintForm) form;

    try
    {
      //获得当前的userData对象
      UserData ud = this.getUserData(request);
      pf.setLrr(ud.getYhid()); //获得单前登陆的用户id，作为录入人id

      VOPackage vo = new VOPackage();
      
      HashMap datamap = new HashMap();
      datamap.put("sjly",pf.getHeadSjly());
      datamap.put("jsjdm",pf.getJsjdm());
      datamap.put("sbbh",pf.getSbbh());
      
      vo.setAction(ActionConstant.INT_ACTION_QUERY);
      vo.setData(datamap);
      vo.setUserData(ud);
      vo.setProcessor(ProcessorNames.PRINT_DZJKZYJKS_PROCESSOR);

      //调用processor
      datamap = (HashMap) ShenbaoProxy.getInstance().process(vo);
      //保存返回值
      pf.setHjjedx((String)datamap.get("hjjedx"));
      pf.setHjjexx((String)datamap.get("hjjexx"));
      pf.setSzitem((List)datamap.get("datalist"));
      pf.setZh((String)datamap.get("zh"));
      pf.setYhmc((String)datamap.get("yhmc"));
      pf.setSbrq((String)datamap.get("sbrq"));
      pf.setNsrmc((String)datamap.get("nsrmc"));
      pf.setSwjgzzjgdm((String)datamap.get("swjgzzjgdm"));
      
      
      //在web层填充名称
      Map tmpmap = CodeTableUtil.getCodeTableMap(request, "SWJJZZJG_MAP");
      
      //征收机关名称（税务所的名称），需要根据代码查名称
      Swjgzzjg jg = (Swjgzzjg)tmpmap.get(pf.getSwjgzzjgdm());
      pf.setSwjgzzjgmc(jg.getSwjgzzjgmc());

      //国库名称
      pf.setGkzzjgdm(jg.getGkjhh());
      pf.setGkzzjgmc(jg.getSkgk());
      
      //税种名称
      tmpmap = CodeTableUtil.getCodeTableMap(request, "SZSM_MAP");
      List szlist = pf.getSzitem();
      Map sz;
      String tempstr;
      String smdm;
      for (int i = 0;i < szlist.size();i++)
      {
          sz = (Map)szlist.get(i);
          smdm = (String)sz.get("szsmdm");
          tempstr = ((Szsm)tmpmap.get(sz.get("szdm"))).getSzsmmc();
          if (smdm.endsWith("91") || smdm.endsWith("92"))
          {
              tempstr += "(滞纳金、罚款)";    
          }
          sz.put("szmc",tempstr);
      }
      
      request.setAttribute(mapping.getAttribute(), pf);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    return mapping.findForward("Show");
  }
}
