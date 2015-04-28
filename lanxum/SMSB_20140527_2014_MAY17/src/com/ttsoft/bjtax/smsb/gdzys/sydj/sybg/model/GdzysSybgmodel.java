package com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.model;

import java.io.Serializable;
import java.util.List;

public class GdzysSybgmodel implements Serializable{

/*--------------------基本信息------------------------------*/
	
	//纳税人名称
	private String nsrmc;
	
	//计算机代码
	private String jsjdm;
	
	//申报序列号
	private String sbbxlh;
	
	//纳税人类型
	private String nsrlx;
	
	//是否市局审批
	private String sfsjsp;
	
	//税源类型
	private String sylx;
	
	//纳税人识别号
	private String nsrsbh;
	
	//纳税人所属行业
	private String nsrsshy;
	
	//纳税人详细地址
	private String nsrxxdz;
	
	//企业登记注册类型
	private String qydjzclx;
	
	//开户银行
	private String khyh;
	
	//银行账号
	private String yhzh;
	
	//联系人姓名
	private String lxrxm;
	
	//联系电话
	private String lxdh;
	
	//身份证照类型
	private String sfzzlx;
	
	//身份证照号码
	private String sfzzhm;
	
	//减免理由
	private String jmly;
	
	//备注
	private String bz;
	
	//申报状态
	private String sbzt;
	/*-----------土地信息------------------------------------*/
	//计税面积
	private String jsmj;
	
	//计征税额
	private String jzse;
	
	//减免面积
	private String jmmj;
	
	//减免税额
	private String jmse;
	
	//应税面积
	private String ysmj;
	
	//应纳税额
	private String ynse;
	
	//占地批文号
	private String zdpwh;

	//土地坐落地址
	private String tdzldz;
	
	//批准占地面积
	private String pzzdmj;
	
	//建设项目名称
	private String jsxmmc;
	
	//实际占地面积
	private String sjzdmj;
	
	//占地时间
	private String zdsj;
	
	//减免信息录入
	private String jmxxlr;
	
	//录入文号
	private String lrwh;
	
	
	//土地申报明细
	private List sbmx;
	
	//减免信息
	private List jmxx;
	
	/*------------创建信息----------------------------------*/
	//创建人
	private String cjr;
	
	//创建时间
	private String cjsj;
	
	//确认人
	private String qrr;
	
	//确认时间
	private String qrsj;
	
	//确认状态
	private String qrzt;
	
	//市局确认人
	private String sjqrr;
	
	//市局确认时间
	private String sjqrsj;
	
	//市局确认状态
	private String sjqrzt;
	
	/*------------变更信息信息----------------------------------*/
	
	//入库标志----0：未入库  ，1：已入库
	private String rkbz ="0";
	
	//是否已出缴款书--0:未出，1：已出
	private String ycjk = "0";
	
	//是否已出减免证明--0：未出，1已出
	private String ycjm = "0";
	
	/*----------------------------------------------------------------------------------------*/
	
	public String getNsrmc() {
		return nsrmc;
	}

	public String getRkbz() {
		return rkbz;
	}

	public void setRkbz(String rkbz) {
		this.rkbz = rkbz;
	}

	public String getYcjk() {
		return ycjk;
	}

	public void setYcjk(String ycjk) {
		this.ycjk = ycjk;
	}

	public String getYcjm() {
		return ycjm;
	}

	public void setYcjm(String ycjm) {
		this.ycjm = ycjm;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	

	public String getSbbxlh() {
		return sbbxlh;
	}

	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}

