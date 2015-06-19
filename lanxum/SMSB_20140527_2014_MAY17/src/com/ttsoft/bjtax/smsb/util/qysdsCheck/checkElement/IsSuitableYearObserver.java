/**
 * @Title:       IsSuitableYear.java
 * @Description: TODO
 * @date:        2014-4-15����11:10:11
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement;

import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * @Description: TODO  �汾У����
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class IsSuitableYearObserver implements Observer{

	/**
	 *У���Ƿ��Ǻ��ʵİ汾
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
			
			throw new ApplicationException("���������˰���������ڲ������ڱ��汾����ʹ�ú��ʰ汾�����걨");
		}
		return;
	}

}
