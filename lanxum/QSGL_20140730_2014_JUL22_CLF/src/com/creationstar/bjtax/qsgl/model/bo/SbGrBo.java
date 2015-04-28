package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;

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
public class SbGrBo implements Serializable {
    /**
     * 申报日期
     */
    public Timestamp sbrq;

    /**
     * 补录标示
     */
    public boolean bl;

    /**
     * 纳税申报表号
     */
    public String sbbh;
    /**
     * 20081125,modify by fujx,增加建委业务编号
     */
    private String jwywbh;

    /**
     * 20081125,modify by fujx,增加合同编号
     */
    private String htbh;


    /**
     * 缴款方式
     */
    public String jkfsdm;

    public String jkfsmc;

    /**
     * 房屋土地房产局受理号
     */
    public String fcjslh;

    /**
     * 纳税人名称
     */
    public String nsrmc;

    /**
     * 计算机代码
     */
    public String jsjdm;

    /**
     * 减免税金额
     */
    public BigDecimal jmsje;

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
     * 房屋基本信息对象
     * @return Tufwxx
     */
    public Tufwxx voTufwxx = null;

    /**
     * 拆迁信息
     * @return ArrayList of Jsblcq
     */
    public ArrayList cqList;

    /**
     * 公有住房信息
     * @return ArrayList of Jsblgyzf
     */
    public ArrayList gyList;

    /**
     * 拆迁信息
     * @return ArrayList of Jmsbb
     */
    public ArrayList jmList;

    /**
     * 纳税人信息列表
     * @return List of grxx
     */
    public List nsrList;

    
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
	//根据合同编号查出的买方信息
	public String all_buyerInfo;
	
  	
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
     * 获得减免税金额
     * @return String
     */
    public BigDecimal getJmsje() {
        return jmsje;
    }

    public String getJkfsdm() {
        return jkfsdm;
    }

    public String getJkfsmc() {
        return jkfsmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public String getSbbh() {
        return sbbh;
    }

    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    public ArrayList getJmList() {
        return jmList;
    }

    public ArrayList getCqList() {
        return cqList;
    }

    public ArrayList getGyList() {
        return gyList;
    }

    public String getJsjdm() {
        return jsjdm;
    }

    public String getJmlydm() {
        return jmlydm;
    }

    public boolean isBl() {
        return bl;
    }

    public Timestamp getSbrq() {
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
     * 设置减免税金额
     * @param jmsje String
     */
    public void setJmsje(BigDecimal jmsje) {
        this.jmsje = jmsje;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public void setJmList(ArrayList jmList) {
        this.jmList = jmList;
    }

    public void setCqList(ArrayList cqList) {
        this.cqList = cqList;
    }

    public void setGyList(ArrayList gyList) {
        this.gyList = gyList;
    }

    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    public void setJmlydm(String jmlydm) {
        this.jmlydm = jmlydm;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public void setSbrq(Timestamp sbrq) {
        this.sbrq = sbrq;
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * 修改人：付江霞 20081125
     * @return String
     */
    public String getJwywbh() {
        return jwywbh;
    }

    public String getHtbh() {
        return htbh;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    /**
     * 修改人：付江霞 20081125
     * @param jwywbh String
     */
    public void setJwywbh(String jwywbh) {
        this.jwywbh = jwywbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

	public String getAll_buyerInfo() {
		return all_buyerInfo;
	}

	public void setAll_buyerInfo(String all_buyerInfo) {
		this.all_buyerInfo = all_buyerInfo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDivertType() {
		return divertType;
	}

	public void setDivertType(String divertType) {
		this.divertType = divertType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTenementType() {
		return tenementType;
	}

	public void setTenementType(String tenementType) {
		this.tenementType = tenementType;
	}

	public String getRmbPrice() {
		return rmbPrice;
	}

	public void setRmbPrice(String rmbPrice) {
		this.rmbPrice = rmbPrice;
	}

	
}
