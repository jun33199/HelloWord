package com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:核定申报信息</p>
 * @author yugw
 * @version 1.1
 */
public class QysdsBbjcForm extends QysdsNewForm{
	private static final long serialVersionUID = 1L;
	public QysdsBbjcForm() {
		try {
			this.jbInit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void jbInit() throws Exception {
		}
	
	/**
	 * 纳税人计算机代码 String
	 */
	private String jsjdm;
	
	/**
	 * 纳税人名称 String
	 */
	private String nsrmc;
	
	
	/**
	 * 申报年度 String
	 */
	private String sbnd;
	/**
	 * 申报季度
	 */
	private String jd;
	
	/**
	 * 所属经济类型-登记注册类型代码 String
	 */
	private String ssjjlxdm;
	
	/**
	 * 所属经济类型-登记注册类型名称 String
	 */
	private String ssjjlxmc;
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
	
//	private String skssksrq;
	
	/**
	 * 税款所属结束日期 String
	 */
	
//	private String skssjsrq;
	
	/**
	 * 版本号 String
	 */
	
	private String version;
	
	/**
	 * 申请审批表号 String
	 */
	
	private String sqspbh;
	
	private String iszhsb;
	/**
	 * 是否执行了查询动作， 防止查询一次后在进行修改没有进行查询也可以保存,所以做一标志
	 * 1 代表查询  
	 */
	private String isQuery;
	//是否填写过汇算清缴数据 1填写过 
	private String queryFlag="";
	private String tbrq;
	/**
	 * 企业所得税申报方式代码
	 */
	private String  qysdssbfsdm;
	/**
	 * 企业所得税申报方式名称
	 */
	private String qysdssbfsmc;
	/**
	 * 企业所得税税款年度
	 */
	private String sknd;
	/**
	 * 税务登记证号
	 */
	private String swdjzh;
	/**
	 * 纳税人识别号
	 */
	private String nsrsbh;
	/**
	 * 所属行业代码
	 */
	private String sshydm;
	/**
	 * 报表期类型
	 */
	private String bbqlx;
	/**
	 * 增收方式
	 */
	private String zsfs;
	/**
	 * 季度代码
	 */
	
	private String jddm;
	public String getNsrsbhSubmit() {
		return nsrsbhSubmit;
	}
	public void setNsrsbhSubmit(String nsrsbhSubmit) {
		this.nsrsbhSubmit = nsrsbhSubmit;
	}
	public String getSshymcSubmit() {
		return sshymcSubmit;
	}
	public void setSshymcSubmit(String sshymcSubmit) {
		this.sshymcSubmit = sshymcSubmit;
	}
	public String getSsjjlxmcSubmit() {
		return ssjjlxmcSubmit;
	}
	public void setSsjjlxmcSubmit(String ssjjlxmcSubmit) {
		this.ssjjlxmcSubmit = ssjjlxmcSubmit;
	}
	public String getNsrmcSubmit() {
		return nsrmcSubmit;
	}
	public void setNsrmcSubmit(String nsrmcSubmit) {
		this.nsrmcSubmit = nsrmcSubmit;
	}
	/**
	 * 纳税人识别号
	 */
	private String nsrsbhSubmit;
	/**
	 * 所属行业名称
	 */
	private String sshymcSubmit;
	/**
	 * 所属经济类型名称
	 */
	private String ssjjlxmcSubmit;
	/**
	 * 纳税人名称
	 */
	private String nsrmcSubmit;
	/**
	 * path
	 * @return
	 */
	private String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getJddm() {
		return jddm;
	}
	public void setJddm(String jddm) {
		this.jddm = jddm;
	}
	public String getBbqlx() {
		return bbqlx;
	}
	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}
	public String getZsfs() {
		return zsfs;
	}
	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
	}
	public String getSshydm() {
		return sshydm;
	}
	public void setSshydm(String sshydm) {
		this.sshydm = sshydm;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getSwdjzh() {
		return swdjzh;
	}
	public void setSwdjzh(String swdjzh) {
		this.swdjzh = swdjzh;
	}
	public String getSknd() {
		return sknd;
	}
	public void setSknd(String sknd) {
		this.sknd = sknd;
	}
	
	private List qysdssbfsList=new ArrayList(); 
	/**
	 * 页面要素清除
	 * @param actionMapping struts.action.ActionMapping
	 * @param httpServletRequest HttpServletRequest
	 */
	private String sshymc;
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.jsjdm = "";
		this.nsrmc = "";	
		this.sbnd ="";
		this.ssjjlxdm = "";
		this.ssjjlxmc = "";
		this.cjr = "";
		this.cjrq = "";
		this.lrr = "";
		this.lrrq = "";
		this.xtjb = "";
		this.bbmsf = "";
	//	this.skssksrq="";
	//	this.skssjsrq="";
		this.iszhsb="";
		this.sbrq="";
		this.version="";
		this.sqspbh="";
		this.isQuery="";
		this.swdjzh="";
		this.nsrsbh="";
		this.sshydm="";
		this.bbmsf="";
		this.bbqlx="";
		this.cjr="";
		this.cjrq="";
		this.isQuery="";
		this.iszhsb="";
		this.jd="";
		this.jddm="";
		this.jsjdm="";
		this.nsrmc="";
		this.nsrsbh="";
		this.path="";
		this.qysdssbfsdm="";
		this.qysdssbfsmc="";
		this.qysdssbfsList=null;
		this.sbnd="";
		this.sbrq="";
		this.zsfs="";
		this.sshydm="";
		this.sshymc="";
		this.ssjjlxdm="";
		this.ssjjlxmc="";
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		this.tbrq=sdf.format(curTime);
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getSbnd() {
		return sbnd;
	}
	public void setSbnd(String sbnd) {
		this.sbnd = sbnd;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
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
	public String getSbrq() {
		return sbrq;
	}
	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}
	public String getXtjb() {
		return xtjb;
	}
	public void setXtjb(String xtjb) {
		this.xtjb = xtjb;
	}
	public String getBbmsf() {
		return bbmsf;
	}
	public void setBbmsf(String bbmsf) {
		this.bbmsf = bbmsf;
	}
//    public String getSkssksrq() {
//	return skssksrq;
//    }
//    public void setSkssksrq(String skssksrq) {
//	this.skssksrq = skssksrq;
//    }
//    public String getSkssjsrq() {
//	return skssjsrq;
//	}
//	public void setSkssjsrq(String skssjsrq) {
//	this.skssjsrq = skssjsrq;
//	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSqspbh() {
		return sqspbh;
	}
	public void setSqspbh(String sqspbh) {
		this.sqspbh = sqspbh;
	}
	public String getIszhsb() {
		return iszhsb;
	}
	public void setIszhsb(String iszhsb) {
		this.iszhsb = iszhsb;
	}
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getQueryFlag() {
		return queryFlag;
	}
	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}
	public String getTbrq() {
		return tbrq;
	}
	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}
	public String getQysdssbfsdm() {
		return qysdssbfsdm;
	}
	public void setQysdssbfsdm(String qysdssbfsdm) {
		this.qysdssbfsdm = qysdssbfsdm;
	}
	public String getQysdssbfsmc() {
		return qysdssbfsmc;
	}
	public void setQysdssbfsmc(String qysdssbfsmc) {
		this.qysdssbfsmc = qysdssbfsmc;
	}
	public List getQysdssbfsList() {
		return qysdssbfsList;
	}
	public void setQysdssbfsList(List qysdssbfsList) {
		this.qysdssbfsList = qysdssbfsList;
	}
	public String getSshymc() {
		return sshymc;
	}
	public void setSshymc(String sshymc) {
		this.sshymc = sshymc;
	}
	

	
}
