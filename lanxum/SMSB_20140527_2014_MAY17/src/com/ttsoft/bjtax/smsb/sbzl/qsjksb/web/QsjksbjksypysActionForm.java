/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: һƱһ˰�ɿ���ά����</p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbjksypysActionForm
    extends BaseForm
{
    private String[] columns =
        {
        "szsmdm", "szsmmc", "kssl", "jsje", "sjse"};

    //�걨����
    //��������
    //���д���
    //�˺�
    //��λ����
    /**
     * ��ϸ�����б�
     */
    private java.util.ArrayList dataList = new ArrayList();

    /**
     * ��λ����
     */
    private String dwmc;

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * ��������
     */
    private String yhmc;

    /**
     * �ʺ�
     */
    private String zh;

    /**
     * ˰��������ʼ����
     */
    private String skssksrq;

    /**
     * ˰��������������
     */
    private String skssjsrq;

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh;

    /**
     * ¼������
     */
    private String lrrq;

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgmc;

    /**
     * ˰������
     */
    private String szmc;

    /**
     * ��Ӫ��ַ��ϵ�绰
     */
    private String jydzlxdm;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * Ԥ���Ŀ����
     */
    private String yskmdm;

    /**
     * Ԥ���Ŀ����
     */
    private String yskmmc;

    /**
     * Ԥ�㼶������
     */
    private String ysjcmc;

    /**
     * ������֯��������
     */
    private String gkzzjgmc;

    /**
     * �޽�����
     */
    private String xjrq;

    /**
     * �걨���
     */
    private String sbbh;

    /**
     * �м��ֳ�
     */
    private String sjfc;

    /**
     * �����ֳ�
     */
    private String qjfc;

    /**
     * Ԥ�㼶�δ���
     */
    private String ysjcdm;

    /**
     * js����
     */
    private String scriptStr;

    /**
     * ʵ�ɽ��
     */
    private String sjje;

    /**
     * ��תҳ��
     */
    private String forward;

    /**
     * ��������
     */
    private String sjly;

    /**
     * ����ʱ�����ж��Ƿ񷵻��걨���
     */
    private String presbbh;

    /**
     * �ϼƽ���д
     */
    private String hjjedx;

    /**
     * �ϼƽ��
     */
    private String hjje;
    /**
     * ������ϵ
     */
    private String lsgx;
    /**
     * ע����������
     */
    private String zclxmc;
    /**
     * ��ϸƷĿ����
     */
    private String mxPmmc;
    /**
     * ��ϸ��˰����
     */
    private String mxKssl;
    /**
     * ��ϸ��˰���
     */
    private String mxJsje;
    /**
     * ��ϸ��˰��
     */
    private String mxSl;
    /**
     * ��ϸʵ��˰��
     */
    private String mxSjse;
    /**
     * ��ϸ�ֳɱ���
     */
    private String mxFcbl;    
    /**
     * ˰������
     */
    private String sklx;
    /**
     * ˰������
     */
    private String skgkh;
    /**
     * �걨����
     */
    private String sbrq;
    /**
     * �ط�˰�����
     */
    private String dfswjg;
    /**
     * ����
     */
    private String bz;
    
    /**
     * �������
     */
    private String tfrqn;
    
    /**
     * �������
     */
    private String tfrqy; 
    
    /**
     * �������
     */
    private String tfrqr;     

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        /**@todo: finish this method, this is just the skeleton.*/
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Query";
    }

    public String getDwmc ()
    {
        return dwmc;
    }

    public void setDwmc (String dwmc)
    {
        this.dwmc = dwmc;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public java.util.ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getYhmc ()
    {
        return yhmc;
    }

    public void setYhmc (String yhmc)
    {
        this.yhmc = yhmc;
    }

    public String getZh ()
    {
        return zh;
    }

    public void setZh (String zh)
    {
        this.zh = zh;
    }

    public String getSkssksrq ()
    {
        return skssksrq;
    }

    public void setSkssksrq (String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String getSkssjsrq ()
    {
        return skssjsrq;
    }

    public void setSkssjsrq (String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getSwjgzzjgmc ()
    {
        return swjgzzjgmc;
    }

    public void setSwjgzzjgmc (String swjgzzjgmc)
    {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public String getSzmc ()
    {
        return szmc;
    }

    public void setSzmc (String szmc)
    {
        this.szmc = szmc;
    }

    public String getJydzlxdm ()
    {
        return jydzlxdm;
    }

    public void setJydzlxdm (String jydzlxdm)
    {
        this.jydzlxdm = jydzlxdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getYskmdm ()
    {
        return yskmdm;
    }

    public void setYskmdm (String yskmdm)
    {
        this.yskmdm = yskmdm;
    }

    public String getYskmmc ()
    {
        return yskmmc;
    }

    public void setYskmmc (String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public String getYsjcmc ()
    {
        return ysjcmc;
    }

    public void setYsjcmc (String ysjcmc)
    {
        this.ysjcmc = ysjcmc;
    }

    public String getGkzzjgmc ()
    {
        return gkzzjgmc;
    }

    public void setGkzzjgmc (String gkzzjgmc)
    {
        this.gkzzjgmc = gkzzjgmc;
    }

    public String getXjrq ()
    {
        return xjrq;
    }

    public void setXjrq (String xjrq)
    {
        this.xjrq = xjrq;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

    public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }

    public String getSjfc ()
    {
        return sjfc;
    }

    public void setSjfc (String sjfc)
    {
        this.sjfc = sjfc;
    }

    public String getQjfc ()
    {
        return qjfc;
    }

    public void setQjfc (String qjfc)
    {
        this.qjfc = qjfc;
    }

    public String getYsjcdm ()
    {
        return ysjcdm;
    }

    public void setYsjcdm (String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getSjje ()
    {
        return sjje;
    }

    public void setSjje (String sjje)
    {
        this.sjje = sjje;
    }

    public String getForward ()
    {
        return forward;
    }

    public void setForward (String forward)
    {
        this.forward = forward;
    }

    public String getSjly ()
    {
        return sjly;
    }

    public void setSjly (String sjly)
    {
        this.sjly = sjly;
    }

    public String getPresbbh ()
    {
        return presbbh;
    }

    public void setPresbbh (String presbbh)
    {
        this.presbbh = presbbh;
    }

    public String getHjjedx ()
    {
        return hjjedx;
    }

    public void setHjjedx (String hjjedx)
    {
        this.hjjedx = hjjedx;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public String getLsgx ()
    {
        return lsgx;
    }

    public void setLsgx (String lsgx)
    {
        this.lsgx = lsgx;
    }

    public String getZclxmc ()
    {
        return zclxmc;
    }

    public void setZclxmc (String zclxmc)
    {
        this.zclxmc = zclxmc;
    }

    public String getMxJsje ()
    {
        return mxJsje;
    }

    public String getMxKssl ()
    {
        return mxKssl;
    }

    public String getMxPmmc ()
    {
        return mxPmmc;
    }

    public String getMxSjse ()
    {
        return mxSjse;
    }

    public String getMxSl ()
    {
        return mxSl;
    }

    public String getMxFcbl ()
    {
        return mxFcbl;
    }

    public void setMxFcbl (String mxFcbl)
    {
        this.mxFcbl = mxFcbl;
    }
    
    public void setMxSl (String mxSl)
    {
        this.mxSl = mxSl;
    }

    public void setMxSjse (String mxSjse)
    {
        this.mxSjse = mxSjse;
    }

    public void setMxPmmc (String mxPmmc)
    {
        this.mxPmmc = mxPmmc;
    }

    public void setMxKssl (String mxKssl)
    {
        this.mxKssl = mxKssl;
    }

    public void setMxJsje (String mxJsje)
    {
        this.mxJsje = mxJsje;
    }

    public String getSklx ()
    {
        return sklx;
    }

    public void setSklx (String sklx)
    {
        this.sklx = sklx;
    }

    public String getSkgkh ()
    {
        return skgkh;
    }

    public void setSkgkh (String skgkh)
    {
        this.skgkh = skgkh;
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getDfswjg ()
    {
        return dfswjg;
    }

    public void setDfswjg (String dfswjg)
    {
        this.dfswjg = dfswjg;
    }

    public String getBz ()
    {
        return bz;
    }

    public void setBz (String bz)
    {
        this.bz = bz;
    }

    public String getTfrqn ()
    {
        return tfrqn;
    }

    public void setTfrqn (String tfrqn)
    {
        this.tfrqn = tfrqn;
    }
    
    public String getTfrqy ()
    {
        return tfrqy;
    }

    public void setTfrqy (String tfrqy)
    {
        this.tfrqy = tfrqy;
    }
    
    public String getTfrqr ()
    {
        return tfrqr;
    }

    public void setTfrqr (String tfrqr)
    {
        this.tfrqr = tfrqr;
    }
     
}