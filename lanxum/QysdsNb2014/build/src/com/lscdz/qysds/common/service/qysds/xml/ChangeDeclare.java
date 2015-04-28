package com.lscdz.qysds.common.service.qysds.xml;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yangjian.frame.util.FrameException;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsItemDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.APP;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS;

/**
 * ��APPS����ת��ΪQysdsReportsDeclare����
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�ChangeDeclare   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-12-1 ����5:51:50   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-12-1 ����5:51:50   
 * �޸ı�ע��   
 * @version  1.0
 */
public class ChangeDeclare {
	// DateUtils
	private static DateUtils dateUtil = new DateUtils();

	/**
	 * ������
	 */
	public ChangeDeclare() {

	}

	/**
	 * ��APPS����ת��ΪQysdsReportsDeclare����
	 * 
	 * @param dataApps
	 * @return QysdsReportsDeclare �����list
	 */
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public static List getReportDeclare(APPS dataApps) throws FrameException{
		// ����reportDeclare list
		List declareList = new ArrayList();
		for (int i = 0; i < dataApps.getAPPCount(); i++) {

			// ����app���õ���i��app����Ϣ
			APP app = new APP();
			// ȡ�õ�ǰ��app
			app = dataApps.getAPP(i);
			// appAid
			String appAid = new String();
			appAid = app.getAID();
			// nsrjsjdm
			String jsjdm = new String();
			jsjdm = app.getNSRJSJDM();
			// nsrmc
			String nsrmc = new String();
			nsrmc = app.getNSRMC();
			// �ڶ���ѭ����ͨ��report������ѭ������ȡ��report
			for (int m = 0; m < app.getREPORTCount(); m++) {
				// ����reportDeclare����
				QysdsReportsDeclare reportDeclare = new QysdsReportsDeclare();

				// version
				String reportVersion = new String();
				reportVersion = app.getREPORT(m).getVERSION();
				// report BBQLX
				String reportBbqlx = new String();
				reportBbqlx = app.getREPORT(m).getBBQLX();
				// reportQh
				String reportQh = new String();
				reportQh = app.getREPORT(m).getQH();
				// ND
				String sknd = new String();
				sknd = app.getREPORT(m).getND();
				// report SKSSKSRQ
				Timestamp skssksrq = new Timestamp(dateUtil.getDateTime(app.getREPORT(m).getSKSSKSRQ()).getTime());
				// report SKSSJSRQ
				Timestamp skssjsrq = new Timestamp(dateUtil.getDateTime(app.getREPORT(m).getSKSSJSRQ()).getTime());
				// tableContentList
				HashMap tableContentList = new HashMap();

				// ������ѭ����ͨ��table������ѭ������ȡ��table
				for (int j = 0; j < app.getREPORT(m).getTABLES().getTABLECount(); j++) {
					// tableDeclare
					QysdsReportsTableDeclare tableDeclare = new QysdsReportsTableDeclare();
					// table TID
					String tid = new String();
					tid = app.getREPORT(m).getTABLES().getTABLE(j).getTID();
					// table Name
					String tableName = new String();
					tableName = app.getREPORT(m).getTABLES().getTABLE(j)
							.getNAME();
					// itemMap
					HashMap itemMap = new HashMap();
					// ���Ĳ�ѭ����ͨ��inputItem��item������ѭ������ȡ��table
					for (int k = 0; k < app.getREPORT(m).getTABLES().getTABLE(j).getINPUTITEMS().getITEMCount(); k++) {
						// appAid
						reportDeclare.setAppid(appAid);
						// nsrjsjdm
						reportDeclare.setNsrjsjdm(jsjdm);
						// nsrmc
						reportDeclare.setNsrmc(nsrmc);
						// report
						reportDeclare.setVersion(reportVersion);
						// report BBQLX
						reportDeclare.setBbqlx(reportBbqlx);
						// report QH
						reportDeclare.setQh(reportQh);
						// sknd
						reportDeclare.setSknd(sknd);
						// report SKSSKSRQ
						reportDeclare.setSkssksrq(skssksrq);
						// report SKSSJSRQ
						reportDeclare.setSkssjsrq(skssjsrq);
						// itemDeclare
						QysdsReportsItemDeclare itemDeclare = new QysdsReportsItemDeclare();
						// tid
						tableDeclare.setTableId(tid);
						// tableName
						tableDeclare.setTableName(tableName);
						// tableTbblx:���ڱ����޸����ݣ���ʱΪ" "
						tableDeclare.setTbblx(" ");
						// inputItem iid
						String iid = new String();
						iid = app.getREPORT(m).getTABLES().getTABLE(j).getINPUTITEMS().getITEM(k).getIID();
						itemDeclare.setItemID(iid);
						// inputItem VALUE
						String itemValue = new String();
						itemValue = app.getREPORT(m).getTABLES().getTABLE(j).getINPUTITEMS().getITEM(k).getVALUE();
						itemDeclare.setItemValue(itemValue);
						// inputItem type
						String itemType = new String();
						itemType = app.getREPORT(m).getTABLES().getTABLE(j).getINPUTITEMS().getITEM(k).getREPORTTYPE();
						itemDeclare.setItemType(itemType);
						// itemMap��IID��rdataΪ���չ�ϵ��װ
						itemMap.put(iid, itemDeclare);

					}
					// ��itemMap��װ��tableDeclare
					tableDeclare.setCellContentList(itemMap);
					// tableMap��tid �� itemMapΪ���չ�ϵ��װ
					tableContentList.put(tid, tableDeclare);
				}
				reportDeclare.setTableContentList(tableContentList);
				declareList.add(reportDeclare);

			}

		}

		return declareList;

	}

}
