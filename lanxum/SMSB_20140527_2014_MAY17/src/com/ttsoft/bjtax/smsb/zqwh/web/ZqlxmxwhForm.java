/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ˰��������������ϸά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqlxmxwhForm
    extends BaseForm
{

    /**
     * �������ʹ��룬��ѯʹ��
     */
    private String headZqlxdm;

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
     * ������ʹ���
     */
    private String jglxdm;

    public ZqlxmxwhForm ()
    {
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getHeadZqlxdm ()
    {
        return headZqlxdm;
    }

    public void setHeadZqlxdm (String headZqlxdm)
    {
        this.headZqlxdm = headZqlxdm;
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

    public String getTyrq ()
    {
        return tyrq;
    }

    public void setTyrq (String tyrq)
    {
        this.tyrq = tyrq;
    }

    public String getJglxdm ()
    {
        return jglxdm;
    }

    public void setJglxdm (String jglxdm)
    {
        this.jglxdm = jglxdm;
    }

}