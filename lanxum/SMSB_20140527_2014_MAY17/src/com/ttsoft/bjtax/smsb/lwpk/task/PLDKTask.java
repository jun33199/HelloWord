package com.ttsoft.bjtax.smsb.lwpk.task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.common.PKLogCommon;
import com.ttsoft.bjtax.smsb.lwpk.model.PKFSModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PKLogModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.framework.util.VOPackage;

public class PLDKTask implements PKTaskInterface{

	public int execute(PKTaskModel pkTaskModel) {
		//���ؽ��
		int flag = 0;
		//���������з�������Ϣlist
		List list = pkTaskModel.getPkfsList();
		//����������ѭ������
		long time1 = System.currentTimeMillis();
		for(int i=0;i<list.size();i++){
			//���۷���model
			PKFSModel pkfsmodel = (PKFSModel) list.get(i);
			VOPackage vo = new VOPackage();
		    vo.setAction(CodeConstant.SMSB_QUERYACTION);
		    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
		    vo.setData(pkfsmodel);
		    try {
		    	ZhsbProxy.getInstance().process(vo);
				
			} catch (Exception e) {
				e.printStackTrace();
				//��¼�쳣��־
				PKLogCommon.saveLog("�����ۿ��������������͹���","�ڵ���PLDKTask��execute���������������ʱ�������쳣",e);
				flag=1;
			}
		
		}
		long time2 = System.currentTimeMillis();
		System.out.println("����һ�ε�ʱ��" + (time2-time1) +"����");
		return flag;
	}
	
	

}
