/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ���սɿ���¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsJkslrForm
    extends BaseForm
{

    /**
     * ��ϸ��Ŀ���ݼ�
     */
    private List dataList = new ArrayList();

    /**
     * ��ϸ�б����ݼ�
     */
    private String[] columns =
        {"szsmdm", "szsmmc", "kssl", "jsje", "sjse",
        "skssksrq", "skssjsrq", "szdm", "szmc"};

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
     * ˰�������֯��������
     */
    private String swjgzzjgdm; //˰�������֯��������

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgmc; //˰�������֯��������

    /**
     * �ϼ�ʵ�ɽ��
     */
    private String hjsjje; //�ϼ�ʵ�ɽ��

    /**
     * �걨����
     */
    private String sbrq; //�걨����

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
     * ˰�����ʹ���
     */
    private String sklxdm;

    /**
     * ˰����������
     */
    private String sklxmc;

    /**
     * ǰ̨��ʾѡ���б�ʱʹ�õ�����js����
     */
    private String scriptStr; //ǰ̨��ʾѡ���б�ʱʹ�õ�����js����

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
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
        this.dataList.clear();
        this.nsrmc = "";
        this.zjhm = "";
        this.zjlxdm = "02";
        this.dataList = new ArrayList();
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

    public String getHjsjje ()
    {
        return hjsjje;
    }

    public void setHjsjje (String hjsjje)
    {
        this.hjsjje = hjsjje;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
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

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

	public String getSklxdm() {
		return sklxdm;
	}

	public void setSklxdm(String sklxdm) {
		this.sklxdm = sklxdm;
	}

	public String getSklxmc() {
		return sklxmc;
	}

	public void setSklxmc(String sklxmc) {
		this.sklxmc = sklxmc;
	}
}
