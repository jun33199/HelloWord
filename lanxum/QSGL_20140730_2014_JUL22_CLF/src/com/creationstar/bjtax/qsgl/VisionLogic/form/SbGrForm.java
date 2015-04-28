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
 * @author 赵博
 * @version 1.0
 */
public class SbGrForm extends BaseForm {
    public SbGrForm() {
    }

    /**
     * 判断是否来自补录
     */
    private boolean bl = false;


    /**
     * 申报日期
     */
    private String sbrq;

    /**
     * 页面是否提示信息
     */
    private boolean alert = false;

    /**
     * 页面提示信息
     */
    private String alertMessage = "";

    //根据合同编号查出的买方信息
  	public String all_buyerInfo;
    /**
     * 缴款方式
     */
    public ArrayList jkfsList = new ArrayList();
    public String jkfsdm = "03";
    public String jkfsmc;

    /**
     * 减免申报表号
     */
//    public String jmsbbh;

    /**
     * 房屋土地房产局受理号
     */
    public String fcjslh;

    /**
     * 身份证件类型02
     */
    public ArrayList sfzjlxList = new ArrayList();
    /**
     * 国际地区代码CHN
     */
    public ArrayList gjdqList;
    /**
     * 减免税金额
     */
    public String jmsje;

    /**
     * 核定通知书字号
     */
    public String hdtzszh;

    /**
     * 减免理由 根据核定通知书获取
     */
    public String jmlydm;

    /**
     * 备注
     */
    public String bz;

    /**
     * 主产权人
     */
    public String zcqr;
    /**
     * 20081125，修改人：付江霞。增加‘建委业务编号属性’
     */
    private String jwywbh = "";

    /**
     * 20081125，修改人：付江霞。增加‘合同编号属性’
     */
    private String htbh = "";


    /**
     * 产权人页面数组
     */
    public String cqrJs = Constants.CQRJS_INIT;

    /**
     * 房屋基本信息对象
     * @return Tufwxx
     */
    public Tufwxx voTufwxx = new Tufwxx();

    /**
     * 是否增加了房屋基本信息
     */
    public boolean fwjbxxAdded = false;

    /**
     * 拆迁信息
     * @return ArrayList of Jsblcq
     */
    public ArrayList cqList = new ArrayList();

    /**
     * 公有住房信息
     * @return ArrayList of Jsblgyzf
     */
    public ArrayList gyList = new ArrayList();

    /**
     * 拆迁信息
     * @return ArrayList of Jmsbb
     */
    public ArrayList jmList = new ArrayList();

    /**
     * 个人
     * @return ArrayList of Jmsbb
     */
    public List nsrList = new ArrayList();

    //yangxiao 2008-12-06 start
    /**
     * 条形玛
     */
    public String piccode;

    /**
     * 合约签订时间
     */
    public String time;
    /**
     * 土地、房屋座落地址
     */
    public String address;
    /**
     * 土地、房屋权属转移类型
     */
    public String divertType;
    /**
     * 房屋建筑面积
     */
    public String area;

    /**
     * 房屋类别
     */
    public String tenementType;

    /**
     * 成交价格
     */
    public String rmbPrice;

    /**
     * 扫描标示
     */
    public String smbs = "0";

	/**
     * 是否为二手房
     * 01是
     * 00否
     */
    public String sfesf = "00";

    //yangxiao 2008-12-06 end

    /**
     * 房屋个人基本信息对象
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
        //将客户端建委业务编号参数放入bo对象中。修改人：付江霞20081125
        sbgrbo.setJwywbh(this.jwywbh);
        //将客户端合同编号参数放入bo对象中。修改人：付江霞20081125
        sbgrbo.setHtbh(this.htbh);
        //获得客户端的数据--
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
     * 获得备注
     * @return String
     */
    public String getBz() {
        return bz;
    }

    /**
     * 获得房屋土地房产局受理号
     * @return String
     */
    public String getFcjslh() {
        return fcjslh;
    }

    /**
     * 获得核定通知书字号
     * @return String
     */
    public String getHdtzszh() {
        return hdtzszh;
    }

    /**
     * 获得缴款方式
     * @return ArrayList
     */
    public ArrayList getJkfsList() {
        return jkfsList;
    }

    /**
     * 获得减免申报表号
     * @return String
     */
//    public String getJmsbbh()
//    {
//        return jmsbbh;
//    }

    /**
     * 获得减免税金额
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
     * 设置备注
     * @param bz String
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * 设置房屋土地房产局受理号
     * @param fcjslh String
     */
    public void setFcjslh(String fcjslh) {
        this.fcjslh = fcjslh;
    }

    /**
     * 设置核定通知书字号
     * @param hdtzszh String
     */
    public void setHdtzszh(String hdtzszh) {
        this.hdtzszh = hdtzszh;
    }

    /**
     * 设置缴款方式
     * @param jkfsList ArrayList
     */
    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    /**
     * 设置减免税金额
     * @param jmsje String
     */
    public void setJmsje(String jmsje) {
        this.jmsje = jmsje;
    }

    /**
     *  设置减免申报表号
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
     * 20081125,修改人：付江霞，增加‘jwywbh’字段get方法
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
     * 20081125,修改人：付江霞，增加‘jwywbh’字段set方法
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
