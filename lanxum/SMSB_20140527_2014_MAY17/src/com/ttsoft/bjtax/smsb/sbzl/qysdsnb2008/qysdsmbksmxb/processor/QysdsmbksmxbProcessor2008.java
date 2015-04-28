/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.qysdsmbksmxb.processor;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.qysdsmbksmxb.web.QysdsmbksmxbForm2008;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2008.util.QysdsUtil2008;
import com.syax.creports.Constants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.check.Checker;
import com.syax.creports.check.CheckerFactory;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.Constants;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨</p>
 * @author zhangyj
 * @version 1.1
 */

public class QysdsmbksmxbProcessor2008
    implements Processor 
    {
	
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
	
	private Object doShow(VOPackage vo) throws BaseException {
		
		QysdsmbksmxbForm2008 nbForm = (QysdsmbksmxbForm2008) vo.getData();
		Connection conn = null;
		try
		{
//			��ȡ���ݿ�����
			conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report = new QysdsReportsDeclare();
			QysdsUtil2008.setQysdsReport(report, nbForm);
			QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
			table.setTableId(CodeConstant.TABLE_ID_2008_4);
			table.setTableName(CodeConstant.TABLE_NAME_2008_4);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			report.getTableContentList().put(table.getTableId(), table);
//			 ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.querySingleTable(report);
			int[] arrs={1,10,11,20,21,29,30,37,38,43,44,49,50,50};
			table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_4);
			nbForm.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arrs)));
		}catch(Exception ex)
		{
//			�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}	
		return nbForm;
	}
	
	/**
	   * doSave    ���ڱ���ҳ����Ϣ
	   * @param     vo ҵ�����
	   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException    ��������������׳��쳣��Ϣ
	   */
	private Object doSave(VOPackage vo) throws BaseException
	{
	
		QysdsmbksmxbForm2008 mbksmxbForm = (QysdsmbksmxbForm2008)vo.getData();
		Connection con = null;
		try
		{
			//��ȡ���ݿ�����
			con = SfDBResource.getConnection();
           //��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(mbksmxbForm);
//			��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp=AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);
			//mbksmxbForm.setDataList(this.translate2Page((QysdsReportsTableDeclare)report.getTableContentList().get("4���ó�������")));
			//�������״̬Ϊ������ɹ���
			iApp.updateCheckStatus(report,Constants.QYSDS_SHZT_SAVE);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally 
		{
		      SfDBResource.freeConnection(con);
	    }

		return mbksmxbForm;
		
	}
	 /**
	   * doDelete    ����ɾ��ҳ������
	   * @param     vo ҵ�����
	   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException    ��������������׳��쳣��Ϣ
	   */
	private Object doDelete(VOPackage vo) throws BaseException
	{
		QysdsmbksmxbForm2008 form = (QysdsmbksmxbForm2008)vo.getData();
		Connection conn = null;
		try
		{
			//��ȡ���ݿ�����

			conn = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(form);
//			��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp=AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			try
			{
				iApp.deleteSingleTable(report);	
				iApp.updateCheckStatus(report,"");
				QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
				table.setTableId(CodeConstant.TABLE_ID_2008_4);
				table.setTableName(CodeConstant.TABLE_NAME_2008_4);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
				table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_2008_4);
				int[] arrs={1,10,11,20,21,29,30,37,38,43,44,49,50,50};
				form.setDataList(this.translate2Page(QysdsUtil2008.putSpace(table,arrs)));
			}catch(Exception ex)
			{ 
				 ex.printStackTrace();
			}
		}catch(Exception ex)
		{
		    ex.printStackTrace();
		    throw ExceptionUtil.getBaseException(ex);
		}finally
		{
			SfDBResource.freeConnection(conn);
		}
		return form;
	}
	/**
	   * doCheck    ����У��ҳ������
	   * @param     vo ҵ�����
	   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	   * @throws BaseException    ��������������׳��쳣��Ϣ
	   */
	private Object doCheck(VOPackage vo)throws BaseException
	{
		QysdsmbksmxbForm2008 mbksmxbForm = (QysdsmbksmxbForm2008)vo.getData();
		Connection con = null;
		try
		{
//			�������ݿ�����
			con = SfDBResource.getConnection();
//			��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
			QysdsReportsDeclare report=this.translate2Interface(mbksmxbForm);
			/**
			 * ����У��
			 * ͨ�������ִ�У�
			 * δͨ���˳�ִ�У�ҳ����ʾУ������Ϣ
			 */
			Checker checker=CheckerFactory.getAInstance(con,CheckerFactory.ACCESS_MODEL_APP_QYSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(con,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			//���е���У�飬����ȡУ����list,listΪnull��size�����㣬��ʾУ��ͨ��
			List listSingle=checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			mbksmxbForm.setCheckList(listSingle);
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
		}catch(Exception ex)
		{
//			�׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally
		{
//			�ͷ����ݿ�����
			SfDBResource.freeConnection(con);
		}
		return mbksmxbForm;
	}
	
	/**
	 * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ
	 * ҳ�����ݽṹ-->�ӿ����ݽṹ
	 * @param ZcmxbForm 
	 * @return ��ҵ����˰������������
	 */
	private QysdsReportsDeclare translate2Interface(QysdsmbksmxbForm2008 nbForm)
	{
//		��ҵ����˰������������
		QysdsReportsDeclare report =new QysdsReportsDeclare();
		QysdsUtil2008.setQysdsReport(report, nbForm); //��report ����һϵ�е�����
//		��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table=new QysdsReportsTableDeclare();
		table.setTableId(CodeConstant.TABLE_ID_2008_4);
		table.setTableName(CodeConstant.TABLE_NAME_2008_4);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
		List list=nbForm.getDataList();
		
		for(int i=0;i<list.size();i++){
			System.out.println("=====" + list.get(i));
			
			HashMap map=(HashMap)list.get(i);
			
			String hc=(String)map.get("hc");
			if("1".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("1");
				item_1_1.setItemValue((String)map.get("nd"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("2");
				item_1_2.setItemValue((String)map.get("kshyle"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("3");
				item_1_3.setItemValue((String)map.get("kmbkse"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("4");
				item_1_4.setItemValue((String)map.get("hj"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("5");
				item_1_5.setItemValue((String)map.get("d2n"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("6");
				item_1_6.setItemValue((String)map.get("d3n"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("7");
				item_1_7.setItemValue((String)map.get("d4n"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);
				
				QysdsReportsItemDeclare item_1_8=new QysdsReportsItemDeclare();
				item_1_8.setItemID("8");
				item_1_8.setItemValue((String)map.get("d5n"));
				item_1_8.setItemType("11");
				table.getCellContentList().put(item_1_8.getItemID(),item_1_8);
				
				QysdsReportsItemDeclare item_1_9=new QysdsReportsItemDeclare();
				item_1_9.setItemID("9");
				item_1_9.setItemValue((String)map.get("ymbgksehj"));
				item_1_9.setItemType("11");
				table.getCellContentList().put(item_1_9.getItemID(),item_1_9);
				
				QysdsReportsItemDeclare item_1_10=new QysdsReportsItemDeclare();
				item_1_10.setItemID("10");
				item_1_10.setItemValue((String)map.get("bnkmbkse"));
				item_1_10.setItemType("11");
				table.getCellContentList().put(item_1_10.getItemID(),item_1_10);
				
			}	
			if("2".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("11");
				item_1_1.setItemValue((String)map.get("nd"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("12");
				item_1_2.setItemValue((String)map.get("kshyle"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("13");
				item_1_3.setItemValue((String)map.get("kmbkse"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("14");
				item_1_4.setItemValue((String)map.get("hj"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("15");
				item_1_6.setItemValue((String)map.get("d3n"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("16");
				item_1_7.setItemValue((String)map.get("d4n"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);
				
				QysdsReportsItemDeclare item_1_8=new QysdsReportsItemDeclare();
				item_1_8.setItemID("17");
				item_1_8.setItemValue((String)map.get("d5n"));
				item_1_8.setItemType("11");
				table.getCellContentList().put(item_1_8.getItemID(),item_1_8);
				
				QysdsReportsItemDeclare item_1_9=new QysdsReportsItemDeclare();
				item_1_9.setItemID("18");
				item_1_9.setItemValue((String)map.get("ymbgksehj"));
				item_1_9.setItemType("11");
				table.getCellContentList().put(item_1_9.getItemID(),item_1_9);
				
				QysdsReportsItemDeclare item_1_10=new QysdsReportsItemDeclare();
				item_1_10.setItemID("19");
				item_1_10.setItemValue((String)map.get("bnkmbkse"));
				item_1_10.setItemType("11");
				table.getCellContentList().put(item_1_10.getItemID(),item_1_10);
				
				QysdsReportsItemDeclare item_1_11=new QysdsReportsItemDeclare();
				item_1_11.setItemID("20");
				item_1_11.setItemValue((String)map.get("xynmbkse"));
				item_1_11.setItemType("11");
				table.getCellContentList().put(item_1_11.getItemID(),item_1_11);
			}	
			if("3".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("21");
				item_1_1.setItemValue((String)map.get("nd"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("22");
				item_1_2.setItemValue((String)map.get("kshyle"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("23");
				item_1_3.setItemValue((String)map.get("kmbkse"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("24");
				item_1_4.setItemValue((String)map.get("hj"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("25");
				item_1_5.setItemValue((String)map.get("d4n"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("26");
				item_1_6.setItemValue((String)map.get("d5n"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("27");
				item_1_7.setItemValue((String)map.get("ymbgksehj"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);
				
				QysdsReportsItemDeclare item_1_8=new QysdsReportsItemDeclare();
				item_1_8.setItemID("28");
				item_1_8.setItemValue((String)map.get("bnkmbkse"));
				item_1_8.setItemType("11");
				table.getCellContentList().put(item_1_8.getItemID(),item_1_8);
				
				QysdsReportsItemDeclare item_1_9=new QysdsReportsItemDeclare();
				item_1_9.setItemID("29");
				item_1_9.setItemValue((String)map.get("xynmbkse"));
				item_1_9.setItemType("11");
				table.getCellContentList().put(item_1_9.getItemID(),item_1_9);
			}	
			if("4".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("30");
				item_1_1.setItemValue((String)map.get("nd"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("31");
				item_1_2.setItemValue((String)map.get("kshyle"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("32");
				item_1_3.setItemValue((String)map.get("kmbkse"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("33");
				item_1_4.setItemValue((String)map.get("hj"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("34");
				item_1_5.setItemValue((String)map.get("d5n"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("35");
				item_1_6.setItemValue((String)map.get("ymbgksehj"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
				
				QysdsReportsItemDeclare item_1_7=new QysdsReportsItemDeclare();
				item_1_7.setItemID("36");
				item_1_7.setItemValue((String)map.get("bnkmbkse"));
				item_1_7.setItemType("11");
				table.getCellContentList().put(item_1_7.getItemID(),item_1_7);
				
				QysdsReportsItemDeclare item_1_8=new QysdsReportsItemDeclare();
				item_1_8.setItemID("37");
				item_1_8.setItemValue((String)map.get("xynmbkse"));
				item_1_8.setItemType("11");
				table.getCellContentList().put(item_1_8.getItemID(),item_1_8);
			}	
			if("5".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("38");
				item_1_1.setItemValue((String)map.get("nd"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("39");
				item_1_2.setItemValue((String)map.get("kshyle"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("40");
				item_1_3.setItemValue((String)map.get("kmbkse"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("41");
				item_1_4.setItemValue((String)map.get("hj"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("42");
				item_1_5.setItemValue((String)map.get("bnkmbkse"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("43");
				item_1_6.setItemValue((String)map.get("xynmbkse"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
			}
			if("6".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("44");
				item_1_1.setItemValue((String)map.get("nd"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
				
				QysdsReportsItemDeclare item_1_2=new QysdsReportsItemDeclare();
				item_1_2.setItemID("45");
				item_1_2.setItemValue((String)map.get("kshyle"));
				item_1_2.setItemType("11");
				table.getCellContentList().put(item_1_2.getItemID(),item_1_2);
				
				QysdsReportsItemDeclare item_1_3=new QysdsReportsItemDeclare();
				item_1_3.setItemID("46");
				item_1_3.setItemValue((String)map.get("kmbkse"));
				item_1_3.setItemType("11");
				table.getCellContentList().put(item_1_3.getItemID(),item_1_3);
				
				QysdsReportsItemDeclare item_1_4=new QysdsReportsItemDeclare();
				item_1_4.setItemID("47");
				item_1_4.setItemValue((String)map.get("hj"));
				item_1_4.setItemType("11");
				table.getCellContentList().put(item_1_4.getItemID(),item_1_4);
				
				QysdsReportsItemDeclare item_1_5=new QysdsReportsItemDeclare();
				item_1_5.setItemID("48");
				item_1_5.setItemValue((String)map.get("bnkmbkse"));
				item_1_5.setItemType("11");
				table.getCellContentList().put(item_1_5.getItemID(),item_1_5);
				
				QysdsReportsItemDeclare item_1_6=new QysdsReportsItemDeclare();
				item_1_6.setItemID("49");
				item_1_6.setItemValue((String)map.get("xynmbkse"));
				item_1_6.setItemType("11");
				table.getCellContentList().put(item_1_6.getItemID(),item_1_6);
			}
			if("7".equals(hc)){
				QysdsReportsItemDeclare item_1_1=new QysdsReportsItemDeclare();
				item_1_1.setItemID("50");
				item_1_1.setItemValue((String)map.get("xynmbkse"));
				item_1_1.setItemType("11");
				table.getCellContentList().put(item_1_1.getItemID(),item_1_1);
			}
			
			
		}
		report.getTableContentList().put(table.getTableId(),QysdsUtil2008.cleanSpace(table));
		//ceshi
		Iterator it=table.getCellContentList().keySet().iterator();
		while(it.hasNext()){
			QysdsReportsItemDeclare item=(QysdsReportsItemDeclare)table.getCellContentList().get((String)it.next());
			System.out.println(item.getItemID()+"   "+item.getItemValue());
		}
		return report;
	}
	/**
	 * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ
	 * �ӿ����ݽṹ-->ҳ�����ݽṹ
	 * @param QysdsReportsTableDeclare
	 * @return ��ҵ����˰������������
	 */
	private List translate2Page(QysdsReportsTableDeclare table)
	{
		List list = new ArrayList();
		if(true)
		{
			HashMap map = new HashMap();
			map.put("hc","7");
			map.put("xynmbkse", ((QysdsReportsItemDeclare)table.getCellContentList().get("50")).getItemValue());
			list.add(map);
		}
		for(int i=1;i<2;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("nd", ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-10))).getItemValue());
			map.put("kshyle", ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-9))).getItemValue());
			map.put("kmbkse", ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-8))).getItemValue());
			map.put("hj",  ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-7))).getItemValue());
			map.put("d2n", ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-6))).getItemValue());
			map.put("d3n",  ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-5))).getItemValue());
			map.put("d4n",  ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-4))).getItemValue());
			map.put("d5n",  ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-3))).getItemValue());
			map.put("ymbgksehj", ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-2))).getItemValue());
			map.put("bnkmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get(String.valueOf(11*i-1))).getItemValue());
			map.put("xynmbkse",  "*");
			list.add(map);
		}
		for(int i=2;i<3;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("nd", ((QysdsReportsItemDeclare)table.getCellContentList().get("11")).getItemValue());
			map.put("kshyle", ((QysdsReportsItemDeclare)table.getCellContentList().get("12")).getItemValue());
			map.put("kmbkse", ((QysdsReportsItemDeclare)table.getCellContentList().get("13")).getItemValue());
			map.put("hj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("14")).getItemValue());
			map.put("d2n", "*");
			map.put("d3n",  ((QysdsReportsItemDeclare)table.getCellContentList().get("15")).getItemValue());
			map.put("d4n",  ((QysdsReportsItemDeclare)table.getCellContentList().get("16")).getItemValue());
			map.put("d5n",  ((QysdsReportsItemDeclare)table.getCellContentList().get("17")).getItemValue());
			map.put("ymbgksehj", ((QysdsReportsItemDeclare)table.getCellContentList().get("18")).getItemValue());
			map.put("bnkmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("19")).getItemValue());
			map.put("xynmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("20")).getItemValue());
			list.add(map);
		}
		for(int i=3;i<4;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("nd", ((QysdsReportsItemDeclare)table.getCellContentList().get("21")).getItemValue());
			map.put("kshyle", ((QysdsReportsItemDeclare)table.getCellContentList().get("22")).getItemValue());
			map.put("kmbkse", ((QysdsReportsItemDeclare)table.getCellContentList().get("23")).getItemValue());
			map.put("hj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("24")).getItemValue());
			map.put("d2n", "*");
			map.put("d3n",  "*");
			map.put("d4n",  ((QysdsReportsItemDeclare)table.getCellContentList().get("25")).getItemValue());
			map.put("d5n",  ((QysdsReportsItemDeclare)table.getCellContentList().get("26")).getItemValue());
			map.put("ymbgksehj", ((QysdsReportsItemDeclare)table.getCellContentList().get("27")).getItemValue());
			map.put("bnkmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("28")).getItemValue());
			map.put("xynmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("29")).getItemValue());
			list.add(map);
		}
		for(int i=4;i<5;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("nd", ((QysdsReportsItemDeclare)table.getCellContentList().get("30")).getItemValue());
			map.put("kshyle", ((QysdsReportsItemDeclare)table.getCellContentList().get("31")).getItemValue());
			map.put("kmbkse", ((QysdsReportsItemDeclare)table.getCellContentList().get("32")).getItemValue());
			map.put("hj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("33")).getItemValue());
			map.put("d2n", "*");
			map.put("d3n",  "*");
			map.put("d4n",  "*");
			map.put("d5n",  ((QysdsReportsItemDeclare)table.getCellContentList().get("34")).getItemValue());
			map.put("ymbgksehj", ((QysdsReportsItemDeclare)table.getCellContentList().get("35")).getItemValue());
			map.put("bnkmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("36")).getItemValue());
			map.put("xynmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("37")).getItemValue());
			list.add(map);
		}
		for(int i=5;i<6;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("nd", ((QysdsReportsItemDeclare)table.getCellContentList().get("38")).getItemValue());
			map.put("kshyle", ((QysdsReportsItemDeclare)table.getCellContentList().get("39")).getItemValue());
			map.put("kmbkse", ((QysdsReportsItemDeclare)table.getCellContentList().get("40")).getItemValue());
			map.put("hj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("41")).getItemValue());
			map.put("d2n", "*");
			map.put("d3n",  "*");
			map.put("d4n",  "*");
			map.put("d5n",  "*");
			map.put("ymbgksehj","");
			map.put("bnkmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("42")).getItemValue());
			map.put("xynmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("43")).getItemValue());
			list.add(map);
		}
		for(int i=6;i<7;i++)
		{

			HashMap map = new HashMap();
			map.put("hc",String.valueOf(i));
			map.put("nd", ((QysdsReportsItemDeclare)table.getCellContentList().get("44")).getItemValue());
			map.put("kshyle", ((QysdsReportsItemDeclare)table.getCellContentList().get("45")).getItemValue());
			map.put("kmbkse", ((QysdsReportsItemDeclare)table.getCellContentList().get("46")).getItemValue());
			map.put("hj",  ((QysdsReportsItemDeclare)table.getCellContentList().get("47")).getItemValue());
			map.put("d2n", "*");
			map.put("d3n",  "*");
			map.put("d4n",  "*");
			map.put("d5n",  "*");
			map.put("ymbgksehj","*");
			map.put("bnkmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("48")).getItemValue());
			map.put("xynmbkse",  ((QysdsReportsItemDeclare)table.getCellContentList().get("49")).getItemValue());
			list.add(map);
		}
		return list;
	}
}