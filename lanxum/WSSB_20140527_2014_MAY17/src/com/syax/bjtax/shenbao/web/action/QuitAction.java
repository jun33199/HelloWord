package com.syax.bjtax.shenbao.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.common.web.action.DcBaseAction;
import com.syax.common.web.util.DcActionConfig;
import com.syax.frame.exception.BaseException;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;

/**
 * 
 * <p>
 * Title: 北京地税核心征管系统－－网上申报模块
 * </p>
 * <p>
 * Description: 退出登录Action，使用dc框架
 * </p>
 * <p>
 * </p>
 * <p>
 * Company: syax
 * </p>
 * 
 * @author qianchao
 * @version 1.0
 */
public class QuitAction extends DcBaseAction
{
    public QuitAction()
    {
    }

    public String doQuit(HttpServletRequest request, HttpServletResponse response) throws IOException, BaseException
    {
        String menu_url = (String) request.getSession().getAttribute(SessionKey.PARAM_MENU);
        if (request.getSession(false) != null)
        {
            request.getSession(false).invalidate();
        }

        Debug.out("---menu_url: " + menu_url);

        if (menu_url != null)
        {
            response.sendRedirect(response.encodeRedirectURL(menu_url));
        }

        return DcActionConfig.NO_FORWARD;
    }

    public String doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, BaseException
    {
        String logout_url =
            (String)request.getSession().getAttribute(SessionKey.PARAM_LOGOUT);
        if (request.getSession(false) != null)
        {
            request.getSession(false).invalidate();
        }

        Debug.out("---logout_url: " + logout_url);

        response.sendRedirect(logout_url);

        return DcActionConfig.NO_FORWARD;
    }

    protected String beforePerform(HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }
}