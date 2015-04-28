/**
 * @Title:       IsInQsqObserver.java
 * @Description: TODO
 * @date:        2014-4-15上午11:03:15
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement;

import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.util.VOPackage;


/**
 * @Description: TODO 校验清算期
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class IsInQsqObserver implements Observer
{
	/**
	 *是否进入清算期。算期结束不能申报，进入清算期仍然可以申报（需求改动2015.02.02,zhangj）
	 * @throws ApplicationException 
	 */
	public void update(CheckBean checkBean) throws ApplicationException {
		
		
		VOPackage vo = new VOPackage();
		vo.setData(checkBean);
		vo.setProcessor(ProcessorNames.QYQSSDS_CHECKQSQ);
		try {
			checkBean = (CheckBean)ShenbaoProxy.getInstance().process(vo);
		} catch (Exception e) {
			throw new ApplicationException("无法查看该企业是否进入清算期");
		}
		
		if(checkBean == null || checkBean.getJsjdm()==null || "".equals(checkBean.getJsjdm()))
		{
			throw new ApplicationException("无法查看该企业是否进入清算期");
		}
		
//		if(checkBean.isInQsq())
//		{
//			throw new ApplicationException("该企业进入清算期，无法进行当前操作");
//		}
//		if(checkBean.isFinishQs())
//		{
//			throw new ApplicationException("该企业清算期已结束（已完成清算申报），无法进行当前操作");
//		}			
		return;
	}


}
