package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author �Բ�
 * @version 1.0
 */
public class SbGrBo implements Serializable {
    /**
     * �걨����
     */
    public Timestamp sbrq;

    /**
     * ��¼��ʾ
     */
    public boolean bl;

    /**
     * ��˰�걨���
     */
    public String sbbh;
    /**
     * 20081125,modify by fujx,���ӽ�ίҵ����
     */
    private String jwywbh;

    /**
     * 20081125,modify by fujx,���Ӻ�ͬ���
     */
    private String htbh;


    /**
     * �ɿʽ
     */
    public String jkfsdm;

    public String jkfsmc;

    /**
     * �������ط����������
     */
    public String fcjslh;

    /**
     * ��˰������
     */
    public String nsrmc;

    /**
     * ���������
     */
    public String jsjdm;

    /**
     * ����˰���
     */
    public BigDecimal jmsje;

    /**
     * �˶�֪ͨ���ֺ�
     */
    public String hdtzszh;

    /**
     * �������� ���ݺ˶�֪ͨ���ȡ
     */
    public String jmlydm;

    /**
     * ��ע
     */
    public String bz;

    /**
     * ���ݻ�����Ϣ����
     * @return Tufwxx
     */
    public Tufwxx voTufwxx = null;

    /**
     * ��Ǩ��Ϣ
     * @return ArrayList of Jsblcq
     */
    public ArrayList cqList;

    /**
     * ����ס����Ϣ
     * @return ArrayList of Jsblgyzf
     */
    public ArrayList gyList;

    /**
     * ��Ǩ��Ϣ
     * @return ArrayList of Jmsbb
     */
    public ArrayList jmList;

    /**
     * ��˰����Ϣ�б�
     * @return List of grxx
     */
    public List nsrList;

    
    /**
     * ��Լǩ��ʱ��
     */
    public String time;
    /**
     * ���ء����������ַ
     */
    public String address;
    /**
     * ���ء�����Ȩ��ת������
     */
    public String divertType;
    /**
     * ���ݽ������
     */
    public String area;

    /**
     * �������
     */
    public String tenementType;

    /**
     * �ɽ��۸�
     */
    public String rmbPrice;
	//���ݺ�ͬ��Ų��������Ϣ
	public String all_buyerInfo;
	
  	
    /**
     * ��ñ�ע
     * @return String
     */
    public String getBz() {
        return bz;
    }

    /**
     * ��÷������ط����������
     * @return String
     */
    public String getFcjslh() {
        return fcjslh;
    }

    /**
     * ��ú˶�֪ͨ���ֺ�
     * @return String
     */
    public String getHdtzszh() {
        return hdtzszh;
    }

    /**
     * ��ü���˰���
     * @return String
     */
    public BigDecimal getJmsje() {
        return jmsje;
    }

    public String getJkfsdm() {
        return jkfsdm;
    }

    public String getJkfsmc() {
        return jkfsmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    public ArrayList getJmList() {
        return jmList;
    }

    public ArrayList getCqList() {
        return cqList;
    }

    public ArrayList getGyList() {
        return gyList;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getJmlydm() {
        return jmlydm;
    }

    public boolean isBl() {
        return bl;
    }

    public Timestamp getSbrq() {
        return sbrq;
    }


    /**
     * ���ñ�ע
     * @param bz String
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * ���÷������ط����������
     * @param fcjslh String
     */
    public void setFcjslh(String fcjslh) {
        this.fcjslh = fcjslh;
    }

    /**
     * ���ú˶�֪ͨ���ֺ�
     * @param hdtzszh String
     */
    public void setHdtzszh(String hdtzszh) {
        this.hdtzszh = hdtzszh;
    }

    /**
     * ���ü���˰���
     * @param jmsje String
     */
    public void setJmsje(BigDecimal jmsje) {
        this.jmsje = jmsje;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public void setJmList(ArrayList jmList) {
        this.jmList = jmList;
    }

    public void setCqList(ArrayList cqList) {
        this.cqList = cqList;
    }

    public void setGyList(ArrayList gyList) {
        this.gyList = gyList;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setJmlydm(String jmlydm) {
        this.jmlydm = jmlydm;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * �޸��ˣ�����ϼ 20081125
     * @return String
     */
    public String getJwywbh() {
        return jwywbh;
    }

    public String getHtbh() {
        return htbh;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    /**
     * �޸��ˣ�����ϼ 20081125
     * @param jwywbh String
     */
    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

	public String getAll_buyerInfo() {
		return all_buyerInfo;
	}

	public void setAll_buyerInfo(String all_buyerInfo) {
		this.all_buyerInfo = all_buyerInfo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDivertType() {
		return divertType;
	}

	public void setDivertType(String divertType) {
		this.divertType = divertType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTenementType() {
		return tenementType;
	}

	public void setTenementType(String tenementType) {
		this.tenementType = tenementType;
	}

	public String getRmbPrice() {
		return rmbPrice;
	}

	public void setRmbPrice(String rmbPrice) {
		this.rmbPrice = rmbPrice;
	}

	
}
