package com.lscdz.qysds.common.service.djjbsj.bo;

/**
 * Created by CodeGenerator at Wed Dec 17 09:46:39 CST 2014
 * Table:    DJDB.DJ_JL_JBSJ
 * Comments: 税务登记-基本数据
 * Any question, ask the author YangJian! jasperjobs@163.com
 */

import java.sql.*;
import java.math.*;

@SuppressWarnings("serial")
public class Djjbsj implements java.io.Serializable
{
	private Timestamp	cjrq;	//创建时间
	private String	djsllx;	//登记受理类型
	private String	djzclxdm;	//登记注册类型代码
	private String	djzzldm;	//税务登记证种类代码
	private int	gdsgghbs;	//国地税共管户标识
	private String	gjbzhydm;	//国家标准行业代码
	private String	hsxsdm;	//核算形式或预算形式代码
	private String	jsjdm;	//计算机代码
	private String	jydz;	//经营地址
	private String	jydzyb;	//经营地址邮编
	private String	kjzddm;	//会计制度代码
	private Timestamp	kydjrq;	//税务登记日期
	private Timestamp	lrrq;	//录入时间
	private String	lsgxdm;	//隶属关系代码
	private String	nsrmc;	//纳税人名称
	private String	nsrzt;	//纳税人状态
	private Timestamp	qrrq;	//登记确认日期
	private String	qrry;	//登记确认人员
	private String	scjxdm;	//所处街乡代码
	private Timestamp	slrq;	//登记受理日期
	private String	slry;	//登记受理人员
	private String	swdjlx;	//税务登记类型代码
	private String	swdjzh;	//税务登记证号
	private String	swjgzzjgdm;	//税务机关组织机构代码
	private String	zcdz;	//注册地址
	private String	zcdzyb;	//注册地址邮编
	private String	zczbbzdm;	//注册资本或投资总额币种代码
	private BigDecimal	zczbje;	//注册资本或投资总额金额（万元）
	private String	zgbmdm;	//最终主管部门代码
	private Timestamp	cjsj;	//创建时间
	private Timestamp	hzfhrq;	//核准分户日期
	private String	hzfhry;	//核准分户日期
	private String	jydzlxdm;	//经营地址联系电话
	private String	jyfw;	//经营范围
	private String	qyzy;	//企业主页网址
	private String	sbdm;	//申报方式代码
	private String	scbs;	//删除标识
	private String	sfzjhm;	//法人身份证件号码
	private String	sjlylxdm;	//数据来源类型代码
	private BigDecimal	wzztzblhj;	//外资投资比例合计（%）
	private Timestamp	xgsj;	//修改时间
	private Timestamp	yhzrq;	//验换证日期
	private String	yhzry;	//验换证人员
	private String	yyzzh;	//营业执照号或批准成立证照或批准成立文书号码
	private String	zcdzlxdh;	//注册地址联系电话
	private String	zhgxsj;	//最后更新时间
	private BigDecimal	zrrtzblhj;	//自然人投资比例合计（%）
	private String	zzjgdm;	//组织机构代码

