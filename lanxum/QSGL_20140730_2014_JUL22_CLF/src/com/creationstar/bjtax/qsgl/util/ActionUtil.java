/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.util;

//import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.struts.util.PropertyUtils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpcdBO;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO2;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FptpBO;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import javax.servlet.ServletContext;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Bzqkdm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Gjdq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Htypzdzgxb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.JspUtil;
import com.ttsoft.framework.util.StringUtil;
import org.apache.struts.util.PropertyUtils;

import utils.system;

/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ��WEB�㣨������ҵ��㣩�Ĺ��ú�����</p>
 * @author ��˰�����飭���Բ�������
 * @version 1.0
 */
public class ActionUtil {
    /**
     * Ĭ�Ϲ��캯����
     */
    public ActionUtil() {
    }

    /**
     * ȡ�ô洢��������ݵ�ArrayList
     * @param context ServletContext
     * @param tableName String ����Constants�ж���ĳ�������Constants.ZCWH����Ӧ����HashMap�е�key
     * @return ArrayList
     */
    public static ArrayList getCodeTables(ServletContext context,
                                          String tableName) {
        HashMap map = new HashMap();
        ArrayList list = new ArrayList();
        //��servletcontext��ȡ�ô������д�����hashmap
        map = (HashMap) context.getAttribute(Constants.CodeTables);
        //����tableName��ȡ�ô洢��Ӧ������arrylist
        list = (ArrayList) map.get(tableName);

        return list;

    }

    /**
     * ȡ�ô洢��������ݵ�HashMap
     * @param context ServletContext
     * @param tableName String ����Constants�ж���ĳ�������Constants.ZCWH����Ӧ����HashMap�е�key
     * @return HashMap
     */
    public static Map getCodeMaps(ServletContext context, String tableName) {
        HashMap map = new HashMap();
        Map m = new HashMap();
        //��servletcontext��ȡ�ô������д�����hashmap
        map = (HashMap) context.getAttribute(Constants.CodeTables);
        //����tableName��ȡ�ô洢��Ӧ������arrylist
        m = (HashMap) map.get(tableName);

        return m;

    }

    /**
     * ��������Ĵ����ѯ��������
     * @param context ServletContext
     * @param bzqkdm String
     * @return String
     * @throws BaseException 
     * @throws SQLException 
     */
    // �������һ����������ҳ���ȡ��ͨ����ҳ���ȡ��sbbh��ѯ����ע��Ϣ�����ص�֪ͨ���ϡ�
    // ����ϲ  20120406
    public static String getBzQkLy(ServletContext context, String bzqkdm,String sbbh) throws BaseException, SQLException {
    	String result = "";
    	//System.out.println("__________________________"+sbbh+"_______________________");
        if(bzqkdm.equalsIgnoreCase("99")){
        	ActionUtil util=new ActionUtil();
        	String sbbh1=util.getBeizhu1(sbbh);
			result=sbbh1;
			//System.out.print("++++++++++++++++++++++"+result+"+++++++++++++++++++++");
        }else{
        //��servletcontext��ȡ�ô������д�����hashmap
        HashMap map = (HashMap) context.getAttribute(Constants.CodeTables);
        //����tableName��ȡ�ô洢��Ӧ������arrylist
        HashMap bzqkMap = (HashMap) map.get(Constants.BZQK);
        Bzqkdm bzqk = (Bzqkdm) bzqkMap.get(bzqkdm);
        
        result = bzqk.bzzcly;
        }
        return result;
    
    }

    /**
     * ��ȡһ�鹲�в�Ȩ�˵�����
     */
    public static String getNsrmc(List nsrList, String sep) {
        StringBuffer s = new StringBuffer("");
        String sepl = ",";
        //Collections.sort()
        if (sep != null && !sep.equals("")) {
            sepl = sep;
        }
        if (nsrList != null && !nsrList.equals("")) {

            for (int i = 0; i < nsrList.size(); i++) {
                Grxx grxx = (Grxx) nsrList.get(i);
                //System.out.println(grxx.getNsrmc()+"  lx="+grxx.getCqrlx());
                s.append(grxx.getNsrmc());
                s.append((i == nsrList.size() - 1) ? "" : sepl);
            }
        }
        return s.toString();
    }

