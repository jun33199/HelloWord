/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����������ϸά����ѯ</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlmxcxForm
    extends BaseForm
{
    /**
     * ҳ�����ݼ�
     */
    private java.util.List dataList = new ArrayList();

    /**
     * ��ϸ�б����ݼ�
     */
    private String columns[] =
        {
        "szsmdm", "szsmmc", "zqlxdm", "zqlxmc",
        "zqssrqq", "zqssrqz",
        "zqqsrq", "zqzzrq", "cjrq", "lrrq", "swjgzzjgdm",
        "nd", "djzclxdm", "djzclxmc", "zqksrq", "zqts"};

    /**
     * �·ݣ���ѯʹ��
     */
    private String headMonth;

    /**
     * ��ȣ���ѯʹ��
     */
    private String headNd;

    /**
     * �������ͣ���ѯʹ��
     */
    private String headZqlx;

    /**
     * ˰�֣���ѯʹ��
     */
    private String headSz;

    /**
     * �Ǽ�ע�����ͣ���ѯʹ��
     */
    private String headDjzclx;

    /**
     * ��ȣ���ʱʹ��
     */
    private String tempNd;

    /**
     * ������ʼ���ڣ���ʱʹ��
     */
    private String tempZqqsrq;

    /**
     * ˰�֣���ʱʹ��
     */
    private String tempSz;

    /**
     * �������ͣ���ʱʹ��
     */
    private String tempZqlx;

    /**
     * �Ǽ�ע�����ͣ���ʱʹ��
     */
    private String tempDjzclx;

    /**
     * �·ݣ���ʱʹ��
     */
    private String tempMonth;

    public ZqrlmxcxForm ()
    {
        Calendar c = Calendar.getInstance();
        headNd = "" + c.get(Calendar.YEAR);
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public String getHeadMonth ()
    {
        return headMonth;
    }

    public void setHeadMonth (String headMonth)
    {
        this.headMonth = headMonth;
    }

    public String getHeadNd ()
    {
        return headNd;
    }

    public void setHeadNd (String headNd)
    {
        this.headNd = headNd;
    }

    public String getHeadZqlx ()
    {
        return headZqlx;
    }

    public void setHeadZqlx (String headZqlx)
    {
        this.headZqlx = headZqlx;
    }

    public String getHeadSz ()
    {
        return headSz;
    }

    public void setHeadSz (String headSz)
    {
        this.headSz = headSz;
    }

    public String getHeadDjzclx ()
    {
        return headDjzclx;
    }

    public void setHeadDjzclx (String headDjzclx)
    {
        this.headDjzclx = headDjzclx;
    }

    public String getTempNd ()
    {
        return tempNd;
    }

    public void setTempNd (String tempNd)
    {
        this.tempNd = tempNd;
    }

    public String getTempZqqsrq ()
    {
        return tempZqqsrq;
    }

    public void setTempZqqsrq (String tempZqqsrq)
    {
        this.tempZqqsrq = tempZqqsrq;
    }

    public String getTempSz ()
    {
        return tempSz;
    }

    public void setTempSz (String tempSz)
    {
        this.tempSz = tempSz;
    }

    public String getTempZqlx ()
    {
        return tempZqlx;
    }

    public void setTempZqlx (String tempZqlx)
    {
        this.tempZqlx = tempZqlx;
    }

    public String getTempDjzclx ()
    {
        return tempDjzclx;
    }

    public void setTempDjzclx (String tempDjzclx)
    {
        this.tempDjzclx = tempDjzclx;
    }

    public String getTempMonth ()
    {
        return tempMonth;
    }

    public void setTempMonth (String tempMonth)
    {
        this.tempMonth = tempMonth;
    }
}
