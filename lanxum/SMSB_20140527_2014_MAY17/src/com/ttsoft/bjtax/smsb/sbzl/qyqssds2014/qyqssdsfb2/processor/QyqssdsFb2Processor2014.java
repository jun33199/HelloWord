package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsItemDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb2.web.QyqssdsFb2Form2014;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.DateUtilToChinese;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsSaveAndCheck;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsFb2Processor2014   
 * ��������   ���������ծ�峥������ϸ��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����3:31:00   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����3:31:00   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsFb2Processor2014 implements Processor {
	/**
	 * ʵ��Processor�ӿ�
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
	 */

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;
		case CodeConstant.SMSB_CHECKACTION:
			result = doCheck(vo);
			break;
		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}

	/**
	 * doShow��ʼ������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			/*----add by huohb,add skssq-----*/
			String ksrq =fb2Form.getQssbksrq();
			String jsrq =fb2Form.getQssbjsrq();
			String skssq=DateUtilToChinese.convertDate(ksrq, jsrq);
			fb2Form.setSkssq(skssq);
			
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			QyqssdsUtil2014.setQyqssdsReport(report, fb2Form);
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			report.getTableContentList().put(table.getTableId(), table);
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			iApp.querySingleTable(report);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_2);

			int[] arra1 = { 1, 92 };
			fb2Form.setDataList(this.FGXtranslate2Page(QyqssdsUtil2014.putSpace(table, arra1)));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return fb2Form;
	}

	/**
	 * doSave ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		QyqssdsFb2Form2014 form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(form);

			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			iApp.saveSingleTable(report);
			// �������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SAVE);
			
			//�����걨���е��걨״̬��ʶΪ�����ύδ��ˡ�
			QyqssdsSaveAndCheck.updateFlag(conn,form);
			//���������걨��ʼ���ںͽ�������
			QyqssdsUtil2014.updateAllDate(conn, form);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return form;

	}

	/**
	 * doCheck ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo) throws BaseException {
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(fb2Form);

			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);

			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			/* ���У��ͨ�������ýӿڱ������� */
			if (listSingle == null || (listSingle != null && listSingle.size() == 0)) {
				//�����걨�ĺ��������ͨ���ĵ���У�鲻��������
				if(fb2Form.getSqlx()=="0"||fb2Form.getSbShztbs()=="2"){
					
				}else{
					iApp.saveSingleTable(report);
				}
				// �������״̬Ϊ���������ͨ����
				iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SINGLE_PASS);
			} else if (listSingle.size() > 0) {
				// �������δͨ��
				iApp.updateCheckStatus(report, "");
			}
			fb2Form.setCheckList(listSingle);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return fb2Form;
	}

	/**
	 * doDelete ����ɾ��ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doDelete(VOPackage vo) throws BaseException {
		QyqssdsFb2Form2014 fb2Form = (QyqssdsFb2Form2014) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			QyqssdsReportsDeclare report1 = this.translate2Interface(fb2Form);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			iApp.deleteSingleTable(report1);
			iApp.updateCheckStatus(report1, "");
			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			int[] arra1 = { 1, 92 };
			table = (QyqssdsReportsTableDeclare) report1.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
			fb2Form.setDataList(this.FGXtranslate2Page(QyqssdsUtil2014.putSpace(table, arra1)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return fb2Form;
	}


	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param QyqssdsFb2Form2014
	 * @return ��ҵ����˰������������
	 */
	private QyqssdsReportsDeclare translate2Interface(QyqssdsFb2Form2014 form) {
		// ��ҵ����˰������������
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		QyqssdsUtil2014.setQyqssdsReport(report, form);
		// ��ҵ����˰�����ڵ�����������
		QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
		List list = form.getDataList();
		table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_2);
		table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_2);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
		int js = 1; // ������
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
//			String hc1 = (String) map.get("hc1");
//			if (Integer.parseInt(hc1) < 5) {
//				QyqssdsReportsItemDeclare item_7_1 = new QyqssdsReportsItemDeclare();
//				item_7_1.setItemID("" + js);
//				item_7_1.setItemValue((String) map.get("zmjz"));
//				item_7_1.setItemType("11");
//				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
//				js++;
//
//				QyqssdsReportsItemDeclare item_7_2 = new QyqssdsReportsItemDeclare();
//				item_7_2.setItemID("" + js);
//				item_7_2.setItemValue((String) map.get("jsjc"));
//				item_7_2.setItemType("11");
//				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
//				js++;
//
//				QyqssdsReportsItemDeclare item_7_3 = new QyqssdsReportsItemDeclare();
//				item_7_3.setItemID("" + js);
//				item_7_3.setItemValue((String) map.get("qcje"));
//				item_7_3.setItemType("11");
//				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
//				js++;
//
//				QyqssdsReportsItemDeclare item_7_4 = new QyqssdsReportsItemDeclare();
//				item_7_4.setItemID("" + js);
//				item_7_4.setItemValue((String) map.get("fzqcxy"));
//				item_7_4.setItemType("11");
//				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
//				js++;
//			}
//
//			else if ("5".equals(hc1)) {
//				QyqssdsReportsItemDeclare item_7_1 = new QyqssdsReportsItemDeclare();
//				item_7_1.setItemID("21");
//				item_7_1.setItemValue((String) map.get("zmjz"));
//				item_7_1.setItemType("11");
//				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
//
//				QyqssdsReportsItemDeclare item_7_3 = new QyqssdsReportsItemDeclare();
//				item_7_3.setItemID("22");
//				item_7_3.setItemValue((String) map.get("qcje"));
//				item_7_3.setItemType("11");
//				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
//
//				QyqssdsReportsItemDeclare item_7_4 = new QyqssdsReportsItemDeclare();
//				item_7_4.setItemID("23");
//				item_7_4.setItemValue((String) map.get("fzqcxy"));
//				item_7_4.setItemType("11");
//				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
//
//				js = 25;
//			} else {
				QyqssdsReportsItemDeclare item_7_1 = new QyqssdsReportsItemDeclare();
				item_7_1.setItemID("" + js);
				item_7_1.setItemValue((String) map.get("zmjz"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
				js++;

				QyqssdsReportsItemDeclare item_7_2 = new QyqssdsReportsItemDeclare();
				item_7_2.setItemID("" + js);
				item_7_2.setItemValue((String) map.get("jsjc"));
				item_7_2.setItemType("11");
				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
				js++;

				QyqssdsReportsItemDeclare item_7_3 = new QyqssdsReportsItemDeclare();
				item_7_3.setItemID("" + js);
				item_7_3.setItemValue((String) map.get("qcje"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
				js++;

				QyqssdsReportsItemDeclare item_7_4 = new QyqssdsReportsItemDeclare();
				item_7_4.setItemID("" + js);
				item_7_4.setItemValue((String) map.get("fzqcxy"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
				js++;

//			}

		}
		report.getTableContentList().put(table.getTableId(),QyqssdsUtil2014.cleanSpace(table));
		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QyqssdsReportsTableDeclare
	 */
	private List FGXtranslate2Page(QyqssdsReportsTableDeclare table) {
		List list = new ArrayList();
		Map map = table.getCellContentList();
		int js = 1; // ������
		for (int i = 1; i < 24; i++) {
//			if (i < 4) {
//				Map map1 = new HashMap();
//				map1.put("hc1", "" + i);
//
//				QyqssdsReportsItemDeclare item1_1 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String zmjz1 = item1_1.getItemValue();
//				map1.put("zmjz", zmjz1);
//				js++;
//
//				QyqssdsReportsItemDeclare item1_2 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String jsjc1 = item1_2.getItemValue();
//				map1.put("jsjc", jsjc1);
//				js++;
//
//				QyqssdsReportsItemDeclare item1_3 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String qcje1 = item1_3.getItemValue();
//				map1.put("qcje", qcje1);
//				js++;
//
//				QyqssdsReportsItemDeclare item1_4 = (QyqssdsReportsItemDeclare) map.get("" + js);
//				String fzqcxy1 = item1_4.getItemValue();
//				map1.put("fzqcxy", fzqcxy1);
//				js++;
//
//				list.add(map1);
//			} else if (i == 5) {
//				Map map5 = new HashMap();
//				map5.put("hc1", "5");
//				QyqssdsReportsItemDeclare item5_1 = (QyqssdsReportsItemDeclare) map.get("21");
//				String zmjz5 = item5_1.getItemValue();
//				map5.put("zmjz", zmjz5);
//				map5.put("jsjc", "����");
//				QyqssdsReportsItemDeclare item5_3 = (QyqssdsReportsItemDeclare) map.get("22");
//				String qcje5 = item5_3.getItemValue();
//				map5.put("qcje", qcje5);
//				QyqssdsReportsItemDeclare item5_4 = (QyqssdsReportsItemDeclare) map.get("23");
//				String fzqcxy5 = item5_4.getItemValue();
//				map5.put("fzqcxy", fzqcxy5);
//				QyqssdsReportsItemDeclare item5_5 = (QyqssdsReportsItemDeclare) map.get("24");
//				String xj5 = item5_5.getItemValue();
//				map5.put("xj", xj5);
//				js = 25;
//				list.add(map5);
//			} else {
				Map map1 = new HashMap();
				map1.put("hc1", "" + i);

				QyqssdsReportsItemDeclare item1_1 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String zmjz1 = item1_1.getItemValue();
				map1.put("zmjz", zmjz1);
				js++;

				QyqssdsReportsItemDeclare item1_2 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String jsjc1 = item1_2.getItemValue();
				map1.put("jsjc", jsjc1);
				js++;

				QyqssdsReportsItemDeclare item1_3 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String qcje1 = item1_3.getItemValue();
				map1.put("qcje", qcje1);
				js++;

				QyqssdsReportsItemDeclare item1_4 = (QyqssdsReportsItemDeclare) map.get("" + js);
				String fzqcxy1 = item1_4.getItemValue();
				map1.put("fzqcxy", fzqcxy1);
				js++;
				
				list.add(map1);
//			}
		}

		// ���Է������
		for (int h = 0; h < list.size(); h++) {
			HashMap mapcs = (HashMap) list.get(h);

			String hc = (String) mapcs.get("hc1");
			String zmjz = (String) mapcs.get("zmjz");
			String jsjc = (String) mapcs.get("jsjc");
			String qcje = (String) mapcs.get("qcje");
			String fzqcxy = (String) mapcs.get("fzqcxy");


			System.out.println("hc-" + hc + "   zmjz-" + zmjz + "   jsjc-"
					+ jsjc + "     qcje-" + qcje + "       fzqcxy-" + fzqcxy
					);

		}
		return list;

	}

}
