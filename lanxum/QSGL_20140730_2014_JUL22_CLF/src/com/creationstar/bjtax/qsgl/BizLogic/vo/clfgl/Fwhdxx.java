package com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 房屋核定信息VO
 * @author tutu
 * 2013-05-10(1)
 */
public class Fwhdxx implements Serializable {

	/**
     * 申报表号
     */
    public java.lang.String sbbh;
    
    /**
     * 合同编号
     */
    public java.lang.String htbh;
    
    /**
     * 申请人现住址
     */
    public java.lang.String sqrxzdz;
    
    /**
     * 是否为家庭唯一生活用房
     */
    public java.lang.String jtwyshyhbz;
	
	/**
     * 房屋类型
     */
    public java.lang.String fwlxdm;
	
	/**
     * 房屋建筑面积
     */
    public java.math.BigDecimal fwjzmj;
	
	/**
     * 建成年代
     */
	public java.lang.String jcnd;
	
	/**
     * 原购房发票金额
     */
	public java.math.BigDecimal ygffpje;
	
	/**
     * 购房证明日期
     */
	public java.sql.Timestamp gfzmrq;
	
	/**
     * 土地增值税申报方式
     */
	public java.lang.String tdzzssbfs;
	
	/**
     * 取得房地产时所缴纳的契税金额
     */
	public java.math.BigDecimal qdfcqsje;
	
	/**
     * 取得房地产时所缴纳的印花税金额
     */
	public java.math.BigDecimal qdfcyhsje;
	
	/**
     * 取得土地使用权所支付的金额
     */
	public java.math.BigDecimal qdtdsyqzfje;
	
	/**
     * 旧房及建筑物的评估价格
     */
	public java.math.BigDecimal jfpgjg;
	
	/**
     * 价格评估费用
     */
	public java.math.BigDecimal jgpgfy;
	
	/**
     * 房地产中介计算机代码(即房地产中介机构地税计算机代码)
     */
	public java.lang.String fdczjjsjdm;
	
	/**
     * 房地产中介税务登记证号
     */
	public java.lang.String fdczjswdjzh;
	
	/**
     * 房地产中介联系电话
     */
	public java.lang.String fdczjlxdh;
	
	/**
     * 房地产经纪人姓名
     */
	public java.lang.String fdczjjjr;
	
	/**
     * 房地产经纪人联系电话
     */
	public java.lang.String fdczjjjrlxdh;
	
	/**
     * 房地产经纪人身份证号码
     */
	public java.lang.String fdczjjjrzjhm;
	
	/**
     * 经纪人资格证书号码
     */
	public java.lang.String fdczjjjrzgzsh;
	
	/**
     * 产权证标注建筑面积分类
     */
	public java.lang.String cqzbzjzmjfl;
	
	/**
     * 每平米交易单价
     */
	public java.math.BigDecimal mpmjydj;
	
	/**
     * 普通住房最高限价
     */
	public java.math.BigDecimal ptzfzgxj;
	
	/**
     * 房屋容积率
     */
	public java.lang.String fwrjl;
	
	/**
     * 划分标准
     */
	public java.lang.String hfbz;
	
	/**
     * 住房使用时间分类
     */
	public java.lang.String zfsjsjfl;
	
	/**
     * 营业税征收方式
     */
	public java.lang.String yyszsfs;
	
	/**
     * 个人所得税征收方式
     */
	public java.lang.String grsdszsfs;
	
	/**
     * 土地增值税征收方式
     */
	public java.lang.String tdzsszsfs;
	
	/**
     * 计税收入确认方式
     */
	public java.lang.String jssrqrfs;
	
	/**
     * 计税收入金额
     */
	public java.math.BigDecimal jsje;

	/**
     * 住房评估价格
     */
	public java.math.BigDecimal zfpgjg;
	
	/**
     * 住房装修费用
     */
	public java.math.BigDecimal zfzxfy;
	
	/**
     * 住房贷款利息
     */
	public java.math.BigDecimal zfdklx;
	
	/**
     * 手续费
     */
	public java.math.BigDecimal sxf;
	
	/**
     * 公证费
     */
	public java.math.BigDecimal gzf;
	
	/**
     * 合理费用
     */
	public java.math.BigDecimal hlfy;
	
	/**
     * 土地级次代码
     */
	public java.lang.String tdjcdm;
	
	
	/**
     * 房屋产权证标注住房类型代码
     */
	public java.lang.String fwcqzbzzflxdm;
	
	/**
     * 创建人
     */
    public java.lang.String cjr;
    
