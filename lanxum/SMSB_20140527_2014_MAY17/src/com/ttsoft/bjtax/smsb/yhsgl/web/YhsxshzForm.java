/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title:  ������˰��������ϵͳ���������걨</p>
 * <p>Description: ������۵�λӡ��˰����������� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsxshzForm
    extends BaseForm
{
    /**
     * ���۵�λ���������
     */
    private String dsjsjdm = "";

    /**
     * ���۵�λ����
     */
    private String dsdwmc = "";

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh = "";

    /**
     * ʵ�ɽ��
     */
    private String sjse = "";

    public String getDsdwmc ()
    {
        return this.dsdwmc;
    }

    public String getJkpzh ()
    {
        return this.jkpzh;
    }

    public String getSjse ()
    {
        return this.sjse;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSjse (String sjse)
    {
        this.sjse = sjse;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }
}