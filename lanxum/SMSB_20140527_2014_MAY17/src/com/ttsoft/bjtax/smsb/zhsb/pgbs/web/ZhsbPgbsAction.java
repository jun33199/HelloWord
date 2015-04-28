/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.pgbs.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
 * <p>Description: ʵ���ۺ��걨_������˰���ܣ������ɿ���¼�룬ά����</p>
 * @author zzb  20090327
 * @version 1.1
 */
public class ZhsbPgbsAction
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
                                        "��˰����˰��");
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
        //������˰form
    	ZhsbPgbsActionForm form = (ZhsbPgbsActionForm) actionForm;
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
        Debug.out("===  com.ttsoft.bjtax.smsb.zhsb.pgbs.web.ZhsbPgbsAction doQuery()");

        //������˰form
        ZhsbPgbsActionForm form = (ZhsbPgbsActionForm) actionForm;
        try
        {
            UserData ud = this.getUserData(httpServletRequest);
            //��֪����
            //��ʼ��ҳ����Ϣ
            this.setInfo(actionForm, httpServletRequest);
            //������˰form,���ظ���˰��˰Ŀ�������ɵ�ǰ̨��ʾ����ϸ�����б�
            ZhsbPgbsActionForm form1 = (ZhsbPgbsActionForm)this.getInitList(actionForm,
                ud);
            //����и�֪��������Ķ�
            if ( (form1.getGzsx() == null || form1.getGzsx().equals("0")) &&
                form1.getGzsxList().size() > 0)
            {

                //������˰,��֪����Ϊ�ջ�0ʱ�����и�֪����
                ZhsbPgbsGzsxActionForm gzsx = new ZhsbPgbsGzsxActionForm();
                gzsx.setJsjdm(form.getJsjdm());
                gzsx.setSbrq(form.getSbrq());

                //������˰
                httpServletRequest.setAttribute("zhsbPgbsGzsxActionForm", gzsx);
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

        UserData ud = this.getUserData(httpServletRequest);
        //������˰form
        ZhsbPgbsActionForm form= (ZhsbPgbsActionForm) actionForm;
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

        //������˰
        vo.setProcessor(CodeConstant.ZHSB_PGBS_SBLR_PROCESSOR);

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
        	
            //start modifying by qianchao 2005.11.2
            //Map jksMap = ()
            //�������ɽɿ�����걨���
            //form.setSbbh(this.getSbbh(jksMap));
            Object retobj = ZhsbProxy.getInstance().process(vo);
            
            ZhsbPgbsActionForm rtForm = (ZhsbPgbsActionForm)retobj;
            //end modifying by qianchao 2005.11.2

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
            
            if(okList.size()==0){
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!");
            	return this.doQuery(actionMapping,
                        actionForm,
                        httpServletRequest,
                        httpServletResponse);
            }
            
            if(delList.size()>0){
            	httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"���ֽɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!");
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
    	//������˰form
        ZhsbPgbsActionForm form = (ZhsbPgbsActionForm) actionForm;
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
        //������˰,�ɿ���ά��FORMBEAN
        ZhsbPgbsjkswhActionForm jks = new ZhsbPgbsjkswhActionForm();

        //������˰form
        ZhsbPgbsActionForm form = (ZhsbPgbsActionForm) actionForm;

        //���ü��������
        jks.setJsjdm(form.getJsjdm());
        //���õ�λ����
        jks.setNsrmc(form.getNsrmc());
        //������˰,��FORMBEAN����REQUEST
        httpServletRequest.setAttribute("zhsbPgbsjkswhActionForm", jks);
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

        //������˰
        vo.setProcessor(CodeConstant.ZHSB_PGBS_SBLR_PROCESSOR);

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
    	//������˰,�ɿ���ά��FORMBEAN
        ZhsbPgbsjkswhActionForm jks = new ZhsbPgbsjkswhActionForm();

        //������˰form
        ZhsbPgbsActionForm form = (ZhsbPgbsActionForm) actionForm;

        //���ü��������
        jks.setJsjdm(form.getJsjdm());
        //���õ�λ����
        jks.setNsrmc(form.getNsrmc());
        //�걨���
        jks.setSbbh(form.getSbbh());
        //Ԥ�����걨���
        jks.setPresbbh("1");
        //������˰,��FORMBEAN����REQUEST
        httpServletRequest.setAttribute("zhsbPgbsjkswhActionForm", jks);
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
}
