package com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement;

import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

public interface Observer {
	/**
	 * @Description: TODO У�鵥Ԫ����ں���
	 * @param sbxxVO
	 * @throws ApplicationException
	 * @throws BaseException 
	 */
	public void update(CheckBean checkBean) throws ApplicationException, BaseException;
}
