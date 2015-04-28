package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.QysdsnbActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.QysdsnbMapConstant;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * ��ҵ����˰�걨action
 */
public class QysdsnbAction extends ShenbaoAction
{
    protected int getActionID()
    {
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.QYND;
    }

    /**
     * ��ȡ��˰�˵��걨���ݲ���ʾ�걨ҳ��
     *
     * 1�����걨������Ŀ�������ݱ���ȡ����ҵ����ָ��Ķ������ݣ�����form��
     *
     * 2����˰�ѹ����еõ�����˰�˵����շ�ʽ����form�У��������շ�ʽ��ͬʹ��Ӧ���дε�?
     * ����Ϊ�ɱ༭��
     *
     * 3������ҵ����ָ���걨����ȡ����˰�˵��걨���ݣ�����form��Ӧ��������
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        QysdsnbForm nbForm = (QysdsnbForm)form;
        String frompage = nbForm.getFrompage();
        if (frompage != null && !frompage.equals("")){
           request.getSession().setAttribute("FROMPAGE",frompage);
       }else{
           request.getSession().removeAttribute("FROMPAGE");
       }

        //��ü��������
        UserData userData = getUserData(request);
        String jsjdm = userData.yhid;

        //��õ�ǰʱ��
        Timestamp curTime = new Timestamp(System.currentTimeMillis());

        Map map = new HashMap();
        map.put(QysdsnbMapConstant.STRING_KEY_JSJDM, jsjdm);
        map.put(QysdsnbMapConstant.STRING_KEY_DATE, curTime);
        map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, FriendHelper.getSWDJJBSJ(request));

        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
        vo.setAction(QysdsnbActionConstant.INT_ACTION_QUERY_All);
        vo.setData(map);

        nbForm.setActionType("Show");

        try
        {
            //���ݼ��������͵�ǰʱ���ѯ��ҵ����˰�걨����
            QysdsnbData data = (QysdsnbData)ShenbaoProxy.getInstance().process(vo);

            QysdsHelper helper = new QysdsHelper();

            //���õǼ�����
            helper.setDjInfo(data.getDjJbsj(), nbForm);

            //�����걨����
            helper.setQysdsnbData(data.getNbData(), nbForm);

            //���ò���ָ������
            helper.setCwzbData(data.getCwzbData(), nbForm);

            if(nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SYDW) ||
               nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SHTT))
            {
                //������ҵ��λ����
                helper.setSydwData(data.getSydwData(), nbForm);
            }

            //������Ӫ�ɷ�����
            helper.setLygfData(data.getLygfData(), nbForm);

            //����˰����������
            helper.setSkssrq(curTime, nbForm);

            //����˰�ѹ�����ص����ݣ�˰�ʺ������ʸ�
            helper.setSfglData(userData, nbForm, data.getDjJbsj());
        }
        catch(Exception ex)
        {
            String message = null;
            if(ex instanceof ApplicationException)
            {
                message = ( (ApplicationException)ex).getMessage();
            }
            else
            {
                ex.printStackTrace();
                message = "ϵͳ�쳣���������Ա��ϵ";
            }

            ActionErrors errors = new ActionErrors();

            errors.add("", new ActionError("error.server.custom", message));
            saveErrors(request, errors);

            nbForm.setActionType("InitError");

            return(new ActionForward(mapping.getInput()));
        }

        return mapping.findForward("Show");
    }

   /**
    * ִ�д��̲���
    *
    * 1���������
    * 2������QysdsHelper����ò���ָ���걨����list
    * 3������vopackage
    * 4������proxy�ķ�������
    * 5����ʾqycwzb009.jsp
    * @param mapping ActionMapping
    * @param form ActionForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return ActionForward
    */
   public ActionForward doSave(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
   {
       //start added code by qianchao 2006-2-11
       //Ϊǩ��ѹ������
       /*
       ActionForward tokenError = doHandleToken(mapping,request);
       if(tokenError!= null)
       {
           removeForm(mapping,request);
           return tokenError;
       }*/
       //end   added code by qianchao 2006-2-11

       QysdsnbForm nbForm = (QysdsnbForm)form;

       UserData userData = getUserData(request);

       //started added by qianchao 2005-12-27
       //ѹ�����Լ���
       String tmpjsjdm = request.getParameter("qcjsjdm");
       //ended   added by qianchao 2005-12-27
       
       
       QysdsnbData nbdata = new QysdsnbData();

       QysdsHelper helper = new QysdsHelper();
       Timestamp now = new Timestamp(System.currentTimeMillis());
       
       
       
       // started added by qianchao 2006-2-11
       DzyjsjVO dzyj = null;
       
       String strOrginData = request.getParameter("SecX_OrginData");
       String ContainerName = request.getParameter("SecX_ContainerName");
       String signData = request.getParameter("SecX_SignData");

       System.out.println("signData      : " + signData);
       System.out.println("OrginData     : " + strOrginData);
       System.out.println("ContainerName : " + ContainerName);
       
       
       if ((signData != null) && userData.caflag) 
       {
           String SecX_Error = request.getParameter("SecX_Error");
           if (! "0".equals(SecX_Error))
           {
               String tempstr;
               tempstr = "������ǩ������!Error:" + SecX_Error
               + " SecX_OD " + userData.getYhid() + ":" + strOrginData
               + "-----SecX_SD:" + signData  + "-----";
               System.out.println(tempstr);
               ActionErrors errors = new ActionErrors();
               errors.add("", new ActionError("error.server.custom", "������ǩ������errorcode:" + SecX_Error));
               saveErrors(request, errors);
               return (new ActionForward(mapping.getInput()));
           }
           
           System.out.println("jsjdm         : " + tmpjsjdm);
           ContainerName = userData.getCert().getContainerName();
           
           System.out.println("============����ǩ�����ݿ�ʼ==============");
           // /���ǩ��������

           GregorianCalendar calendar = new GregorianCalendar();
           calendar.setTime(now);
           int year = calendar.get(calendar.YEAR) - 1;
           
           String ywuid = nbForm.getJsjdm()+ "+" + year;            
           try
           {
//               dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(
//                       userData, strOrginData, signData,
//                       codeConstants.YWDM_SB_WS_QYSDS_NB, ywuid,
//                       codeConstants.YWCZLX_MODIFY);                
               System.out.println(" lsh=" + dzyj.getLsh());
           }
           catch (Exception ex)
           {
               ex.printStackTrace();

               ActionErrors errors = new ActionErrors();
               errors.add("", new ActionError("error.server.custom", "����ǩ������"));
               saveErrors(request, errors);
               return (new ActionForward(mapping.getInput()));
           }
           System.out.println("============����ǩ�����ݽ���==============");
       }
       // ended added by qianchao 2006-2-11
       
       
       
       try
       {

           SWDJJBSJ djInfo = FriendHelper.getSWDJJBSJ(request);
           //��ҵ����˰�걨����
           helper.getQysdsnbData(userData, nbForm, nbdata, djInfo);
           //����ָ������
           helper.getCwzbData(userData, nbForm, nbdata, djInfo);
           //��ҵ��λ����
           if(nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SYDW) ||
              nbForm.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SHTT))
           {
               helper.getSydwData(userData, nbForm, nbdata, djInfo);
           }
           //��Ӫ�ɷ�����
           helper.getLygfData(userData, nbForm, nbdata, djInfo);

           //��ü��������
           String jsjdm = userData.yhid;

           //��õ�ǰʱ��
           Timestamp curTime = new Timestamp(System.currentTimeMillis());

           Map map = new HashMap();
           map.put(QysdsnbMapConstant.STRING_KEY_JSJDM, jsjdm);
           map.put(QysdsnbMapConstant.STRING_KEY_DATE, curTime);
           map.put(QysdsnbMapConstant.STRING_KEY_QYSDSNB_DATA, nbdata);
           map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, djInfo);

