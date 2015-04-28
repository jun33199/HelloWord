/**
 * @Title:       CheckJdlxObserver.java
 * @Description: TODO
 * @date:        2014-4-15����11:13:05
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
 * @Description: TODO ���ܷ�Χ�������� У����--����˰��������������
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class CheckJdlxObserver implements CheckJdlx{
	
	/**
	 *�������ܷ�Χ��������
	 * @throws BaseException 
	 */
	public void update(CheckBean checkBean) throws BaseException {
		
		System.out.println("˰������������"+checkBean.getSkssrqq());
		System.out.println("˰����������ֹ��"+checkBean.getSkssrqz());
		//���ü����ӿ�
		ServiceProxy sp = new ServiceProxy();
		
		Map djMap = sp.getDjInfo(checkBean.getJsjdm());
		SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
		
		System.out.println("��˰��״̬"+jbsj.getNsrzt());
		if(!"10".equals(jbsj.getNsrzt()))
		{
			throw new ApplicationException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		}
		
		List ls = null;
		try {
			ls =sp.getQysdszgfwjdList(checkBean.getJsjdm(),checkBean.getSkssrqq(),checkBean.getSkssrqz());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ApplicationException("�޷���ȡ��ҵ����˰���ܷ�Χ���������ܽ�����ز���");
		}
		
		
		//�޲�ѯ���
		if(ls==null || ls.size()==0)
			throw new ApplicationException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		
		//�в�ѯ���
		String jdjg = "";		//�������
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
		throw new ApplicationException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		System.out.println("��������ǣ�"+jdjg);
		jdjg = checkfirstKfjg(jdjg ,sp ,checkBean);
		checkBean.setJdlx(jdjg);	
	}

	/**
	 * @Description: TODO У����ҵ����˰Ӧ����
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
	 * @Description: TODO �Ƚ�����������ʱ��
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
		
		String sknd = checkBean.getSkssrqz().substring(0, 4);				//˰���ֹ�����������
		
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
