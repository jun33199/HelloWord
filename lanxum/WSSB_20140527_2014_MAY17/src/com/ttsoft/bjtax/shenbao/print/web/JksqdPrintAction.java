/*
 * <p>Title:������˰�г�����֧��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�Ƽ��ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ��ɷ����޹�˾</p>
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
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ɿ����뵥��ӡ</p>
 * 
 * �������걨�ĵ��ӽɿ�ר�ýɿ��� ʹ�õ�java��jspֱ����ֲ������
 * 
 * @author Ǯ�� 
 * @version 1.1
 */
public class JksqdPrintAction
    extends ShenbaoAction
{

  /**
   * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
   * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
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
   * ҳ���ʼ��
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
      //��õ�ǰ��userData����
      UserData ud = this.getUserData(request);
      pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id

      VOPackage vo = new VOPackage();
      
      HashMap datamap = new HashMap();
      datamap.put("sjly",pf.getHeadSjly());
      datamap.put("jsjdm",pf.getJsjdm());
      datamap.put("sbbh",pf.getSbbh());
      
      vo.setAction(ActionConstant.INT_ACTION_QUERY);
      vo.setData(datamap);
      vo.setUserData(ud);
      vo.setProcessor(ProcessorNames.PRINT_DZJKZYJKS_PROCESSOR);

      //����processor
      datamap = (HashMap) ShenbaoProxy.getInstance().process(vo);
      //���淵��ֵ
      pf.setHjjedx((String)datamap.get("hjjedx"));
      pf.setHjjexx((String)datamap.get("hjjexx"));
      pf.setSzitem((List)datamap.get("datalist"));
      pf.setZh((String)datamap.get("zh"));
      pf.setYhmc((String)datamap.get("yhmc"));
      pf.setSbrq((String)datamap.get("sbrq"));
      pf.setNsrmc((String)datamap.get("nsrmc"));
      pf.setSwjgzzjgdm((String)datamap.get("swjgzzjgdm"));
      
      
      //��web���������
      Map tmpmap = CodeTableUtil.getCodeTableMap(request, "SWJJZZJG_MAP");
      
      //���ջ������ƣ�˰���������ƣ�����Ҫ���ݴ��������
      Swjgzzjg jg = (Swjgzzjg)tmpmap.get(pf.getSwjgzzjgdm());
      pf.setSwjgzzjgmc(jg.getSwjgzzjgmc());

      //��������
      pf.setGkzzjgdm(jg.getGkjhh());
      pf.setGkzzjgmc(jg.getSkgk());
      
      //˰������
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
              tempstr += "(���ɽ𡢷���)";    
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
