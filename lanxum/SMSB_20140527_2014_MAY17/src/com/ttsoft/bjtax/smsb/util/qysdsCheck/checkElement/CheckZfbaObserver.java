/**
 * @Title:       CheckZfbaObserver.java
 * @Description: TODO
 * @date:        2014-7-31上午16:01:09
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement;

import com.ttsoft.bjtax.sfgl.common.model.ZfjgInf;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * @Description: TODO 企业所得税申报表校验器--根据时间点
 * @author: 	 hezq
 * @time:        2014-7-31
 */

public class CheckZfbaObserver implements Observer{
	
	/**
	 *检查企业所得税申报表对应类型
	 * @throws BaseException 
	 */
	 
	public void update(CheckBean checkBean) throws BaseException {

		ZfjgInf zfjgInf = null;
		// 鉴定类型为 跨省市总机构纳税人 02 或跨省市分支机构纳税人 03 时
		if (CheckJdlx.QYSDSZGFWJDLX_KSSZJGNSR.equals(checkBean.getJdlx())
				|| CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())) {
			// 调用鉴定接口
			try {
				zfjgInf = new ServiceProxy().getZjgbaInf(checkBean.getJsjdm(),
						checkBean.getSkssrqq(), checkBean.getSkssrqz());

			} catch (BaseException e) {
				throw ExceptionUtil.getBaseException(new Exception("无法获取汇总纳税备案数据！"));
			}
			if (zfjgInf != null) {
				// 总机构
				if ("0".equals(zfjgInf.getBaqylx())) {
					if (!zfjgInf.isIsftsk()) {
						checkBean.setJdlx(CheckJdlx.QYSDSZGFWJDLX_DLNSR);// 设置鉴定类型为独立纳税人
						System.out.println("---------CheckZfbaObserver  总机构已设置为独立纳税人");
					}
				} 
                // 分支机构
				if ("1".equals(zfjgInf.getBaqylx())){
					// 是否参与分摊企业所得税项目为否且分支机构类型为二级分支机构
					if (!zfjgInf.isCyftsk() && "02".equals(zfjgInf.getFzlx())) {
						checkBean.setJdlx(CheckJdlx.QYSDSZGFWJDLX_DLNSR);// 设置鉴定类型为独立纳税人
						System.out.println("---------CheckZfbaObserver  分支机构已设置为独立纳税人");
					}
				}
			}
		}
	}

	

}
