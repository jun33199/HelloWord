/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * 项目名称：smsb   
 * 类名称：QyqssdsFb3Processor2014   
 * 类描述：    附表三：剩余财产计算和分配明细表
 * 创建人：wangcy 
 * 创建时间：2014-2-17 下午12:26:15   
 * 修改人：wangcy   
 * 修改时间：2014-2-17 下午12:26:15   
 * 修改备注：   
 * @version  1.0
 */
public class QyqssdsFb3Processor2014 implements Processor {

	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
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
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		// 获取Action传递过来Fb3Form对象
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();

			/*----add by huohb,add skssq-----*/
			String ksrq =fb3Form.getQssbksrq();
			String jsrq =fb3Form.getQssbjsrq();
			String skssq=DateUtilToChinese.convertDate(ksrq, jsrq);
			fb3Form.setSkssq(skssq);
			
			// 创建QyqssdsReportsDeclare对象
			QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
			// 将form中的基本信息转入QyqssdsReportsDeclare report 中
			QyqssdsUtil2014.setQyqssdsReport(report, fb3Form);
			// 设置QyqssdsReportsTableDeclare的基本信息
			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			// 将QyqssdsReportsTableDeclare的基本信息置入QyqssdsReportsDeclare
			report.getTableContentList().put(table.getTableId(), table);

			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 调用查询方法进行查询
			iApp.querySingleTable(report);
			// 获取查询到的具体数据
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			// 将数据库中的数据翻译成页面所需数据格式
			fb3Form.setDataList(this.translate2Page(table));
			fb3Form.setMaxIndex(this.getMxDateMaxIndex(conn, report,fb3Form));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回QyqssdsFb3Form2014
		return fb3Form;
	}

	/**
	 * doSave 用于存储页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		// 得到Action传递过来QyqssdsFb3Form2014对象
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(fb3Form);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 调用saveSingleTable方法进行数据保存
			iApp.saveSingleTable(report);
			// 更新审核状态为“保存成功”
			iApp.updateCheckStatus(report, Constants.QYQSSDS_SHZT_SAVE);
			
			//更新所有申报开始日期和结束日期
			QyqssdsUtil2014.updateAllDate(conn, fb3Form);

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			// 取list
			fb3Form.setDataList(this.translate2Page(table));
			fb3Form.setMaxIndex(this.getMxDateMaxIndex(conn, report,fb3Form));
			
			//更新申报表中的申报状态标识为“已提交未审核”
			QyqssdsSaveAndCheck.updateFlag(conn,fb3Form);
			//更新所有申报开始日期和结束日期
			QyqssdsUtil2014.updateAllDate(conn, fb3Form);
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回fb3Form
		return fb3Form;
	}

	/**
	 * doCheck 用于校验表内关系
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doCheck(VOPackage vo) throws BaseException {
		// 得到Action传递过来QyqssdsFb3Form2014对象
		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(fb3Form);
			// 获取校验接口
			Checker checker = CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYQSSDS);
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 进行单表校验，并获取校验结果list,list为null或size等于零，表示校验通过
			List listSingle = checker.checkSingeTable(report,Constants.CREPORTS_SYSTEM_FS_SHANGMENG);
			fb3Form.setCheckList(listSingle);
			/* 如果校验通过，调用接口保存数据 */
			if (listSingle == null || (listSingle != null && listSingle.size() == 0)) {
				
				//网上申报的和上门审核通过的单表校验不保存数据
				if(fb3Form.getSqlx()=="0"||fb3Form.getSbShztbs()=="2"){
					
				}else{
					iApp.saveSingleTable(report);
				}
				// 更新审核状态为“单表审核通过”
				iApp.updateCheckStatus(report,Constants.QYQSSDS_SHZT_SINGLE_PASS);
				// 审核通过之后保存减免数据
				// this.saveJM(vo);
			} else if (listSingle.size() > 0) {
				// 单表审核未通过
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
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 检验成功返回cbmxbybqyForm
		return fb3Form;
	}

	/**
	 * doDelete 用于删除页面提交的详尽处理信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsFb3Form2014 fb3Form = (QyqssdsFb3Form2014) vo.getData();

		Connection conn = null;

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			// 将ActionForm中的数据结构转换，置入数据库接口参数要求的数据结构
			QyqssdsReportsDeclare report = this.translate2Interface(fb3Form);
			// 获取数据库应用接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			// 调用saveSingleTable方法进行数据删除
			iApp.deleteSingleTable(report);

			iApp.updateCheckStatus(report, "");

			QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
			table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
			table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
			table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
			// 取list
			fb3Form.setDataList(this.translate2Page(table));
			fb3Form.setMaxIndex(this.getMxDateMaxIndex(conn, report,fb3Form));
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}

		return fb3Form;
	}

	/**
	 * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构 页面数据结构-->接口数据结构
	 * 
	 * @param QyqssdsFb3Form2014
	 * @return 企业所得税清算报表申明对象
	 */
	private QyqssdsReportsDeclare translate2Interface(QyqssdsFb3Form2014 form) {

		// 企业所得税报表申明对象
		QyqssdsReportsDeclare report = new QyqssdsReportsDeclare();
		// 将form中的基本信息转入QyqssdsReportsDeclare对象中
		QyqssdsUtil2014.setQyqssdsReport(report, form);
		// 创建企业所得税报表内单表申明对象，并置入基本信息
		QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
		table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
		table.setTableName(CodeConstant.QYQSSDS_TABLE_NAME_2014_3);
		table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);

		// 把页面数据翻译成数据库接口的数据格式
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
                        //股东名称
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
                            //持有清算企业权益性投资比例（%）
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
                            //投资额
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
                            //分配的财产金额
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
                            //其中：确认为股息金额
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
	 * 将接口数据结构中的数据转换置入页面要求的数据结构 接口数据结构-->页面数据结构
	 * 
	 * @param QyqssdsReportsTableDeclare
	 * @return 页面表单数据的List对象
	 */
	private List translate2Page(QyqssdsReportsTableDeclare table) {
		// 创建List对象，用来存放页面表单数据
		ArrayList pagelist = new ArrayList();
		
		Iterator it = table.getCellContentList().keySet().iterator();
		System.out.println("----start---2014版 企业所得税清算 年度申报附表3----translate2Page");
		while (it.hasNext()) {
			QyqssdsReportsItemDeclare item = new QyqssdsReportsItemDeclare();
			String key = (String) it.next();
			item = (QyqssdsReportsItemDeclare) table.getCellContentList().get(key);
			HashMap map = new HashMap();
			
			map.put("hc", item.getItemID());
			map.put("ljje", item.getItemValue());
			
			pagelist.add(map);
//			Debug.out("行次："+item.getItemID()+"，域值："+item.getItemValue());
			System.out.println("行次："+item.getItemID()+"，域值："+item.getItemValue());
		}
		System.out.println("----over---2014版 企业所得税清算 年度申报附表3----translate2Page");
		return pagelist;
	}
    /**
     * 查询明细数据最大index
     *    根据填报人JSJDM，填报表ID获取对应明细数据的最大index
     * @param con Connection
     * @param report QyqssdsReportsDeclare
     * @return int
     * @throws BaseException
     */
    private int getMxDateMaxIndex(Connection con, QyqssdsReportsDeclare report, QyqssdsFb3Form2014 form) throws BaseException
    {
        int maxIndex = 0;
        //获取QyqssdsReportsTableDeclare对象
        QyqssdsReportsTableDeclare table = (QyqssdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.QYQSSDS_TABLE_ID_2014_3);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //sql语句
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
            throw new ApplicationException("查询明细数据最大index失败！");
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
