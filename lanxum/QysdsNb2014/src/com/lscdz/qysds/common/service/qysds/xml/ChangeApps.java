package com.lscdz.qysds.common.service.qysds.xml;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsItemDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.APP;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.INPUTITEMS;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.ITEM;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.REPORT;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLE;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.TABLES;


public class ChangeApps {
	// DateUtils
	private static DateUtils dateUtil = new DateUtils();

	public ChangeApps() {

	}

	/**
	 * ��QysdsReportsDeclare ת��ΪdataApps����
	 * 
	 * @param declareList ��Ԫ��ΪQysdsReportsDeclare����
	 * @return APPS ת�����APPS����
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static APPS getApps(List declareList) throws IOException {
		ChangeApps change = new ChangeApps();
		// dataApps
		APPS dataApps = new APPS();

		// ��List��ȡ��ÿ��reportDeclare
		for (int i = 0; i < declareList.size(); i++) {
			// dataApp
			APP dataApp = new APP();
			// dataReport
			REPORT dataReport = new REPORT();
			// dataTables
			TABLES dataTables = new TABLES();
			QysdsReportsDeclare reportDeclare = new QysdsReportsDeclare();
			reportDeclare = (QysdsReportsDeclare) declareList.get(i);
			// appAid
			String appAid = new String();
			appAid = reportDeclare.getAppid();

			/**
			 * dataAPPS��Ϣ
			 */

			// nsrjsjdm
			String nsrjsjdm = new String();
			nsrjsjdm = reportDeclare.getNsrjsjdm();
			// nsrmc
			String nsrmc = new String();
			nsrmc = reportDeclare.getNsrmc();
			// aid
			dataApp.setAID(appAid);
			// nsrjsjdm
			dataApp.setNSRJSJDM(nsrjsjdm);
			// nsrmc
			dataApp.setNSRMC(nsrmc);
			// bbqlx
			String bbqlx = new String();
			bbqlx = reportDeclare.getBbqlx();
			// nd
			String nd = new String();
			nd = reportDeclare.getSknd();
			// qh
			String qh = new String();
			qh = reportDeclare.getQh();
			// skssjsrq
			String skssjsrq = new String();
			skssjsrq = dateUtil.getDate(reportDeclare.getSkssjsrq());
			// skssksrq
			String skssksrq = new String();
			skssksrq = dateUtil.getDate(reportDeclare.getSkssksrq());
			// version
			String version = new String();
			version = reportDeclare.getVersion();
			// tableMap
			HashMap tableMap = new HashMap();
			tableMap = (HashMap) reportDeclare.getTableContentList();
			// Iterator itorTable = tableMap.keySet().iterator();
			// ��tableMap��Key��������
			ArrayList tidList = change.arrangeMapKey(tableMap);
			for (int j = 0; j < tidList.size(); j++) {
				// tid
				String tableKey = (String) tidList.get(j);
				// tableDeclare
				QysdsReportsTableDeclare tableDeclare = new QysdsReportsTableDeclare();
				tableDeclare = (QysdsReportsTableDeclare) tableMap.get(tableKey);
				// dataTable
				TABLE dataTable = new TABLE();
				// inputItem
				INPUTITEMS inputItem = new INPUTITEMS();
				// dataTable name
				String tableName = new String();
				tableName = tableDeclare.getTableName();
				// dataTable tid
				String tableTid = new String();
				tableTid = tableDeclare.getTableId();
				// inputItem
				HashMap itemMap = new HashMap();
				itemMap = (HashMap) tableDeclare.getCellContentList();
				// ��itemMap��Key��������
				ArrayList keyList = change.arrangeMapKey(itemMap);

				for (int k = 0; k < keyList.size(); k++) {
					String key = (String) keyList.get(k);
					// bbqlx
					dataReport.setBBQLX(change.changeStr(bbqlx));
					// nd
					dataReport.setND(change.changeStr(nd));
					// qh
					dataReport.setQH(change.changeStr(qh));
					// skssjsrq
					dataReport.setSKSSJSRQ(change.changeStr(skssjsrq));
					// skssksrq
					dataReport.setSKSSKSRQ(change.changeStr(skssksrq));
					// version
					dataReport.setVERSION(change.changeStr(version));
					// dataTable name
					dataTable.setNAME(change.changeStr(tableName));
					// dataTable tid
					dataTable.setTID(change.changeStr(tableTid));
					// itemDeclare
					QysdsReportsItemDeclare itemDeclare = new QysdsReportsItemDeclare();
					itemDeclare = (QysdsReportsItemDeclare) itemMap.get(key);
					// item
					ITEM item = new ITEM();
					// itemIid
					item.setIID(change.changeStr(itemDeclare.getItemID()));

					// itemName
					item.setNAME("");
					// itemType
					item.setREPORTTYPE(change.changeStr(itemDeclare
							.getItemType()));
					// itemValue
					item.setVALUE(change.changeStr(itemDeclare.getItemValue()));
					// ��item��װ��inputItem
					inputItem.addITEM(item);
				}
				// ��inputItem��װ��table
				dataTable.setINPUTITEMS(inputItem);
				// ��table��װ��Tables
				dataTables.addTABLE(dataTable);

			}
			// ��tables��װ��report
			dataReport.setTABLES(dataTables);
			// report��װ��dataApp
			// dataApp.addREPORT(dataReport);
			if (i == 0) {
				// report��װ��dataApp
				dataApp.addREPORT(dataReport);
				// dataApp��װ��dataApps
				dataApps.addAPP(dataApp);
			} else {
				// ��dataReport������Ӧ��appAid��װ��dataApp��
				for (int j = 0; j < dataApps.getAPPCount(); j++) {
					// oldDataApp
					APP oldDataApp = new APP();
					oldDataApp = dataApps.getAPP(j);
					if (appAid.equals(oldDataApp.getAID())) {
						oldDataApp.addREPORT(dataReport);
						break;
					} else if (j == dataApps.getAPPCount() - 1) {
						dataApp.addREPORT(dataReport);
						// dataApp��װ��dataApps
						dataApps.addAPP(dataApp);
						break;
					}
				}

			}

