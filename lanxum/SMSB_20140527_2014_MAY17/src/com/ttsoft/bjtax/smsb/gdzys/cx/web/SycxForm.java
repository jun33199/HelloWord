package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;

public class SycxForm extends BaseForm {

	//计算机代码
	private String jsjdm;
	
	//税源类型
	private String sylx;
	
	//纳税人类型
	private String nsrlx;
	
	//税源类型代码
	private String sylxdm;
	
	/*--查询时间--*/
	private String starttime;
	private String endtime;
	
	/*--出局机关--*/	
	private String fjmc=null;
	private String fjdm=null;			//分局代码
	private String swsmc=null;
	private String swsdm=null;			//税务所代码
	
	/*--分页--*/
    private int maxPage=-1;        	//总页码  --(-1表示还未获得总页数)
    private int pageSizeXx =10; 	//每页显示条数，默认为10
    private int currentPageXx = 1;  //当前页码
    
    /*--查询条件2级联动--*/
    private List fjlist ;			//分局列表
    private List swslist = new ArrayList();			//税务所列表
    
    //数据列表
    private List infList;
    
    //详细查询--申报表序列号
    private String sbbxlh;
    
    //信息条数
    private int totalnum = -1;
    
/*-----------------------------------------------------------------------------------------------------------*/
    
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getSylx() {
		return sylx;
	}
	
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getSwsmc() {
		return swsmc;
	}
	public void setSwsmc(String swsmc) {
		this.swsmc = swsmc;
	}
	public void setSylx(String sylx) {
		this.sylx = sylx;
	}
	public String getNsrlx() {
		return nsrlx;
	}
	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}
	public String getSylxdm() {
		return sylxdm;
	}
	public void setSylxdm(String sylxdm) {
		this.sylxdm = sylxdm;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getFjdm() {
		return fjdm;
	}
	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}
	public String getSwsdm() {
		return swsdm;
	}
	public void setSwsdm(String swsdm) {
		this.swsdm = swsdm;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getPageSizeXx() {
		return pageSizeXx;
	}
	public void setPageSizeXx(int pageSizeXx) {
		this.pageSizeXx = pageSizeXx;
	}
	public int getCurrentPageXx() {
		return currentPageXx;
	}
	public void setCurrentPageXx(int currentPageXx) {
		this.currentPageXx = currentPageXx;
	}
	public List getFjlist() {
		return fjlist;
	}
	public void setFjlist(List fjlist) {
		this.fjlist = fjlist;
	}
	public List getSwslist() {
		return swslist;
	}
	public void setSwslist(List swslist) {
		this.swslist = swslist;
	}
	public List getInfList() {
		return infList;
	}
	public void setInfList(List infList) {
		this.infList = infList;
	}
	public String getSbbxlh() {
		return sbbxlh;
	}
	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
	
    
}