    /**
     * 创建日期
     */
    public java.sql.Timestamp cjrq;
    
    /**
     * 录入人
     */
    public java.lang.String lrr;
    
    /**
     * 录入日期
     */
    public java.sql.Timestamp lrrq;
    
    /**
     * 每平米核定价格
     */
	public java.math.BigDecimal mpmhdjg;
	
	/**
     * 土地出让金
     */
	public java.math.BigDecimal tdcrj;
	
	/**
     * 建委查询房屋原值
     */
	public java.math.BigDecimal jwcxfwyz;
	
	/**
     * 城建税税率
     */
	public java.math.BigDecimal cjssl;
	
	/**
     * 发票所载日期
     */
	public java.sql.Timestamp fpszrq;
	
	/**
     * 按年加计数额
     */
	public java.math.BigDecimal anjjse;
	
	/**
	 * 房屋所在区域代码
	 */
	public java.lang.String fwszqydm;
	
	/**
	 * 
	 * 核定总价格
	 */
//added by zhangj	
	public java.math.BigDecimal hdjg;
	/**
     * 房屋核定类型
     */
    public java.lang.String fwhdlxdm;

	/**
     * 权属转移形式明细代码
     */
    public java.lang.String qszyxsmxdm;   
	/**
     * 原契税票房屋计税价格 
     */
    public BigDecimal yqspfwjsjg; 
    public java.lang.String yhszsfs;   
    
	/**
     * 普通住房最高套总价
     */
	public java.math.BigDecimal fwszqyje;

	public java.math.BigDecimal getMpmhdjg() {
		return mpmhdjg;
	}

	public void setMpmhdjg(java.math.BigDecimal mpmhdjg) {
		this.mpmhdjg = mpmhdjg;
	}

	public java.math.BigDecimal getTdcrj() {
		return tdcrj;
	}

	public void setTdcrj(java.math.BigDecimal tdcrj) {
		this.tdcrj = tdcrj;
	}

	public java.math.BigDecimal getJwcxfwyz() {
		return jwcxfwyz;
	}

	public void setJwcxfwyz(java.math.BigDecimal jwcxfwyz) {
		this.jwcxfwyz = jwcxfwyz;
	}

	public java.math.BigDecimal getCjssl() {
		return cjssl;
	}

	public void setCjssl(java.math.BigDecimal cjssl) {
		this.cjssl = cjssl;
	}

	public java.sql.Timestamp getFpszrq() {
		return fpszrq;
	}

	public void setFpszrq(java.sql.Timestamp fpszrq) {
		this.fpszrq = fpszrq;
	}

	public java.math.BigDecimal getAnjjse() {
		return anjjse;
	}

	public void setAnjjse(java.math.BigDecimal anjjse) {
		this.anjjse = anjjse;
	}

	public java.lang.String getSbbh() {
		return sbbh;
	}

	public void setSbbh(java.lang.String sbbh) {
		this.sbbh = sbbh;
	}

	public java.lang.String getHtbh() {
		return htbh;
	}

	public void setHtbh(java.lang.String htbh) {
		this.htbh = htbh;
	}

	public java.lang.String getSqrxzdz() {
		return sqrxzdz;
	}

	public void setSqrxzdz(java.lang.String sqrxzdz) {
		this.sqrxzdz = sqrxzdz;
	}

	public java.lang.String getJtwyshyhbz() {
		return jtwyshyhbz;
	}

	public void setJtwyshyhbz(java.lang.String jtwyshyhbz) {
		this.jtwyshyhbz = jtwyshyhbz;
	}

	public java.lang.String getFwlxdm() {
		return fwlxdm;
	}

	public void setFwlxdm(java.lang.String fwlxdm) {
		this.fwlxdm = fwlxdm;
	}

	public java.math.BigDecimal getFwjzmj() {
		return fwjzmj;
	}

	public void setFwjzmj(java.math.BigDecimal fwjzmj) {
		this.fwjzmj = fwjzmj;
	}

	public java.lang.String getJcnd() {
		return jcnd;
	}

	public void setJcnd(java.lang.String jcnd) {
		this.jcnd = jcnd;
	}

	public java.math.BigDecimal getYgffpje() {
		return ygffpje;
	}

	public void setYgffpje(java.math.BigDecimal ygffpje) {
		this.ygffpje = ygffpje;
	}

	public java.sql.Timestamp getGfzmrq() {
		return gfzmrq;
	}

	public void setGfzmrq(java.sql.Timestamp gfzmrq) {
		this.gfzmrq = gfzmrq;
	}

