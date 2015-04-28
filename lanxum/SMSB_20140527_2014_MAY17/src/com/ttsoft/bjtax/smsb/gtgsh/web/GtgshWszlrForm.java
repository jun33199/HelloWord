/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻���˰֤¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshWszlrForm
    extends BaseForm
{
    /**
     * ҳ�����ݼ�
     */
    private List dataList = new ArrayList();

    /**
     * ҳ����ʾ�б�Ԫ��
     */
    private String columns[] =
        {
        "szsmdm", "szdm", "szmc", "szsmmc",
        "kssl", "jsje", "sl", "yjhkc", "sjse",
        "skssksrq", "skssjsrq", "jzbz", "yskmdm",
        "ysjcdm"};

    /**
     * ��˰�˼��������
     */
    private String nsrjsjdm; //��˰�˼��������

    /**
     * ��˰������
     */
    private String nsrmc; //��˰������

    /**
     * ֤�����ʹ���
     */
    private String zjlxdm; //֤�����ʹ���

    /**
     * ֤������
     */
    private String zjhm; //֤������

    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgdm; //˰�������֯�ṹ����

    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgmc; //˰�������֯�ṹ����

    /**
     * ��˰֤��
     */
    private String wszh; //��˰֤��

    /**
     * �ʻ�����
     */
    private String zhdm; //�ʻ�����

    /**
     * Ʊ֤�������
     */
    private String pzzldm; //Ʊ֤�������

    /**
     * �ϼ�ʵ�ɽ��
     */
    private String hjsjje; //�ϼ�ʵ�ɽ��

    /**
     * ¼������
     */
    private String lrrq; //¼������

    /**
     * ¼����
     */
    private String lrr; //¼����

    /**
     * ���ұ�׼��ҵ����
     */
    private String gjbzhydm; //���ұ�׼��ҵ����

    /**
     * �Ǽ�ע�����ʹ���
     */
    private String djzclxdm; //�Ǽ�ע�����ʹ���

    /**
     * �Ǽ�ע����������
     */
    private String djzclxmc; //�Ǽ�ע����������

    /**
     * ��ַ
     */
    private String dz; //��ַ

    /**
     * ���������
     */
    private String jsjdm; //���������

    /**
     * ˰�������֯�ṹ���� 2
     */
    private String swjgzzjgdm2; //˰�������֯�ṹ���� 2

    /**
     * ����ű���Ϣ
     */
    private String scriptStr; //����ű���Ϣ

    /**
     * ֤����������
     */
    private String zjlxmc; //֤����������

    /**
     * ����ֱ�
     */
    private String ndzb; //����ֱ�

    /**
     * ��˰��״̬
     */
    private String nsrzt; //��˰��״̬

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getNsrjsjdm ()
    {
        return nsrjsjdm;
    }

    public void setNsrjsjdm (String nsrjsjdm)
    {
        this.nsrjsjdm = nsrjsjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getZjlxdm ()
    {
        return zjlxdm;
    }

    public void setZjlxdm (String zjlxdm)
    {
        this.zjlxdm = zjlxdm;
    }

    public String getZjhm ()
    {
        return zjhm;
    }

    public void setZjhm (String zjhm)
    {
        this.zjhm = zjhm;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Show";
        nsrjsjdm = "";
        nsrmc = "";
        zjlxdm = "";
        zjhm = "";
        hjsjje = "";
        swjgzzjgdm2 = "";
        gjbzhydm = "";
        djzclxdm = "";
        djzclxmc = "";
        dz = "";

        dataList = new ArrayList();
        this.nsrzt = CodeConstant.NSRZT_ZC;
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public String getSwjgzzjgmc ()
    {
        return swjgzzjgmc;
    }

    public void setSwjgzzjgmc (String swjgzzjgmc)
    {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public String getWszh ()
    {
        return wszh;
    }

    public void setWszh (String wszh)
    {
        this.wszh = wszh;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public String getZhdm ()
    {
        return zhdm;
    }

    public void setZhdm (String zhdm)
    {
        this.zhdm = zhdm;
    }

    public String getPzzldm ()
    {
        return pzzldm;
    }

    public void setPzzldm (String pzzldm)
    {
        this.pzzldm = pzzldm;
    }

    public String getHjsjje ()
    {
        return hjsjje;
    }

    public void setHjsjje (String hjsjje)
    {
        this.hjsjje = hjsjje;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getSwjgzzjgdm2 ()
    {
        return swjgzzjgdm2;
    }

    public void setSwjgzzjgdm2 (String swjgzzjgdm2)
    {
        this.swjgzzjgdm2 = swjgzzjgdm2;
    }

    public String getGjbzhydm ()
    {
        return gjbzhydm;
    }

    public void setGjbzhydm (String gjbzhydm)
    {
        this.gjbzhydm = gjbzhydm;
    }

    public String getDjzclxdm ()
    {
        return djzclxdm;
    }

    public void setDjzclxdm (String djzclxdm)
    {
        this.djzclxdm = djzclxdm;
    }

    public String getDjzclxmc ()
    {
        return djzclxmc;
    }

    public void setDjzclxmc (String djzclxmc)
    {
        this.djzclxmc = djzclxmc;
    }

    public String getDz ()
    {
        return dz;
    }

    public void setDz (String dz)
    {
        this.dz = dz;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getZjlxmc ()
    {
        return zjlxmc;
    }

    public void setZjlxmc (String zjlxmc)
    {
        this.zjlxmc = zjlxmc;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getNdzb ()
    {
        return ndzb;
    }

    public void setNdzb (String ndzb)
    {
        this.ndzb = ndzb;
    }

    public String getNsrzt ()
    {
        return nsrzt;
    }

    public void setNsrzt (String nsrzt)
    {
        this.nsrzt = nsrzt;
    }
}