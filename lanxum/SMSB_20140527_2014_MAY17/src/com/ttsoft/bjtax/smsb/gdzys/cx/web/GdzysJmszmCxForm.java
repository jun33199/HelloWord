package com.ttsoft.bjtax.smsb.gdzys.cx.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.framework.form.BaseForm;
/**
 *查询统计批量扣税情况 form
 * 201307
 * wangxq
 */
public class GdzysJmszmCxForm extends BaseForm{
	
	
	//分局代码（查询扣款详细信息用）
	private String fjdmxx="";
	//税务所代码（查询扣款详细信息用）
	private String swsdmxx="";
	//银行代码（查询扣款详细信息用）
	private String yhdmxx="";
	//扣款时间（查询扣款详细信息用）
	private String kksjxx="";
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
    
    
    
	/**
     * 总页数（扣款详细信息）
     */
    private int maxPageXx;
	
    /**
     * 每页显示的纪录数，默认为10（扣款详细信息）
     */
    private int pageSizeXx =10;
    /**
     * 当前页码（扣款详细信息）
     */
    private int currentPageXx = 1;
    /**
     * 核对不一致笔数（扣款详细信息）
     */
    private int totalRowCountXx = 0;
    
    
    
    
    /**
	   * 页面提示信息
	*/
	private String message;
	
	/**
	   * 页面提示信息
	 */
	  private String yhjb="";
	
	 //银行代码
	 String yhdm="";
	 //税务所
	 String cxdqjs="";
	 //年度
	 String nd;
	 //扣款起始月份
	 String ystart;
	 //扣款终止月份
	 String yend;
	 //分局List
	 private List swdwlist;
	 //税务所List
	 private List cxswdwlist;
	 //地区局所
	 private String dqjs="";;
	 
	 //是否进行了查询操作
	 private boolean queryFlag = false;
	 
	 //总金额
	 String zje="";
	 //成功总金额
	 String cgZje="";
	 //失败总金额
	 String sbZje="";
	 
	 //总笔数
	 String zbs="";
	 //总成功笔数
	 String zcgbs="";
	 //总失败笔数
	 String zsbbs="";
	 
	 //数据List
	 private List dataList=new ArrayList();
	 
	 
	 //成功失败标识
	 String cgsbFlag="";
	 //减免税证明状态
	 private String status="";
	 //减免税证明编号
	 private String jmszmbh="";
	 
	 
	 
	 public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getJmszmbh() {
		return jmszmbh;
	}




	public void setJmszmbh(String jmszmbh) {
		this.jmszmbh = jmszmbh;
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

	 


	public String getCgsbFlag() {
		return cgsbFlag;
	}




	public void setCgsbFlag(String cgsbFlag) {
		this.cgsbFlag = cgsbFlag;
	}




	public String getYhjb() {
		return yhjb;
	}




	public void setYhjb(String yhjb) {
		this.yhjb = yhjb;
	}




	public boolean isQueryFlag() {
		return queryFlag;
	}



	public void setQueryFlag(boolean queryFlag) {
		this.queryFlag = queryFlag;
	}



	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
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

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getCxdqjs() {
		return cxdqjs;
	}

	public void setCxdqjs(String cxdqjs) {
		this.cxdqjs = cxdqjs;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getYstart() {
		return ystart;
	}

	public void setYstart(String ystart) {
		this.ystart = ystart;
	}

	public String getYend() {
		return yend;
	}

	public void setYend(String yend) {
		this.yend = yend;
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

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public List getCxswdwlist() {
		return cxswdwlist;
	}

	public void setCxswdwlist(List cxswdwlist) {
		this.cxswdwlist = cxswdwlist;
	}





	public String getZje() {
		return zje;
	}

	public void setZje(String zje) {
		this.zje = zje;
	}
	public String getZbs() {
		return zbs;
	}
	public void setZbs(String zbs) {
		this.zbs = zbs;
	}




	public String getZcgbs() {
		return zcgbs;
	}




	public void setZcgbs(String zcgbs) {
		this.zcgbs = zcgbs;
	}




	public String getZsbbs() {
		return zsbbs;
	}




	public void setZsbbs(String zsbbs) {
		this.zsbbs = zsbbs;
	}




	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}




	public String getFjdmxx() {
		return fjdmxx;
	}




	public void setFjdmxx(String fjdmxx) {
		this.fjdmxx = fjdmxx;
	}




	public String getSwsdmxx() {
		return swsdmxx;
	}




	public void setSwsdmxx(String swsdmxx) {
		this.swsdmxx = swsdmxx;
	}




	public String getYhdmxx() {
		return yhdmxx;
	}




	public void setYhdmxx(String yhdmxx) {
		this.yhdmxx = yhdmxx;
	}




	public String getKksjxx() {
		return kksjxx;
	}




	public void setKksjxx(String kksjxx) {
		this.kksjxx = kksjxx;
	}




	public int getMaxPageXx() {
		  try{
	            maxPageXx = this.getTotalRowCountXx() / this.getPageSizeXx();
	            int number = totalRowCountXx % pageSizeXx;
	            if(number != 0){
	                maxPageXx += 1;
	            }
	        } catch(Exception ex){
	            maxPageXx = 0;
	        }
	        return maxPageXx;
	}




	public void setMaxPageXx(int maxPageXx) {
		this.maxPageXx = maxPageXx;
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




	public int getTotalRowCountXx() {
		return totalRowCountXx;
	}




	public void setTotalRowCountXx(int totalRowCountXx) {
		this.totalRowCountXx = totalRowCountXx;
	}




	public String getCgZje() {
		return cgZje;
	}




	public void setCgZje(String cgZje) {
		this.cgZje = cgZje;
	}




	public String getSbZje() {
		return sbZje;
	}




	public void setSbZje(String sbZje) {
		this.sbZje = sbZje;
	}
	
	 
}