	public java.lang.String getTdzzssbfs() {
		return tdzzssbfs;
	}

	public void setTdzzssbfs(java.lang.String tdzzssbfs) {
		this.tdzzssbfs = tdzzssbfs;
	}

	public java.math.BigDecimal getQdfcqsje() {
		return qdfcqsje;
	}

	public void setQdfcqsje(java.math.BigDecimal qdfcqsje) {
		this.qdfcqsje = qdfcqsje;
	}

	public java.math.BigDecimal getQdfcyhsje() {
		return qdfcyhsje;
	}

	public void setQdfcyhsje(java.math.BigDecimal qdfcyhsje) {
		this.qdfcyhsje = qdfcyhsje;
	}

	public java.math.BigDecimal getQdtdsyqzfje() {
		return qdtdsyqzfje;
	}

	public void setQdtdsyqzfje(java.math.BigDecimal qdtdsyqzfje) {
		this.qdtdsyqzfje = qdtdsyqzfje;
	}

	public java.math.BigDecimal getJfpgjg() {
		return jfpgjg;
	}

	public void setJfpgjg(java.math.BigDecimal jfpgjg) {
		this.jfpgjg = jfpgjg;
	}

	public java.math.BigDecimal getJgpgfy() {
		return jgpgfy;
	}

	public void setJgpgfy(java.math.BigDecimal jgpgfy) {
		this.jgpgfy = jgpgfy;
	}

	public java.lang.String getFdczjjsjdm() {
		return fdczjjsjdm;
	}

	public void setFdczjjsjdm(java.lang.String fdczjjsjdm) {
		this.fdczjjsjdm = fdczjjsjdm;
	}

	public java.lang.String getFdczjswdjzh() {
		return fdczjswdjzh;
	}

	public void setFdczjswdjzh(java.lang.String fdczjswdjzh) {
		this.fdczjswdjzh = fdczjswdjzh;
	}

	public java.lang.String getFdczjlxdh() {
		return fdczjlxdh;
	}

	public void setFdczjlxdh(java.lang.String fdczjlxdh) {
		this.fdczjlxdh = fdczjlxdh;
	}

	public java.lang.String getFdczjjjr() {
		return fdczjjjr;
	}

	public void setFdczjjjr(java.lang.String fdczjjjr) {
		this.fdczjjjr = fdczjjjr;
	}

	public java.lang.String getFdczjjjrlxdh() {
		return fdczjjjrlxdh;
	}

	public void setFdczjjjrlxdh(java.lang.String fdczjjjrlxdh) {
		this.fdczjjjrlxdh = fdczjjjrlxdh;
	}

	public java.lang.String getFdczjjjrzjhm() {
		return fdczjjjrzjhm;
	}

	public void setFdczjjjrzjhm(java.lang.String fdczjjjrzjhm) {
		this.fdczjjjrzjhm = fdczjjjrzjhm;
	}

	public java.lang.String getFdczjjjrzgzsh() {
		return fdczjjjrzgzsh;
	}

	public void setFdczjjjrzgzsh(java.lang.String fdczjjjrzgzsh) {
		this.fdczjjjrzgzsh = fdczjjjrzgzsh;
	}

	public java.lang.String getCqzbzjzmjfl() {
		return cqzbzjzmjfl;
	}

	public void setCqzbzjzmjfl(java.lang.String cqzbzjzmjfl) {
		this.cqzbzjzmjfl = cqzbzjzmjfl;
	}

	public java.math.BigDecimal getMpmjydj() {
		return mpmjydj;
	}

	public void setMpmjydj(java.math.BigDecimal mpmjydj) {
		this.mpmjydj = mpmjydj;
	}

	public java.math.BigDecimal getPtzfzgxj() {
		return ptzfzgxj;
	}

	public void setPtzfzgxj(java.math.BigDecimal ptzfzgxj) {
		this.ptzfzgxj = ptzfzgxj;
	}

	public java.lang.String getFwrjl() {
		return fwrjl;
	}

	public void setFwrjl(java.lang.String fwrjl) {
		this.fwrjl = fwrjl;
	}

	public java.lang.String getHfbz() {
		return hfbz;
	}

	public void setHfbz(java.lang.String hfbz) {
		this.hfbz = hfbz;
	}

	public java.lang.String getZfsjsjfl() {
		return zfsjsjfl;
	}

	public void setZfsjsjfl(java.lang.String zfsjsjfl) {
		this.zfsjsjfl = zfsjsjfl;
	}

