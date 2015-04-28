/**
 * @Title:       IsInQsqObserver.java
 * @Description: TODO
 * @date:        2014-4-15����11:03:15
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * @Description: TODO ������У����
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class IsInQsqObserver implements Observer
{
	/**
	 *�Ƿ���������ڡ����ڽ��������걨��������������Ȼ�����걨������Ķ�2015.02.02,zhangj��
	 * @throws ApplicationException 
	 */
	public void update(CheckBean checkBean) throws ApplicationException {
		
		
		VOPackage vo = new VOPackage();
		vo.setData(checkBean);
		vo.setProcessor(CodeConstant.QYQSSDS_CHECKQSQ);
		try {
			checkBean = (CheckBean)SbzlProxy.getInstance().process(vo);
		} catch (Exception e) {
			throw new ApplicationException("�޷��鿴����ҵ�Ƿ����������");
		}
		
		if(checkBean == null || checkBean.getJsjdm()==null || "".equals(checkBean.getJsjdm()))
		{
			throw new ApplicationException("�޷��鿴����ҵ�Ƿ����������");
		}
		
//		if(checkBean.isInQsq())
//		{
//			throw new ApplicationException("����ҵ���������ڣ��޷����е�ǰ����");
//		}
//		if(checkBean.isFinishQs())
//		{
//			throw new ApplicationException("����ҵ�������ѽ���������������걨�����޷����е�ǰ����");
//		}		
		return;
	}


}
