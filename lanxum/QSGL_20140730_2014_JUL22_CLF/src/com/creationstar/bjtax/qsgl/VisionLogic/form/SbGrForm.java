package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.SbGrBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;

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
 * @author �Բ�
 * @version 1.0
 */
public class SbGrForm extends BaseForm {
    public SbGrForm() {
    }

    /**
     * �ж��Ƿ����Բ�¼
     */
    private boolean bl = false;


    /**
     * �걨����
     */
    private String sbrq;

    /**
     * ҳ���Ƿ���ʾ��Ϣ
     */
    private boolean alert = false;

    /**
     * ҳ����ʾ��Ϣ
     */
    private String alertMessage = "";

    //���ݺ�ͬ��Ų��������Ϣ
  	public String all_buyerInfo;
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
     * ���֤������02
     */
    public ArrayList sfzjlxList = new ArrayList();
    /**
     * ���ʵ�������CHN
     */
    public ArrayList gjdqList;
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
     * ��ע
     */
    public String bz;

    /**
     * ����Ȩ��
     */
    public String zcqr;
    /**
     * 20081125���޸��ˣ�����ϼ�����ӡ���ίҵ�������ԡ�
     */
    private String jwywbh = "";

    /**
     * 20081125���޸��ˣ�����ϼ�����ӡ���ͬ������ԡ�
     */
    private String htbh = "";


    /**
     * ��Ȩ��ҳ������
     */
    public String cqrJs = Constants.CQRJS_INIT;

    /**
     * ���ݻ�����Ϣ����
     * @return Tufwxx
     */
    public Tufwxx voTufwxx = new Tufwxx();

    /**
     * �Ƿ������˷��ݻ�����Ϣ
     */
    public boolean fwjbxxAdded = false;

    /**
     * ��Ǩ��Ϣ
     * @return ArrayList of Jsblcq
     */
    public ArrayList cqList = new ArrayList();

    /**
     * ����ס����Ϣ
     * @return ArrayList of Jsblgyzf
     */
    public ArrayList gyList = new ArrayList();

    /**
     * ��Ǩ��Ϣ
     * @return ArrayList of Jmsbb
     */
    public ArrayList jmList = new ArrayList();

    /**
     * ����
     * @return ArrayList of Jmsbb
     */
    public List nsrList = new ArrayList();

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
     * �Ƿ�Ϊ���ַ�
     * 01��
     * 00��
     */
    public String sfesf = "00";

    //yangxiao 2008-12-06 end

    /**
     * ���ݸ��˻�����Ϣ����
     * @return FwjhxxBo
     */
    public FwjhxxBo fwjhxxBo = new FwjhxxBo();

    public boolean fwjhAdded = false;

    public Object getData(List l) {
        SbGrBo sbgrbo = new SbGrBo();
        sbgrbo.bz = this.bz;
        sbgrbo.cqList = this.cqList;
        sbgrbo.fcjslh = this.fcjslh;
        sbgrbo.gyList = this.gyList;
        sbgrbo.hdtzszh = this.hdtzszh;
        sbgrbo.jkfsdm = this.jkfsdm;
        sbgrbo.jkfsmc = this.jkfsmc;
        sbgrbo.jmList = this.jmList;
        sbgrbo.jmsje = DataConvert.String2BigDecimal(this.jmsje);
        sbgrbo.jmlydm = this.jmlydm;
        //���ͻ��˽�ίҵ���Ų�������bo�����С��޸��ˣ�����ϼ20081125
        sbgrbo.setJwywbh(this.jwywbh);
        //���ͻ��˺�ͬ��Ų�������bo�����С��޸��ˣ�����ϼ20081125
        sbgrbo.setHtbh(this.htbh);
        //��ÿͻ��˵�����--
        this.setCqrJs(ActionUtil.displayMNsrDS(l));

        sbgrbo.setNsrList(l);
        this.setNsrList(l);
        sbgrbo.sbbh = this.sbbh;
        sbgrbo.voTufwxx = this.voTufwxx;
        sbgrbo.sbrq = DataConvert.String2Timestamp(this.sbrq);
        sbgrbo.setBl(this.bl);
        return sbgrbo;
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
     * ��ü����걨���
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

    public String getJkfsdm() {
        return jkfsdm;
    }

    public String getJkfsmc() {
        return jkfsmc;
    }

    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    public ArrayList getCqList() {
        return cqList;
    }

    public ArrayList getGyList() {
        return gyList;
    }

    public ArrayList getJmList() {
        return jmList;
    }

    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    public boolean isFwjbxxAdded() {
        return fwjbxxAdded;
    }

    public FwjhxxBo getFwjhxxBo() {
        return fwjhxxBo;
    }

    public boolean isFwjhAdded() {
        return fwjhAdded;
    }

    public ArrayList getGjdqList() {
        return gjdqList;
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
     * ���ýɿʽ
     * @param jkfsList ArrayList
     */
    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    /**
     * ���ü���˰���
     * @param jmsje String
     */
    public void setJmsje(String jmsje) {
        this.jmsje = jmsje;
    }

    /**
     *  ���ü����걨���
     * @param jmsbbh String
     */
//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }

    public void setCqList(ArrayList cqList) {
        this.cqList = cqList;
    }

    public void setGyList(ArrayList gyList) {
        this.gyList = gyList;
    }

    public void setJmList(ArrayList jmList) {
        this.jmList = jmList;
    }

    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public void setFwjbxxAdded(boolean fwjbxxAdded) {
        this.fwjbxxAdded = fwjbxxAdded;
    }

    public void setFwjhxxBo(FwjhxxBo fwjhxxBo) {
        this.fwjhxxBo = fwjhxxBo;
    }

    public void setFwjhAdded(boolean fwjhAdded) {
        this.fwjhAdded = fwjhAdded;
    }

    public void setGjdqList(ArrayList gjdqList) {
        this.gjdqList = gjdqList;
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

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }


    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;

    }

    /**
     * @return Returns the cqrJs.
     */
    public String getCqrJs() {
        return cqrJs;
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

    public String getDivertType() {
        return divertType;
    }

    public String getArea() {
        return area;
    }

    public String getAddress() {
        return address;
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

    public String getTenementType() {
        return tenementType;
    }

    public String getRmbPrice() {
        return rmbPrice;
    }

    /**
     * @param cqrJs The cqrJs to set.
     */
    public void setCqrJs(String cqrJs) {
        this.cqrJs = cqrJs;
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

    public void setDivertType(String divertType) {
        this.divertType = divertType;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setTenementType(String tenementType) {
        this.tenementType = tenementType;
    }

    public void setRmbPrice(String rmbPrice) {
        this.rmbPrice = rmbPrice;
    }

	public void setSfesf(String sfesf)
    {
        this.sfesf = sfesf;
    }

	 public String getSfesf()
    {
        return sfesf;
    }

	public String getAll_buyerInfo() {
		return all_buyerInfo;
	}

	public void setAll_buyerInfo(String all_buyerInfo) {
		this.all_buyerInfo = all_buyerInfo;
	}
	 
}
