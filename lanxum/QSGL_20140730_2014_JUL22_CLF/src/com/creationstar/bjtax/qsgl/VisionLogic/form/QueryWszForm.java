package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.*;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.*;
import com.creationstar.bjtax.qsgl.model.bo.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ������
 * @version 1.0
 */
public class QueryWszForm extends QueryBaseForm {

	//��ͬ���  zzb add 20090831
    public String htbh="";

    /**
     * �ж�ҳ����Դ
     */
    public String yuan;

    /**
     * Ʊ֤�������
     */
    public String pzzldm;

    /**
     * �ֱ�
     */
    public String zb;
    /**
     * ��˰֤��ʼ����
     */
    public String wszqshm;
    /**
     * ��˰֤��ֹ����
     */
    public String wszjzhm;
    /**
     * ���ʼ����
     */
    public String tfqsrq;
    /**
     * ���ֹ����
     */
    public String tfjzrq;

    //��ӡҳ�����õ�����
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
     * ������
     */
    public String jbr;

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
     * ��ϸ��Ϣ��Ӧ�Ľ����
     */
    public QueryWszBo mxbo = new QueryWszBo();

    /**
     * ������˰֤���صĽ��
     */
    public ArrayList wszList;

    /**
     * ���ɵ���˰֤������
     */
    public int wszTotals;

    /**
     * ��ӡ���ڼ�����˰֤
     */
    public int printPages;

    /**
     * ��ϸ��˰Ŀarraylist
     */
    public ArrayList smlist;

    /**
     * ��ϸ�з��ز�Ȩ��ת�����arraylist
     */
    public ArrayList mjlist;

    /**
     * ��ϸ�м� ˰ �� ��arraylist
     */
    public ArrayList jsjelist;


    /**
     * ��ϸ��˰�ʣ�arraylist
     */
    public ArrayList sllist;

    /**
     * ��ϸ��ʵ �� �� ��arraylist
     */
    public ArrayList snjelist;
    
    /**
     * ��ϸ��˰����������arraylist
     */
    public ArrayList skssrqlist;
    
    private Object data;

    /**
     * ����ú���
     */
    /*   public void clear()
       {
           this.setPzzldm("");
           this.setNsrmc("");
           this.setWszqshm("");
           this.setWszjzhm("");
           this.setTfqsrq("");
           this.setTfjzrq("");
       }
     */
    /**
     * ������ֹ����
     * @return String
     */
    public String getTfjzrq() {
        return tfjzrq;
    }

    /**
     * ������ʼ����
     * @return String
     */
    public String getTfqsrq() {
        return tfqsrq;
    }

    /**
     *  �����˰֤��ֹ����
     * @return String
     */
    public String getWszjzhm() {
        return wszjzhm;
    }

    /**
     * �����˰֤��ʼ����
     * @return String
     */
    public String getWszqshm() {
        return wszqshm;
    }

    /**
     * ����ֱ�
     * @return String
     */
    public String getZb() {
        return zb;
    }

    public QueryWszBo getMxbo() {
        return mxbo;
    }

    public ArrayList getJsjelist() {
        return jsjelist;
    }

    public ArrayList getMjlist() {
        return mjlist;
    }

    public ArrayList getSllist() {
        return sllist;
    }

    public ArrayList getSmlist() {
        return smlist;
    }

    public ArrayList getSnjelist() {
        return snjelist;
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
     * �������ֹ����
     * @param tfjzrq String
     */
    public void setTfjzrq(String tfjzrq) {
        this.tfjzrq = tfjzrq;
    }

    /**
     * �������ʼ����
     * @param tfqsrq String
     */
    public void setTfqsrq(String tfqsrq) {
        this.tfqsrq = tfqsrq;
    }

    /**
     * ������˰֤��ֹ����
     * @param wszjzhm String
     */
    public void setWszjzhm(String wszjzhm) {
        this.wszjzhm = wszjzhm;
    }

    /**
     * ������˰֤��ʼ����
     * @param wszqshm String
     */
    public void setWszqshm(String wszqshm) {
        this.wszqshm = wszqshm;
    }

    /**
     * �����ֱ�
     * @param zb String
     */
    public void setZb(String zb) {
        this.zb = zb;
    }

    public void setMxbo(QueryWszBo mxbo) {
        this.mxbo = mxbo;
    }

    public void setJsjelist(ArrayList jsjelist) {
        this.jsjelist = jsjelist;
    }

    public void setMjlist(ArrayList mjlist) {
        this.mjlist = mjlist;
    }

    public void setSllist(ArrayList sllist) {
        this.sllist = sllist;
    }

    public void setSmlist(ArrayList smlist) {
        this.smlist = smlist;
    }

    public void setSnjelist(ArrayList snjelist) {
        this.snjelist = snjelist;
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

    public void setYuan(String yuan) {
        this.yuan = yuan;
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

    public void setWszList(ArrayList wszList) {
        this.wszList = wszList;
    }

    public void setWszTotals(int wszTotals) {
        this.wszTotals = wszTotals;
    }

    public void setPrintPages(int printPages) {
        this.printPages = printPages;
    }

    //��QueryWszForm�е����ݷŵ�wszBo�У�����wszBo
    public Object getData() {
        QueryWszBo queryWszBo = new QueryWszBo();
        queryWszBo.setNdzb(this.zb);
        queryWszBo.setPzzldm(this.pzzldm);
        queryWszBo.setStartWszh(this.wszqshm);
        queryWszBo.setEndWszh(this.wszjzhm);
        queryWszBo.setStartCjrq(this.tfqsrq);
        queryWszBo.setEndCjrq(this.tfjzrq);
        queryWszBo.setNsrmc(this.nsrmc);

        return queryWszBo;
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

    public String getYuan() {
        return yuan;
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

    public ArrayList getWszList() {
        return wszList;
    }

    public int getWszTotals() {
        return wszTotals;
    }

    public int getPrintPages() {
        return printPages;
    }

    /**
     * @return Returns the nsrmc.
     */
    public String getNsrmc() {
        return nsrmc;
    }

    /**
     * @param nsrmc The nsrmc to set.
     */
    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    /**
     * @return Returns the jbr.
     */
    public String getJbr() {
        return jbr;
    }

    public String getHtbh()
    {
        return htbh;
    }

    /**
     * @param jbr The jbr to set.
     */
    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public void setHtbh(String htbh)
    {
        this.htbh = htbh;
    }

	public ArrayList getSkssrqlist() {
		return skssrqlist;
	}

	public void setSkssrqlist(ArrayList skssrqlist) {
		this.skssrqlist = skssrqlist;
	}
}
