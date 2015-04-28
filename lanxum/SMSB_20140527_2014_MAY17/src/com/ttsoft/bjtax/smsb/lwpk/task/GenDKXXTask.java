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
 * <p>Title: 北京地税核心征管系统-税库行</p>
 * <p>Description: 生成代扣信息</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 康洪涛
 * @version 1.0
 */
public class GenDKXXTask implements PKTaskInterface {

	public int execute(PKTaskModel pkTaskModel) {
		//返回结果
		int flag = 0;
		VOPackage vo = new VOPackage();
	    vo.setAction(CodeConstant.SMSB_SAVEACTION);
	    vo.setProcessor("com.ttsoft.bjtax.smsb.lwpk.processor.LWPKProcessor");
	    vo.setData(pkTaskModel);
	    try {
	    	ZhsbProxy.getInstance().process(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			//记录异常日志
			PKLogCommon.saveLog("批量扣款任务调度生成代扣信息功能","在调用GenDKXXTask类生成代扣信息时发生了异常",e);
			flag=1;
		}
		
		return flag;
	}

	

}
