package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.hznszjgfpb.web;
/**
 * <p>Title: ��ҵ����˰������˰�ܻ��������action</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Company: ������˼������ϵͳ���޹�˾</p>
 *
 * @author wangxq
 * @version 1.0
 */

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.gqtzsdmxb.web.GqtzsdmxbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2009.util.QysdsUtil2009;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.syax.creports.Constants;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;

public class HznszjgfpbAction extends SmsbAction
{
	 /**
     * ����Ա����
     */

    private UserData userData = null;
   public HznszjgfpbAction()
   {
   }

   /**
    * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
    * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param actionForm
    *            QysdsnbForm
    * @param httpServletRequest
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    */

   protected void initialRequest(ActionMapping mapping, ActionForm actionForm,
           HttpServletRequest httpServletRequest, HttpServletResponse response)

   {
       super.initialRequest(mapping, actionForm, httpServletRequest, response);
       httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                       "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;<font color=\"#7C9AAB\">��ҵ����˰������˰�ܻ��������</font></td>");
       httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
               "��ҵ����˰������˰�ܻ��������");
       httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
               "help/smsb/sbzl/qysdsnb/qysdsnb-000.htm");

   }

   /**
    * ��ʼ��ҳ������
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward����תĿ��
    * @throws BaseException
    *             ϵͳ��׽�쳣�������쳣�����׳�
    */

