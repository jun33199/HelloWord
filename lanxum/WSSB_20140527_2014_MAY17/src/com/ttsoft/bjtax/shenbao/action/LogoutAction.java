package com.ttsoft.bjtax.shenbao.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.common.util.Debug;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ע��</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */
public class LogoutAction extends Action
{
    public LogoutAction()
    {
    }

    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException
    {
        String logout_url =
            (String)request.getSession().getAttribute(SessionKey.PARAM_LOGOUT);
        if (request.getSession(false) != null)
        {
            request.getSession(false).invalidate();
        }

        Debug.out("---logout_url: " + logout_url);

        response.sendRedirect(logout_url);

        return null;
    }
}