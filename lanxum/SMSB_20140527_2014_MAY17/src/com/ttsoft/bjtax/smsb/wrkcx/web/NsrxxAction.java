package com.ttsoft.bjtax.smsb.wrkcx.web;
import java.util.*;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import java.io.OutputStream;
import com.ttsoft.bjtax.smsb.wrkcx.WrkVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import com.ttsoft.framework.exception.ApplicationException;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import com.ttsoft.bjtax.dj.model.JBSJ;
import com.ttsoft.bjtax.dj.model.ZJG;
import com.ttsoft.bjtax.dj.model.SWDL;
import com.ttsoft.bjtax.smsb.wrkcx.processor.DjxxxxBO;
import com.ttsoft.framework.util.JspUtil;
import com.ttsoft.bjtax.smsb.wrkcx.processor.ConstantKey;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class NsrxxAction extends SmsbAction{
  public NsrxxAction() {
  }

  /**
      *   ���ܲ�ѯ
      *   1����form��ȡ��ѯ����,���Ѳ�ѯ�����ŵ�SybdcxBO��ȥ
      *   2������ѯ����ŵ�session��
      *   @param mapping The ActionMapping used to select this instance
      *   @param form The optional ActionForm bean for this request (if any)
      *   @param request The HTTP request we are processing
      *   @param response The HTTP response we are creating
      *   @return ������ת��ҳ��
      *   @throws BaseException �׳�Ӧ���쳣
      */
     public ActionForward doQuery_DjInfo(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws BaseException
     {
         System.out.println("---------nsrxxAction----doQuery_DjInfo");
         HttpSession session = request.getSession(false);
         session.removeAttribute(WebConstantKey.SESSION_KEY_DJXXXXBO);
         NsrxxForm theForm = (NsrxxForm) form;
         String dj_jsjdm = theForm.getDj_jsjdm();
         try
         {
             com.ttsoft.bjtax.dj.util.DjxxUtil djproxy = new com.ttsoft.bjtax.dj.util.DjxxUtil();
             HashMap map = (HashMap) djproxy.getDjDetailInfo(dj_jsjdm);
             DjxxxxBO djbo = new DjxxxxBO();
             //��ѯ������Ϣ
             djbo.setJbsj( (JBSJ) map.get("JBSJ"));
             JBSJ jbsj=(JBSJ) map.get("JBSJ");

             //��ѯ�ܻ���
             djbo.setZjg( (ZJG) map.get("ZJG"));
             //˰�������Ϣ
             djbo.setSwdl( (SWDL) map.get("SWDL"));
             //��ѯ�����ʷ
             djbo.setBgxxList( (List) map.get("BGSJ_LS"));
             //��ѯ�����Ǽ�
             djbo.setDxdjList( (List) map.get("DXHZCHCL"));
             //��ѯ�����Ǽ���ʷ
             djbo.setDxdjList_ls( (List) map.get("DXHZCHCL_LS"));
             //��ѯ��֧����
             djbo.setFzjgList( (List) map.get("FZJG"));
             //��ѯͣ��ҵ
             djbo.setTydjList( (List) map.get("TFY"));
             //��ѯͣ��ҵ��ʷ
             djbo.setTydjList_ls( (List) map.get("TFY_LS"));
             //��ѯ�����ʺ�
             djbo.setYhzhList( (List) map.get("YHZH"));
             //��ѯע���Ǽ�
             djbo.setZxdjList( (List) map.get("ZXDJ"));
             //˰��Ǽǡ�����ϵ��
             djbo.setLxrList( (List) map.get("QYRY"));
             //˰��Ǽǡ���Ͷ�ʷ��Ǽ���Ϣ
             djbo.setTzfList( (List) map.get("TZF"));

             session.setAttribute(WebConstantKey.SESSION_KEY_DJXXXXBO, djbo);
         }
         catch(Exception e)
         {
             throw new ApplicationException("��ѯ��˰�˵Ǽ���ϸ��Ϣʱ,Զ�̵��ô���");
         }
         //��תҳ��
         return mapping.findForward("Query_DjInfo");
     }

     /**
  * ��ת������״̬��Ϣ
  * @param mapping mapping����
  * @param form ҳ�����
  * @param request ҳ������
  * @param response ҳ����Ӧ
  * @return ������ת��ҳ��
  * @throws BaseException �׳�Ӧ���쳣
  */
 public ActionForward doQtztxx(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
     BaseException
 {

     NsrxxForm cxForm = (NsrxxForm) form;
     //�ѷ���Action���¸�ֵ
     cxForm.setBackAction(cxForm.getBackAction());

     return mapping.findForward("Qtztxx");
 }

 /**
  * ��ת����֧����
  * @param mapping mapping����
  * @param form ҳ�����
  * @param request ҳ������
  * @param response ҳ����Ӧ
  * @return ������ת��ҳ��
  * @throws BaseException �׳�Ӧ���쳣
  */
 public ActionForward doFzjgxx(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
     BaseException
 {

     NsrxxForm cxForm = (NsrxxForm) form;
     //�ѷ���Action���¸�ֵ
     cxForm.setBackAction(cxForm.getBackAction());

     return mapping.findForward("Fzjgxx");
 }

 /**
  * ��ת����������
  * @param mapping mapping����
  * @param form ҳ�����
  * @param request ҳ������
  * @param response ҳ����Ӧ
  * @return ������ת��ҳ��
  * @throws BaseException �׳�Ӧ���쳣
  */
 public ActionForward doJbsj(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws
     BaseException
 {
     HttpSession session = request.getSession();
     NsrxxForm cxForm = (NsrxxForm) form;

     //�ѷ���Action���¸�ֵ
     cxForm.setBackAction(cxForm.getBackAction());

     return mapping.findForward("Jbsj");
 }

 /**
   * ��ת�������ʺ���Ϣ
   * @param mapping mapping����
   * @param form ҳ�����
   * @param request ҳ������
   * @param response ҳ����Ӧ
   * @return ������ת��ҳ��
   * @throws BaseException �׳�Ӧ���쳣
   */
  public ActionForward doYhzhxx(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
      BaseException
  {

      NsrxxForm cxForm = (NsrxxForm) form;
      //�ѷ���Action���¸�ֵ
      cxForm.setBackAction(cxForm.getBackAction());

      return mapping.findForward("Yhzhxx");
  }
  /**
    * ��ת���ܻ�����Ϣ
    * @param mapping mapping����
    * @param form ҳ�����
    * @param request ҳ������
    * @param response ҳ����Ӧ
    * @return ������ת��ҳ��
    * @throws BaseException �׳�Ӧ���쳣
    */
   public ActionForward doZjgxx(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
       BaseException
   {
       NsrxxForm cxForm = (NsrxxForm) form;
       //�ѷ���Action���¸�ֵ
       cxForm.setBackAction(cxForm.getBackAction());

       return mapping.findForward("Zjgxx");
   }
   /**
      * ��ת�������Ϣ
      * @param mapping mapping����
      * @param form ҳ�����
      * @param request ҳ������
      * @param response ҳ����Ӧ
      * @return ������ת��ҳ��
      * @throws BaseException �׳�Ӧ���쳣
      */
     public ActionForward doBgxx(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws
         BaseException
     {

         NsrxxForm cxForm = (NsrxxForm) form;
         //�ѷ���Action���¸�ֵ
         cxForm.setBackAction(cxForm.getBackAction());

         return mapping.findForward("Bgxx");
     }


     /**
          *   ��ʼ��Action���������ñ��⡢������Ϣ�ȹ�����Ϣ
          *   ��ʼ��ҳ�������ҳ��header��Ϣ��
          *   @param mapping The ActionMapping used to select this instance
          *   @param form The optional ActionForm bean for this request (if any)
          *   @param request The HTTP request we are processing
          *   @param response The HTTP response we are creating
          */
         public void initialRequest(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
         {
             String init = JspUtil.displayValue(request.getParameter("initType"));
             NsrxxForm theForm = (NsrxxForm) form;
             String query = theForm.getHzcxlx();
             int initType = 0;
             if (init != null && init.trim().length() > 0)
             {
                 initType = Integer.valueOf(init).intValue();
             }
             if (query != null && query.trim().length() > 0)
             {
                 initType = Integer.valueOf(query).intValue();
             }
             request.setAttribute(WebConstantKey.RAK_REQUEST_URI,
                                  request.getServletPath());
             request.setAttribute(WebConstantKey.RAK_ACTION,
                                  WebConstantKey.ACTION_HZCX);

                     request.setAttribute(ConstantKey.HEAD_HELPURL,
                                          "");
                     request.setAttribute(ConstantKey.HEAD_POSITION,
                                          "�ۺϷ��������Ϣϵͳ&gt;�걨����&gt;�걨��ⲻһ�²�ѯ&gt;��˰����ϸ��Ϣ");
                     request.setAttribute(ConstantKey.HEAD_TITLE, "��˰����ϸ��Ϣ");
                     theForm.setTitle("��˰����ϸ��Ϣ");


         }



}