	public java.lang.String getYyszsfs() {
		return yyszsfs;
	}

	public void setYyszsfs(java.lang.String yyszsfs) {
		this.yyszsfs = yyszsfs;
	}

	public java.lang.String getGrsdszsfs() {
		return grsdszsfs;
	}

	public void setGrsdszsfs(java.lang.String grsdszsfs) {
		this.grsdszsfs = grsdszsfs;
	}

	public java.lang.String getTdzsszsfs() {
		return tdzsszsfs;
	}

	public void setTdzsszsfs(java.lang.String tdzsszsfs) {
		this.tdzsszsfs = tdzsszsfs;
	}

	public java.lang.String getJssrqrfs() {
		return jssrqrfs;
	}

	public void setJssrqrfs(java.lang.String jssrqrfs) {
		this.jssrqrfs = jssrqrfs;
	}

	public java.math.BigDecimal getJsje() {
		return jsje;
	}

	public void setJsje(java.math.BigDecimal jsje) {
		this.jsje = jsje;
	}

	public java.math.BigDecimal getZfpgjg() {
		return zfpgjg;
	}

	public void setZfpgjg(java.math.BigDecimal zfpgjg) {
		this.zfpgjg = zfpgjg;
	}

	public java.math.BigDecimal getZfzxfy() {
		return zfzxfy;
	}

	public void setZfzxfy(java.math.BigDecimal zfzxfy) {
		this.zfzxfy = zfzxfy;
	}

	public java.math.BigDecimal getZfdklx() {
		return zfdklx;
	}

	public void setZfdklx(java.math.BigDecimal zfdklx) {
		this.zfdklx = zfdklx;
	}

	public java.math.BigDecimal getSxf() {
		return sxf;
	}

	public void setSxf(java.math.BigDecimal sxf) {
		this.sxf = sxf;
	}

	public java.math.BigDecimal getGzf() {
		return gzf;
	}

	public void setGzf(java.math.BigDecimal gzf) {
		this.gzf = gzf;
	}

	public java.math.BigDecimal getHlfy() {
		return hlfy;
	}

	public void setHlfy(java.math.BigDecimal hlfy) {
		this.hlfy = hlfy;
	}

	public java.lang.String getTdjcdm() {
		return tdjcdm;
	}

	public void setTdjcdm(java.lang.String tdjcdm) {
		this.tdjcdm = tdjcdm;
	}

	public java.lang.String getFwcqzbzzflxdm() {
		return fwcqzbzzflxdm;
	}

	public void setFwcqzbzzflxdm(java.lang.String fwcqzbzzflxdm) {
		this.fwcqzbzzflxdm = fwcqzbzzflxdm;
	}

	public java.lang.String getCjr() {
		return cjr;
	}

	public void setCjr(java.lang.String cjr) {
		this.cjr = cjr;
	}

	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}

	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}

	public java.lang.String getLrr() {
		return lrr;
	}

	public void setLrr(java.lang.String lrr) {
		this.lrr = lrr;
	}

	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}

	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}

	public java.lang.String getFwszqydm() {
		return fwszqydm;
	}

	public void setFwszqydm(java.lang.String fwszqydm) {
		this.fwszqydm = fwszqydm;
	}

	public java.math.BigDecimal getHdjg() {
		return hdjg;
	}

	public void setHdjg(java.math.BigDecimal hdjg) {
		this.hdjg = hdjg;
	}
	public java.lang.String  getFwhdlxdm() {
		return fwhdlxdm;
	}

	public void setFwhdlxdm(java.lang.String  fwhdlxdm) {
		this.fwhdlxdm = fwhdlxdm;
	}
	public java.lang.String  getQszyxsmxdm() {
		return qszyxsmxdm;
	}

	public void setQszyxsmxdm(java.lang.String  qszyxsmxdm) {
		this.qszyxsmxdm = qszyxsmxdm;
	}	
	public java.math.BigDecimal  getYqspfwjsjg() {
		return yqspfwjsjg;
	}

	public void setYqspfwjsjg(java.math.BigDecimal  yqspfwjsjg) {
		this.yqspfwjsjg = yqspfwjsjg;
	}

	public java.lang.String getYhszsfs() {
		return yhszsfs;
	}

	public void setYhszsfs(java.lang.String yhszsfs) {
		this.yhszsfs = yhszsfs;
	}

	public java.math.BigDecimal getFwszqyje() {
		return fwszqyje;
	}

	public void setFwszqyje(java.math.BigDecimal fwszqyje) {
		this.fwszqyje = fwszqyje;
	}
	
}
