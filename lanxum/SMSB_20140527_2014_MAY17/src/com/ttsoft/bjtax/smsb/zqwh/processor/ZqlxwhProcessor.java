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

import com.ekernel.db.or.ORPrimaryKey;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Czqrl;
import com.ttsoft.bjtax.shenbao.model.domain.Jqsz; //登记接口结果类
import com.ttsoft.bjtax.shenbao.model.domain.Zqlx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqlxwhForm; //申报内部工具类
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TinyTools;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期类型维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqlxwhProcessor
    implements Processor
{
    /**
     * 按年申报
     */
    private final int YEAR_TYPE = 1; //按年申报
    /**
     * 按半年申报
     */
    private final int HALFYEAR_TYPE = 2; //按半年申报
    /**
     * 按季申报
     */
    private final int SEASON_TYPE = 4; //按季申报
    /**
     * 按月申报
     */
    private final int MONTH_TYPE = 12; //按月申报
    /**
     * 本期
     */
    private final String THIS_TERM = "1"; //本期
    /**
     * 上期
     */
    private final String PREVIOUS_TERM = "0"; //上期
    /**
     * 本期累计
     */
    private final String THIS_TERM_TOTAL = "3"; //本期累计
    /**
     * 上期累计
     */
    private final String PREVIOUS_TERM_TOTAL = "2"; //上期累计

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
            case CodeConstant.SMSB_QUERYACTION: //查询
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_UPDATEACTION:
                result = doUpdate(vo);
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
     * 获得前台默认显示控制的ActionForm
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 获得查询结果的ActionForm
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        ZqlxwhForm zf = (ZqlxwhForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            //查询明细数据
            //明细查询条件封装
            Vector vMx = new Vector();
            vMx.add(" 1=1 ORDER BY ZQLXDM ");
            //明细查询数据结果
            List mxDataList = da.query(Zqlx.class, vMx);
            if (mxDataList == null || mxDataList.size() == 0)
            {
                throw new ApplicationException("没有找到指定的记录！");
            }
            //明细OrMapping值对象的每个参数
            String mxNames[] =
                               {
                               "zqlxdm", "zqlxmc", "zqzq", "skssq", "zqksrq",
                               "zqts", "lrr", "lrrq",
                               "tyrq", "jglxdm"};
            //将OrMapping值对象转成Map，以便ActionForm能够使用
            List mxMapDataList = new ArrayList();
            for (int i = 0; i < mxDataList.size(); i++)
            {
                //获得此条明细值
                Zqlx orMx = (Zqlx) mxDataList.get(i);
                Map map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                //将税款所属期的代码转换为名称
                if ( ( (String) map.get("skssq")).equals("0"))
                {
                    map.put("skssq", "上期");
                }
                else if ( ( (String) map.get("skssq")).equals("1"))
                {
                    map.put("skssq", "本期");
                }
                else if ( ( (String) map.get("skssq")).equals("2"))
                {
                    map.put("skssq", "上期累计");
                }
                else if ( ( (String) map.get("skssq")).equals("3"))
                {
                    map.put("skssq", "本期累计");
                }
                //将间隔类型的代码转换为名称
                if ( ( (String) map.get("jglxdm")).equals("0"))
                {
                    map.put("jglxdm", "次");
                }
                else if ( ( (String) map.get("jglxdm")).equals("1"))
                {
                    map.put("jglxdm", "年");
                }
                else if ( ( (String) map.get("jglxdm")).equals("2"))
                {
                    map.put("jglxdm", "半年");
                }
                else if ( ( (String) map.get("jglxdm")).equals("4"))
                {
                    map.put("jglxdm", "季");
                }
                else if ( ( (String) map.get("jglxdm")).equals("12"))
                {
                    map.put("jglxdm", "月");
                }
                mxMapDataList.add(map);
            }
            zf.setDataList(mxMapDataList);
            return zf;
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
     * 更新
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        ZqlxwhForm zf = (ZqlxwhForm) vo.getData();
        try
        {
            String[] tyCheckbox = zf.getTyCheckbox();
            Zqlx z = null;
            if (tyCheckbox != null)
            {
                conn = SfDBResource.getConnection();
                SfDBAccess da = new SfDBAccess(conn);
                for (int i = 0; i < tyCheckbox.length; i++)
                {
                    z = new Zqlx();
                    ORPrimaryKey zqlxKey = new ORPrimaryKey(new Zqlx(),
                        tyCheckbox[i]);
                    z = (Zqlx) da.query(zqlxKey);
                    //当前日期
                    Calendar gCalendar = GregorianCalendar.getInstance();
                    z.setTyrq(calendarToTimestamp(gCalendar));
                    //更新数据
                    da.update(z);
                }
            }
            //更新征期类型代码表
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

        return zf;
    }

    /**
     * 保存
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        try
        {
            ZqlxwhForm zf = (ZqlxwhForm) vo.getData();
            UserData userData = vo.getUserData();
            //得到明细数据集
            List mxMapData = zf.getDataList();
            if (mxMapData == null)
            {
                return zf;
            }
            //存贮明细数据值对象
            List mxData = new ArrayList();
            //录入人
            String strLrr = userData.getYhid();
            //当前日期
            Calendar gCalendar = GregorianCalendar.getInstance();
            String strDate = calendarToString(gCalendar);

            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Vector vMx = new Vector();
            List mxDataList = da.query(Zqlx.class, vMx);

            String strAlreadyExistMessage = "征期类型代码";
            String strAlreadyExistMessage_bak = "征期类型代码";

            for (int i = 0; i < mxMapData.size(); i++)
            {
                Map map = (Map) mxMapData.get(i);
                //添加一些信息
                map.put("lrr", strLrr);
                map.put("lrrq", strDate);
                Zqlx orMx = new Zqlx();
                //将数据传递给明细值对象
                BeanUtil.populate(orMx, map);
                boolean alreadyExist = false;
                for (int j = 0; j < mxDataList.size(); j++)
                {
                    Zqlx checker = (Zqlx) mxDataList.get(j);
                    if (orMx.getZqlxdm().equals(checker.getZqlxdm()))
                    {
                        alreadyExist = true;
                        strAlreadyExistMessage = strAlreadyExistMessage
                                                 + orMx.getZqlxdm() +
                                                 ",";
                        break;
                    }
                }
                if (alreadyExist == false)
                {
                    mxData.add(orMx);
                }
            }
            try
            {
                 //插入明细表数据
                da.insert(mxData);
            }
            catch (BaseException bex)
            {
                bex.printStackTrace();
                throw new ApplicationException("保存失败！");
            }
            if (!strAlreadyExistMessage.equals(strAlreadyExistMessage_bak))
            {
                strAlreadyExistMessage = strAlreadyExistMessage.substring(0,
                    strAlreadyExistMessage.length() - 1) + "已经在数据库中存在，请重新输入。";
                 //向前台报message
                throw new ApplicationException(strAlreadyExistMessage);

            }
            //更新征期类型代码表
            return zf;
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
     * 生成征期日历
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 空
     * @throws BaseException
     */
    private Object doCreateCalendar (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        try
        {
            ZqlxwhForm zf = (ZqlxwhForm) vo.getData();
            int whnf = Integer.parseInt(zf.getWhnf()); //所维护年份
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
            throw new ApplicationException("生成征期日历失败");
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
     * @param gCalendar 日期
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
     * @param timestamp 日期
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
     * 将Calendar转换为一个Timestamp型日期
     * @param gCalendar 日期
     * @return Timestamp型日期
     */
    private Timestamp calendarToTimestamp (Calendar gCalendar)
    {
        int year = gCalendar.get(Calendar.YEAR) - 1900;
        int month = gCalendar.get(Calendar.MONTH);
        int date = gCalendar.get(Calendar.DATE);

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
        czqrl.setZqqsrq(calendarToTimestamp(gCalendar)); //征期起始日期
        if (tianShu == 31)
        {
            String tempStr = czqrl.getZqqsrq().toString();
            Timestamp aa = calendarToTimestamp(gCalendar);
            int tempInt = getMonthLastDate(TinyTools.getYear(aa) - 1900,
                                           TinyTools.getMonth(aa));
            gCalendar.set(TinyTools.getYear(aa), TinyTools.getMonth(aa),
                          tempInt);
            czqrl.setZqzzrq(calendarToTimestamp(gCalendar)); //征期终止日期
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
            czqrl.setZqzzrq(calendarToTimestamp(gCalendar)); //征期终止日期
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
                    czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                    ssCalendar.set(whnf, 11, 31);
                    czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //上期
                    ssCalendar.set(whnf - 1, 0, 1);
                    czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                    ssCalendar.set(whnf - 1, 11, 31);
                    czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                }
//                else if (SKSSQ.equals(THIS_TERM_TOTAL))
//                { //本期累计
//                    ssCalendar.set(whnf, 0, 1);
//                    czqrl.setZqssrqq(Timestamp.valueOf(""));
//                    ssCalendar.set(whnf, 11, 31);
//                    czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
//                }
//                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
//                { //上期累计
//                    ssCalendar.set(whnf - 1, 0, 1);
//                    czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
//                    ssCalendar.set(whnf - 1, 11, 31);
//                    czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
//                }
                break;
            case HALFYEAR_TYPE:

                //按半年申报
                if (SKSSQ.equals(THIS_TERM))
                { //本期
                    if (zqMonth >= 0 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 6, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 11, 31);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //上期
                    if (zqMonth >= 0 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf - 1, 6, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf - 1, 11, 31);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
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
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 3, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 6, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 9, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 11, 31);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //上期
                    if (zqMonth >= 0 && zqMonth <= 2)
                    {
                        ssCalendar.set(whnf - 1, 9, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf - 1, 11, 31);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 3, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 6, 1);
                        czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
                    }
                }
                break;
            case MONTH_TYPE:

                //按月申报
                if (SKSSQ.equals(THIS_TERM))
                { //本期
                    ssCalendar.set(whnf, zqMonth, 1);
                    czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                    ssCalendar.set(whnf, zqMonth,
                                   getMonthLastDate(whnf, zqMonth));
                    czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
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
                    czqrl.setZqssrqq(calendarToTimestamp(ssCalendar));
                    ssCalendar.set(whnfBak, zqMonth,
                                   getMonthLastDate(whnf, zqMonth));
                    czqrl.setZqssrqz(calendarToTimestamp(ssCalendar));
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

}
