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
 * 企业所得税季报父类，处理dao相关操作
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-4-22 上午11:29:25
 */
public abstract class QysdsjbBaseHelper {
	public void delete(QysdsjbBaseVo qysdsjbBaseVo)throws FrameException{
		// 获取税款所属季度
		String jd = QysdsBaseUtil.preQuarter(DateUtils.getDateTime(qysdsjbBaseVo.getSkssjsrq()));
		String nd = qysdsjbBaseVo.getSkssksrq().substring(0, 4);
		// 设置报表期类型
		qysdsjbBaseVo.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		// 设置季度
		qysdsjbBaseVo.setQh(jd);
		// 设置年度
		qysdsjbBaseVo.setSknd(nd);
		this.deleteReportData(this.getReportVo(qysdsjbBaseVo));

	}
	public void save(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException {
		this.saveReportData(qysdsjbBaseVo);			
	}
	public void query(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException {
		Connection conn = null;
		try {		
		// 获取税款所属季度
		String jd = QysdsBaseUtil.preQuarter(DateUtils.getDateTime(qysdsjbBaseVo.getSkssjsrq()));
		String nd = qysdsjbBaseVo.getSkssksrq().substring(0, 4);
		// 设置季度
		qysdsjbBaseVo.setQh(jd);
		// 设置年度
		qysdsjbBaseVo.setSknd(nd);
		// 设置form中其它所需属性
		conn = ResourceManager.getConnection();
		qysdsjbBaseVo = (CzzssdsjbFpbVo) QysdsBaseUtil.queryDjxxByInterfaceDJ(conn, qysdsjbBaseVo);
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("查询数据出错！");
		}
		qysdsjbBaseVo.setReportData(this.queryReportData(this.getReportVo(qysdsjbBaseVo)));
		
	}

	protected String queryReportData(ReportVo reportVo) throws FrameException {
		String nsrjsjdm = reportVo.getJsjdm(); // 计算机代码
		String nd = reportVo.getNd(); // 年度
		String bbqlx = reportVo.getBbqlx(); // 报表期类型
		String qh = reportVo.getQh(); // 期号
		String tid = reportVo.getTid(); // 申报代码
		String tname=reportVo.getTname();//表名
		String skssksrq=reportVo.getSkssksrq();//税款开始时间
		String skssjsrq=reportVo.getSkssjsrq();//税款结束时间
		Connection conn = null;
		// 取得数据库连接
		try {
			conn = ResourceManager.getConnection();
//			conn = ConnFactoryTest.getConnect();
			// 构造查询参数对象
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
			// 查询返回后数据转换
			List paraList = new ArrayList();
			// conn = ResourceManager.getConnection();
			// 声明查询接口
			IAppAccess access = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDSJB);
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) access
					.querySingleTable(declare);
			Map map = qrd.getTableContentList();
			// 判断查询得到的数据是否为空，为空则提示
			if (map.isEmpty()) {
				throw new FrameException("没有对应的报表数据！");
			} else {
				qrd.setAppid("001");
				paraList.add(qrd);
				// 将查询接口转换成字符串
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
			throw new FrameException("查询报表数据出错！");		
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
		String nsrjsjdm = reportVo.getJsjdm(); // 计算机代码
		String nd = reportVo.getNd(); // 年度
		String bbqlx = reportVo.getBbqlx(); // 报表期类型
		String qh = reportVo.getQh(); // 期号
		String tid = reportVo.getTid(); // 申报代码
		String tname=reportVo.getTname();//表名
		String skssksrq=reportVo.getSkssksrq();//税款开始时间
		String skssjsrq=reportVo.getSkssjsrq();//税款结束时间
		Connection conn = null;
		// 取得数据库连接
		try {
			conn = ResourceManager.getConnection();
			// 构造查询参数对象
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
			// 查询返回后数据转换
			List paraList = new ArrayList();
			// 声明查询接口
			IAppAccess access = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDSJB);
			access.deleteSingleTable(declare);
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("删除报表数据出错！");		
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
		String reportData = qysdsjbBaseVo.getReportData(); // 报表数据
		ReadReports rp = new ReadReports();
		// 取得证书、登陆方式、签名值
		String reportType = ReportsInterface.REPROTTYPE_REPORTDATA; // 报表类型
		List declareList = null;
		// 报文数据进行解压缩后再处理
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
			throw new FrameException("报表数据解压出错！");
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
