package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.jbxxb.web;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税年报基本信息表</p>
 * @author lianglw
 * @version 1.1
 */

public class JbxxbForm2008
extends BaseForm {
	public JbxxbForm2008() {
		try {
			this.jbInit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 纳税人计算机代码 String
	 */
	private String jsjdm;
	
	/**
	 * 纳税人识别号－税务登记证号 String
	 */
	private String nsrsbh;
	
	/**
	 * 纳税人名称 String
	 */
	private String nsrmc;
	
	
	/**
	 * 申报年度 String
	 */
	private String sbnd;
	
	/**
	 * 税款年度 String
	 */
	private String sknd;
	
	/**
	 * 所属经济类型-登记注册类型代码 String
	 */
	private String ssjjlxdm;
	
	/**
	 * 所属经济类型-登记注册类型名称 String
	 */
	private String ssjjlxmc;
	
	/**
	 * 联系电话 String
	 */
	private String lxdh;
		
	/**
	 * 经营地址 String
	 */
	private String jydz;
	
	   /**
     * 所属行业代码
     */
    private String sshydm;
    
    /**
     * 所属行业名称
     */
    private String sshymc;
	
	
	/**
	 * 征收方式代码 String
	 */
	private String zsfsdm;

	/**
	 * 征收方式名称
	 */
	private String zsfsmc;
	
	/**
	 * 财会制度代码 String
	 */
	private String cwkjzddm;

	/**
	 * 财会制度名称 String
	 */
	private String cwkjzdmc;
	
	/**
	 * 财会制度代码修改前的 String
	 */
	private String cwkjzddm_old;

	
	
	/**
	 * 工资管理形式代码 String
	 */
	private String gzglxsdm;
	
	/**
	 * 工资管理形式名称 String
	 */
	private String gzglxsmc;

	
	/**
	 * 工资管理形式代码修改前的 String
	 */
	private String gzglxsdm_old;

	
	/**
	 * 减免类型代码 String
	 */
	private String jmlxdm;
	
	/**
	 * 减免类型名称 String
	 */
	private String jmlxmc;

	
	/**
	 * 减免类型代码修改前的 String
	 */
	private String jmlxdm_old;
	
	/**
	 * 税务机关组织机构代码 String
	 *
	 * 从登记数据中取得
	 */
	
	private String swjgzzjgdm;
	
	/**
	 * 税务机关组织机构名称 String
	 */
	private String swjgzzjgmc;
	
	
	/**
	 * 创建人代码 String
	 *
	 * 从登录数据中取得
	 */
	private String cjr;
	
	/**
	 * 创建时间 String
	 */
	
	private String cjrq;
	
    
	/**
	 * 录入人代码 String
	 *
	 * 从登录数据中取得
	 */
	private String lrr;
	
	/**
	 * 录入日期 String
	 */
	
	private String lrrq;
	
	/**
	 * 申报日期 String
	 */
	
	private String sbrq;
	
	/**
	 * 系统级别 String
	 */
	
	private String xtjb;
	
	/**
	 * 报表描述符 String
	 */
	
	private String bbmsf;
	
	/**
	 * 税款所属开始日期 String
	 */
	
	private String skssksrq;
	
	/**
	 * 税款所属结束日期 String
	 */
	
	private String skssjsrq;
	
	/**
	 * 版本号 String
	 */
	
	private String version;
	
	/**
	 * 申请审批表号 String
	 */
	
	private String sqspbh;
	
	private String iszhsb;

	
	private void jbInit() throws Exception {
	}
	
	
	/**
	 * 页面要素清除
	 * @param actionMapping struts.action.ActionMapping
	 * @param httpServletRequest HttpServletRequest
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		

		this.jsjdm = "";
		this.nsrsbh = "";
		
		this.nsrmc = "";	
		this.sknd = "";
		this.sbnd = "";
		this.ssjjlxdm = "";
		this.ssjjlxmc = "";

		this.lxdh = "";
		this.jydz = "";
		this.sshydm = "";
		this.sshymc = "";

		
		this.zsfsdm = "";	
		this.zsfsmc = "";
		this.cwkjzddm = "";
		this.cwkjzdmc = "";
		this.cwkjzddm_old = "";

		this.gzglxsdm = "";
		this.gzglxsmc = "";
		this.gzglxsdm_old = "";

		this.jmlxdm = "";
		this.jmlxmc = "";
		this.jmlxdm_old = "";

		this.swjgzzjgdm = "";	
		this.swjgzzjgmc = "";
		this.cjr = "";
		this.cjrq = "";
		this.lrr = "";
		this.lrrq = "";
		this.xtjb = "";
		this.bbmsf = "";
		
		this.skssksrq="";
		
		this.skssjsrq="";
		
		this.iszhsb="";
		this.sbrq="";
		
		this.version="";
		
		this.sqspbh="";

	}


	public String getBbmsf() {
		return bbmsf;
	}


	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}


	public String getCjr() {
		return cjr;
	}


	public void setCjr(String cjr) {
		this.cjr = cjr;
	}


	public String getCjrq() {
		return cjrq;
	}


	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}


	public String getCwkjzddm() {
		return cwkjzddm;
	}


	public void setCwkjzddm(String cwkjzddm) {
		this.cwkjzddm = cwkjzddm;
	}


	public String getCwkjzddm_old() {
		return cwkjzddm_old;
	}


	public void setCwkjzddm_old(String cwkjzddm_old) {
		this.cwkjzddm_old = cwkjzddm_old;
	}


	public String getCwkjzdmc() {
		return cwkjzdmc;
	}


	public void setCwkjzdmc(String cwkjzdmc) {
		this.cwkjzdmc = cwkjzdmc;
	}


	public String getGzglxsdm() {
		return gzglxsdm;
	}


	public void setGzglxsdm(String gzglxsdm) {
		this.gzglxsdm = gzglxsdm;
	}


	public String getGzglxsdm_old() {
		return gzglxsdm_old;
	}


	public void setGzglxsdm_old(String gzglxsdm_old) {
		this.gzglxsdm_old = gzglxsdm_old;
	}


	public String getGzglxsmc() {
		return gzglxsmc;
	}


	public void setGzglxsmc(String gzglxsmc) {
		this.gzglxsmc = gzglxsmc;
	}


	public String getIszhsb() {
		return iszhsb;
	}


	public void setIszhsb(String iszhsb) {
		this.iszhsb = iszhsb;
	}


	public String getJmlxdm() {
		return jmlxdm;
	}


	public void setJmlxdm(String jmlxdm) {
		this.jmlxdm = jmlxdm;
	}


	public String getJmlxdm_old() {
		return jmlxdm_old;
	}


	public void setJmlxdm_old(String jmlxdm_old) {
		this.jmlxdm_old = jmlxdm_old;
	}


	public String getJmlxmc() {
		return jmlxmc;
	}


	public void setJmlxmc(String jmlxmc) {
		this.jmlxmc = jmlxmc;
	}


	public String getJsjdm() {
		return jsjdm;
	}


	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}


	public String getJydz() {
		return jydz;
	}


	public void setJydz(String jydz) {
		this.jydz = jydz;
	}


	public String getLrr() {
		return lrr;
	}


	public void setLrr(String lrr) {
		this.lrr = lrr;
	}


	public String getLrrq() {
		return lrrq;
	}


	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}


	public String getLxdh() {
		return lxdh;
	}


	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
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





	public String getSbrq() {
		return sbrq;
	}


	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
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


	public String getSqspbh() {
		return sqspbh;
	}


	public void setSqspbh(String sqspbh) {
		this.sqspbh = sqspbh;
	}


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


	public String getSsjjlxdm() {
		return ssjjlxdm;
	}


	public void setSsjjlxdm(String ssjjlxdm) {
		this.ssjjlxdm = ssjjlxdm;
	}


	public String getSsjjlxmc() {
		return ssjjlxmc;
	}


	public void setSsjjlxmc(String ssjjlxmc) {
		this.ssjjlxmc = ssjjlxmc;
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


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getXtjb() {
		return xtjb;
	}


	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
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


	public String getSbnd() {
		return sbnd;
	}


	public void setSbnd(String sbnd) {
		this.sbnd = sbnd;
	}


	public String getSknd() {
		return sknd;
	}


	public void setSknd(String sknd) {
		this.sknd = sknd;
	}


	


	


	
	
	
	
	
}

