package com.ttsoft.bjtax.smsb.zjyjmsb.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.zjyjmsb.web.ZjyjmsbForm;
import com.ttsoft.bjtax.smsb.zjyjmsb.web.ZjyjmsbInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbz;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx12;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx3;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 再就业减免申报</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四一安信</p>
 * @author qinwei
 * @version 1.0 2006-5-18
 */
public class ZjyjmsbProcessor implements Processor{
	
	 public ZjyjmsbProcessor(){
	 }
	     /**
	     * 处理入口
	     * @param vo 前台数据
	     * @throws BaseException 操作异常
	     */
	 public Object process(VOPackage vo) throws BaseException
	    {
	       switch(vo.getAction())
	        {   
	            case CodeConstant.SMSB_SHOWACTION:
	            	
	             return doShowSbData(vo);
	            
	            case CodeConstant.SMSB_SAVEACTION:
	            	
	             
	             return doSaveSbData(vo);
	            
	            case CodeConstant.SMSB_QUERYACTION:
	            	
	          
	             return	 doQuerySbData(vo);
	                 
		           
	                
	            case CodeConstant.SMSB_DELETEACTION:
	            	doDeleteSbData(vo);
	                return null;
               
	           
	            default:
	                throw new SystemException("no such mothod!");
	                
	        }
	    }

