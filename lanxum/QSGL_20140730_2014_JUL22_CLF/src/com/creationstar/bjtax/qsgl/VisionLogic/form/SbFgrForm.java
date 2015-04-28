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
 * @author 卫军丽
 * @version 1.0
 */
public class SbFgrForm extends BaseForm {

    /**
     * 判断是否来自补录
     */
    private boolean bl = false;

    /**
     * 申报日期
     */
    private String sbrq;
    /**
     * 20081125，修改人：付江霞。增加‘建委业务编号属性’
     */
    private String jwywbh = "";

    /**
     * 20081125，修改人：付江霞。增加‘合同编号属性’
     */
    private String htbh = "";


    /**
     * 页面是否提示信息
     */
    private boolean alert = false;

    /**
     * 页面提示信息
     */
    private String alertMessage = "";


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
     * 联系电话
     */
    public String lxdh;


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
     * 纳税人类型
     */
    public ArrayList nsrlxList = new ArrayList();
    public String nsrlxdm;
    public String nsrlxmc;
    /**
     * 银行账号
     */
    public String yhzh;

    /**
     *银行代码 现在数据库中不保存银行代码
     */
    public String yhdm;
    public String yhmc;
    public ArrayList yhList = new ArrayList();
    /**
     * 联系人姓名
     */
    public String lxrxm;
    /**
     * 备注
     */
    public String bz;
    /**
     * 拆迁信息
     * @return ArrayList of Jsblcq
     */
    public ArrayList cqList = new ArrayList();

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
     * 扫描纳税人名称
     */
    public String smnsrmc;

	 /**
     * 是否为二手房
     * 01是
     * 00否
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
     * 公有住房基本信息对象
     * @return  Jsblgyzf
     */
    public ArrayList gyList = new ArrayList();

    /**
     * 减免税信息
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
     * 房屋基本信息对象
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
     * 是否增加了房屋基本信息
     */
    public boolean fwjbxxAdded = false;

    /**
     * 是否增加了房屋交换信息
     */
    public boolean fwjhAdded = false;

    /**
     * 房屋交换对象
     * @return FwjhxxBo
     */
    public FwjhxxBo fwjhxxBo = new FwjhxxBo();

    /**
     * 纳税人名称
     */
    public String nsrmc;
    public void setNsrmc(String s) {
        this.nsrmc = s;
    }

    public String getNsrmc() {
        return nsrmc;
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
     *  获得减免申报表号
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

    /**
     * 获得联系电话
     * @return String
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 获得联系人姓名
     * @return String
     */
    public String getLxrxm() {
        return lxrxm;
    }

    /**
     * 获得银行账号
     * @return String
     */
    public String getYhzh() {
        return yhzh;
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
     * 设置缴款方式代码
     * @param String jkfsdm
     */
    public String getJkfsdm() {
        return jkfsdm;
    }

    /**
     * 设置缴款方式名称
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
     * 设置减免申报表号
     * @param jmsbbh String
     */
//    public void setJmsbbh(String jmsbbh)
//    {
//        this.jmsbbh = jmsbbh;
//    }

    /**
     * 设置减免税金额
     * @param jmsje String
     */
    public void setJmsje(String jmsje) {
        this.jmsje = jmsje;
    }

    /**
     * 设置联系电话
     * @param lxdh String
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 设置联系人姓名
     * @param lxrxm String
     */
    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    /**
     * 设置银行账号
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
        //将客户端建委业务编号参数放入bo对象中。修改人：付江霞20081125
        sbfgrbo.setJwywbh(this.jwywbh);
        //将客户端合同编号参数放入bo对象中。修改人：付江霞20081125
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
     * 20081125,修改人：付江霞，增加‘jwywbh’字段set方法
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
     * 20081125,修改人：付江霞，增加‘jwywbh’字段get方法
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
