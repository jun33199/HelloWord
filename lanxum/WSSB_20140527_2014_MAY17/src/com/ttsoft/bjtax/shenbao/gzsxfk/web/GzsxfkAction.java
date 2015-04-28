package com.ttsoft.bjtax.shenbao.gzsxfk.web;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.dao.GzsxDbAccess;
import com.ttsoft.bjtax.shenbao.gzsx.web.GzsxForm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
//import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm;
//import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;

//import com.ttsoft.bjtax.smsb.gzwh.web.GzsxfkForm;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class GzsxfkAction extends ShenbaoAction {
    //private VOPackage vo = new VOPackage();
	public GzsxfkAction(){
		
	}
	public ActionForward doShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
            //着这里做查询，
			VOPackage vo = new VOPackage();
			GzsxForm gf = (GzsxForm) form;
			Map map=new HashMap();
			map.put("jsjdm", gf.getJsjdm());
			map.put("gzsx_id", gf.getGzsx_id());
//			System.out.println("---------doShowjsjdm----"+gf.getJsjdm());
//			System.out.println("---------doShowgzsx_id----"+gf.getGzsx_id());
//			System.out.println("---------doShownsrmc----"+gf.getNsrmc());
			vo.setData(map);
			//System.out.println("--------GzsxfkAction 1111111111111111");
			vo.setProcessor(ProcessorNames.GZSXFK_PROCESSOR);
			vo.setAction(ActionConstant.INT_ACTION_FKNRQUERY);
			String nr=(String)ShenbaoProxy.getInstance().process(vo);
			if(!"2".equals(nr)){
	//	    System.out.println("--------11111111111111修改反馈内容111111111111");
		        gf.setNsrfk(nr);
            	gf.setSavetype("update");
            }   
			
			//ShenbaoProxy.getInstance().process(vo);
			
			//System.out.println("--------2222222222222222");
            //传递form
            request.setAttribute(mapping.getAttribute(), gf);//这两行代码有待审阅，不这样是不是form就不能用了呢，因为我要用form中的极端及代码
            //及纳税人名称及告知事项id 2009.4.2wcl增加
			return mapping.findForward("Show");
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			 ActionForward forward = doHandleToken(mapping, request);
		        if (forward != null)
		        {
		            return forward;
		        }
		        //2009.4.2.wcl增加。待验证。这个缺少的数据：1.反馈的内容（这个在页面上通过框架可以从foem获得）2.反馈的时间，		        
            GzsxForm gf = (GzsxForm) form;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	        gf.setFksj(new Date(System.currentTimeMillis()));
	        gf.setYdsj(format.format(new Date(System.currentTimeMillis())));
	        VOPackage vo = new VOPackage();
	        Map map=new HashMap();
			map.put("jsjdm", gf.getJsjdm());
			map.put("gzsx_id", gf.getGzsx_id());
			map.put("nsrfk",gf.getNsrfk());
			map.put("fksj", gf.getFksj());
			map.put("ydsj", gf.getYdsj());
			map.put("nsrmc", gf.getNsrmc());
			map.put("savetype", gf.getSavetype());
//			System.out.println("---------doSavejsjdm----"+gf.getJsjdm());
//			System.out.println("---------doSavegzsx_id----"+gf.getGzsx_id());
//			System.out.println("---------doSavensrmc----"+gf.getNsrmc());
//			System.out.println("---------doSavensrfk----"+gf.getNsrfk());
//			System.out.println("---------doSavesavetype----"+gf.getSavetype());
//			System.out.println("---------doSavefksj----"+gf.getFksj());
//			System.out.println("---------doSaveydsj----"+gf.getYdsj());
			vo.setData(map);
			vo.setProcessor(ProcessorNames.GZSXFK_PROCESSOR);
			vo.setAction(ActionConstant.INT_ACTION_SAVEORUPDATEFKNR);
			if(((String)ShenbaoProxy.getInstance().process(vo)).equals("1")){
            	gf.setFknrsavebs("1");
            }else 
            {
            	gf.setFknrsavebs("2");
            }
			
			return mapping.findForward("Show");
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	/*
	 * 保存纳税人的阅读时间
	 */
	public ActionForward doYdsjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			 ActionForward forward = doHandleToken(mapping, request);
		        if (forward != null)
		        {
		            return forward;
		        }
		        //2009.4.2.wcl增加。待验证。这个缺少的数据：1.反馈的内容（这个在页面上通过框架可以从foem获得）2.反馈的时间，		        
            GzsxForm gf = (GzsxForm) form;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
            gf.setYdsj(format.format(new Date(System.currentTimeMillis())));
	        VOPackage vo = new VOPackage();
			vo.setData(gf);
			vo.setProcessor(ProcessorNames.GZSXFK_PROCESSOR);
			vo.setAction(ActionConstant.INT_ACTION_SAVEORUPDATEFKNR);
			if((String)ShenbaoProxy.getInstance().process(vo)=="1"){
            	gf.setFknrsavebs("1");
            }   
			return mapping.findForward("Show");
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	

}
