/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ¼��ӡ��˰������� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmlrForm
    extends BaseForm
{
    //��������

    /**
     * ����ƾ֤��
     */
    private String xspzh = "";

    /**
     * ���۵�λ���������
     */
    private String dsjsjdm = "";

    /**
     * ����ʱ��
     */
    private String cjsj = "";

    /**
     * ¼������
     */
    private String lrrq = "";

    /**
     * ���۵�λ����
     */
    private String dsdwmc = "";

    /**
     * ������Դ
     */
    private String sjly = "";

    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgdm = "";

    /**
     * ��ʽ����
     */
    private String fsdm = "";

    /**
     * ¼����
     */
    private String lrr = "";

    /**
     * �ϼƽ��
     */
    private String hjje = "";

    //�������ݣ����ܻ������ݣ�
    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh = "";

    /**
     * ��������
     */
    private String hzrq = "";

    /**
     * ������
     */
    private String hzr = "";

    /**
     * �ѻ��ܱ�ʾ
     */
    private String yhzbs = "";

    //�ӱ����ݣ�list֮��ģ�
    /**
     * ���������
     */
    private String jsjdm = "";

    /**
     * ��λ����
     */
    private String dwmc = "";

    /**
     * ������λ����
     */
    private String ghdwmc = "";

    /**
     * ����������
     */
    private String ghrxm = "";

    /**
     * ���ҵ�������
     */
    private String gjdqdm = "";

    /**
     * ֤�����ʹ���
     */
    private String zjlxdm = "";

    /**
     * ֤������
     */
    private String zjhm = "";

    /**
     *�ӱ����ݣ�list�����ģ�
     */
    private String columns[] =
        {
        "spmzdm", "spmzje", "gpsl", "je"};

    /**
     * ��ϸ��Ŀ����
     */
    private ArrayList dataList = new ArrayList();

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        //��������
        xspzh = "";
        dsjsjdm = "";
        lrrq = "";
        cjsj = "";
        dsdwmc = "";
        sjly = "0"; //¼��
        swjgzzjgdm = "";
        fsdm = "1"; //����
        lrr = "";
        hjje = "";
        //�������ݣ����ܻ������ݣ�
        jkpzh = "";
        hzrq = "";
        hzr = "";
        yhzbs = "";

        //�ӱ����ݣ�list֮��ģ�
        jsjdm = "";
        dwmc = "";
        ghdwmc = "";
        ghrxm = "";
        gjdqdm = "";
        zjlxdm = "02";
        zjhm = "";

        dataList.clear();
    }

    public String getCjsj ()
    {
        return cjsj;
    }

    public void setCjsj (String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String getDsdwmc ()
    {
        return dsdwmc;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public String getDwmc ()
    {
        return dwmc;
    }

    public String getGhdwmc ()
    {
        return ghdwmc;
    }

    public String getGhrxm ()
    {
        return ghrxm;
    }

    public String getFsdm ()
    {
        return fsdm;
    }

    public String getGjdqdm ()
    {
        return gjdqdm;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public String getHzr ()
    {
        return hzr;
    }

    public String getHzrq ()
    {
        return hzrq;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public String getSjly ()
    {
        return sjly;
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public String getXspzh ()
    {
        return xspzh;
    }

    public String getYhzbs ()
    {
        return yhzbs;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public String getZjlxdm ()
    {
        return zjlxdm;
    }

    public void setZjlxdm (String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public void setYhzbs (String yhzbs)
    {
        this.yhzbs = yhzbs;
    }

    public void setXspzh (String xspzh)
    {
        this.xspzh = xspzh;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSjly (String sjly)
    {
        this.sjly = sjly;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setHzrq (String hzrq)
    {
        this.hzrq = hzrq;
    }

    public void setHzr (String hzr)
    {
        this.hzr = hzr;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public void setGjdqdm (String gjdqdm)
    {
        this.gjdqdm = gjdqdm;
    }

    public void setFsdm (String fsdm)
    {
        this.fsdm = fsdm;
    }

    public void setDwmc (String dwmc)
    {
        this.dwmc = dwmc;
    }

    public void setGhdwmc (String ghdwmc)
    {
        this.ghdwmc = ghdwmc;
    }

    public void setGhrxm (String ghrxm)
    {
        this.ghrxm = ghrxm;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public void setDsdwmc (String dsdwmc)
    {
        this.dsdwmc = dsdwmc;
    }

}