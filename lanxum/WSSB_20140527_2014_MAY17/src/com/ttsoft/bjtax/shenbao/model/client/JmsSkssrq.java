package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  网上申报模块</p>
 * <p>Description: 减免税申报特定税款所属日期</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author tujb
 * @version 1.0 201404
 */
public class JmsSkssrq implements Serializable
{

	/**
     * 税款所属开始日期常量
     */
	private String skssksrq;

    /**
     * 税款所属结束日期常量
     */
	private String skssjsrq;

	public String getSkssksrq() {
		return skssksrq;
	}

	public void setSkssksrq(String skssksrq) {
		this.skssksrq = skssksrq;
	}

	public String getSkssjsrq() {
		return skssjsrq;
	}

	public void setSkssjsrq(String skssjsrq) {
		this.skssjsrq = skssjsrq;
	}
}