	public String getNsrlx() {
		return nsrlx;
	}

	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}

	public String getSfsjsp() {
		return sfsjsp;
	}

	public void setSfsjsp(String sfsjsp) {
		this.sfsjsp = sfsjsp;
	}

	public String getSylx() {
		return sylx;
	}

	public void setSylx(String sylx) {
		this.sylx = sylx;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrsshy() {
		return nsrsshy;
	}

	public void setNsrsshy(String nsrsshy) {
		this.nsrsshy = nsrsshy;
	}

	public String getNsrxxdz() {
		return nsrxxdz;
	}

	public void setNsrxxdz(String nsrxxdz) {
		this.nsrxxdz = nsrxxdz;
	}

	public String getQydjzclx() {
		return qydjzclx;
	}

	public void setQydjzclx(String qydjzclx) {
		this.qydjzclx = qydjzclx;
	}

	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getLxrxm() {
		return lxrxm;
	}

	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getSfzzlx() {
		return sfzzlx;
	}

	public void setSfzzlx(String sfzzlx) {
		this.sfzzlx = sfzzlx;
	}

	public String getSfzzhm() {
		return sfzzhm;
	}

	public void setSfzzhm(String sfzzhm) {
		this.sfzzhm = sfzzhm;
	}

	public String getJmly() {
		return jmly;
	}

	public void setJmly(String jmly) {
		this.jmly = jmly;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSbzt() {
		return sbzt;
	}

	public void setSbzt(String sbzt) {
		this.sbzt = sbzt;
	}

	public String getJsmj() {
		return jsmj;
	}

	public void setJsmj(String jsmj) {
		this.jsmj = jsmj;
	}

	public String getJzse() {
		return jzse;
	}

	public void setJzse(String jzse) {
		this.jzse = jzse;
	}

	public String getJmmj() {
		return jmmj;
	}

	public void setJmmj(String jmmj) {
		this.jmmj = jmmj;
	}

	public String getJmse() {
		return jmse;
	}

	public void setJmse(String jmse) {
		this.jmse = jmse;
	}

	public String getYsmj() {
		return ysmj;
	}

	public void setYsmj(String ysmj) {
		this.ysmj = ysmj;
	}

	public String getYnse() {
		return ynse;
	}

	public void setYnse(String ynse) {
		this.ynse = ynse;
	}

	public String getZdpwh() {
		return zdpwh;
	}

	public void setZdpwh(String zdpwh) {
		this.zdpwh = zdpwh;
	}

	public String getTdzldz() {
		return tdzldz;
	}

	public void setTdzldz(String tdzldz) {
		this.tdzldz = tdzldz;
	}

	public String getPzzdmj() {
		return pzzdmj;
	}

	public void setPzzdmj(String pzzdmj) {
		this.pzzdmj = pzzdmj;
	}

	public String getJsxmmc() {
		return jsxmmc;
	}

	public void setJsxmmc(String jsxmmc) {
		this.jsxmmc = jsxmmc;
	}

	public String getSjzdmj() {
		return sjzdmj;
	}

	public void setSjzdmj(String sjzdmj) {
		this.sjzdmj = sjzdmj;
	}

	public String getZdsj() {
		return zdsj;
	}

	public void setZdsj(String zdsj) {
		this.zdsj = zdsj;
	}

	public String getJmxxlr() {
		return jmxxlr;
	}

	public void setJmxxlr(String jmxxlr) {
		this.jmxxlr = jmxxlr;
	}

	public String getLrwh() {
		return lrwh;
	}

	public void setLrwh(String lrwh) {
		this.lrwh = lrwh;
	}

	public List getSbmx() {
		return sbmx;
	}

	public void setSbmx(List sbmx) {
		this.sbmx = sbmx;
	}

	public List getJmxx() {
		return jmxx;
	}

	public void setJmxx(List jmxx) {
		this.jmxx = jmxx;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getQrr() {
		return qrr;
	}

	public void setQrr(String qrr) {
		this.qrr = qrr;
	}

	public String getQrsj() {
		return qrsj;
	}

	public void setQrsj(String qrsj) {
		this.qrsj = qrsj;
	}

	public String getQrzt() {
		return qrzt;
	}

	public void setQrzt(String qrzt) {
		this.qrzt = qrzt;
	}

	public String getSjqrr() {
		return sjqrr;
	}

	public void setSjqrr(String sjqrr) {
		this.sjqrr = sjqrr;
	}

	public String getSjqrsj() {
		return sjqrsj;
	}

	public void setSjqrsj(String sjqrsj) {
		this.sjqrsj = sjqrsj;
	}

	public String getSjqrzt() {
		return sjqrzt;
	}

	public void setSjqrzt(String sjqrzt) {
		this.sjqrzt = sjqrzt;
	}

	
	/*-------------------------------------------------------------------------------------------------------------------------------*/
	
}
