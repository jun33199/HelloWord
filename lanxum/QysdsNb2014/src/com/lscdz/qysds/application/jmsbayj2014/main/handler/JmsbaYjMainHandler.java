package com.lscdz.qysds.application.jmsbayj2014.main.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsbayj2014.Jmsba2014Contant;
import com.lscdz.qysds.application.jmsbayj2014.base.handler.JmsbaBaseHandler;
import com.lscdz.qysds.application.jmsbayj2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsbayj2014.base.vo.JmsbaZlqdVo;
import com.lscdz.qysds.application.jmsbayj2014.main.access.JmsbaMainAccessExt;
import com.lscdz.qysds.application.jmsbayj2014.main.helper.JmsbaNbMainHelper;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.inner.JmsbaBasxVo;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.inner.JmsbaSqlx;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.inner.JmsbaSxdm;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.inner.JmsbaZgswjg;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.request.JmsbaAddBasxReq;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.request.JmsbaDeleteBasxReq;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.request.JmsbaNbQueryMainReq;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.request.JmsbaRollbackBasxReq;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.response.JmsbaAddBasxRes;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.response.JmsbaDeleteBasxRes;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.response.JmsbaInitRes;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.response.JmsbaNbQueryMainRes;
import com.lscdz.qysds.application.jmsbayj2014.main.vo.response.JmsbaRollbackBasxRes;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.sf_dm_jmbasxdm;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.util.SbzlAccess;
import com.lscdz.qysds.common.util.Skssrq;
import com.lscdz.qysds.common.vo.sf_jl_qysdsjmsbajl_2014;
import com.lscdz.util.ActionHandler;
import com.lscdz.util.ClientMessage;
import com.lscdz.util.MyLogger;
import com.lscdz.util.SimplePageUtil;
import com.lscdz.util.StringUtil;
import com.lscdz.util.codetable.CodeTableManager;

