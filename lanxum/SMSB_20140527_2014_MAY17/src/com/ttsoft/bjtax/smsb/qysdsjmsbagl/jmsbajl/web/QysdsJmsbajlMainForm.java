package com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web;

import java.util.List;

import com.ttsoft.framework.form.BaseForm;


/**
 * <p>
 * Title: 企业所得税减免备案-公用From
 * </p>
 * <p>
 * Description: 企业所得税减免备案-公用From
 * </p>
 * 
 * @author 开发二部 - 刘超
 * @version 1.0
 */
public class QysdsJmsbajlMainForm extends BaseForm{
	// 备案申请文书号
	private String basqwsh;
	// 备案申请编号
	private String basqbh;
	// 备案年度
	private String band;
	// 计算机代码
	private String jsjdm;
	// 减免备案事项代码
	private String jmbasxdm;
	// 税务机关组织机构代码
	private String swjgzzjgdm;
	// 税种代码
	private String szdm;
	// 申请状态代码
	private String sqzt;
	// 申请类型代码 0：网上申请，1：上门申请
	private String sqlxdm;
	// 文书打印录入项：备案减免税额
	private String bajmse;
	// 文书打印录入项：备案减免比例
	private String bajmbl;
	// 文书打印录入项：减免税政策执行情况
	private String jmszczxqk;
	// 文书打印录入项：起始日期
	private String qsrq;
	// 文书打印录入项：截至日期
	private String jzrq;
	//提交人
	private String tjr;
	//提交时间
	private String tjsj;
	//审核人
	private String shr;
	//审核时间
	private String shsj;
	//查询条件：备案申请编号
	private String filter_basqbh;	
	//查询条件：计算机代码
	private String filter_jsjdm;
	//查询条件：纳税人名称
	private String filter_nsrmc;
	//查询条件：备案年度
	private String filter_band;
	//查询条件：申请类型
	private String filter_sqlx;
	//查询条件：申请状态
	private String filter_sqzt;
	//查询条件：所属主管税务机关
	private String filter_zgswjg;	
	//查询条件：减免税备案事项
	private String filter_jmsbasx;
	//查询条件：所属主管税务机关列表
	private List filter_zgswjgList;
	//查询条件：减免税备案事项列表
	private List filter_jmsbasxList;
	
	//新增条件：计算机代码
	private String add_jsjdm;
	//新增条件：备案年度
	private String add_band;
	//新增条件： 减免税备案事项
	private String add_jmsbasx;
	
	//默认不可编辑：录入日期
	private String mr_lrrq;
	//默认不可编辑：备案年度
	private String mr_band;
	//默认不可编辑：录入人
	private String mr_lrr;
	
	//操作类型
	private String czlx;
	
	//减免资料文档数据
    private String zl ;
    //审核减免资料文档数据
    private String shzl ;
	

	
	private String nsrmc;	
	private String zgsws;
	private String jjlx;
	private String sshy;
	private String qylb;	
	private String lxr;
	private String lxdh;

	
	private String rowsPerPage;//记录中没页记录数
	private String currentPage;//监控记录当前所在页
	
	private String zfsm;
	private String zfyy;
	private String qtzfyy;
	private String zfbglx;
	// 文书打印录入项：减免税政策执行情况
	
