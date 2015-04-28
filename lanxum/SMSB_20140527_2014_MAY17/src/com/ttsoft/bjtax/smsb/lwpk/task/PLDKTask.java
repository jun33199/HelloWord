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
		//返回结果
		int flag = 0;
		//按区县银行分组后的信息list
		List list = pkTaskModel.getPkfsList();
		//按区县银行循环发送
		long time1 = System.currentTimeMillis();
		for(int i=0;i<list.size();i++){
			//批扣发送model
			PKFSModel pkfsmodel = (PKFSModel) list.get(i);
			VOPackage vo = new VOPackage();
		    vo.setAction(CodeConstant.SMSB_QUERYACTION);
		    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
		    vo.setData(pkfsmodel);
		    try {
		    	ZhsbProxy.getInstance().process(vo);
				
			} catch (Exception e) {
				e.printStackTrace();
				//记录异常日志
				PKLogCommon.saveLog("批量扣款任务调度组包发送功能","在调用PLDKTask的execute方法进行组包发送时出现了异常",e);
				flag=1;
			}
		
		}
		long time2 = System.currentTimeMillis();
		System.out.println("发送一次的时间" + (time2-time1) +"毫秒");
		return flag;
	}
	
	

}