public class JmsbaYjMainHandler extends ActionHandler {
	MyLogger myLogger =new MyLogger(JmsbaYjMainHandler.class);
	JmsbaNbMainHelper jmsbaMainHelper=new JmsbaNbMainHelper();
	private  final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
	//��ǰ��¼�û���Ϣ
	private Yh userData=null;
	private String deploy_environment=ResourceManager.getTokenByName("DEPLOY_ENVIRONMENT");
	@Override
	public StringBuffer processMsg(ClientMessage msg) throws FrameException {
		try {
			userData=this.getUserData(msg);
		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
		
		//���ϼ���˰��������
		if(msg.getAction().equalsIgnoreCase("doCommitBasx")){
			return doCommitBasx(msg);
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
		if(msg.getAction().equalsIgnoreCase("doBgsq")){
			return doBgsq(msg);
		}
		
		//�������
		if(msg.getAction().equalsIgnoreCase("doModifyBasx")){
			return doModifyBasx(msg);
		}
		
		//���ͨ��
		if(msg.getAction().equalsIgnoreCase("doAuditBasx")){
			return doAuditBasx(msg);
		}
				
		//��˲���
		if(msg.getAction().equalsIgnoreCase("doAuditRejectBasx")){
			return doAuditRejectBasx(msg);
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
			basxdmList=CodeTableManager.getCodeTableList(CodeTableKey.SF_DM_JMBASXDM_2014);
			for (sf_dm_jmbasxdm sf_dm_jmbasxdm : basxdmList) {
				JmsbaSxdm vo=new JmsbaSxdm();
				vo.setJmbasxDm(sf_dm_jmbasxdm.getJmbasxdm());
				vo.setJmbasxMc(sf_dm_jmbasxdm.getJmbasxmc());
				vo.setBalxdm(sf_dm_jmbasxdm.getBalxdm());
				vo.setJmlbdm(sf_dm_jmbasxdm.getJmlbdm());
				if(sf_dm_jmbasxdm.getYjbs().equals("0")){
					jmsBasxdmList.add(vo);
				}
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
			if("INNER".equals(deploy_environment)){
				List<JmsbaZgswjg> zgswjgList = new ArrayList<JmsbaZgswjg>();
				zgswjgList=JmsbaMainAccessExt.getZgswjgList(userData);
				resVo.setJmsbaZgswjgList(zgswjgList);
			}else{
				resVo.setInZqFlag(SbzlAccess.checkinZq("JMSBAJDYJ2014")?"Y":"N");
			}
			returnBuff.append(this.ConvertVoToXml(resVo));
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
	/*	String jsjdm="";
		String band="";
		String jmsbasxdm="";
		if(baseVo!=null){
			jsjdm=baseVo.getJsjdm();//���������
			band=baseVo.getBand();//�������
			jmsbasxdm=baseVo.getJmbasxdm();//�����������
		}*/
		try {
			conn=ResourceManager.getConnection();
			//List<sf_jl_qysdsjmsbajl_2014> qysdsjmsbajl_2014List=new ArrayList<sf_jl_qysdsjmsbajl_2014>();
			Djjbsj nsrjbsj=ServiceManager.getInstance().getDjjbsjService().query(conn, baseVo.getJsjdm());
			if(nsrjbsj!=null && !StringUtil.isEmpty(nsrjbsj.getJsjdm())){
				Map skssrq = Skssrq.yearSkssrq(FrameCommonAccess.getDBDate());
				Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
				Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
				baseVo.setSksssqq(timeFormat.format(skssksrq));
				baseVo.setSksssqz(timeFormat.format(skssjsrq));
				baseVo.setQh(Skssrq.preQuarter(FrameCommonAccess.getDBDate()));
				/*//�����ں�
				String qh=Skssrq.preQuarter(FrameCommonAccess.getDBDate());
				String sqlWhere=" where jsjdm='"+jsjdm+"' and band='"+band+"' and JMBASXDM='"+jmsbasxdm+"' and qh='"+qh+"'";
				qysdsjmsbajl_2014List=JmsbaMainAccessExt.getRecords(sqlWhere,conn);
				String baqsxmc=CodeTableManager.getNameByCode(CodeTableKey.SF_DM_JMBASXDM_2014, jmsbasxdm);
				if(qysdsjmsbajl_2014List!=null&&qysdsjmsbajl_2014List.size()>0){
					this.setRtnBizCode(RtnCodeMessage.Error_9999.Code);
					this.setRtnBizMessage("����ҵ�Ѿ����б�����¼����������Ϊ'"+baqsxmc+"'");
					returnBuff.append(ConvertVoToXml(null));
					return returnBuff;
				}*/
				baseVo.setBbqlx("1");
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * ͨ�������ύ������������
	 * @param msg
	 * @return
	 */
	private StringBuffer doCommitBasx(ClientMessage msg) throws FrameException{
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
			returnBuff= baseHandler.doCommit(msg);
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
	private StringBuffer doBgsq(ClientMessage msg) throws FrameException{
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
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * �޸ı�����������
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
			returnBuff= baseHandler.doModifyBasx(msg);
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
		JmsbaDeleteBasxReq deleteReq=new JmsbaDeleteBasxReq();
		this.ConvertXmlToVo(msg, deleteReq);
		//�����ӿ�
		Connection conn=null;
		JmsbaDeleteBasxRes deleteRes=new JmsbaDeleteBasxRes();
		try {
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			JmsbaMainAccessExt.deleteJmsbaZlqd(conn, deleteReq.getJmsbaBaseData().getBasqwsh());
			JmsbaMainAccessExt.deleteJmsbaZb(conn, deleteReq.getJmsbaBaseData().getBasqwsh());
			returnBuff.append(ConvertVoToXml(deleteRes));
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
	 * ���� �����������ύδ��˵Ŀɽ��г���
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
		Connection conn=null;
		try {
			conn=ResourceManager.getConnection();
			conn.setAutoCommit(false);
			//���ز���
			JmsbaRollbackBasxRes rollback=new JmsbaRollbackBasxRes();
			JmsbaMainAccessExt.doRollbackBasx(conn, rollbackReq.getJmsbaBaseData());
			returnBuff.append(this.ConvertVoToXml(rollback));
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
	 * ���ͨ��
	 * @param msg
	 * @return
	 */
	private StringBuffer doAuditBasx(ClientMessage msg) throws FrameException{
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
			returnBuff= baseHandler.doAuditBasx(msg);
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
	
	/**
	 * ��˲���
	 * @param msg
	 * @return
	 */
	private StringBuffer doAuditRejectBasx(ClientMessage msg) throws FrameException{
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
			returnBuff= baseHandler.doAuditRejectBasx(msg);
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			this.setRtnBizCode(RtnCodeMessage.Error_1005.Code);
			this.setRtnBizMessage(RtnCodeMessage.Error_1005.Message);
			returnBuff.append(this.ConvertVoToXml(null));
		}
		return returnBuff;
	}
}
