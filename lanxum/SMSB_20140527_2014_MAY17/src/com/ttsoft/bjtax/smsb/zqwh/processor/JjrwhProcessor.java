/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Czqrl;
import com.ttsoft.bjtax.shenbao.model.domain.Jqsz;
import com.ttsoft.bjtax.shenbao.model.domain.Zqlx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zqwh.web.JjrwhForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 节假日维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class JjrwhProcessor
    implements Processor
{
    /**征期类型，按年申报*/
    private final int YEAR_TYPE = 1;

    /**征期类型，按半年申报*/
    private final int HALFYEAR_TYPE = 2;

    /**征期类型，按季申报*/
    private final int SEASON_TYPE = 4;

    /**征期类型，按月申报*/
    private final int MONTH_TYPE = 12;

    /**本期*/
    private final String THIS_TERM = "1";

    /**上期*/
    private final String PREVIOUS_TERM = "0";
    /**本期累计*/
    private final String THIS_TERM_TOTAL = "3";

    /**上期累计*/
    private final String PREVIOUS_TERM_TOTAL = "2";
    /**
     * 通用处理调度模块
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {

        //回传对象
        Object result = null;
        //判断VO是否为空
        if (vo == null)
        {
            throw new NullPointerException();
        }
        //根据Action的值调用不同的process方法
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION: //前台默认显示
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION: //查询
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_CREATECALENDAR:
                result = doCreateCalendar(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 显示后台处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        JjrwhForm jf = (JjrwhForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);

            //查询明细数据
            Vector vecJjrwh = new Vector();
            vecJjrwh.add(" SSNF= '" + jf.getWhnf() + "' ");
            //明细查询数据结果
            List JjrwhList = da.query(Jqsz.class, vecJjrwh);

            String[] holiday = new String[JjrwhList.size()];
            for (int i = 0; i < JjrwhList.size(); i++)
            {
                //获得此条明细值
                Jqsz jqsz = (Jqsz) JjrwhList.get(i);
                holiday[i] = timestampToString(jqsz.getJqrq());
            }
            jf.setHolyArr(holiday);
            return jf;
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * 保存后台处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        JjrwhForm jf = (JjrwhForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Jqsz j = new Jqsz();
            da.delete(" SSNF= '" + jf.getWhnf() + "' ", j);
            String[] saveHoliday = jf.getSaveHoliday();
            List saveHolidayList = new ArrayList();
            Timestamp timeNow = SfTimeUtil.getNowTimestamp();
            if (saveHoliday != null)
            {
                for (int i = 0; i < saveHoliday.length; i++)
                {
                    j = new Jqsz();
                    j.setJqrq(stringToTimestamp(saveHoliday[i]));
                    j.setSsnf("" + TinyTools.getYear(j.getJqrq()));
                    j.setCjrq(timeNow);
                    j.setLrrq(timeNow);
                    j.setSwjgzzjgdm(vo.getUserData().getSsdwdm());
                    saveHolidayList.add(j);
                }
                da.insert(saveHolidayList);
            }

            //查询明细数据
            Vector vecJjrwh = new Vector();
            vecJjrwh.add(" SSNF= '" + jf.getWhnf() + "' ");
            //明细查询数据结果
            List JjrwhList = da.query(Jqsz.class, vecJjrwh);

            String[] holiday = new String[JjrwhList.size()];
            for (int i = 0; i < JjrwhList.size(); i++)
            {
                //获得此条明细值
                Jqsz jqsz = (Jqsz) JjrwhList.get(i);
                holiday[i] = timestampToString(jqsz.getJqrq());
            }
            jf.setHolyArr(holiday);
            return jf;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("保存失败！");
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * 生成征期日历
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doCreateCalendar (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        try
        {
            JjrwhForm jf = (JjrwhForm) vo.getData();
            int whnf = Integer.parseInt(jf.getWhnf()); //所维护年份
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Czqrl c = new Czqrl();
            //删除所维护年份的征期日历
            String strDeleteCondition = " ND = '" + whnf + "' ";
            da.delete(strDeleteCondition, c);

            //检索本年度假日信息
            Vector jqVector = new Vector();
            jqVector.add(" SSNF = '" + whnf + "' ");
            List jqList = da.query(Jqsz.class, jqVector);
            String[] holiday = new String[jqList.size()];
            //构造本年度假日数组
            for (int i = 0; i < jqList.size(); i++)
            {
                holiday[i] = timestampToString( ( (Jqsz) jqList.get(i)).getJqrq());
            }

            //检索本年度征期类型
            Vector zqlxVector = new Vector();
            zqlxVector.add(" TYRQ IS NULL AND ZQZQ IS NOT NULL");
            List zqlxList = da.query(Zqlx.class, zqlxVector);
            String[] zqlxColumns =
                {
                "zqlxdm", "zqlxmc", "zqzq", "skssq",
                "zqksrq", "zqts", "lrr", "lrrq",
                "tyrq"};
            Map[] zqlxMap = new Map[zqlxList.size()];
            for (int i = 0; i < zqlxList.size(); i++)
            {
                //获得此条明细值
                Zqlx orMx = (Zqlx) zqlxList.get(i);
                Map map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(zqlxColumns, orMx, map);
                zqlxMap[i] = map;
            }

            //生成征期类型
            for (int i = 0; i < zqlxMap.length; i++)
            {
                Czqrl czqrl[] = null;
                //申报类型
                int shenbaoType = 0;

                //分别处理每一个征期类型
                String zqzq = (String) zqlxMap[i].get("zqzq");
                Vector zqMonthVector = new Vector(); //存放有征期的月份
                //处理征期周期
                for (int j = 0; j < 12; j++)
                {
                    String month = zqzq.substring(j, j + 1);
                    if (month.equals("1"))
                    {
                        zqMonthVector.add("" + j);
                    }
                }
                //确定申报类型
                int vectorLength = zqMonthVector.size();
                switch (vectorLength)
                {
                    case YEAR_TYPE:

                        //按年申报
                        czqrl = new Czqrl[YEAR_TYPE];
                        for (int k = 0; k < YEAR_TYPE; k++)
                        {
                            czqrl[k] = new Czqrl();
                            int zqMonth = Integer.parseInt( (String)
                                zqMonthVector.get(k));
                            Calendar zqCalendar = GregorianCalendar.getInstance();
                            int zqDate = Integer.parseInt( (String) zqlxMap[i].
                                get("zqksrq"));
                            zqCalendar.set(whnf, zqMonth, zqDate);
                            int tianShu = Integer.parseInt( (String) zqlxMap[i].
                                get("zqts"));
                            //计算征期起始日期、征期终止日期
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //计算征期所属日期起、征期所属日期止
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth, YEAR_TYPE,
                                SKSSQ);
                        }
                        break;
                    case HALFYEAR_TYPE:

                        //按半年申报
                        czqrl = new Czqrl[HALFYEAR_TYPE];
                        for (int k = 0; k < HALFYEAR_TYPE; k++)
                        {
                            czqrl[k] = new Czqrl();
                            int zqMonth = Integer.parseInt( (String)
                                zqMonthVector.get(k));
                            Calendar zqCalendar = GregorianCalendar.getInstance();
                            int zqDate = Integer.parseInt( (String) zqlxMap[i].
                                get("zqksrq"));
                            zqCalendar.set(whnf, zqMonth, zqDate);
                            int tianShu = Integer.parseInt( (String) zqlxMap[i].
                                get("zqts"));
                            //计算征期起始日期、征期终止日期
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //计算征期所属日期起、征期所属日期止
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth,
                                HALFYEAR_TYPE, SKSSQ);
                        }
                        break;
                    case SEASON_TYPE:

                        //按季申报
                        czqrl = new Czqrl[SEASON_TYPE];
                        for (int k = 0; k < SEASON_TYPE; k++)
                        {
                            czqrl[k] = new Czqrl();
                            int zqMonth = Integer.parseInt( (String)
                                zqMonthVector.get(k));
                            Calendar zqCalendar = GregorianCalendar.getInstance();
                            int zqDate = Integer.parseInt( (String) zqlxMap[i].
                                get("zqksrq"));
                            zqCalendar.set(whnf, zqMonth, zqDate);
                            int tianShu = Integer.parseInt( (String) zqlxMap[i].
                                get("zqts"));
                            //计算征期起始日期、征期终止日期
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //计算征期所属日期起、征期所属日期止
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth,
                                SEASON_TYPE, SKSSQ);
                        }
                        break;
                    case MONTH_TYPE:

                        //按月申报
                        czqrl = new Czqrl[MONTH_TYPE];
                        for (int k = 0; k < MONTH_TYPE; k++)
                        {
                            czqrl[k] = new Czqrl();
                            int zqMonth = Integer.parseInt( (String)
                                zqMonthVector.get(k));
                            Calendar zqCalendar = GregorianCalendar.getInstance();
                            int zqDate = Integer.parseInt( (String) zqlxMap[i].
                                get("zqksrq"));
                            zqCalendar.set(whnf, zqMonth, zqDate);
                            int tianShu = Integer.parseInt( (String) zqlxMap[i].
                                get("zqts"));
                            //计算征期起始日期、征期终止日期
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //计算征期所属日期起、征期所属日期止
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth, MONTH_TYPE,
                                SKSSQ);
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException(
                            "不支持这样的申报方式");
                }
                //插入数据
                Timestamp timeNow = SfTimeUtil.getNowTimestamp();

                for (int n = 0; n < czqrl.length; n++)
                {
                    czqrl[n].setZqlxdm( (String) zqlxMap[i].get("zqlxdm"));
                    czqrl[n].setZqlxmc( (String) zqlxMap[i].get("zqlxmc"));
                    czqrl[n].setCjrq(timeNow);
                    czqrl[n].setLrrq(timeNow);
                    czqrl[n].setNd("" + whnf);
                    czqrl[n].setSwjgzzjgdm(vo.getUserData().getSsdwdm());
                    da.insert(czqrl[n]);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("生成征期日历失败！");
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }

        return null;
    }

    /**
     * 将Timestamp型转换为一个String型日期 （eg.20030611）
     * @param gCalendar 日历
     * @return String型日期 （eg.20030611）
     */
    private String calendarToString (Calendar gCalendar)
    {
        int month = gCalendar.get(Calendar.MONTH) + 1;
        int date = gCalendar.get(Calendar.DATE);
        String strMonth = "" + month;
        String strDate = "" + date;
        if (month < 10)
        {
            strMonth = "0" + strMonth;
        }
        if (date < 10)
        {
            strDate = "0" + strDate;
        }
        String strDay = "" + gCalendar.get(Calendar.YEAR) + strMonth + strDate;
        return strDay;
    }

    /**
     * 将Timestamp型转换为一个String型日期 （eg.20030611）
     * @param timestamp 日期时间
     * @return String型日期 （eg.20030611）
     */
    private String timestampToString (Timestamp timestamp)
    {
        Calendar gCalendar = GregorianCalendar.getInstance();
        gCalendar.setTime(timestamp);
        int year = gCalendar.get(Calendar.YEAR);
        int month = gCalendar.get(Calendar.MONTH) + 1;
        int date = gCalendar.get(Calendar.DATE);
        String strYear = "" + year;
        String strMonth = "" + month;
        String strDate = "" + date;
        if (month < 10)
        {
            strMonth = "0" + strMonth;
        }
        if (date < 10)
        {
            strDate = "0" + strDate;
        }
        String strDay = strYear + strMonth + strDate;
        return strDay;
    }

    /**
     * 计算征期起始日期、征期终止日期
     * @param czqrl 保存值对象
     * @param gCalendar 征期第一天
     * @param tianShu 正期天数
     * @param holiday 节假日数组
     * @return 保存值对象
     */
    private Czqrl computeZengQi (Czqrl czqrl, Calendar gCalendar, int tianShu,
                                 String[] holiday)
    {
        czqrl.setZqqsrq(TinyTools.calendar2Timestamp(gCalendar)); //征期起始日期
        if (tianShu == 31)
        {
            String tempStr = czqrl.getZqqsrq().toString();
            Timestamp aa = TinyTools.calendar2Timestamp(gCalendar);
            int tempInt = getMonthLastDate(TinyTools.getYear(aa) - 1900,
                                           TinyTools.getMonth(aa));
            gCalendar.set(TinyTools.getYear(aa), TinyTools.getMonth(aa),
                          tempInt);
            czqrl.setZqzzrq(TinyTools.calendar2Timestamp(gCalendar)); //征期终止日期
        }
        else
        {
            gCalendar.add(Calendar.DATE, tianShu - 1);
            while (true)
            {
                for (int i = 0; i < holiday.length; i++)
                {
                    if (calendarToString(gCalendar).equals(holiday[i]))
                    {
                        gCalendar.add(Calendar.DATE, 1);
                        i = -1;
                    }
                }
                break;
            }
            czqrl.setZqzzrq(TinyTools.calendar2Timestamp(gCalendar)); //征期终止日期
        }

        return czqrl;

    }

    /**
     * 计算征期所属日期起、征期所属日期止
     * @param czqrl 保存值对象
     * @param whnf 征期年份
     * @param zqMonth 征期月份
     * @param Type 征期方式（按年申报、按半年申报、按季申报、按月申报）
     * @param SKSSQ 所属期区分（本期或上期）
     * @return 保存值对象
     */
    private Czqrl computerSuoShuRiQi (Czqrl czqrl, int whnf, int zqMonth,
                                      int Type,
                                      String SKSSQ)
    {
        Calendar ssCalendar = GregorianCalendar.getInstance();
        switch (Type)
        {
            case YEAR_TYPE:

                //按年申报
                if (SKSSQ.equals(THIS_TERM))
                { //本期
                    ssCalendar.set(whnf, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //上期
                    ssCalendar.set(whnf - 1, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf - 1, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(THIS_TERM_TOTAL))//Modified by lufeng 2004-07-28
                { //本期累计
                    ssCalendar.set(whnf, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
                { //上期累计
                    ssCalendar.set(whnf - 1, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf - 1, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                break;
            case HALFYEAR_TYPE:

                //按半年申报
                if (SKSSQ.equals(THIS_TERM))
                { //本期
                    if (zqMonth >= 0 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 6, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //上期
                    if (zqMonth >= 0 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf - 1, 6, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf - 1, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                else if (SKSSQ.equals(THIS_TERM_TOTAL)) //Modified by lufeng 2004-07-28
                { //本期累计
                    if (zqMonth >= 0 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
                { //上期累计
                    if (zqMonth >= 0 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf - 1, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf - 1, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }

                break;
            case SEASON_TYPE:

                //按季申报
                if (SKSSQ.equals(THIS_TERM))
                { //本期
                    if (zqMonth >= 0 && zqMonth <= 2)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 3, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 6, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 9, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //上期
                    if (zqMonth >= 0 && zqMonth <= 2)
                    {
                        ssCalendar.set(whnf - 1, 9, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf - 1, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 3, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 6, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                else if (SKSSQ.equals(THIS_TERM_TOTAL))//Modified by lufeng 2004-07-28
                { //本期累计
                    if (zqMonth >= 0 && zqMonth <= 2)
                    {
                        ssCalendar.set(whnf, 0, 1);//从本年1月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);//从本年1月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 0, 1);//从本年1月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 0, 1);//从本年1月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
                { //上期累计
                    if (zqMonth >= 0 && zqMonth <= 2)
                    {
                        ssCalendar.set(whnf - 1, 0, 1);//从上年1月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf - 1, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);//从本年1月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 3, 1);//从本年4月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 6, 1);//从本年7月1号开始
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                break;
            case MONTH_TYPE:

                //按月申报
                if (SKSSQ.equals(THIS_TERM))
                { //本期
                    ssCalendar.set(whnf, zqMonth, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, zqMonth,
                                   getMonthLastDate(whnf, zqMonth));
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //上期
                    int whnfBak = whnf;
                    if (zqMonth == 0)
                    {
                        zqMonth = 11;
                        whnfBak = whnfBak - 1;
                    }
                    else
                    {
                        zqMonth = zqMonth - 1;
                    }
                    ssCalendar.set(whnfBak, zqMonth, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnfBak, zqMonth,
                                   getMonthLastDate(whnf, zqMonth));
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(THIS_TERM_TOTAL))//Modified by lufeng 2004-07-28
                { //本期累计
                    ssCalendar.set(whnf, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, zqMonth,
                                   getMonthLastDate(whnf, zqMonth));
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
                { //上期累计
                    int whnfBak = whnf;
                    if (zqMonth == 0)
                    {
                        //zqMonth = 11;
                        whnfBak = whnfBak - 1;
                        ssCalendar.set(whnfBak, zqMonth, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                        ssCalendar.set(whnfBak, 11,
                                       getMonthLastDate(whnfBak, 11));
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));

                    }
                    else
                    {
                        zqMonth = zqMonth - 1;
                        ssCalendar.set(whnfBak, zqMonth, 1);
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                        System.out.println("whnf=========="+whnf);
                        ssCalendar.set(whnf, zqMonth,
                                       getMonthLastDate(whnf, zqMonth));
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                    }

                }
                break;
        }
        return czqrl;
    }

    /**
     * 得到指定年月的最后一天
     * @param whnf 年
     * @param month 月
     * @return 本月的最后一天
     */
    private int getMonthLastDate (int whnf, int month)
    {
        Calendar gCalendar = GregorianCalendar.getInstance();
        gCalendar.set(whnf, month, 3);
        return gCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将日期字串(format:yyyyMMdd eg. 20030611)转化为Timestamp型日期
     * @param strDate 时间
     * @return Timestamp型日期
     */
    private Timestamp stringToTimestamp (String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
        int date = Integer.parseInt(strDate.substring(6, 8));

        Calendar gCalendar = GregorianCalendar.getInstance();
        gCalendar.set(gCalendar.YEAR, year);
        gCalendar.set(gCalendar.MONTH, month);
        gCalendar.set(gCalendar.DATE, date);
        gCalendar.set(gCalendar.HOUR_OF_DAY, 0);
        gCalendar.set(gCalendar.MINUTE, 0);
        gCalendar.set(gCalendar.SECOND, 0);
        gCalendar.set(gCalendar.MILLISECOND, 0);
        Timestamp t = new Timestamp(gCalendar.getTime().getTime());
        return t;
    }
}
