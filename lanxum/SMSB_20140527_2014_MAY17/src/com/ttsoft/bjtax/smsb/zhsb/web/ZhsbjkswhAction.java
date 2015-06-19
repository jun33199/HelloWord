/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TranslateHelper;
import com.ttsoft.bjtax.smsb.print.web.JksqdPrintForm;
import com.ttsoft.bjtax.smsb.util.Debug;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ���ۺ��걨���ܣ������ɿ���¼�룬ά����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbjkswhAction
    extends SmsbAction
{
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
                                        "help/smsb/zhsb/zhsbjkswh-000.htm");
    }

    /**
     * ��ѯ����������Ӧ��δ���ɿ���
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        Debug.out("=====com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhAction == doQuery()");

        //��õ�ǰ��userData����
        UserData ud = this.getUserData(httpServletRequest);
        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_QUERYACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbjkswhProcessor");
        vo.setData(form);
        vo.setUserData(ud);
        try
        {
            form = (ZhsbjkswhActionForm) ZhsbProxy.getInstance().process(vo);
            //���淵��ֵ--------Shi Yanfeng 20031029-------

            //start add by qianchao 2005.10.27
            List ar = TranslateHelper.splitMAP(form.getDataList(),httpServletRequest);

            Debug.out("((List)ar.get(0)).size()=" + ((List)ar.get(0)).size());
            Debug.out("((List)ar.get(1)).size()=" + ((List)ar.get(1)).size());

            form.setDataList((List)ar.get(0));
            form.setNlwDataList((List)ar.get(1));
            //end add by qianchao 2005.10.27


            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�����Ϣʧ��!");
        }
    }

    /**
     * �쿴һƱһ˰�ɿ�����Ϣ
     * modified by qianchao 2005.11.2
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpys (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;
        ZhsbjksypysActionForm form1 = new ZhsbjksypysActionForm();
        form1.setJkpzh(form.getJkpzhStr());
        form1.setJsjdm(form.getJsjdm());
        form1.setSjly(form.getKjflag());
        form1.setForward("Back");
        //�����걨��ű�־
        httpServletRequest.setAttribute("zhsbjksypysActionForm", form1);
        return actionMapping.findForward("Ypys");
    }

    /**
     * �쿴һƱ��˰�ɿ�����Ϣ
     * modified by qianchao 2005.11.2
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */

    public ActionForward doYpds (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
      /*
        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;

        ZhsbjksypdsActionForm form1 = new ZhsbjksypdsActionForm();
        form1.setSbbh(form.getSbbh());
        form1.setJsjdm(form.getJsjdm());
        form1.setForward("Back");

        httpServletRequest.setAttribute("zhsbjksypdsActionForm", form1);
        return actionMapping.findForward("Ypds");
       */
      ZhsbjkswhActionForm pf1 = (ZhsbjkswhActionForm) actionForm;
      JksqdPrintForm pf = new JksqdPrintForm();

      Debug.out("pf1.getJsjdm()=" + pf1.getJsjdm());
      Debug.out("pf1.getSbbh()=" + pf1.getSbbh());

      pf.setH_jsjdm(pf1.getJsjdm());
      pf.setH_sbbh(pf1.getSbbh());
      pf.setJsjdm(pf1.getJsjdm());
      pf.setSbbh(pf1.getSbbh());
      pf.setKjflag(pf1.getKjflag());
      pf.setHeadSjly(CodeConstant.SMSB_SJLY_SBLR); //������Դ
      if("1".equals(pf1.getKjflag())){//����ǿ۽�����˰������Դ��36
    	  pf.setHeadSjly(CodeConstant.SMSB_SJLY_YQKJ); //������Դ
      }
      pf.setActionType("Show");
      httpServletRequest.setAttribute("jksqdPrintForm", pf);
      return actionMapping.findForward("PrintSQD");

    }

    /**
     * �����ɿ���
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doCx (ActionMapping actionMapping,
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

        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;

        form.setCxStr("");
        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPDATEACTION);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zhsb.processor.ZhsbjkswhProcessor");
        vo.setData(form);
        //�ܿ���Ϣ
        vo.setUserData(this.getUserData(httpServletRequest));
        try
        {
            form = (ZhsbjkswhActionForm) ZhsbProxy.getInstance().process(vo);

            //start add by qianchao 2005.11.3
            //form.setCxStr(this.getWarStr(form.getCoList()));
            form.setSbbh("");
            httpServletRequest.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "�����ɹ���");
            //end add by qianchao 2005.11.3
            if("1".equals(form.getKjflag())){
            	form.setSjly(CodeConstant.SMSB_SJLY_YQKJ);
            }
            
            //���淵��ֵ--------Shi Yanfeng 20031029-------
            httpServletRequest.setAttribute(actionMapping.getAttribute(), form);
            return actionMapping.findForward("Cx");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
        }
    }

    /**
     * �����б�õ���ʾ��Ϣ
     * @param list �����ϸ�б�
     * @return String ��ʾ��Ϣ
     */
    private String getWarStr (List list)
    {
        String ret = "";
        String sf = "����˰";
        Map map = new HashMap();
        for (int i = 0; i < list.size(); i++)
        {
            Sbjkmx temp = (Sbjkmx) list.get(i);
            //����ʾ�ظ��Ľɿ���
            if (map.get(temp.getJkpzh().substring(0, 15)) != null)
            {
                continue;
            }
            map.put(temp.getJkpzh().substring(0, 15), temp);

            if (temp.getJkpzh().substring(15, 16).equals("0"))
            {
                //һƱһ˰��16λƱ��
                ret = ret + "<li>" + temp.getJkpzh();
            }
            else
            {
                //һƱ��˰��15λƱ��
                ret = ret + "<li>" + temp.getJkpzh().substring(0, 15);

            }
            //�ж�����˰��ػ��Ǹ�˰���
            String szsm = CodeUtils.getCodeMapValue("SZSMYFJS",
                "szsmdm", temp.getSzsmdm(),
                "fjsszsmdm");
            if (szsm != null && !szsm.equals(""))
            {
                sf = "��˰";
            }
        }
        if (!ret.equals(""))
        {
            ret = "<font color=red>�����漰��" + sf + "�ɿ���</font>" + ret;

        }
        return ret;
    }

    /**
     * �����ۺ��걨��ҳ��
     * @param actionMapping  The ActionMapping used to select this instance
         * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return ActionForward
     */
    public ActionForward doBack (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {
        ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) actionForm;

        Debug.out("=====com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhAction==in doBack()");
        //��������ԴΪ�۽�����˰,��ת���ɿ�����˰�����¼��ҳ��
        if(form.getKjflag().equals("1")){
        	ActionForward gotourl = new ActionForward("/webapp/smsb/qysds/kjqysds/kjqysdsbgbAction.do?actionType=Show");//url���Ը��ݲ�ͬ������ָ����ͬ�õ�ַ�Ͳ�ͬ�ò���
            gotourl.setPath("/webapp/smsb/qysds/kjqysds/kjqysdsbgbAction.do?actionType=Show");
            gotourl.setRedirect(true);
            return gotourl;     	
        }
        else{
        	return actionMapping.findForward("Back");
        }
    }

}
