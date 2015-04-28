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
 * <p>Title: ������˰�ۺϹ���ϵͳ--�����걨</p>
 * <p>Description: �پ�ҵ����˰�걨��ѯProcessor</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: ��һ����</p>
 * @author qinwei
 * @version 1.0
 */

public class ZjyjmcxProcessor 
        implements Processor{

	public ZjyjmcxProcessor(){
		
	}
	/**
     * �������
     * @param vo ǰ̨����
     * @throws BaseException �����쳣
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
  * ��ʼ������
  * @param vo Value Object
  * @return PageForm
  * @exception BaseException BaseException
  */
 private Object doShow(VOPackage vo) throws BaseException {
   //1.�������
   ZjyjmcxForm zjycxFrom = null;
   UserData ud = null;
   SfDBAccess da = null;
   Connection conn = null;
   ArrayList datalist = new ArrayList();
   //2.��ʼ��VO���ݶ���
   try {
     ud = vo.getUserData();
     zjycxFrom = (ZjyjmcxForm) vo.getData();
   }
   catch (Exception e) {
     e.printStackTrace();
     throw ExceptionUtil.getBaseException(e);
   }
   //3.��ʼҵ��
   try {
     ///3.1.��ʼ������
     conn = SfDBResource.getConnection();
     da = new SfDBAccess(conn);
     ///3.2.����ud���û��������˰�������֯�������루���أ���ֵ�б�
     zjycxFrom.setFjList(this.getQxList(da, ud));
     ///3.2.����ud���û��������˰�������֯�������루˰��������ֵ�б�
     zjycxFrom.setSwsList(this.getSwsList(da, ud));
     ///3.3.���ü���˰���
     zjycxFrom.setJmslblist(this.getJmslbList());
     ///3.4.����˰������
     zjycxFrom.setJmszArray(this.getJmszlist());
     ///3.5.���ü�������
     zjycxFrom.setJdlist(this.getJdlist());
     //���ý������
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
  * ��ѯ��̨����
  * @param vo ���ݼ����󣨰���Form��UserData����
  * @return ��ǰҳ���Ӧ��Form����
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
         ///3.2.����ud���û��������˰�������֯�������루���أ���ֵ�б�
         zjycxFrom.setFjList(this.getQxList(da, userData));
         ///3.2.����ud���û��������˰�������֯�������루˰��������ֵ�б�
         zjycxFrom.setSwsList(this.getSwsList(da, userData));
         ///3.3.���ü���˰���
         zjycxFrom.setJmslblist(this.getJmslbList());
         ///3.4.����˰������
         zjycxFrom.setJmszArray(this.getJmszlist());
         ///3.5.���ü�������
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
  * ���ݵ�ǰ��ѯ�������Excel�ļ�
  * @param vo ���ݼ����󣨰���Form��UserData����
  * @return ��ǰҳ���Ӧ��Form����
  * @throws BaseException
  */
 private Object doSaveExcel(VOPackage vo) throws BaseException {
	    //1.�������
	    ZjyjmcxForm zjycxFrom = null;
	    UserData ud = null;
	    SfDBAccess da = null;
	    Connection conn = null;
	    List cxjg = null;
	    //2.��ʼ��VO���ݶ���
	    try {
	      ud = vo.getUserData();
	      zjycxFrom = (ZjyjmcxForm) vo.getData();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      throw ExceptionUtil.getBaseException(e);
	    }
	    //3.��ʼҵ��
	    try {
	    	conn = SfDBResource.getConnection();
	         da = new SfDBAccess(conn);
	         ///3.2.����ud���û��������˰�������֯�������루���أ���ֵ�б�
	         zjycxFrom.setFjList(this.getQxList(da, ud));
	         ///3.2.����ud���û��������˰�������֯�������루˰��������ֵ�б�
	         zjycxFrom.setSwsList(this.getSwsList(da, ud));
	         ///3.3.���ü���˰���
	         zjycxFrom.setJmslblist(this.getJmslbList());
	         ///3.4.����˰������
	         zjycxFrom.setJmszArray(this.getJmszlist());
	         ///3.5.���ü�������
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
 /**��ѯ�پ�ҵ�����걨����
   * @param zf ҳ�����
   * @param da ���ݿ��������
   * @return ��ѯ���
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
             throw new ApplicationException("��ѯ�پ�ҵ�����걨����ʧ��");
           }catch (Exception e) {
             e.printStackTrace();
             throw new ApplicationException("��ѯ�پ�ҵ�����걨����ʧ��");
           }
           //99.����ֵ
           return cxjg;
	 
 }
 /**
  * ��ȡ�����б�
  * @param db
  * @param userData
  * @return
  * @throws BaseException
  */
 private ArrayList getQxList(SfDBAccess db, UserData userData) throws
     BaseException {
   ArrayList list = new ArrayList();
   try {
     //˰���
     String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
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
  * ��ȡ˰�����б�
  * @param db
  * @param userData
  * @return
  * @throws BaseException
  */
 private ArrayList getSwsList(SfDBAccess db, UserData userData) throws
     BaseException {
   ArrayList list = new ArrayList();
   try {
     //˰���
     String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
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
       LabelValueBean label = new LabelValueBean("*����˰����", "0");
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
 //��ü��������б�Value=��˰���������� Label��һλ��Nsrlxdm,�ڶ�λ��Sbblxdm
 private ArrayList getJmslbList()  {
	 ArrayList list = new ArrayList();
	 list.add(new LabelValueBean("�°���ҵ������30%����", "10"));
	 list.add(new LabelValueBean("�°���ҵ��ҵ������30%����", "20"));
	 list.add(new LabelValueBean("������ҵ������30%����", "30"));
	 list.add(new LabelValueBean("������ҵ��ҵ������30%����", "40"));
	 list.add(new LabelValueBean("�������뾭��ʵ��(��һ)", "50"));
	 list.add(new LabelValueBean("��ҵ�����¸�ʧҵ��Ա", "11"));
	 list.add(new LabelValueBean("�������뾭��ʵ��(���)", "21"));
	 list.add(new LabelValueBean("���徭Ӫ", "31"));
	 list.add(0,new LabelValueBean("*�������", ""));
	 return list;
 }
 //��ü���˰�ִ���,Value=˰�����ƣ�Lable=˰�ִ���
 private ArrayList getJmszlist() {
	ArrayList list = new ArrayList();
	list.add(new LabelValueBean("*˰���ܼ�", "%"));
    list.add(new LabelValueBean("Ӫҵ˰", ZjyjmsbConstant.SZDM_YYS));
    list.add(new LabelValueBean("����ά������˰", ZjyjmsbConstant.SZDM_JSS));
    list.add(new LabelValueBean("�����Ѹ���", ZjyjmsbConstant.SZDM_JYFFJ));
    list.add(new LabelValueBean("��������˰", ZjyjmsbConstant.SZDM_GRSDS));
    list.add(new LabelValueBean("��ҵ����˰", ZjyjmsbConstant.SZDM_QYSDS));

	 
	 return list;
	 
 }
 //��ü��ȵ��б�
 private ArrayList getJdlist() {
    ArrayList list = new ArrayList();
    list.add(new LabelValueBean("1����", "1"));
    list.add(new LabelValueBean("2����", "2"));
    list.add(new LabelValueBean("3����", "3"));
    list.add(new LabelValueBean("4����", "4"));
    list.add(0,new LabelValueBean("*ȫ��", "%"));
    return list; 		
}
 /**
  * ��ȡҳ��
  * @param rsCount ��ѯ�����build
  * @return ҳ��
  */
 private String getPageTotalCount(int rsCount) {
   //1.�������
   String countTotal = "0";
   //2.��ʼҵ��
   int pageCount = 0;
   if ( (rsCount % CodeConstant.SD_PG_LENGTH) == 0) {
     pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
   }
   else {
     pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
   }
   countTotal = String.valueOf(pageCount);
   //99.����ֵ
   return countTotal;
 }
 /**
  * ��ȡ��ǰ��ҳ���ݼ�
  * @param tmpList �������ݼ�
  * @param pf ҳ�����
  * @return ��ǰ��ҳ���ݼ�
  */
 private List getPageDataList(List cxjg, ZjyjmcxForm zjycxForm) {
   //1.�������
   List dataList = new ArrayList();
   //2.��ʼ������ֵ
   int startIndex = this.getPageStartIndex(zjycxForm.getNextPage(), zjycxForm.getTotalpage());
   int endIndex = this.getPageEndIndex(zjycxForm.getNextPage(), zjycxForm.getTotalpage());
   System.out.println("startIndex="+startIndex+"endIndex="+endIndex);
   //3.��ʼҵ��
   for (int i = startIndex; i < endIndex; i++) {
     if (i < cxjg.size()) {
       dataList.add(cxjg.get(i));
     }
   }
   cxjg = null;
   //99.����ֵ
   return dataList;
 }
 /**
  * ��ȡ��ǰҳ����index
  * @param nextPage ��һҳ
  * @param countTotal ��ҳ��
  * @return ����index
  */
 private int getPageEndIndex(String nextPage, String countTotal) {
   //1.�������
   int iNextPage = Integer.parseInt(nextPage);
   int iCountTotal = Integer.parseInt(countTotal);
   int end = -1;
   //2.��ʼҵ��
   end = iNextPage * CodeConstant.SD_PG_LENGTH;
   //99.����ֵ
   return end;
 }

 /**
  * ��ȡ��ǰҳ��ʼindex
  * @param nextPage ��һҳ
  * @param countTotal ��ҳ��
  * @return ��ʼindex
  */
 private int getPageStartIndex(String nextPage, String countTotal) {
   //1.�������
   int iNextPage = Integer.parseInt(nextPage);
   int iCountTotal = Integer.parseInt(countTotal);
   int start = -1;
   //2.��ʼҵ��
   start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
   //99.����ֵ
   return start;
 }
}