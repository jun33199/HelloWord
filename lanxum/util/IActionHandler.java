package com.lscdz.util;

import yangjian.frame.util.FrameException;

public interface IActionHandler {
	/**
	 * ����ͻ����ķ��������� XML ��������
	 * @param msg
	 * @return
	 */
	public StringBuffer processMsg(ClientMessage msg) throws FrameException;
	
}
