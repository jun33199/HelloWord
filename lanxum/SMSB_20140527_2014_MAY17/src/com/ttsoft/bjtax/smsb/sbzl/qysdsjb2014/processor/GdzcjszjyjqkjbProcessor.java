package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.processor;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.GdzcjszjyjqkjbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web.ZfjgqysdsjbForm;
import java.util.GregorianCalendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;

import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import java.util.HashMap;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import java.sql.Connection;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.persistent.AppAccessFactory;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.syax.creports.Constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import java.sql.Statement;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import java.text.DecimalFormat;
import com.syax.creports.util.Arith;
import com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.QysdsUtil2014;




public class GdzcjszjyjqkjbProcessor implements Processor
{
   
    public GdzcjszjyjqkjbProcessor()
    {
    }

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

    public Object process(VOPackage vo) throws BaseException
    {

        Object result = null;
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case CodeConstant.SMSB_QUERYACTION:
            result = doQuery(vo);
            break;
        case CodeConstant.SMSB_SAVEACTION:
            result = doSave(vo);
            break;
        case CodeConstant.SMSB_DELETEACTION:
            result = doDelete(vo);
            break;

        default:
            throw new ApplicationException("用户执行了系统不支持的方法或功能.");
        }

        return result;
    }



    /**
     * doQuery 用于返回页面索要查询的详尽信息
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     *
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) vo.getData();
        Connection conn = null;
        
        UserData userData=(UserData)vo.getUserData();
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(gdzcjszjyjqkjbForm.getSkssjsrq()));
		// 获取税款所属年度
		// String sknd = this.getNd(jd, getsbnd, CzzssdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// 从页面中取得税款所属期和年度
		String nd = gdzcjszjyjqkjbForm.getSkssksrq().substring(0, 4);
		// 设置季度
		gdzcjszjyjqkjbForm.setQh(jd);
		// 设置年度
		gdzcjszjyjqkjbForm.setSknd(nd);
		gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		gdzcjszjyjqkjbForm.setLrr(userData.getYhid());
        try {
//			获取数据库连接
            conn = SfDBResource.getConnection();
            System.out.println("jsjdm: "+gdzcjszjyjqkjbForm.getJsjdm());
            gdzcjszjyjqkjbForm=(GdzcjszjyjqkjbForm)QysdsNewUtil.queryDjxxByInterfaceDJ(conn, gdzcjszjyjqkjbForm, userData);
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsNewUtil.setQysdsReport(report, gdzcjszjyjqkjbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
            table.setTableName(CodeConstant.TABLE_NAME_GDZCJSZJYJQK_2014);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
            report.getTableContentList().put(table.getTableId(), table);
//			 获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.querySingleTable(report);
            int[] arrs = {1, 50};

            table = (QysdsReportsTableDeclare) report.getTableContentList().get(
                    CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
            List listgd = (List)this.translate2Page(QysdsNewUtil.putSpace(
                    table, arrs)).get("GD");
            gdzcjszjyjqkjbForm.setGdzcjszjyjqkjbList(listgd);
            
            //从数据库中获取所属行业代码
            this.getSshydm(gdzcjszjyjqkjbForm,table);

        } catch (Exception ex) {
//			抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }

        return gdzcjszjyjqkjbForm;
    }
    /**
     * 从数据库中获取所属行业代码，hc为“999”
     * @param table
     * @return
     */
    private void getSshydm(GdzcjszjyjqkjbForm jbForm,QysdsReportsTableDeclare table) {
    	String yz="";
		if (table.getCellContentList().get("999") != null)
        {
			QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) table
					.getCellContentList().get("999");
			yz=qrtid.getItemValue();
			if (yz == null) {
				yz= "";
			} 
		}
		jbForm.setGjbzhydm(yz);
		if(!"".equals(yz.trim())){
			jbForm.setSshy(yz);
		}
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
    	
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) vo.getData();    	
        Connection con = null;
        UserData userData=(UserData)vo.getUserData();
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(gdzcjszjyjqkjbForm.getSkssjsrq()));
		// 获取税款所属年度
		// String sknd = this.getNd(jd, getsbnd, CzzssdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// 从页面中取得税款所属期和年度
		String nd = gdzcjszjyjqkjbForm.getSkssksrq().substring(0, 4);
		// 设置季度
		gdzcjszjyjqkjbForm.setQh(jd);
		// 设置年度
		gdzcjszjyjqkjbForm.setSknd(nd);
		gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		gdzcjszjyjqkjbForm.setLrr(userData.getYhid());
		
        System.out.println("jd: "+jd+"nd: "+nd+"jsjdm: "+gdzcjszjyjqkjbForm.getJsjdm());
        System.out.println("NSRMC: "+gdzcjszjyjqkjbForm.getNsrmc());
        try {
            //获取数据库连接
            con = SfDBResource.getConnection();
            
            gdzcjszjyjqkjbForm=(GdzcjszjyjqkjbForm)QysdsNewUtil.queryDjxxByInterfaceDJ(con, gdzcjszjyjqkjbForm, userData);
            //保存从前端获取的所属行业代码
            gdzcjszjyjqkjbForm.setSshy(gdzcjszjyjqkjbForm.getGjbzhydm());
            //将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = this.translate2Interface(
                    gdzcjszjyjqkjbForm);
