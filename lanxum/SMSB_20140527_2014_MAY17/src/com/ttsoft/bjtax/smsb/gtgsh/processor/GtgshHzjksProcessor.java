/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.math.*;
import java.sql.*;
import java.util.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.gtgsh.web.*;
import com.ttsoft.bjtax.smsb.lszs.web.LszsHzjksForm;
import com.ttsoft.bjtax.smsb.model.client.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;

/**
 * 把完税证汇总成缴款书。分一税一票和一税多票两种
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 完税证汇总缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */

public class GtgshHzjksProcessor
    implements Processor
{
	//每张票中的最大预算科目
    private static final int EVE_PAGE_YSKM = 14;
    
    public GtgshHzjksProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        Object result = null;

            /**@todo Implement this com.ttsoft.framework.processor.Processor method*/
        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION:
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result =doFp(vo);;
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_UPDATEACTION:
                result = doUpdate(vo);
                break;
            case CodeConstant.SMSB_HZSBJKDACTION:

                //汇总申报缴款单
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_HZJKSACTION:

                //汇总缴款书
                result = doQuery(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }

    /**
     * 页面初始化
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        GtgshHzjksForm pf = new GtgshHzjksForm();
        pf = (GtgshHzjksForm) vo.getData();
        //汇总日期范围，缺省是汇总当天的信息
        pf.setHzksrq(SfDateUtil.getDate());
        pf.setHzjsrq(SfDateUtil.getDate());

        try
        {
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //从登记接口中获得信息
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }

            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm()); //税务机关组织机构代码
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //税务机关组织机构名称
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
        }
        return pf;
    }

    /**
     * 查询
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        ArrayList dataList = new ArrayList();
        Connection conn = null;
        double hjje = 0;
        GtgshHzjksForm pf = new GtgshHzjksForm();
        pf = (GtgshHzjksForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //从登记接口中获得信息
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }

            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm()); //税务机关组织机构代码
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //税务机关组织机构名称

            Vector tempVector = new Vector();
            tempVector.addElement("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            tempVector.addElement("hzksrq>=to_date('" + pf.getHzksrq()
                                  + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
            tempVector.addElement("hzjsrq<=to_date('" + pf.getHzjsrq()
                                  + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
            tempVector.addElement("jsjdm='" + pf.getJsjdm() + "'"); //Modified by lufeng 2003-12-24
            tempVector.addElement("jkpzh like '" + pf.getJsjdm() + "%'"); //Modified by lufeng 2003-12-24
            tempVector.addElement("sbhzdh='" + pf.getSbhzdh() + "'");
            tempVector.addElement("1=1 order by jkpzh asc");

            List tempList = da.query(Gtgshwszsbhz.class, tempVector);

            for (int i = 0; i < tempList.size(); i++)
            {
                Gtgshwszsbhz hz = (Gtgshwszsbhz) tempList.get(i);
                HashMap map = new HashMap();
                //判断一票一税 / 一票多税 ，末位为0则为一票一税，否则为一票多税。
                String strJkpzh = hz.getJkpzh();
                map.put("jkpzh_ypds",
                        strJkpzh.substring(0, strJkpzh.length() - 1));
                //显示的缴款凭证号，只显示前17位
                strJkpzh = strJkpzh.substring(strJkpzh.length() - 1,
                                              strJkpzh.length());
                if (strJkpzh.equals("0"))
                {
                    map.put("jkpzh", hz.getJkpzh());
                }
                else
                {
                    //对于一票多税，最后一位不显示
                    map.put("jkpzh", map.get("jkpzh_ypds"));
                }
                map.put("sjse", String.valueOf(hz.getSjse()));
                hjje = hjje
                       + StringUtil.getDouble( (hz.getSjse()).toString(), 0);
                dataList.add(map);
            }
            //整理一票多税的列表，以便显示
            dataList = (ArrayList) JksUtil.getYpdsList(dataList);
            pf.setDataList(dataList);
            pf.setHjzs(String.valueOf(dataList.size()));
            pf.setHjje(StringUtil.getCurrency(String.valueOf(hjje)));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;

    }

    
    /**
     * 处理Oracle SQL in超过1000的方法
     * @param ids 所有id
     * @param 每组id的数量
     * @param in or not in
     * @return 分组后的含in sql
     */
    private  String  getOracleSQLIn(List ids, int count, String field,String type) {  

	    count = Math.min(count, 1000);  

	    int len = ids.size();  

	    int size = len % count;  

	    if (size == 0) {  

	        size = len / count;  

	    } else {   

	        size = (len / count) + 1;  

	    }  

	    StringBuffer builder = new StringBuffer();  

	    for (int i = 0; i < size; i++) {  

	        int fromIndex = i * count;  

	        int toIndex = Math.min(fromIndex + count, len);  

	        //System.out.println(ids.subList(fromIndex, toIndex));  

	       // String productId = "";//StringUtils.defaultIfEmpty(StringUtils.join(ids.subList(fromIndex, toIndex), "','"), ""); 
	        List tempList=ids.subList(fromIndex, toIndex);
	        
	        StringBuffer buf = new StringBuffer();
	        for(int k=0;k<tempList.size();k++){
	        	//HashMap map=(HashMap)tempList.get(k);
	        	if(k!=0){
	        		buf.append(",");
	        	}
	        	
	        	buf.append("'").append((String)tempList.get(k)).append("'");
	        }

	        if (i != 0) {  

	            builder.append(" or ");  

	        }  

	        builder.append(field).append(" "+type+" (").append(buf.toString()).append(")");  

	    }  
	    return builder.toString();
	}  
    
    
    
    /**
     * 分票汇总
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doFp (VOPackage vo)
        throws BaseException
    {
    	
    	try{
    		
    	   //完税证和预算科目代码List
    	   List list=getWszhAndYskmdms(vo);
    	   System.out.println("GtgshHzjksProcessor.doFp##################list.size()="+list.size());
    	   if(list.size()==0){
    	     throw new ApplicationException("没有符合条件的完税证可以汇总！");
    	   }else{
    	    recursionFp(list,vo);
    	   }
    	}catch(Exception ex){
    		 ex.printStackTrace();
             throw ExceptionUtil.getBaseException(ex);
    	}
    	GtgshHzjksForm pf = (GtgshHzjksForm) vo.getData();
    	return pf;
    }
    
    /**
     * 分票汇总
     * @param list 预算科目数据集对象
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
	private void recursionFp(List list,VOPackage vo)  throws BaseException{
		try{
		
			System.out.println("GtgshHzjksProcessor.recursionFp##################list.size()="+list.size());
			//有需要汇总的完税证
			if(list.size()>0){
				    List xhzWszxhList=getXhzWszxhList(list);
				    System.out.println("GtgshHzjksProcessor.recursionFp##################xhzWszxhListt.size()="+xhzWszxhList.size());
                	//取得本次需要汇总的完税证号集合 sql
                	String hzsql=getOracleSQLIn(xhzWszxhList,1000,"a.wszxh"," in");
                	System.out.println("GtgshHzjksProcessor.recursionFp##################hzsql="+hzsql);
                	//进行汇总操作
                	doSave(vo,hzsql);
                	//取得下一次完税证和预算科目代码List
                	List nextList=getWszhAndYskmdms(vo);
                	System.out.println("GtgshHzjksProcessor.recursionFp##################nextlist()="+nextList.size());
                	//进行下一次分票操作（递归调用）
                	if(nextList.size()>0){
                		recursionFp(nextList,vo);
                	}
			}
		}catch(Exception e){
			 e.printStackTrace();
			throw new ApplicationException("完税证汇总分票处理出错！");
		}
	}
    
	  /**
     * 获取需要汇总的完税证序号 集合方法
     * @param list 数据集对象（完税证序号和其对应的预算科目代码集合）
     * @return 完税证序号List对象
     * @throws BaseException
     */
	 private List getXhzWszxhList(List list) throws BaseException{
		 ArrayList reList=new ArrayList();
		 HashMap yskmdmMap = new HashMap();
		 for(int i=0;i<list.size();i++){
			  //完税证序号
			  String wszxh=(String)((HashMap)list.get(i)).get("wszxh");
			  //System.out.println("GtgshHzjksProcessor.getXhzWszxhList##################wszxh="+wszxh);
			  //预算科目代码List
			  List yskmdmList=(List)((HashMap)list.get(i)).get(wszxh);
			  //System.out.println("GtgshHzjksProcessor.getXhzWszxhList##################yskmdmList.size()="+yskmdmList.size());
			  HashMap  tempYskmdmMap=(HashMap)yskmdmMap.clone();
			  for(int j=0;j<yskmdmList.size();j++){
				  tempYskmdmMap.put((String)yskmdmList.get(j), (String)yskmdmList.get(j));
			  }
			  if(tempYskmdmMap.size()<=EVE_PAGE_YSKM){	
				  reList.add(wszxh);
				  yskmdmMap=tempYskmdmMap;
			  }
		 }
		 return reList;
	 }	 
	
   /**
     * 获取完税证 和对应预算科目的 集合方法
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 完税证List对象
     * @throws BaseException
     */
    private List getWszhAndYskmdms(VOPackage vo) throws BaseException{
    	GtgshHzjksForm pf = (GtgshHzjksForm) vo.getData();
    	Connection conn = null;
    	ResultSet rs = null;
    	ResultSet rs1 = null;
    	ArrayList reList=new ArrayList();
    	//StringBuffer sqlBuf=new StringBuffer();
    	String strSql="";
    	String timeCon = ""; //时间条件
        String whereCon = ""; //where 条件
        String qxdm = ""; //区县代码
       
        String hzksrq = pf.getHzksrq();
        String hzjsrq = pf.getHzjsrq();
        String hzlx = pf.getHzlx();
    	try
         {
    		UserData ud = (UserData) vo.getUserData();
		    qxdm = InterfaceDj.getQxdm(ud);
		   //时间条件
	        if (hzksrq != null && !hzksrq.equals(""))
	        {
	            timeCon = timeCon + " and a.cjrq>=to_date('" + hzksrq +
	                      " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
	        }
	        if (hzjsrq != null && !hzjsrq.equals(""))
	        {
	            timeCon = timeCon + " and a.cjrq<=to_date('" + hzjsrq +
	                      " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
	        }

	        //本人汇总 1 / 本单位汇总 0
	        if (hzlx.equals(CodeConstant.HZFS_DW))
	        {
	            whereCon = " and a.jsjdm='" + pf.getJsjdm() + "'";
	        }
	        if (hzlx.equals(CodeConstant.HZFS_GR))
	        {
	            whereCon = " and a.lrr='" + pf.getLrr() + "'";

	        }
           
            
	        strSql = "select a.wszxh,a.nsrjsjdm" +
            " from sbdb.sb_jl_gtgshwszz a" +
            " where " +
            " a.qxdm='" + qxdm + "' " + timeCon +
            " and a.clbjdm = '" + CodeConstant.SMSB_WSZ_PRINT + "'" + //已打印
            " and (a.sbhzdh = '' or a.sbhzdh is null) " + whereCon ;
    		
	  
            System.out.println("GtgshHzjksProcessor.getWszhAndYskmdms##########sql="+strSql); 		
            
    		 conn = SfDBResource.getConnection();
             SfDBAccess da = new SfDBAccess(conn);
             rs = da.querySQL(strSql);
             while (rs.next())
             { 
            	 
                 HashMap remap = new HashMap();
                 strSql = "select distinct(b.yskmdm) yskmdm" +
                 " from sbdb.sb_jl_gtgshwszmx b " +
                 " where " +
                 " b.nsrjsjdm='"+ rs.getString("nsrjsjdm")+"' and b.wszxh='"+rs.getString("wszxh")+"'"; 
                 //System.out.println(reList.size()+"GtgshHzjksProcessor.getWszhAndYskmdms##########sql="+strSql);
                 rs1 = da.querySQL(strSql);
                 ArrayList yskmList=new ArrayList();
                 while (rs1.next())
                 {
                	 yskmList.add(rs1.getString("yskmdm"));
                 }
                 //完税证序号
                 remap.put("wszxh", rs.getString("wszxh"));
                 //完税证序号对应的预算科目
                 remap.put(rs.getString("wszxh"), yskmList);
                 reList.add(remap);                             
             }
             if(rs1!=null){
                 rs1.close();
              }
             rs.close();
         }
    	catch (Exception ex)
        {
    		 throw new ApplicationException("查询预算科目出错！");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
   return reList;
	}
    
    /**
     * 查询，根据条件查询出还没有汇总的完税证号，然后进行汇总
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo,String insql)
        throws BaseException
    {
        Connection conn = null;
        double hjje = 0; //合计金额
        int intCount = 0; //张数
        String hzff = "";
        String strSql = ""; //拼凑sql
        String tableName = "";  //表名称
        String timeCon = ""; //时间条件
        String whereCon = ""; //where 条件
        String qxdm = ""; //区县代码

        List dataList = new ArrayList();
        List hzList = new ArrayList();
        List sbjkzbList = new ArrayList();
        List sbjkmxList = new ArrayList();

        GtgshHzjksForm pf = (GtgshHzjksForm) vo.getData();
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String hzlx = pf.getHzlx();
        String hzksrq = pf.getHzksrq();
        String hzjsrq = pf.getHzjsrq();
        int ypys = StringUtil.getInt(pf.getYpys(), 1); //得到一税一票/一税多票标识
        //定义Ormap
        Gtgshwszmx wszmx = new Gtgshwszmx();
        Gtgshwszz wszz = new Gtgshwszz();
        Sbjkzb sbjkzb = new Sbjkzb();
        Sbjkmx sbjkmx = new Sbjkmx();

        //时间条件
        if (hzksrq != null && !hzksrq.equals(""))
        {
            timeCon = timeCon + " and a.cjrq>=to_date('" + hzksrq +
                      " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
        }
        if (hzjsrq != null && !hzjsrq.equals(""))
        {
            timeCon = timeCon + " and a.cjrq<=to_date('" + hzjsrq +
                      " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }

        //本人汇总 1 / 本单位汇总 0
        if (hzlx.equals(CodeConstant.HZFS_DW))
        {
            whereCon = " and a.jsjdm='" + pf.getJsjdm() + "'";
        }
        if (hzlx.equals(CodeConstant.HZFS_GR))
        {
            whereCon = " and a.lrr='" + pf.getLrr() + "'";

        }
        if(insql.length()>0){
        	insql=" and ("+insql+")";
        }
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //得到区县代码
            qxdm = InterfaceDj.getQxdm(ud);
            
            
            /*
            strSql = "select distinct b.szsmdm,b.jsjdm, " +
                     " to_char(b.skssksrq,'yyyymmdd') skssksrq, " +
                     " to_char(b.skssjsrq,'yyyymmdd') skssjsrq, b.szdm," +
                     " sum(b.sjse) sjse,sum(b.kssl) kssl,sum(b.jsje) jsje " +
                     " from sbdb.sb_jl_gtgshwszz a, sbdb.sb_jl_gtgshwszmx b " +
                     " where a.wszh = b.wszh and a.ndzb=b.ndzb " +
                     " and a.qxdm='" + qxdm + "' " + whereCon + timeCon +
                     " and a.clbjdm = '" + CodeConstant.SMSB_WSZ_PRINT + "'" + //已打印
                     " and (a.sbhzdh = '' or a.sbhzdh is null) " +
                     " and a.nsrjsjdm = b.nsrjsjdm and a.wszxh = b.wszxh " +
                     " group by b.szsmdm, b.jsjdm, b.skssksrq, b.skssjsrq, b.szdm " +
                     " order by b.szsmdm asc";
            */
            
            
            strSql = "with t as (select b.szsmdm,b.jsjdm," +
	   		 " min(b.skssksrq) over(partition by b.szdm,b.yskmdm) skssksrq, " +
	   		 " max(b.skssjsrq) over(partition by b.szdm,b.yskmdm) skssjsrq, b.szdm," +
	   		 " b.sjse,b.kssl,b.jsje" +
	   		 " from sbdb.sb_jl_gtgshwszz a, sbdb.sb_jl_gtgshwszmx b " +
	   		 " where a.wszh = b.wszh and a.ndzb = b.ndzb " +
	   		 " and a.qxdm='" + qxdm + "' " + timeCon +
	   		 " and a.clbjdm = '" + CodeConstant.SMSB_WSZ_PRINT + "'" + //已打印
	   		 " and (a.sbhzdh = '' or a.sbhzdh is null) " + whereCon +
	   		 " and a.nsrjsjdm=b.nsrjsjdm and a.wszxh=b.wszxh"+insql+" ) " +
	   		 " select szsmdm,jsjdm, to_char(skssksrq,'yyyymmdd') skssksrq, to_char(skssjsrq,'yyyymmdd') skssjsrq, szdm, " +
	   		 " sum(sjse) sjse, sum(kssl) kssl, sum(jsje) jsje " +
	   		 " from t" +
	      	 " group by t.szsmdm, t.jsjdm, t.skssksrq, t.skssjsrq,t.szdm " +
	   	     " order by t.szsmdm asc";
            
      System.out.println("GtgshHzjksProcessor.doSave##########sql="+strSql); 		
            
            
            
            
            System.out.println("查询可汇总数据＝＝＝＝＝＝＝＝＝＝＝"+strSql);
            try
            {
                ResultSet rs = da.querySQL(strSql);
                double sjse = 0;
                while (rs.next())
                {
                    HashMap map = new HashMap();
                    //JksUtil.getJkpzh(hzlx,java.sql.Date.valueOf(hzjsrq))
                    map.put("jsjdm", rs.getString("jsjdm"));
                    map.put("szsmdm", rs.getString("szsmdm"));
                    map.put("szdm", rs.getString("szdm"));
                    map.put("skssksrq", rs.getString("skssksrq"));
                    map.put("skssjsrq", rs.getString("skssjsrq"));
                    map.put("sjse", String.valueOf(rs.getBigDecimal("sjse")));
                    map.put("kssl", String.valueOf(rs.getBigDecimal("kssl")));
                    map.put("jsje", String.valueOf(rs.getBigDecimal("jsje")));
                    sjse = sjse + rs.getDouble("sjse");
                    sbjkmxList.add(map);
                }
                rs.close();
                //填写申报缴款主表
                if (sbjkmxList.size() <= 0)
                {
                    pf.setDataList(dataList);
                    pf.setSbhzdh("");
                    pf.setHjje("0");
                    pf.setHjzs("0");
                    throw new ApplicationException("没有符合条件的完税证可以汇总！");
                }
                else
                {
                    HashMap map = (HashMap) sbjkmxList.get(0);
                    //sbjkzb.setYhdm("");临时一税一票标识
                    sbjkzb.setJsjdm(String.valueOf(map.get("jsjdm")));
                    sbjkzb.setSjje(new BigDecimal(String.valueOf(sjse)));
                    //补充主表信息
                    HashMap mapDJ = new HashMap();
                    try
                    {
                        mapDJ = (HashMap) InterfaceDj.getDjInfo(sbjkzb.getJsjdm(),
                            ud);
                    }
                    catch (Exception ex1)
                    {
                        ex1.printStackTrace();
                        throw ExceptionUtil.getBaseException(ex1);
                    }
                    //Modified by lufeng 2003-12-09
                    SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");
                    if (jbsj == null)
                    {
                        throw new ApplicationException("获取登记信息出错！");
                    }

                    //登记注册类型，如果是税务所，使用默认值
                    if (hzlx.equals(CodeConstant.HZFS_DW))
                    {
                        sbjkzb.setDjzclxdm(CodeConstant.ZRR_JKS_DJZCLXDM); //默认410
                        sbjkzb.setDjzclxmc(jbsj.getDjzclxmc());
                        //国家标准行业代码
                        sbjkzb.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //默认8190
                        sbjkzb.setGjbzhymc(jbsj.getGjbzhymc());
                    }
                    else
                    { //个人汇总使用
                        sbjkzb.setDjzclxdm(jbsj.getDjzclxdm()); //不默认
                        sbjkzb.setDjzclxmc(jbsj.getDjzclxmc());
                        //国家标准行业代码
                        sbjkzb.setGjbzhydm(jbsj.getGjbzhydm()); //不默认
                        sbjkzb.setGjbzhymc(jbsj.getGjbzhymc());
                    }
                    //税务机关组织机构
                    sbjkzb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
                    sbjkzb.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
                    //征收机关
                    sbjkzb.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
                    sbjkzb.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
                    //隶属关系
                    sbjkzb.setLsgxdm(jbsj.getLsgxdm());
                    sbjkzb.setLsgxmc(jbsj.getLsgxmc());
                    //经营地址联系电话
                    sbjkzb.setJydzlxdm(jbsj.getJydzlxdm());
                    //汇总生成缴款书不插入银行信息 Modifyed by wuyouzhi 2006.2.7
//                    //得到银行信息//Modified by lufeng 2003-12-09
//                    ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
//                    for (int i = 0; i < dmList.size(); i++)
//                    {
//                        YHZH yhzh = (YHZH) dmList.get(i);
//                        if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
//                        {
//                            sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
//                            sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
//                            sbjkzb.setZh(yhzh.getZh()); //帐户
//                            break;
//                        }
//                    }

                    //录入人
                    sbjkzb.setLrr(pf.getLrr());
                    //申报日期
                    sbjkzb.setSbrq(nowTime);
                    //创建日期
                    sbjkzb.setCjrq(nowTime); //创建日期
                    sbjkzb.setLrrq(nowTime); //录入日期
                    sbjkzb.setQxdm(qxdm); //区县代码
                    //数据来源
                    sbjkzb.setSjly(CodeConstant.SMSB_SJLY_GTGSHHZ);
                    //税款类型代码
                    sbjkzb.setSklxdm(CodeConstant.SKLXDM_GTGSH); //默认为120

                    sbjkzb.setRkje(new BigDecimal("0"));
                    sbjkzb.setLrr(pf.getLrr());
                    sbjkzb.setClbjdm(CodeConstant.CLBJDM_WCL); //申报 处理标记代码

                    //申报方式代码
                    sbjkzb.setFsdm(CodeConstant.FSDM_SMSB);
                    //帐务标识,（即 zwbs第18～19位）‘01’现金缴款,
                    sbjkzb.setZwbs("00000000000000000010");
                } //End of if
            }
            catch (Exception sqlEx)
            {
                sqlEx.printStackTrace();
                throw ExceptionUtil.getBaseException(sqlEx);
            }
            finally
            {
            } //End of try

            if (sbjkmxList.size() > 0)
            {
                //调用函数进行插入操作
                //循环生成缴款信息，并调用申报缴款方法进行数据更新,返回缴款信息
                JksUtil jksUtil = new JksUtil();
                //一税一票 /一税多票，多个科目
                try
                {
                    if (ypys == CodeConstant.PRINT_YPYS)
                    {
                        sbjkzbList = jksUtil.getJkDataLS(sbjkzb, sbjkmxList,
                            CodeConstant.PRINT_YPYS);
                    }
                    else
                    {
                        sbjkzbList = jksUtil.getJkDataLS(sbjkzb, sbjkmxList,
                            CodeConstant.PRINT_YPDS_KM);
                    }
                }
                catch (Exception ex2)
                {
                    ex2.printStackTrace();
                    throw new ApplicationException("更新申报缴款信息出错！");
                }

                String sbhzdh = jksUtil.getSequenceOfSbhzd(conn); //获取申报汇总单号
                //把汇总数据写入完税证汇总表。
                DeclareInfor dclInfo = new DeclareInfor();
                if (ypys == CodeConstant.PRINT_YPYS)
                { //一票一税
                    for (int i = 0; i < sbjkzbList.size(); i++)
                    {
                        List hzTmpList = new ArrayList();
                        dclInfo = (DeclareInfor) sbjkzbList.get(i);
                        sbjkzb = (Sbjkzb) dclInfo.getSbjkzb();
                        hjje = hjje
                               + StringUtil.getDouble( (sbjkzb.getSjje()).toString(),
                            0);
                        //写入汇总表
                        try
                        {
                            hzTmpList = (List) updateHZ(da, sbjkzb, sbhzdh,
                                nowTime, pf);
                        }
                        catch (Exception ex)
                        {
                            throw ExceptionUtil.getBaseException(ex);
                        }

                        dataList = (List) ListUtils.union(dataList, hzTmpList);
                    }
                }
                else if (ypys == CodeConstant.PRINT_YPDS_KM)
                { //一票多税

                    for (int i = 0; i < sbjkzbList.size(); i++)
                    {
                        List onePageList = (List) sbjkzbList.get(i);
                        List tmpList = new ArrayList();
                        for (int j = 0; j < onePageList.size(); j++)
                        {
                            List hzTmpList = new ArrayList();
                            dclInfo = (DeclareInfor) onePageList.get(j);
                            sbjkzb = (Sbjkzb) dclInfo.getSbjkzb();
                            hjje = hjje
                                   + StringUtil.getDouble( (sbjkzb.getSjje()).toString(),
                                0);
                            try
                            {
                                hzTmpList = (List) updateHZ(da, sbjkzb, sbhzdh,
                                    nowTime, pf);
                            }
                            catch (BaseException ex4)
                            {
                                throw ExceptionUtil.getBaseException(ex4);
                            }
                            tmpList = (List) ListUtils.union(tmpList, hzTmpList);
                        } //End of inside loop
                        //汇总每个外层循环的所有信息
                        dataList = (List) ListUtils.union(dataList, tmpList);
                    } //End of outside loop
                } //End of if

                //回填汇总单号到
                strSql = "update sbdb.sb_jl_gtgshwszz a set a.sbhzdh='"
                         + sbhzdh + "'," +
                         " a.lrrq=sysdate " +
                         " where a.qxdm='" + qxdm + "' " + timeCon + whereCon +
                         " and a.clbjdm='" + CodeConstant.SMSB_WSZ_PRINT + "'" +
                         " and (a.sbhzdh = '' or a.sbhzdh is null) "+insql;
                try
                {
                    da.updateSQL(strSql);
                }
                catch (BaseException ex3)
                {
                    ex3.printStackTrace();
                    throw new ApplicationException("回填汇总单号出错！");
                }

                //整理一票多税的列表，以便显示
                ArrayList detailList = (ArrayList) dataList;
                detailList = (ArrayList) JksUtil.getYpdsList(detailList);
                //回传dataList
                pf.setDataList(detailList);
                pf.setSbhzdh(sbhzdh);
                
                pf.setHjje(StringUtil.getCurrency(String.valueOf(hjje)));
                pf.setHjzs(String.valueOf(dataList.size()));
                System.out.println("GtgshHzjksProcessor.doSave##########sbjkzb.getSbbh()="+sbjkzb.getSbbh()); 
                pf.setSbbh(sbjkzb.getSbbh());
                
                //设置申报编号 和 申报汇总单号
                HashMap bhMap = new HashMap();
                bhMap.put("sbbh", sbjkzb.getSbbh());
                bhMap.put("sbhzdh", sbhzdh);
                bhMap.put("hjje", pf.getHjje());
               // bhMap.put("hjzs", pf.getHjzs());
                bhMap.put("dataList", pf.getDataList());
                pf.getBhList().add(bhMap);
                
            } //End of if

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * 删除
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 更新
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 更新汇总表信息，当汇总成功后需要回写汇总表
     * @param sfda 数据库操作对象
     * @param sbjkzb 申报缴款主表对象
     * @param sbhzdh 申报汇总单号
     * @param nowTime 当前时间
     * @param pf 当前页面的Form对象
     * @return 汇总后生成的缴款书列表
     * @throws BaseException
     */
    private List updateHZ (SfDBAccess sfda, Sbjkzb sbjkzb, String sbhzdh,
                           Timestamp nowTime, GtgshHzjksForm pf)
        throws
        BaseException
    {

        List tempList = new ArrayList();
        Gtgshwszsbhz hz = new Gtgshwszsbhz();
        try
        {
            //sbjkzb = (Sbjkzb) dclInfo.getSbjkzb();
            hz.setSbhzdh(sbhzdh);
            hz.setJsjdm(sbjkzb.getJsjdm());
            hz.setJkpzh(sbjkzb.getJkpzh());
            hz.setHzrq(nowTime); //汇总日期
            hz.setCjrq(nowTime); //创建日期
            hz.setLrrq(nowTime); //录入日期
            hz.setQxdm(sbjkzb.getQxdm()); //区县代码
            hz.setSjse(sbjkzb.getSjje());
            hz.setHzksrq(Timestamp.valueOf(setFormatDate(pf.getHzksrq()) +
                                           " 00:00:00"));
            hz.setHzjsrq(Timestamp.valueOf(setFormatDate(pf.getHzjsrq()) +
                                           " 23:59:59"));
            hz.setClbjdm(CodeConstant.CLBJDM_WCL); //申报 处理标记代码
            hz.setSwjgzzjgdm(pf.getSwjgzzjgdm());
            hz.setLrr(pf.getLrr());
            //设置年度
            hz.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));

            //本人汇总 / 本单位汇总
            if ( (pf.getHzlx()).equals(CodeConstant.HZFS_DW))
            {
                hz.setHzfs(CodeConstant.HZFS_DW);
            }
            if ( (pf.getHzlx()).equals(CodeConstant.HZFS_GR))
            {
                hz.setHzfs(CodeConstant.HZFS_GR);
            }
            //写数据
            sfda.insert(hz);
            HashMap map = new HashMap();
            String strJkpzh = sbjkzb.getJkpzh();
            map.put("jkpzh_ypds", strJkpzh.substring(0, strJkpzh.length() - 1));
            //一票一税和一票多税
            int ypys = StringUtil.getInt(pf.getYpys(), 1);
            if (ypys == CodeConstant.PRINT_YPYS)
            {
                map.put("jkpzh", sbjkzb.getJkpzh());
            }
            else
            {
                map.put("jkpzh", map.get("jkpzh_ypds"));
            }
            map.put("sjse", String.valueOf(sbjkzb.getSjje()));
            tempList.add(map);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("保存完税证汇总信息出错！");
        }
        return tempList;
    } //End of updateHZ

    /**
     * 时间转换函数。getFormatDate函数的逆运算
     * 如：20080808 00:00:00 转化为2008-08-08 00:00:00
     * 或者：20080808 转化为2008-08-08
     * @param inTime 日期或日期时间字符串
     * @return 格式化后的日期字符串
     * @throws BaseException
     */
    private static String setFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        try
        {
            String tempStr = inTime.substring(0, 4);
            String defStr = inTime.substring(4, 6);
            result = tempStr + "-" + defStr + "-" + inTime.substring(6);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    } //End of setFormatDate

}
