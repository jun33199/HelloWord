/*
 * Created on 2006-5-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.frame.exception.ApplicationException;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CAUtils {

	/**
	 * 
	 */
	public CAUtils() {
		super();
		// TODO Auto-generated constructor stub
	}
	  public static String bool2Str(boolean b) {
	    return b?"1":"0";
	  }

	  public static String getTsxx(String ywczlx) {
	  	if (ywczlx==null) return "";
	  	if (ywczlx.equals(CAcodeConstants.YWCZLX_NEW)){
	  		return "保存成功！";
	  	}
	  	if (ywczlx.equals(CAcodeConstants.YWCZLX_MODIFY)){
	  		return "修改成功！";
	  	}
	  	if (ywczlx.equals(CAcodeConstants.YWCZLX_DELETE)){
	  		return "删除成功！";
	  	}
	  	
	  	return "保存成功！";
	  }

	  public static  String jumpTo(boolean isCa,String ptAdd,String defAdd){
	    	String addr=defAdd;
	    	if (!isCa){
	    		addr=ptAdd;    		
	    	}
	    	return addr;
	    	
	 }

	  public static DzyjsjVO saveDzyjsj(UserData ud ,DzyjsjVO dzyjsj ,String s1, String s2 ,String s3 ,String s4 ) throws BaseException, ApplicationException{
	  	DzyjsjVO dzyj=dzyjsj;
        DzyjHelper dh = new DzyjHelper();
	  	
        if (dzyjsj == null)
        {
            throw new ApplicationException("电子原件数据对象为空。");
        }
        if (dzyjsj.getYwdm() == null)
        {
            throw new ApplicationException("电子原件数据对应的业务代码为空。");
        }
        if (ud.getCaflag())
	  	{
	        try
	        {
                dzyj = dh.saveDzyjsj(dzyj, s1, s2, s3, s4);
	        }catch (Exception ex)
		    {
		        throw ExceptionUtil.getBaseException(ex);
		    }
	  	}	  	
	  	else if (dzyj.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY) ||dzyj.getYwczlx().equals(CAcodeConstants.YWCZLX_DELETE)) 
	  	{
	  		List ret = new ArrayList();
	  		try
	        {
	  	       ret = dh.queryDzyjsj(ud.getYhid(),dzyj.getYwdm(),s1, s2, s3, s4);
	        }catch (Exception ex)
	        {
	            throw ExceptionUtil.getBaseException(ex);
	        }
	  	    if (ret.size() > 0)
	  	    {
	  	        throw new ApplicationException("要修改申报表有电子原件数据，请到税务所办理。");
	  	    }
        }
	  	return dzyj;
	  }
}
