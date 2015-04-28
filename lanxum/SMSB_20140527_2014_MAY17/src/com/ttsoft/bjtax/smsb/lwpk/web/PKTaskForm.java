package com.ttsoft.bjtax.smsb.lwpk.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.ttsoft.framework.form.BaseForm;



/**
 * <p>Title: 北京地税核心征管系统--税库行</p>
 * <p>Description: 定时计划表Form</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 技术研发部门－－康洪涛
 * @version 1.0
 */
public class PKTaskForm extends BaseForm {

	
	//序号
	private String xh;
	/*查询用条件*/
	//执行年度
	private String nd;
	//执行月度
	private String yd;
	//查询任务类型
	private String cxrwlx;
	
	//是否初始化
	private String isinit;
	//执行日期
	private String zxrq;
	//执行时间
	private String zxsj;
	//任务类型
	private String zxrwlx;
	//定时任务列表
	private List pkTaskList = new ArrayList();
	
	/*显示下拉菜单选项*/
	//时间列表
	List zxsjList = new ArrayList();
	
	//月度列表
	List cxydList = new ArrayList();
	//类型列表
	List cxlxList = new ArrayList();
	//类型名称列表
	List cxlxmcList = new ArrayList();

	

	public String getCxrwlx() {
		return cxrwlx;
	}

	public void setCxrwlx(String cxrwlx) {
		this.cxrwlx = cxrwlx;
	}

	public String getZxrwlx() {
		return zxrwlx;
	}

	public void setZxrwlx(String zxrwlx) {
		this.zxrwlx = zxrwlx;
	}

	public List getCxlxList() {
		return cxlxList;
	}

	public void setCxlxList(List cxlxList) {
		this.cxlxList = cxlxList;
	}

	public List getCxlxmcList() {
		return cxlxmcList;
	}

	public void setCxlxmcList(List cxlxmcList) {
		this.cxlxmcList = cxlxmcList;
	}

	public List getCxydList() {
		return cxydList;
	}

	public void setCxydList(List cxydList) {
		this.cxydList = cxydList;
	}

	public List getZxsjList() {
		return zxsjList;
	}

	public void setZxsjList(List zxsjList) {
		this.zxsjList = zxsjList;
	}

	public String getIsinit() {
		return isinit;
	}

	public void setIsinit(String isinit) {
		this.isinit = isinit;
	}

	public String getNd() {
		return nd;
	}
	
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
	public List getPkTaskList() {
		return pkTaskList;
	}
	public void setPkTaskList(List pkTaskList) {
		this.pkTaskList = pkTaskList;
	}
	public String getZxrq() {
		return zxrq;
	}
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}
	public String getZxsj() {
		return zxsj;
	}
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}
	
}
