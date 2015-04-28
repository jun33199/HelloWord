package com.lscdz.qysds.application.qysdsnb2014.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.DownloadQysdsDataReq;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbResponse;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.persistent.AppAccessFactory;
import com.lscdz.qysds.common.service.qysds.persistent.access.IAppAccess;
import com.lscdz.qysds.common.service.qysds.xml.ChangeApps;
import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
import com.lscdz.qysds.common.service.qysds.xml.bs.WriteReports;
import com.lscdz.qysds.common.util.ZipUtil;

public class DownloadDataHelper {
	/**
	 * 下载纳税人企业所得税申报数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static QysdsNbResponse download(DownloadQysdsDataReq request)
			throws FrameException {
		Connection conn = null;
		QysdsNbResponse qysdsResponse = new QysdsNbResponse();
		try {
			// 取得参数
			String nsrjsjdm = request.getJsjdm(); // 计算机代码
			String nd = request.getNd(); // 年度
			String bbqlx = request.getBbqlx(); // 报表期类型
			String qh = request.getQh(); // 期号
			// 取得数据库连接
			conn = ResourceManager.getConnection();
			// 声明查询接口
			// IAppAccess access =
			// AppAccessFactory.getAInstance(conn,ConstantKey.ACCESS_MODEL_APP_QYSDS2014);
			// 构造查询参数对象
			QysdsReportsDeclare declare = new QysdsReportsDeclare();
			declare.setNsrjsjdm(nsrjsjdm);
			declare.setSknd(nd);
			declare.setBbqlx(bbqlx);
			declare.setQh(qh);
			// 查询返回后数据转换
			List paraList = new ArrayList();
			//conn = ResourceManager.getConnection();
			// 声明查询接口
			IAppAccess access = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) access
					.query(declare);
			;
			Map map = qrd.getTableContentList();
			// 判断查询得到的数据是否为空，为空则提示
			if (map.isEmpty()) {
				qysdsResponse
						.setErrorNo(QysdsNbConstant.ERROR_TYPE_NODATAFOUND);
				qysdsResponse.setErrorXX("没有对应的报表数据！");
			} else {
				qrd.setAppid(request.getAID());
				paraList.add(qrd);
				// 将查询接口转换成字符串
				ReportsInterface rf = new WriteReports();
				String str_paraList = rf
						.getXMLStr(ChangeApps.getApps(paraList));
				String compressParaList = ZipUtil.compressTobase64(ZipUtil
						.compress(str_paraList));
				qysdsResponse.setDataApps(compressParaList);
			}
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			qysdsResponse.setErrorXX("服务器忙，获取申报表数据失败，请您稍后再下载申报表数据！");
			return qysdsResponse;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FrameException("下载企业所得税年报数据发生异常！");
			}
		}
		return qysdsResponse;
	}
}
