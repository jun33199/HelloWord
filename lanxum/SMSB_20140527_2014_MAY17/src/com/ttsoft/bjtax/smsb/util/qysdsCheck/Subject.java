/**
 * @Title:       Bubject.java
 * @Description: TODO
 * @date:        2014-4-15上午10:06:38
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.Observer;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class Subject {
	//@Field--observers:TODO 校验容器
	private List observers = new ArrayList();

	void addObserver(Observer obs){
		this.observers.add(obs);
	}
	
	void delObserver(Observer obs){
		this.observers.remove(obs);
	}
		
	
	/**
	 * @Description: TODO  链式校验
	 * @param qysdsNewForm
	 * @throws BaseException 
	 */
	public void checkElement(CheckBean checkBean) throws BaseException
	{
		for(int i = 0;i<observers.size();i++)
		{
			((Observer)observers.get(i)).update(checkBean);
		}
	}
		
}
