package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.*;

import com.creationstar.bjtax.qsgl.BizLogic.vo.*;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.*;
import com.creationstar.bjtax.qsgl.model.bo.*;
import com.creationstar.bjtax.qsgl.util.*;


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
 * @author ������
 * @version 1.0
 */
public class SbFgrForm extends BaseForm {

    /**
     * �ж��Ƿ����Բ�¼
     */
    private boolean bl = false;

    /**
     * �걨����
     */
    private String sbrq;
    /**
     * 20081125���޸��ˣ�����ϼ�����ӡ���ίҵ�������ԡ�
     */
    private String jwywbh = "";

    /**
     * 20081125���޸��ˣ�����ϼ�����ӡ���ͬ������ԡ�
     */
    private String htbh = "";


    /**
     * ҳ���Ƿ���ʾ��Ϣ
     */
    private boolean alert = false;

    /**
     * ҳ����ʾ��Ϣ
     */
    private String alertMessage = "";


    /**
     * �ɿʽ
     */
    public ArrayList jkfsList = new ArrayList();
    public String jkfsdm = "03";
    public String jkfsmc;
    /**
     * �����걨���
     */
//    public String jmsbbh;

    /**
     * �������ط����������
     */
    public String fcjslh;

    /**
     * ��ϵ�绰
     */
    public String lxdh;


    /**
     * ����˰���
     */
    public String jmsje;

    /**
     * �˶�֪ͨ���ֺ�
     */
    public String hdtzszh;

    /**
     * �������� ���ݺ˶�֪ͨ���ȡ
     */
    public String jmlydm;


    /**
     * ��˰������
     */
    public ArrayList nsrlxList = new ArrayList();
    public String nsrlxdm;
    public String nsrlxmc;
    /**
     * �����˺�
     */
    public String yhzh;

    /**
     *���д��� �������ݿ��в��������д���
     */
    public String yhdm;
    public String yhmc;
    public ArrayList yhList = new ArrayList();
    /**
     * ��ϵ������
     */
    public String lxrxm;
    /**
     * ��ע
     */
    public String bz;
    /**
     * ��Ǩ��Ϣ
     * @return ArrayList of Jsblcq
     */
    public ArrayList cqList = new ArrayList();

    //yangxiao 2008-12-06 start
    /**
     * ������
     */
    public String piccode;

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


    /**
     * ɨ���ʾ
     */
    public String smbs = "0";

    /**
     * ɨ����˰������
     */
    public String smnsrmc;

	 /**
     * �Ƿ�Ϊ���ַ�
     * 01��
     * 00��
     */
    public String sfesf = "00";

    //yangxiao 2008-12-06 end


    public void setCqList(ArrayList cqList) {
        this.cqList = cqList;
    }

    public ArrayList getCqList() {
        return cqList;
    }

    /**
     * ����ס��������Ϣ����
     * @return  Jsblgyzf
     */
    public ArrayList gyList = new ArrayList();

    /**
     * ����˰��Ϣ
     * @return ArrayList of Jmsbb
     */
    public ArrayList jmList = new ArrayList();
    public void setJmList(ArrayList jmList) {
        this.jmList = jmList;
    }

    public ArrayList getJmList() {
        return jmList;
    }

    /**
     * ���ݻ�����Ϣ����
     * @return Tufwxx
     */
    public Tufwxx voTufwxx = null;
    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    /**
     * �Ƿ������˷��ݻ�����Ϣ
     */
    public boolean fwjbxxAdded = false;

    /**
     * �Ƿ������˷��ݽ�����Ϣ
     */
    public boolean fwjhAdded = false;

    /**
     * ���ݽ�������
     * @return FwjhxxBo
     */
    public FwjhxxBo fwjhxxBo = new FwjhxxBo();

    /**
     * ��˰������
     */
    public String nsrmc;
    public void setNsrmc(String s) {
        this.nsrmc = s;
    }

    public String getNsrmc() {
        return nsrmc;
    }

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
     * ��ýɿʽ
     * @return ArrayList
     */
    public ArrayList getJkfsList() {
        return jkfsList;
    }

    /**
     *  ��ü����걨���
     * @return String
     */
//    public String getJmsbbh()
//    {
//        return jmsbbh;
//    }

    /**
     * ��ü���˰���
     * @return String
     */
    public String getJmsje() {
        return jmsje;
    }

    /**
     * �����ϵ�绰
     * @return String
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * �����ϵ������
     * @return String
     */
    public String getLxrxm() {
        return lxrxm;
    }

