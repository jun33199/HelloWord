package com.lscdz.qysds.application.qysdsjb2014.base.handler;

import java.lang.reflect.Method;

import yangjian.frame.util.FrameException;

import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.qysdsnb2014.handler.QysdsNbHandler;
import com.lscdz.util.ActionHandler;
import com.lscdz.util.ClientMessage;
import com.lscdz.util.MyLogger;

public abstract class QysdsjbBaseHandler extends ActionHandler{
	public MyLogger myLogger =new MyLogger(QysdsNbHandler.class);
	//��ǰ��¼�û���Ϣ
	public Yh userData=null;
	@Override
	public StringBuffer processMsg(ClientMessage msg) throws FrameException {
		Method m=null;
		StringBuffer returnBuff = new StringBuffer();
		//����ȡ�����û����ݣ��򷵻ؿ���
		try {
			userData=this.getUserData(msg);
		} catch (FrameException e) {
			e.printStackTrace();
			this.rtnBizCode=RtnCodeMessage.Error_1010.Code;
			this.rtnBizMessage=RtnCodeMessage.Error_1010.Message;
			return new StringBuffer(this.ConvertVoToXml(null));
		}
		//�������������ļ�Ϊ�գ��򷵻ؿ���
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		//���������Ӧ����
		try {
			m = this.getClass().getMethod(msg.getAction(), new Class[] {ClientMessage.class});
			return returnBuff.append((StringBuffer) m.invoke(this, new Object[] {msg}));
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("δ�ҵ��÷�����"+msg.getAction());
		}
	}
	public abstract StringBuffer doInit(ClientMessage msg) throws FrameException;
	public abstract StringBuffer doQuery(ClientMessage msg) throws FrameException;
	public abstract StringBuffer doDelete(ClientMessage msg) throws FrameException;
	public abstract StringBuffer doSave(ClientMessage msg) throws FrameException;
	
}
