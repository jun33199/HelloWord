/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title:Title: 北京地税综合管理系统  框架模块</p>
 * <p>Description: 计算税款所属日期</p>
 * <p>通过传入一个日期,获得税款所属日期<p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class Skssrq
{
    /**
     * 税款所属开始日期常量
     */
    public static final String SKSSKSRQ = "SKSSKSRQ";

    /**
     * 税款所属结束日期常量
     */
    public static final String SKSSJSRQ = "SKSSJSRQ";

    /**
     * 税款所属日期年度常量
     */
    public static final String SKSSRQ_ND = "ND";

    /**
     * 企业所得税
     */
    private static final String SZDM_QYSDS = "30";

    /**
     * 个人所得税()
     */
    private static final String SZDM_GRSDS = "0512";

    /**
     * 印花税
     */
    private static final String SZDM_YHS = "16";

    /**
     * 房产税
     */
    private static final String SZDM_FCS = ",12,89,";

    /**
     * 车船使用费
     */
    private static final String SZDM_CCSYF = ",11,88,";

    /**
     * 土地使用费
     */
    private static final String SZDM_TDSYF = ",15,76,";

    /**
     * 土地使用费
     */

    public static final String SZDM_CFT = ",12,89,11,88,09,15,76,";

    /**
     * 正常税票税款类型的首位
     */
    private static final String SKLXDM_ZCSP = "1";

    /**
     * 补缴欠税税款类型的首位
     */
    private static final String SKLXDM_BJQS = "4";

    /**
     * 征期类型代码:季报
     */
    private static final String ZQLXDM_JB = "4";

    /**
     * 征期类型代码:年报
     */
    private static final String ZQLXDM_NB = "1";

    /**
     * 征期类型代码:月报
     */
    private static final String ZQLXDM_YB = "12";

    /**
     * 征期类型代码:半年
     */
    private static final String ZQLXDM_SY = "2";

    /**
     * 日期格式
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 计算年报类型的税款所属日期
     * @param curDate 日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 Strng
     */
    public static Map yearSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        calendar.add(calendar.YEAR, -1);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        Timestamp skssksrqDate;
        Timestamp skssjsrqDate;
        Map retMap = new HashMap();
        skssksrqDate = new Timestamp(new GregorianCalendar(year, 0, 1).getTime().
                                     getTime());
        skssjsrqDate = new Timestamp(new GregorianCalendar(year, 11, 31).
                                     getTime().getTime());
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);
        calendar.add(calendar.YEAR, 1);
        return retMap;
    }

    /**
     * 计算季报类型的税款所属日期
     * @param curDate 日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map quarterSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int jd = month / 3 + 1;
        Timestamp skssksrqDate = new Timestamp(new GregorianCalendar(year,
            (jd - 1) * 3, 1).getTime().getTime());
        Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
            (jd - 1) * 3 + 2,
            new GregorianCalendar(year, (jd - 1) * 3 + 2,
                                  1).getActualMaximum(calendar.
            DAY_OF_MONTH)
            ).getTime().getTime());
        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);
        return retMap;
    }

    /**
     * 取得传入日期所在的前一个季度
     * @param curDate 日期
     * @return String 季度
     */
    public static String preQuarter (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);
        int jd = month / 3;
        if (jd == 0)
        {
            jd = 4;
        }
        return new Integer(jd).toString();
    }

    /**
     * 取得传入日期所在的当前季度
     * @param curDate 日期
     * @return String 季度
     */
    public static String curQuarter (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);
        int jd = month / 3 + 1;
        return new Integer(jd).toString();
    }

    /**
     * 计算月报类型的税款所属日期
     * @param curDate 日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map monthSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        calendar.add(calendar.MONTH, -1);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int month = calendar.get(calendar.MONTH);
        int maxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        Timestamp skssksrqDate = new Timestamp(new GregorianCalendar(year,
            month, 1).getTime().getTime());
        Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
            month, maxDay).getTime().getTime());
        calendar.add(calendar.MONTH, 1);
        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);
        return retMap;
    }

    /**
     * 计算其它类型的税款所属日期
     * @param curDate 日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map otherSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        calendar.add(calendar.MONTH, -1);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int maxDate = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        Timestamp skssksrqDate = new Timestamp(new GregorianCalendar(year, 0, 1).
                                               getTime().getTime());
        Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
            month, maxDate).getTime().getTime());
        calendar.add(calendar.MONTH, 1);
        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);
        return retMap;
    }

        /*---------------------------------------------------------------------------*/

    /**
     * 得到税款所属时期
     * @param jsjdm 计算机代码
     * @param szsmdm 税种税目代码
     * @param sklxdm 税款类型代码
     * @param zqlxdm 征期类型代码
     * @param curDate 当前时间
     * @param sjje 实缴金额
     * @param kssl 课税数量
     * @param jsje 计税金额
     * @param  ynsjeMap 该计算机代码对应的所有的年应纳税额
     * @return Map 税款所属开始 结束时间
     * @throws BaseException
     */
    public static Map getSksssq (String jsjdm, String szsmdm, String sklxdm,
                                 String zqlxdm,
                                 Date curDate, BigDecimal sjje, BigDecimal kssl,
                                 BigDecimal jsje, Map ynsjeMap)
        throws
        BaseException
    {
        Map sksssqMap = new HashMap();
        if (szsmdm == null || sklxdm == null || zqlxdm == null || curDate == null)
        {
            return sksssqMap;
        }
        if (sklxdm.equals(CodeConstant.SKLXDM_ZCJK))
        {
            return doZcsp(jsjdm, szsmdm, zqlxdm, curDate, sjje, kssl, jsje,
                          ynsjeMap);
        }
        else if (sklxdm.equals(CodeConstant.SKLXDM_SDJJ))
        {
            return monthSkssrq(curDate);
        }

        return sksssqMap;
    }

    /**
     * 得到税款所属时期
     * @param jsjdm 计算机代码
     * @param szsmdm 税种税目代码
     * @param sklxdm 税款类型代码
     * @param zqlxdm 征期类型代码
     * @param curDate 当前时间
     * @param sjje 实缴金额
     * @param kssl 课税数量
     * @param jsje 计税金额
     * @param  ynsje 该计算机代码对应的所有的年应纳税额
     * @return Map 税款所属开始 结束时间
     * @throws BaseException
     */
    public static Map getSksssq (String jsjdm, String szsmdm, String sklxdm,
                                 String zqlxdm,
                                 Date curDate, BigDecimal sjje, BigDecimal kssl,
                                 BigDecimal jsje, BigDecimal ynsje)
        throws
        BaseException
    {
        Map sksssqMap = new HashMap();
        if (szsmdm == null || sklxdm == null || zqlxdm == null || curDate == null)
        {
            return sksssqMap;
        }
        if (sklxdm.equals(CodeConstant.SKLXDM_ZCJK))
        {
            //生成定期定额数据
            Map ynsjeMap = new HashMap();
            Dqdedlmx1 temp = new Dqdedlmx1();
            //设置年应纳税额
            if (ynsje != null)
            {
                temp.setYnsrd(ynsje);
            }
            ynsjeMap.put(szsmdm, temp);
            return doZcsp(jsjdm, szsmdm, zqlxdm, curDate, sjje, kssl, jsje,
                          ynsjeMap);
        }
        else if (sklxdm.equals(CodeConstant.SKLXDM_SDJJ))
        {
            return monthSkssrq(curDate);
        }

        return sksssqMap;
    }

    /**
     * 得到税款所属时期
     * 正常税票
     * @param jsjdm 计算机代码
     * @param szsmdm 税种税目代码
     * @param zqlxdm 征期类型代码
     * @param curDate 当前时间
     * @param sjse 实缴金额
     * @param kssl 课税数量
     * @param jsje 计税金额
     * @param  ynsjeMap 该计算机代码对应的所有的年应纳税额
     * @return Map 税款所属开始 结束时间
     * @throws BaseException
     */

    private static Map doZcsp (String jsjdm,
                               String szsmdm,
                               String zqlxdm,
                               Date curDate,
                               BigDecimal sjse,
                               BigDecimal kssl,
                               BigDecimal jsje, Map ynsjeMap)
        throws BaseException
    {
        //分析当前时间
        int curYear = TinyTools.getYear(curDate);
        int curMonth = TinyTools.getMonth(curDate); //0--11，－》1月－12月
        int curQuarter = TinyTools.getQuarter(curDate);
        int curDay = TinyTools.getDay(curDate);
        Calendar curCalendar = new GregorianCalendar(curYear, curMonth, curDay);

        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        Calendar calendar = null;
        //返回的结果map
        Map sksssqMap = new HashMap();
        BigDecimal ys = new BigDecimal("12"); //12个月

        if (ZQLXDM_JB.equals(zqlxdm))
        { //季报
            if (szsmdm.substring(0, 2).equals(SZDM_QYSDS) ||
                szsmdm.substring(0, 4).equals(SZDM_GRSDS))
            {

                return accumulatedQuarter(curDate);
            }
            else
            {

                return commonQuarter(curDate);
            }
        }

        if (ZQLXDM_NB.equals(zqlxdm))
        { //年报
            if (szsmdm.substring(0, 2).equals(SZDM_YHS)
                || szsmdm.substring(0, 2).equals(SZDM_QYSDS)
                || szsmdm.substring(0, 4).equals(SZDM_GRSDS))
            {
                //开始日期:上一年的1月1号
                skssksrq = getTimestampMinDay(curYear - 1, 0);
                //结束日期:上一年的最后一天
                skssjsrq = getTimestampMaxDay(curYear - 1, 11);
            }
//      else {
//        Debug.out("这是规则所不允许的！年报中不存在这种税种税目代码“：" + szsmdm);
//      }
        }

        if (ZQLXDM_YB.equals(zqlxdm))
        { //月报
            //开始日期:上月的一号
            skssksrq = getTimestampMinDay(curYear, curMonth - 1);
            //结束日期:上月的最后一天
            skssjsrq = getTimestampMaxDay(curYear, curMonth - 1);
        }
        //填充返回结果
        sksssqMap.put(SKSSKSRQ, skssksrq);
        sksssqMap.put(SKSSJSRQ, skssjsrq);
        return sksssqMap;
    }

    /**
     * 得到指定月份对应的上一季度的最小月份
     * @param month 指定月份
     * @return 指定月份对应的上一季度的最小月份
     */
    private static int getMinMonth_Prequarter (final int month)
    {
        if (month < 3)
        {
            return 0;
        }
        else
        {
            return (month / 3 - 1) * 3;
        }

    }

    /**
     * 去当前年的税款所属日期
     * @param curDate 日期
     * @return Map
     */
    private static Map curYearSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString(); // 求年度

        Map retMap = new HashMap(2);

        // 上一年的第一天
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, 0, 1).getTime().getTime());
        // 上一年的最后一天
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, 11, 31).getTime().getTime());

        retMap.put(SKSSKSRQ, skssksrqDate); // 开始日期
        retMap.put(SKSSJSRQ, skssjsrqDate); // 结束日期
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }

    /**
     * 得到指定月份对应的上一季度的最大月份
     * @param month 指定月份
     * @return 指定月份对应的上一季度的最大月份
     */
    private static int getMaxMonth_Prequarter (final int month)
    {
        if (month < 3)
        {
            return 2;
        }
        else
        {
            return (month / 3 - 1) * 3 + 2;
        }
    }

    /**
     * 得到指定年和月对应的最大的天数
     * @param year 指定年
     * @param month 指定月
     * @return 指定年和月对应的最大的天数
     */
    private static int getmaxDay (final int year, final int month)
    {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到指定年和月对应的最大的天数的Timestamp日期格式
     * @param year 指定的年
     * @param month 指定的月
     * @return 返回指定的日期
     */
    public static Timestamp getTimestampMaxDay (final int year, final int month)
    {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return new Timestamp( (new GregorianCalendar(year, month, maxDay)).
                             getTime().
                             getTime());
    }

    /**
     * 得到指定年和月对应的第1天的Timestamp日期格式
     * @param year 指定的年
     * @param month 指定的月
     * @return 返回指定的日期
     */
    public static Timestamp getTimestampMinDay (final int year, final int month)
    {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 根据征期日历得到税款所属时期<br>
     * 当前日期的月等于征期起始或截止日期的月
     * 征期截止日期＋1天=限缴日期<br>
     *
     * @param szsmdm 税种税目代码
     * @param rq 当前时间
     * @param djzclxdm 登记注册类型
     * @return Zqrl 税款所属开始 结束时间，限缴日期，
     * @throws Exception
     */
    public static Zqrl getSksssqByZqrl (String szsmdm, Date rq, String djzclxdm)
        throws
        Exception
    {
        Zqrl ret = new Zqrl();
        Connection conn = null;
        try
        {
            //得到连接
            conn = SfDBResource.getConnection();

            String dateStr = TinyTools.Date2String(rq,"yyyyMM");

            Vector criteria = new Vector(); //查询条件
            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("szsmdm = '" + szsmdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");
            SfDBAccess db = new SfDBAccess(conn);
            List queryresult = db.query(new Zqrl().getClass(), criteria);
            if (queryresult.size() == 0)
            {
                //如果征期日历中没有核定则使用本月15号作为税款所属日期
                /*******************************************/
                /**              Shi Yanfeng              **/
                /**修改限缴日期***/
                /*******************************************/
                long currentTime = System.currentTimeMillis();
                Timestamp time = new Timestamp(currentTime);
//        time.setDate(15);
                time = new Timestamp(TinyTools.setByField(time, Calendar.DATE,
                    15).
                                     getTime());
                Zqrl zqrl = new Zqrl();
                zqrl.setZqzzrq(time);
                //return monthSkssrqZqrl(rq);
                return zqrl;
                //return null;
            }
            else
            {
                return (Zqrl) queryresult.get(0);

            }

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询征期日历失败!");
        }
        finally
        {
            //释放连接
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 计算月报类型的税款所属日期
     * @param curDate 日期
     * @return Zqrl
     */
    public static Zqrl monthSkssrqZqrl (Date curDate)
    {
        Zqrl ret = new Zqrl();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        calendar.add(calendar.MONTH, -1);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int month = calendar.get(calendar.MONTH);
        int maxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
        Timestamp skssksrqDate = new Timestamp(new GregorianCalendar(year,
            month, 1).getTime().getTime());
        Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
            month, maxDay).getTime().getTime());
        //限缴日期为上月十号
        Timestamp skxjrqDate = new Timestamp(new GregorianCalendar(year,
            month, 10).getTime().getTime());
        calendar.add(calendar.MONTH, 1);

        ret.setZqssrqq(skssksrqDate);
        ret.setZqssrqz(skssjsrqDate);
        ret.setZqzzrq(skxjrqDate);
        return ret;
    }

    /**
     * 计算税款所属时期
     * @param ys 计算出的月数
     * @param curDate 当前日期
     * @return map
     */
    private static Map getSksq (BigDecimal ys, Date curDate)
    {
        HashMap dataMap = new HashMap();
        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        BigDecimal def_6 = new BigDecimal("6");
        BigDecimal def_12 = new BigDecimal("12");
        //分析当前时间
        int curYear = TinyTools.getYear(curDate);
        int curMonth = TinyTools.getMonth(curDate);
        int curQuarter = TinyTools.getQuarter(curDate);
        int curDay = TinyTools.getDay(curDate);

        if (curMonth <= 6)
        { //前半年
            if (ys.equals(def_6))
            { //如果计算结果为6
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:6月的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 5);
            }
            else if (ys.equals(def_12))
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:当12月的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if (ys.compareTo(def_6) == -1)
            { //ys小于6，返回-1
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:计算结果月的最后一天
                skssjsrq = getTimestampMaxDay(curYear,
                                              Integer.parseInt(ys.toString())
                                              - 1);
            }
            else if (ys.compareTo(def_12) == -1)
            { //ys小于12，返回-1
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:计算结果月的最后一天
                skssjsrq = getTimestampMaxDay(curYear,
                                              Integer.parseInt(ys.toString())
                                              - 1);
            }
            else if (ys.compareTo(def_12) == 1)
            { //大于12
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:本年的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
        }
        else if (curMonth > 6)
        { //后半年
            if (ys.equals(def_6))
            { //如果计算结果为6
                //开始日期,本年的7月1号
                skssksrq = getTimestampMinDay(curYear, 6);
                //结束日期:本年的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if (ys.compareTo(def_6) == -1)
            { //ys小于6，返回-1
                //开始日期,本年的7月1号
                skssksrq = getTimestampMinDay(curYear, 6);
                //结束日期:6+计算结果月的最后一天
                skssjsrq = getTimestampMaxDay(curYear,
                                              6 + Integer.parseInt(ys.toString()));
            }
            else
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:本年的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
        }
        //填充返回结果
        dataMap.put(SKSSKSRQ, skssksrq);
        dataMap.put(SKSSJSRQ, skssjsrq);
        return dataMap;
    }

    /**
     * 如果申报的是车房土税种，取税款所属时期的时候，要先读税费的缴纳次数核定的接口；
     * 如果接口返回0则说明没有缴纳次数的核定，这时申报按年生成税款所属时期，即申报日期所在年的1月1日至12月31日；
     * 如果接口返回的值大于0，则根据当前申报日期是在上半年还是下半年来生成税款所属时期，如果当前申报日期是在上半年，且接口返回值不等于0，则税款所属时期为申报日期所在的上半年；
     * 如果申报日期是在下半年，且接口返回值不等于0时，同理。
     * @param  curDate 申报日期
     * @param  jncs 缴纳次数
     * @return Map 税款所属日期
     */
    public static Map getCftSkssrq (Date curDate, int jncs)
    {
        HashMap dataMap = new HashMap();
        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        //分析当前时间
        int curYear = TinyTools.getYear(curDate);
        int curMonth = TinyTools.getMonth(curDate);

        if (jncs <= 0)
        {
            //如果接口返回0则说明没有缴纳次数的核定，这时申报按年生成税款所属时期，即申报日期所在年的1月1日至12月31日；
            //开始日期,本年的1月1号
            skssksrq = getTimestampMinDay(curYear, 0);
            //结束日期:当12月的最后一天
            skssjsrq = getTimestampMaxDay(curYear, 11);
        }
        else
        {
            if (curMonth < 6)
            {
                //如果当前申报日期是在上半年，且接口返回值不等于0，则税款所属时期为申报日期所在的上半年；
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:6月的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 5);
            }
            else
            {
                //开始日期,本年的7月1号
                skssksrq = getTimestampMinDay(curYear, 6);
                //结束日期:本年的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 11);

            }

        }
        dataMap.put(Skssrq.SKSSKSRQ, skssksrq);
        dataMap.put(Skssrq.SKSSJSRQ, skssjsrq);
        return dataMap;

    }

    /**
     * 取得税种税目值对象
     * @param  szsmdm 税种税目代码
     * @return Szsm 税种税目值对象 如果没有该值对象则返回null
     */
    private static Szsm getSzsm (String szsmdm)
    {
        List ret = new ArrayList();
        //得到税种税目值对象列表
        ret = CodeManager.getCodeList("ORSZSM", CodeConstants.CODE_MAP_ORLIST).
              getRecords();
        for (int i = 0; i < ret.size(); i++)
        {
            //得到值对象
            Szsm temp = (Szsm) ret.get(i);
            if (temp.getSzsmdm().equals(szsmdm))
            {
                //返回匹配的值对象
                return temp;
            }
        }
        return null;

    }

    /**
     * 计算半年期类型的税款所属日期
     * @param curDate 日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map semiYearSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        Timestamp skssksrqDate = null;
        Timestamp skssjsrqDate = null;
        if (month >= 0 && month <= 5)
        {
            skssksrqDate = new Timestamp(new GregorianCalendar(year,
                0, 1).getTime().getTime());
            skssjsrqDate = new Timestamp(new GregorianCalendar(year,
                5, 30).getTime().getTime());
        }
        else
        {
            skssksrqDate = new Timestamp(new GregorianCalendar(year,
                6, 1).getTime().getTime());
            skssjsrqDate = new Timestamp(new GregorianCalendar(year,
                11, 31).getTime().getTime());
        }
        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);
        return retMap;
    }

    /**
     * 计算季报类型的税款所属日期
     * @param curDate 当前日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map commonQuarter (Date curDate)
    {
//    if (curDate.getMonth() < 3) {
        if (TinyTools.getMonth(curDate) < 3)
        {
            // 一季度相当于年报
            return yearSkssrq(curDate);
        }

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        String nd = new Integer(year).toString();

        // 上季度
        int jd = month / 3;

        // 上一季度第一天
        Timestamp skssksrqDate = getFirstDayOfQuarter(year, jd);
        // 上一季度最后一天
        Timestamp skssjsrqDate = getLastDayOfQuarter(year, jd);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }
    /**
     * 计算季报类型的税款所属日期
     * @param  nd 年度 jd 季度
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *              使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *              使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
     public static Map commonQuarter (int nd,int jd)
    {

        System.out.println(jd);

        // 上一季度第一天
        Timestamp skssksrqDate = getFirstDayOfQuarter(nd, jd);
        // 上一季度最后一天
        Timestamp skssjsrqDate = getLastDayOfQuarter(nd, jd);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
       

        return retMap;
    }

    /**
     * 得到累积型的税款所属日期
     * @param curDate 当前日期
     * @return 累积型的税款所属日期
     */
    private static Map accumulatedQuarter (Date curDate)
    {
        Map sksssqMap = new HashMap();

//    if (curDate.getMonth() < 3) {
        if (TinyTools.getMonth(curDate) < 3)
        {
            // 第一季度是年报
            return yearSkssrq(curDate);
        }

        //开始日期,本年的1月1号
//    sksssqMap.put(SKSSKSRQ, getTimestampMinDay(curDate.getYear() + 1900, 0));
        sksssqMap.put(SKSSKSRQ,
                      getTimestampMinDay(TinyTools.getYear(curDate), 0));
        //结束日期:当前日期的上个月的最后一天
//    sksssqMap.put(SKSSJSRQ,
//                  getTimestampMaxDay(curDate.getYear() + 1900,
//                                     curDate.getMonth() - 1));
        sksssqMap.put(SKSSJSRQ,
                      getTimestampMaxDay(TinyTools.getYear(curDate),
                                         TinyTools.getMonth(curDate) - 1));

        return sksssqMap;
    }

    /**
     * 得到季度的第一天
     * @param year 年
     * @param quarter 季度
     * @return 季度的第一天
     */
    private static Timestamp getFirstDayOfQuarter (int year, int quarter)
    {
        Calendar c = null; ;
        switch (quarter)
        {
            case 1:
                c = new GregorianCalendar(year, Calendar.JANUARY, 1);
                break;

            case 2:
                c = new GregorianCalendar(year, Calendar.APRIL, 1);
                break;

            case 3:
                c = new GregorianCalendar(year, Calendar.JULY, 1);
                break;

            case 4:
                c = new GregorianCalendar(year, Calendar.OCTOBER, 1);
                break;
        }
        return new Timestamp(c.getTime().getTime());
    }

    /**
     * 得到季度的最后一天
     * @param year 年
     * @param quarter 季度
     * @return 季度的最后一天
     */

    private static Timestamp getLastDayOfQuarter (int year, int quarter)
    {
        Calendar c = new GregorianCalendar();
        switch (quarter)
        {
            case 1:
                c = new GregorianCalendar(year, Calendar.MARCH, 31);
                break;

            case 2:
                c = new GregorianCalendar(year, Calendar.JUNE, 30);
                break;

            case 3:
                c = new GregorianCalendar(year, Calendar.SEPTEMBER, 30);
                break;

            case 4:
                c = new GregorianCalendar(year, Calendar.DECEMBER, 31);
                break;
        }

        return new Timestamp(c.getTime().getTime());
    }

}
