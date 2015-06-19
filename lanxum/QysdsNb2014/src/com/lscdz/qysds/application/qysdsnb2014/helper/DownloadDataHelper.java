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
	 * ������˰����ҵ����˰�걨����
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
			// ȡ�ò���
			String nsrjsjdm = request.getJsjdm(); // ���������
			String nd = request.getNd(); // ���
			String bbqlx = request.getBbqlx(); // ����������
			String qh = request.getQh(); // �ں�
			// ȡ�����ݿ�����
			conn = ResourceManager.getConnection();
			// ������ѯ�ӿ�
			// IAppAccess access =
			// AppAccessFactory.getAInstance(conn,ConstantKey.ACCESS_MODEL_APP_QYSDS2014);
			// �����ѯ��������
			QysdsReportsDeclare declare = new QysdsReportsDeclare();
			declare.setNsrjsjdm(nsrjsjdm);
			declare.setSknd(nd);
			declare.setBbqlx(bbqlx);
			declare.setQh(qh);
			// ��ѯ���غ�����ת��
			List paraList = new ArrayList();
			//conn = ResourceManager.getConnection();
			// ������ѯ�ӿ�
			IAppAccess access = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			QysdsReportsDeclare qrd = (QysdsReportsDeclare) access
					.query(declare);
			;
			Map map = qrd.getTableContentList();
			// �жϲ�ѯ�õ��������Ƿ�Ϊ�գ�Ϊ������ʾ
			if (map.isEmpty()) {
				qysdsResponse
						.setErrorNo(QysdsNbConstant.ERROR_TYPE_NODATAFOUND);
				qysdsResponse.setErrorXX("û�ж�Ӧ�ı������ݣ�");
			} else {
				qrd.setAppid(request.getAID());
				paraList.add(qrd);
				// ����ѯ�ӿ�ת�����ַ���
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
			qysdsResponse.setErrorXX("������æ����ȡ�걨������ʧ�ܣ������Ժ��������걨�����ݣ�");
			return qysdsResponse;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FrameException("������ҵ����˰�걨���ݷ����쳣��");
			}
		}
		return qysdsResponse;
	}
}
