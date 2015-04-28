/**
 * @Title:       IsSuitableYear.java
 * @Description: TODO
 * @date:        2014-4-15上午11:10:11
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement;

import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * @Description: TODO  版本校验器
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class IsSuitableYearObserver implements Observer{

	/**
	 *校验是否是合适的版本
	 * @throws ApplicationException 
	 */
	public void update(CheckBean checkBean) throws ApplicationException 
	{
		// TODO Auto-generated method stub
		
		int versionTimeStartInt;
		int versionTimeEndInt;
		int currentTimeInt;
		
		try {
			currentTimeInt = Integer.parseInt(checkBean.getCurrentTime());
		} catch (Exception e) {
			currentTimeInt = 1;
		}
		
		try {
			versionTimeStartInt = Integer.parseInt(checkBean.getVersionStartTime());
		} catch (Exception e) {
			versionTimeStartInt = currentTimeInt-1;
		}
		 
		try {
			versionTimeEndInt = Integer.parseInt(checkBean.getVersionEndTime());
		} catch (Exception e) {
			versionTimeEndInt = currentTimeInt+1;
		}
		
		if(currentTimeInt < versionTimeStartInt || currentTimeInt > versionTimeEndInt)
		{
			
			throw new ApplicationException("您所输入的税款所属日期不适用于本版本，请使用合适版本进行申报");
		}
		return;
	}

}
