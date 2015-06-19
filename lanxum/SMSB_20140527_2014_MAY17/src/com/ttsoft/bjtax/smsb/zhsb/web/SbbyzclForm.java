package com.ttsoft.bjtax.smsb.zhsb.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: ������˰��������ϵͳ-�����걨-�걨��һ�´���ҳ�����</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Ha Zhengze</p>
 *
 * @author Ha Zhengze
 * @version 1.0
 */
public class SbbyzclForm extends BaseForm {

    /**
     * ǰ̨��ʾ��Ϣ
     */
    private String opeMessage ="";

    /**
     * ��ѯ���������������
     */
    private String queryJsjdm;

    /**
     * ��ѯ���������
     */
    private String queryNd;

    /**
     * ��ѯ��������ʼ����
     */
    private String queryKsrq;

    /**
     * ��ѯ��������������
     */
    private String queryJsrq;

    /**
     * �����������˰������
     */
    private String nsrmc;

    /**
     * �����������˰��״̬
     */
    private String nsrzt;

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgdm;

    /**
     * ˰�������֯��������
     */
    private String swjgzzjgmc;

    /**
     * ����˰�������֯��������
     */
    private String zgswjgzzjgdm;

    /**
     * ����˰�������֯��������
     */
    private String zgswjgzzjgmc;

    /**
     * ����
     */
    private String rq;

    /**
     * ������
     */
    private String czr;

    /**
     * ���ݼ���
     * �ڴ����ΪSingleSbInfo.java
     */
    private ArrayList dataList = new ArrayList();

    /**
     * ������ϸ����
     * �ڴ���ϸΪSbjkzb.java
     */
    private ArrayList datamxList = new ArrayList();

    /**
     * �걨���
     */
    private String parSbbh;

    /**
     * ���������
     */
    private String parJsjdm;

    private String opeFlag = "0"; //0-��ʼ��������1-�Ѳ�ѯ������Ϣ��2-�Ѳ�ѯ�걨��Ϣ

    // ����¼��Ƿ˰ȷ���ڡ���ӡ�걨���� added by zhangyj 20070720
    /**
     * Ƿ˰ȷ����
     */
    private String qsqrq;    

    /**
     * �ϼƽ��
     */
    private BigDecimal hj;

    /**
     * ����
     */
    private Sbjkzb zb;

    /**
     * ������ϸ
     */
    private List mxList = new ArrayList();

    //end
    
    public String getCzr() {
        return czr;
    }

    public ArrayList getDataList() {
        return dataList;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getQueryJsjdm() {
        return queryJsjdm;
    }

    public String getQueryNd() {
        return queryNd;
    }

    public String getRq() {
        return rq;
    }

    public String getSwjgzzjgdm() {
        return swjgzzjgdm;
    }

    public String getSwjgzzjgmc() {
        return swjgzzjgmc;
    }

    public ArrayList getDatamxList() {
        return datamxList;
    }

    public String getZgswjgzzjgdm() {
        return zgswjgzzjgdm;
    }

    public String getZgswjgzzjgmc() {
        return zgswjgzzjgmc;
    }

    public String getNsrzt() {
        return nsrzt;
    }

    public String getQueryJsrq() {
        return queryJsrq;
    }

    public String getQueryKsrq() {
        return queryKsrq;
    }

    public String getParSbbh() {
        return parSbbh;
    }

    public String getParJsjdm() {
        return parJsjdm;
    }

    public String getOpeFlag() {
        return opeFlag;
    }

    public String getOpeMessage() {

        return opeMessage;
    }

    public String getQsqrq() {

        return qsqrq;
    } 

    public BigDecimal getHj() {
        return hj;
    }

    public Sbjkzb getZb() {
        return zb;
    }
    
    public List getMxList() {
        return mxList;
    }    

    public String getSjsehjdx()
    {
        return Currency.convert(this.hj);
    }    
    
    public void setCzr(String czr) {
        this.czr = czr;
    }

    public void setDataList(ArrayList dataList) {
        this.dataList = dataList;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setQueryJsjdm(String queryJsjdm) {
        this.queryJsjdm = queryJsjdm;
    }

    public void setQueryNd(String queryNd) {
        this.queryNd = queryNd;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public void setSwjgzzjgdm(String swjgzzjgdm) {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setSwjgzzjgmc(String swjgzzjgmc) {
        this.swjgzzjgmc = swjgzzjgmc;
    }

    public void setDatamxList(ArrayList datamxList) {
        this.datamxList = datamxList;
    }

    public void setZgswjgzzjgdm(String zgswjgzzjgdm) {
        this.zgswjgzzjgdm = zgswjgzzjgdm;
    }

    public void setZgswjgzzjgmc(String zgswjgzzjgmc) {
        this.zgswjgzzjgmc = zgswjgzzjgmc;
    }

    public void setNsrzt(String nsrzt) {
        this.nsrzt = nsrzt;
    }

    public void setQueryJsrq(String queryJsrq) {
        this.queryJsrq = queryJsrq;
    }

    public void setQueryKsrq(String queryKsrq) {
        this.queryKsrq = queryKsrq;
    }

    public void setParSbbh(String parSbbh) {
        this.parSbbh = parSbbh;
    }

    public void setParJsjdm(String parJsjdm) {
        this.parJsjdm = parJsjdm;
    }

    public void setOpeFlag(String opeFlag) {
        this.opeFlag = opeFlag;
    }

    public void setOpeMessage(String opeMessage) {

        this.opeMessage = opeMessage;
    }
    
    public void setQsqrq(String qsqrq) {

        this.qsqrq = qsqrq;
    }

    public void setHj(BigDecimal hj) {
        this.hj = hj;
    }

    public void setZb(Sbjkzb zb) {
        this.zb = zb;
    }
    
    public void setMxList(List mxList) {
        this.mxList = mxList;
    }  
      
}
