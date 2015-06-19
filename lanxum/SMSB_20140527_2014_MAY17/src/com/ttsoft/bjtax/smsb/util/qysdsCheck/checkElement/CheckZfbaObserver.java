/**
 * @Title:       CheckZfbaObserver.java
 * @Description: TODO
 * @date:        2014-7-31����16:01:09
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement;

import com.ttsoft.bjtax.sfgl.common.model.ZfjgInf;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * @Description: TODO ��ҵ����˰�걨��У����--����ʱ���
 * @author: 	 hezq
 * @time:        2014-7-31
 */

public class CheckZfbaObserver implements Observer{
	
	/**
	 *�����ҵ����˰�걨���Ӧ����
	 * @throws BaseException 
	 */
	 
	public void update(CheckBean checkBean) throws BaseException {

		ZfjgInf zfjgInf = null;
		// ��������Ϊ ��ʡ���ܻ�����˰�� 02 ���ʡ�з�֧������˰�� 03 ʱ
		if (CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR.equals(checkBean.getJdlx())
				|| CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())) {
			// ���ü����ӿ�
			try {
				zfjgInf = new ServiceProxy().getZjgbaInf(checkBean.getJsjdm(),
						checkBean.getSkssrqq(), checkBean.getSkssrqz());

			} catch (BaseException e) {
				throw ExceptionUtil.getBaseException(new Exception("�޷���ȡ������˰�������ݣ�"));
			}
			if (zfjgInf != null) {
				// �ܻ���
				if ("0".equals(zfjgInf.getBaqylx())) {
					if (!zfjgInf.isIsftsk()) {
						checkBean.setJdlx(CheckJdlx.QYSDSZGFWJDLX_DLNSR);// ���ü�������Ϊ������˰��
						System.out.println("---------CheckZfbaObserver  �ܻ���������Ϊ������˰��");
					}
				} 
                // ��֧����
				if ("1".equals(zfjgInf.getBaqylx())){
					// �Ƿ�����̯��ҵ����˰��ĿΪ���ҷ�֧��������Ϊ������֧����
					if (!zfjgInf.isCyftsk() && "02".equals(zfjgInf.getFzlx())) {
						checkBean.setJdlx(CheckJdlx.QYSDSZGFWJDLX_DLNSR);// ���ü�������Ϊ������˰��
						System.out.println("---------CheckZfbaObserver  ��֧����������Ϊ������˰��");
					}
				}
			}
		}
	}

	

}
