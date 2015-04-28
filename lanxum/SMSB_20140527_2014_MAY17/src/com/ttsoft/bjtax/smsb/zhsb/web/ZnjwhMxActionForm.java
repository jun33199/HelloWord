package com.ttsoft.bjtax.smsb.zhsb.web;

import java.util.ArrayList;

import com.ttsoft.framework.form.BaseForm;

public class ZnjwhMxActionForm extends BaseForm{
	
    private String[] columns =
    {
    "szsmdm", "szsmmc", "kssl", "jsje", "sjse"};

//申报日期
//银行名称
//银行代码
//账号
//单位名称
/**
 * 明细数据列表
 */
private java.util.ArrayList dataList = new ArrayList();

/**
 * 单位名称
 */
private String dwmc;

/**
 * 计算机代码
 */
private String jsjdm;

/**
 * 银行名称
 */
private String yhmc;

/**
 * 帐号
 */
private String zh;

/**
 * 税款所属开始日期
 */
private String skssksrq;

/**
 * 税款所属结束日期
 */
private String skssjsrq;

/**
 * 缴款凭证号
 */
private String jkpzh;

/**
 * 录入日期
 */
private String lrrq;

/**
 * 税务机关组织机构名称
 */
private String swjgzzjgmc;

/**
 * 税种名称
 */
private String szmc;

/**
 * 经营地址联系电话
 */
private String jydzlxdm;

/**
 * 纳税人名称
 */
private String nsrmc;

/**
 * 预算科目代码
 */
private String yskmdm;

/**
 * 预算科目名称
 */
private String yskmmc;

/**
 * 预算级次名称
 */
private String ysjcmc;

/**
 * 国库组织机构名称
 */
private String gkzzjgmc;

/**
 * 限缴日期
 */
private String xjrq;

/**
 * 申报编号
 */
private String sbbh;

/**
 * 市级分成
 */
private String sjfc;

/**
 * 区级分成
 */
private String qjfc;

/**
 * 预算级次代码
 */
private String ysjcdm;

/**
 * js数组
 */
private String scriptStr;

/**
 * 实缴金额
 */
private String sjje;

/**
 * 跳转页面
 */
private String forward;

/**
 * 数据类型
 */
private String sjly;

/**
 * 返回时用来判断是否返回申报编号
 */
private String presbbh;

/**
 * 合计金额大写
 */
private String hjjedx;

/**
 * 合计金额
 */
private String hjje;
/**
 * 隶属关系
 */
private String lsgx;
/**
 * 注册类型名称
 */
private String zclxmc;
/**
 * 明细品目名称
 */
private String mxPmmc;
/**
 * 明细课税数量
 */
private String mxKssl;
/**
 * 明细缴税金额
 */
private String mxJsje;
/**
 * 明细，税率
 */
private String mxSl;
/**
 * 明细实缴税额
 */
private String mxSjse;
/**
 * 税款类型
 */
private String sklx;
/**
 * 税款国库号
 */
private String skgkh;
/**
 * 申报日期
 */
private String sbrq;
/**
 * 地方税务机关
 */
private String dfswjg;
/**
 * 币种
 */
private String bz;

/**
 * 起始日期
 */
private String qsrq;

/**
 * 截止日期
 */
private String jzrq;

public String getBz() {
	return bz;
}
public void setBz(String bz) {
	this.bz = bz;
}
public String[] getColumns() {
	return columns;
}
public void setColumns(String[] columns) {
	this.columns = columns;
}
public java.util.ArrayList getDataList() {
	return dataList;
}
public void setDataList(java.util.ArrayList dataList) {
	this.dataList = dataList;
}
public String getDfswjg() {
	return dfswjg;
}
public void setDfswjg(String dfswjg) {
	this.dfswjg = dfswjg;
}
public String getDwmc() {
	return dwmc;
}
public void setDwmc(String dwmc) {
	this.dwmc = dwmc;
}
public String getForward() {
	return forward;
}
public void setForward(String forward) {
	this.forward = forward;
}
public String getGkzzjgmc() {
	return gkzzjgmc;
}
public void setGkzzjgmc(String gkzzjgmc) {
	this.gkzzjgmc = gkzzjgmc;
}
public String getHjje() {
	return hjje;
}
public void setHjje(String hjje) {
	this.hjje = hjje;
}
public String getHjjedx() {
	return hjjedx;
}
public void setHjjedx(String hjjedx) {
	this.hjjedx = hjjedx;
}
public String getJkpzh() {
	return jkpzh;
}
public void setJkpzh(String jkpzh) {
	this.jkpzh = jkpzh;
}
public String getJsjdm() {
	return jsjdm;
}
public void setJsjdm(String jsjdm) {
	this.jsjdm = jsjdm;
}
public String getJydzlxdm() {
	return jydzlxdm;
}
public void setJydzlxdm(String jydzlxdm) {
	this.jydzlxdm = jydzlxdm;
}
public String getLrrq() {
	return lrrq;
}
public void setLrrq(String lrrq) {
	this.lrrq = lrrq;
}
public String getLsgx() {
	return lsgx;
}
public void setLsgx(String lsgx) {
	this.lsgx = lsgx;
}
public String getMxJsje() {
	return mxJsje;
}
public void setMxJsje(String mxJsje) {
	this.mxJsje = mxJsje;
}
public String getMxKssl() {
	return mxKssl;
}
public void setMxKssl(String mxKssl) {
	this.mxKssl = mxKssl;
}
public String getMxPmmc() {
	return mxPmmc;
}
public void setMxPmmc(String mxPmmc) {
	this.mxPmmc = mxPmmc;
}
public String getMxSjse() {
	return mxSjse;
}
public void setMxSjse(String mxSjse) {
	this.mxSjse = mxSjse;
}
public String getMxSl() {
	return mxSl;
}
public void setMxSl(String mxSl) {
	this.mxSl = mxSl;
}
public String getNsrmc() {
	return nsrmc;
}
public void setNsrmc(String nsrmc) {
	this.nsrmc = nsrmc;
}
public String getPresbbh() {
	return presbbh;
}
public void setPresbbh(String presbbh) {
	this.presbbh = presbbh;
}
public String getQjfc() {
	return qjfc;
}
public void setQjfc(String qjfc) {
	this.qjfc = qjfc;
}
public String getSbbh() {
	return sbbh;
}
public void setSbbh(String sbbh) {
	this.sbbh = sbbh;
}
public String getSbrq() {
	return sbrq;
}
public void setSbrq(String sbrq) {
	this.sbrq = sbrq;
}
public String getScriptStr() {
	return scriptStr;
}
public void setScriptStr(String scriptStr) {
	this.scriptStr = scriptStr;
}
public String getSjfc() {
	return sjfc;
}
public void setSjfc(String sjfc) {
	this.sjfc = sjfc;
}
public String getSjje() {
	return sjje;
}
public void setSjje(String sjje) {
	this.sjje = sjje;
}
public String getSjly() {
	return sjly;
}
public void setSjly(String sjly) {
	this.sjly = sjly;
}
public String getSkgkh() {
	return skgkh;
}
public void setSkgkh(String skgkh) {
	this.skgkh = skgkh;
}
public String getSklx() {
	return sklx;
}
public void setSklx(String sklx) {
	this.sklx = sklx;
}
public String getSkssjsrq() {
	return skssjsrq;
}
public void setSkssjsrq(String skssjsrq) {
	this.skssjsrq = skssjsrq;
}
public String getSkssksrq() {
	return skssksrq;
}
public void setSkssksrq(String skssksrq) {
	this.skssksrq = skssksrq;
}
public String getSwjgzzjgmc() {
	return swjgzzjgmc;
}
public void setSwjgzzjgmc(String swjgzzjgmc) {
	this.swjgzzjgmc = swjgzzjgmc;
}
public String getSzmc() {
	return szmc;
}
public void setSzmc(String szmc) {
	this.szmc = szmc;
}
public String getXjrq() {
	return xjrq;
}
public void setXjrq(String xjrq) {
	this.xjrq = xjrq;
}
public String getYhmc() {
	return yhmc;
}
public void setYhmc(String yhmc) {
	this.yhmc = yhmc;
}
public String getYsjcdm() {
	return ysjcdm;
}
public void setYsjcdm(String ysjcdm) {
	this.ysjcdm = ysjcdm;
}
public String getYsjcmc() {
	return ysjcmc;
}
public void setYsjcmc(String ysjcmc) {
	this.ysjcmc = ysjcmc;
}
public String getYskmdm() {
	return yskmdm;
}
public void setYskmdm(String yskmdm) {
	this.yskmdm = yskmdm;
}
public String getYskmmc() {
	return yskmmc;
}
public void setYskmmc(String yskmmc) {
	this.yskmmc = yskmmc;
}
public String getZclxmc() {
	return zclxmc;
}
public void setZclxmc(String zclxmc) {
	this.zclxmc = zclxmc;
}
public String getZh() {
	return zh;
}
public void setZh(String zh) {
	this.zh = zh;
}
public String getJzrq() {
	return jzrq;
}
public void setJzrq(String jzrq) {
	this.jzrq = jzrq;
}
public String getQsrq() {
	return qsrq;
}
public void setQsrq(String qsrq) {
	this.qsrq = qsrq;
}
	

}