/*    public ActionForward doShow(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {

       try {
           // ��ȡZfjgqysdsjbForm����
           ZfjgqysdsjbForm zfjgForm = (ZfjgqysdsjbForm) form;
           // ��ȡ���ɷ�ϵͳ�û���Ϣ
           UserData userData = this.getUserData(request);
           // ��ʼ�����ݴ�������
           VOPackage vo = new VOPackage();
           // ���ú�̨����Actionֵ---SHOWACTION
           vo.setAction(CodeConstant.SMSB_SHOWACTION);
           // �����������Data����
           vo.setData(zfjgForm);
           // ���ñ��ɷ�ϵͳ�û���Ϣ
           vo.setUserData(userData);
           // ����ProxyҪ���õ�processor����---ZfjgqysdsjbProcessor
           vo.setProcessor(CodeConstant.SBZL_QYSDSJB2012_ZFJG_PROCESSOR);

           // ����Proxy����ʼ��CzqysdsjbForm��ֵ
           zfjgForm = (ZfjgqysdsjbForm) SbzlProxy.getInstance().process(vo);
           // ��HdzssdsjbForm ����request��
           System.out.println("mapping.getAttribute() = " + mapping.getAttribute());
           request.setAttribute(mapping.getAttribute(), zfjgForm);

           return mapping.findForward("Show");

       } catch (Exception ex) {
           // ϵͳ��׽�쳣�������쳣�����׳�
           throw ExceptionUtil.getBaseException(ex);
       }
   }*/

   /**
    * ��ѯ���걨����
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward����תĿ��
    * @throws BaseException
    *             ϵͳ��׽�쳣�������쳣�����׳�
    */
   public ActionForward doQuery(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {
   	
   	    System.out.println("HznszjgfpbAction####ddddddd##########doQuery");
//   	CzzssdsjbForm czzssdsjbForm=(CzzssdsjbForm)request.getAttribute("czzssdsjbForm");
   	   QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false).getAttribute(CodeConstant.SESSIONKEY_QYSDSNEWFORM);
		
       // ��ȡZfjgqysdsjbForm����
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       System.out.println("22####hznszjgfpbForm.getYnsdse()="+hznszjgfpbForm.getYnsdse());
       if(request.getParameter("skksrq")!=null){
    	   hznszjgfpbForm.setSkssksrq(request.getParameter("skksrq"));
       }else{
    	   hznszjgfpbForm.setYnsdse(null);
    	   hznszjgfpbForm.setZjgftse(null);
    	   hznszjgfpbForm.setZjgfpse(null);
    	   hznszjgfpbForm.setFzjgftse(null);
       }
       if(request.getParameter("skjsrq")!=null){
    	   hznszjgfpbForm.setSkssjsrq(request.getParameter("skjsrq"));
       }
       
       hznszjgfpbForm.setJsjdm(baseForm.getJsjdm());
       hznszjgfpbForm.setSbrq(baseForm.getSbrq());
       System.out.println("###baseForm.getNsrmc()="+baseForm.getNsrmc());
      // hznszjgfpbForm.setNsrmc(baseForm.getNsrmc());
       hznszjgfpbForm.setZjgmc(baseForm.getNsrmc());
       
       // ��ȡ���ɷ�ϵͳ�û���Ϣ
       UserData userData = this.getUserData(request);
       // ���ñ���������-����
       hznszjgfpbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
       // ����˰����������
       // czqysdsjbForm.setSwjsjdm(userData.getXtsbm1());
       // ����¼��������
       hznszjgfpbForm.setLrr(userData.getYhid());
       this.getBaseForm(request, hznszjgfpbForm);
       // ��ʼ�����ݴ�������
       VOPackage vo = new VOPackage();
       // ���ú�̨����Actionֵ---QUERYACTION
       vo.setAction(CodeConstant.SMSB_QUERYACTION);
       // �����������Data����,������HznszjgfpbForm
       vo.setData(hznszjgfpbForm);
       // ����ProxyҪ���õ�processor����---ZfjgqysdsjbProcessor
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);
       // ����ϵͳ�û���Ϣ
       vo.setUserData(userData);
       try {
           // ����Proxy��ִ��processor,��ȡhznszjgfpbForm����ֵ
           hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           hznszjgfpbForm.setJglx("1");
           // ��czqysdsjbForm����request,��Ϊ����������
           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
           // �����ɹ���ת
           return mapping.findForward("Show");

       } catch (Exception ex) {

           hznszjgfpbForm.reset(mapping,request);
           // ϵͳ��׽�쳣�������쳣�����׳�
           throw ExceptionUtil.getBaseException(ex);
       }
   }

   /**
    * �����걨����
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward����תĿ��
    * @throws BaseException
    *             ϵͳ��׽�쳣�������쳣�����׳�
    */
   public ActionForward doSave(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {

       // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
       ActionForward forward = doHandleToken(mapping, request);
       if (forward != null) {
           return forward;
       }
       
       //System.out.println("$$$$$$$$$17.1_1="+request.getParameter("fzjgfpbl"));
       //System.out.println("$$$$$$$$$17.2_1="+request.getParameter("17.2_1"));
       //request.getp
       
       // ��ȡHznszjgfpbForm����
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       
       // ��ȡ���ɷ�ϵͳ�û���Ϣ
       UserData userData = this.getUserData(request);
       // ����¼��������
       hznszjgfpbForm.setLrr(userData.getYhid());
       // ���ñ���������
       hznszjgfpbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
       // ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����czqysdsjbForm��DataList��
       System.out.println("length = " + hznszjgfpbForm.getFzjgColumns().length);
       System.out.println("Column[0] = " + hznszjgfpbForm.getFzjgColumns()[0]);
       System.out.println("Column[1] = " + hznszjgfpbForm.getFzjgColumns()[1]);
       hznszjgfpbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
               hznszjgfpbForm.getFzjgColumns()));
       System.out.println("list size = " + hznszjgfpbForm.getQysdsjbList().size());
       
       System.out.println("nsrmc = " + hznszjgfpbForm.getNsrmc());
       this.getBaseForm(request, hznszjgfpbForm);
       // ��czqysdsjbForm����request,������ʧ��ʱ����֤ҳ��������Ȼ��ʾ
       request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
       // ��ʼ�����ݴ�������
       VOPackage vo = new VOPackage();
       // ������������
       vo.setAction(CodeConstant.SMSB_SAVEACTION);
       vo.setData(hznszjgfpbForm);
       vo.setUserData(userData);
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);

       try {
           // ����Proxy��ִ��processor,��ȡczqysdsjbForm����ֵ
           hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           hznszjgfpbForm.setJglx("1");
           // ��czqysdsjbForm�еĻ��������ÿ�
//           hznszjgfpbForm.reset(mapping, request);
           // ��czqysdsjbForm����request,��Ϊ����������
           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
           // �����ɹ���ת
           request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
           return mapping.findForward("Show");
       } catch (Exception ex) {
           // ϵͳ��׽�쳣�������쳣�����׳�
           throw ExceptionUtil.getBaseException(ex);
       }
   }

   /**
    * ɾ���걨����
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            CzqysdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward����תĿ��
    * @throws BaseException
    *             ϵͳ��׽�쳣�������쳣�����׳�
    */

   public ActionForward doDelete(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException {
   	 System.out.println("doDelete ============Action ");
   	
       // -------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
       ActionForward forward = doHandleToken(mapping, request);
       if (forward != null) {
           return forward;
       }
       // ��ȡ���ɷ�ϵͳ�û���Ϣ
       UserData userData = this.getUserData(request);
       // ��ȡHznszjgfpbForm����
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       // ����columns�е��ֶ���ȡ����Ҫ��ǰ̨�б����ݣ�����hznszjgfpbForm��DataList�У�
       hznszjgfpbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
               hznszjgfpbForm.getFzjgColumns()));
       // ��zfjgForm����request,��ɾ��ʧ��ʱ����֤ҳ��������Ȼ��ʾ
       request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
       // ����¼��������
       hznszjgfpbForm.setLrr(userData.getYhid());
       // ���ñ���������
       hznszjgfpbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
       // ��ʼ�����ݴ�������
       VOPackage vo = new VOPackage();
       // ������������
       vo.setAction(CodeConstant.SMSB_DELETEACTION);
       vo.setData(hznszjgfpbForm);
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);
       vo.setUserData(userData);
       this.getBaseForm(request, hznszjgfpbForm);
       try {
           // ����Proxy��ִ��processor,��ȡhznszjgfpbForm����ֵ
           hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           // ��hznszjgfpbForm�еĻ��������ÿ�
//           hznszjgfpbForm.reset(mapping, request);
//           // ��hznszjgfpbForm����request,��Ϊ����������
//           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
//           // �����ɹ���ת������ʾ��Ϣ��ͷ�ļ�header.jsp�л�ȡ
//           request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "ɾ���ɹ���");
//           //this.doJump(mapping, hznszjgfpbForm, request, response);
//           return mapping.findForward("Show");
           
			System.out.println("I am jumping to  Hznszjgfpb....");
			// ��ȡCzzssdsjbForm����
			HznszjgfpbForm tempHznszjgfpbForm = new HznszjgfpbForm();

			// ��CzzssdsjbForm ����request��
			//request.setAttribute(mapping.getAttribute(), CzzssdsjbForm);
			//
    System.out.println("####hznszjgfpbForm.getSkssksrq()="+hznszjgfpbForm.getSkssksrq());
    System.out.println("####hznszjgfpbForm.getSkssjsrq()="+hznszjgfpbForm.getSkssjsrq());
    
    System.out.println("11####hznszjgfpbForm.getYnsdse()="+hznszjgfpbForm.getYnsdse());
    
    System.out.println("33####tempHznszjgfpbFormgetYnsdse()="+tempHznszjgfpbForm.getYnsdse());
    hznszjgfpbForm.setYnsdse(null);
    
			tempHznszjgfpbForm.setJsjdm(hznszjgfpbForm.getJsjdm());
			tempHznszjgfpbForm.setSkssksrq(hznszjgfpbForm.getSkssksrq());
			tempHznszjgfpbForm.setSkssjsrq(hznszjgfpbForm.getSkssjsrq());
			//request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
			request.setAttribute("hznszjgfpbForm", tempHznszjgfpbForm);
			return mapping.findForward("Query");
           
       } catch (Exception ex) {
           // ϵͳ��׽�쳣�������쳣�����׳�
           throw ExceptionUtil.getBaseException(ex);
       }
   }

   /**
    * ��תҳ������
    *
    * @param mapping
    *            struts.action.ActionMapping
    * @param form
    *            HdzssdsjbForm
    * @param request
    *            HttpServletRequest
    * @param response
    *            HttpServletResponse
    * @return actionMapping.findForward����תĿ��
    * @throws BaseException
    *             ϵͳ��׽�쳣�������쳣�����׳�
    */
   
   public ActionForward doJump(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response)
           throws BaseException
   {
       try {
           System.out.println("I am jumping....");
           // ��ȡHznszjgfpbForm����
           HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
           String jsjdm = hznszjgfpbForm.getJsjdm();
           String jumpStr = null;
           // ��CzzssdsjbForm ����request��
           HznszjgfpbForm tempHznszjgfpbForm=new HznszjgfpbForm();
           if(jsjdm != null && !jsjdm.equals(""))
           {
        	   tempHznszjgfpbForm.setJsjdm(jsjdm);
               jumpStr = "Query";
           }
           else
           {
               jumpStr = "Jump";
           }
           request.setAttribute("hznszjgfpbForm", tempHznszjgfpbForm);
           System.out.println("jumpStr = " + jumpStr);
           return mapping.findForward(jumpStr);
       } catch (Exception ex) {
           // ϵͳ��׽�쳣�������쳣�����׳�
           throw ExceptionUtil.getBaseException(ex);
       }
   }
   
   /**
    * �˳�ҳ��
    * @param mapping struts.action.ActionMapping
    * @param form QysdsnbForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return actionMapping.findForward����תĿ��
    * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
    */
   public ActionForward doExit(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
           BaseException {

       return mapping.findForward("Return");
   }
   
   
   /**
    * ���ҳ������
    * @param mapping struts.action.ActionMapping
    * @param form QysdsnbForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return actionMapping.findForward����תĿ��
    * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
    */
   public ActionForward doCheck(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws
           BaseException {

       //-------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------
       ActionForward forward = doHandleToken(mapping, request);
       if (forward != null) {
           return forward;
       }
       HznszjgfpbForm hznszjgfpbForm = (HznszjgfpbForm) form;
       hznszjgfpbForm.setQysdsjbList(SfRequestUtil.getValuesToList(request,
               hznszjgfpbForm.getFzjgColumns()));
       /*
       hznszjgfpbForm.setSbbczlList(SfRequestUtil.getValuesToList(request,
    		   hznszjgfpbForm.getSbbczl_columns()));
       hznszjgfpbForm.setHjList(SfRequestUtil.getValuesToList(request,
    		   hznszjgfpbForm.getHj_columns()));
      */		   
       this.getBaseForm(request, hznszjgfpbForm); //������˰�˻�����Ϣ
       userData = this.getUserData(request);
       request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);

       VOPackage vo = new VOPackage();
       vo.setAction(CodeConstant.SMSB_CHECKACTION); //���ò�������
       vo.setData(hznszjgfpbForm); //���ò�������
       vo.setUserData(userData);
       vo.setProcessor(CodeConstant.SBZL_QYSDSNB_HZNSZJGFPB_PROCESSOR);
       try {
           //����processor
    	   hznszjgfpbForm = (HznszjgfpbForm) SbzlProxy.getInstance().process(vo);
           request.setAttribute(mapping.getAttribute(), hznszjgfpbForm);
       
           
           if (hznszjgfpbForm.getCheckList() == null) {
               request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
           } else if (hznszjgfpbForm.getCheckList().size() == 0) {
               request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ͨ����");
           } else if (hznszjgfpbForm.getCheckList().size() > 0) {
               request.setAttribute(CodeConstant.CHECK_RESULT_HTML,
                                    QysdsUtil2009.getCheckResults(hznszjgfpbForm.
                       getCheckList()));
           }
           return mapping.findForward("Query");
       } catch (Exception ex) {
           //ϵͳ��׽�쳣�������쳣�����׳�
           throw ExceptionUtil.getBaseException(ex);
       }

   }
   
   
   /**
    * ��session�л�ȡ������Ϣ
    *
    * @param request
    * @return
    */
   private void getBaseForm(HttpServletRequest request, HznszjgfpbForm form) {

       userData = this.getUserData(request);

       QysdsNewForm baseForm = (QysdsNewForm) request.getSession(false)
                               .getAttribute(CodeConstant.
                                             SESSIONKEY_QYSDSNEWFORM);

       String ksrq = request.getParameter("skksrq");
       String jsrq = request.getParameter("skjsrq");

       if ((ksrq != null && !"".equals(ksrq)) &&
           (jsrq != null && !"".equals(jsrq))) {
           baseForm.setSkssksrq(request.getParameter("skksrq"));
           baseForm.setSkssjsrq(request.getParameter("skjsrq"));
           request.getSession(false).setAttribute(CodeConstant.
                   SESSIONKEY_QYSDSNEWFORM, baseForm);
       }

       if (baseForm != null) {
           form.setJsjdm(baseForm.getJsjdm());
           form.setSbrq(baseForm.getSbrq());
           form.setNsrmc(baseForm.getNsrmc());
           form.setQh("1");
           form.setSknd(baseForm.getSknd());
           form.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
           form.setSkssksrq(baseForm.getSkssksrq());
           form.setSkssjsrq(baseForm.getSkssjsrq());
           form.setSwjsjdm(baseForm.getSwjsjdm());
           form.setSwjgzzjgdm(baseForm.getSwjgzzjgdm());
           form.setQxdm(baseForm.getQxdm());
           form.setLrr(userData.getYhid());
           form.setCkzd(baseForm.getCkzd());
           form.setZsfs(baseForm.getZsfs());
           form.setSsjjlx(baseForm.getSsjjlx());
           form.setSshy(baseForm.getSshy());
           form.setGzglxs(baseForm.getGzglxs());
           form.setJmlx(baseForm.getJmlx());
           form.setNextTableURL(QysdsUtil2009.getTableURL(baseForm,
                   "N_" + CodeConstant.TABLE_ID_2012_12));
           form.setPreviousTableURL(QysdsUtil2009.getTableURL(baseForm,
                   "P_" + CodeConstant.TABLE_ID_2012_12));
       }
   }
   
}

