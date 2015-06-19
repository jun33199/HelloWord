/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ڼ���ά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class JjrwhProcessor
    implements Processor
{
    /**�������ͣ������걨*/
    private final int YEAR_TYPE = 1;

    /**�������ͣ��������걨*/
    private final int HALFYEAR_TYPE = 2;

    /**�������ͣ������걨*/
    private final int SEASON_TYPE = 4;

    /**�������ͣ������걨*/
    private final int MONTH_TYPE = 12;

    /**����*/
    private final String THIS_TERM = "1";

    /**����*/
    private final String PREVIOUS_TERM = "0";
    /**�����ۼ�*/
    private final String THIS_TERM_TOTAL = "3";

    /**�����ۼ�*/
    private final String PREVIOUS_TERM_TOTAL = "2";
    /**
     * ͨ�ô������ģ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {

        //�ش�����
        Object result = null;
        //�ж�VO�Ƿ�Ϊ��
        if (vo == null)
        {
            throw new NullPointerException();
        }
        //����Action��ֵ���ò�ͬ��process����
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION: //ǰ̨Ĭ����ʾ
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION: //��ѯ
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
     * ��ʾ��̨����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        JjrwhForm jf = (JjrwhForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);

            //��ѯ��ϸ����
            Vector vecJjrwh = new Vector();
            vecJjrwh.add(" SSNF= '" + jf.getWhnf() + "' ");
            //��ϸ��ѯ���ݽ��
            List JjrwhList = da.query(Jqsz.class, vecJjrwh);

            String[] holiday = new String[JjrwhList.size()];
            for (int i = 0; i < JjrwhList.size(); i++)
            {
                //��ô�����ϸֵ
                Jqsz jqsz = (Jqsz) JjrwhList.get(i);
                holiday[i] = timestampToString(jqsz.getJqrq());
            }
            jf.setHolyArr(holiday);
            return jf;
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * �����̨����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        JjrwhForm jf = (JjrwhForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
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

            //��ѯ��ϸ����
            Vector vecJjrwh = new Vector();
            vecJjrwh.add(" SSNF= '" + jf.getWhnf() + "' ");
            //��ϸ��ѯ���ݽ��
            List JjrwhList = da.query(Jqsz.class, vecJjrwh);

            String[] holiday = new String[JjrwhList.size()];
            for (int i = 0; i < JjrwhList.size(); i++)
            {
                //��ô�����ϸֵ
                Jqsz jqsz = (Jqsz) JjrwhList.get(i);
                holiday[i] = timestampToString(jqsz.getJqrq());
            }
            jf.setHolyArr(holiday);
            return jf;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("����ʧ�ܣ�");
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * ������������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doCreateCalendar (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        try
        {
            JjrwhForm jf = (JjrwhForm) vo.getData();
            int whnf = Integer.parseInt(jf.getWhnf()); //��ά�����
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Czqrl c = new Czqrl();
            //ɾ����ά����ݵ���������
            String strDeleteCondition = " ND = '" + whnf + "' ";
            da.delete(strDeleteCondition, c);

            //��������ȼ�����Ϣ
            Vector jqVector = new Vector();
            jqVector.add(" SSNF = '" + whnf + "' ");
            List jqList = da.query(Jqsz.class, jqVector);
            String[] holiday = new String[jqList.size()];
            //���챾��ȼ�������
            for (int i = 0; i < jqList.size(); i++)
            {
                holiday[i] = timestampToString( ( (Jqsz) jqList.get(i)).getJqrq());
            }

            //�����������������
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
                //��ô�����ϸֵ
                Zqlx orMx = (Zqlx) zqlxList.get(i);
                Map map = new HashMap();
                //��ֵ�����ֵ����Map
                BeanUtil.copyBeanToMap(zqlxColumns, orMx, map);
                zqlxMap[i] = map;
            }

            //������������
            for (int i = 0; i < zqlxMap.length; i++)
            {
                Czqrl czqrl[] = null;
                //�걨����
                int shenbaoType = 0;

                //�ֱ���ÿһ����������
                String zqzq = (String) zqlxMap[i].get("zqzq");
                Vector zqMonthVector = new Vector(); //��������ڵ��·�
                //������������
                for (int j = 0; j < 12; j++)
                {
                    String month = zqzq.substring(j, j + 1);
                    if (month.equals("1"))
                    {
                        zqMonthVector.add("" + j);
                    }
                }
                //ȷ���걨����
                int vectorLength = zqMonthVector.size();
                switch (vectorLength)
                {
                    case YEAR_TYPE:

                        //�����걨
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
                            //����������ʼ���ڡ�������ֹ����
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //������������������������������ֹ
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth, YEAR_TYPE,
                                SKSSQ);
                        }
                        break;
                    case HALFYEAR_TYPE:

                        //�������걨
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
                            //����������ʼ���ڡ�������ֹ����
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //������������������������������ֹ
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth,
                                HALFYEAR_TYPE, SKSSQ);
                        }
                        break;
                    case SEASON_TYPE:

                        //�����걨
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
                            //����������ʼ���ڡ�������ֹ����
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //������������������������������ֹ
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth,
                                SEASON_TYPE, SKSSQ);
                        }
                        break;
                    case MONTH_TYPE:

                        //�����걨
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
                            //����������ʼ���ڡ�������ֹ����
                            czqrl[k] = computeZengQi(czqrl[k], zqCalendar,
                                tianShu, holiday);
                            String SKSSQ = (String) zqlxMap[i].get("skssq");
                            //������������������������������ֹ
                            czqrl[k] = computerSuoShuRiQi(czqrl[k], whnf,
                                zqMonth, MONTH_TYPE,
                                SKSSQ);
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException(
                            "��֧���������걨��ʽ");
                }
                //��������
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
            throw new ApplicationException("������������ʧ�ܣ�");
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }

        return null;
    }

    /**
     * ��Timestamp��ת��Ϊһ��String������ ��eg.20030611��
     * @param gCalendar ����
     * @return String������ ��eg.20030611��
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
     * ��Timestamp��ת��Ϊһ��String������ ��eg.20030611��
     * @param timestamp ����ʱ��
     * @return String������ ��eg.20030611��
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
     * ����������ʼ���ڡ�������ֹ����
     * @param czqrl ����ֵ����
     * @param gCalendar ���ڵ�һ��
     * @param tianShu ��������
     * @param holiday �ڼ�������
     * @return ����ֵ����
     */
    private Czqrl computeZengQi (Czqrl czqrl, Calendar gCalendar, int tianShu,
                                 String[] holiday)
    {
        czqrl.setZqqsrq(TinyTools.calendar2Timestamp(gCalendar)); //������ʼ����
        if (tianShu == 31)
        {
            String tempStr = czqrl.getZqqsrq().toString();
            Timestamp aa = TinyTools.calendar2Timestamp(gCalendar);
            int tempInt = getMonthLastDate(TinyTools.getYear(aa) - 1900,
                                           TinyTools.getMonth(aa));
            gCalendar.set(TinyTools.getYear(aa), TinyTools.getMonth(aa),
                          tempInt);
            czqrl.setZqzzrq(TinyTools.calendar2Timestamp(gCalendar)); //������ֹ����
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
            czqrl.setZqzzrq(TinyTools.calendar2Timestamp(gCalendar)); //������ֹ����
        }

        return czqrl;

    }

    /**
     * ������������������������������ֹ
     * @param czqrl ����ֵ����
     * @param whnf �������
     * @param zqMonth �����·�
     * @param Type ���ڷ�ʽ�������걨���������걨�������걨�������걨��
     * @param SKSSQ ���������֣����ڻ����ڣ�
     * @return ����ֵ����
     */
    private Czqrl computerSuoShuRiQi (Czqrl czqrl, int whnf, int zqMonth,
                                      int Type,
                                      String SKSSQ)
    {
        Calendar ssCalendar = GregorianCalendar.getInstance();
        switch (Type)
        {
            case YEAR_TYPE:

                //�����걨
                if (SKSSQ.equals(THIS_TERM))
                { //����
                    ssCalendar.set(whnf, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //����
                    ssCalendar.set(whnf - 1, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf - 1, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(THIS_TERM_TOTAL))//Modified by lufeng 2004-07-28
                { //�����ۼ�
                    ssCalendar.set(whnf, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
                { //�����ۼ�
                    ssCalendar.set(whnf - 1, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf - 1, 11, 31);
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                break;
            case HALFYEAR_TYPE:

                //�������걨
                if (SKSSQ.equals(THIS_TERM))
                { //����
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
                { //����
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
                { //�����ۼ�
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
                { //�����ۼ�
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

                //�����걨
                if (SKSSQ.equals(THIS_TERM))
                { //����
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
                { //����
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
                { //�����ۼ�
                    if (zqMonth >= 0 && zqMonth <= 2)
                    {
                        ssCalendar.set(whnf, 0, 1);//�ӱ���1��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);//�ӱ���1��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 0, 1);//�ӱ���1��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 0, 1);//�ӱ���1��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
                { //�����ۼ�
                    if (zqMonth >= 0 && zqMonth <= 2)
                    {
                        ssCalendar.set(whnf - 1, 0, 1);//������1��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf - 1, 11, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 3 && zqMonth <= 5)
                    {
                        ssCalendar.set(whnf, 0, 1);//�ӱ���1��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 2, 31);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 6 && zqMonth <= 8)
                    {
                        ssCalendar.set(whnf, 3, 1);//�ӱ���4��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 5, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                    else if (zqMonth >= 9 && zqMonth <= 11)
                    {
                        ssCalendar.set(whnf, 6, 1);//�ӱ���7��1�ſ�ʼ
                        czqrl.setZqssrqq(TinyTools.calendar2Timestamp(
                            ssCalendar));
                        ssCalendar.set(whnf, 8, 30);
                        czqrl.setZqssrqz(TinyTools.calendar2Timestamp(
                            ssCalendar));
                    }
                }
                break;
            case MONTH_TYPE:

                //�����걨
                if (SKSSQ.equals(THIS_TERM))
                { //����
                    ssCalendar.set(whnf, zqMonth, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, zqMonth,
                                   getMonthLastDate(whnf, zqMonth));
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM))
                { //����
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
                { //�����ۼ�
                    ssCalendar.set(whnf, 0, 1);
                    czqrl.setZqssrqq(TinyTools.calendar2Timestamp(ssCalendar));
                    ssCalendar.set(whnf, zqMonth,
                                   getMonthLastDate(whnf, zqMonth));
                    czqrl.setZqssrqz(TinyTools.calendar2Timestamp(ssCalendar));
                }
                else if (SKSSQ.equals(PREVIOUS_TERM_TOTAL))
                { //�����ۼ�
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
     * �õ�ָ�����µ����һ��
     * @param whnf ��
     * @param month ��
     * @return ���µ����һ��
     */
    private int getMonthLastDate (int whnf, int month)
    {
        Calendar gCalendar = GregorianCalendar.getInstance();
        gCalendar.set(whnf, month, 3);
        return gCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * �������ִ�(format:yyyyMMdd eg. 20030611)ת��ΪTimestamp������
     * @param strDate ʱ��
     * @return Timestamp������
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
