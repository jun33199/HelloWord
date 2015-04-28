/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.thtflog.LogBeanClient;
import com.ttsoft.thtflog.LogData;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 一些小的帮助方法的总和</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class TinyTools {
	
	public static void main(String[] args){
		TinyTools.stringToDate("20061101","yyyyMMdd");
		System.out.println(TinyTools.stringToDate("20061101","yyyyMMdd").getDay());
		
		boolean zj = TinyTools.isCompany("725904N");
		System.out.println("zj==>>" + zj);
	}
  /**
   * 将Calendar转换为一个Timestamp型日期（只精确到天）
   * @param gCalendar 时间
   * @return Timestamp型日期
   */
  public static Timestamp calendar2Timestamp(Calendar gCalendar) {
    gCalendar.set(gCalendar.HOUR_OF_DAY, 0);
    gCalendar.set(gCalendar.MINUTE, 0);
    gCalendar.set(gCalendar.SECOND, 0);
    gCalendar.set(gCalendar.MILLISECOND, 0);
    Timestamp t = new Timestamp(gCalendar.getTime().getTime());
    return t;
  }

  /**
   * 将时间类型转换为格式字符串
   * @param date 日期
   * @param format String
   * @return 格式化后的字符串
   */
  public static String Date2String(java.util.Date date, String format) {
    if (date == null) {
      return null;
    }

    // 格式化Class
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);

    return sdf.format(date);
  }

  /**
   * 将时间类型转换为 "yyyy-mm-dd" 格式的字符串
   * @param date 日期
   * @return 格式化后的字符串
   */
  public static String Date2String(java.util.Date date) {
    return TinyTools.Date2String(date, "yyyy-MM-dd");
  }

  /**
   * 按照指定的格式分析字符时间并转换为日期格式时间
   * @param stringDate 字符时间
   * @param pattern 时间格式
   * @return 转换后的日期
   */
  public static Date stringToDate(String stringDate, String pattern) {

    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      return simpleDateFormat.parse(stringDate);
    }
    catch (Exception ex) {
      //这里把异常转换为空值返回,调用方必须做一个检查
      return null;
    }
  }

  /**
   * 按照默认的格式(yyyy-MM-dd)分析字符时间并转换为日期格式时间
   * @param stringDate 字符串时间
   * @return 转换后的日期格式时间
   */
  public static Date stringToDate(String stringDate) {
    return stringToDate(stringDate, "yyyy-MM-dd");
  }

  /**
   * 得到给定日期的季度 为int型
   * @param date 给定的日期
   * @return int 季度值(1,2,3,4)
   */
  public static int getQuarter(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.MONTH) / 3 + 1);
  }

  /**
   * 得到给定日期的年份 为int型
   * @param date 给定的日期
   * @return int 年份值
   */
  public static int getYear(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.YEAR));
  }

  /**
   * 得到给定日期的月份 为int型
   * @param date 给定的日期
   * @return int 月份值(0开始)
   */
  public static int getMonth(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.MONTH));
  }

  /**
   * 得到给定日期的本月的几号 为int型
   * @param date 给定的日期
   * @return int 天号(1开始)
   */
  public static int getDay(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.DATE));
  }

  //保留上次的时间
  private static long oldtime;

  /**
   * 性能调试工具
   * @param message 调试信息
   */
  public static void log(String message) {
    long now = System.currentTimeMillis();
    long delta = now - oldtime;
    oldtime = now;
    //Debug.out("Pass:" + delta + "\t" + message);
  }

  /**
   * 获得前年的这天日期
   * @param year 指定年
   * @param date 给定的日期
   * @return 前年的这天日期
   */
  public static Date addYear(int year, Date date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //修改年
    calendar.add(calendar.YEAR, year);
    //得到修改后的日期
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * 设定指定域值
   * @param date 给定的日期
   * @param field 指定域
   * @param num 指定域值
   * @return Date
   */
  public static Date setByField(Date date, int field, int num) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //修改年
    calendar.set(field, num);
    //得到修改后的日期
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * 加减当前天
   * @param day 指定天
   * @param date 给定的日期
   * @return 上一月份的日期
   */
  public static Date addDay(int day, Date date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //修改年
    calendar.add(calendar.DATE, day);
    //得到修改后的日期
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * 加减当前月
   * @param month 指定月
   * @param date 给定的日期
   * @return 上一月份的日期
   */
  public static Date addMonth(int month, Date date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //修改年
    calendar.add(calendar.MONTH, month);
    //得到修改后的日期
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * 得到给定日期的添加秒的日期
   * @param date 给定的日期2003-11-20
   * @return date 2003-11-20 16:03:01.945
   */
  public static Date getDayTime(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    Calendar calendar2 = new GregorianCalendar();
    //设置时分秒
    calendar.set(Calendar.HOUR, calendar2.get(Calendar.HOUR));
    calendar.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
    calendar.set(Calendar.SECOND, calendar2.get(Calendar.SECOND));
    return (calendar.getTime());
  }

  public static List splitList(List sourcelist, int max) {
    List splitList = new ArrayList();
    int eachpageIndex = 1;
    List eachPageList = new ArrayList();
    for (int i = 0; i < sourcelist.size(); i++) {
      if (eachpageIndex == max) {
        eachPageList.add(sourcelist.get(i));
        splitList.add(eachPageList);
        eachPageList = new ArrayList();
        eachpageIndex = 1;
      }
      else {
        eachPageList.add(sourcelist.get(i));
        eachpageIndex++;
      }
    }
    if (eachpageIndex != 1) {
      splitList.add(eachPageList);
    }
    return splitList;
  }

  /**
   * 得到时间序号
   * @param swjgzzjgdm 税务机关组织结构代码
   * @return String 序号
   */
  public static String getXh(String swjgzzjgdm) {
    return swjgzzjgdm
        + TinyTools.Date2String(new Date(), "yyyyMMddHHmmssSSS");
  }

  /**
   *记录日志
   * 20040429 Shi Yanfeng
   * @param ld 日志数据
   */
  public static void makeLog(LogData ld) {
    //通过LogBeanClient向消息Bean发送消息，记录日志
//    Debug.outVO(ld);
    ld.setRzjbdm(LogData.LEVEL_3);
    LogBeanClient.thtflog(ld);
  }

  /**
   * 申报征收>个体工商户>撤销完税证 记录日志
   * 20040429 Shi Yanfeng
   * @param ud 操作员信息
   * @param wszh 完税证号
   * @param jsjdm 计算机代码
   * @param wszxh 完税证序号
   */
  public static void makeLog4Gtgsh(UserData ud, String wszh, String jsjdm,
                                   String wszxh) {
    //构造LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("申报征收>个体工商户>撤销完税证");
    logData.setCzlx("撤销完税证");
    logData.setCzms("纳税人计算机代码：" + jsjdm + " 完税证号：" + wszh + " 完税证序号："
                    + wszxh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * 申报征收>个体工商户>撤销完税证 记录日志
   * 20040429 Shi Yanfeng
   * @param ud 操作员信息
   * @param sbhzdh 申报汇总但号
   */
  public static void makeLog4GtgshZfjks(UserData ud, String sbhzdh) {
    //构造LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("申报征收>个体工商户>作废完税证汇总缴款书");
    logData.setCzlx("作废完税证汇总缴款书");
    logData.setCzms(" 申报汇总单号：" + sbhzdh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * 撤销印花税购买情况汇总 记录日志
   * 20040429 Shi Yanfeng
   * @param ud 操作员信息
   * @param jkpzh 缴款凭证号
   */
  public static void makeLog4YhsZfjks(UserData ud, String jkpzh) {
    //构造LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("撤销印花税购买情况汇总");
    logData.setCzlx("撤销印花税购买情况汇总");
    logData.setCzms("缴款凭证号：" + jkpzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * 撤销印花税购买情况汇总 记录日志
   * 20040429 Shi Yanfeng
   * @param ud 操作员信息
   * @param jkpzh 缴款凭证号
   */
  public static void makeLog4DsYhsZfjks(UserData ud, String jkpzh) {
    //构造LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("撤销印花税代售单位销售汇总");
    logData.setCzlx("撤销印花税代售单位销售汇总");
    logData.setCzms("缴款凭证号：" + jkpzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * 撤销印花税购买情况汇总 记录日志
   * 20040429 Shi Yanfeng
   * @param ud 操作员信息
   * @param xspzh 日志数据
   */
  public static void makeLog4YhsCxgm(UserData ud, String xspzh) {
    //构造LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("录入印花税购买情况作废");
    logData.setCzlx("录入印花税购买情况作废");
    logData.setCzms("销售凭证号：" + xspzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * 撤销印花税购买情况汇总 记录日志
   * 20040429 Shi Yanfeng
   * @param ud 操作员信息
   * @param jkpzh 日志数据
   */
  public static void makeLog4ZhsbCx(UserData ud, String jkpzh) {
    //构造LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("申报缴款");
    logData.setCzlx("撤销缴款书");
    logData.setCzms("缴款凭证号：" + jkpzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * 撤销印花税购买情况汇总 记录日志
   * @param ud 操作员信息
   * @param jsjdm 日志数据
   * @param sblx 标明是季报或年报
   */
  public static void makeLog4Qysds(UserData ud, String jsjdm, String sblx) {
    //构造LogData
    LogData logData = new LogData(ud);
    logData.setYwmc(sblx);
    logData.setCzlx("删除申报数据");
    logData.setCzms("计算机代码：" + jsjdm);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * 得到当前数据库时间的时间戳
   * @return  当前时间戳
   */
  public static Timestamp getDBTimestamp(Connection conn) {
    ResultSet rs = null;
    Statement stmt = null;
    String sql = "select sysdate from dual";

    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      rs.next();
      Timestamp ret = rs.getTimestamp("sysdate");
      System.out.println("-DB Timestamp -==" + ret);
      rs.close();
      stmt.close();
      return ret;
    }
    catch (SQLException ex) {
      long curTime = System.currentTimeMillis();
      return new Timestamp(curTime);
    }
  }

  //将秒格式的Timestamp转换为到天格式的Timestamp
    public static java.sql.Timestamp second2Day(java.util.Date date)
    {
        if (date==null)
            return null;
        java.sql.Timestamp tempStamp = null;

        try
        {
            String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            tempStamp = java.sql.Timestamp.valueOf(tempStr +" 00:00:00.000");
        }
        catch (Exception ex) {}

        return tempStamp;
    }

    /**
     * 根据properties的名称，从配置文件中读取该properties对应的值。
     * @param propertyName properties的名称
     * @return java.lang.String
     * @throws Exception
     * added by qinw 20060925 
     */
    public static String getProperty(String propertyName) throws Exception
    {
        String value = null;
        Connection conn = null;
        ORManager orMgr = null;
        try
        {
            // 查询属性配置表的查询结果
            ArrayList result = new ArrayList();

            // 获得数据库连接
            conn = SfDBResource.getConnection();
            // 获得 ORManager
            orMgr = SfDBResource.getORManager();
            String sqlWhere = "propertyname = '" + propertyName + "' AND zxbs = '0'";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(com.ttsoft.bjtax.shenbao.model.domain.Properties.class.getName(), criteria);
            result = (ArrayList) orMgr.query(0, conn, orCtx); // 查询属性配置表
            if (result.size() == 0)
            {
                return ""; // 没有可以维护的申报数据！
            }
            value = ((com.ttsoft.bjtax.shenbao.model.domain.Properties) result.get(0)).getPropertyvalue();
            System.out.println("getProperty value========="+value);
            
            if (value == null)
                value = "";
        }
        catch (BaseException ex)
        {
        	throw ExceptionUtil.getBaseException(ex, "查询再就业征期日历出错");
        } 
        finally
        { 
             SfDBResource.freeConnection(conn);
        }
        if(value == null)
            value = "";
        return value.trim();
    }

    
    
    /**
     * 根据当前日期在properties对应的值范围内，从配置文件中读取该properties名称，从而确定申报所属年度季度值。
     * @param propertyValue properties的值
     * @return java.lang.String
     * @throws Exception
     * added by zhangyj 20080421 
     */
    public static String getPropertyName(String date) throws Exception
    {
        String name = null;
        Connection conn = null;
        ORManager orMgr = null;
        try
        {
            // 查询属性配置表的查询结果
            ArrayList result = new ArrayList();

            // 获得数据库连接
            conn = SfDBResource.getConnection();
            // 获得 ORManager
            orMgr = SfDBResource.getORManager();
            String sqlWhere = " '" + date + "'>=substr(propertyvalue,1,8) AND '" + date + "'<=substr(propertyvalue,10,8) AND propertyname LIKE 'SMSB_ZJYJMSB_ZQRL_QUARTER%' AND zxbs = '0'";         
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(com.ttsoft.bjtax.shenbao.model.domain.Properties.class.getName(), criteria);
            result = (ArrayList) orMgr.query(0, conn, orCtx); // 查询属性配置表
            if (result.size() == 0)
            {
                return ""; // 没有可以维护的申报数据！
            }
            name = ((com.ttsoft.bjtax.shenbao.model.domain.Properties) result.get(0)).getPropertyname();
            
            if (name == null)
            	name = "";
        }
        catch (BaseException ex)
        {
        	throw ExceptionUtil.getBaseException(ex, "查询再就业征期日历出错");
        } 
        finally
        { 
             SfDBResource.freeConnection(conn);
        }
        if(name == null)
        	name = "";
        return name.trim();
    }    
    
    
    /** 判断当前日期是否在征期内
     *  目前只有再就业减免申报使用 
     * @param zqstr
     * @param now
     * @return true当前日期符合条件申报资料可以录入，false当前日期不符合条件
     * added by qinw 20060925
     */
    public static boolean withinZq(String zqstr, Date now)
    {   
    	if (zqstr.equals("")) {
    		return false;
    	}
        try
        {
            int fromDate = Integer.parseInt(zqstr.substring(0, 4));
            int toDate = Integer.parseInt(zqstr.substring(5));
            int nowDate = Integer.parseInt((new SimpleDateFormat("MMdd")).format(now));

            if (nowDate <= toDate && nowDate >= fromDate)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
            return false;
        }
        finally
        {

        }
    }
    
    /**
	 * @author gaoyh
     * @date 2010-09-27
     * @modify-type add
     * @description 根据计算机代码判断是企业还是自然人, 
     * 				如果计算机代码最后一位为数字则对应的纳税人为企业，否则为自然人
	 * @param jsjdm
	 *            页面中输入的计算机代码
	 * @return boolean
	 */
	public static boolean isCompany(String jsjdm) {
		System.out
				.println("======根据计算机代码最后一位判断纳税人是企业还是自然人（计算机代码最后一位为数字则纳税人为企业，否则为自然人）======");

		String lastCharacter = jsjdm.substring(jsjdm.length() - 1);
		boolean isNum = lastCharacter.matches("[0-9]+");

		return isNum;
	}
}
