/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��������ά��Ŀ¼</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqwhAction
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
                             "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����</font>&gt;<font color=\"#6F8EA2\">�걨����</font>&gt;</td>");
        request.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                             "��������ά��");
        request.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                             "help/smsb/zqwh/zqwh-001.htm");
    }

    /**
     * ��ʾ����
     * @param actionMapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param httpServletRequest The HTTP request we are processing
     * @param httpServletResponse The HTTP response we are creating
     * @return the element previously at the specified position.
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
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }

    }

}