/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.SzsmYskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfswjghdxxlrForm;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.StringUtil;


/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 在业务层（区别于WEB层）的公用函数类</p>
 * @author 契税开发组－－赵博
 * @version 1.1
 */
public class CommonUtil {
	
	/**
	 * 根据证件代码获取证件类型名称
	 * @param form
	 * @param zjdm
	 * @return 证件名称
	 * @author hhb
	 */
	public static String getZjmc(ClfswjghdxxlrForm form, String zjdm){
		List list=form.getZjList();
		String zjmc = "";
		for (int i = 0; i < list.size(); i++) {
			Zjlx zjlx= (Zjlx)list.get(i);
			if(zjlx.getZjlxdm().equals(zjdm)){
				zjmc = zjlx.getZjlxmc();
				return zjmc;
			}
		}
		return zjmc;
	}
    /**
     * 获取数据库的系统时间。
     *
     * @param con the Connection.
     * @return Timestamp 系统时间。
     * @throws BaseException 可能抛出的异常。
     */
    public static Timestamp getDBtime(Connection con) throws BaseException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "select sysdate from dual";
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            rs.next();

            Timestamp dbtime = rs.getTimestamp("SYSDATE");
            return dbtime;
        } catch (SQLException ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                pst.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
    }

    /**
     * 获取前台传到后台的查询条件。
     *
     * @param Object obj，实际为BO。
     * @return String，处理好的查询条件，如果没有查询条件返回一个空字符串""。
     * @throws BaseException 可能抛出的异常。
     */
    public static String getSqlQueryConditions(Object obj) {

        boolean firstCondition = true;
        StringBuffer userSQL = new StringBuffer("");

        if (obj == null) { //如果传入的业务对象为null，则返回一个null给调用者捕获
            return null;
        }
        try {
            for (int i = 0; i < obj.getClass().getFields().length; i++) {

                String name = obj.getClass().getFields()[i].getName(); //得到业务对象的实例字段名称，也就是查询的条件名称
                Object o = obj.getClass().getFields()[i].get(obj); //得到业务对象的实例字段的值，也就是该查询条件的值

                if (o == null) { //如果该实例字段为null
                    continue;
                } else {
                    if (firstCondition) { //如果是第一个不为null的查询条件，添加where
                        userSQL.append(" WHERE ");
                        firstCondition = false;
                    } else { //否则直接加AND
                        userSQL.append(" AND ");
                    }
                }

                if (o instanceof Integer) {
                    userSQL.append(name).append(" = ").append(((Integer) o).
                            intValue());
                } else if (o instanceof Long) {
                    userSQL.append(name).append(" = ").append(((Long) o).
                            longValue());
                } else if (o instanceof Float) {
                    userSQL.append(name).append(" = ").append(((Float) o).
                            floatValue());
                } else if (o instanceof Double) {
                    userSQL.append(name).append(" = ").append(((Double) o).
                            doubleValue());
                } else if (o instanceof String) {
                    userSQL.append(name).append(" = '").append((String) o).
                            append("'");
                } else if (o instanceof Timestamp) {
                    ; //暂时不提供Timestamp的处理，因为不知道查询的匹配条件是“= or between”
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userSQL.toString();
    }

    /**
     * 获取前台传到后台的查询条件。
     *
     * @param Object obj，实际为BO。
     * @return String，处理好的查询条件，如果没有查询条件返回一个空字符串""，此方法用于多表查询。
     * @throws BaseException 可能抛出的异常。
     */
    public static String getSqlQueryCondition(Object obj) {

        boolean firstCondition = true;
        StringBuffer userSQL = new StringBuffer("");

        if (obj == null) { //如果传入的业务对象为null，则返回一个null给调用者捕获
            return null;
        }
        try {
            for (int i = 0; i < obj.getClass().getFields().length; i++) {

                String name = obj.getClass().getFields()[i].getName(); //得到业务对象的实例字段名称，也就是查询的条件名称
                Object o = obj.getClass().getFields()[i].get(obj); //得到业务对象的实例字段的值，也就是该查询条件的值

                if (o == null) { //如果该实例字段为null
                    continue;
                } else {
                    if (firstCondition) { //如果是第一个不为null的查询条件，添加where
                        userSQL.append(" ");
                        firstCondition = false;
                    } else { //否则直接加AND
                        userSQL.append(" AND ");
                    }
                }

                if (o instanceof Integer) {
                    userSQL.append(name).append(" = ").append(((Integer) o).
                            intValue());
                } else if (o instanceof Long) {
                    userSQL.append(name).append(" = ").append(((Long) o).
                            longValue());
                } else if (o instanceof Float) {
                    userSQL.append(name).append(" = ").append(((Float) o).
                            floatValue());
                } else if (o instanceof Double) {
                    userSQL.append(name).append(" = ").append(((Double) o).
                            doubleValue());
                } else if (o instanceof String) {
                    userSQL.append(name).append(" = '").append((String) o).
                            append("'");
                } else if (o instanceof Timestamp) {
                    ; //暂时不提供Timestamp的处理，因为不知道查询的匹配条件是“= or between”
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userSQL.toString();
    }

    /**
     * 申报表号（个人、非个人），同时包括不征的表号：
     * "1位字母" + ZHDM(8位,剪除01市局标示) + 时间(040101) + 流水号(4位)
     * 申报表号的1位字母为"S"
     * 1234567890123456789
     * @param zhdm String
     * @param conn Connection
     * @param lx String
     * @return String
     * @throws ApplicationException
     */
    private static String getSBBH(String zhdm, Connection conn, String lx) throws
            ApplicationException {
        if (zhdm == null) {
            throw new ApplicationException("传入的参数“帐户代码”不能为空！");
        }

        if (zhdm.length() != 10) {
            throw new ApplicationException("传入的参数“帐户代码”位数异常");
        }

        //ZHDM(8位,剪除01市局标示)
        String SBBH = lx + zhdm.substring(2);
        SBBH = SBBH + getDatetime(new Date(), "yyMMdd");

        /** @todo 修改数据库中SBBH字段长度 */
        Statement stmt = null;
        String sql =
                "select MAX(SBBH) maxsbbh from qsdb.qs_jl_sbzb where sbbh like '" +
                SBBH + "____'";
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String maxSbbh = rs.getString("maxsbbh");
                if (maxSbbh == null) {
                    SBBH = SBBH + "0001";
                } else {
                    maxSbbh = maxSbbh.substring(15);
                    //Debug.out("maxSbbh in CommonUtil.getSBBH() is " + maxSbbh);
                    maxSbbh = String.valueOf(Integer.parseInt(maxSbbh) + 1);
                    //Debug.out("maxSbbh is " + maxSbbh);
                    //Debug.out("4-maxSbbh.length() is " + (4 - maxSbbh.length()));
                    String newLS = DataConvert.fillString(maxSbbh, "0", 4);
                    //Debug.out("newLS is " + newLS);
                    SBBH = SBBH + newLS;
                }
            } else {
                SBBH = SBBH + "0001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getSBBH() 方法中数据库错误！", e);
        }
        return SBBH;
    }

    /**
     * 获得申报表号
     * "1位字母" + ZHDM(8位,剪除01市局标示) + 时间(040101) + 流水号(4位)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getSBBH(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "S");
    }

    /**
     * 获得申报表号（不征的采集表号）
     * "1位字母" + ZHDM(8位,剪除01市局标示) + 时间(040101) + 流水号(4位)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getBZBH(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "B");
    }

    /**
     * 获得申报表号（批量的采集表号）
     * "1位字母" + ZHDM(8位,剪除01市局标示) + 时间(040101) + 流水号(4位)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getPLBH(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "P");
    }


    /**
     * 减免申报表号（个人、非个人）
     * "1位字母" + ZHDM(8位,剪除01市局标示) + 时间(040101) + 流水号(4位)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getJMSBBH2(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "J");
    }

    /**
     * 减免申报表号（个人、非个人）：
     * "1位字母" + ZHDM(8位,剪除01市局标示) + 时间(040101) + 流水号(4位)
     * 申报表号的1位字母为"S"
     * 1234567890123456789
     * @return String
     */
    public static String getJMSBBH(String zhdm, Connection conn) throws
            ApplicationException {
        if (zhdm == null) {
            throw new ApplicationException("传入的参数“帐户代码”不能为空！");
        }

        if (zhdm.length() != 10) {
            throw new ApplicationException("传入的参数“帐户代码”位数异常");
        }

        //ZHDM(8位,剪除01市局标示)
        String JMSBBH = "J" + zhdm.substring(2);
        JMSBBH = JMSBBH + getDatetime(new Date(), "yyMMdd");

        /** @todo 修改数据库中减免申报表中的JMSBH字段长度 */
        Statement stmt = null;
        String sql =
                "select MAX(JMSBBH) maxjmsbh from QSDB.QS_JL_JMSBB  where sbbh like '" +
                JMSBBH + "____'";
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String maxJmsbh = rs.getString("maxjmsbh");
                if (maxJmsbh == null) {
                    JMSBBH = JMSBBH + "0001";
                } else {
                    maxJmsbh = maxJmsbh.substring(15);

                    maxJmsbh = String.valueOf(Integer.parseInt(maxJmsbh) + 1);

                    String newLS = DataConvert.fillString(maxJmsbh, "0", 4);

                    JMSBBH = JMSBBH + newLS;
                }
            } else {
                JMSBBH = JMSBBH + "0001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getJMSBBH() 方法中数据库错误！",
                                           e);
        }
        return JMSBBH;
    }

    /**
     * @return String 房屋土地唯一标示
     */
    public static String getTDFWID(Connection conn) throws ApplicationException {
        Statement stmt = null;
        String sql = "SELECT QSDB.SEQ_QS_JL_TDFWID.NEXTVAL TDFWID FROM DUAL";
        ResultSet rs = null;
        String maxId = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                maxId = rs.getString("TDFWID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getTDFWID() 方法中数据库错误！",
                                           e);
        }
        return maxId;
    }

    /**
     * @return String 房屋土地唯一标示
     */
    public static String getCqxxbh(Connection conn) throws ApplicationException {
        Statement stmt = null;
        String sql = "SELECT QSDB.SEQ_QS_JL_CQXXBH.NEXTVAL CQXXBH FROM DUAL";
        ResultSet rs = null;
        String maxId = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                maxId = rs.getString("CQXXBH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getCqxxbh() 方法中数据库错误！",
                                           e);
        }
        return maxId;
    }

    /**
     * 根据传入的dateFormat，返回相应的截取后的格式的String型的结果
     * @param date Date
     * @param dateFormat String
     * @return String
     */
    public static String getDatetime(Date date, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    /**
     * 获取核定通知书号码
     * hashmap 中的key:
     * ndzb 年度字别
     * wsjc 文书简称
     * lsh 流水号
     * hdtzsh 核定通知书号
     * @param ud UserData
     * @param conn Connection
     * @return HashMap
     * @throws BaseException
     */
    public static HashMap getHDTZSH(UserData ud, Connection conn) throws
            BaseException {
        HashMap resultMap = new HashMap();
        String hdtzsh = "";
        int lsh = 0;
        String tmp = "";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Swjgzzjg swjgzzjg = getSwjgzzjg(ud, conn);
            Swjgzzjg qxfjSwjgzzjg = getSwjgzzjg(swjgzzjg.qxfjdm, conn);
            String ndzb = DateUtil.getDate().substring(0, 4); //年度字别
            String wsjc = qxfjSwjgzzjg.wsjc; //区县字别  海、崇等

            String sql = " select nvl(max(lsh),0) lsh from qsdb.qs_jl_hdtzs t ";
            sql = sql + " where t.ndzb='" + ndzb + "' and t.wsjc='" + wsjc +
                  "'";
            stmt = conn.createStatement();
            //Debug.out("获取核定通知书号码 sql is " + sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lsh = rs.getInt("lsh");
                if (lsh == 0) {
                    tmp = "1";
                } else {
                    tmp = String.valueOf(lsh + 1);
                }
            } else {
                tmp = "1";
            }
            //用0补全位数
            //String newLS = DataConvert.fillString(tmp, "0", 4);
            String newLS = DataConvert.fillString(tmp, "0", 6); //因大兴2014年获取核定通知书号码超过4位，特将位数调整至6位 tujb 2014.10.15

            /** @todo 不能修改这个字符串的任何字段和表示 */
            hdtzsh = "京（" + wsjc + "）地税契核字（" + ndzb + "）第" + newLS + "号";
            resultMap.put("ndzb", ndzb);
            resultMap.put("wsjc", wsjc);
            resultMap.put("lsh", new BigDecimal(newLS));
            resultMap.put("hdtzsh", hdtzsh);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("取得核定通知书号的时候出错！");
        }
        //Debug.out("获取核定通知书号码 is " + hdtzsh);
        return resultMap;
    }


    /**
     * 获取核定通知书号码_减免申报
     * hashmap 中的key:
     * ndzb 年度字别
     * wsjc 文书简称
     * lsh 流水号
     * hdtzsh 核定通知书号
     * @param ud UserData
     * @param conn Connection
     * @return HashMap
     * @throws BaseException
     */
    public static HashMap getHDTZSH_JMSB(UserData ud, Connection conn) throws
            BaseException {
        HashMap resultMap = new HashMap();
        String hdtzsh = "";
        int lsh = 0;
        String tmp = "";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Swjgzzjg swjgzzjg = getSwjgzzjg(ud, conn);
            Swjgzzjg qxfjSwjgzzjg = getSwjgzzjg(swjgzzjg.qxfjdm, conn);
            String ndzb = DateUtil.getDate().substring(0, 4); //年度字别
            String wsjc = qxfjSwjgzzjg.wsjc; //区县字别  海、崇等

            String sql = " select nvl(max(lsh),0) lsh from qsdb.qs_jl_hdtzs t ";
            sql = sql + " where t.ndzb='" + ndzb + "' and t.wsjc='" + wsjc +
                  "'";
            stmt = conn.createStatement();
            //Debug.out("获取核定通知书号码 sql is " + sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lsh = rs.getInt("lsh");
                if (lsh == 0) {
                    tmp = "1";
                } else {
                    tmp = String.valueOf(lsh + 1);
                }
            } else {
                tmp = "1";
            }
            //用0补全位数
            //String newLS = DataConvert.fillString(tmp, "0", 4);
            String newLS = DataConvert.fillString(tmp, "0", 6); //因大兴2014年获取核定通知书号码超过4位，特将位数调整至6位  tujb 2014.10.15
            
            /** @todo 不能修改这个字符串的任何字段和表示 */
            hdtzsh = wsjc + "地税契核字（" + ndzb + "）第" + newLS + "号";
            resultMap.put("ndzb", ndzb);
            resultMap.put("wsjc", wsjc);
            resultMap.put("lsh", new BigDecimal(newLS));
            resultMap.put("hdtzsh", hdtzsh);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("取得核定通知书号的时候出错！");
        }
        //Debug.out("获取核定通知书号码 is " + hdtzsh);
        return resultMap;
    }

    /**
     * 取得参数UserData的QXDM（区县代码）
     * @param ud UserData
     * @return String
     */
    public static String getQxdm(UserData ud) {
        String qxdm = "";
        //如果UserData中的所属单位代码为空，则返回空的区县代码
        if (ud != null && ud.ssdwdm != null) {
            qxdm = ud.getSsdwdm().substring(0, 2);
        }
        return qxdm;
    }

    /**
     * 根据传来的Grxx值对象，查询自然人的计算机代码
     * 如果没有自然人的计算机代码，则向自然人登记的数据库中插入一条，
     * 并且取得新插入的自然人的计算机代码，返回有计算机代码的Grxx值对象；
     *
     * @param grxx Grxx
     * @return Grxx
     */
    public static Grxx handleZRR(Grxx grxx, UserData ud) throws BaseException {
        try {
            ZRRJBSJ zrrJbsj = null;
            try {
                //先获得个人基本数据
                zrrJbsj = getGrJBSJ(grxx);
            } catch (Exception ex) {
            }
            //如果查询到个人基本数据
            if (zrrJbsj != null && zrrJbsj.getJsjdm() != null) {
                if (zrrJbsj.getNsrmc().equals(grxx.nsrmc)) {
                    grxx.setJsjdm(zrrJbsj.getJsjdm());
                } else {
                    throw new ApplicationException("纳税人名称与自然人登记的纳税人名称不匹配！");
                }
            }
            //如果查询不到个人基本数据
            //则需要插入一条个人登记数据，从返回来的个人信息基本数据，得到计算机代码
            else {
                zrrJbsj = insertGrJBSJ(grxx, ud);
                grxx.setJsjdm(zrrJbsj.getJsjdm());
            }
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex,
                                                 "在CommonUtil.handleZRR()方法中查询登记基本信息失败!");
        }
        return grxx;
    }

    /**
     * 根据传来的Grxx值对象，查询自然人的计算机代码
     * 如果没有自然人的计算机代码，则向自然人登记的数据库中插入一条，
     * 并且取得新插入的自然人的计算机代码，返回有计算机代码的Grxx值对象；
     *
     * @param grxx Grxx
     * @return Grxx
     */
    public static List handleZRR(List nsrList, UserData ud) throws
            BaseException {
        List l = new ArrayList();
        List sl = new ArrayList();
        try {
            ZRRJBSJ zrrJbsj = null;
            for (int i = 0; i < nsrList.size(); i++) {
                Grxx grxx = (Grxx) nsrList.get(i);
                //判断重复！！不用判断，登记保存会返回错误
                /*for (int j=0;j<l.size();j++){
                    Grxx g=(Grxx)l.get(j);
                    if (g.getSfzjlx().equals(grxx.getSfzjlx()) && g.getSfzjhm().equals(grxx.getSfzjhm())){
                 throw new ApplicationException("纳税人名称("+grxx.nsrmc+")与自然人登记的纳税人名称不匹配！");

                    }
                                 }
                 */
                zrrJbsj = null;
                try {
                    //先获得个人基本数据
                    zrrJbsj = getGrJBSJ(grxx);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("自然人基本数据＝" + zrrJbsj);
                //如果查询到个人基本数据
                if (zrrJbsj != null && zrrJbsj.getJsjdm() != null) {
                    if (zrrJbsj.getNsrmc().equals(grxx.nsrmc)) {
                        grxx.setJsjdm(zrrJbsj.getJsjdm());

                        l.add(grxx);
                    } else {
                        throw new ApplicationException("纳税人名称(" + grxx.nsrmc +
                                ")与自然人登记的纳税人名称不匹配！");
                        //throw new ApplicationException("纳税人名称与自然人登记的纳税人名称不匹配！");
                    }
                }
                //如果查询不到个人基本数据
                //则需要插入一条个人登记数据，从返回来的个人信息基本数据，得到计算机代码
                else {
                    sl.add(grxx);
                }

            }

            for (int i = 0; i < sl.size(); i++) {
                Grxx grxx = (Grxx) sl.get(i);
                zrrJbsj = insertGrJBSJ(grxx, ud);
                grxx.setJsjdm(zrrJbsj.getJsjdm());
                l.add(grxx);
            }
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex,
                                                 "在CommonUtil.handleZRR()方法中查询登记基本信息失败!");
        }

        return l;
    }


    /**
     * 取得全部登记基本信息
     * HashMap；
     * key= JBSJ
     * value=SWDJJBSJ (一个值对象)；
     * key=YHZH
     * value=List(List中是多个YHZH值对象)；
     * key=TZF
     * value=List(List中是多个TZF值对象)；
     * key=QYRY（法人）
     * value=QYRY(一个值对象)
     * key= BSRY（办税人员）
     * value=QYRY(一个值对象)
     * key= CWRY（财务人员）
     * value=QYRY(一个值对象)
     * 登记基本信息（一条）：
     * 1.纳税人名称
     * 2.登记注册类型代码
     * 3.登记注册类型名称
     * 4.国家标准行业代码
     * 5.国家标准行业名称
     * 6.隶属关系代码
     * 7.隶属关系名称
     * 8.所属税务机关名称
     * 9.所属税务机关代码
     * 10.纳税人经营地地址
     * 11.经营地联系电话
     * 12.经营地邮编
     * 13. 税务登记证号
     * 14.纳税人身份证件号码
     * 15.开业登记日期
     * 16.纳税人状态
     * 银行信息（多条）：
     * 1.开户银行名称
     * 2.银行帐号
     * 3.基本帐号标识
     * 投资方信息（多条）：
     * 1.投资者身份证号
     * 2.投资者身份证件类型
     * 3.投资者姓名
     * 4.所占投资比例
     * 法人代表信息（一条）
     * 1.法人代表姓名
     * 2.法人代表身份证件号码
     * 办税人员信息（一条）
     * 1.办税人员姓名
     * 2.办税人员身份证件号码
     * 财务人员信息（一条）
     * 1.财务人员姓名
     * 2.财务人员身份证件号码
     * 非空的字段：
     * 纳税人名称、
     * 登记注册类型代码、
     * 登记注册类型名称、
     * 开户银行、
     * 银行帐号、
     * 基本帐号标识、
     * 国家标准行业代码、
     * 国家标准行业名称、
     * 所属税务机关名称、
     * 所属税务机关代码、
     * 税务登记证号、
     * 纳税人身份证件号码、
     * 法人代表姓名、
     * 法人代表身份证件号码、
     * 开业登记日期、
     * 纳税人状态、
     * 投资者证件类型、
     * 投资者身份证号、
     * 投资者姓名、
     * 所占投资比例、
     *
     * @param jsjdm 计算机代码
     * @return HashMap 登记基本信息
     * @throws Exception 查询异常
     */
    public static HashMap getFgrDjInfo(String jsjdm) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        try {
            return djS.getDjInfo(jsjdm);
        } catch (BaseException ex) {
            Debug.printException(ex);
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
        }
    }

    /**
     * 取得全部自然人登记基本信息
     * 如果没有该自然人登记信息，则返回null，
     * 如果有则返回HashMap map map包含该自然人登记的值对象(ZRRJBSJ)、
     * 自然人银行信息列表(List)、值对象(FB_JBSJ)、自然人扣款情况（List）、服务单位（List）
     * map中的key值 是DjOuterConstant中的ZRRJBSJ、ZRRYHZH、ZRRFB、ZRRKKQK、ZRRFWDW
     *
     * @param  zjlxdm  证件类型代码 长度2位 不能为空
     * @param  zjhm  证件号码 长度30位 不能为空
     * @param  gjdm  籍代码 长度3位 不能为空
     * @return HashMap 登记自然人基本信息
     * @throws Exception 查询异常
     */
    public static HashMap getGrDjInfo(Grxx grxx) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        try {
            return djS.getZrrDjInfo(grxx.sfzjlx, grxx.sfzjhm, grxx.gjdm);
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "查询个人登记基本信息失败!");
        }
    }

    /**
     * 取得登记基本信息
     * @param jsjdm 计算机代码
     * @return SWDJJBSJ 登记基本信息值对象
     * @throws Exception 查询异常
     */
    public static SWDJJBSJ getFgrJBSJ(String jsjdm) throws
            Exception {
        SWDJJBSJ ret = null;
        ServiceProxy djS = new ServiceProxy();
        try {
            HashMap djMap = djS.getDjInfo(jsjdm);
            ret = (SWDJJBSJ) djMap.get("JBSJ");
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
        }
        return ret;
    }

    /**
     * 取得登记基本信息
     * @param jsjdm 计算机代码
     * @return SWDJJBSJ 登记基本信息值对象
     * @throws Exception 查询异常
     */
    public static ZRRJBSJ getGrJBSJ(String jsjdm) throws
            Exception {
        ZRRJBSJ ret = null;
        ServiceProxy djS = new ServiceProxy();
        try {
            HashMap djMap = djS.getZrrDjInfo(jsjdm);
            ret = (ZRRJBSJ) djMap.get("ZRRJBSJ");
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败!");
        }
        return ret;
    }

    /**
     * 取得纳税人银行信息
     * @param jsjdm 计算机代码
     * @return List YHZH值对象列表
     * @throws Exception 查询异常
     */
    public static List getYHZH(String jsjdm) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        List ret = null;
        try {
            ret = (List) djS.getDjInfo(jsjdm).get("YHZH");
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "查询银行信息失败!");
        }
        return ret;

    }

    /**
     * 取自然人基本登记信息
     * @param  zjlxdm  证件类型代码 长度2位 不能为空
     * @param  zjhm  证件号码 长度30位 不能为空
     * @param  gjdm  籍代码 长度3位 不能为空
     * @return  ZRRJBSJ值对象
     * @throws Exception 查询异常
     */
    public static ZRRJBSJ getGrJBSJ(Grxx grxx) throws Exception {
        ServiceProxy djS = new ServiceProxy();
        ZRRJBSJ zrrJbsj = new ZRRJBSJ();
        try {
            //Map djMap = djS.getZrrDjInfo(grxx.sfzjlx, grxx.sfzjhm, grxx.gjdm);
            Map djMap = DJHelper.getZrrDjInfo(grxx.sfzjlx, grxx.sfzjhm,
                                              grxx.gjdm);
            zrrJbsj = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
            Debug.out("通过接口获取自然人基本数据" + zrrJbsj);
            if (zrrJbsj != null) {
                Debug.out("==>纳税人名称：" + zrrJbsj.getNsrmc());
                Debug.out("==>计算机代码：" + zrrJbsj.getJsjdm());
            }
            if (zrrJbsj == null) {
                throw new ApplicationException("不存在该自然人信息");
            }
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "查询自然人信息失败!");
        }
        return zrrJbsj;
    }

    public static String checkZrr(String sfzjlx, String sfzjhm, String nsrmc,
                                  String gjdm, String cjr, String swjgzzjgdm) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        ZRRJBSJ zrrJbsj = new ZRRJBSJ();
        try {
            Map djMap = djS.getZrrDjInfo(sfzjlx, sfzjhm, gjdm);
            zrrJbsj = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "查询自然人信息失败!");
        }

        //如果查询到个人基本数据
        if (zrrJbsj != null && zrrJbsj.getJsjdm() != null) {
            return zrrJbsj.getJsjdm();
        }
        //如果查询不到个人基本数据
        //则需要插入一条个人登记数据，从返回来的个人信息基本数据，得到计算机代码
        else {

            HashMap zrrMap = new HashMap();
            zrrMap.put(DjOuterConstant.ZJLXDM, sfzjlx);
            zrrMap.put(DjOuterConstant.ZJHM, sfzjhm);
            zrrMap.put(DjOuterConstant.GJDM, gjdm);
            zrrMap.put(DjOuterConstant.NSRMC, nsrmc);
            zrrMap.put(DjOuterConstant.SWJGZZJGDM, swjgzzjgdm);
            zrrMap.put(DjOuterConstant.CZRY, cjr);
            zrrMap.put(DjOuterConstant.CZRQ,
                       new java.sql.Timestamp(System.currentTimeMillis()));

            try {
                zrrJbsj = djS.insertZrrDjInfo(zrrMap);
                if (zrrJbsj == null) {
                    throw new ApplicationException("插入 " + nsrmc + " 自然人信息出错！");
                }
            } catch (BaseException ex) {
                throw ExceptionUtil.getBaseException(ex, "插入自然人信息失败!");
            }

        }

        return zrrJbsj.getJsjdm();
    }

    /**
     * 往登记的库中插入自然人基本登记信息

     HashMap 对应的key值定义在com.ttsoft.bjtax.dj. DjOuterConstant
     public static final String ZJLXDM = "zjlxdm";//String 证件类型代码 不能为空
         public static final String ZJHM = "zjhm";//String 证件号码 不能为空
         public static final String GJDM = "gjdm";//String 国籍代码 不能为空
         public static final String NSRMC = "nsrmc";//String 纳税人名称 不能为空
     public static final String SWJGZZJGDM = "swjgzzjgdm";//String 税务机关组织机构代码  不能为空
         public static final String CZRY = "czry";//String 操作人员  不能为空
     public static final String CZRQ = "czrq";//TimeStamp 操作日期（需要商议，是否传递该参数）
     *
     *
     * @param  Grxx  个人信息值对象
     * @return  ZRRJBSJ值对象
     * @throws Exception 查询异常
     */
    public static ZRRJBSJ insertGrJBSJ(Grxx grxx, UserData ud) throws Exception {
        ServiceProxy djS = new ServiceProxy();
        HashMap zrrMap = new HashMap();
        zrrMap.put(DjOuterConstant.ZJLXDM, grxx.getSfzjlx());
        zrrMap.put(DjOuterConstant.ZJHM, grxx.getSfzjhm());
        zrrMap.put(DjOuterConstant.GJDM, grxx.getGjdm());
        zrrMap.put(DjOuterConstant.NSRMC, grxx.getNsrmc());
        zrrMap.put(DjOuterConstant.SWJGZZJGDM, ud.getSsdwdm());
        zrrMap.put(DjOuterConstant.CZRY, grxx.getCjr());
        zrrMap.put(DjOuterConstant.CZRQ, grxx.getCjrq());
        ZRRJBSJ GrJBSJ = null;
        try {
            GrJBSJ = djS.insertZrrDjInfo(zrrMap);
            if (GrJBSJ == null) {
                throw new ApplicationException("插入 " + grxx.nsrmc + " 自然人信息出错！");
            }
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "插入自然人信息失败!");
        }
        return GrJBSJ;
    }

    /**
     * 得到申报编号
     * @return String
     */
    public static String getJksSbbh(String jsjdm) throws
            BaseException {
        String sbbh = null;
        try {
            sbbh = com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getSbbh(jsjdm);
            if (sbbh == null) {
                throw new ApplicationException("不存在申报编号");
            }
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "查询申报编号失败!");
        }
        return sbbh;
    }

    /**
     * 得到生成完税证时候需要的计算机代码
     * @return String
     */
    public static String getWszJsjdm(UserData ud, Connection con) throws
            BaseException {
        String jsjdm = "";
        String sql =
                "select jsjdm from dmdb.gy_dm_swjgzzjg where SWJGZZJGDM = '" +
                ud.ssdwdm + "'";
        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            if (rs.next()) {
                jsjdm = rs.getString(1);
            }
            //由税务机关组织机构代码表得到计算机代码
            //引用税费的接口获得
//          /** @todo 改称正确的方法取得税务机关组织结构代码 */
//          jsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG", "swjgzzjgdm", ud.ssdwdm,"jsjdm");
        } catch (Exception ex) {
//          throw ExceptionUtil.getBaseException(ex, "获得完税证主表所需的计算机代码的时候出错!");
            ErrorLog.makeLog(ud, "契税申报征收－WszProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");
//             throw ExceptionUtil.getBaseException(ex, ex.getMessage());
        }
        return jsjdm;
    }

    /**
     * 依照税务机关组织机构代码查询国库代码
     * @param swjgzzjgdm 税务机关组织机构代码
     * @return swjgzzjg 税务机关组织机构信息
     * @throws java.lang.Exception 操作异常
     */
    public static Swjgzzjg getSwjgzzjg(UserData ud, Connection con) throws
            Exception {
        Swjgzzjg swjgzzjg = new Swjgzzjg();
        swjgzzjg.setSwjgzzjgdm(ud.ssdwdm);

        try {
            swjgzzjg = (Swjgzzjg) DAOFactory.getInstance().getSwjgzzjgDAO().get(
                    swjgzzjg, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "查询国库失败!");
        }
        return swjgzzjg;
    }

    /**
     * 依照税务机关组织机构代码查询国库代码
     * @param swjgzzjgdm 税务机关组织机构代码
     * @return swjgzzjg 税务机关组织机构信息
     * @throws java.lang.Exception 操作异常
     */
    public static Swjgzzjg getSwjgzzjg(String swjgzzjgdm, Connection con) throws
            Exception {
        Swjgzzjg swjgzzjg = new Swjgzzjg();
        swjgzzjg.setSwjgzzjgdm(swjgzzjgdm);

        try {
            swjgzzjg = (Swjgzzjg) DAOFactory.getInstance().getSwjgzzjgDAO().get(
                    swjgzzjg, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "查询国库失败!");
        }
        return swjgzzjg;
    }

    /**
     * 依照税务机关组织机构代码查询国库代码
     * @param swjgzzjgdm 税务机关组织机构代码
     * @return swjgzzjg 税务机关组织机构信息
     * @throws java.lang.Exception 操作异常
     */
    public static Yskm getYskm(String szsmdm, Connection con) throws Exception {
        Yskm yskm = new Yskm();
        SzsmYskm sy = new SzsmYskm();
        sy.setSzsmdm(szsmdm);

        try {
            sy = (SzsmYskm) DAOFactory.getInstance().getSzsmYskmDAO().get(sy,
                    con);
            yskm.setYskmdm(sy.getMryskmdm());
            System.out.println("sy.getMryskmdm()契税管理通过帐务取得预算科目代码：" +
                               sy.getMryskmdm());
            System.out.println("yskm.getYskmdm()契税管理通过帐务取得预算科目代码：" +
                               yskm.getYskmdm());
            yskm = (Yskm) DAOFactory.getInstance().getYskmDAO().get(yskm, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "查询国库失败!");
        }
        return yskm;
    }

    /**
     * 通过sequence 获得申报汇总单号
     * 汇总单号的长的为8位
     * @param con 数据库连接
     * @return 返回汇总单号
     * @throws Exception 操作异常
     */
    public static String getSequenceOfSbhzd(Connection con) throws
            Exception {
        String sequence = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 获得数据库连接
            String sql = "select sbdb.seq_sb_hzdh.nextval from dual";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            //汇总单号
            long hzdhindex = rs.getLong("nextval");
            NumberFormat nmbFormat = new DecimalFormat("00000000");
            sequence = nmbFormat.format(hzdhindex);

            return sequence;
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "得到汇总单号的最大顺序号失败!");
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
    } //End of getSequenceOfSbhzd


    /**
     * 通过sequence 获完税证序号
     * 完税证序号的长的为4位
     * @param con 数据库连接
     * @return 返回汇总单号
     * @throws Exception 操作异常
     */
    public String getSequenceOfWSZXH(Connection con) throws
            Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获得数据库连接
            String sql = "select sbdb.seq_sb_wszxh.nextval from dual";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            //完税证序号
            String wszxh = rs.getString("nextval");
            return wszxh;
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "得到序列号失败!");
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
    } //End of getSequenceOfWSZXH

    public static HashMap getJmsj(List cqL, List gfL, Spjgxx sp) {
        HashMap map = new HashMap();
        //	List cqL = bo.getCqList();
        //	List gfL = bo.getGyzfList();
        //	Spjgxx sp = bo.getVoSpjgxx();
        double cqze = 0;
        double gyzfze = 0;
        double zcspze = 0;

        if (cqL != null) {
            for (int i = 0; i < cqL.size(); i++) {
                Jsblcq cq = (Jsblcq) cqL.get(i);
                cqze = cqze + cq.getBcsybce().doubleValue();
            }
        }

        if (gfL != null) {
            for (int i = 0; i < gfL.size(); i++) {
                Jsblgyzf gf = (Jsblgyzf) gfL.get(i);
                gyzfze = gyzfze + gf.getBcdke().doubleValue();
            }
        }

        if (sp != null && sp.getJmsje() != null) {
            zcspze = sp.getJmsje().doubleValue();
        }
        Debug.out("公有住房抵扣项 是 " + gyzfze);
        Debug.out("拆迁减免税金额" + cqze + "元");
        Debug.out("正常审批减免税金额" + zcspze + "元");
        map.put(Constants.JE_CQJMJE, new BigDecimal(cqze));
        map.put(Constants.JE_ZCSPJMJE, new BigDecimal(zcspze));
        map.put(Constants.JE_QYZFBCDKE, new BigDecimal(gyzfze));

        return map;

    }

    public static HashMap getJmsj(String sbbh, Connection conn) throws
            ApplicationException {
        HashMap map = new HashMap();
        PreparedStatement pst = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        sql
                .append(
                        "select a.sbbh, nvl(t1.cq_total,0) cq_total, nvl(t2.gyzf_total,0) gyzf_total, nvl(t3.zcsp_total,0) zcsp_total ")
                .append(" from qsdb.qs_jl_sbzb a, ")
                .append(" (select b.sbbh, sum(b.bcsybce) cq_total ")
                .append(
                        " from qsdb.qs_jl_sbzb a, qsdb.qs_jl_sbcqgl b, qsdb.qs_jl_jsblcq c ")
                .append(
                        " where  a.sbbh = b.sbbh and b.cqxyh = c.cqxyh and a.BLJMSBS!='")
                .append(Constants.ZB_BLJMSBS_BZ)
                .append("'")
                .append(" group by b.sbbh) t1, ")
                .append(" (select b.sbbh, sum(b.bcdke) gyzf_total ")
                .append(
                        " from qsdb.qs_jl_sbzb a, qsdb.qs_jl_sbgyzf b, qsdb.qs_jl_jsblgyzf c ")
                .append(
                        " where  a.sbbh = b.sbbh and b.yggyzfqszsh = c.yggyzfqszsh  and a.BLJMSBS!='")
                .append(Constants.ZB_BLJMSBS_BZ)
                .append("'")
                .append(" group by b.sbbh) t2, ")
                .append(" (select b.sbbh, sum(b.jmsje) zcsp_total ")
                .append(" from qsdb.qs_jl_sbzb a, qsdb.qs_jl_spjgxx b ")
                .append(" where  a.sbbh = b.sbbh  and a.BLJMSBS!='")
                .append(Constants.ZB_BLJMSBS_BZ)
                .append("'")
                .append(" group by b.sbbh) t3 ")
                .append(
                        " where a.sbbh = t1.sbbh(+) and a.sbbh = t2.sbbh(+) and a.sbbh = t3.sbbh(+) and ")
                .append(" a.sbbh='").append(sbbh).append("'");

        Debug.out("计算税金的总公式：" + sql.toString());
        try {
            //执行 sql 语句；
            double cqze = 0;
            double gyzfze = 0;
            double zcspze = 0;
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            if (rs.next()) {
                cqze = rs.getDouble("cq_total"); //取得拆迁的总金额
                gyzfze = rs.getDouble("gyzf_total"); //取得公有住房的总金额
                zcspze = rs.getDouble("zcsp_total"); //取得正常审批的总金额
            }
            Debug.out("公有住房抵扣项 是 " + gyzfze);
            Debug.out("拆迁减免税金额" + cqze + "元");
            Debug.out("正常审批减免税金额" + zcspze + "元");

            map.put(Constants.JE_CQJMJE, new BigDecimal(cqze));
            map.put(Constants.JE_ZCSPJMJE, new BigDecimal(zcspze));
            map.put(Constants.JE_QYZFBCDKE, new BigDecimal(gyzfze));

        } catch (Exception ex) {
            throw new ApplicationException("获取契税金额的时候，报错！");
        }
        return map;
    }

    public static JghdsjBo getJZSE(SbxxBo bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //根据申报表号，获得申报主表数据
            Sbzb sbzb = bo.getVoSbzb();
            String sbbh = sbzb.getSbbh();
            FwjhxxBo dfbo = bo.getDfSbxxBo();
            Tufwxx dfTfxx = new Tufwxx();
            if (dfbo != null) {
                dfTfxx = dfbo.getTufwxx();
            }

            //根据申报表号获得，土地房屋的基本信息数据
            //从而得到成交价格、房屋面积，计算得到是否符合普通住宅的减半征收的政策
            Tufwxx tfxx = bo.getVoTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqList(), bo.getGyzfList(),
                                bo.getVoSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //获得普通住宅减免的政策数据
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //上年度商品房住宅平均售价

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("获取契税金额的时候，报错！");
        }
        return hdbo;
    }

    public static JghdsjBo getJZSE(PldrBo bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //根据申报表号，获得申报主表数据
            Sbzb sbzb = bo.getSbzb();
            String sbbh = sbzb.getSbbh();

            Tufwxx dfTfxx = new Tufwxx();

            //根据申报表号获得，土地房屋的基本信息数据
            //从而得到成交价格、房屋面积，计算得到是否符合普通住宅的减半征收的政策
            Tufwxx tfxx = bo.getTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqxxList(), bo.getGyzfxxList(),
                                bo.getSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //获得普通住宅减免的政策数据
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //上年度商品房住宅平均售价

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("获取契税金额的时候，报错！");
        }
        return hdbo;
    }

    //说明：该方法是针对页面中"批量受理(税务人员)"模块
    public static JghdsjBo getJZSE2(PldrBo2 bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //根据申报表号，获得申报主表数据
            Sbzb sbzb = bo.getSbzb();
            String sbbh = sbzb.getSbbh();

            Tufwxx dfTfxx = new Tufwxx();

            //根据申报表号获得，土地房屋的基本信息数据
            //从而得到成交价格、房屋面积，计算得到是否符合普通住宅的减半征收的政策
            Tufwxx tfxx = bo.getTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqxxList(), bo.getGyzfxxList(),
                                bo.getSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //获得普通住宅减免的政策数据
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //上年度商品房住宅平均售价

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("获取契税金额的时候，报错！");
        }
        return hdbo;
    }

    public static JghdsjBo getJZSE(JmsbxxBo bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //根据申报表号，获得申报主表数据
            Sbzb sbzb = bo.getVoSbzb();
            String sbbh = sbzb.getSbbh();
            FwjhxxBo dfbo = bo.getDfSbxxBo();
            Tufwxx dfTfxx = new Tufwxx();
            if (dfbo != null) {
                dfTfxx = dfbo.getTufwxx();
            }

            //根据申报表号获得，土地房屋的基本信息数据
            //从而得到成交价格、房屋面积，计算得到是否符合普通住宅的减半征收的政策
            Tufwxx tfxx = bo.getVoTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqList(), bo.getGyzfList(),
                                bo.getVoSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //获得普通住宅减免的政策数据
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //上年度商品房住宅平均售价

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("获取契税金额的时候，报错！");
        }
        return hdbo;
    }


    private static double getSl(BigDecimal sbsl, String zcsl) {
        double sl = 0.03;

        if (sbsl != null && sbsl.doubleValue() > 0) {
            sl = sbsl.doubleValue();
        } else {
            sl = StringUtil.getDoubleValue(zcsl);
        }
        //Debug.out("税率： " + sl + "");
        return sl;
    }

    private static String getZbz(String zcdm, Connection conn) throws Exception {
        Zcwh zcwh = new Zcwh();
        zcwh.zbdm = zcdm;
        zcwh = (Zcwh) DAOFactory.getInstance().getZcwhDAO().get(zcwh, conn);
        return zcwh.zbz;
    }

    private static JghdsjBo getJZSE(Sbzb sbzb, Tufwxx tfxx, Tufwxx dfTfxx,
                                    double sl, double puzfpjjg, Map jmMap) throws
            Exception {
        // Map map = new HashMap();
        JghdsjBo hdbo = new JghdsjBo();
        double cjjgrmb = getCjjg(tfxx);
        //Debug.out("确定房屋的成交价格 " + cjjgrmb + "元");

        //房屋交换部分的成交价格的确定
        double fwjh_cjjgrmb = getCjjg(dfTfxx);
        //Debug.out("确定交换的房屋的成交价格 " + fwjh_cjjgrmb + "元");

        double cqze = 0;
        double gyzfze = 0;
        double zcspze = 0;
        if (jmMap.get(Constants.JE_CQJMJE) != null) {
            cqze = ((BigDecimal) jmMap.get(Constants.JE_CQJMJE)).doubleValue();
        }
        if (jmMap.get(Constants.JE_QYZFBCDKE) != null) {
            gyzfze = ((BigDecimal) jmMap.get(Constants.JE_QYZFBCDKE)).
                     doubleValue();
        }
        if (jmMap.get(Constants.JE_ZCSPJMJE) != null) {
            zcspze = ((BigDecimal) jmMap.get(Constants.JE_ZCSPJMJE)).
                     doubleValue();
        }
        //拆迁减免金额=本次使用的总补偿额×税率
        cqze = cqze * sl;
        cqze = cqze + 0.0001;
        cqze = DataConvert.round(cqze, 2);
        //Debug.out("拆迁减免的总金额 " + cqze + "元");

        //计征税额=（成交价格—出售已购公有住房的本次抵扣额—房屋交换的成交价格）×税率
        double jzse = (cjjgrmb - gyzfze - fwjh_cjjgrmb) * sl;
        //添加四舍五入的补偿值，这个补偿值是由java的BigDecimal的特性决定的
        jzse = jzse + 0.0001;
        jzse = DataConvert.round(jzse, 2);
        if (jzse < 0) {
            jzse = 0;
        }
        //Debug.out("计征税额3 " + jzse + "元");

        //普通住宅减税金额：系统自动带出。
        double puzzjmje = getPtzzJm(sbzb, tfxx, jzse, cqze, cjjgrmb, puzfpjjg);

        //经济适用房减免税额,add by Ha Zhengze 20081218
        double jjsyfjmje = getJjsyfJm(sbzb, tfxx, jzse, cqze, cjjgrmb, puzfpjjg);

        //应纳税额=计征税额—拆迁减免金额—普通住宅减税金额—税务机关审核的减免税金额
        //double ynse = jzse - cqze - puzzjmje - zcspze;
        //应纳税额=计征税额—拆迁减免金额—普通住宅减税金额-经济适用房减免金额—税务机关审核的减免税金额,add by Ha Zhengze 20081218
        double ynse = jzse - cqze - puzzjmje-jjsyfjmje- zcspze;//经济适用房减免税额,add by Ha Zhengze 20081218
        ynse = DataConvert.round(ynse, 2);
        if (ynse < 0) {
            ynse = 0;
        }
        Debug.out("应纳税额 " + ynse + "元");

        //减免税总金额
        //double jmszje = cqze + zcspze + puzzjmje;
        double jmszje = cqze + zcspze + puzzjmje+jjsyfjmje;//经济适用房减免税额,add by Ha Zhengze 20081224
        jmszje = DataConvert.round(jmszje, 2);
        if (jmszje > jzse) {
            jmszje = jzse;
        }
        Debug.out("减免税总金额 " + jmszje + "元");

        //计税依据=成交价格-扣除出售已购公有住房收入 - 房屋交换的成交价格
        double jsyj = cjjgrmb - gyzfze - fwjh_cjjgrmb;
        jsyj = DataConvert.round(jsyj, 2);
        if (jsyj < 0) {
            jsyj = 0;
        }
        //Debug.out("计税依据 " + jsyj + "元");

        //计征契税=计税依据×税率
        double jzqs = jsyj * sl;
        jzqs = jzqs + 0.0001;
        jzqs = DataConvert.round(jzqs, 2);
        //Debug.out("计征契税 " + jzqs + "元");

        //实际应征契税=计征契税—各项核准减免契税（应等于应纳税额）
        //规则修改，Ha Zhengze 各项核准减免契税包括经济适用房减免金额
        hdbo.setCjjgrmb(new BigDecimal(cjjgrmb));
        hdbo.setFwjhCjjg(new BigDecimal(fwjh_cjjgrmb));
        hdbo.setCqjmje(new BigDecimal(cqze));
        hdbo.setGyzfjmje(new BigDecimal(gyzfze));
        hdbo.setJmzje(new BigDecimal(jmszje));
        hdbo.setJsyj(new BigDecimal(jsyj));
        hdbo.setJzqs(new BigDecimal(jzqs));
        hdbo.setJzse(new BigDecimal(jzse));
        hdbo.setPtzzjmje(new BigDecimal(puzzjmje));
        hdbo.setJjsyfjmje(new BigDecimal(jjsyfjmje));
        hdbo.setSl(new BigDecimal(sl));
        hdbo.setYnse(new BigDecimal(ynse));
        hdbo.setSjyz(new BigDecimal(ynse));

        return hdbo;
    }

    /**
     * 判断是否普通标准住宅
     * @param sbzb Sbzb 申报主表
     * @param tfxx Tufwxx 土地房屋信息
     * @param cjjgrmb double 成交价格人民币
     * @return boolean true-是,false-否;
     * @throws Exception 应用异常
     */
    public static boolean isPtbzzz(Sbzb sbzb, Tufwxx tfxx, double cjjgrmb) throws
            Exception {
        boolean rtnFlag = false;
        Timestamp sj=null; 
        //二手房
        if (Constants.TUFWXX_SFESF_TRUE.equals(tfxx.sfesf)) {
        	sj = tfxx.getHtqdsj(); //合同签订日期
        //新房	
        }else{
         sj=sbzb.getSbrq(); //申报日期
        }
        Timestamp NEW_JZRQ2011 = DateUtils.parseTimestamp("20111210"); //新政策基准分割时间
        
        if(!sj.before(NEW_JZRQ2011)){//在20111210之后(含20111210)
        	rtnFlag=isPtbzzzNew(sbzb, tfxx, cjjgrmb);
        }else{
        	rtnFlag=isPtbzzzOld(sbzb, tfxx, cjjgrmb);
        }
        
        return rtnFlag;
    }
    
    /**
     * 判断是否普通标准住宅(用新标准判断)
     * @param sbzb Sbzb 申报主表
     * @param tfxx Tufwxx 土地房屋信息
     * @param cjjgrmb double 成交价格人民币
     * @return boolean true-是,false-否;
     * @throws Exception 应用异常
     */
    public static boolean isPtbzzzNew(Sbzb sbzb, Tufwxx tfxx, double cjjgrmb) throws
            Exception {
        boolean rtnFlag = false;
        //变量参数
        Timestamp htqdsj = tfxx.getHtqdsj(); //合同签订日期
        BigDecimal fwjzmj = tfxx.getFwjzmj(); //房屋建筑面积
        double dbl_fwjzmj = fwjzmj.doubleValue(); //房屋建筑面积double值
        double cjjg = cjjgrmb; //成交价格
        double dwcjjg = Arith.div(cjjgrmb, dbl_fwjzmj, 2); //单位成交价格
        String fwszqydm = tfxx.getTdjc(); //房屋所在区域代码
        String rjl = tfxx.getRjl();
        System.out.println("==========判定是否普通标准住宅==========");
        System.out.println("htqdsj=" + htqdsj.toString() + "|"
                           + "dbl_fwjzmj=" + dbl_fwjzmj + "|"
                           + "cjjg=" + cjjg + "|"
                           + "dwcjjg=" + dwcjjg + "|"
                           + "fwszqydm=" + fwszqydm + "|"
                           + "rjl=" + rjl);

        //常量参数
        double OLD_CJJG_PER_UNIT = 9432.00; //原平均交易价格基准
        //Timestamp NEW_JZRQ = DateUtils.parseTimestamp("20050601"); //政策基准分割时间
        Timestamp NEW_JZRQ2011 = DateUtils.parseTimestamp("20111210"); //新政策基准分割时间
       // String fwszqydms[] = {"01", "02", "03", "04","11","12","13","14","15","16","17"}; //房屋所在区域代码
       // double fwszqyjgs[] = {2150000.00, 1750000.00, 1650000.00, 1000000.00,38880.00,34560.00,32400.00,28080.00,25920.00,21600.00,17280.00}; //房屋所在区域平均价格
       
        String fwszqydms[] = {"11","12","13","14","15","16","17"}; //房屋所在区域代码
        double fwszqyjgs[] = {38880.00,34560.00,32400.00,28080.00,25920.00,21600.00,17280.00}; //房屋所在区域平均价格
 
        
        //开始标准判定
        double fwszqyjg=0.00;
        if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs) &&
            Constants.TDFWLB.equals(tfxx.fwlxdm)) { //如果为个人且土地房屋类型为其他住宅则继续判定普通标准住宅
            if ("00".equals(fwszqydm)) {
                throw new Exception("没有选择房屋所在区域,无法判定！");
            }
            
            
            Timestamp sj=null; 
            //二手房
            if (Constants.TUFWXX_SFESF_TRUE.equals(tfxx.sfesf)) {
            	sj = tfxx.getHtqdsj(); //合同签订日期
            //新房	
            }else{
             sj=sbzb.getSbrq(); //申报日期
            }
            
           // if(!sj.before(NEW_JZRQ2011)){//如果日期20111210后(含20111210)
            	
            	///获取区域指每平米价格上线
                for (int i = 0; i < fwszqydms.length; i++) {
                    if (fwszqydms[i].equals(fwszqydm)) {
                        fwszqyjg = fwszqyjgs[i];
                        break;
                    }
                }
                //判定区域平均价格是否存在
                if (fwszqyjg == 0.00) {
                    throw new Exception("房屋所在区域代码错误！" + fwszqydm);
                }
                //
                if ("01".equals(rjl) && dbl_fwjzmj <= 140 && dwcjjg <= fwszqyjg) { //符合条件则为普通标准住宅
                    rtnFlag = true;
                } else {
                    rtnFlag = false;
                }
            	
//            }else if (sj.after(NEW_JZRQ)&&sj.before(NEW_JZRQ2011)) { //如果日期在20050601后在20111210前
//                ///获取区域指导价格的1.2倍
//                for (int i = 0; i < fwszqydms.length; i++) {
//                    if (fwszqydms[i].equals(fwszqydm)) {
//                        fwszqyjg = fwszqyjgs[i] * 1.2;
//                        break;
//                    }
//                }
//                //判定区域平均价格是否存在
//                if (fwszqyjg == 0.00) {
//                    throw new Exception("房屋所在区域代码错误！" + fwszqydm);
//                }
//                //
//                if ("01".equals(rjl) && dbl_fwjzmj <= 140 && cjjg <= fwszqyjg) { //符合条件则为普通标准住宅
//                    rtnFlag = true;
//                } else {
//                    rtnFlag = false;
//                }
//
//            } else { //如果合同签订日期在20050601（含）前
//                if (dwcjjg <= OLD_CJJG_PER_UNIT) { //如果单位价格小于等于平均价格
//                    rtnFlag = true;
//                } else {
//                    rtnFlag = false;
//                }
//            }
            
            
            
            
        } else {
            rtnFlag = false;
        }
        System.out.println("==>标准住房判定条件结果："+rtnFlag);
        //99.返回值
        return rtnFlag;
    } 
    
    /**
     * 判断是否普通标准住宅(用旧标准判断)
     * @param sbzb Sbzb 申报主表
     * @param tfxx Tufwxx 土地房屋信息
     * @param cjjgrmb double 成交价格人民币
     * @return boolean true-是,false-否;
     * @throws Exception 应用异常
     */
    public static boolean isPtbzzzOld(Sbzb sbzb, Tufwxx tfxx, double cjjgrmb) throws
            Exception {
        boolean rtnFlag = false;
        //变量参数
        Timestamp htqdsj = tfxx.getHtqdsj(); //合同签订日期
        BigDecimal fwjzmj = tfxx.getFwjzmj(); //房屋建筑面积
        double dbl_fwjzmj = fwjzmj.doubleValue(); //房屋建筑面积double值
        double cjjg = cjjgrmb; //成交价格
        double dwcjjg = Arith.div(cjjgrmb, dbl_fwjzmj, 2); //单位成交价格
        String fwszqydm = tfxx.getTdjc(); //房屋所在区域代码
        String rjl = tfxx.getRjl();
        System.out.println("==========判定是否普通标准住宅==========");
        System.out.println("htqdsj=" + htqdsj.toString() + "|"
                           + "dbl_fwjzmj=" + dbl_fwjzmj + "|"
                           + "cjjg=" + cjjg + "|"
                           + "dwcjjg=" + dwcjjg + "|"
                           + "fwszqydm=" + fwszqydm + "|"
                           + "rjl=" + rjl);
        //常量参数
        double OLD_CJJG_PER_UNIT = 9432.00; //原平均交易价格基准
        Timestamp NEW_JZRQ = DateUtils.parseTimestamp("20050601"); //政策基准分割时间
        String fwszqydms[] = {"01", "02", "03", "04"}; //房屋所在区域代码
        double fwszqyjgs[] = {2150000.00, 1750000.00, 1650000.00, 1000000.00}; //房屋所在区域平均价格
        //开始标准判定
        double fwszqyjg=0.00;
        if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs) &&
            Constants.TDFWLB.equals(tfxx.fwlxdm)) { //如果为个人且土地房屋类型为其他住宅则继续判定普通标准住宅
            if ("00".equals(fwszqydm)) {
                throw new Exception("没有选择房屋所在区域,无法判定！");
            }
            if (htqdsj.after(NEW_JZRQ)) { //如果合同签订日期在20050601后
                ///获取区域指导价格的1.2倍
                for (int i = 0; i < fwszqydms.length; i++) {
                    if (fwszqydms[i].equals(fwszqydm)) {
                        fwszqyjg = fwszqyjgs[i] * 1.2;
                        break;
                    }
                }
                //判定区域平均价格是否存在
                if (fwszqyjg == 0.00) {
                    throw new Exception("房屋所在区域代码错误！" + fwszqydm);
                }
                //
                if ("01".equals(rjl) && dbl_fwjzmj <= 140 && cjjg <= fwszqyjg) { //符合条件则为普通标准住宅
                    rtnFlag = true;
                } else {
                    rtnFlag = false;
                }

            } else { //如果合同签订日期在20050601（含）前
                if (dwcjjg <= OLD_CJJG_PER_UNIT) { //如果单位价格小于等于平均价格
                    rtnFlag = true;
                } else {
                    rtnFlag = false;
                }
            }
        } else {
            rtnFlag = false;
        }
        System.out.println("==>标准住房判定条件结果："+rtnFlag);
        //99.返回值
        return rtnFlag;
    }




    /**
     * 计算经济适用房减免金额
     * @param sbzb Sbzb 申报主表对象
     * @param tfxx Tufwxx 土地房屋信息
     * @param jzse double 计征税额
     * @param cqze double 拆迁总额
     * @param cjjgrmb double 成交价格人民币
     * @param puzfpjjg double 普通住房平均价格，这个是指老的9432平均价格，以后不用了 20081224
     * @return double 普通住房减免金额
     * @throws Exception 异常
     */
    private static double getJjsyfJm(Sbzb sbzb, Tufwxx tfxx, double jzse,
                                     double cqze, double cjjgrmb,
                                     double puzfpjjg) throws Exception {
        double qszymj = 0;
        if (tfxx.fwjzmj != null) {
            qszymj = tfxx.fwjzmj.doubleValue();
        } else {
            throw new ApplicationException("计算房屋的各种税额的时候，权属转移面积不能为零！");
        }
        //Debug.out("确定的房屋的权属转移面积 " + qszymj + "m2");

        //注：（1）普通住宅减税金额=（计征税额—拆迁减免金额）×50%
        //（2）普通住宅判断条件：成交价格÷房屋建筑面积≤上年度商品房住宅平均售价
        //只有个人用户享受普通住宅的减免政策
        double jjsyfjmje = 0;
        double jbzs = 0.5;
        //
        if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs)
            && Constants.TDFWLB.equals(tfxx.fwlxdm)) { //必须是个人且为其它住宅
            if (Constants.SETZ_JJSYF.equals(sbzb.setz)) { //经济适用房
                //合同（契约）签订时间在2007年8月1日之前的，全额征收，没有减免金额。
                //没有享受过经济适用住房减半的房屋可继续享受普通标准住宅减半征收的优惠政策。
                if (!tfxx.getHtqdsj().before(DateUtils.parseTimestamp(
                        "20070801"))) {
                    //
                    jjsyfjmje = (jzse - cqze) * jbzs;
                    //
                    jjsyfjmje = DataConvert.round(jjsyfjmje, 2);
                    if (jjsyfjmje < 0) {
                        jjsyfjmje = 0;
                    }
                }
            }
        }
        Debug.out("经济适用房减税金额 " + jjsyfjmje + "元");
        return jjsyfjmje;
    }
    /**
     * 计算普通住宅减免金额
     * @param sbzb Sbzb 申报主表对象
     * @param tfxx Tufwxx 土地房屋信息
     * @param jzse double 计征税额
     * @param cqze double 拆迁总额
     * @param cjjgrmb double 成交价格人民币
     * @param puzfpjjg double 普通住房平均价格，这个是指老的9432平均价格，以后不用了 20081224
     * @return double 普通住房减免金额
     * @throws Exception 异常
     * 修改说明:[20081224 Ha Zhengze] 增加了经济适用房在20070801前交易的情况如果符合普通标准住宅则享受普通标准住宅减免
     * ,其它原则与原来保持一致.
     *
     */
    private static double getPtzzJm(Sbzb sbzb, Tufwxx tfxx, double jzse,
                                    double cqze, double cjjgrmb,
                                    double puzfpjjg) throws Exception {
        double qszymj = 0;
        if (tfxx.fwjzmj != null) {
            qszymj = tfxx.fwjzmj.doubleValue();
        } else {
            throw new ApplicationException("计算房屋的各种税额的时候，权属转移面积不能为零！");
        }
        //Debug.out("确定的房屋的权属转移面积 " + qszymj + "m2");

        //注：（1）普通住宅减税金额=（计征税额—拆迁减免金额）×50%
        //（2）普通住宅判断条件：成交价格÷房屋建筑面积≤上年度商品房住宅平均售价
        //只有个人用户享受普通住宅的减免政策
        double puzzjmje = 0;
        double jbzs = 0.5;

        //在契税申报时的土地房屋基础信息中，对于二手房的申报信息，无论合同签订日期是多少，
        //统一根据税额调整的选择项结果进行计算。对于新房的申报信息，合同签订日期在05年6月1日之前的适用原计算标准，
        //6月1日之后的根据税额调整的选择项结果进行计算。
        if (Constants.TUFWXX_SFESF_TRUE.equals(tfxx.sfesf)) {
            if (Constants.SETZ_JBZS.equals(sbzb.setz)) { //减半征收
                puzzjmje = (jzse - cqze) * jbzs;
            } else if (Constants.SETZ_JJSYF.equals(sbzb.setz)) { //经济适用房,add by Hazhengze 20081224
                if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs)
                    && Constants.TDFWLB.equals(tfxx.fwlxdm)) {//必须是个人且为其它住宅
                    //合同（契约）签订时间在2007年8月1日之前的，全额征收，没有减免金额。
                    //没有享受过经济适用住房减半的房屋可继续享受普通标准住宅减半征收的优惠政策。
                    if (tfxx.getHtqdsj().before(DateUtils.parseTimestamp(
                            "20070801"))) {
                        if (CommonUtil.isPtbzzz(sbzb, tfxx, cjjgrmb)) {
                            puzzjmje = (jzse - cqze) * jbzs;
                        }
                    }
                }
            }
            //非负操作
            puzzjmje = DataConvert.round(puzzjmje, 2);
            if (puzzjmje < 0) {
                puzzjmje = 0;
            }
        }
        //如果申报的房产不是二手房
        else {
            if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs) &&
                Constants.TDFWLB.equals(tfxx.fwlxdm)) { //必须是个人且为其它住宅
                if (Constants.TDFWQSZY_MM.equals(tfxx.tdfwqszylx) ||
                    Constants.TDFWQSZY_JH.equals(tfxx.tdfwqszylx)) {
                    //added by zhaobo 因为税额调整的原因修改本段程序 start 20050706
                    if (Constants.SETZ_JBZS.equals(sbzb.setz) ||
                        (Constants.SETZ_ZC.equals(sbzb.setz) &&
                         CommonUtil.isPtbzzz(sbzb, tfxx, cjjgrmb))) //modify by hazhengze 20081224,调整普通标准住宅判定标准
                    //|| (Constants.SETZ_ZC.equals(sbzb.setz) && (cjjgrmb / qszymj) <= puzfpjjg))
                    {
                        puzzjmje = (jzse - cqze) * jbzs;
                    }
                    //added by fujx,hazhengze 20081224,税额调整增加经济适用房
                    if (Constants.SETZ_JJSYF.equals(sbzb.setz))
                    {
                        //合同（契约）签订时间在2007年8月1日之前的，全额征收，没有减免金额。
                        //没有享受过经济适用住房减半的房屋可继续享受普通标准住宅减半征收的优惠政策。
                        if (tfxx.getHtqdsj().before(DateUtils.parseTimestamp(
                                "20070801"))) {
                            if (CommonUtil.isPtbzzz(sbzb, tfxx, cjjgrmb)) {
                                puzzjmje = (jzse - cqze) * jbzs;
                            }
                        }
                    }
                    //
                    puzzjmje = DataConvert.round(puzzjmje, 2);
                    if (puzzjmje < 0) {
                        puzzjmje = 0;
                    }
                }
            }
        }
        Debug.out("普通住宅减税金额 " + puzzjmje + "元");
        return puzzjmje;
    }


    /**
     * 计算审核及收款时候需要用到的计征税额
     * Constants 中的定义：
      public static final String JE_CJJG = "JE_CJJG";   //成交价格
      public static final String JE_JSYJ = "JE_JSYJ";   //计税依据
      public static final String JE_JZQS = "JE_JZQS";   //计征契税
      public static final String JE_SJYZ = "JE_SJYZ";   //实际应征
      public static final String JE_JZSE = "JE_JZSE";   //计征税额
      public static final String JE_QYZFBCDKE = "JE_QYZFBCDKE";//出售已购公有住房的本次抵扣额
      public static final String JE_FWJHJG = "JE_FWJHJG";  //房屋交换的对方的成交价格
      public static final String JE_CQJMJE = "JE_CQJMJE";  //拆迁减免金额
      public static final String JE_PTZZJMJE = "JE_PTZZJMJE";//普通住宅减税金额
      public static final String JE_JMSZE = "JE_JMSZE";//减免税总金额
      public static final String JE_YNSE = "JE_YNSE";    //应纳税额
     * @param String 申报表号
     * @return HashMap
     */
    public static JghdsjBo getJZSE(String sbbh, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //根据申报表号，获得申报主表数据
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //获得房屋交换对方申报表号
            Tufwxx dfTfxx = new Tufwxx();
            if (sbzb.dfsbbh != null && sbzb.dfsbbh != "") {
                dfTfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                         getBySbbh(sbzb.dfsbbh, conn);
            }

            //根据申报表号获得，土地房屋的基本信息数据
            //从而得到成交价格、房屋面积，计算得到是否符合普通住宅的减半征收的政策
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(sbbh, conn);
            }
            //获得普通住宅减免的政策数据
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //上年度商品房住宅平均售价

            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));
            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);

        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("获取契税金额的时候，报错！");
        }
        return hdbo;
    }

    /**
     * 根据权属转移类型查询税费权属代码表，得到税种税目
     * @param qszydm 权属转移代码
     * @param conn  数据库联接
     * @return Szsm
     */
    public static Szsm getSZSMDM(String qszydm, Connection con) throws
            BaseException {
        String szsmdm = "";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Szsm szsm = new Szsm();
        try {
            String sql =
                    "select szsmdm from DMDB.SF_DM_QSZYXS where QSZYXSDM = '" +
                    qszydm + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                szsmdm = rs.getString("szsmdm");
            }

            szsm.setSzsmdm(szsmdm);
            szsm = (Szsm) DAOFactory.getInstance().getSzsmDAO().get(szsm, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                pst.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
        return szsm;
    }

    /**
     * 根据权属转移类型查询税费权属代码表，得到税种税目
     * @param qszydm 权属转移代码
     * @param conn  数据库联接
     * @return Szsm
     */
    public static Szsm getSZSM(String szsmdm, Connection con) throws
            BaseException {
        Szsm szsm = new Szsm();
        try {
            szsm.setSzsmdm(szsmdm);
            szsm = (Szsm) DAOFactory.getInstance().getSzsmDAO().get(szsm, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return szsm;
    }

    /**
     * 根据政策代码获得政策的值
     * @param zcdm String
     * @param sbrq String 申报日期
     * @param conn Connection 数据库联接
     * @return double
     */
    public static String getZcsj(String zcdm, Connection conn) throws
            ApplicationException {
        String value = "";
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select zbz from QSDB.QS_JL_ZCWH where ZBDM = '" + zcdm +
                     "'";

        //将政策的有效时间作为条件加入
//      if(sbrq != null && sbrq != "")
//      {
//          sql = sql + " and SXQSRQ <= to_date('','') and SXJZRQ>=to_date('','')";
//       }
//
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                value = rs.getString("zbz");
            }
        } catch (Exception ex) {
            throw new ApplicationException("取得政策的值时候出错！");
        } finally {
            try {
                pst.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
        return value;
    }

    /**
     * 确定成交价格
     * @return double
     */
    public static double getCjjg(Tufwxx tfxx) {
        //成交价格的确定
        //①如果成交价格低于评估价格，以评估价格为成交价格
        //②如果既有人民币价格，又有外币价格，则成交价格=成交价格（评估价格）+外币折合的人民币价格
        double cjjgrmb = 0;
        double pgjgrmb = 0;
        double cjjgwb = 0;
        double zhjgrmb = 0;

        if (tfxx.cjjgrmb != null) {
            cjjgrmb = tfxx.cjjgrmb.doubleValue();
        }
        if (tfxx.pgjgrmb != null) {
            pgjgrmb = tfxx.pgjgrmb.doubleValue();
        }
        if (tfxx.cjjgwb != null) {
            cjjgwb = tfxx.cjjgwb.doubleValue();
        }
        if (tfxx.zhjgrmb != null) {
            zhjgrmb = tfxx.zhjgrmb.doubleValue();
        }
        //按照业务判断确定成交价格
        //如果成交价格比评估价格低,同时又没有折合的人民币价格
        if (pgjgrmb > cjjgrmb && zhjgrmb == 0) {
            cjjgrmb = pgjgrmb;
        }

        //如果既有人民币价格,又有折合人民币的价格
        if (zhjgrmb > 0) {
            if ((cjjgrmb + zhjgrmb) > pgjgrmb) {
                cjjgrmb = cjjgrmb + zhjgrmb;
            } else { //如果比评估价格小
                cjjgrmb = pgjgrmb;
            }
        }
        return cjjgrmb;
    }

    /**
     * 获取审批结果的信息
     * Map :
     * zjlxdm   证件类型代码
     * zjhm   证件号码
     * gjdm   国籍代码
     * qsjmlbdm  契税减免类别代码
     * qtjmly
     * qszyxsdm  契税权属转移性质代码
     * qsjmlxdm 契税减免类型代码
     * jmse  减免税金额
     * cstdfwzl
     *
     * @param spjgbh String
     * @return HashMap
     */
    public static HashMap getZcspjg(String spjgbh) {
        //Debug.out("into commonutil getZcspjg...");
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfS = new com.ttsoft.bjtax.
                sfgl.proxy.ServiceProxy();
        //Debug.out("aaa service sfs: " + sfS);
        HashMap map = null;
        try {
            Map map_tmp = sfS.getQsjmspjg(spjgbh);
            if (map_tmp != null && map_tmp.size() > 0) {
                map = new HashMap(map_tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new ApplicationException("获取税费中有关契税审批结果信息的时候出错！");
        }
        return map;
    }

    /**
     * 插入有减免税情况的申报结果的数据
     * @param jsjdm String
     * @param szsmdm String
     * @param jsje BigDecimal
     * @param jmse BigDecimal
     * @param lrr String
     * @param jmxmdm String
     * @param cjrq Timestamp
     * @param skssjsrq String
     * @param skssksrq String
     * @return boolean
     * @throws BaseException
     */
    public static boolean insertSBJM(HashMap map) throws BaseException {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfS = new com.ttsoft.bjtax.
                sfgl.proxy.ServiceProxy();
        try {
            String jsjdm = (String) map.get("jsjdm");
            String szsmdm = (String) map.get("szsmdm");
            BigDecimal jsje = (BigDecimal) map.get("jsje");
            BigDecimal jmse = (BigDecimal) map.get("jmse");
            String lrr = (String) map.get("lrr");
            String jmxmdm = (String) map.get("jmxmdm");
            String jmxzdm = (String) map.get("jmxzdm");
            String sjly = Constants.JKS_SJLY_FHZ;
           
            Timestamp cjrq = (Timestamp) map.get("cjrq");
            String skssjsrq = DataConvert.TimeStamp2String((Timestamp) map.get(
                    "skssjsrq"));
            String skssksrq = DataConvert.TimeStamp2String((Timestamp) map.get(
                    "skssksrq"));
            
            if(jmxzdm==null) jmxzdm = "";
            
            //调整接口传入参数，增加减免性质代码
            return sfS.insertSBJM(jsjdm, szsmdm, jsje, jmse, lrr, jmxmdm, cjrq,
                                  skssjsrq, skssksrq,jmxzdm,sjly);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    /**
     * 当减免税的数据被撤销的时候，需要同时撤销税费管理中有关此减免的申报数据
     * 以保证减免税记帐的正确
     *
     * @param jsjdm String
     * @param szsmdm String
     * @param cjrq Timestamp
     * @return boolean
     * @throws BaseException
     */
    public static boolean deleteSBJM(HashMap map) throws BaseException {
        String jsjdm = (String) map.get("jsjdm");
        String szsmdm = (String) map.get("szsmdm");
        Timestamp cjrq = (Timestamp) map.get("cjrq");
        String sjly = Constants.JKS_SJLY_FHZ;
        try {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfS = new com.ttsoft.bjtax.
                    sfgl.proxy.ServiceProxy();
            return sfS.deleteSBJM(jsjdm, szsmdm, cjrq,sjly);
        } catch (Exception ex) {
            throw new ApplicationException("删除税费中的申报数据的时候出错！");
        }
    }

    /**
     * 通过UserData中的xtsbm1获得票证帐户的完整数据
     * @param ud UserData
     * @param conn Connection
     * @return Zh
     */
    public static Zh getPzzhVo(UserData ud, Connection conn) throws
            ApplicationException {
        Zh zh = new Zh();
        zh.setZhdm(ud.getXtsbm1());
        try {
            zh = (Zh) DAOFactory.getInstance().getZhDAO().get(zh, conn);

        } catch (Exception ex) {
            throw new ApplicationException("取得票证帐户的时候出错！");
        }
        return zh;
    }

    /**
     * 帐户的类型标识
     * 1：税务机关
     * 2：代征点
     * 3：征收点
     * 4：税务机关征收人员
     * 5：征收点征收人员
     * 通过UserData中的xtsbm1获得征收点的票证帐户的完整数据
     * @param ud UserData
     * @param conn Connection
     * @return Zh
     */
    public static Zh getZsdPzzhVo(UserData ud, Connection conn) throws
            ApplicationException {
        Zh zh = new Zh();
        String zhdm = ud.getXtsbm1();

        //先找到征收点的征收人员的全部数据
        //通过账户的类型标识判断是否为征收点的征收人员，如果是，就返回征收点信息
        //如果不是就返回null;
        zh.setZhdm(zhdm);
        try {
            zh = (Zh) DAOFactory.getInstance().getZhDAO().get(zh, conn);
            Zh zsdZh = new Zh();
            if (zh.lxbs != null && zh.lxbs.equals("5")) {
                //将上级的帐户代码赋给征收点的vo
                zsdZh.setZhdm(zh.getSjzhdm());
                zsdZh = (Zh) DAOFactory.getInstance().getZhDAO().get(zsdZh,
                        conn);
            }

        } catch (Exception ex) {
            throw new ApplicationException("取得票证帐户的时候出错！");
        }
        return zh;
    }

    /**
     * 取得Timestamp 型的契税限缴日期
     * @param htqdrq Timestamp
     * @param ts int 限缴天数
     * @return Timestamp
     */
    public static Timestamp getXjrq(Timestamp htqdrq, int ts) {
        Calendar ca = Calendar.getInstance();

        ca.setTime(htqdrq);
        ca.add(Calendar.DATE, ts);
        Debug.out("限缴日期为：" + ca.toString());
        Date d = ca.getTime();
        return new Timestamp(d.getTime());
    }

    public static void main(String[] arg) {
        HashMap map = CommonUtil.getZcspjg("京地税海减免地字[2004]040001");
        Set keys = map.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            System.out.println("key: " + key);
            Object value = (Object) map.get(key);
            if (value != null) {
                System.out.println("value type: " + value.getClass().getName());
                System.out.println("value: " + value.toString());
            } else {
                System.out.println("value is null.");
            }
        }
    }


    /**
     * 申报表号（个人、非个人），同时包括不征的表号：
     * "1位字母" + ZHDM(8位,剪除01市局标示) + 时间(040101) + 流水号(4位)
     * 申报表号的1位字母为"S"
     * 1234567890123456789
     * @param zhdm String
     * @param conn Connection
     * @param lx String
     * @return String
     * @throws ApplicationException
     */
    public static String getPlh(String zhdm, Connection conn, String lx) throws
            ApplicationException {
        if (zhdm == null) {
            throw new ApplicationException("传入的参数“帐户代码”不能为空！");
        }

        if (zhdm.length() != 10) {
            throw new ApplicationException("传入的参数“帐户代码”位数异常");
        }

        //ZHDM(8位,剪除01市局标示)
        String Plh = lx + zhdm.substring(2);
        Timestamp now = null;
        Date newDate = null;
        try {
            now = getDBtime(conn);
            //newDate = new Date(now.getTime());

        } catch (Exception ex) {
            throw new ApplicationException("获取数据库时间失败");
        }

        Plh = Plh + getDatetime(now, "yyMMddhhmmss");
        return Plh;
    }


    /**
     * 获得申报减免数据的结果集 SB_JL_JM
     * @param conn
     * @param map
     * @param jsjdm String
     * @param szsmdm String
     * @param cjrq Timestamp
     * @return List
     * @throws ApplicationException
     */
    public static ArrayList getJM(Connection conn, HashMap map) throws
            ApplicationException {
        String jsjdm = (String) map.get("jsjdm");
        String szsmdm = (String) map.get("szsmdm");
        Timestamp cjrq = (Timestamp) map.get("cjrq");
        String jmlx = "1"; //写死 审批性减免

        PreparedStatement pre_stmt = null;
        String sql =
                " SELECT JSJDM,JMLX,SZSMDM,SBRQ,LRRQ,JSJE,KSSL,JMSE,SWJGZZJGDM,LRR,FSDM,JZBZ, " +
                " JMXMDM,DJZCLXDM,GJBZHYDM,YSKMDM,YSJCDM,SKSSJSRQ,SKSSKSRQ,ND,CJRQ,QXDM " +
                " from SBDB.SB_JL_JM where jsjdm =? and JMLX=? and SZSMDM=? and CJRQ=? ";
        ResultSet rs = null;

        ArrayList l = new ArrayList();

        try {
            pre_stmt = conn.prepareStatement(sql);
            pre_stmt.setString(1, jsjdm);
            pre_stmt.setString(2, jmlx);
            pre_stmt.setString(3, szsmdm);
            pre_stmt.setTimestamp(4, cjrq);
            rs = pre_stmt.executeQuery();
            while (rs.next()) {
                Jm jm = new Jm();
                jm.setJsjdm(rs.getString("JSJDM"));
                jm.setJmlx(rs.getString("JMLX"));
                jm.setSzsmdm(rs.getString("SZSMDM"));
                jm.setSbrq(rs.getTimestamp("SBRQ"));
                jm.setLrrq(rs.getTimestamp("LRRQ"));
                jm.setJsje(rs.getBigDecimal("JSJE"));
                jm.setKssl(rs.getBigDecimal("KSSL"));
                jm.setJmse(rs.getBigDecimal("JMSE"));
                jm.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                jm.setLrr(rs.getString("LRR"));
                jm.setFsdm(rs.getString("FSDM"));
                jm.setJzbz(rs.getString("JZBZ"));
                jm.setJmxmdm(rs.getString("JMXMDM"));
                jm.setDjzclxdm(rs.getString("DJZCLXDM"));
                jm.setGjbzhydm(rs.getString("GJBZHYDM"));
                jm.setYskmdm(rs.getString("YSKMDM"));
                jm.setYsjcdm(rs.getString("YSJCDM"));
                jm.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                jm.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                jm.setNd(rs.getString("ND"));
                jm.setCjrq(rs.getTimestamp("CJRQ"));
                jm.setQxdm(rs.getString("QXDM"));

                l.add(jm);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getJM() 方法中数据库错误！", e);
        }
        return l;
    }

}
