/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsfb3.web.QyqssdsFb3Form2014;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.DateUtilToChinese;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsSaveAndCheck;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * 
 * ��Ŀ���ƣ�smsb   
 * �����ƣ�QyqssdsFb3Processor2014   
 * ��������    ��������ʣ��Ʋ�����ͷ�����ϸ��
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-17 ����12:26:15   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-17 ����12:26:15   
 * �޸ı�ע��   
 * @version  1.0
 */
public class QyqssdsFb3Processor2014 implements Processor {

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
		// ��ȡAction���ݹ���Fb3Form����
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();

			/*----add by huohb,add skssq-----*/
			String ksrq =fb3Form.getQssbksrq();
			String jsrq =fb3Form.getQssbjsrq();
			String skssq=DateUtilToChinese.convertDate(ksrq, jsrq);
			fb3Form.setSkssq(skssq);
			
			// ����QyqssdsReportsDeclare����
			QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
			// ��form�еĻ�����Ϣת��QyqssdsReportsDeclare report ��
			QyqssdsUtil2014.setQyqssdsReport(report, fb3Form);
			// ����QyqssdsReportsTableDeclare�Ļ�����Ϣ
			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			// ��QyqssdsReportsTableDeclare�Ļ�����Ϣ����QyqssdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ���ò�ѯ�������в�ѯ
			iApp.querySingleTable(report);
			// ��ȡ��ѯ���ľ�������
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			// �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
			fb3Form.setDataList(this.translate2Page(table));
			fb3Form.setMaxIndex(this.getMxDateMaxIndex(conn, report,fb3Form));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����QyqssdsFb3Form2014
		return fb3Form;
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
		// �õ�Action���ݹ���QyqssdsFb3Form2014����
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(fb3Form);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ����saveSingleTable�����������ݱ���
			iApp.saveSingleTable(report);
			// �������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SAVE);
			
			//���������걨��ʼ���ںͽ�������
			QyqssdsUtil2014.updateAllDate(conn, fb3Form);

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			// ȡlist
			fb3Form.setDataList(this.translate2Page(table));
			fb3Form.setMaxIndex(this.getMxDateMaxIndex(conn, report,fb3Form));
			
			//�����걨���е��걨״̬��ʶΪ�����ύδ��ˡ�
			QyqssdsSaveAndCheck.updateFlag(conn,fb3Form);
			//���������걨��ʼ���ںͽ�������
			QyqssdsUtil2014.updateAllDate(conn, fb3Form);
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����fb3Form
		return fb3Form;
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
		// �õ�Action���ݹ���QyqssdsFb3Form2014����
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(fb3Form);
			// ��ȡУ��ӿ�
			Checker checker = CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			fb3Form.setCheckList(listSingle);
			/* ���У��ͨ�������ýӿڱ������� */
			if (listSingle == null || (listSingle != null && listSingle.size() == 0)) {
				
				//�����걨�ĺ��������ͨ���ĵ���У�鲻��������
				if(fb3Form.getSqlx()=="0"||fb3Form.getSbShztbs()=="2"){
					
				}else{
					iApp.saveSingleTable(report);
				}
				// �������״̬Ϊ���������ͨ����
				iApp.updateCheckStatus(report,Constants.QYQSSDS_SHZT_SINGLE_PASS);
				// ���ͨ��֮�󱣴��������
				// this.saveJM(vo);
			} else if (listSingle.size() > 0) {
				// �������δͨ��
				iApp.updateCheckStatus(report, "");
			}
			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			fb3Form.setDataList(this.translate2Page(table));
			//fb3Form.setMaxIndex(this.getMxDateMaxIndex(conn, report,fb3Form));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����cbmxbybqyForm
		return fb3Form;
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

		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			// ��ActionForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
			QyqssdsReportsDeclare report = this.translate2Interface(fb3Form);
			// ��ȡ���ݿ�Ӧ�ýӿ�
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// ����saveSingleTable������������ɾ��
			iApp.deleteSingleTable(report);

