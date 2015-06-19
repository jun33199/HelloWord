/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqrlmxwhForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����������ϸά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlmxwhProcessor
    implements Processor
{

    /**
     * �û���Ϣ����
     */
    private UserData userData = null;

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
            case CodeConstant.SMSB_QUERYACTION: //��ѯ
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * ���ǰ̨Ĭ����ʾ���Ƶ�ActionForm
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ��ò�ѯ�����ActionForm
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        ZqrlmxwhForm form = (ZqrlmxwhForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Vector zqrlVector = new Vector();
            zqrlVector.add(" SZSMDM = '" + form.getSzsmdm() + "' ");
            zqrlVector.add(" ZQLXDM = '" + form.getZqlxdm() + "' ");
            zqrlVector.add(" DJZCLXDM = '" + form.getDjzclxdm() + "' ");
            zqrlVector.add(" ZQQSRQ = TO_DATE('" + form.getZqqsrq()
                           + "','YYYY-MM-DD')");
            zqrlVector.add(" ND = '" + form.getNd() + "' ");

            //��ѯ����ǰ��Ҫ�޸ĵ�����
            List zqrlList = da.query(Zqrl.class, zqrlVector);
            if (zqrlList == null || zqrlList.size() == 0)
            {
                throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
            }
            if (zqrlList != null && zqrlList.size() > 0)
            {
                Zqrl zqrl = (Zqrl) zqrlList.get(0);
                //��ʽ�������Թ���ʾ
                form.setDjzclxmc(CodeUtils.getCodeBeanLabel("ZQWH_DJZCLX",
                    zqrl.getDjzclxdm()));
                form.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM",
                    zqrl.getSzsmdm()));
                form.setZqlxmc(zqrl.getZqlxmc());
                //��ʽ����ʾ����
                form.setZqssrqq(getFormatDate(zqrl.getZqssrqq().toString().
                                              substring(0, 10)));
                form.setZqssrqz(getFormatDate(zqrl.getZqssrqz().toString().
                                              substring(0, 10)));
                form.setZqqsrq(getFormatDate(zqrl.getZqqsrq().toString().
                                             substring(0, 10)));
                form.setZqzzrq(getFormatDate(zqrl.getZqzzrq().toString().
                                             substring(0, 10)));
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * ɾ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        try
        {

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

        return null;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        ZqrlmxwhForm form = (ZqrlmxwhForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�Ȳ�ѯ����ǰ��¼
            Zqrl zqrl = new Zqrl();
            Vector zqrlVector = new Vector();
            zqrlVector.add("szsmdm='" + form.getSzsmdm() + "'");
            zqrlVector.add("djzclxdm='" + form.getDjzclxdm() + "'");
            zqrlVector.add("nd='" + form.getNd() + "'");
            zqrlVector.add("zqlxdm='" + form.getZqlxdm() + "'");
            zqrlVector.add("zqqsrq=to_date('" + form.getZqqsrq()
                           + "','yyyymmdd')");
            List tempList = da.query(Zqrl.class, zqrlVector);

            if (tempList == null || tempList.size() == 0)
            {
                throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
            }
            zqrl = (Zqrl) tempList.get(0);
            //���ø���ֵ��������
            zqrl.setZqzzrq(Timestamp.valueOf(setFormatDate(form.getZqzzrq())
                                             + " 00:00:00"));
            //��������
            da.update(zqrl);
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

        return form;
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
     * ʱ��ת��������
     * �磺2008-08-08 00:00:00 ת��Ϊ20080808 00:00:00
     * ���ߣ�2008-08-08 ת��Ϊ20080808
     * @param inTime ���ڻ�����ʱ���ַ���
     * @return ʱ��
     * @exception BaseException
     */
    public static String getFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        String tempStr = inTime.substring(0, 10);
        String defStr = "";

        try
        {
            if (inTime.length() > 15)
            {
                defStr = inTime.substring(10);
            }
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyyMMdd");
            result = df.format(java.sql.Date.valueOf(tempStr)) + defStr;
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of getFormatDate

    /**
     * ʱ��ת��������getFormatDate������������
     * �磺20080808 00:00:00 ת��Ϊ2008-08-08 00:00:00
     * ���ߣ�20080808 ת��Ϊ2008-08-08
     * @param inTime ���ڻ�����ʱ���ַ���
     * @return ʱ��
     * @exception BaseException
     */
    public static String setFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        try
        {
            String tempStr = inTime.substring(0, 4);
            String defStr = inTime.substring(4, 6);
            result = tempStr + "-" + defStr + "-" + inTime.substring(6);
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of setFormatDate

}
