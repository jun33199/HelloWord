/**
 * @Title:       CheckJdlxObserver2.java
 * @Description: TODO
 * @date:        2014-4-3����04:20:57
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
 * @Description: TODO ���ܷ�Χ�������� У����--����ʱ���
 * @author: 	 Lijn
 * @time:        2014-4-3
 */
public class CheckJdlxObserver2 implements CheckJdlx{
	
	/**
	 *�������ܷ�Χ��������
	 * @throws BaseException 
	 */
	public void update(CheckBean checkBean) throws BaseException {
		
		//���ü����ӿ�
		ServiceProxy sp = new ServiceProxy();
		
		Map djMap = sp.getDjInfo(checkBean.getJsjdm());
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		if(!"10".equals(jbsj.getNsrzt()))
		{
		
			throw new ApplicationException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		}
		
		QYSDSZGFWJD qYSDSZGFWJD = null;
		try {
			qYSDSZGFWJD =sp.getQysdszgfwjd(checkBean.getJsjdm(),checkBean.getSksj());
		} catch (BaseException e) {
			throw new ApplicationException("�޷���ȡ��ҵ����˰���ܷ�Χ���������ܽ�����ز���");
		}
		
		String jdjg = qYSDSZGFWJD.getJdlxdm();
		if(qYSDSZGFWJD!=null && (QYSDSZGFWJDLX_DLNSR.equals(jdjg) || QYSDSZGFWJDLX_KSSFZJGNSR.equals(jdjg) || QYSDSZGFWJDLX_KSSZJGNSR.equals(jdjg) || QYSDSZGFWJDLX_ZFJGJZBSZJGNSR.equals(jdjg)))
		{
			jdjg = checkfirstKfjg(jdjg ,sp ,checkBean);
			checkBean.setJdlx(jdjg);
		}else {
			throw new ApplicationException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		}	
		
	}

	
	/**
	 * @Description: TODO ���ܷ�Χ����ģ��Ҫ���һ��Ǽ� ��ҵ��ʡ���ܺ��ֻܷ������ڱ��е��ܵ�������˰��
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
		String djnd = kydjrq.substring(0, 4);								//�Ǽ����
		
		String sknd = checkBean.getSksj().substring(0, 4);				//˰��ʱ���������
		
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
