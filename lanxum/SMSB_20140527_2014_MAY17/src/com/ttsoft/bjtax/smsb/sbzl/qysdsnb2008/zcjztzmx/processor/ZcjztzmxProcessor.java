/**
 *
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zcjztzmx.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QysdsnbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.zcjztzmx.web.ZcjztzmxForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:2008����������ҵ����˰����
 * </p>
 * 
 * @author Ha Zhengze
 * @version 1.1
 */
public class ZcjztzmxProcessor implements Processor {
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
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			QysdsUtil2008.setQysdsReport(report, ZcjztzmxForm);
			table.setTableId(CodeConstant.TABLE_ID_2008_10);
			table.setTableName(CodeConstant.TABLE_NAME_2008_10);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2008_10);

			int[] arra1 = { 1, 84 };
			ZcjztzmxForm.setDataList(this.FGXtranslate2Page(QysdsUtil2008
					.putSpace(table, arra1)));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return ZcjztzmxForm;
	}

	/**
	 * doQuery ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		return null;
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
		ZcjztzmxForm form = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(form);

			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			// �������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SAVE);
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this.translate2Interface(ZcjztzmxForm);

			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,
					Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			/* ���У��ͨ�������ýӿڱ������� */
			if (listSingle == null
					|| (listSingle != null && listSingle.size() == 0)) {
				iApp.saveSingleTable(report);
				// �������״̬Ϊ���������ͨ����
				iApp
						.updateCheckStatus(report,
								Constants.QYSDS_SHZT_SINGLE_PASS);
			} else if (listSingle.size() > 0) {
				// �������δͨ��
				iApp.updateCheckStatus(report, "");
			}
			ZcjztzmxForm.setCheckList(listSingle);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return ZcjztzmxForm;
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
		ZcjztzmxForm ZcjztzmxForm = (ZcjztzmxForm) vo.getData();
		Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();

			QysdsReportsDeclare report1 = this
					.translate2Interface(ZcjztzmxForm);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(report1);
			iApp.updateCheckStatus(report1, "");
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_10);
			table.setTableName(CodeConstant.TABLE_NAME_2008_10);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			int[] arra1 = { 1, 84 };
			table = (QysdsReportsTableDeclare) report1.getTableContentList()
					.get(CodeConstant.TABLE_ID_2008_10);
			ZcjztzmxForm.setDataList(this.FGXtranslate2Page(QysdsUtil2008
					.putSpace(table, arra1)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return ZcjztzmxForm;
	}

	/**
	 * doUpdate ���ڴ洢ҳ���ύ���꾡������Ϣ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doUpdate(VOPackage vo) throws BaseException {

		QysdsnbForm nbForm = (QysdsnbForm) vo.getData();

		return nbForm;
	}

	/**
	 * ��ʼ��
	 * 
	 * @param nbForm
	 *            ��������
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private void initForm(QysdsnbForm nbForm) throws BaseException {

	}

	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param ZcjztzmxForm
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(ZcjztzmxForm form) {
		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		QysdsUtil2008.setQysdsReport(report, form);
		// ��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		List list = form.getDataList();
		table.setTableId(CodeConstant.TABLE_ID_2008_10);
		table.setTableName(CodeConstant.TABLE_NAME_2008_10);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		int js = 1; // ������
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			String hc1 = (String) map.get("hc1");
			if (Integer.parseInt(hc1)<5) {
				QysdsReportsItemDeclare item_7_1 = new QysdsReportsItemDeclare();
				item_7_1.setItemID("" + js);
				item_7_1.setItemValue((String) map.get("gzxj"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
				js++;

				QysdsReportsItemDeclare item_7_2 = new QysdsReportsItemDeclare();
				item_7_2.setItemID("" + js);
				item_7_2.setItemValue((String) map.get("ghjf"));
				item_7_2.setItemType("11");
				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
				js++;

				QysdsReportsItemDeclare item_7_3 = new QysdsReportsItemDeclare();
				item_7_3.setItemID("" + js);
				item_7_3.setItemValue((String) map.get("zgflf"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
				js++;

				QysdsReportsItemDeclare item_7_4 = new QysdsReportsItemDeclare();
				item_7_4.setItemID("" + js);
				item_7_4.setItemValue((String) map.get("zgjyjf"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
				js++;

				QysdsReportsItemDeclare item_7_5 = new QysdsReportsItemDeclare();
				item_7_5.setItemID("" + js);
				item_7_5.setItemValue((String) map.get("xj"));
				item_7_5.setItemType("11");
				table.getCellContentList().put(item_7_5.getItemID(), item_7_5);
				js++;
			}

			else if ("5".equals(hc1)) {
				QysdsReportsItemDeclare item_7_1 = new QysdsReportsItemDeclare();
				item_7_1.setItemID("21");
				item_7_1.setItemValue((String) map.get("gzxj"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);

				QysdsReportsItemDeclare item_7_3 = new QysdsReportsItemDeclare();
				item_7_3.setItemID("22");
				item_7_3.setItemValue((String) map.get("zgflf"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);

				QysdsReportsItemDeclare item_7_4 = new QysdsReportsItemDeclare();
				item_7_4.setItemID("23");
				item_7_4.setItemValue((String) map.get("zgjyjf"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);

				QysdsReportsItemDeclare item_7_5 = new QysdsReportsItemDeclare();
				item_7_5.setItemID("24");
				item_7_5.setItemValue((String) map.get("xj"));
				item_7_5.setItemType("11");
				table.getCellContentList().put(item_7_5.getItemID(), item_7_5);
				js = 25;
			}
			else {
				QysdsReportsItemDeclare item_7_1 = new QysdsReportsItemDeclare();
				item_7_1.setItemID("" + js);
				item_7_1.setItemValue((String) map.get("gzxj"));
				item_7_1.setItemType("11");
				table.getCellContentList().put(item_7_1.getItemID(), item_7_1);
				js++;

				QysdsReportsItemDeclare item_7_2 = new QysdsReportsItemDeclare();
				item_7_2.setItemID("" + js);
				item_7_2.setItemValue((String) map.get("ghjf"));
				item_7_2.setItemType("11");
				table.getCellContentList().put(item_7_2.getItemID(), item_7_2);
				js++;

				QysdsReportsItemDeclare item_7_3 = new QysdsReportsItemDeclare();
				item_7_3.setItemID("" + js);
				item_7_3.setItemValue((String) map.get("zgflf"));
				item_7_3.setItemType("11");
				table.getCellContentList().put(item_7_3.getItemID(), item_7_3);
				js++;

				QysdsReportsItemDeclare item_7_4 = new QysdsReportsItemDeclare();
				item_7_4.setItemID("" + js);
				item_7_4.setItemValue((String) map.get("zgjyjf"));
				item_7_4.setItemType("11");
				table.getCellContentList().put(item_7_4.getItemID(), item_7_4);
				js++;

				QysdsReportsItemDeclare item_7_5 = new QysdsReportsItemDeclare();
				item_7_5.setItemID("" + js);
				item_7_5.setItemValue((String) map.get("xj"));
				item_7_5.setItemType("11");
				table.getCellContentList().put(item_7_5.getItemID(), item_7_5);
				js++;
			}

		}
		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2008.cleanSpace(table));
		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QysdsReportsTableDeclare
	 */
	private List FGXtranslate2Page(QysdsReportsTableDeclare table) {
		List list = new ArrayList();
		Map map = table.getCellContentList();
        int js = 1; //������
		for(int i=1;i<18;i++){
			if(i<5){
				Map map1 = new HashMap();
				map1.put("hc1", "" + i);
				
				QysdsReportsItemDeclare item1_1 = (QysdsReportsItemDeclare) map.get("" + js);
				String gzxj1 = item1_1.getItemValue();
				map1.put("gzxj", gzxj1);
				js++;
				
				QysdsReportsItemDeclare item1_2 = (QysdsReportsItemDeclare) map.get("" + js);
				String ghjf1 = item1_2.getItemValue();
				map1.put("ghjf", ghjf1);
				js++;
				
				QysdsReportsItemDeclare item1_3 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgflf1 = item1_3.getItemValue();
				map1.put("zgflf", zgflf1);
				js++;
				
				QysdsReportsItemDeclare item1_4 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgjyjf1 = item1_4.getItemValue();
				map1.put("zgjyjf", zgjyjf1);
				js++;
				
				QysdsReportsItemDeclare item1_5 = (QysdsReportsItemDeclare) map.get("" + js);
				String xj1 = item1_5.getItemValue();
				map1.put("xj", xj1);
				js++;
				list.add(map1);
			}
			else if(i==5){
				Map map5 = new HashMap();
				map5.put("hc1", "5");
				QysdsReportsItemDeclare item5_1 = (QysdsReportsItemDeclare) map.get("21");
				String gzxj5 = item5_1.getItemValue();
				map5.put("gzxj", gzxj5);
				map5.put("ghjf", "����");
				QysdsReportsItemDeclare item5_3 = (QysdsReportsItemDeclare) map.get("22");
				String zgflf5 = item5_3.getItemValue();
				map5.put("zgflf", zgflf5);
				QysdsReportsItemDeclare item5_4 = (QysdsReportsItemDeclare) map.get("23");
				String zgjyjf5 = item5_4.getItemValue();
				map5.put("zgjyjf", zgjyjf5);
				QysdsReportsItemDeclare item5_5 = (QysdsReportsItemDeclare) map.get("24");
				String xj5 = item5_5.getItemValue();
				map5.put("xj", xj5);
				js = 25;
				list.add(map5);
			}
			else{
				Map map1 = new HashMap();
				map1.put("hc1", "" + i);
				
				QysdsReportsItemDeclare item1_1 = (QysdsReportsItemDeclare) map.get("" + js);
				String gzxj1 = item1_1.getItemValue();
				map1.put("gzxj", gzxj1);
				js++;
				
				QysdsReportsItemDeclare item1_2 = (QysdsReportsItemDeclare) map.get("" + js);
				String ghjf1 = item1_2.getItemValue();
				map1.put("ghjf", ghjf1);
				js++;
				
				QysdsReportsItemDeclare item1_3 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgflf1 = item1_3.getItemValue();
				map1.put("zgflf", zgflf1);
				js++;
				
				QysdsReportsItemDeclare item1_4 = (QysdsReportsItemDeclare) map.get("" + js);
				String zgjyjf1 = item1_4.getItemValue();
				map1.put("zgjyjf", zgjyjf1);
				js++;
				
				QysdsReportsItemDeclare item1_5 = (QysdsReportsItemDeclare) map.get("" + js);
				String xj1 = item1_5.getItemValue();
				map1.put("xj", xj1);
				js++;
				list.add(map1);
			}
		}

		// ���Է������
		for (int h = 0; h < list.size(); h++) {
			HashMap mapcs = (HashMap) list.get(h);

			String hc = (String) mapcs.get("hc1");
			String gzxj = (String) mapcs.get("gzxj");
			String ghjf = (String) mapcs.get("ghjf");
			String zgflf = (String) mapcs.get("zgflf");
			String zgjyjf = (String) mapcs.get("zgjyjf");
			String xj = (String) mapcs.get("xj");

			System.out.println("hc-" + hc + "   gzxj-" + gzxj + "   ghjf-"
					+ ghjf + "     zgflf-" + zgflf + "       zgjyjf-" + zgjyjf
					+ "   xj-" + xj);

		}
		return list;

	}

}
