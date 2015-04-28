/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ��Ƿ˰�ɿ��걨���ܣ������ɿ���¼�룬ά����</p>
 * @author Zhang Yijun
 * @version 1.1
 */
public class QsjksbAction
    extends SmsbAction
{
    //�û�������Ϣ
    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;Ƿ˰�ɿ��걨</td>");
        //httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
        //                                "Ƿ˰�ɿ��걨");
    }

    /**
     * ��ʼʱ��ʾҳ��
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
     * ���ݼ���������ѯ������Ϣ
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
     * �����걨���ݲ����ɽɿ���
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
    	
        //��ֹrefresh
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
            throw ExceptionUtil.getBaseException(ex1, "����Ƿ˰�ɿ��걨ʧ��!�޷�ȡ����˰�˻�������");
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
        //����ͨ���ܿصõ����û���Ϣ
        vo.setUserData(ud);
        try
        {
            //�������ɽɿ�����걨���
        	System.out.println("======�������ɽɿ�����걨���===123===");

        	
            Object retobj = SbzlProxy.getInstance().process(vo);
            actionForm = (ActionForm)retobj;

            //ת�Ƶ��ɿ���ά������
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
            throw ExceptionUtil.getBaseException(ex, "���ɽɿ�����Ϣʧ��!");
        }
    }
    
	/**
     * ������˰�˻�����Ϣ����ʼ��ҳ����Ϣ
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
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            
            System.out.println("jsjdm=" + form.getJsjdm());			

			if (TinyTools.isCompany(form.getJsjdm())) {

				HashMap djMap = InterfaceDj.getDjInfo_New(form.getJsjdm(), ud);
				// ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
				SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");

				System.out.println("nsrmc=" + jbsj.getNsrmc());

				// ��λ����
				form.setNsrmc(jbsj.getNsrmc());
				// ������˰��״̬
				form.setNsrzt(jbsj.getNsrzt());
				// ͨ���Ǽǽӿ�ȡ�������ʻ���Ϣ
				List bankList = (List) djMap.get("YHZH");
				if (bankList == null) {
					bankList = new ArrayList(0);
				}
				form.setBankList(bankList);
				// ������������
				form.setBankJsArray(this.getBankJsArray(bankList, TinyTools.isCompany(form.getJsjdm())));

			} else {

				HashMap zrrdjMap = (HashMap) InterfaceDj.getZRRInfo(form
						.getJsjdm(), ud);
				// ͨ����Ȼ�˵Ǽǽӿ�ȡ����˰�������Ϣ
				ZRRJBSJ zrrJbsj = (ZRRJBSJ) zrrdjMap
						.get(DjOuterConstant.ZRRJBSJ);

				System.out.println("zrrNsrmc=" + zrrJbsj.getNsrmc());

				// ��λ����
				form.setNsrmc(zrrJbsj.getNsrmc());
				// ������˰��״̬
				form.setNsrzt(zrrJbsj.getNsrzt());
				// ͨ����Ȼ�˵Ǽǽӿ�ȡ�������ʻ���Ϣ
				List bankList = (List) zrrdjMap.get(DjOuterConstant.ZRRYHZH);
				if (bankList == null) {
					bankList = new ArrayList(0);
				}
				form.setBankList(bankList);
				// ������������
				form.setBankJsArray(this.getBankJsArray(bankList, TinyTools.isCompany(form.getJsjdm())));
			}
            
            /*
			 * //��λ���� form.setNsrmc(jbsj.getNsrmc()); //������˰��״̬
			 * form.setNsrzt(jbsj.getNsrzt()); //ͨ���Ǽǽӿ�ȡ�������ʻ���Ϣ List bankList =
			 * (List) djMap.get("YHZH");
			 * 
			 * if(bankList==null){ bankList=new ArrayList(0); }
			 * form.setBankList(bankList); //������������
			 * form.setBankJsArray(this.getBankJsArray(bankList));
			 */
            
            // �걨����
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
     * �õ������ʺŵ�js����
     * @param yhzh �����˺��б�
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
                //��������ݣ���ɾ�������ӵĶ���
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
     * @description Ϊ������˰��������ҵ������Ȼ��
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
					// ��������ݣ���ɾ�������ӵĶ���
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
					// ��������ݣ���ɾ�������ӵĶ���
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
	 * ���ɿ���ά��ҳ��
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
        //�ɿ���ά��FORMBEAN
        QsjksbjkswhActionForm jks = new QsjksbjkswhActionForm();
        QsjksbForm form = (QsjksbForm) actionForm;
        //���ü��������
        jks.setJsjdm(form.getJsjdm());
        //���õ�λ����
        jks.setNsrmc(form.getNsrmc());
        //�걨���
        jks.setSbbhList(form.getSbbhList());
        //Ԥ�����걨���
        jks.setPresbbh("1");
        //��FORMBEAN����REQUEST
        httpServletRequest.setAttribute("qsjksbjkswhActionForm", jks);
        return actionMapping.findForward("JkswhBySave");
    } 
    
    /**
     * ����ѡ���Ľɿ�ƾ֤�����ж���һƱһ˰��һƱ��˰��ת����Ӧ�Ľɿ�����ϸ����
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
        //�ɿ���ά��FORMBEAN
    	QsjksbjkswhActionForm jks = new QsjksbjkswhActionForm();
    	QsjksbForm form = (QsjksbForm) actionForm;
        //���ü��������
        jks.setJsjdm(form.getJsjdm());
        //���õ�λ����
        jks.setNsrmc(form.getNsrmc());

        //��FORMBEAN����REQUEST
        httpServletRequest.setAttribute("qsjksbjkswhActionForm", jks);
        return actionMapping.findForward("Jkswh");
    }    
}

