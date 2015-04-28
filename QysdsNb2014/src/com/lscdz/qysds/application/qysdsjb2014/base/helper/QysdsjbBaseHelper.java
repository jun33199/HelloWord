package com.lscdz.qysds.application.qysdsjb2014.base.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.qysds.application.qysdsjb2014.QysdsJb2014Contant;
import com.lscdz.qysds.application.qysdsjb2014.base.util.QysdsBaseUtil;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.QysdsjbBaseVo;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.ReportVo;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.fpb.vo.CzzssdsjbFpbVo;
import com.lscdz.qysds.common.service.qysds.Constants;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.persistent.AppAccessFactory;
import com.lscdz.qysds.common.service.qysds.persistent.access.IAppAccess;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.xml.ChangeApps;
import com.lscdz.qysds.common.service.qysds.xml.ChangeDeclare;
import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
import com.lscdz.qysds.common.service.qysds.xml.bs.ReadReports;
import com.lscdz.qysds.common.service.qysds.xml.bs.WriteReports;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS;
import com.lscdz.qysds.common.util.ZipUtil;
/**
 * ��ҵ����˰�������࣬����dao��ز���
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-4-22 ����11:29:25
 */
public abstract class QysdsjbBaseHelper {
	public void delete(QysdsjbBaseVo qysdsjbBaseVo)throws FrameException{
		// ��ȡ˰����������
		String jd = QysdsBaseUtil.preQuarter(DateUtils.getDateTime(qysdsjbBaseVo.getSkssjsrq()));
		String nd = qysdsjbBaseVo.getSkssksrq().substring(0, 4);
		// ���ñ���������
		qysdsjbBaseVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// ���ü���
		qysdsjbBaseVo.setQh(jd);
		// �������
		qysdsjbBaseVo.setSknd(nd);
		this.deleteReportData(this.getReportVo(qysdsjbBaseVo));

	}
	public void save(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException {
		this.saveReportData(qysdsjbBaseVo);			
	}
	public void query(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException {
		Connection conn = null;
		try {		
		// ��ȡ˰����������
		String jd = QysdsBaseUtil.preQuarter(DateUtils.getDateTime(qysdsjbBaseVo.getSkssjsrq()));
		String nd = qysdsjbBaseVo.getSkssksrq().substring(0, 4);
		// ���ü���
		qysdsjbBaseVo.setQh(jd);
		// �������
		qysdsjbBaseVo.setSknd(nd);
		// ����form��������������
		conn = ResourceManager.getConnection();
		qysdsjbBaseVo = (CzzssdsjbFpbVo) QysdsBaseUtil.queryDjxxByInterfaceDJ(conn, qysdsjbBaseVo);
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("��ѯ���ݳ���");
		}
		qysdsjbBaseVo.setReportData(this.queryReportData(this.getReportVo(qysdsjbBaseVo)));
		
	}

	protected String queryReportData(ReportVo reportVo) throws FrameException {
		String nsrjsjdm = reportVo.getJsjdm(); // ���������
		String nd = reportVo.getNd(); // ���
		String bbqlx = reportVo.getBbqlx(); // ����������
		String qh = reportVo.getQh(); // �ں�
		String tid = reportVo.getTid(); // �걨����
		String tname=reportVo.getTname();//����
		String skssksrq=reportVo.getSkssksrq();//˰�ʼʱ��
		String skssjsrq=reportVo.getSkssjsrq();//˰�����ʱ��
		Connection conn = null;
		// ȡ�����ݿ�����
		try {
			conn = ResourceManager.getConnection();
//			conn = ConnFactoryTest.getConnect();
			// �����ѯ��������
			QysdsReportsDeclare declare = new QysdsReportsDeclare();
			QysdsReportsTableDeclare qrtd=new QysdsReportsTableDeclare();
			
			declare.setNsrjsjdm(nsrjsjdm);
			declare.setSknd(nd);
			declare.setBbqlx(bbqlx);
			declare.setQh(qh);
			declare.setVersion(QysdsJb2014Contant.SBZL_QYSDSJB_VERSION_2014);
			declare.setSkssjsrq(DateUtils.getDateTime(skssjsrq));
			declare.setSkssksrq(DateUtils.getDateTime(skssksrq));
			declare.setAppid("001");
			qrtd.setTableId(tid);
			qrtd.setTableName(tname);
			HashMap tableMap=new HashMap();
			tableMap.put(tid, qrtd);
			declare.setTableContentList(tableMap);
			// ��ѯ���غ�����ת��
			List paraList = new ArrayList();
			// conn = ResourceManager.getConnection();
			// ������ѯ�ӿ�
			IAppAccess access = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDSJB);
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) access
					.querySingleTable(declare);
			Map map = qrd.getTableContentList();
			// �жϲ�ѯ�õ��������Ƿ�Ϊ�գ�Ϊ������ʾ
			if (map.isEmpty()) {
				throw new FrameException("û�ж�Ӧ�ı������ݣ�");
			} else {
				qrd.setAppid("001");
				paraList.add(qrd);
				// ����ѯ�ӿ�ת�����ַ���
				ReportsInterface rf = new WriteReports();
				String str_paraList = rf
						.getXMLStr(ChangeApps.getApps(paraList));
				String compressParaList;

				compressParaList = ZipUtil.compressTobase64(ZipUtil
						.compress(str_paraList));
				return compressParaList;
			}
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("��ѯ�������ݳ���");		
		}finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void deleteReportData(ReportVo reportVo)throws FrameException{
		String nsrjsjdm = reportVo.getJsjdm(); // ���������
		String nd = reportVo.getNd(); // ���
		String bbqlx = reportVo.getBbqlx(); // ����������
		String qh = reportVo.getQh(); // �ں�
		String tid = reportVo.getTid(); // �걨����
		String tname=reportVo.getTname();//����
		String skssksrq=reportVo.getSkssksrq();//˰�ʼʱ��
		String skssjsrq=reportVo.getSkssjsrq();//˰�����ʱ��
		Connection conn = null;
		// ȡ�����ݿ�����
		try {
			conn = ResourceManager.getConnection();
			// �����ѯ��������
			QysdsReportsDeclare declare = new QysdsReportsDeclare();
			QysdsReportsTableDeclare qrtd=new QysdsReportsTableDeclare();
			
			declare.setNsrjsjdm(nsrjsjdm);
			declare.setSknd(nd);
			declare.setBbqlx(bbqlx);
			declare.setQh(qh);
			declare.setVersion(QysdsJb2014Contant.SBZL_QYSDSJB_VERSION_2014);
			declare.setSkssjsrq(DateUtils.getDateTime(skssjsrq));
			declare.setSkssksrq(DateUtils.getDateTime(skssksrq));
			qrtd.setTableId(tid);
			HashMap tableMap=new HashMap();
			tableMap.put(tid, qrtd);
			declare.setTableContentList(tableMap);
			// ��ѯ���غ�����ת��
			List paraList = new ArrayList();
			// ������ѯ�ӿ�
			IAppAccess access = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDSJB);
			access.deleteSingleTable(declare);
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("ɾ���������ݳ���");		
		}finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	protected void saveReportData(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException{
		Connection conn=null;
		String reportData = qysdsjbBaseVo.getReportData(); // ��������
		ReadReports rp = new ReadReports();
		// ȡ��֤�顢��½��ʽ��ǩ��ֵ
		String reportType = ReportsInterface.REPROTTYPE_REPORTDATA; // ��������
		List declareList = null;
		// �������ݽ��н�ѹ�����ٴ���
		try {
			String decompressReportData = ZipUtil.decompress(ZipUtil.base64Tocompress(reportData));
			declareList = ChangeDeclare.getReportDeclare((APPS) rp.readReport(reportType, decompressReportData));
			conn = ResourceManager.getConnection();
			for (int i = 0; i < declareList.size(); i++) {
				QysdsReportsDeclare declare = (QysdsReportsDeclare) declareList.get(i);
				QysdsBaseUtil.setDeclareData(declare,qysdsjbBaseVo);
				IAppAccess qysdsJBAppAccess=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDSJB);
				qysdsJBAppAccess.saveSingleTable(declare);				
			}
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new FrameException("�������ݽ�ѹ����");
		}finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
	}
	protected abstract ReportVo getReportVo(QysdsjbBaseVo qysdsjbBaseVo);


}
