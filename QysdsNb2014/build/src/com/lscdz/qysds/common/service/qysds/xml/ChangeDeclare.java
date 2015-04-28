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
 * 将APPS对象转换为QysdsReportsDeclare对象
 * 项目名称：QysdsNb2014   
 * 类名称：ChangeDeclare   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-12-1 下午5:51:50   
 * 修改人：wangcy   
 * 修改时间：2014-12-1 下午5:51:50   
 * 修改备注：   
 * @version  1.0
 */
public class ChangeDeclare {
	// DateUtils
	private static DateUtils dateUtil = new DateUtils();

	/**
	 * 构造器
	 */
	public ChangeDeclare() {

	}

	/**
	 * 将APPS对象转换为QysdsReportsDeclare对象
	 * 
	 * @param dataApps
	 * @return QysdsReportsDeclare 对象的list
	 */
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public static List getReportDeclare(APPS dataApps) throws FrameException{
		// 创建reportDeclare list
		List declareList = new ArrayList();
		for (int i = 0; i < dataApps.getAPPCount(); i++) {

			// 创建app并得到第i个app的信息
			APP app = new APP();
			// 取得当前的app
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
			// 第二层循环，通过report数量的循环依次取出report
			for (int m = 0; m < app.getREPORTCount(); m++) {
				// 创建reportDeclare对象
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

				// 第三层循环，通过table数量的循环依次取出table
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
					// 第四层循环，通过inputItem中item数量的循环依次取出table
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
						// tableTbblx:由于报文无该内容，暂时为" "
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
						// itemMap以IID与rdata为对照关系封装
						itemMap.put(iid, itemDeclare);

					}
					// 将itemMap封装到tableDeclare
					tableDeclare.setCellContentList(itemMap);
					// tableMap以tid 与 itemMap为对照关系封装
					tableContentList.put(tid, tableDeclare);
				}
				reportDeclare.setTableContentList(tableContentList);
				declareList.add(reportDeclare);

			}

		}

		return declareList;

	}

}
