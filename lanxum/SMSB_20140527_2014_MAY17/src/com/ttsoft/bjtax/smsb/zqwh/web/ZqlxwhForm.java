/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��������ά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqlxwhForm
    extends BaseForm
{
    /**
     * �������ʹ���
     */
    private String zqlxdm;

    /**
     * ������������
     */
    private String zqlxmc;

    /**
     * ����
     */
    private String zqzq;

    /**
     * ˰����������
     */
    private String skssq;

    /**
     * ���ڿ�ʼ����
     */
    private String zqksrq;

    /**
     * ��������
     */
    private String zqts;

    /**
     * ¼����
     */
    private String lrr;

    /**
     * ¼������
     */
    private String lrrq;

    /**
     * ͣ������
     */
    private String tyrq;

    /**
     * ��ϸ�б����ݼ�
     */
    private String[] columns =
                               {"zqlxdm", "zqlxmc", "zqzq", "skssq", "zqksrq",
                               "zqts", "lrr", "lrrq", "tyrq", "jglxdm"};

                           /**
                            * ҳ�����ݼ�
                            */
    private java.util.List dataList = new java.util.ArrayList();

    /**
     * ѡ��������
     */
    private String[] tyCheckbox;

    /**
     * ά�����
     */
    private String whnf;

    /**
     * ������ʹ���
     */
    private String jglxdm;

    /**
     * ��ǰʱ��
     */
    private String strNow;

    /**
     * �������ʹ��룬��ʱʹ��
     */
    private String tempZqlxdm;

    public ZqlxwhForm ()
    {
        Calendar c = Calendar.getInstance();
        whnf = "" + c.get(Calendar.YEAR);
        //�õ���ǰʱ��
        strNow = SfDateUtil.getDate();
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
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

    public String getZqzq ()
    {
        return zqzq;
    }

    public void setZqzq (String zqzq)
    {
        this.zqzq = zqzq;
    }

    public String getSkssq ()
    {
        return skssq;
    }

    public void setSkssq (String skssq)
    {
        this.skssq = skssq;
    }

    public String getZqksrq ()
    {
        return zqksrq;
    }

    public void setZqksrq (String zqksrq)
    {
        this.zqksrq = zqksrq;
    }

    public String getZqts ()
    {
        return zqts;
    }

    public void setZqts (String zqts)
    {
        this.zqts = zqts;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
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

    public String[] getTyCheckbox ()
    {
        return tyCheckbox;
    }

    public void setTyCheckbox (String[] tyCheckbox)
    {
        this.tyCheckbox = tyCheckbox;
    }

    public String getTyrq ()
    {
        return tyrq;
    }

    public void setTyrq (String tyrq)
    {
        this.tyrq = tyrq;
    }

    public String getWhnf ()
    {
        return whnf;
    }

    public void setWhnf (String whnf)
    {
        this.whnf = whnf;
    }

    public String getJglxdm ()
    {
        return jglxdm;
    }

    public void setJglxdm (String jglxdm)
    {
        this.jglxdm = jglxdm;
    }

    public String getStrNow ()
    {
        return strNow;
    }

    public void setStrNow (String strNow)
    {
        this.strNow = strNow;
    }

    public String getTempZqlxdm ()
    {
        return tempZqlxdm;
    }

    public void setTempZqlxdm (String tempZqlxdm)
    {
        this.tempZqlxdm = tempZqlxdm;
    }
}