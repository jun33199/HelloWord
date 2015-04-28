package com.lscdz.qysds.application.nsrjbxx2014.vo.response;

import java.sql.Timestamp;

public class QyjbxxVo {
	private Timestamp skksrq;
	private Timestamp skjsrq;
	private String nsrmc;
	private String nsrsbh;
	private String nsrzt;
	private String nsrztmc;//纳税人名称
	private String jsjdm;
	private String zgswjgdm;//主管税务机关
	private String zgswjgmc;
	private String zgfjdm;//主管分局
	private String zgfjmc;
	private String syjdlxdm;//税源鉴定类型代码
	private String syjdlxmc;
	private Timestamp syjdqsrq;//税源鉴定起始日期
	private Timestamp syjdjzrq;
	private boolean sfjrqsq;//是否进入清算期
	private boolean sfjsqs;//是否结束清算期
	private boolean sfwxxwlqy;//是否为小型微利企业
	private boolean fzjgsfftqysds;//分支机构是否分摊企业所得税
	private String zsfsdm;
	private String zsfsmc;
	private String sshydm;//所属行业代码
	private String sshymc;//所属行业名称
	
	public String getSshydm() {
		return sshydm;
	}
	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}
	public String getSshymc() {
		return sshymc;
	}
	public void setSshymc(String sshymc) {
		this.sshymc = sshymc;
	}
	public String getZsfsdm() {
		return zsfsdm;
	}
	public void setZsfsdm(String zsfsdm) {
		this.zsfsdm = zsfsdm;
	}
	public String getZsfsmc() {
		return zsfsmc;
	}
	public void setZsfsmc(String zsfsmc) {
		this.zsfsmc = zsfsmc;
	}
	private String djzclx;//登记注册类型
	public String getDjzclx() {
		return djzclx;
	}
	public void setDjzclx(String djzclx) {
		this.djzclx = djzclx;
	}
	public Timestamp getSkksrq() {
		return skksrq;
	}
	public void setSkksrq(Timestamp skksrq) {
		this.skksrq = skksrq;
	}
	public Timestamp getSkjsrq() {
		return skjsrq;
	}
	public void setSkjsrq(Timestamp skjsrq) {
		this.skjsrq = skjsrq;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getNsrzt() {
		return nsrzt;
	}
	public void setNsrzt(String nsrzt) {
		this.nsrzt = nsrzt;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public Timestamp getSyjdqsrq() {
		return syjdqsrq;
	}
	public void setSyjdqsrq(Timestamp syjdqsrq) {
		this.syjdqsrq = syjdqsrq;
	}
	public Timestamp getSyjdjzrq() {
		return syjdjzrq;
	}
	public void setSyjdjzrq(Timestamp syjdjzrq) {
		this.syjdjzrq = syjdjzrq;
	}
	public boolean getSfjrqsq() {
		return sfjrqsq;
	}
	public void setSfjrqsq(boolean sfjrqsq) {
		this.sfjrqsq = sfjrqsq;
	}
	public boolean getSfjsqs() {
		return sfjsqs;
	}
	public void setSfjsqs(boolean sfjsqs) {
		this.sfjsqs = sfjsqs;
	}
	public boolean getSfwxxwlqy() {
		return sfwxxwlqy;
	}
	public void setSfwxxwlqy(boolean sfwxxwlqy) {
		this.sfwxxwlqy = sfwxxwlqy;
	}
	public boolean getFzjgsfftqysds() {
		return fzjgsfftqysds;
	}
	public void setFzjgsfftqysds(boolean fzjgsfftqysds) {
		this.fzjgsfftqysds = fzjgsfftqysds;
	}

	public String getNsrztmc() {
		return nsrztmc;
	}
	public void setNsrztmc(String nsrztmc) {
		this.nsrztmc = nsrztmc;
	}
	public String getSyjdlxdm() {
		return syjdlxdm;
	}
	public void setSyjdlxdm(String syjdlxdm) {
		this.syjdlxdm = syjdlxdm;
	}
	public String getSyjdlxmc() {
		return syjdlxmc;
	}
	public void setSyjdlxmc(String syjdlxmc) {
		this.syjdlxmc = syjdlxmc;
	}
	public String getZgswjgdm() {
		return zgswjgdm;
	}
	public void setZgswjgdm(String zgswjgdm) {
		this.zgswjgdm = zgswjgdm;
	}
	public String getZgswjgmc() {
		return zgswjgmc;
	}
	public void setZgswjgmc(String zgswjgmc) {
		this.zgswjgmc = zgswjgmc;
	}
	public String getZgfjdm() {
		return zgfjdm;
	}
	public void setZgfjdm(String zgfjdm) {
		this.zgfjdm = zgfjdm;
	}
	public String getZgfjmc() {
		return zgfjmc;
	}
	public void setZgfjmc(String zgfjmc) {
		this.zgfjmc = zgfjmc;
	}
}
