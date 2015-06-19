package com.lscdz.qysds.application.jmsbayj2014.base.handler;

import yangjian.frame.util.FrameException;

import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsbayj2014.base.access.JmsbaBaseAccessExt;
import com.lscdz.util.ActionHandler;
import com.lscdz.util.ClientMessage;

public abstract class JmsbaBaseHandler extends ActionHandler{
	public Yh userData = null;
	public JmsbaBaseAccessExt baseAccessExt=new JmsbaBaseAccessExt();
	/**
	 * ��ʼ����ȡ�����ȳ�ʼ������
	 * @param msg
	 * @throws FrameException
	 */
	public abstract StringBuffer doInit(ClientMessage msg) throws FrameException;
	/**
	 * ���汸������ҵ������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doSave(ClientMessage msg) throws FrameException;
	/**
	 * �ύ��������ҵ������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doCommit(ClientMessage msg) throws FrameException;
	/**
	 * �鿴
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doView(ClientMessage msg) throws FrameException;
	
	
	/**
	 * ��������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doAccept(ClientMessage msg) throws FrameException;
	
	/**
	 * �ܾ�����
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doReject(ClientMessage msg) throws FrameException;
	
	/**
	 * ��������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doCancellation(ClientMessage msg) throws FrameException;
	
	/**
	 * �������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doModify(ClientMessage msg) throws FrameException;
	
	/**
	 * ���������������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doModifyBasx(ClientMessage msg) throws FrameException;
	
	/**
	 * ���������������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doAuditBasx(ClientMessage msg) throws FrameException;
	
	/**
	 * ���������������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doAuditRejectBasx(ClientMessage msg) throws FrameException;
}
