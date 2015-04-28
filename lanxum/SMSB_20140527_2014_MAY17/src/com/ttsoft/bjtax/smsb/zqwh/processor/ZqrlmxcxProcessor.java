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
import java.util.HashMap;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqrlmxcxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����������ϸ��ѯ</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlmxcxProcessor
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
        List dataList = new ArrayList();
        //����OrMappingֵ�����ÿ������
        String columns[] =
                           {
                           "szsmdm", "szsmmc", "zqlxdm", "zqlxmc", "zqssrqq",
                           "zqssrqz",
                           "zqqsrq", "zqzzrq", "cjrq", "lrrq", "swjgzzjgdm",
                           "nd", "djzclxdm", "djzclxmc", "zqksrq", "zqts"};

        //�õ�Action���ݹ���ActionForm����
        ZqrlmxcxForm form = (ZqrlmxcxForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();

            SfDBUtils sfDB = new SfDBUtils(conn);
            //�õ�sql���
            String sql = getQuerySql(form, "query");
            List tempList = sfDB.getDataList(sql
                + " ORDER BY A.SZSMDM,A.ZQLXDM,A.DJZCLXDM");
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("û�з��������ļ�¼��");
            }

            //���������ݸ���ActionForm
            for (int i = 0; i < tempList.size(); i++)
            {
                //��ʽ��ÿ����¼
                //��ֵ�����ֵ����Map
                HashMap map = new HashMap();
                map = (HashMap) tempList.get(i);
                map.put("djzclxmc",
                        CodeUtils.getCodeBeanLabel("ZQWH_DJZCLX",
                    map.get("djzclxdm").toString()));
                map.put("szsmmc",
                        CodeUtils.getCodeBeanLabel("DM_SZSM",
                    map.get("szsmdm").toString())); //˰������
                map.put("zqssrqq",
                        (map.get("zqssrqq").toString()).substring(0, 10));
                map.put("zqssrqz",
                        (map.get("zqssrqz").toString()).substring(0, 10));
                map.put("zqqsrq",
                        (map.get("zqqsrq").toString()).substring(0, 10));
                map.put("zqzzrq",
                        (map.get("zqzzrq").toString()).substring(0, 10));

                dataList.add(map);
            }
            //��ֵ�Ż�form����
            form.setTempMonth(form.getHeadMonth()); //��
            form.setTempNd(form.getHeadNd()); //��
            form.setTempDjzclx(form.getHeadDjzclx()); //�Ǽ�ע������
            form.setTempSz(form.getHeadSz()); //˰��
            form.setTempZqlx(form.getHeadZqlx()); //��������
            form.setDataList(dataList);
        }
        catch (Exception ex)
        {
            //�׳��쳣
            form.setDataList(dataList);
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
        ZqrlmxcxForm form = (ZqrlmxcxForm) vo.getData();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            Zqrl zqrl = new Zqrl();
            String tempStr = "";
            Vector zqrlVector = new Vector();
            zqrlVector.add(" SZSMDM = '" + form.getTempSz() + "' ");
            zqrlVector.add(" ZQLXDM = '" + form.getTempZqlx() + "' ");
            zqrlVector.add(" DJZCLXDM = '" + form.getTempDjzclx() + "' ");
            zqrlVector.add(" ZQQSRQ = TO_DATE('" + form.getTempZqqsrq()
                           + "','YYYY-MM-DD')");
            zqrlVector.add(" ND = '" + form.getTempNd() + "' ");
            zqrl.setSzsmdm(form.getTempSz());
            zqrl.setZqlxdm(form.getTempZqlx());
            zqrl.setNd(form.getTempNd());
            zqrl.setDjzclxdm(form.getTempDjzclx());
            zqrl.setZqqsrq(Timestamp.valueOf(form.getTempZqqsrq() + " 00:00:00"));
            //ɾ��
            da.delete(zqrl);

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
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * �õ���ѯ����SQL���
     * @param sf ��ǰҳ���Ӧ��Form����
     * @param flag ��ѯ��query���򱣴棨save�����
     * @return sql�������
     */
    private String getQuerySql (ZqrlmxcxForm sf, String flag)
    {
        String sql = null;
        String whereCon = " ";
        //�õ���ѯ����
        //��ѯ����ʹ���û�����ֵ��������ʹ����ʱֵ
        if (flag.equals("query"))
        {
            if (sf.getHeadDjzclx() != null && !sf.getHeadDjzclx().equals("*")
                && !sf.getHeadDjzclx().equals(""))
            {
                whereCon += " AND A.DJZCLXDM='" + sf.getHeadDjzclx() + "'";
            }
            if (sf.getHeadSz() != null && !sf.getHeadSz().equals("*")
                && !sf.getHeadSz().equals(""))
            {
                whereCon += " AND A.SZSMDM LIKE '" + sf.getHeadSz() + "%'";
            }
            if (sf.getHeadZqlx() != null && ! (sf.getHeadZqlx()).equals("*")
                && ! (sf.getHeadZqlx()).equals(""))
            {
                whereCon += " AND A.ZQLXDM='" + sf.getHeadZqlx() + "'";
            }
            if (sf.getHeadMonth() != null && ! (sf.getHeadMonth()).equals("*")
                && ! (sf.getHeadMonth()).equals(""))
            {
                if (sf.getHeadMonth().equals("12")){  //ʮ���µĴ���
                    whereCon += " AND A.ZQQSRQ>=TO_DATE('" + sf.getHeadNd() + "-"
                    + sf.getHeadMonth() + "-01','YYYY-MM-DD')" +
                    " AND A.ZQQSRQ<=TO_DATE('" + sf.getHeadNd() + "-"
                    + sf.getHeadMonth() + "-31','YYYY-MM-DD')";
                }
                else {
                    whereCon += " AND A.ZQQSRQ>=TO_DATE('" + sf.getHeadNd() + "-"
                    + sf.getHeadMonth() + "-01','YYYY-MM-DD')" +
                    " AND A.ZQQSRQ<TO_DATE('" + sf.getHeadNd() + "-" +
                    (StringUtil.getInt(sf.getHeadMonth(),1)+1) +
                    "-01','YYYY-MM-DD')";
                }
            }
        }

        sql = "SELECT * FROM SBDB.SB_JL_ZQRL A "
              + " WHERE 1=1 " + whereCon + " AND A.ND = '" + sf.getHeadNd()
              + "' ";
        System.out.println("<><>"+sql);
        return sql;
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
}
