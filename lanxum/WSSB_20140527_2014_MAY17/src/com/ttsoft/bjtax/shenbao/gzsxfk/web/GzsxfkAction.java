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
            //����������ѯ��
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
	//	    System.out.println("--------11111111111111�޸ķ�������111111111111");
		        gf.setNsrfk(nr);
            	gf.setSavetype("update");
            }   
			
			//ShenbaoProxy.getInstance().process(vo);
			
			//System.out.println("--------2222222222222222");
            //����form
            request.setAttribute(mapping.getAttribute(), gf);//�����д����д����ģ��������ǲ���form�Ͳ��������أ���Ϊ��Ҫ��form�еļ��˼�����
            //����˰�����Ƽ���֪����id 2009.4.2wcl����
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
		        //2009.4.2.wcl���ӡ�����֤�����ȱ�ٵ����ݣ�1.���������ݣ������ҳ����ͨ����ܿ��Դ�foem��ã�2.������ʱ�䣬		        
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
	 * ������˰�˵��Ķ�ʱ��
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
		        //2009.4.2.wcl���ӡ�����֤�����ȱ�ٵ����ݣ�1.���������ݣ������ҳ����ͨ����ܿ��Դ�foem��ã�2.������ʱ�䣬		        
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
