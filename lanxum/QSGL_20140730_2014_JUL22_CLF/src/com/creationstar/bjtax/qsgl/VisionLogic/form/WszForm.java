package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.DataConvert;

/**
 *
 * <p>Title:��¼��˰֤��form </p>
 *
 * <p>Description:��¼��˰֤��form </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class WszForm extends BaseForm {
    /**
     * Ʊ֤�������
     */
    public String pzzldm;

    /**
     * �ֱ�
     */
    public String ndzb;

    /**
     * ��˰֤����
     */
    public String wszh;

    /**
     * �����
     */
    public String tfrq;

    /**
     * ��˰�˴���
     */
    public String nsrdm;

    /**
     * ��˰������
     */
    public String nsrmc;

    /**
     * ��Լ����ͬ��ǩ������
     */
    public String htqdrq;

    /**
     * ���ز�λ��
     */
    public String fdcwz;

    /**
     * ��˰������ʼ����
     */
    public String skssksrq;

    /**
     * ��˰������������
     */
    public String skssjzrq;

    /**
     * ��ַ
     */
    public String dz;

    /**
     * ˰��˰Ŀ
     */
    public String szsmmc;
    public String szsmdm;

    /**
     * ˰��
     */
    public String szmc;
    public String szdm;

    /**
     * Ȩ��ת�����
     */
    public String qszymj;

    /**
     * ��˰���
     */
    public String jsje;

    /**
     * ˰��
     */
    public String sl;

    /**
     * ʵ��˰��
     */
    public String sjse;

    /**
     * ���ϼ�
     */
    public String jehj_dx;

    /**
     * ���ϼ�
     */
    public String jehj;

    /**
     * ��������
     */
    public String yqts;

    /**
     * ���ɽ�
     */
    public String znj;

    /**
     * ���ջ���
     */
    public String zsjg;

    /**
     * ��ע
     */
    public String bz;


    /**
     * ���ɵ���˰֤������
     */
    public int wszTotals;

    /**
     * ��ӡ���ڼ�����˰֤
     */
    public int printPages;

    /**
     * �ɿ�ƾ֤��
     */
    public String jkpzh;

    /**
     * �걨���ܵ���
     */
    public String sbhzdh;

    /**
     * �����Ǵ���
     */
    public String clbjdm;

    /**
     * ��˰֤�Ƿ��з�Ʊ�������0Ϊû�У�1Ϊ��
     */
    public String fp;

    /**
     * ��˰֤�����vo
     */
    public Qswszz qswszz;

    /**
     * ��˰֤��ϸ���vo
     */
    public Qswszmx qswszmx;

    public void getWszzVo() {
        this.qswszz.setWszh(this.getWszh());
        this.qswszz.setClbjdm(this.getClbjdm());
        this.qswszz.setNdzb(this.getNdzb());
        this.qswszz.setPzzldm(this.getPzzldm());
    }

    public void setWszData() {
        this.qswszz.setCjrq(DataConvert.String2Timestamp(this.getTfrq()));
        this.qswszz.setHjsjje(DataConvert.String2BigDecimal(this.getSjse()));

        this.qswszmx.setSkssksrq(DataConvert.String2Timestamp(this.getSkssksrq()));
        this.qswszmx.setSkssksrq(DataConvert.String2Timestamp(this.getSkssjzrq()));
        this.qswszmx.setJsje(DataConvert.String2BigDecimal(this.getJsje()));
        this.qswszmx.setSjse(this.qswszz.getHjsjje());
        this.qswszmx.setCjrq(this.qswszz.getCjrq());

    }

    /**
     * ����ֱ�
     * @return String
     */
    public String getNdzb() {
        return ndzb;
    }

    public String getBz() {
        return bz;
    }

    public String getDz() {
        return dz;
    }

    public String getFdcwz() {
        return fdcwz;
    }

    public String getHtqdrq() {
        return htqdrq;
    }

    public String getJehj() {
        return jehj;
    }

    public String getJsje() {
        return jsje;
    }

    public String getNsrdm() {
        return nsrdm;
    }

    public String getQszymj() {
        return qszymj;
    }

    public String getSjse() {
        return sjse;
    }

    public String getSl() {
        return sl;
    }

    public String getTfrq() {
        return tfrq;
    }

    public String getWszh() {
        return wszh;
    }

    public String getZsjg() {
        return zsjg;
    }

    /**
     * �����ֱ�
     * @param zb String
     */
    public void setNdzb(String ndzb) {
        this.ndzb = ndzb;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public void setFdcwz(String fdcwz) {
        this.fdcwz = fdcwz;
    }

    public void setHtqdrq(String htqdrq) {
        this.htqdrq = htqdrq;
    }

    public void setJehj(String jehj) {
        this.jehj = jehj;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setNsrdm(String nsrdm) {
        this.nsrdm = nsrdm;
    }

    public void setQszymj(String qszymj) {
        this.qszymj = qszymj;
    }

    public void setSjse(String sjse) {
        this.sjse = sjse;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setTfrq(String tfrq) {
        this.tfrq = tfrq;
    }

    public void setZsjg(String zsjg) {
        this.zsjg = zsjg;
    }

    public void setYqts(String yqts) {
        this.yqts = yqts;
    }

    public void setZnj(String znj) {
        this.znj = znj;
    }

    public void setSzdm(String szdm) {
        this.szdm = szdm;
    }

    public void setSzmc(String szmc) {
        this.szmc = szmc;
    }

    public void setSzsmdm(String szsmdm) {
        this.szsmdm = szsmdm;
    }

    public void setSzsmmc(String szsmmc) {
        this.szsmmc = szsmmc;
    }

    public void setPzzldm(String pzzldm) {
        this.pzzldm = pzzldm;
    }

    public void setJehj_dx(String jehj_dx) {
        this.jehj_dx = jehj_dx;
    }

    public void setSkssjzrq(String skssjzrq) {
        this.skssjzrq = skssjzrq;
    }

    public void setSkssksrq(String skssksrq) {
        this.skssksrq = skssksrq;
    }

    public void setWszTotals(int wszTotals) {
        this.wszTotals = wszTotals;
    }

    public void setPrintPages(int printPages) {
        this.printPages = printPages;
    }

    public void setClbjdm(String clbjdm) {
        this.clbjdm = clbjdm;
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

    public void setSbhzdh(String sbhzdh) {
        this.sbhzdh = sbhzdh;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public String getYqts() {
        return yqts;
    }

    public String getZnj() {
        return znj;
    }

    public String getSzdm() {
        return szdm;
    }

    public String getSzmc() {
        return szmc;
    }

    public String getSzsmdm() {
        return szsmdm;
    }

    public String getSzsmmc() {
        return szsmmc;
    }

    public String getPzzldm() {
        return pzzldm;
    }

    public String getJehj_dx() {
        return jehj_dx;
    }

    public String getSkssjzrq() {
        return skssjzrq;
    }

    public String getSkssksrq() {
        return skssksrq;
    }

    public int getWszTotals() {
        return wszTotals;
    }

    public int getPrintPages() {
        return printPages;
    }

    public String getClbjdm() {
        return clbjdm;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public Qswszmx getQswszmx() {
        return qswszmx;
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

}
