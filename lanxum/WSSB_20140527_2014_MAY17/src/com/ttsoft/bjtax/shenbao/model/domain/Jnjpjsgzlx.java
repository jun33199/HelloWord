package com.ttsoft.bjtax.shenbao.model.domain;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import com.ekernel.db.or.ORObject;

/**
 * 
 * 项目名称：wssb   
 * 类名称：Jnjpjsgzlx  节能减排技术改造项目 
 * 类描述：   减免税备案 
 * 创建人：wangcy 
 * 创建时间：2014-2-14 上午9:59:03   
 * 修改人：wangcy   
 * 修改时间：2014-2-14 上午9:59:03   
 * 修改备注：   
 * @version  1.0
 */

public class Jnjpjsgzlx implements ORObject
{
	//节能减排技术改造项目代码
    private String jnjpjsgzxmdm;
    //节能减排技术改造项目名称
    private String jnjpjsgzxmmc;
    //创建日期
    private java.sql.Timestamp cjrq;
    //录入日期
    private java.sql.Timestamp lrrq;
    //创建人
    private String cjr;
    //录入人
    private String lrr;
    //作废标识
    private String zfbs;
    public Jnjpjsgzlx()
    {
    }
	public String getJnjpjsgzxmdm() {
		return jnjpjsgzxmdm;
	}
	public void setJnjpjsgzxmdm(String jnjpjsgzxmdm) {
		this.jnjpjsgzxmdm = jnjpjsgzxmdm;
	}
	public String getJnjpjsgzxmmc() {
		return jnjpjsgzxmmc;
	}
	public void setJnjpjsgzxmmc(String jnjpjsgzxmmc) {
		this.jnjpjsgzxmmc = jnjpjsgzxmmc;
	}
	public java.sql.Timestamp getCjrq() {
		return cjrq;
	}
	public void setCjrq(java.sql.Timestamp cjrq) {
		this.cjrq = cjrq;
	}
	public java.sql.Timestamp getLrrq() {
		return lrrq;
	}
	public void setLrrq(java.sql.Timestamp lrrq) {
		this.lrrq = lrrq;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getZfbs() {
		return zfbs;
	}
	public void setZfbs(String zfbs) {
		this.zfbs = zfbs;
	}
    
}