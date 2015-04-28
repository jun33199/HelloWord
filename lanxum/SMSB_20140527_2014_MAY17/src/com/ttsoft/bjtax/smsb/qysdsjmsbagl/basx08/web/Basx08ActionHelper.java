package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx08.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.constant.ForwardPath;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.framework.exception.ApplicationException;



public class Basx08ActionHelper {
	
    public static String nullConvertToNbsp(String value){
    	return (value == null || value.trim().length() == 0)?"&nbsp;":value;
    }
    
    public static String getForwardPath(int i,String wsh){
    	
    	String basqwsh = "";
    	
    	switch (i) {
		case 1:
			basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx01DetailAction.do?actionType=Show&basqwsh="+wsh;
			break;
		case 2:
			basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx01DetailAction.do?actionType=Check&czlx=2&xh="+wsh;
			break;
		case 3:
			basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx01DetailAction.do?actionType=Check&czlx=3&xh="+wsh;
			break;
		case 4:
			basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx01DetailAction.do?actionType=Check&czlx=4&xh="+wsh;
			break;
		}
    	
    	return basqwsh;
    	
    }
    
    /**
     * 初始化页面下拉信息
     * @param request HttpServletRequest
     * @throws ApplicationException
     */
    public static void initialPageSelectItem(HttpServletRequest request,List list) throws ApplicationException
    {
        // 获取session
        HttpSession session = request.getSession(false);
        // 获取
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSLYDM) == null) {
            String[][] gxjsArray = new String[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
            	DmVo vo=(DmVo)list.get(i);
            	gxjsArray[i][0] = vo.getDm();
            	gxjsArray[i][1] = vo.getMc();
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GXJSLYDM, gxjsArray);
        }
     
    }
    
    
}
