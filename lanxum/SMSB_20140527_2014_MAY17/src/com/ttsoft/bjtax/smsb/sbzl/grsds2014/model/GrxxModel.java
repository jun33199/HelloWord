/**
 * @Title:       GrList.java
 * @Description: TODO
 * @date:        2014-11-7下午02:02:21
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds2014.model;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-11-7
 */
public class GrxxModel implements Serializable{

	/**
	 * Description：
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Description：计算机代码
	 */
	private String jsjdm="";
	
	/**
	 * Description：身份证件类型
	 */
	private String gr_sfzjlx="";
	
	/**
	 * Description：身份证件号码
	 */
	private String gr_sfzjhm="";
	
	/**
	 * Description：投资者姓名
	 */
	private String gr_tzzxm="";

	/**
	 * Description:分配比例
	 */
	private String gr_fpbl="";
	
	/**
	 * Description：填写状态
	 */
	private String gr_txzt="";
	
	/**
	 * @description: getter-- gr_sfzjlx
	 * @return the gr_sfzjlx
	 */
	public String getGr_sfzjlx() {
		return gr_sfzjlx;
	}

	/**
	 * @description: setter-- gr_sfzjlx
	 * @param gr_sfzjlx the gr_sfzjlx to set
	 */
	public void setGr_sfzjlx(String gr_sfzjlx) {
		this.gr_sfzjlx = gr_sfzjlx;
	}

	/**
	 * @description: getter-- gr_sfzjhm
	 * @return the gr_sfzjhm
	 */
	public String getGr_sfzjhm() {
		return gr_sfzjhm;
	}

	/**
	 * @description: setter-- gr_sfzjhm
	 * @param gr_sfzjhm the gr_sfzjhm to set
	 */
	public void setGr_sfzjhm(String gr_sfzjhm) {
		this.gr_sfzjhm = gr_sfzjhm;
	}

	/**
	 * @description: getter-- gr_tzzxm
	 * @return the gr_tzzxm
	 */
	public String getGr_tzzxm() {
		return gr_tzzxm;
	}

	/**
	 * @description: setter-- gr_tzzxm
	 * @param gr_tzzxm the gr_tzzxm to set
	 */
	public void setGr_tzzxm(String gr_tzzxm) {
		this.gr_tzzxm = gr_tzzxm;
	}

	/**
	 * @description: getter-- gr_fpbl
	 * @return the gr_fpbl
	 */
	public String getGr_fpbl() {
		return gr_fpbl;
	}

	/**
	 * @description: setter-- gr_fpbl
	 * @param gr_fpbl the gr_fpbl to set
	 */
	public void setGr_fpbl(String gr_fpbl) {
		this.gr_fpbl = gr_fpbl;
	}

	
	
	/**
	 * @description: getter-- jsjdm
	 * @return the jsjdm
	 */
	public String getJsjdm() {
		return jsjdm;
	}

	/**
	 * @description: setter-- jsjdm
	 * @param jsjdm the jsjdm to set
	 */
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	/**
	 * @description: getter-- gr_txzt
	 * @return the gr_txzt
	 */
	public String getGr_txzt() {
		return gr_txzt;
	}

	/**
	 * @description: setter-- gr_txzt
	 * @param gr_txzt the gr_txzt to set
	 */
	public void setGr_txzt(String gr_txzt) {
		this.gr_txzt = gr_txzt;
	}

	
	
	
}