           VOPackage vo = new VOPackage();
           vo.setUserData(userData);
           vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
           vo.setAction(QysdsnbActionConstant.INT_ACTION_SAVE_ALL);
           vo.setData(map);

           //���ݼ��������͵�ǰʱ���ѯ��ҵ����˰�걨����
           ShenbaoProxy.getInstance().process(vo);
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰�����˰�걨���걨", (new SimpleDateFormat("yyyyMMdd")).format(now), "�ɹ�!");
       }
       catch(Exception ex)
       {
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "��ҵ����˰�����˰�걨���걨", (new SimpleDateFormat("yyyyMMdd")).format(now), "ʧ��!");
           String message = null;
           if(ex instanceof BaseException)
           {
               message = ( (BaseException)ex).getMessage();
           }
           else
           {
               ex.printStackTrace();
               message = "ϵͳ�쳣���������Ա��ϵ";
           }

           ActionErrors errors = new ActionErrors();

           errors.add("", new ActionError("error.server.custom", message));
           saveErrors(request, errors);
           return(new ActionForward(mapping.getInput()));
       }

       removeForm(mapping,request);
       request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                            "������ҵ����˰����걨��ɹ���");
       return mapping.findForward("Save");
   }

   /**
    * �����걨������
    * @param mapping ActionMapping
    * @param form ActionForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return ActionForward
    */
   public ActionForward doReturn(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
   {
       String frompage = (String)request.getSession().getAttribute("FROMPAGE");
       if (frompage != null && !frompage.equals("")){
           removeForm(mapping, request);
           request.getSession().removeAttribute("FROMPAGE");
           return mapping.findForward(frompage);
       }else{
           removeForm(mapping, request);

           //ת�򷵻غ��ҳ�档
           return mapping.findForward("Return");
       }
/*       removeForm(mapping,request);
       return mapping.findForward("Return");*/
   }


   /**
    * ִ��ɾ������
    * @param mapping ActionMapping
    * @param form ActionForm
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @return ActionForward
    */
   public ActionForward doRemove(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
       throws BaseException
   {
       ActionForward tokenError = doHandleToken(mapping,request);
       if(tokenError!= null)
       {
           removeForm(mapping,request);
           return tokenError;
       }

       QysdsnbForm nbForm = (QysdsnbForm)form;

       //��ü��������
       UserData userData = getUserData(request);
       String jsjdm = userData.yhid;

       //��õ�ǰʱ��
       Timestamp curTime = new Timestamp(System.currentTimeMillis());

       try
       {
           Map map = new HashMap();
           map.put(QysdsnbMapConstant.STRING_KEY_JSJDM,jsjdm);
           map.put(QysdsnbMapConstant.STRING_KEY_DATE,curTime);
           map.put(QysdsnbMapConstant.STRING_KEY_DJJBSJ_DATA, FriendHelper.getSWDJJBSJ(request));

           VOPackage vo = new VOPackage();
           vo.setUserData(userData);
           vo.setProcessor(ProcessorNames.QYSDSNB_PROCESSOR);
           vo.setAction(QysdsnbActionConstant.INT_ACTION_DELETE_ALL);
           vo.setData(map);

           //���ݼ��������͵�ǰʱ��ɾ����ҵ����˰�걨����
           ShenbaoProxy.getInstance().process(vo);
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "ɾ����ҵ����˰�����˰�걨��", (new SimpleDateFormat("yyyyMMdd")).format(curTime), "�ɹ�!");
       }
       catch(Exception ex)
       {
           LogUtil.getInstance().log(FriendHelper.getUserData(request), "ɾ����ҵ����˰�����˰�걨��", (new SimpleDateFormat("yyyyMMdd")).format(curTime), "ʧ��!");
           String message = null;
           if(ex instanceof BaseException)
           {
               message = ( (BaseException)ex).getMessage();
           }
           else
           {
               ex.printStackTrace();
               message = "ϵͳ�쳣���������Ա��ϵ";
           }

           ActionErrors errors = new ActionErrors();

           errors.add("", new ActionError("error.server.custom",message));
           saveErrors(request, errors);
           return(new ActionForward(mapping.getInput()));
       }

       removeForm(mapping,request);
       request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,
                            "ɾ����ҵ����˰����걨��ɹ���");
       return mapping.findForward("Success");
   }
}
