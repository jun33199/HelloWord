package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class PlcxMxBo implements Serializable {
    //���κ�
    private String pch;
    //״̬��Ϣ(�ѵ���,���ύ,�����,�Ѹ���,������ͨ��)
    private String zt = "";
    //�걨���
    private String sbbh = "";
    //��˰������
    private String nsrmc;
    //��˰�˼��������
    private String jsjdm;
    //���ز���Ŀ����
    private String fdcxmmc;
    //���ز���ַ
    private String fdcdz;
    //��ͬǩ������
    private String htqdrq;
    //��˰���
    private String jsje;
    //Ӧ��˰��
    private String ynse;

    public void setFdcdz(String fdcdz) {
        this.fdcdz = fdcdz;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setHtqdrq(String htqdrq) {
        this.htqdrq = htqdrq;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setYnse(String ynse) {
        this.ynse = ynse;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getHtqdrq() {
        return htqdrq;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getJsje() {
        return jsje;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getPch() {
        return pch;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getYnse() {
        return ynse;
    }

    public String getZt() {
        return zt;
    }

    public String getFdcdz() {
        return fdcdz;
    }
}
