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
 *
 * ˵������Bo�����ҳ����"��������(˰����Ա)"ģ��
 */
public class PlcxBo2 implements Serializable {
    //�����ṩ����Ϣ
    private String sjtgz;

    //������
    private String pch;

    //����ʱ���
    private String drsjBegin;
    private String drsjEnd;

    //�ύʱ���
    private String tjsjBegin;
    private String tjsjEnd;

    //����ʱ��
    private String drsj;

    //�ύʱ��
    private String tjsj;

    //�������
    private String drbs;

    //�ύ����
    private String tjbs;

    //����ͨ������
    private String sltgbs;

    //����ͨ���ı���
    private String fstgbs;

    //���ͨ������
    private String shtgbs;

    //���ʱ��
    private String shsj;

    //����ͨ�����걨��Ӧ��˰��
    private String sumYnse;

    public String getDrbs() {
        return drbs;
    }

    public String getDrsj() {
        return drsj;
    }

    public String getFstgbs() {
        return fstgbs;
    }

    public String getShsj() {
        return shsj;
    }

    public String getPch() {
        return pch;
    }

    public String getShtgbs() {
        return shtgbs;
    }

    public String getSjtgz() {
        return sjtgz;
    }

    public String getSltgbs() {
        return sltgbs;
    }

    public String getSumYnse() {
        return sumYnse;
    }

    public String getTjbs() {
        return tjbs;
    }

    public String getTjsj() {
        return tjsj;
    }

    public void setDrbs(String drbs) {
        this.drbs = drbs;
    }

    public void setDrsj(String drsj) {
        this.drsj = drsj;
    }

    public void setFstgbs(String fstgbs) {
        this.fstgbs = fstgbs;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setShtgbs(String shtgbs) {
        this.shtgbs = shtgbs;
    }

    public void setSjtgz(String sjtgz) {
        this.sjtgz = sjtgz;
    }

    public void setSltgbs(String sltgbs) {
        this.sltgbs = sltgbs;
    }

    public void setSumYnse(String sumYnse) {
        this.sumYnse = sumYnse;
    }

    public void setTjbs(String tjbs) {
        this.tjbs = tjbs;
    }

    public void setTjsj(String tjsj) {
        this.tjsj = tjsj;
    }

    public String getDrsjBegin() {
        return drsjBegin;
    }

    public void setDrsjBegin(String drsjBegin) {
        this.drsjBegin = drsjBegin;
    }

    public String getDrsjEnd() {
        return drsjEnd;
    }

    public void setDrsjEnd(String drsjEnd) {
        this.drsjEnd = drsjEnd;
    }

    public String getTjsjBegin() {
        return tjsjBegin;
    }

    public void setTjsjBegin(String tjsjBegin) {
        this.tjsjBegin = tjsjBegin;
    }

    public String getTjsjEnd() {
        return tjsjEnd;
    }

    public void setTjsjEnd(String tjsjEnd) {
        this.tjsjEnd = tjsjEnd;
    }

}
