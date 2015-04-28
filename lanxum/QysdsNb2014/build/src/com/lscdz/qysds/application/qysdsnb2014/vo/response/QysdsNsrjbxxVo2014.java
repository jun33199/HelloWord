package com.lscdz.qysds.application.qysdsnb2014.vo.response;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class QysdsNsrjbxxVo2014 {
	private String	jsjdm;	//计算机代码
	private String	nd;	//税款年度
	private String	nsrzt;	//纳税人状态
	private String	sbnd;	//申报年度
	private String	syjdlx;	//税源鉴定类型
	private String	bbmsf;	//报表描述符 如报表id,例如“1;3;4;5”
	private String	chcbjjff;	//存货成本计价方法（参见代码表：DMDB.SB_DM_QYSDS_CHCBJJFF 可多选|隔开）
	private String	cjr;	//创建人
	private Timestamp	cjrq;	//创建日期
	private String	csgjfxzhjzhy;	//从事国家非限制和禁止行业 Y：是 N：否
	private long	cyrs;	//从业人数
	private String	czjwgljy;	//存在境外关联交易 Y：是 N：否
	private String	dwtzqyid;	//对外投资企业ID
	private String	fzjgsfftqysds;	//分支机构是否分摊企业所得税  Y：是 N：否
	private String	gdzczjff;	//固定资产折旧方法（参见代码表：DMDB.SB_DM_QYSDS_GDZCZJFF 可多选|隔开）
	private String	hznsqy;	//汇总纳税企业 Y：是 N：否
	private String	hznsqylx;	//汇总纳税企业类型 01：总机构 02：按比例缴纳总机构
	private String	hzxshsff;	//坏账损失核算方法 01：备抵法 02：直接核销法
	private String	jwzzkgjmqy;	//境外中资控股居民企业 Y：是 N：否
	private String	jzbwb;	//记账本位币 01：人民币 02：其他
	private String	kjdacfd;	//会计档案的存放地
	private String	kjhsrj;	//会计核算软件
	private String	kjzchgjsffsbh;	//会计政策和估计是否发生变化 Y：是 N：否
	private String	lrr;	//录入人
	private Timestamp	lrrq;	//录入日期
	private String	nsrmc;	//纳税人名称
	private String	nsrsbh;	//纳税人识别号（税务登记证号）
	private String	qtsykjzzhkjzz;	//其他适用的会计准则或会计制度（01：一般企业 02：金融企业 03：事业单位）
	private String	qyzygdid;	//企业主要股东ID
	private String	sblx;	//申报类型 01：正常申报 02：更正申报 03：补充申报
	private String	sdsjsff;	//所得税计算方法 01：应付税款法 02：资产负债表债务法 03：其他
	private String	sfjrqsq;	//是否进入清算期  Y：是 N：否
	private String	sfwcqs;	//是否完成清算    Y：是 N：否
	private Timestamp	sksssqq;	//税款所属时期起
	private Timestamp	sksssqz;	//税款所属时期止
	private String	sndsfxxwlqy;	//上年度是否为小型微利企业  Y：是 N：否
	private String	ssgs;	//上市公司
	private String	ssgslx;	//上市公司类型 01：境内 02：境外
	private String	sshy;	//所属行业
	private String	sshymc;	//所属行业名称
	private String	swjgzzjgdm;	//税务机关组织机构代码
	private String	swjgzzjgmc;	//税务机关组织机构名称
	private String	sykjzzhkjzz;	//适用的会计准则或会计制度（参见代码表：DMDB.SB_DM_QYSDS_KJZZ）
	private Timestamp	syqdjzrq;	//税源鉴定截止日期
	private Timestamp	syqdqsrq;	//税源鉴定起始日期
	private String	version;	//版本号
	private BigDecimal	zczbje;	//注册资本金额
	private BigDecimal	zcze;	//资产总额
	private String zgfjdm;//分局代码
	private String zgfjmc;//分局名称
	private String syjdlxmc;//税源鉴定类型名称
	//private String sykjzddm;//适用会计制度
	private String sykjzzmc;//会计制度名称
	private String qtkjzzmc;//其他会计制度名称
	
	
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNsrzt() {
		return nsrzt;
	}
	public void setNsrzt(String nsrzt) {
		this.nsrzt = nsrzt;
	}
	public String getSbnd() {
		return sbnd;
	}
	public void setSbnd(String sbnd) {
		this.sbnd = sbnd;
	}
	public String getSyjdlx() {
		return syjdlx;
	}
	public void setSyjdlx(String syjdlx) {
		this.syjdlx = syjdlx;
	}
	public String getBbmsf() {
		return bbmsf;
	}
	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}
	public String getChcbjjff() {
		return chcbjjff;
	}
	public void setChcbjjff(String chcbjjff) {
		this.chcbjjff = chcbjjff;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Timestamp getCjrq() {
		return cjrq;
	}
	public void setCjrq(Timestamp cjrq) {
		this.cjrq = cjrq;
	}
	public String getCsgjfxzhjzhy() {
		return csgjfxzhjzhy;
	}
	public void setCsgjfxzhjzhy(String csgjfxzhjzhy) {
		this.csgjfxzhjzhy = csgjfxzhjzhy;
	}
	public long getCyrs() {
		return cyrs;
	}
	public void setCyrs(long cyrs) {
		this.cyrs = cyrs;
	}
	public String getCzjwgljy() {
		return czjwgljy;
	}
	public void setCzjwgljy(String czjwgljy) {
		this.czjwgljy = czjwgljy;
	}
	public String getDwtzqyid() {
		return dwtzqyid;
	}
	public void setDwtzqyid(String dwtzqyid) {
		this.dwtzqyid = dwtzqyid;
	}
	public String getFzjgsfftqysds() {
		return fzjgsfftqysds;
	}
	public void setFzjgsfftqysds(String fzjgsfftqysds) {
		this.fzjgsfftqysds = fzjgsfftqysds;
	}
	public String getGdzczjff() {
		return gdzczjff;
	}
	public void setGdzczjff(String gdzczjff) {
		this.gdzczjff = gdzczjff;
	}
	public String getHznsqy() {
		return hznsqy;
	}
	public void setHznsqy(String hznsqy) {
		this.hznsqy = hznsqy;
	}
	public String getHznsqylx() {
		return hznsqylx;
	}
	public void setHznsqylx(String hznsqylx) {
		this.hznsqylx = hznsqylx;
	}
	public String getHzxshsff() {
		return hzxshsff;
	}
	public void setHzxshsff(String hzxshsff) {
		this.hzxshsff = hzxshsff;
	}
	public String getJwzzkgjmqy() {
		return jwzzkgjmqy;
	}
	public void setJwzzkgjmqy(String jwzzkgjmqy) {
		this.jwzzkgjmqy = jwzzkgjmqy;
	}
	public String getJzbwb() {
		return jzbwb;
	}
	public void setJzbwb(String jzbwb) {
		this.jzbwb = jzbwb;
	}
	public String getKjdacfd() {
		return kjdacfd;
	}
	public void setKjdacfd(String kjdacfd) {
		this.kjdacfd = kjdacfd;
	}
	public String getKjhsrj() {
		return kjhsrj;
	}
	public void setKjhsrj(String kjhsrj) {
		this.kjhsrj = kjhsrj;
	}
	public String getKjzchgjsffsbh() {
		return kjzchgjsffsbh;
	}
	public void setKjzchgjsffsbh(String kjzchgjsffsbh) {
		this.kjzchgjsffsbh = kjzchgjsffsbh;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
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
	
	public String getQtsykjzzhkjzz() {
		return qtsykjzzhkjzz;
	}
	public void setQtsykjzzhkjzz(String qtsykjzzhkjzz) {
		this.qtsykjzzhkjzz = qtsykjzzhkjzz;
	}
	public String getQyzygdid() {
		return qyzygdid;
	}
	public void setQyzygdid(String qyzygdid) {
		this.qyzygdid = qyzygdid;
	}
	public String getSblx() {
		return sblx;
	}
	public void setSblx(String sblx) {
		this.sblx = sblx;
	}
	public String getSdsjsff() {
		return sdsjsff;
	}
	public void setSdsjsff(String sdsjsff) {
		this.sdsjsff = sdsjsff;
	}
	public String getSfjrqsq() {
		return sfjrqsq;
	}
	public void setSfjrqsq(String sfjrqsq) {
		this.sfjrqsq = sfjrqsq;
	}
	public String getSfwcqs() {
		return sfwcqs;
	}
	public void setSfwcqs(String sfwcqs) {
		this.sfwcqs = sfwcqs;
	}
	public Timestamp getSksssqq() {
		return sksssqq;
	}
	public void setSksssqq(Timestamp sksssqq) {
		this.sksssqq = sksssqq;
	}
	public Timestamp getSksssqz() {
		return sksssqz;
	}
	public void setSksssqz(Timestamp sksssqz) {
		this.sksssqz = sksssqz;
	}
	public String getSndsfxxwlqy() {
		return sndsfxxwlqy;
	}
	public void setSndsfxxwlqy(String sndsfxxwlqy) {
		this.sndsfxxwlqy = sndsfxxwlqy;
	}
	public String getSsgs() {
		return ssgs;
	}
	public void setSsgs(String ssgs) {
		this.ssgs = ssgs;
	}
	public String getSsgslx() {
		return ssgslx;
	}
	public void setSsgslx(String ssgslx) {
		this.ssgslx = ssgslx;
	}
	public String getSshy() {
		return sshy;
	}
	public void setSshy(String sshy) {
		this.sshy = sshy;
	}
	public String getSshymc() {
		return sshymc;
	}
	public void setSshymc(String sshymc) {
		this.sshymc = sshymc;
	}
	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}
	public void setSwjgzzjgdm(String swjgzzjgdm) {
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSwjgzzjgmc() {
		return swjgzzjgmc;
	}
	public void setSwjgzzjgmc(String swjgzzjgmc) {
		this.swjgzzjgmc = swjgzzjgmc;
	}
	
	public Timestamp getSyqdjzrq() {
		return syqdjzrq;
	}
	public void setSyqdjzrq(Timestamp syqdjzrq) {
		this.syqdjzrq = syqdjzrq;
	}
	public Timestamp getSyqdqsrq() {
		return syqdqsrq;
	}
	public void setSyqdqsrq(Timestamp syqdqsrq) {
		this.syqdqsrq = syqdqsrq;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public BigDecimal getZczbje() {
		return zczbje;
	}
	public void setZczbje(BigDecimal zczbje) {
		this.zczbje = zczbje;
	}
	public BigDecimal getZcze() {
		return zcze;
	}
	public void setZcze(BigDecimal zcze) {
		this.zcze = zcze;
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
	public String getSyjdlxmc() {
		return syjdlxmc;
	}
	public void setSyjdlxmc(String syjdlxmc) {
		this.syjdlxmc = syjdlxmc;
	}
	public String getSykjzzhkjzz() {
		return sykjzzhkjzz;
	}
	public void setSykjzzhkjzz(String sykjzzhkjzz) {
		this.sykjzzhkjzz = sykjzzhkjzz;
	}
	public String getSykjzzmc() {
		return sykjzzmc;
	}
	public void setSykjzzmc(String sykjzzmc) {
		this.sykjzzmc = sykjzzmc;
	}
	public String getQtkjzzmc() {
		return qtkjzzmc;
	}
	public void setQtkjzzmc(String qtkjzzmc) {
		this.qtkjzzmc = qtkjzzmc;
	}
	
}
