/**
 * @Title:       CheckJdlxObserver2.java
 * @Description: TODO
 * @date:        2014-4-3下午04:20:57
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement;

import java.util.Map;

import com.ttsoft.bjtax.dj.model.QYSDSZGFWJD;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

/**
 * @Description: TODO 征管范围鉴定类型 校验器--根据时间点
 * @author: 	 Lijn
 * @time:        2014-4-3
 */
public class CheckJdlxObserver2 implements CheckJdlx{
	
	/**
	 *检验征管范围鉴定类型
	 * @throws BaseException 
	 */
	public void update(CheckBean checkBean) throws BaseException {
		
		//调用鉴定接口
		ServiceProxy sp = new ServiceProxy();
		
		Map djMap = sp.getDjInfo(checkBean.getJsjdm());
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		if(!"10".equals(jbsj.getNsrzt()))
		{
		
			throw new ApplicationException("该企业不是企业所得税应征户，不能进行相关操作");
		}
		
		QYSDSZGFWJD qYSDSZGFWJD = null;
		try {
			qYSDSZGFWJD =sp.getQysdszgfwjd(checkBean.getJsjdm(),checkBean.getSksj());
		} catch (BaseException e) {
			throw new ApplicationException("无法获取企业所得税征管范围鉴定，不能进行相关操作");
		}
		
		String jdjg = qYSDSZGFWJD.getJdlxdm();
		if(qYSDSZGFWJD!=null && (QYSDSZGFWJDLX_DLNSR.equals(jdjg) || QYSDSZGFWJDLX_KSSFZJGNSR.equals(jdjg) || QYSDSZGFWJDLX_KSSZJGNSR.equals(jdjg) || QYSDSZGFWJDLX_ZFJGJZBSZJGNSR.equals(jdjg)))
		{
			jdjg = checkfirstKfjg(jdjg ,sp ,checkBean);
			checkBean.setJdlx(jdjg);
		}else {
			throw new ApplicationException("该企业不是企业所得税应征户，不能进行相关操作");
		}	
		
	}

	
	/**
	 * @Description: TODO 增管范围鉴定模块要求第一年登记 企业跨省市总和总分机构均在本市的总当独立纳税人
	 * @param jdjg
	 * @param sp
	 * @param checkBean
	 * @return
	 * @throws BaseException
	 */
	private String checkfirstKfjg(String jdjg ,ServiceProxy sp ,CheckBean checkBean) throws BaseException
	{
		Map djMap = sp.getDjInfo(checkBean.getJsjdm());
		
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		String kydjrq = jbsj.getKydjrq().toString();
		String djnd = kydjrq.substring(0, 4);								//登记年度
		
		String sknd = checkBean.getSksj().substring(0, 4);				//税款时间所在年度
		
		boolean isInCondition1 = (djnd!=null && sknd!=null && djnd.equals(sknd));
		boolean isInCondition2 = (QYSDSZGFWJDLX_KSSZJGNSR.equals(jdjg) || QYSDSZGFWJDLX_ZFJGJZBSZJGNSR.equals(jdjg));
		
		
		//System.out.println("****djnd:"+djnd+"****sknd:"+sknd+"*****isInCondition1"+isInCondition1+"***isInCondition2"+isInCondition2);
		if(isInCondition1 && isInCondition2)
		{
			jdjg = QYSDSZGFWJDLX_DLNSR;
		}
		return jdjg;
	}
	
}
