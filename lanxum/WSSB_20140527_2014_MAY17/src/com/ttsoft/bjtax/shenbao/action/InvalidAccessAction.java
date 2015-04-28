package com.ttsoft.bjtax.shenbao.action;

import com.ttsoft.framework.action.BaseAction;
import org.apache.struts.action.*;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ж�Ȩ��</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */
public class InvalidAccessAction extends BaseAction
{
    public InvalidAccessAction()
    {
    }

    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException
    {
        String attribute = request.getParameter("actionType");
        if(attribute != null && attribute.equals("noPermission"))
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"��û��Ȩ�ޣ�");
        }
        if(attribute != null && attribute.equals("invalidToken"))
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"������ذ��˺��˼�����ˢ�£�");
        }
        return mapping.findForward("Success");
    }
}