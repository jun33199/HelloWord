/*
 * <p>Title: ��Ŀ����-�ļ���������</p>
 * <p>Description: �ļ�����</p>
 * <p>Copyright: Copyright (c) 2007 �����еط�˰��֣�������˼������ϵͳ�������޹�˾����Ȩ����. </p>
 * <p>Company: ������˼������ϵͳ�������޹�˾</p>
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
 * <p>Title:˰��˰Ŀ˰Դ����</p>
 * <p>Description: TODO</p>
 * @author  wangxq
 * @date 2014-6-8
 */
public class SzsmSyjdUtil {

	
	 /**
     * ����˰Դ�������ͣ��������˰��˰Ŀ�Ĵ�����
     * @param jsjdm  ���������
     * @param list  ����list
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
           //˰��˰Ŀ����
           String szsmdm=(String)map.get("szsmdm");
           //˰���������
           String skssjsrq=(String)map.get("skssjsrq");
             if(skssjsrq==null){
        	   skssjsrq="";
           }
             
           //������˰Ԥ��
           if(szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //������˰�� ,�ֻܷ������ڱ��е��ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
           //�ܻ���Ԥ��    
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //��ʡ���ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
           //��֧����Ԥ��        
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //��ʡ�з�֧������˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR) &&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
           //������˰�������       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               //������˰�� ,�ֻܷ������ڱ��е��ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
            //�ܻ����������(2013����ǰ)       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))<2013){
	               //��ʡ���ܻ�����˰��
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   if(buf.length()>0){
	            		   buf.append("<br>");
	            	   }
	            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
	               }
               }else{
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("˰��˰Ŀ("+szsmdm+")��˰��������ֻ��С��2013��ȡ�");
               }
           //�ܻ���������ɣ�2013���Ժ�      
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YH)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //��ʡ���ܻ�����˰��
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   if(buf.length()>0){
	            		   buf.append("<br>");
	            	   }
	            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
	               }
               }else{
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("˰��˰Ŀ("+szsmdm+")��˰��������ֻ�ܴ��ڵ���2013��ȡ�");
               }
             //��֧�����������  
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGHSQJ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,(String)map.get("skssjsrq"));
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //��ʡ���ܻ�����˰��
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   if(buf.length()>0){
	            		   buf.append("<br>");
	            	   }
	            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
	               }
               }else{
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("˰��˰Ŀ("+szsmdm+")��˰��������ֻ�ܴ��ڵ���2013��ȡ�");
               }
           }
         
           
         }
    	}catch(Exception ex){
    		throw ExceptionUtil.getBaseException(ex);
    	}
    	return buf.toString();
    }
	
	
	/** 
	 * <p>Title: ������������</p> 
	 * <p>Description: TODO</p>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
