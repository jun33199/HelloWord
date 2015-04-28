/**
 *
 */
package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ssyhmx.processor;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.ssyhmx.web.SsyhmxForm;
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
 * Description:2008����������ҵ����˰����
 * </p>
 *
 * @author Ha Zhengze
 * @version 1.1
 */
public class SsyhmxProcessor implements Processor {
	  /**
	   * ʵ��Processor�ӿ�
	   * @param vo ҵ�����
	   * @return Object VOPackage������
	   * @throws BaseException ҵ���쳣
	   * 		1 ���������Ĳ������Ͳ���ʱ�׳�
	   * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	   * 	�����쳣�׳���EJB��process��������
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
	        throw new ApplicationException(
	            "�û�ִ����ϵͳ��֧�ֵķ�������.");
	    }

	    return result;
	  }

	  /**
	   * doShow��ʼ������ҳ����ϢҪ��
	   * @param vo ҵ�����
	   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private Object doShow(VOPackage vo) throws BaseException 
	  {
	    SsyhmxForm nbForm = (SsyhmxForm) vo.getData();
	    Connection conn = null;
		try {
			// ��ȡ���ݿ�ӿ�
			conn = SfDBResource.getConnection();
			
			// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report,nbForm);
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_5);
			table.setTableName(CodeConstant.TABLE_NAME_2008_5);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(),table);
			// ��ȡ���ݿ�ӿڣ�����query�����������ݲ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_5);
			int []arra={1,47};
			nbForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arra)));
			
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	    return nbForm;
	  }

	  /**
	   * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
	   * @param     vo ҵ�����
	   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException    ��������������׳��쳣��Ϣ
	   *
	   */

	  private Object doQuery(VOPackage vo) throws BaseException {

	   return null;
	  }

	  /**
		 * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
		 * @param   vo ҵ�����
		 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
		 * @throws BaseException ��������������׳��쳣��Ϣ
		 */
		
