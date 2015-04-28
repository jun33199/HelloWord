package com.lscdz.qysds.application.jmsba2014.basx0010.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.Tools;

import com.lscdz.qysds.application.jmsba2014.Jmsba2014Contant;
import com.lscdz.qysds.application.jmsba2014.base.handler.JmsbaBaseHandler;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010AcceptReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010AuditRejectReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010AuditReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010CancellationReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010CommitReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010ModifyContentReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010ModifyReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010RejectReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010SaveReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010ViewReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010AcceptRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010AuditRejectRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010AuditRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010CancellationRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010CommitRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010InitRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010ModifyContentRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010ModifyRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010RejectRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010SaveRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010ViewRes;
import com.lscdz.qysds.application.jmsba2014.main.vo.request.JmsbaDeleteBasxReq;
import com.lscdz.qysds.application.jmsba2014.main.vo.response.JmsbaDeleteBasxRes;
import com.lscdz.qysds.application.jmsba2014.util.QysdsJmsbaUtil;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.sf_dm_zyzhlyzl;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.util.ClientMessage;
import com.lscdz.util.MyLogger;
import com.lscdz.util.StringUtil;
import com.lscdz.util.codetable.CodeTableManager;

public class Basx0010Handler extends JmsbaBaseHandler {
	MyLogger myLogger =new MyLogger(Basx0010Handler.class);
	//��ǰ��¼�û���Ϣ
	@Override
	public StringBuffer processMsg(ClientMessage msg) throws FrameException {
		if(msg.getAction().equalsIgnoreCase("doInit")){
			return doInit(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doSave")){
			return doSave(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doCommit")){
			return doCommit(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doView")){
			return doView(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doAccept")){
			return doAccept(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doReject")){
			return doReject(msg);
		}	
		if(msg.getAction().equalsIgnoreCase("doCancellation")){
			return doCancellation(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doModify")){
			return doModify(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doDelete")){
			return doDelete(msg);
		}
		throw new FrameException("�ݲ�֧�ֵ�Action����" + msg.getAction());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public StringBuffer doInit(ClientMessage msg) throws FrameException {
		StringBuffer retStrBuf=new StringBuffer();
		List<sf_dm_zyzhlyzl> zyzhlyzlList=new ArrayList<sf_dm_zyzhlyzl>();
		zyzhlyzlList=CodeTableManager.getCodeTableList(CodeTableKey.SF_DM_ZYZHLYZL);
		Basx0010InitRes voRes=new Basx0010InitRes();
		voRes.setZyzhlyzlList(zyzhlyzlList);
		retStrBuf.append(this.ConvertVoToXml(voRes));
		return retStrBuf;
	}

	@Override
	public StringBuffer doSave(ClientMessage msg)throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010SaveReq saveVoReq=new Basx0010SaveReq();
		this.ConvertXmlToVo(msg, saveVoReq);
		Connection conn=null;
		boolean jmsjlSaved=false;
		Basx0010SaveRes saveVoRes=new Basx0010SaveRes();
		try {
			String basqWshStr=saveVoReq.getJmsbaBaseData().getBasqwsh();
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			if("EXTERIOR".equals(QysdsNbConstant.deploy_environment)){
				saveVoReq.getJmsbaBaseData().setSqzt(Jmsba2014Contant.QYSDS_JMSBA_SQZT_BCWTJ_CODE);
			}else{
				saveVoReq.getJmsbaBaseData().setSqzt(Jmsba2014Contant.QYSDS_JMSBA_SQZT_TJWSH_CODE);
			}
			JmsbaBaseVo jmsbaBaseVo=baseAccessExt.doQueryJmsbaxx(conn, basqWshStr);
			if(jmsbaBaseVo!=null && jmsbaBaseVo.getJsjdm()!=null && jmsbaBaseVo.getJsjdm().length()>0){
				
				jmsjlSaved=baseAccessExt.doUpdateWs(conn,saveVoReq.getJmsbaBaseData(), userData);
			}else{
				saveVoReq.getJmsbaBaseData().setCjr(userData.getYhid());
				saveVoReq.getJmsbaBaseData().setCjrq(FrameCommonAccess.getDBDate());
				saveVoReq.getJmsbaBaseData().setLrr(userData.getYhid());
				saveVoReq.getJmsbaBaseData().setLrrq(FrameCommonAccess.getDBDate());
				saveVoReq.getJmsbaBaseData().setTjr(userData.getYhid());
				saveVoReq.getJmsbaBaseData().setTjsj(FrameCommonAccess.getDBDate());
				saveVoReq.getJmsbaBaseData().setShr(userData.getYhid());
				saveVoReq.getJmsbaBaseData().setShsj(FrameCommonAccess.getDBDate());
				//�������룬ֱ�ӽ��б������
				Map basqWsh=QysdsJmsbaUtil.getBasqbh(userData.getSsdwdm(), saveVoReq.getJmsbaBaseData().getBand(), saveVoReq.getJmsbaBaseData().getJsjdm(), saveVoReq.getJmsbaBaseData().getJmbasxdm(), conn);
				//�������˰��������
				saveVoReq.getJmsbaBaseData().setBasqbh(basqWsh.get("basqbh").toString());
				saveVoReq.getJmsbaBaseData().setBasqwsh(basqWsh.get("basqwsh").toString());
				//����˰����������嵥����
				jmsjlSaved=baseAccessExt.doSaveWs(conn,saveVoReq.getJmsbaBaseData(), userData);
				if(!jmsjlSaved){
					this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
					this.setRtnBizMessage(RtnCodeMessage.Error_1008.Message);
					returnBuff.append(this.ConvertVoToXml(null));
					return returnBuff;
				}
			}
			conn.commit();	
			conn.setAutoCommit(true);
			saveVoRes.setBasqwsh(saveVoReq.getJmsbaBaseData().getBasqwsh());
			returnBuff.append(this.ConvertVoToXml(saveVoRes));
		}catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();		
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return returnBuff;
	}
	
	@Override
	public StringBuffer doCommit(ClientMessage msg)throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010CommitReq commitVoReq=new Basx0010CommitReq();
		this.ConvertXmlToVo(msg, commitVoReq);
		Connection conn=null;
		boolean jmsjlSaved=false;
		Basx0010CommitRes commitVoRes=new Basx0010CommitRes();
		try {
			String basqWshStr=commitVoReq.getJmsbaBaseData().getBasqwsh();
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			JmsbaBaseVo jmsbaBaseVo=baseAccessExt.doQueryJmsbaxx(conn, basqWshStr);
			commitVoReq.getJmsbaBaseData().setSqzt(Jmsba2014Contant.QYSDS_JMSBA_SQZT_TJWSH_CODE);
			if(jmsbaBaseVo!=null && jmsbaBaseVo.getJsjdm()!=null && jmsbaBaseVo.getJsjdm().length()>0){
				jmsjlSaved=baseAccessExt.doUpdateWs(conn,commitVoReq.getJmsbaBaseData(), userData);
			}else{
				commitVoReq.getJmsbaBaseData().setCjr(userData.getYhid());
				commitVoReq.getJmsbaBaseData().setCjrq(FrameCommonAccess.getDBDate());
				commitVoReq.getJmsbaBaseData().setLrr(userData.getYhid());
				commitVoReq.getJmsbaBaseData().setLrrq(FrameCommonAccess.getDBDate());
				commitVoReq.getJmsbaBaseData().setTjr(userData.getYhid());
				commitVoReq.getJmsbaBaseData().setTjsj(FrameCommonAccess.getDBDate());
				commitVoReq.getJmsbaBaseData().setShr(userData.getYhid());
				commitVoReq.getJmsbaBaseData().setShsj(FrameCommonAccess.getDBDate());
				//�������룬ֱ�ӽ��б������
				Map basqWsh=QysdsJmsbaUtil.getBasqbh(userData.getSsdwdm(), commitVoReq.getJmsbaBaseData().getBand(), commitVoReq.getJmsbaBaseData().getJsjdm(), commitVoReq.getJmsbaBaseData().getJmbasxdm(), conn);
				//�������˰��������
				commitVoReq.getJmsbaBaseData().setBasqbh(basqWsh.get("basqbh").toString());
				commitVoReq.getJmsbaBaseData().setBasqwsh(basqWsh.get("basqwsh").toString());
				//����˰����������嵥����
				jmsjlSaved=baseAccessExt.doSaveWs(conn,commitVoReq.getJmsbaBaseData(), userData);
				if(!jmsjlSaved){
					this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
					this.setRtnBizMessage(RtnCodeMessage.Error_1008.Message);
					returnBuff.append(this.ConvertVoToXml(null));
					return returnBuff;
				}
			}
			conn.commit();	
			conn.setAutoCommit(true);
			commitVoRes.setBasqwsh(commitVoReq.getJmsbaBaseData().getBasqbh());
			returnBuff.append(this.ConvertVoToXml(commitVoRes));
		}catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();			
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return returnBuff;
	}
	
	@Override
	public StringBuffer doView(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010ViewReq viewReq=new Basx0010ViewReq();
		this.ConvertXmlToVo(msg, viewReq);
		Connection conn=null;
		try{
			conn=ResourceManager.getConnection();
			JmsbaBaseVo jmsbaBaseVo=baseAccessExt.doQueryJmsbaxx(conn, viewReq.getJmsbaBaseData().getBasqwsh());
			if(jmsbaBaseVo!=null && jmsbaBaseVo.getJsjdm()!=null){
				Djjbsj nsrjbsj=ServiceManager.getInstance().getDjjbsjService().query(conn, jmsbaBaseVo.getJsjdm());
				if(nsrjbsj!=null && !StringUtil.isEmpty(nsrjbsj.getJsjdm())){
					jmsbaBaseVo.setNsrmc(nsrjbsj.getNsrmc());
					jmsbaBaseVo.setNsrsbh(nsrjbsj.getSwdjzh());
					jmsbaBaseVo.setNsrzt(nsrjbsj.getNsrzt());
					jmsbaBaseVo.setLxrdh(nsrjbsj.getJydzlxdm());
					jmsbaBaseVo.setCzlx(Jmsba2014Contant.QYSDS_JMSBAJL_CZLX_ADD);
					jmsbaBaseVo.setSwjgzzjgdm(nsrjbsj.getSwjgzzjgdm());
				}else{
					this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
					this.setRtnBizMessage("��ȡ����������Ϣʧ��");
					returnBuff.append(ConvertVoToXml(null));
					return returnBuff;	
				}
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
				this.setRtnBizMessage("��ȡ����������Ϣʧ��");
				returnBuff.append(ConvertVoToXml(null));
				return returnBuff;	
			}
			Basx0010ViewRes viewRes=new Basx0010ViewRes();
			viewRes.setJmsbaBaseData(jmsbaBaseVo);
			returnBuff.append(this.ConvertVoToXml(viewRes));
		}
		catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			this.setRtnBizMessage("��ȡ����������Ϣʧ��");
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;	
		}finally{
			Tools.closeConnection(conn);
		}
		return returnBuff;
	}


	/**
	 * ��������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doAccept(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010AcceptReq acceptReq=new Basx0010AcceptReq();
		this.ConvertXmlToVo(msg, acceptReq);
		Basx0010AcceptRes acceptRes=new Basx0010AcceptRes();
		//�����ӿ�
		Connection conn=null;
		try{
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			String basqWshStr=acceptReq.getJmsbaBaseData().getBasqwsh();
			if(basqWshStr!=null && basqWshStr.length()>0){
				//����������룬�������
				JmsbaBaseVo jmsbaBaseVo=baseAccessExt.doQueryJmsbaxx(conn, basqWshStr);
				if(jmsbaBaseVo!=null && jmsbaBaseVo.getJsjdm()!=null && jmsbaBaseVo.getJsjdm().length()>0){
					//��鵱ǰ����˰���������Ƿ�Ϊ����δ��˻��ύδ���
					boolean sqzt = QysdsJmsbaUtil.checkSqzt(basqWshStr, conn);
					if (!sqzt) {
						this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
						this.setRtnBizMessage("�˼���˰���������ѱ���˰�˳��أ�");
						returnBuff.append(ConvertVoToXml(null));
						return returnBuff;
					}
					
					//��������״̬Ϊ���ͨ�� ���ũ���֡�������ҵ ��������״̬
					if(acceptReq.getJmsbaBaseData().getJmbasxdm().equals(Jmsba2014Contant.QYSDS_JMSBA_BASX_0120)){
						QysdsJmsbaUtil.updateSqzt(basqWshStr, Jmsba2014Contant.QYSDS_JMSBA_SQZT_TSZD_CODE, userData.getYhid(),conn);
					}else{
						QysdsJmsbaUtil.updateSqzt(basqWshStr, Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE, userData.getYhid(),conn);
					}
					
					//���������嵥״̬
					QysdsJmsbaUtil.updateZlqd(acceptReq.getJmsbaBaseData(),userData,conn);
				}else{
					this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
					this.setRtnBizMessage("δ�鵽�ü���˰�����������ݣ�");
					returnBuff.append(ConvertVoToXml(null));
					return returnBuff;
				}
			}else{
				acceptReq.getJmsbaBaseData().setCjr(userData.getYhid());
				acceptReq.getJmsbaBaseData().setCjrq(FrameCommonAccess.getDBDate());
				acceptReq.getJmsbaBaseData().setLrr(userData.getYhid());
				acceptReq.getJmsbaBaseData().setLrrq(FrameCommonAccess.getDBDate());
				acceptReq.getJmsbaBaseData().setTjr(userData.getYhid());
				acceptReq.getJmsbaBaseData().setTjsj(FrameCommonAccess.getDBDate());
				acceptReq.getJmsbaBaseData().setShr(userData.getYhid());
				acceptReq.getJmsbaBaseData().setShsj(FrameCommonAccess.getDBDate());
				//�������룬ֱ�ӽ��б������
				Map basqWsh=QysdsJmsbaUtil.getBasqbh(userData.getSsdwdm(), acceptReq.getJmsbaBaseData().getBand(), acceptReq.getJmsbaBaseData().getJsjdm(), acceptReq.getJmsbaBaseData().getJmbasxdm(), conn);
				//�������˰��������
				acceptReq.getJmsbaBaseData().setBasqbh(basqWsh.get("basqbh").toString());
				acceptReq.getJmsbaBaseData().setBasqwsh(basqWsh.get("basqwsh").toString());
				
				if(!baseAccessExt.doSave(conn, acceptReq.getJmsbaBaseData(), userData)){
					this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
					this.setRtnBizMessage("����˰��������ʧ�ܣ�");
					returnBuff.append(ConvertVoToXml(null));
					return returnBuff;
				}
			}
			
			conn.commit();
			conn.setAutoCommit(true);		
		}catch (FrameException ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			this.setRtnBizMessage(ex.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;	
		}catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			this.setRtnBizMessage(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;	
		}finally{
			Tools.closeConnection(conn);
		}
		
	
		returnBuff.append(this.ConvertVoToXml(acceptRes));
		return returnBuff;
	}


	/**
	 * �ܾ�����
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doReject(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010RejectReq rejectReq=new Basx0010RejectReq();
		this.ConvertXmlToVo(msg, rejectReq);
		
		Basx0010RejectRes rejectRes=new Basx0010RejectRes();
		//ִ�и��¾ܾ������ʶ
		//�����ӿ�
		Connection conn=null;
		try{
			conn=ResourceManager.getConnection();
			String basqWshStr=rejectReq.getJmsbaBaseData().getBasqwsh();
			if(basqWshStr!=null && basqWshStr.length()>0){
				//����������룬�������
				JmsbaBaseVo jmsbaBaseVo=baseAccessExt.doQueryJmsbaxx(conn, basqWshStr);
				if(jmsbaBaseVo!=null && jmsbaBaseVo.getJsjdm()!=null && jmsbaBaseVo.getJsjdm().length()>0){
					//��鵱ǰ����˰���������Ƿ�Ϊ����δ��˻��ύδ���
					boolean sqzt = QysdsJmsbaUtil.checkSqzt(basqWshStr, conn);
					if (!sqzt) {
						this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
						this.setRtnBizMessage("�˼���˰���������ѱ���˰�˳��أ�");
						returnBuff.append(ConvertVoToXml(null));
						return returnBuff;
					}
					
					//��������״̬Ϊ���ͨ��
					QysdsJmsbaUtil.updateSqzt(basqWshStr, Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHWTG_CODE, userData.getYhid(),conn);
					
					//���������嵥״̬
					QysdsJmsbaUtil.updateZlqd(rejectReq.getJmsbaBaseData(),userData,conn);
				}else{
					this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
					this.setRtnBizMessage("δ�鵽�ü���˰�����������ݣ�");
					returnBuff.append(ConvertVoToXml(null));
					return returnBuff;
				}
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
				this.setRtnBizMessage("�����ڸñ����������ݣ�");
				returnBuff.append(ConvertVoToXml(null));
				return returnBuff;
			}
		}catch (FrameException ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			this.setRtnBizMessage(ex.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;	
		}catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
			this.setRtnBizMessage(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;	
		}finally{
			Tools.closeConnection(conn);
		}
		
		returnBuff.append(this.ConvertVoToXml(rejectRes));
		return returnBuff;
	}


	/**
	 * ��������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doCancellation(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010CancellationReq cancellationReq=new Basx0010CancellationReq();
		this.ConvertXmlToVo(msg, cancellationReq);
		//�����ӿ�
		Connection conn=null;
		Basx0010CancellationRes cancellationRes=new Basx0010CancellationRes();
		//ִ������ ���ж��Ƿ��������
		
		try {
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			if(baseAccessExt.doDestory(conn, cancellationReq.getJmsbaBaseData(), userData)){
				returnBuff.append(ConvertVoToXml(cancellationRes));
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
				this.setRtnBizMessage("���ϼ���˰������Ŀ��"+cancellationReq.getJmsbaBaseData().getJmbasxmc()+"��ʧ�ܣ�");
				returnBuff.append(ConvertVoToXml(null));
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (FrameException ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(ex.getMessage());
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return returnBuff;
	}


	/**
	 * �������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doModify(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010ModifyReq modifyReq=new Basx0010ModifyReq();
		this.ConvertXmlToVo(msg, modifyReq);
		
		Basx0010ModifyRes modifyRes=new Basx0010ModifyRes();
		//�����ӿ�
		Connection conn=null;
		//ִ�б�� ���ж��Ƿ���Ա��
		try {
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			if(baseAccessExt.doModify(conn, modifyReq.getJmsbaBaseData(), userData)){
				returnBuff.append(ConvertVoToXml(modifyRes));
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
				this.setRtnBizMessage("����������˰������Ŀ��"+modifyReq.getJmsbaBaseData().getJmbasxmc()+"��ʧ�ܣ�");
				returnBuff.append(ConvertVoToXml(null));
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (FrameException ex) {		
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(ex.getMessage());
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return returnBuff;
	}


	/**
	 * ���������������
	 */
	public StringBuffer doModifyBasx(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010ModifyContentReq modifyContentReq=new Basx0010ModifyContentReq();
		this.ConvertXmlToVo(msg, modifyContentReq);
		
		Basx0010ModifyContentRes modifyContentRes=new Basx0010ModifyContentRes();
		//�����ӿ�
		Connection conn=null;
		//ִ�б�� ���ж��Ƿ���Ա��
		try {
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			modifyContentReq.getJmsbaBaseData().setSqzt(Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE);
			if(baseAccessExt.doUpdate(conn, modifyContentReq.getJmsbaBaseData(), userData)){
				returnBuff.append(ConvertVoToXml(modifyContentRes));
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
				this.setRtnBizMessage("����˰������Ŀ��"+modifyContentReq.getJmsbaBaseData().getJmbasxmc()+"�����ʧ�ܣ�");
				returnBuff.append(ConvertVoToXml(null));
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (FrameException ex) {	
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(ex.getMessage());
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return returnBuff;
	}
	
	
	/**
	 * �����û�ɾ��
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doDelete(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
//		JmsbaDeleteBasxReq deleteReq=new JmsbaDeleteBasxReq();
//		this.ConvertXmlToVo(msg, deleteReq);
//		//�����ӿ�
//		Connection conn=null;
//		JmsbaDeleteBasxRes deleteRes=new JmsbaDeleteBasxRes();
//		try {
//			conn=ResourceManager.getConnection();
//			conn.setAutoCommit(false);
//			baseAccessExt.deleteJmsbaZb(conn, deleteReq.getJmsbaBaseData().getBasqwsh());
//			returnBuff.append(ConvertVoToXml(deleteRes));
//			conn.commit();
//			conn.setAutoCommit(true);
//		} catch (FrameException ex) {		
//			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
//			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
//			this.setRtnBizMessage(ex.getMessage());
//			returnBuff.append(ConvertVoToXml(null));
//			return returnBuff;
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
//			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
//			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
//			returnBuff.append(ConvertVoToXml(null));
//			return returnBuff;
//		}finally{
//			if(conn!=null){
//				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
//			}
//		}
		return returnBuff;
	}


	@Override
	public StringBuffer doAuditBasx(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010AuditReq auditReq=new Basx0010AuditReq();
		this.ConvertXmlToVo(msg, auditReq);
		
		Basx0010AuditRes auditRes=new Basx0010AuditRes();
		//�����ӿ�
		Connection conn=null;
		//ִ�б�� ���ж��Ƿ���Ա��
		try {
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			if(baseAccessExt.doAudit(conn, auditReq.getJmsbaBaseData(), userData)){
				returnBuff.append(ConvertVoToXml(auditRes));
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
				this.setRtnBizMessage("����������˰������Ŀ��"+auditReq.getJmsbaBaseData().getJmbasxmc()+"��ʧ�ܣ�");
				returnBuff.append(ConvertVoToXml(null));
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (FrameException ex) {	
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(ex.getMessage());
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return returnBuff;
	}


	@Override
	public StringBuffer doAuditRejectBasx(ClientMessage msg)
			throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010AuditRejectReq auditRejectReq=new Basx0010AuditRejectReq();
		this.ConvertXmlToVo(msg, auditRejectReq);
		
		Basx0010AuditRejectRes auditRejectRes=new Basx0010AuditRejectRes();
		//�����ӿ�
		Connection conn=null;
		//ִ�б�� ���ж��Ƿ���Ա��
		try {
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			if(baseAccessExt.doAuditReject(conn, auditRejectReq.getJmsbaBaseData(), userData)){
				returnBuff.append(ConvertVoToXml(auditRejectRes));
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
				this.setRtnBizMessage("����������˰������Ŀ��"+auditRejectReq.getJmsbaBaseData().getJmbasxmc()+"��ʧ�ܣ�");
				returnBuff.append(ConvertVoToXml(null));
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (FrameException ex) {		
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(ex.getMessage());
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return returnBuff;
	}
	
}
