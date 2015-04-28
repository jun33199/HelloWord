package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo;


/**
 * <p>Title: 北京地税核心征管系统－－网上申报 -- 汇总纳税分支机构分配表BO</p>
 *
 * <p>Description: 用于ejb交互的数据对象</p>
 *
 * <p>Copyright: </p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */

import java.io.*;
import java.sql.*;
import java.util.*;

import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjblistVO;

public class GdzcjszjyjqkjbBO implements Serializable
{
    /**
     * 总机构计算机代码
     */
    private String jsjdm = "";

    /**
     * 总机构纳税人识别号
     */
    private String nsrsbh = "";

    /**
     * 总机构纳税人名称
     */
    private String nsrmc = "";

    /**
     * 申报日期
     */
    private Timestamp sbrq;

    /**
     * 页面展示的申报日期
     */
    private String sbrqshow = "";

    /**
     * 税款所属开始日期
     */
    private Timestamp skssksrq;

    /**
     * 税款所属结束日期
     */
    private Timestamp skssjsrq;

    /**
     * 季度
     */
    private String jd = "";

    /**
     * 所属年度
     */
    private String nd = "";

    /**
     * 申报信息
     */
    private GdzcjszjyjqkjblistVO sbsj = new GdzcjszjyjqkjblistVO();


    /**
     * 报表期类型
     */
    private String bbqlx = "";

    /**
     * 所属行业
     */
    private String sshy = "";
    /**
     * 所属行业代码
     */
    private String sshydm = "";

	public GdzcjszjyjqkjbBO()
    {
    }

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public Timestamp getSbrq() {
		return sbrq;
	}

	public void setSbrq(Timestamp sbrq) {
		this.sbrq = sbrq;
	}

	public String getSbrqshow() {
		return sbrqshow;
	}

	public void setSbrqshow(String sbrqshow) {
		this.sbrqshow = sbrqshow;
	}

	public Timestamp getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(Timestamp skssksrq) {
		this.skssksrq = skssksrq;
	}

	public Timestamp getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(Timestamp skssjsrq) {
		this.skssjsrq = skssjsrq;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}



	public String getBbqlx() {
		return bbqlx;
	}

	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public GdzcjszjyjqkjblistVO getSbsj() {
		return sbsj;
	}

	public void setSbsj(GdzcjszjyjqkjblistVO sbsj) {
		this.sbsj = sbsj;
	}

	public String getSshydm() {
		return sshydm;
	}

	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}




}