	public void setCjrq(Timestamp cjrq)
	{
		this.cjrq = cjrq;
	}
	public Timestamp getCjrq()
	{
		return cjrq;
	}
	public void setDjsllx(String djsllx)
	{
		this.djsllx = djsllx;
	}
	public String getDjsllx()
	{
		return (djsllx == null ? "" : djsllx);
	}
	public void setDjzclxdm(String djzclxdm)
	{
		this.djzclxdm = djzclxdm;
	}
	public String getDjzclxdm()
	{
		return (djzclxdm == null ? "" : djzclxdm);
	}
	public void setDjzzldm(String djzzldm)
	{
		this.djzzldm = djzzldm;
	}
	public String getDjzzldm()
	{
		return (djzzldm == null ? "" : djzzldm);
	}
	public void setGdsgghbs(int gdsgghbs)
	{
		this.gdsgghbs = gdsgghbs;
	}
	public int getGdsgghbs()
	{
		return gdsgghbs;
	}
	public void setGjbzhydm(String gjbzhydm)
	{
		this.gjbzhydm = gjbzhydm;
	}
	public String getGjbzhydm()
	{
		return (gjbzhydm == null ? "" : gjbzhydm);
	}
	public void setHsxsdm(String hsxsdm)
	{
		this.hsxsdm = hsxsdm;
	}
	public String getHsxsdm()
	{
		return (hsxsdm == null ? "" : hsxsdm);
	}
	public void setJsjdm(String jsjdm)
	{
		this.jsjdm = jsjdm;
	}
	public String getJsjdm()
	{
		return (jsjdm == null ? "" : jsjdm);
	}
	public void setJydz(String jydz)
	{
		this.jydz = jydz;
	}
	public String getJydz()
	{
		return (jydz == null ? "" : jydz);
	}
	public void setJydzyb(String jydzyb)
	{
		this.jydzyb = jydzyb;
	}
	public String getJydzyb()
	{
		return (jydzyb == null ? "" : jydzyb);
	}
	public void setKjzddm(String kjzddm)
	{
		this.kjzddm = kjzddm;
	}
	public String getKjzddm()
	{
		return (kjzddm == null ? "" : kjzddm);
	}
	public void setKydjrq(Timestamp kydjrq)
	{
		this.kydjrq = kydjrq;
	}
	public Timestamp getKydjrq()
	{
		return kydjrq;
	}
	public void setLrrq(Timestamp lrrq)
	{
		this.lrrq = lrrq;
	}
	public Timestamp getLrrq()
	{
		return lrrq;
	}
	public void setLsgxdm(String lsgxdm)
	{
		this.lsgxdm = lsgxdm;
	}
	public String getLsgxdm()
	{
		return (lsgxdm == null ? "" : lsgxdm);
	}
	public void setNsrmc(String nsrmc)
	{
		this.nsrmc = nsrmc;
	}
	public String getNsrmc()
	{
		return (nsrmc == null ? "" : nsrmc);
	}
	public void setNsrzt(String nsrzt)
	{
		this.nsrzt = nsrzt;
	}
	public String getNsrzt()
	{
		return (nsrzt == null ? "" : nsrzt);
	}
	public void setQrrq(Timestamp qrrq)
	{
		this.qrrq = qrrq;
	}
	public Timestamp getQrrq()
	{
		return qrrq;
	}
	public void setQrry(String qrry)
	{
		this.qrry = qrry;
	}
	public String getQrry()
	{
		return (qrry == null ? "" : qrry);
	}
	public void setScjxdm(String scjxdm)
	{
		this.scjxdm = scjxdm;
	}
	public String getScjxdm()
	{
		return (scjxdm == null ? "" : scjxdm);
	}
	public void setSlrq(Timestamp slrq)
	{
		this.slrq = slrq;
	}
	public Timestamp getSlrq()
	{
		return slrq;
	}
	public void setSlry(String slry)
	{
		this.slry = slry;
	}
	public String getSlry()
	{
		return (slry == null ? "" : slry);
	}
	public void setSwdjlx(String swdjlx)
	{
		this.swdjlx = swdjlx;
	}
	public String getSwdjlx()
	{
		return (swdjlx == null ? "" : swdjlx);
	}
	public void setSwdjzh(String swdjzh)
	{
		this.swdjzh = swdjzh;
	}
	public String getSwdjzh()
	{
		return (swdjzh == null ? "" : swdjzh);
	}
	public void setSwjgzzjgdm(String swjgzzjgdm)
	{
		this.swjgzzjgdm = swjgzzjgdm;
	}
	public String getSwjgzzjgdm()
	{
		return (swjgzzjgdm == null ? "" : swjgzzjgdm);
	}
	public void setZcdz(String zcdz)
	{
		this.zcdz = zcdz;
	}
	public String getZcdz()
	{
		return (zcdz == null ? "" : zcdz);
	}
	public void setZcdzyb(String zcdzyb)
	{
		this.zcdzyb = zcdzyb;
	}
	public String getZcdzyb()
	{
		return (zcdzyb == null ? "" : zcdzyb);
	}
	public void setZczbbzdm(String zczbbzdm)
	{
		this.zczbbzdm = zczbbzdm;
	}
	public String getZczbbzdm()
	{
		return (zczbbzdm == null ? "" : zczbbzdm);
	}
	public void setZczbje(BigDecimal zczbje)
	{
		this.zczbje = zczbje;
	}
	public BigDecimal getZczbje()
	{
		return zczbje;
	}
	public void setZgbmdm(String zgbmdm)
	{
		this.zgbmdm = zgbmdm;
	}
	public String getZgbmdm()
	{
		return (zgbmdm == null ? "" : zgbmdm);
	}
	public void setCjsj(Timestamp cjsj)
	{
		this.cjsj = cjsj;
	}
	public Timestamp getCjsj()
	{
		return cjsj;
	}
	public void setHzfhrq(Timestamp hzfhrq)
	{
		this.hzfhrq = hzfhrq;
	}
	public Timestamp getHzfhrq()
	{
		return hzfhrq;
	}
	public void setHzfhry(String hzfhry)
	{
		this.hzfhry = hzfhry;
	}
	public String getHzfhry()
	{
		return (hzfhry == null ? "" : hzfhry);
	}
	public void setJydzlxdm(String jydzlxdm)
	{
		this.jydzlxdm = jydzlxdm;
	}
	public String getJydzlxdm()
	{
		return (jydzlxdm == null ? "" : jydzlxdm);
	}
	public void setJyfw(String jyfw)
	{
		this.jyfw = jyfw;
	}
	public String getJyfw()
	{
		return (jyfw == null ? "" : jyfw);
	}
	public void setQyzy(String qyzy)
	{
		this.qyzy = qyzy;
	}
	public String getQyzy()
	{
		return (qyzy == null ? "" : qyzy);
	}
	public void setSbdm(String sbdm)
	{
		this.sbdm = sbdm;
	}
	public String getSbdm()
	{
		return (sbdm == null ? "" : sbdm);
	}
	public void setScbs(String scbs)
	{
		this.scbs = scbs;
	}
	public String getScbs()
	{
		return (scbs == null ? "" : scbs);
	}
	public void setSfzjhm(String sfzjhm)
	{
		this.sfzjhm = sfzjhm;
	}
	public String getSfzjhm()
	{
		return (sfzjhm == null ? "" : sfzjhm);
	}
	public void setSjlylxdm(String sjlylxdm)
	{
		this.sjlylxdm = sjlylxdm;
	}
	public String getSjlylxdm()
	{
		return (sjlylxdm == null ? "" : sjlylxdm);
	}
	public void setWzztzblhj(BigDecimal wzztzblhj)
	{
		this.wzztzblhj = wzztzblhj;
	}
	public BigDecimal getWzztzblhj()
	{
		return wzztzblhj;
	}
	public void setXgsj(Timestamp xgsj)
	{
		this.xgsj = xgsj;
	}
	public Timestamp getXgsj()
	{
		return xgsj;
	}
	public void setYhzrq(Timestamp yhzrq)
	{
		this.yhzrq = yhzrq;
	}
	public Timestamp getYhzrq()
	{
		return yhzrq;
	}
	public void setYhzry(String yhzry)
	{
		this.yhzry = yhzry;
	}
	public String getYhzry()
	{
		return (yhzry == null ? "" : yhzry);
	}
	public void setYyzzh(String yyzzh)
	{
		this.yyzzh = yyzzh;
	}
	public String getYyzzh()
	{
		return (yyzzh == null ? "" : yyzzh);
	}
	public void setZcdzlxdh(String zcdzlxdh)
	{
		this.zcdzlxdh = zcdzlxdh;
	}
	public String getZcdzlxdh()
	{
		return (zcdzlxdh == null ? "" : zcdzlxdh);
	}
	public void setZhgxsj(String zhgxsj)
	{
		this.zhgxsj = zhgxsj;
	}
	public String getZhgxsj()
	{
		return (zhgxsj == null ? "" : zhgxsj);
	}
	public void setZrrtzblhj(BigDecimal zrrtzblhj)
	{
		this.zrrtzblhj = zrrtzblhj;
	}
	public BigDecimal getZrrtzblhj()
	{
		return zrrtzblhj;
	}
	public void setZzjgdm(String zzjgdm)
	{
		this.zzjgdm = zzjgdm;
	}
	public String getZzjgdm()
	{
		return (zzjgdm == null ? "" : zzjgdm);
	}
}
