package com.lscdz.qysds.application.qysdsjb2014.hdzsjb.handler;

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
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.util.CzzsjbSbbUtil;
import com.lscdz.qysds.application.qysdsjb2014.hdzsjb.helper.HdzsjbHelper;
import com.lscdz.qysds.application.qysdsjb2014.hdzsjb.util.HdzsjbUtil;
import com.lscdz.qysds.application.qysdsjb2014.hdzsjb.vo.HdzsjbVo;
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
public class QysdsHdzsjbHandler extends QysdsjbBaseHandler{

	/**
	 * ��ʼ������������
	 * @param msg
	 * @return
	 * @throws FrameException 
	 */
	@Override
	public StringBuffer doInit(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		HdzsjbVo hdzsjbVo=new HdzsjbVo();
		try {
			this.ConvertXmlToVo(msg, hdzsjbVo);
			// �õ���ǰʱ���������
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			Timestamp curTime = new Timestamp(System.currentTimeMillis());
			Map getsbjd = new CzzsjbSbbUtil().quarterSkssrq(curTime);
			Timestamp skssksrq = (Timestamp) getsbjd.get(QysdsJb2014Contant.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) getsbjd.get(QysdsJb2014Contant.SKSSJSRQ);
			hdzsjbVo.setSbrq(DateUtils.getDate(curTime));
			hdzsjbVo.setSkssksrq(DateUtils.getDate(skssksrq));
			hdzsjbVo.setSkssjsrq(DateUtils.getDate(skssjsrq)); 
			HdzsjbUtil.initCodeTable(hdzsjbVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage("��ʼ������");
			return returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff.append(this.ConvertVoToXml(hdzsjbVo));
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
		HdzsjbVo hdzsjbVo=new HdzsjbVo();
		try {			
			this.ConvertXmlToVo(msg, hdzsjbVo);			
			conn=ResourceManager.getConnection();
			// ���ñ���������
			hdzsjbVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// ����¼��������
			hdzsjbVo.setLrr(userData.getYhid());		
			CheckBean checkBean = new CheckBean();	
			//���������ڡ������ֱܷ��������ܷ�Χ����
			QysdsBaseUtil.qysdsCheckAll(conn,checkBean, hdzsjbVo);
			if("".equals(checkBean.getJdlx())){
				this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
				this.setRtnBizMessage("����ҵ������ҵ����˰Ӧ������");
				return returnBuff.append(this.ConvertVoToXml(null));				
			}
			new HdzsjbHelper().query(hdzsjbVo);
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
			this.setRtnBizMessage(ex.getMessage());
			return returnBuff.append(this.ConvertVoToXml(null));
		}finally{
			Tools.closeConnection(conn);
		}
		return returnBuff.append(this.ConvertVoToXml(hdzsjbVo));
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
		HdzsjbVo hdzsjbVo=new HdzsjbVo();
		try {			
			this.ConvertXmlToVo(msg, hdzsjbVo);			
			conn=ResourceManager.getConnection();
			// ����¼��������
			hdzsjbVo.setLrr(userData.getYhid());		
			new HdzsjbHelper().delete(hdzsjbVo);
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
		HdzsjbVo hdzsjbVo=new HdzsjbVo();
		try {			
			this.ConvertXmlToVo(msg, hdzsjbVo);			
			conn=ResourceManager.getConnection();
			// ���ñ���������
			hdzsjbVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			// ����¼��������
			hdzsjbVo.setLrr(userData.getYhid());		
			CheckBean checkBean = new CheckBean();	
			//���������ڡ������ֱܷ��������ܷ�Χ����
			QysdsBaseUtil.qysdsCheckAll(conn,checkBean, hdzsjbVo);
			// �����ҵ�Ǽǻ�����Ϣ
			Djjbsj djsj= ServiceManager.getInstance().getDjjbsjService().query(conn, hdzsjbVo.getJsjdm());			
			Timestamp skssksrq = DateUtils.getDateTime(hdzsjbVo.getSkssksrq());
			Timestamp kydjrq=djsj.getKydjrq();
			if(skssksrq.compareTo(kydjrq)<0){
				hdzsjbVo.setIsXky("Y");
	        }else{
	        	hdzsjbVo.setIsXky("N");
	        }	
			new HdzsjbHelper().save(hdzsjbVo);			
		}catch (Exception ex) {
			ex.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1008.Message);
			return returnBuff.append(this.ConvertVoToXml(hdzsjbVo));
		}finally{
			Tools.closeConnection(conn);
		}
		this.setRtnBizCode(RtnCodeMessage.Success.Code);
		this.setRtnBizMessage("����ɹ���");
		return returnBuff.append(this.ConvertVoToXml(hdzsjbVo));
	}
}
