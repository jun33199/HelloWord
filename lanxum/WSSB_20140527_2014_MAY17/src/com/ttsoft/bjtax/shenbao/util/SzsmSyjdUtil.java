/*
 * <p>Title: 项目名称-文件中文名称</p>
 * <p>Description: 文件描述</p>
 * <p>Copyright: Copyright (c) 2007 北京市地方税务局，北京立思辰电子系统技术有限公司，版权所有. </p>
 * <p>Company: 北京立思辰电子系统技术有限公司</p>
 * @author wangxq
 * @date 2014-6-8
 * @version 1.0
 */
package com.ttsoft.bjtax.shenbao.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ttsoft.bjtax.dj.model.QYSDSZGFWJD;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
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
    public static String getSzsmcljeBySyjdlx (Sbjkzb sbjkzb,List list) throws
    Exception
    {
    	StringBuffer buf=new StringBuffer();
    	try{
    	
        ServiceProxy sp = new ServiceProxy();
        QYSDSZGFWJD qYSDSZGFWJD=null;
       
        
        
    	 for (int i = 0; i < list.size(); i++)
         {
    		Sbjkmx mxTmp = (Sbjkmx) list.get(i);
           //System.out.println(i+1+"##="+map.get("szsmdm")+"#"+map.get("skssksrq")+"#"+map.get("skssjsrq"));
    	   
    	   //计算机代码	
    	   String jsjdm=mxTmp.getJsjdm();
           //税种税目代码
           String szsmdm=mxTmp.getSzsmdm();
           
         //计算税款截止日期
      	 Timestamp tempSkjzrq=null;
           if (mxTmp.getSkssksrq() == null && mxTmp.getSkssjsrq() == null) {
               Map map = Skssrq.getSksssq(mxTmp.getJsjdm(), mxTmp.getSzsmdm(),
              		 sbjkzb.getDjzclxdm(), sbjkzb
                                          .getSklxdm(), mxTmp.getZqlxdm(),
                                          sbjkzb.getLrrq(), mxTmp.getSjse(),
                                          mxTmp.getKssl(), mxTmp
                                          .getJsje());
             
               if(map.get(Skssrq.SKSSJSRQ)==null){
              	 tempSkjzrq=(Timestamp) Skssrq.monthSkssrq(new Date()).get(
                           Skssrq.SKSSJSRQ);
               }else{
              	 tempSkjzrq=(Timestamp) map.get(Skssrq.SKSSJSRQ);
               }
               
           }else{
          	 tempSkjzrq=mxTmp.getSkssjsrq();
           }
           SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
           String skssjsrq = simpleDataFormat.format(tempSkjzrq); 
           //System.out.println(i+1+"#########skssjsrq="+skssjsrq);
           
           
           //税款结束日期
//           String skssjsrq=(String)map.get("skssjsrq");
//             if(skssjsrq==null){
//        	   skssjsrq="";
//           }
             
           //独立纳税预缴
           if(szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //独立纳税人 ,总分机构均在本市的总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
           //总机构预缴    
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //跨省市总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
           //分支机构预缴        
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //跨省市分支机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
           //独立纳税汇算清缴       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //独立纳税人 ,总分机构均在本市的总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("您当前选择的税种税目("+szsmdm+")不符合您在当前税款所属期间认定的企业所得税税源鉴定结果，请重新选择。");
               }
            //总机构汇算清缴(2013年以前)       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
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
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
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
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
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
	//过滤税种税目
    public static boolean  checkSzsmdmBySyjdlx(HttpServletRequest request,SWDJJBSJ djsj,String szsmdm) throws Exception
    {
    	 boolean flag=true;
    	try{
     if(szsmdm.startsWith("30")){//如果是企业所得税才会进行判断
        ServiceProxy sp = new ServiceProxy();
        QYSDSZGFWJD qYSDSZGFWJD=null;
        String jsjdm=djsj.getJsjdm();
        
        Szsm szsm = getSzsm(jsjdm,szsmdm, request);
        String zqlxdm=szsm.getZqlxdm();//征期类型代码
        
        String djzclxdm=djsj.getDjzclxdm();//登记注册类型代码
        
           
           //独立纳税预缴
           if(szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //独立纳税人 ,总分机构均在本市的总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
                  flag=false;
               }
           //总机构预缴    
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //跨省市总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   flag=false;
            	}
           //分支机构预缴        
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //跨省市分支机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   flag=false;
                }
           //独立纳税汇算清缴       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //独立纳税人 ,总分机构均在本市的总机构纳税人
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   flag=false;
               }
            //总机构汇算清缴(2013年以前)       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))<2013){
	               //跨省市总机构纳税人
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   flag=false;
	              }
               }else{
            	   flag=false;
               }
           //总机构汇算清缴（2013年以后）      
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YH)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //跨省市总机构纳税人
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   flag=false;
	            	}
               }else{
            	   flag=false;
               }
             //分支机构汇算清缴  
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGHSQJ)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //跨省市总机构纳税人
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   flag=false;
	            	}
               }else{
            	   flag=false;
             }
               
           }
      }  
    	}catch(Exception ex){
    		ex.printStackTrace();
    		throw ExceptionUtil.getBaseException(ex);
    	}
    	return flag;
    }
	
    
   
    
	private static String getSkssjsrq(String jsjdm,
            String szsmdm,
            String djzclxdm,
            String sklxdm,
            String zqlxdm) {
		

            Timestamp now = new Timestamp(System.currentTimeMillis());
      	    Timestamp tempSkjzrq=null;
               Map map = Skssrq.getSksssq(jsjdm,
            		   					  szsmdm,
            		   					djzclxdm, 
            		   					sklxdm,
            		   					zqlxdm,
                                         now, null,
                                         null, null);
               if(map.get(Skssrq.SKSSJSRQ)==null){
              	 tempSkjzrq=(Timestamp) Skssrq.monthSkssrq(new Date()).get(
                           Skssrq.SKSSJSRQ);
               }else{
              	 tempSkjzrq=(Timestamp) map.get(Skssrq.SKSSJSRQ);
               }
               
           SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
           String skssjsrq = simpleDataFormat.format(tempSkjzrq); 
		return skssjsrq;
		  
	}
	
	private static Szsm getSzsm(String jsjdm,String szsmdm, HttpServletRequest request) {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);
        Szsm tmpsz = (Szsm) szsmMap.get(szsmdm);
        if (tmpsz == null) {
            try {
                StringBuffer tmpstr = new StringBuffer();
                String namestr = "";
                String pvs[] = null;
                Enumeration em = request.getParameterNames();
                while (em.hasMoreElements()) {
                    namestr = (String) em.nextElement();
                    pvs = request.getParameterValues(namestr);
                    for (int i = 0; i < pvs.length; i++) {
                        tmpstr.append(namestr).append(" : ").append(pvs[i]).
                            append("\r\n");
                    }
                }
                tmpstr.append("szsmdm : ").append(szsmdm).append("\r\n");
                tmpstr.append("jsjdm : ").append(jsjdm).append("\r\n");
            }
            catch (RuntimeException e) {
                e.printStackTrace();
                return null;
            }
        }
        return tmpsz;
    }

	public static boolean isQs (String szsmdm) throws
	    Exception{
		boolean flag=false;
		if(szsmdm.equals(CodeConstant.SZSMDM_FZJGHSQJ)||szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YH)
        		||szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)||szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)
        		||szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)||szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)
        		||szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
			
			return true ;
		}else{
			
			return false;
		}
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
