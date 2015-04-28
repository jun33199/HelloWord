package com.lscdz.qysds.common.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class Skssrq {

	 /**
     * ˰��������ʼ���ڳ���
     */
    public static final String SKSSKSRQ = "SKSSKSRQ";

    /**
     * ˰�������������ڳ���
     */
    public static final String SKSSJSRQ = "SKSSJSRQ";

    /**
     * ˰������������ȳ���
     */
    public static final String SKSSRQ_ND = "ND";
	/**
     * �����걨���͵�˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ��һ��ĵ�һ��
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ��һ������һ��
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� Strng
     */
    public static Map yearSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        calendar.add(calendar.YEAR, -1);  // ���һ
        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();  // �����

        Map retMap = new HashMap(2);

        // ��һ��ĵ�һ��
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, 0, 1).getTime().getTime());
        // ��һ������һ��
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, 11, 31).getTime().getTime());

        retMap.put(SKSSKSRQ, skssksrqDate);  // ��ʼ����
        retMap.put(SKSSJSRQ, skssjsrqDate);  // ��������
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }
    
    /**
     * �����������͵�˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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
     * ȡ�ô����������ڵ�ǰһ������
     * @param curDate ����
     * @return String ����
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
