package com.ttsoft.bjtax.shenbao.fangtu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import com.syax.common.web.action.DcBaseAction;
import com.syax.frame.exception.BaseException;

public class FangtuBaseAction extends DcBaseAction{
	protected static MessageResources messages = MessageResources.getMessageResources("ApplicationResources");

	
	protected String beforePerform(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) {
		return null;
	}
    public String doReturn(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
    	request.getSession(false).removeAttribute("SESSION_ID_FT_JSJDM");
        return "Return";
    }
}
