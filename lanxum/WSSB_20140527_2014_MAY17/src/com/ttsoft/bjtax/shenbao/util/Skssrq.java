package com.ttsoft.bjtax.shenbao.util;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.math.BigDecimal;

import com.ttsoft.common.util.Debug;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.framework.exception.*;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.shenbao.proxy.TrancProxy;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.dj.model.*;


/**
 * <p>Title:Title: 北京地税综合管理系统  框架模块</p>
 * <p>Description: 计算税款所属日期</p>
 * <p>通过传入一个日期,获得税款所属日期<p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT </p>
 * @author zhiyong He
 * @version 1.0
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
     * 计算年报类型的税款所属日期
     * @param curDate 当前日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 上一年的第一天
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 上一年的最后一天
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 Strng
     */
    public static Map yearSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        calendar.add(calendar.YEAR, -1);  // 年减一
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();  // 求年度

        Map retMap = new HashMap(2);

        // 上一年的第一天
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, 0, 1).getTime().getTime());
        // 上一年的最后一天
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, 11, 31).getTime().getTime());

        retMap.put(SKSSKSRQ, skssksrqDate);  // 开始日期
        retMap.put(SKSSJSRQ, skssjsrqDate);  // 结束日期
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
    public static Map commonQuarter(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        // 上一季度
        int jd = month / 3;

        if (month<3)
        {
            year --;
            jd = 4;
        }

        String nd = String.valueOf(year);

        // 上季度第一天
        Timestamp skssksrqDate = getFirstDayOfQuarter(year, jd);
        // 上季度最后一天
        Timestamp skssjsrqDate = getLastDayOfQuarter(year, jd);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }

    
    /**
     * 计算季报再就业减免申报的税款所属日期
     * @param curDate 当前日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map zjyjmsbQuarter(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        int jd =0;

        if (month==3)
        {
            jd = 1;
        }
        if (month==5)
        {
            year --;
            jd = 4;
        }
        if (month==6)
        {
            jd = 2;
        }
        if (month==9)
        {
            jd = 3;
        }

        String nd = String.valueOf(year);

        // 上季度第一天
        Timestamp skssksrqDate = getFirstDayOfQuarter(year, jd);
        // 上季度最后一天
        Timestamp skssjsrqDate = getLastDayOfQuarter(year, jd);

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
    public static String preQuarter(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);
        int month = calendar.get(calendar.MONTH);
        int jd = month / 3;
        if (jd == 0)
        {
            jd = 4;
        }
        return new Integer(jd).toString();
    }

    /**
     * 计算月报类型的税款所属日期
     * @param curDate 当前日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map monthSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        calendar.add(calendar.MONTH, -1);
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int month = calendar.get(calendar.MONTH);

        int maxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

        // 上个月第一天
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, month, 1).getTime().getTime());

        // 上个月最后一天
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, month, maxDay).getTime().getTime());
        calendar.add(calendar.MONTH, 1);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }

    /**
     * 半年申报的税款所属日期
     * 当前日期为上半年中的日期时，税款所属为当前年的1月1日至6月31日；
     * 当前日期为下半年中的日期时，税款所属为当前年的7月1日至12月31日；
     * @param curDate 当前时间
     * @return Map
     */
    public static Map halfYearSkssrq(Date curDate)
    {
        Map ret = new HashMap(2);
        int month = curDate.getMonth();
        if (month <6)
        {
            Calendar time1 = new GregorianCalendar(curDate.getYear() + 1900, 0, 1);
            Calendar time2 = new GregorianCalendar(curDate.getYear() + 1900, 5, 30);

            ret.put(SKSSKSRQ, new Timestamp(time1.getTime().getTime()));
            ret.put(SKSSJSRQ, new Timestamp(time2.getTime().getTime()));
        }
        else
        {
            Calendar time1 = new GregorianCalendar(curDate.getYear() + 1900, 6, 1);
            Calendar time2 = new GregorianCalendar(curDate.getYear() + 1900, 11, 31);

            ret.put(SKSSKSRQ, new Timestamp(time1.getTime().getTime()));
            ret.put(SKSSJSRQ, new Timestamp(time2.getTime().getTime()));
        }
        return ret;
    }

    /**
     * 计算其它类型的税款所属日期
     * @param curDate 当前日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map otherSkssrq(Date curDate)
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

    /**
     * 得到税款所属时期
     * @param jsjdm 计算机代码
     * @param szsmdm 税种税目代码
     * @param djzclx 登记注册类型代码
     * @param sklxdm 税款类型代码
     * @param zqlxdm 征期类型代码
     * @param curDate 当前时间
     * @param sjse 实缴金额
     * @param kssl 课税数量
     * @param jsje 计税金额
     * @return Map 税款所属开始 结束时间
     * @throws BaseException
     */
    public static Map getSksssq(String jsjdm,
                                String szsmdm,
                                String djzclx,
                                String sklxdm,
                                String zqlxdm,
                                Date curDate,
                                BigDecimal sjse,
                                BigDecimal kssl,
                                BigDecimal jsje)
    {
        if(szsmdm == null || sklxdm == null || curDate == null)
        {
            return new HashMap();
        }
        if(CodeConstant.SKLXDM_ZCJK.equals(sklxdm))
        {
            try
            {
                if((SzsmdmConstant.TDSYF + SzsmdmConstant.FCS + SzsmdmConstant.CCSYF)
                   .indexOf(szsmdm.substring(0,2)) < 0)
                {
                    // 车房土不查数据库
                    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
                    String rq = simpleDataFormat.format(curDate); //当前年月:YYYYMMdd
                    Map data = (Map)TrancProxy.getZqzzrq(szsmdm, djzclx, rq);
                    //如果查询数据库得到税款所属日期(税款所属结束日期不为空)，则返回查询结果
                    if(data != null && data.get(SKSSKSRQ) != null && data.get(SKSSJSRQ) != null)
                        return data;
                }
                //如果数据库中没有对应结果，则计算税款所属日期
                return doZcsp(jsjdm, szsmdm, zqlxdm, curDate, sjse, kssl, jsje);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return new HashMap();
            }
        }
        else if (CodeConstant.SKLXDM_SDJJ.equals(sklxdm))
        {
            return monthSkssrq(curDate);
        }

        return new HashMap();
    }

    private static String getNwzfldm(String jsjdm)
    {
        try
        {
            com.ttsoft.bjtax.dj.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.dj.proxy.ServiceProxy();

            SWDJJBSJ djsj = (SWDJJBSJ)proxy.getDjInfo(jsjdm).get("JBSJ");

            String djzclxdm = djsj.getDjzclxdm();

            return TrancProxy.getDjzclx(djzclxdm).getNwzfldm();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            return null;
        }
    }

    //正常税票
    private static Map doZcsp(String jsjdm,
                              String szsmdm,
                              String zqlxdm,
                              Date curDate,
                              BigDecimal sjse,
                              BigDecimal kssl,
                              BigDecimal jsje)
    {
        // 车房土
        if((SzsmdmConstant.TDSYF + SzsmdmConstant.FCS + SzsmdmConstant.CCSYF)
           .indexOf(szsmdm.substring(0,2)) > 0)
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            int jncs = 0;

            try
            {
                jncs = proxy.getJncs(jsjdm, szsmdm.substring(0, 2));
                /**
                 * 城镇土地使用税2007年10月征期征收限制
                 * 因2007年上半年未征收土地税,下半年征期须征收全年税款
                 * 故特作如下控制:
                 * 2007年城镇土地使用税缴纳次数统一为全年征收,即缴纳次数为0
                 * 2007.10征期过后,按税费认定缴纳次数征收
                 * 
                 * 王志民 2007-8-15日备注
                 */
                Date today=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                
                if(szsmdm.substring(0, 2).equals("15")&&sdf.format(today).substring(0,4).equals("2007")){
                	jncs=0;
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            Date now = new Date();
            if (jncs > 0)
            {
                return halfYearSkssrq(now);
            }
            else
            {
                return curYearSkssrq(now);
            }

//            return calCFT(jsjdm, szsmdm, curDate, sjse, kssl, jsje);
        }

        if (SzsmdmConstant.YYS.equals(szsmdm.substring(0, 2)))
        {
            String nwzfldm = getNwzfldm(jsjdm);

            if (CodeConstant.DJZCLXDM_NWZFLDM_GAT.equals(nwzfldm) ||
                CodeConstant.DJZCLXDM_NWZFLDM_WZ.equals(nwzfldm))
            {
                // 外资的营业税是一般季报
                return commonQuarter(curDate);
            }
        }

        if (SzsmdmConstant.JRY.equals(szsmdm.substring(0, 3)))
        {
            String nwzfldm = getNwzfldm(jsjdm);

            if (CodeConstant.DJZCLXDM_NWZFLDM_GAT.equals(nwzfldm) ||
                CodeConstant.DJZCLXDM_NWZFLDM_WZ.equals(nwzfldm))
            {
                // 内资的金融业是一般季报
                return commonQuarter(curDate);
            }
        }

        if (SzsmdmConstant.GTGSH.equals(szsmdm.substring(0, 4)))
        {
            // 个体工商户
            String zsfsdm = getZsfsdm(jsjdm);
            if (CodeConstant.ZSFSDM_CZZS.equals(zsfsdm))
            {
                // 查账征收是累计季报
                return accumulatedQuarter(curDate);
            }
            else if (CodeConstant.ZSFSDM_HDZS.equals(zsfsdm))
            {
                // 核定征收是一般季报
                return commonQuarter(curDate);
            }
        }

        if(CodeConstant.ZQLXDM_QUARTER.equals(zqlxdm)) //季报
        {
            if(SzsmdmConstant.QYSDS.equals(szsmdm.substring(0, 2)))
            {
                // 企业所得税季报是累计季报
                return accumulatedQuarter(curDate);
            }
            else
            {
                return commonQuarter(curDate);
            }
        }
        else if(CodeConstant.ZQLXDM_YEAR.equals(zqlxdm)) //年报
        {
            return yearSkssrq(curDate);
        }
        else if(CodeConstant.ZQLXDM_MONTH.equals(zqlxdm)) //月报
        {
            return monthSkssrq(curDate);
        }
        else
        {
            return new HashMap();
        }
    }

    private static String getZsfsdm(String jsjdm)
    {
        try
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            return proxy.getZsfsInfo(jsjdm, new Date()).getZsfsdm();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    private static Map accumulatedQuarter(Date curDate)
    {
        Map sksssqMap = new HashMap();

        if (curDate.getMonth() < 3)
        {
            // 第一季度是年报
            return yearSkssrq(curDate);
        }

        //开始日期,本年的1月1号
        sksssqMap.put(SKSSKSRQ, getTimestampMinDay(curDate.getYear() + 1900,0));
        //结束日期:当前日期的上个月的最后一天
        sksssqMap.put(SKSSJSRQ, getTimestampMaxDay(curDate.getYear() + 1900, curDate.getMonth() - 1));

        return sksssqMap;
    }

    /**
     * 计算车房土的税款所属日期
     * @param jsjdm 计算机代码
     * @param szsmdm 税种税目代码
     * @param curDate 当前时间
     * @param sjse 实缴金额
     * @param kssl 课税数量
     * @param jsje 计税金额
     * @return Map
     */
    private static Map calCFT(String jsjdm,
                              String szsmdm,
                              Date curDate,
                              BigDecimal sjse,
                              BigDecimal kssl,
                              BigDecimal jsje)
    {
        try
        {
            Map sksssqMap = new HashMap();

            BigDecimal ys = new BigDecimal("12");  //12个月

            //计算车、房、土税款所属时期  说明：不足一个月的，按一个月计算（暂定）
            if(SzsmdmConstant.CCSYF.indexOf(szsmdm.substring(0, 2)) > 0) //车船税款
            {
                //用实缴/单位税额/课税数量×12＝每辆车交了多少个月的税款
                //首先判断是按照课税数量还是计税金额进行收税
                //如果税目代码对应的asljbs字段值为2，则按照课税数量进行计算，否则按照计税金额计算
                Szsm smData = new Szsm();
                smData = (Szsm)TrancProxy.getSzsm(szsmdm);  //取得税种税目对象

                String countKey = smData.getAsljbs(); //计算标识
                BigDecimal dwse = smData.getSl(); //单位税额
                //获取月数:  每辆车交了多少个月的税款
                BigDecimal monCount = new BigDecimal("0");
                if(countKey != null && countKey.equals("2")) //按课税数量
                {
                    monCount = sjse.divide(dwse, 2).divide(kssl, 2)
                        .multiply(ys).setScale(0, BigDecimal.ROUND_UP);
                    sksssqMap = (Map)getSksq(monCount, curDate); //获得税款所属时期
                }
                else //按计税金额
                {
                    monCount = sjse.divide(dwse, 2).divide(jsje, 2)
                        .multiply(ys).setScale(0, BigDecimal.ROUND_UP);
                    sksssqMap = (Map)getSksq(monCount, curDate); //获得税款所属时期
                }
                return sksssqMap;
            }
            else if(SzsmdmConstant.FCS.indexOf(szsmdm.substring(0, 2)) > 0 ||
                    SzsmdmConstant.TDSYF.indexOf(szsmdm.substring(0, 2)) > 0) //房产税款或者土地
            {
                //取税费接口的年应纳税额，用实缴/年应纳税额（从税费接口取）*12，判断计算的结果和当前日期：
                Cftsyhd tempObj = getCft(jsjdm, curDate, szsmdm);
                BigDecimal nynse = tempObj.getJsje(); //获得年应纳税额
                //获得计算所得月数
                BigDecimal months =
                    sjse.divide(nynse, 2).multiply(ys).setScale(0, BigDecimal.ROUND_UP);
                sksssqMap = (Map)getSksq(months, curDate); //获得税款所属时期
                return sksssqMap;
            }

            return sksssqMap;
        }
        catch(Exception ex)
        {
            return curYearSkssrq(curDate);
        }
    }

    private static Map curYearSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();  // 求年度

        Map retMap = new HashMap(2);

        // 当年的第一天
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, 0, 1).getTime().getTime());
        // 当年的最后一天
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, 11, 31).getTime().getTime());

        retMap.put(SKSSKSRQ, skssksrqDate);  // 开始日期
        retMap.put(SKSSJSRQ, skssjsrqDate);  // 结束日期
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }

    public static Cftsyhd getCft(String jsjdm, Date curDate, String szsmdm)
        throws BaseException
    {
        Cftsyhd obj = new Cftsyhd();
        try
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List tempList = (List)sfServiceProxy.getCftsyhdInfo(jsjdm, curDate);
            if(tempList.size() == 0)
                throw new Exception("没有核定该计算机代码("+jsjdm+")的定额数据！");

            for(int i = 0; i < tempList.size(); i++)
            {
                Cftsyhd tempObj = (Cftsyhd)tempList.get(i);
                if(tempObj.getSzsmdm().equals(szsmdm))
                {
                    BigDecimal nynse = tempObj.getJsje(); //获得年应纳税额
                    if(nynse.equals("0"))
                        throw new Exception("核定年应纳税额数据为0！");
                    return tempObj;
                }
            }

            //如果没有返回数据，则说明不存在该税目对应的核定数据
            throw new Exception("没有该税种税目（" + szsmdm + "）的核定数据！");
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "没有获得年应纳税额！");
        }
    }

    /**
     * 得到指定年和月对应的最大的天数的Timestamp日期格式
     * @param year 指定的年
     * @param month 指定的月
     * @return 返回指定的日期
     */
    private static Timestamp getTimestampMaxDay(final int year ,final int month)
    {
        Calendar calendar = new GregorianCalendar(year,month,1);
        int maxDay =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return new Timestamp((new GregorianCalendar(year,month,maxDay)).getTime().getTime());
    }

    /**
     * 得到指定年和月对应的第1天的Timestamp日期格式
     * @param year 指定的年
     * @param month 指定的月
     * @return 返回指定的日期
     */
    private static Timestamp getTimestampMinDay(final int year, final int month)
    {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return new Timestamp( calendar.getTime().getTime());
    }

    /**
     * 计算税款所属时期
     * @param ys 计算出的月数
     * @param curDate 当前日期
     * @return map
     */
    private static Map getSksq(BigDecimal ys, Date curDate)
    {
        HashMap dataMap = new HashMap();
        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        BigDecimal def_6 = new BigDecimal("6");
        BigDecimal def_12 = new BigDecimal("12");
        //分析当前时间
        int curYear    = getYear(curDate);
        int curMonth   = getMonth(curDate);
        int curQuarter = getQuarter(curDate);
        int curDay     = getDay(curDate);

        if(curMonth <= 6)  //前半年
        {
            if(ys.equals(def_6)) //如果计算结果为6
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:6月的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 5);
            }
            else if(ys.equals(def_12))
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear, 0);
                //结束日期:当12月的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if(ys.compareTo(def_6) == -1) //ys小于6，返回-1
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear,0);
                //结束日期:计算结果月的最后一天
                skssjsrq = getTimestampMaxDay(curYear,Integer.parseInt(ys.toString())-1);
            }
            else if(ys.compareTo(def_12) == -1) //ys小于12，返回-1
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear,0);
                //结束日期:计算结果月的最后一天
                skssjsrq = getTimestampMaxDay(curYear,Integer.parseInt(ys.toString())-1);
            }
            else if(ys.compareTo(def_12) == 1)  //大于12
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear,0);
                //结束日期:本年的最后一天
                skssjsrq = getTimestampMaxDay(curYear,11);
            }
        }
        else if(curMonth > 6)  //后半年
        {
            if(ys.equals(def_6)) //如果计算结果为6
            {
                //开始日期,本年的7月1号
                skssksrq = getTimestampMinDay(curYear, 6);
                //结束日期:本年的最后一天
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if(ys.compareTo(def_6) == -1) //ys小于6，返回-1
            {
                //开始日期,本年的7月1号
                skssksrq = getTimestampMinDay(curYear,6);
                //结束日期:6+计算结果月的最后一天
                skssjsrq = getTimestampMaxDay(curYear,6+Integer.parseInt(ys.toString()));
            }
            else
            {
                //开始日期,本年的1月1号
                skssksrq = getTimestampMinDay(curYear,0);
                //结束日期:本年的最后一天
                skssjsrq = getTimestampMaxDay(curYear,11);
            }
        }
        //填充返回结果
        dataMap.put(SKSSKSRQ, skssksrq);
        dataMap.put(SKSSJSRQ, skssjsrq);
        return dataMap;
    }

    /**
     * 得到给定日期的季度 为int型
     * @param date 给定的日期
     * @return int 季度值(1,2,3,4)
     */
    private static int getQuarter(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return(calendar.get(Calendar.MONTH) / 3 + 1);
    }

    /**
     * 得到给定日期的年份 为int型
     * @param date 给定的日期
     * @return int 年份值
     */
    private static int getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return(calendar.get(Calendar.YEAR));
    }

    /**
     * 得到给定日期的月份 为int型
     * @param date 给定的日期
     * @return int 月份值(0开始)
     */
    private static int getMonth(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return(calendar.get(Calendar.MONTH));
    }

    /**
     * 得到给定日期的本月的几号 为int型
     * @param date 给定的日期
     * @return int 天号(1开始)
     */
    private static int getDay(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return(calendar.get(Calendar.DATE));
    }

    private static Timestamp getFirstDayOfQuarter(int year, int quarter)
    {
        Calendar c = null; ;
        switch(quarter)
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

    private static Timestamp getLastDayOfQuarter(int year, int quarter)
    {
        Calendar c = new GregorianCalendar();
        switch(quarter)
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