		private Object doSave(VOPackage vo) throws BaseException 
		{
			SsyhmxForm form = (SsyhmxForm) vo.getData();
			Connection conn = null;
			try {
				//��ȡ���ݿ�����
				conn = SfDBResource.getConnection();
				
				//��ActionForm�е����ݽṹ6ת���������ݿ�ӿڲ���Ҫ������ݽṹ
				QysdsReportsDeclare report=this.translate2Interface(form);
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.saveSingleTable(report);
//				�������״̬Ϊ������ɹ���
				iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
//				wlyd(form, conn);
			}catch (Exception ex) { 
				//�׳��쳣
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally {
				SfDBResource.freeConnection(conn);
			}
			return form;
		}
    //��С��΢����ҵ���϶�
	private void wlyd(SsyhmxForm form, Connection conn) throws SQLException {
		String sql = "select yz from sbdb.sb_jl_qysdssbb_zb_nd t where t.nsrjsjdm = '" + form.getJsjdm() + "' and t.sbdm = '" + CodeConstant.TABLE_ID_2008_ZB + "' and t.hc = '25'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		String yz = null;
		if(rs.next()){
			yz = rs.getString("yz");
		}
		rs.close();
		st.close();
		HashMap map = getHcMap(form);
		double zbh25 = getValue(yz);
		double h34 = getValue((String)map.get("34"));
		double h45 = getValue((String)map.get("45"));
		double h46 = getValue((String)map.get("46"));
		String h47 = (String)map.get("47");
		String wlrdbs = "yes"; //��С��΢����ҵ���϶���ʶ
		if(h34>0){
			if("01".equals(h47)){
				if(!(h46<=30000000&&h45<=100&&zbh25<=300000)){
					wlrdbs = "no";
				}
			}
			else
			{
				if(!(h46<=10000000&&h45<=80&&zbh25<=300000)){
					wlrdbs = "no";
				}
			}
		}
		form.setWlrdbs(wlrdbs);
	}
		private double getValue(String str){
			if(str!=null&&!"".equals(str.trim())){
				return Double.parseDouble(str);
			}
			return 0;
		}
		
		/**
		 * doCheck   ���ڴ洢ҳ���ύ���꾡������Ϣ
		 * @param   vo ҵ�����
		 * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
		 * @throws BaseException ��������������׳��쳣��Ϣ
		 */
		private Object doCheck(VOPackage vo) throws BaseException 
		{
			SsyhmxForm SsyhmxForm = (SsyhmxForm) vo.getData();
			Connection conn = null;
			try
	         {
				//�������ݿ�����
				conn = SfDBResource.getConnection();
				
				//��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
				QysdsReportsDeclare report=this.translate2Interface(SsyhmxForm);
				
				 //��ȡУ��ӿ�
				Checker checker=CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
				IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				
				//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
				List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
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
				SsyhmxForm.setCheckList(listSingle);
//				wlyd(SsyhmxForm, conn);
			}
			catch (Exception ex)
			{ 
				//�׳��쳣
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally
			{
				//�ͷ����ݿ�����
				SfDBResource.freeConnection(conn);
			}
			return SsyhmxForm;
		}

	  /**
	   * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
	   * @param    vo ҵ�����
	   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private Object doDelete(VOPackage vo) throws BaseException 
	  {
		  SsyhmxForm SsyhmxForm=(SsyhmxForm)vo.getData();
		  Connection conn = null;
			try {
				// ��ȡ���ݿ�ӿ�
				conn = SfDBResource.getConnection();
				
				// ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	            QysdsReportsDeclare report=this.translate2Interface(SsyhmxForm);
				
				//��ȡ���ݿ�ӿڣ�����delete������������ɾ��,iApp����һ��report����
				IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
				iApp.deleteSingleTable(report);
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_2008_5);
				table.setTableName(CodeConstant.TABLE_NAME_2008_5);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				int []arra={1,47};
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_5);
				SsyhmxForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arra)));
				
			}catch (Exception ex) { 
				//�׳��쳣
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			}
			finally {
				SfDBResource.freeConnection(conn);
			}
		  
	      return SsyhmxForm;
	  }

	  /**
	   * doUpdate  ���ڴ洢ҳ���ύ���꾡������Ϣ
	   * @param    vo ҵ�����
	   * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private Object doUpdate(VOPackage vo) throws BaseException {

	    SsyhmxForm nbForm = (SsyhmxForm) vo.getData();

	    return nbForm;
	  }

	  /**
	   * ��ʼ��
	   * @param nbForm ��������
	   * @throws BaseException ��������������׳��쳣��Ϣ
	   */

	  private void initForm(SsyhmxForm nbForm) throws BaseException 
	  {
	   }
	  /**
		 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
		 * ҳ�����ݽṹ-->�ӿ����ݽṹ
		 * @param SsyhmxForm 
		 * @return ��ҵ����˰������������
		 */
	  
	  private QysdsReportsDeclare translate2Interface(SsyhmxForm form){
			//��ҵ����˰������������
			QysdsReportsDeclare report =new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report,form);
			System.out.println("---sbrq: " + report.getSbrq());
			System.out.println("---getSkssksrq: " + report.getSkssksrq());
			System.out.println("---getSkssjsrq: " + report.getSkssjsrq());
			//��ҵ����˰�����ڵ�����������
			QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_5);
			table.setTableName(CodeConstant.TABLE_NAME_2008_5);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			
			List list=form.getDataList();
			for(int i=0;i<list.size();i++){
				    HashMap map=(HashMap)list.get(i);
					QysdsReportsItemDeclare item=new QysdsReportsItemDeclare();
					String hc=(String)map.get("hc");
					item.setItemID(hc);
					item.setItemValue((String)map.get("je"));
					item.setItemType("11");
					table.getCellContentList().put(item.getItemID(),item);
				  }	
			report.getTableContentList().put(table.getTableId(), QysdsUtil2008.cleanSpace(table));
			return report;
	  }
	  private HashMap getHcMap(SsyhmxForm form){
			List list=form.getDataList();
			HashMap reMap = new HashMap();
			for(int i=0;i<list.size();i++){
				    HashMap map=(HashMap)list.get(i);
					String hc=(String)map.get("hc");
					reMap.put(hc, (String)map.get("je"));
			}	
			return reMap;
	  }
	  /**
		 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ
		 * �ӿ����ݽṹ-->ҳ�����ݽṹ
		 * @param QysdsReportsTableDeclare
		 * @return ��ҵ����˰������������
		 */
		private List translate2Page(QysdsReportsTableDeclare table){
			
			List list = new ArrayList();
			HashMap map = (HashMap)table.getCellContentList();
			Iterator it=map.keySet().iterator();
			while(it.hasNext()){
				HashMap backMap = new HashMap();
				String key=(String)it.next();
				QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)map.get(key);
				backMap.put("hc", item.getItemID());
				backMap.put("je", item.getItemValue());
				list.add(backMap);
			}
			for(int i=0;i<list.size();i++)
			{
//				System.out.println("==2page list content==" + list.get(i));
			}

			return list;
		}
	  }
