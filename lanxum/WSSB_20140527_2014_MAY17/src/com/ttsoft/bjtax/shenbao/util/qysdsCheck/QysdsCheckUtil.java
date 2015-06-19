/**
 * @Title:       QysdsCheckUtil.java
 * @Description: TODO
 * @date:        2014-4-15����09:59:30
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.util.qysdsCheck;

import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlxObserver;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlxObserver2;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckZfbaObserver;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.IsInQsqObserver;
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
		subject1.addObserver(new IsInQsqObserver());		//������
		subject1.addObserver(new CheckJdlxObserver());		//���ܷ�Χ����
		subject1.addObserver(new CheckZfbaObserver());		//�ֱܷ���
		
		subject2 = new Subject();
		subject2.addObserver(new CheckJdlxObserver2());		//���ܷ�Χ����
		
		subject3 = new Subject();
		subject3.addObserver(new CheckJdlxObserver());		//���ܷ�Χ����
	}
	
	private static QysdsCheckUtil instance;
	
	/**
	 * @Description: TODO ����
	 * @return
	 */
	public static QysdsCheckUtil getInstance()
	{
		if(instance == null)
		{
			instance = new QysdsCheckUtil();
		}
		return instance;
	}

	/**
	 * У����1
	 * @description�� ��У���鱻���¹���ʹ�ã������걨����2012a,b�� 
	 * 					�걨������Ϣ��
	 * 					��֧�����걨��
	 * 					��ҵ����˰���ⱸ��
	 * @description�� ����У�����ݰ����������ڣ����ܷ�Χ��������,�ֱܷ�����
	*/
	public final Subject subject1 ;
	
	
	
	/**
	 * У����2
	 * @description�� ��У���鱻���¹���ʹ��:��ҵ����˰����	
	 * @description�� ����У�����ݰ��������ܷ�Χ�������ͣ�
	*/
	public final Subject subject2 ;
	
	/**
	 * У����3
	 * @description�� ��У���鱻���¹���ʹ��:��ҵ����˰����	
	 * @description�� ����У�����ݰ��������ܷ�Χ��������(ʱ���)��
	*/
	public final Subject subject3 ;
	
	
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
