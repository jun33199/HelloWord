package com.ttsoft.bjtax.smsb.zjyjmsb.processor;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.wynsk.web.WynskcxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.zjyjmsb.cx.web.ZjyjmcxForm;
import com.ttsoft.bjtax.smsb.zjyjmsb.web.ZjyjmsbConstant;
import com.ttsoft.bjtax.smsb.zjyjmsb.cx.ZjycxlxVO;

/**
 * <p>Title: 北京地税综合管理系统--上门申报</p>
 * <p>Description: 再就业减免税申报查询Processor</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四一安信</p>
 * @author qinwei
 * @version 1.0
 */

public class ZjyjmcxProcessor 
        implements Processor{

	public ZjyjmcxProcessor(){
		
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
           return doShow(vo);
          case CodeConstant.SMSB_QUERYACTION: 
           return doQuery(vo);
          case CodeConstant.SMSB_TOEXCELACTION: 
           return doSaveExcel(vo);
          default:
           throw new SystemException("no such mothod!");
                
        }
    }
 /**
  * 初始化方法
  * @param vo Value Object
  * @return PageForm
  * @exception BaseException BaseException
  */
 private Object doShow(VOPackage vo) throws BaseException {
   //1.申明句柄
   ZjyjmcxForm zjycxFrom = null;
   UserData ud = null;
   SfDBAccess da = null;
   Connection conn = null;
   ArrayList datalist = new ArrayList();
   //2.初始化VO数据对象
   try {
     ud = vo.getUserData();
     zjycxFrom = (ZjyjmcxForm) vo.getData();
   }
   catch (Exception e) {
     e.printStackTrace();
     throw ExceptionUtil.getBaseException(e);
   }
   //3.开始业务
   try {
     ///3.1.初始化工具
     conn = SfDBResource.getConnection();
     da = new SfDBAccess(conn);
     ///3.2.根据ud的用户级别决定税务机关组织机构代码（区县）的值列表
     zjycxFrom.setFjList(this.getQxList(da, ud));
     ///3.2.根据ud的用户级别决定税务机关组织机构代码（税务所）的值列表
     zjycxFrom.setSwsList(this.getSwsList(da, ud));
     ///3.3.设置减免税类别
     zjycxFrom.setJmslblist(this.getJmslbList());
     ///3.4.设置税款类型
     zjycxFrom.setJmszArray(this.getJmszlist());
     ///3.5.设置季度类型
     zjycxFrom.setJdlist(this.getJdlist());
     //设置结果集合
     zjycxFrom.setDataList(datalist);
   }
   catch (Exception e) {
     e.printStackTrace();
     throw ExceptionUtil.getBaseException(e);
   }
   finally {
     SfDBResource.freeConnection(conn);
   }
   return zjycxFrom;
 }
 /**
  * 查询后台处理
  * @param vo 数据集对象（包括Form和UserData对象）
  * @return 当前页面对应的Form对象
  * @throws BaseException
  */
 
 private Object doQuery(VOPackage vo) throws BaseException
 {   
	 
	 ZjyjmcxForm zjycxFrom = (ZjyjmcxForm) vo.getData();
	 List cxjg = new ArrayList();
	 List datalist = new ArrayList();
     SfDBAccess da = null;
     Connection conn = null;
     UserData userData = vo.getUserData();
     try
     {   
         conn = SfDBResource.getConnection();
         da = new SfDBAccess(conn);
         ///3.2.根据ud的用户级别决定税务机关组织机构代码（区县）的值列表
         zjycxFrom.setFjList(this.getQxList(da, userData));
         ///3.2.根据ud的用户级别决定税务机关组织机构代码（税务所）的值列表
         zjycxFrom.setSwsList(this.getSwsList(da, userData));
         ///3.3.设置减免税类别
         zjycxFrom.setJmslblist(this.getJmslbList());
         ///3.4.设置税款类型
         zjycxFrom.setJmszArray(this.getJmszlist());
         ///3.5.设置季度类型
         zjycxFrom.setJdlist(this.getJdlist());
         
         cxjg = queryZJY(da,zjycxFrom,userData);
         
         zjycxFrom.setTotalpage(this.getPageTotalCount(cxjg.size()));
         System.out.println("cxjg.size="+cxjg.size()+"totalpage"+zjycxFrom.getTotalpage());
         if (null == cxjg) {
        	 cxjg = new ArrayList();
           }
         datalist = this.getPageDataList(cxjg, zjycxFrom);
         System.out.println("-------dataList.size:" + datalist.size());
         zjycxFrom.setDataList( (ArrayList) datalist);
     }
       catch (Exception e) {
         e.printStackTrace();
         throw ExceptionUtil.getBaseException(e);
       }
       finally {
    	     SfDBResource.freeConnection(conn);
       }
     return zjycxFrom;
 }  
 /**
  * 依据当前查询结果生成Excel文件
  * @param vo 数据集对象（包括Form和UserData对象）
  * @return 当前页面对应的Form对象
  * @throws BaseException
  */
 private Object doSaveExcel(VOPackage vo) throws BaseException {
	    //1.申明句柄
	    ZjyjmcxForm zjycxFrom = null;
	    UserData ud = null;
	    SfDBAccess da = null;
	    Connection conn = null;
	    List cxjg = null;
	    //2.初始化VO数据对象
	    try {
	      ud = vo.getUserData();
	      zjycxFrom = (ZjyjmcxForm) vo.getData();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      throw ExceptionUtil.getBaseException(e);
	    }
	    //3.开始业务
	    try {
	    	conn = SfDBResource.getConnection();
	         da = new SfDBAccess(conn);
	         ///3.2.根据ud的用户级别决定税务机关组织机构代码（区县）的值列表
	         zjycxFrom.setFjList(this.getQxList(da, ud));
	         ///3.2.根据ud的用户级别决定税务机关组织机构代码（税务所）的值列表
	         zjycxFrom.setSwsList(this.getSwsList(da, ud));
	         ///3.3.设置减免税类别
	         zjycxFrom.setJmslblist(this.getJmslbList());
	         ///3.4.设置税款类型
	         zjycxFrom.setJmszArray(this.getJmszlist());
	         ///3.5.设置季度类型
	         zjycxFrom.setJdlist(this.getJdlist());
	         
	         cxjg = queryZJY(da,zjycxFrom,ud);    

	         zjycxFrom.setDataList((ArrayList)cxjg);
	         cxjg = null;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      throw ExceptionUtil.getBaseException(e);
	    }
	    finally {
	      SfDBResource.freeConnection(conn);
	    }
	    return zjycxFrom;
 }
 /**查询再就业减免申报数据
   * @param zf 页面对象
   * @param da 数据库操作对象
   * @return 查询结果
   * @exception BaseException BaseException
   */
 private List queryZJY(SfDBAccess da, ZjyjmcxForm zjycxFrom,UserData userData) throws
          BaseException {
	 
	 List cxjg = new ArrayList();
     ResultSet rs = null;
	 try
     {         
    	 String nd = zjycxFrom.getNd();
    	 String jd = zjycxFrom.getJd();
    	 String qxdm =zjycxFrom.getQueryfj().substring(0,2);
    	 String swsdm = zjycxFrom.getQuerysws();
    	 String jsjdm = zjycxFrom.getJsjdm();
    	 String nsrmc = zjycxFrom.getNsrmc();
    	 String jmsz = zjycxFrom.getJmsz();
    	 String[] jmslb = zjycxFrom.getJmslb();
    	 System.out.println("nd="+nd+"jd="+jd+"qxdm="+qxdm+"swsdm="+swsdm);
         StringBuffer sqlString = new StringBuffer();
         sqlString.append(" SELECT jsjdm,nsrmc, SUM(xnxgsyrs),nsrlxmc, SUM(yys), SUM(grsds),SUM(jss),SUM(qysds),SUM(jyffj),");
         sqlString.append("SUM(nvl(yys,0)+nvl(grsds,0)+nvl(jss,0)+nvl(qysds,0)+nvl(jyffj,0)),sbrq,swjgzzjgmc ");
         sqlString.append(" FROM (select b.nsrmc,a.nsrlxmc,a.bbq,a.jsjdm, a.xnxgsyrs,c.sbrq,d.swjgzzjgmc,");
         sqlString.append("sum(decode(a.szdm, '02', nvl(j1, 0) + nvl(j2, 0) + nvl(j3, 0))) yys,");
         sqlString.append("sum(decode(a.szdm, '05', nvl(j1, 0) + nvl(j2, 0) + nvl(j3, 0))) grsds,");
         sqlString.append("sum(decode(a.szdm, '10', nvl(j1, 0) + nvl(j2, 0) + nvl(j3, 0))) jss,");
         sqlString.append("sum(decode(a.szdm, '30', nvl(j1, 0) + nvl(j2, 0) + nvl(j3, 0))) qysds,");
         sqlString.append("sum(decode(a.szdm, '51', nvl(j1, 0) + nvl(j2, 0) + nvl(j3, 0))) jyffj");
         sqlString.append(" from sbdb.sb_jl_zjyjmsbmx12 a,djdb.dj_jl_jbsj b,sbdb.sb_jl_zjyjmsbz c,dmdb.gy_dm_swjgzzjg d");
         sqlString.append(" where c.qxdm='").append(qxdm).append("'");
         if(!swsdm.equals("0")){
         sqlString.append(" and c.swjgzzjgdm='").append(swsdm).append("'");
         }
         sqlString.append(" and a.bbnd='").append(nd).append("'");
         sqlString.append(" and a.bbq like '").append(jd).append("'");
         if(jsjdm!=null &&!jsjdm.equals("")){
         sqlString.append(" and a.jsjdm='").append(jsjdm).append("'"); 
         }
         if(nsrmc!=null &&!nsrmc.equals("")){
         sqlString.append(" and b.nsrmc like '%").append(nsrmc).append("%'"); 
         }
         if(null!=jmslb&&jmslb.length>=1){
    		if(!"".equals(jmslb[0])){
    			 sqlString.append(" and (a.nsrlxdm='").append(jmslb[0].substring(0,1)).append("' and a.sbblxdm='")
    			          .append(jmslb[0].substring(1,2)).append("'");
    		     for(int i=1;i<jmslb.length;i++){
    			 sqlString.append(" or a.nsrlxdm='").append(jmslb[i].substring(0,1)).append("' and a.sbblxdm='")
		          .append(jmslb[i].substring(1,2)).append("'");
    	         }
    		     sqlString.append(")");
    		}
         }
    	 if(jmsz!=null &&!jmsz.equals("")){
    	 sqlString.append(" and a.szdm like '").append(jmsz).append("'"); 
    	 }
         sqlString.append(" and a.jsjdm=b.jsjdm ");
         sqlString.append(" and b.jsjdm=c.jsjdm ");
         sqlString.append(" and a.bbnd=c.bbnd ");
         sqlString.append(" and a.bbq=c.bbq ");
         sqlString.append(" and a.bblxdm=c.bblxdm ");
         sqlString.append(" and c.swjgzzjgdm=d.swjgzzjgdm ");
         sqlString.append(" group by a.nd,a.jsjdm,c.sbrq,a.nsrlxmc, a.xnxgsyrs,a.bbq,b.nsrmc,d.swjgzzjgmc) ");
         sqlString.append(" GROUP BY jsjdm,sbrq,nsrmc,nsrlxmc,swjgzzjgmc ");
         sqlString.append(" ORDER BY sbrq,jsjdm ");
         String sql = sqlString.toString();
         System.out.println(sql);
         rs =da.querySQL(sql);
         while(rs.next()){
        	 ZjycxlxVO lxVo = new ZjycxlxVO();
        	 lxVo.setJsjdm(rs.getString(1));
        	 lxVo.setNsrmc(rs.getString(2));
        	 lxVo.setXnxgsyrs(rs.getString(3));
        	 lxVo.setJmslb(rs.getString(4));
        	 lxVo.setYys(rs.getString(5));
        	 lxVo.setGrsds(rs.getString(6));
        	 lxVo.setCjs(rs.getString(7));
        	 lxVo.setQysds(rs.getString(8));
        	 lxVo.setJyffj(rs.getString(9));
        	 lxVo.setHj(rs.getString(10));
        	 lxVo.setXsjmsj(rs.getString(11).substring(0,10));
        	 lxVo.setSwjgzzjgmc(rs.getString(12));
        	 cxjg.add(lxVo);
         }
     }
         catch (SQLException e) {
             e.printStackTrace();
             throw new ApplicationException("查询再就业减免申报数据失败");
           }catch (Exception e) {
             e.printStackTrace();
             throw new ApplicationException("查询再就业减免申报数据失败");
           }
           //99.返回值
           return cxjg;
	 
 }
 /**
  * 获取区县列表
  * @param db
  * @param userData
  * @return
  * @throws BaseException
  */
 private ArrayList getQxList(SfDBAccess db, UserData userData) throws
     BaseException {
   ArrayList list = new ArrayList();
   try {
     //税务局
     String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
     StringBuffer sb = new StringBuffer();
     sb.append(
         " SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG ");
     if (!"90".equals(iQxdm)) {
       sb.append(" where SWJGZZJGDM  = '" + iQxdm + "00' ");
     }
     else {
       sb.append(" where substr(SWJGZZJGDM,3,2) = '00' ");
     }
     sb.append(" order by SWJGZZJGDM ");
     ResultSet rs = db.querySQL(sb.toString());
     while (rs.next()) {
       LabelValueBean bean = new LabelValueBean("", "");
       bean.setValue( (String) rs.getString("value"));
       bean.setLabel( (String) rs.getString("descr"));
       if ("9000".equals( (String) rs.getString("value"))) {
         //list.add(0, bean);
       }
       else {
         list.add(bean);
       }
     }
   }
   catch (SQLException e) {
     throw ExceptionUtil.getBaseException(e);
   }
   return list;
 }
 /**
  * 获取税务所列表
  * @param db
  * @param userData
  * @return
  * @throws BaseException
  */
 private ArrayList getSwsList(SfDBAccess db, UserData userData) throws
     BaseException {
   ArrayList list = new ArrayList();
   try {
     //税务局
     String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
     String yhjb = userData.getYhjb();
     String ssdwdm = userData.getSsdwdm();
     StringBuffer sb = new StringBuffer();
     sb.append(
         " SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG ");
     sb.append(
         " where substr(SWJGZZJGDM,3,2) <> '00' AND SWJGZZJGDM not like '90%' ");
     if (!"90".equals(iQxdm)) {
       sb.append(" and SWJGZZJGDM like '" + iQxdm + "%' ");
       if ("30".equals(yhjb)) {
         sb.append(" and SWJGZZJGDM ='" + ssdwdm + "'");
       }
     }
     sb.append(" order by SWJGZZJGDM ");
     if (!"30".equals(yhjb)) {
       LabelValueBean label = new LabelValueBean("*所有税务所", "0");
       list.add(label);
     }
     ResultSet rs = db.querySQL(sb.toString());
     while (rs.next()) {
       LabelValueBean bean = new LabelValueBean("", "");
       bean.setValue( (String) rs.getString("value"));
       bean.setLabel( (String) rs.getString("descr"));
       list.add(bean);
     }
   }
   catch (SQLException e) {
     throw ExceptionUtil.getBaseException(e);
   }
   return list;
 }
 //获得减免类型列表，Value=纳税人类型名称 Label第一位是Nsrlxdm,第二位是Sbblxdm
 private ArrayList getJmslbList()  {
	 ArrayList list = new ArrayList();
	 list.add(new LabelValueBean("新办企业服务型30%以上", "10"));
	 list.add(new LabelValueBean("新办企业商业纯零售30%以上", "20"));
	 list.add(new LabelValueBean("现有企业服务型30%以上", "30"));
	 list.add(new LabelValueBean("现有企业商业纯零售30%以上", "40"));
	 list.add(new LabelValueBean("主辅分离经济实体(表一)", "50"));
	 list.add(new LabelValueBean("企业吸纳下岗失业人员", "11"));
	 list.add(new LabelValueBean("主辅分离经济实体(表二)", "21"));
	 list.add(new LabelValueBean("个体经营", "31"));
	 list.add(0,new LabelValueBean("*所有类别", ""));
	 return list;
 }
 //获得减免税种代码,Value=税种名称，Lable=税种代码
 private ArrayList getJmszlist() {
	ArrayList list = new ArrayList();
	list.add(new LabelValueBean("*税种总计", "%"));
    list.add(new LabelValueBean("营业税", ZjyjmsbConstant.SZDM_YYS));
    list.add(new LabelValueBean("城市维护建设税", ZjyjmsbConstant.SZDM_JSS));
    list.add(new LabelValueBean("教育费附加", ZjyjmsbConstant.SZDM_JYFFJ));
    list.add(new LabelValueBean("个人所得税", ZjyjmsbConstant.SZDM_GRSDS));
    list.add(new LabelValueBean("企业所得税", ZjyjmsbConstant.SZDM_QYSDS));

	 
	 return list;
	 
 }
 //获得季度的列表
 private ArrayList getJdlist() {
    ArrayList list = new ArrayList();
    list.add(new LabelValueBean("1季度", "1"));
    list.add(new LabelValueBean("2季度", "2"));
    list.add(new LabelValueBean("3季度", "3"));
    list.add(new LabelValueBean("4季度", "4"));
    list.add(0,new LabelValueBean("*全年", "%"));
    return list; 		
}
 /**
  * 获取页数
  * @param rsCount 查询结果集build
  * @return 页数
  */
 private String getPageTotalCount(int rsCount) {
   //1.句柄申明
   String countTotal = "0";
   //2.开始业务
   int pageCount = 0;
   if ( (rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
     pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
   }
   else {
     pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
   }
   countTotal = String.valueOf(pageCount);
   //99.返回值
   return countTotal;
 }
 /**
  * 获取当前分页数据集
  * @param tmpList 完整数据集
  * @param pf 页面对象
  * @return 当前分页数据集
  */
 private List getPageDataList(List cxjg, ZjyjmcxForm zjycxForm) {
   //1.申明句柄
   List dataList = new ArrayList();
   //2.初始化参数值
   int startIndex = this.getPageStartIndex(zjycxForm.getNextPage(), zjycxForm.getTotalpage());
   int endIndex = this.getPageEndIndex(zjycxForm.getNextPage(), zjycxForm.getTotalpage());
   System.out.println("startIndex="+startIndex+"endIndex="+endIndex);
   //3.开始业务
   for (int i = startIndex; i < endIndex; i++) {
     if (i < cxjg.size()) {
       dataList.add(cxjg.get(i));
     }
   }
   cxjg = null;
   //99.返回值
   return dataList;
 }
 /**
  * 获取当前页结束index
  * @param nextPage 下一页
  * @param countTotal 总页数
  * @return 结束index
  */
 private int getPageEndIndex(String nextPage, String countTotal) {
   //1.句柄申明
   int iNextPage = Integer.parseInt(nextPage);
   int iCountTotal = Integer.parseInt(countTotal);
   int end = -1;
   //2.开始业务
   end = iNextPage * CodeConstant.SD_PG_LENGTH;
   //99.返回值
   return end;
 }

 /**
  * 获取当前页开始index
  * @param nextPage 下一页
  * @param countTotal 总页数
  * @return 开始index
  */
 private int getPageStartIndex(String nextPage, String countTotal) {
   //1.句柄申明
   int iNextPage = Integer.parseInt(nextPage);
   int iCountTotal = Integer.parseInt(countTotal);
   int start = -1;
   //2.开始业务
   start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
   //99.返回值
   return start;
 }
}