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
	 * 初始化获取代码表等初始化数据
	 * @param msg
	 * @throws FrameException
	 */
	public abstract StringBuffer doInit(ClientMessage msg) throws FrameException;
	/**
	 * 保存备案事项业务数据
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doSave(ClientMessage msg) throws FrameException;
	/**
	 * 提交备案事项业务数据
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doCommit(ClientMessage msg) throws FrameException;
	/**
	 * 查看
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doView(ClientMessage msg) throws FrameException;
	
	
	/**
	 * 接受申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doAccept(ClientMessage msg) throws FrameException;
	
	/**
	 * 拒绝申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doReject(ClientMessage msg) throws FrameException;
	
	/**
	 * 作废申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doCancellation(ClientMessage msg) throws FrameException;
	
	/**
	 * 变更申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doModify(ClientMessage msg) throws FrameException;
	
	/**
	 * 变更备案事项内容
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doModifyBasx(ClientMessage msg) throws FrameException;
	
	/**
	 * 变更备案事项内容
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doAuditBasx(ClientMessage msg) throws FrameException;
	
	/**
	 * 变更备案事项内容
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public abstract StringBuffer doAuditRejectBasx(ClientMessage msg) throws FrameException;
}
