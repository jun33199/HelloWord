package com.lscdz.qysds.application.jmsba2014.main.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.Jmsba2014Contant;
import com.lscdz.qysds.application.jmsba2014.base.handler.JmsbaBaseHandler;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaZlqdVo;
import com.lscdz.qysds.application.jmsba2014.main.access.JmsbaMainAccessExt;
import com.lscdz.qysds.application.jmsba2014.main.helper.JmsbaNbMainHelper;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaBasxVo;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaSqlx;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaSxdm;
import com.lscdz.qysds.application.jmsba2014.main.vo.inner.JmsbaZgswjg;
import com.lscdz.qysds.application.jmsba2014.main.vo.request.JmsbaAddBasxReq;
import com.lscdz.qysds.application.jmsba2014.main.vo.request.JmsbaDeleteBasxReq;
import com.lscdz.qysds.application.jmsba2014.main.vo.request.JmsbaNbQueryMainReq;
import com.lscdz.qysds.application.jmsba2014.main.vo.request.JmsbaRollbackBasxReq;
import com.lscdz.qysds.application.jmsba2014.main.vo.response.JmsbaAddBasxRes;
import com.lscdz.qysds.application.jmsba2014.main.vo.response.JmsbaDeleteBasxRes;
import com.lscdz.qysds.application.jmsba2014.main.vo.response.JmsbaInitRes;
import com.lscdz.qysds.application.jmsba2014.main.vo.response.JmsbaNbQueryMainRes;
import com.lscdz.qysds.application.jmsba2014.main.vo.response.JmsbaRollbackBasxRes;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.sf_dm_jmbasxdm;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.util.ActionHandler;
import com.lscdz.util.ClientMessage;
import com.lscdz.util.MyLogger;
import com.lscdz.util.SimplePageUtil;
import com.lscdz.util.StringUtil;
import com.lscdz.util.codetable.CodeTableManager;

public class JmsbaNbMainHandler extends ActionHandler {
	MyLogger myLogger =new MyLogger(JmsbaNbMainHandler.class);
	JmsbaNbMainHelper jmsbaMainHelper=new JmsbaNbMainHelper();
	//��ǰ��¼�û���Ϣ
	private Yh userData=null;
	@Override
	public StringBuffer processMsg(ClientMessage msg) throws FrameException {
		try {
			userData=this.getUserData(msg);
		} catch (FrameException e) {
			e.printStackTrace();
			this.rtnBizCode=RtnCodeMessage.Error_1010.Code;
			this.rtnBizMessage=RtnCodeMessage.Error_1010.Message;
			return new StringBuffer(this.ConvertVoToXml(null));
		}
		//��ʼ������˰����������
		if(msg.getAction().equalsIgnoreCase("doShow")){
			return doShow(msg);
		}
		//��ѯ����˰�����б�
		if(msg.getAction().equalsIgnoreCase("doQuery")){
			return doQuery(msg);
		}
		//��������˰��������
		if(msg.getAction().equalsIgnoreCase("doAdd")){
			return doAdd(msg);
		}
		
		//��ʼ�����������������
		if(msg.getAction().equalsIgnoreCase("doInitBasx")){
			return doBasxInit(msg);
		}
		//�������˰��������
		if(msg.getAction().equalsIgnoreCase("doSaveBasx")){
			return doSaveBasx(msg);
		}
		//�鿴
		if(msg.getAction().equalsIgnoreCase("doViewBasx")){
			return doViewBasx(msg);
		}
		
		//��������
		if(msg.getAction().equalsIgnoreCase("doAcceptBasx")){
			return doAcceptBasx(msg);
		}
		//�ܾ�����
		if(msg.getAction().equalsIgnoreCase("doRejectBasx")){
			return doRejectBasx(msg);
		}
		
		//��������
		if(msg.getAction().equalsIgnoreCase("doCancellationBasx")){
			return doCancellationBasx(msg);
		}
		
		//�������
		if(msg.getAction().equalsIgnoreCase("doModifyBasx")){
			return doModifyBasx(msg);
		}
		
		//���� ���ڱ���Ϊ�ύ�Ľ���ɾ������
		if(msg.getAction().equalsIgnoreCase("doDelete")){
			return doDelete(msg);
		}
			
		//���� �����ύΪ��˵Ľ��г���
		if(msg.getAction().equalsIgnoreCase("doRollback")){
			return doRollback(msg);
		}

		throw new FrameException("�ݲ�֧�ֵ�Action����" + msg.getAction());
	}
	
