package com.ttsoft.bjtax.smsb.lwpk.task;

import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;

/**
 * <p>Title: 北京市税库银联网系统</p>
 * <p>Description: 批量扣款接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 立思辰</p>
 * @author 康洪涛
 * @version 1.0
 */
public interface PKTaskInterface {
	
	/**
	 * 根据业务类型执行业务处理
	 * @param pkTaskModel
	 * @return 0执行 成功，1执行失败，2执行异常
	 */
	public int execute(PKTaskModel pkTaskModel);
}