    /**
     * ��������˺�
     * @return String
     */
    public String getYhzh() {
        return yhzh;
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
     * ���ýɿʽ����
     * @param String jkfsdm
     */
    public String getJkfsdm() {
        return jkfsdm;
    }

    /**
     * ���ýɿʽ����
     * @param String jkfsdm
     */

    public String getJkfsmc() {
        return jkfsmc;
    }

    public ArrayList getNsrlxList() {
        return nsrlxList;
    }

    public String getNsrlxdm() {
        return nsrlxdm;
    }

    public String getNsrlxmc() {
        return nsrlxmc;
    }

    public boolean isFwjbxxAdded() {
        return fwjbxxAdded;
    }

    public ArrayList getGyList() {
        return gyList;
    }

    public String getYhdm() {
        return yhdm;
    }

    public ArrayList getYhList() {
        return yhList;
    }

    public String getYhmc() {
        return yhmc;
    }

    public boolean isFwjhAdded() {
        return fwjhAdded;
    }

    public FwjhxxBo getFwjhxxBo() {
        return fwjhxxBo;
    }

    public String getJmlydm() {
        return jmlydm;
    }

    public boolean isAlert() {
        return alert;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public boolean isBl() {
        return bl;
    }

    public String getSbrq() {
        return sbrq;
    }

    /**
     * ���ü����걨���
     * @param jmsbbh String
     */
//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    /**
     * ���ü���˰���
     * @param jmsje String
     */
    public void setJmsje(String jmsje) {
        this.jmsje = jmsje;
    }

    /**
     * ������ϵ�绰
     * @param lxdh String
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * ������ϵ������
     * @param lxrxm String
     */
    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    /**
     * ���������˺�
     * @param yhzh String
     */
    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setNsrlxList(ArrayList nsrlxList) {
        this.nsrlxList = nsrlxList;
    }

    public void setNsrlxdm(String nsrlxdm) {
        this.nsrlxdm = nsrlxdm;
    }

    public void setNsrlxmc(String nsrlxmc) {
        this.nsrlxmc = nsrlxmc;
    }

    public void setFwjbxxAdded(boolean fwjbxxAdded) {
        this.fwjbxxAdded = fwjbxxAdded;
    }

    public void setGyList(ArrayList gyList) {
        this.gyList = gyList;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public void setYhList(ArrayList yhList) {
        this.yhList = yhList;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public void setFwjhAdded(boolean fwjhAdded) {
        this.fwjhAdded = fwjhAdded;
    }

    public void setFwjhxxBo(FwjhxxBo fwjhxxBo) {
        this.fwjhxxBo = fwjhxxBo;
    }

    public void setJmlydm(String jmlydm) {
        this.jmlydm = jmlydm;
    }

    public void setAlertMessage(String alertMessage) {
        if (alertMessage == null || alertMessage.equals("")) {
            this.alert = false;
        } else {
            this.alert = true;
        }
        this.alertMessage = alertMessage;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public Object getData() {
        SbFgrBo sbfgrbo = new SbFgrBo();
        sbfgrbo.bz = this.bz;
        sbfgrbo.fcjslh = this.fcjslh;
        sbfgrbo.hdtzszh = this.hdtzszh;
        sbfgrbo.jkfsdm = this.jkfsdm;
        sbfgrbo.jkfsmc = this.jkfsmc;
        sbfgrbo.jmList = this.jmList;
        sbfgrbo.jmsje = DataConvert.String2BigDecimal(this.jmsje);
        sbfgrbo.lxdh = this.lxdh;
        sbfgrbo.nsrmc = this.nsrmc;
        sbfgrbo.nsrlxdm = this.nsrlxdm;
        sbfgrbo.nsrlxmc = this.nsrlxmc;
        //���ͻ��˽�ίҵ���Ų�������bo�����С��޸��ˣ�����ϼ20081125
        sbfgrbo.setJwywbh(this.jwywbh);
        //���ͻ��˺�ͬ��Ų�������bo�����С��޸��ˣ�����ϼ20081125
        sbfgrbo.setHtbh(this.htbh);
        sbfgrbo.sbbh = this.sbbh;
        sbfgrbo.khyhdm = this.yhdm;
        if (yhmc != null && !yhmc.equals("")) {
            String sz[] = DataConvert.splitYh(yhmc);
            sbfgrbo.khyhmc = sz[0];
            sbfgrbo.yhzh = sz[1];
        } else {
            sbfgrbo.khyhmc = "";
            sbfgrbo.yhzh = "";
        }

        sbfgrbo.nsjsjdm = this.jsjdm;
        sbfgrbo.lxrxm = this.lxrxm;
        //sbfgrbo.voTufwxx = this.voTufwxx;
        sbfgrbo.sbrq = DataConvert.String2Timestamp(this.sbrq);
        sbfgrbo.setBl(this.bl);
        return sbfgrbo;

    }

    /**
     * 20081125,�޸��ˣ�����ϼ�����ӡ�jwywbh���ֶ�set����
     */
    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public void setSmbs(String smbs) {
        this.smbs = smbs;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPiccode(String piccode) {
        this.piccode = piccode;
    }

    public void setDivertType(String divertType) {
        this.divertType = divertType;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTenementType(String tenementType) {
        this.tenementType = tenementType;
    }

    public void setRmbPrice(String rmbPrice) {
        this.rmbPrice = rmbPrice;
    }

    public void setSmnsrmc(String smnsrmc)
    {
        this.smnsrmc = smnsrmc;
    }

    /**
     * 20081125,�޸��ˣ�����ϼ�����ӡ�jwywbh���ֶ�get����
     * @return String
     */
    public String getJwywbh() {
        return jwywbh;
    }

    public String getHtbh() {
        return htbh;
    }

    public String getSmbs() {
        return smbs;
    }

    public String getTime() {
        return time;
    }

    public String getPiccode() {
        return piccode;
    }

    public String getDivertType() {
        return divertType;
    }

    public String getArea() {
        return area;
    }

    public String getAddress() {
        return address;
    }

    public String getTenementType() {
        return tenementType;
    }

    public String getRmbPrice() {
        return rmbPrice;
    }

    public String getSmnsrmc()
    {
        return smnsrmc;
    }
	
	public void setSfesf(String sfesf)
    {
        this.sfesf = sfesf;
    }

	 public String getSfesf()
    {
        return sfesf;
    }

}
