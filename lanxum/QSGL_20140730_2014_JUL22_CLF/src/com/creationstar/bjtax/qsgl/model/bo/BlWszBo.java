package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;

public class BlWszBo implements Serializable {
    /**
     * �걨���
     */
    private String sbbh;

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh;

    /**
     * �걨���ܵ���
     */
    private String sbhzdh;

    /**
     * ��˰֤��
     */
    private String wszh;

    /**
     * ����ֱ�
     */
    private String ndzb;

    /**
     * Ʊ֤�������
     */
    private String pzzldm;

    /**
     * �����Ǵ���
     */
    private String clbjdm;

    /**
     * �Ƿ��з�Ʊ�����
     */
    private String fp;

    /**
     * ��˰֤����vo
     */
    private Qswszz qswszz;

    /**
     * ��˰֤��ϸ��vo
     */
    private Qswszmx qswszmx;

    public String getJkpzh() {
        return jkpzh;
    }

    public Qswszmx getQswszmx() {
        return qswszmx;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Qswszz getQswszz() {
        return qswszz;
    }

    public String getSbhzdh() {
        return sbhzdh;
    }

    public String getFp() {
        return fp;
    }

    public String getClbjdm() {
        return clbjdm;
    }

    public String getNdzb() {
        return ndzb;
    }

    public String getPzzldm() {
        return pzzldm;
    }

    public String getWszh() {
        return wszh;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setQswszmx(Qswszmx qswszmx) {
        this.qswszmx = qswszmx;
    }

    public void setQswszz(Qswszz qswszz) {
        this.qswszz = qswszz;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSbhzdh(String sbhzdh) {
        this.sbhzdh = sbhzdh;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
    }

    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setPzzldm(String pzzldm) {
        this.pzzldm = pzzldm;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }
}
