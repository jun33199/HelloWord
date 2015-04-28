package com.lscdz.qysds.application.qysdsjb2014.czzsjb.gdzcjszj.handler;

import java.sql.Connection;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.Tools;

import com.lscdz.qysds.application.qysdsjb2014.base.handler.QysdsjbBaseHandler;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.gdzcjszj.helper.CzzsjbGdzcjszjHelper;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.gdzcjszj.vo.CzzssdsjbGdzcjszjVo;
import com.lscdz.qysds.common.service.qysds.Constants;
import com.lscdz.util.ClientMessage;

public class QysdsCzzsjbGdzcjszjHandler extends QysdsjbBaseHandler{
	/**
	 * ��ʼ������������
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
	 * ��ѯ�ѱ��������
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doQuery(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		Connection conn =null;
		CzzssdsjbGdzcjszjVo czzssdsjbGdzcjszjVo=new CzzssdsjbGdzcjszjVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbGdzcjszjVo);			
			conn=ResourceManager.getConnection();
			// ���ñ���������
			czzssdsjbGdzcjszjVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// ����¼��������
			czzssdsjbGdzcjszjVo.setLrr(userData.getYhid());		
			new CzzsjbGdzcjszjHelper().query(czzssdsjbGdzcjszjVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
			this.setRtnBizMessage(ex.getMessage());
			return returnBuff.append(this.ConvertVoToXml(null));
		}finally{
			Tools.closeConnection(conn);
		}
		return returnBuff.append(this.ConvertVoToXml(czzssdsjbGdzcjszjVo));
	}
	/**
	 * ɾ�����е�����
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doDelete(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Connection conn =null;
		CzzssdsjbGdzcjszjVo czzssdsjbGdzcjszjVo=new CzzssdsjbGdzcjszjVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbGdzcjszjVo);			
			conn=ResourceManager.getConnection();
			// ����¼��������
			czzssdsjbGdzcjszjVo.setLrr(userData.getYhid());		
			new CzzsjbGdzcjszjHelper().delete(czzssdsjbGdzcjszjVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1002.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1002.Message);
			return returnBuff.append(this.ConvertVoToXml(null));
		}finally{
			Tools.closeConnection(conn);
		}
		this.setRtnBizCode(RtnCodeMessage.Success.Code);
		this.setRtnBizMessage("ɾ���ɹ���");
		return returnBuff.append(this.ConvertVoToXml(null));
	}
	/**
	 * ��������
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doSave(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Connection conn =null;
		CzzssdsjbGdzcjszjVo czzssdsjbGdzcjszjVo=new CzzssdsjbGdzcjszjVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbGdzcjszjVo);			
			conn=ResourceManager.getConnection();
			// ���ñ���������
			czzssdsjbGdzcjszjVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// ����¼��������
			czzssdsjbGdzcjszjVo.setLrr(userData.getYhid());	
			new CzzsjbGdzcjszjHelper().save(czzssdsjbGdzcjszjVo);			
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1008.Message);
			return returnBuff.append(this.ConvertVoToXml(czzssdsjbGdzcjszjVo));
		}finally{
			Tools.closeConnection(conn);
		}
		this.setRtnBizCode(RtnCodeMessage.Success.Code);
		this.setRtnBizMessage("����ɹ���");
		return returnBuff.append(this.ConvertVoToXml(czzssdsjbGdzcjszjVo));
	}
}
