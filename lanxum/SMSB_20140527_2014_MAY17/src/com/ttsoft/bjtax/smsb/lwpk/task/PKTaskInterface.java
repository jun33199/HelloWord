package com.ttsoft.bjtax.smsb.lwpk.task;

import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;

/**
 * <p>Title: ������˰��������ϵͳ</p>
 * <p>Description: �����ۿ�ӿ�</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author ������
 * @version 1.0
 */
public interface PKTaskInterface {
	
	/**
	 * ����ҵ������ִ��ҵ����
	 * @param pkTaskModel
	 * @return 0ִ�� �ɹ���1ִ��ʧ�ܣ�2ִ���쳣
	 */
	public int execute(PKTaskModel pkTaskModel);
}
