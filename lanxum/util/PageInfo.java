package com.lscdz.util;
/**
 * 
 * ��Ŀ���ƣ�BjtaxPresentationServerText   
 * �����ƣ�PageInfo   
 * ��������   ��ҳ��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-9-10 ����9:36:51   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-9-10 ����9:36:51   
 * �޸ı�ע��   
 * @version  1.0
 */
public class PageInfo {
	private int curPage;//��ǰҳ��
	private int pageSize;//ÿҳ��¼��
	private int totalCount;//�ܼ�¼��
	private int totalPage;//��ҳ��
	
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
