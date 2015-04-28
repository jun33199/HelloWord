/*
 * <p>Title: 项目名称-文件中文名称</p>
 * <p>Description: 文件描述</p>
 * <p>Copyright: Copyright (c) 2007 北京市地方税务局，北京立思辰电子系统技术有限公司，版权所有. </p>
 * <p>Company: 北京立思辰电子系统技术有限公司</p>
 * @author wangxq
 * @date 2014-6-8
 * @version 1.0
 */
package com.ttsoft.bjtax.smsb.util;

import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.dj.model.QYSDSZGFWJD;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title:税种税目税源鉴定</p>
 * <p>Description: TODO</p>
 * @author  wangxq
 * @date 2014-6-8
 */
public class SzsmSyjdUtil {

	
	 /**
     * 根据税源鉴定类型，获得特殊税种税目的处理结果
     * @param jsjdm  计算机代码
     * @param list  数据list
     * @throws Exception
     */
    public static String getSzsmcljeBySyjdlx (String jsjdm,List list) throws
    Exception
    {
    	StringBuffer buf=new StringBuffer();
    	try{
    	
        ServiceProxy sp = new ServiceProxy();
        QYSDSZGFWJD qYSDSZGFWJD=null;
    	 for (int i = 0; i < list.size(); i++)
         {
           HashMap map = (HashMap) list.get(i);
           //System.out.println(i+1+"##="+map.get("szsmdm")+"#"+map.get("skssksrq")+"#"+map.get("skssjsrq"));
           //税种税目代码
           String szsmdm=(String)map.get("szsmdm");
           //税款结束日期
           String skssjsrq=(String)map.get("skssjsrq");
             if(skssjsrq==null){
        	   skssjsrq="";
           }
             
           //独立纳税预缴
           if(szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //独立纳税人 ,总分机构均在本市的总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
           //总机构预缴    
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //跨省市总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
           //分支机构预缴        
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //跨省市分支机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR) &&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
           //独立纳税汇算清缴       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //独立纳税人 ,总分机构均在本市的总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
            //总机构汇算清缴(2013年以前)       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))<2013){
	               //跨省市总机构纳税人
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   if(buf.length()>0){
	            		   buf.append("<br>");
	            	   }
	            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
	               }
               }else{
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("税种税目("+szsmdm+")，税款所属期只能小于2013年度。");
               }
           //总机构汇算清缴（2013年以后）      
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YH)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //跨省市总机构纳税人
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   if(buf.length()>0){
	            		   buf.append("<br>");
	            	   }
	            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
	               }
               }else{
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("税种税目("+szsmdm+")，税款所属期只能大于等于2013年度。");
               }
             //分支机构汇算清缴  
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGHSQJ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //跨省市总机构纳税人
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   if(buf.length()>0){
	            		   buf.append("<br>");
	            	   }
	            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
	               }
               }else{
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("税种税目("+szsmdm+")，税款所属期只能大于等于2013年度。");
               }
           }
         
           
         }
    	}catch(Exception ex){
    		throw ExceptionUtil.getBaseException(ex);
    	}
    	return buf.toString();
    }
	
	
	/** 
	 * <p>Title: 方法中文名称</p> 
	 * <p>Description: TODO</p>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
