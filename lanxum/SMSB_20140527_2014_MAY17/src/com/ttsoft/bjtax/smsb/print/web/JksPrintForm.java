/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ӡ�ɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class JksPrintForm
    extends BaseForm
{
	//���水ť�ǲ�����ʾ��true:��ʾ;false:����ʾ��
	private boolean saveBtnVisible=true ; 
	
	
	//�޽������Ƿ�ɱ༭��true:�ɱ༭;false:���ɱ༭��
	private boolean xjrqEdit=true ; 

	
	//�Ƿ�ȡ��ע��true:��ʾ;false:����ʾ��
	private boolean bzVisible=false ; 
	
    /**
     * ҳ�����ݼ�
     */
    private List dataList = new ArrayList();

    /**
     * ¼����
     */
    private String lrr; //¼����

    /**
     * �ɿ�ƾ֤��
     */
    private String headJkpzh; //�ɿ�ƾ֤��

    /**
     * ���������
     */
    private String headJsjdm; //���������

    /**
     * ������ϵ
     */
    private String headLsgx; //������ϵ

    /**
     * ���ջ�������
     */
    private String headZsjgmc; //���ջ�������

    /**
     * ������������
     */
    private String headZclxmc; //������������

    /**
     * �����
     */
    private String headTfrq; //�����

    /**
     * ����
     */
    private String dm; //�ɿλ��Ϣ������

    /**
     * �绰
     */
    private String dh; //�ɿλ��Ϣ���绰

    /**
     * ȫ��
     */
    private String qc; //�ɿλ��Ϣ��ȫ��

    /**
     * �˺�
     */
    private String zh; //�ɿλ��Ϣ���˺�

    /**
     * ˰��������ʼ����
     */
    private String skssksrq; //˰��������ʼ����

    /**
     * ˰��������������
     */
    private String skssjsrq; //˰��������������

    /**
     * Ԥ���Ŀ����
     */
    private String yskmdm; //Ԥ���Ŀ����

    /**
     * ���Ŀ����
     */
    private String yskmmc; //���Ŀ����

    /**
     * Ԥ�㼶������
     */
    private String yskmjc; //Ԥ�㼶������

    /**
     * ˰�����
     */
    private String skgk; //˰�����

    /**
     * ˰���޽�����
     */
    private String skxjrq; //˰���޽�����

    /**
     * �ϼƽ��
     */
    private String hjje; //�ϼƽ��

    /**
     * �ɿλ
     */
    private String jkdw; //�ɿλ��==qc

    /**
     * �ط�˰�����
     */
    private String dfswjg; //�ط�˰����� ==headZsjgmc

    /**
     * ��Ʊ��
     */
    private String tpr; //��Ʊ��

    /**
     * ����
     */
    private String bm; //����

    /**
     * �ϼƽ���д
     */
    private String hjjedx; //�ϼƽ���д

    /**
     * ��ע
     */
    private String bz; //��ע

    /**
     * ��������
     */
    private String khyh; //��������

    /**
     * ˰��˰Ŀ����
     */
    private String szsmdm; //˰��˰Ŀ����

    /**
     * �걨��������Դ
     */
    private String headSjly; //�걨��������Դ

    /**
     * ˰������
     */
    private String szdm; //˰������

    /**
     * ��ϸƷĿ����
     */
    private String mxPmmc; //��ϸƷĿ����

    /**
     * ��ϸ��˰����
     */
    private String mxKssl; //��ϸ��˰����

    /**
     * ��ϸ��˰���
     */
    private String mxJsje; //��ϸ��˰���

    /**
     * ��ϸ��˰��
     */
    private String mxSl; //��ϸ��˰��

    /**
     * ��ϸʵ��˰��
     */
    private String mxSjse; //��ϸʵ��˰��

    /**
     * ��ϸ�ֳɱ���
     */
    private String mxFcbl; //��ϸ�ֳɱ���
    
    /**
     * ˰������
     */
    private String sklx;

    /**
     * �ɱ༭��˰��������ʼ����
     */
    private String editSkssksrq;

    /**
     * �ɱ༭��˰��������������
     */
    private String editSkssjsrq;

    /**
     * ˰������
     */
    private String editSkxjrq; //˰������
    
    /**
     * �������
     */
    private String headTfrqn; //�������
    
    /**
     * �������
     */
    private String headTfrqy; //�������
    
    /**
     * �������
     */
    private String headTfrqr; //�������

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        dataList = new ArrayList();
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getHeadJkpzh ()
    {
        return headJkpzh;
    }

    public void setHeadJkpzh (String headJkpzh)
    {
        this.headJkpzh = headJkpzh;
    }

    public String getHeadJsjdm ()
    {
        return headJsjdm;
    }

    public void setHeadJsjdm (String headJsjdm)
    {
        this.headJsjdm = headJsjdm;
    }

    public String getHeadLsgx ()
    {
        return headLsgx;
    }

    public void setHeadLsgx (String headLsgx)
    {
        this.headLsgx = headLsgx;
    }

    public String getHeadZsjgmc ()
    {
        return headZsjgmc;
    }

    public void setHeadZsjgmc (String headZsjgmc)
    {
        this.headZsjgmc = headZsjgmc;
    }

    public String getHeadZclxmc ()
    {
        return headZclxmc;
    }

    public void setHeadZclxmc (String headZclxmc)
    {
        this.headZclxmc = headZclxmc;
    }

    public String getHeadTfrq ()
    {
        return headTfrq;
    }

    public void setHeadTfrq (String headTfrq)
    {
        this.headTfrq = headTfrq;
    }

    public String getDm ()
    {
        return dm;
    }

    public void setDm (String dm)
    {
        this.dm = dm;
    }

    public String getDh ()
    {
        return dh;
    }

    public void setDh (String dh)
    {
        this.dh = dh;
    }

    public String getQc ()
    {
        return qc;
    }

    public void setQc (String qc)
    {
        this.qc = qc;
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

    public String getYskmjc ()
    {
        return yskmjc;
    }

    public void setYskmjc (String yskmjc)
    {
        this.yskmjc = yskmjc;
    }

    public String getSkgk ()
    {
        return skgk;
    }

    public void setSkgk (String skgk)
    {
        this.skgk = skgk;
    }

    public String getSkxjrq ()
    {
        return skxjrq;
    }

    public void setSkxjrq (String skxjrq)
    {
        this.skxjrq = skxjrq;
    }

    public String getHjje ()
    {
        return hjje;
    }

    public String getJkdw ()
    {
        return jkdw;
    }

    public void setJkdw (String jkdw)
    {
        this.jkdw = jkdw;
    }

    public String getDfswjg ()
    {
        return dfswjg;
    }

    public void setDfswjg (String dfswjg)
    {
        this.dfswjg = dfswjg;
    }

    public String getTpr ()
    {
        return tpr;
    }

    public void setTpr (String tpr)
    {
        this.tpr = tpr;
    }

    public String getBm ()
    {
        return bm;
    }

    public void setBm (String bm)
    {
        this.bm = bm;
    }

    public String getHjjedx ()
    {
        return hjjedx;
    }

    public void setHjjedx (String hjjedx)
    {
        this.hjjedx = hjjedx;
    }

    public String getBz ()
    {
        return bz;
    }

    public void setBz (String bz)
    {
        this.bz = bz;
    }

    public String getKhyh ()
    {
        return khyh;
    }

    public void setKhyh (String khyh)
    {
        this.khyh = khyh;
    }

    public String getSzsmdm ()
    {
        return szsmdm;
    }

    public void setSzsmdm (String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setHjje (String hjje)
    {
        this.hjje = hjje;
    }

    public String getHeadSjly ()
    {
        return headSjly;
    }

    public void setHeadSjly (String headSjly)
    {
        this.headSjly = headSjly;
    }

    public String getSzdm ()
    {
        return szdm;
    }

    public void setSzdm (String szdm)
    {
        this.szdm = szdm;
    }

    public String getMxPmmc ()
    {
        return mxPmmc;
    }

    public void setMxPmmc (String mxPmmc)
    {
        this.mxPmmc = mxPmmc;
    }

    public String getMxKssl ()
    {
        return mxKssl;
    }

    public void setMxKssl (String mxKssl)
    {
        this.mxKssl = mxKssl;
    }

    public String getMxJsje ()
    {
        return mxJsje;
    }

    public void setMxJsje (String mxJsje)
    {
        this.mxJsje = mxJsje;
    }

    public String getMxSjse ()
    {
        return mxSjse;
    }

    public void setMxSjse (String mxSjse)
    {
        this.mxSjse = mxSjse;
    }

    public String getMxSl ()
    {
        return mxSl;
    }

    public void setMxSl (String mxSl)
    {
        this.mxSl = mxSl;
    }

    public String getMxFcbl ()
    {
        return mxFcbl;
    }

    public void setMxFcbl (String mxFcbl)
    {
        this.mxFcbl = mxFcbl;
    }
    
    public String getSklx ()
    {
        return sklx;
    }

    public void setSklx (String sklx)
    {
        this.sklx = sklx;
    }

    public String getEditSkssksrq ()
    {
        return editSkssksrq;
    }

    public void setEditSkssksrq (String editSkssksrq)
    {
        this.editSkssksrq = editSkssksrq;
    }

    public String getEditSkssjsrq ()
    {
        return editSkssjsrq;
    }

    public void setEditSkssjsrq (String editSkssjsrq)
    {
        this.editSkssjsrq = editSkssjsrq;
    }

    public String getEditSkxjrq ()
    {
        return editSkxjrq;
    }

    public void setEditSkxjrq (String editSkxjrq)
    {
        this.editSkxjrq = editSkxjrq;
    }
    
    public String getHeadTfrqn ()
    {
        return headTfrqn;
    }

    public void setHeadTfrqn (String headTfrqn)
    {
        this.headTfrqn = headTfrqn;
    }
    public String getHeadTfrqy ()
    {
        return headTfrqy;
    }

    public void setHeadTfrqy (String headTfrqy)
    {
        this.headTfrqy = headTfrqy;
    }

    public String getHeadTfrqr ()
    {
        return headTfrqr;
    }

    public void setHeadTfrqr (String headTfrqr)
    {
        this.headTfrqr = headTfrqr;
    }

	public boolean isSaveBtnVisible() {
		return saveBtnVisible;
	}

	public void setSaveBtnVisible(boolean saveBtnVisible) {
		this.saveBtnVisible = saveBtnVisible;
	}

	public boolean isXjrqEdit() {
		return xjrqEdit;
	}

	public void setXjrqEdit(boolean xjrqEdit) {
		this.xjrqEdit = xjrqEdit;
	}

	public boolean isBzVisible() {
		return bzVisible;
	}

	public void setBzVisible(boolean bzVisible) {
		this.bzVisible = bzVisible;
	}


    
}