     /**
      * 查询再就业减免申报明细记录
      * @param VOPackage vo
      * @return Map
      * @throws com.ttsoft.framework.exception.BaseException
      */
     private Object doShowSbData(VOPackage vo) throws BaseException
     {   
         ZjyjmsbForm zjyForm = (ZjyjmsbForm)vo.getData();
         try
         {   
           //设置年度
           Timestamp now = new Timestamp(System.currentTimeMillis());
           String date=TinyTools.Date2String(now,"yyyyMMdd");
           String name=TinyTools.getPropertyName(date);
           String nd=name.substring(26,30);
           String xsjd=name.substring(30,31);
           int xsyf=0;//根据季度系统自动带出的月份
           int jd =Integer.parseInt(xsjd);        
           
           switch(jd){
           case 1: xsyf=1; break;
           case 2: xsyf=4;break;
           case 3: xsyf=7;break;
           case 4: xsyf=10;break;
           }
           zjyForm.setNd(nd);
           zjyForm.setJd(xsjd);
           zjyForm.setYf(xsyf);
           return zjyForm;
          }
         catch(Exception ex)
         {
         	throw ExceptionUtil.getBaseException(ex, "查询再就业征期日历出错");
         }
     }	 
	 
	 
            /**
             * 查询再就业减免申报明细记录
             * @param VOPackage vo
             * @return Map
             * @throws com.ttsoft.framework.exception.BaseException
             */
            private Map doQuerySbData(VOPackage vo) throws BaseException
            {   
            	Connection conn = null;
            	Map mxMap = new HashMap();
                ZjyjmsbForm zjyForm = (ZjyjmsbForm)vo.getData();
                String jsjdm = zjyForm.getJsjdm();
                String bbnd = zjyForm.getNd();
                String bbq = zjyForm.getJd();
                PreparedStatement pstmt = null;
            	ResultSet mx12result =null;
            	ResultSet mx3result  =null;
            	
            	//20071016出表前录入的2007年4季度数据不可修改删除
            	ResultSet zresult  =null;
            	mxMap.put("SJPD","false");//默认可修改
            	
            	List mx12List = new ArrayList();
                try
                {   
                	//增加征期判断
/*                	Date now = new Date();
                	String zqstr = TinyTools.getProperty("WSSB_ZJYJMSB_ZQRL_MONTH_"
                            + (new SimpleDateFormat("MM")).format(now));
                	boolean withinzq = TinyTools.withinZq(zqstr, now);
                	System.out.println("----------------zqstr="+zqstr+" withinzq="+withinzq);
                	mxMap.put("ZQPD",new Boolean(withinzq));
*/                	//查询再就业减免明细表12的sql语句
                	conn = SfDBResource.getConnection();

                	
                	if(bbnd.equals("2007")&&bbq.equals("4")){
                    	String queryzSql="SELECT sbrq FROM SBDB.SB_JL_ZJYJMSBZ"
                            + " WHERE JSJDM = ?  "
                            + " AND  BBND = '2007' AND BBQ = '4' AND SBRQ<=to_date(20071015,'yyyymmdd')";  
                    	
                    	System.out.println("queryzSql====="+queryzSql);
                    	pstmt = conn.prepareStatement(queryzSql);
                    	pstmt.setString(1, jsjdm);
                    	zresult =  pstmt.executeQuery();

                    	if(zresult.next()) 
                    	{ 
                    		mxMap.put("SJPD","true");
                    	}                    
                	}

                	
                	String querymx12Sql= "SELECT * FROM SBDB.SB_JL_ZJYJMSBMX12"
                        + " WHERE JSJDM = ?  "
                        + " AND  BBND = ? AND BBQ = ? ";
                	
                	System.out.println("querymx12Sql====="+querymx12Sql);
                    pstmt = conn.prepareStatement(querymx12Sql);
                    pstmt.setString(1, jsjdm);
                    pstmt.setString(2, bbnd);
                    pstmt.setString(3, bbq);
                    mx12result =  pstmt.executeQuery();
                    

                    
                    while (mx12result.next()) 
                    { 
                    	Zjyjmsbmx12 zjyjmsbmx12 = new Zjyjmsbmx12();
                    	zjyjmsbmx12.setJ1(mx12result.getString("J1"));
                    	zjyjmsbmx12.setJ2(mx12result.getString("J2"));
                    	zjyjmsbmx12.setJ3(mx12result.getString("J3"));
                    	zjyjmsbmx12.setSzdm(mx12result.getString("SZDM"));
                    	zjyjmsbmx12.setSbblxdm(mx12result.getString("SBBLXDM"));
                    	zjyjmsbmx12.setNsrlxdm(mx12result.getString("NSRLXDM"));
                    	zjyjmsbmx12.setXnxgsyrs(mx12result.getString("XNXGSYRS"));
                        mx12List.add(zjyjmsbmx12);
                    	
                    }
                   
                   if (mx12List != null)
                   {
                	   mxMap.put("MX12LIST",mx12List);
                   }
                    
                   
                   //查询再就业减免明细表3的sql语句
                    String querymx3Sql= "SELECT * FROM SBDB.SB_JL_ZJYJMSBMX3"
                        + " WHERE JSJDM = ?  "
                        + " AND  BBND = ? AND BBQ = ? ";
                    pstmt = conn.prepareStatement(querymx3Sql);
                    pstmt.setString(1, jsjdm);
                    pstmt.setString(2, bbnd);
                    pstmt.setString(3, bbq);
                    mx3result =  pstmt.executeQuery();
                    if (mx3result.next()) 
                    {
                    	Zjyjmsbmx3 zjyjmsbmx3 = new Zjyjmsbmx3();
                    	zjyjmsbmx3.setQnyysr(mx3result.getString("QNYYSR"));
                        zjyjmsbmx3.setQnjmse(mx3result.getString("QNJMSE"));
                    	mxMap.put("MX3",zjyjmsbmx3);
                    	
                    }	
                    return mxMap;
                    
                 }
                catch(Exception ex)
                {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex, "查询申报数据失败,请与管理员联系!");
                }
                finally
                {   
                try {
                   	   pstmt.close();
                   	   SfDBResource.freeConnection(conn);
                     } 
                   catch(Exception e) {}
                }
            }
           
           
	     //保存再就业减免申报数据
	    private Object doSaveSbData(VOPackage vo) throws BaseException
	    {
	        //首先删除上次的申报数据

	        doDeleteSbData(vo);
	        Map dataMap =(Map)vo.getData();
	    	ZjyjmsbInfor zjyjmsbinfor = (ZjyjmsbInfor)dataMap.get("zjyjmsbinfor");
	        Zjyjmsbz zjyjmsbz = null;
	        List zjyjmsbmxList = null;
	        Zjyjmsbmx3 zjyjmsbmx3 = null;
	        UserData ud = vo.getUserData();
	

	      
	       
	        try
	        {
                
	            if(zjyjmsbinfor != null)
	            {
	                zjyjmsbz = zjyjmsbinfor.getZjyjmsbz();
	                zjyjmsbmxList = zjyjmsbinfor.getZjyjmsbmxList();
	                zjyjmsbmx3 = zjyjmsbinfor.getZjyjmsbmx3();
	             }
	            else
	            {
	                throw ExceptionUtil.getBaseException(new Exception("找不到再就业减免申报信息！"));
	            }

	            //插入再就业减免申报数据
	            insertSbData(zjyjmsbz,zjyjmsbmxList,zjyjmsbmx3);

	            
	         }

	       
	        catch(Exception e)
	        {

	            throw ExceptionUtil.getBaseException(e, "保存申报数据失败,请与管理员联系!");
	        }
	        return Boolean.TRUE;
	    }
	    
	   
         //插入再就业减免申报数据
        private void insertSbData(Zjyjmsbz zjyjmsbz, List zjyjmsbmxList,Zjyjmsbmx3 zjyjmsbmx3) 
                                   throws BaseException
        {
           Zjyjmsbmx12 zjyjmsbmx12 = null;
           Connection conn = null;
           ORManager orMgr = null;
           try
           {  
             conn = SfDBResource.getConnection();
             orMgr = SfDBResource.getORManager();

             //插入再就业减免申报主表数据
             orMgr.makePersistent(0, conn, zjyjmsbz);
          

             //插入再就业减免申报明细12数据
             if(zjyjmsbmxList!=null){
             for(int i = 0; i < zjyjmsbmxList.size(); i++)
               {
                 zjyjmsbmx12 = (Zjyjmsbmx12)zjyjmsbmxList.get(i);
             
                 orMgr.makePersistent(0, conn, zjyjmsbmx12);
                
               }
             }
             //插入再就业减免申报明细3数据
             if(zjyjmsbmx3!=null) {
            	 orMgr.makePersistent(0, conn, zjyjmsbmx3);
               }
         
             }
           catch(Exception ex)
           {
           ex.printStackTrace();
           throw ExceptionUtil.getBaseException(ex, "保存申报数据失败,请与管理员联系!");
           }
           finally
           {
        	   SfDBResource.freeConnection(conn);
           }
        }
       
        /**
         * 删除再就业减免申报表记录
         * @param VoPackage
         * @throws com.ttsoft.framework.exception.BaseException
         */
      
        private void doDeleteSbData(VOPackage vo) throws BaseException
        {
        	Connection conn = null;
            Map dataMap =(Map)vo.getData();
            String jsjdm = (String)dataMap.get("JSJDM");
            String bbnd = (String)dataMap.get("BBND");
            String bbq = (String)dataMap.get("BBQ");
            PreparedStatement pstmt = null;
            ResultSet zbresult = null;
        	String jzbz = null;
            try
            {   
                conn = SfDBResource.getConnection();
            	//查询是否已存在申报数据，帐务标识是否为"000000"
            	String queryzbSql = "SELECT * FROM SBDB.SB_JL_ZJYJMSBZ"
                    + " WHERE JSJDM = ?  "
                    + " AND  BBND = ? AND BBQ = ? ";
            	pstmt = conn.prepareStatement(queryzbSql);
            	pstmt.setString(1, jsjdm);
            	pstmt.setString(2, bbnd);
            	pstmt.setString(3, bbq);
                zbresult = pstmt.executeQuery();
               
                	while (zbresult.next()) 
                    {   
                		jzbz=zbresult.getString("JZBZ");
                    }
               
               if(jzbz!=null&&jzbz.equals("000000")){
            	  
                //删除再就业减免明细表12的sql语句
            	String deletemx12Sql = "DELETE FROM SBDB.SB_JL_ZJYJMSBMX12"
                    + " WHERE JSJDM = ?  "
                    + " AND  BBND = ? AND BBQ = ? ";
            	pstmt = conn.prepareStatement(deletemx12Sql);
            	pstmt.setString(1, jsjdm);
            	pstmt.setString(2, bbnd);
            	pstmt.setString(3, bbq);
            	pstmt.executeUpdate();                
            	
               //删除再就业减免明细表3的sql语句
            	
            	String deletemx3Sql = "DELETE FROM SBDB.SB_JL_ZJYJMSBMX3"
                    + " WHERE JSJDM = ?  "
                    + " AND  BBND = ? AND BBQ = ? ";
            	pstmt = conn.prepareStatement(deletemx3Sql);
            	pstmt.setString(1, jsjdm);
            	pstmt.setString(2, bbnd);
            	pstmt.setString(3, bbq);
            	pstmt.executeUpdate();   
            	
            	
            	// 删除再就业减免申报主表记录
            	String deletezbSql = "DELETE FROM SBDB.SB_JL_ZJYJMSBZ"
                    + " WHERE JSJDM = ?  "
                    + " AND  BBND = ? AND BBQ = ? ";
            	pstmt = conn.prepareStatement(deletezbSql);
            	pstmt.setString(1, jsjdm);
            	pstmt.setString(2, bbnd);
            	pstmt.setString(3, bbq);
            	pstmt.executeUpdate();  
           
                }
            }
            catch(Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex, "删除再就业减免申报数据错误");
            }
            finally
            {
                try {
                	   pstmt.close();
                	   SfDBResource.freeConnection(conn);
                     } 
                catch(Exception e) {}
            }
       }
        
      
}
