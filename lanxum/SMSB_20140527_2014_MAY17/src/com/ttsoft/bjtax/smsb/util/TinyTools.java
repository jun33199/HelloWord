/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: һЩС�İ����������ܺ�</p>
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
   * ��Calendarת��Ϊһ��Timestamp�����ڣ�ֻ��ȷ���죩
   * @param gCalendar ʱ��
   * @return Timestamp������
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
   * ��ʱ������ת��Ϊ��ʽ�ַ���
   * @param date ����
   * @param format String
   * @return ��ʽ������ַ���
   */
  public static String Date2String(java.util.Date date, String format) {
    if (date == null) {
      return null;
    }

    // ��ʽ��Class
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);

    return sdf.format(date);
  }

  /**
   * ��ʱ������ת��Ϊ "yyyy-mm-dd" ��ʽ���ַ���
   * @param date ����
   * @return ��ʽ������ַ���
   */
  public static String Date2String(java.util.Date date) {
    return TinyTools.Date2String(date, "yyyy-MM-dd");
  }

  /**
   * ����ָ���ĸ�ʽ�����ַ�ʱ�䲢ת��Ϊ���ڸ�ʽʱ��
   * @param stringDate �ַ�ʱ��
   * @param pattern ʱ���ʽ
   * @return ת���������
   */
  public static Date stringToDate(String stringDate, String pattern) {

    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      return simpleDateFormat.parse(stringDate);
    }
    catch (Exception ex) {
      //������쳣ת��Ϊ��ֵ����,���÷�������һ�����
      return null;
    }
  }

  /**
   * ����Ĭ�ϵĸ�ʽ(yyyy-MM-dd)�����ַ�ʱ�䲢ת��Ϊ���ڸ�ʽʱ��
   * @param stringDate �ַ���ʱ��
   * @return ת��������ڸ�ʽʱ��
   */
  public static Date stringToDate(String stringDate) {
    return stringToDate(stringDate, "yyyy-MM-dd");
  }

  /**
   * �õ��������ڵļ��� Ϊint��
   * @param date ����������
   * @return int ����ֵ(1,2,3,4)
   */
  public static int getQuarter(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.MONTH) / 3 + 1);
  }

  /**
   * �õ��������ڵ���� Ϊint��
   * @param date ����������
   * @return int ���ֵ
   */
  public static int getYear(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.YEAR));
  }

  /**
   * �õ��������ڵ��·� Ϊint��
   * @param date ����������
   * @return int �·�ֵ(0��ʼ)
   */
  public static int getMonth(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.MONTH));
  }

  /**
   * �õ��������ڵı��µļ��� Ϊint��
   * @param date ����������
   * @return int ���(1��ʼ)
   */
  public static int getDay(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    return (calendar.get(Calendar.DATE));
  }

  //�����ϴε�ʱ��
  private static long oldtime;

  /**
   * ���ܵ��Թ���
   * @param message ������Ϣ
   */
  public static void log(String message) {
    long now = System.currentTimeMillis();
    long delta = now - oldtime;
    oldtime = now;
    //Debug.out("Pass:" + delta + "\t" + message);
  }

  /**
   * ���ǰ�����������
   * @param year ָ����
   * @param date ����������
   * @return ǰ�����������
   */
  public static Date addYear(int year, Date date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //�޸���
    calendar.add(calendar.YEAR, year);
    //�õ��޸ĺ������
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * �趨ָ����ֵ
   * @param date ����������
   * @param field ָ����
   * @param num ָ����ֵ
   * @return Date
   */
  public static Date setByField(Date date, int field, int num) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //�޸���
    calendar.set(field, num);
    //�õ��޸ĺ������
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * �Ӽ���ǰ��
   * @param day ָ����
   * @param date ����������
   * @return ��һ�·ݵ�����
   */
  public static Date addDay(int day, Date date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //�޸���
    calendar.add(calendar.DATE, day);
    //�õ��޸ĺ������
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * �Ӽ���ǰ��
   * @param month ָ����
   * @param date ����������
   * @return ��һ�·ݵ�����
   */
  public static Date addMonth(int month, Date date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    //�޸���
    calendar.add(calendar.MONTH, month);
    //�õ��޸ĺ������
    Date temp = calendar.getTime();
    return temp;
  }

  /**
   * �õ��������ڵ�����������
   * @param date ����������2003-11-20
   * @return date 2003-11-20 16:03:01.945
   */
  public static Date getDayTime(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    Calendar calendar2 = new GregorianCalendar();
    //����ʱ����
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
   * �õ�ʱ�����
   * @param swjgzzjgdm ˰�������֯�ṹ����
   * @return String ���
   */
  public static String getXh(String swjgzzjgdm) {
    return swjgzzjgdm
        + TinyTools.Date2String(new Date(), "yyyyMMddHHmmssSSS");
  }

  /**
   *��¼��־
   * 20040429 Shi Yanfeng
   * @param ld ��־����
   */
  public static void makeLog(LogData ld) {
    //ͨ��LogBeanClient����ϢBean������Ϣ����¼��־
//    Debug.outVO(ld);
    ld.setRzjbdm(LogData.LEVEL_3);
    LogBeanClient.thtflog(ld);
  }

  /**
   * �걨����>���幤�̻�>������˰֤ ��¼��־
   * 20040429 Shi Yanfeng
   * @param ud ����Ա��Ϣ
   * @param wszh ��˰֤��
   * @param jsjdm ���������
   * @param wszxh ��˰֤���
   */
  public static void makeLog4Gtgsh(UserData ud, String wszh, String jsjdm,
                                   String wszxh) {
    //����LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("�걨����>���幤�̻�>������˰֤");
    logData.setCzlx("������˰֤");
    logData.setCzms("��˰�˼�������룺" + jsjdm + " ��˰֤�ţ�" + wszh + " ��˰֤��ţ�"
                    + wszxh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * �걨����>���幤�̻�>������˰֤ ��¼��־
   * 20040429 Shi Yanfeng
   * @param ud ����Ա��Ϣ
   * @param sbhzdh �걨���ܵ���
   */
  public static void makeLog4GtgshZfjks(UserData ud, String sbhzdh) {
    //����LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("�걨����>���幤�̻�>������˰֤���ܽɿ���");
    logData.setCzlx("������˰֤���ܽɿ���");
    logData.setCzms(" �걨���ܵ��ţ�" + sbhzdh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * ����ӡ��˰����������� ��¼��־
   * 20040429 Shi Yanfeng
   * @param ud ����Ա��Ϣ
   * @param jkpzh �ɿ�ƾ֤��
   */
  public static void makeLog4YhsZfjks(UserData ud, String jkpzh) {
    //����LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("����ӡ��˰�����������");
    logData.setCzlx("����ӡ��˰�����������");
    logData.setCzms("�ɿ�ƾ֤�ţ�" + jkpzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * ����ӡ��˰����������� ��¼��־
   * 20040429 Shi Yanfeng
   * @param ud ����Ա��Ϣ
   * @param jkpzh �ɿ�ƾ֤��
   */
  public static void makeLog4DsYhsZfjks(UserData ud, String jkpzh) {
    //����LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("����ӡ��˰���۵�λ���ۻ���");
    logData.setCzlx("����ӡ��˰���۵�λ���ۻ���");
    logData.setCzms("�ɿ�ƾ֤�ţ�" + jkpzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * ����ӡ��˰����������� ��¼��־
   * 20040429 Shi Yanfeng
   * @param ud ����Ա��Ϣ
   * @param xspzh ��־����
   */
  public static void makeLog4YhsCxgm(UserData ud, String xspzh) {
    //����LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("¼��ӡ��˰�����������");
    logData.setCzlx("¼��ӡ��˰�����������");
    logData.setCzms("����ƾ֤�ţ�" + xspzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * ����ӡ��˰����������� ��¼��־
   * 20040429 Shi Yanfeng
   * @param ud ����Ա��Ϣ
   * @param jkpzh ��־����
   */
  public static void makeLog4ZhsbCx(UserData ud, String jkpzh) {
    //����LogData
    LogData logData = new LogData(ud);
    logData.setYwmc("�걨�ɿ�");
    logData.setCzlx("�����ɿ���");
    logData.setCzms("�ɿ�ƾ֤�ţ�" + jkpzh);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * ����ӡ��˰����������� ��¼��־
   * @param ud ����Ա��Ϣ
   * @param jsjdm ��־����
   * @param sblx �����Ǽ������걨
   */
  public static void makeLog4Qysds(UserData ud, String jsjdm, String sblx) {
    //����LogData
    LogData logData = new LogData(ud);
    logData.setYwmc(sblx);
    logData.setCzlx("ɾ���걨����");
    logData.setCzms("��������룺" + jsjdm);
    logData.setYxsj(new java.sql.Date(System.currentTimeMillis()));
    makeLog(logData);
  }

  /**
   * �õ���ǰ���ݿ�ʱ���ʱ���
   * @return  ��ǰʱ���
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

  //�����ʽ��Timestampת��Ϊ�����ʽ��Timestamp
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
     * ����properties�����ƣ��������ļ��ж�ȡ��properties��Ӧ��ֵ��
     * @param propertyName properties������
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
            // ��ѯ�������ñ�Ĳ�ѯ���
            ArrayList result = new ArrayList();

            // ������ݿ�����
            conn = SfDBResource.getConnection();
            // ��� ORManager
            orMgr = SfDBResource.getORManager();
            String sqlWhere = "propertyname = '" + propertyName + "' AND zxbs = '0'";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(com.ttsoft.bjtax.shenbao.model.domain.Properties.class.getName(), criteria);
            result = (ArrayList) orMgr.query(0, conn, orCtx); // ��ѯ�������ñ�
            if (result.size() == 0)
            {
                return ""; // û�п���ά�����걨���ݣ�
            }
            value = ((com.ttsoft.bjtax.shenbao.model.domain.Properties) result.get(0)).getPropertyvalue();
            System.out.println("getProperty value========="+value);
            
            if (value == null)
                value = "";
        }
        catch (BaseException ex)
        {
        	throw ExceptionUtil.getBaseException(ex, "��ѯ�پ�ҵ������������");
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
     * ���ݵ�ǰ������properties��Ӧ��ֵ��Χ�ڣ��������ļ��ж�ȡ��properties���ƣ��Ӷ�ȷ���걨������ȼ���ֵ��
     * @param propertyValue properties��ֵ
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
            // ��ѯ�������ñ�Ĳ�ѯ���
            ArrayList result = new ArrayList();

            // ������ݿ�����
            conn = SfDBResource.getConnection();
            // ��� ORManager
            orMgr = SfDBResource.getORManager();
            String sqlWhere = " '" + date + "'>=substr(propertyvalue,1,8) AND '" + date + "'<=substr(propertyvalue,10,8) AND propertyname LIKE 'SMSB_ZJYJMSB_ZQRL_QUARTER%' AND zxbs = '0'";         
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(com.ttsoft.bjtax.shenbao.model.domain.Properties.class.getName(), criteria);
            result = (ArrayList) orMgr.query(0, conn, orCtx); // ��ѯ�������ñ�
            if (result.size() == 0)
            {
                return ""; // û�п���ά�����걨���ݣ�
            }
            name = ((com.ttsoft.bjtax.shenbao.model.domain.Properties) result.get(0)).getPropertyname();
            
            if (name == null)
            	name = "";
        }
        catch (BaseException ex)
        {
        	throw ExceptionUtil.getBaseException(ex, "��ѯ�پ�ҵ������������");
        } 
        finally
        { 
             SfDBResource.freeConnection(conn);
        }
        if(name == null)
        	name = "";
        return name.trim();
    }    
    
    
    /** �жϵ�ǰ�����Ƿ���������
     *  Ŀǰֻ���پ�ҵ�����걨ʹ�� 
     * @param zqstr
     * @param now
     * @return true��ǰ���ڷ��������걨���Ͽ���¼�룬false��ǰ���ڲ���������
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
     * @description ���ݼ���������ж�����ҵ������Ȼ��, 
     * 				���������������һλΪ�������Ӧ����˰��Ϊ��ҵ������Ϊ��Ȼ��
	 * @param jsjdm
	 *            ҳ��������ļ��������
	 * @return boolean
	 */
	public static boolean isCompany(String jsjdm) {
		System.out
				.println("======���ݼ�����������һλ�ж���˰������ҵ������Ȼ�ˣ�������������һλΪ��������˰��Ϊ��ҵ������Ϊ��Ȼ�ˣ�======");

		String lastCharacter = jsjdm.substring(jsjdm.length() - 1);
		boolean isNum = lastCharacter.matches("[0-9]+");

		return isNum;
	}
}