//			获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(con,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.saveSingleTable(report);
//			更新审核状态为“保存成功”
            iApp.updateCheckStatus(report, Constants.QYSDS_SHZT_SAVE);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(con);
        }
        return gdzcjszjyjqkjbForm;
    }

    /**
     * 将接口数据结构中的数据转换置入页面要求的数据结构
     * 接口数据结构-->页面数据结构
     * @param QysdsReportsTableDeclare
     * @return 企业所得税报表申明对象
     */  
    private HashMap translate2Page(QysdsReportsTableDeclare table) {
        HashMap backMap = new HashMap();
        List pagelist = new ArrayList();
        int DB_HC;
        for(int i=0;i<13;i++){
        	HashMap rowmap=new HashMap();
        	for(int j=1;j<=11;j++){
    			String yz="";
    			DB_HC=i*11+j;
    			
				if (table.getCellContentList().get(DB_HC+"") != null)
                {
					QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) table
							.getCellContentList().get(DB_HC+"");
					yz=qrtid.getItemValue();
					if (yz == null) {
						yz= "";
					} 
				}
				System.out.println("query yz: "+yz);
				rowmap.put(new GdzcjszjyjqkjbForm().getSb_columns()[j-1], yz);
				
        	}
        	rowmap.put("hc", String.valueOf(i+1));
        	pagelist.add(rowmap);
        }
        
        
        backMap.put("GD", pagelist);
        return backMap;
    }
    /**
     * 将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
     * 页面数据结构-->接口数据结构
     * @param 
     * @return 企业所得税报表申明对象
     */
    
    private QysdsReportsDeclare translate2Interface(GdzcjszjyjqkjbForm jbForm) {
//		企业所得税报表申明对象
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        QysdsNewUtil.setQysdsReport(report, jbForm); //对report 进行一系列的设置
//		企业所得税报表内单表申明对象
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
        table.setTableName(CodeConstant.TABLE_NAME_GDZCJSZJYJQK_2014);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        List list = jbForm.getGdzcjszjyjqkjbList(); //存放固定行的LIST
        int DB_HC;
        for(int i=0;i<list.size();i++){
        	HashMap rowmap=(HashMap) list.get(i);
        	for(int j=1;j<=11;j++){
        		
    			String yz=(String) rowmap.get(new GdzcjszjyjqkjbForm().getSb_columns()[j-1]);
    			DB_HC=i*11+j;
    			if(yz==null||"".equals(yz.trim())) continue;
    			
    			
    			System.out.println(".............zhangj..yz: "+yz+"dbhc: "+DB_HC);
                //设置固定行第一列数据
                QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                item.setItemID(DB_HC+"");
                item.setItemValue(yz);
                item.setItemType("11");
                table.getCellContentList().put(item.getItemID(), item);
        	}
        }
        

        saveSshy(jbForm,table);//将从页面传来的所属行业代码存入数据库中，hc为“999”(与以上表单数据存在一张表中)
        
        report.getTableContentList().put(table.getTableId(),
                QysdsNewUtil.cleanSpace(table));
        return report;
	}
    /**
     * 将从页面传来的所属行业代码存入数据库中，hc为“999”(与以上表单数据存在一张表中)
     * @param jbForm
     * @param table
     */
    private void saveSshy(GdzcjszjyjqkjbForm jbForm,QysdsReportsTableDeclare table){
        QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
        item.setItemID("999");
        item.setItemValue(jbForm.getSshy());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);
    }
    
    /**
     * doQuery 用于返回页面索要查询的详尽信息
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     *
     */
    private Object doDelete(VOPackage vo) throws BaseException
    {
    	GdzcjszjyjqkjbForm gdzcjszjyjqkjbForm = (GdzcjszjyjqkjbForm) vo.getData();
        Connection conn = null;
        
        UserData userData=(UserData)vo.getUserData();
		String jd = QysdsNewUtil.preQuarter(SfDateUtil.getDate(gdzcjszjyjqkjbForm.getSkssjsrq()));
		// 获取税款所属年度
		// String sknd = this.getNd(jd, getsbnd, CzzssdsjbForm.getSbrq());
		// String nd = (String)getsbnd.get(Skssrq.SKSSRQ_ND);
		// 从页面中取得税款所属期和年度
		String nd = gdzcjszjyjqkjbForm.getSkssksrq().substring(0, 4);
		// 设置季度
		gdzcjszjyjqkjbForm.setQh(jd);
		// 设置年度
		gdzcjszjyjqkjbForm.setSknd(nd);
		gdzcjszjyjqkjbForm.setBbqlx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		gdzcjszjyjqkjbForm.setLrr(userData.getYhid());
        try {
//			获取数据库连接
            conn = SfDBResource.getConnection();
            
            gdzcjszjyjqkjbForm=(GdzcjszjyjqkjbForm)QysdsNewUtil.queryDjxxByInterfaceDJ(conn, gdzcjszjyjqkjbForm, userData);
//			将ActionForm中的数据结构转换置入数据库接口参数要求的数据结构
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            QysdsNewUtil.setQysdsReport(report, gdzcjszjyjqkjbForm);
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_GDZCJSZJYJQK_2014);
            table.setTableName(CodeConstant.TABLE_NAME_GDZCJSZJYJQK_2014);
            table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR);
            report.getTableContentList().put(table.getTableId(), table);
//			 获取数据库接口，调用save方法进行数据保存
            IAppAccess iApp = AppAccessFactory.getAInstance(conn,
                    AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(report);
            
        } catch (Exception ex) {
//			抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }

        return gdzcjszjyjqkjbForm;
    }
}