	/**
	 * ��ʼ����ȡ������������
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private StringBuffer doShow(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		try {
			JmsbaInitRes resVo=new JmsbaInitRes();
			
			//����˰��������
			List<sf_dm_jmbasxdm> basxdmList=new ArrayList<sf_dm_jmbasxdm>();
			List<JmsbaSxdm> jmsBasxdmList=new ArrayList<JmsbaSxdm>();
			basxdmList=CodeTableManager.getCodeTableList(CodeTableKey.SF_DM_JMBASXDM);
			for (sf_dm_jmbasxdm sf_dm_jmbasxdm : basxdmList) {
				JmsbaSxdm vo=new JmsbaSxdm();
				vo.setJmbasxDm(sf_dm_jmbasxdm.getJmbasxdm());
				vo.setJmbasxMc(sf_dm_jmbasxdm.getJmbasxmc());
				vo.setBalxdm(sf_dm_jmbasxdm.getBalxdm());
				vo.setJmlbdm(sf_dm_jmbasxdm.getJmlbdm());
				jmsBasxdmList.add(vo);
			}
			resVo.setJmsbaSxdmList(jmsBasxdmList);
			
			//����˰�������
			SimpleDateFormat sf=new SimpleDateFormat("yyyy");
			int band = Integer.parseInt(sf.format(new Date()))-1;
			resVo.setAdd_band(String.valueOf(band));
			//����˰������������
			List<JmsbaSqlx> jmsbaSqlxList = new ArrayList<JmsbaSqlx>();
			JmsbaSqlx wsVo=new JmsbaSqlx();
			wsVo.setJmsbaSqlxDm(QysdsNbConstant.QYSDS_SQLX_WS_CODE);
			wsVo.setJmsbaSqlxMc(QysdsNbConstant.QYSDS_SQLX_WS_NAME);
			jmsbaSqlxList.add(wsVo);
			JmsbaSqlx smVo=new JmsbaSqlx();
			smVo.setJmsbaSqlxDm(QysdsNbConstant.QYSDS_SQLX_SM_CODE);
			smVo.setJmsbaSqlxMc(QysdsNbConstant.QYSDS_SQLX_SM_NAME);
			jmsbaSqlxList.add(smVo);
			resVo.setJmsbaSqlxList(jmsbaSqlxList);
			//��ȡ����˰������б�
			List<JmsbaZgswjg> zgswjgList = new ArrayList<JmsbaZgswjg>();
			zgswjgList=JmsbaMainAccessExt.getZgswjgList(userData);
			resVo.setJmsbaZgswjgList(zgswjgList);
			returnBuff.append(this.ConvertVoToXml(resVo));
		} catch (Exception e) {
			e.printStackTrace();
			this.rtnBizCode=RtnCodeMessage.Error_9999.Code;
			this.rtnBizMessage="��ʼ����ȡ�����쳣";
			return new StringBuffer(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * ������������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doAdd(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		JmsbaAddBasxReq voReq=new JmsbaAddBasxReq();
		JmsbaAddBasxRes voRes=new JmsbaAddBasxRes();
		this.ConvertXmlToVo(msg, voReq);
		JmsbaBaseVo baseVo=voReq.getJmsbaBaseData();
		Connection conn = null;
		try {
			conn=ResourceManager.getConnection();
			Djjbsj nsrjbsj=ServiceManager.getInstance().getDjjbsjService().query(conn, baseVo.getJsjdm());
			if(nsrjbsj!=null && !StringUtil.isEmpty(nsrjbsj.getJsjdm())){
				
				baseVo.setNsrmc(nsrjbsj.getNsrmc());
				baseVo.setNsrsbh(nsrjbsj.getSwdjzh());
				baseVo.setNsrzt(nsrjbsj.getNsrzt());
				baseVo.setLxrdh(nsrjbsj.getJydzlxdm());
				baseVo.setCzlx(Jmsba2014Contant.QYSDS_JMSBAJL_CZLX_ADD);
				baseVo.setSwjgzzjgdm(nsrjbsj.getSwjgzzjgdm());
				//��ȡ���������嵥
				List<JmsbaZlqdVo> zlqdList = new ArrayList<JmsbaZlqdVo>();
				zlqdList=JmsbaMainAccessExt.getAddJmsbaZlqdList(conn, baseVo);
				baseVo.setZlqdList(zlqdList);
				voRes.setJmsbaBaseData(baseVo);
			}else{
				this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
				this.setRtnBizMessage("��ȡ��˰��("+baseVo.getJsjdm()+")������Ϣ����!");
				returnBuff.append(ConvertVoToXml(null));
				return returnBuff;
			}
		} catch (FrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {conn.close();} catch (SQLException e) {}
			}
		}
		
		returnBuff.append(ConvertVoToXml(voRes));
		return returnBuff;
	}
	/**
	 * ��ѯ����˰��������
	 * @param msg
	 * @return
	 * @throws FrameException
	 */
	private StringBuffer doQuery(ClientMessage msg)  throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		
		JmsbaNbQueryMainReq voReq=new JmsbaNbQueryMainReq();
		this.ConvertXmlToVo(msg, voReq);
		StringBuffer sqlWhere=jmsbaMainHelper.doQuery(voReq, userData);
		
