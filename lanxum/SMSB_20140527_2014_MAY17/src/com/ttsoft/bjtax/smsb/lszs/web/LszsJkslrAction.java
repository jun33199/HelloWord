/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.LWHelper;
import com.ttsoft.bjtax.smsb.zhsb.pgbs.web.ZhsbPgbsActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ���սɿ���¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsJkslrAction
    extends SmsbAction
{
    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, form, request, response);
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;" +
                             "<font color=\"#7C9AAB\">�걨����</font>&gt;" +
                             "<font color=\"#7C9AAB\">��ɢ˰����</font>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "�ɿ���¼��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/lszs/jkslr-000.htm");
        try
        {
            LszsJkslrForm pf = (LszsJkslrForm) form;
            //���ü��������
            UserData ud = this.getUserData(request);
            pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //�������ؼ��������
            //���ó�ʼ�������Ĳ���
            ZhsbActionForm temp = new ZhsbActionForm();
            //���ü��������
            temp.setJsjdm(pf.getJsjdm());
            //�����걨����
            temp.setSbrq(SfDateUtil.getDate());
            ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);
            //����ǰ̨��ʾ��js����
            pf.setScriptStr(zhsb.getScriptStr());

        }
        catch (Exception ex)
        {

        }

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
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {

        LszsJkslrForm pf = (LszsJkslrForm) form;

        try
        {
            //���Ԥװ�ص���Ϣ
            //��õ�ǰ��userData����
            //���ü��������
            UserData ud = this.getUserData(request);
            pf.reset(mapping, request);
            if (ud != null)
            {
                pf.setLrr(ud.getYhid());
                pf.setJsjdm(InterfaceDj.getZrrJsjdm(ud)); //�������ؼ�������룬��ʱд����
                pf.setLrr(ud.getYhid()); //��õ�ǰ��½���û�id����Ϊ¼����id
            }
            //����Ż�ñ����ļ��������
            //pf.se
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_SHOWACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_JKSLR_PROCESSOR);
            //����processor
            pf = (LszsJkslrForm) ZhsbProxy.getInstance().process(vo);
//      //���ó�ʼ�������Ĳ���
//      ZhsbActionForm temp = new ZhsbActionForm();
//      //���ü��������
//      temp.setJsjdm(pf.getJsjdm());
//      ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);
//      //����ǰ̨��ʾ��js����
//      pf.setScriptStr(zhsb.getScriptStr());
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Show");
    }

    /**
     * ��ѯ
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doQuery (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {

        LszsJkslrForm pf = (LszsJkslrForm) form;
        try
        {
            //UserData����
            UserData ud = this.getUserData(request);
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setData(pf);
            vo.setUserData(ud);
            vo.setProcessor(CodeConstant.LSZS_JKSLR_PROCESSOR);
            //����processor
            pf = (LszsJkslrForm) ZhsbProxy.getInstance().process(vo);
            //���ó�ʼ����js����
            //���ó�ʼ�������Ĳ���
            ZhsbActionForm temp = new ZhsbActionForm();
            //���ü��������
            temp.setJsjdm(pf.getJsjdm());
            //�����걨����
            temp.setSbrq(SfDateUtil.getDate());
            ZhsbActionForm zhsb = (ZhsbActionForm)this.getInitList(temp);
            //����ǰ̨��ʾ��js����
            pf.setScriptStr(zhsb.getScriptStr());
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Query");
    }

    /**
     * ����
     * modified by qianchao 2005.10.26
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        String forwardFlag = "Save";
        LszsJkslrForm pf = (LszsJkslrForm) form;

        //��ֹrefresh
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        //UserData����
        UserData ud = this.getUserData(request);

        String columns[] = pf.getColumns();
        pf.setDataList(this.getValuesToList(request, columns));
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor(CodeConstant.LSZS_JKSLR_PROCESSOR);


        //start add by qianchao 2005.10.25
        System.out.println("�������״̬���趨˰Ʊ���͡�");
        System.out.println("pf.getJsjdm()=" + pf.getJsjdm());
        HashMap datamap = new HashMap();
        boolean lwstate = LWHelper.getLWState(request,pf.getJsjdm());
        if (lwstate)
        {
          datamap.put(CodeConstant.MAP_VO_YPYSorYPDS, new Integer(CodeConstant.PRINT_YPDS_KM));
        }
        else
        {
          datamap.put(CodeConstant.MAP_VO_YPYSorYPDS, new Integer(CodeConstant.PRINT_YPYS));
        }
        datamap.put(CodeConstant.MAP_VO_FORM,pf);
        vo.setData(datamap);
        //end add by qianchao 2005.10.25


        //����ͨ���ܿصõ����û���Ϣ
        vo.setUserData(ud);
        try
        {
            Object tt = ZhsbProxy.getInstance().process(vo);
            //end modifying by qianchao 2005.11.2
            
            LszsJkslrForm rtForm = (LszsJkslrForm)tt;
            
            List dataList = rtForm.getDataList();
            List delList = new ArrayList();
            List okList = new ArrayList();
            
            if(dataList.size()>0){
            	if(lwstate){
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
	            map.put("jsjdm", rtForm.getJsjdm());
	            map.put("jkpzhList", delList);
	            vo.setData(map);
	            ZhsbProxy.getInstance().process(vo);
	            //��������ʣ������
	            rtForm.setDataList(okList);
            }
            
            if(okList.size()==0){
            	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!");
            	return this.doQuery(mapping,form,request,response);
            }
            
            if(delList.size()>0){
            	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"���ֽɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!");
            }else{
            	request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"����ɹ���");
            }
            
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            request.setAttribute(mapping.getAttribute(), pf);
            pf.reset(mapping, request);
            
            /********************Modified by lufeng 2003-11-11********************/
            //����ɹ�����б��ӡҳ��
            LszsCxjksForm wszPF = new LszsCxjksForm();
            wszPF.setActionType("Show");
            request.setAttribute(mapping.getAttribute(), wszPF);

            forwardFlag = "Dismiss";
            //pf.reset(mapping, request);
        }
        catch (Exception ex)
        {
            //return (new ActionForward(mapping.getInput()));
            forwardFlag = "Save";
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward(forwardFlag);
    }

    /**
     * ת������ҳ��
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return the element previously at the specified position.
     * @exception BaseException
     */
    public ActionForward doDismiss (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {
        LszsCxjksForm pf = new LszsCxjksForm();
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            pf.setActionType("Show");
            request.setAttribute("lszsCxjksForm", pf);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Dismiss");
    }

    /**
     * ȡ�ð���˰��˰Ŀlist��Ӫҵ˰����˰list��list
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @return the element previously at the specified position.
     * @exception Exception
     */
    private ActionForm getInitList (ActionForm actionForm)
        throws Exception
    {
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
        //�����ۺ��걨��processorȡ��ǰ̨��ʾ�õ�js����
        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
        vo.setData(actionForm);
        try
        {
            return (ActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
        }
    }

}
