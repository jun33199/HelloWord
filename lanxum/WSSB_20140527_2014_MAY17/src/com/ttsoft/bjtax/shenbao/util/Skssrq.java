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
 * <p>Title:Title: ������˰�ۺϹ���ϵͳ  ���ģ��</p>
 * <p>Description: ����˰����������</p>
 * <p>ͨ������һ������,���˰����������<p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT </p>
 * @author zhiyong He
 * @version 1.0
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
     * ���㼾�����͵�˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
     */
    public static Map commonQuarter(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);

        // ��һ����
        int jd = month / 3;

        if (month<3)
        {
            year --;
            jd = 4;
        }

        String nd = String.valueOf(year);

        // �ϼ��ȵ�һ��
        Timestamp skssksrqDate = getFirstDayOfQuarter(year, jd);
        // �ϼ������һ��
        Timestamp skssjsrqDate = getLastDayOfQuarter(year, jd);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }

    
    /**
     * ���㼾���پ�ҵ�����걨��˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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

        // �ϼ��ȵ�һ��
        Timestamp skssksrqDate = getFirstDayOfQuarter(year, jd);
        // �ϼ������һ��
        Timestamp skssjsrqDate = getLastDayOfQuarter(year, jd);

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

    /**
     * �����±����͵�˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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

        // �ϸ��µ�һ��
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, month, 1).getTime().getTime());

        // �ϸ������һ��
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
     * �����걨��˰����������
     * ��ǰ����Ϊ�ϰ����е�����ʱ��˰������Ϊ��ǰ���1��1����6��31�գ�
     * ��ǰ����Ϊ�°����е�����ʱ��˰������Ϊ��ǰ���7��1����12��31�գ�
     * @param curDate ��ǰʱ��
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
     * �����������͵�˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
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
     * �õ�˰������ʱ��
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param djzclx �Ǽ�ע�����ʹ���
     * @param sklxdm ˰�����ʹ���
     * @param zqlxdm �������ʹ���
     * @param curDate ��ǰʱ��
     * @param sjse ʵ�ɽ��
     * @param kssl ��˰����
     * @param jsje ��˰���
     * @return Map ˰��������ʼ ����ʱ��
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
                    // �������������ݿ�
                    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
                    String rq = simpleDataFormat.format(curDate); //��ǰ����:YYYYMMdd
                    Map data = (Map)TrancProxy.getZqzzrq(szsmdm, djzclx, rq);
                    //�����ѯ���ݿ�õ�˰����������(˰�������������ڲ�Ϊ��)���򷵻ز�ѯ���
                    if(data != null && data.get(SKSSKSRQ) != null && data.get(SKSSJSRQ) != null)
                        return data;
                }
                //������ݿ���û�ж�Ӧ����������˰����������
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

    //����˰Ʊ
    private static Map doZcsp(String jsjdm,
                              String szsmdm,
                              String zqlxdm,
                              Date curDate,
                              BigDecimal sjse,
                              BigDecimal kssl,
                              BigDecimal jsje)
    {
        // ������
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
                 * ��������ʹ��˰2007��10��������������
                 * ��2007���ϰ���δ��������˰,�°�������������ȫ��˰��
                 * ���������¿���:
                 * 2007���������ʹ��˰���ɴ���ͳһΪȫ������,�����ɴ���Ϊ0
                 * 2007.10���ڹ���,��˰���϶����ɴ�������
                 * 
                 * ��־�� 2007-8-15�ձ�ע
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
                // ���ʵ�Ӫҵ˰��һ�㼾��
                return commonQuarter(curDate);
            }
        }

        if (SzsmdmConstant.JRY.equals(szsmdm.substring(0, 3)))
        {
            String nwzfldm = getNwzfldm(jsjdm);

            if (CodeConstant.DJZCLXDM_NWZFLDM_GAT.equals(nwzfldm) ||
                CodeConstant.DJZCLXDM_NWZFLDM_WZ.equals(nwzfldm))
            {
                // ���ʵĽ���ҵ��һ�㼾��
                return commonQuarter(curDate);
            }
        }

        if (SzsmdmConstant.GTGSH.equals(szsmdm.substring(0, 4)))
        {
            // ���幤�̻�
            String zsfsdm = getZsfsdm(jsjdm);
            if (CodeConstant.ZSFSDM_CZZS.equals(zsfsdm))
            {
                // �����������ۼƼ���
                return accumulatedQuarter(curDate);
            }
            else if (CodeConstant.ZSFSDM_HDZS.equals(zsfsdm))
            {
                // �˶�������һ�㼾��
                return commonQuarter(curDate);
            }
        }

        if(CodeConstant.ZQLXDM_QUARTER.equals(zqlxdm)) //����
        {
            if(SzsmdmConstant.QYSDS.equals(szsmdm.substring(0, 2)))
            {
                // ��ҵ����˰�������ۼƼ���
                return accumulatedQuarter(curDate);
            }
            else
            {
                return commonQuarter(curDate);
            }
        }
        else if(CodeConstant.ZQLXDM_YEAR.equals(zqlxdm)) //�걨
        {
            return yearSkssrq(curDate);
        }
        else if(CodeConstant.ZQLXDM_MONTH.equals(zqlxdm)) //�±�
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
            // ��һ�������걨
            return yearSkssrq(curDate);
        }

        //��ʼ����,�����1��1��
        sksssqMap.put(SKSSKSRQ, getTimestampMinDay(curDate.getYear() + 1900,0));
        //��������:��ǰ���ڵ��ϸ��µ����һ��
        sksssqMap.put(SKSSJSRQ, getTimestampMaxDay(curDate.getYear() + 1900, curDate.getMonth() - 1));

        return sksssqMap;
    }

    /**
     * ���㳵������˰����������
     * @param jsjdm ���������
     * @param szsmdm ˰��˰Ŀ����
     * @param curDate ��ǰʱ��
     * @param sjse ʵ�ɽ��
     * @param kssl ��˰����
     * @param jsje ��˰���
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

            BigDecimal ys = new BigDecimal("12");  //12����

            //���㳵��������˰������ʱ��  ˵��������һ���µģ���һ���¼��㣨�ݶ���
            if(SzsmdmConstant.CCSYF.indexOf(szsmdm.substring(0, 2)) > 0) //����˰��
            {
                //��ʵ��/��λ˰��/��˰������12��ÿ�������˶��ٸ��µ�˰��
                //�����ж��ǰ��տ�˰�������Ǽ�˰��������˰
                //���˰Ŀ�����Ӧ��asljbs�ֶ�ֵΪ2�����տ�˰�������м��㣬�����ռ�˰������
                Szsm smData = new Szsm();
                smData = (Szsm)TrancProxy.getSzsm(szsmdm);  //ȡ��˰��˰Ŀ����

                String countKey = smData.getAsljbs(); //�����ʶ
                BigDecimal dwse = smData.getSl(); //��λ˰��
                //��ȡ����:  ÿ�������˶��ٸ��µ�˰��
                BigDecimal monCount = new BigDecimal("0");
                if(countKey != null && countKey.equals("2")) //����˰����
                {
                    monCount = sjse.divide(dwse, 2).divide(kssl, 2)
                        .multiply(ys).setScale(0, BigDecimal.ROUND_UP);
                    sksssqMap = (Map)getSksq(monCount, curDate); //���˰������ʱ��
                }
                else //����˰���
                {
                    monCount = sjse.divide(dwse, 2).divide(jsje, 2)
                        .multiply(ys).setScale(0, BigDecimal.ROUND_UP);
                    sksssqMap = (Map)getSksq(monCount, curDate); //���˰������ʱ��
                }
                return sksssqMap;
            }
            else if(SzsmdmConstant.FCS.indexOf(szsmdm.substring(0, 2)) > 0 ||
                    SzsmdmConstant.TDSYF.indexOf(szsmdm.substring(0, 2)) > 0) //����˰���������
            {
                //ȡ˰�ѽӿڵ���Ӧ��˰���ʵ��/��Ӧ��˰���˰�ѽӿ�ȡ��*12���жϼ���Ľ���͵�ǰ���ڣ�
                Cftsyhd tempObj = getCft(jsjdm, curDate, szsmdm);
                BigDecimal nynse = tempObj.getJsje(); //�����Ӧ��˰��
                //��ü�����������
                BigDecimal months =
                    sjse.divide(nynse, 2).multiply(ys).setScale(0, BigDecimal.ROUND_UP);
                sksssqMap = (Map)getSksq(months, curDate); //���˰������ʱ��
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
        String nd = new Integer(year).toString();  // �����

        Map retMap = new HashMap(2);

        // ����ĵ�һ��
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, 0, 1).getTime().getTime());
        // ��������һ��
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, 11, 31).getTime().getTime());

        retMap.put(SKSSKSRQ, skssksrqDate);  // ��ʼ����
        retMap.put(SKSSJSRQ, skssjsrqDate);  // ��������
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
                throw new Exception("û�к˶��ü��������("+jsjdm+")�Ķ������ݣ�");

            for(int i = 0; i < tempList.size(); i++)
            {
                Cftsyhd tempObj = (Cftsyhd)tempList.get(i);
                if(tempObj.getSzsmdm().equals(szsmdm))
                {
                    BigDecimal nynse = tempObj.getJsje(); //�����Ӧ��˰��
                    if(nynse.equals("0"))
                        throw new Exception("�˶���Ӧ��˰������Ϊ0��");
                    return tempObj;
                }
            }

            //���û�з������ݣ���˵�������ڸ�˰Ŀ��Ӧ�ĺ˶�����
            throw new Exception("û�и�˰��˰Ŀ��" + szsmdm + "���ĺ˶����ݣ�");
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e, "û�л����Ӧ��˰�");
        }
    }

    /**
     * �õ�ָ������¶�Ӧ������������Timestamp���ڸ�ʽ
     * @param year ָ������
     * @param month ָ������
     * @return ����ָ��������
     */
    private static Timestamp getTimestampMaxDay(final int year ,final int month)
    {
        Calendar calendar = new GregorianCalendar(year,month,1);
        int maxDay =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return new Timestamp((new GregorianCalendar(year,month,maxDay)).getTime().getTime());
    }

    /**
     * �õ�ָ������¶�Ӧ�ĵ�1���Timestamp���ڸ�ʽ
     * @param year ָ������
     * @param month ָ������
     * @return ����ָ��������
     */
    private static Timestamp getTimestampMinDay(final int year, final int month)
    {
        Calendar calendar = new GregorianCalendar(year, month, 1);
        return new Timestamp( calendar.getTime().getTime());
    }

    /**
     * ����˰������ʱ��
     * @param ys �����������
     * @param curDate ��ǰ����
     * @return map
     */
    private static Map getSksq(BigDecimal ys, Date curDate)
    {
        HashMap dataMap = new HashMap();
        Timestamp skssksrq = null;
        Timestamp skssjsrq = null;
        BigDecimal def_6 = new BigDecimal("6");
        BigDecimal def_12 = new BigDecimal("12");
        //������ǰʱ��
        int curYear    = getYear(curDate);
        int curMonth   = getMonth(curDate);
        int curQuarter = getQuarter(curDate);
        int curDay     = getDay(curDate);

        if(curMonth <= 6)  //ǰ����
        {
            if(ys.equals(def_6)) //���������Ϊ6
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:6�µ����һ��
                skssjsrq = getTimestampMaxDay(curYear, 5);
            }
            else if(ys.equals(def_12))
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear, 0);
                //��������:��12�µ����һ��
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if(ys.compareTo(def_6) == -1) //ysС��6������-1
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear,0);
                //��������:�������µ����һ��
                skssjsrq = getTimestampMaxDay(curYear,Integer.parseInt(ys.toString())-1);
            }
            else if(ys.compareTo(def_12) == -1) //ysС��12������-1
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear,0);
                //��������:�������µ����һ��
                skssjsrq = getTimestampMaxDay(curYear,Integer.parseInt(ys.toString())-1);
            }
            else if(ys.compareTo(def_12) == 1)  //����12
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear,0);
                //��������:��������һ��
                skssjsrq = getTimestampMaxDay(curYear,11);
            }
        }
        else if(curMonth > 6)  //�����
        {
            if(ys.equals(def_6)) //���������Ϊ6
            {
                //��ʼ����,�����7��1��
                skssksrq = getTimestampMinDay(curYear, 6);
                //��������:��������һ��
                skssjsrq = getTimestampMaxDay(curYear, 11);
            }
            else if(ys.compareTo(def_6) == -1) //ysС��6������-1
            {
                //��ʼ����,�����7��1��
                skssksrq = getTimestampMinDay(curYear,6);
                //��������:6+�������µ����һ��
                skssjsrq = getTimestampMaxDay(curYear,6+Integer.parseInt(ys.toString()));
            }
            else
            {
                //��ʼ����,�����1��1��
                skssksrq = getTimestampMinDay(curYear,0);
                //��������:��������һ��
                skssjsrq = getTimestampMaxDay(curYear,11);
            }
        }
        //��䷵�ؽ��
        dataMap.put(SKSSKSRQ, skssksrq);
        dataMap.put(SKSSJSRQ, skssjsrq);
        return dataMap;
    }

    /**
     * �õ��������ڵļ��� Ϊint��
     * @param date ����������
     * @return int ����ֵ(1,2,3,4)
     */
    private static int getQuarter(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return(calendar.get(Calendar.MONTH) / 3 + 1);
    }

    /**
     * �õ��������ڵ���� Ϊint��
     * @param date ����������
     * @return int ���ֵ
     */
    private static int getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return(calendar.get(Calendar.YEAR));
    }

    /**
     * �õ��������ڵ��·� Ϊint��
     * @param date ����������
     * @return int �·�ֵ(0��ʼ)
     */
    private static int getMonth(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return(calendar.get(Calendar.MONTH));
    }

    /**
     * �õ��������ڵı��µļ��� Ϊint��
     * @param date ����������
     * @return int ���(1��ʼ)
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