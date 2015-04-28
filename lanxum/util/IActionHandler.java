package com.lscdz.util;

import yangjian.frame.util.FrameException;

public interface IActionHandler {
	/**
	 * 处理客户报文方法，返回 XML 报文内容
	 * @param msg
	 * @return
	 */
	public StringBuffer processMsg(ClientMessage msg) throws FrameException;
	
}
