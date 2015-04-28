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
import com.ttsoft.bjtax.shenbao.model.domain.Zqlx;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqlxmxwhForm;
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
public class ZqlxmxwhProcessor
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
        ZqlxmxwhForm form = (ZqlxmxwhForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            Vector zqlxVector = new Vector();
            zqlxVector.add(" ZQLXDM = '" + form.getHeadZqlxdm() + "' ");

            //��ѯ����ǰ��Ҫ�޸ĵ�����
            List zqlxList = da.query(Zqlx.class, zqlxVector);
            if (zqlxList == null || zqlxList.size() == 0)
            {
                throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
            }
            if (zqlxList != null && zqlxList.size() > 0)
            {
                Zqlx zqlx = (Zqlx) zqlxList.get(0);
                //��ʽ�������Թ���ʾ
                form.setZqlxdm(zqlx.getZqlxdm());
                form.setZqlxmc(zqlx.getZqlxmc());
                form.setJglxdm(zqlx.getJglxdm());
                form.setLrr(zqlx.getLrr());
                form.setLrrq(getFormatDate(zqlx.getLrrq().toString().substring(
                    0, 10)));
                form.setSkssq(zqlx.getSkssq().toString());

                if (zqlx.getTyrq() != null
                    && zqlx.getTyrq().toString().length() > 10)
                {
                    form.setTyrq(getFormatDate(zqlx.getTyrq().toString().
                                               substring(0, 10)));
                }
                else
                {
                    form.setTyrq("");
                }
                form.setZqksrq(zqlx.getZqksrq().toString());
                form.setZqts(zqlx.getZqts().toString());
                form.setZqzq(zqlx.getZqzq());
                //��˰�������ڵĴ���ת��Ϊ����
//                if (form.getSkssq().equals("0"))
//                {
//                    form.setSkssq("����");
//                }
//                else if (form.getSkssq().equals("1"))
//                {
//                    form.setSkssq("����");
//                }
                //��������͵Ĵ���ת��Ϊ����
//                if (form.getJglxdm().equals("0"))
//                {
//                    form.setJglxdm("��");
//                }
//                else if (form.getJglxdm().equals("1"))
//                {
//                    form.setJglxdm("��");
//                }
//                else if (form.getJglxdm().equals("2"))
//                {
//                    form.setJglxdm("����");
//                }
//                else if (form.getJglxdm().equals("4"))
//                {
//                    form.setJglxdm("��");
//                }
//                else if (form.getJglxdm().equals("12"))
//                {
//                    form.setJglxdm("��");
//                }

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
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
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
        ZqlxmxwhForm form = (ZqlxmxwhForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�Ȳ�ѯ����ǰ��¼
            Zqlx zqlx = new Zqlx();
            Vector zqlxVector = new Vector();
            zqlxVector.add("zqlxdm='" + form.getHeadZqlxdm() + "'");
            List tempList = da.query(Zqlx.class, zqlxVector);

            if (tempList == null || tempList.size() == 0)
            {
                throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
            }
            zqlx = (Zqlx) tempList.get(0);
            //���ø���ֵ��������
            zqlx.setZqts(new BigDecimal(form.getZqts()));
            zqlx.setJglxdm(form.getJglxdm());
            zqlx.setSkssq(new BigDecimal(form.getSkssq()));
            zqlx.setZqksrq(new BigDecimal(form.getZqksrq()));
            zqlx.setZqzq(form.getZqzq());
            //��������
            da.update(zqlx);
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
     * ɾ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doDelete (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ʱ��ת��������
     * �磺2008-08-08 00:00:00 ת��Ϊ20080808 00:00:00
     * ���ߣ�2008-08-08 ת��Ϊ20080808
     * @param inTime ���ڻ�����ʱ���ַ���
     * @return ���ڻ�����ʱ��
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
     * @return ����
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
