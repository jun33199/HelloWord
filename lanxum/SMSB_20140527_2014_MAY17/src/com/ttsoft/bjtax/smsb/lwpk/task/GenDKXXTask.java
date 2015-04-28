package com.ttsoft.bjtax.smsb.lwpk.task;


import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.PKLogCommon;
import com.ttsoft.bjtax.smsb.lwpk.model.PKLogModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;
import com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor;
import com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ-˰����</p>
 * <p>Description: ���ɴ�����Ϣ</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ��˼��</p>
 * @author ������
 * @version 1.0
 */
public class GenDKXXTask implements PKTaskInterface {

	public int execute(PKTaskModel pkTaskModel) {
		//���ؽ��
		int flag = 0;
		VOPackage vo = new VOPackage();
	    vo.setAction(CodeConstant.SMSB_SAVEACTION);
	    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
	    vo.setData(pkTaskModel);
	    try {
	    	ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			//��¼�쳣��־
			PKLogCommon.saveLog("�����ۿ�����������ɴ�����Ϣ����","�ڵ���GenDKXXTask�����ɴ�����Ϣʱ�������쳣",e);
			flag=1;
		}
		
		return flag;
	}

	

}
