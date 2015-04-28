package com.ttsoft.bjtax.smsb.gdzys.sbzs.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ--����ռ��˰���չ���</p>
 * <p>Description:�����ɿ���Action </p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysCxjksAction extends SmsbAction{
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
	    super.initialRequest(mapping, form, request, response);
	    request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
	                         "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
	                        // "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
	                         "<font color=\"#7C9AAB\">����ռ������</font>&gt;"+
	                         "<font color=\"#7C9AAB\">�����ɿ���</font>");
	    request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
	                         "�����ɿ���");
	    request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
	                         "help/smsb/lszs/jkscx-000.htm");
	  }
	  
	  /**
	   * ��ʼ��
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
		  GdzysCxjksForm pf = (GdzysCxjksForm) form;
	    try
	    {
	    System.out.println("##############GdzysCxjksAction.doShow");
	      //��õ�ǰ��userData����
	      UserData ud = this.getUserData(request);
	      //���ò�ѯ�����ʾ��ʶδ0
		  pf.setCxJgTsFlag("0");
	      //���淵��ֵ--------Shi Yanfeng 20031029-------
	      request.setAttribute(mapping.getAttribute(), pf);
	      return mapping.findForward("Show");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	      throw ExceptionUtil.getBaseException(ex);
	    }
	  }
	  
	  /**
	   * ���ܣ���ѯ�ɳ��ߵĽɿ�����Ϣ��
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
	  public ActionForward doQuery(ActionMapping mapping,
	                               ActionForm form,
	                               HttpServletRequest request,
	                               HttpServletResponse response) throws
	      BaseException
	  {
		  System.out.println("##############GdzysCxjksAction.doQuery");
	    //��õ�ǰ��userData����
	    UserData ud = this.getUserData(request);

	    GdzysCxjksForm pf = (GdzysCxjksForm) form;
	    try
	    {
	      //��Ԥװ�ص���Ϣ��ò���д
	      VOPackage vo = new VOPackage();
	      vo.setAction(GdzysCodeConstant.SMSB_QUERYACTION);
	      vo.setData(pf);
	      vo.setUserData(ud);
	      vo.setProcessor(GdzysCodeConstant.GDZYS_SBZS_CXJKS_PROCESSOR);

	      //����processor
	      pf = (GdzysCxjksForm) ZhsbProxy.getInstance().process(vo);
	      if(pf.getDataList().size()==0){
		    	//���ò�ѯ�����ʾ��ʶδ1
		  	    pf.setCxJgTsFlag("1");
		      }else{
		       //���ò�ѯ�����ʾ��ʶδ0
			    pf.setCxJgTsFlag("0");
		      }
	      request.setAttribute(mapping.getAttribute(), pf);
	    }
	    catch (Exception ex)
	    {
	      throw ExceptionUtil.getBaseException(ex);
	    }
	    return mapping.findForward("Query");
	  }
	  
	/**
	   * ���ܣ���ѯ�ɳ��ߵĽɿ�����Ϣ��
	   * @param mapping Struct ActionMapping
	   * @param form  instance of BaseForm
	   * @param request request from HTML
	   * @param response response from HTML
	   */
  public ActionForward doCkJks(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      BaseException
  {
	  System.out.println("##############GdzysCxjksAction.doCkJks");
    //��õ�ǰ��userData����
    UserData ud = this.getUserData(request);

    GdzysCxjksForm pf = (GdzysCxjksForm) form;
    try
    {
      //��Ԥװ�ص���Ϣ��ò���д
      VOPackage vo = new VOPackage();
      vo.setAction(GdzysCodeConstant.SMSB_GDZYS_CKJKSACTION);
      vo.setData(pf);
      vo.setUserData(ud);
      vo.setProcessor(GdzysCodeConstant.GDZYS_SBZS_CXJKS_PROCESSOR);

      //����processor
      pf = (GdzysCxjksForm) ZhsbProxy.getInstance().process(vo);
      List dataList=pf.getDataList();
      Map map=(Map)dataList.get(0);
      
      System.out.println("#########jkpzh="+(String)map.get("jkpzh"));
      System.out.println("#########jsjdm="+(String)map.get("jsjdm"));
      
      JksPrintForm jkspf = new JksPrintForm();
     //����ʾ���水ť
      jkspf.setSaveBtnVisible(false);
      //�޽����ڲ��ɱ༭
      jkspf.setXjrqEdit(false);
      //����ʾ��ע
      jkspf.setBzVisible(true);
      
      jkspf.setHeadJkpzh((String)map.get("jkpzh"));
      jkspf.setHeadJsjdm((String)map.get("jsjdm"));
      jkspf.setHeadSjly(GdzysCodeConstant.SMSB_SJLY_GDZYS); //������Դ
      jkspf.setActionType("Show");
      request.setAttribute("jksPrintForm", jkspf);
      return mapping.findForward("Print");
    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex);
    }
  }
  
	/**
   * ���ܣ�ɾ���Ľɿ��飡
   * @param mapping Struct ActionMapping
   * @param form  instance of BaseForm
   * @param request request from HTML
   * @param response response from HTML
   */
public ActionForward doDelJks(ActionMapping mapping,
                           ActionForm form,
                           HttpServletRequest request,
                           HttpServletResponse response) throws
  BaseException
{
  System.out.println("##############GdzysCxjksAction.doDelJks");
//��õ�ǰ��userData����
UserData ud = this.getUserData(request);

GdzysCxjksForm pf = (GdzysCxjksForm) form;
try
{
  //��Ԥװ�ص���Ϣ��ò���д
  VOPackage vo = new VOPackage();
  vo.setAction(CodeConstant.SMSB_DELETEACTION);
  vo.setData(pf);
  vo.setUserData(ud);
  vo.setProcessor(GdzysCodeConstant.GDZYS_SBZS_CXJKS_PROCESSOR);

  //����processor
  pf = (GdzysCxjksForm) ZhsbProxy.getInstance().process(vo);
  if(pf.getDataList().size()==0){
	//���ò�ѯ�����ʾ��ʶδ1
    pf.setCxJgTsFlag("1");
  }else{
	//���ò�ѯ�����ʾ��ʶδ0
	pf.setCxJgTsFlag("0");
   }
  request.setAttribute(mapping.getAttribute(), pf);
  return mapping.findForward("Query");
}
catch (Exception ex)
{
  throw ExceptionUtil.getBaseException(ex);
}
}
  
	  
}