    /**
     * ��ȡ��������ݵ���ά����javascript�ַ���
     * @param dataSource java.util.List  ����Դ��List�а�������CodeTableInterface�ӿڵĶ���.
     * @param allowAnonymous �Ƿ���Ҫ�������м���һ��ֵ=""��ѡ�
     * @return ��ʾ��ά������ַ��������磺[["",""],["01","���֤"],["02","����"]]
     */
    public static String displaySelectDataSource(Collection dataSource,
                                                 String[] anonymousValue,
                                                 String dm, String mc) {
        String arrayString = "";
        if (anonymousValue != null) {
            if (anonymousValue.length == 1) {
                arrayString = "[\"" + anonymousValue[0] + "\",\"\"]";
            } else if (anonymousValue.length > 1) {
                arrayString = "[\"" + anonymousValue[0] + "\",\"" +
                              anonymousValue[1] + "\"]";
            }
        }
        if (dataSource == null && arrayString.length() == 0) {
            return "[[\"\",\"\"]]";
        }
        Object o = null;
        String v1 = null;
        String v2 = null;
        Iterator iterator = dataSource.iterator();
        try {
            while (iterator.hasNext()) {
                if (arrayString.length() > 0) {
                    arrayString += ",";
                }

                o = iterator.next();
                v1 = (String) PropertyUtils.getProperty(o, dm);
                v2 = (String) PropertyUtils.getProperty(o, mc);
                if (v1 != null && v2 != null) {
                    arrayString += "[\"" + StringUtil.escapeForJavascript(v1)
                            + "\",\"" + StringUtil.escapeForJavascript(v2) +
                            "\"]";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        arrayString = "[" + arrayString + "]";
        return arrayString;
    }

    /**
     * ��ȡ�������ݵ���ά����javascript�ַ���
     * @param dataSource java.util.List  ����Դ��List�а�������CodeTableInterface�ӿڵĶ���.
     * @param allowAnonymous �Ƿ���Ҫ�������м���һ��ֵ=""��ѡ�
     * @return ��ʾ��ά������ַ��������磺[["",""],["01","���֤"],["02","����"]]
     */
    public static StringBuffer displayANsrDS(Grxx grxx) {
        StringBuffer s = new StringBuffer();
        s.append("[\"\",\"" +
                 JspUtil.displayValue(grxx.nsrmc, JspUtil.ESCAPE_FOR_JAVASCRIPT) +
                 "\",\n");
        s.append("\"" +
                 JspUtil.displayValue(grxx.lxdh, JspUtil.ESCAPE_FOR_JAVASCRIPT) +
                 "\",\n");
        s.append("\"" +
                 JspUtil.displayValue(grxx.sfzjlx, JspUtil.ESCAPE_FOR_JAVASCRIPT) +
                 "\",\n");
        s.append("\"" +
                 JspUtil.displayValue(grxx.sfzjhm, JspUtil.ESCAPE_FOR_JAVASCRIPT) +
                 "\",\n");
        s.append("\"" +
                 JspUtil.displayValue(grxx.gjdm, JspUtil.ESCAPE_FOR_JAVASCRIPT) +
                 "\",\n");
        s.append("\"" +
                 JspUtil.displayValue(grxx.cqrlx, JspUtil.ESCAPE_FOR_JAVASCRIPT) +
                 "\",\n");
        if (grxx.jsjdm != null && !grxx.jsjdm.equals("")) {
            s.append("\"" +
                     JspUtil.displayValue(grxx.jsjdm, JspUtil.ESCAPE_FOR_JAVASCRIPT) +
                     "\",");
        } else {
            s.append("\"\",");
        }
        s.append("1]");
        return s;
    }

    /**
     * ��ȡ�������ݵ���ά����javascript�ַ���
     * @param dataSource java.util.List  ����Դ��List�а�������CodeTableInterface�ӿڵĶ���.
     * @param allowAnonymous �Ƿ���Ҫ�������м���һ��ֵ=""��ѡ�
     * @return ��ʾ��ά������ַ��������磺[["",""],["01","���֤"],["02","����"]]
     */
    public static String displayMNsrDS(List l) {
        StringBuffer s = new StringBuffer("var aryDataSource=[\n");
        for (int i = 0; i < l.size(); i++) {
            Grxx grxx = (Grxx) l.get(i);

            s.append(displayANsrDS(grxx));
            s.append((i == l.size() - 1) ? "" : ",");
        }
        s.append(" ];\n");

        return s.toString();
    }

//��ӡ��˰֤���߽ɿ���ʱ��ҪJsjdm
    public static String getNsrmc(List l, String[] xsjsjdm, String jsjdm) {
        if (jsjdm == null || jsjdm.equals("")) {
            return "";
        }
        List nsrList = new ArrayList();
        String mcStr = "";
        nsrList.add(jsjdm);

        if (xsjsjdm == null || xsjsjdm.length == 0) {
            for (int j = 0; j < l.size(); j++) {
                Grxx g = (Grxx) l.get(j);
                if (jsjdm.equalsIgnoreCase(g.getJsjdm())) {
                    mcStr = g.getNsrmc() + mcStr;
                    return mcStr;
                }
            }
        }
        //System.out.println("jsjdm=="+jsjdm+"   xsjsjdm.length=="+xsjsjdm.length);

        for (int j = 0; j < l.size(); j++) {
            Grxx g = (Grxx) l.get(j);
            if (!jsjdm.equalsIgnoreCase(g.getJsjdm())) {
                for (int i = 0; i < xsjsjdm.length; i++) {
                    if (g.getJsjdm().equalsIgnoreCase(xsjsjdm[i]) &&
                        !nsrList.contains(g.getJsjdm())) {
                        nsrList.add(g.getJsjdm());
                        mcStr = mcStr + "," + g.getNsrmc();
                    }
                }
            } else {
                mcStr = g.getNsrmc() + mcStr;
            }
        }

        return mcStr;

    }

//  ���* ��˰������* ��ϵ�绰* ���֤������* ���֤������* ����* �Ƿ�����Ȩ��*
    public static List getGrData(String[][] dataSource_grxx, Map zjMap,
                                 Map gjMap) {
        List l = new ArrayList();
        if (dataSource_grxx == null) {
            return l;
        }
        for (int i = 0; i < dataSource_grxx.length; i++) {
            Grxx grxx = new Grxx();

            grxx.lxdh = dataSource_grxx[i][2];
            grxx.nsrmc = dataSource_grxx[i][1];
            grxx.sfzjhm = dataSource_grxx[i][4];
            grxx.sfzjlx = dataSource_grxx[i][3];
            grxx.sfzjlxmc = ((Zjlx) zjMap.get(grxx.sfzjlx)).getZjlxmc();
            grxx.gjdm = dataSource_grxx[i][5];
            grxx.gjmc = ((Gjdq) gjMap.get(grxx.gjdm)).getGjdqmc();
            grxx.cqrlx = dataSource_grxx[i][6];
            //System.out.println("nsrmc=="+grxx.nsrmc+" zjlx=="+grxx.sfzjlx+"  zjhm=="+grxx.sfzjhm+" gj=="+grxx.gjdm);
            //System.out.println("lxdh=="+grxx.lxdh+" cqrlx=="+grxx.cqrlx);
            l.add(grxx);
        }
        return l;
    }
    
    //ͨ����������ѯ�����ݿ��б�ע����Ϣ��Ȼ�󷵻ء�
    //����ϲ	  20120406
    public static String getBeizhu1(String sbbhdm) throws BaseException, SQLException{
      	 String beizhu1 = null;
      	 Connection conn=null;   
      	PreparedStatement pst = null;
        ResultSet rs = null;
      	 String sql="select bz from qsdb.qs_jl_sbzb where sbbh='"+sbbhdm+"'";
      	 //System.out.println("----------1--------------"+sql+"------------1-------------");
      	 try {
      		 conn = QSDBUtil.getConnection();
               
               pst = conn.prepareStatement(sql);

               rs = pst.executeQuery();
               if (rs.next()) {
                   beizhu1 =rs.getString("bz");
                   
               }
               rs.close();
      	 }
      	 catch(SQLException e){
      		 e.printStackTrace();
      		 throw new ApplicationException("ȡ����ע��Ϣʱ��������",e);
      	 }finally { 
      		 pst.close();
      		QSDBUtil.freeConnection(conn);
        }
   		return beizhu1;
      	
      }
    
    /**
     * ��÷�Ʊ���볤��
     * @param fpzlmc
     * @return fphmcd
     * @throws BaseException
     * @throws SQLException
     */
    public static String getFphmcd(String fpzldm) throws BaseException, SQLException
    {
    	String fphmcd = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	String sql="select fphmcd from dmdb.fp_dm_fpzl where fpzldm='"+fpzldm+"'";
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             if (rs.next())
             {
            	 fphmcd =rs.getString("fphmcd");
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("ȡ�÷�Ʊ���೤��ʧ�ܣ�",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return fphmcd;
     }
    
    /**
     * ��÷�Ʊ��������
     * @param fpzlmc
     * @return fphmcd
     * @throws BaseException
     * @throws SQLException
     */
    public static String getFpzlmc(String fpzldm) throws BaseException, SQLException
    {
    	String fpzlmc = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	String sql="select fpzlmc from dmdb.fp_dm_fpzl where fpzldm='"+fpzldm+"'";
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             if (rs.next())
             {
            	 fpzlmc =rs.getString("fpzlmc");
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("ȡ�÷�Ʊ��������ʧ�ܣ�",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return fpzlmc;
     }
    
    /**
     * ���Swjjzzlgmc
     * @param Swjgzzjgdm
     * @return Swjjzzlgmc
     * @throws BaseException
     * @throws SQLException
     */
    public static String getSwjjzzlgmc(String swjgzzjgdm) throws BaseException, SQLException
    {
    	String swjgzzjgmc = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	String sql="select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm='"+swjgzzjgdm+"'";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             if (rs.next())
             {
            	 swjgzzjgmc =rs.getString("swjgzzjgmc");
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("ȡ��˰�������֯��������ʧ�ܣ�",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return swjgzzjgmc;
     }
    
    /**
     * ��������ʱ��ķ�Ʊ����
     * @param fpkfdm
     * @return FpzlList
     * @throws BaseException
     * @throws SQLException
     */
    public static ArrayList queryMaxFpzl(String fpkfdm) throws BaseException, SQLException
    {
    	ArrayList FpzlList = new ArrayList();
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select distinct b.fpzldm,d.fpzlmc, b.fpkfdm from fpdb.fp_jl_fpkcmx a,dmdb.fp_dm_fpzl d, " +
     			"(select c.fpkfdm, c.fpzldm, max(c.jcsj) jcsj from fpdb.fp_jl_fpkcmx c where c.fpkfdm = '"+fpkfdm+"' " +
     			" group by c.fpkfdm, c.fpzldm) b where a.fpkfdm = b.fpkfdm and a.fpzldm = b.fpzldm and a.fpzldm = d.fpzldm " +
     			"and a.jcsj = b.jcsj and a.sl <> '"+Constants.FP_SL_ZERO+"'  order by b.fpzldm ";
     	
     	//System.out.println(sql);
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
             	Fpzl fpzl = new Fpzl();
             	
             	fpzl.setFpzldm(rs.getString("fpzldm"));
             	fpzl.setFpzlmc(rs.getString("fpzlmc"));
                
             	FpzlList.add(fpzl);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("δ���÷�Ʊ�����÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return FpzlList;
     }
    
    
    /**
     * ���ݷ�Ʊ�ⷿ���롢��Ʊ��������������ʱ��ķ�Ʊ���List
     * @param fpkfdm ��Ʊ�ⷿ����
     * @param fpzldm ��Ʊ�������
     * @return FpkcList
     * @throws BaseException
     * @throws SQLException
     */
    public static ArrayList queryMaxFpkc(String fpkfdm,String fpzldm) throws BaseException, SQLException
    {
    	ArrayList FpkcList = new ArrayList();
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select distinct b.fpzldm, d.fpzlmc, b.fpkfdm, a.jcsj, a.qshm, a.jzhm, a.sl, a.swjgzzjgdm from fpdb.fp_jl_fpkcmx a, " +
     			"dmdb.fp_dm_fpzl d, (select c.fpkfdm, c.fpzldm, max(c.jcsj) jcsj from fpdb.fp_jl_fpkcmx c where c.fpkfdm = '"+fpkfdm+"' " +
     			"and c.fpzldm = '"+fpzldm+"' group by c.fpkfdm, c.fpzldm) b  where a.fpkfdm = b.fpkfdm and a.fpzldm = b.fpzldm and " +
     			"a.fpzldm = d.fpzldm and a.jcsj = b.jcsj and a.sl <> '"+Constants.FP_SL_ZERO+"' order by b.fpzldm,a.qshm ";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 Fpkc fpkc = new Fpkc();
             	
            	 fpkc.setFpkfdm(rs.getString("fpkfdm"));
            	 fpkc.setFpzldm(rs.getString("fpzldm"));
            	 fpkc.setJcsj(rs.getTimestamp("jcsj"));
            	 fpkc.setQshm(rs.getString("qshm"));
            	 fpkc.setJzhm(rs.getString("jzhm"));
            	 fpkc.setSl(rs.getBigDecimal("sl"));
            	 fpkc.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	 
            	 FpkcList.add(fpkc);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return FpkcList;
     }
    
    
    /**
     * ���ݷ�Ʊ�ⷿ���롢��Ʊ��������������ʱ��ķ�Ʊ���List(����)
     * @param fpkfdm ��Ʊ�ⷿ����
     * @param fpzldm ��Ʊ�������
     * @return FpkcList
     * @throws BaseException
     * @throws SQLException
     */
    public static ArrayList queryMaxFpkc2(String fpkfdm,String fpzldm,String qshm) throws BaseException, SQLException
    {
    	ArrayList FpkcList = new ArrayList();
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" with fpkc as(select a.fpkfdm,a.fpzldm,b.fpzlmc, a.jcsj, a.qshm, a.jzhm, a.sl from fpdb.fp_jl_fpkcmx a, dmdb.fp_dm_fpzl b " +
     			" where a.fpkfdm = '"+fpkfdm+"' and a.fpzldm = '"+fpzldm+"' and a.sl <>'"+Constants.FP_SL_ZERO+"'  and a.jcsj = (select max(t.jcsj) from fpdb.fp_jl_fpkcmx t " +
     			"where t.fpkfdm = a.fpkfdm and t.fpzldm = a.fpzldm) and a.fpzldm = b.fpzldm(+)) select fpkc.*, sum((case when fpkc.qshm >= '"+qshm+"' " +
     			"then fpkc.sl else to_number(fpkc.jzhm)-to_number('"+qshm+"')+1 end)) over(partition by fpzldm, fpzlmc, fpkfdm) hjsl  from fpkc " +
     			"where to_number('"+qshm+"') - to_number(qshm) <= 1 or to_number(jzhm)-to_number('"+qshm+"') >=0  ";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 Fpkc fpkc = new Fpkc();
             	
            	 fpkc.setFpkfdm(rs.getString("fpkfdm"));
            	 fpkc.setFpzldm(rs.getString("fpzldm"));
            	 fpkc.setJcsj(rs.getTimestamp("jcsj"));
            	 fpkc.setQshm(rs.getString("qshm"));
            	 fpkc.setJzhm(rs.getString("jzhm"));
            	 fpkc.setHjsl(rs.getBigDecimal("hjsl"));
            	 fpkc.setSl(rs.getBigDecimal("sl"));
            	 
            	 FpkcList.add(fpkc);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return FpkcList;
     }
    
    
    /**
     * ���ݷ�Ʊ�ⷿ���롢��Ʊ������롢��ʼ�����������ʱ��ķ�Ʊ���List
     * ���FpkcList.size >0 && FpkcList != null ,��˵���úŶ��ڿ����
     * @param fpkfdm ��Ʊ�ⷿ����
     * @param fpzldm ��Ʊ�������
     * @param qshm ��ʼ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryMaxFpkcxx(String fpkfdm,String fpzldm, String qshm) throws BaseException, SQLException
    {
    	String czQshm = "";
    	ArrayList FpkcList = new ArrayList();
    	Fpkc fpkc = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select distinct b.fpzldm, d.fpzlmc, b.fpkfdm, a.jcsj, a.qshm, a.jzhm, a.sl from fpdb.fp_jl_fpkcmx a,  " +
     			"dmdb.fp_dm_fpzl d,(select c.fpkfdm, c.fpzldm, max(c.jcsj) jcsj from fpdb.fp_jl_fpkcmx c where c.fpkfdm = '"+fpkfdm+"' " +
     			"and c.fpzldm = '"+fpzldm+"' group by c.fpkfdm, c.fpzldm) b where a.fpkfdm = b.fpkfdm and a.fpzldm = b.fpzldm  " +
     			"and a.fpzldm = d.fpzldm and a.jcsj = b.jcsj and a.qshm <= '"+SecurityUtil.dealwithStringPara(qshm)+"' and a.jzhm >= '"+SecurityUtil.dealwithStringPara(qshm)+"'  " +
     			"and a.sl <> '"+Constants.FP_SL_ZERO+"' order by b.fpzldm ,a.qshm";
     	//System.out.println(sql);
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 fpkc = new Fpkc();
             	
            	 fpkc.setFpkfdm(rs.getString("fpkfdm"));
            	 fpkc.setFpzldm(rs.getString("fpzldm"));
            	 fpkc.setQshm(rs.getString("qshm"));
            	 fpkc.setJzhm(rs.getString("jzhm"));
            	 fpkc.setSl(rs.getBigDecimal("sl"));
            	 
            	 FpkcList.add(fpkc);
             }
             
             if ((FpkcList == null) || (FpkcList.size() == 0))
             {
            	 czQshm = qshm;
            	 Debug.out("�����ڿ���еķ�Ʊ����:"+czQshm);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return czQshm;
     }
    
    
    /**
     * ���ݷ�Ʊ�ⷿ���롢��Ʊ������롢��ʼ�����ӡ���� �жϿ���Ƿ��з�Ʊ��ʹ��
     * ���FpkcList.size >0 && FpkcList != null ,��˵���úŶ��ڿ����
     * @param fpkfdm ��Ʊ�ⷿ����
     * @param fpzldm ��Ʊ�������
     * @param qshm ��ʼ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String checkMoreFphm(String fpkfdm,String fpzldm, String qshm, int pageNum) throws BaseException, SQLException
    {
    	String czQshm = "";
    	ArrayList FpkcList = new ArrayList();
    	Fpkc fpkc = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" with fpkc as(select a.fpkfdm,a.fpzldm,b.fpzlmc, a.jcsj, a.qshm, a.jzhm, a.sl from fpdb.fp_jl_fpkcmx a, dmdb.fp_dm_fpzl b " +
     			" where a.fpkfdm = '"+fpkfdm+"' and a.fpzldm = '"+fpzldm+"' and a.jcsj = (select max(t.jcsj) from fpdb.fp_jl_fpkcmx t " +
     			"where t.fpkfdm = a.fpkfdm and t.fpzldm = a.fpzldm) and a.fpzldm = b.fpzldm(+)) select fpkfdm,fpzldm,fpzlmc, jcsj, " +
     			"qshm, jzhm, sum((case when fpkc.qshm >= '"+qshm+"' then fpkc.sl else to_number(fpkc.jzhm)-to_number('"+qshm+"')+1 end)) " +
     			"over(partition by fpzldm, fpzlmc, fpkfdm) hjsl  from fpkc where to_number('"+qshm+"') - to_number(qshm) <= 1 " +
     			"or to_number(jzhm)-to_number('"+qshm+"') >=0 ";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             while (rs.next()) 
             {
            	 fpkc = new Fpkc();
            	 fpkc.setFpkfdm(rs.getString("fpkfdm"));
            	 fpkc.setFpzldm(rs.getString("fpzldm"));
            	 fpkc.setQshm(rs.getString("qshm"));
            	 fpkc.setJzhm(rs.getString("jzhm"));
            	 fpkc.setHjsl(rs.getBigDecimal("hjsl"));
            	 
            	 FpkcList.add(fpkc);
             }
             if ((FpkcList != null) && (FpkcList.size() != 0))
             {
            	 for(int i=0;i<FpkcList.size();i++)
            	 {
            		 Fpkc FpkcListItem= (Fpkc)FpkcList.get(0);
            		 BigDecimal hjsl = FpkcListItem.getHjsl();
            		 if( pageNum - hjsl.intValue() >0 )
            		 {
            			 czQshm = qshm;
                    	 Debug.out("��ӡʹ�÷�Ʊ��治�㣬"+"��"+pageNum+"�ݣ���"+hjsl.intValue()+"��");
            		 }
            	 }
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return czQshm;
     }
    
    
    /**
     * ���ݷ�Ʊ�ⷿ���롢��Ʊ������롢��ʼ�����ѯ��Ʊ�����Ƿ���ʹ��
     * ���FpkcList.size >0 && FpkcList != null ,��˵���úŶ��ڿ����
     * @param fpkfdm ��Ʊ�ⷿ����
     * @param fpzldm ��Ʊ�������
     * @param qshm ��ʼ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String checkFphm(String fpkfdm,String fpzldm, String fphm) throws BaseException, SQLException
    {
    	String czQshm = "";
    	ArrayList FpczList = new ArrayList();
    	Fpczz fpczz = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select fphm from fpdb.fp_jl_fpkpzb where fpkfdm = '"+fpkfdm+"' " +
     			"and fpzldm = '"+fpzldm+"' and fphm = '"+fphm+"' ";
     	
     	//System.out.println(sql);
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 fpczz = new Fpczz();
             	
            	 fpczz.setFphm(rs.getString("fphm"));
            	 
            	 FpczList.add(fpczz);
             }
             
             if (FpczList != null && FpczList.size() > 0)
             {
            	 czQshm = fphm;
            	 Debug.out("��ʹ�õķ�Ʊ����:"+czQshm);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return czQshm;
     }
    
    /**
     * ���ݷ�Ʊ�ⷿ���롢��Ʊ������롢��ʼ�����ж�¼��Ŷ��Ƿ���ڿ����
     * @param fpkfdm ��Ʊ�ⷿ����
     * @param fpzldm ��Ʊ�������
     * @param qshm ��ʼ����
     * @param qshm ��ֹ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryFpkclr(String fpzldm, String qshm, String jzhm) throws BaseException, SQLException
    {
    	String czQshm = "";
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql="select count(1) sl from (select 1 sl from fpdb.fp_jl_fpkcmx where " +
     			"fpzldm = '"+fpzldm+"' and qshm <= '"+SecurityUtil.dealwithStringPara(qshm)+"' and jzhm >= '"+SecurityUtil.dealwithStringPara(qshm)+"' " +
     			"and rkbs = '"+Constants.FP_RKBZ_RK+"' union all select 1 sl from fpdb.fp_jl_fpkcmx " +
     			"where fpzldm = '"+fpzldm+"' and qshm <= '"+SecurityUtil.dealwithStringPara(jzhm)+"' " +
     			"and jzhm >= '"+SecurityUtil.dealwithStringPara(jzhm)+"' and rkbs = '"+Constants.FP_RKBZ_RK+"' union all  select 1 sl " +
     			"from fpdb.fp_jl_fpkcmx  where fpzldm = '"+fpzldm+"' " +
     			"and qshm >= '"+SecurityUtil.dealwithStringPara(qshm)+"'  and jzhm <= '"+SecurityUtil.dealwithStringPara(jzhm)+"' and rkbs = '"+Constants.FP_RKBZ_RK+"')";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 czQshm = String.valueOf(rs.getBigDecimal("sl"));
             }
            
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return czQshm;
     }
    
    
    /**
     * ��ͬ����Ƿ���ڣ���ѯ������Ϣ��
     * @param htbh ��ͬ���
     * @param swjgzzjgdm ˰�������֯��������
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryHtbh(String htbh) throws BaseException, SQLException
    {
    	String strHtbh = "";
    	ArrayList HtbhList = new ArrayList();
    	Fwxx fwxx = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select htbh from qsdb.qs_jl_fwxxb where htbh = '"+SecurityUtil.dealwithStringPara(htbh)+"' ";
     	//System.out.println(sql);
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 fwxx = new Fwxx();
             	
            	 fwxx.setHtbh(rs.getString("htbh"));
            	 
            	 HtbhList.add(fwxx);
             }
             
             if ((HtbhList == null) || (HtbhList.size() == 0))
             {
            	 strHtbh = htbh;
            	 Debug.out("�ú�ͬ��Ų�����:"+strHtbh);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�ú�ͬ��Ų�ѯ����",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return strHtbh;
     }
    
    
    /**
     * ��Ʊʱ��ѯ��ͬ����Ƿ���ڣ���ѯ��Ʊ��Ʊ��
     * @param htbh ��ͬ���
     * @param swjgzzjgdm ˰�������֯��������
     * @param qshm ��ʼ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryHtbh2(String htbh) throws BaseException, SQLException
    {
    	String strHtbh = "";
    	ArrayList HtbhList = new ArrayList();
    	Htypzdzgxb htypzdzgxb = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select distinct a.htbh from qsdb.qs_jl_htypzdzgxb a ,qsdb.qs_jl_fwxxb b " +
     			"where a.htbh = b.htbh and a.htbh = '"+SecurityUtil.dealwithStringPara(htbh)+"' and a.pzfldm='"+Constants.FP_PZFLDM_FP+"' ";
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 htypzdzgxb = new Htypzdzgxb();
             	
            	 htypzdzgxb.setHtbh(rs.getString("htbh"));
            	 
            	 HtbhList.add(htypzdzgxb);
             }

             if ((HtbhList == null) || (HtbhList.size() == 0))
             {
            	 strHtbh = htbh;
            	 Debug.out("��ѯ��ͬ��Ų�����:"+strHtbh);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�ú�ͬ��Ų�ѯ����",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return strHtbh;
     }
    
    
    /**
     * ��ͬ����Ƿ��Դ�������ѯ��ϵ���ձ�
     * @param htbh ��ͬ���
     * @param swjgzzjgdm ˰�������֯��������
     * @param qshm ��ʼ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryHtbh3(String htbh) throws BaseException, SQLException
    {
    	String strHtbh = "";
    	ArrayList HtbhList = new ArrayList();
    	Htypzdzgxb htypzdzgxb = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select htbh from qsdb.qs_jl_htypzdzgxb a where htbh = '"+SecurityUtil.dealwithStringPara(htbh)+"' and pzfldm = '"+Constants.FP_PZFLDM_FP+"'  ";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 htypzdzgxb = new Htypzdzgxb();
             	
            	 htypzdzgxb.setHtbh(rs.getString("htbh"));
            	 
            	 HtbhList.add(htypzdzgxb);
             }
             
             if ((HtbhList != null) && (HtbhList.size() > 0))
             {
            	 strHtbh = htbh;
            	 Debug.out("�ú�ͬ����Ѵ�����Ʊ:"+strHtbh);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�ú�ͬ��Ų�ѯ����",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return strHtbh;
     }
    
    
    /**
     * ���ݺ�ͬ����Ƿ�����ж��Ƿ�˶�����ѯ�˶���Ϣ��
     * @param htbh ��ͬ���
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryHtbh4(String htbh) throws BaseException, SQLException
    {
    	String strHtbh = "";
    	ArrayList HtbhList = new ArrayList();
    	Fwhdxx fwhdxx = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select htbh from qsdb.qs_jl_fwhdxxb where htbh = '"+SecurityUtil.dealwithStringPara(htbh)+"' ";
     	//System.out.println(sql);
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 fwhdxx = new Fwhdxx();
             	
            	 fwhdxx.setHtbh(rs.getString("htbh"));
            	 
            	 HtbhList.add(fwhdxx);
             }
             
             if ((HtbhList == null) || (HtbhList.size() == 0))
             {
            	 strHtbh = htbh;
            	 Debug.out("�ú�ͬ��Ŷ�Ӧ�ĺ˶���Ϣ������:"+strHtbh);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�ú�ͬ��Ŷ�Ӧ�ĺ˶���Ϣ��ѯ����",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return strHtbh;
     }
    
    
    /**
     * ���ݷ�Ʊ������롢��Ʊ�����жϷ�Ʊ�����Ƿ���ڣ���Ʊ�ã�
     * @param fpkfdm ��Ʊ�ⷿ����
     * @param fpzldm ��Ʊ�������
     * @param fphm ��Ʊ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryFphm(String fpzldm,String fphm) throws BaseException, SQLException
    {
    	String strQshm = "";
    	ArrayList qshmList = new ArrayList();
    	Htypzdzgxb htypzdzgxb = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select a.pzhm from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c " +
     			"where b.fpkfdm = c.fpkfdm and b.fpzldm = c.fpzldm and b.fphm = c.fphm and a.pizzldm = b.fpzldm " +
     			"and a.pzhm = b.fphm and a.pzhm = '"+SecurityUtil.dealwithStringPara(fphm)+"' and a.pzfldm = '"+Constants.FP_PZFLDM_FP+"' " +
     			"and a.pizzldm = '"+fpzldm+"' and b.sfyjbltp ='"+Constants.FP_TPBZ_WTP+"' ";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 htypzdzgxb = new Htypzdzgxb();
             	
            	 htypzdzgxb.setPzhm(rs.getString("pzhm"));
            	 
            	 qshmList.add(htypzdzgxb);
             }
             
             if ((qshmList == null) || (qshmList.size() == 0))
             {
            	 strQshm = fphm;
            	 Debug.out("�÷�Ʊ���벻����:"+strQshm);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return strQshm;
     }
    
    /**
     * ���ݷ�Ʊ������롢��Ʊ�����ж��Ƿ���ڣ��ش��ã�
     * @param fpzldm ��Ʊ�������
     * @param fphm ��Ʊ����
     * @return
     * @throws BaseException
     * @throws SQLException
     */
    public static String queryFphm2(String fpzldm,String fphm) throws BaseException, SQLException
    {
    	String strQshm = "";
    	ArrayList qshmList = new ArrayList();
    	Fpczz fpczz = null;
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql=" select b.fphm from  fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c where  " +
     			"b.fpkfdm = c.fpkfdm and b.fpzldm = c.fpzldm and b.fphm = c.fphm and " +
     			"b.fphm = '"+SecurityUtil.dealwithStringPara(fphm)+"'  and b.fpzldm = '"+fpzldm+"' " +
     			"and b.sfyjbltp ='"+Constants.FP_TPBZ_WTP+"' and b.kplxdm <>'"+Constants.FP_KPLX_FP+"' ";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
             pst = conn.prepareStatement(sql);
             
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 fpczz = new Fpczz();
             	
            	 fpczz.setFphm(rs.getString("fphm"));
            	 
            	 qshmList.add(fpczz);
             }
             
             if ((qshmList == null) || (qshmList.size() == 0))
             {
            	 strQshm = fphm;
            	 Debug.out("�÷�Ʊ���벻����:"+strQshm);
             }
             rs.close();
     	 }
     	 catch(SQLException e){
     		 e.printStackTrace();
     		 throw new ApplicationException("�÷�Ʊ�����޿��������",e);
     	 }finally { 
     		 pst.close();
     		QSDBUtil.freeConnection(conn);
       }
  		return strQshm;
     }
    
    
    /**
     * ���ݲ�ѯ������ȡ��Ʊ��Ʊ����ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ����ֵ����ļ���
     * @throws Exception 
     */
    public static ArrayList queryFptp(String fpkfdm, String fpzldm, String fphm) throws
            Exception {
        ArrayList FptpList = new ArrayList();
        String sql = "select a.*,b.*,c.pm,c.dj,c.sl,c.je " +
        		"from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c  " +
        		"where b.fpkfdm = c.fpkfdm  and b.fpzldm = c.fpzldm and b.fphm = c.fphm  " +
        		"and a.pizzldm =b.fpzldm  and a.pzhm = b.fphm " +
        		"and a.pzfldm ='"+Constants.FP_PZFLDM_FP+"' and b.fpkfdm='"+fpkfdm+"' " +
        		"and a.pizzldm='"+fpzldm+"' and a.pzhm ='"+fphm+"'order by b.fpzldm,b.fphm" ;
        
        PreparedStatement ps = null;
        Connection conn=null;   
        try {
        	conn = QSDBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FptpBO fptpBO = new FptpBO();
            	Fpczz fpczz1 = new Fpczz();
            	Fpczmx fpczmx1 = new Fpczmx();
            	Htypzdzgxb htypzdzgxb1 = new Htypzdzgxb();
            	
            	fpczz1.setFphm(rs.getString("fphm"));
            	fpczz1.setFkdw(rs.getString("fkdw"));
            	fpczz1.setSkdw(rs.getString("skdw"));
            	fpczz1.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz1.setFwzldz(rs.getString("fwzldz"));
            	fpczz1.setKplxdm(rs.getString("kplxdm"));
            	fpczz1.setSphm(rs.getString("sphm"));
            	fpczz1.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz1.setTpfphm(rs.getString("tpfphm"));
            	fpczz1.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz1.setCjr(rs.getString("cjr"));
            	fpczz1.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz1.setLrr(rs.getString("lrr"));
            	fpczz1.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz1.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz1.setBz(rs.getString("bz"));
            	fpczz1.setDcbz(rs.getString("dcbz"));
            	fpczz1.setHyfl(rs.getString("hyfl"));
            	fpczz1.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz1.setFwzldz(rs.getString("fwzldz"));
            	
            	fpczmx1.setPm(rs.getString("pm"));
            	fpczmx1.setDj(rs.getBigDecimal("dj"));
            	fpczmx1.setSl(rs.getBigDecimal("sl"));
            	fpczmx1.setJe(rs.getBigDecimal("je"));
            	
            	htypzdzgxb1.setXh(rs.getString("xh"));
            	htypzdzgxb1.setHtbh(rs.getString("htbh"));
            	htypzdzgxb1.setMmfbz(rs.getString("mmfbz"));
            	htypzdzgxb1.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb1.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb1.setPzhm(rs.getString("pzhm"));
            	htypzdzgxb1.setPzndzb(rs.getString("pzndzb"));
            	htypzdzgxb1.setCjr(rs.getString("cjr"));
            	htypzdzgxb1.setCjrq(rs.getTimestamp("cjrq"));
            	htypzdzgxb1.setLrr(rs.getString("lrr"));
            	htypzdzgxb1.setLrrq(rs.getTimestamp("lrrq"));
            	htypzdzgxb1.setSbbh(rs.getString("sbbh"));
            	
            	fptpBO.setFpczz(fpczz1);
            	fptpBO.setFpczmx(fpczmx1);
            	fptpBO.setHtypzdzgxb(htypzdzgxb1);
            	
            	FptpList.add(fptpBO);
           }
            
            rs.close();
        } catch (SQLException e) {
        	e.printStackTrace();
     		 throw new ApplicationException("ȡ����Ʊ��Ϣʱ��������",e);
        } finally {
            ps.close();
            QSDBUtil.freeConnection(conn);
        }
        return FptpList;
    }
    
    /**
     * ���ݲ�ѯ������ȡ��Ʊ��������ֵ���󣨵����������ݣ�
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ����ֵ����ļ���
     * @throws Exception 
     */
    public static ArrayList queryFpdchzData(String fpkfdm,String selectedfpxx, String swjgzzjgdm) throws
            Exception {
    	ArrayList FpAllList = new ArrayList();
        String sql = "select a.jsjdm jsjdm, min(a.kprq) qsrq,max(a.kprq) jzrq," +
        		"sum(case when a.kplxdm='1' then a.sl else 0 end) kpsl, " +
        		"sum(case when a.kplxdm='2' then a.sl else 0 end) tpsl, " +
        		"(select count(kplxdm) sl from fpdb.fp_jl_fpkpzb b where b.kplxdm ='3' and b.swjgzzjgdm = a.swjgzzjgdm and "+selectedfpxx+") fpsl," +
        		"sum(case when a.kplxdm='1' then a.je else 0 end) kpje, " +
        		"sum(case when a.kplxdm='2' then a.je else 0 end) tpje from ( select b.*,c.pm,c.dj,c.sl,c.je,dm1.jsjdm,dm2.kplxmc  " +
        		"from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c, dmdb.gy_dm_swjgzzjg dm1, dmdb.fp_dm_kplx dm2  " +
        		"where b.fpkfdm = c.fpkfdm(+)  and b.fpzldm = c.fpzldm(+) and b.fphm = c.fphm(+) and a.pizzldm(+) =b.fpzldm  " +
        		"and a.pzhm(+) = b.fphm and b.swjgzzjgdm = dm1.swjgzzjgdm and b.swjgzzjgdm ='"+swjgzzjgdm+"' and b.kplxdm = dm2.kplxdm " +
        		"and b.fpkfdm = '"+fpkfdm+"' and "+selectedfpxx+" order by b.dcbz,b.fphm ) a group by a.swjgzzjgdm,a.jsjdm";
        
        //Debug.out("queryFpdchzData()'s SQL : " + sql.toString());
        
        PreparedStatement ps = null;
        Connection conn=null;   
        try {
        	conn = QSDBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
            	FpdcBO2 fpdcBO2 = new FpdcBO2();
            	
            	fpdcBO2.setJsjdm(rs.getString("jsjdm"));
            	fpdcBO2.setQsrq(rs.getString("qsrq"));
            	fpdcBO2.setJzrq(rs.getString("jzrq"));
            	fpdcBO2.setKpsl(rs.getBigDecimal("kpsl"));
            	fpdcBO2.setTpsl(rs.getBigDecimal("tpsl"));
            	fpdcBO2.setFpsl(rs.getBigDecimal("fpsl"));
            	fpdcBO2.setKpje(rs.getBigDecimal("kpje"));
            	fpdcBO2.setTpje(rs.getBigDecimal("tpje"));
            	
            	FpAllList.add(fpdcBO2);
           }
            
            rs.close();
        } catch (SQLException e) {
        	e.printStackTrace();
    		 throw new ApplicationException("������������ʱ��������",e);
        } finally {
            ps.close();
            QSDBUtil.freeConnection(conn);
        }
        return FpAllList;
    }
    
    
    /**
     * ����������ȡ��Ʊ����������ֵ����
     * @param condition ����where �������־�
     * @param conn ���ݿ����Ӷ���
     * @return ArrayList ����ֵ����ļ���
     * @throws Exception 
     */
    public static ArrayList queryFpcd(String fpkfdm, String fpzldm, String fphm) throws
            Exception {
        ArrayList FptpList = new ArrayList();
        String sql = "select a.*,b.*,c.pm,c.dj,c.sl,c.je " +
        		"from qsdb.qs_jl_htypzdzgxb a, fpdb.fp_jl_fpkpzb b, fpdb.fp_jl_fpkpmx c  " +
        		"where b.fpkfdm = c.fpkfdm  and b.fpzldm = c.fpzldm and b.fphm = c.fphm  " +
        		"and a.pizzldm(+) =b.fpzldm  and a.pzhm(+) = b.fphm and b.fpkfdm='"+fpkfdm+"' " +
        		"and b.fpzldm='"+fpzldm+"' and b.fphm='"+fphm+"' order by b.fpzldm,b.fphm" ;
        
        //Debug.out("queryFptp()'s SQL : " + sql);
        
        PreparedStatement ps = null;
        Connection conn=null;   
        try {
        	conn = QSDBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	
            	FpcdBO fpcdBO = new FpcdBO();
            	Fpczz fpczz1 = new Fpczz();
            	Fpczmx fpczmx1 = new Fpczmx();
            	Htypzdzgxb htypzdzgxb1 = new Htypzdzgxb();
            	
            	fpczz1.setFphm(rs.getString("fphm"));
            	fpczz1.setFkdw(rs.getString("fkdw"));
            	fpczz1.setSkdw(rs.getString("skdw"));
            	fpczz1.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz1.setFwzldz(rs.getString("fwzldz"));
            	fpczz1.setKplxdm(rs.getString("kplxdm"));
            	fpczz1.setSphm(rs.getString("sphm"));
            	fpczz1.setTpfpzldm(rs.getString("tpfpzldm"));
            	fpczz1.setTpfphm(rs.getString("tpfphm"));
            	fpczz1.setKpr(rs.getString("kpr"));
            	fpczz1.setSfyjbltp(rs.getString("sfyjbltp"));
            	fpczz1.setCjr(rs.getString("cjr"));
            	fpczz1.setCjrq(rs.getTimestamp("cjrq"));
            	fpczz1.setLrr(rs.getString("lrr"));
            	fpczz1.setLrrq(rs.getTimestamp("lrrq"));
            	fpczz1.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
            	fpczz1.setBz(rs.getString("bz"));
            	fpczz1.setDcbz(rs.getString("dcbz"));
            	fpczz1.setHyfl(rs.getString("hyfl"));
            	fpczz1.setFwcqzh(rs.getString("fwcqzh"));
            	fpczz1.setFwzldz(rs.getString("fwzldz"));
            	
            	fpczmx1.setPm(rs.getString("pm"));
            	fpczmx1.setDj(rs.getBigDecimal("dj"));
            	fpczmx1.setSl(rs.getBigDecimal("sl"));
            	fpczmx1.setJe(rs.getBigDecimal("je"));
            	
            	htypzdzgxb1.setXh(rs.getString("xh"));
            	htypzdzgxb1.setHtbh(rs.getString("htbh"));
            	htypzdzgxb1.setMmfbz(rs.getString("mmfbz"));
            	htypzdzgxb1.setPzfldm(rs.getString("pzfldm"));
            	htypzdzgxb1.setPizzldm(rs.getString("pizzldm"));
            	htypzdzgxb1.setPzhm(rs.getString("pzhm"));
            	htypzdzgxb1.setPzndzb(rs.getString("pzndzb"));
            	htypzdzgxb1.setCjr(rs.getString("cjr"));
            	htypzdzgxb1.setCjrq(rs.getTimestamp("cjrq"));
            	htypzdzgxb1.setLrr(rs.getString("lrr"));
            	htypzdzgxb1.setLrrq(rs.getTimestamp("lrrq"));
            	htypzdzgxb1.setSbbh(rs.getString("sbbh"));
            	
            	fpcdBO.setFpczz(fpczz1);
            	fpcdBO.setFpczmx(fpczmx1);
            	fpcdBO.setHtypzdzgxb(htypzdzgxb1);
            	
            	FptpList.add(fpcdBO);
           }
            
            rs.close();
        } catch (SQLException e) {
        	e.printStackTrace();
   		 throw new ApplicationException("������Ʊ�ش�����ʱ��������",e);
        } finally {
            ps.close();
            QSDBUtil.freeConnection(conn);
        }
        return FptpList;
    }
    
    
	/**
	 * ��Сдת��д
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public static String toChineseCharacter(double money) throws Exception {
		double temp = 0;
		long l = Math.abs((long) money);
		BigDecimal bil = new BigDecimal(l);
		if (bil.toString().length() > 14) 
		{
			throw new Exception("����̫�󣬼��㾫�Ȳ���!");
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		int i = 0;
		String result = "", sign = "", tempStr = "", temp1 = "";
		String[] arr = null;
		sign = money < 0 ? "��" : "";
		temp = Math.abs(money);
		if (l == temp) {
			result = doForEach(new BigDecimal(temp).multiply(new BigDecimal(100)).toString(),
					sign);
		} else {
			nf.setMaximumFractionDigits(2);
			temp1 = nf.format(temp);
			arr = temp1.split(",");
			while (i < arr.length) {
				tempStr += arr[i];
				i++;
			}
			BigDecimal b = new BigDecimal(tempStr);
			b = b.multiply(new BigDecimal(100));
			tempStr=b.toString();
			if(tempStr.indexOf(".")==tempStr.length()-3){
				result = doForEach(tempStr.substring(0,
					tempStr.length() - 3), sign);
			}else{
				result = doForEach(tempStr.substring(0,
						tempStr.length() - 3)+"0", sign);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param result
	 * @param sign
	 * @return
	 */
	public static String doForEach(String result, String sign) {
		//System.out.println("��ӡ���������" + result);
		String flag = "", b_string = "";
		String[] arr = { "��", "��", "Բ", "ʰ", "��", "Ǫ", "��", "ʰ", "��", "Ǫ", "��",
				"ʰ", "��", "Ǫ", "��", "ʰ" };
		String[] arr1 = { "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
		boolean zero = true;
		int len = 0, i = 0, z_count = 0;
		if (result == null) {
			len = 0;
		} else {
			len = result.length();
		}
		while (i < len) {
			flag = result.substring(i, i + 1);
			i++;
			if (flag.equals("0")) {
				if (len - i == 10 || len - i == 6 || len - i == 2 || len == i) {
					if (zero) {
						b_string = b_string.substring(0,
								(b_string.length()) - 1);
						zero = false;
					}
					if (len - i == 10) {
						b_string = b_string + "��";
					}
					if (len - i == 6) {
						b_string = b_string + "��";
					}
					if (len - i == 2) {
						b_string = b_string + "Բ";
					}
					if (len == i) {
						b_string = b_string + "��";
					}
					z_count = 0;
				} else {
					if (z_count == 0) {
						b_string = b_string + "��";
						zero = true;
					}
					z_count = z_count + 1;
				}
			} else {
				b_string = b_string + arr1[Integer.parseInt(flag) - 1]
						+ arr[len - i];
				z_count = 0;
				zero = false;
			}
		}
		b_string = sign + b_string;
		return b_string;
	}
	
    /**
     * ȡ����Ӧ�����ӵ�ַ
     * @param linkName ���ӵ��ֶ���
     * @return linkAddress ���ӵ�ַ
     * @throws SQLException 
     * @author zhangj
     */
    public static String getLink(String linkName){
    	
    	String linkAddress="";
    	Connection conn=null;   
     	PreparedStatement pst = null;
     	ResultSet rs = null;
     	
     	String sql="select propertyname,propertyvalue  from sbdb.SB_JL_PROPERTIES where propertyname=?";
     	
     	 try {
     		 conn = QSDBUtil.getConnection();
			 pst = conn.prepareStatement(sql);
             pst.setString(1, linkName);
             rs = pst.executeQuery();
             
             while (rs.next()) 
             {
            	 linkAddress=rs.getString("propertyvalue");
             }
            
     	 }
     	 catch (BaseException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally { 
			try {
			if(rs!=null){				
				rs.close();
			}
			if(pst!=null){
				pst.close();
			}  
			} catch (SQLException e) {
				e.printStackTrace();
			}			
     		QSDBUtil.freeConnection(conn);
     		
       }

  		return linkAddress;

    }
    /**
     *	����������Ȩ��ת������û�д����ֻ���ڴ�����ת��
     * @param fwqszylx ����Ȩ��ת������
     * @return String ����Ȩ��ת����������
     * @author zhangj
     */ 
    public static String getFwqszylxmc(String fwqszylx){

    	if("03".equals(fwqszylx)){
    		return "��������";
    	}else if("04".equals(fwqszylx)){
    		return "��������";
    	}else if("05".equals(fwqszylx)){
    		return "���ݽ���";
    	}else if("06".equals(fwqszylx)){
    		return "��Ժ�ж�";
    	}else{
    		return "��������";
    	}
    	
    }

}
