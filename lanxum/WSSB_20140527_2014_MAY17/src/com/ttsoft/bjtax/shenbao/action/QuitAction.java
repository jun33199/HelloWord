package com.ttsoft.bjtax.shenbao.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.common.util.Debug;

/**
 *
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �˳���¼Action</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */
public class QuitAction extends Action
{
    public QuitAction()
    {
    }

    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException
    {
        String menu_url =
            (String)request.getSession().getAttribute(SessionKey.PARAM_MENU);
        if (request.getSession(false) != null)
        {
            request.getSession(false).invalidate();
        }

        Debug.out("---menu_url: " + menu_url);

        if (menu_url != null)
        {
            response.sendRedirect(response.encodeRedirectURL(menu_url));
        }

        return null;
    }
}