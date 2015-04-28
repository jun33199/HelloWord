package com.ttsoft.bjtax.shenbao.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ekernel.db.or.ORObject;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 再就业减免申报主表</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  qinwei
 * @version 1.0 2006-4-28
 */
public class Zjyjmsbz
    implements ORObject {

      //计算机代码
	  String jsjdm;
	  
      //税务机关组织机构代码	
	  String swjgzzjgdm;
	  
      //方式代码
	  String fsdm;
	  
	  //报表类型代码
	  String bblxdm;
	  
	  //报表期
	  String bbq;
	  
	  //报表年度
	  String bbnd;
	  
	  //税款所属开始日期
	  Timestamp skssksrq;
	  

	  //税款所属结束日期
	  Timestamp skssjsrq;
	  
	  //申报日期
	  Timestamp sbrq;
	  
	  //记帐标识
	  String jzbz;
	  
	  //录入人
	  String lrr;
	  
	  //录入日期
	  Timestamp lrrq;
	  
	  //创建人
	  String cjr;
	  
	  //创建日期
	  Timestamp cjrq;
	  
	  //区县代码
	  String qxdm;
	  
	  //年度
	  String nd;
	  
	  public String getSwjgzzjgdm() {
		    return swjgzzjgdm;
		  }
	  public String getJsjdm() {
		    return jsjdm;
		  }
	  public String getFsdm() {
		    return fsdm;
		  }
	  public String getBblxdm() {
		    return bblxdm;
		  }
	  public String getBbq() {
		    return bbq;
		  }
	  public String getBbnd() {
		    return bbnd;
		  }
	  public Timestamp getSkssksrq() {
		    return skssksrq;
		  }
	  public Timestamp getSkssjsrq() {
		    return skssjsrq;
		  }
	  public Timestamp getSbrq() {
		    return sbrq;
		  }
	  public String getJzbz() {
		    return jzbz;
		  }
	  public String getLrr() {
			return lrr;
		}
	  public Timestamp getLrrq() {
		    return lrrq;
		  }
	  public String getCjr() {
		    return cjr;
		  }
	  public Timestamp getCjrq() {
		    return cjrq;
		  }
	  public String getQxdm() {
		    return qxdm;
		  }
	  public String getNd() {
		    return nd;
		  }
	  
	  public void setSwjgzzjgdm(String swjgzzjgdm) {
		    this.swjgzzjgdm = swjgzzjgdm;
		  }
	  public void setJsjdm(String jsjdm) {
		    this.jsjdm = jsjdm;
		  }
	  public void setFsdm(String fsdm) {
		    this.fsdm = fsdm;
		  }
	  public void setBblxdm(String bblxdm) {
		    this.bblxdm = bblxdm;
		  }
	  public void setBbq(String bbq) {
		    this.bbq = bbq;
		  }
	  public void setBbnd(String bbnd) {
		    this.bbnd = bbnd;
		  }
	  public void setSkssksrq(Timestamp skssksrq) {
		    this.skssksrq = skssksrq;
		  }
	  public void setSkssjsrq(Timestamp skssjsrq) {
		    this.skssjsrq = skssjsrq;
		  }
	  public void setSbrq(Timestamp sbrq) {
		    this.sbrq = sbrq;
		  }
	  public void setJzbz(String jzbz) {
		    this.jzbz = jzbz;
		  }
	  public void setLrr(String lrr) {
		    this.lrr = lrr;
		  }
	  public void setCjr(String cjr) {
		    this.cjr = cjr;
		  }
	  
	  public void setCjrq(Timestamp cjrq) {
		    this.cjrq =cjrq;
		  }
	  
	  public void setQxdm(String qxdm) {
		    this.qxdm = qxdm;
		  }
	  public void setNd(String nd) {
		    this.nd = nd;
		  }
	
	public void setLrrq(Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	
	  
	  
}


	  
	  



