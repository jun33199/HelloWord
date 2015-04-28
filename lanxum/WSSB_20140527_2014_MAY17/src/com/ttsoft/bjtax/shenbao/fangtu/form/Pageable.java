package com.ttsoft.bjtax.shenbao.fangtu.form;

public interface Pageable extends java.io.Serializable {


	public String getCurrentPageNum();
	public void setCurrentPageNum(String currentPageNum);

	public String getPageSize();

	public void setPageSize(String pageSize);

	public String getTotalItemsNum() ;

	public void setTotalItemsNum(String totalItemsNum);

	public String getTotalPageNum();

	public void setTotalPageNum(String totalPageNum) ;
	
}
