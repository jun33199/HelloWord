package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx22.web;

import java.util.List;

import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;

public class Basx22Form extends QysdsJmsbajlMainForm {
	/**
     * 
     */
    private static final long serialVersionUID = -4161613909822877797L;

    /*
     * ���
     */
    private String xh;

    /*
     * ���ܼ��ż���������Ŀ����
     */
    private String jnjpjsgzxmlx;

    /*
     * ���ܼ��ż���������Ŀ��������
     */
    private String jnjpjsgzxmmc;

    /*
     * ���ܼ��ż���������Ŀ���ʹ���
     */
    private String jnjpjsgzxmdm;
    /*
     * ���ܼ��ż���������Ŀ���ʹ���list
     */
    private List jnjpjsgzxmList;    


    /*
     * ȡ�õ�һ����������֤����������
     */
    private String dybzlmc;

    /*
     * ȡ�õ�һ��������Ӫ�����ʱ��
     */
    private String dybrq;

    /*
     * ������ʼ���
     */
    private String mzqsnd;

    /*
     * ������ֹ���
     */
    private String mzzznd;

    /*
     * ����������ʼ���
     */
    private String jbzsqsnd;

    /*
     * ��������ֹ���
     */
    private String jbzszznd;
    
    /**
     * �Ƿ�����ͬ��Դ������Ŀת�á�����
     */
    private String ZRHTXM="";
    /**
     * ��Ŀת�ú�ͬ����Ŀԭ�����Żݱ����ļ�
     */
    private String ZRHTXMYHWJ="";
    /**
     * ��Ŀת�ú�ͬ����Ŀԭ�����Ż�
     */
    private String ZRHTXMYH="";
    /*
     * 
     */
    private String ckbox;

    /*
     * �����ʾ
     */
    private String clbs = "0";

    /*
     * ���������б�
     */
    private List resultList;

    /*
     * ��������
     */
    private List resultList_wn;

    /*
     * 
     */
    private String checkedStr;

    /*
     * ������ȴ���
     */
    private String banddm;

    
    
    public String getZRHTXM() {
		return ZRHTXM;
	}

	public void setZRHTXM(String zRHTXM) {
		ZRHTXM = zRHTXM;
	}

	public String getZRHTXMYHWJ() {
		return ZRHTXMYHWJ;
	}

	public void setZRHTXMYHWJ(String zRHTXMYHWJ) {
		ZRHTXMYHWJ = zRHTXMYHWJ;
	}

	public String getZRHTXMYH() {
		return ZRHTXMYH;
	}

	public void setZRHTXMYH(String zRHTXMYH) {
		ZRHTXMYH = zRHTXMYH;
	}

	public String getBanddm() {
        return banddm;
    }

    public void setBanddm(String banddm) {
        this.banddm = banddm;
    }

    public String getCheckedStr() {
        return checkedStr;
    }

    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    public String getCkbox() {
        return ckbox;
    }

    public void setCkbox(String ckbox) {
        this.ckbox = ckbox;
    }

    public String getClbs() {
        return clbs;
    }

    public void setClbs(String clbs) {
        this.clbs = clbs;
    }

    public String getDybrq() {
        return dybrq;
    }

    public void setDybrq(String dybrq) {
        this.dybrq = dybrq;
    }

    public String getDybzlmc() {
        return dybzlmc;
    }

    public void setDybzlmc(String dybzlmc) {
        this.dybzlmc = dybzlmc;
    }
    public String getJnjpjsgzxmlx() {
		return jnjpjsgzxmlx;
	}

	public void setJnjpjsgzxmlx(String jnjpjsgzxmlx) {
		this.jnjpjsgzxmlx = jnjpjsgzxmlx;
	}

	public String getJnjpjsgzxmmc() {
		return jnjpjsgzxmmc;
	}

	public void setJnjpjsgzxmmc(String jnjpjsgzxmmc) {
		this.jnjpjsgzxmmc = jnjpjsgzxmmc;
	}

	public String getJnjpjsgzxmdm() {
		return jnjpjsgzxmdm;
	}

	public void setJnjpjsgzxmdm(String jnjpjsgzxmdm) {
		this.jnjpjsgzxmdm = jnjpjsgzxmdm;
	}

	public List getJnjpjsgzxmList() {
		return jnjpjsgzxmList;
	}

	public void setJnjpjsgzxmList(List jnjpjsgzxmList) {
		this.jnjpjsgzxmList = jnjpjsgzxmList;
	}

    public String getJbzsqsnd() {
		return jbzsqsnd;
	}

	public void setJbzsqsnd(String jbzsqsnd) {
		this.jbzsqsnd = jbzsqsnd;
	}

	public String getJbzszznd() {
		return jbzszznd;
	}

	public void setJbzszznd(String jbzszznd) {
		this.jbzszznd = jbzszznd;
	}

	public String getMzqsnd() {
        return mzqsnd;
    }

    public void setMzqsnd(String mzqsnd) {
        this.mzqsnd = mzqsnd;
    }

    public String getMzzznd() {
        return mzzznd;
    }

    public void setMzzznd(String mzzznd) {
        this.mzzznd = mzzznd;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public List getResultList_wn() {
        return resultList_wn;
    }

    public void setResultList_wn(List resultList_wn) {
        this.resultList_wn = resultList_wn;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }	

	
	


}