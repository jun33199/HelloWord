/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ����Ϣ�Ƽ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ����Ϣ�Ƽ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import java.util.ArrayList;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhActionForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Description: �۽���ҵ����˰����˰�սɿ���</p>
 * @author wangxm
 * @version 1.1
 */
public class KjssjksAction
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
                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�۽���ҵ����˰</font></td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE, "�۽���ҵ����˰����˰�սɿ���");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP, "");

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
    	System.out.println("--------Come in KjssjksAction------------------");
        KjssjksForm form = (KjssjksForm) actionForm;
        try
        {
            UserData ud = this.getUserData(httpServletRequest);
            //��װǰ̨��ϢBO
            KjssjksBO qbo = new KjssjksBO();
            qbo.setJsjdm(form.getJsjdm());
            qbo.setBadjxh(form.getBadjxh());
            if(form.getBgbxh()==null || "".equals(form.getBgbxh()) || "null".equals(form.getBgbxh())){
            	form.setBgbxh((String)httpServletRequest.getAttribute("bgbxh"));
            	System.out.println("httpServletRequest.getAttribute----------"+form.getBgbxh());
            }
            qbo.setBgbxh(form.getBgbxh());
            //��ʼ��ҳ����Ϣ
            this.setInfo(form, httpServletRequest);
            //��װ��̨��ϢBO
            KjssjksBO hbo = new KjssjksBO();
            
            VOPackage vo = new VOPackage();
            vo.setAction(CodeConstant.SMSB_QUERYACTION);
            vo.setProcessor(CodeConstant.SBZL_KJSSJKS_PROCESSOR);
            vo.setData(qbo);
            try {
    			// ����Proxy��ִ��processor,��ȡhbo����ֵ
    			hbo = (KjssjksBO) SbzlProxy.getInstance().process(vo);
    			if(null==hbo.getSbbh() || "".equals(hbo.getSbbh())){
	    			//��hbo����form
	    			System.out.println("------------------hbo-------------"+hbo);
	    			form.setSzdm(hbo.getSzdm());
	    			form.setSzmc(hbo.getSzmc());
	    			form.setSzsmdm(hbo.getSzsmdm());
	    			form.setSzsmmc(hbo.getSzsmmc());
	    			
					form.setSkssksrq(hbo.getSkssksrq());
					form.setSkssjsrq(hbo.getSkssjsrq());
					form.setSjse(hbo.getSjse());
					form.setHtmc(hbo.getHtmc());
					form.setHtbh(hbo.getHtbh());
					form.setJsje(hbo.getJsje());
					form.setSl(hbo.getSl());
	    			// ��kjqysdsbgbForm����request,��Ϊ����������
	    			httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
	    			return actionMapping.findForward("Query");
    			}
    			else{
    				form.setSbbh(hbo.getSbbh());
    	            return this.doJkswhBySave(actionMapping, form,
                            httpServletRequest,
                            httpServletResponse);
    			}
    		} catch (Exception ex) {
    			// ϵͳ��׽�쳣�������쳣�����׳�
    			throw ExceptionUtil.getBaseException(ex);
    		}
            
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
        KjssjksForm form = (KjssjksForm) actionForm;
        KjssjksBO qbo=new KjssjksBO();
        //�õ��б�����
        String columns[] = form.getColumns();
        ArrayList list = new ArrayList();
        HashMap map = new HashMap();
        map.put("szsmdm",form.getSzsmdm() );
        map.put("szsmmc", form.getSzsmmc());
        map.put("jsje",form.getJsje());
        map.put("sjse",form.getSjse());
        map.put("skssksrq",form.getSkssksrq());
        map.put("skssjsrq",form.getSkssjsrq());
        map.put("szdm",form.getSzdm());
        map.put("szmc",form.getSzmc());
        map.put("sl",form.getSl());
        
        list.add(map);
        form.setDataList(list);


        String yhdm = form.getYhdm();
        String zh = form.getZh();

        if (! (yhdm.equals("") || zh.equals("")))
        {
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
            throw ExceptionUtil.getBaseException(ex1, "���ɽɿ���ʧ��!�޷�ȡ����˰�˻�������");
          }
          jbsj = (SWDJJBSJ)hm.get("JBSJ");
          String ssdwdm = jbsj.getSwjgzzjgdm();

          form.setJksType(CodeConstant.PRINT_YPYS);
        }
        
        //��form����bo 
        qbo.setJsjdm(form.getJsjdm());
        qbo.setDataList(form.getDataList());
        qbo.setYhdm(form.getYhdm());
        qbo.setZh(form.getZh());
        qbo.setJksType(form.getJksType());
        qbo.setNsrmc(form.getNsrmc());
        qbo.setYhmc(form.getYhmc());
        qbo.setSklxdm(form.getSklxdm());
        qbo.setSklxmc(form.getSklxmc());
        qbo.setSbrq(form.getSbrq());
        
        qbo.setBadjxh(form.getBadjxh());
        qbo.setBgbxh(form.getBgbxh());
        qbo.setSzsmmc(form.getSzsmmc());
        qbo.setSzsmdm(form.getSzsmdm());
        qbo.setSjse(form.getSjse());

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor(CodeConstant.SBZL_KJSSJKS_PROCESSOR);
        vo.setData(qbo);


        //����ͨ���ܿصõ����û���Ϣ
        vo.setUserData(ud);
        try
        {
            //�������ɽɿ�����걨���
            //form.setSbbh(this.getSbbh(jksMap));
        	KjssjksBO hbo = (KjssjksBO)ZhsbProxy.getInstance().process(vo);

        	form.setSbbh(hbo.getSbbh());
        	System.out.println("sbbh----------"+form.getSbbh());
        	// ��kjqysdsbgbForm����request,��Ϊ����������
			//httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
			// �����ɹ���ת
			//httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "���ܽɿ���ɹ���");
			actionForm=(ActionForm)form;
            //ת�Ƶ��ɿ���ά�����棬ֻ��ʾ�������ɵĽɿ���
            return this.doJkswhBySave(actionMapping, actionForm,
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
    private void setInfo (KjssjksForm form, HttpServletRequest request)
        throws
        Exception
    {
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
            /**
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
            }**/
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
    throws
    Exception
    {
        //�ɿ���ά��FORMBEAN
        ZhsbjkswhActionForm jks = new ZhsbjkswhActionForm();
        KjssjksForm form = (KjssjksForm) actionForm;
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


        ActionForward gotourl = new ActionForward("/webapp/smsb/zhsbjkswhAction.do?actionType=Query");//url���Ը��ݲ�ͬ������ָ����ͬ�õ�ַ�Ͳ�ͬ�ò���
        gotourl.setPath("/webapp/smsb/zhsbjkswhAction.do?actionType=Query&jsjdm="+form.getJsjdm()+"&nsrmc="+form.getNsrmc()+"&sbbh="+form.getSbbh()+"&presbbh=1&kjflag=1&bgbxh="+form.getBgbxh()+"&sjly="+CodeConstant.SMSB_SJLY_YQKJ);
        gotourl.setRedirect(true);
        return gotourl;

        //return actionMapping.findForward("JkswhBySave");
    }
    

}
