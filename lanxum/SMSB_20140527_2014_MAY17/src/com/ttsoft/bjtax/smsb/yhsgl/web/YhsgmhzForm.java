/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰������� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmhzForm
    extends BaseForm
{
    /**
     * �Ƿ�����
     */
    private String lw = "00";

    /**
     *������ʼ����
     */
    private String hzqsrq = "";

    /**
     *���ܽ�������
     */
    private String hzjsrq = "";

    /**
     *���ܶ���
     */
    private String hzdx = "";

    /**
     *���ܶ�������
     */
    private String hzdxmc = "";

    /**
     *�ɿ�ƾ֤��
     */
    private String jkpzh = "";

    /**
     * �걨���
     */
    private String sbbh = "";

    /**
     *ʵ�ɽ��
     */
    private String sjje = "";

    /**
     *ӡ��˰������Ա
     */
    private String yhsxsry = "";

    /**
     *���۵�λ���������
     */
    private String dsjsjdm = "";

    /**
     *¼����
     */
    private String lrr = "";

    /**
     *��˰������
     */
    private String nsrmc = "";

    /**
     *���ջ��ش���
     */
    private String zsjgdm = "";

    /**
     * ��ϸ��Ŀ����
     */
    private ArrayList dataList = new ArrayList();

    /**
     *��ǰʱ��
     */
    private String strNow;

    public YhsgmhzForm ()
    {
        //�õ���ǰʱ��
        strNow = SfDateUtil.getDate();
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        hzqsrq = "";
        hzjsrq = "";
        hzdx = "0";
        hzdxmc = "";
        jkpzh = "";
        sjje = "";
        yhsxsry = "";
        dsjsjdm = "";
        lrr = "";
        nsrmc = "";
        zsjgdm = "";
    }

    public String getHzdx ()
    {
        return hzdx;
    }

    public String getHzjsrq ()
    {
        return hzjsrq;
    }

    public String getHzqsrq ()
    {
        return hzqsrq;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getHzdxmc ()
    {
        return hzdxmc;
    }

    public String getSjje ()
    {
        return sjje;
    }

    public String getYhsxsry ()
    {
        return yhsxsry;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setSjje (String sjje)
    {
        this.sjje = sjje;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setHzdxmc (String hzdxmc)
    {
        this.hzdxmc = hzdxmc;
    }

    public void setHzqsrq (String hzqsrq)
    {
        this.hzqsrq = hzqsrq;
    }

    public void setHzjsrq (String hzjsrq)
    {
        this.hzjsrq = hzjsrq;
    }

    public void setHzdx (String hzdx)
    {
        this.hzdx = hzdx;
    }

    public void setYhsxsry (String yhsxsry)
    {
        this.yhsxsry = yhsxsry;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getZsjgdm ()
    {
        return zsjgdm;
    }

    public void setZsjgdm (String zsjgdm)
    {
        this.zsjgdm = zsjgdm;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getStrNow ()
    {
        return strNow;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getLw() {
        return lw;
    }

    public void setStrNow (String strNow)
    {
        this.strNow = strNow;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setLw(String lw) {
        this.lw = lw;
    }
}
