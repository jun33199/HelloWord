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
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 注销</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
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