		int countPage = JmsbaMainAccessExt.getCountPage(SimplePageUtil.CountPageSql(sqlWhere.toString()));
		List<JmsbaBasxVo> jmsbaBasxList = new ArrayList<JmsbaBasxVo>();
		jmsbaBasxList = JmsbaMainAccessExt.doQueryJmsBasxList(SimplePageUtil.SimplePageSql(sqlWhere.toString(),voReq.getPageSize(), voReq.getCurPage()));
		JmsbaNbQueryMainRes voRes = null;
		if (jmsbaBasxList != null && jmsbaBasxList.size() > 0) {
			voRes = new JmsbaNbQueryMainRes();
			voRes.setJmsbaBasxList(jmsbaBasxList);
			/*--���ط�ҳ��Ϣ--*/
			int totalPage = (countPage + voReq.getPageSize() - 1)/ voReq.getPageSize();
			voRes.setTotalPage(totalPage);// ��ҳ��
			voRes.setTotalCount(countPage);// �ܼ�¼��
			// ��ǰ̨�������ĵ�ǰҳ���أ���һҳ����һҳ����ǰ̨���м�1�ͼ�1����
			voRes.setCurPage(voReq.getCurPage());// ��ǰҳ��
			// ��ǰ̨��������ÿҳ��С����ǰ̨
			voRes.setPageSize(voReq.getPageSize());// ҳ���С

		} else {
			this.setRtnBizCode(RtnCodeMessage.Error_1001.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1001.Message);
		}
		returnBuff.append(this.ConvertVoToXml(voRes));
		return returnBuff;
	}
	
	/**
	 * ͨ�������ȡÿ�����������ʼ������ 
	 * @param msg
	 * @return
	 */
	private StringBuffer doBasxInit(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		String basxHandler = this.getValueByTag(msg.getDoc(), "BasxHandler");
		try {
			JmsbaBaseHandler baseHandler=((JmsbaBaseHandler) Class.forName(basxHandler).newInstance());
			returnBuff= baseHandler.doInit(msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * ͨ�����䱣�汸����������
	 * @param msg
	 * @return
	 */
	private StringBuffer doSaveBasx(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		String basxHandler = this.getValueByTag(msg.getDoc(), "BasxHandler");
		try {			
			JmsbaBaseHandler baseHandler=((JmsbaBaseHandler) Class.forName(basxHandler).newInstance());
			baseHandler.userData=this.userData;
			returnBuff= baseHandler.doSave(msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	
	/**
	 * ͨ������鿴�ѱ���ı���������Ϣ
	 * @param msg
	 * @return
	 */
	private StringBuffer doViewBasx(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		String basxHandler = this.getValueByTag(msg.getDoc(), "BasxHandler");
		try {
			JmsbaBaseHandler baseHandler=((JmsbaBaseHandler) Class.forName(basxHandler).newInstance());
			baseHandler.userData=this.userData;
			returnBuff= baseHandler.doView(msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * ��������
	 * @param msg
	 * @return
	 */
	private StringBuffer doAcceptBasx(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		String basxHandler = this.getValueByTag(msg.getDoc(), "BasxHandler");
		try {
			JmsbaBaseHandler baseHandler=((JmsbaBaseHandler) Class.forName(basxHandler).newInstance());
			baseHandler.userData=this.userData;
			returnBuff= baseHandler.doAccept(msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * �ܾ�����
	 * @param msg
	 * @return
	 */
	private StringBuffer doRejectBasx(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		String basxHandler = this.getValueByTag(msg.getDoc(), "BasxHandler");
		try {
			JmsbaBaseHandler baseHandler=((JmsbaBaseHandler) Class.forName(basxHandler).newInstance());
			baseHandler.userData=this.userData;
			returnBuff= baseHandler.doReject(msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * ��������
	 * @param msg
	 * @return
	 */
	private StringBuffer doCancellationBasx(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		String basxHandler = this.getValueByTag(msg.getDoc(), "BasxHandler");
		try {
			JmsbaBaseHandler baseHandler=((JmsbaBaseHandler) Class.forName(basxHandler).newInstance());
			baseHandler.userData=this.userData;
			returnBuff= baseHandler.doCancellation(msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * �������
	 * @param msg
	 * @return
	 */
	private StringBuffer doModifyBasx(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		String basxHandler = this.getValueByTag(msg.getDoc(), "BasxHandler");
		try {
			JmsbaBaseHandler baseHandler=((JmsbaBaseHandler) Class.forName(basxHandler).newInstance());
			baseHandler.userData=this.userData;
			returnBuff= baseHandler.doModify(msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	
	/**
	 * ɾ�� ���������ѱ���δ�ύ�Ŀɽ���ɾ������
	 * @param msg
	 * @return
	 */
	private StringBuffer doDelete(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		
		JmsbaDeleteBasxReq deleteReq=new JmsbaDeleteBasxReq();
		this.ConvertXmlToVo(msg, deleteReq);
		
		//ɾ������
		JmsbaDeleteBasxRes deleteRes=new JmsbaDeleteBasxRes();
		returnBuff.append(this.ConvertVoToXml(deleteRes));
		
		return returnBuff;
	}
	
	/**
	 * ���� �����������ύΪ��˵Ŀɽ��г���
	 * @param msg
	 * @return
	 */
	private StringBuffer doRollback(ClientMessage msg) throws FrameException{
		StringBuffer returnBuff = new StringBuffer();
		if (null == msg.getDoc()) {
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(ConvertVoToXml(null));
			return returnBuff;
		}
		
		JmsbaRollbackBasxReq rollbackReq=new JmsbaRollbackBasxReq();
		this.ConvertXmlToVo(msg, rollbackReq);
		
		//���ز���
		JmsbaRollbackBasxRes rollback=new JmsbaRollbackBasxRes();
		returnBuff.append(this.ConvertVoToXml(rollback));
		
		return returnBuff;
	}
}
