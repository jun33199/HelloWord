package com.ttsoft.bjtax.shenbao.action;

import com.ttsoft.framework.action.BaseAction;
import org.apache.struts.action.*;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 判断权限</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
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
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"您没有权限！");
        }
        if(attribute != null && attribute.equals("invalidToken"))
        {
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE,"您错误地按了后退键或者刷新！");
        }
        return mapping.findForward("Success");
    }
}