package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

public class PldyBo implements Serializable {
    // ���κ�
    private String pch = "";
    // �����ṩ�߼��������
    private String tgzjsjdm = "";
    // �����ṩ������
    private String tgzgjmc = "";
    //����ʱ���
    private String drsjBegin = "";
    private String drsjEnd = "";
    public String getPch() {
        return pch;
    }

    public String getTgzgjmc() {
        return tgzgjmc;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }

    public String getDrsjBegin() {
        return drsjBegin;
    }

    public String getDrsjEnd() {
        return drsjEnd;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setTgzgjmc(String tgzgjmc) {
        this.tgzgjmc = tgzgjmc;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setDrsjBegin(String drsjBegin) {
        this.drsjBegin = drsjBegin;
    }

    public void setDrsjEnd(String drsjEnd) {
        this.drsjEnd = drsjEnd;
    }
}