			iApp.updateCheckStatus(report, "");

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			// ȡlist
			fb3Form.setDataList(this.translate2Page(table));
			fb3Form.setMaxIndex(this.getMxDateMaxIndex(conn, report,fb3Form));
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}

		return fb3Form;
	}

	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * 
	 * @param QyqssdsFb3Form2014
	 * @return ��ҵ����˰���㱨����������
	 */
	private QyqssdsReportsDeclare translate2Interface(QyqssdsFb3Form2014 form) {

		// ��ҵ����˰������������
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		// ��form�еĻ�����Ϣת��QyqssdsReportsDeclare������
		QyqssdsUtil2014.setQyqssdsReport(report, form);
		// ������ҵ����˰�����ڵ����������󣬲����������Ϣ
		QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
		table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
		table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);

		// ��ҳ�����ݷ�������ݿ�ӿڵ����ݸ�ʽ
		List list = form.getDataList();
		List syccfp_List = form.getSyccfpList();
		
		System.out.println("size 1" + list.size());
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
			item.setItemID((String) map.get("hc"));
			item.setItemValue((String) map.get("ljje"));
			item.setItemType("11");
			table.getCellContentList().put(item.getItemID(), item);
		}
		
		System.out.println("size " + syccfp_List.size());
		form.setMaxIndex(syccfp_List.size());
		for (int i = 0; i < syccfp_List.size(); i++) {
			HashMap map = (HashMap) syccfp_List.get(i);
            boolean flag =  true;
            for(int j = 13; j < 18; j++)
            {
            	QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
            	String id = String.valueOf(j) + "." + String.valueOf(i + 1);
            	item.setItemID(id);
            	System.out.println(id+"   ++++++++++++++++++++++++++++");
                switch(j)
                {
                    case 13:
                        //�ɶ�����
                        if(map.get("gdmc") == null || map.get("gdmc").equals(""))
                        {
                            flag = false;
                            item.setItemValue("");
                        }
                        else
                        {
                            item.setItemValue((String) map.get("gdmc"));
                            item.setItemType("11");
                        }
                        break;
                    case 14:
                        if(flag)
                        {
                            //����������ҵȨ����Ͷ�ʱ�����%��
                            item.setItemValue((String) map.get("tzbl"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 15:
                        if(flag)
                        {
                            //Ͷ�ʶ�
                            item.setItemValue((String) map.get("tze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 16:
                        if(flag)
                        {
                            //����ĲƲ����
                            item.setItemValue((String) map.get("ccje"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 17:
                        if(flag)
                        {
                            //���У�ȷ��Ϊ��Ϣ���
                            item.setItemValue((String) map.get("gxje"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                }
                System.out.println(item.getItemValue()+"   ===================================");
    			table.getCellContentList().put(item.getItemID(), item);
            }
		}
		
		report.getTableContentList().put(table.getTableId(),QyqssdsUtil2014.cleanSpace(table));
		return report;
	}

	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * 
	 * @param QyqssdsReportsTableDeclare
	 * @return ҳ������ݵ�List����
	 */
	private List translate2Page(QyqssdsReportsTableDeclare table) {
		// ����List�����������ҳ�������
		ArrayList pagelist = new ArrayList();
		
		Iterator it = table.getCellContentList().keySet().iterator();
		System.out.println("----start---2014�� ��ҵ����˰���� ����걨����3----translate2Page");
		while (it.hasNext()) {
			QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QyqssdsReportsItemDeclare) table.getCellContentList().get(key);
			HashMap map = new HashMap();
			
			map.put("hc", item.getItemID());
			map.put("ljje", item.getItemValue());
			
			pagelist.add(map);
//			Debug.out("�дΣ�"+item.getItemID()+"����ֵ��"+item.getItemValue());
			System.out.println("�дΣ�"+item.getItemID()+"����ֵ��"+item.getItemValue());
		}
		System.out.println("----over---2014�� ��ҵ����˰���� ����걨����3----translate2Page");
		return pagelist;
	}
    /**
     * ��ѯ��ϸ�������index
     *    �������JSJDM�����ID��ȡ��Ӧ��ϸ���ݵ����index
     * @param con Connection
     * @param report QyqssdsReportsDeclare
     * @return int
     * @throws BaseException
     */
    private int getMxDateMaxIndex(Connection con, QyqssdsReportsDeclare report, QyqssdsFb3Form2014 form) throws BaseException
    {
        int maxIndex = 0;
        //��ȡQyqssdsReportsTableDeclare����
        QyqssdsReportsTableDeclare table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //sql���
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from SBDB.SB_JL_QYQSSDSSBB_CB_ND ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("' ");
        sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
        sql.append("and qh = '").append(form.getQh()).append("' ");
        //sql.append("and sknd = '").append(form.getSknd()).append("' ");

        System.out.println("sql:\n" + sql.toString());
        
        try
        {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            maxIndex = rs.getInt(1);
        }
        catch(Exception ex)
        {
            throw new ApplicationException("��ѯ��ϸ�������indexʧ�ܣ�");
        }finally{
    		try {
    			if(rs!=null){
					rs.close();
    			}
    			if(pstmt!=null){
    				pstmt.close();
    			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

        return maxIndex;
    }
}
