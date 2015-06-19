/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfRequestUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ¼��ӡ��˰������� Action</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmlrAction
    extends SmsbAction
{
//    UserData userData = null;

    protected void initialRequest (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
    {
        request.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
            "<font color=\"#7C9AAB\">�ۺϷ��������Ϣϵͳ&gt;�����걨&gt;</font>¼��ӡ��˰�������");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/yhsgl/yhsgmlr-000.htm");
    }

    /**
     * ��ʾ����
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doShow (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            YhsgmlrForm yForm = (YhsgmlrForm) aForm;
            yForm.reset(mapping, request);
            UserData userData = this.getUserData(request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            yForm.setDsdwmc(String.valueOf(userData.getSsdwmc()));
            yForm.setSwjgzzjgdm(String.valueOf(userData.getSsdwdm()));
            yForm.setCjsj(SfDateUtil.getDate());
            try
            {
                //��Ʊ֤�ӿ�
                ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                     getYhspList(this.getUserData(request));
                SfHashList sfList = new SfHashList(yhspList);
                sfList.orderByNum("pzzldm", true);
                yForm.setDataList(sfList.getRecords());
            }
            catch (Exception e1)
            {
                throw ExceptionUtil.getBaseException(e1);
            }
            return mapping.findForward("Show");
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���洦��
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doSave (ActionMapping mapping,
                                 ActionForm aForm, HttpServletRequest request,
                                 HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
        VOPackage vo = new VOPackage();
        YhsgmlrForm yForm = (YhsgmlrForm) aForm;

        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        //�ӱ����ݣ�"spmzdm", "spmzje", "gpsl", "je"������
        String columns[] = yForm.getColumns();
        yForm.setDataList(SfRequestUtil.getValuesToList(request, columns));
        vo.setProcessor(CodeConstant.YHSGL_GMLR_PROCESSOR);
        vo.setData(yForm);

        try
        {
            UserData userData = this.getUserData(request);
            vo.setUserData(userData);
            YhsgmlrForm retForm = new YhsgmlrForm();
            yForm = (YhsgmlrForm) ZhsbProxy.getInstance().process(vo);
            yForm.reset(mapping, request);
            yForm.setLrr(String.valueOf(userData.getYhid()));
            yForm.setDsdwmc(String.valueOf(userData.getSsdwmc()));
            yForm.setSwjgzzjgdm(String.valueOf(userData.getSsdwdm()));
            yForm.setCjsj(SfDateUtil.getDate());

            try
            {
                //��Ʊ֤�ӿ�
                ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                     getYhspList(this.getUserData(request));
                SfHashList sfList = new SfHashList(yhspList);
                sfList.orderByNum("pzzldm", true);
                yForm.setDataList(sfList.getRecords());
                request.setAttribute(mapping.getAttribute(), yForm);
                request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS, "����ɹ���");
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
                throw ExceptionUtil.getBaseException(e1);
            }

            return mapping.findForward("Save");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                //��Ʊ֤�ӿ�
                ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                     getYhspList(this.getUserData(request));
                SfHashList sfList = new SfHashList(yhspList);
                sfList.orderByNum("pzzldm", true);
                yForm.setDataList(sfList.getRecords());
                request.setAttribute(mapping.getAttribute(), yForm);
            }
            catch (Exception e1)
            {
                e.printStackTrace();
                throw ExceptionUtil.getBaseException(e1);
            }

            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ȡ���ƴ���
     * @param mapping The ActionMapping used to select this instance
     * @param aForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return ActionForward
     * @throws BaseException
     */
    public ActionForward doReader (ActionMapping mapping,
                                   ActionForm aForm, HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        YhsgmlrForm yForm = (YhsgmlrForm) aForm;
        //��ʱ������������ʹ�������
        String jsjdm = yForm.getJsjdm();
        String cjsj = yForm.getCjsj();
        yForm.reset(mapping, request);
        //����������ֵ
        UserData userData = this.getUserData(request);
        yForm.setLrr(String.valueOf(userData.getYhid()));
        yForm.setDsdwmc(String.valueOf(userData.getSsdwmc()));
        yForm.setSwjgzzjgdm(String.valueOf(userData.getSsdwdm()));
        yForm.setCjsj(cjsj);

        try
        { //��Ʊ֤�ӿ�
            ArrayList yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                 getYhspList(this.getUserData(request));
            SfHashList sfList = new SfHashList(yhspList);
            sfList.orderByNum("pzzldm", true);
            yForm.setDataList(sfList.getRecords());
        }
        catch (Exception e1)
        {
            throw ExceptionUtil.getBaseException(e1);
        }

        try
        { //���Ǽǽӿ�
            com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.
                bjtax.
                dj.proxy.ServiceProxy();
            HashMap ghdwMap = djProxy.getDjInfo(jsjdm, this.getUserData(request));
            SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
            yForm.setGhdwmc(swdjjbsj.getNsrmc());
            yForm.setJsjdm(jsjdm);
        }
        catch (Exception e2)
        {
            throw ExceptionUtil.getBaseException(e2);
        }
        return mapping.findForward("Reader");
    }

}