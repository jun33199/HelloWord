package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class ShskBo implements Serializable {
    /**
     * ��˰��ʽ
     */
    private String jsfsdm;
    /**
     * �걨���
     */
    private String sbbh;

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * ���ط��������ַ
     */
    private String tdfwzldz;

    /**
     * �ɽ��۸������
     */
    private BigDecimal cjjgrmb;

    /**
     * ��ע
     */
    private String bz;

    /**
     * ˰��
     */
    private BigDecimal sl;

    /**
     * ����˰��
     */
    private BigDecimal jsje;

    /**
     * ��˰����
     */
    private BigDecimal jsyj;

    /**
     * Ӧ��˰��
     */
    private BigDecimal ynse;

    /**
     * ��Ǩ������
     */
    private BigDecimal cqjmje;

    /**
     * ��ͨסլ��˰���
     */
    private BigDecimal ptzzjsje;

    /**
     * �������÷���˰���
     */
    private BigDecimal jjsyfjsje;


    /**
     * ��Ӧ������ʾ�ĺ˶�֪ͨ����ϢHdtzsBo
     */
    private ArrayList hdList = new ArrayList();

    /**
     * �Ƿ��Ѿ����
     */
    private boolean checked = false;
    /**
     * ��˰���б�
     */
    public List nsrList = new ArrayList();

    /**
     * �Ƿ������ε��������
     */
    private boolean pc = false;

    public String getBz() {
        return bz;
    }

    public ArrayList getHdList() {
        return hdList;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public BigDecimal getCjjgrmb() {
        return cjjgrmb;
    }

    public BigDecimal getCqjmje() {
        return cqjmje;
    }

    public BigDecimal getJsje() {
        return jsje;
    }

    public BigDecimal getPtzzjsje() {
        return ptzzjsje;
    }

    public BigDecimal getSl() {
        return sl;
    }

    public BigDecimal getYnse() {
        return ynse;
    }

    public boolean isChecked() {
        return checked;
    }

    public BigDecimal getJsyj() {
        return jsyj;
    }

    public String getJsfsdm() {
        return jsfsdm;
    }

    public boolean isPc() {
        return pc;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setHdList(ArrayList hdList) {
        this.hdList = hdList;
    }

    public void setCjjgrmb(BigDecimal cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setCqjmje(BigDecimal cqjmje) {
        this.cqjmje = cqjmje;
    }

    public void setJsje(BigDecimal jsje) {
        this.jsje = jsje;
    }

    public void setPtzzjsje(BigDecimal ptzzjsje) {
        this.ptzzjsje = ptzzjsje;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public void setYnse(BigDecimal ynse) {
        this.ynse = ynse;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setJsyj(BigDecimal jsyj) {
        this.jsyj = jsyj;
    }

    public void setJsfsdm(String jsfsdm) {
        this.jsfsdm = jsfsdm;
    }

    public void setPc(boolean pc) {
        this.pc = pc;
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    public BigDecimal getJjsyfjsje() {
        return jjsyfjsje;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    public void setJjsyfjsje(BigDecimal jjsyfjsje) {
        this.jjsyfjsje = jjsyfjsje;
    }

    /**
     * �жϸ��걨�Ƿ��Ǹ���
     * @return boolean
     */
    public boolean isPerson() {
        if (nsrList == null || nsrList.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * �жϸ��걨�Ƿ��Ǹ���
     * @return boolean
     */
    public boolean isPersonChecked() {

        if (isPerson() && isChecked() && getYnse().doubleValue() > 0) {
            return true;
        }
        return false;
    }

}
