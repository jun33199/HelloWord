package com.lscdz.qysds.application.qysdsnb2014.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.lscdz.qysds.application.qysdsnb2014.util.QysdsNb2014Util;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbResponse;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
import com.lscdz.qysds.common.service.qysds.xml.bs.WriteReports;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APP;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.APPS;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.CKZD;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.GZGLXS;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JBXX;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.JMLX;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.SSHY;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.SSJJLX;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.SWJGZZJGDM;
import com.lscdz.qysds.common.service.qysds.xml.vo.userInfo.ZSFS;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.qysds.common.util.ZipUtil;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

public class QueryNsrjbxxHelper {

	/**
	 * 取得纳税人基本信息
	 * 
	 * @param jsjdm
	 * @param qysdsResponse
	 * @return QysdsNbResponse
	 * @author zhangj
	 */
	@SuppressWarnings("rawtypes")
	public static QysdsNbResponse getNsrxx(String jsjdm,QysdsNbResponse qysdsResponse) throws FrameException {

		QysdsHelperUtil qysdsHelperUtil = new QysdsHelperUtil();
		Connection conn = null;

		Statement st = null;
		ResultSet rs = null;
		APPS userInfo = new APPS();
		APP userInfoApp = new APP();
		// 设置默认常量
		userInfoApp.setAID(QysdsNbConstant.APP_AID_QYSDSNB);
		userInfoApp.setNAME("");
		userInfoApp.setACTIVITY("1");
		userInfoApp.setREMARK1("");
		userInfoApp.setREMARK2("");
		qysdsResponse.setJsjdm(jsjdm);
		// 取得数据库连接
		try {
			conn = ResourceManager.getConnection();

			// 查询纳税人基本信息
			String sql = "SELECT * FROM SBDB.SB_JL_QYSDS_NSRJBXXB WHERE NSRJSJDM='"+ jsjdm + "'";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			int size = 0;
			while (rs.next()) {
				JBXX jbxx = new JBXX();
				// 如报表id,例如“1;3;4;5”
				jbxx.setBBMSF((rs.getString("BBMSF") == null ? "" : rs.getString("BBMSF")));
				// 财会制度
				CKZD ckzd = new CKZD();
				ckzd.setCODE(QysdsNb2014Util.getCkzd(rs.getString("SYKJZZHKJZZ"), rs.getString("QTSYKJZZHKJZZ")));
				ckzd.setNAME((rs.getString("CKZDMC") == null ? "" : rs.getString("CKZDMC")));
				jbxx.setCKZD(ckzd);
				// 工资管理形式
				GZGLXS gzglxs = new GZGLXS();
				gzglxs.setCODE((rs.getString("GZGLXS") == null ? "" : rs.getString("GZGLXS")));
				gzglxs.setNAME((rs.getString("GZGLXSMC") == null ? "" : rs.getString("GZGLXSMC")));
				jbxx.setGZGLXS(gzglxs);
				// 减免类型
				JMLX jmlx = new JMLX();
				jmlx.setCODE((rs.getString("JMLX") == null ? "" : rs.getString("JMLX")));
				jmlx.setNAME((rs.getString("JMLXMC") == null ? "" : rs.getString("JMLXMC")));
				jbxx.setJMLX(jmlx);
				// 经营地址
				jbxx.setJYDZ((rs.getString("JYDZ") == null ? "" : rs.getString("JYDZ")));
				// 年度
				jbxx.setND((rs.getString("ND") == null ? "" : rs.getString("ND")));
				// 纳税人计算机代码
				jbxx.setNSRJSJDM((rs.getString("NSRJSJDM") == null ? "" : rs.getString("NSRJSJDM")));
				// 纳税人名称
				jbxx.setNSRMC((rs.getString("NSRMC") == null ? "" : rs.getString("NSRMC")));
				// 纳税人识别号－税务登记证号
				jbxx.setNSRSBH((rs.getString("NSRSBH") == null ? "" : rs.getString("NSRSBH")));
				// 申请审批表编号申请审批表编号
				jbxx.setSQSPPBH((rs.getString("SQSPBH") == null ? "" : rs.getString("SQSPBH")));
				// 所属行业
				SSHY sshy = new SSHY();
				sshy.setCODE((rs.getString("SSHY") == null ? "" : rs.getString("SSHY")));
				sshy.setNAME((rs.getString("SSHYMC") == null ? "" : rs.getString("SSHYMC")));
				jbxx.setSSHY(sshy);
				// 所属经济类型-登记注册类型
				SSJJLX ssjjlx = new SSJJLX();
				ssjjlx.setCODE((rs.getString("SSJJLX") == null ? "" : rs.getString("SSJJLX")));
				ssjjlx.setNAME((rs.getString("SSJJLXMC") == null ? "" : rs.getString("SSJJLXMC")));
				jbxx.setSSJJLX(ssjjlx);
				// 税务机关组织机构代码
				SWJGZZJGDM swjgzzjgdm = new SWJGZZJGDM();
				swjgzzjgdm.setCODE((rs.getString("SWJGZZJGDM") == null ? "": rs.getString("SWJGZZJGDM")));
				qysdsResponse.setSwjgzzjgdm((rs.getString("SWJGZZJGDM") == null ? "": rs.getString("SWJGZZJGDM")));
				swjgzzjgdm.setNAME((rs.getString("SWJGZZJGMC") == null ? "": rs.getString("SWJGZZJGMC")));
				jbxx.setSWJGZZJGDM(swjgzzjgdm);
				// 版本号
				jbxx.setVERSION((rs.getString("VERSION") == null ? "" : rs.getString("VERSION")));
				// 系统级别
				jbxx.setXTJB((rs.getString("XTJB") == null ? "" : rs.getString("XTJB")));
				// 企业所得税征收方式
				ZSFS zsfs = new ZSFS();
				zsfs.setCODE((rs.getString("ZSFS") == null ? "" : rs.getString("ZSFS")));
				zsfs.setCYL((rs.getString("CYL") == null ? "" : rs.getString("CYL")));
				zsfs.setDE((rs.getString("DE") == null ? "" : rs.getString("DE")));
				zsfs.setDL((rs.getString("DL") == null ? "" : rs.getString("DL")));
				zsfs.setNAME((rs.getString("ZSFSMC") == null ? "" : rs.getString("ZSFSMC")));
				jbxx.setZSFS(zsfs);
				List readList = qysdsHelperUtil.getReadOnlyHc(jsjdm, jbxx.getND());// sknd???
				System.out.println("readOnly nd:" + jbxx.getND());
				String reads = "";
				if (readList.size() > 0) {
					for (int k = 0; k < readList.size(); k++) {
						reads = reads + readList.get(k) + ",";
					}
				}
				if (!reads.equals("")) {
					reads = reads.substring(0, reads.length() - 1);

				}
				System.out.println("readOnly hc:" + reads);
				jbxx.setT10READONLY(reads);
				userInfoApp.addJBXX(jbxx);
				size++;
			}
			if (size == 0) {
				qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_LOGINERROR);
				qysdsResponse
						.setErrorXX("您没有进行基本信息核定，请登陆北京地税报税系统，填报查账征收企业基本信息表！");
				return qysdsResponse;
			}
			userInfo.addAPP(userInfoApp);

			// 转换基本信息对象为字符串
			ReportsInterface rf = new WriteReports();

			// -------------2007-1-21梁龙武修改，对下载的基本信息报文数据进行压缩，再进行传输
			String str_userInfo = rf.getXMLStr(userInfo);

			String compressStr_userInfo = ZipUtil.compressTobase64(ZipUtil.compress(str_userInfo));

			qysdsResponse.setUserInfo(compressStr_userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_LOGINERROR);
			qysdsResponse.setErrorXX("获取纳税人基本信息出错！");
			return qysdsResponse;
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (st != null) {
					st.close();
					st = null;
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FrameException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new FrameException(e.getMessage());
			}
		}
		return qysdsResponse;
	}

}
