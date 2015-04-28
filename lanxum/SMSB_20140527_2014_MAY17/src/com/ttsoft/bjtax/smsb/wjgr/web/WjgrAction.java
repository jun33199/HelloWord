/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRYHZH;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �⼮��������˰�·��걨��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrAction
    extends SmsbAction
{
    /**
     *��ʼʱ��ʾҳ��
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        try
        {
            WjgrActionForm form = (WjgrActionForm) actionForm;

            //��ʼ�������Ϣ
            this.setInitInfo(form);
            return (new ActionForward(actionMapping.getInput()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "�����⼮��������˰�·��걨��ʧ��!");
        }
    }

    /**
     *��ʼʱ��ʾҳ��
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);
        WjgrActionForm form = (WjgrActionForm) actionForm;
        //�����걨����
        this.setInitInfo(actionForm);
        //�������ݰ�
        VOPackage vo = new VOPackage();
        //����
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        //����processor
        vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
        //����actionForm
        vo.setData(form);
        //����userDate
        vo.setUserData(ud);
        try
        {
            WjgrActionForm retForm = (WjgrActionForm) ZhsbProxy.getInstance().
                                     process(
                vo);
            //������ϸ�б�ʹ��js����
            //���������˺�ʹ�õ�js����
            retForm.setBankJsArray(this.getBankJsArray(retForm.getBankList()));
            httpServletRequest.setAttribute("wjgrActionForm", retForm);

        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }
        return actionMapping.findForward("Query");

    }

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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨�ɿ�</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "��Ȼ�˸�˰�걨");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");
    }

    /*
     *����
     * @param mapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response  The HTTP response we are creating
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

        WjgrActionForm form = (WjgrActionForm) actionForm;
        //�õ��б�����
        String columns[] = form.getColumns();
        //������ϸ����
        form.setDataList(this.getValuesToList(httpServletRequest, columns));


        //start add by qianchao 2005.11.1
        String yhdm = form.getYhdm();
        String zh = form.getZh();

        if (! (yhdm.equals("") || zh.equals("")))
        {
          ZRRJBSJ jbsj = null;
          try
          {
            jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
          }
          catch (Exception ex1)
          {
            ex1.printStackTrace();
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex1, "�����������˰ʧ��!�޷�ȡ����˰�˻�������");
          }
          String ssdwdm = jbsj.getSwjgzzjgdm();
          if (LWUtil.isLW(httpServletRequest.getSession().getServletContext(), ssdwdm,
                          yhdm))
          {
            form.setJksType(CodeConstant.PRINT_YPDS_KM);
          }
          else
          {
            form.setJksType(CodeConstant.PRINT_YPYS);
          }
        }
        //end add by qianchao 2005.11.1

        //�������ݰ�
        VOPackage vo = new VOPackage();
        //����
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //����processor
        vo.setProcessor(CodeConstant.WJGR_PROCESSOR);
        vo.setData(form);
        //����ͨ���ܿصõ����û���Ϣ
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            WjgrActionForm tt = (WjgrActionForm) ZhsbProxy.getInstance().process(vo);
            
            List dataList = tt.getDataList();
            List delList = new ArrayList();
            ArrayList okList = new ArrayList();
            
            if(dataList.size()>0){
            	if(form.getJksType()== CodeConstant.PRINT_YPDS_KM){
            		//һƱ��˰
                	BigDecimal total = new BigDecimal(0.00);
               		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
            			List infoList = (List) iterator.next();
       	
	                    for (int i = 0; i < infoList.size(); i++) {
	
	                    	DeclareInfor declareInfor = (DeclareInfor)infoList.get(i);
	                    	
	                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
	                    	List jkmxList = declareInfor.getSbjkmxInfo();
	                    	
	                    	delList.add(sbjkzb.getJkpzh());
	                    	
	                    	for (int j = 0; j < jkmxList.size(); j++) {
	                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
	                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
	                    		total = total.add(sjse);
	                    	}
	                    }
	                    okList.add(infoList);
            		}
                    
                    if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                    	okList =  new ArrayList();
                    }else{
                    	delList = new ArrayList();
                    }
            	}else{
            		//һƱһ˰        		
                    for (int i = 0; i < dataList.size(); i++) {
                    	
                    	BigDecimal total = new BigDecimal(0.00);
                    	
                    	DeclareInfor declareInfor = (DeclareInfor)dataList.get(i);
                    	
                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
                    	List jkmxList = declareInfor.getSbjkmxInfo();
                    	
                    	for (int j = 0; j < jkmxList.size(); j++) {
                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
                    		total = total.add(sjse);
                    	}
                        if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                        	delList.add(sbjkzb.getJkpzh());
                        }else{
                        	okList.add(declareInfor);
                        }
                    }
        		}
            }
            
            if(delList.size()>0){
	            vo.setAction(CodeConstant.SMSB_DELETEACTION);
	            Map map = new HashMap();
	            map.put("jsjdm", tt.getJsjdm());
	            map.put("jkpzhList", delList);
	            vo.setData(map);
	            ZhsbProxy.getInstance().process(vo);
	            //��������ʣ������
	            tt.setDataList(okList);
            }
            
            if(okList.size()==0){
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!");
            	return this.doQuery(actionMapping,actionForm,httpServletRequest,httpServletResponse);
            }
            
            if(delList.size()>0){
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"���ֽɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!");
            }else{
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"����ɹ���");
            }
            
            form.setSbbh(tt.getSbbh());
        }
        catch (Exception ex)
        {
            form.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex, "�����⼮��������˰�·��걨��ʧ��!");
        }
        //����ɹ��������ѯҳ��
        return this.doJkswh(actionMapping, actionForm, httpServletRequest,httpServletResponse);

    }

    /**
     * ��ʼ��
         * @param actionForm  The optional ActionForm bean for this request (if any)
     */
    private void setInitInfo (ActionForm actionForm)
    {
        WjgrActionForm form = (WjgrActionForm) actionForm;
        //�����걨����
        if (form.getSbrq() == null || form.getSbrq().equals(""))
        {
            form.setSbrq(SfDateUtil.getDate());
        }
        //ȡ��˰����������
        Map skssrq = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
        form.setSkssjsrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.SKSSJSRQ)));
        form.setSkssksrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.SKSSKSRQ)));

    }

    /**
     *��ʼʱ��ʾҳ��
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doJkswh (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("=========doJkswh()=");

        WjgrActionForm form = (WjgrActionForm) actionForm;
        WjgrJkswhActionForm form1 = new WjgrJkswhActionForm();
        form1.setJsjdm(form.getJsjdm());
        form1.setSbbh(form.getSbbh());
        httpServletRequest.setAttribute("wjgrJkswhActionForm", form1);
        httpServletRequest.setAttribute("sbbh", form.getSbbh());
        return actionMapping.findForward("Jkswh");
    }

    /**
     * �õ������ʺŵ�js����
     * @param yhzh ������Ϣ
     * @return String
     */
    private String getBankJsArray (List yhzh)
    {
        StringBuffer ret = new StringBuffer();
        try
        {
            for (int i = 0; i < yhzh.size(); i++)
            {
                ZRRYHZH temp = (ZRRYHZH) yhzh.get(i);
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
            return "var bankInfo = [" +
                SfStringUtils.replaceAll(ret.toString(), "null", "");
        }
        catch (Exception ex)
        {
            return "var bankInfo=new Array();";
        }
    }

}
