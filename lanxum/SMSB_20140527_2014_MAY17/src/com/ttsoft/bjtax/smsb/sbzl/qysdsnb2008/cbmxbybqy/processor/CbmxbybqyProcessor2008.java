/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.cbmxbybqy.web.CbmxbybqyForm2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
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
 * Description:������������ɱ�������ϸ��
 * </p>
 * 
 * @author liwenhua
 * @version 1.1
 */

public class CbmxbybqyProcessor2008 implements Processor {

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
		// �õ�Action���ݹ���CzqysdsjbForm����
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			// ����QysdsReportsDeclare����
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
			QysdsUtil2008.setQysdsReport(report, cbmxbybqyForm);
			// ����QysdsReportsTableDeclare�Ļ�����Ϣ
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_2_1);
			table.setTableName(CodeConstant.TABLE_NAME_2008_2_1);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);
			report.setVersion("20080101");

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���ò�ѯ�������в�ѯ
			iApp.querySingleTable(report);
			// ��ȡ��ѯ���ľ�������
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_2008_2_1);
			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			int[] arrs = { 1, 28 };
			cbmxbybqyForm.setDataList(this.translate2Page(QysdsUtil2008
					.putSpace(table, arrs)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����czqysdsjbForm
		return cbmxbybqyForm;
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
		// �õ�Action���ݹ���CzqysdsjbForm����
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this
					.translate2Interface(cbmxbybqyForm);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);
			//�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����cbmxbybqyForm
		return cbmxbybqyForm;
	}

	/**
	 * doCheck ����У����ڹ�ϵ
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */
	private Object doCheck(VOPackage vo) throws BaseException {
		// �õ�Action���ݹ���CzqysdsjbForm����
		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			// ��ActionForm�е����ݽṹת��,�������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this
					.translate2Interface(cbmxbybqyForm);
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,
					CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			cbmxbybqyForm.setCheckList(listSingle);
			/*���У��ͨ�������ýӿڱ�������*/
			if(listSingle==null ||( listSingle!=null && listSingle.size()==0))
			{
				iApp.saveSingleTable(report);
				//�������״̬Ϊ���������ͨ����
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SINGLE_PASS);
			}else if(listSingle.size()>0){
				//�������δͨ��
				iApp.updateCheckStatus(report,"");
			}

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return cbmxbybqyForm;
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

		CbmxbybqyForm2008 cbmxbybqyForm = (CbmxbybqyForm2008) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = this
					.translate2Interface(cbmxbybqyForm);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			// ����saveSingleTable������������ɾ��
			iApp.deleteSingleTable(report);
			iApp.updateCheckStatus(report,"");

			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_CBMXBYBQY);
			table.setTableName(CodeConstant.TABLE_NAME_CBMXBYBQY);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			table = (QysdsReportsTableDeclare) report.getTableContentList()
					.get(CodeConstant.TABLE_ID_CBMXBYBQY);
			// ȡlist
			int[] arrs = { 1, 28 };
			cbmxbybqyForm.setDataList(this.translate2Page(QysdsUtil2008
					.putSpace(table, arrs)));

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}

		return cbmxbybqyForm;
	}

	/**
	 * ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param cbmxbybqyForm
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(CbmxbybqyForm2008 form) {

		// ��ҵ����˰������������
		QysdsReportsDeclare report = new QysdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QysdsReportsDeclare������
		QysdsUtil2008.setQysdsReport(report, form);
		report.setVersion("20080101");
		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2008_2_1);
		table.setTableName(CodeConstant.TABLE_NAME_2008_2_1);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = form.getDataList();
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("je"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}

		report.getTableContentList().put(table.getTableId(),
				QysdsUtil2008.cleanSpace(table));

		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת��������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QysdsReportsTableDeclare
	 * @return ҳ������ݵ�List����
	 */
	private List translate2Page(QysdsReportsTableDeclare table) {
		// ����List�����������ҳ�������
		ArrayList pagelist = new ArrayList();
		int i = 0;

		Iterator it = table.getCellContentList().keySet().iterator();
		while (it.hasNext()) {
			QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QysdsReportsItemDeclare) table.getCellContentList()
					.get(key);
			HashMap map = new HashMap();
			map.put("hc", item.getItemID());
			map.put("je", item.getItemValue());
			pagelist.add(map);
			System.out.println(i++);
		}

		return pagelist;
	}

}
