package com.lscdz.qysds.common.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class Skssrq {

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
     * 计算其它类型的税款所属日期
     * @param curDate 当前日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public static Map quartorSkssrq(Date curDate)
    {
    	GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);

		int jd = month / 3;
		if (jd == 0) {
			year--;
			jd = 4;
		}
		String nd = new Integer(year).toString();
		Timestamp skssksrqDate = new Timestamp(
				new GregorianCalendar(year, 0, 1).getTime().getTime());
		Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
				(jd - 1) * 3 + 2, new GregorianCalendar(year, (jd - 1) * 3 + 2,
						1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
				.getTime());
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
}
