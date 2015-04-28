package com.ttsoft.bjtax.shenbao.fangtu.form;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.shenbao.fangtu.total.FwChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwZiyongTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdZiyongTotal;



public class FangtuPrintBVO implements java.io.Serializable {

	//计算机编号
	private String jsjdm;
	//纳税人名称
	private String taxpayerName;
	//纳税人识别号
	private String taxpayerId;

	//自用房屋
	private List ziyongFWList = new ArrayList();
	private List bgziyongFWList = new ArrayList();
	//承租房屋
	private List chengzuFWList = new ArrayList();
	private List bgchengzuFWList = new ArrayList();	
	//出租房屋
	private List chuzuFWList = new ArrayList();
	private List bgchuzuFWList = new ArrayList();
	//自用土地
	private List ziyongTDList = new ArrayList();
	private List bgziyongTDList = new ArrayList();
	//承租土地
	private List chengzuTDList = new ArrayList();
	private List bgchengzuTDList = new ArrayList();
	//出租土地
	private List chuzuTDList = new ArrayList();
	private List bgchuzuTDList = new ArrayList();
	
	private FwZiyongTotal fwZiyongTotal = new FwZiyongTotal();
	private TdZiyongTotal tdZiyongTotal = new TdZiyongTotal();
	private FwChuzuTotal fwChuzuTotal = new FwChuzuTotal();
	private TdChuzuTotal tdChuzuTotal = new TdChuzuTotal();
	
	private List zhengceList = new ArrayList();
	
	public FwChuzuTotal getFwChuzuTotal() {
		return fwChuzuTotal;
	}
	public void setFwChuzuTotal(FwChuzuTotal fwChuzuTotal) {
		this.fwChuzuTotal = fwChuzuTotal;
	}
	public FwZiyongTotal getFwZiyongTotal() {
		return fwZiyongTotal;
	}
	public void setFwZiyongTotal(FwZiyongTotal fwZiyongTotal) {
		this.fwZiyongTotal = fwZiyongTotal;
	}
	public TdChuzuTotal getTdChuzuTotal() {
		return tdChuzuTotal;
	}
	public void setTdChuzuTotal(TdChuzuTotal tdChuzuTotal) {
		this.tdChuzuTotal = tdChuzuTotal;
	}
	public TdZiyongTotal getTdZiyongTotal() {
		return tdZiyongTotal;
	}
	public void setTdZiyongTotal(TdZiyongTotal tdZiyongTotal) {
		this.tdZiyongTotal = tdZiyongTotal;
	}
	public List getChengzuFWList() {
		return chengzuFWList;
	}
	public void setChengzuFWList(List chengzuFWList) {
		this.chengzuFWList = chengzuFWList;
	}
	public List getChengzuTDList() {
		return chengzuTDList;
	}
	public void setChengzuTDList(List chengzuTDList) {
		this.chengzuTDList = chengzuTDList;
	}
	public List getChuzuFWList() {
		return chuzuFWList;
	}
	public void setChuzuFWList(List chuzuFWList) {
		this.chuzuFWList = chuzuFWList;
	}
	public List getChuzuTDList() {
		return chuzuTDList;
	}
	public void setChuzuTDList(List chuzuTDList) {
		this.chuzuTDList = chuzuTDList;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getTaxpayerId() {
		return taxpayerId;
	}
	public void setTaxpayerId(String taxpayerId) {
		this.taxpayerId = taxpayerId;
	}
	public String getTaxpayerName() {
		return taxpayerName;
	}
	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}
	public List getZiyongFWList() {
		return ziyongFWList;
	}
	public void setZiyongFWList(List ziyongFWList) {
		this.ziyongFWList = ziyongFWList;
	}
	public List getZiyongTDList() {
		return ziyongTDList;
	}
	public void setZiyongTDList(List ziyongTDList) {
		this.ziyongTDList = ziyongTDList;
	}
	public List getBgchengzuFWList() {
		return bgchengzuFWList;
	}
	public void setBgchengzuFWList(List bgchengzuFWList) {
		this.bgchengzuFWList = bgchengzuFWList;
	}
	public List getBgchengzuTDList() {
		return bgchengzuTDList;
	}
	public void setBgchengzuTDList(List bgchengzuTDList) {
		this.bgchengzuTDList = bgchengzuTDList;
	}
	public List getBgchuzuFWList() {
		return bgchuzuFWList;
	}
	public void setBgchuzuFWList(List bgchuzuFWList) {
		this.bgchuzuFWList = bgchuzuFWList;
	}
	public List getBgchuzuTDList() {
		return bgchuzuTDList;
	}
	public void setBgchuzuTDList(List bgchuzuTDList) {
		this.bgchuzuTDList = bgchuzuTDList;
	}
	public List getBgziyongFWList() {
		return bgziyongFWList;
	}
	public void setBgziyongFWList(List bgziyongFWList) {
		this.bgziyongFWList = bgziyongFWList;
	}
	public List getBgziyongTDList() {
		return bgziyongTDList;
	}
	public void setBgziyongTDList(List bgziyongTDList) {
		this.bgziyongTDList = bgziyongTDList;
	}
	public List getZhengceList() {
		return zhengceList;
	}
	public void setZhengceList(List zhengceList) {
		this.zhengceList = zhengceList;
	}



}
