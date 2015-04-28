package com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.handler;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.Tools;

import com.lscdz.qysds.application.qysdsjb2014.QysdsJb2014Contant;
import com.lscdz.qysds.application.qysdsjb2014.base.handler.QysdsjbBaseHandler;
import com.lscdz.qysds.application.qysdsjb2014.base.util.QysdsBaseUtil;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.helper.CzzsjbSbbHelper;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.util.CzzsjbSbbUtil;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.vo.CzzssdsjbSbbVo;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysds.Constants;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.util.ClientMessage;

/**
 * �������ռ����걨����ز�����
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-4-2 ����03:26:09
 */
public class QysdsCzzsjbSbbHandler extends QysdsjbBaseHandler{

	/**
	 * ��ʼ������������
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doInit(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		CzzssdsjbSbbVo czzssdsjbSbbVo=new CzzssdsjbSbbVo();
		try {
			this.ConvertXmlToVo(msg, czzssdsjbSbbVo);
			// �õ���ǰʱ���������
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			Map getsbjd = new CzzsjbSbbUtil().quarterSkssrq(curTime);
			Timestamp skssksrq = (Timestamp) getsbjd.get(QysdsJb2014Contant.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) getsbjd.get(QysdsJb2014Contant.SKSSJSRQ);
			czzssdsjbSbbVo.setSbrq(DateUtils.getDate(curTime));
			czzssdsjbSbbVo.setSkssksrq(DateUtils.getDate(skssksrq));
			czzssdsjbSbbVo.setSkssjsrq(DateUtils.getDate(skssjsrq));
			CzzsjbSbbUtil.initCodeTable(czzssdsjbSbbVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage("��ʼ����������");
			return returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff.append(this.ConvertVoToXml(czzssdsjbSbbVo));
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
		CzzssdsjbSbbVo czzssdsjbSbbVo=new CzzssdsjbSbbVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbSbbVo);			
			conn=ResourceManager.getConnection();
			// ���ñ���������
			czzssdsjbSbbVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// ����¼��������
			czzssdsjbSbbVo.setLrr(userData.getYhid());		
			CheckBean checkBean = new CheckBean();	
			//���������ڡ������ֱܷ��������ܷ�Χ����
			QysdsBaseUtil.qysdsCheckAll(conn,checkBean, czzssdsjbSbbVo);
			if("".equals(checkBean.getJdlx())){
				this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
				this.setRtnBizMessage("����ҵ������ҵ����˰Ӧ������");
				return returnBuff.append(this.ConvertVoToXml(null));				
			}
			System.out.println("11");
			new CzzsjbSbbHelper().query(czzssdsjbSbbVo);
			CzzsjbSbbUtil.initCodeTable(czzssdsjbSbbVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
			this.setRtnBizMessage(ex.getMessage());
			return returnBuff.append(this.ConvertVoToXml(null));
		}finally{
			Tools.closeConnection(conn);
		}
		return returnBuff.append(this.ConvertVoToXml(czzssdsjbSbbVo));
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
		CzzssdsjbSbbVo czzssdsjbSbbVo=new CzzssdsjbSbbVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbSbbVo);			
			conn=ResourceManager.getConnection();
			// ����¼��������
			czzssdsjbSbbVo.setLrr(userData.getYhid());		
			new CzzsjbSbbHelper().delete(czzssdsjbSbbVo);
			CzzsjbSbbUtil.initCodeTable(czzssdsjbSbbVo);
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
		CzzssdsjbSbbVo czzssdsjbSbbVo=new CzzssdsjbSbbVo();
		try {			
			this.ConvertXmlToVo(msg, czzssdsjbSbbVo);			
			conn=ResourceManager.getConnection();
			// ���ñ���������
			czzssdsjbSbbVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// ����¼��������
			czzssdsjbSbbVo.setLrr(userData.getYhid());		
			CheckBean checkBean = new CheckBean();	
			//���������ڡ������ֱܷ��������ܷ�Χ����
			QysdsBaseUtil.qysdsCheckAll(conn,checkBean, czzssdsjbSbbVo);
			// �����ҵ�Ǽǻ�����Ϣ
			Djjbsj djsj= ServiceManager.getInstance().getDjjbsjService().query(conn, czzssdsjbSbbVo.getJsjdm());			
			Timestamp skssksrq = DateUtils.getDateTime(czzssdsjbSbbVo.getSkssksrq());
			Timestamp kydjrq=djsj.getKydjrq();
			if(skssksrq.compareTo(kydjrq)<0){
				czzssdsjbSbbVo.setIsXky("Y");
	        }else{
	        	czzssdsjbSbbVo.setIsXky("N");
	        }	
			new CzzsjbSbbHelper().save(czzssdsjbSbbVo);			
			CzzsjbSbbUtil.initCodeTable(czzssdsjbSbbVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1008.Message);
			return returnBuff.append(this.ConvertVoToXml(czzssdsjbSbbVo));
		}finally{
			Tools.closeConnection(conn);
		}
		this.setRtnBizCode(RtnCodeMessage.Success.Code);
		this.setRtnBizMessage("����ɹ���");
		return returnBuff.append(this.ConvertVoToXml(czzssdsjbSbbVo));
	}
}
