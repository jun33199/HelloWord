package com.lscdz.util;
/**
 * 
 * 项目名称：BjtaxPresentationServerText   
 * 类名称：PageInfo   
 * 类描述：   分页类
 * 创建人：wangcy 
 * 创建时间：2014-9-10 上午9:36:51   
 * 修改人：wangcy   
 * 修改时间：2014-9-10 上午9:36:51   
 * 修改备注：   
 * @version  1.0
 */
public class PageInfo {
	private int curPage;//当前页数
	private int pageSize;//每页记录数
	private int totalCount;//总记录数
	private int totalPage;//总页数
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
