package com.lscdz.qysds.application.jmsba2014.basx0010.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.qysds.application.jmsba2014.base.access.JmsbaBaseAccessExt;
import com.lscdz.qysds.application.jmsba2014.base.handler.JmsbaBaseHandler;
import com.lscdz.qysds.application.jmsba2014.basx0010.access.Basx0010AccessExt;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.inner.Basx0010Vo;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010AcceptReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010CancellationReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010ModifyReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010RejectReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010SaveReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.request.Basx0010ViewReq;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010AcceptRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010CancellationRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010InitRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010ModifyRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010RejectRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010SaveRes;
import com.lscdz.qysds.application.jmsba2014.basx0010.vo.response.Basx0010ViewRes;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.sf_dm_zyzhlyzl;
import com.lscdz.util.ClientMessage;
import com.lscdz.util.MyLogger;
import com.lscdz.util.codetable.CodeTableManager;

public class Basx0010Handler extends JmsbaBaseHandler {
	MyLogger myLogger =new MyLogger(Basx0010Handler.class);
	//当前登录用户信息
	@Override
	public StringBuffer processMsg(ClientMessage msg) throws FrameException {
		if(msg.getAction().equalsIgnoreCase("doInit")){
			return doInit(msg);
		}
		if(msg.getAction().equalsIgnoreCase("doSave")){
			return doSave(msg);
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
		throw new FrameException("暂不支持的Action方法" + msg.getAction());
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
		Connection conn=null;
		boolean jmsjlSaved=false;
		boolean basxSaved=false;
		try {

			Basx0010SaveReq saveVoReq=new Basx0010SaveReq();
			this.ConvertXmlToVo(msg, saveVoReq);
			Basx0010SaveRes saveVoRes=new Basx0010SaveRes();
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			//减免税主表和资料清单保存
			jmsjlSaved=new JmsbaBaseAccessExt().doSave(conn,saveVoReq.getJmsbaBaseData(), userData);
			//备案事项信息保存
			basxSaved=new Basx0010AccessExt().doSave(conn,saveVoReq, userData);
			if(!(jmsjlSaved==true&&basxSaved==true)){
				this.setRtnBizCode(RtnCodeMessage.Error_1008.Code);
				this.setRtnBizMessage(RtnCodeMessage.Error_1008.Message);
			}
			conn.commit();	
			conn.setAutoCommit(true);
			returnBuff.append(this.ConvertVoToXml(saveVoRes));
		}catch (FrameException e) {
			e.printStackTrace();			
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		} catch (Exception e) {
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
		
		Basx0010ViewRes viewRes=new Basx0010ViewRes();
		viewRes.setJmsbaBaseData(viewReq.getJmsbaBaseData());
		Basx0010Vo vo=new Basx0010Vo();
		vo.setWh("24234234234");
		viewRes.setBasx0010Data(vo);
		
		returnBuff.append(this.ConvertVoToXml(viewRes));
		return returnBuff;
	}


	/**
	 * 接受申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doAccept(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010AcceptReq acceptReq=new Basx0010AcceptReq();
		this.ConvertXmlToVo(msg, acceptReq);
		
		Basx0010AcceptRes acceptRes=new Basx0010AcceptRes();
		//执行更新接受申请标识
		
		
		returnBuff.append(this.ConvertVoToXml(acceptRes));
		return returnBuff;
	}


	/**
	 * 拒绝申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doReject(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010RejectReq rejectReq=new Basx0010RejectReq();
		this.ConvertXmlToVo(msg, rejectReq);
		
		Basx0010RejectRes rejectRes=new Basx0010RejectRes();
		//执行更新拒绝申请标识
		
		
		returnBuff.append(this.ConvertVoToXml(rejectRes));
		return returnBuff;
	}


	/**
	 * 作废申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doCancellation(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010CancellationReq cancellationReq=new Basx0010CancellationReq();
		this.ConvertXmlToVo(msg, cancellationReq);
		
		Basx0010CancellationRes cancellationRes=new Basx0010CancellationRes();
		//执行作废 并判断是否可以作废
		
		
		returnBuff.append(this.ConvertVoToXml(cancellationRes));
		return returnBuff;
	}


	/**
	 * 变更申请
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	public StringBuffer doModify(ClientMessage msg) throws FrameException {
		StringBuffer returnBuff = new StringBuffer();
		Basx0010ModifyReq modifyReq=new Basx0010ModifyReq();
		this.ConvertXmlToVo(msg, modifyReq);
		
		Basx0010ModifyRes modifyRes=new Basx0010ModifyRes();
		//执行变更 并判断是否可以变更
		
		
		returnBuff.append(this.ConvertVoToXml(modifyRes));
		return returnBuff;
	}
	
}
