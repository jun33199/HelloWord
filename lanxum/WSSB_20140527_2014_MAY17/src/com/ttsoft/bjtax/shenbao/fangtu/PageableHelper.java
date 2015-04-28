package com.ttsoft.bjtax.shenbao.fangtu;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ttsoft.bjtax.shenbao.fangtu.form.Pageable;

public class PageableHelper {

	private static Logger logger=Logger.getLogger(PageableHelper.class);
	public PageableHelper() {
		super();
	}

	/**
	 * @param list
	 * @param pageableForm 
	 * @return
	 */
	public static List pageIt(List list, Pageable pageableForm) {
		//´¦Àí·ÖÒ³
		List resList = list;
		
		
		int currentPage;
		int pageSize;
		int totalRows;
		int totalPageNum;
		
		try {
			currentPage = Integer.parseInt( pageableForm.getCurrentPageNum() );
		} catch (Exception e) {
			currentPage = 0;
		}
		try {
			pageSize = Integer.parseInt( pageableForm.getPageSize() );
		} catch (Exception e) {
			pageSize = 0;
		}
		
		
		if (pageSize != -1) {
	
			if (pageSize == 0)
				pageSize = ConstantFangtu.PAGE_SIZE_FANGTU;
	
			totalRows = list.size();
	
			totalPageNum = totalRows / pageSize;
			if ((totalRows % pageSize) > 0) {
				totalPageNum++;
			}
			if (currentPage > totalPageNum) {
				currentPage = totalPageNum;
			}
			
			if ( currentPage < 1 ) currentPage = 1;
			
			logger.debug("totalRows: " + totalRows);
			logger.debug("totalPageNum: " + totalPageNum);
			logger.debug("currentPage: " + currentPage);
			logger.debug("pageSize: " + pageSize);
	
			pageableForm.setPageSize(String.valueOf(pageSize));
			pageableForm.setTotalItemsNum(String.valueOf(totalRows));
			pageableForm.setTotalPageNum(String.valueOf(totalPageNum));
			pageableForm.setCurrentPageNum(String.valueOf(currentPage));
	
			int firstRow = (currentPage - 1) * pageSize;
			int lastRow = firstRow + pageSize;
	
			resList = new ArrayList();
	
			for (int i = 0; i < list.size(); i++) {
				if (i == lastRow)
					break;
	
				if (i >= firstRow) {
					resList.add(list.get(i));
				}
			}
		}
		return resList;
	}

}