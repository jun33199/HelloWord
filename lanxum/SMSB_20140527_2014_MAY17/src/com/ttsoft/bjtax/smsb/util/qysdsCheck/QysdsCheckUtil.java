/**
 * @Title:       QysdsCheckUtil.java
 * @Description: TODO
 * @date:        2014-4-15����09:59:30
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlxObserver;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlxObserver2;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckZfbaObserver;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.IsInQsqObserver;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.IsSuitableYearObserver;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

/**
 * @Description: TODO �ں�̨У����Ϣ
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class QysdsCheckUtil {
	
	/**
	 * @Description: constructor �ڸ÷��������У����
	 */
	private QysdsCheckUtil()
	{
		//�ع�������
		subject1 = new Subject();
		subject1.addObserver(new IsSuitableYearObserver());
		subject1.addObserver(new IsInQsqObserver());
		subject1.addObserver(new CheckJdlxObserver());
		subject1.addObserver(new CheckZfbaObserver()); 
		
		subject2 = new Subject();
		subject2.addObserver(new IsSuitableYearObserver());
		subject2.addObserver(new IsInQsqObserver());
		
		
		
		subject4 = new Subject();
		subject4.addObserver(new IsInQsqObserver());
		subject4.addObserver(new CheckJdlxObserver());
		
		subject5 = new Subject();
		subject5.addObserver(new CheckJdlxObserver());
		
		subject6 = new Subject();
		subject6.addObserver(new CheckJdlxObserver2());
		
	}
	
	private static QysdsCheckUtil instance;
	
	public static QysdsCheckUtil getInstance()
	{
		if(instance == null)
		{
			instance = new QysdsCheckUtil();
		}
		return instance;
	}

	/**
	 * @description:У����1
	 * @description:��У���鱻���¹���ʹ�ã������걨������A�ࣩ--2012��2008��2009��
	 * 				        		�����걨������B�ࣩ--2008��2009��2012��
	 * 				        		�����걨�걨����--2013��2008��2009��
	 * 				     		            �����걨�걨�˶�--2012��2008,2009��
	 * 				        	            �����걨 ��ҵ����˰��֧���������˰�걨��--2013��2012
	 * 				                                                �����걨��ҵ����˰������˰��֧���������2008��2013,2009
	 * 								�����걨����������˰�˻�����Ϣ�ǼǱ�--2013��2009,2008��
	 * @description:����У�����ݰ������汾���ƣ������ڣ����ܷ�Χ�������ͣ�����ʱ��Σ����ֱܷ���
	*/
	public final Subject subject1 ;
	
	/**
	 * @description:У����2
	 * @description:��У���鱻���¹���ʹ�ã������걨������A�ࣩ--2006
	 * 				                                                �����걨������B�ࣩ--2006
	 * 				                                                �����걨�걨����--2006��
	 *   			                                                �����걨�걨�˶�--2006
	 *   			                                                �����걨 ����������˰�˻�����Ϣ�ǼǱ�--2006	
	 * @description:����У�����ݰ������汾���ƣ�������;
	*/
	public final Subject subject2 ;

	/**
	 * @description:У����4
	 * @description:��У���鱻���¹���ʹ�ã� ��ҵ����˰����˰����	
	 * @description:����У�����ݰ�����������,���ܷ�Χ��������(����ʱ���);
	*/
	public final Subject subject4;
	
	/**
	 * @description:У����5
	 * @description:��У���鱻���¹���ʹ�ã� ��ҵ����˰�����걨ģ��	
	 * @description:����У�����ݰ��������ܷ�Χ��������(����ʱ���);
	*/
	public final Subject subject5;
	
	/**
	 * @description:У����6
	 * @description:��У���鱻���¹���ʹ�ã� ��ҵ����˰�������ģ��	
	 * @description:����У�����ݰ��������ܷ�Χ��������(����ʱ���);
	 */
	public final Subject subject6;
	
	
	/**
	 * @Description: TODO 
	 * @param check:У������
	 * @param qysdsNewForm ��form
	 * @throws BaseException 
	 */
 	public void check(Subject check, CheckBean checkBean) throws BaseException 
	{
		//���ù۲���
		check.checkElement(checkBean);
	}
}
