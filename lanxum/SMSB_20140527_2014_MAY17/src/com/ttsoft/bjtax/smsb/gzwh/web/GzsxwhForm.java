/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gzwh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��֪����ά��</p>
 * @author �������飭��ʯ�ҷ�
 * @version 1.1
 */
public class GzsxwhForm
    extends BaseForm
{

    public GzsxwhForm ()
    {
        //Ĭ��ֵ -> ѡ����������
        chooseTypeRadio = "1";
        dataList = new java.util.ArrayList();
        //�õ���ǰʱ��
        strNow = SfDateUtil.getDate();
    }
    private String  cxdqjs1;
    /**
     * ѡ������
     */
    private String chooseTypeRadio;
    /**
     * ��ѯʹ�õĵ�������
     */
    private String cxdqjs;

    /**
     * ��¼��
     */
    private String jlcount;

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * ��ҵ����
     */
    private String qylx;
    private String qylx1;

    /**
     * ��֪����
     */
    private String gzlx;

    /**
     * ��֪��ʼ����
     */
    private String gzqsrq;

    /**
     * ��֪��ֹ����
     */
    private String gzjzrq;

    /**
     * ��ҵ���
     */
    private String hylb;
    private String hylb1;
    /**
     * �Ķ�����
     */
    private String ydcs;

    /**
     * ��������
     */
    private String dqjs;

    /**
     * ��Ϸ�ʽ
     */
    private String jhfs;

    /**
     * ��������:���������
     */
    private String saveType;

    /**
     * ��ϸ��Ŀ����
     */
    private java.util.ArrayList dataList;

    /**
     * ��ϸ���������
     */
    private String mxJsjdm;

    /**
     * ��ϸ��˰������
     */
    private String mxNsrmc;

    /**
     * ��ϸ��֪����
     */
    private String mxGzsxxxxx;

    /**
     * ��ϸ��֪����
     */
    private String mxGzlx;

    /**
     * ��ϸ��֪��ʼ����
     */
    private String mxGzqsrq;

    /**
     * ��ϸ��֪��ֹ����
     */
    private String mxGzjzrq;

    /**
     * ɾ��ʹ�õ�check box��־
     */
    private String[] deleteCheckbox;

    /**
     * ��ϸ��ҵ����
     */
    private String mxQylx;

    /**
     * ��ϸ�б��ѡ��rodioֵ
     */
    private String mxChooseTypeRadio;

    /**
     * �޸ļ�¼�����
     */
    private String modifyIndex;

    /**
     * ��ҳ��ÿҳ��¼��
     */
    private int length;

    /**
     * ��ҳ����ǰҳ��
     */
    private int pgNum;

    /**
     * ��ҳ����ҳ��
     */
    private int pgSum;

    /**
     * ��ϸ��ҵ���
     */
    private String mxHylb;

    /**
     * ��ϸ��������
     */
    private String mxDqjs;

    /**
     * ��ϸ��Ϸ�ʽ
     */
    private String mxJhfs;

    /**
     * ��ϸ�б��radioֵ
     */
    private String mxChooseTypeRadioHidden;

    /**
     * ��ǰʱ��
     */
    private String strNow;

    /**
     * ��������������excel�ļ�
     */
    private org.apache.struts.upload.FormFile excelFile;

    /**
     * excel�ļ��еļ���������б�
     */
    private java.util.ArrayList jsjdmList = new ArrayList();

    ;

    /**
     * ����list
     */
    private java.util.ArrayList phList = new ArrayList();

    /**
     * ����
     */
    private String ph;
    /**
     * ���һ����˰�˵ĸ�֪����2009.4.7wcl���ӡ�
     * 
     */
    private List jsjdmgzsxlilst;
    /**
     * ��֪�������ѯ�����һ�������Ĳ�ѯ���2009.4.7wcl���ӡ�
     * 
     */
    private List tjgzsxlilst;
    /**
     * ��֪�������ѯ�еǼ���������2009.4.7wcl���ӡ�
     * 
     */
    private List nsrztlilst;
    /**
     * ��֪�������ѯ��������������2009.4.7wcl���ӡ�
     * 
     */
    private List scjxlilst;
    /**
     * ��֪�������ѯ��֪����˰���������2009.4.7wcl���ӡ�
     * 
     */
    private List swdwlilst;
    /**
     * ��֪�������ѯ��Χ˰���������2009.4.7wcl���ӡ�
     * 
     */
    private List cxswdwlilst;
    /**
     * ��֪�������ѯ����˰��״̬����2009.4.7wcl���ӡ�
     * 
     */
    private List djlxlilst;
    /**
     * ��֪�������ѯ����ҵ��������2009.4.7wcl���ӡ�
     * 
     */
    private List hylxlilst;
    /**
     * ��֪�������ѯ��ÿһ����֪�����Ӧ��id,2009.4.7wcl���ӡ�
     * 
     */
    private String gzsx_id;
    /**
     * ��֪�������Ѱ����˰��״̬����,2009.4.7wcl���ӡ�
     * 
     */
    private String nsrzt;
    /**
     * ��֪�������ѯ�и�֪����ı���,2009.4.7wcl���ӡ�
     * 
     */
    private String gzsxnrbt;
    /**
     * ��֪�������ѯ����������,2009.4.7wcl���ӡ�
     * 
     */
    private String jxdm;
    private String jxdm1;
    /**
     * ��֪�������ѯ�з�������ϸ����,2009.4.7wcl���ӡ�
     * 
     */
    private String fknr;
    /**
     * ��֪�������ѯ�л�ø�֪��ϸ����ϸ����,2009.4.7wcl���ӡ�
     * 
     */
    
    private String gzsxnr;
    public String getGzsx_id() {
		return gzsx_id;
	}

	public String getJxdm1() {
		return jxdm1;
	}

	public void setJxdm1(String jxdm1) {
		this.jxdm1 = jxdm1;
	}

	public void setGzsx_id(String gzsx_id) {
		this.gzsx_id = gzsx_id;
	}

	public List getJsjdmgzsxlilst() {
		return jsjdmgzsxlilst;
	}

	public void setJsjdmgzsxlilst(List jsjdmgzsxlilst) {
		this.jsjdmgzsxlilst = jsjdmgzsxlilst;
	}

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        pgNum = 0;
        pgSum = 0;
        length = 0;
    }

    public String getChooseTypeRadio ()
    {
        return chooseTypeRadio;
    }

    public void setChooseTypeRadio (String chooseTypeRadio)
    {
        this.chooseTypeRadio = chooseTypeRadio;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getQylx ()
    {
        return qylx;
    }

    public void setQylx (String qylx)
    {
        this.qylx = qylx;
    }

    public String getGzlx ()
    {
        return gzlx;
    }

    public void setGzlx (String gzlx)
    {
        this.gzlx = gzlx;
    }

    public String getGzqsrq ()
    {
        return gzqsrq;
    }

    public void setGzqsrq (String gzqsrq)
    {
        this.gzqsrq = gzqsrq;
    }

    public String getGzjzrq ()
    {
        return gzjzrq;
    }

    public void setGzjzrq (String gzjzrq)
    {
        this.gzjzrq = gzjzrq;
    }

    public java.util.ArrayList getDataList ()
    {
        return dataList;
    }

    public void setDataList (java.util.ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getSaveType ()
    {
        return saveType;
    }

    public void setSaveType (String saveType)
    {
        this.saveType = saveType;
    }

    public String getMxJsjdm ()
    {
        return mxJsjdm;
    }

    public void setMxJsjdm (String mxJsjdm)
    {
        this.mxJsjdm = mxJsjdm;
    }

    public String getMxNsrmc ()
    {
        return mxNsrmc;
    }

    public void setMxNsrmc (String mxNsrmc)
    {
        this.mxNsrmc = mxNsrmc;
    }

    public String getMxGzsxxxxx ()
    {
        return mxGzsxxxxx;
    }

    public void setMxGzsxxxxx (String mxGzsxxxxx)
    {
        this.mxGzsxxxxx = mxGzsxxxxx;
    }

    public String getMxGzlx ()
    {
        return mxGzlx;
    }

    public void setMxGzlx (String mxGzlx)
    {
        this.mxGzlx = mxGzlx;
    }

    public String getMxGzqsrq ()
    {
        return mxGzqsrq;
    }

    public void setMxGzqsrq (String mxGzqsrq)
    {
        this.mxGzqsrq = mxGzqsrq;
    }

    public String getMxGzjzrq ()
    {
        return mxGzjzrq;
    }

    public void setMxGzjzrq (String mxGzjzrq)
    {
        this.mxGzjzrq = mxGzjzrq;
    }

    public String[] getDeleteCheckbox ()
    {
        return deleteCheckbox;
    }

    public void setDeleteCheckbox (String[] deleteCheckbox)
    {
        this.deleteCheckbox = deleteCheckbox;
    }

    public String getMxQylx ()
    {
        return mxQylx;
    }

    public void setMxQylx (String mxQylx)
    {
        this.mxQylx = mxQylx;
    }

    public String getMxChooseTypeRadio ()
    {
        return mxChooseTypeRadio;
    }

    public void setMxChooseTypeRadio (String mxChooseTypeRadio)
    {
        this.mxChooseTypeRadio = mxChooseTypeRadio;
    }

    public String getModifyIndex ()
    {
        return modifyIndex;
    }

    public void setModifyIndex (String modifyIndex)
    {
        this.modifyIndex = modifyIndex;
    }

    public int getLength ()
    {
        return length;
    }

    public void setLength (int length)
    {
        this.length = length;
    }

    public int getPgNum ()
    {
        return pgNum;
    }

    public int getPgSum ()
    {
        return pgSum;
    }

    public void setPgNum (int pgNum)
    {
        this.pgNum = pgNum;
    }

    public void setPgSum (int pgSum)
    {
        this.pgSum = pgSum;
    }

    public String getHylb ()
    {
        return hylb;
    }

    public void setHylb (String hylb)
    {
        this.hylb = hylb;
    }

    public String getDqjs ()
    {
        return dqjs;
    }

    public void setDqjs (String dqjs)
    {
        this.dqjs = dqjs;
    }

    public String getJhfs ()
    {
        return jhfs;
    }

    public void setJhfs (String jhfs)
    {
        this.jhfs = jhfs;
    }

    public String getMxHylb ()
    {
        return mxHylb;
    }

    public void setMxHylb (String mxHylb)
    {
        this.mxHylb = mxHylb;
    }

    public String getMxDqjs ()
    {
        return mxDqjs;
    }

    public void setMxDqjs (String mxDqjs)
    {
        this.mxDqjs = mxDqjs;
    }

    public String getMxJhfs ()
    {
        return mxJhfs;
    }

    public void setMxJhfs (String mxJhfs)
    {
        this.mxJhfs = mxJhfs;
    }

    public String getMxChooseTypeRadioHidden ()
    {
        return mxChooseTypeRadioHidden;
    }

    public void setMxChooseTypeRadioHidden (String mxChooseTypeRadioHidden)
    {
        this.mxChooseTypeRadioHidden = mxChooseTypeRadioHidden;
    }

    public String getStrNow ()
    {
        return strNow;
    }

    public void setStrNow (String strNow)
    {
        this.strNow = strNow;
    }

    public org.apache.struts.upload.FormFile getExcelFile ()
    {
        return excelFile;
    }

    public void setExcelFile (org.apache.struts.upload.FormFile excelFile)
    {
        this.excelFile = excelFile;
    }

    public java.util.ArrayList getJsjdmList ()
    {
        return jsjdmList;
    }

    public void setJsjdmList (java.util.ArrayList jsjdmList)
    {
        this.jsjdmList = jsjdmList;
    }

    public java.util.ArrayList getPhList ()
    {
        return phList;
    }

    public void setPhList (java.util.ArrayList phList)
    {
        this.phList = phList;
    }

    public String getPh ()
    {
        return ph;
    }

    public void setPh (String ph)
    {
        this.ph = ph;
    }

	public List getTjgzsxlilst() {
		return tjgzsxlilst;
	}

	public void setTjgzsxlilst(List tjgzsxlilst) {
		this.tjgzsxlilst = tjgzsxlilst;
	}

	public String getNsrzt() {
		return nsrzt;
	}

	public void setNsrzt(String nsrzt) {
		this.nsrzt = nsrzt;
	}

	public String getGzsxnrbt() {
		return gzsxnrbt;
	}

	public void setGzsxnrbt(String gzsxnrbt) {
		this.gzsxnrbt = gzsxnrbt;
	}

	public String getJxdm() {
		return jxdm;
	}

	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}

	public String getFknr() {
		return fknr;
	}

	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	public List getHylxlilst() {
		return hylxlilst;
	}

	public void setHylxlilst(List hylxlilst) {
		this.hylxlilst = hylxlilst;
	}

	public List getDjlxlilst() {
		return djlxlilst;
	}

	public void setDjlxlilst(List djlxlilst) {
		this.djlxlilst = djlxlilst;
	}

	public List getNsrztlilst() {
		return nsrztlilst;
	}

	public void setNsrztlilst(List nsrztlilst) {
		this.nsrztlilst = nsrztlilst;
	}

	public List getScjxlilst() {
		return scjxlilst;
	}

	public void setScjxlilst(List scjxlilst) {
		this.scjxlilst = scjxlilst;
	}

	public List getSwdwlilst() {
		return swdwlilst;
	}

	public void setSwdwlilst(List swdwlilst) {
		this.swdwlilst = swdwlilst;
	}

	public String getGzsxnr() {
		return gzsxnr;
	}

	public void setGzsxnr(String gzsxnr) {
		this.gzsxnr = gzsxnr;
	}

	public String getJlcount() {
		return jlcount;
	}

	public void setJlcount(String jlcount) {
		this.jlcount = jlcount;
	}

	public String getYdcs() {
		return ydcs;
	}

	public void setYdcs(String ydcs) {
		this.ydcs = ydcs;
	}

	public List getCxswdwlilst() {
		return cxswdwlilst;
	}

	public void setCxswdwlilst(List cxswdwlilst) {
		this.cxswdwlilst = cxswdwlilst;
	}

	public String getCxdqjs() {
		return cxdqjs;
	}

	public void setCxdqjs(String cxdqjs) {
		this.cxdqjs = cxdqjs;
	}

	public String getCxdqjs1() {
		return cxdqjs1;
	}

	public void setCxdqjs1(String cxdqjs1) {
		this.cxdqjs1 = cxdqjs1;
	}

	public String getQylx1() {
		return qylx1;
	}

	public void setQylx1(String qylx1) {
		this.qylx1 = qylx1;
	}

	public String getHylb1() {
		return hylb1;
	}

	public void setHylb1(String hylb1) {
		this.hylb1 = hylb1;
	}

	
	
}