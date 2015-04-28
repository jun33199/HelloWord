/**
 * @Title:       CheckJdlxObserver.java
 * @Description: TODO
 * @date:        2014-4-15上午11:13:05
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement;

import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.QYSDSZGFWJD;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

/**
 * @Description: TODO 征管范围鉴定类型 校验器--根据税款所属日期区间
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class CheckJdlxObserver implements CheckJdlx{
	
	/**
	 *检验征管范围鉴定类型
	 * @throws BaseException 
	 */
	public void update(CheckBean checkBean) throws BaseException {
		
		System.out.println("税款所属日期起："+checkBean.getSkssrqq());
		System.out.println("税款所属日期止："+checkBean.getSkssrqz());
		//调用鉴定接口
		ServiceProxy sp = new ServiceProxy();
		
		Map djMap = sp.getDjInfo(checkBean.getJsjdm());
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		
		System.out.println("纳税人状态"+jbsj.getNsrzt());
		if(!"10".equals(jbsj.getNsrzt()))
		{
			throw new ApplicationException("该企业不是企业所得税应征户，不能进行相关操作");
		}
		
		List ls = null;
		try {
			ls =sp.getQysdszgfwjdList(checkBean.getJsjdm(),checkBean.getSkssrqq(),checkBean.getSkssrqz());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ApplicationException("无法获取企业所得税征管范围鉴定，不能进行相关操作");
		}
		
		
		//无查询结果
		if(ls==null || ls.size()==0)
			throw new ApplicationException("该企业不是企业所得税应征户，不能进行相关操作");
		
		//有查询结果
		String jdjg = "";		//鉴定结果
		QYSDSZGFWJD finalJd = (QYSDSZGFWJD)ls.get(0);	
		for(int i=0;i<ls.size();i++)
		{

			QYSDSZGFWJD temp=(QYSDSZGFWJD)ls.get(i);

			if(checkYzh(temp) && (checktime(finalJd,temp) || "".equals(jdjg)) )
			{
				jdjg = temp.getJdlxdm();
				finalJd = temp;
			}	
		}

		if(jdjg==null || "".equals(jdjg))
		throw new ApplicationException("该企业不是企业所得税应征户，不能进行相关操作");
		System.out.println("鉴定结果是："+jdjg);
		jdjg = checkfirstKfjg(jdjg ,sp ,checkBean);
		checkBean.setJdlx(jdjg);	
	}

	/**
	 * @Description: TODO 校验企业所得税应征户
	 * @param qYSDSZGFWJD
	 * @return
	 */
	private boolean checkYzh(QYSDSZGFWJD qYSDSZGFWJD)
	{
		if(QYSDSZGFWJDLX_DLNSR.equals(qYSDSZGFWJD.getJdlxdm()) || QYSDSZGFWJDLX_KSSFZJGNSR.equals(qYSDSZGFWJD.getJdlxdm()) || QYSDSZGFWJDLX_KSSZJGNSR.equals(qYSDSZGFWJD.getJdlxdm()) || QYSDSZGFWJDLX_ZFJGJZBSZJGNSR.equals(qYSDSZGFWJD.getJdlxdm()))
		{
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Description: TODO 比较两条鉴定的时间
	 * @param finaljd
	 * @param currentjd
	 * @return
	 */
	private boolean checktime(QYSDSZGFWJD finaljd ,QYSDSZGFWJD currentjd)
	{
		if(finaljd.getJdqsrq()==null || currentjd.getJdqsrq()==null || finaljd.getJdqsrq().compareTo(currentjd.getJdqsrq())>=0)
		{
			return false;
		}else{
		return true;
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
		
		String sknd = checkBean.getSkssrqz().substring(0, 4);				//税款截止日期所在年度
		
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
