package com.ttsoft.bjtax.shenbao.zrrsb.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.print.web.JksqdPrintForm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.TranslateHelper;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.zrrsb.ZrrsbActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * �ɿ����б�
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class ZrrJksAction extends ShenbaoAction
{
    public ZrrJksAction()
    {
    }

    protected int getActionID()
    {
        return SbzlAccess.ZRRSB;
    }

    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        ZrrJksForm myform = (ZrrJksForm)form;
        try
        {
            // ��ѯ�����걨δ�ɿ������
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.ZRRSB_PROCESSOR);

            HashMap map = new HashMap();

            //start modifying by qianchao 2005.10.28
            UserData ud = getUserData(request);

            //end modifying by qianchao 2005.10.28
            map.put(ZhsbMapConstant.JSJDM, ud.getXtsbm1());
            map.put(ZhsbMapConstant.WHRQ, (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
            map.put("WSSB_SWJGZZJGDM", FriendHelper.getZrrjbsj(request).getSwjgzzjgdm());
            vo.setData(map);
            vo.setUserData(ud);
            HashMap jks = (HashMap)ShenbaoProxy.getInstance().process(vo);

            //start add by qianchao 2005.10.28
            myform.setJsjdm(ud.getXtsbm1());
            myform.setNsrmc(ud.getYhmc());

            HashMap ret = TranslateHelper.translateMMM2Map(jks);
            TranslateHelper.completeSzsmmc(ret,request);
            List lists = TranslateHelper.splitMAPInto2(ret,request);
            myform.setDataList((List)lists.get(0));
            myform.setNlwDataList((List)lists.get(1));

            request.setAttribute(mapping.getAttribute(),myform);
            //end add by qianchao 2005.10.28

            request.getSession().setAttribute(SessionKey.JKS, jks);
            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public ActionForward doViewOne(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws BaseException
    {
        try
        {
            ZrrJksForm myForm = (ZrrJksForm)form;

            if(myForm.getLwState() == CodeConstant.PRINT_YPYS)
            {
                // һƱһ˰
                return mapping.findForward("ViewYPYS");
            }
            else
            {
                // һƱ��˰

                //start added code by qianchao 2006-2-12
                //�����޸ĺ�ĵ��ӽɿ�ר�ýɿ��� �޸����걨�����ʾ��
                JksqdPrintForm pf = new JksqdPrintForm();

                Debug.out("pf1.getJsjdm()=" + myForm.getJsjdm());
                Debug.out("pf1.getSbbh()=" + myForm.getSbbhIndex());

                pf.setH_jsjdm(myForm.getJsjdm());
                pf.setH_sbbh(myForm.getSbbhIndex());
                pf.setJsjdm(myForm.getJsjdm());
                pf.setSbbh(myForm.getSbbhIndex());
                pf.setHeadSjly(CodeConstant.SJLY_SB_ZRR_SBLR); //������Դ
                pf.setActionType("Show");
                request.setAttribute("jksqdPrintForm", pf);
                return mapping.findForward("ViewYPDS");
                //end   added code by qianchao 2006-2-12
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
//        DzyjsjVO dzyj = null;
        UserData ud = getUserData(request);

        try
        {
            ZrrJksForm myForm = (ZrrJksForm)form;
            String sbbh = myForm.getSbbhIndex();
            String jsjdm = myForm.getJsjdm();


            // start added code by qianchao 2006-2-8
//            String strOrginData = request.getParameter("SecX_OrginData");
//            String signData = request.getParameter("SecX_SignData");
//            if (ud.getCaflag())
//            {
//                String SecX_Error = request.getParameter("SecX_Error");
//                if (!"0".equals(SecX_Error))
//                {
//                    String tempstr;
//                    tempstr = "������ǩ������!Error:" + SecX_Error
//                    + " SecX_OD " + ud.getYhid() + ":" + strOrginData
//                    + "-----SecX_SD:" + signData  + "-----";
//                    System.out.println(tempstr);
//                    ActionErrors errors = new ActionErrors();
//                    errors.add("", new ActionError("error.server.custom", "������ǩ������Error:" + SecX_Error));
//                    saveErrors(request, errors);
//                    return (new ActionForward(mapping.getInput()));
//                }
//                System.out.println("============����ǩ�����ݿ�ʼ==============");
//                try
//                {
////                    dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(ud, 
////                            strOrginData, signData,codeConstants.YWDM_SB_WS_ZRR, 
////                            sbbh, codeConstants.YWCZLX_DELETE);
//                }
//                catch (Exception ex)
//                {
//                    ex.printStackTrace();
//
//                    throw ExceptionUtil.getBaseException(ex);
//                }
//                System.out.println("============����ǩ�����ݽ���==============");
//            }
            // end added code by qianchao 2006-2-8
            
            
            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.ZRRSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_DELETE);
            HashMap map = new HashMap();
            map.put(ZrrsbActionConstant.SBBH, sbbh);
            map.put(ZrrsbActionConstant.JSJDM,jsjdm);
            map.put("WSSB_SWJGZZJGDM", FriendHelper.getZrrjbsj(request).getSwjgzzjgdm());
            vo.setData(map);

            ShenbaoProxy.getInstance().process(vo);

            HashMap jksMap = (HashMap)request.getSession().getAttribute(SessionKey.JKS);
            jksMap.remove(sbbh);  //��������걨������ϣ�������걨�������

            //start add by qianchao 2005.10.31

            myForm.setJsjdm(ud.getXtsbm1());
            myForm.setNsrmc(ud.getYhmc());

            HashMap ret = TranslateHelper.translateMMM2Map(jksMap);
            TranslateHelper.completeSzsmmc(ret,request);

            List lists = TranslateHelper.splitMAPInto2(ret,request);
            myForm.setDataList((List)lists.get(0));
            myForm.setNlwDataList((List)lists.get(1));

            request.setAttribute(mapping.getAttribute(),myForm);
            //end add by qianchao 2005.10.31


            request.getSession().setAttribute(SessionKey.JKS, jksMap);

            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
//            if (ud.getCaflag() && (dzyj != null))
//            {
//                System.out.println("============����ɾ��ǩ����ʼ=============="
//                        + dzyj.getLsh());
//                try
//                {
//                    CAProxy.getInstance().deleteSignedData(dzyj);
//                }
//                catch (Exception e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                System.out.println("============����ɾ��ǩ������=============="
//                        + dzyj.getLsh());
//            }
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        request.getSession().removeAttribute(SessionKey.JKS);
        request.getSession().removeAttribute(SessionKey.YPDS_MAP);
        return mapping.findForward("Return");
    }

}
