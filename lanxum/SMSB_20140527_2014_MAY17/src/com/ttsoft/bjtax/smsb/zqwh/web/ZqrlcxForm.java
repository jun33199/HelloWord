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
 * <p>Description: ����������ѯ</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlcxForm
    extends BaseForm
{
    /**
     * �������ͣ���ѯʹ��
     */
    private String headZqlx;

    /**
     * �������ʹ���
     */
    private String zqlxdm;

    /**
     * ������������
     */
    private String zqlxmc;

    /**
     * ��������������
     */
    private String zqssrqq;

    /**
     * ������������ֹ
     */
    private String zqssrqz;

    /**
     * ������ʼ����
     */
    private String zqqsrq;

    /**
     * ������ֹ����
     */
    private String zqzzrq;

    /**
     * ��ϸ�б����ݼ�
     */
    private String[] columns =
        {"szsmdm", "szsmmc", "dqkjfpje", "dqwkjfpje",
        "dqyysrjehj", "jzyysr"};

    /**
     * ҳ�����ݼ�
     */
    private java.util.List dataList = new ArrayList();

    /**
     * ѡ�еļ�¼��
     */
    private java.util.List selectOptionList = new ArrayList();

    /**
     * ά�����
     */
    private String whnf;

    /**
     * ѡ�б��
     */
    private String selectTypeRadio;

    public ZqrlcxForm ()
    {
        Calendar c = Calendar.getInstance();
        whnf = "" + c.get(Calendar.YEAR);
        headZqlx = "*";
        selectTypeRadio = "1";
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getHeadZqlx ()
    {
        return headZqlx;
    }

    public void setHeadZqlx (String headZqlx)
    {
        this.headZqlx = headZqlx;
    }

    public String getZqlxdm ()
    {
        return zqlxdm;
    }

    public void setZqlxdm (String zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }

    public String getZqlxmc ()
    {
        return zqlxmc;
    }

    public void setZqlxmc (String zqlxmc)
    {
        this.zqlxmc = zqlxmc;
    }

    public String getZqssrqq ()
    {
        return zqssrqq;
    }

    public void setZqssrqq (String zqssrqq)
    {
        this.zqssrqq = zqssrqq;
    }

    public String getZqssrqz ()
    {
        return zqssrqz;
    }

    public void setZqssrqz (String zqssrqz)
    {
        this.zqssrqz = zqssrqz;
    }

    public String getZqqsrq ()
    {
        return zqqsrq;
    }

    public void setZqqsrq (String zqqsrq)
    {
        this.zqqsrq = zqqsrq;
    }

    public String getZqzzrq ()
    {
        return zqzzrq;
    }

    public void setZqzzrq (String zqzzrq)
    {
        this.zqzzrq = zqzzrq;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public java.util.List getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getSelectOptionList ()
    {
        return selectOptionList;
    }

    public void setSelectOptionList (java.util.List selectOptionList)
    {
        this.selectOptionList = selectOptionList;
    }

    public String getWhnf ()
    {
        return whnf;
    }

    public void setWhnf (String whnf)
    {
        this.whnf = whnf;
    }

    public String getSelectTypeRadio ()
    {
        return selectTypeRadio;
    }

    public void setSelectTypeRadio (String selectTypeRadio)
    {
        this.selectTypeRadio = selectTypeRadio;
    }
}