package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>
 * Title: 北京地税核心征管系统--耕地占用税查询管理
 * </p>
 * <p>
 * Description: 缴款书查询Form
 * </p>
 * 
 * @author wangxq
 * @version 1.0
 */
public class GdzysJksCxForm extends BaseForm{
	//开始日期
	private String ksrq="";
	//结束日期
	private String jsrq="";
	//计算机代码
	private String jsjdm="";
	//批准占地文号
	private String pzzdwh="";
	//缴款书状态
	private String jkszt="";
	 //税务所
	private String cxdqjs="";
	 //地区局所
	 private String dqjs="";
	 //分局List
	 private List swdwlist;
	 //税务所List
	 private List cxswdwlist;

	 //是否进行了查询操作
	 private boolean queryFlag = false;
	 //数据List
	 private List dataList;
	 //用户级别
	 private String yhjb="";
 
	/**
     * 总页数
     */
    private int maxPage;
	
    /**
     * 每页显示的纪录数，默认为10
     */
    private int pageSize =10;
    /**
     * 当前页码
     */
    private int currentPage = 1;
    /**
     * 核对不一致笔数
     */
    private int totalRowCount = 0;
 
	 
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	public String getJsjdm() {
		return jsjdm;
	}

	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}

	public String getJkszt() {
		return jkszt;
	}

	public void setJkszt(String jkszt) {
		this.jkszt = jkszt;
	}

	public String getCxdqjs() {
		return cxdqjs;
	}

	public void setCxdqjs(String cxdqjs) {
		this.cxdqjs = cxdqjs;
	}

	public String getDqjs() {
		return dqjs;
	}

	public void setDqjs(String dqjs) {
		this.dqjs = dqjs;
	}

	public List getSwdwlist() {
		return swdwlist;
	}

	public void setSwdwlist(List swdwlist) {
		this.swdwlist = swdwlist;
	}

	public List getCxswdwlist() {
		return cxswdwlist;
	}

	public void setCxswdwlist(List cxswdwlist) {
		this.cxswdwlist = cxswdwlist;
	}

	public boolean isQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(boolean queryFlag) {
		this.queryFlag = queryFlag;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public String getYhjb() {
		return yhjb;
	}

	public void setYhjb(String yhjb) {
		this.yhjb = yhjb;
	}
	
	
	 public int getMaxPage (){
	        try{
	            maxPage = this.getTotalRowCount() / this.getPageSize();
	            int number = totalRowCount % pageSize;
	            if(number != 0){
	                maxPage += 1;
	            }
	        } catch(Exception ex){
	            maxPage = 0;
	        }
	        return maxPage;

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getPzzdwh() {
		return pzzdwh;
	}

	public void setPzzdwh(String pzzdwh) {
		this.pzzdwh = pzzdwh;
	}
	
}
