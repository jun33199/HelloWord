/*
 * <p>Title: ��Ŀ����-�ļ���������</p>
 * <p>Description: �ļ�����</p>
 * <p>Copyright: Copyright (c) 2007 �����еط�˰��֣�������˼������ϵͳ�������޹�˾����Ȩ����. </p>
 * <p>Company: ������˼������ϵͳ�������޹�˾</p>
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
    	   
    	   //���������	
    	   String jsjdm=mxTmp.getJsjdm();
           //˰��˰Ŀ����
           String szsmdm=mxTmp.getSzsmdm();
           
         //����˰���ֹ����
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
           
           
           //˰���������
//           String skssjsrq=(String)map.get("skssjsrq");
//             if(skssjsrq==null){
//        	   skssjsrq="";
//           }
             
           //������˰Ԥ��
           if(szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //������˰�� ,�ֻܷ������ڱ��е��ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
           //�ܻ���Ԥ��    
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //��ʡ���ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
           //��֧����Ԥ��        
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //��ʡ�з�֧������˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
           //������˰�������       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //������˰�� ,�ֻܷ������ڱ��е��ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   if(buf.length()>0){
            		   buf.append("<br>");
            	   }
            	   buf.append("����ǰѡ���˰��˰Ŀ("+szsmdm+")���������ڵ�ǰ˰�������ڼ��϶�����ҵ����˰˰Դ���������������ѡ��");
               }
            //�ܻ����������(2013����ǰ)       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)){
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
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
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
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
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
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
	//����˰��˰Ŀ
    public static boolean  checkSzsmdmBySyjdlx(HttpServletRequest request,SWDJJBSJ djsj,String szsmdm) throws Exception
    {
    	 boolean flag=true;
    	try{
     if(szsmdm.startsWith("30")){//�������ҵ����˰�Ż�����ж�
        ServiceProxy sp = new ServiceProxy();
        QYSDSZGFWJD qYSDSZGFWJD=null;
        String jsjdm=djsj.getJsjdm();
        
        Szsm szsm = getSzsm(jsjdm,szsmdm, request);
        String zqlxdm=szsm.getZqlxdm();//�������ʹ���
        
        String djzclxdm=djsj.getDjzclxdm();//�Ǽ�ע�����ʹ���
        
           
           //������˰Ԥ��
           if(szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //������˰�� ,�ֻܷ������ڱ��е��ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
                  flag=false;
               }
           //�ܻ���Ԥ��    
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //��ʡ���ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   flag=false;
            	}
           //��֧����Ԥ��        
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //��ʡ�з�֧������˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSFZJGNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
            	   flag=false;
                }
           //������˰�������       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               //������˰�� ,�ֻܷ������ڱ��е��ܻ�����˰��
               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_DLNSR)&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_ZFJGJZBSDZJGNSR)){
            	   flag=false;
               }
            //�ܻ����������(2013����ǰ)       
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))<2013){
	               //��ʡ���ܻ�����˰��
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   flag=false;
	              }
               }else{
            	   flag=false;
               }
           //�ܻ���������ɣ�2013���Ժ�      
           }else if(szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YH)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //��ʡ���ܻ�����˰��
	               if(!qYSDSZGFWJD.getJdlxdm().equals("")&&!qYSDSZGFWJD.getJdlxdm().equals(CodeConstant.QYSDSZGFWJDLX_KSSZJGNSR)){
	            	   flag=false;
	            	}
               }else{
            	   flag=false;
               }
             //��֧�����������  
           }else if(szsmdm.equals(CodeConstant.SZSMDM_FZJGHSQJ)){
        	   String skssjsrq=getSkssjsrq(jsjdm,szsmdm,djzclxdm, CodeConstant.SKLXDM_ZCJK,zqlxdm);
        	   qYSDSZGFWJD =sp.getQysdszgfwjd(jsjdm,skssjsrq);
               if(skssjsrq.length()>4&&Integer.parseInt(skssjsrq.substring(0,4))>=2013){
	               //��ʡ���ܻ�����˰��
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
	 * <p>Title: ������������</p> 
	 * <p>Description: TODO</p>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
