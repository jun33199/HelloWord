package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClfjyxxCX implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	//交易信息
	private String sbbh;
	private BigDecimal sl;//税率
	private BigDecimal yys_JSJE_hj_02;//营业税计税金额
	private BigDecimal yys_YNSE_hj_02;//营业税应纳金额
	private BigDecimal grsds_JSJE_hj_05;//个人所得税计税金额
	private BigDecimal grsds_YNSE_hj_05;//个人所得税应纳金额
	private BigDecimal grsds_sl_05;//个人所得税税率
	private BigDecimal ycjd_YNSE_hj_02_10_51_54;//营城教地合计金额
	private BigDecimal yhs_YNSE_hj_16;//印花税应纳税额
	private BigDecimal all_jsje_hj;//合计计税金额
	private BigDecimal all_ynse_hj;//合计应纳税额
	
	//ADDED BY ZHANGJ
	private BigDecimal grsds_JMSE_hj_05;//个人所得税减免金额
	private BigDecimal grsds_SJJE_hj_05;//个人所得税实缴金额
	private BigDecimal yys_JMSE_hj_02;//营业税减免金额
	private BigDecimal yys_SJJE_hj_02;//营业税实缴金额
	private BigDecimal all_jmse_hj;//合计减免金额
	private BigDecimal all_sjje_hj;//合计实缴税额
	private BigDecimal tdzzs_JSJE_hj_08;//土地增值税计税金额
	private BigDecimal tdzzs_YNSE_hj_08;//土地增值税应纳金额
	private BigDecimal tdzzs_JMSE_hj_08;//土地增值税减免金额
	private BigDecimal tdzzs_SJJE_hj_08;//土地增值税实缴金额
	private BigDecimal tdzzs_sl_08;//土地增值税税率
	private BigDecimal ycjd_JMSE_hj_02_10_51_54;//营城教地合计减免金额
	private BigDecimal ycjd_SJJE_hj_02_10_51_54;//营城教地合计实缴金额
	private BigDecimal yhs_JMSE_hj_16;//印花税减免税额
	private BigDecimal yhs_SJJE_hj_16;//印花税实缴税额
	
	//契税查询信息（add by tangchangfu 2014-06-12）
	private BigDecimal qs_sl;//契税税率
	private BigDecimal qs_jsje_hj;//契税合计计税金额 //add end
	
	

	public BigDecimal getGrsds_JSJE_hj_05() {
		return grsds_JSJE_hj_05;
	}
	public void setGrsds_JSJE_hj_05(BigDecimal grsds_JSJE_hj_05) {
		this.grsds_JSJE_hj_05 = grsds_JSJE_hj_05;
	}
	public BigDecimal getGrsds_YNSE_hj_05() {
		return grsds_YNSE_hj_05;
	}
	public void setGrsds_YNSE_hj_05(BigDecimal grsds_YNSE_hj_05) {
		this.grsds_YNSE_hj_05 = grsds_YNSE_hj_05;
	}
	public BigDecimal getYcjd_YNSE_hj_02_10_51_54() {
		return ycjd_YNSE_hj_02_10_51_54;
	}
	public void setYcjd_YNSE_hj_02_10_51_54(BigDecimal ycjd_YNSE_hj_02_10_51_54) {
		this.ycjd_YNSE_hj_02_10_51_54 = ycjd_YNSE_hj_02_10_51_54;
	}
	public BigDecimal getYhs_YNSE_hj_16() {
		return yhs_YNSE_hj_16;
	}
	public void setYhs_YNSE_hj_16(BigDecimal yhs_YNSE_hj_16) {
		this.yhs_YNSE_hj_16 = yhs_YNSE_hj_16;
	}
	public BigDecimal getAll_jsje_hj() {
		return all_jsje_hj;
	}
	public void setAll_jsje_hj(BigDecimal all_jsje_hj) {
		this.all_jsje_hj = all_jsje_hj;
	}
	public BigDecimal getAll_ynse_hj() {
		return all_ynse_hj;
	}
	public void setAll_ynse_hj(BigDecimal all_ynse_hj) {
		this.all_ynse_hj = all_ynse_hj;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getYys_JSJE_hj_02() {
		return yys_JSJE_hj_02;
	}
	public void setYys_JSJE_hj_02(BigDecimal yys_JSJE_hj_02) {
		this.yys_JSJE_hj_02 = yys_JSJE_hj_02;
	}
	public BigDecimal getYys_YNSE_hj_02() {
		return yys_YNSE_hj_02;
	}
	public void setYys_YNSE_hj_02(BigDecimal yys_YNSE_hj_02) {
		this.yys_YNSE_hj_02 = yys_YNSE_hj_02;
	}
	public String getSbbh() {
		return sbbh;
	}
	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	public BigDecimal getGrsds_sl_05() {
		return grsds_sl_05;
	}
	public void setGrsds_sl_05(BigDecimal grsds_sl_05) {
		this.grsds_sl_05 = grsds_sl_05;
	}
	public BigDecimal getQs_sl() {
		return qs_sl;
	}
	public void setQs_sl(BigDecimal qs_sl) {
		this.qs_sl = qs_sl;
	}
	public BigDecimal getQs_jsje_hj() {
		return qs_jsje_hj;
	}
	public void setQs_jsje_hj(BigDecimal qs_jsje_hj) {
		this.qs_jsje_hj = qs_jsje_hj;
	}
	public BigDecimal getYys_JMSE_hj_02() {
		return yys_JMSE_hj_02;
	}
	public void setYys_JMSE_hj_02(BigDecimal yysJMSEHj_02) {
		yys_JMSE_hj_02 = yysJMSEHj_02;
	}
	public BigDecimal getYys_SJJE_hj_02() {
		return yys_SJJE_hj_02;
	}
	public void setYys_SJJE_hj_02(BigDecimal yysSJJEHj_02) {
		yys_SJJE_hj_02 = yysSJJEHj_02;
	}
	public BigDecimal getGrsds_JMSE_hj_05() {
		return grsds_JMSE_hj_05;
	}
	public void setGrsds_JMSE_hj_05(BigDecimal grsdsJMSEHj_05) {
		grsds_JMSE_hj_05 = grsdsJMSEHj_05;
	}
	public BigDecimal getGrsds_SJJE_hj_05() {
		return grsds_SJJE_hj_05;
	}
	public void setGrsds_SJJE_hj_05(BigDecimal grsdsSJJEHj_05) {
		grsds_SJJE_hj_05 = grsdsSJJEHj_05;
	}
	public BigDecimal getYcjd_JMSE_hj_02_10_51_54() {
		return ycjd_JMSE_hj_02_10_51_54;
	}
	public void setYcjd_JMSE_hj_02_10_51_54(BigDecimal ycjdJMSEHj_02_10_51_54) {
		ycjd_JMSE_hj_02_10_51_54 = ycjdJMSEHj_02_10_51_54;
	}
	public BigDecimal getYcjd_SJJE_hj_02_10_51_54() {
		return ycjd_SJJE_hj_02_10_51_54;
	}
	public void setYcjd_SJJE_hj_02_10_51_54(BigDecimal ycjdSJJEHj_02_10_51_54) {
		ycjd_SJJE_hj_02_10_51_54 = ycjdSJJEHj_02_10_51_54;
	}
	public BigDecimal getYhs_JMSE_hj_16() {
		return yhs_JMSE_hj_16;
	}
	public void setYhs_JMSE_hj_16(BigDecimal yhsJMSEHj_16) {
		yhs_JMSE_hj_16 = yhsJMSEHj_16;
	}
	public BigDecimal getYhs_SJJE_hj_16() {
		return yhs_SJJE_hj_16;
	}
	public void setYhs_SJJE_hj_16(BigDecimal yhsSJJEHj_16) {
		yhs_SJJE_hj_16 = yhsSJJEHj_16;
	}
	public BigDecimal getAll_jmse_hj() {
		return all_jmse_hj;
	}
	public void setAll_jmse_hj(BigDecimal allJmseHj) {
		all_jmse_hj = allJmseHj;
	}
	public BigDecimal getAll_sjje_hj() {
		return all_sjje_hj;
	}
	public void setAll_sjje_hj(BigDecimal allSjjeHj) {
		all_sjje_hj = allSjjeHj;
	}
	public BigDecimal getTdzzs_JSJE_hj_08() {
		return tdzzs_JSJE_hj_08;
	}
	public void setTdzzs_JSJE_hj_08(BigDecimal tdzzsJSJEHj_08) {
		tdzzs_JSJE_hj_08 = tdzzsJSJEHj_08;
	}
	public BigDecimal getTdzzs_YNSE_hj_08() {
		return tdzzs_YNSE_hj_08;
	}
	public void setTdzzs_YNSE_hj_08(BigDecimal tdzzsYNSEHj_08) {
		tdzzs_YNSE_hj_08 = tdzzsYNSEHj_08;
	}
	public BigDecimal getTdzzs_JMSE_hj_08() {
		return tdzzs_JMSE_hj_08;
	}
	public void setTdzzs_JMSE_hj_08(BigDecimal tdzzsJMSEHj_08) {
		tdzzs_JMSE_hj_08 = tdzzsJMSEHj_08;
	}
	public BigDecimal getTdzzs_SJJE_hj_08() {
		return tdzzs_SJJE_hj_08;
	}
	public void setTdzzs_SJJE_hj_08(BigDecimal tdzzsSJJEHj_08) {
		tdzzs_SJJE_hj_08 = tdzzsSJJEHj_08;
	}
	public BigDecimal getTdzzs_sl_08() {
		return tdzzs_sl_08;
	}
	public void setTdzzs_sl_08(BigDecimal tdzzsSl_08) {
		tdzzs_sl_08 = tdzzsSl_08;
	}
	
}
