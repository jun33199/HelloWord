/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 综合申报_评估补税告知事项，如果有告知事项则显示列表，并且在确认后转回综合申报页面。<br>
 * 如果没有告知事项则直接转回综合申报页面。 </p>
 * @author zzb  20090327
 * @version 1.1
 */

public class ZhsbPgbsGzsxAction
    extends SmsbAction
{
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
        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;告知事项");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                    "告知事项");
    httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                    "help/smsb/zhsb/zhsb-000.htm");
  }

  /**
   *根据计算机代码查询告知事项
   * 如果有告知事项则显示
   * 如果没有告知事项则转回综合申报页面
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creating
   * @throws BaseException
   * @return ActionForward
   */
  public ActionForward doQuery(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws
      BaseException
  {
    try
    { 
      //评估补税	
      ZhsbPgbsGzsxActionForm form = (ZhsbPgbsGzsxActionForm) actionForm;
      VOPackage vo = new VOPackage();
      vo.setAction(CodeConstant.SMSB_ZHSB_GZSX);
      //评估补税
      vo.setProcessor(CodeConstant.ZHSB_PGBS_SBLR_PROCESSOR);
      vo.setData(actionForm);
      ArrayList gzsxList = (ArrayList) ZhsbProxy.getInstance().process(vo);

      //0为正常状态，1为非正常状态
      form.setFzcbs("0");
//如果有非正常告知事项，设置非正常标志
      String names[] =
          {
          "gzsxfcrq", "gzsxnr", "fzcbs"};

      ArrayList mapList = new ArrayList();
      for (int i = 0; i < gzsxList.size(); i++)
      {
        Gzsx temp = (Gzsx) gzsxList.get(i);
        Map map = new HashMap();
        BeanUtil.copyBeanToMap(names, temp, map);
        //转换vo中的日期显示格式
        //map.put("gzsxfcrq",SfTimeUtil.getDateFromDateTime((Timestamp)map.get("gzsxfcrq")) );
        //如果列表中含有非正常告知，则将标示设为1
        if (temp.getFzcbs().equals("1"))
        {
          form.setFzcbs("1");
        }
        mapList.add(map);
      }

      //form.setDataList(gzsxList);
      form.setDataList(mapList);

      return actionMapping.findForward("Query");
    }
    catch (Exception ex)
    {
      return (new ActionForward(actionMapping.getInput()));
    }
  }

  /**
   *根据计算机代码查询告知事项
   * 如果有告知事项则显示
   * 如果没有告知事项则转回综合申报页面
   * @param actionMapping  The ActionMapping used to select this instance
   * @param actionForm  The optional ActionForm bean for this request (if any)
   * @param httpServletRequest  The HTTP request we are processing
   * @param httpServletResponse  The HTTP response we are creatin
   * @return ActionForward
   * @throws BaseException
   */
  public ActionForward doZhsb(ActionMapping actionMapping,
                              ActionForm actionForm,
                              HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws
      BaseException
  {
    //评估补税
	ZhsbPgbsGzsxActionForm form = (ZhsbPgbsGzsxActionForm) actionForm;
	//评估补税
	ZhsbPgbsActionForm zhsb = new ZhsbPgbsActionForm();
    
    zhsb.setJsjdm(form.getJsjdm());
    zhsb.setSbrq(form.getSbrq());
    //zhsb.setJsjdm("06123456");
    zhsb.setGzsx("1");
    //评估补税
    httpServletRequest.setAttribute("zhsbPgbsActionForm", zhsb);
    return actionMapping.findForward("Zhsb");
  }

}