package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.JsblBo;

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
 * @author not attributable
 * @version 1.0
 */
public class QuerySbxxForm extends QueryBaseForm {
    /**
     * JsblBo��Ϊ��ѯ����ʱ��ָ�����Ǹ���ѯҳ�����
     * 0���걨��ѯ
     * 1��������ѯ
     * 2����˲�ѯ
     * 3�����˲�ѯ
     */
    private int fromPage;


    /**
     * �ɿ����
     */
    private String jksh;
    /**
     * ��ίҵ���ţ�modify by fujx
     */
    private String jwywbh;
    /**
     * ��ͬ��ţ�modify by fujx
     */
    private String htbh;

    /**
     * ��˰�������б�
     */
    private ArrayList nsrlxList = new ArrayList();


    /**
     * ��˰֤��
     */
    private String wszh;

    /**
     * ���֤��������������ʾ��Ϣ
     */
    private ArrayList sfzjlxList = new ArrayList();

    /**
     * ���֤�����ʹ���
     */
    private String zjlxdm;

    /**
     * ���ز���Ŀ����
     */
    private String fdcxmmc;

    /**
     * ���ط��������ַ
     */
    private String tdfwzldz;

    /**
     * ��ע
     */
    private String bz;

    /**
     * ״̬��ʶ
     */
    private String ztbs;

    /**
     * �ٵ�״̬��ʶ,��ѯ������,��������տ��ѯҳ��ʹ��
     */
    private String fztbs;

    /**
     * ����˰��ʶ
     */
    private String jmsbs;

    /**
     * ���֤������
     */
    private String sfzjhm;

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * ���κ�
     */
    private String pch;

    public void setWszh(String wszh) {
        this.wszh = wszh;
    }

    public void setJksh(String jksh) {
        this.jksh = jksh;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setSfzjhm(String sfzjhm) {
        this.sfzjhm = sfzjhm;
    }

    public void setNsrlxList(ArrayList nsrlxList) {
        this.nsrlxList = nsrlxList;
    }

    public void setZjlxdm(String zjlxdm) {
        this.zjlxdm = zjlxdm;
    }

    public void setZtbs(String ztbs) {
        this.ztbs = ztbs;
    }

    public void setJmsbs(String jmsbs) {
        this.jmsbs = jmsbs;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setFztbs(String fztbs) {
        this.fztbs = fztbs;
    }

    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public String getJksh() {
        return jksh;
    }

    public String getWszh() {
        return wszh;
    }

    public String getBz() {
        return bz;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getSfzjhm() {
        return sfzjhm;
    }

    public ArrayList getNsrlxList() {
        return nsrlxList;
    }

    public String getZjlxdm() {
        return zjlxdm;
    }

    public String getZtbs() {
        return ztbs;
    }

    public String getJmsbs() {
        return jmsbs;
    }

    public String getSbrq() {
        return sbrq;
    }

    public String getFztbs() {
        return fztbs;
    }

    public int getFromPage() {
        return fromPage;
    }

    public String getPch() {
        return pch;
    }

    public String getHtbh() {
        return htbh;
    }

    public String getJwywbh() {
        return jwywbh;
    }

    /**
     * ǰ̨ҳ��Ĳ�ѯ����
     * @return Object
     */
    public Object getData() {
        JsblBo bo = new JsblBo();
        bo.setSbbh(this.getSbbh());
        //���ӽ�ίҵ���ţ�modify by fujx
        bo.setJwywbh(this.jwywbh);
        //���Ӻ�ͬ��ţ�modify by fujx
        bo.setHtbh(this.htbh);
        //bo.setWszh(this.getWszh());
        bo.setJksh(this.getJksh());
        bo.setNsrlxdm(this.getNsrlx());
        bo.setNsrmc(this.getNsrmc());
        bo.setJsjdm(this.getJsjdm());
        bo.setSfzjhm(this.getSfzjhm());
        bo.setSfzjlx(this.zjlxdm);
        bo.setZtbs(this.ztbs);
        bo.setJmsbs(this.jmsbs);
        bo.setSbrq(this.sbrq);
        bo.setFromPage(this.fromPage);
        bo.setFztbs(this.fztbs);
        bo.setDrpch(this.pch);
        return bo;
    }
}