	public String getQtzfyy() {
		return qtzfyy;
	}
	public void setQtzfyy(String qtzfyy) {
		this.qtzfyy = qtzfyy;
	}
	public String getBasqwsh() {
		return basqwsh;
	}
	public void setBasqwsh(String basqwsh) {
		this.basqwsh = basqwsh;
	}
	public String getBasqbh() {
		return basqbh;
	}
	public void setBasqbh(String basqbh) {
		this.basqbh = basqbh;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getJmbasxdm() {
		return jmbasxdm;
	}
	public void setJmbasxdm(String jmbasxdm) {
		this.jmbasxdm = jmbasxdm;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSzdm() {
		return szdm;
	}
	public void setSzdm(String szdm) {
		this.szdm = szdm;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public String getSqlxdm() {
		return sqlxdm;
	}
	public void setSqlxdm(String sqlxdm) {
		this.sqlxdm = sqlxdm;
	}
	public String getQsrq() {
		return qsrq;
	}
	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}
	public String getJzrq() {
		return jzrq;
	}
	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	public String getFilter_jsjdm() {
		return filter_jsjdm;
	}
	public void setFilter_jsjdm(String filter_jsjdm) {
		this.filter_jsjdm = filter_jsjdm;
	}
	public String getFilter_nsrmc() {
		return filter_nsrmc;
	}
	public void setFilter_nsrmc(String filter_nsrmc) {
		this.filter_nsrmc = filter_nsrmc;
	}
	public String getFilter_band() {
		return filter_band;
	}
	public void setFilter_band(String filter_band) {
		this.filter_band = filter_band;
	}
	public String getFilter_sqlx() {
		return filter_sqlx;
	}
	public void setFilter_sqlx(String filter_sqlx) {
		this.filter_sqlx = filter_sqlx;
	}
	public String getFilter_sqzt() {
		return filter_sqzt;
	}
	public void setFilter_sqzt(String filter_sqzt) {
		this.filter_sqzt = filter_sqzt;
	}
	public String getFilter_jmsbasx() {
		return filter_jmsbasx;
	}
	public void setFilter_jmsbasx(String filter_jmsbasx) {
		this.filter_jmsbasx = filter_jmsbasx;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getZgsws() {
		return zgsws;
	}
	public void setZgsws(String zgsws) {
		this.zgsws = zgsws;
	}
	public String getJjlx() {
		return jjlx;
	}
	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getQylb() {
		return qylb;
	}
	public void setQylb(String qylb) {
		this.qylb = qylb;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public String getTjsj() {
		return tjsj;
	}
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getCzlx() {
		return czlx;
	}
	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}
	public String getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getAdd_band() {
		return add_band;
	}
	public void setAdd_band(String add_band) {
		this.add_band = add_band;
	}
	public String getAdd_jmsbasx() {
		return add_jmsbasx;
	}
	public void setAdd_jmsbasx(String add_jmsbasx) {
		this.add_jmsbasx = add_jmsbasx;
	}
	public String getAdd_jsjdm() {
		return add_jsjdm;
	}
	public void setAdd_jsjdm(String add_jsjdm) {
		this.add_jsjdm = add_jsjdm;
	}
	public String getBajmbl() {
		return bajmbl;
	}
	public void setBajmbl(String bajmbl) {
		this.bajmbl = bajmbl;
	}
	public String getBajmse() {
		return bajmse;
	}
	public void setBajmse(String bajmse) {
		this.bajmse = bajmse;
	}
	public String getJmszczxqk() {
		return jmszczxqk;
	}
	public void setJmszczxqk(String jmszczxqk) {
		this.jmszczxqk = jmszczxqk;
	}
	public String getFilter_zgswjg() {
		return filter_zgswjg;
	}
	public void setFilter_zgswjg(String filter_zgswjg) {
		this.filter_zgswjg = filter_zgswjg;
	}
	public List getFilter_jmsbasxList() {
		return filter_jmsbasxList;
	}
	public void setFilter_jmsbasxList(List filter_jmsbasxList) {
		this.filter_jmsbasxList = filter_jmsbasxList;
	}
	public List getFilter_zgswjgList() {
		return filter_zgswjgList;
	}
	public void setFilter_zgswjgList(List filter_zgswjgList) {
		this.filter_zgswjgList = filter_zgswjgList;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getShzl() {
		return shzl;
	}
	public void setShzl(String shzl) {
		this.shzl = shzl;
	}
	public String getMr_band() {
		return mr_band;
	}
	public void setMr_band(String mr_band) {
		this.mr_band = mr_band;
	}
	public String getMr_lrr() {
		return mr_lrr;
	}
	public void setMr_lrr(String mr_lrr) {
		this.mr_lrr = mr_lrr;
	}
	public String getMr_lrrq() {
		return mr_lrrq;
	}
	public void setMr_lrrq(String mr_lrrq) {
		this.mr_lrrq = mr_lrrq;
	}
	public String getFilter_basqbh() {
		return filter_basqbh;
	}
	public void setFilter_basqbh(String filter_basqbh) {
		this.filter_basqbh = filter_basqbh;
	}
	public String getZfsm() {
		return zfsm;
	}
	public void setZfsm(String zfsm) {
		this.zfsm = zfsm;
	}
	public String getZfyy() {
		return zfyy;
	}
	public void setZfyy(String zfyy) {
		this.zfyy = zfyy;
	}
	public String getZfbglx() {
		return zfbglx;
	}
	public void setZfbglx(String zfbglx) {
		this.zfbglx = zfbglx;
	}

}
