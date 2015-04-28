/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title:Title: ������˰�ۺϹ���ϵͳ  ���ģ��</p>
 * <p>Description: ����˰����������</p>
 * <p>ͨ������һ������,���˰����������<p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class Skssrq
{
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
     * ��ҵ����˰
     */
    private static final String SZDM_QYSDS = "30";

    /**
     * ��������˰()
     */
    private static final String SZDM_GRSDS = "0512";

    /**
     * ӡ��˰
     */
    private static final String SZDM_YHS = "16";

    /**
     * ����˰
     */
    private static final String SZDM_FCS = ",12,89,";

    /**
     * ����ʹ�÷�
     */
    private static final String SZDM_CCSYF = ",11,88,";

    /**
     * ����ʹ�÷�
     */
    private static final String SZDM_TDSYF = ",15,76,";

    /**
     * ����ʹ�÷�
     */

    public static final String SZDM_CFT = ",12,89,11,88,09,15,76,";

    /**
     * ����˰Ʊ˰�����͵���λ
     */
    private static final String SKLXDM_ZCSP = "1";

    /**
     * ����Ƿ˰˰�����͵���λ
     */
    private static final String SKLXDM_BJQS = "4";

    /**
     * �������ʹ���:����
     */
    private static final String ZQLXDM_JB = "4";

    /**
     * �������ʹ���:�걨
     */
    private static final String ZQLXDM_NB = "1";

    /**
     * �������ʹ���:�±�
     */
    private static final String ZQLXDM_YB = "12";

    /**
     * �������ʹ���:����
     */
    private static final String ZQLXDM_SY = "2";

    /**
     * ���ڸ�ʽ
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * �����걨���͵�˰����������
     * @param curDate ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� Strng
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
     * ���㼾�����͵�˰����������
     * @param curDate ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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
     * ȡ�ô����������ڵ�ǰһ������
     * @param curDate ����
     * @return String ����
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
     * ȡ�ô����������ڵĵ�ǰ����
     * @param curDate ����
     * @return String ����
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
     * �����±����͵�˰����������
     * @param curDate ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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
     * �����������͵�˰����������
     * @param curDate ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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
     * �õ�˰������ʱ��
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param sklxdm ˰�����ʹ���
     * @param zqlxdm �������ʹ���
     * @param curDate ��ǰʱ��
     * @param sjje ʵ�ɽ��
     * @param kssl ��˰����
     * @param jsje ��˰���
     * @param  ynsjeMap �ü���������Ӧ�����е���Ӧ��˰��
     * @return Map ˰��������ʼ ����ʱ��
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
     * �õ�˰������ʱ��
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param sklxdm ˰�����ʹ���
     * @param zqlxdm �������ʹ���
     * @param curDate ��ǰʱ��
     * @param sjje ʵ�ɽ��
     * @param kssl ��˰����
     * @param jsje ��˰���
     * @param  ynsje �ü���������Ӧ�����е���Ӧ��˰��
     * @return Map ˰��������ʼ ����ʱ��
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
            //���ɶ��ڶ�������
            Map ynsjeMap = new HashMap();
            Dqdedlmx1 temp = new Dqdedlmx1();
            //������Ӧ��˰��
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
     * �õ�˰������ʱ��
     * ����˰Ʊ
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param zqlxdm �������ʹ���
     * @param curDate ��ǰʱ��
     * @param sjse ʵ�ɽ��
     * @param kssl ��˰����
     * @param jsje ��˰���
     * @param  ynsjeMap �ü���������Ӧ�����е���Ӧ��˰��
     * @return Map ˰��������ʼ ����ʱ��
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
        //������ǰʱ��
        int curYear = TinyTools.getYear(curDate);
        int curMonth = TinyTools.getMonth(curDate); //0--11������1�£�12��
        int curQuarter = TinyTools.getQuarter(curDate);
        int curDay = TinyTools.getDay(curDate);
        Calendar curCalendar = new GregorianCalendar(curYear, curMonth, curDay);

        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        Calendar calendar = null;
        //���صĽ��map
        Map sksssqMap = new HashMap();
        BigDecimal ys = new BigDecimal("12"); //12����

        if (ZQLXDM_JB.equals(zqlxdm))
        { //����
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
        { //�걨
            if (szsmdm.substring(0, 2).equals(SZDM_YHS)
                || szsmdm.substring(0, 2).equals(SZDM_QYSDS)
                || szsmdm.substring(0, 4).equals(SZDM_GRSDS))
            {
                //��ʼ����:��һ���1��1��
                skssksrq = getTimestampMinDay(curYear - 1, 0);
                //��������:��һ������һ��
                skssjsrq = getTimestampMaxDay(curYear - 1, 11);
            }
//      else {
//        Debug.out("���ǹ�����������ģ��걨�в���������˰��˰Ŀ���롰��" + szsmdm);
//      }
        }

        if (ZQLXDM_YB.equals(zqlxdm))
        { //�±�
            //��ʼ����:���µ�һ��
            skssksrq = getTimestampMinDay(curYear, curMonth - 1);
            //��������:���µ����һ��
            skssjsrq = getTimestampMaxDay(curYear, curMonth - 1);
        }
        //��䷵�ؽ��
        sksssqMap.put(SKSSKSRQ, skssksrq);
        sksssqMap.put(SKSSJSRQ, skssjsrq);
        return sksssqMap;
    }

    /**
     * �õ�ָ���·ݶ�Ӧ����һ���ȵ���С�·�
     * @param month ָ���·�
     * @return ָ���·ݶ�Ӧ����һ���ȵ���С�·�
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
     * ȥ��ǰ���˰����������
     * @param curDate ����
     * @return Map
     */
    private static Map curYearSkssrq (Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString(); // �����

        Map retMap = new HashMap(2);

        // ��һ��ĵ�һ��
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, 0, 1).getTime().getTime());
        // ��һ������һ��
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, 11, 31).getTime().getTime());

        retMap.put(SKSSKSRQ, skssksrqDate); // ��ʼ����
        retMap.put(SKSSJSRQ, skssjsrqDate); // ��������
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }

    /**
     * �õ�ָ���·ݶ�Ӧ����һ���ȵ�����·�
     * @param month ָ���·�
     * @return ָ���·ݶ�Ӧ����һ���ȵ�����·�
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
     * �õ�ָ������¶�Ӧ����������
     * @param year ָ����
     * @param month ָ����
     * @return ָ������¶�Ӧ����������
     */
    private static int getmaxDay (final int year, final int month)
    {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * �õ�ָ������¶�Ӧ������������Timestamp���ڸ�ʽ
     * @param year ָ������
     * @param month ָ������
     * @return ����ָ��������
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
     * �õ�ָ������¶�Ӧ�ĵ�1���Timestamp���ڸ�ʽ
     * @param year ָ������
     * @param month ָ������
     * @return ����ָ��������
     */
    public static Timestamp getTimestampMinDay (final int year, final int month)
    {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * �������������õ�˰������ʱ��<br>
     * ��ǰ���ڵ��µ���������ʼ���ֹ���ڵ���
     * ���ڽ�ֹ���ڣ�1��=�޽�����<br>
     *
     * @param szsmdm ˰��˰Ŀ����
     * @param rq ��ǰʱ��
     * @param djzclxdm �Ǽ�ע������
     * @return Zqrl ˰��������ʼ ����ʱ�䣬�޽����ڣ�
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
            //�õ�����
            conn = SfDBResource.getConnection();

            String dateStr = TinyTools.Date2String(rq,"yyyyMM");

            Vector criteria = new Vector(); //��ѯ����
            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("szsmdm = '" + szsmdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");
            SfDBAccess db = new SfDBAccess(conn);
            List queryresult = db.query(new Zqrl().getClass(), criteria);
            if (queryresult.size() == 0)
            {
                //�������������û�к˶���ʹ�ñ���15����Ϊ˰����������
                /*******************************************/
                /**              Shi Yanfeng              **/
                /**�޸��޽�����***/
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
            throw ExceptionUtil.getBaseException(ex, "��ѯ��������ʧ��!");
        }
        finally
        {
            //�ͷ�����
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * �����±����͵�˰����������
     * @param curDate ����
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
        //�޽�����Ϊ����ʮ��
        Timestamp skxjrqDate = new Timestamp(new GregorianCalendar(year,
            month, 10).getTime().getTime());
        calendar.add(calendar.MONTH, 1);

        ret.setZqssrqq(skssksrqDate);
        ret.setZqssrqz(skssjsrqDate);
        ret.setZqzzrq(skxjrqDate);
        return ret;
    }

    /**
     * ����˰������ʱ��
     * @param ys �����������
     * @param curDate ��ǰ����
     * @return map
     */
    private static Map getSksq (BigDecimal ys, Date curDate)
    {
        HashMap dataMap = new HashMap();
        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        BigDecimal def_6 = new BigDecimal("6");
        BigDecimal def_12 = new BigDecimal("12");
        //������ǰʱ��
        int curYear = TinyTools.getYear(curDate);
        int curMonth = TinyTools.getMonth(curDate);
        int curQuarter = TinyTools.getQuarter(curDate);
        int curDay = TinyTools.getDay(curDate);

        if (curMonth <= 6)
        { //ǰ����
            if (ys.equals(def_6))
            { //���������Ϊ6
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:6�µ����һ��
                skssjsrq = getTimestampMaxDay(curYear, 5);
            }
            else if (ys.equals(def_12))
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:��12�µ����һ��
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if (ys.compareTo(def_6) == -1)
            { //ysС��6������-1
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:�������µ����һ��
                skssjsrq = getTimestampMaxDay(curYear,
                                              Integer.parseInt(ys.toString())
                                              - 1);
            }
            else if (ys.compareTo(def_12) == -1)
            { //ysС��12������-1
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:�������µ����һ��
                skssjsrq = getTimestampMaxDay(curYear,
                                              Integer.parseInt(ys.toString())
                                              - 1);
            }
            else if (ys.compareTo(def_12) == 1)
            { //����12
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:��������һ��
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
        }
        else if (curMonth > 6)
        { //�����
            if (ys.equals(def_6))
            { //���������Ϊ6
                //��ʼ����,�����7��1��
                skssksrq = getTimestampMinDay(curYear, 6);
                //��������:��������һ��
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if (ys.compareTo(def_6) == -1)
            { //ysС��6������-1
                //��ʼ����,�����7��1��
                skssksrq = getTimestampMinDay(curYear, 6);
                //��������:6+�������µ����һ��
                skssjsrq = getTimestampMaxDay(curYear,
                                              6 + Integer.parseInt(ys.toString()));
            }
            else
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:��������һ��
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
        }
        //��䷵�ؽ��
        dataMap.put(SKSSKSRQ, skssksrq);
        dataMap.put(SKSSJSRQ, skssjsrq);
        return dataMap;
    }

    /**
     * ����걨���ǳ�����˰�֣�ȡ˰������ʱ�ڵ�ʱ��Ҫ�ȶ�˰�ѵĽ��ɴ����˶��Ľӿڣ�
     * ����ӿڷ���0��˵��û�н��ɴ����ĺ˶�����ʱ�걨��������˰������ʱ�ڣ����걨�����������1��1����12��31�գ�
     * ����ӿڷ��ص�ֵ����0������ݵ�ǰ�걨���������ϰ��껹���°���������˰������ʱ�ڣ������ǰ�걨���������ϰ��꣬�ҽӿڷ���ֵ������0����˰������ʱ��Ϊ�걨�������ڵ��ϰ��ꣻ
     * ����걨���������°��꣬�ҽӿڷ���ֵ������0ʱ��ͬ��
     * @param  curDate �걨����
     * @param  jncs ���ɴ���
     * @return Map ˰����������
     */
    public static Map getCftSkssrq (Date curDate, int jncs)
    {
        HashMap dataMap = new HashMap();
        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        //������ǰʱ��
        int curYear = TinyTools.getYear(curDate);
        int curMonth = TinyTools.getMonth(curDate);

        if (jncs <= 0)
        {
            //����ӿڷ���0��˵��û�н��ɴ����ĺ˶�����ʱ�걨��������˰������ʱ�ڣ����걨�����������1��1����12��31�գ�
            //��ʼ����,�����1��1��
            skssksrq = getTimestampMinDay(curYear, 0);
            //��������:��12�µ����һ��
            skssjsrq = getTimestampMaxDay(curYear, 11);
        }
        else
        {
            if (curMonth < 6)
            {
                //�����ǰ�걨���������ϰ��꣬�ҽӿڷ���ֵ������0����˰������ʱ��Ϊ�걨�������ڵ��ϰ��ꣻ
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:6�µ����һ��
                skssjsrq = getTimestampMaxDay(curYear, 5);
            }
            else
            {
                //��ʼ����,�����7��1��
                skssksrq = getTimestampMinDay(curYear, 6);
                //��������:��������һ��
                skssjsrq = getTimestampMaxDay(curYear, 11);

            }

        }
        dataMap.put(Skssrq.SKSSKSRQ, skssksrq);
        dataMap.put(Skssrq.SKSSJSRQ, skssjsrq);
        return dataMap;

    }

    /**
     * ȡ��˰��˰Ŀֵ����
     * @param  szsmdm ˰��˰Ŀ����
     * @return Szsm ˰��˰Ŀֵ���� ���û�и�ֵ�����򷵻�null
     */
    private static Szsm getSzsm (String szsmdm)
    {
        List ret = new ArrayList();
        //�õ�˰��˰Ŀֵ�����б�
        ret = CodeManager.getCodeList("ORSZSM", CodeConstants.CODE_MAP_ORLIST).
              getRecords();
        for (int i = 0; i < ret.size(); i++)
        {
            //�õ�ֵ����
            Szsm temp = (Szsm) ret.get(i);
            if (temp.getSzsmdm().equals(szsmdm))
            {
                //����ƥ���ֵ����
                return temp;
            }
        }
        return null;

    }

    /**
     * ������������͵�˰����������
     * @param curDate ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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
     * ���㼾�����͵�˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
     */
    public static Map commonQuarter (Date curDate)
    {
//    if (curDate.getMonth() < 3) {
        if (TinyTools.getMonth(curDate) < 3)
        {
            // һ�����൱���걨
            return yearSkssrq(curDate);
        }

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        String nd = new Integer(year).toString();

        // �ϼ���
        int jd = month / 3;

        // ��һ���ȵ�һ��
        Timestamp skssksrqDate = getFirstDayOfQuarter(year, jd);
        // ��һ�������һ��
        Timestamp skssjsrqDate = getLastDayOfQuarter(year, jd);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }
    /**
     * ���㼾�����͵�˰����������
     * @param  nd ��� jd ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *              ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *              ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
     */
     public static Map commonQuarter (int nd,int jd)
    {

        System.out.println(jd);

        // ��һ���ȵ�һ��
        Timestamp skssksrqDate = getFirstDayOfQuarter(nd, jd);
        // ��һ�������һ��
        Timestamp skssjsrqDate = getLastDayOfQuarter(nd, jd);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
       

        return retMap;
    }

    /**
     * �õ��ۻ��͵�˰����������
     * @param curDate ��ǰ����
     * @return �ۻ��͵�˰����������
     */
    private static Map accumulatedQuarter (Date curDate)
    {
        Map sksssqMap = new HashMap();

//    if (curDate.getMonth() < 3) {
        if (TinyTools.getMonth(curDate) < 3)
        {
            // ��һ�������걨
            return yearSkssrq(curDate);
        }

        //��ʼ����,�����1��1��
//    sksssqMap.put(SKSSKSRQ, getTimestampMinDay(curDate.getYear() + 1900, 0));
        sksssqMap.put(SKSSKSRQ,
                      getTimestampMinDay(TinyTools.getYear(curDate), 0));
        //��������:��ǰ���ڵ��ϸ��µ����һ��
//    sksssqMap.put(SKSSJSRQ,
//                  getTimestampMaxDay(curDate.getYear() + 1900,
//                                     curDate.getMonth() - 1));
        sksssqMap.put(SKSSJSRQ,
                      getTimestampMaxDay(TinyTools.getYear(curDate),
                                         TinyTools.getMonth(curDate) - 1));

        return sksssqMap;
    }

    /**
     * �õ����ȵĵ�һ��
     * @param year ��
     * @param quarter ����
     * @return ���ȵĵ�һ��
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
     * �õ����ȵ����һ��
     * @param year ��
     * @param quarter ����
     * @return ���ȵ����һ��
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
