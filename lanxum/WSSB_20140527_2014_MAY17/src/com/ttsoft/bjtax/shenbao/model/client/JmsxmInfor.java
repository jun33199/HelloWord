package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  网上申报模块</p>
 * <p>Description: 减免税申报减免项目及代码</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author tujb
 * @version 1.0 201404
 */
public class JmsxmInfor implements Serializable
{

	// 减免项目代码
    private String jmslxdm;
    // 减免项目名称
    private String jmslxmc;
    //文书号
    private String wh;
    // 减免项目税种代码
    private String szdm;
	public String getJmslxdm() {
		return jmslxdm;
	}
	public void setJmslxdm(String jmslxdm) {
		this.jmslxdm = jmslxdm;
	}
	public String getJmslxmc() {
		return jmslxmc;
	}
	public void setJmslxmc(String jmslxmc) {
		this.jmslxmc = jmslxmc;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}

}
