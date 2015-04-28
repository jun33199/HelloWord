/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.SzsmSyjdUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ���ۺ��걨���ܣ������ɿ���¼�룬ά����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */
public class ZhsbAction
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨�ɿ�</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�ۺ��걨");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/zhsb/zhsb-000.htm");

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
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        form.setBankJsArray("var bankInfo=new Array();");
        form.setSbrq(SfDateUtil.getDate());
        return (new ActionForward(actionMapping.getInput()));
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
        Debug.out("===  com.ttsoft.bjtax.smsb.zhsb.web.ZhsbAction doQuery()");

        ZhsbActionForm form = (ZhsbActionForm) actionForm;

        try
        {
            UserData ud = this.getUserData(httpServletRequest);
            //��֪����
            //��ʼ��ҳ����Ϣ
            this.setInfo(actionForm, httpServletRequest);
            //���ظ���˰��˰Ŀ�������ɵ�ǰ̨��ʾ����ϸ�����б�
            ZhsbActionForm form1 = (ZhsbActionForm)this.getInitList(actionForm,
                ud);
            //����и�֪��������Ķ�
            if ( (form1.getGzsx() == null || form1.getGzsx().equals("0")) &&
                form1.getGzsxList().size() > 0)
            {

                //��֪����Ϊ�ջ�0ʱ�����и�֪����
                ZhsbGzsxActionForm gzsx = new ZhsbGzsxActionForm();
                gzsx.setJsjdm(form.getJsjdm());
                gzsx.setSbrq(form.getSbrq());

                httpServletRequest.setAttribute("zhsbGzsxActionForm", gzsx);
                return actionMapping.findForward("Gzsx");
            }

            form.setScriptStr(form1.getScriptStr());
            //����˰��������������
            form.setSkssrqArr(this.getSkssrq(SfDateUtil.getDate(form.getSbrq())));

            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {

            form.reset(actionMapping, httpServletRequest);

            throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
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

        StringBuffer returnString = new StringBuffer();
        UserData ud = this.getUserData(httpServletRequest);
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        //�õ��б�����
        String columns[] = form.getColumns();
        form.setDataList(this.getValuesToList(httpServletRequest, columns));

        //start add by qianchao 2005.11.1
        String yhdm = form.getYhdm();
        String zh = form.getZh();
        
        SWDJJBSJ jbsj = null;
        HashMap hm = null;
        try
        {
          hm = InterfaceDj.getDjInfo(form.getJsjdm(),ud);
        }
        catch (Exception ex1)
        {
          ex1.printStackTrace();
          form.reset(actionMapping, httpServletRequest);
          throw ExceptionUtil.getBaseException(ex1, "�����ۺ��걨ʧ��!�޷�ȡ����˰�˻�������");
        }
        jbsj = (SWDJJBSJ)hm.get("JBSJ");
        String ssdwdm = jbsj.getSwjgzzjgdm();
        
        if (! (yhdm.equals("") || zh.equals("")))
        {

          if (LWUtil.isZsjgLW(httpServletRequest.getSession().getServletContext(),
                              ssdwdm)) {
              form.setJksType(CodeConstant.PRINT_YPDS_KM);
          }
          else
          {
            form.setJksType(CodeConstant.PRINT_YPYS);
          }
          
        }else{
        	form.setJksType(CodeConstant.PRINT_YPYS);
        }
        //end add by qianchao 2005.11.1
        boolean isLW = LWUtil.isLW(httpServletRequest.getSession().getServletContext(), ssdwdm, yhdm);

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
        vo.setData(form);
        
        //����ͨ���ܿصõ����û���Ϣ
        vo.setUserData(ud);
        try
        {

        	 String checkstr="";
             try
             {
               checkstr=SzsmSyjdUtil.getSzsmcljeBySyjdlx(form.getJsjdm(),form.getDataList());
             }catch (Exception ex1)
             {
             	 ex1.printStackTrace();
             	 throw ExceptionUtil.getBaseException(ex1, "�ж�˰��˰Ŀ����ͼ������ͳ���");
             }
             
             
             if(checkstr.length()>0){
             	throw new ApplicationException(checkstr);
             }
        	
             //���벻ͬ������Ϣ
             getJmList(form ,ud ,returnString);
               
            //start modifying by qianchao 2005.11.2
            //Map jksMap = ()
            //�������ɽɿ�����걨���
            //form.setSbbh(this.getSbbh(jksMap));
            Object retobj = ZhsbProxy.getInstance().process(vo);
            ZhsbActionForm rtForm = (ZhsbActionForm)retobj;
            //end modifying by qianchao 2005.11.2
            
            //������Ϣ����
            operate(form ,ud );
            
            List dataList = rtForm.getDataList();
            List delList = new ArrayList();
            List okList = new ArrayList();
            
            if(dataList.size()>0){
            	
            	//һƱ��˰
            	if(CodeConstant.PRINT_YPDS_KM == form.getJksType()){
                	if(isLW){
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
                   		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
                			List infoList = (List) iterator.next();
                			
                			List _infoList  = new ArrayList();
           	
    	                    for (int i = 0; i < infoList.size(); i++) {
    	
    	                    	DeclareInfor declareInfor = (DeclareInfor)infoList.get(i);
    	                    	
    	                    	BigDecimal total = new BigDecimal(0.00);
    	                    	
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
    	                        	_infoList.add(declareInfor);
    	                        }  
    	                    }
    	                    if(_infoList.size()>0){
    	                    	okList.add(_infoList);
    	                    } 
                		}
                	}
            	}
            	//һƱһ˰
            	if(CodeConstant.PRINT_YPYS == form.getJksType()){
            		if(isLW){
            			BigDecimal total = new BigDecimal(0.00);
            			
	                    for (int i = 0; i < dataList.size(); i++) {	                    	
	                    	DeclareInfor declareInfor = (DeclareInfor)dataList.get(i);
	                    	
	                    	Sbjkzb sbjkzb = declareInfor.getSbjkzb();
	                    	List jkmxList = declareInfor.getSbjkmxInfo();
	                    	
	                    	for (int j = 0; j < jkmxList.size(); j++) {
	                    		Sbjkmx sbjkmx = (Sbjkmx)jkmxList.get(j);
	                    		BigDecimal sjse = sbjkmx.getSjse().setScale(2,BigDecimal.ROUND_HALF_UP);
	                    		total = total.add(sjse);
	                    	}
	                    	
	                    	delList.add(sbjkzb.getJkpzh());
	                    	okList.add(declareInfor);

	                    }
                        if(total.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()<1){
                        	okList =  new ArrayList();
                        }else{
                        	delList = new ArrayList();
                        }
            		}else{
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
            
            if(okList.size()==0 && dataList.size()>0){
            	returnString.append("�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!<br/>");
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,returnString.toString());
            	return this.doQuery(actionMapping,
                        actionForm,
                        httpServletRequest,
                        httpServletResponse);
            }
            
            if(delList.size()>0 && dataList.size()>0){
            	returnString.append("���ֽɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!<br/>");	
            }
            
            if(!"".equals(returnString.toString()) && !(null==returnString.toString()))
            {
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,returnString.toString());
            }

            //ת�Ƶ��ɿ���ά�����棬ֻ��ʾ�������ɵĽɿ���
            return this.doJkswhBySave(actionMapping, rtForm,
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
    private void setInfo (ActionForm actionForm, HttpServletRequest request)
        throws
        Exception
    {
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        try
        {
            //��õ�ǰ��userData����
            UserData ud = this.getUserData(request);
            /* start added by huxiaofeng 2005.8.16*/
            //HashMap djMap = InterfaceDj.getDjInfo(form.getJsjdm(), ud);
            HashMap djMap = InterfaceDj.getDjInfo_New(form.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.16*/

            //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ

            SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
            //��λ����
            form.setNsrmc(jbsj.getNsrmc());
            //������˰��״̬
            form.setNsrzt(jbsj.getNsrzt());
            //ͨ���Ǽǽӿ�ȡ�������ʻ���Ϣ
            List bankList = (List) djMap.get("YHZH"); ;
            //System.out.println("bankList.size()"+bankList.size());
            /* start added by huxiaofeng 2005.8.16*/
            if(bankList==null){
              bankList=new ArrayList(0);
            }
            /* end added by huxiaofeng 2005.8.16*/
            form.setBankList(bankList);
            //������������
            form.setBankJsArray(this.getBankJsArray(bankList));
            //�걨����
            if (form.getSbrq() == null || form.getSbrq().trim().equals(""))
            {
                //�걨����Ϊ�գ������������걨����
                form.setSbrq(SfDateUtil.getDate());
            }
            //�����Ƿ���ʵ����˰��ʾ������Ǽ�ע�����Ͷ�Ӧ�������ʷ������nwzfldmΪ���򣲵�
            //ʱ����ʾ����˰
            //�õ��Ǽ�ע�����ʹ��������

            SfHashList list = (SfHashList) CodeManager.getCodeList(
                "ZHSB_DJZCLX",
                CodeConstants.CODE_MAP_MAPLIST);
            //�õ������������صĵǼ�ע������
            String djzclxdm = jbsj.getDjzclxdm();
            //�õ��Ǽ�ע�����Ͷ�Ӧ�������ʷ������
            for (int i = 0; i < list.size(); i++)
            {

                if (djzclxdm.equals(list.get(i, "djzclxdm")) &&
                    (list.get(i, "nwzfldm").equals("1") ||
                     list.get(i, "nwzfldm").equals("2")))
                {
                    //�����ʷ������Ϊ1��2
                    form.setIsadditons(false);
                    //���������ʷ����ʾ
                    form.setWz(true);
                }
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);

        }

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
        ZhsbjkswhActionForm jks = new ZhsbjkswhActionForm();
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        //���ü��������
        jks.setJsjdm(form.getJsjdm());
        //���õ�λ����
        jks.setNsrmc(form.getNsrmc());
        //��FORMBEAN����REQUEST
        httpServletRequest.setAttribute("zhsbjkswhActionForm", jks);
        return actionMapping.findForward("Jkswh");
    }

    /**
     * ȡ�ð���˰��˰Ŀlist��Ӫҵ˰����˰list��list
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param ud  ����Ա������Ϣ
     * @throws Exception
     * @return    ActionForward
     */
    private ActionForm getInitList (ActionForm actionForm, UserData ud)
        throws
        Exception
    {
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_ZHSB_INITLIST);
        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
        vo.setData(actionForm);
        //����userdata
        vo.setUserData(ud);
        try
        {
            return (ActionForm) ZhsbProxy.getInstance().process(vo);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
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
     * ���ɿ���ά��ҳ��
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @return    ActionForward
     */
    public ActionForward doJkswhBySave (ActionMapping actionMapping,
                                        ActionForm actionForm,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse)
    {
        //�ɿ���ά��FORMBEAN
        ZhsbjkswhActionForm jks = new ZhsbjkswhActionForm();
        ZhsbActionForm form = (ZhsbActionForm) actionForm;
        //���ü��������
        jks.setJsjdm(form.getJsjdm());
        //���õ�λ����
        jks.setNsrmc(form.getNsrmc());
        //�걨���
        jks.setSbbh(form.getSbbh());
        //Ԥ�����걨���
        jks.setPresbbh("1");
        //��FORMBEAN����REQUEST
        httpServletRequest.setAttribute("zhsbjkswhActionForm", jks);
        return actionMapping.findForward("JkswhBySave");
    }

    /**
     * �õ����ɽɿ�����걨���
     * @param  jks �ɿ�����Ϣ
     * @return  �걨���
     */
    /* deleted by qianchao 2005.11.2
    private String getSbbh (Map jks)
    {
        String sbbh = "";
        //���ɽɿ��������
        String jksType = (String) jks.get(CodeConstant.ZHSB_JKS_TYPE);
        //���ɽɿ�����б�
        List jksList = (List) jks.get(CodeConstant.ZHSB_JKS_LIST);
        //
        if (jksType != null && jksType.equals("T"))
        {
            //һƱ��˰
            //�õ���һ��Ʊ���б�
            List temp = (List) jksList.get(0);
            //�õ�����һ��Ʊ�ĵ�һ���ɿ���
            DeclareInfor jksInfo = (DeclareInfor) temp.get(0);
            //�걨�ɿ�����
            Sbjkzb sbjkzb = jksInfo.getSbjkzb();
            sbbh = sbjkzb.getSbbh();
        }
        else
        {
            //һƱһ˰
            DeclareInfor jksInfo = (DeclareInfor) jksList.get(0);
            //�걨�ɿ�����
            Sbjkzb sbjkzb = jksInfo.getSbjkzb();
            sbbh = sbjkzb.getSbbh();

        }
        //
        return sbbh;
    }
*/

    /**
     * �õ�ǰ̨��ת���걨����ʹ�õ�˰����������
     * @param  rq �걨����
     * @return  js����['��','','����','��','']
     */
    private String getSkssrq (Date rq)
    {
        StringBuffer ret = new StringBuffer();
        Map m = Skssrq.monthSkssrq(rq);
        //�·ݵ�˰����������
        ret.append("var skssrqArr = ['" +
                   SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSKSRQ))
                   + "','" +
                   SfDateUtil.getDate( (Timestamp) m.get(Skssrq.SKSSJSRQ))
                   + "',");
        //���ȵ�˰����������
        Map q = Skssrq.quarterSkssrq(TinyTools.addMonth( -1, rq));
        ret.append("'" + SfDateUtil.getDate( (Timestamp) q.get(Skssrq.SKSSKSRQ)) +
                   "','"
                   + SfDateUtil.getDate( (Timestamp) q.get(Skssrq.SKSSJSRQ)) +
                   "',");
        //��
        Map y = Skssrq.yearSkssrq(rq);
        ret.append("'" + SfDateUtil.getDate( (Timestamp) y.get(Skssrq.SKSSKSRQ)) +
                   "','"
                   + SfDateUtil.getDate( (Timestamp) y.get(Skssrq.SKSSJSRQ)) +
                   "',");
        //��������
        Map qq = Skssrq.otherSkssrq(rq);
        ret.append("'" + SfDateUtil.getDate( (Timestamp) qq.get(Skssrq.SKSSKSRQ)) +
                   "','"
                   + SfDateUtil.getDate( (Timestamp) qq.get(Skssrq.SKSSJSRQ)) +
                   "']");
        return ret.toString();

    }

    /**
     * @Description: TODO �����ۺ��걨�п��Լ������Ŀ
     * @param form
     * @return
     * add by lijn 20141013
     * @throws Exception 
     */
    private Map getJmList(ZhsbActionForm form ,UserData ud ,StringBuffer sb) throws Exception
    {
    	List dateList = form.getDataList();
    	Map jmMap = new HashMap();
    	List jmXwqyList = new ArrayList();
    	
    	//ֻ�޸������ɿ���Ĵ����
    	if("400".equals(form.getSklxdm()))
    	{
    		return jmMap;
    	}
    	
    	/*------С΢��ҵӪҵ˰����:�Ƴ�Դ���ݣ�����ר�ŵ�list����---------*/
    	boolean hasYysXwqy = false;					//�����Ƴ�����˰
    		
    	/*-------------------------------------�±�-----------*/
    	List jmXwqyYysYdList = new ArrayList();		//�¶�Ӫҵ˰С΢��ҵ
    	double sumYdJsJeXwqyYys = 0.0;
    	Iterator iteratorYd = dateList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorYd.hasNext())
    	{
    		Map mxMap = (Map)iteratorYd.next();
    		if(mxMap.get("szsmdm")==null)
    		{continue;}
    		String szsmdm_temp = mxMap.get("szsmdm").toString();
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{	
    			//�Ƿ��ǵ���˰��
    			form.setJmMap(mxMap);
    			form.setCheck_jmLx(0);
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(CodeConstant.ZHSB_JM01_CHECKCURRENT_ACTION);
    		     vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(form);
        		Boolean isYysXwjm_dq = (Boolean)ZhsbProxy.getInstance().process(vo);
        			
        		if(isYysXwjm_dq.booleanValue())
        		{
        			jmXwqyYysYdList.add(mxMap);
        			iteratorYd.remove();
        			sumYdJsJeXwqyYys+=Double.parseDouble((String)mxMap.get("jsje"));
        		}
    		}
    	}
    	
    	//�Ƿ�3���˰���
    	if(sumYdJsJeXwqyYys>30000 || sumYdJsJeXwqyYys == 0){
    		dateList.addAll(jmXwqyYysYdList);
    	}else{			
    		jmXwqyList.addAll(jmXwqyYysYdList);
    		hasYysXwqy = true;
    	}
    	
    	/*----------------------------����-------------------*/
    	List jmXwqyYysJdList = new ArrayList();		//����Ӫҵ˰С΢��ҵ
    	double sumJdJsJeXwqyYys = 0.0;
    	Iterator iteratorJd = dateList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorJd.hasNext())
    	{
    		Map mxMap = (Map)iteratorJd.next();
    		if(mxMap.get("szsmdm")==null)
    		{continue;}
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("02".equals(mxMap.get("szsmdm").toString().substring(0, 2)) && !"020091".equals(mxMap.get("szsmdm").toString()) && !"020092".equals(mxMap.get("szsmdm").toString()))
    		{	
    			//�Ƿ��ǵ���˰��
    			form.setJmMap(mxMap);
    			form.setCheck_jmLx(1);
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(CodeConstant.ZHSB_JM01_CHECKCURRENT_ACTION);
    		     vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(form);
        		Boolean isYysXwjm_dq = (Boolean)ZhsbProxy.getInstance().process(vo);
        			
        		if(isYysXwjm_dq.booleanValue())
        		{
        			jmXwqyYysJdList.add(mxMap);
        			iteratorJd.remove();
        			sumJdJsJeXwqyYys+=Double.parseDouble((String)mxMap.get("jsje"));
        		}
    		}
    	}
    	
    	//�Ƿ�9���˰���
    	if(sumJdJsJeXwqyYys>90000 ||  sumJdJsJeXwqyYys == 0){
    		dateList.addAll(jmXwqyYysJdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysJdList);
    		hasYysXwqy = true;
    	}
    	
    	/*---------------------------------------------����Ӫҵ˰������ͬʱ���⸽�ӷ�----------*/
    	if(hasYysXwqy == true)
    	{
    		sb.append("��Ӫҵ˰��˰������Ӫҵ�����3��Ԫ������Ϊ9��Ԫ,������������˰�ˣ���������Ӫҵ˰������˰�˵������걨ģ����м���˰�걨��<br/>");
    		
    		Iterator iteratorfjf = dateList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
        	while(iteratorfjf.hasNext())
        	{
        		Map mxMap = (Map)iteratorfjf.next();
        		if("540010".equals(mxMap.get("szsmdm")) || "510010".equals(mxMap.get("szsmdm")) || "100010".equals(mxMap.get("szsmdm")))
        		{
        			iteratorfjf.remove();
        			jmXwqyList.add(mxMap);
        		}	
        	}
    	}
    	
    	
    	/*---------------------------------------------------------�����Ļ���ҵ�����--------*/
    	List jmXwqyWhsyjsfYdList = new ArrayList();		//�Ļ���ҵ�����
    	double sumYdJsJeXwqyWhsyjsf = 0.0;				//�Ļ���ҵ����ѽ��
    	Iterator iteratorWhsyjsf = dateList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorWhsyjsf.hasNext())
    	{
    		Map mxMap = (Map)iteratorWhsyjsf.next();
    		if(mxMap.get("szsmdm")==null)
    		{continue;}
    		
    		//20150101-20171231	
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		Date date_skssrqq = sdf.parse((String) mxMap.get("skssksrq"));		//˰������������
    		Date date_skssrqz = sdf.parse((String) mxMap.get("skssjsrq"));		//˰����������ֹ
    		Date start = sdf.parse("20150101");
    		Date end = sdf.parse("20180101");
    		if(date_skssrqq.before(start) || date_skssrqz.before(start) || date_skssrqz.after(end)||date_skssrqq.after(end)){
    			continue;
    		}
    		
    		String szsmdm_temp = mxMap.get("szsmdm").toString();
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("535610".equals(szsmdm_temp))
    		{	
    			//�Ƿ��ǵ���˰��
    			form.setJmMap(mxMap);
    			form.setCheck_jmLx(2);
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(CodeConstant.ZHSB_JM01_CHECKCURRENT_ACTION);
    		     vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(form);
        		Boolean isWhsyjsfXwjm_dq = (Boolean)ZhsbProxy.getInstance().process(vo);
        			
        		if(isWhsyjsfXwjm_dq.booleanValue())
        		{
        			jmXwqyWhsyjsfYdList.add(mxMap);
        			iteratorWhsyjsf.remove();
        			sumYdJsJeXwqyWhsyjsf+=Double.parseDouble((String)mxMap.get("jsje"));
        		}
    		}
    	}
    	
    	//�Ƿ�3���˰���
    	if(sumYdJsJeXwqyWhsyjsf<=30000 &&  sumYdJsJeXwqyWhsyjsf > 0 && sumYdJsJeXwqyYys<=30000){
    		
    		jmXwqyList.addAll(jmXwqyWhsyjsfYdList);
    		sb.append("�԰�����˰����Ӫҵ�����3��Ԫ����3��Ԫ���Ľ��������ˣ������Ļ���ҵ����ѡ�����������˵������걨ģ����м���˰�걨��<br/>");
    	}else{
    		dateList.addAll(jmXwqyWhsyjsfYdList);
    	}
    	jmMap.put(CodeConstant.ZHSB_JM01, jmXwqyList);
    	
   
    	
    	
    	
    	//һԪ�����ں�����ʵ��
    	/*--*/
    	
    	form.setJmDateMap(jmMap);
    	return jmMap;
    }
    
    /**
     * @Description: TODO ��ͬ�ļ���Ҫ��������
     * @param jMap
     * @param vo
     * @param form
     * @throws BaseException 
     */
    private void operate(ZhsbActionForm form ,UserData ud ) throws BaseException
    {
    	Map jmMap  = form.getJmDateMap();
    	 
    	//С΢��ҵӪҵ˰���⣺����ɿ����ݵ��Ǳ��浽his��
    	if(jmMap.get(CodeConstant.ZHSB_JM01) != null && ((List)jmMap.get(CodeConstant.ZHSB_JM01)).size()>0)
    	{
    		try {
				//List ls1 = (List) jmMap.get(CodeConstant.ZHSB_JM01);
    			VOPackage vo = new VOPackage();
    			vo.setAction(CodeConstant.ZHSB_JM01_ACTION);
    	        vo.setProcessor(CodeConstant.ZHSB_SBLR_PROCESSOR);
    	        vo.setUserData(ud);
    	        vo.setData(form);
				
    	        ZhsbProxy.getInstance().process(vo);
				
			} catch (Exception e) {
				throw ExceptionUtil.getBaseException(e);
			}
			
			
    	}
    	
    	
    }
    
}
