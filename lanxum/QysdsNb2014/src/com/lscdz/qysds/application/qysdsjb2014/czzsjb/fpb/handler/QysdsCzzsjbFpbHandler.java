package com.lscdz.qysds.application.qysdsjb2014.czzsjb.fpb.handler;

import java.sql.Connection;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.Tools;

import com.lscdz.qysds.application.qysdsjb2014.base.handler.QysdsjbBaseHandler;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.fpb.helper.CzzsjbFpbHelper;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.fpb.vo.CzzssdsjbFpbVo;
import com.lscdz.qysds.common.service.qysds.Constants;
import com.lscdz.util.ClientMessage;

public class QysdsCzzsjbFpbHandler extends QysdsjbBaseHandler{
	/**
	 * 初始化代码表等数据
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doInit(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		return returnBuff.append(this.ConvertVoToXml(null));
	}
	/**
	 * 查询已保存的数据
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doQuery(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		Connection conn =null;
		CzzssdsjbFpbVo czzssdsjbFpbVo=new CzzssdsjbFpbVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbFpbVo);			
			conn=ResourceManager.getConnection();
			// 设置报表期类型
			czzssdsjbFpbVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// 设置录入人名称
			czzssdsjbFpbVo.setLrr(userData.getYhid());		
			new CzzsjbFpbHelper().query(czzssdsjbFpbVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
			this.setRtnBizMessage(ex.getMessage());
			return returnBuff.append(this.ConvertVoToXml(null));
		}finally{
			Tools.closeConnection(conn);
		}
		return returnBuff.append(this.ConvertVoToXml(czzssdsjbFpbVo));
	}
	/**
	 * 删除库中的数据
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doDelete(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Connection conn =null;
		CzzssdsjbFpbVo czzssdsjbFpbVo=new CzzssdsjbFpbVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbFpbVo);			
			conn=ResourceManager.getConnection();
			// 设置录入人名称
			czzssdsjbFpbVo.setLrr(userData.getYhid());		
			new CzzsjbFpbHelper().delete(czzssdsjbFpbVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1002.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1002.Message);
			return returnBuff.append(this.ConvertVoToXml(null));
		}finally{
			Tools.closeConnection(conn);
		}
		this.setRtnBizCode(RtnCodeMessage.Success.Code);
		this.setRtnBizMessage("删除成功！");
		return returnBuff.append(this.ConvertVoToXml(null));
	}
	/**
	 * 保存数据
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doSave(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Connection conn =null;
		CzzssdsjbFpbVo czzssdsjbFpbVo=new CzzssdsjbFpbVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbFpbVo);			
			conn=ResourceManager.getConnection();
			// 设置报表期类型
			czzssdsjbFpbVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// 设置录入人名称
			czzssdsjbFpbVo.setLrr(userData.getYhid());	
			new CzzsjbFpbHelper().save(czzssdsjbFpbVo);			
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1008.Message);
			return returnBuff.append(this.ConvertVoToXml(czzssdsjbFpbVo));
		}finally{
			Tools.closeConnection(conn);
		}
		this.setRtnBizCode(RtnCodeMessage.Success.Code);
		this.setRtnBizMessage("保存成功！");
		return returnBuff.append(this.ConvertVoToXml(czzssdsjbFpbVo));
	}
}