			// dataApp��װ��dataApps
			// dataApps.addAPP(dataApp);
		}
		return dataApps;
	}

	/**
	 * ������Map��keyֵ��������
	 * 
	 * @param declareMap �����Map
	 * @return ������List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList arrangeMapKey(HashMap declareMap) {
		// ����keyList
		ArrayList keyList = new ArrayList();

		Iterator it = declareMap.keySet().iterator();
		while (it.hasNext()) {
			// ��ȡ��ǰkey
			String key = (String) it.next();
			keyList.add(key);
		}

		int size = keyList.size();
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1; j++) {
				double curKey = Double.parseDouble(((String) keyList.get(j)).replace("A",""));
				double nextKey = Double.parseDouble(((String) keyList.get(j + 1)).replace("A", ""));
				String curKey_Str = (String) keyList.get(j);
				String nextKey_Str = (String) keyList.get(j + 1);
				if (curKey > nextKey) {
					keyList.set(j, nextKey_Str);
					keyList.set((j + 1), curKey_Str);
				}
			}
		}
		return keyList;
	}

	/**
	 * �������doubleֵת�����ַ������ ���doubleΪ*.0����ֻҪ����λ
	 * 
	 * @param d ��Ҫת����doubleֵ
	 * @return ת������ַ���
	 */
	@SuppressWarnings("unused")
	private String convertStr(double d) {
		String str = String.valueOf(d);
		if (str.endsWith(".0")) {
			str = str.substring(0, (str.indexOf(".")));
		}
		return str;
	}

	/**
	 * ��nullֵת��Ϊ""
	 * 
	 * @param str
	 *            ��Ҫת�����ַ���
	 * @return ת������ַ���
	 */
	private String changeStr(String str) {
		if (null == str) {
			str = "";
		}
		return str;
	}

}